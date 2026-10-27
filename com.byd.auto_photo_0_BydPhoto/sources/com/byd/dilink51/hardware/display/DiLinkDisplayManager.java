package com.byd.dilink51.hardware.display;

import android.car.Car;
import android.content.Context;
import android.hardware.display.BydDisplayManager;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.PowerManager;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.hardware.display.IDiLinkBacklightCtrl;
import com.byd.dilink.hardware.display.IDiLinkDisplayManager;
import com.byd.dilink.hardware.display.IDiLinkOnekeyStatusListener;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkDisplayManager.class, singleton = true)
public class DiLinkDisplayManager implements IDiLinkDisplayManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private PowerManager f1558a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f1559b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private DiLink5_1_Decouple_OnekeyStatusListener f1560c = new DiLink5_1_Decouple_OnekeyStatusListener();

    public static class DiLink5_1_Decouple_OnekeyStatusListener extends DiLinkCallbackHolders<IDiLinkOnekeyStatusListener, DisplayManager.OnekeyStatusListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public DisplayManager.OnekeyStatusListener createInnerCallback(final IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener) {
            return new DisplayManager.OnekeyStatusListener() { // from class: com.byd.dilink51.hardware.display.DiLinkDisplayManager.DiLink5_1_Decouple_OnekeyStatusListener.1
                @Keep
                public void onOnekeyStatusChange(int i, int i2) {
                    iDiLinkOnekeyStatusListener.onOnekeyStatusChange(i, i2);
                }
            };
        }
    }

    public class a implements IDiLinkBacklightCtrl {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private BydDisplayManager.BacklightCtrl f1563a;

        public a(BydDisplayManager.BacklightCtrl backlightCtrl) {
            this.f1563a = backlightCtrl;
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOff() {
            this.f1563a.turnBacklightOff();
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOn() {
            this.f1563a.turnBacklightOn();
        }
    }

    public DiLinkDisplayManager(Context context) {
        this.f1559b = context.getApplicationContext();
        this.f1558a = (PowerManager) context.getSystemService(Car.POWER_SERVICE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(DisplayManager.OnekeyStatusListener onekeyStatusListener) {
        BydDisplayManager.getInstance(this.f1559b).unregisterOnekeyStatusListener(onekeyStatusListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(Handler handler, DisplayManager.OnekeyStatusListener onekeyStatusListener) {
        BydDisplayManager.getInstance(this.f1559b).registerOnekeyStatusListener(onekeyStatusListener, handler);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    @NonNull
    public IDiLinkBacklightCtrl createBackLightCtrl(@NonNull String str) {
        return new a(BydDisplayManager.getInstance(this.f1559b).createBackLightCtrl(str));
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    @NonNull
    public IDiLinkBacklightCtrl createBackLightCtrl(@NonNull String str, @NonNull int i) {
        return new a(BydDisplayManager.getInstance(this.f1559b).createBackLightCtrl(str, i));
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public int getPowerScreenStatus() {
        return this.f1558a.getPowerScreenStatus();
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public int getPowerScreenStatus(int i) {
        return BydDisplayManager.getInstance(this.f1559b).getPowerScreenStatus(i);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void registerOnekeyStatusListener(@NonNull IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener, @Nullable final Handler handler) {
        this.f1560c.addCallbackIgnoreRemoteException(iDiLinkOnekeyStatusListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.hardware.display.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1566a.b(handler, (DisplayManager.OnekeyStatusListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void setBacklightMode(int i, int i2) {
        BydDisplayManager.getInstance(this.f1559b).setBacklightMode(i, i2);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOff(int i, long j) {
        BydDisplayManager.getInstance(this.f1559b).turnBacklightOff(i, j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOff(long j) {
        this.f1558a.turnBacklightOff(j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOn(int i, long j) {
        BydDisplayManager.getInstance(this.f1559b).turnBacklightOn(i, j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOn(long j) {
        this.f1558a.turnBacklightOn(j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void unregisterOnekeyStatusListener(@NonNull IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener) {
        this.f1560c.removeCallbackIgnoreRemoteException(iDiLinkOnekeyStatusListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.hardware.display.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1565a.a((DisplayManager.OnekeyStatusListener) obj);
            }
        });
    }
}
