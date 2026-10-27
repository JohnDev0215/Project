package com.amplifyframework.predictions.models;

import android.graphics.PointF;
import androidx.annotation.NonNull;
import com.amplifyframework.util.Immutable;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class Polygon {
    private static final int MINIMUM_POINTS_REQUIRED = 3;
    private final List<PointF> points;

    private Polygon(List<PointF> list) {
        this.points = list;
    }

    @NonNull
    public List<PointF> getPoints() {
        return Immutable.of(this.points);
    }

    @NonNull
    public static Polygon fromPoints(@NonNull List<PointF> list) {
        Objects.requireNonNull(list);
        if (list.size() < 3) {
            throw new IllegalArgumentException("A polygon must contain at least three points.");
        }
        return new Polygon(list);
    }
}
