package com.amazon.identity.auth.device.framework;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import com.amazon.identity.auth.device.ga;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AmazonIntentService extends IntentService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f318a = AmazonIntentService.class.getName();

    public AmazonIntentService(String str) {
        super(str);
    }

    public abstract void a(Intent intent);

    @Override // android.app.IntentService
    public final void onHandleIntent(Intent intent) {
        if (intent == null) {
            Log.w(ga.a(f318a), "Ignore: Received (null) Intent");
            return;
        }
        try {
            Log.i(ga.a(f318a), "Service Received: " + intent);
            a(intent);
        } catch (Error e) {
            stopSelf();
            throw e;
        } catch (RuntimeException e2) {
            stopSelf();
            throw e2;
        }
    }
}
