package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat$Feature;
import com.fasterxml.jackson.annotation.JsonFormat$Value;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonParser$NumberType;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.impl.NullsAsEmptyProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsFailProvider;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import p.h71;
import p.l81;
import p.no2;
import p.q71;
import p.ro1;
import p.sl;
import p.ts2;
import p.u4;
import p.uo1;
import p.vs;

/* JADX INFO: loaded from: classes.dex */
public abstract class StdDeserializer<T> extends h71 implements Serializable {
    private static final long serialVersionUID = 1;
    protected final Class<?> _valueClass;
    protected final JavaType _valueType;
    protected static final int F_MASK_INT_COERCIONS = DeserializationFeature.e.b() | DeserializationFeature.k.b();

    @Deprecated
    protected static final int F_MASK_ACCEPT_ARRAYS = DeserializationFeature.m.b() | DeserializationFeature.n.b();

    public StdDeserializer(Class cls) {
        this._valueClass = cls;
        this._valueType = null;
    }

    public static final boolean _isBlank(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) > ' ') {
                return false;
            }
        }
        return true;
    }

    public static final boolean _neitherNull(Object obj, Object obj2) {
        return (obj == null || obj2 == null) ? false : true;
    }

    public static final double _parseDouble(String str) {
        if ("2.2250738585072012e-308".equals(str)) {
            return Double.MIN_NORMAL;
        }
        return Double.parseDouble(str);
    }

    public final boolean _byteOverflow(int i) {
        return i < -128 || i > 255;
    }

    public CoercionAction _checkCoercionFail(DeserializationContext deserializationContext, CoercionAction coercionAction, Class<?> cls, Object obj, String str) throws InvalidFormatException {
        if (coercionAction != CoercionAction.c) {
            return coercionAction;
        }
        Object[] objArr = {str, _coercedTypeDesc()};
        deserializationContext.getClass();
        String str2 = String.format("Cannot coerce %s to %s (but could if coercion was enabled using `CoercionConfig`)", objArr);
        deserializationContext.getClass();
        throw new InvalidFormatException(null, str2, obj, cls);
    }

    public Double _checkDoubleSpecialValue(String str) {
        if (str.isEmpty()) {
            return null;
        }
        char cCharAt = str.charAt(0);
        if (cCharAt == '-') {
            if (_isNegInf(str)) {
                return Double.valueOf(Double.NEGATIVE_INFINITY);
            }
            return null;
        }
        if (cCharAt == 'I') {
            if (_isPosInf(str)) {
                return Double.valueOf(Double.POSITIVE_INFINITY);
            }
            return null;
        }
        if (cCharAt == 'N' && _isNaN(str)) {
            return Double.valueOf(Double.NaN);
        }
        return null;
    }

    public Float _checkFloatSpecialValue(String str) {
        if (str.isEmpty()) {
            return null;
        }
        char cCharAt = str.charAt(0);
        if (cCharAt == '-') {
            if (_isNegInf(str)) {
                return Float.valueOf(Float.NEGATIVE_INFINITY);
            }
            return null;
        }
        if (cCharAt == 'I') {
            if (_isPosInf(str)) {
                return Float.valueOf(Float.POSITIVE_INFINITY);
            }
            return null;
        }
        if (cCharAt == 'N' && _isNaN(str)) {
            return Float.valueOf(Float.NaN);
        }
        return null;
    }

    public CoercionAction _checkFloatToIntCoercion(q71 q71Var, DeserializationContext deserializationContext, Class<?> cls) {
        CoercionAction coercionActionD = deserializationContext.d(LogicalType.c, cls, CoercionInputShape.e);
        if (coercionActionD != CoercionAction.c) {
            return coercionActionD;
        }
        return _checkCoercionFail(deserializationContext, coercionActionD, cls, q71Var.L0(), "Floating-point value (" + q71Var.N0() + ")");
    }

    public CoercionAction _checkFromStringCoercion(DeserializationContext deserializationContext, String str) {
        return _checkFromStringCoercion(deserializationContext, str, logicalType(), handledType());
    }

    public boolean _checkTextualNull(DeserializationContext deserializationContext, String str) {
        if (!_hasTextualNull(str)) {
            return false;
        }
        MapperFeature mapperFeature = MapperFeature.n;
        if (!deserializationContext.w(mapperFeature)) {
            _reportFailedNullCoerce(deserializationContext, true, mapperFeature, "String \"null\"");
        }
        return true;
    }

    public Boolean _coerceBooleanFromInt(q71 q71Var, DeserializationContext deserializationContext, Class<?> cls) throws InvalidFormatException {
        CoercionAction coercionActionD = deserializationContext.d(LogicalType.k, cls, CoercionInputShape.c);
        int iOrdinal = coercionActionD.ordinal();
        if (iOrdinal == 0) {
            _checkCoercionFail(deserializationContext, coercionActionD, cls, q71Var.L0(), "Integer value (" + q71Var.N0() + ")");
            return Boolean.FALSE;
        }
        if (iOrdinal == 2) {
            return null;
        }
        if (iOrdinal == 3) {
            return Boolean.FALSE;
        }
        if (q71Var.K0() == JsonParser$NumberType.c) {
            return Boolean.valueOf(q71Var.I0() != 0);
        }
        return Boolean.valueOf(!"0".equals(q71Var.N0()));
    }

    @Deprecated
    public Object _coerceEmptyString(DeserializationContext deserializationContext, boolean z) {
        boolean z2;
        DeserializationFeature deserializationFeature;
        MapperFeature mapperFeature = MapperFeature.n;
        if (deserializationContext.w(mapperFeature)) {
            if (z) {
                DeserializationFeature deserializationFeature2 = DeserializationFeature.l;
                if (deserializationContext.v(deserializationFeature2)) {
                    z2 = false;
                    deserializationFeature = deserializationFeature2;
                }
            }
            return getNullValue(deserializationContext);
        }
        z2 = true;
        deserializationFeature = mapperFeature;
        _reportFailedNullCoerce(deserializationContext, z2, deserializationFeature, "empty String (\"\")");
        return null;
    }

    public Object _coerceIntegral(q71 q71Var, DeserializationContext deserializationContext) {
        return deserializationContext.v(DeserializationFeature.e) ? q71Var.i() : deserializationContext.v(DeserializationFeature.k) ? Long.valueOf(q71Var.J0()) : q71Var.L0();
    }

    @Deprecated
    public Object _coerceNullToken(DeserializationContext deserializationContext, boolean z) {
        if (z) {
            _verifyNullForPrimitive(deserializationContext);
        }
        return getNullValue(deserializationContext);
    }

    @Deprecated
    public Object _coerceTextualNull(DeserializationContext deserializationContext, boolean z) {
        MapperFeature mapperFeature = MapperFeature.n;
        if (!deserializationContext.w(mapperFeature)) {
            _reportFailedNullCoerce(deserializationContext, true, mapperFeature, "String \"null\"");
        }
        return getNullValue(deserializationContext);
    }

    public String _coercedTypeDesc() {
        String strC;
        JavaType valueType = getValueType();
        boolean z = true;
        if (valueType == null || valueType.i()) {
            Class<?> clsHandledType = handledType();
            if (!clsHandledType.isArray() && !Collection.class.isAssignableFrom(clsHandledType) && !Map.class.isAssignableFrom(clsHandledType)) {
                z = false;
            }
            strC = vs.c(clsHandledType);
        } else {
            if (!valueType.h() && !valueType.b()) {
                z = false;
            }
            strC = vs.d(valueType);
        }
        return z ? u4.x("element of ", strC) : u4.h(strC, " value");
    }

    public T _deserializeFromArray(q71 q71Var, DeserializationContext deserializationContext) {
        CoercionAction coercionAction_findCoercionFromEmptyArray = _findCoercionFromEmptyArray(deserializationContext);
        boolean zV = deserializationContext.v(DeserializationFeature.m);
        if (zV || coercionAction_findCoercionFromEmptyArray != CoercionAction.c) {
            JsonToken jsonTokenV0 = q71Var.V0();
            JsonToken jsonToken = JsonToken.l;
            if (jsonTokenV0 == jsonToken) {
                int iOrdinal = coercionAction_findCoercionFromEmptyArray.ordinal();
                if (iOrdinal == 1 || iOrdinal == 2) {
                    return (T) getNullValue(deserializationContext);
                }
                if (iOrdinal == 3) {
                    return (T) getEmptyValue(deserializationContext);
                }
            } else if (zV) {
                T t_deserializeWrappedValue = _deserializeWrappedValue(q71Var, deserializationContext);
                if (q71Var.V0() != jsonToken) {
                    handleMissingEndArrayForSingle(q71Var, deserializationContext);
                }
                return t_deserializeWrappedValue;
            }
        }
        deserializationContext.n(getValueType(deserializationContext), JsonToken.k, q71Var, null, new Object[0]);
        throw null;
    }

    @Deprecated
    public T _deserializeFromEmpty(q71 q71Var, DeserializationContext deserializationContext) {
        if (!q71Var.S0(JsonToken.k) || !deserializationContext.v(DeserializationFeature.n)) {
            deserializationContext.o(q71Var, getValueType(deserializationContext));
            throw null;
        }
        if (q71Var.V0() == JsonToken.l) {
            return null;
        }
        deserializationContext.o(q71Var, getValueType(deserializationContext));
        throw null;
    }

    public Object _deserializeFromEmptyString(q71 q71Var, DeserializationContext deserializationContext, CoercionAction coercionAction, Class<?> cls, String str) throws InvalidFormatException {
        int iOrdinal = coercionAction.ordinal();
        if (iOrdinal == 0) {
            _checkCoercionFail(deserializationContext, coercionAction, cls, "", "empty String (\"\")");
            return null;
        }
        if (iOrdinal != 3) {
            return null;
        }
        return getEmptyValue(deserializationContext);
    }

    public T _deserializeFromString(q71 q71Var, DeserializationContext deserializationContext) {
        ts2 valueInstantiator = getValueInstantiator();
        Class<?> clsHandledType = handledType();
        String strR0 = q71Var.R0();
        if (strR0.isEmpty()) {
            return (T) _deserializeFromEmptyString(q71Var, deserializationContext, deserializationContext.d(logicalType(), clsHandledType, CoercionInputShape.m), clsHandledType, "empty String (\"\")");
        }
        if (_isBlank(strR0)) {
            return (T) _deserializeFromEmptyString(q71Var, deserializationContext, deserializationContext.e(logicalType(), clsHandledType), clsHandledType, "blank String (all whitespace)");
        }
        deserializationContext.getClass();
        return (T) deserializationContext.m(clsHandledType, valueInstantiator, "no String-argument constructor/factory method to deserialize from String value ('%s')", strR0);
    }

    public T _deserializeWrappedValue(q71 q71Var, DeserializationContext deserializationContext) {
        return q71Var.S0(JsonToken.k) ? (T) handleNestedArrayForSingle(q71Var, deserializationContext) : (T) deserialize(q71Var, deserializationContext);
    }

    @Deprecated
    public void _failDoubleToIntCoercion(q71 q71Var, DeserializationContext deserializationContext, String str) {
        deserializationContext.z(handledType(), "Cannot coerce a floating-point value ('%s') into %s (enable `DeserializationFeature.ACCEPT_FLOAT_AS_INT` to allow)", q71Var.R0(), str);
        throw null;
    }

    public CoercionAction _findCoercionFromBlankString(DeserializationContext deserializationContext) {
        return deserializationContext.e(logicalType(), handledType());
    }

    public CoercionAction _findCoercionFromEmptyArray(DeserializationContext deserializationContext) {
        return deserializationContext.d(logicalType(), handledType(), CoercionInputShape.l);
    }

    public CoercionAction _findCoercionFromEmptyString(DeserializationContext deserializationContext) {
        return deserializationContext.d(logicalType(), handledType(), CoercionInputShape.m);
    }

    public final ro1 _findNullProvider(DeserializationContext deserializationContext, sl slVar, Nulls nulls, h71 h71Var) {
        if (nulls == Nulls.e) {
            if (slVar == null) {
                return new NullsFailProvider(null, deserializationContext.c(h71Var == null ? Object.class : h71Var.handledType()));
            }
            return new NullsFailProvider(slVar.c(), slVar.getType());
        }
        if (nulls != Nulls.k) {
            if (nulls == Nulls.c) {
                return NullsConstantProvider.c;
            }
            return null;
        }
        if (h71Var == null) {
            return null;
        }
        if (h71Var instanceof BeanDeserializerBase) {
            BeanDeserializerBase beanDeserializerBase = (BeanDeserializerBase) h71Var;
            if (!beanDeserializerBase.getValueInstantiator().e()) {
                JavaType valueType = slVar == null ? beanDeserializerBase.getValueType() : slVar.getType();
                deserializationContext.y(valueType, String.format("Cannot create empty instance of %s, no default Creator", valueType));
                throw null;
            }
        }
        AccessPattern emptyAccessPattern = h71Var.getEmptyAccessPattern();
        if (emptyAccessPattern == AccessPattern.c) {
            return NullsConstantProvider.e;
        }
        if (emptyAccessPattern != AccessPattern.e) {
            return new NullsAsEmptyProvider(h71Var);
        }
        Object emptyValue = h71Var.getEmptyValue(deserializationContext);
        return emptyValue == null ? NullsConstantProvider.e : new NullsConstantProvider(emptyValue);
    }

    public boolean _hasTextualNull(String str) {
        return "null".equals(str);
    }

    public final boolean _intOverflow(long j) {
        return j < -2147483648L || j > 2147483647L;
    }

    @Deprecated
    public boolean _isEmptyOrTextualNull(String str) {
        return str.isEmpty() || "null".equals(str);
    }

    public boolean _isFalse(String str) {
        char cCharAt = str.charAt(0);
        if (cCharAt == 'f') {
            return "false".equals(str);
        }
        if (cCharAt == 'F') {
            return "FALSE".equals(str) || "False".equals(str);
        }
        return false;
    }

    public final boolean _isIntNumber(String str) {
        int i;
        int length = str.length();
        if (length <= 0) {
            return false;
        }
        char cCharAt = str.charAt(0);
        if (cCharAt != '-' && cCharAt != '+') {
            i = 0;
        } else {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        while (i < length) {
            char cCharAt2 = str.charAt(i);
            if (cCharAt2 > '9' || cCharAt2 < '0') {
                return false;
            }
            i++;
        }
        return true;
    }

    public final boolean _isNaN(String str) {
        return "NaN".equals(str);
    }

    public final boolean _isNegInf(String str) {
        return "-Infinity".equals(str) || "-INF".equals(str);
    }

    public final boolean _isPosInf(String str) {
        return "Infinity".equals(str) || "INF".equals(str);
    }

    public boolean _isTrue(String str) {
        char cCharAt = str.charAt(0);
        if (cCharAt == 't') {
            return "true".equals(str);
        }
        if (cCharAt == 'T') {
            return "TRUE".equals(str) || "True".equals(str);
        }
        return false;
    }

    public Number _nonNullNumber(Number number) {
        if (number == null) {
            return 0;
        }
        return number;
    }

    public final Boolean _parseBoolean(q71 q71Var, DeserializationContext deserializationContext, Class<?> cls) {
        int iC = q71Var.c();
        if (iC == 1) {
            deserializationContext.p(q71Var, cls);
            throw null;
        }
        if (iC == 3) {
            return (Boolean) _deserializeFromArray(q71Var, deserializationContext);
        }
        if (iC != 6) {
            if (iC == 7) {
                return _coerceBooleanFromInt(q71Var, deserializationContext, cls);
            }
            switch (iC) {
                case 9:
                    return Boolean.TRUE;
                case 10:
                    return Boolean.FALSE;
                case 11:
                    return null;
                default:
                    deserializationContext.p(q71Var, cls);
                    throw null;
            }
        }
        String strN0 = q71Var.N0();
        CoercionAction coercionAction_checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, strN0, LogicalType.k, cls);
        if (coercionAction_checkFromStringCoercion == CoercionAction.k) {
            return null;
        }
        if (coercionAction_checkFromStringCoercion == CoercionAction.l) {
            return Boolean.FALSE;
        }
        String strTrim = strN0.trim();
        int length = strTrim.length();
        if (length == 4) {
            if (_isTrue(strTrim)) {
                return Boolean.TRUE;
            }
        } else if (length == 5 && _isFalse(strTrim)) {
            return Boolean.FALSE;
        }
        if (_checkTextualNull(deserializationContext, strTrim)) {
            return null;
        }
        deserializationContext.s(cls, strTrim, "only \"true\" or \"false\" recognized", new Object[0]);
        throw null;
    }

    @Deprecated
    public boolean _parseBooleanFromInt(q71 q71Var, DeserializationContext deserializationContext) {
        _verifyNumberForScalarCoercion(deserializationContext, q71Var);
        return !"0".equals(q71Var.N0());
    }

    @Deprecated
    public final boolean _parseBooleanPrimitive(DeserializationContext deserializationContext, q71 q71Var, Class<?> cls) {
        return _parseBooleanPrimitive(q71Var, deserializationContext);
    }

    public final byte _parseBytePrimitive(q71 q71Var, DeserializationContext deserializationContext) {
        int iC = q71Var.c();
        if (iC == 1) {
            deserializationContext.p(q71Var, Byte.TYPE);
            throw null;
        }
        if (iC != 3) {
            if (iC == 11) {
                _verifyNullForPrimitive(deserializationContext);
                return (byte) 0;
            }
            CoercionAction coercionAction = CoercionAction.l;
            CoercionAction coercionAction2 = CoercionAction.k;
            if (iC == 6) {
                String strN0 = q71Var.N0();
                CoercionAction coercionAction_checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, strN0, LogicalType.c, Byte.TYPE);
                if (coercionAction_checkFromStringCoercion == coercionAction2) {
                    _verifyNullForPrimitive(deserializationContext);
                    return (byte) 0;
                }
                if (coercionAction_checkFromStringCoercion == coercionAction) {
                    return (byte) 0;
                }
                String strTrim = strN0.trim();
                if (_hasTextualNull(strTrim)) {
                    _verifyNullForPrimitiveCoercion(deserializationContext, strTrim);
                    return (byte) 0;
                }
                try {
                    int iA = uo1.a(strTrim);
                    if (!_byteOverflow(iA)) {
                        return (byte) iA;
                    }
                    deserializationContext.s(this._valueClass, strTrim, "overflow, value cannot be represented as 8-bit value", new Object[0]);
                    throw null;
                } catch (IllegalArgumentException unused) {
                    deserializationContext.s(this._valueClass, strTrim, "not a valid `byte` value", new Object[0]);
                    throw null;
                }
            }
            if (iC == 7) {
                return q71Var.q();
            }
            if (iC == 8) {
                CoercionAction coercionAction_checkFloatToIntCoercion = _checkFloatToIntCoercion(q71Var, deserializationContext, Byte.TYPE);
                if (coercionAction_checkFloatToIntCoercion == coercionAction2 || coercionAction_checkFloatToIntCoercion == coercionAction) {
                    return (byte) 0;
                }
                return q71Var.q();
            }
        } else if (deserializationContext.v(DeserializationFeature.m)) {
            if (q71Var.V0() == JsonToken.k) {
                return ((Byte) handleNestedArrayForSingle(q71Var, deserializationContext)).byteValue();
            }
            byte b_parseBytePrimitive = _parseBytePrimitive(q71Var, deserializationContext);
            _verifyEndArrayForSingle(q71Var, deserializationContext);
            return b_parseBytePrimitive;
        }
        deserializationContext.o(q71Var, deserializationContext.c(Byte.TYPE));
        throw null;
    }

    public Date _parseDate(q71 q71Var, DeserializationContext deserializationContext) {
        int iC = q71Var.c();
        if (iC == 1) {
            deserializationContext.p(q71Var, this._valueClass);
            throw null;
        }
        if (iC == 3) {
            return _parseDateFromArray(q71Var, deserializationContext);
        }
        if (iC == 11) {
            return (Date) getNullValue(deserializationContext);
        }
        if (iC == 6) {
            return _parseDate(q71Var.N0().trim(), deserializationContext);
        }
        if (iC == 7) {
            return new Date(q71Var.J0());
        }
        deserializationContext.p(q71Var, this._valueClass);
        throw null;
    }

    public Date _parseDateFromArray(q71 q71Var, DeserializationContext deserializationContext) {
        CoercionAction coercionAction_findCoercionFromEmptyArray = _findCoercionFromEmptyArray(deserializationContext);
        boolean zV = deserializationContext.v(DeserializationFeature.m);
        if (zV || coercionAction_findCoercionFromEmptyArray != CoercionAction.c) {
            JsonToken jsonTokenV0 = q71Var.V0();
            if (jsonTokenV0 == JsonToken.l) {
                int iOrdinal = coercionAction_findCoercionFromEmptyArray.ordinal();
                if (iOrdinal == 1 || iOrdinal == 2) {
                    return (Date) getNullValue(deserializationContext);
                }
                if (iOrdinal == 3) {
                    return (Date) getEmptyValue(deserializationContext);
                }
            } else if (zV) {
                if (jsonTokenV0 == JsonToken.k) {
                    return (Date) handleNestedArrayForSingle(q71Var, deserializationContext);
                }
                Date date_parseDate = _parseDate(q71Var, deserializationContext);
                _verifyEndArrayForSingle(q71Var, deserializationContext);
                return date_parseDate;
            }
        }
        deserializationContext.n(deserializationContext.c(this._valueClass), JsonToken.k, q71Var, null, new Object[0]);
        throw null;
    }

    public final double _parseDoublePrimitive(q71 q71Var, DeserializationContext deserializationContext) {
        int iC = q71Var.c();
        if (iC == 1) {
            deserializationContext.p(q71Var, Double.TYPE);
            throw null;
        }
        if (iC != 3) {
            if (iC == 11) {
                _verifyNullForPrimitive(deserializationContext);
                return 0.0d;
            }
            if (iC == 6) {
                String strN0 = q71Var.N0();
                Double d_checkDoubleSpecialValue = _checkDoubleSpecialValue(strN0);
                if (d_checkDoubleSpecialValue != null) {
                    return d_checkDoubleSpecialValue.doubleValue();
                }
                CoercionAction coercionAction_checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, strN0, LogicalType.c, Double.TYPE);
                if (coercionAction_checkFromStringCoercion == CoercionAction.k) {
                    _verifyNullForPrimitive(deserializationContext);
                    return 0.0d;
                }
                if (coercionAction_checkFromStringCoercion == CoercionAction.l) {
                    return 0.0d;
                }
                String strTrim = strN0.trim();
                if (!_hasTextualNull(strTrim)) {
                    return _parseDoublePrimitive(deserializationContext, strTrim);
                }
                _verifyNullForPrimitiveCoercion(deserializationContext, strTrim);
                return 0.0d;
            }
            if (iC == 7 || iC == 8) {
                return q71Var.E0();
            }
        } else if (deserializationContext.v(DeserializationFeature.m)) {
            if (q71Var.V0() == JsonToken.k) {
                return ((Double) handleNestedArrayForSingle(q71Var, deserializationContext)).doubleValue();
            }
            double d_parseDoublePrimitive = _parseDoublePrimitive(q71Var, deserializationContext);
            _verifyEndArrayForSingle(q71Var, deserializationContext);
            return d_parseDoublePrimitive;
        }
        deserializationContext.p(q71Var, Double.TYPE);
        throw null;
    }

    public final float _parseFloatPrimitive(q71 q71Var, DeserializationContext deserializationContext) {
        int iC = q71Var.c();
        if (iC == 1) {
            deserializationContext.p(q71Var, Float.TYPE);
            throw null;
        }
        if (iC != 3) {
            if (iC == 11) {
                _verifyNullForPrimitive(deserializationContext);
                return 0.0f;
            }
            if (iC == 6) {
                String strN0 = q71Var.N0();
                Float f_checkFloatSpecialValue = _checkFloatSpecialValue(strN0);
                if (f_checkFloatSpecialValue != null) {
                    return f_checkFloatSpecialValue.floatValue();
                }
                CoercionAction coercionAction_checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, strN0, LogicalType.c, Float.TYPE);
                if (coercionAction_checkFromStringCoercion == CoercionAction.k) {
                    _verifyNullForPrimitive(deserializationContext);
                    return 0.0f;
                }
                if (coercionAction_checkFromStringCoercion == CoercionAction.l) {
                    return 0.0f;
                }
                String strTrim = strN0.trim();
                if (!_hasTextualNull(strTrim)) {
                    return _parseFloatPrimitive(deserializationContext, strTrim);
                }
                _verifyNullForPrimitiveCoercion(deserializationContext, strTrim);
                return 0.0f;
            }
            if (iC == 7 || iC == 8) {
                return q71Var.H0();
            }
        } else if (deserializationContext.v(DeserializationFeature.m)) {
            if (q71Var.V0() == JsonToken.k) {
                return ((Float) handleNestedArrayForSingle(q71Var, deserializationContext)).floatValue();
            }
            float f_parseFloatPrimitive = _parseFloatPrimitive(q71Var, deserializationContext);
            _verifyEndArrayForSingle(q71Var, deserializationContext);
            return f_parseFloatPrimitive;
        }
        deserializationContext.p(q71Var, Float.TYPE);
        throw null;
    }

    public final int _parseIntPrimitive(q71 q71Var, DeserializationContext deserializationContext) {
        int iC = q71Var.c();
        if (iC == 1) {
            deserializationContext.p(q71Var, Integer.TYPE);
            throw null;
        }
        if (iC != 3) {
            if (iC == 11) {
                _verifyNullForPrimitive(deserializationContext);
                return 0;
            }
            CoercionAction coercionAction = CoercionAction.l;
            CoercionAction coercionAction2 = CoercionAction.k;
            if (iC == 6) {
                String strN0 = q71Var.N0();
                CoercionAction coercionAction_checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, strN0, LogicalType.c, Integer.TYPE);
                if (coercionAction_checkFromStringCoercion == coercionAction2) {
                    _verifyNullForPrimitive(deserializationContext);
                    return 0;
                }
                if (coercionAction_checkFromStringCoercion == coercionAction) {
                    return 0;
                }
                String strTrim = strN0.trim();
                if (!_hasTextualNull(strTrim)) {
                    return _parseIntPrimitive(deserializationContext, strTrim);
                }
                _verifyNullForPrimitiveCoercion(deserializationContext, strTrim);
                return 0;
            }
            if (iC == 7) {
                return q71Var.I0();
            }
            if (iC == 8) {
                CoercionAction coercionAction_checkFloatToIntCoercion = _checkFloatToIntCoercion(q71Var, deserializationContext, Integer.TYPE);
                if (coercionAction_checkFloatToIntCoercion == coercionAction2 || coercionAction_checkFloatToIntCoercion == coercionAction) {
                    return 0;
                }
                return q71Var.P0();
            }
        } else if (deserializationContext.v(DeserializationFeature.m)) {
            if (q71Var.V0() == JsonToken.k) {
                return ((Integer) handleNestedArrayForSingle(q71Var, deserializationContext)).intValue();
            }
            int i_parseIntPrimitive = _parseIntPrimitive(q71Var, deserializationContext);
            _verifyEndArrayForSingle(q71Var, deserializationContext);
            return i_parseIntPrimitive;
        }
        deserializationContext.p(q71Var, Integer.TYPE);
        throw null;
    }

    public final Integer _parseInteger(q71 q71Var, DeserializationContext deserializationContext, Class<?> cls) {
        int iC = q71Var.c();
        if (iC == 1) {
            deserializationContext.p(q71Var, cls);
            throw null;
        }
        if (iC == 3) {
            return (Integer) _deserializeFromArray(q71Var, deserializationContext);
        }
        if (iC == 11) {
            return (Integer) getNullValue(deserializationContext);
        }
        CoercionAction coercionAction = CoercionAction.l;
        CoercionAction coercionAction2 = CoercionAction.k;
        if (iC != 6) {
            if (iC == 7) {
                return Integer.valueOf(q71Var.I0());
            }
            if (iC == 8) {
                CoercionAction coercionAction_checkFloatToIntCoercion = _checkFloatToIntCoercion(q71Var, deserializationContext, cls);
                return coercionAction_checkFloatToIntCoercion == coercionAction2 ? (Integer) getNullValue(deserializationContext) : coercionAction_checkFloatToIntCoercion == coercionAction ? (Integer) getEmptyValue(deserializationContext) : Integer.valueOf(q71Var.P0());
            }
            deserializationContext.o(q71Var, getValueType(deserializationContext));
            throw null;
        }
        String strN0 = q71Var.N0();
        CoercionAction coercionAction_checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, strN0);
        if (coercionAction_checkFromStringCoercion == coercionAction2) {
            return (Integer) getNullValue(deserializationContext);
        }
        if (coercionAction_checkFromStringCoercion == coercionAction) {
            return (Integer) getEmptyValue(deserializationContext);
        }
        String strTrim = strN0.trim();
        return _checkTextualNull(deserializationContext, strTrim) ? (Integer) getNullValue(deserializationContext) : _parseInteger(deserializationContext, strTrim);
    }

    public final Long _parseLong(q71 q71Var, DeserializationContext deserializationContext, Class<?> cls) {
        int iC = q71Var.c();
        if (iC == 1) {
            deserializationContext.p(q71Var, cls);
            throw null;
        }
        if (iC == 3) {
            return (Long) _deserializeFromArray(q71Var, deserializationContext);
        }
        if (iC == 11) {
            return (Long) getNullValue(deserializationContext);
        }
        CoercionAction coercionAction = CoercionAction.l;
        CoercionAction coercionAction2 = CoercionAction.k;
        if (iC != 6) {
            if (iC == 7) {
                return Long.valueOf(q71Var.J0());
            }
            if (iC == 8) {
                CoercionAction coercionAction_checkFloatToIntCoercion = _checkFloatToIntCoercion(q71Var, deserializationContext, cls);
                return coercionAction_checkFloatToIntCoercion == coercionAction2 ? (Long) getNullValue(deserializationContext) : coercionAction_checkFloatToIntCoercion == coercionAction ? (Long) getEmptyValue(deserializationContext) : Long.valueOf(q71Var.Q0());
            }
            deserializationContext.o(q71Var, getValueType(deserializationContext));
            throw null;
        }
        String strN0 = q71Var.N0();
        CoercionAction coercionAction_checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, strN0);
        if (coercionAction_checkFromStringCoercion == coercionAction2) {
            return (Long) getNullValue(deserializationContext);
        }
        if (coercionAction_checkFromStringCoercion == coercionAction) {
            return (Long) getEmptyValue(deserializationContext);
        }
        String strTrim = strN0.trim();
        return _checkTextualNull(deserializationContext, strTrim) ? (Long) getNullValue(deserializationContext) : _parseLong(deserializationContext, strTrim);
    }

    public final long _parseLongPrimitive(q71 q71Var, DeserializationContext deserializationContext) {
        int iC = q71Var.c();
        if (iC == 1) {
            deserializationContext.p(q71Var, Long.TYPE);
            throw null;
        }
        if (iC != 3) {
            if (iC == 11) {
                _verifyNullForPrimitive(deserializationContext);
                return 0L;
            }
            CoercionAction coercionAction = CoercionAction.l;
            CoercionAction coercionAction2 = CoercionAction.k;
            if (iC == 6) {
                String strN0 = q71Var.N0();
                CoercionAction coercionAction_checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, strN0, LogicalType.c, Long.TYPE);
                if (coercionAction_checkFromStringCoercion == coercionAction2) {
                    _verifyNullForPrimitive(deserializationContext);
                    return 0L;
                }
                if (coercionAction_checkFromStringCoercion == coercionAction) {
                    return 0L;
                }
                String strTrim = strN0.trim();
                if (!_hasTextualNull(strTrim)) {
                    return _parseLongPrimitive(deserializationContext, strTrim);
                }
                _verifyNullForPrimitiveCoercion(deserializationContext, strTrim);
                return 0L;
            }
            if (iC == 7) {
                return q71Var.J0();
            }
            if (iC == 8) {
                CoercionAction coercionAction_checkFloatToIntCoercion = _checkFloatToIntCoercion(q71Var, deserializationContext, Long.TYPE);
                if (coercionAction_checkFloatToIntCoercion == coercionAction2 || coercionAction_checkFloatToIntCoercion == coercionAction) {
                    return 0L;
                }
                return q71Var.Q0();
            }
        } else if (deserializationContext.v(DeserializationFeature.m)) {
            if (q71Var.V0() == JsonToken.k) {
                return ((Long) handleNestedArrayForSingle(q71Var, deserializationContext)).longValue();
            }
            long j_parseLongPrimitive = _parseLongPrimitive(q71Var, deserializationContext);
            _verifyEndArrayForSingle(q71Var, deserializationContext);
            return j_parseLongPrimitive;
        }
        deserializationContext.p(q71Var, Long.TYPE);
        throw null;
    }

    public final short _parseShortPrimitive(q71 q71Var, DeserializationContext deserializationContext) {
        int iC = q71Var.c();
        if (iC == 1) {
            deserializationContext.p(q71Var, Short.TYPE);
            throw null;
        }
        if (iC != 3) {
            if (iC == 11) {
                _verifyNullForPrimitive(deserializationContext);
                return (short) 0;
            }
            CoercionAction coercionAction = CoercionAction.l;
            CoercionAction coercionAction2 = CoercionAction.k;
            if (iC == 6) {
                String strN0 = q71Var.N0();
                LogicalType logicalType = LogicalType.c;
                Class cls = Short.TYPE;
                CoercionAction coercionAction_checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, strN0, logicalType, cls);
                if (coercionAction_checkFromStringCoercion == coercionAction2) {
                    _verifyNullForPrimitive(deserializationContext);
                    return (short) 0;
                }
                if (coercionAction_checkFromStringCoercion == coercionAction) {
                    return (short) 0;
                }
                String strTrim = strN0.trim();
                if (_hasTextualNull(strTrim)) {
                    _verifyNullForPrimitiveCoercion(deserializationContext, strTrim);
                    return (short) 0;
                }
                try {
                    int iA = uo1.a(strTrim);
                    if (!_shortOverflow(iA)) {
                        return (short) iA;
                    }
                    deserializationContext.s(cls, strTrim, "overflow, value cannot be represented as 16-bit value", new Object[0]);
                    throw null;
                } catch (IllegalArgumentException unused) {
                    deserializationContext.s(Short.TYPE, strTrim, "not a valid `short` value", new Object[0]);
                    throw null;
                }
            }
            if (iC == 7) {
                return q71Var.M0();
            }
            if (iC == 8) {
                CoercionAction coercionAction_checkFloatToIntCoercion = _checkFloatToIntCoercion(q71Var, deserializationContext, Short.TYPE);
                if (coercionAction_checkFloatToIntCoercion == coercionAction2 || coercionAction_checkFloatToIntCoercion == coercionAction) {
                    return (short) 0;
                }
                return q71Var.M0();
            }
        } else if (deserializationContext.v(DeserializationFeature.m)) {
            if (q71Var.V0() == JsonToken.k) {
                return ((Short) handleNestedArrayForSingle(q71Var, deserializationContext)).shortValue();
            }
            short s_parseShortPrimitive = _parseShortPrimitive(q71Var, deserializationContext);
            _verifyEndArrayForSingle(q71Var, deserializationContext);
            return s_parseShortPrimitive;
        }
        deserializationContext.o(q71Var, deserializationContext.c(Short.TYPE));
        throw null;
    }

    public final String _parseString(q71 q71Var, DeserializationContext deserializationContext) {
        if (q71Var.S0(JsonToken.o)) {
            return q71Var.N0();
        }
        if (q71Var.S0(JsonToken.n)) {
            Object objG0 = q71Var.G0();
            if (objG0 instanceof byte[]) {
                return deserializationContext.h().a((byte[]) objG0);
            }
            if (objG0 == null) {
                return null;
            }
            return objG0.toString();
        }
        if (q71Var.S0(JsonToken.c)) {
            deserializationContext.p(q71Var, this._valueClass);
            throw null;
        }
        String strR0 = q71Var.R0();
        if (strR0 != null) {
            return strR0;
        }
        deserializationContext.p(q71Var, String.class);
        throw null;
    }

    public void _reportFailedNullCoerce(DeserializationContext deserializationContext, boolean z, Enum<?> r5, String str) {
        deserializationContext.A(this, "Cannot coerce %s to Null value as %s (%s `%s.%s` to allow)", str, _coercedTypeDesc(), z ? "enable" : "disable", r5.getDeclaringClass().getSimpleName(), r5.name());
        throw null;
    }

    public final boolean _shortOverflow(int i) {
        return i < -32768 || i > 32767;
    }

    public void _verifyEndArrayForSingle(q71 q71Var, DeserializationContext deserializationContext) {
        if (q71Var.V0() != JsonToken.l) {
            handleMissingEndArrayForSingle(q71Var, deserializationContext);
        }
    }

    public final void _verifyNullForPrimitive(DeserializationContext deserializationContext) {
        if (deserializationContext.v(DeserializationFeature.l)) {
            deserializationContext.A(this, "Cannot coerce `null` to %s (disable `DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES` to allow)", _coercedTypeDesc());
            throw null;
        }
    }

    public final void _verifyNullForPrimitiveCoercion(DeserializationContext deserializationContext, String str) {
        boolean z;
        DeserializationFeature deserializationFeature;
        MapperFeature mapperFeature = MapperFeature.n;
        if (deserializationContext.w(mapperFeature)) {
            DeserializationFeature deserializationFeature2 = DeserializationFeature.l;
            if (!deserializationContext.v(deserializationFeature2)) {
                return;
            }
            z = false;
            deserializationFeature = deserializationFeature2;
        } else {
            z = true;
            deserializationFeature = mapperFeature;
        }
        _reportFailedNullCoerce(deserializationContext, z, deserializationFeature, str.isEmpty() ? "empty String (\"\")" : String.format("String \"%s\"", str));
    }

    @Deprecated
    public final void _verifyNullForScalarCoercion(DeserializationContext deserializationContext, String str) {
        MapperFeature mapperFeature = MapperFeature.n;
        if (deserializationContext.w(mapperFeature)) {
            return;
        }
        _reportFailedNullCoerce(deserializationContext, true, mapperFeature, str.isEmpty() ? "empty String (\"\")" : String.format("String \"%s\"", str));
    }

    @Deprecated
    public void _verifyNumberForScalarCoercion(DeserializationContext deserializationContext, q71 q71Var) {
        MapperFeature mapperFeature = MapperFeature.n;
        if (deserializationContext.w(mapperFeature)) {
            return;
        }
        deserializationContext.A(this, "Cannot coerce Number (%s) to %s (enable `%s.%s` to allow)", q71Var.N0(), _coercedTypeDesc(), mapperFeature.getDeclaringClass().getSimpleName(), "ALLOW_COERCION_OF_SCALARS");
        throw null;
    }

    @Deprecated
    public void _verifyStringForScalarCoercion(DeserializationContext deserializationContext, String str) {
        MapperFeature mapperFeature = MapperFeature.n;
        if (deserializationContext.w(mapperFeature)) {
            return;
        }
        deserializationContext.A(this, "Cannot coerce String \"%s\" to %s (enable `%s.%s` to allow)", str, _coercedTypeDesc(), mapperFeature.getDeclaringClass().getSimpleName(), "ALLOW_COERCION_OF_SCALARS");
        throw null;
    }

    @Override // p.h71
    public Object deserializeWithType(q71 q71Var, DeserializationContext deserializationContext, no2 no2Var) {
        return no2Var.a(q71Var, deserializationContext);
    }

    public ro1 findContentNullProvider(DeserializationContext deserializationContext, sl slVar, h71 h71Var) {
        Nulls nullsFindContentNullStyle = findContentNullStyle(deserializationContext, slVar);
        if (nullsFindContentNullStyle == Nulls.c) {
            return NullsConstantProvider.c;
        }
        if (nullsFindContentNullStyle != Nulls.e) {
            ro1 ro1Var_findNullProvider = _findNullProvider(deserializationContext, slVar, nullsFindContentNullStyle, h71Var);
            return ro1Var_findNullProvider != null ? ro1Var_findNullProvider : h71Var;
        }
        if (slVar != null) {
            return new NullsFailProvider(slVar.c(), slVar.getType().d());
        }
        JavaType javaTypeC = deserializationContext.c(h71Var.handledType());
        if (javaTypeC.h()) {
            javaTypeC = javaTypeC.d();
        }
        return new NullsFailProvider(null, javaTypeC);
    }

    public Nulls findContentNullStyle(DeserializationContext deserializationContext, sl slVar) {
        return slVar != null ? slVar.getMetadata().a() : deserializationContext.i().b().a();
    }

    public h71 findConvertingContentDeserializer(DeserializationContext deserializationContext, sl slVar, h71 h71Var) {
        if (_neitherNull(deserializationContext.g(), slVar)) {
            slVar.j();
        }
        return h71Var;
    }

    public h71 findDeserializer(DeserializationContext deserializationContext, JavaType javaType, sl slVar) {
        return deserializationContext.f(slVar, javaType);
    }

    public Boolean findFormatFeature(DeserializationContext deserializationContext, sl slVar, Class<?> cls, JsonFormat$Feature jsonFormat$Feature) {
        JsonFormat$Value jsonFormat$ValueFindFormatOverrides = findFormatOverrides(deserializationContext, slVar, cls);
        if (jsonFormat$ValueFindFormatOverrides != null) {
            return jsonFormat$ValueFindFormatOverrides.b(jsonFormat$Feature);
        }
        return null;
    }

    public JsonFormat$Value findFormatOverrides(DeserializationContext deserializationContext, sl slVar, Class<?> cls) {
        return slVar != null ? slVar.d(deserializationContext.i(), cls) : deserializationContext.j(cls);
    }

    public final ro1 findValueNullProvider(DeserializationContext deserializationContext, SettableBeanProperty settableBeanProperty, PropertyMetadata propertyMetadata) {
        return null;
    }

    @Deprecated
    public final Class<?> getValueClass() {
        return this._valueClass;
    }

    public ts2 getValueInstantiator() {
        return null;
    }

    public JavaType getValueType() {
        return this._valueType;
    }

    public void handleMissingEndArrayForSingle(q71 q71Var, DeserializationContext deserializationContext) {
        deserializationContext.B(this, JsonToken.l, "Attempted to unwrap '%s' value from an array (with `DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS`) but it contains more than one value", handledType().getName());
        throw null;
    }

    public Object handleNestedArrayForSingle(q71 q71Var, DeserializationContext deserializationContext) {
        deserializationContext.n(getValueType(deserializationContext), q71Var.b(), q71Var, String.format("Cannot deserialize instance of %s out of %s token: nested Arrays not allowed with %s", vs.g(this._valueClass), JsonToken.k, "DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS"), new Object[0]);
        throw null;
    }

    public void handleUnknownProperty(q71 q71Var, DeserializationContext deserializationContext, Object obj, String str) {
        if (obj == null) {
            obj = handledType();
        }
        deserializationContext.q(q71Var, this, obj, str);
    }

    @Override // p.h71
    public Class<?> handledType() {
        return this._valueClass;
    }

    public boolean isDefaultDeserializer(h71 h71Var) {
        return vs.f(h71Var);
    }

    public boolean isDefaultKeyDeserializer(l81 l81Var) {
        return vs.f(l81Var);
    }

    public StdDeserializer(JavaType javaType) {
        this._valueClass = javaType == null ? Object.class : javaType.f();
        this._valueType = javaType;
    }

    public CoercionAction _checkFromStringCoercion(DeserializationContext deserializationContext, String str, LogicalType logicalType, Class<?> cls) {
        if (str.isEmpty()) {
            return _checkCoercionFail(deserializationContext, deserializationContext.d(logicalType, cls, CoercionInputShape.m), cls, str, "empty String (\"\")");
        }
        boolean z_isBlank = _isBlank(str);
        CoercionAction coercionAction = CoercionAction.c;
        if (z_isBlank) {
            return _checkCoercionFail(deserializationContext, deserializationContext.e(logicalType, cls), cls, str, "blank String (all whitespace)");
        }
        if (deserializationContext.u(StreamReadCapability.c)) {
            return CoercionAction.e;
        }
        CoercionAction coercionActionD = deserializationContext.d(logicalType, cls, CoercionInputShape.k);
        if (coercionActionD != coercionAction) {
            return coercionActionD;
        }
        deserializationContext.A(this, "Cannot coerce String value (\"%s\") to %s (but might if coercion using `CoercionConfig` was enabled)", str, _coercedTypeDesc());
        throw null;
    }

    public final boolean _parseBooleanPrimitive(q71 q71Var, DeserializationContext deserializationContext) {
        int iC = q71Var.c();
        if (iC == 1) {
            deserializationContext.p(q71Var, Boolean.TYPE);
            throw null;
        }
        if (iC != 3) {
            if (iC == 6) {
                String strN0 = q71Var.N0();
                LogicalType logicalType = LogicalType.k;
                Class cls = Boolean.TYPE;
                CoercionAction coercionAction_checkFromStringCoercion = _checkFromStringCoercion(deserializationContext, strN0, logicalType, cls);
                if (coercionAction_checkFromStringCoercion == CoercionAction.k) {
                    _verifyNullForPrimitive(deserializationContext);
                    return false;
                }
                if (coercionAction_checkFromStringCoercion == CoercionAction.l) {
                    return false;
                }
                String strTrim = strN0.trim();
                int length = strTrim.length();
                if (length == 4) {
                    if (_isTrue(strTrim)) {
                        return true;
                    }
                } else if (length == 5 && _isFalse(strTrim)) {
                    return false;
                }
                if (_hasTextualNull(strTrim)) {
                    _verifyNullForPrimitiveCoercion(deserializationContext, strTrim);
                    return false;
                }
                deserializationContext.s(cls, strTrim, "only \"true\"/\"True\"/\"TRUE\" or \"false\"/\"False\"/\"FALSE\" recognized", new Object[0]);
                throw null;
            }
            if (iC == 7) {
                return Boolean.TRUE.equals(_coerceBooleanFromInt(q71Var, deserializationContext, Boolean.TYPE));
            }
            switch (iC) {
                case 9:
                    return true;
                case 11:
                    _verifyNullForPrimitive(deserializationContext);
                case 10:
                    return false;
            }
        } else if (deserializationContext.v(DeserializationFeature.m)) {
            if (q71Var.V0() == JsonToken.k) {
                return ((Boolean) handleNestedArrayForSingle(q71Var, deserializationContext)).booleanValue();
            }
            boolean z_parseBooleanPrimitive = _parseBooleanPrimitive(q71Var, deserializationContext);
            _verifyEndArrayForSingle(q71Var, deserializationContext);
            return z_parseBooleanPrimitive;
        }
        deserializationContext.p(q71Var, Boolean.TYPE);
        throw null;
    }

    public JavaType getValueType(DeserializationContext deserializationContext) {
        JavaType javaType = this._valueType;
        return javaType != null ? javaType : deserializationContext.c(this._valueClass);
    }

    public Date _parseDate(String str, DeserializationContext deserializationContext) {
        try {
            if (str.isEmpty()) {
                if (_checkFromStringCoercion(deserializationContext, str).ordinal() != 3) {
                    return null;
                }
                return new Date(0L);
            }
            if (_hasTextualNull(str)) {
                return null;
            }
            return deserializationContext.x(str);
        } catch (IllegalArgumentException e) {
            deserializationContext.s(this._valueClass, str, "not a valid representation (error: %s)", vs.b(e));
            throw null;
        }
    }

    public final Integer _parseInteger(DeserializationContext deserializationContext, String str) {
        try {
            if (str.length() > 9) {
                long jB = uo1.b(str);
                if (!_intOverflow(jB)) {
                    return Integer.valueOf((int) jB);
                }
                deserializationContext.s(Integer.class, str, "Overflow: numeric value (%s) out of range of `java.lang.Integer` (%d -%d)", str, Integer.MIN_VALUE, Integer.MAX_VALUE);
                throw null;
            }
            return Integer.valueOf(uo1.a(str));
        } catch (IllegalArgumentException unused) {
            deserializationContext.s(Integer.class, str, "not a valid `java.lang.Integer` value", new Object[0]);
            throw null;
        }
    }

    public final Long _parseLong(DeserializationContext deserializationContext, String str) {
        try {
            return Long.valueOf(uo1.b(str));
        } catch (IllegalArgumentException unused) {
            deserializationContext.s(Long.class, str, "not a valid `java.lang.Long` value", new Object[0]);
            throw null;
        }
    }

    public final double _parseDoublePrimitive(DeserializationContext deserializationContext, String str) {
        try {
            return _parseDouble(str);
        } catch (IllegalArgumentException unused) {
            deserializationContext.s(Double.TYPE, str, "not a valid `double` value (as String to convert)", new Object[0]);
            throw null;
        }
    }

    public final float _parseFloatPrimitive(DeserializationContext deserializationContext, String str) {
        try {
            return Float.parseFloat(str);
        } catch (IllegalArgumentException unused) {
            deserializationContext.s(Float.TYPE, str, "not a valid `float` value", new Object[0]);
            throw null;
        }
    }

    public final int _parseIntPrimitive(DeserializationContext deserializationContext, String str) {
        try {
            if (str.length() > 9) {
                long jB = uo1.b(str);
                if (!_intOverflow(jB)) {
                    return (int) jB;
                }
                deserializationContext.s(Integer.TYPE, str, "Overflow: numeric value (%s) out of range of int (%d -%d)", str, Integer.MIN_VALUE, Integer.MAX_VALUE);
                throw null;
            }
            return uo1.a(str);
        } catch (IllegalArgumentException unused) {
            deserializationContext.s(Integer.TYPE, str, "not a valid `int` value", new Object[0]);
            throw null;
        }
    }

    public final long _parseLongPrimitive(DeserializationContext deserializationContext, String str) {
        try {
            return uo1.b(str);
        } catch (IllegalArgumentException unused) {
            deserializationContext.s(Long.TYPE, str, "not a valid `long` value", new Object[0]);
            throw null;
        }
    }
}
