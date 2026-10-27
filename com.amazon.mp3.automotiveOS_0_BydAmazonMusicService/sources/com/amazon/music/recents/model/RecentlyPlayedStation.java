package com.amazon.music.recents.model;

import androidx.annotation.Nullable;
import com.amazon.music.recents.model.MusicItem;

/* JADX INFO: loaded from: classes4.dex */
public final class RecentlyPlayedStation extends MusicItem {

    @Nullable
    private Boolean isExplicit;

    @Nullable
    private String trackAsin;

    @Nullable
    private String trackLuid;

    private RecentlyPlayedStation() {
    }

    @Nullable
    public String getTrackAsin() {
        return this.trackAsin;
    }

    @Nullable
    public String getTrackLuid() {
        return this.trackLuid;
    }

    @Nullable
    public Boolean getIsExplicit() {
        return this.isExplicit;
    }

    public static final class Builder extends MusicItem.Builder<RecentlyPlayedStation, Builder> {
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
        public RecentlyPlayedStation createObject() {
            return new RecentlyPlayedStation();
        }

        public final Builder trackAsin(@Nullable String str) {
            ((RecentlyPlayedStation) this.object).trackAsin = str;
            return (Builder) this.thisObject;
        }

        public final Builder trackLuid(@Nullable String str) {
            ((RecentlyPlayedStation) this.object).trackLuid = str;
            return (Builder) this.thisObject;
        }

        public final Builder isExplicit(@Nullable Boolean bool) {
            ((RecentlyPlayedStation) this.object).isExplicit = bool;
            return (Builder) this.thisObject;
        }
    }
}
