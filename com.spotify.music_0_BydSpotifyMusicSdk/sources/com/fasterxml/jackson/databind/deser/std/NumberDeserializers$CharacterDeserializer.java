package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.type.LogicalType;
import p.n61;
import p.q71;

/* JADX INFO: loaded from: classes.dex */
@n61
public class NumberDeserializers$CharacterDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Character> {
    public static final NumberDeserializers$CharacterDeserializer c = new NumberDeserializers$CharacterDeserializer(Character.TYPE, 0);
    public static final NumberDeserializers$CharacterDeserializer e = new NumberDeserializers$CharacterDeserializer(Character.class, null);
    private static final long serialVersionUID = 1;

    public NumberDeserializers$CharacterDeserializer(Class cls, Character ch) {
        super(cls, LogicalType.c, ch, (char) 0);
    }

    @Override // p.h71
    public final Object deserialize(q71 q71Var, DeserializationContext deserializationContext) throws InvalidFormatException {
        int iC = q71Var.c();
        if (iC == 1) {
            deserializationContext.p(q71Var, this._valueClass);
            throw null;
        }
        if (iC == 3) {
            return _deserializeFromArray(q71Var, deserializationContext);
        }
        if (iC == 11) {
            if (this._primitive) {
                _verifyNullForPrimitive(deserializationContext);
            }
            return (Character) getNullValue(deserializationContext);
        }
        if (iC == 6) {
            String strN0 = q71Var.N0();
            if (strN0.length() == 1) {
                return Character.valueOf(strN0.charAt(0));
            }
            CoercionAction coercionAction_checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, strN0);
            if (coercionAction_checkFromStringCoercion == CoercionAction.k) {
                return (Character) getNullValue(deserializationContext);
            }
            if (coercionAction_checkFromStringCoercion == CoercionAction.l) {
                return (Character) getEmptyValue(deserializationContext);
            }
            String strTrim = strN0.trim();
            if (_checkTextualNull(deserializationContext, strTrim)) {
                return (Character) getNullValue(deserializationContext);
            }
            deserializationContext.s(handledType(), strTrim, "Expected either Integer value code or 1-character String", new Object[0]);
            throw null;
        }
        if (iC != 7) {
            deserializationContext.o(q71Var, getValueType(deserializationContext));
            throw null;
        }
        CoercionAction coercionActionD = deserializationContext.d(logicalType(), this._valueClass, CoercionInputShape.c);
        int iOrdinal = coercionActionD.ordinal();
        if (iOrdinal == 0) {
            _checkCoercionFail(deserializationContext, coercionActionD, this._valueClass, q71Var.L0(), "Integer value (" + q71Var.N0() + ")");
        } else if (iOrdinal != 2) {
            if (iOrdinal == 3) {
                return (Character) getEmptyValue(deserializationContext);
            }
            int iI0 = q71Var.I0();
            if (iI0 >= 0 && iI0 <= 65535) {
                return Character.valueOf((char) iI0);
            }
            deserializationContext.r(handledType(), Integer.valueOf(iI0), "value outside valid Character range (0x0000 - 0xFFFF)", new Object[0]);
            throw null;
        }
        return (Character) getNullValue(deserializationContext);
    }
}
