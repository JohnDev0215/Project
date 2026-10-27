package p;

import com.spotify.automotive.partnervoice.PartnerVoiceSearchIntent;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class ks1 {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[PartnerVoiceSearchIntent.values().length];
        try {
            iArr[PartnerVoiceSearchIntent.PLAY.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[PartnerVoiceSearchIntent.PLAY_LIKED_SONGS.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[PartnerVoiceSearchIntent.SEARCH.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[PartnerVoiceSearchIntent.SHOW.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr[PartnerVoiceSearchIntent.SHOW_LIKED_SONGS.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr[PartnerVoiceSearchIntent.SHOW_RECENTS.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            iArr[PartnerVoiceSearchIntent.SHOW_HOME.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        a = iArr;
    }
}
