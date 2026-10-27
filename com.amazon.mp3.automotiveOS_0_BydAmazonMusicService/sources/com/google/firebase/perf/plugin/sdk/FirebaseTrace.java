package com.google.firebase.perf.plugin.sdk;

import com.google.firebase.perf.plugin.util.AsmString;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;
import org.objectweb.asm.commons.Method;

/* JADX INFO: loaded from: classes6.dex */
public class FirebaseTrace {
    private final AdviceAdapter adviceAdapter;
    private int timerLocalIndex = -1;
    private final Type classType = Type.getObjectType(AsmString.CLASS_FIREBASE_PERF_TRACE);

    public FirebaseTrace(AdviceAdapter adviceAdapter) {
        this.adviceAdapter = adviceAdapter;
    }

    public void start(String str) {
        this.timerLocalIndex = this.adviceAdapter.newLocal(this.classType);
        this.adviceAdapter.push(str);
        this.adviceAdapter.invokeStatic(Type.getObjectType(AsmString.CLASS_FIREBASE_PERFORMANCE), new Method(AsmString.METHOD_FIREBASE_PERFORMANCE_START_TRACE_WITH_STRING, AsmString.METHOD_DESC_FIREBASE_PERFORMANCE_START_TRACE_WITH_STRING));
        this.adviceAdapter.storeLocal(this.timerLocalIndex);
    }

    public void stop() {
        int i = this.timerLocalIndex;
        if (i == -1) {
            throw new IllegalStateException("FirebaseTrace.stop called without calling FirebaseTrace.start");
        }
        this.adviceAdapter.loadLocal(i);
        this.adviceAdapter.invokeVirtual(this.classType, new Method(AsmString.METHOD_FIREBASE_PERF_TRACE_STOP, "()V"));
    }
}
