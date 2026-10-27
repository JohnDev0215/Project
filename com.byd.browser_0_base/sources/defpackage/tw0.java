package defpackage;

import android.graphics.Bitmap;
import android.view.View;
import org.chromium.components.content_creation.notes.models.Background;

/* JADX INFO: compiled from: ImageBackground.java */
/* JADX INFO: loaded from: classes2.dex */
public final class tw0 implements Background {
    public final String a;
    public Bitmap b;

    public tw0(String str) {
        this.a = str;
    }

    @Override // org.chromium.components.content_creation.notes.models.Background
    public final void a(View view, float f) {
        if (view == null) {
            return;
        }
        qc2 qc2Var = new qc2(view.getContext().getResources(), this.b);
        qc2Var.b(f);
        if (qc2Var.c != 119) {
            qc2Var.c = 119;
            qc2Var.j = true;
            qc2Var.invalidateSelf();
        }
        view.setBackground(qc2Var);
    }
}
