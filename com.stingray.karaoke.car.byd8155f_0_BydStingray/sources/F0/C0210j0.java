package F0;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* JADX INFO: renamed from: F0.j0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0210j0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f2214a;

    public C0210j0(Context context) {
        this.f2214a = context;
    }

    public final void a(String str) {
        try {
            this.f2214a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (ActivityNotFoundException e10) {
            throw new IllegalArgumentException("Can't open " + str + '.', e10);
        }
    }
}
