package com.amazon.music.destination;

import com.amazon.music.destination.parser.Action;

/* JADX INFO: loaded from: classes4.dex */
public class AlbumDestination extends AsinActionDestination {
    private String mViewType;

    public AlbumDestination(String str, String str2, Action action, String str3, String str4) {
        super(str, str2, action, str3, str4);
    }

    public AlbumDestination(String str, String str2, Action action, String str3, String str4, String str5) {
        super(str, str2, action, str3, str4);
        this.mViewType = str5;
    }

    public String getViewType() {
        return this.mViewType;
    }
}
