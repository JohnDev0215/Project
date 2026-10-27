package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaPeriodFormat;
import java.io.IOException;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Period;
import org.joda.time.ReadablePeriod;
import org.joda.time.Seconds;
import org.joda.time.Weeks;
import org.joda.time.Years;

/* JADX INFO: loaded from: classes6.dex */
public class PeriodDeserializer extends JodaDeserializerBase<ReadablePeriod> {
    private static final long serialVersionUID = 1;
    private final JacksonJodaPeriodFormat _format;
    private final boolean _requireFullPeriod;

    @Override // com.fasterxml.jackson.datatype.joda.deser.JodaDeserializerBase, com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public /* bridge */ /* synthetic */ Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return super.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
    }

    @Override // com.fasterxml.jackson.datatype.joda.deser.JodaDeserializerBase, com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public /* bridge */ /* synthetic */ LogicalType logicalType() {
        return super.logicalType();
    }

    public PeriodDeserializer() {
        this(true);
    }

    public PeriodDeserializer(boolean z) {
        super((Class<?>) (z ? Period.class : ReadablePeriod.class));
        this._format = FormatConfig.DEFAULT_PERIOD_FORMAT;
        this._requireFullPeriod = z;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public ReadablePeriod deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        if (jsonTokenCurrentToken == JsonToken.VALUE_STRING) {
            return _fromString(jsonParser, deserializationContext, jsonParser.getText());
        }
        if (jsonTokenCurrentToken == JsonToken.VALUE_NUMBER_INT) {
            return new Period(jsonParser.getLongValue());
        }
        if (jsonTokenCurrentToken != JsonToken.START_OBJECT && jsonTokenCurrentToken != JsonToken.FIELD_NAME) {
            return (ReadablePeriod) deserializationContext.handleUnexpectedToken(handledType(), jsonTokenCurrentToken, jsonParser, "expected JSON Number, String or Object", new Object[0]);
        }
        return _fromObject(jsonParser, deserializationContext);
    }

    protected ReadablePeriod _fromString(JsonParser jsonParser, DeserializationContext deserializationContext, String str) throws IOException {
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return _fromEmptyString(jsonParser, deserializationContext, strTrim);
        }
        return this._format.parsePeriod(deserializationContext, strTrim);
    }

    protected ReadablePeriod _fromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ReadablePeriod readablePeriodYears;
        JsonNode jsonNode = (JsonNode) jsonParser.readValueAsTree();
        String strAsText = jsonNode.path("fieldType").path("name").asText();
        String strAsText2 = jsonNode.path("periodType").path("name").asText();
        int iAsInt = jsonNode.path(strAsText).asInt();
        if (strAsText2.equals("Seconds")) {
            readablePeriodYears = Seconds.seconds(iAsInt);
        } else if (strAsText2.equals("Minutes")) {
            readablePeriodYears = Minutes.minutes(iAsInt);
        } else if (strAsText2.equals("Hours")) {
            readablePeriodYears = Hours.hours(iAsInt);
        } else if (strAsText2.equals("Days")) {
            readablePeriodYears = Days.days(iAsInt);
        } else if (strAsText2.equals("Weeks")) {
            readablePeriodYears = Weeks.weeks(iAsInt);
        } else if (strAsText2.equals("Months")) {
            readablePeriodYears = Months.months(iAsInt);
        } else if (strAsText2.equals("Years")) {
            readablePeriodYears = Years.years(iAsInt);
        } else {
            deserializationContext.reportInputMismatch(handledType(), "Don't know how to deserialize %s using periodName '%s'", handledType().getName(), strAsText2);
            return null;
        }
        return (!this._requireFullPeriod || (readablePeriodYears instanceof Period)) ? readablePeriodYears : readablePeriodYears.toPeriod();
    }
}
