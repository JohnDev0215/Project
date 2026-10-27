package com.amplifyframework.geo.models;

import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes5.dex */
public class Place {
    private final Geometry geometry;

    public Place(Geometry geometry) {
        this.geometry = geometry;
    }

    @Nullable
    public Geometry getGeometry() {
        return this.geometry;
    }

    public String toString() {
        return "Place{geometry=" + this.geometry + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(this.geometry, ((Place) obj).geometry);
    }

    public int hashCode() {
        return ObjectsCompat.hash(this.geometry);
    }
}
