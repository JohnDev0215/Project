package b.b.g.q.d;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.core.view.GravityCompat;

/* JADX INFO: compiled from: PopupWindowUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class p {

    /* JADX INFO: compiled from: PopupWindowUtil.java */
    public static class a implements PopupWindow.OnDismissListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f1496a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Activity f1497b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ PopupWindow.OnDismissListener f1498c;

        public a(boolean z, Activity activity, PopupWindow.OnDismissListener onDismissListener) {
            this.f1496a = z;
            this.f1497b = activity;
            this.f1498c = onDismissListener;
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            if (!this.f1496a) {
                p.a(this.f1497b.getWindow(), 1.0f);
            }
            PopupWindow.OnDismissListener onDismissListener = this.f1498c;
            if (onDismissListener != null) {
                onDismissListener.onDismiss();
            }
        }
    }

    /* JADX INFO: compiled from: PopupWindowUtil.java */
    public static class b implements PopupWindow.OnDismissListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ PopupWindow.OnDismissListener f1499a;

        public b(PopupWindow.OnDismissListener onDismissListener) {
            this.f1499a = onDismissListener;
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            PopupWindow.OnDismissListener onDismissListener = this.f1499a;
            if (onDismissListener != null) {
                onDismissListener.onDismiss();
            }
        }
    }

    public static void a(Window window, float f2) {
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = f2;
        window.setAttributes(attributes);
    }

    public static PopupWindow b(Activity activity, View view, View view2, int i, int i2, boolean z, boolean z2, PopupWindow.OnDismissListener onDismissListener) {
        PopupWindow popupWindow = new PopupWindow(view2, i, i2);
        popupWindow.setInputMethodMode(1);
        popupWindow.setSoftInputMode(16);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        view2.measure(0, 0);
        a(activity.getWindow(), 0.5f);
        popupWindow.setOnDismissListener(new a(z, activity, onDismissListener));
        int[] iArr = new int[2];
        popupWindow.showAsDropDown(view, iArr[0], iArr[1], !z2 ? GravityCompat.END : 7);
        return popupWindow;
    }

    public static PopupWindow c(View view, View view2, int i, int i2, boolean z, PopupWindow.OnDismissListener onDismissListener) {
        PopupWindow popupWindow = new PopupWindow(view2, i, i2);
        popupWindow.setInputMethodMode(1);
        popupWindow.setSoftInputMode(16);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        view2.measure(0, 0);
        popupWindow.setOnDismissListener(new b(onDismissListener));
        int[] iArr = new int[2];
        popupWindow.showAsDropDown(view, iArr[0], iArr[1], !z ? GravityCompat.END : 7);
        return popupWindow;
    }
}
