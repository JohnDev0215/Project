package com.fasterxml.jackson.datatype.guava.ser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonMapFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.std.MapProperty;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.google.common.collect.Multimap;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes6.dex */
public class MultimapSerializer extends ContainerSerializer<Multimap<?, ?>> implements ContextualSerializer {
    private static final long serialVersionUID = 1;
    protected PropertySerializerMap _dynamicValueSerializers;
    protected final Object _filterId;
    protected final Set<String> _ignoredEntries;
    private final JsonSerializer<Object> _keySerializer;
    private final BeanProperty _property;
    protected final boolean _sortKeys;
    private final MapLikeType _type;
    private final JsonSerializer<Object> _valueSerializer;
    private final TypeSerializer _valueTypeSerializer;

    public MultimapSerializer(MapLikeType mapLikeType, BeanDescription beanDescription, JsonSerializer<Object> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer2, Set<String> set, Object obj) {
        super(mapLikeType.getRawClass(), false);
        this._type = mapLikeType;
        this._property = null;
        this._keySerializer = jsonSerializer;
        this._valueTypeSerializer = typeSerializer;
        this._valueSerializer = jsonSerializer2;
        this._ignoredEntries = set;
        this._filterId = obj;
        this._sortKeys = false;
        this._dynamicValueSerializers = PropertySerializerMap.emptyForProperties();
    }

    protected MultimapSerializer(MultimapSerializer multimapSerializer, BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer2, Set<String> set, Object obj, boolean z) {
        super(multimapSerializer);
        this._type = multimapSerializer._type;
        this._property = beanProperty;
        this._keySerializer = jsonSerializer;
        this._valueTypeSerializer = typeSerializer;
        this._valueSerializer = jsonSerializer2;
        this._dynamicValueSerializers = multimapSerializer._dynamicValueSerializers;
        this._ignoredEntries = set;
        this._filterId = obj;
        this._sortKeys = z;
    }

