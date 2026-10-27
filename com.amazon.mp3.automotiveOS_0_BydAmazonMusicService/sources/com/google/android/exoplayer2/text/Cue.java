package com.google.android.exoplayer2.text;

import android.graphics.Bitmap;
import android.text.Layout;
import android.util.Pair;
import androidx.core.view.ViewCompat;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public class Cue {
    public final Bitmap bitmap;
    public final float bitmapHeight;
    public final boolean isSafeOffsetAdjustmentNeeded;
    public final boolean isWideAspectRatio;
    public final float line;
    public final int lineAnchor;
    public final int lineType;
    public final int maxWindowColumnCount;
    public final int maxWindowRowCount;
    public final float position;
    public final int positionAnchor;
    public final float size;
    public final CharSequence text;
    public final Layout.Alignment textAlignment;
    public final List<Pair<Integer, CharSequence>> textRows;
    public final int windowBorderColor;
    public final int windowBorderType;
    public final int windowColor;
    public final boolean windowColorSet;

    public Cue(Bitmap bitmap, float f, int i, float f2, int i2, float f3, float f4) {
        this(null, null, null, bitmap, f2, 0, i2, f, i, f3, f4, false, ViewCompat.MEASURED_STATE_MASK, 0, 0, 0, 0, true, false);
    }

    public Cue(CharSequence charSequence) {
        this(charSequence, null, Float.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE);
    }

    public Cue(CharSequence charSequence, Layout.Alignment alignment, float f, int i, int i2, float f2, int i3, float f3) {
        this(charSequence, alignment, f, i, i2, f2, i3, f3, false, ViewCompat.MEASURED_STATE_MASK);
    }

    public Cue(CharSequence charSequence, Layout.Alignment alignment, float f, int i, int i2, float f2, int i3, float f3, boolean z, int i4) {
        this(charSequence, null, alignment, null, f, i, i2, f2, i3, f3, Float.MIN_VALUE, z, i4, 0, 0, 0, 0, true, false);
    }

    public Cue(List<Pair<Integer, CharSequence>> list, Layout.Alignment alignment, float f, int i, int i2, float f2, int i3, float f3, boolean z, int i4, int i5, int i6, int i7, int i8, boolean z2, boolean z3) {
        this(null, list, alignment, null, f, i, i2, f2, i3, f3, Float.MIN_VALUE, z, i4, i5, i6, i7, i8, z2, z3);
    }

    public Cue(CharSequence charSequence, List<Pair<Integer, CharSequence>> list, Layout.Alignment alignment, Bitmap bitmap, float f, int i, int i2, float f2, int i3, float f3, float f4, boolean z, int i4, int i5, int i6, int i7, int i8, boolean z2, boolean z3) {
        this.text = charSequence;
        this.textAlignment = alignment;
        this.bitmap = bitmap;
        this.line = f;
        this.lineType = i;
        this.lineAnchor = i2;
        this.position = f2;
        this.positionAnchor = i3;
        this.size = f3;
        this.bitmapHeight = f4;
        this.windowColorSet = z;
        this.windowColor = i4;
        this.textRows = list;
        this.windowBorderColor = i5;
        this.windowBorderType = i6;
        this.maxWindowRowCount = i7;
        this.maxWindowColumnCount = i8;
        this.isWideAspectRatio = z2;
        this.isSafeOffsetAdjustmentNeeded = z3;
    }
}
