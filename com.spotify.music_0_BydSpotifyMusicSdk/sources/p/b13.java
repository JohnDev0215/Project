package p;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.play.core.assetpacks.ExtractionForegroundService;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class b13 implements ServiceConnection {
    public final tz2 a = new tz2("ExtractionForegroundServiceConnection", 0);
    public final ArrayList b = new ArrayList();
    public final Context c;
    public ExtractionForegroundService d;
    public Notification e;

    public b13(Context context) {
        this.c = context;
    }

    public final void a() {
        ArrayList arrayList;
        synchronized (this.b) {
            arrayList = new ArrayList(this.b);
            this.b.clear();
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            e33 e33Var = (e33) arrayList.get(i);
            try {
                Bundle bundle = new Bundle();
                Bundle bundle2 = new Bundle();
                Parcel parcelG0 = e33Var.g0();
                int i2 = n23.a;
                parcelG0.writeInt(1);
                bundle.writeToParcel(parcelG0, 0);
                parcelG0.writeInt(1);
                bundle2.writeToParcel(parcelG0, 0);
                e33Var.h0(parcelG0, 2);
            } catch (RemoteException unused) {
                this.a.c("Could not resolve Play Store service state update callback.", new Object[0]);
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.a.b("Starting foreground installation service.", new Object[0]);
        ExtractionForegroundService extractionForegroundService = ((a13) iBinder).b;
        this.d = extractionForegroundService;
        extractionForegroundService.startForeground(-1883842196, this.e);
        a();
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
    }
}
