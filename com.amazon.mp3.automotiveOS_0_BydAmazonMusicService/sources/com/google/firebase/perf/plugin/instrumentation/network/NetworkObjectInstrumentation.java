package com.google.firebase.perf.plugin.instrumentation.network;

import org.objectweb.asm.MethodVisitor;

/* JADX INFO: loaded from: classes6.dex */
public interface NetworkObjectInstrumentation {
    void injectAfter(MethodVisitor methodVisitor);

    void injectBefore(MethodVisitor methodVisitor);

    boolean replaceMethod(MethodVisitor methodVisitor, int i);
}
