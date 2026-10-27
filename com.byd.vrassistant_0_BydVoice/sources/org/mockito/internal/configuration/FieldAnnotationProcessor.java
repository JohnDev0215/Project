package org.mockito.internal.configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes4.dex */
public interface FieldAnnotationProcessor<A extends Annotation> {
    Object process(A a, Field field);
}
