package com.amazon.music.recents.model;

import androidx.annotation.Nullable;
import com.amazon.music.recents.model.MusicItem;

/* JADX INFO: loaded from: classes4.dex */
public final class RecentlyPlayedUserPlaylist extends MusicItem {

    @Nullable
    private Integer durationSeconds;

    @Nullable
    private String trackAsin;

    @Nullable
    private Integer trackCount;

    @Nullable
    private String trackLuid;

    private RecentlyPlayedUserPlaylist() {
    }

    @Nullable
    public String getTrackAsin() {
        return this.trackAsin;
    }

    @Nullable
    public String getTrackLuid() {
        return this.trackLuid;
    }

    public static final class Builder extends MusicItem.Builder<RecentlyPlayedUserPlaylist, Builder> {
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
        public RecentlyPlayedUserPlaylist createObject() {
            return new RecentlyPlayedUserPlaylist();
        }

        public final Builder trackAsin(@Nullable String str) {
            ((RecentlyPlayedUserPlaylist) this.object).trackAsin = str;
            return (Builder) this.thisObject;
        }

        public final Builder trackLuid(@Nullable String str) {
            ((RecentlyPlayedUserPlaylist) this.object).trackLuid = str;
            return (Builder) this.thisObject;
        }

        public final Builder durationSeconds(@Nullable Integer num) {
            ((RecentlyPlayedUserPlaylist) this.object).durationSeconds = num;
            return (Builder) this.thisObject;
        }

        public final Builder trackCount(@Nullable Integer num) {
            ((RecentlyPlayedUserPlaylist) this.object).trackCount = num;
            return (Builder) this.thisObject;
        }
    }
}
