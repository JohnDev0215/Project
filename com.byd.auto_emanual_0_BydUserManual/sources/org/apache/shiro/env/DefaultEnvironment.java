package org.apache.shiro.env;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Destroyable;
import org.apache.shiro.util.LifecycleUtils;

/* JADX INFO: loaded from: classes.dex */
public class DefaultEnvironment implements NamedObjectEnvironment, Destroyable {
    public static final String DEFAULT_SECURITY_MANAGER_KEY = "securityManager";
    protected final Map<String, Object> objects;
    private String securityManagerName;

    public DefaultEnvironment() {
        this(new ConcurrentHashMap());
    }

    public DefaultEnvironment(Map<String, ?> map) {
        this.securityManagerName = "securityManager";
        if (map == null) {
            throw new IllegalArgumentException("Backing map cannot be null.");
        }
        this.objects = map;
    }

    @Override // org.apache.shiro.env.Environment
    public SecurityManager getSecurityManager() throws IllegalStateException {
        SecurityManager securityManagerLookupSecurityManager = lookupSecurityManager();
        if (securityManagerLookupSecurityManager != null) {
            return securityManagerLookupSecurityManager;
        }
        throw new IllegalStateException("No SecurityManager found in Environment.  This is an invalid environment state.");
    }

    public void setSecurityManager(SecurityManager securityManager) {
        if (securityManager == null) {
            throw new IllegalArgumentException("Null SecurityManager instances are not allowed.");
        }
        setObject(getSecurityManagerName(), securityManager);
    }

    protected SecurityManager lookupSecurityManager() {
        return (SecurityManager) getObject(getSecurityManagerName(), SecurityManager.class);
    }

    public String getSecurityManagerName() {
        return this.securityManagerName;
    }

    public void setSecurityManagerName(String str) {
        this.securityManagerName = str;
    }

    public Map<String, Object> getObjects() {
        return this.objects;
    }

    @Override // org.apache.shiro.env.NamedObjectEnvironment
    public <T> T getObject(String str, Class<T> cls) throws RequiredTypeException {
        Objects.requireNonNull(str, "name parameter cannot be null.");
        Objects.requireNonNull(cls, "requiredType parameter cannot be null.");
        T t = (T) this.objects.get(str);
        if (t == null) {
            return null;
        }
        if (cls.isInstance(t)) {
            return t;
        }
        throw new RequiredTypeException("Object named '" + str + "' (of type [" + t.getClass().getName() + "]) is not of required type [" + cls.getName() + "].");
    }

    public void setObject(String str, Object obj) {
        Objects.requireNonNull(str, "name parameter cannot be null.");
        if (obj == null) {
            this.objects.remove(str);
        } else {
            this.objects.put(str, obj);
        }
    }

    @Override // org.apache.shiro.util.Destroyable
    public void destroy() throws Exception {
        LifecycleUtils.destroy((Collection) this.objects.values());
    }
}
