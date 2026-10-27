package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import java.io.IOException;
import org.joda.time.YearMonth;

/* JADX INFO: loaded from: classes6.dex */
public class YearMonthSerializer extends JodaDateSerializerBase<YearMonth> {
    private static final long serialVersionUID = 1;

    public YearMonthSerializer() {
        this(FormatConfig.DEFAULT_YEAR_MONTH_FORMAT, 0);
    }

    public YearMonthSerializer(JacksonJodaDateFormat jacksonJodaDateFormat) {
        this(jacksonJodaDateFormat, 0);
    }

    public YearMonthSerializer(JacksonJodaDateFormat jacksonJodaDateFormat, int i) {
        super(YearMonth.class, jacksonJodaDateFormat, SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, 1, i);
    }

    @Override // com.fasterxml.jackson.datatype.joda.ser.JodaDateSerializerBase
    /* JADX INFO: renamed from: withFormat, reason: avoid collision after fix types in other method */
    public JodaDateSerializerBase<YearMonth> withFormat2(JacksonJodaDateFormat jacksonJodaDateFormat, int i) {
        return new YearMonthSerializer(jacksonJodaDateFormat, i);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(YearMonth yearMonth, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(this._format.createFormatter(serializerProvider).print(yearMonth));
    }
}
