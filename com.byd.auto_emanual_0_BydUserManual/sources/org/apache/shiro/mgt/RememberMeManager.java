package org.apache.shiro.mgt;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;

/* JADX INFO: loaded from: classes.dex */
public interface RememberMeManager {
    void forgetIdentity(SubjectContext subjectContext);

    PrincipalCollection getRememberedPrincipals(SubjectContext subjectContext);

    void onFailedLogin(Subject subject, AuthenticationToken authenticationToken, AuthenticationException authenticationException);

    void onLogout(Subject subject);

    void onSuccessfulLogin(Subject subject, AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo);
}
