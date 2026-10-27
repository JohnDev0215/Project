package org.apache.shiro.authz.aop;

import java.lang.annotation.Annotation;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.annotation.RequiresGuest;

/* JADX INFO: loaded from: classes.dex */
public class GuestAnnotationHandler extends AuthorizingAnnotationHandler {
    public GuestAnnotationHandler() {
        super(RequiresGuest.class);
    }

    @Override // org.apache.shiro.authz.aop.AuthorizingAnnotationHandler
    public void assertAuthorized(Annotation annotation) throws AuthorizationException {
        if ((annotation instanceof RequiresGuest) && getSubject().getPrincipal() != null) {
            throw new UnauthenticatedException("Attempting to perform a guest-only operation.  The current Subject is not a guest (they have been authenticated or remembered from a previous login).  Access denied.");
        }
    }
}
