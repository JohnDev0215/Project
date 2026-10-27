package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.ArrayList;
import org.chromium.chrome.browser.payments.ui.PaymentRequestSection;
import org.chromium.chrome.browser.payments.ui.f;

/* JADX INFO: compiled from: FocusAnimator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class go0 {
    public final LinearLayout a;
    public final View b;
    public final int c;
    public final ArrayList<Integer> d = a();

    public go0(LinearLayout linearLayout, PaymentRequestSection paymentRequestSection, f fVar) {
        this.a = linearLayout;
        this.b = paymentRequestSection;
        this.c = linearLayout.getChildCount();
        linearLayout.addOnLayoutChangeListener(new bo0(this, fVar));
    }

    public final ArrayList<Integer> a() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int i = 0;
        int measuredHeight = 0;
        while (true) {
            LinearLayout linearLayout = this.a;
            if (i >= linearLayout.getChildCount()) {
                arrayList.add(Integer.valueOf(measuredHeight));
                return arrayList;
            }
            arrayList.add(Integer.valueOf(measuredHeight));
            measuredHeight += linearLayout.getChildAt(i).getMeasuredHeight();
            i++;
        }
    }

    public final void b() {
        LinearLayout linearLayout = this.a;
        ViewGroup viewGroup = (ViewGroup) linearLayout.getParent();
        if (linearLayout.getParent() == null) {
            return;
        }
        View view = this.b;
        if (view != null) {
            viewGroup.requestChildFocus(linearLayout, view);
        }
        int iMax = Math.max(0, linearLayout.getMeasuredHeight() - (viewGroup.getBottom() - viewGroup.getTop()));
        if (viewGroup.getScrollY() > iMax) {
            viewGroup.setScrollY(iMax);
        }
    }
}
