package com.android.bluetooth.mapclient;

import android.net.INetd;
import com.android.vcard.VCardEntry;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class BmessageBuilder {
    private static final String BBODY_BEGIN = "BEGIN:BBODY";
    private static final String BBODY_CHARSET = "CHARSET:";
    private static final String BBODY_ENCODING = "ENCODING:";
    private static final String BBODY_END = "END:BBODY";
    private static final String BBODY_LANGUAGE = "LANGUAGE:";
    private static final String BBODY_LENGTH = "LENGTH:";
    private static final String BENV_BEGIN = "BEGIN:BENV";
    private static final String BENV_END = "END:BENV";
    private static final String BMSG_BEGIN = "BEGIN:BMSG";
    private static final String BMSG_END = "END:BMSG";
    private static final String BMSG_FOLDER = "FOLDER:";
    private static final String BMSG_STATUS = "STATUS:";
    private static final String BMSG_TYPE = "TYPE:";
    private static final String BMSG_VERSION = "VERSION:1.0";
    private static final String CRLF = "\r\n";
    private static final String MSG_BEGIN = "BEGIN:MSG";
    private static final String MSG_END = "END:MSG";
    private static final String VCARD_BEGIN = "BEGIN:VCARD";
    private static final String VCARD_EMAIL = "EMAIL:";
    private static final String VCARD_END = "END:VCARD";
    private static final String VCARD_N = "N:";
    private static final String VCARD_TEL = "TEL:";
    private static final String VCARD_VERSION = "VERSION:2.1";
    private final StringBuilder mBmsg = new StringBuilder();

    private BmessageBuilder() {
    }

    public static String createBmessage(Bmessage bmsg) {
        BmessageBuilder b = new BmessageBuilder();
        b.build(bmsg);
        return b.mBmsg.toString();
    }

    private void build(Bmessage bmsg) {
        int bodyLen = MSG_BEGIN.length() + MSG_END.length() + ("\r\n".length() * 3) + bmsg.mMessage.getBytes().length;
        StringBuilder sb = this.mBmsg;
        sb.append(BMSG_BEGIN);
        sb.append("\r\n");
        StringBuilder sb2 = this.mBmsg;
        sb2.append(BMSG_VERSION);
        sb2.append("\r\n");
        StringBuilder sb3 = this.mBmsg;
        sb3.append(BMSG_STATUS);
        sb3.append(bmsg.mBmsgStatus);
        sb3.append("\r\n");
        StringBuilder sb4 = this.mBmsg;
        sb4.append(BMSG_TYPE);
        sb4.append(bmsg.mBmsgType);
        sb4.append("\r\n");
        StringBuilder sb5 = this.mBmsg;
        sb5.append(BMSG_FOLDER);
        sb5.append(bmsg.mBmsgFolder);
        sb5.append("\r\n");
        for (VCardEntry vcard : bmsg.mOriginators) {
            buildVcard(vcard);
        }
        StringBuilder sb6 = this.mBmsg;
        sb6.append(BENV_BEGIN);
        sb6.append("\r\n");
        for (VCardEntry vcard2 : bmsg.mRecipients) {
            buildVcard(vcard2);
        }
        StringBuilder sb7 = this.mBmsg;
        sb7.append(BBODY_BEGIN);
        sb7.append("\r\n");
        if (bmsg.mBbodyEncoding != null) {
            StringBuilder sb8 = this.mBmsg;
            sb8.append(BBODY_ENCODING);
            sb8.append(bmsg.mBbodyEncoding);
            sb8.append("\r\n");
        }
        if (bmsg.mBbodyCharset != null) {
            StringBuilder sb9 = this.mBmsg;
            sb9.append(BBODY_CHARSET);
            sb9.append(bmsg.mBbodyCharset);
            sb9.append("\r\n");
        }
        if (bmsg.mBbodyLanguage != null) {
            StringBuilder sb10 = this.mBmsg;
            sb10.append(BBODY_LANGUAGE);
            sb10.append(bmsg.mBbodyLanguage);
            sb10.append("\r\n");
        }
        StringBuilder sb11 = this.mBmsg;
        sb11.append(BBODY_LENGTH);
        sb11.append(bodyLen);
        sb11.append("\r\n");
        StringBuilder sb12 = this.mBmsg;
        sb12.append(MSG_BEGIN);
        sb12.append("\r\n");
        StringBuilder sb13 = this.mBmsg;
        sb13.append(bmsg.mMessage);
        sb13.append("\r\n");
        StringBuilder sb14 = this.mBmsg;
        sb14.append(MSG_END);
        sb14.append("\r\n");
        StringBuilder sb15 = this.mBmsg;
        sb15.append(BBODY_END);
        sb15.append("\r\n");
        StringBuilder sb16 = this.mBmsg;
        sb16.append(BENV_END);
        sb16.append("\r\n");
        StringBuilder sb17 = this.mBmsg;
        sb17.append(BMSG_END);
        sb17.append("\r\n");
    }

    private void buildVcard(VCardEntry vcard) {
        String n = buildVcardN(vcard);
        List<VCardEntry.PhoneData> tel = vcard.getPhoneList();
        List<VCardEntry.EmailData> email = vcard.getEmailList();
        StringBuilder sb = this.mBmsg;
        sb.append(VCARD_BEGIN);
        sb.append("\r\n");
        StringBuilder sb2 = this.mBmsg;
        sb2.append(VCARD_VERSION);
        sb2.append("\r\n");
        StringBuilder sb3 = this.mBmsg;
        sb3.append(VCARD_N);
        sb3.append(n);
        sb3.append("\r\n");
        if (tel != null && tel.size() > 0) {
            StringBuilder sb4 = this.mBmsg;
            sb4.append(VCARD_TEL);
            sb4.append(tel.get(0).getNumber());
            sb4.append("\r\n");
        }
        if (email != null && email.size() > 0) {
            StringBuilder sb5 = this.mBmsg;
            sb5.append(VCARD_EMAIL);
            sb5.append(email.get(0).getAddress());
            sb5.append("\r\n");
        }
        StringBuilder sb6 = this.mBmsg;
        sb6.append(VCARD_END);
        sb6.append("\r\n");
    }

    private String buildVcardN(VCardEntry vcard) {
        VCardEntry.NameData nd = vcard.getNameData();
        StringBuilder sb = new StringBuilder();
        sb.append(nd.getFamily());
        sb.append(";");
        String given = nd.getGiven();
        String suffix = INetd.NEXTHOP_NONE;
        sb.append(given == null ? INetd.NEXTHOP_NONE : nd.getGiven());
        sb.append(";");
        sb.append(nd.getMiddle() == null ? INetd.NEXTHOP_NONE : nd.getMiddle());
        sb.append(";");
        sb.append(nd.getPrefix() == null ? INetd.NEXTHOP_NONE : nd.getPrefix());
        sb.append(";");
        if (nd.getSuffix() != null) {
            suffix = nd.getSuffix();
        }
        sb.append(suffix);
        return sb.toString();
    }
}
