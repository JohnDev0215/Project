package N7;

import o8.C2057b;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public enum r {
    /* JADX INFO: Fake field, exist only in values array */
    UBYTE(C2057b.e("kotlin/UByte", false)),
    /* JADX INFO: Fake field, exist only in values array */
    USHORT(C2057b.e("kotlin/UShort", false)),
    /* JADX INFO: Fake field, exist only in values array */
    UINT(C2057b.e("kotlin/UInt", false)),
    /* JADX INFO: Fake field, exist only in values array */
    ULONG(C2057b.e("kotlin/ULong", false));

    public final C2057b l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C2061f f4884m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C2057b f4885n;

    r(C2057b c2057b) {
        this.l = c2057b;
        C2061f c2061fI = c2057b.i();
        A7.m.e(c2061fI, "classId.shortClassName");
        this.f4884m = c2061fI;
        this.f4885n = new C2057b(c2057b.g(), C2061f.e(c2061fI.b() + "Array"));
    }
}
