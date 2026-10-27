package com.visualon.OSMPSubTitle.DataObject;

/* JADX INFO: loaded from: classes7.dex */
public class ImageStore {
    public byte[] data;
    public int height;
    public int id;
    public int size;
    public int type;
    public int width;

    public ImageStore(int i, int i2, int i3, int i4, int i5, byte[] bArr) {
        this.id = i;
        this.width = i2;
        this.height = i3;
        this.size = i4;
        this.data = bArr;
        this.type = i5;
    }
}
