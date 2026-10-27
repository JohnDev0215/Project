package defpackage;

import android.net.Uri;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class CG {
    public static final String[] a = {"", "http://www.", "https://www.", "http://", "https://", "tel:", "mailto:", "ftp://anonymous:anonymous@", "ftp://ftp.", "ftps://", "sftp://", "smb://", "nfs://", "ftp://", "dav://", "news:", "telnet://", "imap:", "rtsp://", "urn:", "pop:", "sip:", "sips:", "tftp:", "btspp://", "btl2cap://", "btgoep://", "tcpobex://", "irdaobex://", "file://", "urn:epc:id:", "urn:epc:tag:", "urn:epc:pat:", "urn:epc:raw:", "urn:epc:", "urn:nfc:"};

    public static NdefRecord a(byte[] bArr, String str, boolean z) {
        byte b;
        String[] strArr = a;
        String string = Uri.parse(new String(bArr, "UTF-8")).normalizeScheme().toString();
        if (string.length() == 0) {
            throw new IllegalArgumentException("uri is empty");
        }
        byte[] bArrB = str == null ? null : AbstractC1623us.b(str);
        if (z) {
            return new NdefRecord((short) 3, AbstractC1623us.b(string), bArrB, null);
        }
        int i = 1;
        while (true) {
            if (i >= strArr.length) {
                b = 0;
                break;
            }
            if (string.startsWith(strArr[i])) {
                b = (byte) i;
                string = string.substring(strArr[i].length());
                break;
            }
            i++;
        }
        byte[] bArrB2 = AbstractC1623us.b(string);
        byte[] bArr2 = new byte[bArrB2.length + 1];
        bArr2[0] = b;
        System.arraycopy(bArrB2, 0, bArr2, 1, bArrB2.length);
        return new NdefRecord((short) 1, NdefRecord.RTD_URI, bArrB, bArr2);
    }

    public static C1318pF b(Uri uri, boolean z) {
        if (uri == null) {
            return null;
        }
        C1318pF c1318pF = new C1318pF();
        c1318pF.b = 0;
        if (z) {
            c1318pF.c = "absolute-url";
        } else {
            c1318pF.c = "url";
        }
        c1318pF.h = AbstractC1623us.b(uri.toString());
        return c1318pF;
    }

    public static C1263oF c(byte[] bArr) {
        try {
            return f(new NdefMessage(bArr));
        } catch (FormatException | UnsupportedEncodingException unused) {
            return null;
        }
    }

    public static boolean d(String str) {
        if (Charset.forName("US-ASCII").newEncoder().canEncode(str) && str.length() >= 2 && str.length() <= 256 && str.charAt(0) == ':') {
            return Character.isLowerCase(str.charAt(1)) || Character.isDigit(str.charAt(1));
        }
        return false;
    }

    public static NdefMessage e(C1263oF c1263oF) {
        try {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < c1263oF.b.length; i++) {
                arrayList.add(g(c1263oF.b[i]));
            }
            NdefRecord[] ndefRecordArr = new NdefRecord[arrayList.size()];
            arrayList.toArray(ndefRecordArr);
            return new NdefMessage(ndefRecordArr);
        } catch (BG | UnsupportedEncodingException | IllegalArgumentException unused) {
            throw new BG();
        }
    }

    public static C1263oF f(NdefMessage ndefMessage) {
        C1318pF c1318pF;
        NdefRecord[] records = ndefMessage.getRecords();
        C1263oF c1263oF = new C1263oF();
        ArrayList arrayList = new ArrayList();
        for (NdefRecord ndefRecord : records) {
            short tnf = ndefRecord.getTnf();
            C1318pF c1318pF2 = null;
            if (tnf == 0) {
                c1318pF2 = new C1318pF();
                c1318pF2.b = 0;
                c1318pF2.c = "empty";
                c1318pF2.h = new byte[0];
            } else if (tnf != 1) {
                if (tnf == 2) {
                    String str = new String(ndefRecord.getType(), "UTF-8");
                    byte[] payload = ndefRecord.getPayload();
                    c1318pF = new C1318pF();
                    c1318pF.b = 0;
                    c1318pF.c = "mime";
                    c1318pF.d = str;
                    c1318pF.h = payload;
                } else if (tnf == 3) {
                    c1318pF2 = b(ndefRecord.toUri(), true);
                } else if (tnf == 4) {
                    String str2 = new String(ndefRecord.getType(), "UTF-8");
                    byte[] payload2 = ndefRecord.getPayload();
                    c1318pF = new C1318pF();
                    c1318pF.b = 1;
                    c1318pF.c = str2;
                    c1318pF.h = payload2;
                    c1318pF.i = c(payload2);
                } else if (tnf == 5) {
                    byte[] payload3 = ndefRecord.getPayload();
                    c1318pF2 = new C1318pF();
                    c1318pF2.b = 0;
                    c1318pF2.c = "unknown";
                    c1318pF2.h = payload3;
                }
                c1318pF2 = c1318pF;
            } else if (Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_URI)) {
                c1318pF2 = b(ndefRecord.toUri(), false);
            } else if (Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
                byte[] payload4 = ndefRecord.getPayload();
                if (payload4.length != 0) {
                    c1318pF = new C1318pF();
                    c1318pF.b = 0;
                    c1318pF.c = "text";
                    c1318pF.f = (payload4[0] & 128) == 0 ? "utf-8" : "utf-16";
                    int i = payload4[0] & 63;
                    c1318pF.g = new String(payload4, 1, i, "US-ASCII");
                    int i2 = i + 1;
                    if (i2 <= payload4.length) {
                        c1318pF.h = Arrays.copyOfRange(payload4, i2, payload4.length);
                        c1318pF2 = c1318pF;
                    }
                }
            } else if (Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_SMART_POSTER)) {
                byte[] payload5 = ndefRecord.getPayload();
                c1318pF2 = new C1318pF();
                c1318pF2.b = 0;
                c1318pF2.c = "smart-poster";
                c1318pF2.h = payload5;
                c1318pF2.i = c(payload5);
            } else {
                String str3 = ':' + new String(ndefRecord.getType(), "UTF-8");
                if (d(str3)) {
                    byte[] payload6 = ndefRecord.getPayload();
                    C1318pF c1318pF3 = new C1318pF();
                    c1318pF3.b = 2;
                    c1318pF3.c = str3;
                    c1318pF3.h = payload6;
                    c1318pF3.i = c(payload6);
                    c1318pF2 = c1318pF3;
                }
            }
            if (c1318pF2 != null && ndefRecord.getTnf() != 0) {
                c1318pF2.e = new String(ndefRecord.getId(), "UTF-8");
            }
            if (c1318pF2 != null) {
                arrayList.add(c1318pF2);
            }
        }
        C1318pF[] c1318pFArr = new C1318pF[arrayList.size()];
        c1263oF.b = c1318pFArr;
        arrayList.toArray(c1318pFArr);
        return c1263oF;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00a5, code lost:
    
        throw new defpackage.BG();
     */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.nfc.NdefRecord g(defpackage.C1318pF r14) throws defpackage.BG {
        /*
            Method dump skipped, instruction units count: 766
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.CG.g(pF):android.nfc.NdefRecord");
    }
}
