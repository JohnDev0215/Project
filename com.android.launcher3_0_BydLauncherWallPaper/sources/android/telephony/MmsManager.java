package android.telephony;

import android.app.ActivityThread;
import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import com.android.internal.telephony.IMms;

/* JADX INFO: loaded from: classes3.dex */
public class MmsManager {
    private static final String TAG = "MmsManager";
    private final Context mContext;

    public MmsManager(Context context) {
        this.mContext = context;
    }

    public void sendMultimediaMessage(int i, Uri uri, String str, Bundle bundle, PendingIntent pendingIntent, long j) {
        try {
            IMms iMmsAsInterface = IMms.Stub.asInterface(ServiceManager.getService("imms"));
            if (iMmsAsInterface == null) {
                return;
            }
            iMmsAsInterface.sendMessage(i, ActivityThread.currentPackageName(), uri, str, bundle, pendingIntent, j);
        } catch (RemoteException unused) {
        }
    }

    public void downloadMultimediaMessage(int i, String str, Uri uri, Bundle bundle, PendingIntent pendingIntent, long j) {
        try {
            IMms iMmsAsInterface = IMms.Stub.asInterface(ServiceManager.getService("imms"));
            if (iMmsAsInterface == null) {
                return;
            }
            iMmsAsInterface.downloadMessage(i, ActivityThread.currentPackageName(), str, uri, bundle, pendingIntent, j);
        } catch (RemoteException unused) {
        }
    }
}
