package com.amazon.mp3.likes;

import android.content.Context;
import android.util.Log;
import com.amazon.music.connect.firefly.LikesService;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.ratings.LikeState;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: MediaItemLikesProviderImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u000eH\u0016J\u001a\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/likes/MediaItemLikesProviderImpl;", "Lcom/amazon/music/media/playback/MediaItem$MediaItemLikesProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "mLikesServiceProxy", "Lcom/amazon/mp3/likes/LikesServiceProxy;", "fetchAndInitializeLikeState", "", "mediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "asinList", "", "setLikeState", "tracksAsin", "likeState", "Lcom/amazon/music/media/playback/ratings/LikeState;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MediaItemLikesProviderImpl implements MediaItem.MediaItemLikesProvider {
    private final String TAG;
    private final Context context;
    private LikesServiceProxy mLikesServiceProxy;

    public MediaItemLikesProviderImpl(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.TAG = Reflection.getOrCreateKotlinClass(MediaItemLikesProviderImpl.class).getSimpleName();
        this.mLikesServiceProxy = new LikesServiceProxy(context);
    }

    @Override // com.amazon.music.media.playback.MediaItem.MediaItemLikesProvider
    public void fetchAndInitializeLikeState(MediaItem mediaItem, List<String> asinList) {
        List<LikeState> likeStates;
        Intrinsics.checkNotNullParameter(mediaItem, "mediaItem");
        Intrinsics.checkNotNullParameter(asinList, "asinList");
        LikesServiceProxy likesServiceProxy = this.mLikesServiceProxy;
        if (likesServiceProxy == null) {
            return;
        }
        try {
            LikesService likesService = likesServiceProxy.get();
            LikeState likeState = null;
            if (likesService != null && (likeStates = likesService.getLikeStates(asinList)) != null) {
                likeState = likeStates.get(0);
            }
            mediaItem.setLikeState(likeState);
        } catch (Exception e) {
            Log.d(this.TAG, Intrinsics.stringPlus("Exception encountered while attempting to retrieve LikeState. ", e));
        }
    }

    @Override // com.amazon.music.media.playback.MediaItem.MediaItemLikesProvider
    public void setLikeState(String tracksAsin, LikeState likeState) {
        Intrinsics.checkNotNullParameter(likeState, "likeState");
        if (tracksAsin != null) {
            LikesService likesService = this.mLikesServiceProxy.get();
            if (likesService == null) {
                return;
            }
            likesService.setLikeState(tracksAsin, likeState);
            return;
        }
        Log.e(this.TAG, "Exception encountered while attempting to set LikeState. ");
    }
}
