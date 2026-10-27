package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import java.io.IOException;
import org.joda.time.Instant;

/* JADX INFO: loaded from: classes6.dex */
public class InstantDeserializer extends JodaDateDeserializerBase<Instant> {
    private static final long serialVersionUID = 1;

    public InstantDeserializer() {
        this(FormatConfig.DEFAULT_DATETIME_PARSER);
    }

    public InstantDeserializer(JacksonJodaDateFormat jacksonJodaDateFormat) {
        super(Instant.class, jacksonJodaDateFormat);
    }

    @Override // com.fasterxml.jackson.datatype.joda.deser.JodaDateDeserializerBase
    public JodaDateDeserializerBase<?> withFormat(JacksonJodaDateFormat jacksonJodaDateFormat) {
        return new InstantDeserializer(jacksonJodaDateFormat);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Instant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
        return (Instant) _handleNotNumberOrString(jsonParser, deserializationContext);
    }

    protected Instant _fromString(JsonParser jsonParser, DeserializationContext deserializationContext, String str) throws IOException {
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return _fromEmptyString(jsonParser, deserializationContext, strTrim);
        }
        if (deserializationContext.isEnabled(StreamReadCapability.UNTYPED_SCALARS) && _isValidTimestampString(strTrim)) {
            return _fromTimestamp(deserializationContext, NumberInput.parseLong(strTrim));
        }
        return Instant.parse(strTrim, this._format.createParser(deserializationContext));
    }

    protected Instant _fromTimestamp(DeserializationContext deserializationContext, long j) {
        return new Instant(j);
    }
}
