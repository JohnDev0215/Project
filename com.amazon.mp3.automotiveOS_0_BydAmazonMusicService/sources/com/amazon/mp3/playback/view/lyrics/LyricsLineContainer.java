package com.amazon.mp3.playback.view.lyrics;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsLineContainer.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u000e\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0002R\u0016\u0010\t\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/playback/view/lyrics/LyricsLineContainer;", "Landroid/widget/FrameLayout;", "", "updateAlpha", "", "alpha", "setMaskAlpha", "setViewAlpha", "resetAlpha", "mMaskAlpha", "F", "mViewAlpha", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attributeSet", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LyricsLineContainer extends FrameLayout {
    private float mMaskAlpha;
    private float mViewAlpha;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LyricsLineContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        this.mViewAlpha = 1.0f;
    }

    public final void setMaskAlpha(float alpha) {
        if (alpha < 0.0f || alpha > 1.0f) {
            return;
        }
        this.mMaskAlpha = alpha;
        updateAlpha();
    }

    public final void setViewAlpha(float alpha) {
        if (alpha < 0.0f || alpha > 1.0f) {
            return;
        }
        this.mViewAlpha = alpha;
        updateAlpha();
    }

    public final void resetAlpha() {
        this.mMaskAlpha = 0.0f;
        this.mViewAlpha = 1.0f;
    }

    private final void updateAlpha() {
        View childAt = getChildAt(0);
        if (childAt == null) {
            return;
        }
        childAt.setAlpha(this.mViewAlpha * this.mMaskAlpha);
    }
}
