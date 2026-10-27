package org.apache.shiro.authz.aop;

import org.apache.shiro.aop.AnnotationResolver;

/* JADX INFO: loaded from: classes.dex */
public class UserAnnotationMethodInterceptor extends AuthorizingAnnotationMethodInterceptor {
    public UserAnnotationMethodInterceptor() {
        super(new UserAnnotationHandler());
    }

    public UserAnnotationMethodInterceptor(AnnotationResolver annotationResolver) {
        super(new UserAnnotationHandler(), annotationResolver);
    }
}
