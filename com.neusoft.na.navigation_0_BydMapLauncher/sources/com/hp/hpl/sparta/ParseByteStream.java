package com.hp.hpl.sparta;

import com.neusoft.na.common.FileUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes2.dex */
class ParseByteStream implements ParseSource {
    private ParseCharStream parseSource_;

    public ParseByteStream(String str, InputStream inputStream, ParseLog parseLog, String str2, ParseHandler parseHandler) throws ParseException, IOException {
        parseLog = parseLog == null ? ParseSource.DEFAULT_LOG : parseLog;
        if (!inputStream.markSupported()) {
            throw new Error("Precondition violation: the InputStream passed to ParseByteStream must support mark");
        }
        inputStream.mark(ParseSource.MAXLOOKAHEAD);
        byte[] bArr = new byte[4];
        str2 = str2 == null ? guessEncoding(str, bArr, inputStream.read(bArr), parseLog) : str2;
        try {
            inputStream.reset();
            try {
                this.parseSource_ = new ParseCharStream(str, new InputStreamReader(inputStream, fixEncoding(str2)), parseLog, str2, parseHandler);
            } catch (IOException unused) {
                parseLog.note(new StringBuffer("Problem reading with assumed encoding of ").append(str2).append(" so restarting with euc-jp").toString(), str, 1);
                inputStream.reset();
                try {
                    this.parseSource_ = new ParseCharStream(str, new InputStreamReader(inputStream, fixEncoding("euc-jp")), parseLog, (String) null, parseHandler);
                } catch (UnsupportedEncodingException unused2) {
                    throw new ParseException(parseLog, str, 1, 0, "euc-jp", "\"euc-jp\" is not a supported encoding");
                }
            }
        } catch (EncodingMismatchException e) {
            String declaredEncoding = e.getDeclaredEncoding();
            parseLog.note(new StringBuffer("Encoding declaration of ").append(declaredEncoding).append(" is different that assumed ").append(str2).append(" so restarting the parsing with the new encoding").toString(), str, 1);
            inputStream.reset();
            try {
                this.parseSource_ = new ParseCharStream(str, new InputStreamReader(inputStream, fixEncoding(declaredEncoding)), parseLog, (String) null, parseHandler);
            } catch (UnsupportedEncodingException unused3) {
                throw new ParseException(parseLog, str, 1, 0, declaredEncoding, new StringBuffer("\"").append(declaredEncoding).append("\" is not a supported encoding").toString());
            }
        }
    }

    private static boolean equals(byte[] bArr, int i) {
        return bArr[0] == ((byte) (i >>> 24)) && bArr[1] == ((byte) ((i >>> 16) & 255)) && bArr[2] == ((byte) ((i >>> 8) & 255)) && bArr[3] == ((byte) (i & 255));
    }

    private static boolean equals(byte[] bArr, short s) {
        return bArr[0] == ((byte) (s >>> 8)) && bArr[1] == ((byte) (s & 255));
    }

    private static String fixEncoding(String str) {
        return str.toLowerCase().equals("utf8") ? "UTF-8" : str;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String guessEncoding(java.lang.String r5, byte[] r6, int r7, com.hp.hpl.sparta.ParseLog r8) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hp.hpl.sparta.ParseByteStream.guessEncoding(java.lang.String, byte[], int, com.hp.hpl.sparta.ParseLog):java.lang.String");
    }

    private static String hex(byte b) {
        String hexString = Integer.toHexString(b);
        int length = hexString.length();
        return length != 1 ? length != 2 ? hexString.substring(hexString.length() - 2) : hexString : new StringBuffer(FileUtils.DEFAULT_VERSION).append(hexString).toString();
    }

    @Override // com.hp.hpl.sparta.ParseSource
    public int getLineNumber() {
        return this.parseSource_.getLineNumber();
    }

    @Override // com.hp.hpl.sparta.ParseSource
    public String getSystemId() {
        return this.parseSource_.getSystemId();
    }

    @Override // com.hp.hpl.sparta.ParseSource
    public String toString() {
        return this.parseSource_.toString();
    }
}
