package com.google.firebase.perf.plugin.instrumentation.network.hook;

import com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentation;
import org.objectweb.asm.MethodVisitor;

/* JADX INFO: loaded from: classes6.dex */
public class BaseInstrumentation implements NetworkObjectInstrumentation {
    private final String desc;
    private final String name;
    private final String owner;

    @Override // com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentation
    public void injectAfter(MethodVisitor methodVisitor) {
    }

    @Override // com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentation
    public void injectBefore(MethodVisitor methodVisitor) {
    }

    public BaseInstrumentation(String str, String str2, String str3) {
        this.owner = str;
        this.name = str2;
        this.desc = str3;
    }

    @Override // com.google.firebase.perf.plugin.instrumentation.network.NetworkObjectInstrumentation
    public boolean replaceMethod(MethodVisitor methodVisitor, int i) {
        methodVisitor.visitMethodInsn(184, this.owner, this.name, this.desc, false);
        return true;
    }

    public String toString() {
        return "[" + getClass().getSimpleName() + " : " + this.owner + " : " + this.name + " : " + this.desc + "]";
    }
}
