package spi;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.android.car.apps.common.ControlBar;
import com.android.car.ui.rotary.FocusParkingView;
import com.android.car.ui.rotary.a;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class a3 implements ViewTreeObserver.OnGlobalFocusChangeListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ a3(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
    public final void onGlobalFocusChanged(View view, View view2) {
        switch (this.a) {
            case 0:
            case 1:
            case 2:
            case 3:
                ControlBar.d((ControlBar) this.b);
                break;
            default:
                y3 y3Var = (y3) this.b;
                boolean zHasFocus = y3Var.a.hasFocus();
                if (zHasFocus) {
                    View focusedChild = y3Var.a.getFocusedChild();
                    while (focusedChild != null && !focusedChild.isFocused()) {
                        focusedChild = focusedChild instanceof ViewGroup ? ((ViewGroup) focusedChild).getFocusedChild() : null;
                    }
                    y3Var.A = focusedChild;
                } else if (y3Var.b) {
                    com.android.car.ui.rotary.a aVar = y3Var.x;
                    View view3 = y3Var.A;
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    a.c cVar = aVar.a;
                    if (cVar.a != 1) {
                        cVar.c = view3 != null ? new a.d(view3, jUptimeMillis) : null;
                    }
                    y3Var.A = null;
                }
                if (y3Var.b || !zHasFocus || view == null || (view instanceof FocusParkingView)) {
                    y3Var.z = null;
                } else {
                    o4 o4VarH = yc.h(view);
                    y3Var.z = o4VarH;
                    if (o4VarH == null) {
                        Log.w("FocusAreaHelper", "No ancestor focus area for " + view);
                    }
                }
                if (y3Var.y && zHasFocus && view != null && yc.h(view) == y3Var.a) {
                    y3Var.x.b.clear();
                }
                if ((y3Var.d || y3Var.c) && y3Var.b != zHasFocus) {
                    y3Var.a.invalidate();
                }
                y3Var.b = zHasFocus;
                break;
        }
    }
}
