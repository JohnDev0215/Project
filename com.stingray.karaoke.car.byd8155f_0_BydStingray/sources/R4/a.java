package R4;

import android.graphics.Typeface;
import com.google.android.material.chip.Chip;

/* JADX INFO: loaded from: classes.dex */
public final class a extends com.bumptech.glide.d {
    public final /* synthetic */ int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f7426e;

    public /* synthetic */ a(Object obj, int i7) {
        this.d = i7;
        this.f7426e = obj;
    }

    private final void P(int i7) {
    }

    @Override // com.bumptech.glide.d
    public final void I(int i7) {
        switch (this.d) {
            case 0:
                break;
            default:
                U4.i iVar = (U4.i) this.f7426e;
                iVar.d = true;
                U4.h hVar = (U4.h) iVar.f9073e.get();
                if (hVar != null) {
                    e eVar = (e) hVar;
                    eVar.s();
                    eVar.invalidateSelf();
                }
                break;
        }
    }

    @Override // com.bumptech.glide.d
    public final void J(Typeface typeface, boolean z6) {
        switch (this.d) {
            case 0:
                Chip chip = (Chip) this.f7426e;
                e eVar = chip.f12824p;
                chip.setText(eVar.f7446L0 ? eVar.f7449N : chip.getText());
                chip.requestLayout();
                chip.invalidate();
                break;
            default:
                if (!z6) {
                    U4.i iVar = (U4.i) this.f7426e;
                    iVar.d = true;
                    U4.h hVar = (U4.h) iVar.f9073e.get();
                    if (hVar != null) {
                        e eVar2 = (e) hVar;
                        eVar2.s();
                        eVar2.invalidateSelf();
                    }
                    break;
                }
                break;
        }
    }
}
