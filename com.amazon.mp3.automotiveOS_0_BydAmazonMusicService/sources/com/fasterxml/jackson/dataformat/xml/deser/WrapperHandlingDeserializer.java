package com.fasterxml.jackson.dataformat.xml.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.std.DelegatingDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.dataformat.xml.util.TypeUtil;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes6.dex */
public class WrapperHandlingDeserializer extends DelegatingDeserializer {
    private static final long serialVersionUID = 1;
    protected final boolean _caseInsensitive;
    protected final Set<String> _namesToWrap;
    protected final JavaType _type;

    public WrapperHandlingDeserializer(BeanDeserializerBase beanDeserializerBase) {
        this(beanDeserializerBase, null);
    }

    public WrapperHandlingDeserializer(BeanDeserializerBase beanDeserializerBase, Set<String> set) {
        super(beanDeserializerBase);
        this._namesToWrap = set;
        this._type = beanDeserializerBase.getValueType();
        this._caseInsensitive = beanDeserializerBase.isCaseInsensitive();
    }

    @Override // com.fasterxml.jackson.databind.deser.std.DelegatingDeserializer
    protected JsonDeserializer<?> newDelegatingInstance(JsonDeserializer<?> jsonDeserializer) {
        throw new IllegalStateException("Internal error: should never get called");
    }

    @Override // com.fasterxml.jackson.databind.deser.std.DelegatingDeserializer, com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        PropertyName wrapperName;
        JavaType javaTypeConstructType = this._type;
        if (javaTypeConstructType == null) {
            javaTypeConstructType = deserializationContext.constructType(this._delegatee.handledType());
        }
        BeanDeserializerBase beanDeserializerBase_verifyDeserType = _verifyDeserType(deserializationContext.handleSecondaryContextualization(this._delegatee, beanProperty, javaTypeConstructType));
        Iterator<SettableBeanProperty> itProperties = beanDeserializerBase_verifyDeserType.properties();
        HashSet hashSet = null;
        while (itProperties.hasNext()) {
            SettableBeanProperty next = itProperties.next();
            if (TypeUtil.isIndexedType(next.getType()) && ((wrapperName = next.getWrapperName()) == null || wrapperName == PropertyName.NO_NAME)) {
                if (hashSet == null) {
                    hashSet = new HashSet();
                }
                hashSet.add(next.getName());
                Iterator<PropertyName> it = next.findAliases(deserializationContext.getConfig()).iterator();
                while (it.hasNext()) {
                    hashSet.add(it.next().getSimpleName());
                }
            }
        }
        return hashSet == null ? beanDeserializerBase_verifyDeserType : new WrapperHandlingDeserializer(beanDeserializerBase_verifyDeserType, hashSet);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.DelegatingDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        _configureParser(jsonParser);
        return this._delegatee.deserialize(jsonParser, deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.DelegatingDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        _configureParser(jsonParser);
        return this._delegatee.deserialize(jsonParser, deserializationContext, obj);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.DelegatingDeserializer, com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        _configureParser(jsonParser);
        return this._delegatee.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
    }

    protected final void _configureParser(JsonParser jsonParser) throws IOException {
        while (jsonParser instanceof JsonParserDelegate) {
            jsonParser = ((JsonParserDelegate) jsonParser).delegate();
        }
        if (jsonParser instanceof FromXmlParser) {
            JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
            if (jsonTokenCurrentToken == JsonToken.START_OBJECT || jsonTokenCurrentToken == JsonToken.START_ARRAY || jsonTokenCurrentToken == JsonToken.FIELD_NAME) {
                ((FromXmlParser) jsonParser).addVirtualWrapping(this._namesToWrap, this._caseInsensitive);
            }
        }
    }

    protected BeanDeserializerBase _verifyDeserType(JsonDeserializer<?> jsonDeserializer) {
        if (!(jsonDeserializer instanceof BeanDeserializerBase)) {
            throw new IllegalArgumentException("Can not change delegate to be of type " + jsonDeserializer.getClass().getName());
        }
        return (BeanDeserializerBase) jsonDeserializer;
    }
}
