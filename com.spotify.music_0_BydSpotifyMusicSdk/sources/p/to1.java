package p;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.deser.std.NullifyingDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$BooleanDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$ByteDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$CharacterDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$DoubleDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$FloatDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$IntegerDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$LongDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers$ShortDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import p.no2;
import p.q71;

/* JADX INFO: loaded from: classes.dex */
public abstract class to1 {
    public static final HashSet a = new HashSet();

    static {
        Class[] clsArr = {Boolean.class, Byte.class, Short.class, Character.class, Integer.class, Long.class, Float.class, Double.class, Number.class, BigDecimal.class, BigInteger.class};
        for (int i = 0; i < 11; i++) {
            a.add(clsArr[i].getName());
        }
    }

    public static StdDeserializer a(Class cls, String str) {
        if (cls.isPrimitive()) {
            if (cls == Integer.TYPE) {
                return NumberDeserializers$IntegerDeserializer.c;
            }
            if (cls == Boolean.TYPE) {
                return NumberDeserializers$BooleanDeserializer.c;
            }
            if (cls == Long.TYPE) {
                return NumberDeserializers$LongDeserializer.c;
            }
            if (cls == Double.TYPE) {
                return NumberDeserializers$DoubleDeserializer.c;
            }
            if (cls == Character.TYPE) {
                return NumberDeserializers$CharacterDeserializer.c;
            }
            if (cls == Byte.TYPE) {
                return NumberDeserializers$ByteDeserializer.c;
            }
            if (cls == Short.TYPE) {
                return NumberDeserializers$ShortDeserializer.c;
            }
            if (cls == Float.TYPE) {
                return NumberDeserializers$FloatDeserializer.c;
            }
            if (cls == Void.TYPE) {
                return NullifyingDeserializer.c;
            }
        } else {
            if (!a.contains(str)) {
                return null;
            }
            if (cls == Integer.class) {
                return NumberDeserializers$IntegerDeserializer.e;
            }
            if (cls == Boolean.class) {
                return NumberDeserializers$BooleanDeserializer.e;
            }
            if (cls == Long.class) {
                return NumberDeserializers$LongDeserializer.e;
            }
            if (cls == Double.class) {
                return NumberDeserializers$DoubleDeserializer.e;
            }
            if (cls == Character.class) {
                return NumberDeserializers$CharacterDeserializer.e;
            }
            if (cls == Byte.class) {
                return NumberDeserializers$ByteDeserializer.e;
            }
            if (cls == Short.class) {
                return NumberDeserializers$ShortDeserializer.e;
            }
            if (cls == Float.class) {
                return NumberDeserializers$FloatDeserializer.e;
            }
            if (cls == Number.class) {
                return new StdScalarDeserializer<Object>() { // from class: com.fasterxml.jackson.databind.deser.std.NumberDeserializers$NumberDeserializer
                    @Override // p.h71
                    public final Object deserialize(q71 q71Var, DeserializationContext deserializationContext) {
                        int iC = q71Var.c();
                        if (iC == 1) {
                            deserializationContext.p(q71Var, this._valueClass);
                            throw null;
                        }
                        if (iC == 3) {
                            return _deserializeFromArray(q71Var, deserializationContext);
                        }
                        if (iC != 6) {
                            if (iC == 7) {
                                return deserializationContext.t(StdDeserializer.F_MASK_INT_COERCIONS) ? _coerceIntegral(q71Var, deserializationContext) : q71Var.L0();
                            }
                            if (iC == 8) {
                                return (!deserializationContext.v(DeserializationFeature.c) || q71Var.U0()) ? q71Var.L0() : q71Var.o0();
                            }
                            deserializationContext.o(q71Var, getValueType(deserializationContext));
                            throw null;
                        }
                        String strN0 = q71Var.N0();
                        CoercionAction coercionAction_checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, strN0);
                        if (coercionAction_checkFromStringCoercion == CoercionAction.k) {
                            return getNullValue(deserializationContext);
                        }
                        if (coercionAction_checkFromStringCoercion == CoercionAction.l) {
                            return getEmptyValue(deserializationContext);
                        }
                        String strTrim = strN0.trim();
                        if (_hasTextualNull(strTrim)) {
                            return getNullValue(deserializationContext);
                        }
                        if (_isPosInf(strTrim)) {
                            return Double.valueOf(Double.POSITIVE_INFINITY);
                        }
                        if (_isNegInf(strTrim)) {
                            return Double.valueOf(Double.NEGATIVE_INFINITY);
                        }
                        if (_isNaN(strTrim)) {
                            return Double.valueOf(Double.NaN);
                        }
                        try {
                            if (!_isIntNumber(strTrim)) {
                                return deserializationContext.v(DeserializationFeature.c) ? new BigDecimal(strTrim) : Double.valueOf(strTrim);
                            }
                            if (deserializationContext.v(DeserializationFeature.e)) {
                                return new BigInteger(strTrim);
                            }
                            long j = Long.parseLong(strTrim);
                            return (deserializationContext.v(DeserializationFeature.k) || j > 2147483647L || j < -2147483648L) ? Long.valueOf(j) : Integer.valueOf((int) j);
                        } catch (IllegalArgumentException unused) {
                            deserializationContext.s(this._valueClass, strTrim, "not a valid number", new Object[0]);
                            throw null;
                        }
                    }

                    @Override // com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.deser.std.StdDeserializer, p.h71
                    public final Object deserializeWithType(q71 q71Var, DeserializationContext deserializationContext, no2 no2Var) {
                        int iC = q71Var.c();
                        return (iC == 6 || iC == 7 || iC == 8) ? deserialize(q71Var, deserializationContext) : no2Var.b(q71Var, deserializationContext);
                    }

                    @Override // p.h71
                    public final LogicalType logicalType() {
                        return LogicalType.c;
                    }
                };
            }
            if (cls == BigDecimal.class) {
                return new StdScalarDeserializer<BigDecimal>() { // from class: com.fasterxml.jackson.databind.deser.std.NumberDeserializers$BigDecimalDeserializer
                    @Override // p.h71
                    public final Object deserialize(q71 q71Var, DeserializationContext deserializationContext) {
                        int iC = q71Var.c();
                        if (iC == 1) {
                            deserializationContext.p(q71Var, this._valueClass);
                            throw null;
                        }
                        if (iC == 3) {
                            return _deserializeFromArray(q71Var, deserializationContext);
                        }
                        if (iC != 6) {
                            if (iC == 7 || iC == 8) {
                                return q71Var.o0();
                            }
                            deserializationContext.o(q71Var, getValueType(deserializationContext));
                            throw null;
                        }
                        String strN0 = q71Var.N0();
                        CoercionAction coercionAction_checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, strN0);
                        if (coercionAction_checkFromStringCoercion == CoercionAction.k) {
                            return (BigDecimal) getNullValue(deserializationContext);
                        }
                        if (coercionAction_checkFromStringCoercion == CoercionAction.l) {
                            return (BigDecimal) getEmptyValue(deserializationContext);
                        }
                        String strTrim = strN0.trim();
                        if (_hasTextualNull(strTrim)) {
                            return (BigDecimal) getNullValue(deserializationContext);
                        }
                        try {
                            return new BigDecimal(strTrim);
                        } catch (IllegalArgumentException unused) {
                            deserializationContext.s(this._valueClass, strTrim, "not a valid representation", new Object[0]);
                            throw null;
                        }
                    }

                    @Override // p.h71
                    public final Object getEmptyValue(DeserializationContext deserializationContext) {
                        return BigDecimal.ZERO;
                    }

                    @Override // p.h71
                    public final LogicalType logicalType() {
                        return LogicalType.e;
                    }
                };
            }
            if (cls == BigInteger.class) {
                return new StdScalarDeserializer<BigInteger>() { // from class: com.fasterxml.jackson.databind.deser.std.NumberDeserializers$BigIntegerDeserializer
                    @Override // p.h71
                    public final Object deserialize(q71 q71Var, DeserializationContext deserializationContext) {
                        if (q71Var.T0()) {
                            return q71Var.i();
                        }
                        int iC = q71Var.c();
                        if (iC == 1) {
                            deserializationContext.p(q71Var, this._valueClass);
                            throw null;
                        }
                        if (iC == 3) {
                            return _deserializeFromArray(q71Var, deserializationContext);
                        }
                        CoercionAction coercionAction = CoercionAction.l;
                        CoercionAction coercionAction2 = CoercionAction.k;
                        if (iC != 6) {
                            if (iC == 8) {
                                CoercionAction coercionAction_checkFloatToIntCoercion = _checkFloatToIntCoercion(q71Var, deserializationContext, this._valueClass);
                                return coercionAction_checkFloatToIntCoercion == coercionAction2 ? (BigInteger) getNullValue(deserializationContext) : coercionAction_checkFloatToIntCoercion == coercionAction ? (BigInteger) getEmptyValue(deserializationContext) : q71Var.o0().toBigInteger();
                            }
                            deserializationContext.o(q71Var, getValueType(deserializationContext));
                            throw null;
                        }
                        String strN0 = q71Var.N0();
                        CoercionAction coercionAction_checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, strN0);
                        if (coercionAction_checkFromStringCoercion == coercionAction2) {
                            return (BigInteger) getNullValue(deserializationContext);
                        }
                        if (coercionAction_checkFromStringCoercion == coercionAction) {
                            return (BigInteger) getEmptyValue(deserializationContext);
                        }
                        String strTrim = strN0.trim();
                        if (_hasTextualNull(strTrim)) {
                            return (BigInteger) getNullValue(deserializationContext);
                        }
                        try {
                            return new BigInteger(strTrim);
                        } catch (IllegalArgumentException unused) {
                            deserializationContext.s(this._valueClass, strTrim, "not a valid representation", new Object[0]);
                            throw null;
                        }
                    }

                    @Override // p.h71
                    public final Object getEmptyValue(DeserializationContext deserializationContext) {
                        return BigInteger.ZERO;
                    }

                    @Override // p.h71
                    public final LogicalType logicalType() {
                        return LogicalType.c;
                    }
                };
            }
        }
        throw new IllegalArgumentException("Internal error: can't find deserializer for ".concat(cls.getName()));
    }
}
