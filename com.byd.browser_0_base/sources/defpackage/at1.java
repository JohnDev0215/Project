package defpackage;

import android.graphics.Rect;
import defpackage.p72;
import org.chromium.chrome.browser.toolbar.optional_button.b;

/* JADX INFO: compiled from: OptionalButtonCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class at1 implements p72.c {
    public final /* synthetic */ b a;

    public at1(b bVar) {
        this.a = bVar;
    }

    @Override // p72.c
    public final float a(Rect rect) {
        return this.a.b.getResources().getDisplayMetrics().density * 20.0f;
    }

    @Override // p72.c
    public final float b(Rect rect) {
        return this.a.b.getResources().getDisplayMetrics().density * 20.0f;
    }
}
