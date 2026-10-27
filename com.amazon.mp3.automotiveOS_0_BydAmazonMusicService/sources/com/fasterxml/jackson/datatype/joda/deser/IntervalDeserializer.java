package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import java.io.IOException;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;

/* JADX INFO: loaded from: classes6.dex */
public class IntervalDeserializer extends JodaDateDeserializerBase<Interval> {
    private static final long serialVersionUID = 1;

    public IntervalDeserializer() {
        this(FormatConfig.DEFAULT_DATETIME_PARSER);
    }

    public IntervalDeserializer(JacksonJodaDateFormat jacksonJodaDateFormat) {
        super(Interval.class, jacksonJodaDateFormat);
    }

    @Override // com.fasterxml.jackson.datatype.joda.deser.JodaDateDeserializerBase
    public JodaDateDeserializerBase<?> withFormat(JacksonJodaDateFormat jacksonJodaDateFormat) {
        return new IntervalDeserializer(jacksonJodaDateFormat);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Interval deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.VALUE_STRING)) {
            return _fromString(jsonParser, deserializationContext, jsonParser.getText());
        }
        if (jsonParser.isExpectedStartObjectToken()) {
            return _fromString(jsonParser, deserializationContext, deserializationContext.extractScalarFromObject(jsonParser, this, handledType()));
        }
        return (Interval) deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser.currentToken(), jsonParser, "expected JSON String", new Object[0]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [com.fasterxml.jackson.databind.DeserializationContext] */
    /* JADX WARN: Type inference failed for: r11v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v5, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.datatype.joda.deser.IntervalDeserializer, com.fasterxml.jackson.datatype.joda.deser.JodaDateDeserializerBase, com.fasterxml.jackson.datatype.joda.deser.JodaDeserializerBase] */
    protected Interval _fromString(JsonParser jsonParser, DeserializationContext deserializationContext, String str) throws IOException {
        Interval interval;
        ?? Trim = str.trim();
        if (Trim.isEmpty()) {
            return (Interval) _fromEmptyString(jsonParser, deserializationContext, Trim);
        }
        int iIndexOf = Trim.indexOf(47, 1);
        boolean z = iIndexOf > 0;
        if (!z) {
            iIndexOf = Trim.indexOf(45, 1);
        }
        if (iIndexOf < 0) {
            throw deserializationContext.weirdStringException(Trim, handledType(), "no slash or hyphen found to separate start, end");
        }
        String strSubstring = Trim.substring(0, iIndexOf);
        try {
            if (z) {
                interval = Interval.parseWithOffset(Trim);
            } else {
                long jLongValue = Long.valueOf(strSubstring).longValue();
                strSubstring = Trim.substring(iIndexOf + 1);
                interval = new Interval(jLongValue, Long.valueOf(strSubstring).longValue());
            }
            Trim = this._format.shouldAdjustToContextTimeZone(deserializationContext);
            DateTimeZone dateTimeZoneForTimeZone = Trim != 0 ? DateTimeZone.forTimeZone(deserializationContext.getTimeZone()) : null;
            if (this._format.isTimezoneExplicit()) {
                dateTimeZoneForTimeZone = this._format.getTimeZone();
            }
            DateTimeZone dateTimeZone = dateTimeZoneForTimeZone;
            return (dateTimeZone == null || dateTimeZone.equals(interval.getStart().getZone())) ? interval : new Interval(interval.getStartMillis(), interval.getEndMillis(), dateTimeZone);
        } catch (NumberFormatException unused) {
            return (Interval) deserializationContext.handleWeirdStringValue(handledType(), strSubstring, "Failed to parse number from '%s' (full source String '%s')", new Object[]{strSubstring, Trim});
        }
    }
}
