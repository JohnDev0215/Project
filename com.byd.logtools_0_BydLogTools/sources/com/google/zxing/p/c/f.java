package com.google.zxing.p.c;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.g;

/* JADX INFO: compiled from: QRCode.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Mode f4435a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ErrorCorrectionLevel f4436b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private g f4437c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f4438d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private b f4439e;

    public static boolean b(int i) {
        return i >= 0 && i < 8;
    }

    public b a() {
        return this.f4439e;
    }

    public void c(ErrorCorrectionLevel errorCorrectionLevel) {
        this.f4436b = errorCorrectionLevel;
    }

    public void d(int i) {
        this.f4438d = i;
    }

    public void e(b bVar) {
        this.f4439e = bVar;
    }

    public void f(Mode mode) {
        this.f4435a = mode;
    }

    public void g(g gVar) {
        this.f4437c = gVar;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        sb.append("<<\n");
        sb.append(" mode: ");
        sb.append(this.f4435a);
        sb.append("\n ecLevel: ");
        sb.append(this.f4436b);
        sb.append("\n version: ");
        sb.append(this.f4437c);
        sb.append("\n maskPattern: ");
        sb.append(this.f4438d);
        if (this.f4439e == null) {
            sb.append("\n matrix: null\n");
        } else {
            sb.append("\n matrix:\n");
            sb.append(this.f4439e);
        }
        sb.append(">>\n");
        return sb.toString();
    }
}
