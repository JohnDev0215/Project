package defpackage;

import android.R;
import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: compiled from: PartialCustomTabVersionCompat.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class ow1 {
    public final Activity a;
    public final Runnable b;

    /* JADX INFO: compiled from: PartialCustomTabVersionCompat.java */
    public static class a extends ow1 {
        public nw1 c;

        /* JADX INFO: renamed from: ow1$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: PartialCustomTabVersionCompat.java */
        public class ViewOnLayoutChangeListenerC0161a implements View.OnLayoutChangeListener {
            public final /* synthetic */ View e;

            public ViewOnLayoutChangeListenerC0161a(View view) {
                this.e = view;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                this.e.removeOnLayoutChangeListener(this);
                a.this.b.run();
            }
        }

        public a(Activity activity, Runnable runnable) {
            super(activity, runnable);
        }

        @Override // defpackage.ow1
        public final int b() {
            return i().heightPixels;
        }

        @Override // defpackage.ow1
        public final int c() {
            return i().widthPixels;
        }

        @Override // defpackage.ow1
        public final int d() {
            return (int) (c() / i().density);
        }

        @Override // defpackage.ow1
        public final int e() {
            int iB = b();
            Activity activity = this.a;
            int iF = f() + activity.findViewById(R.id.content).getHeight();
            Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getSize(point);
            return iB - Math.max(iF, point.y);
        }

        @Override // defpackage.ow1
        public final int f() {
            Activity activity = this.a;
            int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (identifier > 0) {
                return activity.getResources().getDimensionPixelSize(identifier);
            }
            return 0;
        }

        @Override // defpackage.ow1
        public final boolean g(ld ldVar) {
            nw1 nw1Var;
            View viewFindViewById = this.a.findViewById(R.id.content);
            if (ldVar == null && (nw1Var = this.c) != null) {
                viewFindViewById.removeOnLayoutChangeListener(nw1Var);
                this.c = null;
                return true;
            }
            int i = 0;
            if (ldVar == null || this.c != null) {
                return false;
            }
            nw1 nw1Var2 = new nw1(this, i, ldVar);
            this.c = nw1Var2;
            viewFindViewById.addOnLayoutChangeListener(nw1Var2);
            return true;
        }

        @Override // defpackage.ow1
        public final void h() {
            View viewFindViewById = this.a.findViewById(R.id.content);
            if (viewFindViewById == null) {
                return;
            }
            viewFindViewById.addOnLayoutChangeListener(new ViewOnLayoutChangeListenerC0161a(viewFindViewById));
        }

        public final DisplayMetrics i() {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            vj1.h.getClass();
            Activity activity = this.a;
            if (vj1.o(activity)) {
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            } else {
                activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
            }
            return displayMetrics;
        }
    }

    /* JADX INFO: compiled from: PartialCustomTabVersionCompat.java */
    public static class b extends ow1 {
        public pw1 c;

        public b(Activity activity, Runnable runnable) {
            super(activity, runnable);
        }

        @Override // defpackage.ow1
        public final int b() {
            return this.a.getWindowManager().getCurrentWindowMetrics().getBounds().height();
        }

        @Override // defpackage.ow1
        public final int c() {
            return this.a.getWindowManager().getCurrentWindowMetrics().getBounds().width();
        }

        @Override // defpackage.ow1
        public final int d() {
            return (int) (c() / this.a.getResources().getDisplayMetrics().density);
        }

        @Override // defpackage.ow1
        public final int e() {
            return this.a.getWindowManager().getCurrentWindowMetrics().getWindowInsets().getInsets(WindowInsets.Type.navigationBars()).bottom;
        }

        @Override // defpackage.ow1
        public final int f() {
            return this.a.getWindowManager().getCurrentWindowMetrics().getWindowInsets().getInsets(WindowInsets.Type.statusBars()).top;
        }

        @Override // defpackage.ow1
        public final boolean g(ld ldVar) {
            boolean z = ldVar == null;
            pw1 pw1Var = this.c;
            boolean z2 = (pw1Var == null) ^ z;
            if (ldVar == null && pw1Var != null) {
                this.c = null;
            } else if (ldVar != null && pw1Var == null) {
                this.c = new pw1(this, ldVar);
            }
            if (z2) {
                this.a.getWindow().getDecorView().setWindowInsetsAnimationCallback(this.c);
            }
            return z2;
        }

        @Override // defpackage.ow1
        public final void h() {
            this.b.run();
        }
    }

    public ow1(Activity activity, Runnable runnable) {
        this.a = activity;
        this.b = runnable;
    }

    public static ow1 a(Activity activity, Runnable runnable) {
        return Build.VERSION.SDK_INT < 30 ? new a(activity, runnable) : new b(activity, runnable);
    }

    public abstract int b();

    public abstract int c();

    public abstract int d();

    public abstract int e();

    public abstract int f();

    public abstract boolean g(ld ldVar);

    public abstract void h();
}
