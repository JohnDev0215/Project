package com.amazon.mp3.library.cache.image.loader;

import com.amazon.mp3.library.cache.image.ImageMetadata;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.Genre;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.task.JobContext;
import com.amazon.mp3.util.CompositeImageGenerator;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class ImageLoaderFactory {
    private static ImageLoaderFactory sInstance;
    private HashMap<ItemType, DefaultImageLoader> mDefaultImageLoaderMap = new HashMap<>();
    private HashMap<ItemType, ImageLoader> mImageLoaderMap = new HashMap<>();
    private Map<String, ItemType> mItemTypeNameMapping = new HashMap();
    private static HashSet<ItemType> sTypes = new HashSet<>();
    public static final CompositeImageGenerator COMPOSITE_IMAGE_GENERATOR = new CompositeImageGenerator(0.5f, 1.0f, 46.0f, 30.0f, 46.0f, 84.0f, 70, -2);

    public interface DefaultImageLoader extends ImageLoader {
        void generateImages();

        JobContext<ImageMetadata> getImage(JobContext<ImageMetadata> jobContext, boolean forceLoad);

        @Override // com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
        /* synthetic */ int getMaxImageSize();
    }

    public interface ImageLoader {
        String getCacheFileName(ItemType type, String source, int size, String id);

        JobContext<ImageMetadata> getImage(JobContext<ImageMetadata> jobContext);

        int getMaxImageSize();

        void removeAll(ItemType type, String source);

        void removeIds(ItemType type, String source, Collection<String> idsToRemove, boolean skipCms);
    }

    public enum ItemType {
        ALBUM(1),
        ARTIST(2),
        CONTRIBUTOR(3),
        PLAYLIST_UDO(4),
        PLAYLIST_SMART(5),
        GENRE(6),
        SONG(7),
        ARTIST_HERO(8),
        PRIME_PLAYLIST_BANNER(9),
        PRIME_PLAYLIST_THUMBNAIL(10),
        PRIME_PLAYLIST_TRACK(11),
        ALL_SONGS(12),
        ALEXA_RECENTS(13),
        PLAYLIST(14),
        CASTING_RECENTS(14),
        PLAYLIST_UDO_BANNER(15),
        PLAYLIST_UDO_THUMBNAIL(16);

        private int mIntValue;

        ItemType(int intValue) {
            this.mIntValue = intValue;
            ImageLoaderFactory.sTypes.add(this);
        }

        public int intValue() {
            return this.mIntValue;
        }

        public static ItemType getItemType(int value) {
            for (ItemType itemType : values()) {
                if (value == itemType.intValue()) {
                    return itemType;
                }
            }
            return null;
        }

        public boolean isComposite() {
            int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ordinal()];
            return i == 1 || i == 2 || i == 3 || i == 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ImageLoader getImageLoader() {
            switch (AnonymousClass1.$SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ordinal()]) {
                case 1:
                case 2:
                case 8:
                    return new PlaylistImageLoader(this);
                case 3:
                    return new GenreImageLoader(this);
                case 4:
                    return new AllSongsImageLoader(this);
                case 5:
                    return new ArtistImageLoader(this);
                case 6:
                    return new ArtistHeroImageLoader(this);
                case 7:
                    return new ContributorImageLoader(this);
                case 9:
                    return new AlbumImageLoader(this);
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                    return new PrimePlaylistImageLoader(this);
                case 15:
                case 16:
                    return new AlexaRecentsImageLoader(this);
                default:
                    return new NoOpImageLoader(this);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public DefaultImageLoader getDefaultImageLoader() {
            switch (AnonymousClass1.$SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 7:
                case 8:
                case 9:
                case 11:
                case 12:
                    return new DefaultGenericImageLoader(this);
                case 6:
                    return new DefaultArtistHeroImageLoader(this);
                case 10:
                    return new DefaultPlaylistImageLoader(this);
                case 13:
                case 14:
                default:
                    return new NoOpImageLoader(this);
                case 15:
                case 16:
                    return new AlexaRecentsImageLoader(this);
            }
        }

        public static Set<ItemType> types() {
            return ImageLoaderFactory.sTypes;
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType;

        static {
            int[] iArr = new int[ItemType.values().length];
            $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType = iArr;
            try {
                iArr[ItemType.PLAYLIST_SMART.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ItemType.PLAYLIST_UDO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ItemType.GENRE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ItemType.ALL_SONGS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ItemType.ARTIST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ItemType.ARTIST_HERO.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ItemType.CONTRIBUTOR.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ItemType.PLAYLIST.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ItemType.ALBUM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ItemType.PRIME_PLAYLIST_BANNER.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ItemType.PRIME_PLAYLIST_THUMBNAIL.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ItemType.PRIME_PLAYLIST_TRACK.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ItemType.PLAYLIST_UDO_BANNER.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ItemType.PLAYLIST_UDO_THUMBNAIL.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ItemType.ALEXA_RECENTS.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ItemType.CASTING_RECENTS.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    public static synchronized ImageLoaderFactory getInstance() {
        if (sInstance == null) {
            sInstance = new ImageLoaderFactory();
        }
        return sInstance;
    }

    private ImageLoaderFactory() {
        for (ItemType itemType : ItemType.values()) {
            this.mDefaultImageLoaderMap.put(itemType, itemType.getDefaultImageLoader());
        }
        this.mItemTypeNameMapping.put(new Playlist().getItemTypeName(), ItemType.PLAYLIST);
        Map<String, ItemType> map = this.mItemTypeNameMapping;
        String itemTypeName = new MusicTrack().getItemTypeName();
        ItemType itemType2 = ItemType.ALBUM;
        map.put(itemTypeName, itemType2);
        this.mItemTypeNameMapping.put(new Artist().getItemTypeName(), ItemType.ARTIST);
        this.mItemTypeNameMapping.put(new Album().getItemTypeName(), itemType2);
        this.mItemTypeNameMapping.put(new Genre().getItemTypeName(), ItemType.GENRE);
        this.mItemTypeNameMapping.put(new CatalogPlaylist().getItemTypeName(), ItemType.PRIME_PLAYLIST_THUMBNAIL);
    }

    public ImageLoader getImageLoader(ItemType type) {
        if (!this.mImageLoaderMap.containsKey(type)) {
            ImageLoader imageLoader = type.getImageLoader();
            this.mImageLoaderMap.put(type, imageLoader);
            return imageLoader;
        }
        return this.mImageLoaderMap.get(type);
    }

    public DefaultImageLoader getDefaultImageLoader(ItemType type) {
        return this.mDefaultImageLoaderMap.get(type);
    }

    public void regenerateDefaultImages() {
        Iterator<ItemType> it = this.mDefaultImageLoaderMap.keySet().iterator();
        while (it.hasNext()) {
            this.mDefaultImageLoaderMap.get(it.next()).generateImages();
        }
    }
}
