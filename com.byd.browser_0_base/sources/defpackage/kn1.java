package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import defpackage.di1;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: NfcSystemLevelPrompt.java */
/* JADX INFO: loaded from: classes3.dex */
public final class kn1 implements di1.a {
    public bi1 e;
    public WindowAndroid f;
    public Runnable g;

    @Override // di1.a
    public final void b(int i) {
        Runnable runnable = this.g;
        if (runnable != null) {
            runnable.run();
        }
        this.g = null;
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        if (i == 1) {
            this.e.b(2, propertyModel);
            return;
        }
        try {
            this.f.u(new Intent("android.settings.panel.action.NFC"), new jn1(this, propertyModel), null);
        } catch (ActivityNotFoundException unused) {
            this.e.b(1, propertyModel);
        }
    }
}
