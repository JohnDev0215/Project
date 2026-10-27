package r;

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
    public final Context f7564a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final View f7565b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final TextView f7566c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WindowManager.LayoutParams f7567d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Rect f7568e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int[] f7569f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int[] f7570g;

    public i0(Context context) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.f7567d = layoutParams;
        this.f7568e = new Rect();
        this.f7569f = new int[2];
        this.f7570g = new int[2];
        this.f7564a = context;
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.abc_tooltip, (ViewGroup) null);
        this.f7565b = viewInflate;
        this.f7566c = (TextView) viewInflate.findViewById(R$id.message);
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

    public final void a(View view, int i8, int i9, boolean z7, WindowManager.LayoutParams layoutParams) {
        int height;
        int i10;
        layoutParams.token = view.getApplicationWindowToken();
        int dimensionPixelOffset = this.f7564a.getResources().getDimensionPixelOffset(R$dimen.tooltip_precise_anchor_threshold);
        if (view.getWidth() < dimensionPixelOffset) {
            i8 = view.getWidth() / 2;
        }
        if (view.getHeight() >= dimensionPixelOffset) {
            int dimensionPixelOffset2 = this.f7564a.getResources().getDimensionPixelOffset(R$dimen.tooltip_precise_anchor_extra_offset);
            height = i9 + dimensionPixelOffset2;
            i10 = i9 - dimensionPixelOffset2;
        } else {
            height = view.getHeight();
            i10 = 0;
        }
        layoutParams.gravity = 49;
        int dimensionPixelOffset3 = this.f7564a.getResources().getDimensionPixelOffset(z7 ? R$dimen.tooltip_y_offset_touch : R$dimen.tooltip_y_offset_non_touch);
        View viewB = b(view);
        if (viewB == null) {
            Log.e("TooltipPopup", "Cannot find app view");
            return;
        }
        viewB.getWindowVisibleDisplayFrame(this.f7568e);
        Rect rect = this.f7568e;
        if (rect.left < 0 && rect.top < 0) {
            Resources resources = this.f7564a.getResources();
            int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
            int dimensionPixelSize = identifier != 0 ? resources.getDimensionPixelSize(identifier) : 0;
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            this.f7568e.set(0, dimensionPixelSize, displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        viewB.getLocationOnScreen(this.f7570g);
        view.getLocationOnScreen(this.f7569f);
        int[] iArr = this.f7569f;
        int i11 = iArr[0];
        int[] iArr2 = this.f7570g;
        iArr[0] = i11 - iArr2[0];
        iArr[1] = iArr[1] - iArr2[1];
        layoutParams.x = (iArr[0] + i8) - (viewB.getWidth() / 2);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.f7565b.measure(iMakeMeasureSpec, iMakeMeasureSpec);
        int measuredHeight = this.f7565b.getMeasuredHeight();
        int[] iArr3 = this.f7569f;
        int i12 = ((iArr3[1] + i10) - dimensionPixelOffset3) - measuredHeight;
        int i13 = iArr3[1] + height + dimensionPixelOffset3;
        if (z7) {
            if (i12 >= 0) {
                layoutParams.y = i12;
                return;
            } else {
                layoutParams.y = i13;
                return;
            }
        }
        if (measuredHeight + i13 <= this.f7568e.height()) {
            layoutParams.y = i13;
        } else {
            layoutParams.y = i12;
        }
    }

    public void c() {
        if (d()) {
            ((WindowManager) this.f7564a.getSystemService("window")).removeView(this.f7565b);
        }
    }

    public boolean d() {
        return this.f7565b.getParent() != null;
    }

    public void e(View view, int i8, int i9, boolean z7, CharSequence charSequence) {
        if (d()) {
            c();
        }
        this.f7566c.setText(charSequence);
        a(view, i8, i9, z7, this.f7567d);
        ((WindowManager) this.f7564a.getSystemService("window")).addView(this.f7565b, this.f7567d);
    }
}
