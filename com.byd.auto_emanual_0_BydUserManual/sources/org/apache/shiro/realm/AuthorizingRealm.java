package org.apache.shiro.realm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.PermissionResolverAware;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.RolePermissionResolverAware;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.config.Ini;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.Initializable;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public abstract class AuthorizingRealm extends AuthenticatingRealm implements Authorizer, Initializable, PermissionResolverAware, RolePermissionResolverAware {
    private static final String DEFAULT_AUTHORIZATION_CACHE_SUFFIX = ".authorizationCache";
    private Cache<Object, AuthorizationInfo> authorizationCache;
    private String authorizationCacheName;
    private boolean authorizationCachingEnabled;
    private PermissionResolver permissionResolver;
    private RolePermissionResolver permissionRoleResolver;
    private static final Logger log = LoggerFactory.getLogger(AuthorizingRealm.class);
    private static final AtomicInteger INSTANCE_COUNT = new AtomicInteger();

    protected abstract AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection);

    protected Object getAuthorizationCacheKey(PrincipalCollection principalCollection) {
        return principalCollection;
    }

    public AuthorizingRealm() {
        this(null, null);
    }

    public AuthorizingRealm(CacheManager cacheManager) {
        this(cacheManager, null);
    }

    public AuthorizingRealm(CredentialsMatcher credentialsMatcher) {
        this(null, credentialsMatcher);
    }

    public AuthorizingRealm(CacheManager cacheManager, CredentialsMatcher credentialsMatcher) {
        if (cacheManager != null) {
            setCacheManager(cacheManager);
        }
        if (credentialsMatcher != null) {
            setCredentialsMatcher(credentialsMatcher);
        }
        this.authorizationCachingEnabled = true;
        this.permissionResolver = new WildcardPermissionResolver();
        int andIncrement = INSTANCE_COUNT.getAndIncrement();
        this.authorizationCacheName = getClass().getName() + DEFAULT_AUTHORIZATION_CACHE_SUFFIX;
        if (andIncrement > 0) {
            this.authorizationCacheName += "." + andIncrement;
        }
    }

    @Override // org.apache.shiro.realm.AuthenticatingRealm, org.apache.shiro.realm.CachingRealm, org.apache.shiro.util.Nameable
    public void setName(String str) {
        super.setName(str);
        String str2 = this.authorizationCacheName;
        if (str2 == null || !str2.startsWith(getClass().getName())) {
            return;
        }
        this.authorizationCacheName = str + DEFAULT_AUTHORIZATION_CACHE_SUFFIX;
    }

    public void setAuthorizationCache(Cache<Object, AuthorizationInfo> cache) {
        this.authorizationCache = cache;
    }

    public Cache<Object, AuthorizationInfo> getAuthorizationCache() {
        return this.authorizationCache;
    }

    public String getAuthorizationCacheName() {
        return this.authorizationCacheName;
    }

    public void setAuthorizationCacheName(String str) {
        this.authorizationCacheName = str;
    }

    public boolean isAuthorizationCachingEnabled() {
        return isCachingEnabled() && this.authorizationCachingEnabled;
    }

    public void setAuthorizationCachingEnabled(boolean z) {
        this.authorizationCachingEnabled = z;
        if (z) {
            setCachingEnabled(true);
        }
    }

    public PermissionResolver getPermissionResolver() {
        return this.permissionResolver;
    }

    @Override // org.apache.shiro.authz.permission.PermissionResolverAware
    public void setPermissionResolver(PermissionResolver permissionResolver) {
        if (permissionResolver == null) {
            throw new IllegalArgumentException("Null PermissionResolver is not allowed");
        }
        this.permissionResolver = permissionResolver;
    }

    public RolePermissionResolver getRolePermissionResolver() {
        return this.permissionRoleResolver;
    }

    @Override // org.apache.shiro.authz.permission.RolePermissionResolverAware
    public void setRolePermissionResolver(RolePermissionResolver rolePermissionResolver) {
        this.permissionRoleResolver = rolePermissionResolver;
    }

    @Override // org.apache.shiro.realm.AuthenticatingRealm
    protected void onInit() {
        super.onInit();
        getAvailableAuthorizationCache();
    }

    @Override // org.apache.shiro.realm.AuthenticatingRealm, org.apache.shiro.realm.CachingRealm
    protected void afterCacheManagerSet() {
        super.afterCacheManagerSet();
        getAvailableAuthorizationCache();
    }

    private Cache<Object, AuthorizationInfo> getAuthorizationCacheLazy() {
        if (this.authorizationCache == null) {
            Logger logger = log;
            if (logger.isDebugEnabled()) {
                logger.debug("No authorizationCache instance set.  Checking for a cacheManager...");
            }
            CacheManager cacheManager = getCacheManager();
            if (cacheManager != null) {
                String authorizationCacheName = getAuthorizationCacheName();
                if (logger.isDebugEnabled()) {
                    logger.debug("CacheManager [" + cacheManager + "] has been configured.  Building authorization cache named [" + authorizationCacheName + Ini.SECTION_SUFFIX);
                }
                this.authorizationCache = cacheManager.getCache(authorizationCacheName);
            } else if (logger.isDebugEnabled()) {
                logger.debug("No cache or cacheManager properties have been set.  Authorization cache cannot be obtained.");
            }
        }
        return this.authorizationCache;
    }

    private Cache<Object, AuthorizationInfo> getAvailableAuthorizationCache() {
        Cache<Object, AuthorizationInfo> authorizationCache = getAuthorizationCache();
        return (authorizationCache == null && isAuthorizationCachingEnabled()) ? getAuthorizationCacheLazy() : authorizationCache;
    }

    protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principalCollection) {
        AuthorizationInfo authorizationInfoDoGetAuthorizationInfo = null;
        if (principalCollection == null) {
            return null;
        }
        Logger logger = log;
        if (logger.isTraceEnabled()) {
            logger.trace("Retrieving AuthorizationInfo for principals [" + principalCollection + Ini.SECTION_SUFFIX);
        }
        Cache<Object, AuthorizationInfo> availableAuthorizationCache = getAvailableAuthorizationCache();
        if (availableAuthorizationCache != null) {
            if (logger.isTraceEnabled()) {
                logger.trace("Attempting to retrieve the AuthorizationInfo from cache.");
            }
            authorizationInfoDoGetAuthorizationInfo = availableAuthorizationCache.get(getAuthorizationCacheKey(principalCollection));
            if (logger.isTraceEnabled()) {
                if (authorizationInfoDoGetAuthorizationInfo == null) {
                    logger.trace("No AuthorizationInfo found in cache for principals [" + principalCollection + Ini.SECTION_SUFFIX);
                } else {
                    logger.trace("AuthorizationInfo found in cache for principals [" + principalCollection + Ini.SECTION_SUFFIX);
                }
            }
        }
        if (authorizationInfoDoGetAuthorizationInfo == null && (authorizationInfoDoGetAuthorizationInfo = doGetAuthorizationInfo(principalCollection)) != null && availableAuthorizationCache != null) {
            if (logger.isTraceEnabled()) {
                logger.trace("Caching authorization info for principals: [" + principalCollection + "].");
            }
            availableAuthorizationCache.put(getAuthorizationCacheKey(principalCollection), authorizationInfoDoGetAuthorizationInfo);
        }
        return authorizationInfoDoGetAuthorizationInfo;
    }

    protected void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
        Cache<Object, AuthorizationInfo> availableAuthorizationCache;
        if (principalCollection == null || (availableAuthorizationCache = getAvailableAuthorizationCache()) == null) {
            return;
        }
        availableAuthorizationCache.remove(getAuthorizationCacheKey(principalCollection));
    }

    protected Collection<Permission> getPermissions(AuthorizationInfo authorizationInfo) {
        HashSet hashSet = new HashSet();
        if (authorizationInfo != null) {
            Collection<Permission> objectPermissions = authorizationInfo.getObjectPermissions();
            if (!CollectionUtils.isEmpty(objectPermissions)) {
                hashSet.addAll(objectPermissions);
            }
            Collection<Permission> collectionResolvePermissions = resolvePermissions(authorizationInfo.getStringPermissions());
            if (!CollectionUtils.isEmpty(collectionResolvePermissions)) {
                hashSet.addAll(collectionResolvePermissions);
            }
            Collection<Permission> collectionResolveRolePermissions = resolveRolePermissions(authorizationInfo.getRoles());
            if (!CollectionUtils.isEmpty(collectionResolveRolePermissions)) {
                hashSet.addAll(collectionResolveRolePermissions);
            }
        }
        if (hashSet.isEmpty()) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(hashSet);
    }

    private Collection<Permission> resolvePermissions(Collection<String> collection) {
        Collection<Permission> collectionEmptySet = Collections.emptySet();
        PermissionResolver permissionResolver = getPermissionResolver();
        if (permissionResolver != null && !CollectionUtils.isEmpty(collection)) {
            collectionEmptySet = new LinkedHashSet<>(collection.size());
            for (String str : collection) {
                if (StringUtils.clean(str) != null) {
                    collectionEmptySet.add(permissionResolver.resolvePermission(str));
                }
            }
        }
        return collectionEmptySet;
    }

    private Collection<Permission> resolveRolePermissions(Collection<String> collection) {
        Collection<Permission> collectionEmptySet = Collections.emptySet();
        RolePermissionResolver rolePermissionResolver = getRolePermissionResolver();
        if (rolePermissionResolver != null && !CollectionUtils.isEmpty(collection)) {
            collectionEmptySet = new LinkedHashSet<>(collection.size());
            Iterator<String> it = collection.iterator();
            while (it.hasNext()) {
                Collection<Permission> collectionResolvePermissionsInRole = rolePermissionResolver.resolvePermissionsInRole(it.next());
                if (!CollectionUtils.isEmpty(collectionResolvePermissionsInRole)) {
                    collectionEmptySet.addAll(collectionResolvePermissionsInRole);
                }
            }
        }
        return collectionEmptySet;
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean isPermitted(PrincipalCollection principalCollection, String str) {
        return isPermitted(principalCollection, getPermissionResolver().resolvePermission(str));
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean isPermitted(PrincipalCollection principalCollection, Permission permission) {
        return isPermitted(permission, getAuthorizationInfo(principalCollection));
    }

    protected boolean isPermitted(Permission permission, AuthorizationInfo authorizationInfo) {
        Collection<Permission> permissions = getPermissions(authorizationInfo);
        if (permissions == null || permissions.isEmpty()) {
            return false;
        }
        Iterator<Permission> it = permissions.iterator();
        while (it.hasNext()) {
            if (it.next().implies(permission)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean[] isPermitted(PrincipalCollection principalCollection, String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(getPermissionResolver().resolvePermission(str));
        }
        return isPermitted(principalCollection, arrayList);
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean[] isPermitted(PrincipalCollection principalCollection, List<Permission> list) {
        return isPermitted(list, getAuthorizationInfo(principalCollection));
    }

    protected boolean[] isPermitted(List<Permission> list, AuthorizationInfo authorizationInfo) {
        int i = 0;
        if (list == null || list.isEmpty()) {
            return new boolean[0];
        }
        boolean[] zArr = new boolean[list.size()];
        Iterator<Permission> it = list.iterator();
        while (it.hasNext()) {
            zArr[i] = isPermitted(it.next(), authorizationInfo);
            i++;
        }
        return zArr;
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean isPermittedAll(PrincipalCollection principalCollection, String... strArr) {
        if (strArr == null || strArr.length <= 0) {
            return false;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(getPermissionResolver().resolvePermission(str));
        }
        return isPermittedAll(principalCollection, arrayList);
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean isPermittedAll(PrincipalCollection principalCollection, Collection<Permission> collection) {
        AuthorizationInfo authorizationInfo = getAuthorizationInfo(principalCollection);
        return authorizationInfo != null && isPermittedAll(collection, authorizationInfo);
    }

    protected boolean isPermittedAll(Collection<Permission> collection, AuthorizationInfo authorizationInfo) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        Iterator<Permission> it = collection.iterator();
        while (it.hasNext()) {
            if (!isPermitted(it.next(), authorizationInfo)) {
                return false;
            }
        }
        return true;
    }

    @Override // org.apache.shiro.authz.Authorizer
    public void checkPermission(PrincipalCollection principalCollection, String str) throws AuthorizationException {
        checkPermission(principalCollection, getPermissionResolver().resolvePermission(str));
    }

    @Override // org.apache.shiro.authz.Authorizer
    public void checkPermission(PrincipalCollection principalCollection, Permission permission) throws AuthorizationException {
        checkPermission(permission, getAuthorizationInfo(principalCollection));
    }

    protected void checkPermission(Permission permission, AuthorizationInfo authorizationInfo) {
        if (!isPermitted(permission, authorizationInfo)) {
            throw new UnauthorizedException("User is not permitted [" + permission + Ini.SECTION_SUFFIX);
        }
    }

    @Override // org.apache.shiro.authz.Authorizer
    public void checkPermissions(PrincipalCollection principalCollection, String... strArr) throws AuthorizationException {
        if (strArr != null) {
            for (String str : strArr) {
                checkPermission(principalCollection, str);
            }
        }
    }

    @Override // org.apache.shiro.authz.Authorizer
    public void checkPermissions(PrincipalCollection principalCollection, Collection<Permission> collection) throws AuthorizationException {
        checkPermissions(collection, getAuthorizationInfo(principalCollection));
    }

    protected void checkPermissions(Collection<Permission> collection, AuthorizationInfo authorizationInfo) {
        if (collection == null || collection.isEmpty()) {
            return;
        }
        Iterator<Permission> it = collection.iterator();
        while (it.hasNext()) {
            checkPermission(it.next(), authorizationInfo);
        }
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean hasRole(PrincipalCollection principalCollection, String str) {
        return hasRole(str, getAuthorizationInfo(principalCollection));
    }

    protected boolean hasRole(String str, AuthorizationInfo authorizationInfo) {
        return (authorizationInfo == null || authorizationInfo.getRoles() == null || !authorizationInfo.getRoles().contains(str)) ? false : true;
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean[] hasRoles(PrincipalCollection principalCollection, List<String> list) {
        AuthorizationInfo authorizationInfo = getAuthorizationInfo(principalCollection);
        return authorizationInfo != null ? hasRoles(list, authorizationInfo) : new boolean[list != null ? list.size() : 0];
    }

    protected boolean[] hasRoles(List<String> list, AuthorizationInfo authorizationInfo) {
        int i = 0;
        if (list == null || list.isEmpty()) {
            return new boolean[0];
        }
        boolean[] zArr = new boolean[list.size()];
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            zArr[i] = hasRole(it.next(), authorizationInfo);
            i++;
        }
        return zArr;
    }

    @Override // org.apache.shiro.authz.Authorizer
    public boolean hasAllRoles(PrincipalCollection principalCollection, Collection<String> collection) {
        AuthorizationInfo authorizationInfo = getAuthorizationInfo(principalCollection);
        return authorizationInfo != null && hasAllRoles(collection, authorizationInfo);
    }

    private boolean hasAllRoles(Collection<String> collection, AuthorizationInfo authorizationInfo) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            if (!hasRole(it.next(), authorizationInfo)) {
                return false;
            }
        }
        return true;
    }

    @Override // org.apache.shiro.authz.Authorizer
    public void checkRole(PrincipalCollection principalCollection, String str) throws AuthorizationException {
        checkRole(str, getAuthorizationInfo(principalCollection));
    }

    protected void checkRole(String str, AuthorizationInfo authorizationInfo) {
        if (!hasRole(str, authorizationInfo)) {
            throw new UnauthorizedException("User does not have role [" + str + Ini.SECTION_SUFFIX);
        }
    }

    @Override // org.apache.shiro.authz.Authorizer
    public void checkRoles(PrincipalCollection principalCollection, Collection<String> collection) throws AuthorizationException {
        checkRoles(collection, getAuthorizationInfo(principalCollection));
    }

    @Override // org.apache.shiro.authz.Authorizer
    public void checkRoles(PrincipalCollection principalCollection, String... strArr) throws AuthorizationException {
        checkRoles(principalCollection, Arrays.asList(strArr));
    }

    protected void checkRoles(Collection<String> collection, AuthorizationInfo authorizationInfo) {
        if (collection == null || collection.isEmpty()) {
            return;
        }
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            checkRole(it.next(), authorizationInfo);
        }
    }

    @Override // org.apache.shiro.realm.AuthenticatingRealm, org.apache.shiro.realm.CachingRealm
    protected void doClearCache(PrincipalCollection principalCollection) {
        super.doClearCache(principalCollection);
        clearCachedAuthorizationInfo(principalCollection);
    }
}
