package defpackage;

import java.util.ArrayList;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: SettingsAutofillAndPaymentsObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public final class nj2 {
    public static final ArrayList a = new ArrayList();
    public static nj2 b;

    /* JADX INFO: compiled from: SettingsAutofillAndPaymentsObserver.java */
    public interface a {
        void a();

        void b(qa qaVar);
    }

    public static nj2 a() {
        Object obj = ThreadUtils.a;
        if (b == null) {
            b = new nj2();
        }
        return b;
    }
}
