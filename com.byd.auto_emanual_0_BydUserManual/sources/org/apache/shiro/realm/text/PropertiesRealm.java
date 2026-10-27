package org.apache.shiro.realm.text;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.shiro.ShiroException;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.util.Destroyable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class PropertiesRealm extends TextConfigurationRealm implements Destroyable, Runnable {
    private static final int DEFAULT_RELOAD_INTERVAL_SECONDS = 10;
    private static final String DEFAULT_RESOURCE_PATH = "classpath:shiro-users.properties";
    private static final String ROLENAME_PREFIX = "role.";
    private static final String USERNAME_PREFIX = "user.";
    private static final Logger log = LoggerFactory.getLogger(PropertiesRealm.class);
    protected long fileLastModified;
    protected ExecutorService scheduler = null;
    protected boolean useXmlFormat = false;
    protected String resourcePath = DEFAULT_RESOURCE_PATH;
    protected int reloadIntervalSeconds = 10;

    public void setUseXmlFormat(boolean z) {
        this.useXmlFormat = z;
    }

    public void setResourcePath(String str) {
        this.resourcePath = str;
    }

    public void setReloadIntervalSeconds(int i) {
        this.reloadIntervalSeconds = i;
    }

    @Override // org.apache.shiro.realm.text.TextConfigurationRealm, org.apache.shiro.realm.AuthorizingRealm, org.apache.shiro.realm.AuthenticatingRealm
    public void onInit() {
        super.onInit();
        afterRoleCacheSet();
    }

    protected void afterRoleCacheSet() {
        loadProperties();
        if (this.resourcePath.startsWith(ResourceUtils.FILE_PREFIX) && this.scheduler == null) {
            startReloadThread();
        }
    }

    @Override // org.apache.shiro.util.Destroyable
    public void destroy() {
        try {
            try {
                ExecutorService executorService = this.scheduler;
                if (executorService != null) {
                    executorService.shutdown();
                }
            } catch (Exception e) {
                Logger logger = log;
                if (logger.isInfoEnabled()) {
                    logger.info("Unable to cleanly shutdown Scheduler.  Ignoring (shutting down)...", e);
                }
            }
        } finally {
            this.scheduler = null;
        }
    }

    protected void startReloadThread() {
        if (this.reloadIntervalSeconds > 0) {
            ScheduledExecutorService scheduledExecutorServiceNewSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
            this.scheduler = scheduledExecutorServiceNewSingleThreadScheduledExecutor;
            ScheduledExecutorService scheduledExecutorService = scheduledExecutorServiceNewSingleThreadScheduledExecutor;
            int i = this.reloadIntervalSeconds;
            scheduledExecutorService.scheduleAtFixedRate(this, i, i, TimeUnit.SECONDS);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            reloadPropertiesIfNecessary();
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("Error while reloading property files for realm.", e);
            }
        }
    }

    private void loadProperties() {
        String str = this.resourcePath;
        if (str == null || str.length() == 0) {
            throw new IllegalStateException("The resourcePath property is not set.  It must be set prior to this realm being initialized.");
        }
        Logger logger = log;
        if (logger.isDebugEnabled()) {
            logger.debug("Loading user security information from file [" + this.resourcePath + "]...");
        }
        createRealmEntitiesFromProperties(loadProperties(this.resourcePath));
    }

    private Properties loadProperties(String str) {
        Properties properties = new Properties();
        try {
            try {
                Logger logger = log;
                if (logger.isDebugEnabled()) {
                    logger.debug("Opening input stream for path [" + str + "]...");
                }
                InputStream inputStreamForPath = ResourceUtils.getInputStreamForPath(str);
                if (this.useXmlFormat) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Loading properties from path [" + str + "] in XML format...");
                    }
                    properties.loadFromXML(inputStreamForPath);
                } else {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Loading properties from path [" + str + "]...");
                    }
                    properties.load(inputStreamForPath);
                }
                ResourceUtils.close(inputStreamForPath);
                return properties;
            } catch (IOException e) {
                throw new ShiroException("Error reading properties path [" + str + "].  Initializing of the realm from this file failed.", e);
            }
        } catch (Throwable th) {
            ResourceUtils.close(null);
            throw th;
        }
    }

    private void reloadPropertiesIfNecessary() {
        if (isSourceModified()) {
            restart();
        }
    }

    private boolean isSourceModified() {
        return this.resourcePath.startsWith(ResourceUtils.FILE_PREFIX) && isFileModified();
    }

    private boolean isFileModified() {
        String str = this.resourcePath;
        long jLastModified = new File(str.substring(str.indexOf(":") + 1)).lastModified();
        if (jLastModified <= this.fileLastModified) {
            return false;
        }
        this.fileLastModified = jLastModified;
        return true;
    }

    private void restart() {
        String str = this.resourcePath;
        if (str == null || str.length() == 0) {
            throw new IllegalStateException("The resourcePath property is not set.  It must be set prior to this realm being initialized.");
        }
        Logger logger = log;
        if (logger.isDebugEnabled()) {
            logger.debug("Loading user security information from file [" + this.resourcePath + "]...");
        }
        try {
            destroy();
        } catch (Exception unused) {
        }
        init();
    }

    private void createRealmEntitiesFromProperties(Properties properties) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        Enumeration<?> enumerationPropertyNames = properties.propertyNames();
        while (enumerationPropertyNames.hasMoreElements()) {
            String strTrim = ((String) enumerationPropertyNames.nextElement()).trim();
            String strTrim2 = properties.getProperty(strTrim).trim();
            Logger logger = log;
            if (logger.isTraceEnabled()) {
                logger.trace("Processing properties line - key: [" + strTrim + "], value: [" + strTrim2 + "].");
            }
            if (isUsername(strTrim)) {
                sb.append(getUsername(strTrim)).append(" = ").append(strTrim2).append("\n");
            } else if (isRolename(strTrim)) {
                sb2.append(getRolename(strTrim)).append(" = ").append(strTrim2).append("\n");
            } else {
                throw new IllegalStateException("Encountered unexpected key/value pair.  All keys must be prefixed with either 'user.' or 'role.'.");
            }
        }
        setUserDefinitions(sb.toString());
        setRoleDefinitions(sb2.toString());
        processDefinitions();
    }

    protected String getName(String str, String str2) {
        return str.substring(str2.length(), str.length());
    }

    protected boolean isUsername(String str) {
        return str != null && str.startsWith(USERNAME_PREFIX);
    }

    protected boolean isRolename(String str) {
        return str != null && str.startsWith(ROLENAME_PREFIX);
    }

    protected String getUsername(String str) {
        return getName(str, USERNAME_PREFIX);
    }

    protected String getRolename(String str) {
        return getName(str, ROLENAME_PREFIX);
    }
}
