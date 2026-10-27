package com.spotify.connectivity.connectiontype;

import p.v03;

/* JADX INFO: loaded from: classes.dex */
public interface ConnectionState {

    public static final class Connecting implements ConnectionState {
        public static final Connecting INSTANCE = new Connecting();

        private Connecting() {
        }
    }

    public static final class Offline implements ConnectionState {
        private final OfflineReason reason;

        public Offline(OfflineReason offlineReason) {
            v03.j(offlineReason, "reason");
            this.reason = offlineReason;
        }

        public static /* synthetic */ Offline copy$default(Offline offline, OfflineReason offlineReason, int i, Object obj) {
            if ((i & 1) != 0) {
                offlineReason = offline.reason;
            }
            return offline.copy(offlineReason);
        }

        public final OfflineReason component1() {
            return this.reason;
        }

        public final Offline copy(OfflineReason offlineReason) {
            v03.j(offlineReason, "reason");
            return new Offline(offlineReason);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Offline) && this.reason == ((Offline) obj).reason;
        }

        public final OfflineReason getReason() {
            return this.reason;
        }

        public int hashCode() {
            return this.reason.hashCode();
        }

        public String toString() {
            return "Offline(reason=" + this.reason + ')';
        }
    }

    public static final class Online implements ConnectionState {
        public static final Online INSTANCE = new Online();

        private Online() {
        }
    }
}
