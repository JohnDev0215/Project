package com.fasterxml.jackson.datatype.guava.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
abstract class GuavaImmutableMapDeserializer<T extends ImmutableMap<Object, Object>> extends GuavaMapDeserializer<T> {
    private static final long serialVersionUID = 2;

    protected abstract ImmutableMap.Builder<Object, Object> createBuilder();

    GuavaImmutableMapDeserializer(JavaType javaType, KeyDeserializer keyDeserializer, JsonDeserializer<?> jsonDeserializer, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider) {
        super(javaType, keyDeserializer, jsonDeserializer, typeDeserializer, nullValueProvider);
    }

    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaMapDeserializer, com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase, com.fasterxml.jackson.databind.JsonDeserializer
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.CONSTANT;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.fasterxml.jackson.datatype.guava.deser.GuavaMapDeserializer
    public T _deserializeEntries(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Object objDeserializeWithType;
        Object nullValue;
        KeyDeserializer keyDeserializer = this._keyDeserializer;
        JsonDeserializer<?> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        ImmutableMap.Builder<Object, Object> builderCreateBuilder = createBuilder();
        while (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            Object objDeserializeKey = currentName;
            if (keyDeserializer != null) {
                objDeserializeKey = keyDeserializer.deserializeKey(currentName, deserializationContext);
            }
            if (jsonParser.nextToken() == JsonToken.VALUE_NULL) {
                if (!this._skipNullValues && (nullValue = this._nullProvider.getNullValue(deserializationContext)) != null) {
                    builderCreateBuilder.put(objDeserializeKey, nullValue);
                }
            } else {
                if (typeDeserializer == null) {
                    objDeserializeWithType = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                } else {
                    objDeserializeWithType = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                }
                builderCreateBuilder.put(objDeserializeKey, objDeserializeWithType);
            }
            jsonParser.nextToken();
        }
        return (T) builderCreateBuilder.build();
    }
}
