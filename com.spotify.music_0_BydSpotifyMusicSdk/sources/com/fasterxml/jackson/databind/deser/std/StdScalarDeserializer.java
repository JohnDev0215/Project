package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.util.AccessPattern;
import p.no2;
import p.q71;

/* JADX INFO: loaded from: classes.dex */
public abstract class StdScalarDeserializer<T> extends StdDeserializer<T> {
    private static final long serialVersionUID = 1;

    @Override // p.h71
    public Object deserialize(q71 q71Var, DeserializationContext deserializationContext, Object obj) {
        deserializationContext.l(this);
        return deserialize(q71Var, deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, p.h71
    public Object deserializeWithType(q71 q71Var, DeserializationContext deserializationContext, no2 no2Var) {
        return no2Var.b(q71Var, deserializationContext);
    }

    @Override // p.h71
    public final AccessPattern getEmptyAccessPattern() {
        return AccessPattern.e;
    }

    @Override // p.h71
    public AccessPattern getNullAccessPattern() {
        return AccessPattern.c;
    }

    @Override // p.h71
    public final Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return Boolean.FALSE;
    }
}
