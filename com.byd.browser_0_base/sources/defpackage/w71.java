package defpackage;

import defpackage.f83;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: LinkToTextHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class w71 implements f83.c {
    public final /* synthetic */ Callback e;

    public w71(Callback callback) {
        this.e = callback;
    }

    @Override // defpackage.sl
    public final void c(String[] strArr) {
        this.e.onResult(strArr);
    }
}
