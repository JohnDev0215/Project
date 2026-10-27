package com.amplifyframework.geo.models;

import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class MapStyleDescriptor {
    private final String json;

    public MapStyleDescriptor(@NonNull String str) {
        Objects.requireNonNull(str);
        this.json = str;
    }

    @NonNull
    public String getJson() {
        return this.json;
    }

    public String toString() {
        return "MapStyleDescriptor{json='" + this.json + "'}";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MapStyleDescriptor.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(this.json, ((MapStyleDescriptor) obj).json);
    }

    public int hashCode() {
        return ObjectsCompat.hash(this.json);
    }
}
