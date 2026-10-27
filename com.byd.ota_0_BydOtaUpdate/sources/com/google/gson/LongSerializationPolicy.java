package com.google.gson;

import c.b.a.k;
import c.b.a.n;

/* JADX INFO: loaded from: classes.dex */
public enum LongSerializationPolicy {
    DEFAULT { // from class: com.google.gson.LongSerializationPolicy.1
        @Override // com.google.gson.LongSerializationPolicy
        public k serialize(Long l) {
            return new n(l);
        }
    },
    STRING { // from class: com.google.gson.LongSerializationPolicy.2
        @Override // com.google.gson.LongSerializationPolicy
        public k serialize(Long l) {
            return new n(String.valueOf(l));
        }
    };

    public abstract k serialize(Long l);
}
