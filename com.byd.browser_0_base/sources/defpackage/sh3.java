package defpackage;

import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: UiThreadTaskTraitsImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class sh3 {
    public static final d73 a;
    public static final d73 b;
    public static final d73 c;
    public static final d73 d;

    /* JADX INFO: compiled from: UiThreadTaskTraitsImpl.java */
    public static class a {
    }

    static {
        new a();
        d73 d73Var = d73.h;
        new sh3();
        d73Var.getClass();
        byte[] bArr = new byte[8];
        bArr[2] = 1;
        d73 d73Var2 = new d73(d73Var);
        d73Var2.d = (byte) 1;
        d73Var2.e = bArr;
        a = d73Var2;
        b = d73Var2.a(0);
        c = d73Var2.a(1);
        d = d73Var2.a(2);
        if (wj.b) {
            return;
        }
        wj.b = true;
        PostTask.e.set(1, new wj());
    }
}
