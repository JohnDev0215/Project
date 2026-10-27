package com.byd.dilink51_main.hardware.display;

import android.car.Car;
import android.content.Context;
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
@Platform(versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkDisplayManager.class, singleton = true)
public class DiLinkDisplayManager implements IDiLinkDisplayManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DisplayManager f1764a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private PowerManager f1765b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private DiLink5_1_OnekeyStatusListener f1766c = new DiLink5_1_OnekeyStatusListener();

    public static class DiLink5_1_OnekeyStatusListener extends DiLinkCallbackHolders<IDiLinkOnekeyStatusListener, DisplayManager.OnekeyStatusListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public DisplayManager.OnekeyStatusListener createInnerCallback(final IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener) {
            return new DisplayManager.OnekeyStatusListener() { // from class: com.byd.dilink51_main.hardware.display.DiLinkDisplayManager.DiLink5_1_OnekeyStatusListener.1
                @Keep
                public void onOnekeyStatusChange(int i, int i2) {
                    iDiLinkOnekeyStatusListener.onOnekeyStatusChange(i, i2);
                }
            };
        }
    }

    public class a implements IDiLinkBacklightCtrl {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private DisplayManager.BacklightCtrl f1769a;

        public a(DisplayManager.BacklightCtrl backlightCtrl) {
            if (backlightCtrl == null && DiLink.isNotSelfCheckInnerTest()) {
                throw new IllegalArgumentException("backlightCtrl is null");
            }
            this.f1769a = backlightCtrl;
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOff() {
            this.f1769a.turnBacklightOff();
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOn() {
            this.f1769a.turnBacklightOn();
        }
    }

    public class b implements IDiLinkBacklightCtrl {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private PowerManager.BacklightCtrl f1771a;

        public b(PowerManager.BacklightCtrl backlightCtrl) {
            if (backlightCtrl == null && DiLink.isNotSelfCheckInnerTest()) {
                throw new IllegalArgumentException("backlightCtrl is null");
            }
            this.f1771a = backlightCtrl;
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOff() {
            this.f1771a.turnBacklightOff();
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOn() {
            this.f1771a.turnBacklightOn();
        }
    }

    public DiLinkDisplayManager(Context context) {
        this.f1764a = (DisplayManager) context.getSystemService("display");
        this.f1765b = (PowerManager) context.getSystemService(Car.POWER_SERVICE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(DisplayManager.OnekeyStatusListener onekeyStatusListener) {
        this.f1764a.unregisterOnekeyStatusListener(onekeyStatusListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(Handler handler, DisplayManager.OnekeyStatusListener onekeyStatusListener) {
        this.f1764a.registerOnekeyStatusListener(onekeyStatusListener, handler);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    @NonNull
    public IDiLinkBacklightCtrl createBackLightCtrl(@NonNull String str) {
        return new b(this.f1765b.createBackLightCtrl(str));
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    @NonNull
    public IDiLinkBacklightCtrl createBackLightCtrl(@NonNull String str, @NonNull int i) {
        return new a(this.f1764a.createBackLightCtrl(str, i));
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public int getPowerScreenStatus() {
        return this.f1765b.getPowerScreenStatus();
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public int getPowerScreenStatus(int i) {
        return this.f1764a.getPowerScreenStatus(i);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void registerOnekeyStatusListener(@NonNull IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener, @Nullable final Handler handler) {
        this.f1766c.addCallbackIgnoreRemoteException(iDiLinkOnekeyStatusListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.hardware.display.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1774a.b(handler, (DisplayManager.OnekeyStatusListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void setBacklightMode(int i, int i2) {
        this.f1764a.setBacklightMode(i, i2);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOff(int i, long j) {
        this.f1764a.turnBacklightOff(i, j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOff(long j) {
        this.f1765b.turnBacklightOff(j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOn(int i, long j) {
        this.f1764a.turnBacklightOn(i, j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOn(long j) {
        this.f1765b.turnBacklightOn(j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void unregisterOnekeyStatusListener(@NonNull IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener) {
        this.f1766c.removeCallbackIgnoreRemoteException(iDiLinkOnekeyStatusListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.hardware.display.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1773a.a((DisplayManager.OnekeyStatusListener) obj);
            }
        });
    }
}
