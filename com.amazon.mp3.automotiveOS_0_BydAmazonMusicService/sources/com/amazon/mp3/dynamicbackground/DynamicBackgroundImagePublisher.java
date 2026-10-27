package com.amazon.mp3.dynamicbackground;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.util.Log;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.content.res.AppCompatResources;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.cloudqueue.sequencer.StormTrackMediaItem;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.provider.source.nowplaying.LibraryMediaItem;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.store.util.ImageCache;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.config.MediaItemImageManager;
import com.amazon.music.views.library.styles.StyleSheet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: compiled from: DynamicBackgroundImagePublisher.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u0000 E2\u00020\u00012\u00020\u0002:\u0002EFB\t\b\u0002¢\u0006\u0004\bC\u0010DJ\u001a\u0010\b\u001a\u00020\u00072\b\b\u0001\u0010\u0004\u001a\u00020\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0005J$\u0010\b\u001a\u00020\u00072\b\b\u0001\u0010\u0004\u001a\u00020\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\tJ\u0010\u0010\u000b\u001a\u00020\u00072\b\b\u0001\u0010\u0004\u001a\u00020\u0003J&\u0010\u0014\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012J\b\u0010\u0015\u001a\u00020\u0007H\u0016J\u0010\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0016H\u0016J\u0006\u0010\u0019\u001a\u00020\u0007J\u0006\u0010\u001a\u001a\u00020\u0007J\b\u0010\u001b\u001a\u00020\u0007H\u0002J\b\u0010\u001c\u001a\u00020\u0007H\u0002J\b\u0010\u001d\u001a\u00020\u0007H\u0002J\u0012\u0010!\u001a\u0004\u0018\u00010 2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002J\u0012\u0010$\u001a\u0004\u0018\u00010 2\u0006\u0010#\u001a\u00020\"H\u0002J\b\u0010%\u001a\u00020\u0007H\u0002J\b\u0010&\u001a\u00020\u0007H\u0002J\u0012\u0010'\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0002R\u001c\u0010*\u001a\n )*\u0004\u0018\u00010(0(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u001c\u0010-\u001a\n )*\u0004\u0018\u00010,0,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.R\u0014\u0010/\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100R\u001a\u00102\u001a\b\u0012\u0004\u0012\u00020\u0003018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103R\u001a\u00104\u001a\b\u0012\u0004\u0012\u00020\u0005018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00103R\u001a\u00105\u001a\b\u0012\u0004\u0012\u00020\t018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00103R\u0014\u00106\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00107R\u0018\u00108\u001a\u0004\u0018\u00010 8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b8\u00109R\u0016\u0010;\u001a\u00020:8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b;\u0010<R\u0018\u0010>\u001a\u0004\u0018\u00010=8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b>\u0010?R\u0016\u0010A\u001a\u00020@8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bA\u0010B¨\u0006G"}, d2 = {"Lcom/amazon/mp3/dynamicbackground/DynamicBackgroundImagePublisher;", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "Lcom/amazon/music/media/playback/config/MediaItemImageManager$OnBitmapChangedListener;", "Lcom/amazon/mp3/dynamicbackground/DynamicBackgroundImagePublisher$DynamicBackgroundSubscriber;", "subscriber", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "", "subscribeBackgroundImageUpdate", "Lcom/amazon/mp3/dynamicbackground/DynamicBackgroundImageCondition;", "dynamicBackgroundImageCondition", "unSubscribeBackgroundImageUpdate", "Landroid/content/Context;", "context", "Landroid/content/res/Resources;", "resources", "Landroid/widget/ImageView;", "imageView", "Landroid/graphics/Bitmap;", "bitmap", "setBackgroundWithTransition", "onPlayStateChanged", "Lcom/amazon/music/media/playback/config/MediaItemImageManager;", "mediaItemImageManager", "onBitmapChanged", "pauseDynamicBackgroundImagePublisher", "resumeDynamicBackgroundImagePublisher", "clear", "attachPlaybackListener", "detachPlaybackListener", "Lcom/amazon/mp3/library/provider/source/nowplaying/LibraryMediaItem;", "libraryMediaItem", "Lrx/Subscription;", "getBitmapForLibraryContent", "Lcom/amazon/mp3/cloudqueue/sequencer/StormTrackMediaItem;", "mediaItem", "getBitmapForCloudQueueContent", "cancelRetrievingImageForLibraryContent", "cancelTransformBlurredImageSubscription", "onBackgroundImageUpdate", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Lcom/amazon/music/media/playback/PlaybackController;", "mPlaybackController", "Lcom/amazon/music/media/playback/PlaybackController;", "mMediaItemImageManager", "Lcom/amazon/music/media/playback/config/MediaItemImageManager;", "", "dynamicBackgroundSubscriberList", "Ljava/util/List;", "dynamicBackgroundStyleSheetList", "dynamicBackgroundImageConditionList", "defaultDynamicBackgroundImageCondition", "Lcom/amazon/mp3/dynamicbackground/DynamicBackgroundImageCondition;", "mGetBitMapForLibraryContentSubscription", "Lrx/Subscription;", "Lrx/subscriptions/CompositeSubscription;", "mTransformBlurredImageSubscriptions", "Lrx/subscriptions/CompositeSubscription;", "Lcom/amazon/music/media/playback/MediaItem;", "mPrevMediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "", "prevBitmapGenerationId", "I", "<init>", "()V", "Companion", "DynamicBackgroundSubscriber", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class DynamicBackgroundImagePublisher implements OnPlayStateChangedListener, MediaItemImageManager.OnBitmapChangedListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<DynamicBackgroundImagePublisher> instance$delegate = LazyKt__LazyJVMKt.lazy(new Function0<DynamicBackgroundImagePublisher>() { // from class: com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DynamicBackgroundImagePublisher invoke() {
            return new DynamicBackgroundImagePublisher(null);
        }
    });
    private final String TAG;
    private final DynamicBackgroundImageCondition defaultDynamicBackgroundImageCondition;
    private final List<DynamicBackgroundImageCondition> dynamicBackgroundImageConditionList;
    private final List<StyleSheet> dynamicBackgroundStyleSheetList;
    private final List<DynamicBackgroundSubscriber> dynamicBackgroundSubscriberList;
    private Subscription mGetBitMapForLibraryContentSubscription;
    private final MediaItemImageManager mMediaItemImageManager;
    private final PlaybackController mPlaybackController;
    private MediaItem mPrevMediaItem;
    private CompositeSubscription mTransformBlurredImageSubscriptions;
    private int prevBitmapGenerationId;

    /* JADX INFO: compiled from: DynamicBackgroundImagePublisher.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H'J\b\u0010\u0006\u001a\u00020\u0003H'J\b\u0010\u0007\u001a\u00020\u0003H'¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/dynamicbackground/DynamicBackgroundImagePublisher$DynamicBackgroundSubscriber;", "", "onBackgroundImageUpdate", "", "bitmap", "Landroid/graphics/Bitmap;", "onPauseBackgroundImageUpdate", "onResumeBackgroundImageUpdate", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface DynamicBackgroundSubscriber {
        @UiThread
        void onBackgroundImageUpdate(Bitmap bitmap);

        @UiThread
        void onPauseBackgroundImageUpdate();

        @UiThread
        void onResumeBackgroundImageUpdate();
    }

    public /* synthetic */ DynamicBackgroundImagePublisher(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static final DynamicBackgroundImagePublisher getInstance() {
        return INSTANCE.getInstance();
    }

    /* JADX INFO: compiled from: DynamicBackgroundImagePublisher.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R!\u0010\u0005\u001a\u00020\u00068FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u0012\u0004\b\u0007\u0010\u0002\u001a\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/dynamicbackground/DynamicBackgroundImagePublisher$Companion;", "", "()V", "BACKGROUND_IMAGE_SIZE", "", "instance", "Lcom/amazon/mp3/dynamicbackground/DynamicBackgroundImagePublisher;", "getInstance$annotations", "getInstance", "()Lcom/amazon/mp3/dynamicbackground/DynamicBackgroundImagePublisher;", "instance$delegate", "Lkotlin/Lazy;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DynamicBackgroundImagePublisher getInstance() {
            return (DynamicBackgroundImagePublisher) DynamicBackgroundImagePublisher.instance$delegate.getValue();
        }
    }

    private DynamicBackgroundImagePublisher() {
        this.TAG = DynamicBackgroundImagePublisher.class.getSimpleName();
        PlaybackController playbackController = Playback.getInstance().getPlaybackController(null);
        this.mPlaybackController = playbackController;
        this.mMediaItemImageManager = new MediaItemImageManager(playbackController.getPlayback().getPlaybackConfig(), 128, 128, new MediaItem.ImageType[0]);
        this.dynamicBackgroundSubscriberList = new ArrayList();
        this.dynamicBackgroundStyleSheetList = new ArrayList();
        this.dynamicBackgroundImageConditionList = new ArrayList();
        this.defaultDynamicBackgroundImageCondition = new DynamicBackgroundImageCondition(null, null, null, null, 15, null);
        this.mTransformBlurredImageSubscriptions = new CompositeSubscription();
    }

    public final synchronized void subscribeBackgroundImageUpdate(@NonNull DynamicBackgroundSubscriber subscriber, @NonNull StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(subscriber, "subscriber");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        subscribeBackgroundImageUpdate(subscriber, styleSheet, this.defaultDynamicBackgroundImageCondition);
    }

    public final void subscribeBackgroundImageUpdate(@NonNull DynamicBackgroundSubscriber subscriber, @NonNull StyleSheet styleSheet, DynamicBackgroundImageCondition dynamicBackgroundImageCondition) {
        Intrinsics.checkNotNullParameter(subscriber, "subscriber");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(dynamicBackgroundImageCondition, "dynamicBackgroundImageCondition");
        this.dynamicBackgroundSubscriberList.add(subscriber);
        this.dynamicBackgroundStyleSheetList.add(styleSheet);
        this.dynamicBackgroundImageConditionList.add(dynamicBackgroundImageCondition);
        if (this.dynamicBackgroundSubscriberList.size() == 1) {
            attachPlaybackListener();
        }
        this.mPrevMediaItem = null;
        this.prevBitmapGenerationId = -1;
        try {
            onPlayStateChanged();
        } catch (Exception e) {
            Log.e(this.TAG, Intrinsics.stringPlus("Error while triggering onPlayStateChanged:\n ", ExceptionsKt__ExceptionsKt.stackTraceToString(e)));
        }
    }

    public final synchronized void unSubscribeBackgroundImageUpdate(@NonNull DynamicBackgroundSubscriber subscriber) {
        Intrinsics.checkNotNullParameter(subscriber, "subscriber");
        int iIndexOf = this.dynamicBackgroundSubscriberList.indexOf(subscriber);
        if (iIndexOf < 0) {
            return;
        }
        this.dynamicBackgroundStyleSheetList.remove(iIndexOf);
        this.dynamicBackgroundSubscriberList.remove(subscriber);
        if (this.dynamicBackgroundSubscriberList.size() == 0) {
            clear();
        }
    }

    private final void clear() {
        detachPlaybackListener();
        cancelRetrievingImageForLibraryContent();
        cancelTransformBlurredImageSubscription();
    }

    private final void attachPlaybackListener() {
        this.mPlaybackController.addOnPlayStateChangedListener(this);
        this.mMediaItemImageManager.addOnBitmapChangedListener(this);
    }

    private final void detachPlaybackListener() {
        this.mPlaybackController.removeOnPlayStateChangedListener(this);
        this.mMediaItemImageManager.removeOnBitmapChangedListener(this);
    }

    private final synchronized Subscription getBitmapForLibraryContent(final LibraryMediaItem libraryMediaItem) {
        final long albumId;
        albumId = MediaItemHelper.getAlbumId(libraryMediaItem);
        return albumId != 3 ? Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DynamicBackgroundImagePublisher.m969getBitmapForLibraryContent$lambda1(albumId, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Bitmap>() { // from class: com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher.getBitmapForLibraryContent.2
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Log.d(DynamicBackgroundImagePublisher.this.TAG, throwable.getMessage(), throwable);
            }

            @Override // rx.Observer
            public void onNext(Bitmap bitmap) {
                if (bitmap != null) {
                    DynamicBackgroundImagePublisher.this.onBackgroundImageUpdate(bitmap);
                } else {
                    DynamicBackgroundImagePublisher.this.mMediaItemImageManager.setMediaItem(null);
                    DynamicBackgroundImagePublisher.this.mMediaItemImageManager.setMediaItem(libraryMediaItem);
                }
            }
        }) : null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getBitmapForLibraryContent$lambda-1, reason: not valid java name */
    public static final void m969getBitmapForLibraryContent$lambda1(long j, Subscriber subscriber) {
        Unit unit;
        Intrinsics.checkNotNullParameter(subscriber, "subscriber");
        Drawable onCurrentThread = CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.ALBUM, null, 128, String.valueOf(j), true, false);
        if (onCurrentThread == null) {
            unit = null;
        } else {
            subscriber.onNext(((BitmapDrawable) onCurrentThread).getBitmap().copy(Bitmap.Config.ARGB_8888, true));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            subscriber.onNext(null);
        }
        subscriber.onCompleted();
    }

    private final synchronized Subscription getBitmapForCloudQueueContent(final StormTrackMediaItem mediaItem) {
        final String albumAsin = MediaItemHelper.getAlbumAsin(mediaItem);
        if (albumAsin == null || albumAsin.length() == 0) {
            return null;
        }
        return Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) throws Throwable {
                DynamicBackgroundImagePublisher.m966getBitmapForCloudQueueContent$lambda3(albumAsin, mediaItem, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DynamicBackgroundImagePublisher.m967getBitmapForCloudQueueContent$lambda4(this.f$0, mediaItem, (Bitmap) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DynamicBackgroundImagePublisher.m968getBitmapForCloudQueueContent$lambda5(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getBitmapForCloudQueueContent$lambda-3, reason: not valid java name */
    public static final void m966getBitmapForCloudQueueContent$lambda3(String str, StormTrackMediaItem mediaItem, Subscriber subscriber) throws Throwable {
        Drawable onCurrentThread;
        Bitmap bitmap;
        Intrinsics.checkNotNullParameter(mediaItem, "$mediaItem");
        Intrinsics.checkNotNullParameter(subscriber, "subscriber");
        Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
        String artworkUrlForAsin = new CTAPrimeCache(applicationContext).getArtworkUrlForAsin(str);
        Bitmap bitmapCopy = null;
        if (!(artworkUrlForAsin == null || artworkUrlForAsin.length() == 0)) {
            onCurrentThread = new ImageCache(applicationContext, 128, 128, Bitmap.Config.RGB_565).cacheOnCurrentThread(artworkUrlForAsin);
        } else {
            long albumId = MediaItemHelper.getAlbumId(mediaItem);
            onCurrentThread = 3 != albumId ? CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.ALBUM, null, 128, String.valueOf(albumId), true, false) : null;
        }
        if (onCurrentThread != null) {
            BitmapDrawable bitmapDrawable = onCurrentThread instanceof BitmapDrawable ? (BitmapDrawable) onCurrentThread : null;
            if (bitmapDrawable != null && (bitmap = bitmapDrawable.getBitmap()) != null) {
                bitmapCopy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            }
        }
        subscriber.onNext(bitmapCopy);
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getBitmapForCloudQueueContent$lambda-4, reason: not valid java name */
    public static final void m967getBitmapForCloudQueueContent$lambda4(DynamicBackgroundImagePublisher this$0, StormTrackMediaItem mediaItem, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mediaItem, "$mediaItem");
        if (bitmap != null) {
            this$0.onBackgroundImageUpdate(bitmap);
        } else {
            this$0.mMediaItemImageManager.setMediaItem(null);
            this$0.mMediaItemImageManager.setMediaItem(mediaItem);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getBitmapForCloudQueueContent$lambda-5, reason: not valid java name */
    public static final void m968getBitmapForCloudQueueContent$lambda5(DynamicBackgroundImagePublisher this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.e(this$0.TAG, th.getMessage(), th);
    }

    public final void setBackgroundWithTransition(Context context, Resources resources, ImageView imageView, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(resources, "resources");
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Drawable[] drawableArr = new Drawable[2];
        if (imageView.getDrawable() == null) {
            drawableArr[0] = AppCompatResources.getDrawable(context, R.drawable.background_gradient);
        } else {
            drawableArr[0] = imageView.getDrawable();
        }
        drawableArr[1] = new BitmapDrawable(resources, bitmap);
        TransitionDrawable transitionDrawable = new TransitionDrawable(drawableArr);
        transitionDrawable.setCrossFadeEnabled(true);
        imageView.setImageDrawable(transitionDrawable);
        transitionDrawable.startTransition(resources.getInteger(imageView.getDrawable() == null ? R.integer.duration_milliseconds_2500 : R.integer.duration_milliseconds_1000));
    }

    @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
    public void onPlayStateChanged() {
        MediaCollectionInfo sourceMediaCollectionInfo;
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        if (!(currentMediaItem == null || (sourceMediaCollectionInfo = currentMediaItem.getSourceMediaCollectionInfo()) == null || sourceMediaCollectionInfo.getPrimaryType() != MediaCollectionId.Type.PROGRAM_ID) || this.mPrevMediaItem == currentMediaItem) {
            return;
        }
        this.mPrevMediaItem = currentMediaItem;
        if (currentMediaItem instanceof LibraryMediaItem) {
            cancelRetrievingImageForLibraryContent();
            this.mGetBitMapForLibraryContentSubscription = getBitmapForLibraryContent((LibraryMediaItem) currentMediaItem);
        } else if (currentMediaItem instanceof StormTrackMediaItem) {
            cancelRetrievingImageForLibraryContent();
            this.mGetBitMapForLibraryContentSubscription = getBitmapForCloudQueueContent((StormTrackMediaItem) currentMediaItem);
        } else {
            this.mMediaItemImageManager.setMediaItem(null);
            this.mMediaItemImageManager.setMediaItem(currentMediaItem);
        }
    }

    @Override // com.amazon.music.media.playback.config.MediaItemImageManager.OnBitmapChangedListener
    public void onBitmapChanged(MediaItemImageManager mediaItemImageManager) {
        Unit unit;
        Intrinsics.checkNotNullParameter(mediaItemImageManager, "mediaItemImageManager");
        Bitmap bitmap = mediaItemImageManager.getBitmap();
        if (bitmap == null) {
            unit = null;
        } else {
            if (this.prevBitmapGenerationId != bitmap.getGenerationId()) {
                onBackgroundImageUpdate(bitmap);
                this.prevBitmapGenerationId = bitmap.getGenerationId();
            }
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            onBackgroundImageUpdate(null);
        }
    }

    private final synchronized void cancelRetrievingImageForLibraryContent() {
        Subscription subscription = this.mGetBitMapForLibraryContentSubscription;
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    private final synchronized void cancelTransformBlurredImageSubscription() {
        this.mTransformBlurredImageSubscriptions.clear();
    }

    public final synchronized void pauseDynamicBackgroundImagePublisher() {
        Iterator<DynamicBackgroundSubscriber> it = this.dynamicBackgroundSubscriberList.iterator();
        while (it.hasNext()) {
            it.next().onPauseBackgroundImageUpdate();
        }
    }

    public final synchronized void resumeDynamicBackgroundImagePublisher() {
        if (this.mPlaybackController.getCurrentMediaItem() == null) {
            return;
        }
        Iterator<DynamicBackgroundSubscriber> it = this.dynamicBackgroundSubscriberList.iterator();
        while (it.hasNext()) {
            it.next().onResumeBackgroundImageUpdate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void onBackgroundImageUpdate(Bitmap bitmap) {
        cancelTransformBlurredImageSubscription();
        if (bitmap == null) {
            Iterator<DynamicBackgroundSubscriber> it = this.dynamicBackgroundSubscriberList.iterator();
            while (it.hasNext()) {
                it.next().onBackgroundImageUpdate(null);
            }
            return;
        }
        int i = 0;
        int size = this.dynamicBackgroundSubscriberList.size();
        while (i < size) {
            int i2 = i + 1;
            final DynamicBackgroundSubscriber dynamicBackgroundSubscriber = this.dynamicBackgroundSubscriberList.get(i);
            Context context = dynamicBackgroundSubscriber instanceof Context ? (Context) dynamicBackgroundSubscriber : null;
            if (context == null) {
                context = AmazonApplication.getApplication().getApplicationContext();
            }
            DynamicBackgroundEffectUtils dynamicBackgroundEffectUtils = DynamicBackgroundEffectUtils.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(context, "context");
            this.mTransformBlurredImageSubscriptions.add(dynamicBackgroundEffectUtils.getTransformBlurredImageSubscription(bitmap, context, this.dynamicBackgroundStyleSheetList.get(i), this.dynamicBackgroundImageConditionList.get(i)).subscribe(new Observer<Bitmap>() { // from class: com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher$onBackgroundImageUpdate$subscription$1
                @Override // rx.Observer
                public void onCompleted() {
                }

                @Override // rx.Observer
                public void onError(Throwable e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    Log.e(this.this$0.TAG, "error occurred when transforming background image", e);
                }

                @Override // rx.Observer
                public void onNext(Bitmap bitmap2) {
                    dynamicBackgroundSubscriber.onBackgroundImageUpdate(bitmap2);
                }
            }));
            i = i2;
        }
    }
}
