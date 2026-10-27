package org.apache.shiro.jndi;

import java.util.Properties;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class JndiLocator {
    public static final String CONTAINER_PREFIX = "java:comp/env/";
    private static final Logger log = LoggerFactory.getLogger(JndiLocator.class);
    private boolean resourceRef = false;
    private JndiTemplate jndiTemplate = new JndiTemplate();

    public void setJndiTemplate(JndiTemplate jndiTemplate) {
        if (jndiTemplate == null) {
            jndiTemplate = new JndiTemplate();
        }
        this.jndiTemplate = jndiTemplate;
    }

    public JndiTemplate getJndiTemplate() {
        return this.jndiTemplate;
    }

    public void setJndiEnvironment(Properties properties) {
        this.jndiTemplate = new JndiTemplate(properties);
    }

    public Properties getJndiEnvironment() {
        return this.jndiTemplate.getEnvironment();
    }

    public void setResourceRef(boolean z) {
        this.resourceRef = z;
    }

    public boolean isResourceRef() {
        return this.resourceRef;
    }

    protected Object lookup(String str) throws NamingException {
        return lookup(str, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.naming.NamingException */
    protected Object lookup(String str, Class cls) throws NamingException {
        Object objLookup;
        if (str == null) {
            throw new IllegalArgumentException("jndiName argument must not be null");
        }
        String strConvertJndiName = convertJndiName(str);
        try {
            objLookup = getJndiTemplate().lookup(strConvertJndiName, cls);
        } catch (NamingException e) {
            if (!strConvertJndiName.equals(str)) {
                Logger logger = log;
                if (logger.isDebugEnabled()) {
                    logger.debug("Converted JNDI name [" + strConvertJndiName + "] not found - trying original name [" + str + "]. " + e);
                }
                objLookup = getJndiTemplate().lookup(str, cls);
            } else {
                throw e;
            }
        }
        log.debug("Located object with JNDI name '{}'", strConvertJndiName);
        return objLookup;
    }

    protected String convertJndiName(String str) {
        return (isResourceRef() && !str.startsWith(CONTAINER_PREFIX) && str.indexOf(58) == -1) ? CONTAINER_PREFIX + str : str;
    }
}
