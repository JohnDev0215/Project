package ts.car.service.upgrade;

/* JADX INFO: loaded from: classes.dex */
public interface IUpgradeListener {
    void onUpgradeProgressChanged(String str, int i);

    void onUpgradeStatusChanged(String str, int i, int i2);
}
