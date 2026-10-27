package spi;

import android.view.View;
import java.util.function.Predicate;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class tc implements Predicate {
    public final /* synthetic */ int a;
    public final /* synthetic */ View b;

    public /* synthetic */ tc(View view, int i) {
        this.a = i;
        this.b = view;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        switch (this.a) {
            case 0:
                View view = (View) obj;
                if (view == this.b || yc.m(view) || !yc.b(view) || !yc.o(view)) {
                }
                break;
            case 1:
                View view2 = (View) obj;
                if (view2 == this.b || !view2.isFocusedByDefault() || !yc.b(view2)) {
                }
                break;
            case 2:
                View view3 = (View) obj;
                if (view3 == this.b || !view3.isSelected() || !yc.b(view3)) {
                }
                break;
            default:
                View view4 = (View) obj;
                if (view4 == this.b || !yc.b(view4)) {
                }
                break;
        }
        return false;
    }
}
