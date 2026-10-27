package w5;

import h3.y;
import t5.C2438c;
import v5.EnumC2551a;

/* JADX INFO: renamed from: w5.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2603e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final EnumC2551a f23335a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f23336b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ y f23337e;

    public C2603e(y yVar, EnumC2551a enumC2551a, int i7, int i10, int i11) {
        this.f23337e = yVar;
        this.f23335a = enumC2551a;
        this.f23336b = i7;
        this.c = i10;
        this.d = i11;
    }

    public final int a() {
        EnumC2551a enumC2551a = EnumC2551a.BYTE;
        EnumC2551a enumC2551a2 = this.f23335a;
        int i7 = this.d;
        if (enumC2551a2 != enumC2551a) {
            return i7;
        }
        X1.a aVar = (X1.a) this.f23337e.o;
        C2438c c2438c = (C2438c) aVar.f9972e;
        int i10 = this.f23336b;
        return ((String) aVar.d).substring(i10, i7 + i10).getBytes(c2438c.f22469a[this.c].charset()).length;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        EnumC2551a enumC2551a = this.f23335a;
        sb.append(enumC2551a);
        sb.append('(');
        EnumC2551a enumC2551a2 = EnumC2551a.ECI;
        y yVar = this.f23337e;
        if (enumC2551a == enumC2551a2) {
            sb.append(((C2438c) ((X1.a) yVar.o).f9972e).f22469a[this.c].charset().displayName());
        } else {
            String str = (String) ((X1.a) yVar.o).d;
            int i7 = this.f23336b;
            String strSubstring = str.substring(i7, this.d + i7);
            StringBuilder sb2 = new StringBuilder();
            for (int i10 = 0; i10 < strSubstring.length(); i10++) {
                if (strSubstring.charAt(i10) < ' ' || strSubstring.charAt(i10) > '~') {
                    sb2.append('.');
                } else {
                    sb2.append(strSubstring.charAt(i10));
                }
            }
            sb.append(sb2.toString());
        }
        sb.append(')');
        return sb.toString();
    }
}
