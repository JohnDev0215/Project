package defpackage;

import android.content.Intent;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: NfcSystemLevelPrompt.java */
/* JADX INFO: loaded from: classes3.dex */
public final class jn1 implements WindowAndroid.c {
    public final /* synthetic */ PropertyModel a;
    public final /* synthetic */ kn1 b;

    public jn1(kn1 kn1Var, PropertyModel propertyModel) {
        this.b = kn1Var;
        this.a = propertyModel;
    }

    @Override // org.chromium.ui.base.WindowAndroid.c
    public final void b(Intent intent, int i) {
        this.b.e.b(1, this.a);
    }
}
