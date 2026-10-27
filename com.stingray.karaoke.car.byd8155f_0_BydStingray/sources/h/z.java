package h;

import android.view.View;
import com.stingray.drivemodedetector.R;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class z extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final z f15149n = new z(1, 0);
    public static final z o = new z(1, 1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f15150m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ z(int i7, int i10) {
        super(i7);
        this.f15150m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f15150m) {
            case 0:
                View view = (View) obj;
                A7.m.f(view, "it");
                Object parent = view.getParent();
                if (parent instanceof View) {
                    return (View) parent;
                }
                return null;
            default:
                View view2 = (View) obj;
                A7.m.f(view2, "it");
                Object tag = view2.getTag(R.id.view_tree_on_back_pressed_dispatcher_owner);
                if (tag instanceof y) {
                    return (y) tag;
                }
                return null;
        }
    }
}
