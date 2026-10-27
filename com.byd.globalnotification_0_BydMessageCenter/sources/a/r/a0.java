package a.r;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class a0 extends l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ViewGroup f1277a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f1278b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f1279c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ b0 f1280d;

    public a0(b0 b0Var, ViewGroup viewGroup, View view, View view2) {
        this.f1280d = b0Var;
        this.f1277a = viewGroup;
        this.f1278b = view;
        this.f1279c = view2;
    }

    @Override // a.r.i.d
    public void c(i iVar) {
        this.f1279c.setTag(f.save_overlay_view, null);
        this.f1277a.getOverlay().remove(this.f1278b);
        iVar.w(this);
    }

    @Override // a.r.l, a.r.i.d
    public void d(i iVar) {
        this.f1277a.getOverlay().remove(this.f1278b);
    }

    @Override // a.r.l, a.r.i.d
    public void e(i iVar) {
        if (this.f1278b.getParent() == null) {
            this.f1277a.getOverlay().add(this.f1278b);
        } else {
            this.f1280d.d();
        }
    }
}
