package com.amazon.mp3.dmsfcore.providers;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.animation.AddButtonAnimation;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.cache.likes.LikesStateCacheManager;
import com.amazon.mp3.library.fragment.AddAndDownloadTrackTask;
import com.amazon.mp3.library.fragment.OnItemFinishedAddingListener;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.music.connect.firefly.LikesService;
import com.amazon.music.media.playback.ratings.LikeState;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.skyfire.ui.providers.LibraryProvider;
import com.amazon.music.views.library.views.HorizontalTileView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DMSFLibraryProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J$\u0010\u000b\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\nH\u0002J(\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J \u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J \u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/dmsfcore/providers/DMSFLibraryProvider;", "Lcom/amazon/music/skyfire/ui/providers/LibraryProvider;", "()V", "addTrackToLibrary", "", "context", "Landroid/content/Context;", "view", "Lcom/amazon/music/views/library/views/HorizontalTileView;", "asin", "", "sendMetricEvent", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "blockRef", "setLikeState", "setToLike", "", "setLikeTrack", "setNeutralTrack", "showUpsell", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DMSFLibraryProvider implements LibraryProvider {
    @Override // com.amazon.music.skyfire.ui.providers.LibraryProvider
    public void addTrackToLibrary(Context context, HorizontalTileView view, String asin) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(asin, "asin");
        AddButtonAnimation.startAnimation(context, view.getRowButtonContainerView(), view.getRowButtonView(), view.getRowButtonClickedImageView(), null, false);
        new AddAndDownloadTrackTask(context, asin, false, true, (OnItemFinishedAddingListener) null).execute(new Void[0]);
    }

    @Override // com.amazon.music.skyfire.ui.providers.LibraryProvider
    public void setLikeTrack(Context context, HorizontalTileView view, String asin) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(asin, "asin");
        setLikeState(context, view, asin, true);
    }

    @Override // com.amazon.music.skyfire.ui.providers.LibraryProvider
    public void setNeutralTrack(Context context, HorizontalTileView view, String asin) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(asin, "asin");
        setLikeState(context, view, asin, false);
    }

    @Override // com.amazon.music.skyfire.ui.providers.LibraryProvider
    public void showUpsell(Context context, String asin) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(asin, "asin");
        UpsellUtil.showBlockingUpsell((MusicHomeActivity) context, asin, UpsellReason.PERSISTENT_UPSELL, UpsellSourceEntity.TRACK);
    }

    private final void setLikeState(final Context context, final HorizontalTileView view, final String asin, final boolean setToLike) {
        AddButtonAnimation.startAnimation(context, view.getRowButtonContainerView(), view.getRowButtonView(), view.getRowButtonClickedImageView(), new AddButtonAnimation.AnimationFinishedListener() { // from class: com.amazon.mp3.dmsfcore.providers.DMSFLibraryProvider.setLikeState.1
            @Override // com.amazon.mp3.animation.AddButtonAnimation.AnimationFinishedListener
            public void onAnimationCanceled() {
            }

            @Override // com.amazon.mp3.animation.AddButtonAnimation.AnimationFinishedListener
            public void onAnimationFinished() {
                view.restyleLikeIcons(setToLike);
                boolean z = setToLike;
                LikeState likeState = z ? LikeState.LIKE : LikeState.NEUTRAL;
                int i = z ? R.string.dmusic_like_action_toast : R.string.dmusic_un_like_action_toast;
                ActionType actionType = z ? ActionType.THUMBS_UP : ActionType.THUMBS_UP_UNDO;
                Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplication().applicationContext");
                LikesService likesService = new LikesServiceProxy(applicationContext).get();
                if (likesService != null) {
                    likesService.setLikeState(asin, likeState);
                }
                LikesStateCacheManager companion = LikesStateCacheManager.INSTANCE.getInstance(context);
                if (companion != null) {
                    companion.add(asin, setToLike);
                }
                BauhausToastUtils.showTextToast(context, i, 0);
                this.sendMetricEvent(actionType, asin, String.valueOf(view.getId()));
            }
        }, false, true, R.animator.add_button_animation, R.animator.add_successful_animation_retain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendMetricEvent(ActionType actionType, String asin, String blockRef) {
        if (actionType != null) {
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).withEntityId(asin).withEntityIdType(EntityIdType.ASIN).withBlockRef(blockRef).withPageType(PageType.DETAIL_ARTIST).build());
        }
    }
}
