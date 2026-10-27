package defpackage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import defpackage.pa1;
import java.util.HashMap;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.c;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: LongScreenshotsMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ra1 implements pa1.a, cg0, View.OnTouchListener, DialogInterface.OnShowListener {
    public Dialog e;
    public Runnable f;
    public View g;
    public ScrollView h;
    public View i;
    public View j;
    public View k;
    public View l;
    public View m;
    public ImageView n;
    public final Activity o;
    public final c p;
    public Bitmap q;
    public final float r;
    public int s;
    public int t;
    public boolean u;

    public ra1(Activity activity, c cVar) {
        this.o = activity;
        this.p = cVar;
        this.r = activity.getResources().getDisplayMetrics().density;
    }

    @Override // defpackage.cg0
    public final void b(eu1 eu1Var) {
        this.f = eu1Var;
        this.p.a(new qa1(this));
    }

    @Override // defpackage.cg0
    public final Bitmap c() {
        int height = this.i.getHeight();
        int height2 = ((View) this.j.getParent()).getHeight() - this.j.getHeight();
        int i = ((ViewGroup.MarginLayoutParams) this.n.getLayoutParams()).topMargin;
        int i2 = height - i;
        int i3 = height2 - i;
        int width = this.q.getWidth();
        int width2 = this.n.getWidth();
        if (width > width2) {
            float f = (width * 1.0f) / width2;
            i2 = (int) (i2 * f);
            i3 = (int) (i3 * f);
        }
        int iMax = Math.max(i2, 0);
        int iMin = Math.min(i3, this.q.getHeight() - 1);
        if (iMin <= iMax) {
            return null;
        }
        Bitmap bitmap = this.q;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, iMax, bitmap.getWidth(), iMin - iMax);
        this.q = null;
        return bitmapCreateBitmap;
    }

    public final void d(boolean z) {
        View view = z ? this.i : this.j;
        int height = view.getHeight();
        float f = this.r;
        int i = (int) ((20 * f) + 0.5f);
        if (height <= i) {
            Toast.c(this.o, z ? R$string.sharing_long_screenshot_reached_top : R$string.sharing_long_screenshot_reached_bottom, 1).e();
            return;
        }
        int iMax = Math.max(i, height - ((int) ((100 * f) + 0.5f)));
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = iMax;
        view.setLayoutParams(layoutParams);
        this.h.smoothScrollBy(0, (iMax - height) * (z ? 1 : -1));
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [T, by1] */
    /* JADX WARN: Type inference failed for: r1v3, types: [T, vt] */
    /* JADX WARN: Type inference failed for: r1v4, types: [T, gs] */
    /* JADX WARN: Type inference failed for: r5v1, types: [T, ie0] */
    public final void f(Bitmap bitmap) {
        this.q = bitmap;
        Activity activity = this.o;
        this.g = activity.getLayoutInflater().inflate(R$layout.long_screenshots_area_selection_dialog, (ViewGroup) null);
        PropertyModel.r<View.OnClickListener> rVar = ma1.a;
        PropertyModel.r<View.OnClickListener> rVar2 = ma1.b;
        PropertyModel.r<View.OnClickListener> rVar3 = ma1.c;
        PropertyModel.r<View.OnClickListener> rVar4 = ma1.d;
        HashMap mapE = PropertyModel.e(new v62[]{rVar, rVar2, rVar3, rVar4});
        int i = 19;
        ?? ie0Var = new ie0(i, this);
        int i2 = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = ie0Var;
        mapE.put(rVar, gVar);
        ?? by1Var = new by1(19, this);
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = by1Var;
        mapE.put(rVar2, gVar2);
        ?? vtVar = new vt(20, this);
        PropertyModel.g gVar3 = new PropertyModel.g();
        gVar3.a = vtVar;
        mapE.put(rVar3, gVar3);
        ?? gsVar = new gs(7, this);
        PropertyModel.g gVar4 = new PropertyModel.g();
        gVar4.a = gsVar;
        mapE.put(rVar4, gVar4);
        j.b(new PropertyModel(mapE), this.g, new bg(i));
        Dialog dialog = new Dialog(activity, R$style.ThemeOverlay_BrowserUI_Fullscreen);
        this.e = dialog;
        dialog.addContentView(this.g, new LinearLayout.LayoutParams(-1, -1));
        this.h = (ScrollView) this.g.findViewById(R$id.long_screenshot_scroll_view);
        this.i = this.g.findViewById(R$id.region_selection_top);
        this.j = this.g.findViewById(R$id.region_selection_bottom);
        this.k = this.g.findViewById(R$id.sharing_long_screenshot_dialog_instructions);
        View viewFindViewById = this.g.findViewById(R$id.up_button);
        this.l = viewFindViewById;
        viewFindViewById.setOnTouchListener(this);
        View viewFindViewById2 = this.g.findViewById(R$id.down_button);
        this.m = viewFindViewById2;
        viewFindViewById2.setOnTouchListener(this);
        ImageView imageView = (ImageView) this.g.findViewById(R$id.screenshot_image);
        this.n = imageView;
        imageView.setScaleType(ImageView.ScaleType.FIT_START);
        this.n.setImageBitmap(this.q);
        this.e.setOnShowListener(this);
        this.e.show();
    }

    @Override // android.content.DialogInterface.OnShowListener
    public final void onShow(DialogInterface dialogInterface) {
        ViewGroup.LayoutParams layoutParams = this.j.getLayoutParams();
        layoutParams.height = this.i.getHeight() + (this.q.getHeight() - this.h.getHeight());
        this.j.setLayoutParams(layoutParams);
    }

    @Override // android.view.View.OnTouchListener
    @SuppressLint({"ClickableViewAccessibility"})
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        int i;
        boolean z = view == this.l;
        View view2 = z ? this.i : this.j;
        int rawY = (int) motionEvent.getRawY();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            this.s = rawY;
            this.t = layoutParams.height;
            this.u = true;
            this.h.requestDisallowInterceptTouchEvent(true);
        } else {
            if (actionMasked == 1) {
                if (!this.u) {
                    return false;
                }
                (z ? this.l : this.m).performClick();
                this.u = false;
                return false;
            }
            if (actionMasked != 2) {
                return false;
            }
            this.k.setVisibility(4);
            ViewGroup.LayoutParams layoutParams2 = view2.getLayoutParams();
            layoutParams2.height = this.t + ((rawY - this.s) * (z ? 1 : -1));
            this.u = false;
            int height = this.i.getHeight();
            int height2 = ((View) this.j.getParent()).getHeight() - this.j.getHeight();
            int height3 = ((View) this.j.getParent()).getHeight();
            float f = this.r;
            int i2 = (int) ((50 * f) + 0.5f);
            if (z && layoutParams2.height + i2 > height2) {
                layoutParams2.height = height2 - i2;
            }
            if (!z && layoutParams2.height > (i = (height3 - height) - i2)) {
                layoutParams2.height = i;
            }
            int i3 = (int) ((20 * f) + 0.5f);
            if (layoutParams2.height < i3) {
                layoutParams2.height = i3;
            }
            view2.setLayoutParams(layoutParams2);
        }
        return true;
    }

    @Override // pa1.a
    public final void a(int i) {
    }
}
