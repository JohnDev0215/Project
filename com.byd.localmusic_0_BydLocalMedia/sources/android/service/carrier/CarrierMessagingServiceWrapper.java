package android.service.carrier;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.service.carrier.ICarrierMessagingCallback;
import android.service.carrier.ICarrierMessagingService;
import com.android.internal.util.Preconditions;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class CarrierMessagingServiceWrapper {
    private volatile CarrierMessagingServiceConnection mCarrierMessagingServiceConnection;
    private volatile ICarrierMessagingService mICarrierMessagingService;

    public static abstract class CarrierMessagingCallbackWrapper {
        public void onDownloadMmsComplete(int i) {
        }

        public void onFilterComplete(int i) {
        }

        public void onSendMmsComplete(int i, byte[] bArr) {
        }

        public void onSendMultipartSmsComplete(int i, int[] iArr) {
        }

        public void onSendSmsComplete(int i, int i2) {
        }
    }

    public abstract void onServiceReady();

    public boolean bindToCarrierMessagingService(Context context, String str) {
        Preconditions.checkState(this.mCarrierMessagingServiceConnection == null);
        Intent intent = new Intent(CarrierMessagingService.SERVICE_INTERFACE);
        intent.setPackage(str);
        this.mCarrierMessagingServiceConnection = new CarrierMessagingServiceConnection();
        return context.bindService(intent, this.mCarrierMessagingServiceConnection, 1);
    }

    public void disposeConnection(Context context) {
        Preconditions.checkNotNull(this.mCarrierMessagingServiceConnection);
        context.unbindService(this.mCarrierMessagingServiceConnection);
        this.mCarrierMessagingServiceConnection = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onServiceReady(ICarrierMessagingService iCarrierMessagingService) {
        this.mICarrierMessagingService = iCarrierMessagingService;
        onServiceReady();
    }

    public void filterSms(MessagePdu messagePdu, String str, int i, int i2, CarrierMessagingCallbackWrapper carrierMessagingCallbackWrapper) {
        if (this.mICarrierMessagingService != null) {
            try {
                this.mICarrierMessagingService.filterSms(messagePdu, str, i, i2, new CarrierMessagingCallbackWrapperInternal(carrierMessagingCallbackWrapper));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendTextSms(String str, int i, String str2, int i2, CarrierMessagingCallbackWrapper carrierMessagingCallbackWrapper) {
        if (this.mICarrierMessagingService != null) {
            try {
                this.mICarrierMessagingService.sendTextSms(str, i, str2, i2, new CarrierMessagingCallbackWrapperInternal(carrierMessagingCallbackWrapper));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendDataSms(byte[] bArr, int i, String str, int i2, int i3, CarrierMessagingCallbackWrapper carrierMessagingCallbackWrapper) {
        if (this.mICarrierMessagingService != null) {
            try {
                this.mICarrierMessagingService.sendDataSms(bArr, i, str, i2, i3, new CarrierMessagingCallbackWrapperInternal(carrierMessagingCallbackWrapper));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendMultipartTextSms(List<String> list, int i, String str, int i2, CarrierMessagingCallbackWrapper carrierMessagingCallbackWrapper) {
        if (this.mICarrierMessagingService != null) {
            try {
                this.mICarrierMessagingService.sendMultipartTextSms(list, i, str, i2, new CarrierMessagingCallbackWrapperInternal(carrierMessagingCallbackWrapper));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendMms(Uri uri, int i, Uri uri2, CarrierMessagingCallbackWrapper carrierMessagingCallbackWrapper) {
        if (this.mICarrierMessagingService != null) {
            try {
                this.mICarrierMessagingService.sendMms(uri, i, uri2, new CarrierMessagingCallbackWrapperInternal(carrierMessagingCallbackWrapper));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void downloadMms(Uri uri, int i, Uri uri2, CarrierMessagingCallbackWrapper carrierMessagingCallbackWrapper) {
        if (this.mICarrierMessagingService != null) {
            try {
                this.mICarrierMessagingService.downloadMms(uri, i, uri2, new CarrierMessagingCallbackWrapperInternal(carrierMessagingCallbackWrapper));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private final class CarrierMessagingServiceConnection implements ServiceConnection {
        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        private CarrierMessagingServiceConnection() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            CarrierMessagingServiceWrapper.this.onServiceReady(ICarrierMessagingService.Stub.asInterface(iBinder));
        }
    }

    private final class CarrierMessagingCallbackWrapperInternal extends ICarrierMessagingCallback.Stub {
        CarrierMessagingCallbackWrapper mCarrierMessagingCallbackWrapper;

        CarrierMessagingCallbackWrapperInternal(CarrierMessagingCallbackWrapper carrierMessagingCallbackWrapper) {
            this.mCarrierMessagingCallbackWrapper = carrierMessagingCallbackWrapper;
        }

        @Override // android.service.carrier.ICarrierMessagingCallback
        public void onFilterComplete(int i) throws RemoteException {
            this.mCarrierMessagingCallbackWrapper.onFilterComplete(i);
        }

        @Override // android.service.carrier.ICarrierMessagingCallback
        public void onSendSmsComplete(int i, int i2) throws RemoteException {
            this.mCarrierMessagingCallbackWrapper.onSendSmsComplete(i, i2);
        }

        @Override // android.service.carrier.ICarrierMessagingCallback
        public void onSendMultipartSmsComplete(int i, int[] iArr) throws RemoteException {
            this.mCarrierMessagingCallbackWrapper.onSendMultipartSmsComplete(i, iArr);
        }

        @Override // android.service.carrier.ICarrierMessagingCallback
        public void onSendMmsComplete(int i, byte[] bArr) throws RemoteException {
            this.mCarrierMessagingCallbackWrapper.onSendMmsComplete(i, bArr);
        }

        @Override // android.service.carrier.ICarrierMessagingCallback
        public void onDownloadMmsComplete(int i) throws RemoteException {
            this.mCarrierMessagingCallbackWrapper.onDownloadMmsComplete(i);
        }
    }
}
