package p;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public final class ge1 {
    public static final ee1 b = new ee1();
    public final uj1 a;

    public ge1() {
        uj1 uj1Var;
        uj1[] uj1VarArr = new uj1[2];
        uj1VarArr[0] = g01.a;
        try {
            uj1Var = (uj1) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            uj1Var = b;
        }
        uj1VarArr[1] = uj1Var;
        fe1 fe1Var = new fe1();
        fe1Var.a = uj1VarArr;
        Charset charset = w51.a;
        this.a = fe1Var;
    }
}
