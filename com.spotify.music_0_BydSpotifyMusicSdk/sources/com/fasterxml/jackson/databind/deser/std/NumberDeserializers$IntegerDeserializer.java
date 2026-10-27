package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.type.LogicalType;
import p.n61;
import p.no2;
import p.q71;

/* JADX INFO: loaded from: classes.dex */
@n61
public final class NumberDeserializers$IntegerDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Integer> {
    public static final NumberDeserializers$IntegerDeserializer c = new NumberDeserializers$IntegerDeserializer(Integer.TYPE, 0);
    public static final NumberDeserializers$IntegerDeserializer e = new NumberDeserializers$IntegerDeserializer(Integer.class, null);
    private static final long serialVersionUID = 1;

    public NumberDeserializers$IntegerDeserializer(Class cls, Integer num) {
        super(cls, LogicalType.c, num, 0);
    }

    @Override // p.h71
    public final Object deserialize(q71 q71Var, DeserializationContext deserializationContext) {
        return q71Var.T0() ? Integer.valueOf(q71Var.I0()) : this._primitive ? Integer.valueOf(_parseIntPrimitive(q71Var, deserializationContext)) : _parseInteger(q71Var, deserializationContext, Integer.class);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.deser.std.StdDeserializer, p.h71
    public final Object deserializeWithType(q71 q71Var, DeserializationContext deserializationContext, no2 no2Var) {
        return q71Var.T0() ? Integer.valueOf(q71Var.I0()) : this._primitive ? Integer.valueOf(_parseIntPrimitive(q71Var, deserializationContext)) : _parseInteger(q71Var, deserializationContext, Integer.class);
    }

    @Override // p.h71
    public final boolean isCachable() {
        return true;
    }
}
