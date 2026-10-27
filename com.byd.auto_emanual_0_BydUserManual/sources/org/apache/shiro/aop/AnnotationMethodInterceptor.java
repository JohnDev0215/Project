package org.apache.shiro.aop;

import java.lang.annotation.Annotation;

/* JADX INFO: loaded from: classes.dex */
public abstract class AnnotationMethodInterceptor extends MethodInterceptorSupport {
    private AnnotationHandler handler;
    private AnnotationResolver resolver;

    public AnnotationMethodInterceptor(AnnotationHandler annotationHandler) {
        this(annotationHandler, new DefaultAnnotationResolver());
    }

    public AnnotationMethodInterceptor(AnnotationHandler annotationHandler, AnnotationResolver annotationResolver) {
        if (annotationHandler == null) {
            throw new IllegalArgumentException("AnnotationHandler argument cannot be null.");
        }
        setHandler(annotationHandler);
        setResolver(annotationResolver == null ? new DefaultAnnotationResolver() : annotationResolver);
    }

    public AnnotationHandler getHandler() {
        return this.handler;
    }

    public void setHandler(AnnotationHandler annotationHandler) {
        this.handler = annotationHandler;
    }

    public AnnotationResolver getResolver() {
        return this.resolver;
    }

    public void setResolver(AnnotationResolver annotationResolver) {
        this.resolver = annotationResolver;
    }

    public boolean supports(MethodInvocation methodInvocation) {
        return getAnnotation(methodInvocation) != null;
    }

    protected Annotation getAnnotation(MethodInvocation methodInvocation) {
        return getResolver().getAnnotation(methodInvocation, getHandler().getAnnotationClass());
    }
}
