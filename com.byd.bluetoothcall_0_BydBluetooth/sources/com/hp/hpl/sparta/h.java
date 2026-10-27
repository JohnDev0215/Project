package com.hp.hpl.sparta;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes.dex */
class h implements l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private i f4177c;

    public h(String str, InputStream inputStream, k kVar, String str2, j jVar) throws ParseException, IOException {
        k kVar2 = kVar == null ? l.f4181a : kVar;
        if (!inputStream.markSupported()) {
            throw new Error("Precondition violation: the InputStream passed to ParseByteStream must support mark");
        }
        inputStream.mark(l.f4182b);
        byte[] bArr = new byte[4];
        String strD = str2 == null ? d(str, bArr, inputStream.read(bArr), kVar2) : str2;
        try {
            inputStream.reset();
            try {
                this.f4177c = new i(str, new InputStreamReader(inputStream, c(strD)), kVar2, strD, jVar);
            } catch (IOException unused) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Problem reading with assumed encoding of ");
                stringBuffer.append(strD);
                stringBuffer.append(" so restarting with ");
                stringBuffer.append("euc-jp");
                kVar2.a(stringBuffer.toString(), str, 1);
                inputStream.reset();
                try {
                    this.f4177c = new i(str, new InputStreamReader(inputStream, c("euc-jp")), kVar2, null, jVar);
                } catch (UnsupportedEncodingException unused2) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("\"");
                    stringBuffer2.append("euc-jp");
                    stringBuffer2.append("\" is not a supported encoding");
                    throw new ParseException(kVar2, str, 1, 0, "euc-jp", stringBuffer2.toString());
                }
            }
        } catch (EncodingMismatchException e2) {
            String declaredEncoding = e2.getDeclaredEncoding();
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Encoding declaration of ");
            stringBuffer3.append(declaredEncoding);
            stringBuffer3.append(" is different that assumed ");
            stringBuffer3.append(strD);
            stringBuffer3.append(" so restarting the parsing with the new encoding");
            kVar2.a(stringBuffer3.toString(), str, 1);
            inputStream.reset();
            try {
                this.f4177c = new i(str, new InputStreamReader(inputStream, c(declaredEncoding)), kVar2, null, jVar);
            } catch (UnsupportedEncodingException unused3) {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("\"");
                stringBuffer4.append(declaredEncoding);
                stringBuffer4.append("\" is not a supported encoding");
                throw new ParseException(kVar2, str, 1, 0, declaredEncoding, stringBuffer4.toString());
            }
        }
    }

    private static boolean a(byte[] bArr, int i) {
        return bArr[0] == ((byte) (i >>> 24)) && bArr[1] == ((byte) ((i >>> 16) & 255)) && bArr[2] == ((byte) ((i >>> 8) & 255)) && bArr[3] == ((byte) (i & 255));
    }

    private static boolean b(byte[] bArr, short s) {
        return bArr[0] == ((byte) (s >>> 8)) && bArr[1] == ((byte) (s & 255));
    }

    private static String c(String str) {
        return str.toLowerCase().equals("utf8") ? "UTF-8" : str;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String d(java.lang.String r5, byte[] r6, int r7, com.hp.hpl.sparta.k r8) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 259
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hp.hpl.sparta.h.d(java.lang.String, byte[], int, com.hp.hpl.sparta.k):java.lang.String");
    }

    private static String e(byte b2) {
        String hexString = Integer.toHexString(b2);
        int length = hexString.length();
        if (length != 1) {
            return length != 2 ? hexString.substring(hexString.length() - 2) : hexString;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("0");
        stringBuffer.append(hexString);
        return stringBuffer.toString();
    }

    @Override // com.hp.hpl.sparta.l
    public String toString() {
        return this.f4177c.toString();
    }
}
