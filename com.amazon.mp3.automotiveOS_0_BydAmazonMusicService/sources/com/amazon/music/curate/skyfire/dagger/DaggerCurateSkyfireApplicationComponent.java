package com.amazon.music.curate.skyfire.dagger;

import com.amazon.music.curate.skyfire.local.CreateNewPlaylistSkillContext;
import com.amazon.music.curate.skyfire.local.LocalSkill;
import com.amazon.music.curate.skyfire.local.PlayAlbumSkillContext;
import com.amazon.music.curate.skyfire.local.PlayArtistSkillContext;
import com.amazon.music.curate.skyfire.local.PlayCatalogPlaylistSkillContext;
import com.amazon.music.curate.skyfire.local.PlayLibraryPlaylistSkillContext;
import com.amazon.music.curate.skyfire.local.PlayStationSkillContext;
import com.amazon.music.curate.skyfire.local.ShowAlbumsSkillContext;
import com.amazon.music.curate.skyfire.local.ShowArtistsSkillContext;
import com.amazon.music.curate.skyfire.local.ShowBrowseHomeSkillContext;
import com.amazon.music.curate.skyfire.local.ShowCatalogPlaylistSkillContext;
import com.amazon.music.curate.skyfire.local.ShowImportPlaylistsSkillContext;
import com.amazon.music.curate.skyfire.local.ShowLibraryPlaylistSkillContext;
import com.amazon.music.curate.skyfire.local.ShowPlaylistsSkillContext;
import com.amazon.music.curate.skyfire.local.ShowPodcastsSkillContext;
import com.amazon.music.curate.skyfire.local.ShowRecentlyAddedSkillContext;
import com.amazon.music.curate.skyfire.local.ShowRecentlyDownloadedSkillContext;
import com.amazon.music.curate.skyfire.local.ShowRecentlyPlayedSkillContext;
import com.amazon.music.curate.skyfire.local.ShowSongsSkillContext;
import com.google.common.collect.ImmutableMap;
import dagger.internal.Preconditions;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class DaggerCurateSkyfireApplicationComponent {
    private DaggerCurateSkyfireApplicationComponent() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static CurateSkyfireApplicationComponent create() {
        return new Builder().build();
    }

    public static final class Builder {
        private Builder() {
        }

        @Deprecated
        public Builder curateSkyfireModule(CurateSkyfireModule curateSkyfireModule) {
            Preconditions.checkNotNull(curateSkyfireModule);
            return this;
        }

        public CurateSkyfireApplicationComponent build() {
            return new CurateSkyfireApplicationComponentImpl();
        }
    }

    private static final class CurateSkyfireApplicationComponentImpl implements CurateSkyfireApplicationComponent {
        private final CurateSkyfireApplicationComponentImpl curateSkyfireApplicationComponentImpl;

        private CurateSkyfireApplicationComponentImpl() {
            this.curateSkyfireApplicationComponentImpl = this;
        }

        @Override // com.amazon.music.curate.skyfire.dagger.CurateSkyfireApplicationComponent
        public Map<Class<?>, LocalSkill> skillsByClass() {
            return ImmutableMap.builderWithExpectedSize(18).put(ShowPlaylistsSkillContext.class, CurateSkyfireModule_ProvideShowPlaylistsSkillFactory.provideShowPlaylistsSkill()).put(ShowSongsSkillContext.class, CurateSkyfireModule_ProvideShowSongsSkillFactory.provideShowSongsSkill()).put(ShowAlbumsSkillContext.class, CurateSkyfireModule_ProvideShowAlbumsSkillFactory.provideShowAlbumsSkill()).put(ShowArtistsSkillContext.class, CurateSkyfireModule_ProvideShowArtistsSkillFactory.provideShowArtistsSkill()).put(ShowPodcastsSkillContext.class, CurateSkyfireModule_ProvideShowPodcastsSkillFactory.provideShowPodcastsSkill()).put(ShowRecentlyPlayedSkillContext.class, CurateSkyfireModule_ProvideShowRecentlyPlayedSkillFactory.provideShowRecentlyPlayedSkill()).put(ShowRecentlyAddedSkillContext.class, CurateSkyfireModule_ProvideShowRecentlyAddedSkillFactory.provideShowRecentlyAddedSkill()).put(ShowRecentlyDownloadedSkillContext.class, CurateSkyfireModule_ProvideShowRecentlyDownloadedSkillFactory.provideShowRecentlyDownloadedSkill()).put(ShowCatalogPlaylistSkillContext.class, CurateSkyfireModule_ProvideShowCatalogPlaylistSkillFactory.provideShowCatalogPlaylistSkill()).put(ShowLibraryPlaylistSkillContext.class, CurateSkyfireModule_ProvideShowLibraryPlaylistSkillFactory.provideShowLibraryPlaylistSkill()).put(PlayCatalogPlaylistSkillContext.class, CurateSkyfireModule_ProvidePlayCatalogPlaylistSkillFactory.providePlayCatalogPlaylistSkill()).put(PlayLibraryPlaylistSkillContext.class, CurateSkyfireModule_ProvidePlayLibraryPlaylistSkillFactory.providePlayLibraryPlaylistSkill()).put(PlayAlbumSkillContext.class, CurateSkyfireModule_ProvidePlayAlbumSkillFactory.providePlayAlbumSkill()).put(PlayArtistSkillContext.class, CurateSkyfireModule_ProvidePlayArtistSkillFactory.providePlayArtistSkill()).put(PlayStationSkillContext.class, CurateSkyfireModule_ProvidePlaySationSkillFactory.providePlaySationSkill()).put(ShowBrowseHomeSkillContext.class, CurateSkyfireModule_ProvideShowBrowseHomeSkillFactory.provideShowBrowseHomeSkill()).put(ShowImportPlaylistsSkillContext.class, CurateSkyfireModule_ProvideShowImportPlaylistsSkillFactory.provideShowImportPlaylistsSkill()).put(CreateNewPlaylistSkillContext.class, CurateSkyfireModule_ProvideCreateNewPlaylistFactory.provideCreateNewPlaylist()).build();
        }
    }
}
