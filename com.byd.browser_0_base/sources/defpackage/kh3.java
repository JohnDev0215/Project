package defpackage;

import androidx.emoji2.text.h;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: TypefaceEmojiRasterizer.java */
/* JADX INFO: loaded from: classes.dex */
public final class kh3 {
    public static final ThreadLocal<mh1> d = new ThreadLocal<>();
    public final int a;
    public final h b;
    public volatile int c = 0;

    public kh3(h hVar, int i) {
        this.b = hVar;
        this.a = i;
    }

    public final int a(int i) {
        mh1 mh1VarC = c();
        int iA = mh1VarC.a(16);
        if (iA == 0) {
            return 0;
        }
        ByteBuffer byteBuffer = mh1VarC.b;
        int i2 = iA + mh1VarC.a;
        return byteBuffer.getInt((i * 4) + byteBuffer.getInt(i2) + i2 + 4);
    }

    public final int b() {
        mh1 mh1VarC = c();
        int iA = mh1VarC.a(16);
        if (iA == 0) {
            return 0;
        }
        int i = iA + mh1VarC.a;
        return mh1VarC.b.getInt(mh1VarC.b.getInt(i) + i);
    }

    public final mh1 c() {
        ThreadLocal<mh1> threadLocal = d;
        mh1 mh1Var = threadLocal.get();
        if (mh1Var == null) {
            mh1Var = new mh1();
            threadLocal.set(mh1Var);
        }
        nh1 nh1Var = this.b.a;
        int iA = nh1Var.a(6);
        if (iA != 0) {
            int i = iA + nh1Var.a;
            int i2 = (this.a * 4) + nh1Var.b.getInt(i) + i + 4;
            mh1Var.b(nh1Var.b.getInt(i2) + i2, nh1Var.b);
        }
        return mh1Var;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", id:");
        mh1 mh1VarC = c();
        int iA = mh1VarC.a(4);
        sb.append(Integer.toHexString(iA != 0 ? mh1VarC.b.getInt(iA + mh1VarC.a) : 0));
        sb.append(", codepoints:");
        int iB = b();
        for (int i = 0; i < iB; i++) {
            sb.append(Integer.toHexString(a(i)));
            sb.append(" ");
        }
        return sb.toString();
    }
}
