package c.a.g.l;

import android.content.Context;
import android.content.Intent;

/* JADX INFO: compiled from: LogHelperUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2769a = "i";

    public static void a(Context context) {
        c.a.b.d.e.a(f2769a, "saveLog");
        Intent intent = new Intent();
        intent.setPackage("com.byd.logtools");
        intent.setAction("bydlogtool.action.saveotatestlog");
        context.sendBroadcast(intent);
    }
}
