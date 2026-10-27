package com.amazon.avod.playback.smoothstream.rubymanifestparser;

import com.amazon.avod.playback.smoothstream.ProtectionHeader;

/* JADX INFO: loaded from: classes2.dex */
class RubyProtectionHeader implements ProtectionHeader {
    private final String mEncodedProtectionData;
    private final String mSystemID;

    RubyProtectionHeader(String str, String str2) {
        this.mSystemID = str;
        this.mEncodedProtectionData = str2;
    }

    static ProtectionHeader newProtectionHeaderFromHandle(long j, RubyManifestParserJni rubyManifestParserJni) {
        String[] protectionHeaderMetaData = rubyManifestParserJni.getProtectionHeaderMetaData(j);
        return new RubyProtectionHeader(protectionHeaderMetaData[0], protectionHeaderMetaData[1]);
    }

    @Override // com.amazon.avod.playback.smoothstream.ProtectionHeader
    public String getEncodedProtectionData() {
        return this.mEncodedProtectionData;
    }
}
