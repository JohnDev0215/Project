package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.type.LogicalType;
import p.n61;
import p.no2;
import p.q71;

/* JADX INFO: loaded from: classes.dex */
@n61
public class NumberDeserializers$DoubleDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Double> {
    public static final NumberDeserializers$DoubleDeserializer c = new NumberDeserializers$DoubleDeserializer(Double.TYPE, Double.valueOf(0.0d));
    public static final NumberDeserializers$DoubleDeserializer e = new NumberDeserializers$DoubleDeserializer(Double.class, null);
    private static final long serialVersionUID = 1;

    public NumberDeserializers$DoubleDeserializer(Class cls, Double d) {
        super(cls, LogicalType.e, d, Double.valueOf(0.0d));
    }

    public final Double a(q71 q71Var, DeserializationContext deserializationContext) {
        int iC = q71Var.c();
        if (iC == 1) {
            deserializationContext.p(q71Var, this._valueClass);
            throw null;
        }
        if (iC == 3) {
            return _deserializeFromArray(q71Var, deserializationContext);
        }
        if (iC == 11) {
            return (Double) getNullValue(deserializationContext);
        }
        if (iC != 6) {
            if (iC == 7 || iC == 8) {
                return Double.valueOf(q71Var.E0());
            }
            deserializationContext.o(q71Var, getValueType(deserializationContext));
            throw null;
        }
        String strN0 = q71Var.N0();
        Double d_checkDoubleSpecialValue = _checkDoubleSpecialValue(strN0);
        if (d_checkDoubleSpecialValue != null) {
            return d_checkDoubleSpecialValue;
        }
        CoercionAction coercionAction_checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, strN0);
        if (coercionAction_checkFromStringCoercion == CoercionAction.k) {
            return (Double) getNullValue(deserializationContext);
        }
        if (coercionAction_checkFromStringCoercion == CoercionAction.l) {
            return (Double) getEmptyValue(deserializationContext);
        }
        String strTrim = strN0.trim();
        if (_checkTextualNull(deserializationContext, strTrim)) {
            return (Double) getNullValue(deserializationContext);
        }
        try {
            return Double.valueOf(StdDeserializer._parseDouble(strTrim));
        } catch (IllegalArgumentException unused) {
            deserializationContext.s(this._valueClass, strTrim, "not a valid `Double` value", new Object[0]);
            throw null;
        }
    }

    @Override // p.h71
    public final Object deserialize(q71 q71Var, DeserializationContext deserializationContext) {
        return q71Var.S0(JsonToken.f27p) ? Double.valueOf(q71Var.E0()) : this._primitive ? Double.valueOf(_parseDoublePrimitive(q71Var, deserializationContext)) : a(q71Var, deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.deser.std.StdDeserializer, p.h71
    public final Object deserializeWithType(q71 q71Var, DeserializationContext deserializationContext, no2 no2Var) {
        return q71Var.S0(JsonToken.f27p) ? Double.valueOf(q71Var.E0()) : this._primitive ? Double.valueOf(_parseDoublePrimitive(q71Var, deserializationContext)) : a(q71Var, deserializationContext);
    }
}
