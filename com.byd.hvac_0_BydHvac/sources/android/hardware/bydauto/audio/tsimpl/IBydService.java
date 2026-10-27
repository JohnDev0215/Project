package android.hardware.bydauto.audio.tsimpl;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface IBydService {
    boolean isServiceConnected();

    boolean registerConnectionListener(IServiceConnectionListener iServiceConnectionListener);

    boolean unregisterConnectionListener(IServiceConnectionListener iServiceConnectionListener);
}
