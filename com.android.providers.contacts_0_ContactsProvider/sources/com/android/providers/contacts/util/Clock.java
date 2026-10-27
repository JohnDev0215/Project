package com.android.providers.contacts.util;

/* JADX INFO: loaded from: classes.dex */
public class Clock {
    private static final Clock INSTANCE;
    private static Clock sInstance;

    static {
        Clock clock = new Clock();
        INSTANCE = clock;
        sInstance = clock;
    }

    public static final Clock getInstance() {
        return sInstance;
    }

    static void injectInstance(Clock clock) {
        sInstance = clock;
    }

    static void resetInstance() {
        sInstance = INSTANCE;
    }

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
