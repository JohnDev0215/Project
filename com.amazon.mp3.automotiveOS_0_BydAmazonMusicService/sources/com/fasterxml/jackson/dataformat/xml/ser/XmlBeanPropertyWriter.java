package com.fasterxml.jackson.dataformat.xml.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes6.dex */
public class XmlBeanPropertyWriter extends BeanPropertyWriter {
    private static final long serialVersionUID = 1;
    protected final QName _wrappedQName;
    protected final QName _wrapperQName;

    public XmlBeanPropertyWriter(BeanPropertyWriter beanPropertyWriter, PropertyName propertyName, PropertyName propertyName2) {
        this(beanPropertyWriter, propertyName, propertyName2, null);
    }

    public XmlBeanPropertyWriter(BeanPropertyWriter beanPropertyWriter, PropertyName propertyName, PropertyName propertyName2, JsonSerializer<Object> jsonSerializer) {
        super(beanPropertyWriter);
        this._wrapperQName = _qname(propertyName);
        this._wrappedQName = _qname(propertyName2);
        if (jsonSerializer != null) {
            assignSerializer(jsonSerializer);
        }
    }

    private QName _qname(PropertyName propertyName) {
        String namespace = propertyName.getNamespace();
        if (namespace == null) {
            namespace = "";
        }
        return new QName(namespace, propertyName.getSimpleName());
    }

    @Override // com.fasterxml.jackson.databind.ser.BeanPropertyWriter, com.fasterxml.jackson.databind.ser.PropertyWriter
    public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        Object obj2 = get(obj);
        if (obj2 == null) {
            return;
        }
        JsonSerializer<?> jsonSerializer_findAndAddDynamic = this._serializer;
        if (jsonSerializer_findAndAddDynamic == null) {
            Class<?> cls = obj2.getClass();
            PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
            JsonSerializer<?> jsonSerializerSerializerFor = propertySerializerMap.serializerFor(cls);
            jsonSerializer_findAndAddDynamic = jsonSerializerSerializerFor == null ? _findAndAddDynamic(propertySerializerMap, cls, serializerProvider) : jsonSerializerSerializerFor;
        }
        Object obj3 = this._suppressableValue;
        if (obj3 != null) {
            if (BeanPropertyWriter.MARKER_FOR_EMPTY == obj3) {
                if (jsonSerializer_findAndAddDynamic.isEmpty(serializerProvider, obj2)) {
                    return;
                }
            } else if (obj3.equals(obj2)) {
                return;
            }
        }
        if (obj2 == obj && _handleSelfReference(obj, jsonGenerator, serializerProvider, jsonSerializer_findAndAddDynamic)) {
            return;
        }
        ToXmlGenerator toXmlGenerator = jsonGenerator instanceof ToXmlGenerator ? (ToXmlGenerator) jsonGenerator : null;
        if (toXmlGenerator != null) {
            toXmlGenerator.startWrappedValue(this._wrapperQName, this._wrappedQName);
        }
        jsonGenerator.writeFieldName(this._name);
        TypeSerializer typeSerializer = this._typeSerializer;
        if (typeSerializer == null) {
            jsonSerializer_findAndAddDynamic.serialize(obj2, jsonGenerator, serializerProvider);
        } else {
            jsonSerializer_findAndAddDynamic.serializeWithType(obj2, jsonGenerator, serializerProvider, typeSerializer);
        }
        if (toXmlGenerator != null) {
            toXmlGenerator.finishWrappedValue(this._wrapperQName, this._wrappedQName);
        }
    }
}
