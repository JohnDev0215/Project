package defpackage;

import org.chromium.components.browser_ui.photo_picker.h;

/* JADX INFO: compiled from: PhotoPickerDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class x02 extends xr1 {
    public final /* synthetic */ h d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x02(h hVar) {
        super(true);
        this.d = hVar;
    }

    @Override // defpackage.xr1
    public final void a() {
        h hVar = this.d;
        if (hVar.k.G.b()) {
            return;
        }
        b(false);
        hVar.g.c();
    }
}
