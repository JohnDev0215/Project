package ts.car.service.dvr;

import android.os.RemoteException;
import android.view.Surface;
import ts.car.service.IPlatformService;
import ts.car.service.dvr.data.PictureWaterMarkParameters;
import ts.car.service.dvr.data.RecordParameters;
import ts.car.service.dvr.data.TimeWaterMarkParameters;

/* JADX INFO: loaded from: classes.dex */
public interface IDvrManager extends IPlatformService {
    void flushMemoryVideo() throws RemoteException;

    int getDvrState() throws RemoteException;

    long getFreeStorage() throws RemoteException;

    int getImageQuality() throws RemoteException;

    PictureWaterMarkParameters getPictureWatermarkParameters(int i) throws RemoteException;

    RecordParameters getRecordParameters() throws RemoteException;

    TimeWaterMarkParameters getTimeWatermarkParameters(int i) throws RemoteException;

    void lockVideoFiles(String[] strArr) throws RemoteException;

    void prepare() throws RemoteException;

    int registerDvrStatusListener(IDvrStatusListener iDvrStatusListener) throws RemoteException;

    int registerRecordStatusListener(IDvrRecordStatusListener iDvrRecordStatusListener) throws RemoteException;

    void release() throws RemoteException;

    void resetFactory() throws RemoteException;

    int setImageQuality(int i) throws RemoteException;

    void setPictureWatermarkParameters(PictureWaterMarkParameters pictureWaterMarkParameters) throws RemoteException;

    void setRecordParameters(RecordParameters recordParameters) throws RemoteException;

    void setTimeWatermarkParameters(TimeWaterMarkParameters timeWaterMarkParameters) throws RemoteException;

    void startEmergencyRecord() throws RemoteException;

    void startPreview(Surface surface) throws RemoteException;

    void startRecord() throws RemoteException;

    void stopEmergencyRecord() throws RemoteException;

    void stopPreview() throws RemoteException;

    void stopRecord() throws RemoteException;

    void takePhoto() throws RemoteException;

    void unlockVideoFiles(String[] strArr) throws RemoteException;

    int unregisterDvrStatusListener(IDvrStatusListener iDvrStatusListener) throws RemoteException;

    int unregisterRecordStatusListener(IDvrRecordStatusListener iDvrRecordStatusListener) throws RemoteException;
}
