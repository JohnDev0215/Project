package b.b.q;

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

/* JADX INFO: compiled from: TooltipPopup.java */
/* JADX INFO: loaded from: classes.dex */
public class i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1763a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final View f1764b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final TextView f1765c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WindowManager.LayoutParams f1766d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Rect f1767e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int[] f1768f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int[] f1769g;

    public i0(Context context) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.f1766d = layoutParams;
        this.f1767e = new Rect();
        this.f1768f = new int[2];
        this.f1769g = new int[2];
        this.f1763a = context;
        View viewInflate = LayoutInflater.from(context).inflate(b.b.g.abc_tooltip, (ViewGroup) null);
        this.f1764b = viewInflate;
        this.f1765c = (TextView) viewInflate.findViewById(b.b.f.message);
        layoutParams.setTitle(i0.class.getSimpleName());
        layoutParams.packageName = context.getPackageName();
        layoutParams.type = 1002;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = b.b.i.Animation_AppCompat_Tooltip;
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

    public final void a(View view, int i2, int i3, boolean z, WindowManager.LayoutParams layoutParams) {
        int height;
        int i4;
        layoutParams.token = view.getApplicationWindowToken();
        int dimensionPixelOffset = this.f1763a.getResources().getDimensionPixelOffset(b.b.d.tooltip_precise_anchor_threshold);
        if (view.getWidth() < dimensionPixelOffset) {
            i2 = view.getWidth() / 2;
        }
        if (view.getHeight() >= dimensionPixelOffset) {
            int dimensionPixelOffset2 = this.f1763a.getResources().getDimensionPixelOffset(b.b.d.tooltip_precise_anchor_extra_offset);
            height = i3 + dimensionPixelOffset2;
            i4 = i3 - dimensionPixelOffset2;
        } else {
            height = view.getHeight();
            i4 = 0;
        }
        layoutParams.gravity = 49;
        int dimensionPixelOffset3 = this.f1763a.getResources().getDimensionPixelOffset(z ? b.b.d.tooltip_y_offset_touch : b.b.d.tooltip_y_offset_non_touch);
        View viewB = b(view);
        if (viewB == null) {
            Log.e("TooltipPopup", "Cannot find app view");
            return;
        }
        viewB.getWindowVisibleDisplayFrame(this.f1767e);
        Rect rect = this.f1767e;
        if (rect.left < 0 && rect.top < 0) {
            Resources resources = this.f1763a.getResources();
            int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
            int dimensionPixelSize = identifier != 0 ? resources.getDimensionPixelSize(identifier) : 0;
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            this.f1767e.set(0, dimensionPixelSize, displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        viewB.getLocationOnScreen(this.f1769g);
        view.getLocationOnScreen(this.f1768f);
        int[] iArr = this.f1768f;
        int i5 = iArr[0];
        int[] iArr2 = this.f1769g;
        iArr[0] = i5 - iArr2[0];
        iArr[1] = iArr[1] - iArr2[1];
        layoutParams.x = (iArr[0] + i2) - (viewB.getWidth() / 2);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.f1764b.measure(iMakeMeasureSpec, iMakeMeasureSpec);
        int measuredHeight = this.f1764b.getMeasuredHeight();
        int[] iArr3 = this.f1768f;
        int i6 = ((iArr3[1] + i4) - dimensionPixelOffset3) - measuredHeight;
        int i7 = iArr3[1] + height + dimensionPixelOffset3;
        if (z) {
            if (i6 >= 0) {
                layoutParams.y = i6;
                return;
            } else {
                layoutParams.y = i7;
                return;
            }
        }
        if (measuredHeight + i7 <= this.f1767e.height()) {
            layoutParams.y = i7;
        } else {
            layoutParams.y = i6;
        }
    }

    public void c() {
        if (d()) {
            ((WindowManager) this.f1763a.getSystemService("window")).removeView(this.f1764b);
        }
    }

    public boolean d() {
        return this.f1764b.getParent() != null;
    }

    public void e(View view, int i2, int i3, boolean z, CharSequence charSequence) {
        if (d()) {
            c();
        }
        this.f1765c.setText(charSequence);
        a(view, i2, i3, z, this.f1766d);
        ((WindowManager) this.f1763a.getSystemService("window")).addView(this.f1764b, this.f1766d);
    }
}
