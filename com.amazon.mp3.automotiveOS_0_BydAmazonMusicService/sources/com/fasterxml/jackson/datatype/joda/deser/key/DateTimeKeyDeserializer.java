package com.fasterxml.jackson.datatype.joda.deser.key;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import java.io.IOException;
import java.util.TimeZone;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/* JADX INFO: loaded from: classes6.dex */
public class DateTimeKeyDeserializer extends JodaKeyDeserializer {
    private static final long serialVersionUID = 1;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.datatype.joda.deser.key.JodaKeyDeserializer
    public DateTime deserialize(String str, DeserializationContext deserializationContext) throws IOException {
        if (deserializationContext.isEnabled(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)) {
            TimeZone timeZone = deserializationContext.getTimeZone();
            return new DateTime(str, timeZone == null ? DateTimeZone.UTC : DateTimeZone.forTimeZone(timeZone));
        }
        return DateTime.parse(str);
    }
}
