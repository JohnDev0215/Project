package com.fasterxml.jackson.dataformat.xml.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializerCache;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
public class XmlDeserializationContext extends DefaultDeserializationContext {
    private static final long serialVersionUID = 1;

    public XmlDeserializationContext(DeserializerFactory deserializerFactory) {
        super(deserializerFactory, (DeserializerCache) null);
    }

    private XmlDeserializationContext(XmlDeserializationContext xmlDeserializationContext, DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues) {
        super(xmlDeserializationContext, deserializationConfig, jsonParser, injectableValues);
    }

    private XmlDeserializationContext(XmlDeserializationContext xmlDeserializationContext) {
        super(xmlDeserializationContext);
    }

    private XmlDeserializationContext(XmlDeserializationContext xmlDeserializationContext, DeserializerFactory deserializerFactory) {
        super(xmlDeserializationContext, deserializerFactory);
    }

    private XmlDeserializationContext(XmlDeserializationContext xmlDeserializationContext, DeserializationConfig deserializationConfig) {
        super(xmlDeserializationContext, deserializationConfig);
    }

    @Override // com.fasterxml.jackson.databind.deser.DefaultDeserializationContext
    public XmlDeserializationContext copy() {
        return new XmlDeserializationContext(this);
    }

    @Override // com.fasterxml.jackson.databind.deser.DefaultDeserializationContext
    public DefaultDeserializationContext createInstance(DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues) {
        return new XmlDeserializationContext(this, deserializationConfig, jsonParser, injectableValues);
    }

    @Override // com.fasterxml.jackson.databind.deser.DefaultDeserializationContext
    public DefaultDeserializationContext createDummyInstance(DeserializationConfig deserializationConfig) {
        return new XmlDeserializationContext(this, deserializationConfig);
    }

    @Override // com.fasterxml.jackson.databind.deser.DefaultDeserializationContext
    public DefaultDeserializationContext with(DeserializerFactory deserializerFactory) {
        return new XmlDeserializationContext(this, deserializerFactory);
    }

    @Override // com.fasterxml.jackson.databind.deser.DefaultDeserializationContext
    public Object readRootValue(JsonParser jsonParser, JavaType javaType, JsonDeserializer<Object> jsonDeserializer, Object obj) throws IOException {
        if (obj == null) {
            return jsonDeserializer.deserialize(jsonParser, this);
        }
        return jsonDeserializer.deserialize(jsonParser, this, obj);
    }

    @Override // com.fasterxml.jackson.databind.DeserializationContext
    public String extractScalarFromObject(JsonParser jsonParser, JsonDeserializer<?> jsonDeserializer, Class<?> cls) throws IOException {
        String text = "";
        while (jsonParser.nextToken() == JsonToken.FIELD_NAME) {
            String strCurrentName = jsonParser.currentName();
            if (jsonParser.nextToken() == JsonToken.VALUE_STRING) {
                if (strCurrentName.equals("")) {
                    text = jsonParser.getText();
                }
            } else {
                jsonParser.skipChildren();
            }
        }
        return text;
    }
}
