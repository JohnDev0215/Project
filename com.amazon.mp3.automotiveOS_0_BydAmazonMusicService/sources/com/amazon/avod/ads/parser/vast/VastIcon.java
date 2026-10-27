package com.amazon.avod.ads.parser.vast;

import com.google.common.base.Preconditions;
import java.net.URI;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastIcon {
    private final String mApiFramework;
    private final VastTimeSpan mDuration;
    private final int mHeight;
    private final VastIconClicks mIconClicks;
    private final List<URI> mIconViewTracking;
    private final VastTimeSpan mOffset;
    private final String mProgram;
    private final VastResource mResource;
    private final int mWidth;
    private final String mXPosition;
    private final String mYPosition;

    public VastIcon(@Nonnull String str, int i, int i2, @Nonnull String str2, @Nonnull String str3, @Nullable VastTimeSpan vastTimeSpan, @Nullable VastTimeSpan vastTimeSpan2, @Nullable String str4, @Nonnull VastResource vastResource, @Nullable VastIconClicks vastIconClicks, @Nullable List<URI> list) {
        Preconditions.checkArgument(i >= 0, "width");
        Preconditions.checkArgument(i2 >= 0, "height");
        this.mProgram = (String) Preconditions.checkNotNull(str, "program");
        this.mWidth = i;
        this.mHeight = i2;
        this.mXPosition = str2;
        this.mYPosition = str3;
        this.mOffset = vastTimeSpan;
        this.mDuration = vastTimeSpan2;
        this.mApiFramework = str4;
        this.mResource = (VastResource) Preconditions.checkNotNull(vastResource, "resource");
        this.mIconClicks = vastIconClicks;
        this.mIconViewTracking = list;
    }

    @Nonnull
    public String getProgram() {
        return this.mProgram;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    @Nonnull
    public String getXPosition() {
        return this.mXPosition;
    }

    @Nonnull
    public String getYPosition() {
        return this.mYPosition;
    }

    @Nullable
    public VastTimeSpan getOffset() {
        return this.mOffset;
    }

    @Nullable
    public VastTimeSpan getDuration() {
        return this.mDuration;
    }

    @Nonnull
    public VastResource getResource() {
        return this.mResource;
    }
}
