package f0;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.window.SplashScreenView;
import f0.c;
import java.util.Objects;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: SplashScreen.kt */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"CustomSplashScreen"})
public final class c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f6317b = new a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f6318a;

    /* JADX INFO: compiled from: SplashScreen.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }

        public final c a(Activity activity) {
            l.e(activity, "<this>");
            c cVar = new c(activity, null);
            cVar.b();
            return cVar;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SplashScreen.kt */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Activity f6319a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f6320b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Integer f6321c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Integer f6322d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Drawable f6323e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f6324f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private d f6325g;

        /* JADX INFO: compiled from: SplashScreen.kt */
        public static final class a implements ViewTreeObserver.OnPreDrawListener {

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            final /* synthetic */ View f6327g;

            a(View view) {
                this.f6327g = view;
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (b.this.d().a()) {
                    return false;
                }
                this.f6327g.getViewTreeObserver().removeOnPreDrawListener(this);
                b.b(b.this);
                return true;
            }
        }

        public b(Activity activity) {
            l.e(activity, "activity");
            this.f6319a = activity;
            this.f6325g = new d() { // from class: f0.d
                @Override // f0.c.d
                public final boolean a() {
                    return c.b.i();
                }
            };
        }

        public static final /* synthetic */ e b(b bVar) {
            Objects.requireNonNull(bVar);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean i() {
            return false;
        }

        public final Activity c() {
            return this.f6319a;
        }

        public final d d() {
            return this.f6325g;
        }

        public void e() {
            TypedValue typedValue = new TypedValue();
            Resources.Theme currentTheme = this.f6319a.getTheme();
            if (currentTheme.resolveAttribute(f0.a.windowSplashScreenBackground, typedValue, true)) {
                this.f6321c = Integer.valueOf(typedValue.resourceId);
                this.f6322d = Integer.valueOf(typedValue.data);
            }
            if (currentTheme.resolveAttribute(f0.a.windowSplashScreenAnimatedIcon, typedValue, true)) {
                this.f6323e = currentTheme.getDrawable(typedValue.resourceId);
            }
            if (currentTheme.resolveAttribute(f0.a.splashScreenIconSize, typedValue, true)) {
                this.f6324f = typedValue.resourceId == f0.b.splashscreen_icon_size_with_background;
            }
            l.d(currentTheme, "currentTheme");
            g(currentTheme, typedValue);
        }

        public void f(d keepOnScreenCondition) {
            l.e(keepOnScreenCondition, "keepOnScreenCondition");
            this.f6325g = keepOnScreenCondition;
            View viewFindViewById = this.f6319a.findViewById(R.id.content);
            viewFindViewById.getViewTreeObserver().addOnPreDrawListener(new a(viewFindViewById));
        }

        protected final void g(Resources.Theme currentTheme, TypedValue typedValue) {
            l.e(currentTheme, "currentTheme");
            l.e(typedValue, "typedValue");
            if (currentTheme.resolveAttribute(f0.a.postSplashScreenTheme, typedValue, true)) {
                int i7 = typedValue.resourceId;
                this.f6320b = i7;
                if (i7 != 0) {
                    this.f6319a.setTheme(i7);
                }
            }
        }

        public final void h(d dVar) {
            l.e(dVar, "<set-?>");
            this.f6325g = dVar;
        }
    }

    /* JADX INFO: renamed from: f0.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SplashScreen.kt */
    private static final class C0073c extends b {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private ViewTreeObserver.OnPreDrawListener f6328h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private boolean f6329i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private final ViewGroup.OnHierarchyChangeListener f6330j;

        /* JADX INFO: renamed from: f0.c$c$a */
        /* JADX INFO: compiled from: SplashScreen.kt */
        public static final class a implements ViewGroup.OnHierarchyChangeListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Activity f6332b;

            a(Activity activity) {
                this.f6332b = activity;
            }

            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public void onChildViewAdded(View view, View view2) {
                if (view2 instanceof SplashScreenView) {
                    C0073c c0073c = C0073c.this;
                    c0073c.k(c0073c.j((SplashScreenView) view2));
                    ((ViewGroup) this.f6332b.getWindow().getDecorView()).setOnHierarchyChangeListener(null);
                }
            }

            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public void onChildViewRemoved(View view, View view2) {
            }
        }

        /* JADX INFO: renamed from: f0.c$c$b */
        /* JADX INFO: compiled from: SplashScreen.kt */
        public static final class b implements ViewTreeObserver.OnPreDrawListener {

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            final /* synthetic */ View f6334g;

            b(View view) {
                this.f6334g = view;
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (C0073c.this.d().a()) {
                    return false;
                }
                this.f6334g.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0073c(Activity activity) {
            super(activity);
            l.e(activity, "activity");
            this.f6329i = true;
            this.f6330j = new a(activity);
        }

        @Override // f0.c.b
        public void e() {
            Resources.Theme theme = c().getTheme();
            l.d(theme, "activity.theme");
            g(theme, new TypedValue());
            ((ViewGroup) c().getWindow().getDecorView()).setOnHierarchyChangeListener(this.f6330j);
        }

        @Override // f0.c.b
        public void f(d keepOnScreenCondition) {
            l.e(keepOnScreenCondition, "keepOnScreenCondition");
            h(keepOnScreenCondition);
            View viewFindViewById = c().findViewById(R.id.content);
            ViewTreeObserver viewTreeObserver = viewFindViewById.getViewTreeObserver();
            if (this.f6328h != null && viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.f6328h);
            }
            b bVar = new b(viewFindViewById);
            this.f6328h = bVar;
            viewTreeObserver.addOnPreDrawListener(bVar);
        }

        public final boolean j(SplashScreenView child) {
            l.e(child, "child");
            WindowInsets windowInsetsBuild = new WindowInsets.Builder().build();
            l.d(windowInsetsBuild, "Builder().build()");
            Rect rect = new Rect(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
            return (windowInsetsBuild == child.getRootView().computeSystemWindowInsets(windowInsetsBuild, rect) && rect.isEmpty()) ? false : true;
        }

        public final void k(boolean z6) {
            this.f6329i = z6;
        }
    }

    /* JADX INFO: compiled from: SplashScreen.kt */
    public interface d {
        boolean a();
    }

    private c(Activity activity) {
        this.f6318a = Build.VERSION.SDK_INT >= 31 ? new C0073c(activity) : new b(activity);
    }

    public /* synthetic */ c(Activity activity, g gVar) {
        this(activity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void b() {
        this.f6318a.e();
    }

    public static final c c(Activity activity) {
        return f6317b.a(activity);
    }

    public final void d(d condition) {
        l.e(condition, "condition");
        this.f6318a.f(condition);
    }
}
