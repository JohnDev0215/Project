package RR9QRrr.RR9QRrr.RR9QRrr.RRrr9r9.R9rQ9R;

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

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkDisplayManager.class, singleton = true)
public class RR9QRrr implements IDiLinkDisplayManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DisplayManager f112a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private PowerManager f113b;

    public class a implements IDiLinkBacklightCtrl {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private PowerManager.BacklightCtrl f114a;

        public a(PowerManager.BacklightCtrl backlightCtrl) {
            if (backlightCtrl == null && DiLink.isNotSelfCheckInnerTest()) {
                throw new IllegalArgumentException("backlightCtrl is null");
            }
            this.f114a = backlightCtrl;
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOff() {
            this.f114a.TurnBacklightOff();
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOn() {
            this.f114a.TurnBacklightOn();
        }
    }

    public RR9QRrr(Context context) {
        this.f112a = (DisplayManager) context.getSystemService("display");
        this.f113b = (PowerManager) context.getSystemService(Car.POWER_SERVICE);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    @NonNull
    public IDiLinkBacklightCtrl createBackLightCtrl(@NonNull String str) {
        return new a(this.f113b.createBackLightCtrl(str));
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    @NonNull
    public IDiLinkBacklightCtrl createBackLightCtrl(@NonNull String str, @NonNull int i) {
        throw new UnsupportedTargetPlatformException("rcs 没有 createBackLightCtrl！");
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public int getPowerScreenStatus() {
        return this.f113b.getPowerScreenStatus();
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public int getPowerScreenStatus(int i) {
        throw new UnsupportedTargetPlatformException("rcs 没有 getPowerScreenStatus！");
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void registerOnekeyStatusListener(@NonNull IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener, @Nullable Handler handler) {
        throw new UnsupportedTargetPlatformException("rcs 没有 registerOnekeyStatusListener方法！");
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void setBacklightMode(int i, int i2) {
        throw new UnsupportedTargetPlatformException("rcs 没有 setBacklightMode 方法！");
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOff(int i, long j) {
        throw new UnsupportedTargetPlatformException("rcs 没有 registerOnekeyStatusListener方法！");
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOff(long j) {
        this.f113b.turnBacklightOff(j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOn(int i, long j) {
        throw new UnsupportedTargetPlatformException("rcs 没有 registerOnekeyStatusListener方法！");
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOn(long j) {
        this.f113b.turnBacklightOn(j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void unregisterOnekeyStatusListener(@NonNull IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener) {
        throw new UnsupportedTargetPlatformException("rcs 没有 unregisterOnekeyStatusListener方法！");
    }
}
