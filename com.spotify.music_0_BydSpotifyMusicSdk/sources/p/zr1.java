package p;

import okhttp3.MultipartBody;

/* JADX INFO: loaded from: classes.dex */
public final class zr1 extends ux2 {
    public static final zr1 g = new zr1();

    @Override // p.ux2
    public final void c(n32 n32Var, Object obj) {
        MultipartBody.Part part = (MultipartBody.Part) obj;
        if (part != null) {
            MultipartBody.Builder builder = n32Var.i;
            builder.getClass();
            builder.c.add(part);
        }
    }
}
