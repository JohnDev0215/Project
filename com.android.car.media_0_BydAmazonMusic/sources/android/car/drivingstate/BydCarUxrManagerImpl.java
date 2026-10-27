package android.car.drivingstate;

import android.car.XCarRoute;
import android.car.utils.Logs;
import android.car.utils.SystemPropertiesUtils;
import android.content.Context;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import com.alibaba.android.arouter.facade.annotation.Route;
import java.util.Objects;
import spi.c;

/* JADX INFO: loaded from: classes.dex */
@Route(path = XCarRoute.CAR_UXR_SERVICE)
public class BydCarUxrManagerImpl extends BydBaseCarUxrManagerImpl {
    private static final int GEAR_BOX_8155F_P = 1;
    private static final String TAG = "BydCarUxrManagerImpl_8155";
    private transient AbsBYDAutoGearboxListener absBYDAutoGearboxListener;
    private transient BYDAutoGearboxDevice bydAutoGearboxDevice;
    private transient boolean initGearBoxTag = false;
    private transient Context mContext;

    @Override // android.car.drivingstate.BydBaseCarUxrManagerImpl
    public int getGearboxType() {
        int gear;
        if (this.bydAutoGearboxDevice == null) {
            Logs.e(TAG, "bydAutoGearboxDevice is null");
            return -1;
        }
        String property = SystemPropertiesUtils.getProperty("persist.sys.energytype", "1");
        Logs.i(TAG, "energy type: " + property);
        Objects.requireNonNull(property);
        if (property.equals("1")) {
            gear = this.bydAutoGearboxDevice.getGear();
        } else {
            if (!property.equals("2")) {
                return -1;
            }
            gear = this.bydAutoGearboxDevice.getGearboxAutoModeLevel();
        }
        Logs.d(TAG, "getGearboxType " + gear);
        return gear;
    }

    @Override // android.car.drivingstate.BydBaseCarUxrManagerImpl
    public int getPDef() {
        return 1;
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        this.mContext = context;
    }

    @Override // android.car.drivingstate.IXCarUxRestrictionsManager
    public void initGearBox() {
        if (this.initGearBoxTag) {
            return;
        }
        this.initGearBoxTag = true;
        Logs.d(TAG, "BydCarUxrManagerImpl,init");
        try {
            this.haveClass = true;
        } catch (ClassNotFoundException e) {
            StringBuilder sbK = c.k("init ");
            sbK.append(e.getMessage());
            Logs.e(TAG, sbK.toString());
            this.haveClass = false;
        }
        if (this.haveClass) {
            try {
                this.bydAutoGearboxDevice = BYDAutoGearboxDevice.getInstance(this.mContext);
                AbsBYDAutoGearboxListener absBYDAutoGearboxListener = new AbsBYDAutoGearboxListener() { // from class: android.car.drivingstate.BydCarUxrManagerImpl.1
                    @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
                    public void onGearChanged(int i) {
                        super.onGearChanged(i);
                        Logs.d(AbsBYDAutoGearboxListener.TAG, "onGearChanged" + i);
                        BydCarUxrManagerImpl.this.onGearboxTypeChanged(i);
                    }

                    @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
                    public void onGearboxAutoModeLevelChanged(int i) {
                        super.onGearChanged(i);
                        Logs.d(AbsBYDAutoGearboxListener.TAG, "onGearboxAutoModeLevelChanged" + i);
                        BydCarUxrManagerImpl.this.onGearboxTypeChanged(i);
                    }
                };
                this.absBYDAutoGearboxListener = absBYDAutoGearboxListener;
                this.bydAutoGearboxDevice.registerListener(absBYDAutoGearboxListener);
            } catch (Exception e2) {
                this.bydAutoGearboxDevice = null;
                StringBuilder sbK2 = c.k("init...");
                sbK2.append(e2.getMessage());
                Logs.e(TAG, sbK2.toString());
            }
        }
    }

    @Override // android.car.drivingstate.BydBaseCarUxrManagerImpl
    public void onCarDisconnectedPlatform() {
        Logs.d(TAG, "BydCarUxrManagerImpl, onCarDisconnected()");
        if (this.haveClass) {
            this.bydAutoGearboxDevice.unregisterListener(this.absBYDAutoGearboxListener);
            this.absBYDAutoGearboxListener = null;
            this.bydAutoGearboxDevice = null;
        }
    }
}
