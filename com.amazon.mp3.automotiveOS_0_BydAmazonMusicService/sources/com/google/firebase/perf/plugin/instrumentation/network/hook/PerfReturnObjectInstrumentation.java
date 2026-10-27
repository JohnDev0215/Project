package com.google.firebase.perf.plugin.instrumentation.network.hook;

import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentation;
import com.google.firebase.perf.plugin.util.AsmString;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/* JADX INFO: loaded from: classes6.dex */
public class PerfReturnObjectInstrumentation implements NetworkObjectInstrumentation, Opcodes {
    private final String returnType;

    @Override // com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentation
    public void injectBefore(MethodVisitor methodVisitor) {
    }

    @Override // com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentation
    public boolean replaceMethod(MethodVisitor methodVisitor, int i) {
        return false;
    }

    public PerfReturnObjectInstrumentation(String str) {
        this.returnType = str;
    }

    @Override // com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentation
    public void injectAfter(MethodVisitor methodVisitor) {
        methodVisitor.visitMethodInsn(184, AsmString.CLASS_FIREBASE_PERF_URL_CONNECTION, AsmString.METHOD_FIREBASE_PERF_URL_CONNECTION_INSTRUMENT_WITH_OBJECT, AsmString.METHOD_DESC_FIREBASE_PERF_URL_CONNECTION_INSTRUMENT_WITH_OBJECT, false);
        methodVisitor.visitTypeInsn(192, this.returnType);
    }
}