    protected MultimapSerializer withResolved(BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer2, Set<String> set, Object obj, boolean z) {
        return new MultimapSerializer(this, beanProperty, jsonSerializer, typeSerializer, jsonSerializer2, set, obj, z);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    protected ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new MultimapSerializer(this, this._property, this._keySerializer, typeSerializer, this._valueSerializer, this._ignoredEntries, this._filterId, this._sortKeys);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v39 */
    /* JADX WARN: Type inference failed for: r0v40 */
    /* JADX WARN: Type inference failed for: r0v5, types: [com.fasterxml.jackson.databind.JsonSerializer] */
    /* JADX WARN: Type inference failed for: r12v0, types: [com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.datatype.guava.ser.MultimapSerializer] */
    @Override // com.fasterxml.jackson.databind.ser.ContextualSerializer
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonFormatVisitable jsonFormatVisitableCreateContextual;
        JsonSerializer<?> jsonSerializerHandleSecondaryContextualization;
        Boolean feature;
        Set<String> setFindIgnoredForSerialization;
        JsonFormatVisitable jsonFormatVisitable = this._valueSerializer;
        if (jsonFormatVisitable == null) {
            JavaType contentType = this._type.getContentType();
            jsonFormatVisitableCreateContextual = jsonFormatVisitable;
            if (contentType.isFinal()) {
                jsonFormatVisitableCreateContextual = serializerProvider.findValueSerializer(contentType, beanProperty);
            }
        } else {
            boolean z = jsonFormatVisitable instanceof ContextualSerializer;
            jsonFormatVisitableCreateContextual = jsonFormatVisitable;
            if (z) {
                jsonFormatVisitableCreateContextual = ((ContextualSerializer) jsonFormatVisitable).createContextual(serializerProvider, beanProperty);
            }
        }
        AnnotationIntrospector annotationIntrospector = serializerProvider.getAnnotationIntrospector();
        AnnotatedMember member = beanProperty == null ? null : beanProperty.getMember();
        Object objFindFilterId = this._filterId;
        JsonFormatVisitable jsonFormatVisitable2 = jsonFormatVisitableCreateContextual;
        jsonFormatVisitable2 = jsonFormatVisitableCreateContextual;
        if (member != null && annotationIntrospector != null) {
            Object objFindKeySerializer = annotationIntrospector.findKeySerializer(member);
            jsonSerializerSerializerInstance = objFindKeySerializer != null ? serializerProvider.serializerInstance(member, objFindKeySerializer) : null;
            Object objFindContentSerializer = annotationIntrospector.findContentSerializer(member);
            JsonFormatVisitable jsonFormatVisitableSerializerInstance = jsonFormatVisitableCreateContextual;
            if (objFindContentSerializer != null) {
                jsonFormatVisitableSerializerInstance = serializerProvider.serializerInstance(member, objFindContentSerializer);
            }
            objFindFilterId = annotationIntrospector.findFilterId(member);
            jsonFormatVisitable2 = jsonFormatVisitableSerializerInstance;
        }
        Object obj = objFindFilterId;
        ?? r0 = jsonFormatVisitable2;
        if (jsonFormatVisitable2 == null) {
            r0 = this._valueSerializer;
        }
        JsonSerializer<?> jsonSerializerFindContextualConvertingSerializer = findContextualConvertingSerializer(serializerProvider, beanProperty, r0);
        if (jsonSerializerFindContextualConvertingSerializer == null) {
            JavaType contentType2 = this._type.getContentType();
            if (contentType2.useStaticType()) {
                jsonSerializerFindContextualConvertingSerializer = serializerProvider.findValueSerializer(contentType2, beanProperty);
            }
        } else {
            jsonSerializerFindContextualConvertingSerializer = serializerProvider.handleSecondaryContextualization(jsonSerializerFindContextualConvertingSerializer, beanProperty);
        }
        JsonSerializer<?> jsonSerializer = jsonSerializerFindContextualConvertingSerializer;
        if (jsonSerializerSerializerInstance == null) {
            jsonSerializerSerializerInstance = this._keySerializer;
        }
        if (jsonSerializerSerializerInstance == null) {
            jsonSerializerHandleSecondaryContextualization = serializerProvider.findKeySerializer(this._type.getKeyType(), beanProperty);
        } else {
            jsonSerializerHandleSecondaryContextualization = serializerProvider.handleSecondaryContextualization(jsonSerializerSerializerInstance, beanProperty);
        }
        JsonSerializer<?> jsonSerializer2 = jsonSerializerHandleSecondaryContextualization;
        TypeSerializer typeSerializerForProperty = this._valueTypeSerializer;
        if (typeSerializerForProperty != null) {
            typeSerializerForProperty = typeSerializerForProperty.forProperty(beanProperty);
        }
        TypeSerializer typeSerializer = typeSerializerForProperty;
        Set<String> hashSet = this._ignoredEntries;
        boolean z2 = false;
        if (annotationIntrospector != null && member != null) {
            JsonIgnoreProperties.Value valueFindPropertyIgnoralByName = annotationIntrospector.findPropertyIgnoralByName(serializerProvider.getConfig(), member);
            if (valueFindPropertyIgnoralByName != null && (setFindIgnoredForSerialization = valueFindPropertyIgnoralByName.findIgnoredForSerialization()) != null && !setFindIgnoredForSerialization.isEmpty()) {
                hashSet = hashSet == null ? new HashSet<>() : new HashSet(hashSet);
                Iterator<String> it = setFindIgnoredForSerialization.iterator();
                while (it.hasNext()) {
                    hashSet.add(it.next());
                }
            }
            Boolean boolFindSerializationSortAlphabetically = annotationIntrospector.findSerializationSortAlphabetically(member);
            if (boolFindSerializationSortAlphabetically != null && boolFindSerializationSortAlphabetically.booleanValue()) {
                z2 = true;
            }
        }
        Set<String> set = hashSet;
        JsonFormat.Value valueFindFormatOverrides = findFormatOverrides(serializerProvider, beanProperty, handledType());
        return withResolved(beanProperty, jsonSerializer2, typeSerializer, jsonSerializer, set, obj, (valueFindFormatOverrides == null || (feature = valueFindFormatOverrides.getFeature(JsonFormat.Feature.WRITE_SORTED_MAP_ENTRIES)) == null) ? z2 : feature.booleanValue());
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public JsonSerializer<?> getContentSerializer() {
        return this._valueSerializer;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public JavaType getContentType() {
        return this._type.getContentType();
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public boolean hasSingleElement(Multimap<?, ?> multimap) {
        return multimap.size() == 1;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public boolean isEmpty(SerializerProvider serializerProvider, Multimap<?, ?> multimap) {
        return multimap.isEmpty();
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(Multimap<?, ?> multimap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.setCurrentValue(multimap);
        if (!multimap.isEmpty()) {
            if (this._filterId != null) {
                serializeFilteredFields(multimap, jsonGenerator, serializerProvider);
            } else {
                serializeFields(multimap, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serializeWithType(Multimap<?, ?> multimap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        jsonGenerator.setCurrentValue(multimap);
        WritableTypeId writableTypeIdWriteTypePrefix = typeSerializer.writeTypePrefix(jsonGenerator, typeSerializer.typeId(multimap, JsonToken.START_OBJECT));
        if (!multimap.isEmpty()) {
            if (this._filterId != null) {
                serializeFilteredFields(multimap, jsonGenerator, serializerProvider);
            } else {
                serializeFields(multimap, jsonGenerator, serializerProvider);
            }
        }
        typeSerializer.writeTypeSuffix(jsonGenerator, writableTypeIdWriteTypePrefix);
    }

    private final void serializeFields(Multimap<?, ?> multimap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Set<String> set = this._ignoredEntries;
        PropertySerializerMap propertySerializerMap = this._dynamicValueSerializers;
        for (Map.Entry<?, Collection<?>> entry : multimap.asMap().entrySet()) {
            Object key = entry.getKey();
            if (set == null || !set.contains(key)) {
                if (key == null) {
                    serializerProvider.findNullKeySerializer(this._type.getKeyType(), this._property).serialize(null, jsonGenerator, serializerProvider);
                } else {
                    this._keySerializer.serialize(key, jsonGenerator, serializerProvider);
                }
                jsonGenerator.writeStartArray();
                for (Object obj : entry.getValue()) {
                    if (obj == null) {
                        serializerProvider.defaultSerializeNull(jsonGenerator);
                    } else {
                        JsonSerializer<Object> jsonSerializer_findAndAddDynamic = this._valueSerializer;
                        if (jsonSerializer_findAndAddDynamic == null) {
                            Class<?> cls = obj.getClass();
                            JsonSerializer<Object> jsonSerializerSerializerFor = propertySerializerMap.serializerFor(cls);
                            if (jsonSerializerSerializerFor == null) {
                                jsonSerializer_findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                                propertySerializerMap = this._dynamicValueSerializers;
                            } else {
                                jsonSerializer_findAndAddDynamic = jsonSerializerSerializerFor;
                            }
                        }
                        TypeSerializer typeSerializer = this._valueTypeSerializer;
                        if (typeSerializer == null) {
                            jsonSerializer_findAndAddDynamic.serialize(obj, jsonGenerator, serializerProvider);
                        } else {
                            jsonSerializer_findAndAddDynamic.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
                        }
                    }
                }
                jsonGenerator.writeEndArray();
            }
        }
    }

    private final void serializeFilteredFields(Multimap<?, ?> multimap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        JsonSerializer<Object> defaultNullValueSerializer;
        Set<String> set = this._ignoredEntries;
        PropertyFilter propertyFilterFindPropertyFilter = findPropertyFilter(serializerProvider, this._filterId, multimap);
        MapProperty mapProperty = new MapProperty(this._valueTypeSerializer, this._property);
        for (Map.Entry<?, Collection<?>> entry : multimap.asMap().entrySet()) {
            Object key = entry.getKey();
            if (set == null || !set.contains(key)) {
                Collection<?> value = entry.getValue();
                if (value == null) {
                    defaultNullValueSerializer = serializerProvider.getDefaultNullValueSerializer();
                } else {
                    defaultNullValueSerializer = this._valueSerializer;
                }
                mapProperty.reset(key, value, this._keySerializer, defaultNullValueSerializer);
                try {
                    propertyFilterFindPropertyFilter.serializeAsField(multimap, jsonGenerator, serializerProvider, mapProperty);
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider, e, value, "" + key);
                }
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonMapFormatVisitor jsonMapFormatVisitorExpectMapFormat = jsonFormatVisitorWrapper == null ? null : jsonFormatVisitorWrapper.expectMapFormat(javaType);
        if (jsonMapFormatVisitorExpectMapFormat != null) {
            jsonMapFormatVisitorExpectMapFormat.keyFormat(this._keySerializer, this._type.getKeyType());
            JsonSerializer<Object> jsonSerializer_findAndAddDynamic = this._valueSerializer;
            JavaType contentType = this._type.getContentType();
            SerializerProvider provider = jsonFormatVisitorWrapper.getProvider();
            if (jsonSerializer_findAndAddDynamic == null) {
                jsonSerializer_findAndAddDynamic = _findAndAddDynamic(this._dynamicValueSerializers, contentType, provider);
            }
            jsonMapFormatVisitorExpectMapFormat.valueFormat(new JsonFormatVisitable(provider, contentType, jsonSerializer_findAndAddDynamic) { // from class: com.fasterxml.jackson.datatype.guava.ser.MultimapSerializer.1
                final JavaType arrayType;
                final /* synthetic */ SerializerProvider val$prov;
                final /* synthetic */ JsonSerializer val$valueSer2;
                final /* synthetic */ JavaType val$vt;

                {
                    this.val$prov = provider;
                    this.val$vt = contentType;
                    this.val$valueSer2 = jsonSerializer_findAndAddDynamic;
                    this.arrayType = provider.getTypeFactory().constructArrayType(contentType);
                }

                @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable
                public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper2, JavaType javaType2) throws JsonMappingException {
                    JsonArrayFormatVisitor jsonArrayFormatVisitorExpectArrayFormat = jsonFormatVisitorWrapper2.expectArrayFormat(this.arrayType);
                    if (jsonArrayFormatVisitorExpectArrayFormat != null) {
                        jsonArrayFormatVisitorExpectArrayFormat.itemsFormat(this.val$valueSer2, this.val$vt);
                    }
                }
            }, contentType);
        }
    }

    protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap.SerializerAndMapResult serializerAndMapResultFindAndAddSecondarySerializer = propertySerializerMap.findAndAddSecondarySerializer(cls, serializerProvider, this._property);
        PropertySerializerMap propertySerializerMap2 = serializerAndMapResultFindAndAddSecondarySerializer.map;
        if (propertySerializerMap != propertySerializerMap2) {
            this._dynamicValueSerializers = propertySerializerMap2;
        }
        return serializerAndMapResultFindAndAddSecondarySerializer.serializer;
    }

    protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, JavaType javaType, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap.SerializerAndMapResult serializerAndMapResultFindAndAddSecondarySerializer = propertySerializerMap.findAndAddSecondarySerializer(javaType, serializerProvider, this._property);
        PropertySerializerMap propertySerializerMap2 = serializerAndMapResultFindAndAddSecondarySerializer.map;
        if (propertySerializerMap != propertySerializerMap2) {
            this._dynamicValueSerializers = propertySerializerMap2;
        }
        return serializerAndMapResultFindAndAddSecondarySerializer.serializer;
    }
}
