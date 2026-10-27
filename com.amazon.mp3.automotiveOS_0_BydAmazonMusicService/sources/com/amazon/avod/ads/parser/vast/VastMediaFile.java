package com.amazon.avod.ads.parser.vast;

import com.google.common.base.Preconditions;
import java.net.URI;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class VastMediaFile {
    private final String mApiFramework;
    private final Integer mBitrate;
    private final String mCodec;
    private final DeliveryType mDeliveryType;
    private final int mHeight;
    private final String mId;
    private final Boolean mMaintainAspectRatio;
    private final Integer mMaxBitrate;
    private final String mMimeType;
    private final Integer mMinBitrate;
    private final Boolean mScalable;
    private final URI mUri;
    private final int mWidth;

    public enum DeliveryType {
        streaming,
        progressive
    }

    public VastMediaFile(@Nullable String str, @Nonnull DeliveryType deliveryType, @Nonnull String str2, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, int i, int i2, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable String str3, @Nullable String str4, @Nonnull URI uri) {
        Preconditions.checkArgument(i >= 0);
        Preconditions.checkArgument(i2 >= 0);
        this.mId = str;
        this.mDeliveryType = (DeliveryType) Preconditions.checkNotNull(deliveryType, "deliveryType");
        this.mMimeType = (String) Preconditions.checkNotNull(str2, "mimeType");
        this.mBitrate = num;
        this.mMinBitrate = num2;
        this.mMaxBitrate = num3;
        this.mWidth = i;
        this.mHeight = i2;
        this.mScalable = bool;
        this.mMaintainAspectRatio = bool2;
        this.mApiFramework = str3;
        this.mCodec = str4;
        this.mUri = (URI) Preconditions.checkNotNull(uri, "uri");
    }

    @Nonnull
    public String getMimeType() {
        return this.mMimeType;
    }

    @Nullable
    public Integer getBitrate() {
        return this.mBitrate;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    @Nullable
    public Boolean isScalable() {
        return this.mScalable;
    }

    @Nullable
    public String getCodec() {
        return this.mCodec;
    }

    @Nonnull
    public URI getUri() {
        return this.mUri;
    }
}
