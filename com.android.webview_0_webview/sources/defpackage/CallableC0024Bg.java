package defpackage;

import android.content.Context;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: Bg, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class CallableC0024Bg implements Callable {
    public final /* synthetic */ Context a;

    public CallableC0024Bg(Context context) {
        this.a = context;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Object call() {
        return this.a.getSharedPreferences("google_sdk_flags", 0);
    }
}
