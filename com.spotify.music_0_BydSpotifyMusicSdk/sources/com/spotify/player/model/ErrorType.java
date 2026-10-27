package com.spotify.player.model;

import com.spotify.voiceassistants.playermodels.ContextResponseKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class ErrorType {
    public static final ErrorType A;
    public static final ErrorType B;
    public static final ErrorType C;
    public static final ErrorType D;
    public static final ErrorType E;
    public static final ErrorType F;
    public static final ErrorType G;
    public static final ErrorType H;
    public static final ErrorType I;
    public static final ErrorType J;
    public static final ErrorType K;
    public static final ErrorType L;
    public static final ErrorType M;
    public static final ErrorType N;
    public static final ErrorType O;
    public static final ErrorType P;
    public static final ErrorType Q;
    public static final ErrorType R;
    public static final ErrorType S;
    public static final ErrorType T;
    public static final ErrorType U;
    public static final ErrorType V;
    public static final ErrorType W;
    public static final ErrorType X;
    public static final /* synthetic */ ErrorType[] Y;
    public static final ErrorType c;
    public static final ErrorType e;
    public static final ErrorType k;
    public static final ErrorType l;
    public static final ErrorType m;
    public static final ErrorType n;
    public static final ErrorType o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final ErrorType f61p;
    public static final ErrorType q;
    public static final ErrorType r;
    public static final ErrorType s;
    public static final ErrorType t;
    public static final ErrorType u;
    public static final ErrorType v;
    public static final ErrorType w;
    public static final ErrorType x;
    public static final ErrorType y;
    public static final ErrorType z;

    static {
        ErrorType errorType = new ErrorType(ContextResponseKt.RESULT_SUCCESS, 0);
        c = errorType;
        ErrorType errorType2 = new ErrorType("PLAY_TOKEN_LOST", 1);
        ErrorType errorType3 = new ErrorType("PLAYBACK_STUCK", 2);
        e = errorType3;
        ErrorType errorType4 = new ErrorType("PLAYBACK_ERROR", 3);
        k = errorType4;
        ErrorType errorType5 = new ErrorType("LICENSE_CHANGE", 4);
        l = errorType5;
        ErrorType errorType6 = new ErrorType("PLAYBACK_REPORTING_ERROR", 5);
        m = errorType6;
        ErrorType errorType7 = new ErrorType("TRACK_UNAVAILABLE_OFFLINE", 6);
        ErrorType errorType8 = new ErrorType("CONTEXT_PAGE_MALFORMED_DATA", 7);
        ErrorType errorType9 = new ErrorType("CONTEXT_PAGE_MISC_ERROR", 8);
        ErrorType errorType10 = new ErrorType("CONTEXT_PAGE_NOT_FOUND", 9);
        ErrorType errorType11 = new ErrorType("PLAY_RESTRICTED", 10);
        n = errorType11;
        ErrorType errorType12 = new ErrorType("STOP_RESTRICTED", 11);
        o = errorType12;
        ErrorType errorType13 = new ErrorType("UPDATE_RESTRICTED", 12);
        f61p = errorType13;
        ErrorType errorType14 = new ErrorType("PAUSE_RESTRICTED", 13);
        q = errorType14;
        ErrorType errorType15 = new ErrorType("RESUME_RESTRICTED", 14);
        r = errorType15;
        ErrorType errorType16 = new ErrorType("SKIP_TO_PREV_RESTRICTED", 15);
        s = errorType16;
        ErrorType errorType17 = new ErrorType("SKIP_TO_NEXT_RESTRICTED", 16);
        t = errorType17;
        ErrorType errorType18 = new ErrorType("SKIP_TO_NON_EXISTENT_TRACK", 17);
        u = errorType18;
        ErrorType errorType19 = new ErrorType("SKIP_TO_NON_EXISTENT_TRACK_AUTO_STOPPED", 18);
        v = errorType19;
        ErrorType errorType20 = new ErrorType("TOGGLE_REPEAT_CONTEXT_RESTRICTED", 19);
        w = errorType20;
        ErrorType errorType21 = new ErrorType("TOGGLE_REPEAT_TRACK_RESTRICTED", 20);
        x = errorType21;
        ErrorType errorType22 = new ErrorType("TOGGLE_SHUFFLE_RESTRICTED", 21);
        y = errorType22;
        ErrorType errorType23 = new ErrorType("SET_OPTIONS_RESTRICTED", 22);
        z = errorType23;
        ErrorType errorType24 = new ErrorType("SET_QUEUE_RESTRICTED", 23);
        A = errorType24;
        ErrorType errorType25 = new ErrorType("ADD_TO_QUEUE_RESTRICTED", 24);
        B = errorType25;
        ErrorType errorType26 = new ErrorType("INTERRUPT_PLAYBACK_RESTRICTED", 25);
        C = errorType26;
        ErrorType errorType27 = new ErrorType("SEEK_TO_RESTRICTED", 26);
        D = errorType27;
        ErrorType errorType28 = new ErrorType("ONE_TRACK_UNPLAYABLE", 27);
        E = errorType28;
        ErrorType errorType29 = new ErrorType("ONE_TRACK_UNPLAYABLE_AUTO_STOPPED", 28);
        F = errorType29;
        ErrorType errorType30 = new ErrorType("ALL_TRACKS_UNPLAYABLE_AUTO_STOPPED", 29);
        G = errorType30;
        ErrorType errorType31 = new ErrorType("QUEUE_REVISION_MISMATCH", 30);
        H = errorType31;
        ErrorType errorType32 = new ErrorType("VIDEO_PLAYBACK_ERROR", 31);
        I = errorType32;
        ErrorType errorType33 = new ErrorType("VIDEO_GEORESTRICTED", 32);
        J = errorType33;
        ErrorType errorType34 = new ErrorType("VIDEO_UNSUPPORTED_PLATFORM_VERSION", 33);
        K = errorType34;
        ErrorType errorType35 = new ErrorType("VIDEO_UNSUPPORTED_CLIENT_VERSION", 34);
        L = errorType35;
        ErrorType errorType36 = new ErrorType("VIDEO_UNSUPPORTED_KEY_VERSION", 35);
        M = errorType36;
        ErrorType errorType37 = new ErrorType("VIDEO_COUNTRY_RESTRICTED", 36);
        N = errorType37;
        ErrorType errorType38 = new ErrorType("VIDEO_UNAVAILABLE", 37);
        O = errorType38;
        ErrorType errorType39 = new ErrorType("VIDEO_CATALOGUE_RESTRICTED", 38);
        P = errorType39;
        ErrorType errorType40 = new ErrorType("VIDEO_MANIFEST_DELETED", 39);
        Q = errorType40;
        ErrorType errorType41 = new ErrorType("PICK_AND_SHUFFLE_CAPPED", 40);
        R = errorType41;
        ErrorType errorType42 = new ErrorType("PICK_AND_SHUFFLE_CONNECT_RESTRICTED", 41);
        S = errorType42;
        ErrorType errorType43 = new ErrorType("CONTEXT_LOADING_FAILED", 42);
        T = errorType43;
        ErrorType errorType44 = new ErrorType("AUDIOBOOK_NOT_PLAYABLE", 43);
        U = errorType44;
        ErrorType errorType45 = new ErrorType("TIMEOUT", 44);
        V = errorType45;
        ErrorType errorType46 = new ErrorType("INVALID", 45);
        W = errorType46;
        ErrorType errorType47 = new ErrorType("UNKNOWN", 46);
        X = errorType47;
        Y = new ErrorType[]{errorType, errorType2, errorType3, errorType4, errorType5, errorType6, errorType7, errorType8, errorType9, errorType10, errorType11, errorType12, errorType13, errorType14, errorType15, errorType16, errorType17, errorType18, errorType19, errorType20, errorType21, errorType22, errorType23, errorType24, errorType25, errorType26, errorType27, errorType28, errorType29, errorType30, errorType31, errorType32, errorType33, errorType34, errorType35, errorType36, errorType37, errorType38, errorType39, errorType40, errorType41, errorType42, errorType43, errorType44, errorType45, errorType46, errorType47};
    }

    public static ErrorType valueOf(String str) {
        return (ErrorType) Enum.valueOf(ErrorType.class, str);
    }

    public static ErrorType[] values() {
        return (ErrorType[]) Y.clone();
    }
}
