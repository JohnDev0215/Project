package p;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.google.android.material.chip.Chip;

/* JADX INFO: loaded from: classes.dex */
public final class ls extends ViewOutlineProvider {
    public final /* synthetic */ Chip a;

    public ls(Chip chip) {
        this.a = chip;
    }

    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        os osVar = this.a.m;
        if (osVar != null) {
            osVar.getOutline(outline);
        } else {
            outline.setAlpha(0.0f);
        }
    }
}
