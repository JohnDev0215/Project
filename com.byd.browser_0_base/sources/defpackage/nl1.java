package defpackage;

import android.net.Uri;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: compiled from: NdefMessageUtils.java */
/* JADX INFO: loaded from: classes3.dex */
public final class nl1 {
    public static final String[] a = {"", "http://www.", "https://www.", "http://", "https://", "tel:", "mailto:", "ftp://anonymous:anonymous@", "ftp://ftp.", "ftps://", "sftp://", "smb://", "nfs://", "ftp://", "dav://", "news:", "telnet://", "imap:", "rtsp://", "urn:", "pop:", "sip:", "sips:", "tftp:", "btspp://", "btl2cap://", "btgoep://", "tcpobex://", "irdaobex://", "file://", "urn:epc:id:", "urn:epc:tag:", "urn:epc:pat:", "urn:epc:raw:", "urn:epc:", "urn:nfc:"};

    public static NdefRecord a(byte[] bArr, String str, boolean z) {
        byte b;
        String string = Uri.parse(new String(bArr, "UTF-8")).normalizeScheme().toString();
        if (string.length() == 0) {
            throw new IllegalArgumentException("uri is empty");
        }
        byte[] bytes = str == null ? null : str.getBytes(StandardCharsets.UTF_8);
        if (z) {
            return new NdefRecord((short) 3, string.getBytes(StandardCharsets.UTF_8), bytes, null);
        }
        int i = 1;
        while (true) {
            if (i >= 36) {
                b = 0;
                break;
            }
            String[] strArr = a;
            if (string.startsWith(strArr[i])) {
                b = (byte) i;
                string = string.substring(strArr[i].length());
                break;
            }
            i++;
        }
        byte[] bArrB = c6.b(string);
        byte[] bArr2 = new byte[bArrB.length + 1];
        bArr2[0] = b;
        System.arraycopy(bArrB, 0, bArr2, 1, bArrB.length);
        return new NdefRecord((short) 1, NdefRecord.RTD_URI, bytes, bArr2);
    }

    public static ol1 b(boolean z, Uri uri) {
        if (uri == null) {
            return null;
        }
        ol1 ol1Var = new ol1(0);
        ol1Var.b = 0;
        if (z) {
            ol1Var.c = "absolute-url";
        } else {
            ol1Var.c = "url";
        }
        ol1Var.h = c6.b(uri.toString());
        return ol1Var;
    }

