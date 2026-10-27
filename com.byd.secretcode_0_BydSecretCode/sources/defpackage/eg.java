package defpackage;

import android.hardware.bydauto.test.bean.NetworkOperator;
import com.byd.secretcode.R;

/* JADX INFO: compiled from: NetworkOperatorBean.java */
/* JADX INFO: loaded from: classes.dex */
public class eg {
    public NetworkOperator a;
    public int b;

    public int a() {
        return this.b;
    }

    public int b() {
        int i = this.b;
        return i != 1 ? i != 2 ? i != 3 ? R.string.str_network_state_default : R.string.str_network_state_unable : R.string.str_network_state_unconnected : R.string.str_network_state_connected;
    }

    public NetworkOperator c() {
        return this.a;
    }

    public void d(int i) {
        this.b = i;
    }

    public void e(NetworkOperator networkOperator) {
        this.a = networkOperator;
    }
}
