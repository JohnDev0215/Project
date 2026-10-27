package m3;

import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/* JADX INFO: renamed from: m3.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ViewOnLayoutChangeListenerC1806f implements View.OnLayoutChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f19055a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f19056b;

    public /* synthetic */ ViewOnLayoutChangeListenerC1806f(Object obj, int i7) {
        this.f19055a = i7;
        this.f19056b = obj;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i7, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
        int height;
        int height2;
        switch (this.f19055a) {
            case 0:
                s sVar = (s) this.f19056b;
                sVar.getClass();
                int i17 = i12 - i10;
                int i18 = i16 - i14;
                if (i11 - i7 != i15 - i13 || i17 != i18) {
                    PopupWindow popupWindow = sVar.f19138v;
                    if (popupWindow.isShowing()) {
                        sVar.q();
                        int width = sVar.getWidth() - popupWindow.getWidth();
                        int i19 = sVar.f19140w;
                        popupWindow.update(view, width - i19, (-popupWindow.getHeight()) - i19, -1, -1);
                    }
                }
                break;
            default:
                x xVar = (x) this.f19056b;
                s sVar2 = xVar.f19157a;
                int width2 = (sVar2.getWidth() - sVar2.getPaddingLeft()) - sVar2.getPaddingRight();
                int height3 = (sVar2.getHeight() - sVar2.getPaddingBottom()) - sVar2.getPaddingTop();
                ViewGroup viewGroup = xVar.c;
                int iC = x.c(viewGroup) - (viewGroup != null ? viewGroup.getPaddingRight() + viewGroup.getPaddingLeft() : 0);
                if (viewGroup == null) {
                    height = 0;
                } else {
                    height = viewGroup.getHeight();
                    ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
                    if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                        height += marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
                    }
                }
                int paddingBottom = height - (viewGroup != null ? viewGroup.getPaddingBottom() + viewGroup.getPaddingTop() : 0);
                int iMax = Math.max(iC, x.c(xVar.f19165k) + x.c(xVar.f19163i));
                ViewGroup viewGroup2 = xVar.d;
                if (viewGroup2 == null) {
                    height2 = 0;
                } else {
                    height2 = viewGroup2.getHeight();
                    ViewGroup.LayoutParams layoutParams2 = viewGroup2.getLayoutParams();
                    if (layoutParams2 instanceof ViewGroup.MarginLayoutParams) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams2;
                        height2 += marginLayoutParams2.topMargin + marginLayoutParams2.bottomMargin;
                    }
                }
                boolean z6 = width2 <= iMax || height3 <= (height2 * 2) + paddingBottom;
                if (xVar.f19155A != z6) {
                    xVar.f19155A = z6;
                    view.post(new t(xVar, 1));
                }
                boolean z10 = i11 - i7 != i15 - i13;
                if (!xVar.f19155A && z10) {
                    view.post(new t(xVar, 2));
                    break;
                }
                break;
        }
    }
}
