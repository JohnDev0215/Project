package d1;

import B8.A;
import android.view.MotionEvent;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.AndroidComposeView;
import m7.q;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: d1.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1059c extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13282m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ o f13283n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1059c(o oVar, int i7) {
        super(1);
        this.f13282m = i7;
        this.f13283n = oVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        boolean zDispatchTouchEvent;
        switch (this.f13282m) {
            case 0:
                Owner owner = (Owner) obj;
                AndroidComposeView androidComposeView = owner instanceof AndroidComposeView ? (AndroidComposeView) owner : null;
                o oVar = this.f13283n;
                if (androidComposeView != null) {
                    A a10 = new A(androidComposeView, 7, oVar);
                    V.d dVar = androidComposeView.B0;
                    if (!dVar.i(a10)) {
                        dVar.b(a10);
                    }
                }
                oVar.removeAllViewsInLayout();
                return q.f19267a;
            default:
                MotionEvent motionEvent = (MotionEvent) obj;
                int actionMasked = motionEvent.getActionMasked();
                o oVar2 = this.f13283n;
                switch (actionMasked) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        zDispatchTouchEvent = oVar2.dispatchTouchEvent(motionEvent);
                        break;
                    default:
                        zDispatchTouchEvent = oVar2.dispatchGenericMotionEvent(motionEvent);
                        break;
                }
                return Boolean.valueOf(zDispatchTouchEvent);
        }
    }
}
