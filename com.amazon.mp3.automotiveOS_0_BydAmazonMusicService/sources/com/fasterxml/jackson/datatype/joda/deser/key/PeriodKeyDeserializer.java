package com.fasterxml.jackson.datatype.joda.deser.key;

import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
public class PeriodKeyDeserializer extends JodaKeyDeserializer {
    private static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.datatype.joda.deser.key.JodaKeyDeserializer
    protected Object deserialize(String str, DeserializationContext deserializationContext) throws IOException {
        return JodaKeyDeserializer.PERIOD_FORMAT.parsePeriod(deserializationContext, str);
    }
}
