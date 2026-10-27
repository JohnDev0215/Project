package com.amazon.music.endpoint;

import com.amazonaws.regions.ServiceAbbreviations;

/* JADX INFO: loaded from: classes4.dex */
public enum EndpointType {
    CIRRUS("cirrus"),
    DMLS(com.amazon.mp3.environment.Environment.DMLS_PATH),
    MPQS_VOICE_ENABLED("mpqsVoiceEnabled"),
    MPQS_NON_VOICE_ENABLED("mpqsNonVoiceEnabled"),
    MUSE("muse"),
    MUSE_BRUSH("muse_brush"),
    NAUTILUS(com.amazon.mp3.environment.Environment.NAUTILUS_PATH),
    NIMBLY("nimbly"),
    PLAYLIST(com.amazon.mp3.environment.Environment.PLAYLIST_PATH),
    STRATUS(com.amazon.mp3.environment.Environment.STRATUS_PATH),
    TENZING_TEXT_SEARCH("tenzingTextSearch"),
    TENZING_TEXT_SUGGEST("tenzingTextSuggest"),
    TENZING_TEXT_INSTANT_SEARCH("tenzingTextInstantSearch"),
    HELP("help"),
    LEGAL("legal"),
    IAM(ServiceAbbreviations.IAM);

    private final String text;

    EndpointType(String str) {
        this.text = str;
    }

    String getText() {
        return this.text;
    }
}
