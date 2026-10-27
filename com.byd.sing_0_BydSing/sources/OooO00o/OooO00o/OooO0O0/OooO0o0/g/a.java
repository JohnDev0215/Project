package OooO00o.OooO00o.OooO0O0.OooO0o0.g;

import com.loostone.libbottom.util.JniUtil;
import com.loostone.libtuning.component.provider.TuningProvider;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f38a = new a();

    public final String a(String str, String str2) {
        h.d(str, TuningProvider.VALUE);
        h.d(str2, "seed");
        return JniUtil.f3799OooO00o.decryption(str, Integer.parseInt(str2));
    }

    public final String b(String str, String str2) {
        h.d(str, TuningProvider.VALUE);
        h.d(str2, "seed");
        return JniUtil.f3799OooO00o.encryption(str, Integer.parseInt(str2));
    }
}
