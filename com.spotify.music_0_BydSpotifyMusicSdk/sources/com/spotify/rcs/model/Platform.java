package com.spotify.rcs.model;

import p.n51;
import p.o51;
import p.p51;

/* JADX INFO: loaded from: classes.dex */
public enum Platform implements n51 {
    UNKNOWN(0),
    ANDROID(1),
    BACKEND(2),
    IOS(3),
    WEB(4),
    UNRECOGNIZED(-1);

    private static final o51 internalValueMap = new o51() { // from class: com.spotify.rcs.model.Platform.1
        /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
        public Platform m346findValueByNumber(int i) {
            return Platform.a(i);
        }
    };
    private final int value;

    public static final class PlatformVerifier implements p51 {
        static final p51 INSTANCE = new PlatformVerifier();

        private PlatformVerifier() {
        }

        @Override // p.p51
        public boolean isInRange(int i) {
            return Platform.a(i) != null;
        }
    }

    Platform(int i) {
        this.value = i;
    }

    public static Platform a(int i) {
        if (i == 0) {
            return UNKNOWN;
        }
        if (i == 1) {
            return ANDROID;
        }
        if (i == 2) {
            return BACKEND;
        }
        if (i == 3) {
            return IOS;
        }
        if (i != 4) {
            return null;
        }
        return WEB;
    }

    @Override // p.n51
    public final int getNumber() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
