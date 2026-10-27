package com.byd.dilink60.hardware.display;

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
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkDisplayManager.class, singleton = true)
public class DiLinkDisplayManager implements IDiLinkDisplayManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DisplayManager f1992a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private PowerManager f1993b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private DiLink6_0_OnekeyStatusListener f1994c = new DiLink6_0_OnekeyStatusListener();

    public static class DiLink6_0_OnekeyStatusListener extends DiLinkCallbackHolders<IDiLinkOnekeyStatusListener, DisplayManager.OnekeyStatusListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public DisplayManager.OnekeyStatusListener createInnerCallback(final IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener) {
            return new DisplayManager.OnekeyStatusListener() { // from class: com.byd.dilink60.hardware.display.DiLinkDisplayManager.DiLink6_0_OnekeyStatusListener.1
                @Keep
                public void onOnekeyStatusChange(int i, int i2) {
                    iDiLinkOnekeyStatusListener.onOnekeyStatusChange(i, i2);
                }
            };
        }
    }

    public class a implements IDiLinkBacklightCtrl {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private DisplayManager.BacklightCtrl f1997a;

        public a(DisplayManager.BacklightCtrl backlightCtrl) {
            if (backlightCtrl == null && DiLink.isNotSelfCheckInnerTest()) {
                throw new IllegalArgumentException("backlightCtrl is null");
            }
            this.f1997a = backlightCtrl;
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOff() {
            this.f1997a.turnBacklightOff();
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOn() {
            this.f1997a.turnBacklightOn();
        }
    }

    public class b implements IDiLinkBacklightCtrl {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private PowerManager.BacklightCtrl f1999a;

        public b(PowerManager.BacklightCtrl backlightCtrl) {
            if (backlightCtrl == null && DiLink.isNotSelfCheckInnerTest()) {
                throw new IllegalArgumentException("backlightCtrl is null");
            }
            this.f1999a = backlightCtrl;
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOff() {
            this.f1999a.turnBacklightOff();
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOn() {
            this.f1999a.turnBacklightOn();
        }
    }

    public DiLinkDisplayManager(Context context) {
        this.f1992a = (DisplayManager) context.getSystemService("display");
        this.f1993b = (PowerManager) context.getSystemService(Car.POWER_SERVICE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(DisplayManager.OnekeyStatusListener onekeyStatusListener) {
        this.f1992a.unregisterOnekeyStatusListener(onekeyStatusListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(Handler handler, DisplayManager.OnekeyStatusListener onekeyStatusListener) {
        this.f1992a.registerOnekeyStatusListener(onekeyStatusListener, handler);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    @NonNull
    public IDiLinkBacklightCtrl createBackLightCtrl(@NonNull String str) {
        return new b(this.f1993b.createBackLightCtrl(str));
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    @NonNull
    public IDiLinkBacklightCtrl createBackLightCtrl(@NonNull String str, @NonNull int i) {
        return new a(this.f1992a.createBackLightCtrl(str, i));
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public int getPowerScreenStatus() {
        return this.f1993b.getPowerScreenStatus();
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public int getPowerScreenStatus(int i) {
        return this.f1992a.getPowerScreenStatus(i);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void registerOnekeyStatusListener(@NonNull IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener, @Nullable final Handler handler) {
        this.f1994c.addCallbackIgnoreRemoteException(iDiLinkOnekeyStatusListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.hardware.display.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2001a.b(handler, (DisplayManager.OnekeyStatusListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void setBacklightMode(int i, int i2) {
        throw new UnsupportedTargetPlatformException("Di6.0找不到对应方法");
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOff(int i, long j) {
        this.f1992a.turnBacklightOff(i, j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOff(long j) {
        this.f1993b.turnBacklightOff(j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOn(int i, long j) {
        this.f1992a.turnBacklightOn(i, j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOn(long j) {
        this.f1993b.turnBacklightOn(j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void unregisterOnekeyStatusListener(@NonNull IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener) {
        this.f1994c.removeCallbackIgnoreRemoteException(iDiLinkOnekeyStatusListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.hardware.display.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2003a.a((DisplayManager.OnekeyStatusListener) obj);
            }
        });
    }
}
