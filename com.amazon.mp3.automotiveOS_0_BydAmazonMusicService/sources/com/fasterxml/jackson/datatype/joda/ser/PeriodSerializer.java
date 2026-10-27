package com.fasterxml.jackson.datatype.joda.ser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaPeriodFormat;
import java.io.IOException;
import java.lang.reflect.Type;
import org.joda.time.ReadablePeriod;

/* JADX INFO: loaded from: classes6.dex */
public class PeriodSerializer extends JodaSerializerBase<ReadablePeriod> implements ContextualSerializer {
    private static final long serialVersionUID = 1;
    protected final JacksonJodaPeriodFormat _format;

    public PeriodSerializer() {
        this(FormatConfig.DEFAULT_PERIOD_FORMAT);
    }

    protected PeriodSerializer(JacksonJodaPeriodFormat jacksonJodaPeriodFormat) {
        super(ReadablePeriod.class);
        this._format = jacksonJodaPeriodFormat;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContextualSerializer
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonFormat.Value valueFindFormatOverrides;
        Boolean bool;
        if (beanProperty != null && (valueFindFormatOverrides = findFormatOverrides(serializerProvider, beanProperty, handledType())) != null) {
            JacksonJodaPeriodFormat jacksonJodaPeriodFormatWithUseTimestamp = this._format;
            if (valueFindFormatOverrides.getShape().isNumeric()) {
                bool = Boolean.TRUE;
            } else if (valueFindFormatOverrides.getShape() == JsonFormat.Shape.STRING) {
                bool = Boolean.FALSE;
            } else {
                bool = valueFindFormatOverrides.getShape() == JsonFormat.Shape.ARRAY ? Boolean.TRUE : null;
            }
            if (bool != null) {
                jacksonJodaPeriodFormatWithUseTimestamp = jacksonJodaPeriodFormatWithUseTimestamp.withUseTimestamp(bool);
            }
            JacksonJodaPeriodFormat jacksonJodaPeriodFormatWithLocale = jacksonJodaPeriodFormatWithUseTimestamp.withFormat(valueFindFormatOverrides.getPattern()).withLocale(valueFindFormatOverrides.getLocale());
            if (jacksonJodaPeriodFormatWithLocale != this._format) {
                return new PeriodSerializer(jacksonJodaPeriodFormatWithLocale);
            }
        }
        return this;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(ReadablePeriod readablePeriod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(this._format.createFormatter(serializerProvider).print(readablePeriod));
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.jsonschema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode("string", true);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        jsonFormatVisitorWrapper.expectStringFormat(javaType);
    }
}
