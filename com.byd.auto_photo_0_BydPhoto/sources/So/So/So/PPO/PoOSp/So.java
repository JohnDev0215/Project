package So.So.So.PPO.PoOSp;

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
@Platform(versions = {DiLink.VERSION_NAME_FSE_RUI, DiLink.VERSION_NAME_FSE_5_0, DiLink.VERSION_NAME_FSE_5_1})
@ServiceImpl(service = IDiLinkDisplayManager.class, singleton = true)
public class So implements IDiLinkDisplayManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DisplayManager f136a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private PowerManager f137b;

    public class a implements IDiLinkBacklightCtrl {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private PowerManager.BacklightCtrl f138a;

        public a(PowerManager.BacklightCtrl backlightCtrl) {
            this.f138a = backlightCtrl;
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOff() {
            this.f138a.turnBacklightOff();
        }

        @Override // com.byd.dilink.hardware.display.IDiLinkBacklightCtrl
        public void turnBacklightOn() {
            this.f138a.turnBacklightOn();
        }
    }

    public So(Context context) {
        this.f136a = (DisplayManager) context.getSystemService("display");
        this.f137b = (PowerManager) context.getSystemService(Car.POWER_SERVICE);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    @NonNull
    public IDiLinkBacklightCtrl createBackLightCtrl(@NonNull String str) {
        return new a(this.f137b.createBackLightCtrl(str));
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    @NonNull
    public IDiLinkBacklightCtrl createBackLightCtrl(@NonNull String str, @NonNull int i) {
        throw new UnsupportedTargetPlatformException("FSE 没有 createBackLightCtrl 这个函数！");
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public int getPowerScreenStatus() {
        return this.f137b.getPowerScreenStatus();
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public int getPowerScreenStatus(int i) {
        throw new UnsupportedTargetPlatformException("FSE 没有 getPowerScreenStatus 这个函数！");
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void registerOnekeyStatusListener(@NonNull IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener, @Nullable Handler handler) {
        throw new UnsupportedTargetPlatformException("FSE 没有 registerOnekeyStatusListener 这个函数！");
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void setBacklightMode(int i, int i2) {
        throw new UnsupportedTargetPlatformException("FSE 没有 setBacklightMode 方法！");
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOff(int i, long j) {
        throw new UnsupportedTargetPlatformException("FSE 没有 turnBacklightOff 这个函数！");
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOff(long j) {
        this.f137b.turnBacklightOff(j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOn(int i, long j) {
        throw new UnsupportedTargetPlatformException("FSE 没有 turnBacklightOn 这个函数！");
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void turnBacklightOn(long j) {
        this.f137b.turnBacklightOn(j);
    }

    @Override // com.byd.dilink.hardware.display.IDiLinkDisplayManager
    public void unregisterOnekeyStatusListener(@NonNull IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener) {
        throw new UnsupportedTargetPlatformException("FSE 没有 unregisterOnekeyStatusListener 这个函数！");
    }
}
