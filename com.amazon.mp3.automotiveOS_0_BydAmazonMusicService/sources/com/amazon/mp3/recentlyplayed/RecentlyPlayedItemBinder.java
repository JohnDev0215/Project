package com.amazon.mp3.recentlyplayed;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.BrowseViewsFactory;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.recentlyplayed.ShovelerImageLoader;
import com.amazon.mp3.util.BitmapUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: loaded from: classes4.dex */
public class RecentlyPlayedItemBinder implements ShovelerImageLoader.Listener {
    private CompositeSubscription fetchArtworkSubscription;
    private final Context mContext;
    private Map<String, RecentlyPlayedItem.Type> mEntityTypeMap;
    private List<ImageView> mImages;
    private final ShovelerImageLoader mShovelerImageLoader;
    private CompositeSubscription subscription;

    public RecentlyPlayedItemBinder(final Context mContext) {
        this(mContext, BrowseViewsFactory.getImageLoaderByContext(mContext));
    }

    public RecentlyPlayedItemBinder(final Context context, final ShovelerImageLoader shovelerImageLoader) {
        this.subscription = new CompositeSubscription();
        this.fetchArtworkSubscription = new CompositeSubscription();
        this.mContext = context.getApplicationContext();
        this.mShovelerImageLoader = shovelerImageLoader;
        shovelerImageLoader.setListener(this);
        this.mImages = new ArrayList();
        this.mEntityTypeMap = new HashMap();
    }

