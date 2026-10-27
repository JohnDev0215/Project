package F1;

import A7.C0054b;
import android.view.View;
import android.view.ViewGroup;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class I extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final I f2305m = new I(1);

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        View view = (View) obj;
        ViewGroup viewGroup = view instanceof ViewGroup ? (ViewGroup) view : null;
        if (viewGroup != null) {
            return new C0054b(viewGroup, 1);
        }
        return null;
    }
}
