package p;

import com.google.common.base.Optional;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class lb2 {
    public static final Charset e = Charset.forName("UTF-8");
    public final ConcurrentHashMap a = new ConcurrentHashMap();
    public final g90 b;
    public final v4 c;
    public final kk0 d;

    public lb2(g90 g90Var, v4 v4Var, kk0 kk0Var) {
        this.b = g90Var;
        this.c = v4Var;
        this.d = kk0Var;
    }

    public final byte[] a(Optional optional, String str, String str2, String str3) {
        Optional optionalE = optional.d() ? Optional.e(si0.a((String) optional.c())) : optional;
        int i = com.google.common.hash.d.a;
        zu zuVarA = com.google.common.hash.b.a.a();
        zuVarA.getClass();
        String string = str.toString();
        Charset charset = e;
        zu zuVarX0 = zuVarA.x0(string.getBytes(charset));
        zuVarX0.getClass();
        zu zuVarX02 = zuVarX0.x0(str2.toString().getBytes(charset));
        zuVarX02.getClass();
        zu zuVarX03 = zuVarX02.x0(str3.toString().getBytes(charset));
        CharSequence charSequence = (CharSequence) optionalE.g("");
        zuVarX03.getClass();
        byte[] bArrA = zuVarX03.x0(charSequence.toString().getBytes(charset)).l0().a();
        ConcurrentHashMap concurrentHashMap = this.a;
        if (!Arrays.equals(bArrA, (byte[]) concurrentHashMap.get(optional.g("")))) {
            concurrentHashMap.put((String) optional.g(""), bArrA);
        }
        return bArrA;
    }
}
