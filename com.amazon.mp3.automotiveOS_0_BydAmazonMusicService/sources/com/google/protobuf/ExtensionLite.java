package com.google.protobuf;

import com.google.protobuf.MessageLite;

/* JADX INFO: loaded from: classes7.dex */
public abstract class ExtensionLite<ContainingType extends MessageLite, Type> {
    public abstract int getNumber();

    boolean isLite() {
        return true;
    }
}
