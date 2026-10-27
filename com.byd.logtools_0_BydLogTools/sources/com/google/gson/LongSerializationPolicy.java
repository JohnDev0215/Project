package com.google.gson;

/* JADX INFO: loaded from: classes.dex */
public enum LongSerializationPolicy {
    DEFAULT { // from class: com.google.gson.LongSerializationPolicy.1
        @Override // com.google.gson.LongSerializationPolicy
        public i serialize(Long l) {
            return l == null ? j.f4149a : new l(l);
        }
    },
    STRING { // from class: com.google.gson.LongSerializationPolicy.2
        @Override // com.google.gson.LongSerializationPolicy
        public i serialize(Long l) {
            return l == null ? j.f4149a : new l(l.toString());
        }
    };

    public abstract i serialize(Long l);
}
