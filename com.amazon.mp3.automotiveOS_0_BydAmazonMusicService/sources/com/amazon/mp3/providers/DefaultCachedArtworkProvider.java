package com.amazon.mp3.providers;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItemBinder;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedManager;
import com.amazon.mp3.util.BitmapUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.RxKt;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.RecentsMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.providers.CachedArtworkProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultCachedArtworkProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 :2\u00020\u0001:\u0002:;B\u001d\u0012\b\u0010%\u001a\u0004\u0018\u00010$\u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010)¢\u0006\u0004\b8\u00109J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0002J(\u0010\u0010\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00020\u000eH\u0002J \u0010\u0010\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J\u001c\u0010\u0013\u001a\u00020\u00122\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0011\u001a\u0004\u0018\u00010\fH\u0002J\u0011\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u001a\u0010\u001c\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001aH\u0002J\u001a\u0010\u001e\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001dH\u0002J\u001a\u0010 \u001a\u0004\u0018\u00010\f2\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u001fH\u0002J\u001a\u0010\"\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020!H\u0002J\u001a\u0010#\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020!H\u0002J\"\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u001a\u0010\u0010\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016R\u0019\u0010%\u001a\u0004\u0018\u00010$8\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R\u0019\u0010*\u001a\u0004\u0018\u00010)8\u0006¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u0014\u0010/\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100R\u001c\u00103\u001a\n 2*\u0004\u0018\u000101018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00104R\u0014\u00106\u001a\u0002058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00107¨\u0006<"}, d2 = {"Lcom/amazon/mp3/providers/DefaultCachedArtworkProvider;", "Lcom/amazon/music/views/library/providers/CachedArtworkProvider;", "Landroid/widget/ImageView;", "imageView", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "", "loadCachedArtworkForUnknownSize", "Landroid/net/Uri;", "uri", "loadSmartPlaylistCachedArtwork", "Lio/reactivex/rxjava3/core/Observable;", "Landroid/graphics/drawable/Drawable;", "imageObservable", "Lcom/amazon/music/imageloader/ImageLoader$ImageLoaderCallback;", "imageLoaderCallback", "loadCachedArtwork", "drawable", "Landroid/graphics/Bitmap;", "convertDrawableToBitmap", "", "getCollectionDetailHeaderArtworkSize", "()Ljava/lang/Integer;", "size", "getContentMetadataArtwork", "cropDrawable", "", "cachedImageId", "getArtistArtworkForCachedImageId", "", "getAlbumArtworkForCachedImageId", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "getPrimePlaylistTrackArtworkForMetadata", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "getPlaylistArtworkForMetadata", "getLibraryPlaylistArtwork", "Landroid/app/Activity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "getActivity", "()Landroid/app/Activity;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/mp3/recentlyplayed/RecentlyPlayedItemBinder;", "recentsBinder", "Lcom/amazon/mp3/recentlyplayed/RecentlyPlayedItemBinder;", "Lcom/amazon/mp3/recentlyplayed/RecentlyPlayedManager;", "kotlin.jvm.PlatformType", "recentlyPlayedManager", "Lcom/amazon/mp3/recentlyplayed/RecentlyPlayedManager;", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "<init>", "(Landroid/app/Activity;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "Companion", "LifecycleCallback", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class DefaultCachedArtworkProvider implements CachedArtworkProvider {
    private static final String TAG = DefaultCachedArtworkProvider.class.getSimpleName();
    private final Activity activity;
    private final CompositeDisposable compositeDisposable;
    private final RecentlyPlayedManager recentlyPlayedManager;
    private final RecentlyPlayedItemBinder recentsBinder;
    private final StyleSheet styleSheet;

    /* JADX INFO: compiled from: DefaultCachedArtworkProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ScreenSizeKey.values().length];
            iArr[ScreenSizeKey.XSMALL.ordinal()] = 1;
            iArr[ScreenSizeKey.SMALL.ordinal()] = 2;
            iArr[ScreenSizeKey.MEDIUM.ordinal()] = 3;
            iArr[ScreenSizeKey.LARGE.ordinal()] = 4;
            iArr[ScreenSizeKey.XLARGE.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public DefaultCachedArtworkProvider(Activity activity, StyleSheet styleSheet) {
        Application application;
        this.activity = activity;
        this.styleSheet = styleSheet;
        RecentlyPlayedItemBinder recentlyPlayedItemBinder = new RecentlyPlayedItemBinder(activity);
        this.recentsBinder = recentlyPlayedItemBinder;
        this.recentlyPlayedManager = RecentlyPlayedManager.getInstance(activity);
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        this.compositeDisposable = compositeDisposable;
        if (activity == null || (application = activity.getApplication()) == null) {
            return;
        }
        application.registerActivityLifecycleCallbacks(new LifecycleCallback(compositeDisposable, recentlyPlayedItemBinder, activity.hashCode()));
    }

    public /* synthetic */ DefaultCachedArtworkProvider(Activity activity, StyleSheet styleSheet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(activity, (i & 2) != 0 ? null : styleSheet);
    }

    @Override // com.amazon.music.views.library.providers.CachedArtworkProvider
    public void loadCachedArtwork(ImageLoader.ImageLoaderCallback imageLoaderCallback, ImageView imageView, ContentMetadata metadata) {
        Integer collectionDetailHeaderArtworkSize;
        Intrinsics.checkNotNullParameter(imageLoaderCallback, "imageLoaderCallback");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        if (!(metadata instanceof AlbumMetadata ? true : metadata instanceof PlaylistMetadata ? true : metadata instanceof ArtistMetadata) || (collectionDetailHeaderArtworkSize = getCollectionDetailHeaderArtworkSize()) == null) {
            return;
        }
        loadCachedArtwork(getContentMetadataArtwork(collectionDetailHeaderArtworkSize.intValue(), metadata), imageView, imageLoaderCallback);
    }

    @Override // com.amazon.music.views.library.providers.CachedArtworkProvider
    public void loadCachedArtwork(final ImageView imageView, final ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        if (metadata instanceof TrackMetadata ? true : metadata instanceof ArtistMetadata ? true : metadata instanceof AlbumMetadata) {
            if (imageView == null) {
                return;
            }
            loadCachedArtworkForUnknownSize(imageView, metadata);
            return;
        }
        if (metadata instanceof PlaylistMetadata) {
            if (MediaProvider.SmartPlaylists.isSmartPlaylist(metadata.getUri())) {
                if (imageView == null) {
                    return;
                }
                loadSmartPlaylistCachedArtwork(imageView, metadata.getUri());
                return;
            } else {
                if (imageView == null) {
                    return;
                }
                loadCachedArtworkForUnknownSize(imageView, metadata);
                return;
            }
        }
        if (metadata instanceof RecentsMetadata) {
            Observable observableObserveOn = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.providers.DefaultCachedArtworkProvider$$ExternalSyntheticLambda0
                @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
                public final void subscribe(ObservableEmitter observableEmitter) {
                    DefaultCachedArtworkProvider.m1601loadCachedArtwork$lambda4(this.f$0, metadata, observableEmitter);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            Intrinsics.checkNotNullExpressionValue(observableObserveOn, "create<RecentlyPlayedIte…dSchedulers.mainThread())");
            RxKt.subscribe(observableObserveOn, this.compositeDisposable, new Function1<RecentlyPlayedItem, Unit>() { // from class: com.amazon.mp3.providers.DefaultCachedArtworkProvider.loadCachedArtwork.6
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(RecentlyPlayedItem recentlyPlayedItem) {
                    invoke2(recentlyPlayedItem);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(final RecentlyPlayedItem recentlyPlayedItem) {
                    ImageView imageView2 = imageView;
                    if (!(imageView2 != null && imageView2.getWidth() == 0)) {
                        RecentlyPlayedItemBinder recentlyPlayedItemBinder = this.recentsBinder;
                        ImageView imageView3 = imageView;
                        recentlyPlayedItemBinder.setArtworkDrawable(imageView3, imageView3 != null ? imageView3.getWidth() : 0, recentlyPlayedItem);
                    } else {
                        ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
                        final ImageView imageView4 = imageView;
                        final DefaultCachedArtworkProvider defaultCachedArtworkProvider = this;
                        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.mp3.providers.DefaultCachedArtworkProvider.loadCachedArtwork.6.1
                            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                            public void onGlobalLayout() {
                                imageView4.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                RecentlyPlayedItemBinder recentlyPlayedItemBinder2 = defaultCachedArtworkProvider.recentsBinder;
                                ImageView imageView5 = imageView4;
                                recentlyPlayedItemBinder2.setArtworkDrawable(imageView5, imageView5.getWidth(), recentlyPlayedItem);
                            }
                        });
                    }
                }
            }, new Function1<Throwable, Unit>() { // from class: com.amazon.mp3.providers.DefaultCachedArtworkProvider.loadCachedArtwork.7
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Throwable error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    Log.error(DefaultCachedArtworkProvider.TAG, Intrinsics.stringPlus("Error retrieving recent item: ", error));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: loadCachedArtwork$lambda-4, reason: not valid java name */
    public static final void m1601loadCachedArtwork$lambda4(DefaultCachedArtworkProvider this$0, ContentMetadata metadata, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(metadata, "$metadata");
        observableEmitter.onNext(this$0.recentlyPlayedManager.toRecentItem((RecentsMetadata) metadata, false));
        observableEmitter.onComplete();
    }

    private final void loadCachedArtworkForUnknownSize(final ImageView imageView, final ContentMetadata metadata) {
        if (imageView.getWidth() == 0) {
            imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.mp3.providers.DefaultCachedArtworkProvider.loadCachedArtworkForUnknownSize.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    DefaultCachedArtworkProvider defaultCachedArtworkProvider = this;
                    defaultCachedArtworkProvider.loadCachedArtwork((Observable<Drawable>) defaultCachedArtworkProvider.getContentMetadataArtwork(imageView.getWidth(), metadata), imageView);
                }
            });
        } else {
            loadCachedArtwork(getContentMetadataArtwork(imageView.getWidth(), metadata), imageView);
        }
    }

    private final void loadSmartPlaylistCachedArtwork(final ImageView imageView, final Uri uri) {
        Observable observableObserveOn = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.providers.DefaultCachedArtworkProvider$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                DefaultCachedArtworkProvider.m1602loadSmartPlaylistCachedArtwork$lambda5(uri, imageView, observableEmitter);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Intrinsics.checkNotNullExpressionValue(observableObserveOn, "create<Drawable> { subsc…dSchedulers.mainThread())");
        RxKt.subscribe(observableObserveOn, this.compositeDisposable, new Function1<Drawable, Unit>() { // from class: com.amazon.mp3.providers.DefaultCachedArtworkProvider.loadSmartPlaylistCachedArtwork.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Drawable drawable) {
                invoke2(drawable);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final Drawable drawable) {
                if (imageView.getWidth() == 0) {
                    ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
                    final ImageView imageView2 = imageView;
                    viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.mp3.providers.DefaultCachedArtworkProvider.loadSmartPlaylistCachedArtwork.2.1
                        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                        public void onGlobalLayout() {
                            imageView2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            imageView2.setImageDrawable(drawable);
                        }
                    });
                    return;
                }
                imageView.setImageDrawable(drawable);
            }
        }, new Function1<Throwable, Unit>() { // from class: com.amazon.mp3.providers.DefaultCachedArtworkProvider.loadSmartPlaylistCachedArtwork.3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Log.error(DefaultCachedArtworkProvider.TAG, Intrinsics.stringPlus("Error retrieving/generating playlist image: ", it));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: loadSmartPlaylistCachedArtwork$lambda-5, reason: not valid java name */
    public static final void m1602loadSmartPlaylistCachedArtwork$lambda5(Uri uri, ImageView imageView, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(uri, "$uri");
        Intrinsics.checkNotNullParameter(imageView, "$imageView");
        observableEmitter.onNext(CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.PLAYLIST_SMART, MediaProvider.getSource(uri), imageView.getWidth(), String.valueOf(MediaProvider.SmartPlaylists.getPlaylistId(uri)), true));
        observableEmitter.onComplete();
    }

    private final void loadCachedArtwork(Observable<Drawable> imageObservable, final ImageView imageView, final ImageLoader.ImageLoaderCallback imageLoaderCallback) {
        Observable<Drawable> observableObserveOn = imageObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Intrinsics.checkNotNullExpressionValue(observableObserveOn, "imageObservable\n        …dSchedulers.mainThread())");
        RxKt.subscribe(observableObserveOn, this.compositeDisposable, new Function1<Drawable, Unit>() { // from class: com.amazon.mp3.providers.DefaultCachedArtworkProvider.loadCachedArtwork.8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Drawable drawable) {
                invoke2(drawable);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final Drawable drawable) {
                ImageView imageView2 = imageView;
                boolean z = false;
                if (imageView2 != null && imageView2.getWidth() == 0) {
                    z = true;
                }
                if (!z) {
                    imageLoaderCallback.onBitmapLoaded(this.convertDrawableToBitmap(imageView, drawable));
                    return;
                }
                ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
                if (viewTreeObserver == null) {
                    return;
                }
                final ImageView imageView3 = imageView;
                final DefaultCachedArtworkProvider defaultCachedArtworkProvider = this;
                final ImageLoader.ImageLoaderCallback imageLoaderCallback2 = imageLoaderCallback;
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.mp3.providers.DefaultCachedArtworkProvider.loadCachedArtwork.8.1
                    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                    public void onGlobalLayout() {
                        imageView3.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        imageLoaderCallback2.onBitmapLoaded(defaultCachedArtworkProvider.convertDrawableToBitmap(imageView3, drawable));
                    }
                });
            }
        }, new Function1<Throwable, Unit>() { // from class: com.amazon.mp3.providers.DefaultCachedArtworkProvider.loadCachedArtwork.9
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Log.error(DefaultCachedArtworkProvider.TAG, it.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadCachedArtwork(Observable<Drawable> imageObservable, final ImageView imageView) {
        Observable<Drawable> observableObserveOn = imageObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Intrinsics.checkNotNullExpressionValue(observableObserveOn, "imageObservable\n        …dSchedulers.mainThread())");
        RxKt.subscribe(observableObserveOn, this.compositeDisposable, new Function1<Drawable, Unit>() { // from class: com.amazon.mp3.providers.DefaultCachedArtworkProvider.loadCachedArtwork.10
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Drawable drawable) {
                invoke2(drawable);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final Drawable drawable) {
                ImageView imageView2 = imageView;
                boolean z = false;
                if (imageView2 != null && imageView2.getWidth() == 0) {
                    z = true;
                }
                if (z) {
                    ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
                    if (viewTreeObserver == null) {
                        return;
                    }
                    final ImageView imageView3 = imageView;
                    viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.mp3.providers.DefaultCachedArtworkProvider.loadCachedArtwork.10.1
                        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                        public void onGlobalLayout() {
                            imageView3.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            imageView3.setImageDrawable(drawable);
                        }
                    });
                    return;
                }
                ImageView imageView4 = imageView;
                if (imageView4 == null) {
                    return;
                }
                imageView4.setImageDrawable(drawable);
            }
        }, new Function1<Throwable, Unit>() { // from class: com.amazon.mp3.providers.DefaultCachedArtworkProvider.loadCachedArtwork.11
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Log.error(DefaultCachedArtworkProvider.TAG, it.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap convertDrawableToBitmap(ImageView imageView, Drawable drawable) {
        if (imageView != null) {
            imageView.setImageDrawable(drawable);
        }
        Drawable drawable2 = imageView == null ? null : imageView.getDrawable();
        if (drawable2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.BitmapDrawable");
        }
        Bitmap bitmap = ((BitmapDrawable) drawable2).getBitmap();
        imageView.setImageDrawable(null);
        Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
        return bitmap;
    }

    private final Integer getCollectionDetailHeaderArtworkSize() {
        Resources resources;
        Resources resources2;
        Resources resources3;
        StyleSheet styleSheet = this.styleSheet;
        ScreenSizeKey widthScreenSizeKey = styleSheet == null ? null : styleSheet.getWidthScreenSizeKey();
        int i = widthScreenSizeKey == null ? -1 : WhenMappings.$EnumSwitchMapping$0[widthScreenSizeKey.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            Activity activity = this.activity;
            if (activity == null || (resources = activity.getResources()) == null) {
                return null;
            }
            return Integer.valueOf(resources.getDimensionPixelSize(R.dimen.collection_detail_header_artwork_size_small_square));
        }
        if (i == 4 || i == 5) {
            Activity activity2 = this.activity;
            if (activity2 == null || (resources2 = activity2.getResources()) == null) {
                return null;
            }
            return Integer.valueOf(resources2.getDimensionPixelSize(R.dimen.collection_detail_header_artwork_size_medium_square));
        }
        Activity activity3 = this.activity;
        if (activity3 == null || (resources3 = activity3.getResources()) == null) {
            return null;
        }
        return Integer.valueOf(resources3.getDimensionPixelSize(R.dimen.collection_detail_header_artwork_size_large_square));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Observable<Drawable> getContentMetadataArtwork(final int size, final ContentMetadata metadata) {
        Observable<Drawable> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.providers.DefaultCachedArtworkProvider$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                DefaultCachedArtworkProvider.m1600getContentMetadataArtwork$lambda8(metadata, this, size, observableEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …er.onComplete()\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getContentMetadataArtwork$lambda-8, reason: not valid java name */
    public static final void m1600getContentMetadataArtwork$lambda8(ContentMetadata metadata, DefaultCachedArtworkProvider this$0, int i, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(metadata, "$metadata");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Drawable primePlaylistTrackArtworkForMetadata = null;
        if (metadata instanceof PlaylistMetadata) {
            primePlaylistTrackArtworkForMetadata = this$0.getPlaylistArtworkForMetadata(i, (PlaylistMetadata) metadata);
        } else if (metadata instanceof AlbumMetadata) {
            Long cachedImageId = ((AlbumMetadata) metadata).getCachedImageId();
            if (cachedImageId != null) {
                primePlaylistTrackArtworkForMetadata = this$0.getAlbumArtworkForCachedImageId(i, cachedImageId.longValue());
            }
        } else if (metadata instanceof ArtistMetadata) {
            String artistId = ((ArtistMetadata) metadata).getArtistId();
            if (artistId != null) {
                primePlaylistTrackArtworkForMetadata = this$0.getArtistArtworkForCachedImageId(i, artistId);
            }
        } else if (metadata instanceof TrackMetadata) {
            TrackMetadata trackMetadata = (TrackMetadata) metadata;
            Long cachedImageId2 = trackMetadata.getCachedImageId();
            if (cachedImageId2 != null && cachedImageId2.longValue() != 0) {
                primePlaylistTrackArtworkForMetadata = this$0.getAlbumArtworkForCachedImageId(i, cachedImageId2.longValue());
            } else {
                primePlaylistTrackArtworkForMetadata = this$0.getPrimePlaylistTrackArtworkForMetadata(i, trackMetadata);
            }
        }
        if (primePlaylistTrackArtworkForMetadata != null) {
            observableEmitter.onNext(this$0.cropDrawable(primePlaylistTrackArtworkForMetadata, metadata));
        } else {
            observableEmitter.onError(new NullPointerException("Artwork drawable is null, metadata type is " + ((Object) metadata.getClass().getSimpleName()) + " and its uri is " + metadata.getUri()));
        }
        observableEmitter.onComplete();
    }

    private final Drawable cropDrawable(Drawable drawable, ContentMetadata metadata) {
        Activity activity = this.activity;
        Context applicationContext = activity == null ? null : activity.getApplicationContext();
        if (applicationContext != null && (drawable instanceof BitmapDrawable)) {
            if (metadata instanceof TrackMetadata ? true : metadata instanceof PlaylistMetadata ? true : metadata instanceof AlbumMetadata) {
                drawable = BitmapUtil.cropDrawableAsRoundedCornerRect(applicationContext, (BitmapDrawable) drawable);
            } else if (metadata instanceof ArtistMetadata) {
                drawable = BitmapUtil.cropDrawableAsCircle(applicationContext, (BitmapDrawable) drawable);
            } else {
                drawable = (BitmapDrawable) drawable;
            }
            Intrinsics.checkNotNullExpressionValue(drawable, "{\n            when (meta…e\n            }\n        }");
        }
        return drawable;
    }

    private final Drawable getArtistArtworkForCachedImageId(int size, String cachedImageId) {
        return CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.ARTIST, size, cachedImageId, true);
    }

    private final Drawable getAlbumArtworkForCachedImageId(int size, long cachedImageId) {
        return CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.ALBUM, size, String.valueOf(cachedImageId), true);
    }

    private final Drawable getPrimePlaylistTrackArtworkForMetadata(int size, TrackMetadata metadata) {
        CacheManager cacheManager = CacheManager.getInstance();
        ImageLoaderFactory.ItemType itemType = ImageLoaderFactory.ItemType.PRIME_PLAYLIST_TRACK;
        String source = metadata.getSource();
        String asin = metadata.getAsin();
        return cacheManager.getOnCurrentThread(itemType, source, size, String.valueOf(asin == null ? 0 : asin.hashCode()), true);
    }

    private final Drawable getPlaylistArtworkForMetadata(int size, PlaylistMetadata metadata) {
        if (MediaProvider.PrimePlaylists.isPrimePlaylist(metadata.getUri())) {
            return CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL, "cirrus", size, metadata.getAsin(), true);
        }
        return getLibraryPlaylistArtwork(size, metadata);
    }

    private final Drawable getLibraryPlaylistArtwork(int size, PlaylistMetadata metadata) {
        Uri uri = metadata.getUri();
        if (!MediaProvider.UdoPlaylists.isUdoPlaylist(uri)) {
            return CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.PLAYLIST_SMART, MediaProvider.getSource(metadata.getUri()), size, String.valueOf(MediaProvider.Playlists.getPlaylistId(uri)), true);
        }
        Drawable onCurrentThread = CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.PLAYLIST_UDO_THUMBNAIL, "cirrus", size, String.valueOf(MediaProvider.Playlists.getPlaylistId(uri)), true);
        return onCurrentThread == null ? CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.PLAYLIST_UDO, MediaProvider.getSource(metadata.getUri()), size, String.valueOf(MediaProvider.Playlists.getPlaylistId(uri)), true) : onCurrentThread;
    }

    /* JADX INFO: compiled from: DefaultCachedArtworkProvider.kt */
    @Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u001a\u0010\r\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016R\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0019\u001a\u00020\u00188\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001f"}, d2 = {"Lcom/amazon/mp3/providers/DefaultCachedArtworkProvider$LifecycleCallback;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "Landroid/app/Activity;", "p0", "", "onActivityPaused", "onActivityResumed", "onActivityStarted", "onActivityDestroyed", "Landroid/os/Bundle;", "p1", "onActivitySaveInstanceState", "onActivityStopped", "onActivityCreated", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "getCompositeDisposable", "()Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "Lcom/amazon/mp3/recentlyplayed/RecentlyPlayedItemBinder;", "recentsBinder", "Lcom/amazon/mp3/recentlyplayed/RecentlyPlayedItemBinder;", "getRecentsBinder", "()Lcom/amazon/mp3/recentlyplayed/RecentlyPlayedItemBinder;", "", "hashCode", "I", "getHashCode", "()I", "<init>", "(Lio/reactivex/rxjava3/disposables/CompositeDisposable;Lcom/amazon/mp3/recentlyplayed/RecentlyPlayedItemBinder;I)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class LifecycleCallback implements Application.ActivityLifecycleCallbacks {
        private final CompositeDisposable compositeDisposable;
        private final int hashCode;
        private final RecentlyPlayedItemBinder recentsBinder;

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity p0, Bundle p1) {
            Intrinsics.checkNotNullParameter(p0, "p0");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity p0, Bundle p1) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            Intrinsics.checkNotNullParameter(p1, "p1");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
        }

        public LifecycleCallback(CompositeDisposable compositeDisposable, RecentlyPlayedItemBinder recentlyPlayedItemBinder, int i) {
            this.compositeDisposable = compositeDisposable;
            this.recentsBinder = recentlyPlayedItemBinder;
            this.hashCode = i;
        }

        public final int getHashCode() {
            return this.hashCode;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            if (p0.hashCode() == this.hashCode) {
                CompositeDisposable compositeDisposable = this.compositeDisposable;
                if (compositeDisposable != null) {
                    compositeDisposable.dispose();
                }
                RecentlyPlayedItemBinder recentlyPlayedItemBinder = this.recentsBinder;
                if (recentlyPlayedItemBinder == null) {
                    return;
                }
                recentlyPlayedItemBinder.clearBinding();
            }
        }
    }
}
