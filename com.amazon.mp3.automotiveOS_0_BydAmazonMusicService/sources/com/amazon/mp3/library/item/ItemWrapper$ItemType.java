package com.amazon.mp3.library.item;

import android.net.Uri;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public enum ItemWrapper$ItemType {
    ALBUM,
    ARTIST,
    UDO_PLAYLIST,
    SMART_PLAYLIST,
    PRIME_PLAYLIST,
    SONG,
    GENRE;

    private static final String TAG = ItemWrapper$ItemType.class.getSimpleName();

    public Uri getUriForCid(String cid) {
        return getUriForId("cirrus", cid);
    }

    public Uri getUriForLcid(String lcid) {
        return getUriForId("cirrus-local", lcid);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Uri getUriForId(String source, String id) {
        long j;
        if (this != SONG) {
            try {
                j = Long.parseLong(id);
            } catch (NumberFormatException unused) {
                return null;
            }
        } else {
            j = -1;
        }
        switch (ItemWrapper$1.$SwitchMap$com$amazon$mp3$library$item$ItemWrapper$ItemType[ordinal()]) {
            case 1:
                return MediaProvider.Albums.getContentUri(source, j);
            case 2:
                return MediaProvider.Artists.getContentUri(source, j);
            case 3:
                return MediaProvider.UdoPlaylists.getContentUri(source, j);
            case 4:
                return MediaProvider.SmartPlaylists.getContentUri(source, j);
            case 5:
                return MediaProvider.PrimePlaylists.getContentUri(source, id);
            case 6:
                return MediaProvider.Tracks.getContentUri(source, id);
            case 7:
                return MediaProvider.Genres.getContentUri(source, j);
            default:
                return null;
        }
    }

    public static ItemWrapper$ItemType getTypeForUri(Uri uri) {
        int iMatch = DefaultUriMatcher.match(uri);
        if (iMatch != 2 && iMatch != 19) {
            if (iMatch != 33) {
                if (iMatch == 4 || iMatch == 5) {
                    return ALBUM;
                }
                switch (iMatch) {
                    case 8:
                    case 9:
                    case 10:
                        return ARTIST;
                    default:
                        switch (iMatch) {
                            case 13:
                            case 15:
                                return SMART_PLAYLIST;
                            case 14:
                            case 16:
                                return UDO_PLAYLIST;
                            case 17:
                                break;
                            default:
                                switch (iMatch) {
                                    case 25:
                                    case 26:
                                    case 27:
                                        return GENRE;
                                    case 28:
                                    case 29:
                                        break;
                                    default:
                                        Log.error(TAG, "Got a URI (%s) that I can't match to this ItemType: (%d)", uri, Integer.valueOf(DefaultUriMatcher.match(uri)));
                                        return null;
                                }
                                break;
                        }
                        break;
                }
            }
            return PRIME_PLAYLIST;
        }
        return SONG;
    }
}
