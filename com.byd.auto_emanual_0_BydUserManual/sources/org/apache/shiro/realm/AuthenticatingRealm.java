package org.apache.shiro.realm;

import java.util.concurrent.atomic.AtomicInteger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public abstract class AuthenticatingRealm extends CachingRealm implements Initializable {
    private static final String DEFAULT_AUTHENTICATION_CACHE_SUFFIX = ".authenticationCache";
    private Cache<Object, AuthenticationInfo> authenticationCache;
    private String authenticationCacheName;
    private boolean authenticationCachingEnabled;
    private Class<? extends AuthenticationToken> authenticationTokenClass;
    private CredentialsMatcher credentialsMatcher;
    private static final Logger log = LoggerFactory.getLogger(AuthenticatingRealm.class);
    private static final AtomicInteger INSTANCE_COUNT = new AtomicInteger();

    protected abstract AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException;

    protected void onInit() {
    }

    public AuthenticatingRealm() {
        this(null, new SimpleCredentialsMatcher());
    }

    public AuthenticatingRealm(CacheManager cacheManager) {
        this(cacheManager, new SimpleCredentialsMatcher());
    }

    public AuthenticatingRealm(CredentialsMatcher credentialsMatcher) {
        this(null, credentialsMatcher);
    }

    public AuthenticatingRealm(CacheManager cacheManager, CredentialsMatcher credentialsMatcher) {
        this.authenticationTokenClass = UsernamePasswordToken.class;
        this.authenticationCachingEnabled = false;
        int andIncrement = INSTANCE_COUNT.getAndIncrement();
        this.authenticationCacheName = getClass().getName() + DEFAULT_AUTHENTICATION_CACHE_SUFFIX;
        if (andIncrement > 0) {
            this.authenticationCacheName += "." + andIncrement;
        }
        if (cacheManager != null) {
            setCacheManager(cacheManager);
        }
        if (credentialsMatcher != null) {
            setCredentialsMatcher(credentialsMatcher);
        }
    }

    public CredentialsMatcher getCredentialsMatcher() {
        return this.credentialsMatcher;
    }

    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        this.credentialsMatcher = credentialsMatcher;
    }

    public Class getAuthenticationTokenClass() {
        return this.authenticationTokenClass;
    }

    public void setAuthenticationTokenClass(Class<? extends AuthenticationToken> cls) {
        this.authenticationTokenClass = cls;
    }

    public void setAuthenticationCache(Cache<Object, AuthenticationInfo> cache) {
        this.authenticationCache = cache;
    }

    public Cache<Object, AuthenticationInfo> getAuthenticationCache() {
        return this.authenticationCache;
    }

    public String getAuthenticationCacheName() {
        return this.authenticationCacheName;
    }

    public void setAuthenticationCacheName(String str) {
        this.authenticationCacheName = str;
    }

    public boolean isAuthenticationCachingEnabled() {
        return this.authenticationCachingEnabled && isCachingEnabled();
    }

    public void setAuthenticationCachingEnabled(boolean z) {
        this.authenticationCachingEnabled = z;
        if (z) {
            setCachingEnabled(true);
        }
    }

    @Override // org.apache.shiro.realm.CachingRealm, org.apache.shiro.util.Nameable
    public void setName(String str) {
        super.setName(str);
        String str2 = this.authenticationCacheName;
        if (str2 == null || !str2.startsWith(getClass().getName())) {
            return;
        }
        this.authenticationCacheName = str + DEFAULT_AUTHENTICATION_CACHE_SUFFIX;
    }

    @Override // org.apache.shiro.realm.Realm
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken != null && getAuthenticationTokenClass().isAssignableFrom(authenticationToken.getClass());
    }

    @Override // org.apache.shiro.util.Initializable
    public final void init() {
        getAvailableAuthenticationCache();
        onInit();
    }

    @Override // org.apache.shiro.realm.CachingRealm
    protected void afterCacheManagerSet() {
        getAvailableAuthenticationCache();
    }

    private Cache<Object, AuthenticationInfo> getAvailableAuthenticationCache() {
        Cache<Object, AuthenticationInfo> authenticationCache = getAuthenticationCache();
        return (authenticationCache == null && isAuthenticationCachingEnabled()) ? getAuthenticationCacheLazy() : authenticationCache;
    }

    private Cache<Object, AuthenticationInfo> getAuthenticationCacheLazy() {
        if (this.authenticationCache == null) {
            Logger logger = log;
            logger.trace("No authenticationCache instance set.  Checking for a cacheManager...");
            CacheManager cacheManager = getCacheManager();
            if (cacheManager != null) {
                String authenticationCacheName = getAuthenticationCacheName();
                logger.debug("CacheManager [{}] configured.  Building authentication cache '{}'", cacheManager, authenticationCacheName);
                this.authenticationCache = cacheManager.getCache(authenticationCacheName);
            }
        }
        return this.authenticationCache;
    }

    private AuthenticationInfo getCachedAuthenticationInfo(AuthenticationToken authenticationToken) {
        Cache<Object, AuthenticationInfo> availableAuthenticationCache = getAvailableAuthenticationCache();
        if (availableAuthenticationCache == null || authenticationToken == null) {
            return null;
        }
        Logger logger = log;
        logger.trace("Attempting to retrieve the AuthenticationInfo from cache.");
        Object authenticationCacheKey = getAuthenticationCacheKey(authenticationToken);
        AuthenticationInfo authenticationInfo = availableAuthenticationCache.get(authenticationCacheKey);
        if (authenticationInfo == null) {
            logger.trace("No AuthorizationInfo found in cache for key [{}]", authenticationCacheKey);
            return authenticationInfo;
        }
        logger.trace("Found cached AuthorizationInfo for key [{}]", authenticationCacheKey);
        return authenticationInfo;
    }

    private void cacheAuthenticationInfoIfPossible(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        if (!isAuthenticationCachingEnabled(authenticationToken, authenticationInfo)) {
            log.debug("AuthenticationInfo caching is disabled for info [{}].  Submitted token: [{}].", authenticationInfo, authenticationToken);
            return;
        }
        Cache<Object, AuthenticationInfo> availableAuthenticationCache = getAvailableAuthenticationCache();
        if (availableAuthenticationCache != null) {
            Object authenticationCacheKey = getAuthenticationCacheKey(authenticationToken);
            availableAuthenticationCache.put(authenticationCacheKey, authenticationInfo);
            log.trace("Cached AuthenticationInfo for continued authentication.  key=[{}], value=[{}].", authenticationCacheKey, authenticationInfo);
        }
    }

    protected boolean isAuthenticationCachingEnabled(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        return isAuthenticationCachingEnabled();
    }

    @Override // org.apache.shiro.realm.Realm
    public final AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        AuthenticationInfo cachedAuthenticationInfo = getCachedAuthenticationInfo(authenticationToken);
        if (cachedAuthenticationInfo == null) {
            cachedAuthenticationInfo = doGetAuthenticationInfo(authenticationToken);
            log.debug("Looked up AuthenticationInfo [{}] from doGetAuthenticationInfo", cachedAuthenticationInfo);
            if (authenticationToken != null && cachedAuthenticationInfo != null) {
                cacheAuthenticationInfoIfPossible(authenticationToken, cachedAuthenticationInfo);
            }
        } else {
            log.debug("Using cached authentication info [{}] to perform credentials matching.", cachedAuthenticationInfo);
        }
        if (cachedAuthenticationInfo != null) {
            assertCredentialsMatch(authenticationToken, cachedAuthenticationInfo);
        } else {
            log.debug("No AuthenticationInfo found for submitted AuthenticationToken [{}].  Returning null.", authenticationToken);
        }
        return cachedAuthenticationInfo;
    }

    protected void assertCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) throws AuthenticationException {
        CredentialsMatcher credentialsMatcher = getCredentialsMatcher();
        if (credentialsMatcher != null) {
            if (!credentialsMatcher.doCredentialsMatch(authenticationToken, authenticationInfo)) {
                throw new IncorrectCredentialsException("Submitted credentials for token [" + authenticationToken + "] did not match the expected credentials.");
            }
            return;
        }
        throw new AuthenticationException("A CredentialsMatcher must be configured in order to verify credentials during authentication.  If you do not wish for credentials to be examined, you can configure an " + AllowAllCredentialsMatcher.class.getName() + " instance.");
    }

    protected Object getAuthenticationCacheKey(AuthenticationToken authenticationToken) {
        if (authenticationToken != null) {
            return authenticationToken.getPrincipal();
        }
        return null;
    }

    protected Object getAuthenticationCacheKey(PrincipalCollection principalCollection) {
        return getAvailablePrincipal(principalCollection);
    }

    @Override // org.apache.shiro.realm.CachingRealm
    protected void doClearCache(PrincipalCollection principalCollection) {
        super.doClearCache(principalCollection);
        clearCachedAuthenticationInfo(principalCollection);
    }

    private static boolean isEmpty(PrincipalCollection principalCollection) {
        return principalCollection == null || principalCollection.isEmpty();
    }

    protected void clearCachedAuthenticationInfo(PrincipalCollection principalCollection) {
        Cache<Object, AuthenticationInfo> availableAuthenticationCache;
        if (isEmpty(principalCollection) || (availableAuthenticationCache = getAvailableAuthenticationCache()) == null) {
            return;
        }
        availableAuthenticationCache.remove(getAuthenticationCacheKey(principalCollection));
    }
}
