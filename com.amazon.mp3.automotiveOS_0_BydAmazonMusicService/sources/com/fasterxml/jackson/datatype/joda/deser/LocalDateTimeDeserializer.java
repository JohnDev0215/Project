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
import org.joda.time.LocalDateTime;

/* JADX INFO: loaded from: classes6.dex */
public class LocalDateTimeDeserializer extends JodaDateDeserializerBase<LocalDateTime> {
    private static final long serialVersionUID = 1;

    public LocalDateTimeDeserializer() {
        this(FormatConfig.DEFAULT_LOCAL_DATETIME_PARSER);
    }

    public LocalDateTimeDeserializer(JacksonJodaDateFormat jacksonJodaDateFormat) {
        super(LocalDateTime.class, jacksonJodaDateFormat);
    }

    @Override // com.fasterxml.jackson.datatype.joda.deser.JodaDateDeserializerBase
    public JodaDateDeserializerBase<?> withFormat(JacksonJodaDateFormat jacksonJodaDateFormat) {
        return new LocalDateTimeDeserializer(jacksonJodaDateFormat);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
        return (LocalDateTime) deserializationContext.handleUnexpectedToken(handledType(), jsonParser.currentToken(), jsonParser, "expected String, Number or JSON Array", new Object[0]);
    }

    protected LocalDateTime _fromString(JsonParser jsonParser, DeserializationContext deserializationContext, String str) throws IOException {
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return _fromEmptyString(jsonParser, deserializationContext, strTrim);
        }
        if (deserializationContext.isEnabled(StreamReadCapability.UNTYPED_SCALARS) && _isValidTimestampString(strTrim)) {
            return _fromTimestamp(deserializationContext, NumberInput.parseLong(strTrim));
        }
        return this._format.createParser(deserializationContext).parseLocalDateTime(strTrim);
    }

    protected LocalDateTime _fromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        int intValue;
        JsonToken jsonTokenNextToken = jsonParser.nextToken();
        LocalDateTime localDateTime = null;
        if (jsonTokenNextToken.isNumeric()) {
            int intValue2 = jsonParser.getIntValue();
            jsonTokenNextToken = jsonParser.nextToken();
            if (jsonTokenNextToken.isNumeric()) {
                int intValue3 = jsonParser.getIntValue();
                jsonTokenNextToken = jsonParser.nextToken();
                if (jsonTokenNextToken.isNumeric()) {
                    int intValue4 = jsonParser.getIntValue();
                    jsonTokenNextToken = jsonParser.nextToken();
                    if (jsonTokenNextToken.isNumeric()) {
                        int intValue5 = jsonParser.getIntValue();
                        jsonTokenNextToken = jsonParser.nextToken();
                        if (jsonTokenNextToken.isNumeric()) {
                            int intValue6 = jsonParser.getIntValue();
                            jsonTokenNextToken = jsonParser.nextToken();
                            if (jsonTokenNextToken.isNumeric()) {
                                int intValue7 = jsonParser.getIntValue();
                                jsonTokenNextToken = jsonParser.nextToken();
                                if (jsonTokenNextToken.isNumeric()) {
                                    intValue = jsonParser.getIntValue();
                                    jsonTokenNextToken = jsonParser.nextToken();
                                } else {
                                    intValue = 0;
                                }
                                localDateTime = new LocalDateTime(intValue2, intValue3, intValue4, intValue5, intValue6, intValue7, intValue);
                            }
                        }
                    }
                }
            }
        }
        JsonToken jsonToken = JsonToken.END_ARRAY;
        if (jsonTokenNextToken == jsonToken) {
            return localDateTime;
        }
        throw deserializationContext.wrongTokenException(jsonParser, handledType(), jsonToken, "after LocalDateTime ints");
    }

    protected LocalDateTime _fromTimestamp(DeserializationContext deserializationContext, long j) {
        return new LocalDateTime(j, this._format.isTimezoneExplicit() ? this._format.getTimeZone() : DateTimeZone.forTimeZone(deserializationContext.getTimeZone()));
    }
}