    public static boolean c(String str) {
        if (Charset.forName("US-ASCII").newEncoder().canEncode(str) && str.length() >= 2 && str.length() <= 256 && str.charAt(0) == ':') {
            return Character.isLowerCase(str.charAt(1)) || Character.isDigit(str.charAt(1));
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ml1 d(NdefMessage ndefMessage) {
        ol1 ol1Var;
        ol1 ol1Var2;
        NdefRecord[] records = ndefMessage.getRecords();
        ml1 ml1Var = new ml1(0);
        ArrayList arrayList = new ArrayList();
        for (NdefRecord ndefRecord : records) {
            short tnf = ndefRecord.getTnf();
            if (tnf != 0) {
                ol1Var = null;
                ml1 ml1VarD = null;
                ml1 ml1VarD2 = null;
                ml1 ml1VarD3 = null;
                ol1Var = null;
                ol1Var = null;
                ol1Var = null;
                if (tnf != 1) {
                    if (tnf == 2) {
                        String str = new String(ndefRecord.getType(), "UTF-8");
                        byte[] payload = ndefRecord.getPayload();
                        ol1Var = new ol1(0);
                        ol1Var.b = 0;
                        ol1Var.c = "mime";
                        ol1Var.d = str;
                        ol1Var.h = payload;
                    } else if (tnf == 3) {
                        ol1Var = b(true, ndefRecord.toUri());
                    } else if (tnf == 4) {
                        String str2 = new String(ndefRecord.getType(), "UTF-8");
                        byte[] payload2 = ndefRecord.getPayload();
                        ol1Var2 = new ol1(0);
                        ol1Var2.b = 1;
                        ol1Var2.c = str2;
                        ol1Var2.h = payload2;
                        try {
                            ml1VarD3 = d(new NdefMessage(payload2));
                        } catch (FormatException | UnsupportedEncodingException unused) {
                        }
                        ol1Var2.i = ml1VarD3;
                        ol1Var = ol1Var2;
                    } else if (tnf == 5) {
                        byte[] payload3 = ndefRecord.getPayload();
                        ol1Var = new ol1(0);
                        ol1Var.b = 0;
                        ol1Var.c = "unknown";
                        ol1Var.h = payload3;
                    }
                } else if (Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_URI)) {
                    ol1Var = b(false, ndefRecord.toUri());
                } else if (Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
                    byte[] payload4 = ndefRecord.getPayload();
                    if (payload4.length != 0) {
                        ol1 ol1Var3 = new ol1(0);
                        ol1Var3.b = 0;
                        ol1Var3.c = "text";
                        byte b = payload4[0];
                        ol1Var3.f = (b & 128) == 0 ? "utf-8" : "utf-16";
                        int i = b & 63;
                        ol1Var3.g = new String(payload4, 1, i, "US-ASCII");
                        int i2 = i + 1;
                        if (i2 <= payload4.length) {
                            ol1Var3.h = Arrays.copyOfRange(payload4, i2, payload4.length);
                            ol1Var = ol1Var3;
                        }
                    }
                } else if (Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_SMART_POSTER)) {
                    byte[] payload5 = ndefRecord.getPayload();
                    ol1 ol1Var4 = new ol1(0);
                    ol1Var4.b = 0;
                    ol1Var4.c = "smart-poster";
                    ol1Var4.h = payload5;
                    try {
                        ml1VarD2 = d(new NdefMessage(payload5));
                    } catch (FormatException | UnsupportedEncodingException unused2) {
                    }
                    ol1Var4.i = ml1VarD2;
                    ol1Var = ol1Var4;
                } else {
                    String strConcat = ":".concat(new String(ndefRecord.getType(), "UTF-8"));
                    if (c(strConcat)) {
                        byte[] payload6 = ndefRecord.getPayload();
                        ol1Var2 = new ol1(0);
                        ol1Var2.b = 2;
                        ol1Var2.c = strConcat;
                        ol1Var2.h = payload6;
                        try {
                            ml1VarD = d(new NdefMessage(payload6));
                        } catch (FormatException | UnsupportedEncodingException unused3) {
                        }
                        ol1Var2.i = ml1VarD;
                        ol1Var = ol1Var2;
                    }
                }
            } else {
                ol1Var = new ol1(0);
                ol1Var.b = 0;
                ol1Var.c = "empty";
                ol1Var.h = new byte[0];
            }
            if (ol1Var != null && ndefRecord.getTnf() != 0) {
                ol1Var.e = new String(ndefRecord.getId(), "UTF-8");
            }
            if (ol1Var != null) {
                arrayList.add(ol1Var);
            }
        }
        ol1[] ol1VarArr = new ol1[arrayList.size()];
        ml1Var.b = ol1VarArr;
        arrayList.toArray(ol1VarArr);
        return ml1Var;
    }

    public static NdefMessage e(ml1 ml1Var) throws m21 {
        try {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (true) {
                ol1[] ol1VarArr = ml1Var.b;
                if (i >= ol1VarArr.length) {
                    NdefRecord[] ndefRecordArr = new NdefRecord[arrayList.size()];
                    arrayList.toArray(ndefRecordArr);
                    return new NdefMessage(ndefRecordArr);
                }
                arrayList.add(f(ol1VarArr[i]));
                i++;
            }
        } catch (UnsupportedEncodingException | IllegalArgumentException | m21 unused) {
            throw new m21();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:110:0x0194, code lost:
    
        throw new defpackage.m21();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.nfc.NdefRecord f(defpackage.ol1 r14) throws defpackage.m21 {
        /*
            Method dump skipped, instruction units count: 792
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nl1.f(ol1):android.nfc.NdefRecord");
    }
}
