package defpackage;

import android.app.Activity;
import android.content.Intent;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class m2 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Activity f;

    public /* synthetic */ m2(Activity activity, int i) {
        this.e = i;
        this.f = activity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        Activity activity = this.f;
        switch (i) {
            case 1:
                activity.closeContextMenu();
                break;
            default:
                Intent intent = new Intent("android.settings.SECURITY_SETTINGS");
                intent.setFlags(268435456);
                activity.startActivity(intent);
                break;
        }
    }
}
