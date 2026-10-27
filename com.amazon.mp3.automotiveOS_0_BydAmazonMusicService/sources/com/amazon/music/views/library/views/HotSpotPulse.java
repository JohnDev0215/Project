package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appcompat.content.res.AppCompatResources;
import com.amazon.music.views.library.R$drawable;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HotSpotPulse.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012J\u0017\u0010\u0013\u001a\u00020\u000f2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\u000fH\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/amazon/music/views/library/views/HotSpotPulse;", "Landroid/widget/ImageView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "isPulsing", "", "pulsingDrawable", "Landroid/graphics/drawable/AnimationDrawable;", "setIsPulsing", "", "setPulseClickListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/view/View$OnClickListener;", "setStyle", "size", "(Ljava/lang/Integer;)V", "updateIcon", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HotSpotPulse extends ImageView {
    private boolean isPulsing;
    private AnimationDrawable pulsingDrawable;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public HotSpotPulse(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ HotSpotPulse(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public HotSpotPulse(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final void setStyle(Integer size) {
        if (size != null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(size.intValue(), size.intValue());
            layoutParams.gravity = 17;
            setLayoutParams(layoutParams);
        }
        updateIcon();
    }

    public final void setIsPulsing(boolean isPulsing) {
        this.isPulsing = isPulsing;
        updateIcon();
    }

    private final void updateIcon() {
        if (this.isPulsing && this.pulsingDrawable == null) {
            this.pulsingDrawable = (AnimationDrawable) AppCompatResources.getDrawable(getContext(), R$drawable.hotspot_pulsing);
        }
        AnimationDrawable animationDrawable = this.pulsingDrawable;
        if (animationDrawable == null) {
            return;
        }
        if (this.isPulsing) {
            animationDrawable.start();
            setImageDrawable(this.pulsingDrawable);
        } else {
            animationDrawable.stop();
            setVisibility(8);
            setImageDrawable(null);
        }
    }

    public final void setPulseClickListener(View.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        setOnClickListener(listener);
    }
}
