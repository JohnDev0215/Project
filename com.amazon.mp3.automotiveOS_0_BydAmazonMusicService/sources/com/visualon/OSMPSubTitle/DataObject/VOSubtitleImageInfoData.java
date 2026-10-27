package com.visualon.OSMPSubTitle.DataObject;

import com.visualon.OSMPUtils.voOSType;

/* JADX INFO: loaded from: classes7.dex */
public class VOSubtitleImageInfoData {
    public int height;
    public voOSType.VOOSMP_IMAGE_TYPE imageType;
    public int width;
    public int size = 0;
    public byte[] picData = null;

    public byte[] getPicData() {
        return this.picData;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public voOSType.VOOSMP_IMAGE_TYPE getImageType() {
        return this.imageType;
    }
}
