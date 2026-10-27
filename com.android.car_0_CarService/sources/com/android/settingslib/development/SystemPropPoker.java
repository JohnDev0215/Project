package com.android.settingslib.development;

import android.os.AsyncTask;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class SystemPropPoker {
    private static final String TAG = "SystemPropPoker";
    private static final SystemPropPoker sInstance = new SystemPropPoker();
    private boolean mBlockPokes = false;

    private SystemPropPoker() {
    }

    public static SystemPropPoker getInstance() {
        return sInstance;
    }

    public void blockPokes() {
        this.mBlockPokes = true;
    }

    public void unblockPokes() {
        this.mBlockPokes = false;
    }

    public void poke() {
        if (!this.mBlockPokes) {
            createPokerTask().execute(new Void[0]);
        }
    }

    PokerTask createPokerTask() {
        return new PokerTask();
    }

    public static class PokerTask extends AsyncTask<Void, Void, Void> {
        String[] listServices() {
            return ServiceManager.listServices();
        }

        IBinder checkService(String service) {
            return ServiceManager.checkService(service);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... params) {
            String[] services = listServices();
            if (services == null) {
                Log.e(SystemPropPoker.TAG, "There are no services, how odd");
                return null;
            }
            for (String service : services) {
                IBinder obj = checkService(service);
                if (obj != null) {
                    Parcel data = Parcel.obtain();
                    try {
                        obj.transact(1599295570, data, null, 0);
                    } catch (RemoteException e) {
                    } catch (Exception e2) {
                        Log.i(SystemPropPoker.TAG, "Someone wrote a bad service '" + service + "' that doesn't like to be poked", e2);
                    }
                    data.recycle();
                }
            }
            return null;
        }
    }
}
