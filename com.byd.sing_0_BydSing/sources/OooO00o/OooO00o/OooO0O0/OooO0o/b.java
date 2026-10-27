package OooO00o.OooO00o.OooO0o0.OooO0o;

import com.blankj.utilcode.util.f;
import com.blankj.utilcode.util.g;
import com.loostone.libbottom.util.security.CoreConfig;
import com.loostone.libtuning.component.provider.TuningProvider;
import kotlin.jvm.internal.h;
import kotlin.text.o;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f494a = new b();

    public final String a(String str) {
        return b(str, CoreConfig.INSTANCE.getPRIVATE_KEY());
    }

    public final String b(String str, String str2) {
        try {
            byte[] bArrB = g.b(f.a(str), f.a(str2), 512, "RSA/ECB/PKCS1Padding");
            h.c(bArrB, "decryptByteArray");
            return o.c(bArrB);
        } catch (Exception unused) {
            return null;
        }
    }

    public final String c(String str, String str2) {
        h.d(str, "code");
        h.d(str2, TuningProvider.KEY);
        try {
            byte[] bArrA = f.a(str);
            String strA = a(str2);
            byte[] bArrA2 = g.a(bArrA, strA == null ? null : o.d(strA), "AES/ECB/PKCS5Padding", null);
            h.c(bArrA2, "decryptByteArray");
            return o.c(bArrA2);
        } catch (Exception unused) {
            return null;
        }
    }
}
