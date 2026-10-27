package N7;

import o8.C2057b;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public enum q {
    UBYTEARRAY(C2057b.e("kotlin/UByteArray", false)),
    USHORTARRAY(C2057b.e("kotlin/UShortArray", false)),
    UINTARRAY(C2057b.e("kotlin/UIntArray", false)),
    ULONGARRAY(C2057b.e("kotlin/ULongArray", false));

    public final C2061f l;

    q(C2057b c2057b) {
        C2061f c2061fI = c2057b.i();
        A7.m.e(c2061fI, "classId.shortClassName");
        this.l = c2061fI;
    }
}
