package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.type.LogicalType;
import p.n61;
import p.q71;
import p.uo1;

/* JADX INFO: loaded from: classes.dex */
@n61
public class NumberDeserializers$ShortDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Short> {
    public static final NumberDeserializers$ShortDeserializer c = new NumberDeserializers$ShortDeserializer(Short.TYPE, 0);
    public static final NumberDeserializers$ShortDeserializer e = new NumberDeserializers$ShortDeserializer(Short.class, null);
    private static final long serialVersionUID = 1;

    public NumberDeserializers$ShortDeserializer(Class cls, Short sh) {
        super(cls, LogicalType.c, sh, (short) 0);
    }

    @Override // p.h71
    public final Object deserialize(q71 q71Var, DeserializationContext deserializationContext) {
        if (q71Var.T0()) {
            return Short.valueOf(q71Var.M0());
        }
        if (this._primitive) {
            return Short.valueOf(_parseShortPrimitive(q71Var, deserializationContext));
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
            return (Short) getNullValue(deserializationContext);
        }
        CoercionAction coercionAction = CoercionAction.l;
        CoercionAction coercionAction2 = CoercionAction.k;
        if (iC != 6) {
            if (iC == 7) {
                return Short.valueOf(q71Var.M0());
            }
            if (iC == 8) {
                CoercionAction coercionAction_checkFloatToIntCoercion = _checkFloatToIntCoercion(q71Var, deserializationContext, this._valueClass);
                return coercionAction_checkFloatToIntCoercion == coercionAction2 ? (Short) getNullValue(deserializationContext) : coercionAction_checkFloatToIntCoercion == coercionAction ? (Short) getEmptyValue(deserializationContext) : Short.valueOf(q71Var.M0());
            }
            deserializationContext.o(q71Var, getValueType(deserializationContext));
            throw null;
        }
        String strN0 = q71Var.N0();
        CoercionAction coercionAction_checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, strN0);
        if (coercionAction_checkFromStringCoercion == coercionAction2) {
            return (Short) getNullValue(deserializationContext);
        }
        if (coercionAction_checkFromStringCoercion == coercionAction) {
            return (Short) getEmptyValue(deserializationContext);
        }
        String strTrim = strN0.trim();
        if (_checkTextualNull(deserializationContext, strTrim)) {
            return (Short) getNullValue(deserializationContext);
        }
        try {
            int iA = uo1.a(strTrim);
            if (!_shortOverflow(iA)) {
                return Short.valueOf((short) iA);
            }
            deserializationContext.s(this._valueClass, strTrim, "overflow, value cannot be represented as 16-bit value", new Object[0]);
            throw null;
        } catch (IllegalArgumentException unused) {
            deserializationContext.s(this._valueClass, strTrim, "not a valid Short value", new Object[0]);
            throw null;
        }
    }
}
