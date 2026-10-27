package com.amazon.mp3.view.player;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.view.TrackRatingListener;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayerRatingViewManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\nJ\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0018\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0012H\u0002J\u000e\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\nJ*\u0010\u001c\u001a\u00020\u000f2\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020!J\u0006\u0010#\u001a\u00020\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/mp3/view/player/PlayerRatingViewManager;", "", "playbackController", "Lcom/amazon/music/media/playback/PlaybackController;", "(Lcom/amazon/music/media/playback/PlaybackController;)V", "mThumbsDownListener", "Landroid/view/View$OnClickListener;", "mThumbsUpListener", "mTrackRatingListeners", "Ljava/util/ArrayList;", "Lcom/amazon/mp3/view/TrackRatingListener;", "thumbsDownButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "thumbsUpButton", "addThumbsListener", "", "trackRatingListener", "isXSmallScreenPortrait", "", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "context", "Landroid/content/Context;", "onRatingButtonClick", "view", "Landroid/view/View;", "thumbsUp", "removeThumbsListener", "setRatingButtons", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "shouldUpdateButtonInit", "enabledStateManager", "Lcom/amazon/mp3/view/player/PlayerViewEnabledStateManager;", "updateEnabledState", "updateThumbState", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlayerRatingViewManager {
    private final View.OnClickListener mThumbsDownListener;
    private final View.OnClickListener mThumbsUpListener;
    private final ArrayList<TrackRatingListener> mTrackRatingListeners;
    private final PlaybackController playbackController;
    private BaseButton thumbsDownButton;
    private BaseButton thumbsUpButton;

    public PlayerRatingViewManager(PlaybackController playbackController) {
        Intrinsics.checkNotNullParameter(playbackController, "playbackController");
        this.playbackController = playbackController;
        this.mTrackRatingListeners = new ArrayList<>();
        this.mThumbsUpListener = new View.OnClickListener() { // from class: com.amazon.mp3.view.player.PlayerRatingViewManager$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayerRatingViewManager.m1728mThumbsUpListener$lambda0(this.f$0, view);
            }
        };
        this.mThumbsDownListener = new View.OnClickListener() { // from class: com.amazon.mp3.view.player.PlayerRatingViewManager$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayerRatingViewManager.m1727mThumbsDownListener$lambda1(this.f$0, view);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mThumbsUpListener$lambda-0, reason: not valid java name */
    public static final void m1728mThumbsUpListener$lambda0(PlayerRatingViewManager this$0, View v) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(v, "v");
        this$0.onRatingButtonClick(v, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mThumbsDownListener$lambda-1, reason: not valid java name */
    public static final void m1727mThumbsDownListener$lambda1(PlayerRatingViewManager this$0, View v) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(v, "v");
        this$0.onRatingButtonClick(v, false);
    }

    public final void setRatingButtons(final BaseButton thumbsUpButton, final BaseButton thumbsDownButton, LifecycleOwner lifecycleOwner, final Context context) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(context, "context");
        this.thumbsUpButton = thumbsUpButton;
        this.thumbsDownButton = thumbsDownButton;
        StyleSheetProvider.getStyleSheet().observe(lifecycleOwner, new Observer() { // from class: com.amazon.mp3.view.player.PlayerRatingViewManager$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                PlayerRatingViewManager.m1729setRatingButtons$lambda5(this.f$0, context, thumbsUpButton, thumbsDownButton, (StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setRatingButtons$lambda-5, reason: not valid java name */
    public static final void m1729setRatingButtons$lambda5(PlayerRatingViewManager this$0, Context context, BaseButton baseButton, BaseButton baseButton2, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullExpressionValue(styleSheet, "styleSheet");
        BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(this$0.isXSmallScreenPortrait(styleSheet, context) ? IconSizeKey.TINY : IconSizeKey.SMALL, IconStyleKey.PRIMARY);
        ButtonStyle iconStyle = styleSheet.getIconStyle(IconStyleKey.ACCENT);
        if (iconStyle != null && iconBuilder != null) {
            iconBuilder.withActiveStyle(iconStyle);
        }
        if (baseButton != null) {
            baseButton.setOnClickListener(this$0.mThumbsUpListener);
            if (iconBuilder != null) {
                Drawable drawable = baseButton.getContext().getDrawable(R.drawable.ic_action_like);
                Intrinsics.checkNotNull(drawable);
                Intrinsics.checkNotNullExpressionValue(drawable, "it.context.getDrawable(R…rawable.ic_action_like)!!");
                iconBuilder.withIcon(drawable);
            }
            if (iconBuilder != null) {
                iconBuilder.applyStyle(baseButton);
            }
            baseButton.setId(R.id.PersistentPlayerThumbsUpButton);
        }
        if (baseButton2 != null) {
            baseButton2.setOnClickListener(this$0.mThumbsDownListener);
            if (iconBuilder != null) {
                Drawable drawable2 = baseButton2.getContext().getDrawable(R.drawable.ic_action_dislike);
                Intrinsics.checkNotNull(drawable2);
                Intrinsics.checkNotNullExpressionValue(drawable2, "it.context.getDrawable(R…able.ic_action_dislike)!!");
                iconBuilder.withIcon(drawable2);
            }
            if (iconBuilder != null) {
                iconBuilder.applyStyle(baseButton2);
            }
            baseButton2.setId(R.id.PersistentPlayerThumbsDownButton);
        }
        this$0.updateThumbState();
    }

    private final boolean isXSmallScreenPortrait(StyleSheet styleSheet, Context context) {
        return ScreenUtil.isPortrait() && styleSheet.getWidthScreenSizeKey(context).compareTo(ScreenSizeKey.XSMALL) <= 0;
    }

    public final void updateEnabledState(PlayerViewEnabledStateManager enabledStateManager) {
        Intrinsics.checkNotNullParameter(enabledStateManager, "enabledStateManager");
        EnabledViewState enabledViewState = enabledStateManager.getEnabledViewState(PlayerViewAction.RATE);
        BaseButton baseButton = this.thumbsUpButton;
        if (baseButton != null) {
            enabledViewState.updateViewState(baseButton, Boolean.TRUE);
        }
        BaseButton baseButton2 = this.thumbsDownButton;
        if (baseButton2 != null) {
            enabledViewState.updateViewState(baseButton2, Boolean.TRUE);
        }
        updateThumbState();
    }

    public final boolean shouldUpdateButtonInit(PlayerViewEnabledStateManager enabledStateManager) {
        Intrinsics.checkNotNullParameter(enabledStateManager, "enabledStateManager");
        return (enabledStateManager.getEnabledViewState(PlayerViewAction.RATE) != EnabledViewState.GONE) != (this.thumbsUpButton != null);
    }

    public final void addThumbsListener(TrackRatingListener trackRatingListener) {
        Intrinsics.checkNotNullParameter(trackRatingListener, "trackRatingListener");
        this.mTrackRatingListeners.add(trackRatingListener);
    }

    public final void removeThumbsListener(TrackRatingListener trackRatingListener) {
        Intrinsics.checkNotNullParameter(trackRatingListener, "trackRatingListener");
        this.mTrackRatingListeners.remove(trackRatingListener);
    }

    public final void updateThumbState() {
        BaseButton baseButton = this.thumbsUpButton;
        if (baseButton != null && baseButton.isEnabled()) {
            BaseButton baseButton2 = this.thumbsUpButton;
            if (baseButton2 != null && baseButton2.getVisibility() == 0) {
                int currentItemRating = this.playbackController.getCurrentItemRating();
                BaseButton baseButton3 = this.thumbsUpButton;
                if (baseButton3 != null) {
                    baseButton3.setActive(1 == currentItemRating);
                }
                BaseButton baseButton4 = this.thumbsDownButton;
                if (baseButton4 == null) {
                    return;
                }
                baseButton4.setActive(2 == currentItemRating);
            }
        }
    }

    private final void onRatingButtonClick(View view, boolean thumbsUp) {
        UserInteractionAppEvent.builder(view.getResources().getResourceName(view.getId())).publish();
        if (thumbsUp) {
            this.playbackController.toggleThumbsUp();
        } else {
            this.playbackController.toggleThumbsDown();
        }
        updateThumbState();
        int currentItemRating = this.playbackController.getCurrentItemRating();
        if (1 == currentItemRating) {
            Iterator<TrackRatingListener> it = this.mTrackRatingListeners.iterator();
            while (it.hasNext()) {
                it.next().onThumbsUp();
            }
        }
        if (2 == currentItemRating) {
            Iterator<TrackRatingListener> it2 = this.mTrackRatingListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onThumbsDown();
            }
        }
    }
}
