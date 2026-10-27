package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import java.io.IOException;
import org.joda.time.MonthDay;

/* JADX INFO: loaded from: classes6.dex */
public class MonthDaySerializer extends JodaDateSerializerBase<MonthDay> {
    private static final long serialVersionUID = 1;

    public MonthDaySerializer() {
        this(FormatConfig.DEFAULT_MONTH_DAY_FORMAT, 0);
    }

    public MonthDaySerializer(JacksonJodaDateFormat jacksonJodaDateFormat) {
        this(jacksonJodaDateFormat, 0);
    }

    public MonthDaySerializer(JacksonJodaDateFormat jacksonJodaDateFormat, int i) {
        super(MonthDay.class, jacksonJodaDateFormat, SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, 1, i);
    }

    @Override // com.fasterxml.jackson.datatype.joda.ser.JodaDateSerializerBase
    /* JADX INFO: renamed from: withFormat, reason: avoid collision after fix types in other method */
    public JodaDateSerializerBase<MonthDay> withFormat2(JacksonJodaDateFormat jacksonJodaDateFormat, int i) {
        return new MonthDaySerializer(jacksonJodaDateFormat, i);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(MonthDay monthDay, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(this._format.createFormatter(serializerProvider).print(monthDay));
    }
}
