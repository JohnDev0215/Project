package com.spotify.connectivity.logoutanalyticsdelegate;

import com.spotify.authentication.authtriggerserviceapi.AuthLogoutReason;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class LogoutEvent {

    public static final class DidLogout extends LogoutEvent {
        public static final DidLogout INSTANCE = new DidLogout();

        private DidLogout() {
            super(null);
        }
    }

    public static final class OfflineUserRemoved extends LogoutEvent {
        public static final OfflineUserRemoved INSTANCE = new OfflineUserRemoved();

        private OfflineUserRemoved() {
            super(null);
        }
    }

    public static final class StoredCredentialsRemoved extends LogoutEvent {
        public static final StoredCredentialsRemoved INSTANCE = new StoredCredentialsRemoved();

        private StoredCredentialsRemoved() {
            super(null);
        }
    }

    public static final class WillLogout extends LogoutEvent {
        private final boolean newLoginFlow;
        private final AuthLogoutReason reason;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WillLogout(AuthLogoutReason authLogoutReason, boolean z) {
            super(null);
            v03.j(authLogoutReason, "reason");
            this.reason = authLogoutReason;
            this.newLoginFlow = z;
        }

        public static /* synthetic */ WillLogout copy$default(WillLogout willLogout, AuthLogoutReason authLogoutReason, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                authLogoutReason = willLogout.reason;
            }
            if ((i & 2) != 0) {
                z = willLogout.newLoginFlow;
            }
            return willLogout.copy(authLogoutReason, z);
        }

        public final AuthLogoutReason component1() {
            return this.reason;
        }

        public final boolean component2() {
            return this.newLoginFlow;
        }

        public final WillLogout copy(AuthLogoutReason authLogoutReason, boolean z) {
            v03.j(authLogoutReason, "reason");
            return new WillLogout(authLogoutReason, z);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof WillLogout)) {
                return false;
            }
            WillLogout willLogout = (WillLogout) obj;
            return this.reason == willLogout.reason && this.newLoginFlow == willLogout.newLoginFlow;
        }

        public final boolean getNewLoginFlow() {
            return this.newLoginFlow;
        }

        public final AuthLogoutReason getReason() {
            return this.reason;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v1, types: [int] */
        /* JADX WARN: Type inference failed for: r1v2 */
        /* JADX WARN: Type inference failed for: r1v3 */
        public int hashCode() {
            int iHashCode = this.reason.hashCode() * 31;
            boolean z = this.newLoginFlow;
            ?? r1 = z;
            if (z) {
                r1 = 1;
            }
            return iHashCode + r1;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("WillLogout(reason=");
            sb.append(this.reason);
            sb.append(", newLoginFlow=");
            return u4.n(sb, this.newLoginFlow, ')');
        }

        public /* synthetic */ WillLogout(AuthLogoutReason authLogoutReason, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(authLogoutReason, (i & 2) != 0 ? false : z);
        }
    }

    private LogoutEvent() {
    }

    public /* synthetic */ LogoutEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final String message() {
        if (this instanceof WillLogout) {
            return "auth.will_logout";
        }
        if (this instanceof StoredCredentialsRemoved) {
            return "auth.stored_credentials_removed";
        }
        if (this instanceof OfflineUserRemoved) {
            return "auth.offline_user_removed";
        }
        if (this instanceof DidLogout) {
            return "auth.did_logout";
        }
        throw new NoWhenBranchMatchedException();
    }
}
