package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import java.io.IOException;
import org.joda.time.DateMidnight;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

/* JADX INFO: loaded from: classes6.dex */
@Deprecated
public class DateMidnightDeserializer extends JodaDateDeserializerBase<DateMidnight> {
    private static final long serialVersionUID = 1;

    public DateMidnightDeserializer() {
        this(FormatConfig.DEFAULT_DATEONLY_FORMAT);
    }

    public DateMidnightDeserializer(JacksonJodaDateFormat jacksonJodaDateFormat) {
        super(DateMidnight.class, jacksonJodaDateFormat);
    }

    @Override // com.fasterxml.jackson.datatype.joda.deser.JodaDateDeserializerBase
    public JodaDateDeserializerBase<?> withFormat(JacksonJodaDateFormat jacksonJodaDateFormat) {
        return new DateMidnightDeserializer(jacksonJodaDateFormat);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public DateMidnight deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.isExpectedStartArrayToken()) {
            jsonParser.nextToken();
            int intValue = jsonParser.getIntValue();
            jsonParser.nextToken();
            int intValue2 = jsonParser.getIntValue();
            jsonParser.nextToken();
            int intValue3 = jsonParser.getIntValue();
            JsonToken jsonTokenNextToken = jsonParser.nextToken();
            JsonToken jsonToken = JsonToken.END_ARRAY;
            if (jsonTokenNextToken != jsonToken) {
                throw deserializationContext.wrongTokenException(jsonParser, handledType(), jsonToken, "after DateMidnight ints");
            }
            return new DateMidnight(intValue, intValue2, intValue3, this._format.isTimezoneExplicit() ? this._format.getTimeZone() : DateTimeZone.forTimeZone(deserializationContext.getTimeZone()));
        }
        int iCurrentTokenId = jsonParser.currentTokenId();
        if (iCurrentTokenId == 6) {
            return _fromString(jsonParser, deserializationContext, jsonParser.getText());
        }
        if (iCurrentTokenId == 7) {
            return new DateMidnight(jsonParser.getLongValue());
        }
        throw deserializationContext.wrongTokenException(jsonParser, handledType(), JsonToken.START_ARRAY, "expected JSON Array, Number or String");
    }

    protected DateMidnight _fromString(JsonParser jsonParser, DeserializationContext deserializationContext, String str) throws IOException {
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return _fromEmptyString(jsonParser, deserializationContext, strTrim);
        }
        if (deserializationContext.isEnabled(StreamReadCapability.UNTYPED_SCALARS) && _isValidTimestampString(strTrim)) {
            return _fromTimestamp(deserializationContext, NumberInput.parseLong(strTrim));
        }
        LocalDate localDate = this._format.createParser(deserializationContext).parseLocalDate(strTrim);
        if (localDate == null) {
            return null;
        }
        return localDate.toDateMidnight();
    }

    protected DateMidnight _fromTimestamp(DeserializationContext deserializationContext, long j) {
        return new DateMidnight(j);
    }
}
