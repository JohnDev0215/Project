package p;

import com.google.protobuf.ProtoSyntax;

/* JADX INFO: loaded from: classes.dex */
public final class o02 implements tj1 {
    public final wj1 a;
    public final String b;
    public final Object[] c;
    public final int d;

    public o02(wj1 wj1Var, String str, Object[] objArr) {
        this.a = wj1Var;
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

    public final wj1 a() {
        return this.a;
    }

    public final Object[] b() {
        return this.c;
    }

    public final String c() {
        return this.b;
    }

    public final ProtoSyntax d() {
        return (this.d & 1) == 1 ? ProtoSyntax.c : ProtoSyntax.e;
    }
}
