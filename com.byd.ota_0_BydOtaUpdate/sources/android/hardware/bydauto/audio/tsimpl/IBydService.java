package android.hardware.bydauto.audio.tsimpl;

/* JADX INFO: loaded from: classes.dex */
public interface IBydService {
    boolean isServiceConnected();

    boolean registerConnectionListener(IServiceConnectionListener iServiceConnectionListener);

    boolean unregisterConnectionListener(IServiceConnectionListener iServiceConnectionListener);
}
