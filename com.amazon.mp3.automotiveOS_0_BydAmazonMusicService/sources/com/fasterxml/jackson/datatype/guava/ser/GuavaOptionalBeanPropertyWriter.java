package com.fasterxml.jackson.datatype.guava.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.google.common.base.Optional;

/* JADX INFO: loaded from: classes6.dex */
public class GuavaOptionalBeanPropertyWriter extends BeanPropertyWriter {
    private static final long serialVersionUID = 1;

    protected GuavaOptionalBeanPropertyWriter(BeanPropertyWriter beanPropertyWriter) {
        super(beanPropertyWriter);
    }

    protected GuavaOptionalBeanPropertyWriter(BeanPropertyWriter beanPropertyWriter, PropertyName propertyName) {
        super(beanPropertyWriter, propertyName);
    }

    @Override // com.fasterxml.jackson.databind.ser.BeanPropertyWriter
    protected BeanPropertyWriter _new(PropertyName propertyName) {
        return new GuavaOptionalBeanPropertyWriter(this, propertyName);
    }

    @Override // com.fasterxml.jackson.databind.ser.BeanPropertyWriter
    public BeanPropertyWriter unwrappingWriter(NameTransformer nameTransformer) {
        return new GuavaUnwrappingOptionalBeanPropertyWriter(this, nameTransformer);
    }

    @Override // com.fasterxml.jackson.databind.ser.BeanPropertyWriter, com.fasterxml.jackson.databind.ser.PropertyWriter
    public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        Object obj2;
        if (this._nullSerializer == null && ((obj2 = get(obj)) == null || Optional.absent().equals(obj2))) {
            return;
        }
        super.serializeAsField(obj, jsonGenerator, serializerProvider);
    }
}
