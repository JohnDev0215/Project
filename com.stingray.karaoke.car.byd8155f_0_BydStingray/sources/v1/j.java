package v1;

import android.app.NotificationManager;
import android.content.Context;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class j {
    public static String d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static ServiceConnectionC2542i f22975g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f22976a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final NotificationManager f22977b;
    public static final Object c = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static HashSet f22973e = new HashSet();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Object f22974f = new Object();

    public j(Context context) {
        this.f22976a = context;
        this.f22977b = (NotificationManager) context.getSystemService("notification");
    }
}
