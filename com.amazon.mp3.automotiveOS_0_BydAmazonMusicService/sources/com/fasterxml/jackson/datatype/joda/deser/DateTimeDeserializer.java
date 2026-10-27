package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import java.io.IOException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadableInstant;

/* JADX INFO: loaded from: classes6.dex */
public class DateTimeDeserializer extends JodaDateDeserializerBase<ReadableInstant> {
    private static final long serialVersionUID = 1;

    public DateTimeDeserializer(Class<?> cls, JacksonJodaDateFormat jacksonJodaDateFormat) {
        super(cls, jacksonJodaDateFormat);
    }

    public static <T extends ReadableInstant> JsonDeserializer<T> forType(Class<T> cls) {
        return new DateTimeDeserializer(cls, FormatConfig.DEFAULT_DATETIME_PARSER);
    }

    @Override // com.fasterxml.jackson.datatype.joda.deser.JodaDateDeserializerBase
    public JodaDateDeserializerBase<?> withFormat(JacksonJodaDateFormat jacksonJodaDateFormat) {
        return new DateTimeDeserializer(this._valueClass, jacksonJodaDateFormat);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public ReadableInstant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
        return (ReadableInstant) _handleNotNumberOrString(jsonParser, deserializationContext);
    }

    protected ReadableInstant _fromString(JsonParser jsonParser, DeserializationContext deserializationContext, String str) throws IOException {
        String strSubstring;
        DateTimeZone dateTimeZoneForID;
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return _fromEmptyString(jsonParser, deserializationContext, strTrim);
        }
        int iIndexOf = strTrim.indexOf(91);
        if (iIndexOf > 0) {
            int iLastIndexOf = strTrim.lastIndexOf(93);
            if (iLastIndexOf < iIndexOf) {
                strSubstring = strTrim.substring(iIndexOf + 1);
            } else {
                strSubstring = strTrim.substring(iIndexOf + 1, iLastIndexOf);
            }
            try {
                dateTimeZoneForID = DateTimeZone.forID(strSubstring);
            } catch (IllegalArgumentException unused) {
                deserializationContext.reportInputMismatch(handledType(), "Unknown DateTimeZone id '%s'", strSubstring);
                dateTimeZoneForID = null;
            }
            DateTime dateTime = this._format.createParser(deserializationContext).withZone(dateTimeZoneForID).parseDateTime(strTrim.substring(0, iIndexOf));
            return this._format.shouldAdjustToContextTimeZone(deserializationContext) ? dateTime.withZone(this._format.getTimeZone()) : dateTime;
        }
        if (deserializationContext.isEnabled(StreamReadCapability.UNTYPED_SCALARS) && _isValidTimestampString(strTrim)) {
            return _fromTimestamp(deserializationContext, NumberInput.parseLong(strTrim));
        }
        return this._format.createParser(deserializationContext).parseDateTime(strTrim);
    }

    protected DateTime _fromTimestamp(DeserializationContext deserializationContext, long j) {
        return new DateTime(j, this._format.isTimezoneExplicit() ? this._format.getTimeZone() : DateTimeZone.forTimeZone(deserializationContext.getTimeZone()));
    }
}
