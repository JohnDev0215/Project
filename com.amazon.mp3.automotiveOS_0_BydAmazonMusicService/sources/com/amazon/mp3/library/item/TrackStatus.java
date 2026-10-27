package com.amazon.mp3.library.item;

/* JADX INFO: loaded from: classes3.dex */
public enum TrackStatus {
    AVAILABLE,
    LOCKED,
    RECYCLED,
    RECYCLEDLOCKED;

    private static final String AVAILABLE_STRING = AVAILABLE.toString();

    public static boolean isAvailable(String status) {
        return AVAILABLE_STRING.equals(status);
    }
}
