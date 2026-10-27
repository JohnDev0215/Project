package t3;

import A7.m;
import A7.o;
import android.view.View;
import com.stingray.drivemodedetector.R;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class f extends o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final f f22457n = new f(1, 0);
    public static final f o = new f(1, 1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f22458m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f(int i7, int i10) {
        super(i7);
        this.f22458m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f22458m) {
            case 0:
                View view = (View) obj;
                m.f(view, "view");
                Object parent = view.getParent();
                if (parent instanceof View) {
                    return (View) parent;
                }
                return null;
            default:
                View view2 = (View) obj;
                m.f(view2, "view");
                Object tag = view2.getTag(R.id.view_tree_saved_state_registry_owner);
                if (tag instanceof e) {
                    return (e) tag;
                }
                return null;
        }
    }
}
