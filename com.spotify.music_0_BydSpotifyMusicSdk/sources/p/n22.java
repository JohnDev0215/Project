package p;

import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class n22 {
    public static final List s = Collections.emptyList();
    public final View a;
    public WeakReference b;
    public int j;
    public RecyclerView r;
    public int c = -1;
    public int d = -1;
    public long e = -1;
    public int f = -1;
    public int g = -1;
    public n22 h = null;
    public n22 i = null;
    public ArrayList k = null;
    public List l = null;
    public int m = 0;
    public f22 n = null;
    public boolean o = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f157p = 0;
    public int q = -1;

    public n22(View view) {
        if (view == null) {
            throw new IllegalArgumentException("itemView may not be null");
        }
        this.a = view;
    }

    public final void a(Object obj) {
        if (obj == null) {
            b(1024);
            return;
        }
        if ((1024 & this.j) == 0) {
            if (this.k == null) {
                ArrayList arrayList = new ArrayList();
                this.k = arrayList;
                this.l = Collections.unmodifiableList(arrayList);
            }
            this.k.add(obj);
        }
    }

    public final void b(int i) {
        this.j = i | this.j;
    }

    public final int c() {
        int i = this.g;
        return i == -1 ? this.c : i;
    }

    public final List d() {
        ArrayList arrayList;
        return ((this.j & 1024) != 0 || (arrayList = this.k) == null || arrayList.size() == 0) ? s : this.l;
    }

    public final boolean e(int i) {
        return (i & this.j) != 0;
    }

    public final boolean f() {
        View view = this.a;
        return (view.getParent() == null || view.getParent() == this.r) ? false : true;
    }

    public final boolean g() {
        return (this.j & 1) != 0;
    }

    public final boolean h() {
        return (this.j & 4) != 0;
    }

    public final boolean i() {
        if ((this.j & 16) == 0) {
            WeakHashMap weakHashMap = zt2.a;
            if (!it2.i(this.a)) {
                return true;
            }
        }
        return false;
    }

    public final boolean j() {
        return (this.j & 8) != 0;
    }

    public final boolean k() {
        return this.n != null;
    }

    public final boolean l() {
        return (this.j & 256) != 0;
    }

    public final boolean m() {
        return (this.j & 2) != 0;
    }

    public final void n(int i, boolean z) {
        if (this.d == -1) {
            this.d = this.c;
        }
        if (this.g == -1) {
            this.g = this.c;
        }
        if (z) {
            this.g += i;
        }
        this.c += i;
        View view = this.a;
        if (view.getLayoutParams() != null) {
            ((z12) view.getLayoutParams()).c = true;
        }
    }

    public final void o() {
        this.j = 0;
        this.c = -1;
        this.d = -1;
        this.e = -1L;
        this.g = -1;
        this.m = 0;
        this.h = null;
        this.i = null;
        ArrayList arrayList = this.k;
        if (arrayList != null) {
            arrayList.clear();
        }
        this.j &= -1025;
        this.f157p = 0;
        this.q = -1;
        RecyclerView.j(this);
    }

    public final void p(boolean z) {
        int i = this.m;
        int i2 = z ? i - 1 : i + 1;
        this.m = i2;
        if (i2 < 0) {
            this.m = 0;
            Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
            return;
        }
        if (!z && i2 == 1) {
            this.j |= 16;
        } else if (z && i2 == 0) {
            this.j &= -17;
        }
    }

    public final boolean q() {
        return (this.j & 128) != 0;
    }

    public final boolean r() {
        return (this.j & 32) != 0;
    }

    public final String toString() {
        StringBuilder sbF = hf2.f(getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName(), "{");
        sbF.append(Integer.toHexString(hashCode()));
        sbF.append(" position=");
        sbF.append(this.c);
        sbF.append(" id=");
        sbF.append(this.e);
        sbF.append(", oldPos=");
        sbF.append(this.d);
        sbF.append(", pLpos:");
        sbF.append(this.g);
        StringBuilder sb = new StringBuilder(sbF.toString());
        if (k()) {
            sb.append(" scrap ");
            sb.append(this.o ? "[changeScrap]" : "[attachedScrap]");
        }
        if (h()) {
            sb.append(" invalid");
        }
        if (!g()) {
            sb.append(" unbound");
        }
        if ((this.j & 2) != 0) {
            sb.append(" update");
        }
        if (j()) {
            sb.append(" removed");
        }
        if (q()) {
            sb.append(" ignored");
        }
        if (l()) {
            sb.append(" tmpDetached");
        }
        if (!i()) {
            sb.append(" not recyclable(" + this.m + ")");
        }
        if ((this.j & 512) != 0 || h()) {
            sb.append(" undefined adapter position");
        }
        if (this.a.getParent() == null) {
            sb.append(" no parent");
        }
        sb.append("}");
        return sb.toString();
    }
}
