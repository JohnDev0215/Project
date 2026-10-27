package OooO00o.OooO00o.OooO0O0.OooO0o0.f;

import com.loostone.libbottom.util.JniUtil;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f37a = new a();

    public final int a() {
        return JniUtil.f3799OooO00o.recordRead2Cache();
    }

    public final int b(int i, int i2, int i3) {
        return JniUtil.f3799OooO00o.recordCreate(i, i2, i3);
    }

    public final int c(byte[] bArr, int i, int i2) {
        h.d(bArr, "buffer");
        return JniUtil.f3799OooO00o.recordRead(bArr, i, i2);
    }

    public final int d() {
        return JniUtil.f3799OooO00o.recordRelease();
    }
}
