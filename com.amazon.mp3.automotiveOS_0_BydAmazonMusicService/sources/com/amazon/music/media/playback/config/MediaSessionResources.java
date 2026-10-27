package com.amazon.music.media.playback.config;

import androidx.annotation.DrawableRes;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes4.dex */
public class MediaSessionResources {
    private String addToLibraryActionName;

    @DrawableRes
    private int addToLibraryDrawable;
    private String repeatAllModeActionName;

    @DrawableRes
    private int repeatAllModeDrawable;
    private String repeatNoneModeActionName;

    @DrawableRes
    private int repeatNoneModeDrawable;
    private String repeatOneModeActionName;

    @DrawableRes
    private int repeatOneModeDrawable;
    private String shuffleActionName;

    @DrawableRes
    private int shuffleNormalDrawable;

    @DrawableRes
    private int shuffleSelectedDrawable;
    private String thumbsDownActionName;

    @DrawableRes
    private int thumbsDownNormalDrawable;

    @DrawableRes
    private int thumbsDownSelectedDrawable;
    private String thumbsDownUnrateActionName;
    private String thumbsUpActionName;

    @DrawableRes
    private int thumbsUpNormalDrawable;

    @DrawableRes
    private int thumbsUpSelectedDrawable;
    private String thumbsUpUnrateActionName;
    private String unshuffleActionName;

    @Nullable
    public String getThumbsUpActionName() {
        return this.thumbsUpActionName;
    }

    @Nullable
    public String getThumbsDownActionName() {
        return this.thumbsDownActionName;
    }

    @Nullable
    public String getThumbsUpUnrateActionName() {
        return this.thumbsUpUnrateActionName;
    }

    @Nullable
    public String getThumbsDownUnrateActionName() {
        return this.thumbsDownUnrateActionName;
    }

    @Nullable
    public String getShuffleActionName() {
        return this.shuffleActionName;
    }

    @Nullable
    public String getUnshuffleActionName() {
        return this.unshuffleActionName;
    }

    @Nullable
    public String getAddToLibraryActionName() {
        return this.addToLibraryActionName;
    }

    @Nullable
    public String getRepeatNoneModeActionName() {
        return this.repeatNoneModeActionName;
    }

    @Nullable
    public String getRepeatAllModeActionName() {
        return this.repeatAllModeActionName;
    }

    @Nullable
    public String getRepeatOneModeActionName() {
        return this.repeatOneModeActionName;
    }

    @DrawableRes
    public int getThumbsUpSelectedDrawable() {
        return this.thumbsUpSelectedDrawable;
    }

    @DrawableRes
    public int getThumbsUpNormalDrawable() {
        return this.thumbsUpNormalDrawable;
    }

    @DrawableRes
    public int getThumbsDownSelectedDrawable() {
        return this.thumbsDownSelectedDrawable;
    }

    @DrawableRes
    public int getThumbsDownNormalDrawable() {
        return this.thumbsDownNormalDrawable;
    }

    @DrawableRes
    public int getShuffleSelectedDrawable() {
        return this.shuffleSelectedDrawable;
    }

    @DrawableRes
    public int getShuffleNormalDrawable() {
        return this.shuffleNormalDrawable;
    }

    @DrawableRes
    public int getAddToLibraryDrawable() {
        return this.addToLibraryDrawable;
    }

    @DrawableRes
    public int getRepeatNoneModeDrawable() {
        return this.repeatNoneModeDrawable;
    }

    @DrawableRes
    public int getRepeatOneModeDrawable() {
        return this.repeatOneModeDrawable;
    }

    @DrawableRes
    public int getRepeatAllModeDrawable() {
        return this.repeatAllModeDrawable;
    }

    public boolean isShuffleResourcesReady() {
        return (this.shuffleActionName == null || this.unshuffleActionName == null || this.shuffleNormalDrawable == 0 || this.shuffleSelectedDrawable == 0) ? false : true;
    }

    public boolean isThumbsUpResourcesReady() {
        return (this.thumbsUpActionName == null || this.thumbsUpUnrateActionName == null || this.thumbsUpNormalDrawable == 0 || this.thumbsUpSelectedDrawable == 0) ? false : true;
    }

    public boolean isThumbsDownResourcesReady() {
        return (this.thumbsDownActionName == null || this.thumbsDownUnrateActionName == null || this.thumbsDownNormalDrawable == 0 || this.thumbsDownSelectedDrawable == 0) ? false : true;
    }

    public boolean isRepeatModeResourcesReady() {
        return (this.repeatNoneModeActionName == null || this.repeatNoneModeDrawable == 0 || this.repeatAllModeActionName == null || this.repeatAllModeDrawable == 0 || this.repeatOneModeActionName == null || this.repeatOneModeDrawable == 0) ? false : true;
    }

