package com.amazon.music.curate.skyfire.dagger;

import com.amazon.music.curate.skyfire.local.CreateNewPlaylistSkill;
import com.amazon.music.curate.skyfire.local.LocalSkill;
import com.amazon.music.curate.skyfire.local.PlayAlbumSkill;
import com.amazon.music.curate.skyfire.local.PlayArtistSkill;
import com.amazon.music.curate.skyfire.local.PlayCatalogPlaylistSkill;
import com.amazon.music.curate.skyfire.local.PlayLibraryPlaylistSkill;
import com.amazon.music.curate.skyfire.local.PlayStationSkill;
import com.amazon.music.curate.skyfire.local.ShowAlbumsSkill;
import com.amazon.music.curate.skyfire.local.ShowArtistsSkill;
import com.amazon.music.curate.skyfire.local.ShowBrowseHomeSkill;
import com.amazon.music.curate.skyfire.local.ShowCatalogPlaylistSkill;
import com.amazon.music.curate.skyfire.local.ShowImportPlaylistsSkill;
import com.amazon.music.curate.skyfire.local.ShowLibraryPlaylistSkill;
import com.amazon.music.curate.skyfire.local.ShowPlaylistsSkill;
import com.amazon.music.curate.skyfire.local.ShowPodcastsSkill;
import com.amazon.music.curate.skyfire.local.ShowRecentlyAddedSkill;
import com.amazon.music.curate.skyfire.local.ShowRecentlyDownloadedSkill;
import com.amazon.music.curate.skyfire.local.ShowRecentlyPlayedSkill;
import com.amazon.music.curate.skyfire.local.ShowSongsSkill;

/* JADX INFO: loaded from: classes4.dex */
public final class CurateSkyfireModule {
    static LocalSkill provideShowPlaylistsSkill() {
        return new ShowPlaylistsSkill();
    }

    static LocalSkill provideShowSongsSkill() {
        return new ShowSongsSkill();
    }

    static LocalSkill provideShowAlbumsSkill() {
        return new ShowAlbumsSkill();
    }

    static LocalSkill provideShowArtistsSkill() {
        return new ShowArtistsSkill();
    }

    static LocalSkill provideShowPodcastsSkill() {
        return new ShowPodcastsSkill();
    }

    static LocalSkill provideShowRecentlyPlayedSkill() {
        return new ShowRecentlyPlayedSkill();
    }

    static LocalSkill provideShowRecentlyAddedSkill() {
        return new ShowRecentlyAddedSkill();
    }

    static LocalSkill provideShowRecentlyDownloadedSkill() {
        return new ShowRecentlyDownloadedSkill();
    }

    static LocalSkill provideShowCatalogPlaylistSkill() {
        return new ShowCatalogPlaylistSkill();
    }

    static LocalSkill provideShowLibraryPlaylistSkill() {
        return new ShowLibraryPlaylistSkill();
    }

    static LocalSkill providePlayCatalogPlaylistSkill() {
        return new PlayCatalogPlaylistSkill();
    }

    static LocalSkill providePlayLibraryPlaylistSkill() {
        return new PlayLibraryPlaylistSkill();
    }

    static LocalSkill providePlayAlbumSkill() {
        return new PlayAlbumSkill();
    }

    static LocalSkill providePlayArtistSkill() {
        return new PlayArtistSkill();
    }

    static LocalSkill providePlaySationSkill() {
        return new PlayStationSkill();
    }

    static LocalSkill provideShowBrowseHomeSkill() {
        return new ShowBrowseHomeSkill();
    }

    static LocalSkill provideShowImportPlaylistsSkill() {
        return new ShowImportPlaylistsSkill();
    }

    static LocalSkill provideCreateNewPlaylist() {
        return new CreateNewPlaylistSkill();
    }
}
