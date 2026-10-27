package com.google.firebase.installations;

import com.google.firebase.FirebaseException;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseInstallationsException extends FirebaseException {
    private final Status status;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class Status {
        public static final Status c;
        public static final Status e;
        public static final Status k;
        public static final /* synthetic */ Status[] l;

        static {
            Status status = new Status("BAD_CONFIG", 0);
            c = status;
            Status status2 = new Status("UNAVAILABLE", 1);
            e = status2;
            Status status3 = new Status("TOO_MANY_REQUESTS", 2);
            k = status3;
            l = new Status[]{status, status2, status3};
        }

        public static Status valueOf(String str) {
            return (Status) Enum.valueOf(Status.class, str);
        }

        public static Status[] values() {
            return (Status[]) l.clone();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirebaseInstallationsException(String str, Status status) {
        super(str);
        he1.f("Detail message must not be empty", str);
        this.status = status;
    }

    public FirebaseInstallationsException() {
        this.status = Status.c;
    }
}
