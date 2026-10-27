package g.a.f.k.c;

import g.a.f.i;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: classes.dex */
public class d extends BufferedWriter {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f5745c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public char[] f5746d;

    public d(Writer writer) {
        super(writer);
        this.f5746d = new char[64];
        String strD = i.d();
        this.f5745c = strD != null ? strD.length() : 2;
    }

    public final void c(byte[] bArr) throws IOException {
        char[] cArr;
        int i2;
        byte[] bArrB = g.a.f.j.a.b(bArr);
        int length = 0;
        while (length < bArrB.length) {
            int i3 = 0;
            while (true) {
                cArr = this.f5746d;
                if (i3 == cArr.length || (i2 = length + i3) >= bArrB.length) {
                    break;
                }
                cArr[i3] = (char) bArrB[i2];
                i3++;
            }
            write(cArr, 0, i3);
            newLine();
            length += this.f5746d.length;
        }
    }

    public void d(c cVar) throws IOException {
        b bVarA = cVar.a();
        v(bVarA.d());
        if (!bVarA.c().isEmpty()) {
            for (a aVar : bVarA.c()) {
                write(aVar.b());
                write(": ");
                write(aVar.c());
                newLine();
            }
            newLine();
        }
        c(bVarA.b());
        s(bVarA.d());
    }

    public final void s(String str) throws IOException {
        write("-----END " + str + "-----");
        newLine();
    }

    public final void v(String str) throws IOException {
        write("-----BEGIN " + str + "-----");
        newLine();
    }
}
