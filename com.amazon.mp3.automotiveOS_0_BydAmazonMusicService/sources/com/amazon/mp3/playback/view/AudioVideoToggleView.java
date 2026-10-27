package com.amazon.mp3.playback.view;

import android.animation.Animator;
import android.content.Context;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.prime.requester.HybridQueueType;
import com.amazon.mp3.util.Log;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AudioVideoToggleView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u0000 *2\u00020\u0001:\u0001*B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u001b\u001a\u00020\u001cJ\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u001eH\u0002J \u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020\u001eH\u0002J\u0006\u0010$\u001a\u00020\u001cJ\u000e\u0010%\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020\u0011J\u0018\u0010'\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u001e2\b\b\u0002\u0010#\u001a\u00020\u001eJ\u0018\u0010(\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u001e2\b\b\u0002\u0010#\u001a\u00020\u001eJ\u0006\u0010)\u001a\u00020\u001cR\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0013\u0010\u000eR\u001b\u0010\u0015\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\n\u001a\u0004\b\u0016\u0010\bR\u001b\u0010\u0018\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\n\u001a\u0004\b\u0019\u0010\u000e¨\u0006+"}, d2 = {"Lcom/amazon/mp3/playback/view/AudioVideoToggleView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "audioIcon", "Landroid/widget/ImageView;", "getAudioIcon", "()Landroid/widget/ImageView;", "audioIcon$delegate", "Lkotlin/Lazy;", "audioIconContainer", "Landroid/view/View;", "getAudioIconContainer", "()Landroid/view/View;", "audioIconContainer$delegate", "audioVideoToggleListener", "Landroid/view/View$OnClickListener;", "selectedBackground", "getSelectedBackground", "selectedBackground$delegate", "videoIcon", "getVideoIcon", "videoIcon$delegate", "videoIconContainer", "getVideoIconContainer", "videoIconContainer$delegate", "hideToggle", "", "isAudioSelected", "", "isVideoSelected", "playAnimation", "isDefault", "moveRight", "isUserInitiated", "removeAudioVideoToggleListener", "setAudioVideoToggleListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setAudioVideoToggleToAudio", "setAudioVideoToggleToVideo", "showToggle", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AudioVideoToggleView extends RelativeLayout {
    private static final String TAG = AudioVideoToggleView.class.getSimpleName();

    /* JADX INFO: renamed from: audioIcon$delegate, reason: from kotlin metadata */
    private final Lazy audioIcon;

    /* JADX INFO: renamed from: audioIconContainer$delegate, reason: from kotlin metadata */
    private final Lazy audioIconContainer;
    private View.OnClickListener audioVideoToggleListener;

    /* JADX INFO: renamed from: selectedBackground$delegate, reason: from kotlin metadata */
    private final Lazy selectedBackground;

    /* JADX INFO: renamed from: videoIcon$delegate, reason: from kotlin metadata */
    private final Lazy videoIcon;

    /* JADX INFO: renamed from: videoIconContainer$delegate, reason: from kotlin metadata */
    private final Lazy videoIconContainer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioVideoToggleView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.audioIconContainer = LazyKt__LazyJVMKt.lazy(new Function0<View>() { // from class: com.amazon.mp3.playback.view.AudioVideoToggleView$audioIconContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final View invoke() {
                return this.this$0.findViewById(R.id.audioIconContainer);
            }
        });
        this.videoIconContainer = LazyKt__LazyJVMKt.lazy(new Function0<View>() { // from class: com.amazon.mp3.playback.view.AudioVideoToggleView$videoIconContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final View invoke() {
                return this.this$0.findViewById(R.id.videoIconContainer);
            }
        });
        this.audioIcon = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.mp3.playback.view.AudioVideoToggleView$audioIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R.id.audioIcon);
            }
        });
        this.videoIcon = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.mp3.playback.view.AudioVideoToggleView$videoIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R.id.videoIcon);
            }
        });
        this.selectedBackground = LazyKt__LazyJVMKt.lazy(new Function0<View>() { // from class: com.amazon.mp3.playback.view.AudioVideoToggleView$selectedBackground$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final View invoke() {
                return this.this$0.findViewById(R.id.selected_background);
            }
        });
        View.inflate(context, R.layout.audio_video_toggle, this);
        getAudioIcon().setTag(HybridQueueType.AUDIO_QUEUE.INSTANCE);
        getVideoIcon().setTag(HybridQueueType.VIDEO_QUEUE.INSTANCE);
        getVideoIconContainer().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.AudioVideoToggleView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudioVideoToggleView.m1511_init_$lambda0(this.f$0, view);
            }
        });
        getAudioIconContainer().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.AudioVideoToggleView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudioVideoToggleView.m1512_init_$lambda1(this.f$0, view);
            }
        });
        hideToggle();
    }

    private final View getAudioIconContainer() {
        Object value = this.audioIconContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-audioIconContainer>(...)");
        return (View) value;
    }

    private final View getVideoIconContainer() {
        Object value = this.videoIconContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-videoIconContainer>(...)");
        return (View) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ImageView getAudioIcon() {
        Object value = this.audioIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-audioIcon>(...)");
        return (ImageView) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ImageView getVideoIcon() {
        Object value = this.videoIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-videoIcon>(...)");
        return (ImageView) value;
    }

    private final View getSelectedBackground() {
        Object value = this.selectedBackground.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-selectedBackground>(...)");
        return (View) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m1511_init_$lambda0(AudioVideoToggleView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setAudioVideoToggleToVideo(false, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-1, reason: not valid java name */
    public static final void m1512_init_$lambda1(AudioVideoToggleView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setAudioVideoToggleToAudio(false, true);
    }

    public final void setAudioVideoToggleListener(View.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.audioVideoToggleListener = listener;
    }

    public final void removeAudioVideoToggleListener() {
        this.audioVideoToggleListener = null;
    }

    public final void showToggle() {
        setVisibility(0);
    }

    public final void hideToggle() {
        setVisibility(8);
    }

    public static /* synthetic */ void setAudioVideoToggleToAudio$default(AudioVideoToggleView audioVideoToggleView, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = false;
        }
        audioVideoToggleView.setAudioVideoToggleToAudio(z, z2);
    }

    public final void setAudioVideoToggleToAudio(boolean isDefault, boolean isUserInitiated) {
        Log.debug(TAG, "setAudioVideoToggleToAudio " + isDefault + ' ' + isUserInitiated);
        if (isAudioSelected()) {
            return;
        }
        getVideoIconContainer().setEnabled(true);
        getAudioIconContainer().setEnabled(false);
        playAnimation(isDefault, false, isUserInitiated);
    }

    public static /* synthetic */ void setAudioVideoToggleToVideo$default(AudioVideoToggleView audioVideoToggleView, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = false;
        }
        audioVideoToggleView.setAudioVideoToggleToVideo(z, z2);
    }

    public final void setAudioVideoToggleToVideo(boolean isDefault, boolean isUserInitiated) {
        Log.debug(TAG, "setAudioVideoToggleToVideo " + isDefault + ' ' + isUserInitiated);
        if (isVideoSelected()) {
            return;
        }
        getVideoIconContainer().setEnabled(false);
        getAudioIconContainer().setEnabled(true);
        playAnimation(isDefault, true, isUserInitiated);
    }

    private final void playAnimation(boolean isDefault, boolean moveRight, final boolean isUserInitiated) {
        Log.debug(TAG, "playAnimation " + isDefault + ' ' + moveRight + ' ' + isUserInitiated);
        getSelectedBackground().clearAnimation();
        ViewPropertyAnimator viewPropertyAnimatorAnimate = getSelectedBackground().animate();
        viewPropertyAnimatorAnimate.translationX(moveRight ? getContext().getResources().getDimensionPixelSize(R.dimen.toggle_container_width) : 0.0f);
        viewPropertyAnimatorAnimate.alpha(isDefault ? 0.15f : 0.3f);
        viewPropertyAnimatorAnimate.setInterpolator(new LinearInterpolator());
        viewPropertyAnimatorAnimate.setDuration(400L);
        viewPropertyAnimatorAnimate.setListener(new Animator.AnimatorListener() { // from class: com.amazon.mp3.playback.view.AudioVideoToggleView.playAnimation.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator p0) {
                View.OnClickListener onClickListener;
                Intrinsics.checkNotNullParameter(p0, "p0");
                if (!isUserInitiated || (onClickListener = this.audioVideoToggleListener) == null) {
                    return;
                }
                onClickListener.onClick(this.isVideoSelected() ? this.getVideoIcon() : this.getAudioIcon());
            }
        }).start();
    }

    private final boolean isAudioSelected() {
        return !getAudioIconContainer().isEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isVideoSelected() {
        return !getVideoIconContainer().isEnabled();
    }
}
