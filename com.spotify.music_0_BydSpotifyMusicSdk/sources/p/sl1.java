package p;

import android.os.IInterface;
import android.os.RemoteCallbackList;
import androidx.room.MultiInstanceInvalidationService;

/* JADX INFO: loaded from: classes.dex */
public final class sl1 extends RemoteCallbackList {
    public final /* synthetic */ MultiInstanceInvalidationService a;

    public sl1(MultiInstanceInvalidationService multiInstanceInvalidationService) {
        this.a = multiInstanceInvalidationService;
    }

    @Override // android.os.RemoteCallbackList
    public final void onCallbackDied(IInterface iInterface, Object obj) {
        v03.j((v21) iInterface, "callback");
        v03.j(obj, "cookie");
        this.a.e.remove((Integer) obj);
    }
}
