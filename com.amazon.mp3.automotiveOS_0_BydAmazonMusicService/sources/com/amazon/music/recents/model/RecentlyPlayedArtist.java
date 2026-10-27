package com.amazon.music.recents.model;

import androidx.annotation.Nullable;
import com.amazon.music.recents.model.MusicItem;

/* JADX INFO: loaded from: classes4.dex */
public class RecentlyPlayedArtist extends MusicItem {

    @Nullable
    private String trackAsin;

    @Nullable
    private String trackLuid;

    @Nullable
    private String trackTitle;

    private RecentlyPlayedArtist() {
    }

    @Nullable
    public String getTrackAsin() {
        return this.trackAsin;
    }

    @Nullable
    public String getTrackLuid() {
        return this.trackLuid;
    }

    public static final class Builder extends MusicItem.Builder<RecentlyPlayedArtist, Builder> {
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
        public RecentlyPlayedArtist createObject() {
            return new RecentlyPlayedArtist();
        }

        public final Builder trackTitle(@Nullable String str) {
            ((RecentlyPlayedArtist) this.object).trackTitle = str;
            return (Builder) this.thisObject;
        }

        public final Builder trackAsin(@Nullable String str) {
            ((RecentlyPlayedArtist) this.object).trackAsin = str;
            return (Builder) this.thisObject;
        }

        public final Builder trackLuid(@Nullable String str) {
            ((RecentlyPlayedArtist) this.object).trackLuid = str;
            return (Builder) this.thisObject;
        }
    }
}
