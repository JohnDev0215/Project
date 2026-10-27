package a5;

import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public final class t implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ TextInputLayout f11194m;

    public /* synthetic */ t(TextInputLayout textInputLayout, int i7) {
        this.l = i7;
        this.f11194m = textInputLayout;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                TextInputLayout textInputLayout = this.f11194m;
                textInputLayout.f12932s0.performClick();
                textInputLayout.f12932s0.jumpDrawablesToCurrentState();
                break;
            default:
                this.f11194m.f12925p.requestLayout();
                break;
        }
    }
}
