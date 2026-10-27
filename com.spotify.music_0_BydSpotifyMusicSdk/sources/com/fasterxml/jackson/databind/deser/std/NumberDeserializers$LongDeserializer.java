package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.type.LogicalType;
import p.n61;
import p.q71;

/* JADX INFO: loaded from: classes.dex */
@n61
public final class NumberDeserializers$LongDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Long> {
    public static final NumberDeserializers$LongDeserializer c = new NumberDeserializers$LongDeserializer(Long.TYPE, 0L);
    public static final NumberDeserializers$LongDeserializer e = new NumberDeserializers$LongDeserializer(Long.class, null);
    private static final long serialVersionUID = 1;

    public NumberDeserializers$LongDeserializer(Class cls, Long l) {
        super(cls, LogicalType.c, l, 0L);
    }

    @Override // p.h71
    public final Object deserialize(q71 q71Var, DeserializationContext deserializationContext) {
        return q71Var.T0() ? Long.valueOf(q71Var.J0()) : this._primitive ? Long.valueOf(_parseLongPrimitive(q71Var, deserializationContext)) : _parseLong(q71Var, deserializationContext, Long.class);
    }

    @Override // p.h71
    public final boolean isCachable() {
        return true;
    }
}
