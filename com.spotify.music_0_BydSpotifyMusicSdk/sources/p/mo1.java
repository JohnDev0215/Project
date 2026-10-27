package p;

import android.app.NotificationManager;
import android.content.Context;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class mo1 {
    public static String c;
    public final NotificationManager a;
    public static final Object b = new Object();
    public static HashSet d = new HashSet();

    public mo1(Context context) {
        this.a = (NotificationManager) context.getSystemService("notification");
    }
}
