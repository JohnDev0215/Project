package com.spotify.automotive.partnervoice;

import androidx.annotation.Keep;
import com.spotify.voiceassistants.playermodels.ParsedQueryKt;
import com.spotify.voiceassistants.playermodels.ShowIntentQueryKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class PartnerVoiceSearchIntent {

    @Keep
    public static final PartnerVoiceSearchIntent PLAY;

    @Keep
    public static final PartnerVoiceSearchIntent PLAY_LIKED_SONGS;

    @Keep
    public static final PartnerVoiceSearchIntent SEARCH;

    @Keep
    public static final PartnerVoiceSearchIntent SHOW;

    @Keep
    public static final PartnerVoiceSearchIntent SHOW_HOME;

    @Keep
    public static final PartnerVoiceSearchIntent SHOW_LIKED_SONGS;

    @Keep
    public static final PartnerVoiceSearchIntent SHOW_RECENTS;
    public static final /* synthetic */ PartnerVoiceSearchIntent[] c;

    static {
        PartnerVoiceSearchIntent partnerVoiceSearchIntent = new PartnerVoiceSearchIntent(ParsedQueryKt.INTENT_PLAY, 0);
        PLAY = partnerVoiceSearchIntent;
        PartnerVoiceSearchIntent partnerVoiceSearchIntent2 = new PartnerVoiceSearchIntent("PLAY_LIKED_SONGS", 1);
        PLAY_LIKED_SONGS = partnerVoiceSearchIntent2;
        PartnerVoiceSearchIntent partnerVoiceSearchIntent3 = new PartnerVoiceSearchIntent("SEARCH", 2);
        SEARCH = partnerVoiceSearchIntent3;
        PartnerVoiceSearchIntent partnerVoiceSearchIntent4 = new PartnerVoiceSearchIntent(ShowIntentQueryKt.INTENT_SHOW, 3);
        SHOW = partnerVoiceSearchIntent4;
        PartnerVoiceSearchIntent partnerVoiceSearchIntent5 = new PartnerVoiceSearchIntent("SHOW_LIKED_SONGS", 4);
        SHOW_LIKED_SONGS = partnerVoiceSearchIntent5;
        PartnerVoiceSearchIntent partnerVoiceSearchIntent6 = new PartnerVoiceSearchIntent("SHOW_RECENTS", 5);
        SHOW_RECENTS = partnerVoiceSearchIntent6;
        PartnerVoiceSearchIntent partnerVoiceSearchIntent7 = new PartnerVoiceSearchIntent("SHOW_HOME", 6);
        SHOW_HOME = partnerVoiceSearchIntent7;
        c = new PartnerVoiceSearchIntent[]{partnerVoiceSearchIntent, partnerVoiceSearchIntent2, partnerVoiceSearchIntent3, partnerVoiceSearchIntent4, partnerVoiceSearchIntent5, partnerVoiceSearchIntent6, partnerVoiceSearchIntent7};
    }

    public static PartnerVoiceSearchIntent valueOf(String str) {
        return (PartnerVoiceSearchIntent) Enum.valueOf(PartnerVoiceSearchIntent.class, str);
    }

    public static PartnerVoiceSearchIntent[] values() {
        return (PartnerVoiceSearchIntent[]) c.clone();
    }
}
