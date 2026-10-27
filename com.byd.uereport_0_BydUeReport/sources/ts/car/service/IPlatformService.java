package ts.car.service;

/* JADX INFO: loaded from: classes2.dex */
public interface IPlatformService {
    boolean isServiceConnected();

    boolean registerConnectionListener(IServiceConnectionListener iServiceConnectionListener);

    String serviceName();

    boolean unregisterConnectionListener(IServiceConnectionListener iServiceConnectionListener);
}
