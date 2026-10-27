package org.apache.shiro.authz.aop;

import org.apache.shiro.aop.AnnotationResolver;

/* JADX INFO: loaded from: classes.dex */
public class AuthenticatedAnnotationMethodInterceptor extends AuthorizingAnnotationMethodInterceptor {
    public AuthenticatedAnnotationMethodInterceptor() {
        super(new AuthenticatedAnnotationHandler());
    }

    public AuthenticatedAnnotationMethodInterceptor(AnnotationResolver annotationResolver) {
        super(new AuthenticatedAnnotationHandler(), annotationResolver);
    }
}
