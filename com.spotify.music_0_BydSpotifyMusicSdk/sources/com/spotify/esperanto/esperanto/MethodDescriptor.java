package com.spotify.esperanto.esperanto;

import p.hf2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class MethodDescriptor {
    private final String inputType;
    private final String methodName;
    private final String outputType;

    public MethodDescriptor(String str, String str2, String str3) {
        v03.j(str, "inputType");
        v03.j(str2, "outputType");
        v03.j(str3, "methodName");
        this.inputType = str;
        this.outputType = str2;
        this.methodName = str3;
    }

    public static /* synthetic */ MethodDescriptor copy$default(MethodDescriptor methodDescriptor, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = methodDescriptor.inputType;
        }
        if ((i & 2) != 0) {
            str2 = methodDescriptor.outputType;
        }
        if ((i & 4) != 0) {
            str3 = methodDescriptor.methodName;
        }
        return methodDescriptor.copy(str, str2, str3);
    }

    public final String component1() {
        return this.inputType;
    }

    public final String component2() {
        return this.outputType;
    }

    public final String component3() {
        return this.methodName;
    }

    public final MethodDescriptor copy(String str, String str2, String str3) {
        v03.j(str, "inputType");
        v03.j(str2, "outputType");
        v03.j(str3, "methodName");
        return new MethodDescriptor(str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MethodDescriptor)) {
            return false;
        }
        MethodDescriptor methodDescriptor = (MethodDescriptor) obj;
        return v03.b(this.inputType, methodDescriptor.inputType) && v03.b(this.outputType, methodDescriptor.outputType) && v03.b(this.methodName, methodDescriptor.methodName);
    }

    public final String getInputType() {
        return this.inputType;
    }

    public final String getMethodName() {
        return this.methodName;
    }

    public final String getOutputType() {
        return this.outputType;
    }

    public int hashCode() {
        return this.methodName.hashCode() + hf2.b(this.outputType, this.inputType.hashCode() * 31, 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("MethodDescriptor(inputType=");
        sb.append(this.inputType);
        sb.append(", outputType=");
        sb.append(this.outputType);
        sb.append(", methodName=");
        return hf2.d(sb, this.methodName, ')');
    }
}
