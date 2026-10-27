package com.google.gson;

import b.c.a.i;
import b.c.a.l;

/* JADX INFO: loaded from: classes3.dex */
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
