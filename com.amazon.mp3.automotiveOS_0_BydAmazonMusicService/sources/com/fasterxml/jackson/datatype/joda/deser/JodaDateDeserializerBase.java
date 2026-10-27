package com.fasterxml.jackson.datatype.joda.deser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
public abstract class JodaDateDeserializerBase<T> extends JodaDeserializerBase<T> implements ContextualDeserializer {
    private static final long serialVersionUID = 1;
    protected final JacksonJodaDateFormat _format;

    public abstract JodaDateDeserializerBase<?> withFormat(JacksonJodaDateFormat jacksonJodaDateFormat);

    @Override // com.fasterxml.jackson.datatype.joda.deser.JodaDeserializerBase
    public /* bridge */ /* synthetic */ Object _handleNotNumberOrString(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return super._handleNotNumberOrString(jsonParser, deserializationContext);
    }

    @Override // com.fasterxml.jackson.datatype.joda.deser.JodaDeserializerBase, com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public /* bridge */ /* synthetic */ Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return super.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
    }

    @Override // com.fasterxml.jackson.datatype.joda.deser.JodaDeserializerBase, com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public /* bridge */ /* synthetic */ LogicalType logicalType() {
        return super.logicalType();
    }

    protected JodaDateDeserializerBase(Class<?> cls, JacksonJodaDateFormat jacksonJodaDateFormat) {
        super(cls);
        this._format = jacksonJodaDateFormat;
    }

    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        Boolean bool;
        JsonFormat.Value valueFindFormatOverrides = findFormatOverrides(deserializationContext, beanProperty, handledType());
        if (valueFindFormatOverrides != null) {
            JacksonJodaDateFormat jacksonJodaDateFormatWithUseTimestamp = this._format;
            if (valueFindFormatOverrides.getShape().isNumeric()) {
                bool = Boolean.TRUE;
            } else if (valueFindFormatOverrides.getShape() == JsonFormat.Shape.STRING) {
                bool = Boolean.FALSE;
            } else {
                bool = valueFindFormatOverrides.getShape() == JsonFormat.Shape.ARRAY ? Boolean.TRUE : null;
            }
            if (bool != null) {
                jacksonJodaDateFormatWithUseTimestamp = jacksonJodaDateFormatWithUseTimestamp.withUseTimestamp(bool);
            }
            JacksonJodaDateFormat jacksonJodaDateFormatWith = jacksonJodaDateFormatWithUseTimestamp.with(valueFindFormatOverrides);
            if (jacksonJodaDateFormatWith != this._format) {
                return withFormat(jacksonJodaDateFormatWith);
            }
        }
        return this;
    }
}
