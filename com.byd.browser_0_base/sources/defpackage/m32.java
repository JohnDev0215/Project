package defpackage;

import android.os.Bundle;
import android.view.View;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.preference.e;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.x;

/* JADX INFO: compiled from: PreferenceRecyclerViewAccessibilityDelegate.java */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class m32 extends x {
    public final RecyclerView f;
    public final x.a g;
    public final a h;

    /* JADX INFO: compiled from: PreferenceRecyclerViewAccessibilityDelegate.java */
    public class a extends C0214v {
        public a() {
        }

        @Override // defpackage.C0214v
        public final void d(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            m32 m32Var = m32.this;
            m32Var.g.d(view, accessibilityNodeInfoCompat);
            RecyclerView recyclerView = m32Var.f;
            recyclerView.getClass();
            int I = RecyclerView.I(view);
            RecyclerView.e adapter = recyclerView.getAdapter();
            if (adapter instanceof e) {
                ((e) adapter).F(I);
            }
        }

        @Override // defpackage.C0214v
        public final boolean g(View view, int i, Bundle bundle) {
            return m32.this.g.g(view, i, bundle);
        }
    }

    public m32(RecyclerView recyclerView) {
        super(recyclerView);
        this.g = this.e;
        this.h = new a();
        this.f = recyclerView;
    }

    @Override // androidx.recyclerview.widget.x
    public final C0214v j() {
        return this.h;
    }
}
