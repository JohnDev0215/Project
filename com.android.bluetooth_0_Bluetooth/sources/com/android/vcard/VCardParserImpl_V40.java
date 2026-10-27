package com.android.vcard;

import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
class VCardParserImpl_V40 extends VCardParserImpl_V30 {
    public VCardParserImpl_V40() {
    }

    public VCardParserImpl_V40(int vcardType) {
        super(vcardType);
    }

    @Override // com.android.vcard.VCardParserImpl_V30, com.android.vcard.VCardParserImpl_V21
    protected int getVersion() {
        return 2;
    }

    @Override // com.android.vcard.VCardParserImpl_V30, com.android.vcard.VCardParserImpl_V21
    protected String getVersionString() {
        return VCardConstants.VERSION_V40;
    }

    @Override // com.android.vcard.VCardParserImpl_V30, com.android.vcard.VCardParserImpl_V21
    protected String maybeUnescapeText(String text) {
        return unescapeText(text);
    }

    public static String unescapeText(String text) {
        StringBuilder builder = new StringBuilder();
        int length = text.length();
        int i = 0;
        while (i < length) {
            char ch = text.charAt(i);
            if (ch == '\\' && i < length - 1) {
                i++;
                char next_ch = text.charAt(i);
                if (next_ch == 'n' || next_ch == 'N') {
                    builder.append("\n");
                } else {
                    builder.append(next_ch);
                }
            } else {
                builder.append(ch);
            }
            i++;
        }
        return builder.toString();
    }

    public static String unescapeCharacter(char ch) {
        if (ch == 'n' || ch == 'N') {
            return "\n";
        }
        return String.valueOf(ch);
    }

    @Override // com.android.vcard.VCardParserImpl_V30, com.android.vcard.VCardParserImpl_V21
    protected Set<String> getKnownPropertyNameSet() {
        return VCardParser_V40.sKnownPropertyNameSet;
    }
}
