package org.apache.shiro.authz.aop;

import org.apache.shiro.aop.AnnotationResolver;

/* JADX INFO: loaded from: classes.dex */
public class RoleAnnotationMethodInterceptor extends AuthorizingAnnotationMethodInterceptor {
    public RoleAnnotationMethodInterceptor() {
        super(new RoleAnnotationHandler());
    }

    public RoleAnnotationMethodInterceptor(AnnotationResolver annotationResolver) {
        super(new RoleAnnotationHandler(), annotationResolver);
    }
}
