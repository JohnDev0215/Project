package com.visualon.OSMPSubTitle.DataObject;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes7.dex */
public class VOSubtitleDisplayInfo {
    public ArrayList<VOSubtitleTextRowInfo> lineTextRowInfoList = new ArrayList<>();
    public VOSubtitleTextDisplayDescriptor dispDescriptor = new VOSubtitleTextDisplayDescriptor();
    public ArrayList<VOSubtitleImageInfo> imageInfo = new ArrayList<>();

    public ArrayList<VOSubtitleTextRowInfo> getLineTextRowInfoList() {
        return this.lineTextRowInfoList;
    }

    public VOSubtitleTextDisplayDescriptor getDispDescriptor() {
        return this.dispDescriptor;
    }

    public ArrayList<VOSubtitleImageInfo> getImageInfo() {
        return this.imageInfo;
    }
}
