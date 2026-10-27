package com.google.firebase.perf.plugin.instrumentation.annotation;

/* JADX INFO: loaded from: classes6.dex */
public abstract class AnnotatedMethodInstrumentationConfig {
    private final AnnotatedMethodInstrumentationFactory annotatedMethodFactory;
    private final String classDesc;

    public AnnotatedMethodInstrumentationConfig(AnnotatedMethodInstrumentationFactory annotatedMethodInstrumentationFactory, String str) {
        if (str == null) {
            throw new IllegalArgumentException("className was null");
        }
        this.annotatedMethodFactory = annotatedMethodInstrumentationFactory;
        this.classDesc = str;
    }

    public AnnotatedMethodInstrumentationFactory getFactory() {
        return this.annotatedMethodFactory;
    }

    public String getClassDesc() {
        return this.classDesc;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AnnotatedMethodInstrumentationConfig) {
            return ((AnnotatedMethodInstrumentationConfig) obj).classDesc.equals(this.classDesc);
        }
        return false;
    }

    public int hashCode() {
        return this.classDesc.hashCode();
    }
}
