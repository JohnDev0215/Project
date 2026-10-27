package org.apache.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;

/* JADX INFO: loaded from: classes.dex */
public abstract class SecurityUtils {
    private static volatile SecurityManager securityManager;

    public static Subject getSubject() {
        Subject subject = ThreadContext.getSubject();
        if (subject != null) {
            return subject;
        }
        Subject subjectBuildSubject = new Subject.Builder().buildSubject();
        ThreadContext.bind(subjectBuildSubject);
        return subjectBuildSubject;
    }

    public static void setSecurityManager(SecurityManager securityManager2) {
        securityManager = securityManager2;
    }

    public static SecurityManager getSecurityManager() throws UnavailableSecurityManagerException {
        SecurityManager securityManager2 = ThreadContext.getSecurityManager();
        if (securityManager2 == null) {
            securityManager2 = securityManager;
        }
        if (securityManager2 != null) {
            return securityManager2;
        }
        throw new UnavailableSecurityManagerException("No SecurityManager accessible to the calling code, either bound to the " + ThreadContext.class.getName() + " or as a vm static singleton.  This is an invalid application configuration.");
    }
}
