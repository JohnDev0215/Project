package com.amazon.mp3.providers;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.datasource.LikesAndFollowsDBOperations;
import com.amazon.mp3.social.MusicRelationshipProvider;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.ButtonModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.FollowProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: DefaultFollowProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J \u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/providers/DefaultFollowProvider;", "Lcom/amazon/music/views/library/providers/FollowProvider;", "context", "Landroid/content/Context;", "musicRelationshipProvider", "Lcom/amazon/mp3/social/MusicRelationshipProvider;", "(Landroid/content/Context;Lcom/amazon/mp3/social/MusicRelationshipProvider;)V", "TAG", "", "kotlin.jvm.PlatformType", "follow", "", "model", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "shouldFollow", "", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "unfollow", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultFollowProvider implements FollowProvider {
    private final String TAG;
    private final Context context;
    private final MusicRelationshipProvider musicRelationshipProvider;

    public DefaultFollowProvider(Context context, MusicRelationshipProvider musicRelationshipProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(musicRelationshipProvider, "musicRelationshipProvider");
        this.context = context;
        this.musicRelationshipProvider = musicRelationshipProvider;
        this.TAG = DefaultPlaybackProvider.class.getSimpleName();
    }

    @Override // com.amazon.music.views.library.providers.FollowProvider
    public void follow(BaseViewModel model) {
        ContentMetadata metadata;
        Intrinsics.checkNotNullParameter(model, "model");
        if (!(model instanceof ButtonModel) || (metadata = ((ButtonModel) model).getMetadata()) == null) {
            return;
        }
        follow(true, model, metadata);
    }

    @Override // com.amazon.music.views.library.providers.FollowProvider
    public void unfollow(BaseViewModel model) {
        ContentMetadata metadata;
        Intrinsics.checkNotNullParameter(model, "model");
        if (!(model instanceof ButtonModel) || (metadata = ((ButtonModel) model).getMetadata()) == null) {
            return;
        }
        follow(false, model, metadata);
    }

    private final void follow(final boolean shouldFollow, final BaseViewModel model, final ContentMetadata metadata) {
        String asin;
        Observable<Void> observableUnFollowArtist;
        Observable<Void> observableObserveOn;
        if (!(metadata instanceof ArtistMetadata) || (asin = ((ArtistMetadata) metadata).getAsin()) == null) {
            return;
        }
        if (shouldFollow) {
            observableUnFollowArtist = this.musicRelationshipProvider.followArtist(asin, ChildUserUtil.INSTANCE.isChildUser(this.context));
        } else {
            observableUnFollowArtist = this.musicRelationshipProvider.unFollowArtist(asin);
        }
        Observable<Void> observableSubscribeOn = observableUnFollowArtist.subscribeOn(Schedulers.io());
        if (observableSubscribeOn == null || (observableObserveOn = observableSubscribeOn.observeOn(AndroidSchedulers.mainThread())) == null) {
            return;
        }
        observableObserveOn.subscribe(new Observer<Object>() { // from class: com.amazon.mp3.providers.DefaultFollowProvider.follow.2
            @Override // rx.Observer
            public void onNext(Object p0) {
            }

            @Override // rx.Observer
            public void onCompleted() {
                BauhausToastUtils.showTextToast(this.context, shouldFollow ? R.string.dmusic_follow_artist_request_success_text : R.string.dmusic_unfollow_artist_request_success_text, 1);
                BaseViewModel baseViewModel = model;
                if (baseViewModel instanceof ButtonModel) {
                    ((ButtonModel) baseViewModel).updateActiveState(shouldFollow);
                }
                if (AmazonApplication.getCapabilities().isTrueLikesAndFollowsEnabled()) {
                    LikesAndFollowsDBOperations.INSTANCE.updateArtistFollowStatusInLocalDB(shouldFollow, (ArtistMetadata) metadata);
                }
                UIEventHandler.INSTANCE.emitUiClickMetric(shouldFollow ? ActionType.FOLLOW_ARTIST : ActionType.UNFOLLOW_ARTIST, metadata);
            }

            @Override // rx.Observer
            public void onError(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Log.error(this.TAG, Intrinsics.stringPlus("Cannot update artist follow status ", throwable.getLocalizedMessage()));
                BauhausToastUtils.showTextToast(this.context, R.string.dmusic_follow_artist_request_failed_text, 0);
            }
        });
    }
}
