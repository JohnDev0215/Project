package com.byd.dilink50.hardware.display;

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
@Platform(versions = {DiLink.VERSION_NAME_5_0})
@ServiceImpl(service = IDiLinkDisplayManager.class, singleton = true)
public class DiLinkDisplayManager implements IDiLinkDisplayManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DisplayManager f1383a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private PowerManager f1384b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private DiLink5_0_OnekeyStatusListener f1385c = new DiLink5_0_OnekeyStatusListener();

    public static class DiLink5_0_OnekeyStatusListener extends DiLinkCallbackHolders<IDiLinkOnekeyStatusListener, DisplayManager.OnekeyStatusListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public DisplayManager.OnekeyStatusListener createInnerCallback(final IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener) {
            return new DisplayManager.OnekeyStatusListener() { // from class: com.byd.dilink50.hardware.display.DiLinkDisplayManager.DiLink5_0_OnekeyStatusListener.1
                @Keep
                public void onOnekeyStatusChange(int i, int i2) {
                    iDiLinkOnekeyStatusListener.onOnekeyStatusChange(i, i2);
                }
            };
        }
    }

    public class a implements IDiLinkBacklightCtrl {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private DisplayManager.BacklightCtrl f1388a;

        public a(DisplayManager.BacklightCtrl backlightCtrl) {
            if (backlightCtrl == null && DiLink.isNotSelfCheckInnerTest()) {
                throw new IllegalArgumentException("backlightCtrl is null");
            }
            this.f1388a = backlightCtrl;
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOff() {
            this.f1388a.turnBacklightOff();
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOn() {
            this.f1388a.turnBacklightOn();
        }
    }

    public class b implements IDiLinkBacklightCtrl {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private PowerManager.BacklightCtrl f1390a;

        public b(PowerManager.BacklightCtrl backlightCtrl) {
            if (backlightCtrl == null && DiLink.isNotSelfCheckInnerTest()) {
                throw new IllegalArgumentException("backlightCtrl is null");
            }
            this.f1390a = backlightCtrl;
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOff() {
            this.f1390a.turnBacklightOff();
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOn() {
            this.f1390a.turnBacklightOn();
        }
    }

    public DiLinkDisplayManager(Context context) {
        this.f1383a = (DisplayManager) context.getSystemService("display");
        this.f1384b = (PowerManager) context.getSystemService(Car.POWER_SERVICE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(DisplayManager.OnekeyStatusListener onekeyStatusListener) {
        this.f1383a.unregisterOnekeyStatusListener(onekeyStatusListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(Handler handler, DisplayManager.OnekeyStatusListener onekeyStatusListener) {
        this.f1383a.registerOnekeyStatusListener(onekeyStatusListener, handler);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    @NonNull
    public IDiLinkBacklightCtrl createBackLightCtrl(@NonNull String str) {
        return new b(this.f1384b.createBackLightCtrl(str));
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    @NonNull
    public IDiLinkBacklightCtrl createBackLightCtrl(@NonNull String str, @NonNull int i) {
        return new a(this.f1383a.createBackLightCtrl(str, i));
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public int getPowerScreenStatus() {
        return this.f1384b.getPowerScreenStatus();
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public int getPowerScreenStatus(int i) {
        return this.f1383a.getPowerScreenStatus(i);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void registerOnekeyStatusListener(@NonNull IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener, @Nullable final Handler handler) {
        this.f1385c.addCallbackIgnoreRemoteException(iDiLinkOnekeyStatusListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink50.hardware.display.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1393a.b(handler, (DisplayManager.OnekeyStatusListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void setBacklightMode(int i, int i2) {
        throw new UnsupportedTargetPlatformException("Di5.0找不到对应方法");
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOff(int i, long j) {
        this.f1383a.turnBacklightOff(i, j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOff(long j) {
        this.f1384b.turnBacklightOff(j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOn(int i, long j) {
        this.f1383a.turnBacklightOn(i, j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOn(long j) {
        this.f1384b.turnBacklightOn(j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void unregisterOnekeyStatusListener(@NonNull IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener) {
        this.f1385c.removeCallbackIgnoreRemoteException(iDiLinkOnekeyStatusListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink50.hardware.display.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1392a.a((DisplayManager.OnekeyStatusListener) obj);
            }
        });
    }
}
