package defpackage;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.base.TraceEvent;
import org.chromium.ui.modelutil.m;

/* JADX INFO: compiled from: OmniboxSuggestionsDropdownAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class tr1 extends m {
    public int j;
    public RecyclerView.m k;
    public int l;
    public int m;

    public tr1(ya1 ya1Var) {
        super(ya1Var);
        this.j = -1;
    }

    @Override // org.chromium.ui.modelutil.m
    public final View D(int i, ViewGroup viewGroup) {
        TraceEvent traceEventH0 = TraceEvent.H0("OmniboxSuggestionsList.CreateView", "type:" + i);
        try {
            xa3 xa3Var = new xa3("Android.Omnibox.SuggestionView.CreateTime2", 2);
            xa3Var.g = SystemClock.currentThreadTimeMillis();
            try {
                View viewD = super.D(i, viewGroup);
                xa3Var.close();
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return viewD;
            } catch (Throwable th) {
                try {
                    xa3Var.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        } catch (Throwable th2) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused2) {
                }
            }
            throw th2;
        }
    }

    @Override // org.chromium.ui.modelutil.m, androidx.recyclerview.widget.RecyclerView.e
    /* JADX INFO: renamed from: E */
    public final void t(m.b bVar, int i) {
        this.m++;
        super.t(bVar, i);
    }

    @Override // org.chromium.ui.modelutil.m, androidx.recyclerview.widget.RecyclerView.e
    /* JADX INFO: renamed from: F */
    public final m.b v(int i, RecyclerView recyclerView) {
        this.l++;
        return super.v(i, recyclerView);
    }

    @Override // org.chromium.ui.modelutil.m, androidx.recyclerview.widget.RecyclerView.e
    /* JADX INFO: renamed from: G */
    public final void z(m.b bVar) {
        bVar.u(null);
        View view = bVar.e;
        if (view == null) {
            return;
        }
        view.setSelected(false);
    }

    public final boolean I(int i) {
        if (this.k == null) {
            return false;
        }
        if (i != -1 && (i < 0 || i >= k())) {
            return false;
        }
        View viewU = this.k.u(this.j);
        if (viewU != null) {
            viewU.setSelected(false);
        }
        this.j = i;
        this.k.v0(i);
        View viewU2 = this.k.u(i);
        if (viewU2 != null) {
            viewU2.setSelected(true);
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void s(RecyclerView recyclerView) {
        this.k = recyclerView.getLayoutManager();
        this.j = -1;
    }
}
