package com.amazon.music.destination;

import com.amazon.music.destination.parser.Action;

/* JADX INFO: loaded from: classes4.dex */
public class VideoDestination extends AsinActionDestination {
    private Boolean isVideoStoryDeeplink;

    public VideoDestination(Boolean bool, String str, Action action, String str2, String str3) {
        super(null, str, action, str2, str3, true);
        this.isVideoStoryDeeplink = bool;
    }

    public VideoDestination(String str, Action action, String str2, String str3) {
        super(null, str, action, str2, str3, true);
        this.isVideoStoryDeeplink = Boolean.FALSE;
    }

    public VideoDestination(Action action, String str, String str2) {
        super(null, null, action, str, str2, true);
        this.isVideoStoryDeeplink = Boolean.FALSE;
    }

    public Boolean isVideoStoryDeeplink() {
        return this.isVideoStoryDeeplink;
    }
}
