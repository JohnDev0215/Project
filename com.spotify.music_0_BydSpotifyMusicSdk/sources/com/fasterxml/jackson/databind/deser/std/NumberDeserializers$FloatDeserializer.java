package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.type.LogicalType;
import p.n61;
import p.q71;

/* JADX INFO: loaded from: classes.dex */
@n61
public class NumberDeserializers$FloatDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Float> {
    public static final NumberDeserializers$FloatDeserializer c = new NumberDeserializers$FloatDeserializer(Float.TYPE, Float.valueOf(0.0f));
    public static final NumberDeserializers$FloatDeserializer e = new NumberDeserializers$FloatDeserializer(Float.class, null);
    private static final long serialVersionUID = 1;

    public NumberDeserializers$FloatDeserializer(Class cls, Float f) {
        super(cls, LogicalType.e, f, Float.valueOf(0.0f));
    }

    @Override // p.h71
    public final Object deserialize(q71 q71Var, DeserializationContext deserializationContext) {
        if (q71Var.S0(JsonToken.f27p)) {
            return Float.valueOf(q71Var.H0());
        }
        if (this._primitive) {
            return Float.valueOf(_parseFloatPrimitive(q71Var, deserializationContext));
        }
        int iC = q71Var.c();
        if (iC == 1) {
            deserializationContext.p(q71Var, this._valueClass);
            throw null;
        }
        if (iC == 3) {
            return _deserializeFromArray(q71Var, deserializationContext);
        }
        if (iC == 11) {
            return (Float) getNullValue(deserializationContext);
        }
        if (iC != 6) {
            if (iC == 7 || iC == 8) {
                return Float.valueOf(q71Var.H0());
            }
            deserializationContext.o(q71Var, getValueType(deserializationContext));
            throw null;
        }
        String strN0 = q71Var.N0();
        Float f_checkFloatSpecialValue = _checkFloatSpecialValue(strN0);
        if (f_checkFloatSpecialValue != null) {
            return f_checkFloatSpecialValue;
        }
        CoercionAction coercionAction_checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, strN0);
        if (coercionAction_checkFromStringCoercion == CoercionAction.k) {
            return (Float) getNullValue(deserializationContext);
        }
        if (coercionAction_checkFromStringCoercion == CoercionAction.l) {
            return (Float) getEmptyValue(deserializationContext);
        }
        String strTrim = strN0.trim();
        if (_checkTextualNull(deserializationContext, strTrim)) {
            return (Float) getNullValue(deserializationContext);
        }
        try {
            return Float.valueOf(Float.parseFloat(strTrim));
        } catch (IllegalArgumentException unused) {
            deserializationContext.s(this._valueClass, strTrim, "not a valid `Float` value", new Object[0]);
            throw null;
        }
    }
}
