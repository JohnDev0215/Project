package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import java.io.IOException;
import org.joda.time.DateMidnight;

/* JADX INFO: loaded from: classes6.dex */
@Deprecated
public class DateMidnightSerializer extends JodaDateSerializerBase<DateMidnight> {
    private static final long serialVersionUID = 1;

    public DateMidnightSerializer() {
        this(FormatConfig.DEFAULT_LOCAL_DATEONLY_FORMAT, 0);
    }

    public DateMidnightSerializer(JacksonJodaDateFormat jacksonJodaDateFormat) {
        this(jacksonJodaDateFormat, 0);
    }

    public DateMidnightSerializer(JacksonJodaDateFormat jacksonJodaDateFormat, int i) {
        super(DateMidnight.class, jacksonJodaDateFormat, SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, 3, i);
    }

    @Override // com.fasterxml.jackson.datatype.joda.ser.JodaDateSerializerBase
    /* JADX INFO: renamed from: withFormat */
    public JodaDateSerializerBase<DateMidnight> withFormat2(JacksonJodaDateFormat jacksonJodaDateFormat, int i) {
        return new DateMidnightSerializer(jacksonJodaDateFormat, i);
    }

    @Override // com.fasterxml.jackson.datatype.joda.ser.JodaDateSerializerBase, com.fasterxml.jackson.databind.JsonSerializer
    public boolean isEmpty(SerializerProvider serializerProvider, DateMidnight dateMidnight) {
        return dateMidnight.getMillis() == 0;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(DateMidnight dateMidnight, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        int i_serializationShape = _serializationShape(serializerProvider);
        if (i_serializationShape == 1) {
            jsonGenerator.writeString(this._format.createFormatterWithLocale(serializerProvider).print(dateMidnight));
            return;
        }
        if (i_serializationShape == 2) {
            jsonGenerator.writeNumber(dateMidnight.getMillis());
            return;
        }
        if (i_serializationShape != 3) {
            return;
        }
        jsonGenerator.writeStartArray();
        jsonGenerator.writeNumber(dateMidnight.year().get());
        jsonGenerator.writeNumber(dateMidnight.monthOfYear().get());
        jsonGenerator.writeNumber(dateMidnight.dayOfMonth().get());
        jsonGenerator.writeEndArray();
    }
}
