package b.b.j;

import android.hardware.bydauto.digital.tsimpl.DigitalKeyBean;
import android.hardware.bydauto.digital.tsimpl.IDigitalKeyTboxListener;
import java.util.List;

/* JADX INFO: compiled from: AbsDigitalKeyTboxListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class b implements IDigitalKeyTboxListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1934a = "AbsDigitalKeyTboxListener";

    @Override // android.hardware.bydauto.digital.tsimpl.IDigitalKeyTboxListener
    public void onDeleteAllNfcKeyResult(int i, int i2) {
        b.b.a.f.f.b(this.f1934a, "onDeleteNfcKeyResult %d", Integer.valueOf(i2));
    }

    @Override // android.hardware.bydauto.digital.tsimpl.IDigitalKeyTboxListener
    public void onDeleteNfcKeyByKeyNoResult(String str, int i) {
        b.b.a.f.f.b(this.f1934a, "onDeleteNfcKeyByKeyNoResult %s  %d", str, Integer.valueOf(i));
    }

    @Override // android.hardware.bydauto.digital.tsimpl.IDigitalKeyTboxListener
    public void onDeleteOwnIphoneKeyResult(String str, int i) {
        b.b.a.f.f.b(this.f1934a, "onDeleteNfcKeyByKeyNoResult %s  %d", str, Integer.valueOf(i));
    }

    @Override // android.hardware.bydauto.digital.tsimpl.IDigitalKeyTboxListener
    public void onKeyListPageRefresh() {
        b.b.a.f.f.b(this.f1934a, "onKeyListPageRefresh", new Object[0]);
    }

    @Override // android.hardware.bydauto.digital.tsimpl.IDigitalKeyTboxListener
    public void onKeyListResponse(int i, List<DigitalKeyBean> list) {
        String str = this.f1934a;
        StringBuilder sb = new StringBuilder();
        sb.append("onKeyListResponse:size:");
        sb.append(list != null ? list.size() : 0);
        b.b.a.f.f.b(str, sb.toString(), new Object[0]);
    }
}
