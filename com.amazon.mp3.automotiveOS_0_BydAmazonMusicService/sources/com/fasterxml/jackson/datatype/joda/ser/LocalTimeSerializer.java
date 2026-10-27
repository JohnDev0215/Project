package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import java.io.IOException;
import org.joda.time.LocalTime;

/* JADX INFO: loaded from: classes6.dex */
public class LocalTimeSerializer extends JodaDateSerializerBase<LocalTime> {
    private static final long serialVersionUID = 1;

    public LocalTimeSerializer() {
        this(FormatConfig.DEFAULT_LOCAL_TIMEONLY_PRINTER, 0);
    }

    public LocalTimeSerializer(JacksonJodaDateFormat jacksonJodaDateFormat) {
        this(jacksonJodaDateFormat, 0);
    }

    public LocalTimeSerializer(JacksonJodaDateFormat jacksonJodaDateFormat, int i) {
        super(LocalTime.class, jacksonJodaDateFormat, SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, 3, i);
    }

    @Override // com.fasterxml.jackson.datatype.joda.ser.JodaDateSerializerBase
    /* JADX INFO: renamed from: withFormat, reason: avoid collision after fix types in other method */
    public JodaDateSerializerBase<LocalTime> withFormat2(JacksonJodaDateFormat jacksonJodaDateFormat, int i) {
        return new LocalTimeSerializer(jacksonJodaDateFormat, i);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(LocalTime localTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (_serializationShape(serializerProvider) == 1) {
            jsonGenerator.writeString(this._format.createFormatter(serializerProvider).print(localTime));
            return;
        }
        jsonGenerator.writeStartArray();
        jsonGenerator.writeNumber(localTime.hourOfDay().get());
        jsonGenerator.writeNumber(localTime.minuteOfHour().get());
        jsonGenerator.writeNumber(localTime.secondOfMinute().get());
        jsonGenerator.writeNumber(localTime.millisOfSecond().get());
        jsonGenerator.writeEndArray();
    }
}
