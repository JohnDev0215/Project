package com.amazon.music.casting;

/* JADX INFO: loaded from: classes4.dex */
public class CastingDevice {
    private final CastingCategory category;
    private final String deviceTypeId;
    private final String imageUrl;
    private final boolean isPlaying;
    private final String name;
    private final boolean overridePlayback;
    private final String targetId;

    public CastingCategory getCategory() {
        return this.category;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public String getDeviceTypeId() {
        return this.deviceTypeId;
    }

    public String getName() {
        return this.name;
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public boolean shouldOverridePlayback() {
        return this.overridePlayback;
    }

    private CastingDevice(CastingCategory castingCategory, String str, String str2, String str3, boolean z, boolean z2, String str4) {
        this.category = castingCategory;
        this.targetId = str;
        this.deviceTypeId = str2;
        this.name = str3;
        this.isPlaying = z;
        this.overridePlayback = z2;
        this.imageUrl = str4;
    }

    public static class Builder {
        private final CastingCategory category;
        private String deviceTypeId;
        private String imageUrl;
        private boolean isPlaying;
        private String name;
        private boolean overridePlayback = true;
        private final String targetId;

        public Builder(CastingCategory castingCategory, String str) {
            this.category = castingCategory;
            this.targetId = str;
        }

        public Builder withDeviceTypeId(String str) {
            this.deviceTypeId = str;
            return this;
        }

        public Builder withName(String str) {
            this.name = str;
            return this;
        }

        public Builder withIsPlaying(boolean z) {
            this.isPlaying = z;
            return this;
        }

        public Builder withOverridePlayback(boolean z) {
            this.overridePlayback = z;
            return this;
        }

        public Builder withImageUrl(String str) {
            this.imageUrl = str;
            return this;
        }

        public CastingDevice build() {
            return new CastingDevice(this.category, this.targetId, this.deviceTypeId, this.name, this.isPlaying, this.overridePlayback, this.imageUrl);
        }
    }

    public String toString() {
        return "CastingDevice{category=" + this.category.getName() + ", targetId=" + this.targetId + ", deviceTypeId=" + this.deviceTypeId + ", isPlaying=" + this.isPlaying + ", overridePlayback=" + this.overridePlayback + ", imageUrl=" + this.imageUrl + '}';
    }
}
