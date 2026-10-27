package androidx.mediarouter.media;

import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import androidx.mediarouter.media.h;

/* JADX INFO: compiled from: MediaRoute2ProviderServiceAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends h.c {
    public final /* synthetic */ Messenger a;
    public final /* synthetic */ int b;

    public b(String str, Intent intent, Messenger messenger, int i) {
        this.a = messenger;
        this.b = i;
    }

    public static void c(Messenger messenger, int i, int i2, Object obj, Bundle bundle) {
        Message messageObtain = Message.obtain();
        messageObtain.what = i;
        messageObtain.arg1 = i2;
        messageObtain.arg2 = 0;
        messageObtain.obj = obj;
        messageObtain.setData(bundle);
        try {
            messenger.send(messageObtain);
        } catch (DeadObjectException unused) {
        } catch (RemoteException e) {
            Log.e("MR2ProviderService", "Could not send message to the client.", e);
        }
    }

    @Override // androidx.mediarouter.media.h.c
    public final void a(String str, Bundle bundle) {
        int i = c.j;
        int i2 = this.b;
        Messenger messenger = this.a;
        if (str == null) {
            c(messenger, 4, i2, bundle, null);
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("error", str);
        c(messenger, 4, i2, bundle, bundle2);
    }

    @Override // androidx.mediarouter.media.h.c
    public final void b(Bundle bundle) {
        int i = c.j;
        c(this.a, 3, this.b, bundle, null);
    }
}
