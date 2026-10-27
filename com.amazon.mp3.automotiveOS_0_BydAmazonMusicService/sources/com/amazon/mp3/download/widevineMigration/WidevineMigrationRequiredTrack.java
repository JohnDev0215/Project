package com.amazon.mp3.download.widevineMigration;

import com.amazon.identity.auth.device.utils.AccountConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WidevineMigrationTrackerDAO.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\b\u0080\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u001f\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0007¢\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0004\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\t\u0010\b\u001a\u00020\u0007HÖ\u0001R\u0017\u0010\t\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/download/widevineMigration/WidevineMigrationRequiredTrack;", "", "other", "", "equals", "", "toString", "", "hashCode", "asin", "Ljava/lang/String;", "getAsin", "()Ljava/lang/String;", "luid", "getLuid", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "I", "getType", "()I", "<init>", "(Ljava/lang/String;Ljava/lang/String;I)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class WidevineMigrationRequiredTrack {
    private final String asin;
    private final String luid;
    private final int type;

    public int hashCode() {
        return (((this.asin.hashCode() * 31) + this.luid.hashCode()) * 31) + Integer.hashCode(this.type);
    }

    public String toString() {
        return "WidevineMigrationRequiredTrack(asin=" + this.asin + ", luid=" + this.luid + ", type=" + this.type + ')';
    }

    public WidevineMigrationRequiredTrack(String asin, String luid, int i) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        Intrinsics.checkNotNullParameter(luid, "luid");
        this.asin = asin;
        this.luid = luid;
        this.type = i;
    }

    public final String getAsin() {
        return this.asin;
    }

    public final String getLuid() {
        return this.luid;
    }

    public final int getType() {
        return this.type;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(other == null ? null : other.getClass(), WidevineMigrationRequiredTrack.class)) {
            return false;
        }
        if (other != null) {
            WidevineMigrationRequiredTrack widevineMigrationRequiredTrack = (WidevineMigrationRequiredTrack) other;
            if (Integer.valueOf(this.type).equals(0) && (this.asin.equals(widevineMigrationRequiredTrack.asin) || this.luid.equals(widevineMigrationRequiredTrack.luid))) {
                return true;
            }
            return Integer.valueOf(this.type).equals(1) && this.asin.equals(widevineMigrationRequiredTrack.asin) && this.luid.equals(widevineMigrationRequiredTrack.luid);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.download.widevineMigration.WidevineMigrationRequiredTrack");
    }
}
