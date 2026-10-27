package defpackage;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import org.chromium.components.browser_ui.modaldialog.ModalDialogView;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class me0 implements View.OnTouchListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ me0(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        int i = this.e;
        boolean z = false;
        Object obj = this.f;
        switch (i) {
            case 0:
                pe0 pe0Var = (pe0) obj;
                pe0Var.getClass();
                if (motionEvent.getAction() == 1) {
                    long jCurrentTimeMillis = System.currentTimeMillis() - pe0Var.l;
                    if ((jCurrentTimeMillis < 0 || jCurrentTimeMillis > 300) != false) {
                        pe0Var.j = false;
                    }
                    pe0Var.u();
                    pe0Var.j = true;
                    pe0Var.l = System.currentTimeMillis();
                }
                return false;
            case 1:
                view.performClick();
                ((GestureDetector) obj).onTouchEvent(motionEvent);
                return true;
            case 2:
                ModalDialogView modalDialogView = (ModalDialogView) obj;
                int i2 = ModalDialogView.D;
                modalDialogView.getClass();
                try {
                    z = (MotionEvent.class.getField("FLAG_WINDOW_IS_PARTIALLY_OBSCURED").getInt(null) & motionEvent.getFlags()) != 0;
                    if (motionEvent.getAction() == 0 && !modalDialogView.x) {
                        modalDialogView.x = true;
                        oj2.U(z ? 1 : 0, 2, "Android.ModalDialog.SecurityFilteredTouchResult");
                    }
                    if (z && modalDialogView.y != null && motionEvent.getAction() == 0) {
                        modalDialogView.y.run();
                    }
                    break;
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    Log.e("cr_ModalDialogView", "Reflection failure: " + e);
                }
                return z;
            default:
                ((Runnable) obj).run();
                return false;
        }
    }
}
