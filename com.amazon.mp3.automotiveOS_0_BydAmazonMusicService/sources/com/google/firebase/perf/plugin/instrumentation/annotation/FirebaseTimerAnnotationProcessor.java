package com.google.firebase.perf.plugin.instrumentation.annotation;

import com.google.firebase.perf.plugin.instrumentation.InstrumentationContext;
import com.google.firebase.perf.plugin.instrumentation.model.AnnotationInfo;
import com.google.firebase.perf.plugin.sdk.FirebaseTrace;
import org.objectweb.asm.commons.AdviceAdapter;

/* JADX INFO: loaded from: classes6.dex */
public class FirebaseTimerAnnotationProcessor implements AnnotatedMethodAdapter {
    private static final String TIMER_ANNOTATION_ATTR_ENABLED = "enabled";
    private static final String TIMER_ANNOTATION_ATTR_NAME = "name";
    private final AnnotationInfo annotationInfo;
    private final FirebaseTrace firebaseTrace;
    private boolean traceAdded;

    private FirebaseTimerAnnotationProcessor(AdviceAdapter adviceAdapter, AnnotationInfo annotationInfo) {
        this.traceAdded = false;
        this.firebaseTrace = new FirebaseTrace(adviceAdapter);
        this.annotationInfo = annotationInfo;
    }

    @Override // com.google.firebase.perf.plugin.instrumentation.annotation.AnnotatedMethodAdapter
    public void onMethodEnter() {
        Object obj = this.annotationInfo.values.get(TIMER_ANNOTATION_ATTR_ENABLED);
        if (obj instanceof Boolean ? ((Boolean) obj).booleanValue() : true) {
            this.firebaseTrace.start(this.annotationInfo.values.get("name").toString());
            this.traceAdded = true;
        }
    }

    @Override // com.google.firebase.perf.plugin.instrumentation.annotation.AnnotatedMethodAdapter
    public void onMethodExit() {
        if (this.traceAdded) {
            this.firebaseTrace.stop();
        }
    }

    public static class Factory implements AnnotatedMethodInstrumentationFactory {
        @Override // com.google.firebase.perf.plugin.instrumentation.annotation.AnnotatedMethodInstrumentationFactory
        public AnnotatedMethodAdapter newAnnotatedMethodInstrumentation(InstrumentationContext instrumentationContext, AdviceAdapter adviceAdapter, AnnotationInfo annotationInfo, String str, String str2) {
            return new FirebaseTimerAnnotationProcessor(adviceAdapter, annotationInfo);
        }
    }
}
