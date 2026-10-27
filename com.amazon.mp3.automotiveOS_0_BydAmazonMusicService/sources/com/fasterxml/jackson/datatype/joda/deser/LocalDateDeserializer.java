package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import java.io.IOException;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

/* JADX INFO: loaded from: classes6.dex */
public class LocalDateDeserializer extends JodaDateDeserializerBase<LocalDate> {
    private static final long serialVersionUID = 1;

    public LocalDateDeserializer() {
        this(FormatConfig.DEFAULT_LOCAL_DATEONLY_FORMAT);
    }

    public LocalDateDeserializer(JacksonJodaDateFormat jacksonJodaDateFormat) {
        super(LocalDate.class, jacksonJodaDateFormat);
    }

    @Override // com.fasterxml.jackson.datatype.joda.deser.JodaDateDeserializerBase
    public JodaDateDeserializerBase<?> withFormat(JacksonJodaDateFormat jacksonJodaDateFormat) {
        return new LocalDateDeserializer(jacksonJodaDateFormat);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        int iCurrentTokenId = jsonParser.currentTokenId();
        if (iCurrentTokenId == 1) {
            return _fromString(jsonParser, deserializationContext, deserializationContext.extractScalarFromObject(jsonParser, this, handledType()));
        }
        if (iCurrentTokenId == 6) {
            return _fromString(jsonParser, deserializationContext, jsonParser.getText());
        }
        if (iCurrentTokenId == 7) {
            return _fromTimestamp(deserializationContext, jsonParser.getLongValue());
        }
        if (jsonParser.isExpectedStartArrayToken()) {
            return _fromArray(jsonParser, deserializationContext);
        }
        return (LocalDate) deserializationContext.handleUnexpectedToken(handledType(), jsonParser.currentToken(), jsonParser, "expected String, Number or JSON Array", new Object[0]);
    }

    protected LocalDate _fromString(JsonParser jsonParser, DeserializationContext deserializationContext, String str) throws IOException {
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return _fromEmptyString(jsonParser, deserializationContext, strTrim);
        }
        if (deserializationContext.isEnabled(StreamReadCapability.UNTYPED_SCALARS) && _isValidTimestampString(strTrim)) {
            return _fromTimestamp(deserializationContext, NumberInput.parseLong(strTrim));
        }
        return this._format.createParser(deserializationContext).parseLocalDate(strTrim);
    }

    protected LocalDate _fromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        int iNextIntValue = jsonParser.nextIntValue(-1);
        if (iNextIntValue == -1) {
            iNextIntValue = _parseIntPrimitive(jsonParser, deserializationContext);
        }
        int iNextIntValue2 = jsonParser.nextIntValue(-1);
        if (iNextIntValue2 == -1) {
            iNextIntValue2 = _parseIntPrimitive(jsonParser, deserializationContext);
        }
        int iNextIntValue3 = jsonParser.nextIntValue(-1);
        if (iNextIntValue3 == -1) {
            iNextIntValue3 = _parseIntPrimitive(jsonParser, deserializationContext);
        }
        JsonToken jsonTokenNextToken = jsonParser.nextToken();
        JsonToken jsonToken = JsonToken.END_ARRAY;
        if (jsonTokenNextToken != jsonToken) {
            throw deserializationContext.wrongTokenException(jsonParser, handledType(), jsonToken, "after LocalDate ints");
        }
        return new LocalDate(iNextIntValue, iNextIntValue2, iNextIntValue3);
    }

    protected LocalDate _fromTimestamp(DeserializationContext deserializationContext, long j) {
        return new LocalDate(j, this._format.isTimezoneExplicit() ? this._format.getTimeZone() : DateTimeZone.forTimeZone(deserializationContext.getTimeZone()));
    }
}
