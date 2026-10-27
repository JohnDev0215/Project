package com.hp.hpl.sparta.xpath;

import java.io.IOException;
import java.io.Reader;

/* JADX INFO: loaded from: classes2.dex */
public class SimpleStreamTokenizer {
    private static final int QUOTE = -6;
    public static final int TT_EOF = -1;
    public static final int TT_NUMBER = -2;
    public static final int TT_WORD = -3;
    private static final int WHITESPACE = -5;
    private int nextType_;
    private final Reader reader_;
    public int ttype = Integer.MIN_VALUE;
    public int nval = Integer.MIN_VALUE;
    public String sval = "";
    private final StringBuffer buf_ = new StringBuffer();
    private final int[] charType_ = new int[256];
    private boolean pushedBack_ = false;
    private char inQuote_ = 0;

    public SimpleStreamTokenizer(Reader reader) throws IOException {
        char c = 0;
        this.reader_ = reader;
        while (true) {
            int[] iArr = this.charType_;
            if (c >= iArr.length) {
                nextToken();
                return;
            }
            if (('A' <= c && c <= 'Z') || (('a' <= c && c <= 'z') || c == '-')) {
                iArr[c] = -3;
            } else if ('0' <= c && c <= '9') {
                iArr[c] = -2;
            } else if (c < 0 || c > ' ') {
                iArr[c] = c;
            } else {
                iArr[c] = WHITESPACE;
            }
            c = (char) (c + 1);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x00a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int nextToken() throws java.io.IOException {
        /*
            r12 = this;
            boolean r0 = r12.pushedBack_
            r1 = 0
            if (r0 == 0) goto La
            r12.pushedBack_ = r1
            int r0 = r12.ttype
            return r0
        La:
            int r0 = r12.nextType_
            r12.ttype = r0
        Le:
            r0 = r1
        Lf:
            java.io.Reader r2 = r12.reader_
            int r2 = r2.read()
            r3 = -1
            if (r2 != r3) goto L26
            char r4 = r12.inQuote_
            if (r4 != 0) goto L1e
            r4 = r3
            goto L2a
        L1e:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Unterminated quote"
            r0.<init>(r1)
            throw r0
        L26:
            int[] r4 = r12.charType_
            r4 = r4[r2]
        L2a:
            char r5 = r12.inQuote_
            r6 = -5
            r7 = 1
            if (r5 != 0) goto L34
            if (r4 != r6) goto L34
            r8 = r7
            goto L35
        L34:
            r8 = r1
        L35:
            if (r0 != 0) goto L3c
            if (r8 == 0) goto L3a
            goto L3c
        L3a:
            r0 = r1
            goto L3d
        L3c:
            r0 = r7
        L3d:
            if (r8 != 0) goto Lf
            r8 = 34
            r9 = 39
            if (r4 == r9) goto L47
            if (r4 != r8) goto L51
        L47:
            if (r5 != 0) goto L4d
            char r5 = (char) r4
            r12.inQuote_ = r5
            goto L51
        L4d:
            if (r5 != r4) goto L51
            r12.inQuote_ = r1
        L51:
            char r5 = r12.inQuote_
            if (r5 == 0) goto L56
            r4 = r5
        L56:
            if (r0 != 0) goto L65
            int r0 = r12.ttype
            if (r0 < r3) goto L60
            if (r0 == r9) goto L60
            if (r0 != r8) goto L65
        L60:
            if (r0 == r4) goto L63
            goto L65
        L63:
            r0 = r1
            goto L66
        L65:
            r0 = r7
        L66:
            r3 = -2
            r5 = -3
            if (r0 == 0) goto Lab
            int r10 = r12.ttype
            if (r10 == r5) goto L94
            if (r10 == r3) goto L87
            if (r10 == r8) goto L75
            if (r10 == r9) goto L75
            goto La1
        L75:
            java.lang.StringBuffer r10 = r12.buf_
            java.lang.String r10 = r10.toString()
            java.lang.StringBuffer r11 = r12.buf_
            int r11 = r11.length()
            int r11 = r11 - r7
            java.lang.String r7 = r10.substring(r7, r11)
            goto L9a
        L87:
            java.lang.StringBuffer r7 = r12.buf_
            java.lang.String r7 = r7.toString()
            int r7 = java.lang.Integer.parseInt(r7)
            r12.nval = r7
            goto L9c
        L94:
            java.lang.StringBuffer r7 = r12.buf_
            java.lang.String r7 = r7.toString()
        L9a:
            r12.sval = r7
        L9c:
            java.lang.StringBuffer r7 = r12.buf_
            r7.setLength(r1)
        La1:
            if (r4 == r6) goto Lab
            r6 = -6
            if (r4 != r6) goto La8
            r6 = r2
            goto La9
        La8:
            r6 = r4
        La9:
            r12.nextType_ = r6
        Lab:
            if (r4 == r5) goto Lb4
            if (r4 == r3) goto Lb4
            if (r4 == r8) goto Lb4
            if (r4 == r9) goto Lb4
            goto Lba
        Lb4:
            java.lang.StringBuffer r3 = r12.buf_
            char r2 = (char) r2
            r3.append(r2)
        Lba:
            if (r0 == 0) goto Le
            int r0 = r12.ttype
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hp.hpl.sparta.xpath.SimpleStreamTokenizer.nextToken():int");
    }

    public void ordinaryChar(char c) {
        this.charType_[c] = c;
    }

    public void pushBack() {
        this.pushedBack_ = true;
    }

    public String toString() {
        int i = this.ttype;
        if (i != -3) {
            if (i == -2) {
                return Integer.toString(this.nval);
            }
            if (i == -1) {
                return "(EOF)";
            }
            if (i != 34) {
                return i != 39 ? new StringBuffer().append("'").append((char) this.ttype).append("'").toString() : new StringBuffer().append("'").append(this.sval).append("'").toString();
            }
        }
        return new StringBuffer().append("\"").append(this.sval).append("\"").toString();
    }

    public void wordChars(char c, char c2) {
        while (c <= c2) {
            this.charType_[c] = -3;
            c = (char) (c + 1);
        }
    }
}