    public void clearBinding() {
        this.mShovelerImageLoader.close();
        this.mImages = null;
        this.mEntityTypeMap = null;
        CompositeSubscription compositeSubscription = this.subscription;
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            this.subscription.unsubscribe();
        }
        CompositeSubscription compositeSubscription2 = this.fetchArtworkSubscription;
        if (compositeSubscription2 == null || compositeSubscription2.isUnsubscribed()) {
            return;
        }
        this.fetchArtworkSubscription.unsubscribe();
    }

    public void setArtworkDrawable(final ImageView imageView, final int imageWidth, final RecentlyPlayedItem recentlyPlayedItem) {
        if (!this.mImages.contains(imageView)) {
            this.mImages.add(imageView);
            this.mEntityTypeMap.put(recentlyPlayedItem.getCollectionId(), recentlyPlayedItem.getItemType());
        }
        final String source = MediaProvider.getSource(recentlyPlayedItem.getContentUri());
        final ImageLoaderFactory.ItemType imageType = getImageType(recentlyPlayedItem);
        this.fetchArtworkSubscription.add(Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.recentlyplayed.RecentlyPlayedItemBinder$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$setArtworkDrawable$0(imageType, source, imageWidth, recentlyPlayedItem, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Drawable>() { // from class: com.amazon.mp3.recentlyplayed.RecentlyPlayedItemBinder.1
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(final Throwable throwable) {
            }

            @Override // rx.Observer
            public void onNext(final Drawable artworkDrawable) {
                RecentlyPlayedItemBinder.this.setImageViewArtwork(imageView, recentlyPlayedItem, artworkDrawable);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setArtworkDrawable$0(ImageLoaderFactory.ItemType itemType, String str, int i, RecentlyPlayedItem recentlyPlayedItem, Subscriber subscriber) {
        subscriber.onNext(this.mShovelerImageLoader.loadImage(itemType, str, i, recentlyPlayedItem.getCollectionId()));
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setImageViewArtwork(final ImageView imageView, final RecentlyPlayedItem recentlyPlayedItem, final Drawable artworkDrawable) {
        if (imageView == null || recentlyPlayedItem == null) {
            return;
        }
        if ((recentlyPlayedItem.getItemType().equals(RecentlyPlayedItem.Type.ARTIST) || recentlyPlayedItem.getItemType().equals(RecentlyPlayedItem.Type.PRIME_ARTIST)) && (artworkDrawable instanceof BitmapDrawable)) {
            setCircularImageForArtist((BitmapDrawable) artworkDrawable, imageView);
        } else {
            imageView.setImageDrawable(artworkDrawable);
        }
        imageView.setTag(recentlyPlayedItem.getCollectionId());
    }

    @Override // com.amazon.mp3.recentlyplayed.ShovelerImageLoader.Listener
    public void onImageLoaded(final Drawable drawable, final String source, final String id) {
        for (ImageView imageView : this.mImages) {
            if (id.equals(imageView.getTag())) {
                if ((RecentlyPlayedItem.Type.ARTIST.equals(this.mEntityTypeMap.get(id)) || RecentlyPlayedItem.Type.PRIME_ARTIST.equals(this.mEntityTypeMap.get(id))) && (drawable instanceof BitmapDrawable)) {
                    setCircularImageForArtist((BitmapDrawable) drawable, imageView);
                } else {
                    imageView.setImageDrawable(drawable);
                }
            }
        }
    }

    public ImageLoaderFactory.ItemType getImageType(final RecentlyPlayedItem recentlyPlayedItem) {
        switch (AnonymousClass2.$SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[recentlyPlayedItem.getItemType().ordinal()]) {
            case 1:
                if (recentlyPlayedItem.isShuffleOn()) {
                    return ImageLoaderFactory.ItemType.ALL_SONGS;
                }
                return ImageLoaderFactory.ItemType.ALBUM;
            case 2:
                return ImageLoaderFactory.ItemType.ALBUM;
            case 3:
            case 4:
                return ImageLoaderFactory.ItemType.ARTIST;
            case 5:
                return ImageLoaderFactory.ItemType.GENRE;
            case 6:
                return ImageLoaderFactory.ItemType.PLAYLIST_UDO;
            case 7:
                return ImageLoaderFactory.ItemType.PLAYLIST_SMART;
            case 8:
                return ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL;
            case 9:
                return ImageLoaderFactory.ItemType.ALBUM;
            case 10:
            case 11:
                return ImageLoaderFactory.ItemType.ALEXA_RECENTS;
            default:
                throw new IllegalArgumentException();
        }
    }

    public String getSecondaryTitle(final RecentlyPlayedItem recentlyPlayedItem) {
        Resources resources = this.mContext.getResources();
        int i = AnonymousClass2.$SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[recentlyPlayedItem.getItemType().ordinal()];
        if (i != 12) {
            switch (i) {
                case 1:
                    return resources.getString(R.string.dmusic_recently_played_track);
                case 2:
                    return resources.getString(R.string.dmusic_recently_played_album);
                case 3:
                case 4:
                    return resources.getString(R.string.dmusic_recently_played_artist);
                case 5:
                    return resources.getString(R.string.dmusic_recently_played_genre);
                case 6:
                case 7:
                    return resources.getString(R.string.dmusic_recently_played_playlist);
                case 8:
                    return resources.getString(R.string.dmusic_recently_played_prime_playlist);
                case 9:
                    return getPrimeBrowseSecondaryTitle(recentlyPlayedItem.getContentUri().buildUpon().appendPath("tracks").build());
                default:
                    return "";
            }
        }
        return this.mContext.getResources().getString(R.string.dmusic_library_item_name_station);
    }

    public String getPrimeBrowseSecondaryTitle(Uri tracksContentUri) {
        Resources resources = this.mContext.getResources();
        switch (AnonymousClass2.$SwitchMap$com$amazon$mp3$library$provider$MediaProvider$PrimeBrowseTracksCollection$ContentSource[MediaProvider.PrimeBrowseTracksCollection.getContentSource(tracksContentUri).ordinal()]) {
            case 1:
                return resources.getString(R.string.dmusic_recently_played_artist);
            case 2:
                return resources.getString(R.string.dmusic_recently_played_search_results);
            case 3:
                return resources.getString(R.string.dmusic_new_to_prime_songs_card);
            case 4:
                return resources.getString(R.string.dmusic_popular_prime_songs_card);
            case 5:
            case 6:
                return resources.getString(R.string.dmusic_recommended_prime_songs_card);
            default:
                return resources.getString(R.string.dmusic_library_item_name_track);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.recentlyplayed.RecentlyPlayedItemBinder$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$library$provider$MediaProvider$PrimeBrowseTracksCollection$ContentSource;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type;

        static {
            int[] iArr = new int[MediaProvider.PrimeBrowseTracksCollection.ContentSource.values().length];
            $SwitchMap$com$amazon$mp3$library$provider$MediaProvider$PrimeBrowseTracksCollection$ContentSource = iArr;
            try {
                iArr[MediaProvider.PrimeBrowseTracksCollection.ContentSource.ARTIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$MediaProvider$PrimeBrowseTracksCollection$ContentSource[MediaProvider.PrimeBrowseTracksCollection.ContentSource.SEARCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$MediaProvider$PrimeBrowseTracksCollection$ContentSource[MediaProvider.PrimeBrowseTracksCollection.ContentSource.NEW_TO_PRIME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$MediaProvider$PrimeBrowseTracksCollection$ContentSource[MediaProvider.PrimeBrowseTracksCollection.ContentSource.POPULAR_IN_PRIME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$MediaProvider$PrimeBrowseTracksCollection$ContentSource[MediaProvider.PrimeBrowseTracksCollection.ContentSource.RECOMMENDED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$MediaProvider$PrimeBrowseTracksCollection$ContentSource[MediaProvider.PrimeBrowseTracksCollection.ContentSource.SIMILAR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr2 = new int[RecentlyPlayedItem.Type.values().length];
            $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type = iArr2;
            try {
                iArr2[RecentlyPlayedItem.Type.ALL_SONGS.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.ALBUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.ARTIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.PRIME_ARTIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.GENRE.ordinal()] = 5;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.PLAYLIST.ordinal()] = 6;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.SMART_PLAYLIST.ordinal()] = 7;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.PRIME_PLAYLIST.ordinal()] = 8;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.PRIME_BROWSE_SONGS.ordinal()] = 9;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.CASTING_RECENTS.ordinal()] = 10;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.ALEXA_RECENTS.ordinal()] = 11;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.STATION.ordinal()] = 12;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    private void setCircularImageForArtist(final BitmapDrawable drawable, final ImageView imageView) {
        imageView.setBackgroundColor(0);
        this.subscription.add(BitmapUtil.cropDrawableAsCircle(this.mContext, drawable, imageView));
    }
}
