package defpackage;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: renamed from: a6, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0474a6 {
    public final ArrayList a = new ArrayList();
    public ArrayList b = null;
    public final ArrayList c = new ArrayList();
    public final List d = Collections.unmodifiableList(this.a);
    public int e = 2;
    public int f = 2;
    public final /* synthetic */ RecyclerView g;

    public C0474a6(RecyclerView recyclerView) {
        this.g = recyclerView;
    }

    public void a() {
        this.a.clear();
        int size = this.c.size() - 1;
        if (size >= 0) {
            c(size);
            throw null;
        }
        this.c.clear();
        if (RecyclerView.K0) {
            C1472s5 c1472s5 = this.g.q0;
            int[] iArr = c1472s5.c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            c1472s5.d = 0;
        }
    }

    public int b(int i) {
        if (i >= 0 && i < this.g.r0.a()) {
            RecyclerView recyclerView = this.g;
            return !recyclerView.r0.g ? i : recyclerView.u.f(i, 0);
        }
        throw new IndexOutOfBoundsException("invalid position " + i + ". State item count is " + this.g.r0.a() + this.g.m());
    }

    public void c(int i) {
        RecyclerView.c();
        throw null;
    }

    public void d(int i, boolean z, long j) {
        if (i < 0 || i >= this.g.r0.a()) {
            throw new IndexOutOfBoundsException("Invalid item position " + i + "(" + i + "). Item count:" + this.g.r0.a() + this.g.m());
        }
        boolean z2 = this.g.r0.g;
        if (this.a.size() > 0) {
            throw null;
        }
        if (!z) {
            C0752f5 c0752f5 = this.g.v;
            if (c0752f5.c.size() > 0) {
                View view = (View) c0752f5.c.get(0);
                Objects.requireNonNull((L5) c0752f5.a);
                RecyclerView.p(view);
                throw null;
            }
        }
        if (this.c.size() > 0) {
            throw null;
        }
        int iF = this.g.u.f(i, 0);
        if (iF >= 0) {
            Objects.requireNonNull(this.g);
            throw null;
        }
        throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + i + "(offset:" + iF + ").state:" + this.g.r0.a() + this.g.m());
    }

    public void e() {
        W5 w5 = this.g.B;
        this.f = this.e + (w5 != null ? w5.k : 0);
        int size = this.c.size() - 1;
        if (size < 0 || this.c.size() <= this.f) {
            return;
        }
        c(size);
        throw null;
    }
}
