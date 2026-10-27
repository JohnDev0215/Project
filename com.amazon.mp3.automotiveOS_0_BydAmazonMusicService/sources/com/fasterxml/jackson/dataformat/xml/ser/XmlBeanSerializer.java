package com.fasterxml.jackson.dataformat.xml.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.BeanAsArraySerializer;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.Set;

/* JADX INFO: loaded from: classes6.dex */
public class XmlBeanSerializer extends XmlBeanSerializerBase {
    private static final long serialVersionUID = 1;

    public XmlBeanSerializer(BeanSerializerBase beanSerializerBase) {
        super(beanSerializerBase);
    }

    public XmlBeanSerializer(XmlBeanSerializerBase xmlBeanSerializerBase, ObjectIdWriter objectIdWriter, Object obj) {
        super(xmlBeanSerializerBase, objectIdWriter, obj);
    }

    public XmlBeanSerializer(XmlBeanSerializerBase xmlBeanSerializerBase, ObjectIdWriter objectIdWriter) {
        super(xmlBeanSerializerBase, objectIdWriter);
    }

    public XmlBeanSerializer(XmlBeanSerializerBase xmlBeanSerializerBase, Set<String> set, Set<String> set2) {
        super(xmlBeanSerializerBase, set, set2);
    }

    protected XmlBeanSerializer(XmlBeanSerializerBase xmlBeanSerializerBase, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2) {
        super(xmlBeanSerializerBase, beanPropertyWriterArr, beanPropertyWriterArr2);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public JsonSerializer<Object> unwrappingSerializer(NameTransformer nameTransformer) {
        return new UnwrappingXmlBeanSerializer(this, nameTransformer);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public BeanSerializerBase withObjectIdWriter(ObjectIdWriter objectIdWriter) {
        return new XmlBeanSerializer(this, objectIdWriter, this._propertyFilterId);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase, com.fasterxml.jackson.databind.JsonSerializer
    public BeanSerializerBase withFilterId(Object obj) {
        return new XmlBeanSerializer(this, this._objectIdWriter, obj);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    protected BeanSerializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
        return new XmlBeanSerializer(this, set, set2);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    protected BeanSerializerBase withProperties(BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2) {
        return new XmlBeanSerializer(this, beanPropertyWriterArr, beanPropertyWriterArr2);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    protected BeanSerializerBase asArraySerializer() {
        return (this._objectIdWriter == null && this._anyGetterWriter == null && this._propertyFilterId == null) ? new BeanAsArraySerializer(this) : this;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase, com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (this._objectIdWriter != null) {
            _serializeWithObjectId(obj, jsonGenerator, serializerProvider, true);
            return;
        }
        jsonGenerator.writeStartObject();
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
        } else {
            serializeFields(obj, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }

    public String toString() {
        return "XmlBeanSerializer for " + handledType().getName();
    }
}
