package com.amazon.music.recents.model;

import androidx.annotation.Nullable;
import com.amazon.music.recents.model.MusicItem;

/* JADX INFO: loaded from: classes4.dex */
public final class RecentlyPlayedAlbum extends MusicItem {

    @Nullable
    private String artistName;

    @Nullable
    private String catalogArtistId;

    @Nullable
    private String trackAsin;

    @Nullable
    private String trackLuid;

    private RecentlyPlayedAlbum() {
    }

    @Nullable
    public String getTrackAsin() {
        return this.trackAsin;
    }

    @Nullable
    public String getTrackLuid() {
        return this.trackLuid;
    }

    public static final class Builder extends MusicItem.Builder<RecentlyPlayedAlbum, Builder> {
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
        public RecentlyPlayedAlbum createObject() {
            return new RecentlyPlayedAlbum();
        }

        public final Builder catalogArtistId(@Nullable String str) {
            ((RecentlyPlayedAlbum) this.object).catalogArtistId = str;
            return (Builder) this.thisObject;
        }

        public final Builder artistName(@Nullable String str) {
            ((RecentlyPlayedAlbum) this.object).artistName = str;
            return (Builder) this.thisObject;
        }

        public final Builder trackAsin(@Nullable String str) {
            ((RecentlyPlayedAlbum) this.object).trackAsin = str;
            return (Builder) this.thisObject;
        }

        public final Builder trackLuid(@Nullable String str) {
            ((RecentlyPlayedAlbum) this.object).trackLuid = str;
            return (Builder) this.thisObject;
        }
    }
}
