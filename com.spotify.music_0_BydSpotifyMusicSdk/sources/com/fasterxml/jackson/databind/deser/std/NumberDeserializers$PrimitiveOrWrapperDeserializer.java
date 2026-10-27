package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import p.vs;

/* JADX INFO: loaded from: classes.dex */
public abstract class NumberDeserializers$PrimitiveOrWrapperDeserializer<T> extends StdScalarDeserializer<T> {
    private static final long serialVersionUID = 1;
    protected final T _emptyValue;
    protected final LogicalType _logicalType;
    protected final T _nullValue;
    protected final boolean _primitive;

    /* JADX WARN: Multi-variable type inference failed */
    public NumberDeserializers$PrimitiveOrWrapperDeserializer(Class cls, LogicalType logicalType, Object obj, Object obj2) {
        super(cls);
        this._logicalType = logicalType;
        this._nullValue = obj;
        this._emptyValue = obj2;
        this._primitive = cls.isPrimitive();
    }

    @Override // p.h71
    public final Object getEmptyValue(DeserializationContext deserializationContext) {
        return this._emptyValue;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, p.h71
    public final AccessPattern getNullAccessPattern() {
        return this._primitive ? AccessPattern.k : this._nullValue == null ? AccessPattern.c : AccessPattern.e;
    }

    @Override // p.h71
    public final Object getNullValue(DeserializationContext deserializationContext) {
        if (!this._primitive || !deserializationContext.v(DeserializationFeature.l)) {
            return this._nullValue;
        }
        deserializationContext.A(this, "Cannot map `null` into type %s (set DeserializationConfig.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)", vs.a(handledType()));
        throw null;
    }

    @Override // p.h71
    public final LogicalType logicalType() {
        return this._logicalType;
    }
}
