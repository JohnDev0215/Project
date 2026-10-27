package k3;

import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import l3.C1752h;
import l3.C1753i;
import l3.C1754j;

/* JADX INFO: loaded from: classes.dex */
public final class E0 extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f17755a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f17756b;
    public Object c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public E0(I0 i02, Looper looper) {
        super(looper);
        this.c = i02;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        switch (this.f17755a) {
            case 1:
                WeakReference weakReference = (WeakReference) this.c;
                if (weakReference == null) {
                    return;
                }
                Messenger messenger = (Messenger) weakReference.get();
                C1752h c1752h = (C1752h) ((WeakReference) this.f17756b).get();
                if (messenger == null || c1752h == null) {
                    return;
                }
                Bundle data = message.getData();
                l3.P.o(data);
                try {
                    int i7 = message.what;
                    if (i7 == 1) {
                        l3.P.o(data.getBundle("data_root_hints"));
                        data.getString("data_media_item_id");
                        return;
                    }
                    if (i7 != 2) {
                        if (i7 != 3) {
                            h6.n.t("MediaBrowserCompat", "Unhandled message: " + message + "\n  Client version: 1\n  Service version: " + message.arg1);
                            return;
                        }
                        l3.P.o(data.getBundle("data_options"));
                        l3.P.o(data.getBundle("data_notify_children_changed_options"));
                        String string = data.getString("data_media_item_id");
                        ArrayList parcelableArrayList = data.getParcelableArrayList("data_media_item_list");
                        Parcelable.Creator<C1753i> creator = C1753i.CREATOR;
                        if (parcelableArrayList != null) {
                            ArrayList arrayList = new ArrayList();
                            for (int i10 = 0; i10 < parcelableArrayList.size(); i10++) {
                                arrayList.add(h6.n.g((Parcelable) parcelableArrayList.get(i10), creator));
                            }
                        }
                        if (c1752h.f18574g != messenger) {
                            return;
                        }
                        if (string != null && c1752h.f18572e.get(string) != null) {
                            throw new ClassCastException();
                        }
                        if (C1754j.f18577b) {
                            Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + string);
                            return;
                        }
                        return;
                    }
                    return;
                } catch (BadParcelableException unused) {
                    h6.n.j("MediaBrowserCompat", "Could not unparcel the data.");
                    return;
                }
            default:
                super.handleMessage(message);
                return;
        }
    }

    public E0(C1752h c1752h) {
        this.f17756b = new WeakReference(c1752h);
    }
}
