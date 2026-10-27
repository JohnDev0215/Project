package defpackage;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Messenger;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: GoogleCloudMessagingV2.java */
/* JADX INFO: loaded from: classes2.dex */
public final class et0 {
    public PendingIntent a;
    public final Object b = new Object();

    public final Bundle a(Bundle bundle, String str) throws IOException {
        bundle.putString("sender", str);
        bundle.putString("subscription", str);
        if (bundle.getString("subtype") == null) {
            bundle.putString("subtype", str);
        }
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        if (!zt1.e("com.google.android.gms")) {
            throw new IOException("Google Play Services missing");
        }
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        Messenger messenger = new Messenger(new dt0(Looper.getMainLooper(), linkedBlockingQueue));
        Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
        intent.setPackage("com.google.android.gms");
        synchronized (this.b) {
            if (this.a == null) {
                Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                this.a = PendingIntent.getBroadcast(xz.a, 0, intent2, q11.d(false));
            }
        }
        intent.putExtra("app", this.a);
        intent.putExtras(bundle);
        intent.putExtra("google.messenger", messenger);
        xz.a.startService(intent);
        try {
            Intent intent3 = (Intent) linkedBlockingQueue.poll(5000L, TimeUnit.MILLISECONDS);
            if (intent3 == null) {
                throw new IOException("SERVICE_NOT_AVAILABLE");
            }
            if (intent3.getStringExtra("registration_id") != null) {
                return intent3.getExtras();
            }
            String stringExtra = intent3.getStringExtra("error");
            if (stringExtra != null) {
                throw new IOException(stringExtra);
            }
            throw new IOException("SERVICE_NOT_AVAILABLE");
        } catch (InterruptedException e) {
            throw new IOException(e.getMessage());
        }
    }
}
