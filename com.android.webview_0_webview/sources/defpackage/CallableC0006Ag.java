package defpackage;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: Ag, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class CallableC0006Ag implements Callable {
    public final /* synthetic */ SharedPreferences a;
    public final /* synthetic */ String b;
    public final /* synthetic */ String c;

    public CallableC0006Ag(SharedPreferences sharedPreferences, String str, String str2) {
        this.a = sharedPreferences;
        this.b = str;
        this.c = str2;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Object call() {
        return this.a.getString(this.b, this.c);
    }
}
