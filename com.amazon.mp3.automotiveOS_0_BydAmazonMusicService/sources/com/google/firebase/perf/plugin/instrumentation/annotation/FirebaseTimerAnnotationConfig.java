package com.google.firebase.perf.plugin.instrumentation.annotation;

import com.google.firebase.perf.plugin.instrumentation.annotation.FirebaseTimerAnnotationProcessor;
import com.google.firebase.perf.plugin.util.AsmString;

/* JADX INFO: loaded from: classes6.dex */
public class FirebaseTimerAnnotationConfig extends AnnotatedMethodInstrumentationConfig {
    public FirebaseTimerAnnotationConfig() {
        super(new FirebaseTimerAnnotationProcessor.Factory(), AsmString.ANNOTATION_FIREBASE_PERF_ADD_TRACE);
    }
}
