package com.sun.xml.bind.v2.model.annotation;

import com.sun.xml.bind.v2.model.core.ErrorHandler;
import java.lang.annotation.Annotation;

/* JADX INFO: loaded from: classes3.dex */
public interface AnnotationReader<T, C, F, M> {
    Annotation[] getAllFieldAnnotations(F f, Locatable locatable);

    Annotation[] getAllMethodAnnotations(M m, Locatable locatable);

    <A extends Annotation> A getClassAnnotation(Class<A> cls, C c, Locatable locatable);

    T[] getClassArrayValue(Annotation annotation, String str);

    T getClassValue(Annotation annotation, String str);

    <A extends Annotation> A getFieldAnnotation(Class<A> cls, F f, Locatable locatable);

    <A extends Annotation> A getMethodAnnotation(Class<A> cls, M m, Locatable locatable);

    <A extends Annotation> A getMethodAnnotation(Class<A> cls, M m, M m2, Locatable locatable);

    <A extends Annotation> A getMethodParameterAnnotation(Class<A> cls, M m, int i, Locatable locatable);

    <A extends Annotation> A getPackageAnnotation(Class<A> cls, C c, Locatable locatable);

    boolean hasClassAnnotation(C c, Class<? extends Annotation> cls);

    boolean hasFieldAnnotation(Class<? extends Annotation> cls, F f);

    boolean hasMethodAnnotation(Class<? extends Annotation> cls, M m);

    boolean hasMethodAnnotation(Class<? extends Annotation> cls, String str, M m, M m2, Locatable locatable);

    void setErrorHandler(ErrorHandler errorHandler);
}
