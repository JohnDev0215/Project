package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.LogicalType;
import p.h71;
import p.i00;
import p.no2;
import p.q71;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public class StdDelegatingDeserializer<T> extends StdDeserializer<T> {
    private static final long serialVersionUID = 1;
    protected final i00 _converter;
    protected final h71 _delegateDeserializer;
    protected final JavaType _delegateType;

    public StdDelegatingDeserializer(i00 i00Var, JavaType javaType, h71 h71Var) {
        super(javaType);
        this._delegateType = javaType;
        this._delegateDeserializer = h71Var;
    }

    @Override // p.h71
    public final Object deserialize(q71 q71Var, DeserializationContext deserializationContext) {
        Object objDeserialize = this._delegateDeserializer.deserialize(q71Var, deserializationContext);
        if (objDeserialize == null) {
            return null;
        }
        return ((v03) this._converter).o(objDeserialize);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, p.h71
    public final Object deserializeWithType(q71 q71Var, DeserializationContext deserializationContext, no2 no2Var) {
        Object objDeserialize = this._delegateDeserializer.deserialize(q71Var, deserializationContext);
        if (objDeserialize == null) {
            return null;
        }
        return ((v03) this._converter).o(objDeserialize);
    }

    @Override // p.h71
    public final h71 getDelegatee() {
        return this._delegateDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, p.h71
    public final Class handledType() {
        return this._delegateDeserializer.handledType();
    }

    @Override // p.h71
    public final LogicalType logicalType() {
        return this._delegateDeserializer.logicalType();
    }

    @Override // p.h71
    public final Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return this._delegateDeserializer.supportsUpdate(deserializationConfig);
    }

    @Override // p.h71
    public final Object deserialize(q71 q71Var, DeserializationContext deserializationContext, Object obj) {
        if (this._delegateType.f().isAssignableFrom(obj.getClass())) {
            return this._delegateDeserializer.deserialize(q71Var, deserializationContext, obj);
        }
        throw new UnsupportedOperationException(String.format("Cannot update object of type %s (using deserializer for type %s)".concat(obj.getClass().getName()), this._delegateType));
    }
}
