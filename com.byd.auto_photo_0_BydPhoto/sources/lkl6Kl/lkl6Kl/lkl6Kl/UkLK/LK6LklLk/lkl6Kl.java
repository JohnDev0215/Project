package lkl6Kl.lkl6Kl.lkl6Kl.UkLK.LK6LklLk;

import android.car.Car;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.PowerManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.display.IDiLinkBacklightCtrl;
import com.byd.dilink.hardware.display.IDiLinkDisplayManager;
import com.byd.dilink.hardware.display.IDiLinkOnekeyStatusListener;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_4_0})
@ServiceImpl(service = IDiLinkDisplayManager.class, singleton = true)
public class lkl6Kl implements IDiLinkDisplayManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DisplayManager f3129a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private PowerManager f3130b;

    public class a implements IDiLinkBacklightCtrl {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private PowerManager.BacklightCtrl f3131a;

        public a(PowerManager.BacklightCtrl backlightCtrl) {
            if (backlightCtrl == null && DiLink.isNotSelfCheckInnerTest()) {
                throw new IllegalArgumentException("backlightCtrl is null");
            }
            this.f3131a = backlightCtrl;
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOff() {
            this.f3131a.TurnBacklightOff();
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOn() {
            this.f3131a.TurnBacklightOn();
        }
    }

    public lkl6Kl(Context context) {
        this.f3129a = (DisplayManager) context.getSystemService("display");
        this.f3130b = (PowerManager) context.getSystemService(Car.POWER_SERVICE);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    @NonNull
    public IDiLinkBacklightCtrl createBackLightCtrl(@NonNull String str) {
        return new a(this.f3130b.createBackLightCtrl(str));
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    @NonNull
    public IDiLinkBacklightCtrl createBackLightCtrl(@NonNull String str, @NonNull int i) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 createBackLightCtrl 方法！");
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public int getPowerScreenStatus() {
        return this.f3130b.getPowerScreenStatus();
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public int getPowerScreenStatus(int i) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 getPowerScreenStatus 方法！");
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void registerOnekeyStatusListener(@NonNull IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener, @Nullable Handler handler) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 registerOnekeyStatusListener 方法！");
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void setBacklightMode(int i, int i2) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 setBacklightMode 方法！");
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOff(int i, long j) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 turnBacklightOff 方法！");
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOff(long j) {
        this.f3130b.TurnBacklightOff(j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOn(int i, long j) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 turnBacklightOn 方法！");
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOn(long j) {
        this.f3130b.TurnBacklightOn(j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void unregisterOnekeyStatusListener(@NonNull IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 unregisterOnekeyStatusListener 方法！");
    }
}
