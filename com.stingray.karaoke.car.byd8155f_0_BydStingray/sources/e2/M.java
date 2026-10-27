package e2;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class M extends IOException {
    public final boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f13984m;

    public M(String str, RuntimeException runtimeException, boolean z6, int i7) {
        super(str, runtimeException);
        this.l = z6;
        this.f13984m = i7;
    }

    public static M a(RuntimeException runtimeException, String str) {
        return new M(str, runtimeException, true, 1);
    }

    public static M b(String str) {
        return new M(str, null, false, 1);
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.getMessage());
        sb.append(" {contentIsMalformed=");
        sb.append(this.l);
        sb.append(", dataType=");
        return M5.b.h(sb, this.f13984m, "}");
    }
}
