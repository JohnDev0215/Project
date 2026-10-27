package com.fasterxml.jackson.datatype.joda.deser.key;

import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/* JADX INFO: loaded from: classes6.dex */
public class LocalDateTimeKeyDeserializer extends JodaKeyDeserializer {
    private static final DateTimeFormatter parser = ISODateTimeFormat.localDateOptionalTimeParser();
    private static final long serialVersionUID = 1;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.datatype.joda.deser.key.JodaKeyDeserializer
    public LocalDateTime deserialize(String str, DeserializationContext deserializationContext) throws IOException {
        return parser.parseLocalDateTime(str);
    }
}
