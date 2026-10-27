package com.google.gson;

import c.b.b.i;
import c.b.b.l;

/* JADX INFO: loaded from: classes.dex */
public enum LongSerializationPolicy {
    DEFAULT { // from class: com.google.gson.LongSerializationPolicy.1
        @Override // com.google.gson.LongSerializationPolicy
        public i serialize(Long l) {
            return new l(l);
        }
    },
    STRING { // from class: com.google.gson.LongSerializationPolicy.2
        @Override // com.google.gson.LongSerializationPolicy
        public i serialize(Long l) {
            return new l(String.valueOf(l));
        }
    };

    public abstract i serialize(Long l);
}
