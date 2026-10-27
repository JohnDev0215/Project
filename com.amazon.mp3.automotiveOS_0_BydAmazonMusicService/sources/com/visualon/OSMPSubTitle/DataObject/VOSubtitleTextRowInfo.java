package com.visualon.OSMPSubTitle.DataObject;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes7.dex */
public class VOSubtitleTextRowInfo {
    public ArrayList<VOSubtitleTextInfoEntry> segmentTextInfoList = new ArrayList<>();
    public VOSubtitleTextRowDescriptor textRowDes = new VOSubtitleTextRowDescriptor();

    public ArrayList<VOSubtitleTextInfoEntry> getSegmentTextInfoList() {
        return this.segmentTextInfoList;
    }
}
