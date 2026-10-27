package com.amazon.identity.auth.device;

import android.annotation.SuppressLint;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Binder;
import android.util.Log;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import com.amazon.identity.auth.device.framework.RemoteMAPException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class s5 {
    public static final String c = "com.amazon.identity.auth.device.s5";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f542a;
    public final ContentResolver b;

    public s5(Context context) {
        this(context, context.getContentResolver());
    }

    @SuppressLint({"NewApi"})
    public <T> T a(Uri uri, v4<T> v4Var) throws RemoteMAPException {
        try {
            Context context = this.f542a;
            ProviderInfo providerInfoA = z5.a(uri, 0, context.getPackageManager());
            if (providerInfoA == null) {
                throw new IllegalStateException(String.format("Authority %s does not exist on the device", uri.getAuthority()));
            }
            e.a(context, providerInfoA.packageName);
            int i = 0;
            while (true) {
                ContentProviderClient contentProviderClientAcquireUnstableContentProviderClient = null;
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    contentProviderClientAcquireUnstableContentProviderClient = this.b.acquireUnstableContentProviderClient(uri);
                    T tA = v4Var.a(contentProviderClientAcquireUnstableContentProviderClient);
                    if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                        contentProviderClientAcquireUnstableContentProviderClient.release();
                    }
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    return tA;
                } catch (DeviceDataStoreException e) {
                    throw new RemoteMAPException(e);
                } catch (Exception e2) {
                    if (i >= 1) {
                        Log.e(ga.a(c), "Got exception querying " + uri + ". Failing after " + i + " retries.", e2);
                        yd.a("ContentProviderFailure", new String[0]);
                        throw new RemoteMAPException(e2);
                    }
                    try {
                        Log.w(ga.a(c), "Got exception querying " + uri + ". Retrying." + e2.getMessage());
                        yd.a("ContentProviderRetry", new String[0]);
                        if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                            contentProviderClientAcquireUnstableContentProviderClient.release();
                        }
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                        if (i < 1) {
                            try {
                                Thread.sleep(10L);
                            } catch (InterruptedException e3) {
                                Log.e(ga.a(c), "Got an InterruptedException while retrying calling " + uri, e3);
                                Thread.currentThread().interrupt();
                            }
                        }
                        i++;
                    } catch (Throwable th) {
                        if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                            contentProviderClientAcquireUnstableContentProviderClient.release();
                        }
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                        throw th;
                    }
                }
            }
        } catch (Exception e4) {
            throw new RemoteMAPException(e4);
        }
    }

    public s5(Context context, ContentResolver contentResolver) {
        this.f542a = context;
        this.b = contentResolver;
    }
}
