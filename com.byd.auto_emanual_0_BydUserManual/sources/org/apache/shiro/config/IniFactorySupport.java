package org.apache.shiro.config;

import java.util.Map;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.util.AbstractFactory;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class IniFactorySupport<T> extends AbstractFactory<T> {
    public static final String DEFAULT_INI_RESOURCE_PATH = "classpath:shiro.ini";
    private static final transient Logger log = LoggerFactory.getLogger(IniFactorySupport.class);
    private Map<String, ?> defaultBeans;
    private Ini ini;

    protected abstract T createDefaultInstance();

    protected abstract T createInstance(Ini ini);

    protected IniFactorySupport() {
    }

    protected IniFactorySupport(Ini ini) {
        this.ini = ini;
    }

    public Ini getIni() {
        return this.ini;
    }

    public void setIni(Ini ini) {
        this.ini = ini;
    }

    protected Map<String, ?> getDefaults() {
        return this.defaultBeans;
    }

    public void setDefaults(Map<String, ?> map) {
        this.defaultBeans = map;
    }

    public static Ini loadDefaultClassPathIni() {
        if (!ResourceUtils.resourceExists(DEFAULT_INI_RESOURCE_PATH)) {
            return null;
        }
        Logger logger = log;
        logger.debug("Found shiro.ini at the root of the classpath.");
        Ini ini = new Ini();
        ini.loadFromPath(DEFAULT_INI_RESOURCE_PATH);
        if (!CollectionUtils.isEmpty(ini)) {
            return ini;
        }
        logger.warn("shiro.ini found at the root of the classpath, but it did not contain any data.");
        return ini;
    }

    protected Ini resolveIni() {
        Ini ini = getIni();
        if (!CollectionUtils.isEmpty(ini)) {
            return ini;
        }
        log.debug("Null or empty Ini instance.  Falling back to the default {} file.", DEFAULT_INI_RESOURCE_PATH);
        return loadDefaultClassPathIni();
    }

    @Override // org.apache.shiro.util.AbstractFactory
    public T createInstance() {
        T tCreateInstance;
        Ini iniResolveIni = resolveIni();
        if (CollectionUtils.isEmpty(iniResolveIni)) {
            log.debug("No populated Ini available.  Creating a default instance.");
            tCreateInstance = createDefaultInstance();
            if (tCreateInstance == null) {
                throw new IllegalStateException(getClass().getName() + " implementation did not return a default instance in the event of a null/empty Ini configuration.  This is required to support the Factory interface.  Please check your implementation.");
            }
        } else {
            log.debug("Creating instance from Ini [" + iniResolveIni + Ini.SECTION_SUFFIX);
            tCreateInstance = createInstance(iniResolveIni);
            if (tCreateInstance == null) {
                throw new IllegalStateException(getClass().getName() + " implementation did not return a constructed instance from the createInstance(Ini) method implementation.");
            }
        }
        return tCreateInstance;
    }
}
