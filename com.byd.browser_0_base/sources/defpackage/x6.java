package defpackage;

import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import org.chromium.base.Callback;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$style;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.f;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: AppMenu.java */
/* JADX INFO: loaded from: classes2.dex */
public final class x6 implements AdapterView.OnItemClickListener, View.OnKeyListener, b7 {
    public static Callback<Throwable> u;
    public final int e;
    public final int f;
    public final int g;
    public final int h;
    public final int[] i;
    public PopupWindow j;
    public ListView k;
    public f l;
    public final i7 m;
    public View n;
    public int o = -1;
    public boolean p;
    public AnimatorSet q;
    public long r;
    public boolean s;
    public ya1 t;

    public x6(int i, i7 i7Var, Resources resources) {
        this.e = i;
        this.m = i7Var;
        this.g = resources.getDimensionPixelSize(R$dimen.menu_negative_software_vertical_offset);
        this.f = resources.getDimensionPixelSize(R$dimen.menu_vertical_fade_distance);
        resources.getDimensionPixelSize(R$dimen.menu_negative_vertical_offset_not_top_anchored);
        this.h = resources.getDimensionPixelOffset(R$dimen.menu_chip_highlight_extension);
        this.i = new int[2];
    }

    @Override // defpackage.b7
    public final void a(PropertyModel propertyModel) {
        if (propertyModel.k(j7.d)) {
            int iH = propertyModel.h(j7.a);
            this.s = true;
            c();
            i7 i7Var = this.m;
            i7Var.l.S0(iH, i7Var.k.g(iH));
        }
    }

    @Override // defpackage.b7
    public final boolean b(View view, PropertyModel propertyModel) {
        if (!propertyModel.k(j7.d)) {
            return false;
        }
        this.s = true;
        CharSequence charSequence = (CharSequence) propertyModel.j(j7.c);
        if (TextUtils.isEmpty(charSequence)) {
            charSequence = (CharSequence) propertyModel.j(j7.b);
        }
        Context context = view.getContext();
        int iC = yp.c(context, R$dimen.toast_elevation);
        Toast.Builder builder = new Toast.Builder(context);
        builder.b = charSequence;
        builder.c = view;
        builder.d = Integer.valueOf(iC);
        builder.e = Integer.valueOf(R$style.TextAppearance_TextSmall_Primary);
        if (builder.b == null) {
            return false;
        }
        builder.a().e();
        return true;
    }

    public final void c() {
        PopupWindow popupWindow = this.j;
        if (popupWindow == null ? false : popupWindow.isShowing()) {
            this.j.dismiss();
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        a(this.t.get(i).b);
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (this.k != null && keyEvent.getKeyCode() == 82) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                keyEvent.startTracking();
                view.getKeyDispatcherState().startTracking(keyEvent, this);
                return true;
            }
            if (keyEvent.getAction() == 1) {
                view.getKeyDispatcherState().handleUpEvent(keyEvent);
                if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                    c();
                    return true;
                }
            }
        }
        return false;
    }
}
