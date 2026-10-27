package spi;

import android.widget.Toast;
import com.blankj.utilcode.util.Utils;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ya implements Runnable {
    public final /* synthetic */ String a;
    public final /* synthetic */ int b;

    public /* synthetic */ ya(String str, int i) {
        this.a = str;
        this.b = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str = this.a;
        int i = this.b;
        Toast toast = za.a;
        if (toast != null) {
            toast.cancel();
            za.a = null;
        }
        Toast toastMakeText = Toast.makeText(Utils.getApp(), str, i);
        za.a = toastMakeText;
        toastMakeText.show();
    }
}
