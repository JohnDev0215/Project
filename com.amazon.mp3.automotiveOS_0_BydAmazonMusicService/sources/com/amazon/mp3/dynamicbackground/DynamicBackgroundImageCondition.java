package com.amazon.mp3.dynamicbackground;

import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.BlurStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.GradientStyleKey;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: DynamicBackgroundEffectUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b\u001d\u0010\u001eJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001f"}, d2 = {"Lcom/amazon/mp3/dynamicbackground/DynamicBackgroundImageCondition;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/views/library/styles/keys/BlurStyleKey;", "blurStyleKey", "Lcom/amazon/music/views/library/styles/keys/BlurStyleKey;", "getBlurStyleKey", "()Lcom/amazon/music/views/library/styles/keys/BlurStyleKey;", "Lcom/amazon/music/views/library/styles/keys/ColorKey;", "colorOverlayColorKey", "Lcom/amazon/music/views/library/styles/keys/ColorKey;", "getColorOverlayColorKey", "()Lcom/amazon/music/views/library/styles/keys/ColorKey;", "Lcom/amazon/music/views/library/styles/keys/AlphaKey;", "colorOverlayAlphaKey", "Lcom/amazon/music/views/library/styles/keys/AlphaKey;", "getColorOverlayAlphaKey", "()Lcom/amazon/music/views/library/styles/keys/AlphaKey;", "Lcom/amazon/music/views/library/styles/keys/GradientStyleKey;", "gradientStyleKey", "Lcom/amazon/music/views/library/styles/keys/GradientStyleKey;", "getGradientStyleKey", "()Lcom/amazon/music/views/library/styles/keys/GradientStyleKey;", "<init>", "(Lcom/amazon/music/views/library/styles/keys/BlurStyleKey;Lcom/amazon/music/views/library/styles/keys/ColorKey;Lcom/amazon/music/views/library/styles/keys/AlphaKey;Lcom/amazon/music/views/library/styles/keys/GradientStyleKey;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class DynamicBackgroundImageCondition {
    private final BlurStyleKey blurStyleKey;
    private final AlphaKey colorOverlayAlphaKey;
    private final ColorKey colorOverlayColorKey;
    private final GradientStyleKey gradientStyleKey;

    public DynamicBackgroundImageCondition() {
        this(null, null, null, null, 15, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DynamicBackgroundImageCondition)) {
            return false;
        }
        DynamicBackgroundImageCondition dynamicBackgroundImageCondition = (DynamicBackgroundImageCondition) other;
        return this.blurStyleKey == dynamicBackgroundImageCondition.blurStyleKey && this.colorOverlayColorKey == dynamicBackgroundImageCondition.colorOverlayColorKey && this.colorOverlayAlphaKey == dynamicBackgroundImageCondition.colorOverlayAlphaKey && this.gradientStyleKey == dynamicBackgroundImageCondition.gradientStyleKey;
    }

    public int hashCode() {
        BlurStyleKey blurStyleKey = this.blurStyleKey;
        int iHashCode = (blurStyleKey == null ? 0 : blurStyleKey.hashCode()) * 31;
        ColorKey colorKey = this.colorOverlayColorKey;
        int iHashCode2 = (iHashCode + (colorKey == null ? 0 : colorKey.hashCode())) * 31;
        AlphaKey alphaKey = this.colorOverlayAlphaKey;
        int iHashCode3 = (iHashCode2 + (alphaKey == null ? 0 : alphaKey.hashCode())) * 31;
        GradientStyleKey gradientStyleKey = this.gradientStyleKey;
        return iHashCode3 + (gradientStyleKey != null ? gradientStyleKey.hashCode() : 0);
    }

    public String toString() {
        return "DynamicBackgroundImageCondition(blurStyleKey=" + this.blurStyleKey + ", colorOverlayColorKey=" + this.colorOverlayColorKey + ", colorOverlayAlphaKey=" + this.colorOverlayAlphaKey + ", gradientStyleKey=" + this.gradientStyleKey + ')';
    }

    public DynamicBackgroundImageCondition(BlurStyleKey blurStyleKey, ColorKey colorKey, AlphaKey alphaKey, GradientStyleKey gradientStyleKey) {
        this.blurStyleKey = blurStyleKey;
        this.colorOverlayColorKey = colorKey;
        this.colorOverlayAlphaKey = alphaKey;
        this.gradientStyleKey = gradientStyleKey;
    }

    public /* synthetic */ DynamicBackgroundImageCondition(BlurStyleKey blurStyleKey, ColorKey colorKey, AlphaKey alphaKey, GradientStyleKey gradientStyleKey, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? BlurStyleKey.BLUR_2 : blurStyleKey, (i & 2) != 0 ? ColorKey.COLOR_SECONDARY : colorKey, (i & 4) != 0 ? AlphaKey.GLASS_4 : alphaKey, (i & 8) != 0 ? GradientStyleKey.GRADIENT_1 : gradientStyleKey);
    }

    public final BlurStyleKey getBlurStyleKey() {
        return this.blurStyleKey;
    }

    public final ColorKey getColorOverlayColorKey() {
        return this.colorOverlayColorKey;
    }

    public final AlphaKey getColorOverlayAlphaKey() {
        return this.colorOverlayAlphaKey;
    }

    public final GradientStyleKey getGradientStyleKey() {
        return this.gradientStyleKey;
    }
}
