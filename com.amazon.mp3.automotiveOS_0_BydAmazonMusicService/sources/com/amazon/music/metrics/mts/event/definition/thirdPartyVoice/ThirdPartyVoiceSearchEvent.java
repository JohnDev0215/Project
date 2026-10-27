package com.amazon.music.metrics.mts.event.definition.thirdPartyVoice;

import com.amazon.music.metrics.mts.MTSEvent;

/* JADX INFO: loaded from: classes4.dex */
public class ThirdPartyVoiceSearchEvent extends MTSEvent {
    public ThirdPartyVoiceSearchEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        super("thirdPartyVoiceSearch", 1L);
        if (str != null) {
            addAttribute("thirdPartyVoiceAssistantName", str);
        }
        if (str2 != null) {
            addAttribute("googleAssistantSearchQuery", str2);
        }
        if (str3 != null) {
            addAttribute("googleAssistantMediaAlbum", str3);
        }
        if (str4 != null) {
            addAttribute("googleAssistantMediaArtist", str4);
        }
        if (str5 != null) {
            addAttribute("googleAssistantMediaGenre", str5);
        }
        if (str6 != null) {
            addAttribute("googleAssistantMediaPlaylist", str6);
        }
        if (str7 != null) {
            addAttribute("googleAssistantMediaTitle", str7);
        }
        if (str8 != null) {
            addAttribute("googleAssistantMediaFocus", str8);
        }
        if (str9 != null) {
            addAttribute("playbackInstanceId", str9);
        }
        if (str10 != null) {
            addAttribute("thirdPartyVoiceUtteranceId", str10);
        }
    }
}
