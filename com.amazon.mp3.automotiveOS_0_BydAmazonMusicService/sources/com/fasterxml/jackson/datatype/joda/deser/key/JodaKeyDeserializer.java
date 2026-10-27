package com.fasterxml.jackson.datatype.joda.deser.key;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaPeriodFormat;
import java.io.IOException;
import java.io.Serializable;

/* JADX INFO: loaded from: classes6.dex */
abstract class JodaKeyDeserializer extends KeyDeserializer implements Serializable {
    protected static final JacksonJodaPeriodFormat PERIOD_FORMAT = FormatConfig.DEFAULT_PERIOD_FORMAT;
    private static final long serialVersionUID = 1;

    protected abstract Object deserialize(String str, DeserializationContext deserializationContext) throws IOException;

    JodaKeyDeserializer() {
    }

    @Override // com.fasterxml.jackson.databind.KeyDeserializer
    public final Object deserializeKey(String str, DeserializationContext deserializationContext) throws IOException {
        if (str.length() == 0) {
            return null;
        }
        return deserialize(str, deserializationContext);
    }
}
