package p;

import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class sf1 extends Handler {
    public final WeakReference a;
    public WeakReference b;

    public sf1(ag1 ag1Var) {
        this.a = new WeakReference(ag1Var);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        WeakReference weakReference = this.b;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        WeakReference weakReference2 = this.a;
        if (weakReference2.get() == null) {
            return;
        }
        Bundle data = message.getData();
        bi1.a(data);
        ag1 ag1Var = (ag1) weakReference2.get();
        Messenger messenger = (Messenger) this.b.get();
        try {
            int i = message.what;
            if (i == 1) {
                bi1.a(data.getBundle("data_root_hints"));
                data.getString("data_media_item_id");
                ag1Var.getClass();
                message = message;
            } else if (i == 2) {
                ag1Var.getClass();
                message = message;
            } else if (i != 3) {
                message.toString();
                message = message;
            } else {
                bi1.a(data.getBundle("data_options"));
                bi1.a(data.getBundle("data_notify_children_changed_options"));
                String string = data.getString("data_media_item_id");
                data.getParcelableArrayList("data_media_item_list");
                uf1 uf1Var = (uf1) ag1Var;
                if (uf1Var.g != messenger) {
                    message = message;
                } else {
                    u4.z(uf1Var.e.get(string));
                    message = cg1.b;
                }
            }
        } catch (BadParcelableException unused) {
            Log.e("MediaBrowserCompat", "Could not unparcel the data.");
            if (message.what == 1) {
                ag1Var.getClass();
            }
        }
    }
}
