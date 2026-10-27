package com.blankj.utilcode.util;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.blankj.utilcode.util.w;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final class ToastUtils {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ToastUtils f2590a = n();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static WeakReference<e> f2591b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f2592c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2593d = -1;
    private int e = -1;
    private int f = -1;
    private int g = -16777217;
    private int h = -1;
    private int i = -16777217;
    private int j = -1;
    private boolean k = false;
    private Drawable[] l = new Drawable[4];
    private boolean m = false;

    public static final class UtilsMaxWidthRelativeLayout extends RelativeLayout {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final int f2594b = y.d(80.0f);

        public UtilsMaxWidthRelativeLayout(Context context) {
            super(context);
        }

        @Override // android.widget.RelativeLayout, android.view.View
        protected void onMeasure(int i, int i2) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(y.h() - f2594b, Integer.MIN_VALUE), i2);
        }

        public UtilsMaxWidthRelativeLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public UtilsMaxWidthRelativeLayout(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
        }
    }

    static class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ToastUtils.f2591b != null) {
                e eVar = (e) ToastUtils.f2591b.get();
                if (eVar != null) {
                    eVar.cancel();
                }
                WeakReference unused = ToastUtils.f2591b = null;
            }
        }
    }

    static class b implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f2596c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ CharSequence f2597d;
        final /* synthetic */ int e;

        b(View view, CharSequence charSequence, int i) {
            this.f2596c = view;
            this.f2597d = charSequence;
            this.e = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            ToastUtils.l();
            e eVarO = ToastUtils.o(ToastUtils.this);
            WeakReference unused = ToastUtils.f2591b = new WeakReference(eVarO);
            View view = this.f2596c;
            if (view != null) {
                eVarO.c(view);
            } else {
                eVarO.b(this.f2597d);
            }
            eVarO.a(this.e);
        }
    }

    static abstract class c implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected Toast f2598a = new Toast(w.a());

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected ToastUtils f2599b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        protected View f2600c;

        c(ToastUtils toastUtils) {
            this.f2599b = toastUtils;
            if (toastUtils.f2593d == -1 && this.f2599b.e == -1 && this.f2599b.f == -1) {
                return;
            }
            this.f2598a.setGravity(this.f2599b.f2593d, this.f2599b.e, this.f2599b.f);
        }

        private void e() {
            if (y.w()) {
                c(d(-1));
            }
        }

        private void f(TextView textView) {
            if (this.f2599b.h != -1) {
                this.f2600c.setBackgroundResource(this.f2599b.h);
                textView.setBackgroundColor(0);
                return;
            }
            if (this.f2599b.g != -16777217) {
                Drawable background = this.f2600c.getBackground();
                Drawable background2 = textView.getBackground();
                if (background != null && background2 != null) {
                    background.mutate().setColorFilter(new PorterDuffColorFilter(this.f2599b.g, PorterDuff.Mode.SRC_IN));
                    textView.setBackgroundColor(0);
                } else if (background != null) {
                    background.mutate().setColorFilter(new PorterDuffColorFilter(this.f2599b.g, PorterDuff.Mode.SRC_IN));
                } else if (background2 != null) {
                    background2.mutate().setColorFilter(new PorterDuffColorFilter(this.f2599b.g, PorterDuff.Mode.SRC_IN));
                } else {
                    this.f2600c.setBackgroundColor(this.f2599b.g);
                }
            }
        }

        @Override // com.blankj.utilcode.util.ToastUtils.e
        public void b(CharSequence charSequence) {
            View viewW = this.f2599b.w(charSequence);
            if (viewW != null) {
                c(viewW);
                e();
                return;
            }
            View view = this.f2598a.getView();
            this.f2600c = view;
            if (view == null || view.findViewById(R.id.message) == null) {
                c(y.y(d.a.a.b.f3900a));
            }
            TextView textView = (TextView) this.f2600c.findViewById(R.id.message);
            textView.setText(charSequence);
            if (this.f2599b.i != -16777217) {
                textView.setTextColor(this.f2599b.i);
            }
            if (this.f2599b.j != -1) {
                textView.setTextSize(this.f2599b.j);
            }
            f(textView);
            e();
        }

        @Override // com.blankj.utilcode.util.ToastUtils.e
        public void c(View view) {
            this.f2600c = view;
            this.f2598a.setView(view);
        }

        @Override // com.blankj.utilcode.util.ToastUtils.e
        public void cancel() {
            Toast toast = this.f2598a;
            if (toast != null) {
                toast.cancel();
            }
            this.f2598a = null;
            this.f2600c = null;
        }

        View d(int i) {
            Bitmap bitmapF = y.F(this.f2600c);
            ImageView imageView = new ImageView(w.a());
            imageView.setTag("TAG_TOAST" + i);
            imageView.setImageBitmap(bitmapF);
            return imageView;
        }
    }

    static final class d extends c {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static int f2601d;
        private w.a e;
        private e f;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                d.this.cancel();
            }
        }

        class b extends w.a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int f2603a;

            b(int i) {
                this.f2603a = i;
            }

            @Override // com.blankj.utilcode.util.w.a
            public void a(Activity activity) {
                if (d.this.i()) {
                    d.this.l(activity, this.f2603a, false);
                }
            }
        }

        d(ToastUtils toastUtils) {
            super(toastUtils);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean i() {
            return this.e != null;
        }

        private void j() {
            b bVar = new b(f2601d);
            this.e = bVar;
            y.a(bVar);
        }

        private e k(int i) {
            f fVar = new f(this.f2599b);
            fVar.f2598a = this.f2598a;
            fVar.a(i);
            return fVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void l(Activity activity, int i, boolean z) {
            Window window = activity.getWindow();
            if (window != null) {
                ViewGroup viewGroup = (ViewGroup) window.getDecorView();
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                layoutParams.gravity = this.f2598a.getGravity();
                layoutParams.bottomMargin = this.f2598a.getYOffset() + y.l();
                layoutParams.topMargin = this.f2598a.getYOffset() + y.o();
                layoutParams.leftMargin = this.f2598a.getXOffset();
                View viewD = d(i);
                if (z) {
                    viewD.setAlpha(0.0f);
                    viewD.animate().alpha(1.0f).setDuration(200L).start();
                }
                viewGroup.addView(viewD, layoutParams);
            }
        }

        private e m(Activity activity, int i) {
            g gVar = new g(this.f2599b, activity.getWindowManager(), 99);
            gVar.f2600c = d(-1);
            gVar.f2598a = this.f2598a;
            gVar.a(i);
            return gVar;
        }

        private void n() {
            y.B(this.e);
            this.e = null;
        }

        @Override // com.blankj.utilcode.util.ToastUtils.e
        public void a(int i) {
            if (this.f2598a == null) {
                return;
            }
            if (!y.t()) {
                this.f = k(i);
                return;
            }
            boolean z = false;
            for (Activity activity : y.g()) {
                if (y.s(activity)) {
                    if (z) {
                        l(activity, f2601d, true);
                    } else {
                        this.f = m(activity, i);
                        z = true;
                    }
                }
            }
            if (!z) {
                this.f = k(i);
                return;
            }
            j();
            y.D(new a(), i == 0 ? 2000L : 3500L);
            f2601d++;
        }

        @Override // com.blankj.utilcode.util.ToastUtils.c, com.blankj.utilcode.util.ToastUtils.e
        public void cancel() {
            Window window;
            if (i()) {
                n();
                for (Activity activity : y.g()) {
                    if (y.s(activity) && (window = activity.getWindow()) != null) {
                        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
                        StringBuilder sb = new StringBuilder();
                        sb.append("TAG_TOAST");
                        sb.append(f2601d - 1);
                        View viewFindViewWithTag = viewGroup.findViewWithTag(sb.toString());
                        if (viewFindViewWithTag != null) {
                            try {
                                viewGroup.removeView(viewFindViewWithTag);
                            } catch (Exception unused) {
                            }
                        }
                    }
                }
            }
            e eVar = this.f;
            if (eVar != null) {
                eVar.cancel();
                this.f = null;
            }
            super.cancel();
        }
    }

    interface e {
        void a(int i);

        void b(CharSequence charSequence);

        void c(View view);

        void cancel();
    }

    static final class f extends c {

        static class a extends Handler {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private Handler f2605a;

            a(Handler handler) {
                this.f2605a = handler;
            }

            @Override // android.os.Handler
            public void dispatchMessage(Message message) {
                try {
                    this.f2605a.dispatchMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                this.f2605a.handleMessage(message);
            }
        }

        f(ToastUtils toastUtils) {
            super(toastUtils);
            if (Build.VERSION.SDK_INT == 25) {
                try {
                    Field declaredField = Toast.class.getDeclaredField("mTN");
                    declaredField.setAccessible(true);
                    Object obj = declaredField.get(this.f2598a);
                    Field declaredField2 = declaredField.getType().getDeclaredField("mHandler");
                    declaredField2.setAccessible(true);
                    declaredField2.set(obj, new a((Handler) declaredField2.get(obj)));
                } catch (Exception unused) {
                }
            }
        }

        @Override // com.blankj.utilcode.util.ToastUtils.e
        public void a(int i) {
            Toast toast = this.f2598a;
            if (toast == null) {
                return;
            }
            toast.setDuration(i);
            this.f2598a.show();
        }
    }

    public static void l() {
        y.C(new a());
    }

    private static CharSequence m(CharSequence charSequence) {
        return charSequence == null ? "toast null" : charSequence.length() == 0 ? "toast nothing" : charSequence;
    }

    public static ToastUtils n() {
        return new ToastUtils();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static e o(ToastUtils toastUtils) {
        if (!toastUtils.m && androidx.core.app.h.b(w.a()).a()) {
            if (Build.VERSION.SDK_INT < 23) {
                return new f(toastUtils);
            }
            if (!y.v()) {
                return new f(toastUtils);
            }
        }
        int i = Build.VERSION.SDK_INT;
        return i < 25 ? new g(toastUtils, 2005) : y.v() ? i >= 26 ? new g(toastUtils, 2038) : new g(toastUtils, 2002) : new d(toastUtils);
    }

    private static void p(View view, CharSequence charSequence, int i, ToastUtils toastUtils) {
        y.C(toastUtils.new b(view, charSequence, i));
    }

    private static void q(CharSequence charSequence, int i, ToastUtils toastUtils) {
        p(null, m(charSequence), i, toastUtils);
    }

    public static void r(CharSequence charSequence) {
        q(charSequence, 1, f2590a);
    }

    public static void s(String str, Object... objArr) {
        q(y.f(str, objArr), 1, f2590a);
    }

    public static void t(int i) {
        q(y.p(i), 0, f2590a);
    }

    public static void u(CharSequence charSequence) {
        q(charSequence, 0, f2590a);
    }

    public static void v(String str, Object... objArr) {
        q(y.f(str, objArr), 0, f2590a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View w(CharSequence charSequence) {
        if (!"dark".equals(this.f2592c) && !"light".equals(this.f2592c)) {
            Drawable[] drawableArr = this.l;
            if (drawableArr[0] == null && drawableArr[1] == null && drawableArr[2] == null && drawableArr[3] == null) {
                return null;
            }
        }
        View viewY = y.y(d.a.a.b.f3900a);
        TextView textView = (TextView) viewY.findViewById(R.id.message);
        if ("dark".equals(this.f2592c)) {
            ((GradientDrawable) viewY.getBackground().mutate()).setColor(Color.parseColor("#BB000000"));
            textView.setTextColor(-1);
        }
        textView.setText(charSequence);
        if (this.l[0] != null) {
            View viewFindViewById = viewY.findViewById(d.a.a.a.f3897b);
            c.g.k.t.m0(viewFindViewById, this.l[0]);
            viewFindViewById.setVisibility(0);
        }
        if (this.l[1] != null) {
            View viewFindViewById2 = viewY.findViewById(d.a.a.a.f3899d);
            c.g.k.t.m0(viewFindViewById2, this.l[1]);
            viewFindViewById2.setVisibility(0);
        }
        if (this.l[2] != null) {
            View viewFindViewById3 = viewY.findViewById(d.a.a.a.f3898c);
            c.g.k.t.m0(viewFindViewById3, this.l[2]);
            viewFindViewById3.setVisibility(0);
        }
        if (this.l[3] != null) {
            View viewFindViewById4 = viewY.findViewById(d.a.a.a.f3896a);
            c.g.k.t.m0(viewFindViewById4, this.l[3]);
            viewFindViewById4.setVisibility(0);
        }
        return viewY;
    }

    static final class g extends c {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private WindowManager f2606d;
        private WindowManager.LayoutParams e;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                g.this.cancel();
            }
        }

        g(ToastUtils toastUtils, int i) {
            super(toastUtils);
            this.e = new WindowManager.LayoutParams();
            this.f2606d = (WindowManager) w.a().getSystemService("window");
            this.e.type = i;
        }

        @Override // com.blankj.utilcode.util.ToastUtils.e
        public void a(int i) {
            if (this.f2598a == null) {
                return;
            }
            WindowManager.LayoutParams layoutParams = this.e;
            layoutParams.height = -2;
            layoutParams.width = -2;
            layoutParams.format = -3;
            layoutParams.windowAnimations = R.style.Animation.Toast;
            layoutParams.setTitle("ToastWithoutNotification");
            WindowManager.LayoutParams layoutParams2 = this.e;
            layoutParams2.flags = 152;
            layoutParams2.packageName = w.a().getPackageName();
            this.e.gravity = this.f2598a.getGravity();
            WindowManager.LayoutParams layoutParams3 = this.e;
            int i2 = layoutParams3.gravity;
            if ((i2 & 7) == 7) {
                layoutParams3.horizontalWeight = 1.0f;
            }
            if ((i2 & 112) == 112) {
                layoutParams3.verticalWeight = 1.0f;
            }
            layoutParams3.x = this.f2598a.getXOffset();
            this.e.y = this.f2598a.getYOffset();
            this.e.horizontalMargin = this.f2598a.getHorizontalMargin();
            this.e.verticalMargin = this.f2598a.getVerticalMargin();
            try {
                WindowManager windowManager = this.f2606d;
                if (windowManager != null) {
                    windowManager.addView(this.f2600c, this.e);
                }
            } catch (Exception unused) {
            }
            y.D(new a(), i == 0 ? 2000L : 3500L);
        }

        @Override // com.blankj.utilcode.util.ToastUtils.c, com.blankj.utilcode.util.ToastUtils.e
        public void cancel() {
            try {
                WindowManager windowManager = this.f2606d;
                if (windowManager != null) {
                    windowManager.removeViewImmediate(this.f2600c);
                    this.f2606d = null;
                }
            } catch (Exception unused) {
            }
            super.cancel();
        }

        g(ToastUtils toastUtils, WindowManager windowManager, int i) {
            super(toastUtils);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.e = layoutParams;
            this.f2606d = windowManager;
            layoutParams.type = i;
        }
    }
}
