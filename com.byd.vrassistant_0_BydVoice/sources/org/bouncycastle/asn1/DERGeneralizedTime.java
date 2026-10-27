package org.bouncycastle.asn1;

import com.blankj.utilcode.constant.TimeConstants;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes4.dex */
public class DERGeneralizedTime extends ASN1Object {
    String time;

    public DERGeneralizedTime(String str) {
        this.time = str;
        try {
            getDate();
        } catch (ParseException e) {
            throw new IllegalArgumentException("invalid date string: " + e.getMessage());
        }
    }

    public DERGeneralizedTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = simpleDateFormat.format(date);
    }

    DERGeneralizedTime(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length];
        for (int i = 0; i != length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        this.time = new String(cArr);
    }

    private String calculateGMTOffset() {
        String str;
        TimeZone timeZone = TimeZone.getDefault();
        int rawOffset = timeZone.getRawOffset();
        if (rawOffset < 0) {
            rawOffset = -rawOffset;
            str = "-";
        } else {
            str = "+";
        }
        int i = rawOffset / TimeConstants.HOUR;
        int i2 = (rawOffset - (((i * 60) * 60) * 1000)) / TimeConstants.MIN;
        try {
            if (timeZone.useDaylightTime() && timeZone.inDaylightTime(getDate())) {
                i += str.equals("+") ? 1 : -1;
            }
        } catch (ParseException unused) {
        }
        return "GMT" + str + convert(i) + ":" + convert(i2);
    }

    private String convert(int i) {
        return i < 10 ? "0" + i : Integer.toString(i);
    }

    public static DERGeneralizedTime getInstance(Object obj) {
        if (obj == null || (obj instanceof DERGeneralizedTime)) {
            return (DERGeneralizedTime) obj;
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static DERGeneralizedTime getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        DERObject object = aSN1TaggedObject.getObject();
        return (z || (object instanceof DERGeneralizedTime)) ? getInstance(object) : new DERGeneralizedTime(((ASN1OctetString) object).getOctets());
    }

    private byte[] getOctets() {
        char[] charArray = this.time.toCharArray();
        byte[] bArr = new byte[charArray.length];
        for (int i = 0; i != charArray.length; i++) {
            bArr[i] = (byte) charArray[i];
        }
        return bArr;
    }

    private boolean hasFractionalSeconds() {
        return this.time.indexOf(46) == 14;
    }

    @Override // org.bouncycastle.asn1.ASN1Object
    boolean asn1Equals(DERObject dERObject) {
        if (dERObject instanceof DERGeneralizedTime) {
            return this.time.equals(((DERGeneralizedTime) dERObject).time);
        }
        return false;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.DERObject
    void encode(DEROutputStream dEROutputStream) throws IOException {
        dEROutputStream.writeEncoded(24, getOctets());
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Date getDate() throws java.text.ParseException {
        /*
            Method dump skipped, instruction units count: 305
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.asn1.DERGeneralizedTime.getDate():java.util.Date");
    }

    public String getTime() {
        StringBuilder sbAppend;
        String strSubstring;
        if (this.time.charAt(r0.length() - 1) == 'Z') {
            sbAppend = new StringBuilder().append(this.time.substring(0, r1.length() - 1));
            strSubstring = "GMT+00:00";
        } else {
            int length = this.time.length() - 5;
            char cCharAt = this.time.charAt(length);
            if (cCharAt == '-' || cCharAt == '+') {
                int i = length + 3;
                sbAppend = new StringBuilder().append(this.time.substring(0, length)).append("GMT").append(this.time.substring(length, i)).append(":");
                strSubstring = this.time.substring(i);
            } else {
                int length2 = this.time.length() - 3;
                char cCharAt2 = this.time.charAt(length2);
                if (cCharAt2 == '-' || cCharAt2 == '+') {
                    sbAppend = new StringBuilder().append(this.time.substring(0, length2)).append("GMT").append(this.time.substring(length2));
                    strSubstring = ":00";
                } else {
                    sbAppend = new StringBuilder().append(this.time);
                    strSubstring = calculateGMTOffset();
                }
            }
        }
        return sbAppend.append(strSubstring).toString();
    }

    public String getTimeString() {
        return this.time;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.DERObject, org.bouncycastle.asn1.ASN1Encodable
    public int hashCode() {
        return this.time.hashCode();
    }
}
