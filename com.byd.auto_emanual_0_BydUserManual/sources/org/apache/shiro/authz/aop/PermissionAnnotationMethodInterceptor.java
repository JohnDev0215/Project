package org.apache.shiro.authz.aop;

import org.apache.shiro.aop.AnnotationResolver;

/* JADX INFO: loaded from: classes.dex */
public class PermissionAnnotationMethodInterceptor extends AuthorizingAnnotationMethodInterceptor {
    public PermissionAnnotationMethodInterceptor() {
        super(new PermissionAnnotationHandler());
    }

    public PermissionAnnotationMethodInterceptor(AnnotationResolver annotationResolver) {
        super(new PermissionAnnotationHandler(), annotationResolver);
    }
}
