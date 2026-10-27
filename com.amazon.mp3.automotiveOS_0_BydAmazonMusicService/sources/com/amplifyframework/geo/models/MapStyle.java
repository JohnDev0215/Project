package com.amplifyframework.geo.models;

import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class MapStyle {
    private final String mapName;
    private final String style;

    public MapStyle(@NonNull String str, @NonNull String str2) {
        Objects.requireNonNull(str);
        this.mapName = str;
        Objects.requireNonNull(str2);
        this.style = str2;
    }

    @NonNull
    public String getMapName() {
        return this.mapName;
    }

    @NonNull
    public String getStyle() {
        return this.style;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MapStyle.class != obj.getClass()) {
            return false;
        }
        MapStyle mapStyle = (MapStyle) obj;
        return ObjectsCompat.equals(this.mapName, mapStyle.mapName) && ObjectsCompat.equals(this.style, mapStyle.style);
    }

    public int hashCode() {
        return ObjectsCompat.hash(this.mapName, this.style);
    }

    public String toString() {
        return "MapStyle{mapName='" + this.mapName + "', style='" + this.style + "'}";
    }
}
