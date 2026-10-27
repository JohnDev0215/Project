package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import java.io.IOException;
import org.joda.time.MonthDay;

/* JADX INFO: loaded from: classes6.dex */
public class MonthDayDeserializer extends JodaDateDeserializerBase<MonthDay> {
    private static final long serialVersionUID = 1;

    public MonthDayDeserializer() {
        this(FormatConfig.DEFAULT_MONTH_DAY_FORMAT);
    }

    public MonthDayDeserializer(JacksonJodaDateFormat jacksonJodaDateFormat) {
        super(MonthDay.class, jacksonJodaDateFormat);
    }

    @Override // com.fasterxml.jackson.datatype.joda.deser.JodaDateDeserializerBase
    public JodaDateDeserializerBase<?> withFormat(JacksonJodaDateFormat jacksonJodaDateFormat) {
        return new MonthDayDeserializer(jacksonJodaDateFormat);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public MonthDay deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.VALUE_STRING)) {
            return _fromString(jsonParser, deserializationContext, jsonParser.getText());
        }
        if (jsonParser.isExpectedStartObjectToken()) {
            return _fromString(jsonParser, deserializationContext, deserializationContext.extractScalarFromObject(jsonParser, this, handledType()));
        }
        return (MonthDay) deserializationContext.handleUnexpectedToken(handledType(), jsonParser.currentToken(), jsonParser, "expected JSON String", new Object[0]);
    }

    protected MonthDay _fromString(JsonParser jsonParser, DeserializationContext deserializationContext, String str) throws IOException {
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return _fromEmptyString(jsonParser, deserializationContext, strTrim);
        }
        return MonthDay.parse(strTrim, this._format.createParser(deserializationContext));
    }
}
