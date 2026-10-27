package org.apache.shiro.aop;

import java.lang.annotation.Annotation;

/* JADX INFO: loaded from: classes.dex */
public interface AnnotationResolver {
    Annotation getAnnotation(MethodInvocation methodInvocation, Class<? extends Annotation> cls);
}
