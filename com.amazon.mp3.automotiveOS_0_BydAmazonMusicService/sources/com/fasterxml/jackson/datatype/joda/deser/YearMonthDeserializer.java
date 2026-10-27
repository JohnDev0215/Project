package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import java.io.IOException;
import org.joda.time.YearMonth;

/* JADX INFO: loaded from: classes6.dex */
public class YearMonthDeserializer extends JodaDateDeserializerBase<YearMonth> {
    private static final long serialVersionUID = 1;

    public YearMonthDeserializer() {
        this(FormatConfig.DEFAULT_YEAR_MONTH_FORMAT);
    }

    public YearMonthDeserializer(JacksonJodaDateFormat jacksonJodaDateFormat) {
        super(YearMonth.class, jacksonJodaDateFormat);
    }

    @Override // com.fasterxml.jackson.datatype.joda.deser.JodaDateDeserializerBase
    public JodaDateDeserializerBase<?> withFormat(JacksonJodaDateFormat jacksonJodaDateFormat) {
        return new YearMonthDeserializer(jacksonJodaDateFormat);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public YearMonth deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.VALUE_STRING)) {
            return _fromString(jsonParser, deserializationContext, jsonParser.getText());
        }
        if (jsonParser.isExpectedStartObjectToken()) {
            return _fromString(jsonParser, deserializationContext, deserializationContext.extractScalarFromObject(jsonParser, this, handledType()));
        }
        return (YearMonth) deserializationContext.handleUnexpectedToken(handledType(), jsonParser.currentToken(), jsonParser, "expected JSON String", new Object[0]);
    }

    protected YearMonth _fromString(JsonParser jsonParser, DeserializationContext deserializationContext, String str) throws IOException {
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return _fromEmptyString(jsonParser, deserializationContext, strTrim);
        }
        return YearMonth.parse(strTrim, this._format.createParser(deserializationContext));
    }
}
