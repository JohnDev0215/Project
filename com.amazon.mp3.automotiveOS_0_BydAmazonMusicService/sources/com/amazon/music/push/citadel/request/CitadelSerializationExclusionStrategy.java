package com.amazon.music.push.citadel.request;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/* JADX INFO: loaded from: classes4.dex */
public class CitadelSerializationExclusionStrategy implements ExclusionStrategy {
    @Override // com.google.gson.ExclusionStrategy
    public boolean shouldSkipClass(Class<?> cls) {
        return false;
    }

    @Override // com.google.gson.ExclusionStrategy
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return fieldAttributes.getDeclaringClass().equals(CitadelRequest.class);
    }
}
