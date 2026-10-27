package o2;

import java.util.List;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.data.CarPlayDevice;

/* JADX INFO: compiled from: IDataService.java */
/* JADX INFO: loaded from: classes.dex */
public interface a {
    List<AndroidAutoDevice> getAndroidAutoDevices();

    List<CarPlayDevice> getCarPlayDevices();
}
