package com.fasterxml.jackson.module.jaxb;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.StdConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/* JADX INFO: loaded from: classes6.dex */
public class AdapterConverter extends StdConverter<Object, Object> {
    protected final XmlAdapter<Object, Object> _adapter;
    protected final boolean _forSerialization;
    protected final JavaType _inputType;
    protected final JavaType _targetType;

    public AdapterConverter(XmlAdapter<?, ?> xmlAdapter, JavaType javaType, JavaType javaType2, boolean z) {
        this._adapter = xmlAdapter;
        this._inputType = javaType;
        this._targetType = javaType2;
        this._forSerialization = z;
    }

    @Override // com.fasterxml.jackson.databind.util.StdConverter, com.fasterxml.jackson.databind.util.Converter
    public Object convert(Object obj) {
        try {
            if (this._forSerialization) {
                return this._adapter.marshal(obj);
            }
            return this._adapter.unmarshal(obj);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    @Override // com.fasterxml.jackson.databind.util.StdConverter, com.fasterxml.jackson.databind.util.Converter
    public JavaType getInputType(TypeFactory typeFactory) {
        return this._inputType;
    }

    @Override // com.fasterxml.jackson.databind.util.StdConverter, com.fasterxml.jackson.databind.util.Converter
    public JavaType getOutputType(TypeFactory typeFactory) {
        return this._targetType;
    }
}
