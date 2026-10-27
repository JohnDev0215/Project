package org.apache.shiro.env;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.IniSecurityManagerFactory;

/* JADX INFO: loaded from: classes.dex */
public class BasicIniEnvironment extends DefaultEnvironment {
    public BasicIniEnvironment(Ini ini) {
        setSecurityManager(new IniSecurityManagerFactory(ini).getInstance());
    }

    public BasicIniEnvironment(String str) {
        this(Ini.fromResourcePath(str));
    }
}
