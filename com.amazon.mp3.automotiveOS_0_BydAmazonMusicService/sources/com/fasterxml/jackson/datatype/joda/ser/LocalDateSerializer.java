package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import java.io.IOException;
import org.joda.time.LocalDate;

/* JADX INFO: loaded from: classes6.dex */
public class LocalDateSerializer extends JodaDateSerializerBase<LocalDate> {
    private static final long serialVersionUID = 1;

    public LocalDateSerializer() {
        this(FormatConfig.DEFAULT_LOCAL_DATEONLY_FORMAT, 0);
    }

    public LocalDateSerializer(JacksonJodaDateFormat jacksonJodaDateFormat) {
        this(jacksonJodaDateFormat, 0);
    }

    public LocalDateSerializer(JacksonJodaDateFormat jacksonJodaDateFormat, int i) {
        super(LocalDate.class, jacksonJodaDateFormat, SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, 3, i);
    }

    @Override // com.fasterxml.jackson.datatype.joda.ser.JodaDateSerializerBase
    /* JADX INFO: renamed from: withFormat, reason: avoid collision after fix types in other method */
    public JodaDateSerializerBase<LocalDate> withFormat2(JacksonJodaDateFormat jacksonJodaDateFormat, int i) {
        return new LocalDateSerializer(jacksonJodaDateFormat, i);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (_serializationShape(serializerProvider) == 1) {
            jsonGenerator.writeString(this._format.createFormatter(serializerProvider).print(localDate));
            return;
        }
        jsonGenerator.writeStartArray();
        jsonGenerator.writeNumber(localDate.year().get());
        jsonGenerator.writeNumber(localDate.monthOfYear().get());
        jsonGenerator.writeNumber(localDate.dayOfMonth().get());
        jsonGenerator.writeEndArray();
    }
}
