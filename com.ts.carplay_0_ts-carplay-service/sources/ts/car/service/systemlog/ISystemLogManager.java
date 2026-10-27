package ts.car.service.systemlog;

import android.os.RemoteException;
import java.util.List;
import java.util.Map;
import ts.car.service.IPlatformService;
import ts.car.service.systemlog.config.LogConfig;

/* JADX INFO: loaded from: classes.dex */
public interface ISystemLogManager extends IPlatformService {
    public static final String PERMISSION_SYSTEMLOG = "car.permission.ACCESS_LOG_ADMIN";

    int cleanAll() throws RemoteException;

    int cleanLog(String str) throws RemoteException;

    int cleanLog(List<LogConfig> list) throws RemoteException;

    int exportAll() throws RemoteException;

    int exportLog(String str) throws RemoteException;

    int exportLog(List<LogConfig> list) throws RemoteException;

    LogConfig getLogConfig(String str) throws RemoteException;

    List<LogConfig> getLogConfigList() throws RemoteException;

    void registerListener(IProcessStateCallback iProcessStateCallback) throws RemoteException;

    int setLogLevel(String str, int i) throws RemoteException;

    int setLogLevel(Map<String, Integer> map) throws RemoteException;

    int startCollecting(String str) throws RemoteException;

    int startCollectingAll() throws RemoteException;

    int stopCollecting(String str) throws RemoteException;

    int stopCollectingAll() throws RemoteException;

    void unregisterListener(IProcessStateCallback iProcessStateCallback) throws RemoteException;
}
