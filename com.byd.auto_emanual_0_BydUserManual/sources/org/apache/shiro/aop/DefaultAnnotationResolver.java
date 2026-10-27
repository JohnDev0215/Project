package org.apache.shiro.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class DefaultAnnotationResolver implements AnnotationResolver {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.shiro.aop.AnnotationResolver
    public Annotation getAnnotation(MethodInvocation methodInvocation, Class<? extends Annotation> cls) {
        if (methodInvocation == null) {
            throw new IllegalArgumentException("method argument cannot be null");
        }
        Method method = methodInvocation.getMethod();
        if (method == null) {
            throw new IllegalArgumentException(MethodInvocation.class.getName() + " parameter incorrectly constructed.  getMethod() returned null");
        }
        Annotation annotation = method.getAnnotation(cls);
        if (annotation != null) {
            return annotation;
        }
        Object obj = methodInvocation.getThis();
        return obj != null ? obj.getClass().getAnnotation(cls) : null;
    }
}
