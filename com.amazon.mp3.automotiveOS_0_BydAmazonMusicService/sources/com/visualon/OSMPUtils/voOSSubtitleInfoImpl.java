package com.visualon.OSMPUtils;

import android.os.Parcel;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes7.dex */
public class voOSSubtitleInfoImpl implements voOSSubtitleInfo {
    private String mAlign;
    private int mCodingType;
    private String mLanguage;

    public boolean parse(Parcel parcel) {
        byte[] bArr = new byte[256];
        parcel.readByteArray(bArr);
        try {
            String str = new String(bArr, 0, 256, "utf-8");
            this.mLanguage = str;
            this.mLanguage = str.trim();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.mCodingType = parcel.readInt();
        byte[] bArr2 = new byte[8];
        parcel.readByteArray(bArr2);
        try {
            String str2 = new String(bArr2, 0, 8, "utf-8");
            this.mAlign = str2;
            this.mAlign = str2.trim();
            return true;
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return true;
        }
    }

    @Override // com.visualon.OSMPUtils.voOSSubtitleInfo
    public String Language() {
        return this.mLanguage;
    }

    @Override // com.visualon.OSMPUtils.voOSSubtitleInfo
    public int CodingType() {
        return this.mCodingType;
    }

    @Override // com.visualon.OSMPUtils.voOSSubtitleInfo
    public String Align() {
        return this.mAlign;
    }
}
