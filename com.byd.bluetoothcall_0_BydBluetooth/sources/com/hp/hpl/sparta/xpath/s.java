package com.hp.hpl.sparta.xpath;

import java.io.IOException;
import java.io.Reader;

/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4209e;
    private final Reader f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4205a = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4206b = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f4207c = "";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final StringBuffer f4208d = new StringBuffer();
    private final int[] g = new int[256];
    private boolean h = false;
    private char i = 0;

    public s(Reader reader) throws IOException {
        char c2 = 0;
        this.f = reader;
        while (true) {
            int[] iArr = this.g;
            if (c2 >= iArr.length) {
                a();
                return;
            }
            if (('A' <= c2 && c2 <= 'Z') || (('a' <= c2 && c2 <= 'z') || c2 == '-')) {
                iArr[c2] = -3;
            } else if ('0' <= c2 && c2 <= '9') {
                iArr[c2] = -2;
            } else if (c2 < 0 || c2 > ' ') {
                iArr[c2] = c2;
            } else {
                iArr[c2] = -5;
            }
            c2 = (char) (c2 + 1);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x00a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int a() throws java.io.IOException {
        /*
            r12 = this;
            boolean r0 = r12.h
            r1 = 0
            if (r0 == 0) goto La
            r12.h = r1
            int r0 = r12.f4205a
            return r0
        La:
            int r0 = r12.f4209e
            r12.f4205a = r0
        Le:
            r0 = r1
        Lf:
            java.io.Reader r2 = r12.f
            int r2 = r2.read()
            r3 = -1
            if (r2 != r3) goto L26
            char r4 = r12.i
            if (r4 != 0) goto L1e
            r4 = r3
            goto L2a
        L1e:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Unterminated quote"
            r0.<init>(r1)
            throw r0
        L26:
            int[] r4 = r12.g
            r4 = r4[r2]
        L2a:
            char r5 = r12.i
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
            r12.i = r5
            goto L51
        L4d:
            if (r5 != r4) goto L51
            r12.i = r1
        L51:
            char r5 = r12.i
            if (r5 == 0) goto L56
            r4 = r5
        L56:
            if (r0 != 0) goto L65
            int r0 = r12.f4205a
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
            int r10 = r12.f4205a
            if (r10 == r5) goto L94
            if (r10 == r3) goto L87
            if (r10 == r8) goto L75
            if (r10 == r9) goto L75
            goto La1
        L75:
            java.lang.StringBuffer r10 = r12.f4208d
            java.lang.String r10 = r10.toString()
            java.lang.StringBuffer r11 = r12.f4208d
            int r11 = r11.length()
            int r11 = r11 - r7
            java.lang.String r7 = r10.substring(r7, r11)
            goto L9a
        L87:
            java.lang.StringBuffer r7 = r12.f4208d
            java.lang.String r7 = r7.toString()
            int r7 = java.lang.Integer.parseInt(r7)
            r12.f4206b = r7
            goto L9c
        L94:
            java.lang.StringBuffer r7 = r12.f4208d
            java.lang.String r7 = r7.toString()
        L9a:
            r12.f4207c = r7
        L9c:
            java.lang.StringBuffer r7 = r12.f4208d
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
            r12.f4209e = r6
        Lab:
            if (r4 == r5) goto Lb4
            if (r4 == r3) goto Lb4
            if (r4 == r8) goto Lb4
            if (r4 == r9) goto Lb4
            goto Lba
        Lb4:
            java.lang.StringBuffer r3 = r12.f4208d
            char r2 = (char) r2
            r3.append(r2)
        Lba:
            if (r0 == 0) goto Le
            int r0 = r12.f4205a
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hp.hpl.sparta.xpath.s.a():int");
    }

    public void b(char c2) {
        this.g[c2] = c2;
    }

    public void c() {
        this.h = true;
    }

    public void d(char c2, char c3) {
        while (c2 <= c3) {
            this.g[c2] = -3;
            c2 = (char) (c2 + 1);
        }
    }

    public String toString() {
        int i = this.f4205a;
        if (i != -3) {
            if (i == -2) {
                return Integer.toString(this.f4206b);
            }
            if (i == -1) {
                return "(EOF)";
            }
            if (i != 34) {
                if (i != 39) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("'");
                    stringBuffer.append((char) this.f4205a);
                    stringBuffer.append("'");
                    return stringBuffer.toString();
                }
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("'");
                stringBuffer2.append(this.f4207c);
                stringBuffer2.append("'");
                return stringBuffer2.toString();
            }
        }
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("\"");
        stringBuffer3.append(this.f4207c);
        stringBuffer3.append("\"");
        return stringBuffer3.toString();
    }
}
