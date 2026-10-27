package com.amazon.avod.playbackclient.subtitle.views.util;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.method.TransformationMethod;
import com.amazon.avod.playbackclient.subtitle.views.util.EdgeRenderer;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitleRenderPreset {
    private final int mBackgroundArgbColor;
    private final Drawable mIcon;
    private final String mPresetName;
    private final int mTextArgbColor;
    private final EdgeRenderer.EdgeType mTextEdgeType;
    private final float mTextFontScale;
    private final TransformationMethod mTextTransformationMethod;
    private final Typeface mTextType;
    private final int mWindowArgbColor;

    public SubtitleRenderPreset(@Nonnull String str, @Nullable Drawable drawable, @Nonnull Typeface typeface, @Nonnull TransformationMethod transformationMethod, @Nonnull EdgeRenderer.EdgeType edgeType, float f, int i, int i2, int i3) {
        this.mPresetName = (String) Preconditions.checkNotNull(str, "presetName");
        this.mIcon = drawable;
        this.mTextType = (Typeface) Preconditions.checkNotNull(typeface, "Typeface");
        this.mTextTransformationMethod = (TransformationMethod) Preconditions.checkNotNull(transformationMethod, "TransformationMethod");
        this.mTextEdgeType = (EdgeRenderer.EdgeType) Preconditions.checkNotNull(edgeType, "EdgeType");
        this.mTextFontScale = checkTextFontScale(f);
        this.mTextArgbColor = i;
        this.mBackgroundArgbColor = i2;
        this.mWindowArgbColor = i3;
    }

    private static float checkTextFontScale(float f) {
        Preconditions.checkArgument(f > 0.0f, "Font scale is a multiplier and must be positive");
        return f;
    }

    @Nonnull
    public Typeface getTextTypeface() {
        return this.mTextType;
    }

    @Nonnull
    public TransformationMethod getTextTransformationMethod() {
        return this.mTextTransformationMethod;
    }

    @Nonnull
    public EdgeRenderer.EdgeType getTextEdgeType() {
        return this.mTextEdgeType;
    }

    @Nonnegative
    public float getTextFontScale() {
        return this.mTextFontScale;
    }

    public int getTextArgbColor() {
        return this.mTextArgbColor;
    }

    public int getBackgroundArgbColor() {
        return this.mBackgroundArgbColor;
    }

    public int getWindowArgbColor() {
        return this.mWindowArgbColor;
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass().getSimpleName()).add("mPresetName", this.mPresetName).toString();
    }
}
