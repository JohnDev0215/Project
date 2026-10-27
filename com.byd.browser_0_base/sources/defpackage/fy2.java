package defpackage;

import org.chromium.base.b;

/* JADX INFO: compiled from: SystemNightModeMonitor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fy2 {
    public static fy2 c;
    public final b<a> a = new b<>();
    public boolean b;

    /* JADX INFO: compiled from: SystemNightModeMonitor.java */
    public interface a {
        void a();
    }

    public fy2() {
        this.b = (xz.a.getResources().getConfiguration().uiMode & 48) == 32;
    }

    public static fy2 a() {
        if (c == null) {
            c = new fy2();
        }
        return c;
    }
}
