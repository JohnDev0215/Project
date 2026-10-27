package defpackage;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.material.internal.CheckableImageButton;

/* JADX INFO: renamed from: Dk, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0064Dk extends AbstractC0750f3 {
    public final /* synthetic */ CheckableImageButton c;

    public C0064Dk(CheckableImageButton checkableImageButton) {
        this.c = checkableImageButton;
    }

    @Override // defpackage.AbstractC0750f3
    public void a(View view, AccessibilityEvent accessibilityEvent) {
        AbstractC0750f3.b.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setChecked(this.c.isChecked());
    }

    @Override // defpackage.AbstractC0750f3
    public void b(View view, C1525t3 c1525t3) {
        super.b(view, c1525t3);
        c1525t3.a.setCheckable(true);
        c1525t3.a.setChecked(this.c.isChecked());
    }
}
