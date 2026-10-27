package o;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$style;

/* JADX INFO: compiled from: TooltipPopup.java */
/* JADX INFO: loaded from: classes.dex */
public class i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f4305a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final View f4306b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final TextView f4307c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WindowManager.LayoutParams f4308d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Rect f4309e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int[] f4310f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int[] f4311g;

    public i0(Context context) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.f4308d = layoutParams;
        this.f4309e = new Rect();
        this.f4310f = new int[2];
        this.f4311g = new int[2];
        this.f4305a = context;
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.abc_tooltip, (ViewGroup) null);
        this.f4306b = viewInflate;
        this.f4307c = (TextView) viewInflate.findViewById(R$id.message);
        layoutParams.setTitle(i0.class.getSimpleName());
        layoutParams.packageName = context.getPackageName();
        layoutParams.type = 1002;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = R$style.Animation_AppCompat_Tooltip;
        layoutParams.flags = 24;
    }

    public static View b(View view) {
        View rootView = view.getRootView();
        ViewGroup.LayoutParams layoutParams = rootView.getLayoutParams();
        if ((layoutParams instanceof WindowManager.LayoutParams) && ((WindowManager.LayoutParams) layoutParams).type == 2) {
            return rootView;
        }
        for (Context context = view.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return ((Activity) context).getWindow().getDecorView();
            }
        }
        return rootView;
    }

    public final void a(View view, int i3, int i4, boolean z3, WindowManager.LayoutParams layoutParams) {
        int height;
        int i5;
        layoutParams.token = view.getApplicationWindowToken();
        int dimensionPixelOffset = this.f4305a.getResources().getDimensionPixelOffset(R$dimen.tooltip_precise_anchor_threshold);
        if (view.getWidth() < dimensionPixelOffset) {
            i3 = view.getWidth() / 2;
        }
        if (view.getHeight() >= dimensionPixelOffset) {
            int dimensionPixelOffset2 = this.f4305a.getResources().getDimensionPixelOffset(R$dimen.tooltip_precise_anchor_extra_offset);
            height = i4 + dimensionPixelOffset2;
            i5 = i4 - dimensionPixelOffset2;
        } else {
            height = view.getHeight();
            i5 = 0;
        }
        layoutParams.gravity = 49;
        int dimensionPixelOffset3 = this.f4305a.getResources().getDimensionPixelOffset(z3 ? R$dimen.tooltip_y_offset_touch : R$dimen.tooltip_y_offset_non_touch);
        View viewB = b(view);
        if (viewB == null) {
            Log.e("TooltipPopup", "Cannot find app view");
            return;
        }
        viewB.getWindowVisibleDisplayFrame(this.f4309e);
        Rect rect = this.f4309e;
        if (rect.left < 0 && rect.top < 0) {
            Resources resources = this.f4305a.getResources();
            int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
            int dimensionPixelSize = identifier != 0 ? resources.getDimensionPixelSize(identifier) : 0;
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            this.f4309e.set(0, dimensionPixelSize, displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        viewB.getLocationOnScreen(this.f4311g);
        view.getLocationOnScreen(this.f4310f);
        int[] iArr = this.f4310f;
        int i6 = iArr[0];
        int[] iArr2 = this.f4311g;
        iArr[0] = i6 - iArr2[0];
        iArr[1] = iArr[1] - iArr2[1];
        layoutParams.x = (iArr[0] + i3) - (viewB.getWidth() / 2);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.f4306b.measure(iMakeMeasureSpec, iMakeMeasureSpec);
        int measuredHeight = this.f4306b.getMeasuredHeight();
        int[] iArr3 = this.f4310f;
        int i7 = ((iArr3[1] + i5) - dimensionPixelOffset3) - measuredHeight;
        int i8 = iArr3[1] + height + dimensionPixelOffset3;
        if (z3) {
            if (i7 >= 0) {
                layoutParams.y = i7;
                return;
            } else {
                layoutParams.y = i8;
                return;
            }
        }
        if (measuredHeight + i8 <= this.f4309e.height()) {
            layoutParams.y = i8;
        } else {
            layoutParams.y = i7;
        }
    }

    public void c() {
        if (d()) {
            ((WindowManager) this.f4305a.getSystemService("window")).removeView(this.f4306b);
        }
    }

    public boolean d() {
        return this.f4306b.getParent() != null;
    }

    public void e(View view, int i3, int i4, boolean z3, CharSequence charSequence) {
        if (d()) {
            c();
        }
        this.f4307c.setText(charSequence);
        a(view, i3, i4, z3, this.f4308d);
        ((WindowManager) this.f4305a.getSystemService("window")).addView(this.f4306b, this.f4308d);
    }
}
