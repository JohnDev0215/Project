package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import java.io.IOException;
import org.joda.time.LocalTime;

/* JADX INFO: loaded from: classes6.dex */
public class LocalTimeDeserializer extends JodaDateDeserializerBase<LocalTime> {
    private static final long serialVersionUID = 1;

    public LocalTimeDeserializer() {
        this(FormatConfig.DEFAULT_LOCAL_TIMEONLY_PARSER);
    }

    public LocalTimeDeserializer(JacksonJodaDateFormat jacksonJodaDateFormat) {
        super(LocalTime.class, jacksonJodaDateFormat);
    }

    @Override // com.fasterxml.jackson.datatype.joda.deser.JodaDateDeserializerBase
    public JodaDateDeserializerBase<?> withFormat(JacksonJodaDateFormat jacksonJodaDateFormat) {
        return new LocalTimeDeserializer(jacksonJodaDateFormat);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        int iCurrentTokenId = jsonParser.currentTokenId();
        if (iCurrentTokenId == 1) {
            return _fromString(jsonParser, deserializationContext, deserializationContext.extractScalarFromObject(jsonParser, this, handledType()));
        }
        if (iCurrentTokenId == 6) {
            return _fromString(jsonParser, deserializationContext, jsonParser.getText());
        }
        if (iCurrentTokenId == 7) {
            return new LocalTime(jsonParser.getLongValue());
        }
        if (jsonParser.isExpectedStartArrayToken()) {
            return _fromArray(jsonParser, deserializationContext);
        }
        return (LocalTime) deserializationContext.handleUnexpectedToken(handledType(), jsonParser.currentToken(), jsonParser, "expected JSON Array, String or Number", new Object[0]);
    }

    protected LocalTime _fromString(JsonParser jsonParser, DeserializationContext deserializationContext, String str) throws IOException {
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return _fromEmptyString(jsonParser, deserializationContext, strTrim);
        }
        if (deserializationContext.isEnabled(StreamReadCapability.UNTYPED_SCALARS) && _isValidTimestampString(strTrim)) {
            return new LocalTime(NumberInput.parseLong(strTrim));
        }
        return this._format.createParser(deserializationContext).parseLocalTime(strTrim);
    }

    protected LocalTime _fromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        int intValue;
        jsonParser.nextToken();
        int intValue2 = jsonParser.getIntValue();
        jsonParser.nextToken();
        int intValue3 = jsonParser.getIntValue();
        jsonParser.nextToken();
        int intValue4 = jsonParser.getIntValue();
        jsonParser.nextToken();
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        JsonToken jsonToken = JsonToken.END_ARRAY;
        if (jsonTokenCurrentToken != jsonToken) {
            intValue = jsonParser.getIntValue();
            jsonParser.nextToken();
        } else {
            intValue = 0;
        }
        if (jsonParser.currentToken() != jsonToken) {
            throw deserializationContext.wrongTokenException(jsonParser, handledType(), jsonToken, "after LocalTime ints");
        }
        return new LocalTime(intValue2, intValue3, intValue4, intValue);
    }
}
