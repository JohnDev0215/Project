package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import java.io.IOException;
import org.joda.time.DateTime;

/* JADX INFO: loaded from: classes6.dex */
public class DateTimeSerializer extends JodaDateSerializerBase<DateTime> {
    private static final long serialVersionUID = 1;

    public DateTimeSerializer() {
        this(FormatConfig.DEFAULT_DATETIME_PRINTER, 0);
    }

    public DateTimeSerializer(JacksonJodaDateFormat jacksonJodaDateFormat) {
        this(jacksonJodaDateFormat, 0);
    }

    public DateTimeSerializer(JacksonJodaDateFormat jacksonJodaDateFormat, int i) {
        super(DateTime.class, jacksonJodaDateFormat, SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, 2, i);
    }

    @Override // com.fasterxml.jackson.datatype.joda.ser.JodaDateSerializerBase
    /* JADX INFO: renamed from: withFormat */
    public JodaDateSerializerBase<DateTime> withFormat2(JacksonJodaDateFormat jacksonJodaDateFormat, int i) {
        return new DateTimeSerializer(jacksonJodaDateFormat, i);
    }

    @Override // com.fasterxml.jackson.datatype.joda.ser.JodaDateSerializerBase, com.fasterxml.jackson.databind.JsonSerializer
    public boolean isEmpty(SerializerProvider serializerProvider, DateTime dateTime) {
        return dateTime.getMillis() == 0;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(DateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        boolean z = _serializationShape(serializerProvider) != 1;
        if (!writeWithZoneId(serializerProvider)) {
            if (z) {
                jsonGenerator.writeNumber(dateTime.getMillis());
                return;
            } else {
                jsonGenerator.writeString(this._format.createFormatter(serializerProvider).print(dateTime));
                return;
            }
        }
        if (z) {
            jsonGenerator.writeNumber(dateTime.getMillis());
            return;
        }
        StringBuilder sb = new StringBuilder(40);
        sb.append(this._format.createFormatter(serializerProvider).withOffsetParsed().print(dateTime));
        sb.append('[');
        sb.append(dateTime.getZone());
        sb.append(']');
        jsonGenerator.writeString(sb.toString());
    }
}
