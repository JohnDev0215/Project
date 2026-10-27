package com.fasterxml.jackson.datatype.guava.deser;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.google.common.collect.ImmutableRangeSet;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public class RangeSetDeserializer extends JsonDeserializer<RangeSet<Comparable<?>>> implements ContextualDeserializer {
    private JavaType genericRangeListType;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public LogicalType logicalType() {
        return LogicalType.Collection;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public RangeSet<Comparable<?>> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JavaType javaType = this.genericRangeListType;
        if (javaType == null) {
            throw new JsonParseException(jsonParser, "RangeSetJsonSerializer was not contextualized (no deserialize target type). You need to specify the generic type down to the generic parameter of RangeSet.");
        }
        Iterable iterable = (Iterable) deserializationContext.findContextualValueDeserializer(javaType, null).deserialize(jsonParser, deserializationContext);
        ImmutableRangeSet.Builder builder = ImmutableRangeSet.builder();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            builder.add((Range) it.next());
        }
        return builder.build();
    }

    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) {
        JavaType javaTypeContainedType = deserializationContext.getContextualType().containedType(0);
        if (javaTypeContainedType == null) {
            return this;
        }
        RangeSetDeserializer rangeSetDeserializer = new RangeSetDeserializer();
        rangeSetDeserializer.genericRangeListType = deserializationContext.getTypeFactory().constructCollectionType(List.class, deserializationContext.getTypeFactory().constructParametricType(Range.class, javaTypeContainedType));
        return rangeSetDeserializer;
    }
}
