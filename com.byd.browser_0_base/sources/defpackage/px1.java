package defpackage;

import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.base.b;
import org.chromium.chrome.browser.password_manager.settings.PasswordUIView;

/* JADX INFO: compiled from: PasswordManagerHandlerProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class px1 implements ox1 {
    public PasswordUIView e;
    public final b<ox1> f = new b<>();

    /* JADX INFO: compiled from: PasswordManagerHandlerProvider.java */
    public static final class a {
        public static final px1 a = new px1();
    }

    @Override // defpackage.ox1
    public final void x(int i) {
        Object obj = ThreadUtils.a;
        Iterator<ox1> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((ox1) aVar.next()).x(i);
            }
        }
    }

    @Override // defpackage.ox1
    public final void z(int i) {
        Object obj = ThreadUtils.a;
        Iterator<ox1> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((ox1) aVar.next()).z(i);
            }
        }
    }
}
