package com.google.protobuf;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class ProtoSyntax {
    public static final ProtoSyntax c;
    public static final ProtoSyntax e;
    public static final /* synthetic */ ProtoSyntax[] k;

    static {
        ProtoSyntax protoSyntax = new ProtoSyntax("PROTO2", 0);
        c = protoSyntax;
        ProtoSyntax protoSyntax2 = new ProtoSyntax("PROTO3", 1);
        e = protoSyntax2;
        k = new ProtoSyntax[]{protoSyntax, protoSyntax2};
    }

    public static ProtoSyntax valueOf(String str) {
        return (ProtoSyntax) Enum.valueOf(ProtoSyntax.class, str);
    }

    public static ProtoSyntax[] values() {
        return (ProtoSyntax[]) k.clone();
    }
}
