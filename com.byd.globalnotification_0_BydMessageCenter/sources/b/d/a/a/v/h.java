package b.d.a.a.v;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class h extends a.h.k.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ f f2095d;

    public h(f fVar) {
        this.f2095d = fVar;
    }

    @Override // a.h.k.a
    public void d(View view, a.h.k.x.b bVar) {
        this.f807a.onInitializeAccessibilityNodeInfo(view, bVar.f856a);
        int visibility = this.f2095d.f0.getVisibility();
        bVar.f856a.setHintText(this.f2095d.q().getString(visibility == 0 ? b.d.a.a.i.mtrl_picker_toggle_to_year_selection : b.d.a.a.i.mtrl_picker_toggle_to_day_selection));
    }
}
