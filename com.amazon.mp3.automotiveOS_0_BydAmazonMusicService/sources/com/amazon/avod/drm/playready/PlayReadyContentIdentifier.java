package com.amazon.avod.drm.playready;

import com.amazon.avod.util.DLog;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlayReadyContentIdentifier {
    private final PlayReadyHeader mHeader;
    private final String mTitleId;

    private PlayReadyContentIdentifier(@Nonnull String str, @Nullable PlayReadyHeader playReadyHeader) {
        this.mTitleId = (String) Preconditions.checkNotNull(str);
        this.mHeader = playReadyHeader;
    }

    public static PlayReadyContentIdentifier createUsingTitleIdAndDrmAssetId(@Nonnull String str, @Nonnull String str2) {
        if (str2 != null) {
            return new PlayReadyContentIdentifier(str, PlayReadyHeader.fromBase64(str2));
        }
        DLog.warnf("null drmAssetId for titleId %s: \"%s\"", str, str2);
        return new PlayReadyContentIdentifier(str, null);
    }

    @Nonnull
    public String getTitleId() {
        return this.mTitleId;
    }

    @Nullable
    public String getKeyId() {
        PlayReadyHeader playReadyHeader = this.mHeader;
        if (playReadyHeader != null) {
            return playReadyHeader.getKeyID().toString();
        }
        return null;
    }

    @Nullable
    public String getKeyIdBase64() {
        PlayReadyHeader playReadyHeader = this.mHeader;
        if (playReadyHeader != null) {
            return playReadyHeader.getKeyID().toBase64();
        }
        return null;
    }

    @Nullable
    public PlayReadyHeader getHeader() {
        return this.mHeader;
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("titleId", this.mTitleId).add("KeyId", getKeyId()).add("KeyIdBase64", getKeyIdBase64()).toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlayReadyContentIdentifier)) {
            return false;
        }
        PlayReadyContentIdentifier playReadyContentIdentifier = (PlayReadyContentIdentifier) obj;
        return Objects.equal(this.mTitleId, playReadyContentIdentifier.getTitleId()) && Objects.equal(this.mHeader, playReadyContentIdentifier.getHeader());
    }

    public int hashCode() {
        return Objects.hashCode(this.mTitleId, this.mHeader);
    }
}
