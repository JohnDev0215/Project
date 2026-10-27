package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import java.io.IOException;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormatter;

/* JADX INFO: loaded from: classes6.dex */
public class IntervalSerializer extends JodaDateSerializerBase<Interval> {
    private static final long serialVersionUID = 1;

    public IntervalSerializer() {
        this(FormatConfig.DEFAULT_DATETIME_PRINTER, 0);
    }

    public IntervalSerializer(JacksonJodaDateFormat jacksonJodaDateFormat) {
        this(jacksonJodaDateFormat, 0);
    }

    public IntervalSerializer(JacksonJodaDateFormat jacksonJodaDateFormat, int i) {
        super(Interval.class, jacksonJodaDateFormat, SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, 2, i);
    }

    @Override // com.fasterxml.jackson.datatype.joda.ser.JodaDateSerializerBase
    /* JADX INFO: renamed from: withFormat */
    public JodaDateSerializerBase<Interval> withFormat2(JacksonJodaDateFormat jacksonJodaDateFormat, int i) {
        return new IntervalSerializer(jacksonJodaDateFormat, i);
    }

    @Override // com.fasterxml.jackson.datatype.joda.ser.JodaDateSerializerBase, com.fasterxml.jackson.databind.JsonSerializer
    public boolean isEmpty(SerializerProvider serializerProvider, Interval interval) {
        return interval.getStartMillis() == interval.getEndMillis();
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(Interval interval, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String str;
        if (_serializationShape(serializerProvider) == 1) {
            DateTimeFormatter dateTimeFormatterCreateFormatter = this._format.createFormatter(serializerProvider);
            str = dateTimeFormatterCreateFormatter.print(interval.getStart()) + "/" + dateTimeFormatterCreateFormatter.print(interval.getEnd());
        } else {
            str = interval.getStartMillis() + "-" + interval.getEndMillis();
        }
        jsonGenerator.writeString(str);
    }
}
