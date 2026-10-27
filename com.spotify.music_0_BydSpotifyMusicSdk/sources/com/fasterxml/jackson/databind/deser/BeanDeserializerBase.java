package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import p.ts2;

/* JADX INFO: loaded from: classes.dex */
public abstract class BeanDeserializerBase extends StdDeserializer<Object> {
    private static final long serialVersionUID = 1;

    static {
        new PropertyName("#temporary-name", null);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer
    public abstract ts2 getValueInstantiator();

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer
    public abstract JavaType getValueType();
}
