package b.d.a.a.z;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.material.internal.CheckableImageButton;

/* JADX INFO: loaded from: classes.dex */
public class a extends a.h.k.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ CheckableImageButton f2160d;

    public a(CheckableImageButton checkableImageButton) {
        this.f2160d = checkableImageButton;
    }

    @Override // a.h.k.a
    public void c(View view, AccessibilityEvent accessibilityEvent) {
        this.f807a.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setChecked(this.f2160d.isChecked());
    }

    @Override // a.h.k.a
    public void d(View view, a.h.k.x.b bVar) {
        this.f807a.onInitializeAccessibilityNodeInfo(view, bVar.f856a);
        bVar.f856a.setCheckable(this.f2160d.f2456d);
        bVar.f856a.setChecked(this.f2160d.isChecked());
    }
}
