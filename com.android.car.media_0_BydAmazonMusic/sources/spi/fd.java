package spi;

import android.app.Application;
import android.widget.Toast;
import com.alibaba.android.arouter.facade.Postcard;

/* JADX INFO: compiled from: _ARouter.java */
/* JADX INFO: loaded from: classes.dex */
public final class fd implements Runnable {
    public final /* synthetic */ Postcard a;

    public fd(hd hdVar, Postcard postcard) {
        this.a = postcard;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Application application = hd.i;
        StringBuilder sbK = c.k("There's no route matched!\n Path = [");
        sbK.append(this.a.getPath());
        sbK.append("]\n Group = [");
        sbK.append(this.a.getGroup());
        sbK.append("]");
        Toast.makeText(application, sbK.toString(), 1).show();
    }
}
