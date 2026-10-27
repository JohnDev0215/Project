package com.fasterxml.jackson.datatype.guava.deser.multimap;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public abstract class GuavaMultimapDeserializer<T extends Multimap<Object, Object>> extends StdDeserializer<T> implements ContextualDeserializer {
    private static final List<String> METHOD_NAMES = ImmutableList.of("copyOf", "create");
    private static final long serialVersionUID = 1;
    private final Method creatorMethod;
    private final JsonDeserializer<?> elementDeserializer;
    private final TypeDeserializer elementTypeDeserializer;
    private final KeyDeserializer keyDeserializer;
    private final NullValueProvider nullProvider;
    private final boolean skipNullValues;
    private final MapLikeType type;

    protected abstract JsonDeserializer<?> _createContextual(MapLikeType mapLikeType, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer, Method method, NullValueProvider nullValueProvider);

    protected abstract T createMultimap();

    public GuavaMultimapDeserializer(MapLikeType mapLikeType, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        this(mapLikeType, keyDeserializer, typeDeserializer, jsonDeserializer, findTransformer(mapLikeType.getRawClass()), null);
    }

    public GuavaMultimapDeserializer(MapLikeType mapLikeType, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer, Method method, NullValueProvider nullValueProvider) {
        super(mapLikeType);
        this.type = mapLikeType;
        this.keyDeserializer = keyDeserializer;
        this.elementTypeDeserializer = typeDeserializer;
        this.elementDeserializer = jsonDeserializer;
        this.creatorMethod = method;
        this.nullProvider = nullValueProvider;
        this.skipNullValues = nullValueProvider == null ? false : NullsConstantProvider.isSkipper(nullValueProvider);
    }

    private static Method findTransformer(Class<?> cls) {
        Method method;
        Method method2;
        if (cls != LinkedListMultimap.class && cls != ListMultimap.class && cls != Multimap.class) {
            Iterator<String> it = METHOD_NAMES.iterator();
            while (it.hasNext()) {
                try {
                    method2 = cls.getMethod(it.next(), Multimap.class);
                } catch (NoSuchMethodException unused) {
                }
                if (method2 != null) {
                    return method2;
                }
            }
            Iterator<String> it2 = METHOD_NAMES.iterator();
            while (it2.hasNext()) {
                try {
                    method = cls.getMethod(it2.next(), Multimap.class);
                } catch (NoSuchMethodException unused2) {
                }
                if (method != null) {
                    return method;
                }
            }
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public LogicalType logicalType() {
        return LogicalType.Map;
    }

    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<?> jsonDeserializerHandleSecondaryContextualization;
        KeyDeserializer keyDeserializerFindKeyDeserializer = this.keyDeserializer;
        if (keyDeserializerFindKeyDeserializer == null) {
            keyDeserializerFindKeyDeserializer = deserializationContext.findKeyDeserializer(this.type.getKeyType(), beanProperty);
        }
        KeyDeserializer keyDeserializer = keyDeserializerFindKeyDeserializer;
        JsonDeserializer<?> jsonDeserializer = this.elementDeserializer;
        JavaType contentType = this.type.getContentType();
        if (jsonDeserializer == null) {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.findContextualValueDeserializer(contentType, beanProperty);
        } else {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(jsonDeserializer, beanProperty, contentType);
        }
        JsonDeserializer<?> jsonDeserializer2 = jsonDeserializerHandleSecondaryContextualization;
        TypeDeserializer typeDeserializerForProperty = this.elementTypeDeserializer;
        if (typeDeserializerForProperty != null) {
            typeDeserializerForProperty = typeDeserializerForProperty.forProperty(beanProperty);
        }
        return _createContextual(this.type, keyDeserializer, typeDeserializerForProperty, jsonDeserializer2, this.creatorMethod, findContentNullProvider(deserializationContext, beanProperty, jsonDeserializer2));
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            return (T) deserializeFromSingleValue(jsonParser, deserializationContext);
        }
        return (T) deserializeContents(jsonParser, deserializationContext);
    }

    private T deserializeContents(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Object currentName;
        Object objDeserialize;
        T t = (T) createMultimap();
        expect(jsonParser, JsonToken.START_OBJECT);
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            KeyDeserializer keyDeserializer = this.keyDeserializer;
            if (keyDeserializer != null) {
                currentName = keyDeserializer.deserializeKey(jsonParser.getCurrentName(), deserializationContext);
            } else {
                currentName = jsonParser.getCurrentName();
            }
            jsonParser.nextToken();
            expect(jsonParser, JsonToken.START_ARRAY);
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
                    if (!this.skipNullValues) {
                        objDeserialize = this.nullProvider.getNullValue(deserializationContext);
                    }
                } else {
                    TypeDeserializer typeDeserializer = this.elementTypeDeserializer;
                    if (typeDeserializer != null) {
                        objDeserialize = this.elementDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                    } else {
                        objDeserialize = this.elementDeserializer.deserialize(jsonParser, deserializationContext);
                    }
                }
                t.put(currentName, objDeserialize);
            }
        }
        Method method = this.creatorMethod;
        if (method == null) {
            return t;
        }
        try {
            return (T) method.invoke(null, t);
        } catch (IllegalAccessException e) {
            throw new JsonMappingException(jsonParser, "Could not map to " + this.type, _peel(e));
        } catch (IllegalArgumentException e2) {
            throw new JsonMappingException(jsonParser, "Could not map to " + this.type, _peel(e2));
        } catch (InvocationTargetException e3) {
            throw new JsonMappingException(jsonParser, "Could not map to " + this.type, _peel(e3));
        }
    }

    private T deserializeFromSingleValue(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Object currentName;
        T t = (T) createMultimap();
        expect(jsonParser, JsonToken.START_OBJECT);
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            KeyDeserializer keyDeserializer = this.keyDeserializer;
            if (keyDeserializer != null) {
                currentName = keyDeserializer.deserializeKey(jsonParser.getCurrentName(), deserializationContext);
            } else {
                currentName = jsonParser.getCurrentName();
            }
            jsonParser.nextToken();
            if (jsonParser.currentToken() == JsonToken.START_ARRAY) {
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    t.put(currentName, getCurrentTokenValue(jsonParser, deserializationContext));
                }
            } else {
                t.put(currentName, getCurrentTokenValue(jsonParser, deserializationContext));
            }
        }
        Method method = this.creatorMethod;
        if (method == null) {
            return t;
        }
        try {
            return (T) method.invoke(null, t);
        } catch (IllegalAccessException e) {
            throw new JsonMappingException(jsonParser, "Could not map to " + this.type, _peel(e));
        } catch (IllegalArgumentException e2) {
            throw new JsonMappingException(jsonParser, "Could not map to " + this.type, _peel(e2));
        } catch (InvocationTargetException e3) {
            throw new JsonMappingException(jsonParser, "Could not map to " + this.type, _peel(e3));
        }
    }

    private Object getCurrentTokenValue(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        TypeDeserializer typeDeserializer = this.elementTypeDeserializer;
        if (typeDeserializer != null) {
            return this.elementDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
        }
        return this.elementDeserializer.deserialize(jsonParser, deserializationContext);
    }

    private void expect(JsonParser jsonParser, JsonToken jsonToken) throws IOException {
        if (jsonParser.getCurrentToken() == jsonToken) {
            return;
        }
        throw new JsonMappingException(jsonParser, "Expecting " + jsonToken + ", found " + jsonParser.getCurrentToken(), jsonParser.getCurrentLocation());
    }

    private Throwable _peel(Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }
}
