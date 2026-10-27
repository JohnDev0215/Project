package com.byd.card.bean;

import com.byd.card.drag.ApplicationInfo;

/* JADX INFO: loaded from: classes.dex */
public class MessageEvent {
    public ApplicationInfo applicationInfo;
    public String pkg;
    public AppOrShortcutItem popAddCardDataItem;
    public String tag;

    public MessageEvent(String tag) {
        this.tag = tag;
    }

    public MessageEvent(String tag, String pkg) {
        this.tag = tag;
        this.pkg = pkg;
    }

    public MessageEvent(String tag, AppOrShortcutItem popAddCardDataItem) {
        this.tag = tag;
        this.popAddCardDataItem = popAddCardDataItem;
    }

    public MessageEvent(String tag, ApplicationInfo applicationInfo) {
        this.tag = tag;
        this.applicationInfo = applicationInfo;
    }
}
