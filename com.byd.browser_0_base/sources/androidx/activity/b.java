package androidx.activity;

import android.content.Intent;
import android.content.IntentSender;
import androidx.activity.ComponentActivity;

/* JADX INFO: compiled from: ComponentActivity.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ IntentSender.SendIntentException f;
    public final /* synthetic */ ComponentActivity.b g;

    public b(ComponentActivity.b bVar, int i, IntentSender.SendIntentException sendIntentException) {
        this.g = bVar;
        this.e = i;
        this.f = sendIntentException;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.g.a(this.e, 0, new Intent().setAction("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST").putExtra("androidx.activity.result.contract.extra.SEND_INTENT_EXCEPTION", this.f));
    }
}
