package org.apache.shiro.jndi;

import javax.naming.NamingException;
import org.apache.shiro.util.Factory;

/* JADX INFO: loaded from: classes.dex */
public class JndiObjectFactory<T> extends JndiLocator implements Factory<T> {
    private Class<? extends T> requiredType;
    private String resourceName;

    @Override // org.apache.shiro.util.Factory
    public T getInstance() {
        try {
            Class<? extends T> cls = this.requiredType;
            if (cls != null) {
                return cls.cast(lookup(this.resourceName, cls));
            }
            return (T) lookup(this.resourceName);
        } catch (NamingException e) {
            Class<? extends T> cls2 = this.requiredType;
            throw new IllegalStateException("Unable to look up " + (cls2 != null ? cls2.getName() : "object") + " with jndi name '" + this.resourceName + "'.", e);
        }
    }

    public String getResourceName() {
        return this.resourceName;
    }

    public void setResourceName(String str) {
        this.resourceName = str;
    }

    public Class<? extends T> getRequiredType() {
        return this.requiredType;
    }

    public void setRequiredType(Class<? extends T> cls) {
        this.requiredType = cls;
    }
}
