package com.google.gson;

import k6.i;
import k6.l;

/* JADX INFO: loaded from: classes2.dex */
public enum LongSerializationPolicy {
    DEFAULT { // from class: com.google.gson.LongSerializationPolicy.1
        @Override // com.google.gson.LongSerializationPolicy
        public i serialize(Long l8) {
            return new l(l8);
        }
    },
    STRING { // from class: com.google.gson.LongSerializationPolicy.2
        @Override // com.google.gson.LongSerializationPolicy
        public i serialize(Long l8) {
            return new l(String.valueOf(l8));
        }
    };

    public abstract i serialize(Long l8);
}
