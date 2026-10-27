package p;

import android.media.session.MediaSessionManager;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class xh1 extends wh1 {
    @Override // p.vh1, p.uh1
    public final void b(gi1 gi1Var) {
    }

    @Override // p.vh1, p.uh1
    public final gi1 d() {
        MediaSessionManager.RemoteUserInfo currentControllerInfo = this.a.getCurrentControllerInfo();
        gi1 gi1Var = new gi1();
        String packageName = currentControllerInfo.getPackageName();
        if (packageName == null) {
            throw new NullPointerException("package shouldn't be null");
        }
        if (TextUtils.isEmpty(packageName)) {
            throw new IllegalArgumentException("packageName should be nonempty");
        }
        gi1Var.a = new hi1(currentControllerInfo.getPackageName(), currentControllerInfo.getPid(), currentControllerInfo.getUid());
        return gi1Var;
    }
}
