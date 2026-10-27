package b.d.a.a.j0;

import a.h.k.x.b;
import android.view.View;
import com.google.android.material.timepicker.ClockFaceView;

/* JADX INFO: loaded from: classes.dex */
public class b extends a.h.k.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ ClockFaceView f1957d;

    public b(ClockFaceView clockFaceView) {
        this.f1957d = clockFaceView;
    }

    @Override // a.h.k.a
    public void d(View view, a.h.k.x.b bVar) {
        this.f807a.onInitializeAccessibilityNodeInfo(view, bVar.f856a);
        int iIntValue = ((Integer) view.getTag(b.d.a.a.f.material_value_index)).intValue();
        if (iIntValue > 0) {
            bVar.f856a.setTraversalAfter(this.f1957d.A.get(iIntValue - 1));
        }
        bVar.i(b.c.a(0, 1, iIntValue, 1, false, view.isSelected()));
    }
}
