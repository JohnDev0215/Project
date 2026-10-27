package io.gsonfire;

import com.google.gson.JsonElement;

/* JADX INFO: loaded from: classes.dex */
public interface TypeSelector<T> {
    Class<? extends T> getClassForElement(JsonElement jsonElement);
}
