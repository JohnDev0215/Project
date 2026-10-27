package com.neusoft.de.nts.OneCoreTest.communication.messageModel;

import com.google.gson.annotations.SerializedName;
import java.util.EnumSet;

/* JADX INFO: loaded from: classes2.dex */
public enum EMessageType {
    Request,
    Result,
    Error,
    Signal,
    Register,
    Unregister,
    Unknown;

    private final String serializedName;

    EMessageType() {
        try {
            this.serializedName = ((SerializedName) getDeclaringClass().getField(name()).getAnnotation(SerializedName.class)).value();
        } catch (NoSuchFieldException | NullPointerException unused) {
            throw new IllegalStateException("Found no annotation of type " + SerializedName.class.getName());
        }
    }

    public static EMessageType bySerializedName(String str) {
        for (EMessageType eMessageType : EnumSet.allOf(EMessageType.class)) {
            if (eMessageType.serializedName.equals(str)) {
                return eMessageType;
            }
        }
        return Unknown;
    }

    public String serializedName() {
        return this.serializedName;
    }
}
