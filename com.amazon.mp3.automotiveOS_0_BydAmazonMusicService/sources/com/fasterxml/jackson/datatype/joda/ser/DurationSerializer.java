package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import java.io.IOException;
import org.joda.time.Duration;

/* JADX INFO: loaded from: classes6.dex */
public class DurationSerializer extends JodaDateSerializerBase<Duration> {
    private static final long serialVersionUID = 1;

    public DurationSerializer() {
        this(FormatConfig.DEFAULT_DATEONLY_FORMAT, 0);
    }

    public DurationSerializer(JacksonJodaDateFormat jacksonJodaDateFormat) {
        this(jacksonJodaDateFormat, 0);
    }

    public DurationSerializer(JacksonJodaDateFormat jacksonJodaDateFormat, int i) {
        super(Duration.class, jacksonJodaDateFormat, SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, 2, i);
    }

    @Override // com.fasterxml.jackson.datatype.joda.ser.JodaDateSerializerBase
    /* JADX INFO: renamed from: withFormat, reason: merged with bridge method [inline-methods] */
    public JodaDateSerializerBase<Duration> withFormat2(JacksonJodaDateFormat jacksonJodaDateFormat, int i) {
        return new DurationSerializer(jacksonJodaDateFormat, i);
    }

    @Override // com.fasterxml.jackson.datatype.joda.ser.JodaDateSerializerBase, com.fasterxml.jackson.databind.JsonSerializer
    public boolean isEmpty(SerializerProvider serializerProvider, Duration duration) {
        return duration.getMillis() == 0;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(Duration duration, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (_serializationShape(serializerProvider) == 1) {
            jsonGenerator.writeString(duration.toString());
        } else {
            jsonGenerator.writeNumber(duration.getMillis());
        }
    }
}
