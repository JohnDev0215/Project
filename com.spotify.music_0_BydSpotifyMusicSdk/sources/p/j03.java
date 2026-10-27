package p;

import android.app.NotificationManager;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class j03 extends c33 {
    public final tz2 b;
    public final Context c;
    public final com.google.android.play.core.assetpacks.c d;
    public final m23 e;
    public final b13 f;
    public final NotificationManager g;

    public j03(Context context, com.google.android.play.core.assetpacks.c cVar, m23 m23Var, b13 b13Var) {
        super("com.google.android.play.core.assetpacks.protocol.IAssetPackExtractionService");
        this.b = new tz2("AssetPackExtractionService", 0);
        this.c = context;
        this.d = cVar;
        this.e = m23Var;
        this.f = b13Var;
        this.g = (NotificationManager) context.getSystemService("notification");
    }
}
