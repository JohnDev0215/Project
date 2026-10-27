package defpackage;

import android.annotation.TargetApi;
import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.a;

/* JADX INFO: compiled from: Chip.java */
/* JADX INFO: loaded from: classes.dex */
public final class go extends ViewOutlineProvider {
    public final /* synthetic */ Chip a;

    public go(Chip chip) {
        this.a = chip;
    }

    @Override // android.view.ViewOutlineProvider
    @TargetApi(21)
    public final void getOutline(View view, Outline outline) {
        a aVar = this.a.i;
        if (aVar != null) {
            aVar.getOutline(outline);
        } else {
            outline.setAlpha(0.0f);
        }
    }
}
