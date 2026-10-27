package com.amazon.music.ui.model.castingtile;

import com.amazon.music.model.Block;
import com.amazon.music.optional.Optional;

/* JADX INFO: loaded from: classes4.dex */
public class CastingTileModel extends Block {
    public final Optional<String> category;
    public final Optional<String> deviceTypeId;
    public final Optional<String> imageUrl;
    public final Optional<Boolean> isPlaying;
    public final Optional<String> targetId;
    public final Optional<String> title;

    private CastingTileModel(String str, String str2, String str3, String str4, String str5, String str6, Boolean bool) {
        super(str);
        this.targetId = Optional.of(str2);
        this.deviceTypeId = Optional.ofNullable(str3);
        this.title = Optional.of(str4);
        this.isPlaying = Optional.ofNullable(bool);
        this.category = Optional.ofNullable(str5);
        this.imageUrl = Optional.ofNullable(str6);
    }

    public static final CastingTileBuilder builder(String str, String str2, String str3) {
        return new CastingTileBuilder(str, str2, str3);
    }

    public static final class CastingTileBuilder {
        private String category;
        private String deviceTypeId;
        private String imageUrl;
        private Boolean isPlaying;
        private final String targetId;
        private final String title;
        private final String uuid;

        private CastingTileBuilder(String str, String str2, String str3) {
            this.uuid = str;
            this.targetId = str2;
            this.title = str3;
        }

        public CastingTileBuilder withDeviceTypeId(String str) {
            this.deviceTypeId = str;
            return this;
        }

        public CastingTileBuilder withIsPlaying(boolean z) {
            this.isPlaying = Boolean.valueOf(z);
            return this;
        }

        public CastingTileBuilder withCategory(String str) {
            this.category = str;
            return this;
        }

        public CastingTileBuilder withImageUrl(String str) {
            this.imageUrl = str;
            return this;
        }

        public CastingTileModel build() {
            return new CastingTileModel(this.uuid, this.targetId, this.deviceTypeId, this.title, this.category, this.imageUrl, this.isPlaying);
        }
    }
}
