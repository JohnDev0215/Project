package defpackage;

import android.view.View;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class yy0 implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Runnable f;

    public /* synthetic */ yy0(int i, Runnable runnable) {
        this.e = i;
        this.f = runnable;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.e;
        Runnable runnable = this.f;
        switch (i) {
            case 0:
                runnable.run();
                break;
            case 1:
                runnable.run();
                break;
            case 2:
                runnable.run();
                break;
            default:
                runnable.run();
                break;
        }
    }
}
