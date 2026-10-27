package p;

import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public final class rj2 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ TextInputLayout e;

    public /* synthetic */ rj2(TextInputLayout textInputLayout, int i) {
        this.c = i;
        this.e = textInputLayout;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        TextInputLayout textInputLayout = this.e;
        switch (i) {
            case 0:
                textInputLayout.q0.performClick();
                textInputLayout.q0.jumpDrawablesToCurrentState();
                break;
            default:
                textInputLayout.m.requestLayout();
                break;
        }
    }
}
