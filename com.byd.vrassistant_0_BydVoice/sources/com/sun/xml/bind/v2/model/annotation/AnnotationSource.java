package com.sun.xml.bind.v2.model.annotation;

import java.lang.annotation.Annotation;

/* JADX INFO: loaded from: classes3.dex */
public interface AnnotationSource {
    boolean hasAnnotation(Class<? extends Annotation> cls);

    <A extends Annotation> A readAnnotation(Class<A> cls);
}
