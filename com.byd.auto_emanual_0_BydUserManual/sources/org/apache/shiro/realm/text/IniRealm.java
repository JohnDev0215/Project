package org.apache.shiro.realm.text;

import org.apache.shiro.config.Ini;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class IniRealm extends TextConfigurationRealm {
    public static final String ROLES_SECTION_NAME = "roles";
    public static final String USERS_SECTION_NAME = "users";
    private static final transient Logger log = LoggerFactory.getLogger(IniRealm.class);
    private Ini ini;
    private String resourcePath;

    public IniRealm() {
    }

    public IniRealm(Ini ini) {
        this();
        processDefinitions(ini);
    }

    public IniRealm(String str) {
        this();
        Ini iniFromResourcePath = Ini.fromResourcePath(str);
        this.ini = iniFromResourcePath;
        this.resourcePath = str;
        processDefinitions(iniFromResourcePath);
    }

    public String getResourcePath() {
        return this.resourcePath;
    }

    public void setResourcePath(String str) {
        this.resourcePath = str;
    }

    public Ini getIni() {
        return this.ini;
    }

    public void setIni(Ini ini) {
        this.ini = ini;
    }

    @Override // org.apache.shiro.realm.text.TextConfigurationRealm, org.apache.shiro.realm.AuthorizingRealm, org.apache.shiro.realm.AuthenticatingRealm
    protected void onInit() {
        super.onInit();
        Ini ini = getIni();
        String resourcePath = getResourcePath();
        if (!CollectionUtils.isEmpty(this.users) || !CollectionUtils.isEmpty(this.roles)) {
            if (!CollectionUtils.isEmpty(ini)) {
                log.warn("Users or Roles are already populated.  Configured Ini instance will be ignored.");
            }
            if (StringUtils.hasText(resourcePath)) {
                log.warn("Users or Roles are already populated.  resourcePath '{}' will be ignored.", resourcePath);
            }
            log.debug("Instance is already populated with users or roles.  No additional user/role population will be performed.");
            return;
        }
        if (CollectionUtils.isEmpty(ini)) {
            Logger logger = log;
            logger.debug("No INI instance configuration present.  Checking resourcePath...");
            if (StringUtils.hasText(resourcePath)) {
                logger.debug("Resource path {} defined.  Creating INI instance.", resourcePath);
                ini = Ini.fromResourcePath(resourcePath);
                if (!CollectionUtils.isEmpty(ini)) {
                    setIni(ini);
                }
            }
        }
        if (CollectionUtils.isEmpty(ini)) {
            throw new IllegalStateException("Ini instance and/or resourcePath resulted in null or empty Ini configuration.  Cannot load account data.");
        }
        processDefinitions(ini);
    }

    private void processDefinitions(Ini ini) {
        if (CollectionUtils.isEmpty(ini)) {
            log.warn("{} defined, but the ini instance is null or empty.", getClass().getSimpleName());
            return;
        }
        Ini.Section section = ini.getSection(ROLES_SECTION_NAME);
        if (!CollectionUtils.isEmpty(section)) {
            log.debug("Discovered the [{}] section.  Processing...", ROLES_SECTION_NAME);
            processRoleDefinitions(section);
        }
        Ini.Section section2 = ini.getSection(USERS_SECTION_NAME);
        if (!CollectionUtils.isEmpty(section2)) {
            log.debug("Discovered the [{}] section.  Processing...", USERS_SECTION_NAME);
            processUserDefinitions(section2);
        } else {
            log.info("{} defined, but there is no [{}] section defined.  This realm will not be populated with any users and it is assumed that they will be populated programatically.  Users must be defined for this Realm instance to be useful.", getClass().getSimpleName(), USERS_SECTION_NAME);
        }
    }
}
