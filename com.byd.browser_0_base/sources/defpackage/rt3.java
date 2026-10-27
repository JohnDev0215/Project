package defpackage;

import android.R;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import android.view.inputmethod.InputMethodManager;

/* JADX INFO: compiled from: WindowInsetsControllerCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class rt3 {
    public final e a;

    /* JADX INFO: compiled from: WindowInsetsControllerCompat.java */
    public static class b extends a {
        public b(Window window, View view) {
            super(window, view);
        }

        @Override // rt3.e
        public final boolean b() {
            return (this.a.getDecorView().getSystemUiVisibility() & 8192) != 0;
        }

        @Override // rt3.e
        public final void d(boolean z) {
            if (!z) {
                h(8192);
                return;
            }
            Window window = this.a;
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            g(8192);
        }
    }

    /* JADX INFO: compiled from: WindowInsetsControllerCompat.java */
    public static class c extends b {
        public c(Window window, View view) {
            super(window, view);
        }

        @Override // rt3.e
        public final void c(boolean z) {
            if (!z) {
                h(16);
                return;
            }
            Window window = this.a;
            window.clearFlags(134217728);
            window.addFlags(Integer.MIN_VALUE);
            g(16);
        }
    }

    /* JADX INFO: compiled from: WindowInsetsControllerCompat.java */
    public static class d extends e {
        public final WindowInsetsController a;
        public final wm2<f, WindowInsetsController.OnControllableInsetsChangedListener> b;
        public final Window c;

        public d(Window window, rt3 rt3Var) {
            WindowInsetsController insetsController = window.getInsetsController();
            this.b = new wm2<>();
            this.a = insetsController;
            this.c = window;
        }

        @Override // rt3.e
        public final void a(int i) {
            this.a.hide(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, ut3] */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // rt3.e
        public void addOnControllableInsetsChangedListener(f fVar) {
            wm2<f, WindowInsetsController.OnControllableInsetsChangedListener> wm2Var = this.b;
            if (wm2Var.containsKey(fVar)) {
                return;
            }
            ?? r1 = new WindowInsetsController.OnControllableInsetsChangedListener() { // from class: ut3
                @Override // android.view.WindowInsetsController.OnControllableInsetsChangedListener
                public final void onControllableInsetsChanged(WindowInsetsController windowInsetsController, int i) {
                    if (this.a.a == windowInsetsController) {
                        throw null;
                    }
                }
            };
            wm2Var.put(fVar, r1);
            this.a.addOnControllableInsetsChangedListener(r1);
        }

        @Override // rt3.e
        public final boolean b() {
            return (this.a.getSystemBarsAppearance() & 8) != 0;
        }

        @Override // rt3.e
        public final void c(boolean z) {
            WindowInsetsController windowInsetsController = this.a;
            Window window = this.c;
            if (z) {
                if (window != null) {
                    View decorView = window.getDecorView();
                    decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 16);
                }
                windowInsetsController.setSystemBarsAppearance(16, 16);
                return;
            }
            if (window != null) {
                View decorView2 = window.getDecorView();
                decorView2.setSystemUiVisibility(decorView2.getSystemUiVisibility() & (-17));
            }
            windowInsetsController.setSystemBarsAppearance(0, 16);
        }

        @Override // rt3.e
        public final void d(boolean z) {
            WindowInsetsController windowInsetsController = this.a;
            Window window = this.c;
            if (z) {
                if (window != null) {
                    View decorView = window.getDecorView();
                    decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 8192);
                }
                windowInsetsController.setSystemBarsAppearance(8, 8);
                return;
            }
            if (window != null) {
                View decorView2 = window.getDecorView();
                decorView2.setSystemUiVisibility(decorView2.getSystemUiVisibility() & (-8193));
            }
            windowInsetsController.setSystemBarsAppearance(0, 8);
        }

        @Override // rt3.e
        public final void e(int i) {
            this.a.setSystemBarsBehavior(i);
        }

        @Override // rt3.e
        public final void f(int i) {
            Window window = this.c;
            if (window != null && (i & 8) != 0 && Build.VERSION.SDK_INT < 33) {
                ((InputMethodManager) window.getContext().getSystemService("input_method")).isActive();
            }
            this.a.show(i);
        }

        @Override // rt3.e
        public void removeOnControllableInsetsChangedListener(f fVar) {
            WindowInsetsController.OnControllableInsetsChangedListener onControllableInsetsChangedListenerRemove = this.b.remove(fVar);
            if (onControllableInsetsChangedListenerRemove != null) {
                this.a.removeOnControllableInsetsChangedListener(onControllableInsetsChangedListenerRemove);
            }
        }
    }

    /* JADX INFO: compiled from: WindowInsetsControllerCompat.java */
    public interface f {
    }

    public rt3(Window window, View view) {
        if (Build.VERSION.SDK_INT >= 30) {
            this.a = new d(window, this);
        } else {
            this.a = new c(window, view);
        }
    }

    public void addOnControllableInsetsChangedListener(f fVar) {
        this.a.addOnControllableInsetsChangedListener(fVar);
    }

    public void removeOnControllableInsetsChangedListener(f fVar) {
        this.a.removeOnControllableInsetsChangedListener(fVar);
    }

    /* JADX INFO: compiled from: WindowInsetsControllerCompat.java */
    public static class a extends e {
        public final Window a;
        public final View b;

        public a(Window window, View view) {
            this.a = window;
            this.b = view;
        }

        @Override // rt3.e
        public final void a(int i) {
            for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                if ((i & i2) != 0) {
                    if (i2 == 1) {
                        g(4);
                    } else if (i2 == 2) {
                        g(2);
                    } else if (i2 == 8) {
                        Window window = this.a;
                        ((InputMethodManager) window.getContext().getSystemService("input_method")).hideSoftInputFromWindow(window.getDecorView().getWindowToken(), 0);
                    }
                }
            }
        }

        @Override // rt3.e
        public final void e(int i) {
            if (i == 0) {
                h(6144);
                return;
            }
            if (i == 1) {
                h(4096);
                g(2048);
            } else {
                if (i != 2) {
                    return;
                }
                h(2048);
                g(4096);
            }
        }

        @Override // rt3.e
        public final void f(int i) {
            for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                if ((i & i2) != 0) {
                    Window window = this.a;
                    if (i2 == 1) {
                        h(4);
                        window.clearFlags(1024);
                    } else if (i2 == 2) {
                        h(2);
                    } else if (i2 == 8) {
                        View viewFindViewById = this.b;
                        if (viewFindViewById.isInEditMode() || viewFindViewById.onCheckIsTextEditor()) {
                            viewFindViewById.requestFocus();
                        } else {
                            viewFindViewById = window.getCurrentFocus();
                        }
                        if (viewFindViewById == null) {
                            viewFindViewById = window.findViewById(R.id.content);
                        }
                        if (viewFindViewById != null && viewFindViewById.hasWindowFocus()) {
                            viewFindViewById.post(new qt3(viewFindViewById, 0));
                        }
                    }
                }
            }
        }

        public final void g(int i) {
            View decorView = this.a.getDecorView();
            decorView.setSystemUiVisibility(i | decorView.getSystemUiVisibility());
        }

        public final void h(int i) {
            View decorView = this.a.getDecorView();
            decorView.setSystemUiVisibility((~i) & decorView.getSystemUiVisibility());
        }

        @Override // rt3.e
        public void addOnControllableInsetsChangedListener(f fVar) {
        }

        @Override // rt3.e
        public void removeOnControllableInsetsChangedListener(f fVar) {
        }
    }

    /* JADX INFO: compiled from: WindowInsetsControllerCompat.java */
    public static class e {
        public boolean b() {
            return false;
        }

        public void a(int i) {
        }

        public void addOnControllableInsetsChangedListener(f fVar) {
        }

        public void c(boolean z) {
        }

        public void d(boolean z) {
        }

        public void e(int i) {
        }

        public void f(int i) {
        }

        public void removeOnControllableInsetsChangedListener(f fVar) {
        }
    }
}
