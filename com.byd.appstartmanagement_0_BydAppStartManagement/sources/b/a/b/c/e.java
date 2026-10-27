package b.a.b.c;

import android.content.Context;
import android.content.Intent;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class e {
    public static void a(Context context, boolean z) {
        Intent intent = new Intent("com.byd.carsettings.not.mainactivity.change");
        intent.putExtra("show", z);
        context.sendBroadcast(intent);
    }
}
