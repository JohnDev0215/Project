package s3;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import k3.C1648i0;

/* JADX INFO: loaded from: classes.dex */
public abstract class U {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final List f22218t = Collections.emptyList();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f22219a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public WeakReference f22220b;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f22226j;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public RecyclerView f22232r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public AbstractC2405y f22233s;
    public int c = -1;
    public int d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f22221e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f22222f = -1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f22223g = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public U f22224h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public U f22225i = null;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final ArrayList f22227k = null;
    public final List l = null;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f22228m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public C1648i0 f22229n = null;
    public boolean o = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f22230p = 0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f22231q = -1;

    public U(View view) {
        if (view == null) {
            throw new IllegalArgumentException("itemView may not be null");
        }
        this.f22219a = view;
    }

    public final void a(int i7) {
        this.f22226j = i7 | this.f22226j;
    }

    public final int b() {
        int i7 = this.f22223g;
        return i7 == -1 ? this.c : i7;
    }

    public final List c() {
        ArrayList arrayList;
        return ((this.f22226j & 1024) != 0 || (arrayList = this.f22227k) == null || arrayList.size() == 0) ? f22218t : this.l;
    }

    public final boolean d(int i7) {
        return (this.f22226j & i7) != 0;
    }

    public final boolean e() {
        View view = this.f22219a;
        return (view.getParent() == null || view.getParent() == this.f22232r) ? false : true;
    }

    public final boolean f() {
        return (this.f22226j & 1) != 0;
    }

    public final boolean g() {
        return (this.f22226j & 4) != 0;
    }

    public final boolean h() {
        if ((this.f22226j & 16) == 0) {
            int[] iArr = F1.H.f2303a;
            if (!this.f22219a.hasTransientState()) {
                return true;
            }
        }
        return false;
    }

    public final boolean i() {
        return (this.f22226j & 8) != 0;
    }

    public final boolean j() {
        return this.f22229n != null;
    }

    public final boolean k() {
        return (this.f22226j & 256) != 0;
    }

    public final boolean l() {
        return (this.f22226j & 2) != 0;
    }

    public final void m(int i7, boolean z6) {
        if (this.d == -1) {
            this.d = this.c;
        }
        if (this.f22223g == -1) {
            this.f22223g = this.c;
        }
        if (z6) {
            this.f22223g += i7;
        }
        this.c += i7;
        View view = this.f22219a;
        if (view.getLayoutParams() != null) {
            ((C2368G) view.getLayoutParams()).c = true;
        }
    }

    public final void n() {
        this.f22226j = 0;
        this.c = -1;
        this.d = -1;
        this.f22221e = -1L;
        this.f22223g = -1;
        this.f22228m = 0;
        this.f22224h = null;
        this.f22225i = null;
        ArrayList arrayList = this.f22227k;
        if (arrayList != null) {
            arrayList.clear();
        }
        this.f22226j &= -1025;
        this.f22230p = 0;
        this.f22231q = -1;
        RecyclerView.g(this);
    }

    public final void o(boolean z6) {
        int i7 = this.f22228m;
        int i10 = z6 ? i7 - 1 : i7 + 1;
        this.f22228m = i10;
        if (i10 < 0) {
            this.f22228m = 0;
            h6.n.j("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
            return;
        }
        if (!z6 && i10 == 1) {
            this.f22226j |= 16;
        } else if (z6 && i10 == 0) {
            this.f22226j &= -17;
        }
    }

    public final boolean p() {
        return (this.f22226j & 128) != 0;
    }

    public final boolean q() {
        return (this.f22226j & 32) != 0;
    }

    public final String toString() {
        StringBuilder sbL = A7.k.l(getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName(), "{");
        sbL.append(Integer.toHexString(hashCode()));
        sbL.append(" position=");
        sbL.append(this.c);
        sbL.append(" id=");
        sbL.append(this.f22221e);
        sbL.append(", oldPos=");
        sbL.append(this.d);
        sbL.append(", pLpos:");
        sbL.append(this.f22223g);
        StringBuilder sb = new StringBuilder(sbL.toString());
        if (j()) {
            sb.append(" scrap ");
            sb.append(this.o ? "[changeScrap]" : "[attachedScrap]");
        }
        if (g()) {
            sb.append(" invalid");
        }
        if (!f()) {
            sb.append(" unbound");
        }
        if ((this.f22226j & 2) != 0) {
            sb.append(" update");
        }
        if (i()) {
            sb.append(" removed");
        }
        if (p()) {
            sb.append(" ignored");
        }
        if (k()) {
            sb.append(" tmpDetached");
        }
        if (!h()) {
            sb.append(" not recyclable(" + this.f22228m + ")");
        }
        if ((this.f22226j & 512) != 0 || g()) {
            sb.append(" undefined adapter position");
        }
        if (this.f22219a.getParent() == null) {
            sb.append(" no parent");
        }
        sb.append("}");
        return sb.toString();
    }
}
