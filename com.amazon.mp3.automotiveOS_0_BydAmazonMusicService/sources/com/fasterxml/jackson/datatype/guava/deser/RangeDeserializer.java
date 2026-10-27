package com.fasterxml.jackson.datatype.guava.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.guava.deser.util.RangeFactory;
import com.fasterxml.jackson.datatype.guava.deser.util.RangeHelper;
import com.google.common.base.Preconditions;
import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes6.dex */
public class RangeDeserializer extends StdDeserializer<Range<?>> implements ContextualDeserializer {
    private static final long serialVersionUID = 1;
    protected final BoundType _defaultBoundType;
    protected final JsonDeserializer<Object> _endpointDeserializer;
    protected final RangeHelper.RangeProperties _fieldNames;
    protected final JavaType _rangeType;

    public RangeDeserializer(BoundType boundType, JavaType javaType) {
        this(javaType, null, boundType);
    }

    @Deprecated
    public RangeDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer) {
        this(javaType, jsonDeserializer, null);
    }

    @Deprecated
    public RangeDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer, BoundType boundType) {
        this(javaType, jsonDeserializer, boundType, RangeHelper.standardNames());
    }

    protected RangeDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer, BoundType boundType, RangeHelper.RangeProperties rangeProperties) {
        super(javaType);
        this._rangeType = javaType;
        this._endpointDeserializer = jsonDeserializer;
        this._defaultBoundType = boundType;
        this._fieldNames = rangeProperties;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer
    public JavaType getValueType() {
        return this._rangeType;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public LogicalType logicalType() {
        return LogicalType.POJO;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.fasterxml.jackson.databind.JsonDeserializer] */
    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        ?? CreateContextual;
        RangeHelper.RangeProperties propertyNames = RangeHelper.getPropertyNames(deserializationContext.getConfig(), deserializationContext.getConfig().getPropertyNamingStrategy());
        NullValueProvider nullValueProvider = this._endpointDeserializer;
        if (nullValueProvider == null) {
            JavaType javaTypeContainedType = this._rangeType.containedType(0);
            if (javaTypeContainedType == null) {
                javaTypeContainedType = TypeFactory.unknownType();
            }
            CreateContextual = deserializationContext.findContextualValueDeserializer(javaTypeContainedType, beanProperty);
        } else {
            boolean z = nullValueProvider instanceof ContextualDeserializer;
            CreateContextual = nullValueProvider;
            if (z) {
                CreateContextual = ((ContextualDeserializer) nullValueProvider).createContextual(deserializationContext, beanProperty);
            }
        }
        return (CreateContextual == this._endpointDeserializer && propertyNames == this._fieldNames) ? this : new RangeDeserializer(this._rangeType, CreateContextual, this._defaultBoundType, propertyNames);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Range<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        BoundType boundTypeDeserializeBoundType = this._defaultBoundType;
        Comparable<?> comparableDeserializeEndpoint = null;
        Comparable<?> comparableDeserializeEndpoint2 = null;
        BoundType boundTypeDeserializeBoundType2 = boundTypeDeserializeBoundType;
        while (currentToken != JsonToken.END_OBJECT) {
            expect(deserializationContext, JsonToken.FIELD_NAME, currentToken);
            String currentName = jsonParser.getCurrentName();
            try {
                if (currentName.equals(this._fieldNames.lowerEndpoint)) {
                    jsonParser.nextToken();
                    comparableDeserializeEndpoint = deserializeEndpoint(deserializationContext, jsonParser);
                } else if (currentName.equals(this._fieldNames.upperEndpoint)) {
                    jsonParser.nextToken();
                    comparableDeserializeEndpoint2 = deserializeEndpoint(deserializationContext, jsonParser);
                } else if (currentName.equals(this._fieldNames.lowerBoundType)) {
                    jsonParser.nextToken();
                    boundTypeDeserializeBoundType = deserializeBoundType(deserializationContext, jsonParser);
                } else if (currentName.equals(this._fieldNames.upperBoundType)) {
                    jsonParser.nextToken();
                    boundTypeDeserializeBoundType2 = deserializeBoundType(deserializationContext, jsonParser);
                } else {
                    deserializationContext.handleUnknownProperty(jsonParser, this, Range.class, currentName);
                }
                currentToken = jsonParser.nextToken();
            } catch (IllegalStateException e) {
                throw JsonMappingException.from(jsonParser, e.getMessage());
            }
        }
        boolean z = true;
        try {
            if (comparableDeserializeEndpoint != null && comparableDeserializeEndpoint2 != null) {
                Preconditions.checkState(comparableDeserializeEndpoint.getClass() == comparableDeserializeEndpoint2.getClass(), "Endpoint types are not the same - 'lowerEndpoint' deserialized to [%s], and 'upperEndpoint' deserialized to [%s].", comparableDeserializeEndpoint.getClass().getName(), comparableDeserializeEndpoint2.getClass().getName());
                Preconditions.checkState(boundTypeDeserializeBoundType != null, "'lowerEndpoint' field found, but not 'lowerBoundType'");
                if (boundTypeDeserializeBoundType2 == null) {
                    z = false;
                }
                Preconditions.checkState(z, "'upperEndpoint' field found, but not 'upperBoundType'");
                return RangeFactory.range(comparableDeserializeEndpoint, boundTypeDeserializeBoundType, comparableDeserializeEndpoint2, boundTypeDeserializeBoundType2);
            }
            if (comparableDeserializeEndpoint != null) {
                if (boundTypeDeserializeBoundType == null) {
                    z = false;
                }
                Preconditions.checkState(z, "'lowerEndpoint' field found, but not 'lowerBoundType'");
                return RangeFactory.downTo(comparableDeserializeEndpoint, boundTypeDeserializeBoundType);
            }
            if (comparableDeserializeEndpoint2 != null) {
                if (boundTypeDeserializeBoundType2 == null) {
                    z = false;
                }
                Preconditions.checkState(z, "'upperEndpoint' field found, but not 'upperBoundType'");
                return RangeFactory.upTo(comparableDeserializeEndpoint2, boundTypeDeserializeBoundType2);
            }
            return RangeFactory.all();
        } catch (IllegalStateException e2) {
            throw JsonMappingException.from(jsonParser, e2.getMessage());
        }
    }

    private BoundType deserializeBoundType(DeserializationContext deserializationContext, JsonParser jsonParser) throws IOException {
        expect(deserializationContext, JsonToken.VALUE_STRING, jsonParser.getCurrentToken());
        String text = jsonParser.getText();
        try {
            return BoundType.valueOf(text);
        } catch (IllegalArgumentException unused) {
            return (BoundType) deserializationContext.handleWeirdStringValue(BoundType.class, text, "not a valid BoundType name (should be one oF: %s)", Arrays.asList(BoundType.values()));
        }
    }

    private Comparable<?> deserializeEndpoint(DeserializationContext deserializationContext, JsonParser jsonParser) throws IOException {
        Object objDeserialize = this._endpointDeserializer.deserialize(jsonParser, deserializationContext);
        if (!(objDeserialize instanceof Comparable)) {
            deserializationContext.reportBadDefinition(this._rangeType, String.format("Field [%s] deserialized to [%s], which does not implement Comparable.", jsonParser.getCurrentName(), objDeserialize.getClass().getName()));
        }
        return (Comparable) objDeserialize;
    }

    private void expect(DeserializationContext deserializationContext, JsonToken jsonToken, JsonToken jsonToken2) throws JsonMappingException {
        if (jsonToken2 != jsonToken) {
            deserializationContext.reportInputMismatch(this, String.format("Problem deserializing %s: expecting %s, found %s", handledType().getName(), jsonToken, jsonToken2), new Object[0]);
        }
    }
}
