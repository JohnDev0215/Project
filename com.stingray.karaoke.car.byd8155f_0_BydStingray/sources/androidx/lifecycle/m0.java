package androidx.lifecycle;

import android.view.View;
import com.stingray.drivemodedetector.R;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class m0 extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final m0 f12000n = new m0(1, 0);
    public static final m0 o = new m0(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final m0 f12001p = new m0(1, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final m0 f12002q = new m0(1, 3);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f12003m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m0(int i7, int i10) {
        super(i7);
        this.f12003m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f12003m) {
            case 0:
                View view = (View) obj;
                A7.m.f(view, "currentView");
                Object parent = view.getParent();
                if (parent instanceof View) {
                    return (View) parent;
                }
                return null;
            case 1:
                View view2 = (View) obj;
                A7.m.f(view2, "viewParent");
                Object tag = view2.getTag(R.id.view_tree_lifecycle_owner);
                if (tag instanceof A) {
                    return (A) tag;
                }
                return null;
            case 2:
                View view3 = (View) obj;
                A7.m.f(view3, "view");
                Object parent2 = view3.getParent();
                if (parent2 instanceof View) {
                    return (View) parent2;
                }
                return null;
            default:
                View view4 = (View) obj;
                A7.m.f(view4, "view");
                Object tag2 = view4.getTag(R.id.view_tree_view_model_store_owner);
                if (tag2 instanceof l0) {
                    return (l0) tag2;
                }
                return null;
        }
    }
}
