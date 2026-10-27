package com.amazon.music.recents.model;

import androidx.annotation.Nullable;
import com.amazon.music.recents.model.MusicItem;

/* JADX INFO: loaded from: classes4.dex */
public final class RecentlyPlayedCatalogPlaylist extends MusicItem {

    @Nullable
    private String description;

    @Nullable
    private Integer durationSeconds;

    @Nullable
    private String genre;

    @Nullable
    private String parentPlaylist;

    @Nullable
    private String trackAsin;

    @Nullable
    private String trackLuid;

    private RecentlyPlayedCatalogPlaylist() {
    }

    @Nullable
    public String getTrackAsin() {
        return this.trackAsin;
    }

    @Nullable
    public String getTrackLuid() {
        return this.trackLuid;
    }

    public static final class Builder extends MusicItem.Builder<RecentlyPlayedCatalogPlaylist, Builder> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.music.recents.model.MusicItem.Builder
        public Builder thisObject() {
            return this;
        }

        @Override // com.amazon.music.recents.model.MusicItem.Builder
        public /* bridge */ /* synthetic */ MusicItem build() {
            return super.build();
        }

        @Override // com.amazon.music.recents.model.MusicItem.Builder
        public /* bridge */ /* synthetic */ MusicItem.Builder entityId(String str) {
            return super.entityId(str);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.music.recents.model.MusicItem.Builder
        public RecentlyPlayedCatalogPlaylist createObject() {
            return new RecentlyPlayedCatalogPlaylist();
        }

        public final Builder trackAsin(@Nullable String str) {
            ((RecentlyPlayedCatalogPlaylist) this.object).trackAsin = str;
            return (Builder) this.thisObject;
        }

        public final Builder trackLuid(@Nullable String str) {
            ((RecentlyPlayedCatalogPlaylist) this.object).trackLuid = str;
            return (Builder) this.thisObject;
        }

        public final Builder durationSeconds(@Nullable Integer num) {
            ((RecentlyPlayedCatalogPlaylist) this.object).durationSeconds = num;
            return (Builder) this.thisObject;
        }

        public final Builder genre(@Nullable String str) {
            ((RecentlyPlayedCatalogPlaylist) this.object).genre = str;
            return (Builder) this.thisObject;
        }

        public final Builder parentPlaylist(@Nullable String str) {
            ((RecentlyPlayedCatalogPlaylist) this.object).parentPlaylist = str;
            return (Builder) this.thisObject;
        }

        public final Builder description(@Nullable String str) {
            ((RecentlyPlayedCatalogPlaylist) this.object).description = str;
            return (Builder) this.thisObject;
        }
    }
}
