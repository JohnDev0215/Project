package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
abstract class JodaDeserializerBase<T> extends StdScalarDeserializer<T> {
    protected JodaDeserializerBase(Class<?> cls) {
        super(cls);
    }

    protected JodaDeserializerBase(JodaDeserializerBase<?> jodaDeserializerBase) {
        super(jodaDeserializerBase);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public LogicalType logicalType() {
        return LogicalType.DateTime;
    }

    protected boolean _isValidTimestampString(String str) {
        if (_isIntNumber(str)) {
            if (NumberInput.inLongRange(str, str.charAt(0) == '-')) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: com.fasterxml.jackson.datatype.joda.deser.JodaDeserializerBase$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction;

        static {
            int[] iArr = new int[CoercionAction.values().length];
            $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction = iArr;
            try {
                iArr[CoercionAction.AsEmpty.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[CoercionAction.TryConvert.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[CoercionAction.AsNull.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    protected T _fromEmptyString(JsonParser jsonParser, DeserializationContext deserializationContext, String str) throws IOException {
        if (AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$cfg$CoercionAction[_checkFromStringCoercion(deserializationContext, str).ordinal()] != 1) {
            return null;
        }
        return (T) getEmptyValue(deserializationContext);
    }

    public T _handleNotNumberOrString(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JavaType valueType = getValueType(deserializationContext);
        return (T) deserializationContext.handleUnexpectedToken(valueType, jsonParser.currentToken(), jsonParser, String.format("Cannot deserialize value of type %s from `JsonToken.%s`: expected Number or String", ClassUtil.getTypeDescription(valueType), jsonParser.currentToken()), new Object[0]);
    }
}
