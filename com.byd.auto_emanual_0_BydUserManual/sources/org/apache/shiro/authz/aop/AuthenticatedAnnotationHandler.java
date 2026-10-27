package org.apache.shiro.authz.aop;

import java.lang.annotation.Annotation;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;

/* JADX INFO: loaded from: classes.dex */
public class AuthenticatedAnnotationHandler extends AuthorizingAnnotationHandler {
    public AuthenticatedAnnotationHandler() {
        super(RequiresAuthentication.class);
    }

    @Override // org.apache.shiro.authz.aop.AuthorizingAnnotationHandler
    public void assertAuthorized(Annotation annotation) throws UnauthenticatedException {
        if ((annotation instanceof RequiresAuthentication) && !getSubject().isAuthenticated()) {
            throw new UnauthenticatedException("The current Subject is not authenticated.  Access denied.");
        }
    }
}
