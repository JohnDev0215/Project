package com.spotify.cosmos.util.proto;

import p.n51;
import p.o51;
import p.p51;

/* JADX INFO: loaded from: classes.dex */
public enum PlayabilityRestriction implements n51 {
    UNKNOWN(0),
    NO_RESTRICTION(1),
    EXPLICIT_CONTENT(2),
    AGE_RESTRICTED(3),
    NOT_IN_CATALOGUE(4),
    NOT_AVAILABLE_OFFLINE(5);

    public static final int AGE_RESTRICTED_VALUE = 3;
    public static final int EXPLICIT_CONTENT_VALUE = 2;
    public static final int NOT_AVAILABLE_OFFLINE_VALUE = 5;
    public static final int NOT_IN_CATALOGUE_VALUE = 4;
    public static final int NO_RESTRICTION_VALUE = 1;
    public static final int UNKNOWN_VALUE = 0;
    private static final o51 internalValueMap = new o51() { // from class: com.spotify.cosmos.util.proto.PlayabilityRestriction.1
        /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
        public PlayabilityRestriction m285findValueByNumber(int i) {
            return PlayabilityRestriction.forNumber(i);
        }
    };
    private final int value;

    public static final class PlayabilityRestrictionVerifier implements p51 {
        static final p51 INSTANCE = new PlayabilityRestrictionVerifier();

        private PlayabilityRestrictionVerifier() {
        }

        @Override // p.p51
        public boolean isInRange(int i) {
            return PlayabilityRestriction.forNumber(i) != null;
        }
    }

    PlayabilityRestriction(int i) {
        this.value = i;
    }

    public static PlayabilityRestriction forNumber(int i) {
        if (i == 0) {
            return UNKNOWN;
        }
        if (i == 1) {
            return NO_RESTRICTION;
        }
        if (i == 2) {
            return EXPLICIT_CONTENT;
        }
        if (i == 3) {
            return AGE_RESTRICTED;
        }
        if (i == 4) {
            return NOT_IN_CATALOGUE;
        }
        if (i != 5) {
            return null;
        }
        return NOT_AVAILABLE_OFFLINE;
    }

    public static o51 internalGetValueMap() {
        return internalValueMap;
    }

    public static p51 internalGetVerifier() {
        return PlayabilityRestrictionVerifier.INSTANCE;
    }

    @Override // p.n51
    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static PlayabilityRestriction valueOf(int i) {
        return forNumber(i);
    }
}
