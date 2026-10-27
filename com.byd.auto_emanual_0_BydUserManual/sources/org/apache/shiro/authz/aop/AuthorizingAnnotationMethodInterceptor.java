package org.apache.shiro.authz.aop;

import org.apache.shiro.aop.AnnotationMethodInterceptor;
import org.apache.shiro.aop.AnnotationResolver;
import org.apache.shiro.aop.MethodInvocation;
import org.apache.shiro.authz.AuthorizationException;

/* JADX INFO: loaded from: classes.dex */
public abstract class AuthorizingAnnotationMethodInterceptor extends AnnotationMethodInterceptor {
    public AuthorizingAnnotationMethodInterceptor(AuthorizingAnnotationHandler authorizingAnnotationHandler) {
        super(authorizingAnnotationHandler);
    }

    public AuthorizingAnnotationMethodInterceptor(AuthorizingAnnotationHandler authorizingAnnotationHandler, AnnotationResolver annotationResolver) {
        super(authorizingAnnotationHandler, annotationResolver);
    }

    @Override // org.apache.shiro.aop.MethodInterceptor
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        assertAuthorized(methodInvocation);
        return methodInvocation.proceed();
    }

    public void assertAuthorized(MethodInvocation methodInvocation) throws AuthorizationException {
        try {
            ((AuthorizingAnnotationHandler) getHandler()).assertAuthorized(getAnnotation(methodInvocation));
        } catch (AuthorizationException e) {
            if (e.getCause() == null) {
                e.initCause(new AuthorizationException("Not authorized to invoke method: " + methodInvocation.getMethod()));
            }
            throw e;
        }
    }
}
