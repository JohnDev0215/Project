package com.amazon.mp3.playback;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.cloudqueue.sequencer.StormTrackMediaItem;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.provider.source.nowplaying.LibraryMediaItem;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.playback.PlayerViewModel;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.store.util.ImageCache;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.config.MediaItemImageManager;
import com.amazon.music.media.playback.util.ProgressUpdateHandler;
import java.util.ArrayList;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class PlayerDispatcher implements OnPlayStateChangedListener {
    private Subscription currentBitmapSubscription;
    private final MediaItemImageManager mMediaItemImageManagerCurrent;
    private final MediaItemImageManager mMediaItemImageManagerNext;
    private final MediaItemImageManager mMediaItemImageManagerPrev;
    private final PlayerViewModel mModel;
    private final MediaItemImageManager.OnBitmapChangedListener mOnBitmapChangedListenerCurrent;
    private final MediaItemImageManager.OnBitmapChangedListener mOnBitmapChangedListenerNext;
    private final MediaItemImageManager.OnBitmapChangedListener mOnBitmapChangedListenerPrev;
    private final ProgressUpdateHandler.OnPositionChangedListener mOnPositionChangedListener;
    private final PlaybackController mPlaybackController;
    private final ProgressUpdateHandler mProgressUpdateHandler;
    private Subscription nextBitmapSubscription;
    private Subscription previousBitmapSubscription;

    public PlayerDispatcher(final PlayerViewModel model) {
        PlaybackController playbackController = Playback.getInstance().getPlaybackController(ControlSource.APP_UI);
        this.mPlaybackController = playbackController;
        ProgressUpdateHandler.OnPositionChangedListener onPositionChangedListener = new ProgressUpdateHandler.OnPositionChangedListener() { // from class: com.amazon.mp3.playback.PlayerDispatcher.1
            @Override // com.amazon.music.media.playback.util.ProgressUpdateHandler.OnPositionChangedListener
            public void onPositionChanged(final long position) {
                PlayerDispatcher.this.mModel.updateProgress(position);
            }
        };
        this.mOnPositionChangedListener = onPositionChangedListener;
        this.mOnBitmapChangedListenerCurrent = new MediaItemImageManager.OnBitmapChangedListener() { // from class: com.amazon.mp3.playback.PlayerDispatcher.2
            @Override // com.amazon.music.media.playback.config.MediaItemImageManager.OnBitmapChangedListener
            public void onBitmapChanged(final MediaItemImageManager mediaItemImageManager) {
                PlayerDispatcher.this.mModel.updateCurrentBitmap(mediaItemImageManager.getBitmap());
            }
        };
        this.mOnBitmapChangedListenerNext = new MediaItemImageManager.OnBitmapChangedListener() { // from class: com.amazon.mp3.playback.PlayerDispatcher.3
            @Override // com.amazon.music.media.playback.config.MediaItemImageManager.OnBitmapChangedListener
            public void onBitmapChanged(final MediaItemImageManager mediaItemImageManager) {
                PlayerDispatcher.this.mModel.updateNextBitmap(mediaItemImageManager.getBitmap());
            }
        };
        this.mOnBitmapChangedListenerPrev = new MediaItemImageManager.OnBitmapChangedListener() { // from class: com.amazon.mp3.playback.PlayerDispatcher.4
            @Override // com.amazon.music.media.playback.config.MediaItemImageManager.OnBitmapChangedListener
            public void onBitmapChanged(final MediaItemImageManager mediaItemImageManager) {
                PlayerDispatcher.this.mModel.updatePrevBitmap(mediaItemImageManager.getBitmap());
            }
        };
        this.mModel = model;
        this.mMediaItemImageManagerCurrent = new MediaItemImageManager(playbackController.getPlayback().getPlaybackConfig(), 300, 300, new MediaItem.ImageType[0]);
        this.mMediaItemImageManagerNext = new MediaItemImageManager(playbackController.getPlayback().getPlaybackConfig(), 300, 300, new MediaItem.ImageType[0]);
        this.mMediaItemImageManagerPrev = new MediaItemImageManager(playbackController.getPlayback().getPlaybackConfig(), 300, 300, new MediaItem.ImageType[0]);
        this.mProgressUpdateHandler = new ProgressUpdateHandler(playbackController, 500L, onPositionChangedListener);
    }

    public void attachtoPlaybackListener() {
        this.mPlaybackController.addOnPlayStateChangedListener(this);
        this.mMediaItemImageManagerCurrent.addOnBitmapChangedListener(this.mOnBitmapChangedListenerCurrent);
        this.mMediaItemImageManagerNext.addOnBitmapChangedListener(this.mOnBitmapChangedListenerNext);
        this.mMediaItemImageManagerPrev.addOnBitmapChangedListener(this.mOnBitmapChangedListenerPrev);
        this.mProgressUpdateHandler.setEnabled(true);
    }

    public void detachPlaybackListener() {
        this.mPlaybackController.removeOnPlayStateChangedListener(this);
        this.mMediaItemImageManagerCurrent.removeOnBitmapChangedListener(this.mOnBitmapChangedListenerCurrent);
        this.mMediaItemImageManagerNext.removeOnBitmapChangedListener(this.mOnBitmapChangedListenerNext);
        this.mMediaItemImageManagerPrev.removeOnBitmapChangedListener(this.mOnBitmapChangedListenerPrev);
        this.mProgressUpdateHandler.setEnabled(false);
    }

    private void updatePlaybackControls() {
        this.mModel.refreshConfigState(this.mPlaybackController.getCurrentMediaItem());
        this.mModel.updateHasNext(this.mPlaybackController.canSkipNext());
        this.mModel.updateHasPrevious(this.mPlaybackController.canSkipPrevious() || this.mPlaybackController.canRestartItem());
        this.mModel.updateCanRestart(this.mPlaybackController.canRestartItem());
    }

    private void updatePlayerState() {
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        MediaItem mediaItemPeek = this.mPlaybackController.peek(1);
        MediaItem mediaItemPeek2 = this.mPlaybackController.peek(-1);
        if (mediaItemPeek2 == currentMediaItem) {
            mediaItemPeek2 = null;
        }
        if (mediaItemPeek == currentMediaItem) {
            mediaItemPeek = null;
        }
        boolean zHasAnyInternetConnection = true ^ ConnectivityUtil.hasAnyInternetConnection();
        this.mMediaItemImageManagerCurrent.setMediaItem(null);
        this.mMediaItemImageManagerCurrent.setMediaItem(currentMediaItem, zHasAnyInternetConnection);
        this.mMediaItemImageManagerNext.setMediaItem(null);
        this.mMediaItemImageManagerNext.setMediaItem(mediaItemPeek, zHasAnyInternetConnection);
        this.mMediaItemImageManagerPrev.setMediaItem(null);
        if (mediaItemPeek2 != null) {
            this.mMediaItemImageManagerPrev.setMediaItem(mediaItemPeek2, zHasAnyInternetConnection);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(PlayerViewModel.PlayerContentViewModel.fromMediaItem(mediaItemPeek2));
        arrayList.add(PlayerViewModel.PlayerContentViewModel.fromMediaItem(currentMediaItem));
        arrayList.add(PlayerViewModel.PlayerContentViewModel.fromMediaItem(mediaItemPeek));
        this.mModel.updatePlayerContentViewModelList(arrayList);
        this.mModel.updatePlayerState(this.mPlaybackController.getPlayStatus());
        this.mModel.updateShuffle(this.mPlaybackController.isShuffled());
        this.mModel.updateRepeat(this.mPlaybackController.getRepeatMode());
        this.mModel.updateRating(this.mPlaybackController.getCurrentItemRating());
        this.mModel.updateDuration(this.mPlaybackController.getDurationMillis());
        this.mModel.updateProgress(this.mProgressUpdateHandler.getPositionMillis());
    }

    public void updateMediaItemArtwork() {
        if (this.mMediaItemImageManagerCurrent.getBitmap() != null) {
            this.mModel.updateCurrentBitmap(this.mMediaItemImageManagerCurrent.getBitmap());
        } else {
            MediaItem mediaItem = this.mMediaItemImageManagerCurrent.getMediaItem();
            if (mediaItem instanceof LibraryMediaItem) {
                Subscription subscription = this.currentBitmapSubscription;
                if (subscription != null && !subscription.isUnsubscribed()) {
                    this.currentBitmapSubscription.unsubscribe();
                }
                this.currentBitmapSubscription = getBitmapForLibraryContent(mediaItem, PlayerViewModel.ArtworkBitmapType.CURRENT);
            } else if (mediaItem instanceof StormTrackMediaItem) {
                Subscription subscription2 = this.currentBitmapSubscription;
                if (subscription2 != null && !subscription2.isUnsubscribed()) {
                    this.currentBitmapSubscription.unsubscribe();
                }
                this.currentBitmapSubscription = getBitMapForCloudQueueItem(mediaItem, PlayerViewModel.ArtworkBitmapType.CURRENT);
            }
        }
        if (this.mMediaItemImageManagerPrev.getBitmap() != null) {
            this.mModel.updatePrevBitmap(this.mMediaItemImageManagerPrev.getBitmap());
        } else {
            MediaItem mediaItem2 = this.mMediaItemImageManagerPrev.getMediaItem();
            if (mediaItem2 instanceof LibraryMediaItem) {
                Subscription subscription3 = this.previousBitmapSubscription;
                if (subscription3 != null && !subscription3.isUnsubscribed()) {
                    this.previousBitmapSubscription.unsubscribe();
                }
                this.previousBitmapSubscription = getBitmapForLibraryContent(mediaItem2, PlayerViewModel.ArtworkBitmapType.PREVIOUS);
            } else if (mediaItem2 instanceof StormTrackMediaItem) {
                Subscription subscription4 = this.previousBitmapSubscription;
                if (subscription4 != null && !subscription4.isUnsubscribed()) {
                    this.previousBitmapSubscription.unsubscribe();
                }
                this.previousBitmapSubscription = getBitMapForCloudQueueItem(mediaItem2, PlayerViewModel.ArtworkBitmapType.PREVIOUS);
            }
        }
        if (this.mMediaItemImageManagerNext.getBitmap() != null) {
            this.mModel.updateNextBitmap(this.mMediaItemImageManagerNext.getBitmap());
            return;
        }
        MediaItem mediaItem3 = this.mMediaItemImageManagerNext.getMediaItem();
        if (mediaItem3 instanceof LibraryMediaItem) {
            Subscription subscription5 = this.nextBitmapSubscription;
            if (subscription5 != null && !subscription5.isUnsubscribed()) {
                this.nextBitmapSubscription.unsubscribe();
            }
            this.nextBitmapSubscription = getBitmapForLibraryContent(mediaItem3, PlayerViewModel.ArtworkBitmapType.NEXT);
            return;
        }
        if (mediaItem3 instanceof StormTrackMediaItem) {
            Subscription subscription6 = this.nextBitmapSubscription;
            if (subscription6 != null && !subscription6.isUnsubscribed()) {
                this.nextBitmapSubscription.unsubscribe();
            }
            this.nextBitmapSubscription = getBitMapForCloudQueueItem(mediaItem3, PlayerViewModel.ArtworkBitmapType.NEXT);
        }
    }

    private Subscription getBitmapForLibraryContent(final MediaItem libraryMediaItem, final PlayerViewModel.ArtworkBitmapType bitmapToUpdate) {
        final long albumId = MediaItemHelper.getAlbumId(libraryMediaItem);
        if (albumId != 3) {
            return Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.playback.PlayerDispatcher$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    PlayerDispatcher.lambda$getBitmapForLibraryContent$0(albumId, (Subscriber) obj);
                }
            }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Bitmap>() { // from class: com.amazon.mp3.playback.PlayerDispatcher.5
                @Override // rx.Observer
                public void onCompleted() {
                }

                @Override // rx.Observer
                public void onError(final Throwable throwable) {
                }

                @Override // rx.Observer
                public void onNext(final Bitmap bitmap) {
                    int i = AnonymousClass7.$SwitchMap$com$amazon$mp3$playback$PlayerViewModel$ArtworkBitmapType[bitmapToUpdate.ordinal()];
                    if (i == 1) {
                        PlayerDispatcher.this.mModel.updatePrevBitmap(bitmap);
                    } else if (i == 2) {
                        PlayerDispatcher.this.mModel.updateCurrentBitmap(bitmap);
                    } else {
                        if (i != 3) {
                            return;
                        }
                        PlayerDispatcher.this.mModel.updateNextBitmap(bitmap);
                    }
                }
            });
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$getBitmapForLibraryContent$0(long j, Subscriber subscriber) {
        subscriber.onNext(((BitmapDrawable) CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.ALBUM, 300, Long.toString(j), true)).getBitmap());
        subscriber.onCompleted();
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.PlayerDispatcher$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$playback$PlayerViewModel$ArtworkBitmapType;

        static {
            int[] iArr = new int[PlayerViewModel.ArtworkBitmapType.values().length];
            $SwitchMap$com$amazon$mp3$playback$PlayerViewModel$ArtworkBitmapType = iArr;
            try {
                iArr[PlayerViewModel.ArtworkBitmapType.PREVIOUS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$PlayerViewModel$ArtworkBitmapType[PlayerViewModel.ArtworkBitmapType.CURRENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$PlayerViewModel$ArtworkBitmapType[PlayerViewModel.ArtworkBitmapType.NEXT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private Subscription getBitMapForCloudQueueItem(MediaItem mediaItem, final PlayerViewModel.ArtworkBitmapType bitmapToUpdate) {
        final String source = MediaItemHelper.getSource(mediaItem);
        final String id = mediaItem.getMediaItemId(MediaItemId.Type.ASIN).getId();
        final String artworkUrlForAsin = new CTAPrimeCache(AmazonApplication.getApplication()).getArtworkUrlForAsin(MediaItemHelper.getAlbumAsin(mediaItem));
        return Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.playback.PlayerDispatcher$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) throws Throwable {
                PlayerDispatcher.lambda$getBitMapForCloudQueueItem$1(artworkUrlForAsin, source, id, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Bitmap>() { // from class: com.amazon.mp3.playback.PlayerDispatcher.6
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(final Throwable throwable) {
            }

            @Override // rx.Observer
            public void onNext(final Bitmap bitmap) {
                int i = AnonymousClass7.$SwitchMap$com$amazon$mp3$playback$PlayerViewModel$ArtworkBitmapType[bitmapToUpdate.ordinal()];
                if (i == 1) {
                    PlayerDispatcher.this.mModel.updatePrevBitmap(bitmap);
                } else if (i == 2) {
                    PlayerDispatcher.this.mModel.updateCurrentBitmap(bitmap);
                } else {
                    if (i != 3) {
                        return;
                    }
                    PlayerDispatcher.this.mModel.updateNextBitmap(bitmap);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$getBitMapForCloudQueueItem$1(String str, String str2, String str3, Subscriber subscriber) throws Throwable {
        Drawable onCurrentThread;
        if (!TextUtils.isEmpty(str)) {
            onCurrentThread = new ImageCache(AmazonApplication.getApplication(), 300, 300, Bitmap.Config.RGB_565).cacheOnCurrentThread(str);
        } else {
            onCurrentThread = CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.PRIME_PLAYLIST_TRACK, str2, 300, Long.toString(str3.hashCode()), true);
        }
        if (onCurrentThread instanceof BitmapDrawable) {
            subscriber.onNext(((BitmapDrawable) onCurrentThread).getBitmap());
        }
        subscriber.onCompleted();
    }

    public void refresh() {
        updatePlaybackControls();
        updatePlayerState();
    }

    @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
    public void onPlayStateChanged() {
        updatePlaybackControls();
        updatePlayerState();
    }
}
