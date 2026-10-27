package org.apache.shiro.authz.aop;

import java.lang.annotation.Annotation;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.annotation.RequiresUser;

/* JADX INFO: loaded from: classes.dex */
public class UserAnnotationHandler extends AuthorizingAnnotationHandler {
    public UserAnnotationHandler() {
        super(RequiresUser.class);
    }

    @Override // org.apache.shiro.authz.aop.AuthorizingAnnotationHandler
    public void assertAuthorized(Annotation annotation) throws AuthorizationException {
        if ((annotation instanceof RequiresUser) && getSubject().getPrincipal() == null) {
            throw new UnauthenticatedException("Attempting to perform a user-only operation.  The current Subject is not a user (they haven't been authenticated or remembered from a previous login).  Access denied.");
        }
    }
}
