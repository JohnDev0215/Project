package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.type.LogicalType;
import p.n61;
import p.no2;
import p.q71;

/* JADX INFO: loaded from: classes.dex */
@n61
public final class NumberDeserializers$BooleanDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Boolean> {
    public static final NumberDeserializers$BooleanDeserializer c = new NumberDeserializers$BooleanDeserializer(Boolean.TYPE, Boolean.FALSE);
    public static final NumberDeserializers$BooleanDeserializer e = new NumberDeserializers$BooleanDeserializer(Boolean.class, null);
    private static final long serialVersionUID = 1;

    public NumberDeserializers$BooleanDeserializer(Class cls, Boolean bool) {
        super(cls, LogicalType.k, bool, Boolean.FALSE);
    }

    @Override // p.h71
    public final Object deserialize(q71 q71Var, DeserializationContext deserializationContext) {
        JsonToken jsonTokenB = q71Var.b();
        return jsonTokenB == JsonToken.q ? Boolean.TRUE : jsonTokenB == JsonToken.r ? Boolean.FALSE : this._primitive ? Boolean.valueOf(_parseBooleanPrimitive(q71Var, deserializationContext)) : _parseBoolean(q71Var, deserializationContext, this._valueClass);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.deser.std.StdDeserializer, p.h71
    public final Object deserializeWithType(q71 q71Var, DeserializationContext deserializationContext, no2 no2Var) {
        JsonToken jsonTokenB = q71Var.b();
        return jsonTokenB == JsonToken.q ? Boolean.TRUE : jsonTokenB == JsonToken.r ? Boolean.FALSE : this._primitive ? Boolean.valueOf(_parseBooleanPrimitive(q71Var, deserializationContext)) : _parseBoolean(q71Var, deserializationContext, this._valueClass);
    }
}
