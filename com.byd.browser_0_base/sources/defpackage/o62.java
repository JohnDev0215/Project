package defpackage;

import org.chromium.base.Callback;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class o62 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ s62 f;

    public /* synthetic */ o62(s62 s62Var, int i) {
        this.e = i;
        this.f = s62Var;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0004. Please report as an issue. */
    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        s62 s62Var = this.f;
        switch (i) {
        }
        s62Var.e((Exception) obj);
    }
}
