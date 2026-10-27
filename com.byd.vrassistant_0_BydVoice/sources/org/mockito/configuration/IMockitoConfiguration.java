package org.mockito.configuration;

import org.mockito.stubbing.Answer;

/* JADX INFO: loaded from: classes4.dex */
public interface IMockitoConfiguration {
    boolean cleansStackTrace();

    boolean enableClassCache();

    @Deprecated
    AnnotationEngine getAnnotationEngine();

    Answer<Object> getDefaultAnswer();
}
