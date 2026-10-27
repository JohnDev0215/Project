package o2;

/* JADX INFO: compiled from: IJumpService.java */
/* JADX INFO: loaded from: classes.dex */
public interface b {

    /* JADX INFO: compiled from: IJumpService.java */
    public interface a {
        void onOkResult(Object obj);
    }

    void jump2DeleteWarningDialog(String str, String str2, a aVar);

    void jump2DeviceTipsDialog();

    void jump2DisconnectWarningDialog(String str, a aVar);

    void jump2OffBtWarningDialog(a aVar);

    void jump2OffHotspotWarningDialog(int i8, String str, a aVar);

    void jump2SwitchWarningDialog(String str, a aVar);
}
