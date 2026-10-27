package com.fasterxml.jackson.datatype.guava.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public class RangeSetSerializer extends JsonSerializer<RangeSet<Comparable<?>>> implements ContextualSerializer {
    private JavaType genericRangeListType;

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(RangeSet<Comparable<?>> rangeSet, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        JavaType javaType = this.genericRangeListType;
        if (javaType == null) {
            serializerProvider.findValueSerializer(List.class).serialize(rangeSet.asRanges(), jsonGenerator, serializerProvider);
        } else {
            serializerProvider.findValueSerializer(javaType).serialize(rangeSet.asRanges(), jsonGenerator, serializerProvider);
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.ContextualSerializer
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {
        if (beanProperty == null) {
            return this;
        }
        RangeSetSerializer rangeSetSerializer = new RangeSetSerializer();
        rangeSetSerializer.genericRangeListType = serializerProvider.getTypeFactory().constructCollectionType(List.class, serializerProvider.getTypeFactory().constructParametricType(Range.class, beanProperty.getType().containedType(0)));
        return rangeSetSerializer;
    }
}
