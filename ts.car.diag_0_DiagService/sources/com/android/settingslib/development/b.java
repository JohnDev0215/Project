package com.android.settingslib.development;

import android.os.AsyncTask;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final b f1347b = new b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f1348a = false;

    /* JADX INFO: compiled from: caradapter-bug */
    public static class a extends AsyncTask<Void, Void, Void> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            String[] strArrListServices = listServices();
            if (strArrListServices == null) {
                Log.e("SystemPropPoker", "There are no services, how odd");
                return null;
            }
            for (String str : strArrListServices) {
                IBinder iBinderCheckService = checkService(str);
                if (iBinderCheckService != null) {
                    Parcel parcelObtain = Parcel.obtain();
                    try {
                        iBinderCheckService.transact(1599295570, parcelObtain, null, 0);
                    } catch (RemoteException unused) {
                    } catch (Exception e) {
                        Log.i("SystemPropPoker", "Someone wrote a bad service '" + str + "' that doesn't like to be poked", e);
                    }
                    parcelObtain.recycle();
                }
            }
            return null;
        }

        @VisibleForTesting
        IBinder checkService(String str) {
            return ServiceManager.checkService(str);
        }

        @VisibleForTesting
        String[] listServices() {
            return ServiceManager.listServices();
        }
    }

    private b() {
    }

    @NonNull
    public static b a() {
        return f1347b;
    }

    public void b() {
        if (this.f1348a) {
            return;
        }
        createPokerTask().execute(new Void[0]);
    }

    @VisibleForTesting
    a createPokerTask() {
        return new a();
    }
}
