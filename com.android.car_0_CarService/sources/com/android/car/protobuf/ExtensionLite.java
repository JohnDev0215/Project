package com.android.car.protobuf;

import com.android.car.protobuf.MessageLite;
import com.android.car.protobuf.WireFormat;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ExtensionLite<ContainingType extends MessageLite, Type> {
    public abstract Type getDefaultValue();

    public abstract WireFormat.FieldType getLiteType();

    public abstract MessageLite getMessageDefaultInstance();

    public abstract int getNumber();

    public abstract boolean isRepeated();

    boolean isLite() {
        return true;
    }
}
