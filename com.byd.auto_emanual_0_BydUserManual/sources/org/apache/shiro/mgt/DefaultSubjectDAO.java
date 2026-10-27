package org.apache.shiro.mgt;

import java.lang.reflect.Field;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.subject.support.DelegatingSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class DefaultSubjectDAO implements SubjectDAO {
    private static final Logger log = LoggerFactory.getLogger(DefaultSubjectDAO.class);
    private SessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();

    protected boolean isSessionStorageEnabled(Subject subject) {
        return getSessionStorageEvaluator().isSessionStorageEnabled(subject);
    }

    public SessionStorageEvaluator getSessionStorageEvaluator() {
        return this.sessionStorageEvaluator;
    }

    public void setSessionStorageEvaluator(SessionStorageEvaluator sessionStorageEvaluator) {
        this.sessionStorageEvaluator = sessionStorageEvaluator;
    }

    @Override // org.apache.shiro.mgt.SubjectDAO
    public Subject save(Subject subject) {
        if (isSessionStorageEnabled(subject)) {
            saveToSession(subject);
        } else {
            log.trace("Session storage of subject state for Subject [{}] has been disabled: identity and authentication state are expected to be initialized on every request or invocation.", subject);
        }
        return subject;
    }

    protected void saveToSession(Subject subject) {
        mergePrincipals(subject);
        mergeAuthenticationState(subject);
    }

    private static boolean isEmpty(PrincipalCollection principalCollection) {
        return principalCollection == null || principalCollection.isEmpty();
    }

    protected void mergePrincipals(Subject subject) {
        PrincipalCollection principals;
        if (subject.isRunAs() && (subject instanceof DelegatingSubject)) {
            try {
                Field declaredField = DelegatingSubject.class.getDeclaredField("principals");
                declaredField.setAccessible(true);
                principals = (PrincipalCollection) declaredField.get(subject);
            } catch (Exception e) {
                throw new IllegalStateException("Unable to access DelegatingSubject principals property.", e);
            }
        } else {
            principals = null;
        }
        if (principals == null || principals.isEmpty()) {
            principals = subject.getPrincipals();
        }
        Session session = subject.getSession(false);
        if (session == null) {
            if (isEmpty(principals)) {
                return;
            }
            subject.getSession().setAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY, principals);
            return;
        }
        PrincipalCollection principalCollection = (PrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if (isEmpty(principals)) {
            if (isEmpty(principalCollection)) {
                return;
            }
            session.removeAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        } else {
            if (principals.equals(principalCollection)) {
                return;
            }
            session.setAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY, principals);
        }
    }

    protected void mergeAuthenticationState(Subject subject) {
        Session session = subject.getSession(false);
        if (session == null) {
            if (subject.isAuthenticated()) {
                subject.getSession().setAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY, Boolean.TRUE);
                return;
            }
            return;
        }
        Boolean bool = (Boolean) session.getAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY);
        if (!subject.isAuthenticated()) {
            if (bool != null) {
                session.removeAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY);
            }
        } else if (bool == null || !bool.booleanValue()) {
            session.setAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY, Boolean.TRUE);
        }
    }

    protected void removeFromSession(Subject subject) {
        Session session = subject.getSession(false);
        if (session != null) {
            session.removeAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY);
            session.removeAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        }
    }

    @Override // org.apache.shiro.mgt.SubjectDAO
    public void delete(Subject subject) {
        removeFromSession(subject);
    }
}
