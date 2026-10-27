package com.android.networkstack.protobuf;

import com.android.networkstack.protobuf.Internal;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
final class FieldInfo implements Comparable<FieldInfo> {
    public abstract Field getCachedSizeField();

    public abstract Internal.EnumVerifier getEnumVerifier();

    public abstract Field getField();

    public abstract int getFieldNumber();

    public abstract Object getMapDefaultEntry();

    public abstract Class<?> getMessageFieldClass();

    public abstract OneofInfo getOneof();

    public abstract Field getPresenceField();

    public abstract int getPresenceMask();

    public abstract FieldType getType();

    public abstract boolean isEnforceUtf8();

    public abstract boolean isRequired();
}
