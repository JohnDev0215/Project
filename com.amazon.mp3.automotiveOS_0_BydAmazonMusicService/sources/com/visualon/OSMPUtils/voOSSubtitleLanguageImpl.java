package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public class voOSSubtitleLanguageImpl implements voOSSubtitleLanguage {
    int mFlag;
    String mLangName;
    int mLanguageType;
    int mReserved1;
    int mReserved2;

    public voOSSubtitleLanguageImpl(String str, int i, int i2, int i3, int i4) {
        this.mLangName = str;
        this.mLanguageType = i;
        this.mFlag = i2;
        this.mReserved1 = i3;
        this.mReserved2 = i4;
    }

    @Override // com.visualon.OSMPUtils.voOSSubtitleLanguage
    public String LangName() {
        return this.mLangName;
    }

    @Override // com.visualon.OSMPUtils.voOSSubtitleLanguage
    public int LanguageType() {
        return this.mLanguageType;
    }

    @Override // com.visualon.OSMPUtils.voOSSubtitleLanguage
    public int Flag() {
        return this.mFlag;
    }

    @Override // com.visualon.OSMPUtils.voOSSubtitleLanguage
    public int Reserved1() {
        return this.mReserved1;
    }

    @Override // com.visualon.OSMPUtils.voOSSubtitleLanguage
    public int Reserved2() {
        return this.mReserved2;
    }
}