    private MediaSessionResources(Builder builder) {
        this.thumbsUpSelectedDrawable = 0;
        this.thumbsUpNormalDrawable = 0;
        this.thumbsDownSelectedDrawable = 0;
        this.thumbsDownNormalDrawable = 0;
        this.shuffleSelectedDrawable = 0;
        this.shuffleNormalDrawable = 0;
        this.addToLibraryDrawable = 0;
        this.repeatNoneModeDrawable = 0;
        this.repeatOneModeDrawable = 0;
        this.repeatAllModeDrawable = 0;
        this.thumbsUpSelectedDrawable = builder.thumbsUpSelectedDrawable;
        this.thumbsUpNormalDrawable = builder.thumbsUpNormalDrawable;
        this.thumbsDownSelectedDrawable = builder.thumbsDownSelectedDrawable;
        this.thumbsDownNormalDrawable = builder.thumbsDownNormalDrawable;
        this.shuffleSelectedDrawable = builder.shuffleSelectedDrawable;
        this.shuffleNormalDrawable = builder.shuffleNormalDrawable;
        this.thumbsUpActionName = builder.thumbsUpActionName;
        this.thumbsDownActionName = builder.thumbsDownActionName;
        this.thumbsUpUnrateActionName = builder.thumbsUpUnrateActionName;
        this.thumbsDownUnrateActionName = builder.thumbsDownUnrateActionName;
        this.shuffleActionName = builder.shuffleActionName;
        this.unshuffleActionName = builder.unshuffleActionName;
        this.addToLibraryActionName = builder.addToLibraryActionName;
        this.addToLibraryDrawable = builder.addToLibraryDrawable;
        this.repeatNoneModeActionName = builder.repeatNoneModeActionName;
        this.repeatOneModeActionName = builder.repeatOneModeActionName;
        this.repeatAllModeActionName = builder.repeatAllModeActionName;
        this.repeatNoneModeDrawable = builder.repeatNoneModeDrawable;
        this.repeatOneModeDrawable = builder.repeatOneModeDrawable;
        this.repeatAllModeDrawable = builder.repeatAllModeDrawable;
    }

    public static class Builder {
        private String addToLibraryActionName;
        private String repeatAllModeActionName;
        private String repeatNoneModeActionName;
        private String repeatOneModeActionName;
        private String shuffleActionName;
        private String thumbsDownActionName;
        private String thumbsDownUnrateActionName;
        private String thumbsUpActionName;
        private String thumbsUpUnrateActionName;
        private String unshuffleActionName;

        @DrawableRes
        private int thumbsUpSelectedDrawable = 0;

        @DrawableRes
        private int thumbsUpNormalDrawable = 0;

        @DrawableRes
        private int thumbsDownSelectedDrawable = 0;

        @DrawableRes
        private int thumbsDownNormalDrawable = 0;

        @DrawableRes
        private int shuffleSelectedDrawable = 0;

        @DrawableRes
        private int shuffleNormalDrawable = 0;

        @DrawableRes
        private int addToLibraryDrawable = 0;

        @DrawableRes
        private int repeatNoneModeDrawable = 0;

        @DrawableRes
        private int repeatOneModeDrawable = 0;

        @DrawableRes
        private int repeatAllModeDrawable = 0;

        public Builder setThumbsUpSelectedDrawable(@DrawableRes int i) {
            this.thumbsUpSelectedDrawable = i;
            return this;
        }

        public Builder setThumbsUpNormalDrawable(@DrawableRes int i) {
            this.thumbsUpNormalDrawable = i;
            return this;
        }

        public Builder setThumbsDownSelectedDrawable(@DrawableRes int i) {
            this.thumbsDownSelectedDrawable = i;
            return this;
        }

        public Builder setThumbsDownNormalDrawable(@DrawableRes int i) {
            this.thumbsDownNormalDrawable = i;
            return this;
        }

        public Builder setShuffleSelectedDrawable(@DrawableRes int i) {
            this.shuffleSelectedDrawable = i;
            return this;
        }

        public Builder setShuffleNormalDrawable(@DrawableRes int i) {
            this.shuffleNormalDrawable = i;
            return this;
        }

        public Builder setAddToLibraryDrawable(@DrawableRes int i) {
            this.addToLibraryDrawable = i;
            return this;
        }

        public Builder setRepeatNoneModeDrawable(@DrawableRes int i) {
            this.repeatNoneModeDrawable = i;
            return this;
        }

        public Builder setRepeatAllModeDrawable(@DrawableRes int i) {
            this.repeatAllModeDrawable = i;
            return this;
        }

        public Builder setRepeatOneModeDrawable(@DrawableRes int i) {
            this.repeatOneModeDrawable = i;
            return this;
        }

        public Builder setThumbsUpActionName(@Nonnull String str) {
            this.thumbsUpActionName = str;
            return this;
        }

        public Builder setThumbsDownActionName(@Nonnull String str) {
            this.thumbsDownActionName = str;
            return this;
        }

        public Builder setThumbsUpUnrateActionName(@Nonnull String str) {
            this.thumbsUpUnrateActionName = str;
            return this;
        }

        public Builder setThumbsDownUnrateActionName(@Nonnull String str) {
            this.thumbsDownUnrateActionName = str;
            return this;
        }

        public Builder setShuffleActionName(@Nonnull String str) {
            this.shuffleActionName = str;
            return this;
        }

        public Builder setUnshuffleActionName(@Nonnull String str) {
            this.unshuffleActionName = str;
            return this;
        }

        public Builder setAddToLibraryActionName(@Nonnull String str) {
            this.addToLibraryActionName = str;
            return this;
        }

        public Builder setRepeatNoneModeActionName(@Nonnull String str) {
            this.repeatNoneModeActionName = str;
            return this;
        }

        public Builder setRepeatOneModeActionName(@Nonnull String str) {
            this.repeatOneModeActionName = str;
            return this;
        }

        public Builder setRepeatAllModeActionName(@Nonnull String str) {
            this.repeatAllModeActionName = str;
            return this;
        }

        @Nonnull
        public MediaSessionResources build() {
            return new MediaSessionResources(this);
        }
    }
}
