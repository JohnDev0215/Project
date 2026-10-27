package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.type.LogicalType;
import p.n61;
import p.q71;
import p.uo1;

/* JADX INFO: loaded from: classes.dex */
@n61
public class NumberDeserializers$ByteDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Byte> {
    public static final NumberDeserializers$ByteDeserializer c = new NumberDeserializers$ByteDeserializer(Byte.TYPE, (byte) 0);
    public static final NumberDeserializers$ByteDeserializer e = new NumberDeserializers$ByteDeserializer(Byte.class, null);
    private static final long serialVersionUID = 1;

    public NumberDeserializers$ByteDeserializer(Class cls, Byte b) {
        super(cls, LogicalType.c, b, (byte) 0);
    }

    @Override // p.h71
    public final Object deserialize(q71 q71Var, DeserializationContext deserializationContext) {
        if (q71Var.T0()) {
            return Byte.valueOf(q71Var.q());
        }
        if (this._primitive) {
            return Byte.valueOf(_parseBytePrimitive(q71Var, deserializationContext));
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
            return (Byte) getNullValue(deserializationContext);
        }
        CoercionAction coercionAction = CoercionAction.l;
        CoercionAction coercionAction2 = CoercionAction.k;
        if (iC != 6) {
            if (iC == 7) {
                return Byte.valueOf(q71Var.q());
            }
            if (iC == 8) {
                CoercionAction coercionAction_checkFloatToIntCoercion = _checkFloatToIntCoercion(q71Var, deserializationContext, this._valueClass);
                return coercionAction_checkFloatToIntCoercion == coercionAction2 ? (Byte) getNullValue(deserializationContext) : coercionAction_checkFloatToIntCoercion == coercionAction ? (Byte) getEmptyValue(deserializationContext) : Byte.valueOf(q71Var.q());
            }
            deserializationContext.o(q71Var, getValueType(deserializationContext));
            throw null;
        }
        String strN0 = q71Var.N0();
        CoercionAction coercionAction_checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, strN0);
        if (coercionAction_checkFromStringCoercion == coercionAction2) {
            return (Byte) getNullValue(deserializationContext);
        }
        if (coercionAction_checkFromStringCoercion == coercionAction) {
            return (Byte) getEmptyValue(deserializationContext);
        }
        String strTrim = strN0.trim();
        if (_checkTextualNull(deserializationContext, strTrim)) {
            return (Byte) getNullValue(deserializationContext);
        }
        try {
            int iA = uo1.a(strTrim);
            if (!_byteOverflow(iA)) {
                return Byte.valueOf((byte) iA);
            }
            deserializationContext.s(this._valueClass, strTrim, "overflow, value cannot be represented as 8-bit value", new Object[0]);
            throw null;
        } catch (IllegalArgumentException unused) {
            deserializationContext.s(this._valueClass, strTrim, "not a valid Byte value", new Object[0]);
            throw null;
        }
    }
}
