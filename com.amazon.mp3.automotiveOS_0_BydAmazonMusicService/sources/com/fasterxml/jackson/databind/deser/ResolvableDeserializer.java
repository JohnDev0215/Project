package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;

/* JADX INFO: loaded from: classes6.dex */
public interface ResolvableDeserializer {
    void resolve(DeserializationContext deserializationContext) throws JsonMappingException;
}
