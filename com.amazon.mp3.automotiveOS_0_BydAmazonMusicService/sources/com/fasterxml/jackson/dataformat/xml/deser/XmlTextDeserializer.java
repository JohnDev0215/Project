package com.fasterxml.jackson.dataformat.xml.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.std.DelegatingDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
public class XmlTextDeserializer extends DelegatingDeserializer {
    private static final long serialVersionUID = 1;
    protected final ValueInstantiator _valueInstantiator;
    protected final SettableBeanProperty _xmlTextProperty;
    protected final int _xmlTextPropertyIndex;

    public XmlTextDeserializer(BeanDeserializerBase beanDeserializerBase, SettableBeanProperty settableBeanProperty) {
        super(beanDeserializerBase);
        this._xmlTextProperty = settableBeanProperty;
        this._xmlTextPropertyIndex = settableBeanProperty.getPropertyIndex();
        this._valueInstantiator = beanDeserializerBase.getValueInstantiator();
    }

    public XmlTextDeserializer(BeanDeserializerBase beanDeserializerBase, int i) {
        super(beanDeserializerBase);
        this._xmlTextPropertyIndex = i;
        this._valueInstantiator = beanDeserializerBase.getValueInstantiator();
        this._xmlTextProperty = beanDeserializerBase.findProperty(i);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.DelegatingDeserializer
    protected JsonDeserializer<?> newDelegatingInstance(JsonDeserializer<?> jsonDeserializer) {
        throw new IllegalStateException("Internal error: should never get called");
    }

    @Override // com.fasterxml.jackson.databind.deser.std.DelegatingDeserializer, com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        return new XmlTextDeserializer(_verifyDeserType(this._delegatee), this._xmlTextPropertyIndex);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.DelegatingDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
            Object objCreateUsingDefault = this._valueInstantiator.createUsingDefault(deserializationContext);
            this._xmlTextProperty.deserializeAndSet(jsonParser, deserializationContext, objCreateUsingDefault);
            return objCreateUsingDefault;
        }
        return this._delegatee.deserialize(jsonParser, deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.DelegatingDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
            this._xmlTextProperty.deserializeAndSet(jsonParser, deserializationContext, obj);
            return obj;
        }
        return this._delegatee.deserialize(jsonParser, deserializationContext, obj);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.DelegatingDeserializer, com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return this._delegatee.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
    }

    protected BeanDeserializerBase _verifyDeserType(JsonDeserializer<?> jsonDeserializer) {
        if (!(jsonDeserializer instanceof BeanDeserializerBase)) {
            throw new IllegalArgumentException("Can not change delegate to be of type " + jsonDeserializer.getClass().getName());
        }
        return (BeanDeserializerBase) jsonDeserializer;
    }
}
