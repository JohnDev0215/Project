package com.spotify.connectivity.sessionapi;

import kotlin.jvm.internal.DefaultConstructorMarker;
import p.hf2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class APPermanentErrorResult {

    public static final class APPermanentErrorBadCredentials extends APPermanentErrorResult {
        public static final APPermanentErrorBadCredentials INSTANCE = new APPermanentErrorBadCredentials();

        private APPermanentErrorBadCredentials() {
            super(null);
        }
    }

    public static final class APPermanentErrorPasswordChanged extends APPermanentErrorResult {
        public static final APPermanentErrorPasswordChanged INSTANCE = new APPermanentErrorPasswordChanged();

        private APPermanentErrorPasswordChanged() {
            super(null);
        }
    }

    public static final class APPermanentErrorRegionMismatch extends APPermanentErrorResult {
        public static final APPermanentErrorRegionMismatch INSTANCE = new APPermanentErrorRegionMismatch();

        private APPermanentErrorRegionMismatch() {
            super(null);
        }
    }

    public static final class APPermanentGenericError extends APPermanentErrorResult {
        private final int errorCode;
        private final String errorDescription;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public APPermanentGenericError(int i, String str) {
            super(null);
            v03.j(str, "errorDescription");
            this.errorCode = i;
            this.errorDescription = str;
        }

        public static /* synthetic */ APPermanentGenericError copy$default(APPermanentGenericError aPPermanentGenericError, int i, String str, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = aPPermanentGenericError.errorCode;
            }
            if ((i2 & 2) != 0) {
                str = aPPermanentGenericError.errorDescription;
            }
            return aPPermanentGenericError.copy(i, str);
        }

        public final int component1() {
            return this.errorCode;
        }

        public final String component2() {
            return this.errorDescription;
        }

        public final APPermanentGenericError copy(int i, String str) {
            v03.j(str, "errorDescription");
            return new APPermanentGenericError(i, str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof APPermanentGenericError)) {
                return false;
            }
            APPermanentGenericError aPPermanentGenericError = (APPermanentGenericError) obj;
            return this.errorCode == aPPermanentGenericError.errorCode && v03.b(this.errorDescription, aPPermanentGenericError.errorDescription);
        }

        public final int getErrorCode() {
            return this.errorCode;
        }

        public final String getErrorDescription() {
            return this.errorDescription;
        }

        public int hashCode() {
            return this.errorDescription.hashCode() + (Integer.hashCode(this.errorCode) * 31);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("APPermanentGenericError(errorCode=");
            sb.append(this.errorCode);
            sb.append(", errorDescription=");
            return hf2.d(sb, this.errorDescription, ')');
        }
    }

    private APPermanentErrorResult() {
    }

    public /* synthetic */ APPermanentErrorResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
