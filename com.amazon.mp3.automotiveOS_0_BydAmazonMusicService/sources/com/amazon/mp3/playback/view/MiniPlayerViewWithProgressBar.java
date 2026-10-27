package com.amazon.mp3.playback.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MiniPlayerViewWithProgressBar.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0014\u0010\u0015J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002R\u001b\u0010\n\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR\u001b\u0010\u000f\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\u0007\u001a\u0004\b\r\u0010\u000e¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/playback/view/MiniPlayerViewWithProgressBar;", "Landroid/widget/LinearLayout;", "", "isStage", "isLyricsSharingEnabled", "Lcom/amazon/mp3/playback/view/MiniPlayerView;", "miniPlayerView$delegate", "Lkotlin/Lazy;", "getMiniPlayerView", "()Lcom/amazon/mp3/playback/view/MiniPlayerView;", "miniPlayerView", "Landroid/widget/ProgressBar;", "miniPlayerProgressBar$delegate", "getMiniPlayerProgressBar", "()Landroid/widget/ProgressBar;", "miniPlayerProgressBar", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attributeSet", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class MiniPlayerViewWithProgressBar extends LinearLayout {

    /* JADX INFO: renamed from: miniPlayerProgressBar$delegate, reason: from kotlin metadata */
    private final Lazy miniPlayerProgressBar;

    /* JADX INFO: renamed from: miniPlayerView$delegate, reason: from kotlin metadata */
    private final Lazy miniPlayerView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiniPlayerViewWithProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        this.miniPlayerView = LazyKt__LazyJVMKt.lazy(new Function0<MiniPlayerView>() { // from class: com.amazon.mp3.playback.view.MiniPlayerViewWithProgressBar$miniPlayerView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MiniPlayerView invoke() {
                return (MiniPlayerView) this.this$0.findViewById(R.id.mini_player_container);
            }
        });
        this.miniPlayerProgressBar = LazyKt__LazyJVMKt.lazy(new Function0<ProgressBar>() { // from class: com.amazon.mp3.playback.view.MiniPlayerViewWithProgressBar$miniPlayerProgressBar$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ProgressBar invoke() {
                return (ProgressBar) this.this$0.findViewById(R.id.mini_player_progress_bar);
            }
        });
        setOrientation(1);
        LayoutInflater.from(getContext()).inflate(R.layout.mini_player_view_with_progress_bar, (ViewGroup) this, true);
        if (isStage() && isLyricsSharingEnabled() && (getContext() instanceof NowPlayingFragmentActivity)) {
            getMiniPlayerProgressBar().setVisibility(8);
        } else {
            getMiniPlayerView().setProgressBar(getMiniPlayerProgressBar());
        }
    }

    public final MiniPlayerView getMiniPlayerView() {
        Object value = this.miniPlayerView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-miniPlayerView>(...)");
        return (MiniPlayerView) value;
    }

    private final ProgressBar getMiniPlayerProgressBar() {
        Object value = this.miniPlayerProgressBar.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-miniPlayerProgressBar>(...)");
        return (ProgressBar) value;
    }

    private final boolean isStage() {
        return AmazonApplication.getCapabilities().isYAImmerseEnabled();
    }

    private final boolean isLyricsSharingEnabled() {
        return AmazonApplication.getCapabilities().isLyricsSharingEnabled();
    }
}
