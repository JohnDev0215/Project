package androidx.recyclerview.widget;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class l extends androidx.core.view.a {
    final RecyclerView d;
    private final a e;

    /* JADX INFO: compiled from: caradapter-bug */
    public static class a extends androidx.core.view.a {
        final l d;
        private Map<View, androidx.core.view.a> e = new WeakHashMap();

        public a(@NonNull l lVar) {
            this.d = lVar;
        }

        @Override // androidx.core.view.a
        public boolean a(@NonNull View view, @NonNull AccessibilityEvent accessibilityEvent) {
            androidx.core.view.a aVar = this.e.get(view);
            return aVar != null ? aVar.a(view, accessibilityEvent) : super.a(view, accessibilityEvent);
        }

        @Override // androidx.core.view.a
        @Nullable
        public androidx.core.view.accessibility.c b(@NonNull View view) {
            androidx.core.view.a aVar = this.e.get(view);
            return aVar != null ? aVar.b(view) : super.b(view);
        }

        @Override // androidx.core.view.a
        public void f(@NonNull View view, @NonNull AccessibilityEvent accessibilityEvent) {
            androidx.core.view.a aVar = this.e.get(view);
            if (aVar != null) {
                aVar.f(view, accessibilityEvent);
            } else {
                super.f(view, accessibilityEvent);
            }
        }

        @Override // androidx.core.view.a
        public void g(View view, androidx.core.view.accessibility.b bVar) {
            if (this.d.o() || this.d.d.k0() == null) {
                super.g(view, bVar);
                return;
            }
            this.d.d.k0().M0(view, bVar);
            androidx.core.view.a aVar = this.e.get(view);
            if (aVar != null) {
                aVar.g(view, bVar);
            } else {
                super.g(view, bVar);
            }
        }

        @Override // androidx.core.view.a
        public void h(@NonNull View view, @NonNull AccessibilityEvent accessibilityEvent) {
            androidx.core.view.a aVar = this.e.get(view);
            if (aVar != null) {
                aVar.h(view, accessibilityEvent);
            } else {
                super.h(view, accessibilityEvent);
            }
        }

        @Override // androidx.core.view.a
        public boolean i(@NonNull ViewGroup viewGroup, @NonNull View view, @NonNull AccessibilityEvent accessibilityEvent) {
            androidx.core.view.a aVar = this.e.get(viewGroup);
            return aVar != null ? aVar.i(viewGroup, view, accessibilityEvent) : super.i(viewGroup, view, accessibilityEvent);
        }

        @Override // androidx.core.view.a
        public boolean j(View view, int i, Bundle bundle) {
            if (this.d.o() || this.d.d.k0() == null) {
                return super.j(view, i, bundle);
            }
            androidx.core.view.a aVar = this.e.get(view);
            if (aVar != null) {
                if (aVar.j(view, i, bundle)) {
                    return true;
                }
            } else if (super.j(view, i, bundle)) {
                return true;
            }
            return this.d.d.k0().f1(view, i, bundle);
        }

        @Override // androidx.core.view.a
        public void l(@NonNull View view, int i) {
            androidx.core.view.a aVar = this.e.get(view);
            if (aVar != null) {
                aVar.l(view, i);
            } else {
                super.l(view, i);
            }
        }

        @Override // androidx.core.view.a
        public void m(@NonNull View view, @NonNull AccessibilityEvent accessibilityEvent) {
            androidx.core.view.a aVar = this.e.get(view);
            if (aVar != null) {
                aVar.m(view, accessibilityEvent);
            } else {
                super.m(view, accessibilityEvent);
            }
        }

        androidx.core.view.a n(View view) {
            return this.e.remove(view);
        }

        void o(View view) {
            androidx.core.view.a aVarG = ViewCompat.g(view);
            if (aVarG == null || aVarG == this) {
                return;
            }
            this.e.put(view, aVarG);
        }
    }

    public l(@NonNull RecyclerView recyclerView) {
        this.d = recyclerView;
        androidx.core.view.a aVarN = n();
        if (aVarN == null || !(aVarN instanceof a)) {
            this.e = new a(this);
        } else {
            this.e = (a) aVarN;
        }
    }

    @Override // androidx.core.view.a
    public void f(View view, AccessibilityEvent accessibilityEvent) {
        super.f(view, accessibilityEvent);
        if (!(view instanceof RecyclerView) || o()) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView.k0() != null) {
            recyclerView.k0().I0(accessibilityEvent);
        }
    }

    @Override // androidx.core.view.a
    public void g(View view, androidx.core.view.accessibility.b bVar) {
        super.g(view, bVar);
        if (o() || this.d.k0() == null) {
            return;
        }
        this.d.k0().K0(bVar);
    }

    @Override // androidx.core.view.a
    public boolean j(View view, int i, Bundle bundle) {
        if (super.j(view, i, bundle)) {
            return true;
        }
        if (o() || this.d.k0() == null) {
            return false;
        }
        return this.d.k0().d1(i, bundle);
    }

    @NonNull
    public androidx.core.view.a n() {
        return this.e;
    }

    boolean o() {
        return this.d.p0();
    }
}
