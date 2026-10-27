package com.fasterxml.jackson.dataformat.xml.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.Set;

/* JADX INFO: loaded from: classes6.dex */
public class UnwrappingXmlBeanSerializer extends XmlBeanSerializerBase {
    private static final long serialVersionUID = 1;
    protected final NameTransformer _nameTransformer;

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    protected BeanSerializerBase asArraySerializer() {
        return this;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public boolean isUnwrappingSerializer() {
        return true;
    }

    public UnwrappingXmlBeanSerializer(XmlBeanSerializerBase xmlBeanSerializerBase, NameTransformer nameTransformer) {
        super(xmlBeanSerializerBase, nameTransformer);
        this._nameTransformer = nameTransformer;
    }

    public UnwrappingXmlBeanSerializer(UnwrappingXmlBeanSerializer unwrappingXmlBeanSerializer, ObjectIdWriter objectIdWriter) {
        super(unwrappingXmlBeanSerializer, objectIdWriter);
        this._nameTransformer = unwrappingXmlBeanSerializer._nameTransformer;
    }

    public UnwrappingXmlBeanSerializer(UnwrappingXmlBeanSerializer unwrappingXmlBeanSerializer, ObjectIdWriter objectIdWriter, Object obj) {
        super(unwrappingXmlBeanSerializer, objectIdWriter, obj);
        this._nameTransformer = unwrappingXmlBeanSerializer._nameTransformer;
    }

    protected UnwrappingXmlBeanSerializer(UnwrappingXmlBeanSerializer unwrappingXmlBeanSerializer, Set<String> set, Set<String> set2) {
        super(unwrappingXmlBeanSerializer, set, set2);
        this._nameTransformer = unwrappingXmlBeanSerializer._nameTransformer;
    }

    protected UnwrappingXmlBeanSerializer(UnwrappingXmlBeanSerializer unwrappingXmlBeanSerializer, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2) {
        super(unwrappingXmlBeanSerializer, beanPropertyWriterArr, beanPropertyWriterArr2);
        this._nameTransformer = unwrappingXmlBeanSerializer._nameTransformer;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public JsonSerializer<Object> unwrappingSerializer(NameTransformer nameTransformer) {
        return new UnwrappingXmlBeanSerializer(this, nameTransformer);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public BeanSerializerBase withObjectIdWriter(ObjectIdWriter objectIdWriter) {
        return new UnwrappingXmlBeanSerializer(this, objectIdWriter);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase, com.fasterxml.jackson.databind.JsonSerializer
    public BeanSerializerBase withFilterId(Object obj) {
        return new UnwrappingXmlBeanSerializer(this, this._objectIdWriter, obj);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    protected BeanSerializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
        return new UnwrappingXmlBeanSerializer(this, set, set2);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    protected BeanSerializerBase withProperties(BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2) {
        return new UnwrappingXmlBeanSerializer(this, beanPropertyWriterArr, beanPropertyWriterArr2);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase, com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (this._objectIdWriter != null) {
            _serializeWithObjectId(obj, jsonGenerator, serializerProvider, false);
        } else if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
        } else {
            serializeFields(obj, jsonGenerator, serializerProvider);
        }
    }

    public String toString() {
        return "UnwrappingXmlBeanSerializer for " + handledType().getName();
    }
}
