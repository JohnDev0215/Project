package defpackage;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: compiled from: WebappLauncherActivity.java */
/* JADX INFO: loaded from: classes2.dex */
public final class os3 implements Runnable {
    public final /* synthetic */ Context e;
    public final /* synthetic */ Intent f;

    public os3(Context context, Intent intent) {
        this.e = context;
        this.f = intent;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context = this.e;
        Intent intent = this.f;
        ComponentName componentName = q11.a;
        try {
            context.startActivity(intent, null);
        } catch (ActivityNotFoundException unused) {
        }
    }
}
