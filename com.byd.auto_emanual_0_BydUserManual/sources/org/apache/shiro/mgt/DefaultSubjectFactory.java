package org.apache.shiro.mgt;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.subject.support.DelegatingSubject;

/* JADX INFO: loaded from: classes.dex */
public class DefaultSubjectFactory implements SubjectFactory {
    @Override // org.apache.shiro.mgt.SubjectFactory
    public Subject createSubject(SubjectContext subjectContext) {
        SecurityManager securityManagerResolveSecurityManager = subjectContext.resolveSecurityManager();
        return new DelegatingSubject(subjectContext.resolvePrincipals(), subjectContext.resolveAuthenticated(), subjectContext.resolveHost(), subjectContext.resolveSession(), subjectContext.isSessionCreationEnabled(), securityManagerResolveSecurityManager);
    }

    @Deprecated
    protected Subject newSubjectInstance(PrincipalCollection principalCollection, boolean z, String str, Session session, SecurityManager securityManager) {
        return new DelegatingSubject(principalCollection, z, str, session, true, securityManager);
    }
}
