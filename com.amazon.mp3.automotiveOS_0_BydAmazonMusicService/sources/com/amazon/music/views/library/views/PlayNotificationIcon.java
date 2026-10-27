package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appcompat.content.res.AppCompatResources;
import com.amazon.music.views.library.R$drawable;
import com.amazon.ui.foundations.utils.ColorUtil;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayNotificationIcon.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B1\b\u0007\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0004¢\u0006\u0004\b\u001b\u0010\u001cJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J%\u0010\u0007\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\t\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bR\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0016\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\u0014¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/views/library/views/PlayNotificationIcon;", "Landroid/widget/ImageView;", "", "updateIcon", "", "size", "color", "setStyle", "(Ljava/lang/Integer;Ljava/lang/Integer;)V", "setSize", "(Ljava/lang/Integer;)V", "", "isPlaying", "setIsPlaying", "Landroid/graphics/drawable/AnimationDrawable;", "playingDrawable", "Landroid/graphics/drawable/AnimationDrawable;", "Landroid/graphics/drawable/Drawable;", "pausedDrawable", "Landroid/graphics/drawable/Drawable;", "Z", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "defStyleAttr", "defStyleRes", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class PlayNotificationIcon extends ImageView {
    private boolean isPlaying;
    private Drawable pausedDrawable;
    private AnimationDrawable playingDrawable;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public PlayNotificationIcon(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ PlayNotificationIcon(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public PlayNotificationIcon(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        setBackgroundTintList(ColorUtil.getColorStateList$default(ColorUtil.INSTANCE, 0, null, null, null, 14, null));
        int i3 = R$drawable.ic_playback_equalizeroff;
        this.playingDrawable = (AnimationDrawable) AppCompatResources.getDrawable(context, R$drawable.playback_playing);
        this.pausedDrawable = AppCompatResources.getDrawable(context, i3);
    }

    public static /* synthetic */ void setStyle$default(PlayNotificationIcon playNotificationIcon, Integer num, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        }
        playNotificationIcon.setStyle(num, num2);
    }

    public final void setStyle(Integer size, Integer color) {
        if (size != null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(size.intValue(), size.intValue());
            layoutParams.gravity = 17;
            setLayoutParams(layoutParams);
        }
        if (color != null) {
            setImageTintList(ColorUtil.getColorStateList$default(ColorUtil.INSTANCE, color.intValue(), null, null, null, 14, null));
        }
        updateIcon();
    }

    public final void setSize(Integer size) {
        if (size != null) {
            getLayoutParams().height = size.intValue();
            getLayoutParams().width = size.intValue();
        }
        updateIcon();
    }

    public final void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
        updateIcon();
    }

    private final void updateIcon() {
        AnimationDrawable animationDrawable = this.playingDrawable;
        if (animationDrawable != null) {
            if (this.isPlaying) {
                animationDrawable.start();
                setImageDrawable(animationDrawable);
            } else {
                animationDrawable.stop();
                setImageDrawable(this.pausedDrawable);
            }
        }
    }
}
