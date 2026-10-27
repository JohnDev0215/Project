package defpackage;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: compiled from: ArrayQueue.kt */
/* JADX INFO: loaded from: classes.dex */
public final class o8 {
    public int a;
    public int b;
    public Object c;

    public static o8 a(Context context, int i, Intent intent, int i2) {
        int iD = i2 | q11.d(false);
        return new o8(PendingIntent.getActivity(context, i, intent, iD), iD, i);
    }

    public static o8 b(Context context, int i, Intent intent, int i2, boolean z) {
        int iD = i2 | q11.d(z);
        return new o8(PendingIntent.getBroadcast(context, i, intent, iD), iD, i);
    }

    public /* synthetic */ o8(int i, int i2, String str) {
        this.a = i;
        this.c = str;
        this.b = i2;
    }

    public /* synthetic */ o8(PendingIntent pendingIntent, int i, int i2) {
        this.c = pendingIntent;
        this.a = i;
        this.b = i2;
    }
}
