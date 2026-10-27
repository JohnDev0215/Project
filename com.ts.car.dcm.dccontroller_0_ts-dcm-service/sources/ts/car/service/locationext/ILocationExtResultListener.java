package ts.car.service.locationext;

import ts.car.service.locationext.data.GnssExtAccSpeedData;
import ts.car.service.locationext.data.GnssExtAutoNaviData;
import ts.car.service.locationext.data.GnssExtGyroData;

/* JADX INFO: loaded from: classes.dex */
public interface ILocationExtResultListener {
    void onReportAccelerationSpeed(GnssExtAccSpeedData gnssExtAccSpeedData);

    void onReportAutoNaviData(GnssExtAutoNaviData gnssExtAutoNaviData);

    void onReportGyroData(GnssExtGyroData gnssExtGyroData);

    void onReportLocationMode(int i);

    void onReportNmea(String str);
}
