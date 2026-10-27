package com.visualon.OSMPSubTitle.DataObject;

/* JADX INFO: loaded from: classes7.dex */
public class VOSubtitleInfoEntry {
    public int duration = -1;
    public VORect subtitleRectInfo = new VORect();
    public VOSubtitleDisplayInfo subtitleDispInfo = new VOSubtitleDisplayInfo();
    public int horizontalAlignment = 0;
    public int verticalAlignment = 0;

    public int getDuration() {
        return this.duration;
    }

    public VORect getSubtitleRectInfo() {
        return this.subtitleRectInfo;
    }

    public VOSubtitleDisplayInfo getSubtitleDispInfo() {
        return this.subtitleDispInfo;
    }
}
