package org.apache.shiro.authz.aop;

import org.apache.shiro.aop.AnnotationResolver;

/* JADX INFO: loaded from: classes.dex */
public class GuestAnnotationMethodInterceptor extends AuthorizingAnnotationMethodInterceptor {
    public GuestAnnotationMethodInterceptor() {
        super(new GuestAnnotationHandler());
    }

    public GuestAnnotationMethodInterceptor(AnnotationResolver annotationResolver) {
        super(new GuestAnnotationHandler(), annotationResolver);
    }
}
