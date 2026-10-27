package com.byd.dilink_DiR.hardware.display;

import android.car.Car;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.PowerManager;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.hardware.display.IDiLinkBacklightCtrl;
import com.byd.dilink.hardware.display.IDiLinkDisplayManager;
import com.byd.dilink.hardware.display.IDiLinkOnekeyStatusListener;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_DiR_RUI})
@ServiceImpl(service = IDiLinkDisplayManager.class, singleton = true)
public class DiLinkDisplayManager implements IDiLinkDisplayManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DisplayManager f2191a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private PowerManager f2192b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private DiLinkDiR_OnekeyStatusListener f2193c = new DiLinkDiR_OnekeyStatusListener();

    public static class DiLinkDiR_OnekeyStatusListener extends DiLinkCallbackHolders<IDiLinkOnekeyStatusListener, DisplayManager.OnekeyStatusListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public DisplayManager.OnekeyStatusListener createInnerCallback(final IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener) {
            return new DisplayManager.OnekeyStatusListener() { // from class: com.byd.dilink_DiR.hardware.display.DiLinkDisplayManager.DiLinkDiR_OnekeyStatusListener.1
                @Keep
                public void onOnekeyStatusChange(int i, int i2) {
                    iDiLinkOnekeyStatusListener.onOnekeyStatusChange(i, i2);
                }
            };
        }
    }

    public class a implements IDiLinkBacklightCtrl {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private DisplayManager.BacklightCtrl f2196a;

        public a(DisplayManager.BacklightCtrl backlightCtrl) {
            if (backlightCtrl == null && DiLink.isNotSelfCheckInnerTest()) {
                throw new IllegalArgumentException("backlightCtrl is null");
            }
            this.f2196a = backlightCtrl;
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOff() {
            this.f2196a.turnBacklightOff();
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOn() {
            this.f2196a.turnBacklightOn();
        }
    }

    public class b implements IDiLinkBacklightCtrl {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private PowerManager.BacklightCtrl f2198a;

        public b(PowerManager.BacklightCtrl backlightCtrl) {
            if (backlightCtrl == null && DiLink.isNotSelfCheckInnerTest()) {
                throw new IllegalArgumentException("backlightCtrl is null");
            }
            this.f2198a = backlightCtrl;
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOff() {
            this.f2198a.turnBacklightOff();
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOn() {
            this.f2198a.turnBacklightOn();
        }
    }

    public DiLinkDisplayManager(Context context) {
        this.f2191a = (DisplayManager) context.getSystemService("display");
        this.f2192b = (PowerManager) context.getSystemService(Car.POWER_SERVICE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(DisplayManager.OnekeyStatusListener onekeyStatusListener) {
        this.f2191a.unregisterOnekeyStatusListener(onekeyStatusListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(Handler handler, DisplayManager.OnekeyStatusListener onekeyStatusListener) {
        this.f2191a.registerOnekeyStatusListener(onekeyStatusListener, handler);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    @NonNull
    public IDiLinkBacklightCtrl createBackLightCtrl(@NonNull String str) {
        return new b(this.f2192b.createBackLightCtrl(str));
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    @NonNull
    public IDiLinkBacklightCtrl createBackLightCtrl(@NonNull String str, @NonNull int i) {
        return new a(this.f2191a.createBackLightCtrl(str, i));
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public int getPowerScreenStatus() {
        return this.f2192b.getPowerScreenStatus();
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public int getPowerScreenStatus(int i) {
        return this.f2191a.getPowerScreenStatus(i);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void registerOnekeyStatusListener(@NonNull IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener, @Nullable final Handler handler) {
        this.f2193c.addCallbackIgnoreRemoteException(iDiLinkOnekeyStatusListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.hardware.display.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2200a.d(handler, (DisplayManager.OnekeyStatusListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void setBacklightMode(int i, int i2) {
        throw new UnsupportedTargetPlatformException("DiR找不到对应方法");
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOff(int i, long j) {
        this.f2191a.turnBacklightOff(i, j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOff(long j) {
        this.f2192b.turnBacklightOff(j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOn(int i, long j) {
        this.f2191a.turnBacklightOn(i, j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOn(long j) {
        this.f2192b.turnBacklightOn(j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void unregisterOnekeyStatusListener(@NonNull IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener) {
        this.f2193c.removeCallbackIgnoreRemoteException(iDiLinkOnekeyStatusListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.hardware.display.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2202a.c((DisplayManager.OnekeyStatusListener) obj);
            }
        });
    }
}
