package com.amazon.mp3.auto.provider;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.music.station.StationItem;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class AutoItem {
    private String mAsin;
    private Uri mContentUri;
    private boolean mIsRecentlyPlayed;
    private RecentlyPlayedItem mRecentlyPlayedItem;
    private StationItem mStationItem;
    private String mTitle;
    private JSONObject mTrackProviderState;
    private AutoItemType mType;

    public AutoItem(Uri contentUri, String title, AutoItemType type) {
        this(contentUri, title, type, (StationItem) null);
    }

    public AutoItem(Uri contentUri, String asin, String title, AutoItemType type) {
        this(contentUri, asin, title, type, null);
    }

    public AutoItem(Uri contentUri, String title, AutoItemType type, StationItem stationItem) {
        this.mContentUri = contentUri;
        this.mTitle = title;
        this.mType = type;
        this.mIsRecentlyPlayed = false;
        this.mStationItem = stationItem;
    }

    public AutoItem(Uri contentUri, String asin, String title, AutoItemType type, StationItem stationItem) {
        this.mContentUri = contentUri;
        this.mAsin = asin;
        this.mTitle = title;
        this.mType = type;
        this.mIsRecentlyPlayed = false;
        this.mStationItem = stationItem;
    }

    public StationItem getStationItem() {
        return this.mStationItem;
    }

    public Uri getContentUri() {
        return this.mContentUri;
    }

    public String getAsin() {
        return this.mAsin;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public AutoItemType getType() {
        return this.mType;
    }

    public JSONObject getTrackProviderState() {
        return this.mTrackProviderState;
    }

    public boolean isRecentlyPlayed() {
        return this.mIsRecentlyPlayed;
    }

    public RecentlyPlayedItem getRecentlyPlayedItem() {
        return this.mRecentlyPlayedItem;
    }

    public enum AutoItemType {
        PRIME_STATION,
        PRIME_PLAYLIST,
        CLOUD_PLAYLIST,
        CLOUD_GENRE,
        SHUFFLE_ALL_TRACKS,
        ARTIST,
        ALBUM,
        TRACK;

        public String getDisplayName(Context context) {
            Resources resources = context.getResources();
            switch (AnonymousClass1.$SwitchMap$com$amazon$mp3$auto$provider$AutoItem$AutoItemType[ordinal()]) {
                case 1:
                    return resources.getString(R.string.dmusic_library_item_name_station);
                case 2:
                    return resources.getString(R.string.dmusic_recently_played_prime_playlist);
                case 3:
                    return resources.getString(R.string.dmusic_library_item_name_playlist);
                case 4:
                    return resources.getString(R.string.dmusic_library_item_name_genre);
                case 5:
                    return resources.getString(R.string.dmusic_library_item_name_artist);
                case 6:
                    return resources.getString(R.string.dmusic_library_item_name_album);
                case 7:
                    return resources.getString(R.string.dmusic_library_item_name_track);
                default:
                    return null;
            }
        }

        public static ImageLoaderFactory.ItemType toImageItemType(AutoItemType itemType) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$auto$provider$AutoItem$AutoItemType[itemType.ordinal()];
            if (i == 2) {
                return ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL;
            }
            if (i == 3) {
                return ImageLoaderFactory.ItemType.PLAYLIST_UDO;
            }
            if (i == 4) {
                return ImageLoaderFactory.ItemType.GENRE;
            }
            if (i == 5) {
                return ImageLoaderFactory.ItemType.ARTIST;
            }
            if (i == 6) {
                return ImageLoaderFactory.ItemType.ALBUM;
            }
            if (i != 8) {
                return null;
            }
            return ImageLoaderFactory.ItemType.ALL_SONGS;
        }

        public static AutoItemType toAutoItemType(RecentlyPlayedItem.Type type) {
            switch (AnonymousClass1.$SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[type.ordinal()]) {
                case 1:
                    return PRIME_STATION;
                case 2:
                    return PRIME_PLAYLIST;
                case 3:
                    return CLOUD_PLAYLIST;
                case 4:
                    return CLOUD_GENRE;
                case 5:
                    return ARTIST;
                case 6:
                    return ALBUM;
                case 7:
                case 8:
                    return TRACK;
                default:
                    return SHUFFLE_ALL_TRACKS;
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.auto.provider.AutoItem$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$auto$provider$AutoItem$AutoItemType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type;

        static {
            int[] iArr = new int[RecentlyPlayedItem.Type.values().length];
            $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type = iArr;
            try {
                iArr[RecentlyPlayedItem.Type.STATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.PRIME_PLAYLIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.PLAYLIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.GENRE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.ARTIST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.ALBUM.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.PRIME_BROWSE_SONGS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.ALL_SONGS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr2 = new int[AutoItemType.values().length];
            $SwitchMap$com$amazon$mp3$auto$provider$AutoItem$AutoItemType = iArr2;
            try {
                iArr2[AutoItemType.PRIME_STATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$mp3$auto$provider$AutoItem$AutoItemType[AutoItemType.PRIME_PLAYLIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$mp3$auto$provider$AutoItem$AutoItemType[AutoItemType.CLOUD_PLAYLIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$mp3$auto$provider$AutoItem$AutoItemType[AutoItemType.CLOUD_GENRE.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$mp3$auto$provider$AutoItem$AutoItemType[AutoItemType.ARTIST.ordinal()] = 5;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$mp3$auto$provider$AutoItem$AutoItemType[AutoItemType.ALBUM.ordinal()] = 6;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$mp3$auto$provider$AutoItem$AutoItemType[AutoItemType.TRACK.ordinal()] = 7;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$mp3$auto$provider$AutoItem$AutoItemType[AutoItemType.SHUFFLE_ALL_TRACKS.ordinal()] = 8;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }
}
