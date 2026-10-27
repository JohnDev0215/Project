package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Locale;

/* JADX INFO: renamed from: fA, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0757fA extends BroadcastReceiver {
    public final /* synthetic */ C0813gA a;

    public C0757fA(C0813gA c0813gA) {
        this.a = c0813gA;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.a.W = Locale.getDefault().toLanguageTag();
    }
}
