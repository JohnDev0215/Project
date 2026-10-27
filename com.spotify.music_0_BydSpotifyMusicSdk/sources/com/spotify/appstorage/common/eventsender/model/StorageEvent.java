package com.spotify.appstorage.common.eventsender.model;

import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class StorageEvent {

    public static final class Error {

        public enum Code {
            /* JADX INFO: Fake field, exist only in values array */
            FailedToWriteData("failedToWriteData"),
            /* JADX INFO: Fake field, exist only in values array */
            FailedToReadData("failedToReadData"),
            /* JADX INFO: Fake field, exist only in values array */
            FailedToDeleteData("failedToDeleteData"),
            /* JADX INFO: Fake field, exist only in values array */
            Other("unknownError");

            private final String rawValue;

            Code(String str) {
                this.rawValue = str;
            }
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Error)) {
                return false;
            }
            ((Error) obj).getClass();
            return v03.b(null, null);
        }

        public final int hashCode() {
            throw null;
        }

        public final String toString() {
            return "Error(message=null, code=null)";
        }
    }

    public enum Kind {
        /* JADX INFO: Fake field, exist only in values array */
        Read("read"),
        /* JADX INFO: Fake field, exist only in values array */
        Write("write"),
        /* JADX INFO: Fake field, exist only in values array */
        Delete("delete");

        private final String rawValue;

        Kind(String str) {
            this.rawValue = str;
        }
    }

    public enum StorageType {
        /* JADX INFO: Fake field, exist only in values array */
        StorageManagerCache("storageManagerCache"),
        /* JADX INFO: Fake field, exist only in values array */
        StorageManagerSettings("storageManagerSettings"),
        /* JADX INFO: Fake field, exist only in values array */
        InternalCache("internalCache"),
        /* JADX INFO: Fake field, exist only in values array */
        ExternalCache("externalCache"),
        /* JADX INFO: Fake field, exist only in values array */
        InternalFiles("internalFiles"),
        /* JADX INFO: Fake field, exist only in values array */
        ExternalFiles("externalFiles"),
        /* JADX INFO: Fake field, exist only in values array */
        SharedPreferences("sharedPreferences"),
        /* JADX INFO: Fake field, exist only in values array */
        Unknown("unknown");

        private final String rawValue;

        StorageType(String str) {
            this.rawValue = str;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StorageEvent)) {
            return false;
        }
        ((StorageEvent) obj).getClass();
        return v03.b(null, null) && v03.b(null, null);
    }

    public final int hashCode() {
        throw null;
    }

    public final String toString() {
        return "StorageEvent(kind=null, type=null, dataSize=0, date=null, durationMs=0, error=null)";
    }
}
