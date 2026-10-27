package com.google.firebase.crashlytics.internal.common;

/* JADX INFO: loaded from: classes.dex */
public enum DeliveryMechanism {
    DEVELOPER(1),
    /* JADX INFO: Fake field, exist only in values array */
    USER_SIDELOAD(2),
    /* JADX INFO: Fake field, exist only in values array */
    TEST_DISTRIBUTION(3),
    APP_STORE(4);

    private final int id;

    DeliveryMechanism(int i) {
        this.id = i;
    }

    public final int a() {
        return this.id;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return Integer.toString(this.id);
    }
}
