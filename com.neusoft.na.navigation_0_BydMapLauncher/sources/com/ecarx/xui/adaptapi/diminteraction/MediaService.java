package com.ecarx.xui.adaptapi.diminteraction;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ecarx.xui.adaptapi.diminteraction.IDimTransListener;

/* JADX INFO: loaded from: classes2.dex */
public class MediaService extends Service {
    private DimTransListener dimTransListener = new DimTransListener();

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.d("diminteraction", "MediaService onCreate ");
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.d("diminteraction", "MediaService onBind " + this.dimTransListener);
        return this.dimTransListener;
    }

    private class DimTransListener extends IDimTransListener.Stub {
        private IDimListener listener;

        private DimTransListener() {
        }

        @Override // com.ecarx.xui.adaptapi.diminteraction.IDimTransListener
        public void onUpdate(int i, int i2, String str) throws RemoteException {
            Log.d("diminteraction", "MediaService onUpdate ");
            Log.d("diminteraction", "MediaService onUpdate : " + this.listener);
            this.listener.onUpdate(i, i2, str);
        }

        @Override // com.ecarx.xui.adaptapi.diminteraction.IDimTransListener
        public void updateMedia(int i, String str, String str2, String str3, String str4, int i2) throws RemoteException {
            Log.d("diminteraction", "MediaService updateMedia ");
            Log.d("diminteraction", "MediaService updateMedia : " + this.listener);
            this.listener.updateMedia(i, str, str2, str3, str4, i2);
        }

        @Override // com.ecarx.xui.adaptapi.diminteraction.IDimTransListener
        public void onAction(int i, int i2) throws RemoteException {
            Log.d("diminteraction", "MediaService onAction ");
            Log.d("diminteraction", "MediaService onAction : " + this.listener);
            this.listener.onAction(i, i2);
        }

        @Override // com.ecarx.xui.adaptapi.diminteraction.IDimTransListener
        public void onListChanged(int i, int i2) throws RemoteException {
            Log.d("diminteraction", "MediaService onListChanged ");
            Log.d("diminteraction", "MediaService onListChanged : " + this.listener);
            this.listener.onListChanged(i, i2);
        }

        @Override // com.ecarx.xui.adaptapi.diminteraction.IDimTransListener
        public void onSourceChanged(int i) throws RemoteException {
            Log.d("diminteraction", "MediaService onSourceChanged ");
            Log.d("diminteraction", "MediaService onSourceChanged : " + this.listener);
            this.listener.onSourceChanged(i);
        }

        @Override // com.ecarx.xui.adaptapi.diminteraction.IDimTransListener
        public void setHighLight(String str, String str2, int i) throws RemoteException {
            Log.d("diminteraction", "MediaService setHighLight ");
            Log.d("diminteraction", "MediaService setHighLight : " + this.listener);
            this.listener.setHighLight(str, str2, i);
        }

        @Override // com.ecarx.xui.adaptapi.diminteraction.IDimTransListener
        public void setMode(int i, int i2) throws RemoteException {
            Log.d("diminteraction", "MediaService setMode ");
            Log.d("diminteraction", "MediaService setMode : " + this.listener);
            this.listener.setMode(i, i2);
        }

        @Override // com.ecarx.xui.adaptapi.diminteraction.IDimTransListener
        public void onRadioListChanged(int i, int i2) throws RemoteException {
            Log.d("diminteraction", "MediaService onRadioListChanged ");
            Log.d("diminteraction", "MediaService onRadioListChanged : " + this.listener);
            this.listener.onRadioListChanged(i, i2);
        }

        @Override // com.ecarx.xui.adaptapi.diminteraction.IDimTransListener
        public void setMute(int i) throws RemoteException {
            Log.d("diminteraction", "MediaService setMute ");
            Log.d("diminteraction", "MediaService setMute : " + this.listener);
            this.listener.setMute(i);
        }

        @Override // com.ecarx.xui.adaptapi.diminteraction.IDimTransListener
        public void registerCallBack(IDimListener iDimListener) throws RemoteException {
            Log.d("diminteraction", "MediaService registerCallBack : " + iDimListener);
            this.listener = iDimListener;
        }
    }
}
