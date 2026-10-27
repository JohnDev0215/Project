package com.amplifyframework.geo.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class SearchArea {
    private final Coordinates biasPosition;
    private final BoundingBox boundingBox;

    private SearchArea(BoundingBox boundingBox, Coordinates coordinates) {
        this.boundingBox = boundingBox;
        this.biasPosition = coordinates;
    }

    @NonNull
    public static SearchArea near(@NonNull Coordinates coordinates) {
        Objects.requireNonNull(coordinates);
        return new SearchArea(null, coordinates);
    }

    @NonNull
    public static SearchArea within(@NonNull BoundingBox boundingBox) {
        Objects.requireNonNull(boundingBox);
        return new SearchArea(boundingBox, null);
    }

    @Nullable
    public BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    @Nullable
    public Coordinates getBiasPosition() {
        return this.biasPosition;
    }

    public String toString() {
        return "SearchArea{boundingBox=" + this.boundingBox + ", biasPosition=" + this.biasPosition + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SearchArea.class != obj.getClass()) {
            return false;
        }
        SearchArea searchArea = (SearchArea) obj;
        return ObjectsCompat.equals(this.boundingBox, searchArea.boundingBox) && ObjectsCompat.equals(this.biasPosition, searchArea.biasPosition);
    }

    public int hashCode() {
        return ObjectsCompat.hash(this.boundingBox, this.biasPosition);
    }
}
