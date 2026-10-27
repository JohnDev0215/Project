package com.amazon.mp3.recentlyplayed.sharing;

import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.playlist.api.PlaylistFactory;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.sharing.ShareProviderActionHandler;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.music.share.AlbumShareProvider;
import com.amazon.music.share.ArtistShareProvider;
import com.amazon.music.share.PlaylistShareProvider;
import com.amazon.music.share.ShareProvider;
import com.amazon.music.share.StationShareProvider;
import com.amazon.music.share.UserPlaylistShareProvider;

/* JADX INFO: loaded from: classes4.dex */
public class RecentlyPlayedShareProviderHelper {
    public static ShareProvider getShareProvider(RecentlyPlayedItem recentlyPlayedItem, FragmentActivity activity) {
        String artworkUrlForAsin;
        String musicDomain = EndpointsProvider.get().getMusicDomain();
        switch (AnonymousClass1.$SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[recentlyPlayedItem.getItemType().ordinal()]) {
            case 1:
                Album album = AmazonApplication.getLibraryItemFactory().getAlbum(recentlyPlayedItem.getContentUri());
                if (recentlyPlayedItem.getArtworkImageUrl() != null) {
                    artworkUrlForAsin = recentlyPlayedItem.getArtworkImageUrl();
                } else {
                    artworkUrlForAsin = new CTAPrimeCache(activity).getArtworkUrlForAsin(album.getAsin());
                }
                return new AlbumShareProvider(activity, musicDomain, album.getAsin(), album.getTitle(), album.getArtistName(), artworkUrlForAsin);
            case 2:
                return new PlaylistShareProvider(activity, musicDomain, recentlyPlayedItem.getCollectionAsin(), recentlyPlayedItem.getTitle(), recentlyPlayedItem.getArtworkImageUrl());
            case 3:
                return new StationShareProvider(activity, musicDomain, recentlyPlayedItem.getCollectionId(), recentlyPlayedItem.getTitle(), recentlyPlayedItem.getArtworkImageUrl());
            case 4:
            case 5:
                return new ArtistShareProvider(activity, musicDomain, recentlyPlayedItem.getCollectionAsin(), recentlyPlayedItem.getTitle(), recentlyPlayedItem.getArtworkImageUrl());
            case 6:
                return buildUserPlaylistShareProvider(activity, musicDomain, recentlyPlayedItem);
            default:
                return null;
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.recentlyplayed.sharing.RecentlyPlayedShareProviderHelper$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type;

        static {
            int[] iArr = new int[RecentlyPlayedItem.Type.values().length];
            $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type = iArr;
            try {
                iArr[RecentlyPlayedItem.Type.ALBUM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.PRIME_PLAYLIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.STATION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.ARTIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.PRIME_ARTIST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.PLAYLIST.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private static ShareProvider buildUserPlaylistShareProvider(FragmentActivity activity, String domainName, RecentlyPlayedItem recentlyPlayedItem) {
        if (!ConnectivityUtil.checkConnectivityAndShowDialog(activity)) {
            return null;
        }
        Playlist playlist = AmazonApplication.getLibraryItemFactory().getPlaylist(recentlyPlayedItem.getContentUri());
        return new UserPlaylistShareProvider(activity, domainName, playlist.getLuid(), playlist.getName(), playlist.getVersion(), PlaylistFactory.createPlaylistLibrary(activity), ShareProviderActionHandler.newInstance(), recentlyPlayedItem.getArtworkImageUrl());
    }
}
