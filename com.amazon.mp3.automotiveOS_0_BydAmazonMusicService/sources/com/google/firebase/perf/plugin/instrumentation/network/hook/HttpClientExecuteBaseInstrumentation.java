package com.google.firebase.perf.plugin.instrumentation.network.hook;

import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentation;
import com.google.firebase.perf.plugin.util.AsmString;
import org.objectweb.asm.MethodVisitor;

/* JADX INFO: loaded from: classes6.dex */
public abstract class HttpClientExecuteBaseInstrumentation implements NetworkObjectInstrumentation {
    private final String methodDesc;
    private final String methodName;

    @Override // com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentation
    public void injectAfter(MethodVisitor methodVisitor) {
    }

    @Override // com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentation
    public void injectBefore(MethodVisitor methodVisitor) {
    }

    protected HttpClientExecuteBaseInstrumentation(String str, String str2) {
        this.methodName = str;
        this.methodDesc = str2;
    }

    @Override // com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentation
    public boolean replaceMethod(MethodVisitor methodVisitor, int i) {
        methodVisitor.visitMethodInsn(184, AsmString.CLASS_FIREBASE_PERF_HTTP_CLIENT, this.methodName, this.methodDesc, false);
        return true;
    }
}
