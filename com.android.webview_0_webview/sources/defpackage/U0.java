package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class U0 implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
    public static U0 B;
    public static U0 C;
    public boolean A;
    public final View s;
    public final CharSequence t;
    public final int u;
    public final Runnable v = new S0(this);
    public final Runnable w = new T0(this);
    public int x;
    public int y;
    public V0 z;

    public U0(View view, CharSequence charSequence) {
        this.s = view;
        this.t = charSequence;
        this.u = AbstractC1306p3.c(ViewConfiguration.get(view.getContext()));
        a();
        this.s.setOnLongClickListener(this);
        this.s.setOnHoverListener(this);
    }

    public static void c(U0 u0) {
        U0 u02 = B;
        if (u02 != null) {
            u02.s.removeCallbacks(u02.v);
        }
        B = u0;
        if (u0 != null) {
            u0.s.postDelayed(u0.v, ViewConfiguration.getLongPressTimeout());
        }
    }

    public final void a() {
        this.x = Integer.MAX_VALUE;
        this.y = Integer.MAX_VALUE;
    }

    public void b() {
        if (C == this) {
            C = null;
            V0 v0 = this.z;
            if (v0 != null) {
                v0.a();
                this.z = null;
                a();
                this.s.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (B == this) {
            c(null);
        }
        this.s.removeCallbacks(this.w);
    }

    public void d(boolean z) {
        int height;
        int i;
        long j;
        int longPressTimeout;
        long j2;
        if (AbstractC1251o3.t(this.s)) {
            c(null);
            U0 u0 = C;
            if (u0 != null) {
                u0.b();
            }
            C = this;
            this.A = z;
            V0 v0 = new V0(this.s.getContext());
            this.z = v0;
            View view = this.s;
            int width = this.x;
            int i2 = this.y;
            boolean z2 = this.A;
            CharSequence charSequence = this.t;
            if (v0.b.getParent() != null) {
                v0.a();
            }
            v0.c.setText(charSequence);
            WindowManager.LayoutParams layoutParams = v0.d;
            layoutParams.token = view.getApplicationWindowToken();
            int dimensionPixelOffset = v0.a.getResources().getDimensionPixelOffset(AbstractC0299Qm.t5);
            if (view.getWidth() < dimensionPixelOffset) {
                width = view.getWidth() / 2;
            }
            if (view.getHeight() >= dimensionPixelOffset) {
                int dimensionPixelOffset2 = v0.a.getResources().getDimensionPixelOffset(AbstractC0299Qm.s5);
                height = i2 + dimensionPixelOffset2;
                i = i2 - dimensionPixelOffset2;
            } else {
                height = view.getHeight();
                i = 0;
            }
            layoutParams.gravity = 49;
            int dimensionPixelOffset3 = v0.a.getResources().getDimensionPixelOffset(z2 ? AbstractC0299Qm.w5 : AbstractC0299Qm.v5);
            View rootView = view.getRootView();
            ViewGroup.LayoutParams layoutParams2 = rootView.getLayoutParams();
            if (!(layoutParams2 instanceof WindowManager.LayoutParams) || ((WindowManager.LayoutParams) layoutParams2).type != 2) {
                Context context = view.getContext();
                while (true) {
                    if (!(context instanceof ContextWrapper)) {
                        break;
                    }
                    if (context instanceof Activity) {
                        rootView = ((Activity) context).getWindow().getDecorView();
                        break;
                    }
                    context = ((ContextWrapper) context).getBaseContext();
                }
            }
            if (rootView == null) {
                Log.e("TooltipPopup", "Cannot find app view");
            } else {
                rootView.getWindowVisibleDisplayFrame(v0.e);
                Rect rect = v0.e;
                if (rect.left < 0 && rect.top < 0) {
                    Resources resources = v0.a.getResources();
                    int iB = AbstractC1077kw.b(resources, "status_bar_height", "dimen", "android");
                    int dimensionPixelSize = iB != 0 ? resources.getDimensionPixelSize(iB) : 0;
                    DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                    v0.e.set(0, dimensionPixelSize, displayMetrics.widthPixels, displayMetrics.heightPixels);
                }
                rootView.getLocationOnScreen(v0.g);
                view.getLocationOnScreen(v0.f);
                int[] iArr = v0.f;
                int i3 = iArr[0];
                int[] iArr2 = v0.g;
                iArr[0] = i3 - iArr2[0];
                iArr[1] = iArr[1] - iArr2[1];
                layoutParams.x = (iArr[0] + width) - (rootView.getWidth() / 2);
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                v0.b.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                int measuredHeight = v0.b.getMeasuredHeight();
                int[] iArr3 = v0.f;
                int i4 = ((iArr3[1] + i) - dimensionPixelOffset3) - measuredHeight;
                int i5 = iArr3[1] + height + dimensionPixelOffset3;
                if (z2) {
                    if (i4 >= 0) {
                        layoutParams.y = i4;
                    } else {
                        layoutParams.y = i5;
                    }
                } else if (measuredHeight + i5 <= v0.e.height()) {
                    layoutParams.y = i5;
                } else {
                    layoutParams.y = i4;
                }
            }
            ((WindowManager) v0.a.getSystemService("window")).addView(v0.b, v0.d);
            this.s.addOnAttachStateChangeListener(this);
            if (this.A) {
                j2 = 2500;
            } else {
                if ((this.s.getWindowSystemUiVisibility() & 1) == 1) {
                    j = 3000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                } else {
                    j = 15000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                }
                j2 = j - ((long) longPressTimeout);
            }
            this.s.removeCallbacks(this.w);
            this.s.postDelayed(this.w, j2);
        }
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        boolean z;
        if (this.z != null && this.A) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.s.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                a();
                b();
            }
        } else if (this.s.isEnabled() && this.z == null) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (Math.abs(x - this.x) > this.u || Math.abs(y - this.y) > this.u) {
                this.x = x;
                this.y = y;
                z = true;
            } else {
                z = false;
            }
            if (z) {
                c(this);
            }
        }
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        this.x = view.getWidth() / 2;
        this.y = view.getHeight() / 2;
        d(true);
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        b();
    }
}
