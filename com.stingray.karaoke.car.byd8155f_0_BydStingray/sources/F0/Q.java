package F0;

import android.view.PointerIcon;
import android.view.View;
import y0.C2723a;
import y0.InterfaceC2735m;

/* JADX INFO: loaded from: classes.dex */
public final class Q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Q f2106a = new Q();

    public final void a(View view, InterfaceC2735m interfaceC2735m) {
        PointerIcon systemIcon = interfaceC2735m instanceof C2723a ? PointerIcon.getSystemIcon(view.getContext(), ((C2723a) interfaceC2735m).f24049b) : PointerIcon.getSystemIcon(view.getContext(), 1000);
        if (A7.m.a(view.getPointerIcon(), systemIcon)) {
            return;
        }
        view.setPointerIcon(systemIcon);
    }
}
