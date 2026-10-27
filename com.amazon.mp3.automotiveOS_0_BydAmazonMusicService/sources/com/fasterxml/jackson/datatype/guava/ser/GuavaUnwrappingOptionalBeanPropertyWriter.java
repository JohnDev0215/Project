package com.fasterxml.jackson.datatype.guava.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanPropertyWriter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.google.common.base.Optional;

/* JADX INFO: loaded from: classes6.dex */
public class GuavaUnwrappingOptionalBeanPropertyWriter extends UnwrappingBeanPropertyWriter {
    private static final long serialVersionUID = 1;

    public GuavaUnwrappingOptionalBeanPropertyWriter(BeanPropertyWriter beanPropertyWriter, NameTransformer nameTransformer) {
        super(beanPropertyWriter, nameTransformer);
    }

    protected GuavaUnwrappingOptionalBeanPropertyWriter(UnwrappingBeanPropertyWriter unwrappingBeanPropertyWriter, NameTransformer nameTransformer, SerializedString serializedString) {
        super(unwrappingBeanPropertyWriter, nameTransformer, serializedString);
    }

    @Override // com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanPropertyWriter
    protected UnwrappingBeanPropertyWriter _new(NameTransformer nameTransformer, SerializedString serializedString) {
        return new GuavaUnwrappingOptionalBeanPropertyWriter(this, nameTransformer, serializedString);
    }

    @Override // com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanPropertyWriter, com.fasterxml.jackson.databind.ser.BeanPropertyWriter, com.fasterxml.jackson.databind.ser.PropertyWriter
    public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        Object obj2;
        if (this._nullSerializer == null && ((obj2 = get(obj)) == null || Optional.absent().equals(obj2))) {
            return;
        }
        super.serializeAsField(obj, jsonGenerator, serializerProvider);
    }
}
