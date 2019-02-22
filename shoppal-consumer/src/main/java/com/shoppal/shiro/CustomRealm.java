package com.shoppal.shiro;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shoppal.consumer.shiro.PermService;
import com.shoppal.consumer.shiro.RoleService;
import com.shoppal.consumer.shiro.ShiroUserService;
import com.shoppal.model.ShiroUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Set;

public class CustomRealm  extends AuthorizingRealm {

    @Reference
    private ShiroUserService userService;
    @Reference
    private RoleService roleService;
    @Reference
    private PermService permService;

    //告诉shiro如何根据获取到的用户信息中的密码和盐值来校验密码
    {
        //设置用于匹配密码的CredentialsMatcher
        HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
        hashMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        hashMatcher.setStoredCredentialsHexEncoded(false);
        hashMatcher.setHashIterations(1024);
        this.setCredentialsMatcher(hashMatcher);
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        ShiroUser user = (ShiroUser) getAvailablePrincipal(principals);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        System.out.println("获取角色信息："+user.getRoles());
        System.out.println("获取权限信息："+user.getPerms());
        info.setRoles(user.getRoles());
        info.setStringPermissions(user.getPerms());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }

        ShiroUser userDB = userService.findUserByName(username);

//        ShiroUser userDB = new ShiroUser();
//        userDB.setUname("admin");
//        userDB.setNick("admin"+"NICK");
//        userDB.setPwd("J/ms7qTJtqmysekuY8/v1TAS+VKqXdH5sB7ulXZOWho=");//密码明文是123456
//        userDB.setSalt("wxKYXuTPST5SG0jMQzVPsg==");//加密密码的盐值
//        userDB.setUid(new Random().nextLong());//随机分配一个id
//        userDB.setCreated(new Date());
        if (userDB == null) {
            throw new UnknownAccountException("No account found for admin [" + username + "]");
        }

        //查询用户的角色和权限存到SimpleAuthenticationInfo中，这样在其它地方
        //SecurityUtils.getSubject().getPrincipal()就能拿出用户的所有信息，包括角色和权限
        Set<String> roles = roleService.getRolesByUserId(userDB.getUid());
//        Set<String> roles = new HashSet<>();
//        //三种编程语言代表三种角色：js程序员、java程序员、c++程序员
//        roles.add("js");
//        roles.add("java");
//        roles.add("cpp");
        Set<String> perms = permService.getPermsByUserId(userDB.getUid());
//        Set<String> perms = new HashSet<>();
//        //三种编程语言代表三种角色：js程序员、java程序员、c++程序员
//        //js程序员的权限
//        perms.add("html:edit");
//        //c++程序员的权限
//        perms.add("hardware:debug");
//        //java程序员的权限
//        perms.add("mvn:install");
//        perms.add("mvn:clean");
        perms.add("mvn:test");
        userDB.getRoles().addAll(roles);
        userDB.getPerms().addAll(perms);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userDB, userDB.getPwd(), getName());
        if (userDB.getSalt() != null) {
            info.setCredentialsSalt(ByteSource.Util.bytes(userDB.getSalt()));
        }

        return info;
    }
}
