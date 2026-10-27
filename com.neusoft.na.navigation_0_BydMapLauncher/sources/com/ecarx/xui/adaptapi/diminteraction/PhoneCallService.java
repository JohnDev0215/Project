package com.ecarx.xui.adaptapi.diminteraction;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ecarx.xui.adaptapi.diminteraction.IPhoneTransListener;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class PhoneCallService extends Service {
    private List<IPhoneCallListener> listenerList = new ArrayList();
    private PhoneTransferListener phoneTransferListener;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.d("diminteraction", "PhoneCallService onCreate ");
        this.phoneTransferListener = new PhoneTransferListener();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.d("diminteraction", "PhoneCallService onBind : " + this.phoneTransferListener);
        return this.phoneTransferListener;
    }

    private class PhoneTransferListener extends IPhoneTransListener.Stub {
        @Override // com.ecarx.xui.adaptapi.diminteraction.IPhoneTransListener
        public void notifyMissCallCount(int i) throws RemoteException {
        }

        private PhoneTransferListener() {
        }

        @Override // com.ecarx.xui.adaptapi.diminteraction.IPhoneTransListener
        public void notifyPhoneBasicInfo(String str, int i, int i2) throws RemoteException {
            Log.d("diminteraction", "notifyPhoneBasicInfo ");
            Log.d("diminteraction", "PhoneCallService notifyPhoneBasicInfo size : " + PhoneCallService.this.listenerList.size());
            for (IPhoneCallListener iPhoneCallListener : PhoneCallService.this.listenerList) {
                Log.d("diminteraction", "notifyPhoneBasicInfo notifyPhoneBasicInfo IPhoneCallListener : " + iPhoneCallListener);
                try {
                    iPhoneCallListener.notifyPhoneBasicInfo(str, i, i2);
                } catch (Exception e) {
                    Log.d("diminteraction", "PhoneCallService notifyPhoneBasicInfo : " + e.toString());
                }
            }
        }

        @Override // com.ecarx.xui.adaptapi.diminteraction.IPhoneTransListener
        public void registerCallBack(IPhoneCallListener iPhoneCallListener) throws RemoteException {
            Log.d("diminteraction", "notifyPhoneBasicInfo registerCallBack : " + iPhoneCallListener);
            PhoneCallService.this.listenerList.add(iPhoneCallListener);
        }

        @Override // com.ecarx.xui.adaptapi.diminteraction.IPhoneTransListener
        public void notifyLineInfo(int i, boolean z, boolean z2, int i2, String str, String str2, int i3, long j, int i4) throws RemoteException {
            Log.d("diminteraction", "PhoneCallService notifyLineInfo ");
            Log.d("diminteraction", "PhoneCallService notifyLineInfo size : " + PhoneCallService.this.listenerList.size());
            for (IPhoneCallListener iPhoneCallListener : PhoneCallService.this.listenerList) {
                Log.d("diminteraction", "PhoneCallService notifyLineInfo IPhoneCallListener " + iPhoneCallListener);
                if (iPhoneCallListener != null) {
                    try {
                        iPhoneCallListener.notifyLineInfo(i, z, z2, i2, str, str2, i3, j, i4);
                    } catch (Exception e) {
                        Log.d("diminteraction", "PhoneCallService notifyLineInfo : " + e.toString());
                    }
                }
            }
        }

        @Override // com.ecarx.xui.adaptapi.diminteraction.IPhoneTransListener
        public void notifyDBState(int i) throws RemoteException {
            Log.d("diminteraction", "PhoneCallService notifyDBState ");
            Log.d("diminteraction", "PhoneCallService notifyDBState size : " + PhoneCallService.this.listenerList.size());
            for (IPhoneCallListener iPhoneCallListener : PhoneCallService.this.listenerList) {
                Log.d("diminteraction", "PhoneCallService notifyDBState IPhoneCallListener : " + iPhoneCallListener);
                try {
                    iPhoneCallListener.notifyDBState(i);
                } catch (Exception e) {
                    Log.d("diminteraction", "PhoneCallService notifyDBState : " + e.toString());
                }
            }
        }
    }
}
