package defpackage;

import android.animation.TimeAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import java.util.ArrayList;
import org.chromium.chrome.R$dimen;

/* JADX INFO: compiled from: AppMenuDragHelper.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"NewApi"})
public final class g7 {
    public final x6 a;
    public final float b;
    public final TimeAnimator c;
    public float d;
    public int e;
    public volatile float f;
    public volatile float g;
    public volatile float h;
    public final int i;
    public boolean j;
    public int k;
    public final Rect l;
    public final int[] m;
    public final int n;
    public final int o;

    public g7(Context context, x6 x6Var, int i) {
        TimeAnimator timeAnimator = new TimeAnimator();
        this.c = timeAnimator;
        this.l = new Rect();
        this.m = new int[2];
        this.a = x6Var;
        this.i = i;
        this.b = context.getResources().getDimensionPixelSize(R$dimen.auto_scroll_full_velocity);
        timeAnimator.setTimeListener(new TimeAnimator.TimeListener() { // from class: f7
            @Override // android.animation.TimeAnimator.TimeListener
            public final void onTimeUpdate(TimeAnimator timeAnimator2, long j, long j2) {
                g7 g7Var = this.a;
                if (g7Var.a.k == null) {
                    return;
                }
                float f = (j2 * 0.001f * g7Var.f) + g7Var.d;
                g7Var.d = f;
                int iRound = Math.round(f - g7Var.e);
                g7Var.e += iRound;
                g7Var.a.k.smoothScrollBy(iRound, 0);
                if (Float.isNaN(g7Var.g) || Float.isNaN(g7Var.h)) {
                    return;
                }
                g7Var.b(Math.round(g7Var.g), Math.round(g7Var.h), 0);
            }
        });
        this.n = (ViewConfiguration.getLongPressTimeout() + ViewConfiguration.getTapTimeout()) / 2;
        this.o = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public final Rect a(View view) {
        Rect rect = this.l;
        view.getLocalVisibleRect(rect);
        int[] iArr = this.m;
        view.getLocationOnScreen(iArr);
        rect.offset(iArr[0], iArr[1]);
        return rect;
    }

    public final boolean b(int i, int i2, int i3) {
        boolean z;
        x6 x6Var = this.a;
        ListView listView = x6Var.k;
        View childAt = listView.getChildAt(0);
        if (!(listView.getFirstVisiblePosition() != 0 || childAt == null || childAt.getTop() != 0 || a(childAt).bottom > this.k)) {
            return false;
        }
        ListView listView2 = x6Var.k;
        ArrayList arrayList = new ArrayList();
        for (int i4 = 0; i4 < listView2.getChildCount(); i4++) {
            if (listView2.getChildAt(i4) instanceof LinearLayout) {
                LinearLayout linearLayout = (LinearLayout) listView2.getChildAt(i4);
                z = false;
                for (int i5 = 0; i5 < linearLayout.getChildCount(); i5++) {
                    arrayList.add(linearLayout.getChildAt(i5));
                    if (linearLayout.getChildAt(i5) instanceof ImageButton) {
                        z = true;
                    }
                }
            } else {
                z = false;
            }
            if (!z) {
                arrayList.add(listView2.getChildAt(i4));
            }
        }
        boolean z2 = false;
        for (int i6 = 0; i6 < arrayList.size(); i6++) {
            View view = (View) arrayList.get(i6);
            boolean z3 = view.isEnabled() && view.isShown() && a(view).contains(i, i2);
            if (i3 == 0) {
                view.setPressed(z3);
            } else if (i3 != 1) {
                if (i3 == 2) {
                    view.setPressed(false);
                }
            } else if (z3) {
                n3.X0("MobileUsingMenuBySwButtonDragging");
                view.performClick();
                z2 = true;
            }
        }
        return z2;
    }
}
