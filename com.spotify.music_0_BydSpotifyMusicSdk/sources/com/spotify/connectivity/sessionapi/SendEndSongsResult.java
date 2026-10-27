package com.spotify.connectivity.sessionapi;

import kotlin.jvm.internal.DefaultConstructorMarker;
import p.hf2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class SendEndSongsResult {

    public static final class SendEndSongsFailure extends SendEndSongsResult {
        private final int errorCode;
        private final String errorDescription;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SendEndSongsFailure(int i, String str) {
            super(null);
            v03.j(str, "errorDescription");
            this.errorCode = i;
            this.errorDescription = str;
        }

        public static /* synthetic */ SendEndSongsFailure copy$default(SendEndSongsFailure sendEndSongsFailure, int i, String str, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = sendEndSongsFailure.errorCode;
            }
            if ((i2 & 2) != 0) {
                str = sendEndSongsFailure.errorDescription;
            }
            return sendEndSongsFailure.copy(i, str);
        }

        public final int component1() {
            return this.errorCode;
        }

        public final String component2() {
            return this.errorDescription;
        }

        public final SendEndSongsFailure copy(int i, String str) {
            v03.j(str, "errorDescription");
            return new SendEndSongsFailure(i, str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SendEndSongsFailure)) {
                return false;
            }
            SendEndSongsFailure sendEndSongsFailure = (SendEndSongsFailure) obj;
            return this.errorCode == sendEndSongsFailure.errorCode && v03.b(this.errorDescription, sendEndSongsFailure.errorDescription);
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
            StringBuilder sb = new StringBuilder("SendEndSongsFailure(errorCode=");
            sb.append(this.errorCode);
            sb.append(", errorDescription=");
            return hf2.d(sb, this.errorDescription, ')');
        }
    }

    public static final class SendEndSongsSuccess extends SendEndSongsResult {
        public static final SendEndSongsSuccess INSTANCE = new SendEndSongsSuccess();

        private SendEndSongsSuccess() {
            super(null);
        }
    }

    private SendEndSongsResult() {
    }

    public /* synthetic */ SendEndSongsResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
