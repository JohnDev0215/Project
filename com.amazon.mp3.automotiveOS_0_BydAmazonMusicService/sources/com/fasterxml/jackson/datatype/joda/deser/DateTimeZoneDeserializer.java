package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import java.io.IOException;
import org.joda.time.DateTimeZone;

/* JADX INFO: loaded from: classes6.dex */
public class DateTimeZoneDeserializer extends JodaDeserializerBase<DateTimeZone> {
    private static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.datatype.joda.deser.JodaDeserializerBase, com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public /* bridge */ /* synthetic */ Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return super.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
    }

    @Override // com.fasterxml.jackson.datatype.joda.deser.JodaDeserializerBase, com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public /* bridge */ /* synthetic */ LogicalType logicalType() {
        return super.logicalType();
    }

    public DateTimeZoneDeserializer() {
        super((Class<?>) DateTimeZone.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public DateTimeZone deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        int iCurrentTokenId = jsonParser.currentTokenId();
        if (iCurrentTokenId == 1) {
            return _fromString(jsonParser, deserializationContext, deserializationContext.extractScalarFromObject(jsonParser, this, handledType()));
        }
        if (iCurrentTokenId == 6) {
            return _fromString(jsonParser, deserializationContext, jsonParser.getText());
        }
        if (iCurrentTokenId == 7) {
            return _fromOffset(deserializationContext, jsonParser.getIntValue());
        }
        return _handleNotNumberOrString(jsonParser, deserializationContext);
    }

    protected DateTimeZone _fromString(JsonParser jsonParser, DeserializationContext deserializationContext, String str) throws IOException {
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return _fromEmptyString(jsonParser, deserializationContext, strTrim);
        }
        if (deserializationContext.isEnabled(StreamReadCapability.UNTYPED_SCALARS) && _isValidTimestampString(strTrim)) {
            return _fromOffset(deserializationContext, NumberInput.parseInt(strTrim));
        }
        return DateTimeZone.forID(strTrim);
    }

    protected DateTimeZone _fromOffset(DeserializationContext deserializationContext, int i) {
        return DateTimeZone.forOffsetHours(i);
    }
}
