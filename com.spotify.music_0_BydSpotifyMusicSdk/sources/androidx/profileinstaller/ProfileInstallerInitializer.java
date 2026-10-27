package androidx.profileinstaller;

import android.content.Context;
import java.util.Collections;
import java.util.List;
import p.hy1;
import p.n41;
import p.ua;
import p.zb0;

/* JADX INFO: loaded from: classes.dex */
public class ProfileInstallerInitializer implements n41 {
    @Override // p.n41
    public final List a() {
        return Collections.emptyList();
    }

    @Override // p.n41
    public final Object b(Context context) {
        hy1.a(new ua(this, 5, context.getApplicationContext()));
        return new zb0();
    }
}
