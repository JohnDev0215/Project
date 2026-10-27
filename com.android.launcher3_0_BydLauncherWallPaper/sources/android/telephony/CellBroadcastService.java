package android.telephony;

import android.annotation.SystemApi;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteCallback;
import android.telephony.ICellBroadcastService;
import android.telephony.cdma.CdmaSmsCbProgramData;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public abstract class CellBroadcastService extends Service {
    public static final String CELL_BROADCAST_SERVICE_INTERFACE = "android.telephony.CellBroadcastService";
    private final ICellBroadcastService.Stub mStubWrapper = new ICellBroadcastServiceWrapper();

    public abstract CharSequence getCellBroadcastAreaInfo(int i);

    public abstract void onCdmaCellBroadcastSms(int i, byte[] bArr, int i2);

    public abstract void onCdmaScpMessage(int i, List<CdmaSmsCbProgramData> list, String str, Consumer<Bundle> consumer);

    public abstract void onGsmCellBroadcastSms(int i, byte[] bArr);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mStubWrapper;
    }

    public class ICellBroadcastServiceWrapper extends ICellBroadcastService.Stub {
        public ICellBroadcastServiceWrapper() {
        }

        @Override // android.telephony.ICellBroadcastService
        public void handleGsmCellBroadcastSms(int i, byte[] bArr) {
            CellBroadcastService.this.onGsmCellBroadcastSms(i, bArr);
        }

        @Override // android.telephony.ICellBroadcastService
        public void handleCdmaCellBroadcastSms(int i, byte[] bArr, int i2) {
            CellBroadcastService.this.onCdmaCellBroadcastSms(i, bArr, i2);
        }

        @Override // android.telephony.ICellBroadcastService
        public void handleCdmaScpMessage(int i, List<CdmaSmsCbProgramData> list, String str, final RemoteCallback remoteCallback) {
            CellBroadcastService.this.onCdmaScpMessage(i, list, str, new Consumer() { // from class: android.telephony.-$$Lambda$CellBroadcastService$ICellBroadcastServiceWrapper$vCpvAyk_EjxxoURr9Sdi8TBp-3Q
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    remoteCallback.sendResult((Bundle) obj);
                }
            });
        }

        @Override // android.telephony.ICellBroadcastService
        public CharSequence getCellBroadcastAreaInfo(int i) {
            return CellBroadcastService.this.getCellBroadcastAreaInfo(i);
        }
    }
}
