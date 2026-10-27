package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import p.no2;
import p.q71;

/* JADX INFO: loaded from: classes.dex */
public class NullifyingDeserializer extends StdDeserializer<Object> {
    public static final NullifyingDeserializer c = new NullifyingDeserializer();
    private static final long serialVersionUID = 1;

    public NullifyingDeserializer() {
        super(Object.class);
    }

    @Override // p.h71
    public final Object deserialize(q71 q71Var, DeserializationContext deserializationContext) {
        if (!q71Var.S0(JsonToken.m)) {
            q71Var.X0();
            return null;
        }
        while (true) {
            JsonToken jsonTokenV0 = q71Var.V0();
            if (jsonTokenV0 == null || jsonTokenV0 == JsonToken.e) {
                return null;
            }
            q71Var.X0();
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, p.h71
    public final Object deserializeWithType(q71 q71Var, DeserializationContext deserializationContext, no2 no2Var) {
        int iC = q71Var.c();
        if (iC == 1 || iC == 3 || iC == 5) {
            return no2Var.a(q71Var, deserializationContext);
        }
        return null;
    }

    @Override // p.h71
    public final Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return Boolean.FALSE;
    }
}
