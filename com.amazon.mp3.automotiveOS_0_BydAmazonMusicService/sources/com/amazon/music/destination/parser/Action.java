package com.amazon.music.destination.parser;

import com.amazon.music.find.utils.PageUriUtils;

/* JADX INFO: loaded from: classes4.dex */
public enum Action {
    PLAY(PageUriUtils.DO_PLAY_VALUE),
    PLAYFULL(PageUriUtils.DO_PLAYFULL_VALUE),
    DOWNLOAD("download"),
    HIGHLIGHT("highlight"),
    PLAY_SHUFFLE("playshuffle"),
    RENEW("renew"),
    PLAY_SPOTLIGHT("playspotlight"),
    PLAY_MUSIC_EXPERIENCE("playmusicexperience"),
    SHARE("share");

    private String value;

    Action(String str) {
        this.value = str;
    }

    public static Action fromString(String str) {
        for (Action action : values()) {
            if (action.value.equals(str)) {
                return action;
            }
        }
        return null;
    }
}
