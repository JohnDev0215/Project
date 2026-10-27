package defpackage;

import com.google.protobuf.r0;

/* JADX INFO: compiled from: RawMessageInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class a92 implements zg1 {
    public final r0 a;
    public final String b;
    public final Object[] c;
    public final int d;

    public a92(r0 r0Var, String str, Object[] objArr) {
        this.a = r0Var;
        this.b = str;
        this.c = objArr;
        char cCharAt = str.charAt(0);
        if (cCharAt < 55296) {
            this.d = cCharAt;
            return;
        }
        int i = cCharAt & 8191;
        int i2 = 13;
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char cCharAt2 = str.charAt(i3);
            if (cCharAt2 < 55296) {
                this.d = i | (cCharAt2 << i2);
                return;
            } else {
                i |= (cCharAt2 & 8191) << i2;
                i2 += 13;
                i3 = i4;
            }
        }
    }

    @Override // defpackage.zg1
    public final boolean a() {
        return (this.d & 2) == 2;
    }

    @Override // defpackage.zg1
    public final r0 b() {
        return this.a;
    }

    @Override // defpackage.zg1
    public final int c() {
        return (this.d & 1) == 1 ? 1 : 2;
    }

    public final Object[] d() {
        return this.c;
    }

    public final String e() {
        return this.b;
    }
}
