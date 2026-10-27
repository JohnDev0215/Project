package org.apache.shiro.jndi;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class JndiTemplate {
    private static final Logger log = LoggerFactory.getLogger(JndiTemplate.class);
    private Properties environment;

    public JndiTemplate() {
    }

    public JndiTemplate(Properties properties) {
        this.environment = properties;
    }

    public void setEnvironment(Properties properties) {
        this.environment = properties;
    }

    public Properties getEnvironment() {
        return this.environment;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.naming.NamingException */
    public Object execute(JndiCallback jndiCallback) throws NamingException {
        Context contextCreateInitialContext = createInitialContext();
        try {
            return jndiCallback.doInContext(contextCreateInitialContext);
        } finally {
            try {
                contextCreateInitialContext.close();
            } catch (NamingException e) {
                log.debug("Could not close JNDI InitialContext", e);
            }
        }
    }

    protected Context createInitialContext() throws NamingException {
        Hashtable hashtable;
        Properties environment = getEnvironment();
        if (environment != null) {
            hashtable = new Hashtable(environment.size());
            Enumeration<?> enumerationPropertyNames = environment.propertyNames();
            while (enumerationPropertyNames.hasMoreElements()) {
                String str = (String) enumerationPropertyNames.nextElement();
                hashtable.put(str, environment.getProperty(str));
            }
        } else {
            hashtable = null;
        }
        return new InitialContext(hashtable);
    }

    public Object lookup(final String str) throws NamingException {
        log.debug("Looking up JNDI object with name '{}'", str);
        return execute(new JndiCallback() { // from class: org.apache.shiro.jndi.JndiTemplate.1
            /* JADX INFO: Thrown type has an unknown type hierarchy: javax.naming.NameNotFoundException */
            @Override // org.apache.shiro.jndi.JndiCallback
            public Object doInContext(Context context) throws NamingException, NameNotFoundException {
                Object objLookup = context.lookup(str);
                if (objLookup != null) {
                    return objLookup;
                }
                throw new NameNotFoundException("JNDI object with [" + str + "] not found: JNDI implementation returned null");
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.naming.NamingException */
    public Object lookup(String str, Class cls) throws NamingException {
        Object objLookup = lookup(str);
        if (cls == null || cls.isInstance(objLookup)) {
            return objLookup;
        }
        throw new NamingException("Jndi object acquired under name '" + str + "' is of type [" + objLookup.getClass().getName() + "] and not assignable to the required type [" + cls.getName() + "].");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.naming.NamingException */
    public void bind(final String str, final Object obj) throws NamingException {
        log.debug("Binding JNDI object with name '{}'", str);
        execute(new JndiCallback() { // from class: org.apache.shiro.jndi.JndiTemplate.2
            @Override // org.apache.shiro.jndi.JndiCallback
            public Object doInContext(Context context) throws NamingException {
                context.bind(str, obj);
                return null;
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.naming.NamingException */
    public void rebind(final String str, final Object obj) throws NamingException {
        log.debug("Rebinding JNDI object with name '{}'", str);
        execute(new JndiCallback() { // from class: org.apache.shiro.jndi.JndiTemplate.3
            @Override // org.apache.shiro.jndi.JndiCallback
            public Object doInContext(Context context) throws NamingException {
                context.rebind(str, obj);
                return null;
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.naming.NamingException */
    public void unbind(final String str) throws NamingException {
        log.debug("Unbinding JNDI object with name '{}'", str);
        execute(new JndiCallback() { // from class: org.apache.shiro.jndi.JndiTemplate.4
            @Override // org.apache.shiro.jndi.JndiCallback
            public Object doInContext(Context context) throws NamingException {
                context.unbind(str);
                return null;
            }
        });
    }
}
