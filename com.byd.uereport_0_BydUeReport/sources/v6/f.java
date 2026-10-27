package v6;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;

/* JADX INFO: compiled from: QRCode.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Mode f8596a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ErrorCorrectionLevel f8597b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public u6.a f8598c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f8599d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b f8600e;

    public static boolean b(int i8) {
        return i8 >= 0 && i8 < 8;
    }

    public b a() {
        return this.f8600e;
    }

    public void c(ErrorCorrectionLevel errorCorrectionLevel) {
        this.f8597b = errorCorrectionLevel;
    }

    public void d(int i8) {
        this.f8599d = i8;
    }

    public void e(b bVar) {
        this.f8600e = bVar;
    }

    public void f(Mode mode) {
        this.f8596a = mode;
    }

    public void g(u6.a aVar) {
        this.f8598c = aVar;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        sb.append("<<\n");
        sb.append(" mode: ");
        sb.append(this.f8596a);
        sb.append("\n ecLevel: ");
        sb.append(this.f8597b);
        sb.append("\n version: ");
        sb.append(this.f8598c);
        sb.append("\n maskPattern: ");
        sb.append(this.f8599d);
        if (this.f8600e == null) {
            sb.append("\n matrix: null\n");
        } else {
            sb.append("\n matrix:\n");
            sb.append(this.f8600e);
        }
        sb.append(">>\n");
        return sb.toString();
    }
}
