package org.apache.shiro.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.shiro.config.Ini;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.RealmFactory;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.LifecycleUtils;
import org.apache.shiro.util.Nameable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class IniSecurityManagerFactory extends IniFactorySupport<SecurityManager> {
    public static final String INI_REALM_NAME = "iniRealm";
    public static final String MAIN_SECTION_NAME = "main";
    public static final String SECURITY_MANAGER_NAME = "securityManager";
    private static final transient Logger log = LoggerFactory.getLogger(IniSecurityManagerFactory.class);
    private ReflectionBuilder builder;

    public IniSecurityManagerFactory() {
        this.builder = new ReflectionBuilder();
    }

    public IniSecurityManagerFactory(Ini ini) {
        this();
        setIni(ini);
    }

    public IniSecurityManagerFactory(String str) {
        this(Ini.fromResourcePath(str));
    }

    public Map<String, ?> getBeans() {
        ReflectionBuilder reflectionBuilder = this.builder;
        if (reflectionBuilder != null) {
            return Collections.unmodifiableMap(reflectionBuilder.getObjects());
        }
        return null;
    }

    public void destroy() {
        if (getReflectionBuilder() != null) {
            getReflectionBuilder().destroy();
        }
    }

    private SecurityManager getSecurityManagerBean() {
        return (SecurityManager) getReflectionBuilder().getBean("securityManager", SecurityManager.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.shiro.config.IniFactorySupport
    public SecurityManager createDefaultInstance() {
        return new DefaultSecurityManager();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.shiro.config.IniFactorySupport
    public SecurityManager createInstance(Ini ini) {
        if (CollectionUtils.isEmpty(ini)) {
            throw new NullPointerException("Ini argument cannot be null or empty.");
        }
        SecurityManager securityManagerCreateSecurityManager = createSecurityManager(ini);
        if (securityManagerCreateSecurityManager != null) {
            return securityManagerCreateSecurityManager;
        }
        throw new ConfigurationException(SecurityManager.class + " instance cannot be null.");
    }

    private SecurityManager createSecurityManager(Ini ini) {
        return createSecurityManager(ini, getConfigSection(ini));
    }

    private Ini.Section getConfigSection(Ini ini) {
        Ini.Section section = ini.getSection(MAIN_SECTION_NAME);
        return CollectionUtils.isEmpty(section) ? ini.getSection("") : section;
    }

    protected boolean isAutoApplyRealms(SecurityManager securityManager) {
        if (!(securityManager instanceof RealmSecurityManager) || CollectionUtils.isEmpty(((RealmSecurityManager) securityManager).getRealms())) {
            return true;
        }
        log.info("Realms have been explicitly set on the SecurityManager instance - auto-setting of realms will not occur.");
        return false;
    }

    private SecurityManager createSecurityManager(Ini ini, Ini.Section section) {
        getReflectionBuilder().setObjects(createDefaults(ini, section));
        Map<String, ?> mapBuildInstances = buildInstances(section);
        SecurityManager securityManagerBean = getSecurityManagerBean();
        if (isAutoApplyRealms(securityManagerBean)) {
            Collection<Realm> realms = getRealms(mapBuildInstances);
            if (!CollectionUtils.isEmpty(realms)) {
                applyRealmsToSecurityManager(realms, securityManagerBean);
            }
        }
        return securityManagerBean;
    }

    protected Map<String, ?> createDefaults(Ini ini, Ini.Section section) {
        Realm realmCreateRealm;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("securityManager", createDefaultInstance());
        if (shouldImplicitlyCreateRealm(ini) && (realmCreateRealm = createRealm(ini)) != null) {
            linkedHashMap.put(INI_REALM_NAME, realmCreateRealm);
        }
        Map<String, ?> defaults = getDefaults();
        if (!CollectionUtils.isEmpty(defaults)) {
            linkedHashMap.putAll(defaults);
        }
        return linkedHashMap;
    }

    private Map<String, ?> buildInstances(Ini.Section section) {
        return getReflectionBuilder().buildObjects(section);
    }

    private void addToRealms(Collection<Realm> collection, RealmFactory realmFactory) {
        LifecycleUtils.init(realmFactory);
        Collection<Realm> realms = realmFactory.getRealms();
        if (CollectionUtils.isEmpty(realms)) {
            return;
        }
        collection.addAll(realms);
    }

    private Collection<Realm> getRealms(Map<String, ?> map) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof RealmFactory) {
                addToRealms(arrayList, (RealmFactory) value);
            } else if (value instanceof Realm) {
                Realm realm = (Realm) value;
                String name = realm.getName();
                if (name == null || name.startsWith(realm.getClass().getName())) {
                    if (realm instanceof Nameable) {
                        ((Nameable) realm).setName(key);
                        log.debug("Applied name '{}' to Nameable realm instance {}", key, realm);
                    } else {
                        log.info("Realm does not implement the {} interface.  Configured name will not be applied.", Nameable.class.getName());
                    }
                }
                arrayList.add(realm);
            }
        }
        return arrayList;
    }

    private void assertRealmSecurityManager(SecurityManager securityManager) {
        Objects.requireNonNull(securityManager, "securityManager instance cannot be null");
        if (!(securityManager instanceof RealmSecurityManager)) {
            throw new ConfigurationException("securityManager instance is not a " + RealmSecurityManager.class.getName() + " instance.  This is required to access or configure realms on the instance.");
        }
    }

    protected void applyRealmsToSecurityManager(Collection<Realm> collection, SecurityManager securityManager) {
        assertRealmSecurityManager(securityManager);
        ((RealmSecurityManager) securityManager).setRealms(collection);
    }

    protected boolean shouldImplicitlyCreateRealm(Ini ini) {
        return (CollectionUtils.isEmpty(ini) || (CollectionUtils.isEmpty(ini.getSection(IniRealm.ROLES_SECTION_NAME)) && CollectionUtils.isEmpty(ini.getSection(IniRealm.USERS_SECTION_NAME)))) ? false : true;
    }

    protected Realm createRealm(Ini ini) {
        IniRealm iniRealm = new IniRealm();
        iniRealm.setName(INI_REALM_NAME);
        iniRealm.setIni(ini);
        return iniRealm;
    }

    public ReflectionBuilder getReflectionBuilder() {
        return this.builder;
    }

    public void setReflectionBuilder(ReflectionBuilder reflectionBuilder) {
        this.builder = reflectionBuilder;
    }
}
