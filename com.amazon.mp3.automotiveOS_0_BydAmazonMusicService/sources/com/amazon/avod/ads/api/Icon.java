package com.amazon.avod.ads.api;

import com.amazon.avod.ads.http.AdHttpClient;
import com.amazon.avod.ads.parser.vast.VastIcon;
import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class Icon {
    private final Duration mDuration;
    private final AdHttpClient mHttpClient;
    private final VastIcon mInnerIcon;
    private final Duration mOffset;
    private final Resource mResource;
    private final Integer mXPosition;
    private final IconPositionType mXPositionType;
    private final Integer mYPosition;
    private final IconPositionType mYPositionType;

    public enum IconPositionType {
        TOP("top"),
        BOTTOM("bottom"),
        LEFT("left"),
        RIGHT("right"),
        NUMBER("[0-9]+");

        private final String mMatchRegex;

        IconPositionType(String str) {
            this.mMatchRegex = str;
        }

        boolean matches(String str) {
            return str.matches(this.mMatchRegex);
        }
    }

    public Icon(AdHttpClient adHttpClient, VastIcon vastIcon) {
        this.mHttpClient = (AdHttpClient) Preconditions.checkNotNull(adHttpClient);
        VastIcon vastIcon2 = (VastIcon) Preconditions.checkNotNull(vastIcon);
        this.mInnerIcon = vastIcon2;
        IconPositionType iconPositionTypeFindPositionType = findPositionType(vastIcon2.getXPosition());
        this.mXPositionType = iconPositionTypeFindPositionType;
        IconPositionType iconPositionType = IconPositionType.NUMBER;
        if (iconPositionTypeFindPositionType == iconPositionType) {
            this.mXPosition = Integer.valueOf(Integer.parseInt(vastIcon2.getXPosition()));
        } else {
            this.mXPosition = null;
        }
        IconPositionType iconPositionTypeFindPositionType2 = findPositionType(vastIcon2.getYPosition());
        this.mYPositionType = iconPositionTypeFindPositionType2;
        if (iconPositionTypeFindPositionType2 == iconPositionType) {
            this.mYPosition = Integer.valueOf(Integer.parseInt(vastIcon2.getYPosition()));
        } else {
            this.mYPosition = null;
        }
        if (vastIcon2.getOffset() != null) {
            this.mOffset = new Duration(vastIcon2.getOffset());
        } else {
            this.mOffset = null;
        }
        if (vastIcon2.getDuration() != null) {
            this.mDuration = new Duration(vastIcon2.getDuration());
        } else {
            this.mDuration = null;
        }
        this.mResource = new Resource(vastIcon.getResource());
    }

    public int getWidth() {
        return this.mInnerIcon.getWidth();
    }

    public int getHeight() {
        return this.mInnerIcon.getHeight();
    }

    @Nonnull
    public String getProgram() {
        return this.mInnerIcon.getProgram();
    }

    @Nullable
    public Duration getOffset() {
        return this.mOffset;
    }

    @Nullable
    public Duration getDuration() {
        return this.mDuration;
    }

    @Nonnull
    public Resource getResource() {
        return this.mResource;
    }

    @Nullable
    private IconPositionType findPositionType(String str) {
        for (IconPositionType iconPositionType : IconPositionType.values()) {
            if (iconPositionType.matches(str)) {
                return iconPositionType;
            }
        }
        return null;
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add(JsonDocumentFields.RESOURCE, getResource()).add("Width", getWidth()).add("Height", getHeight()).add("Program", getProgram()).add("Duration", getDuration()).add("Offset", getOffset()).toString();
    }
}
