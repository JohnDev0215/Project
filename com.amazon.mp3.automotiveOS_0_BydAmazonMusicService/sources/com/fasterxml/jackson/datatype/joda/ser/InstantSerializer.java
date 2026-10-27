package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import java.io.IOException;
import org.joda.time.Instant;

/* JADX INFO: loaded from: classes6.dex */
public class InstantSerializer extends JodaDateSerializerBase<Instant> {
    private static final long serialVersionUID = 1;

    public InstantSerializer() {
        this(FormatConfig.DEFAULT_DATETIME_PRINTER, 0);
    }

    public InstantSerializer(JacksonJodaDateFormat jacksonJodaDateFormat) {
        this(jacksonJodaDateFormat, 0);
    }

    public InstantSerializer(JacksonJodaDateFormat jacksonJodaDateFormat, int i) {
        super(Instant.class, jacksonJodaDateFormat, SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, 2, i);
    }

    @Override // com.fasterxml.jackson.datatype.joda.ser.JodaDateSerializerBase
    /* JADX INFO: renamed from: withFormat */
    public JodaDateSerializerBase<Instant> withFormat2(JacksonJodaDateFormat jacksonJodaDateFormat, int i) {
        return new InstantSerializer(jacksonJodaDateFormat, i);
    }

    @Override // com.fasterxml.jackson.datatype.joda.ser.JodaDateSerializerBase, com.fasterxml.jackson.databind.JsonSerializer
    public boolean isEmpty(SerializerProvider serializerProvider, Instant instant) {
        return instant.getMillis() == 0;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(Instant instant, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (_serializationShape(serializerProvider) == 1) {
            jsonGenerator.writeString(this._format.createFormatter(serializerProvider).print(instant));
        } else {
            jsonGenerator.writeNumber(instant.getMillis());
        }
    }
}
