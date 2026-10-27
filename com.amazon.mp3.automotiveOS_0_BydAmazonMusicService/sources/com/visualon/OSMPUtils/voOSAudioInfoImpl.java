package com.visualon.OSMPUtils;

import android.os.Parcel;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes7.dex */
public class voOSAudioInfoImpl implements voOSAudioInfo {
    private voOSAudioFormat mFormat;
    private String mLanguage;

    public voOSAudioInfoImpl() {
    }

    public voOSAudioInfoImpl(voOSAudioFormat voosaudioformat, String str) {
        this.mFormat = voosaudioformat;
        this.mLanguage = str;
    }

    @Override // com.visualon.OSMPUtils.voOSAudioInfo
    public boolean parse(Parcel parcel) {
        voOSAudioFormatImpl voosaudioformatimpl = new voOSAudioFormatImpl();
        this.mFormat = voosaudioformatimpl;
        voosaudioformatimpl.parse(parcel);
        byte[] bArr = new byte[256];
        parcel.readByteArray(bArr);
        try {
            String str = new String(bArr, 0, 256, "utf-8");
            this.mLanguage = str;
            this.mLanguage = str.trim();
            return true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override // com.visualon.OSMPUtils.voOSAudioInfo
    public voOSAudioFormat Format() {
        return this.mFormat;
    }

    @Override // com.visualon.OSMPUtils.voOSAudioInfo
    public String Language() {
        return this.mLanguage;
    }
}
