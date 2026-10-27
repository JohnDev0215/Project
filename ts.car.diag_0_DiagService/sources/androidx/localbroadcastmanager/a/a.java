package androidx.localbroadcastmanager.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public final class a {
    private static final Object e = new Object();
    private static a f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f786a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final HashMap<BroadcastReceiver, ArrayList<c>> f787b = new HashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final HashMap<String, ArrayList<c>> f788c = new HashMap<>();
    private final ArrayList<b> d = new ArrayList<>();

    /* JADX INFO: renamed from: androidx.localbroadcastmanager.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    class HandlerC0027a extends Handler {
        HandlerC0027a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                super.handleMessage(message);
            } else {
                a.this.a();
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Intent f790a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final ArrayList<c> f791b;
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final IntentFilter f792a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final BroadcastReceiver f793b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f794c;

        c(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
            this.f792a = intentFilter;
            this.f793b = broadcastReceiver;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Receiver{");
            sb.append(this.f793b);
            sb.append(" filter=");
            sb.append(this.f792a);
            if (this.f794c) {
                sb.append(" DEAD");
            }
            sb.append("}");
            return sb.toString();
        }
    }

    private a(Context context) {
        this.f786a = context;
        new HandlerC0027a(context.getMainLooper());
    }

    @NonNull
    public static a b(@NonNull Context context) {
        a aVar;
        synchronized (e) {
            if (f == null) {
                f = new a(context.getApplicationContext());
            }
            aVar = f;
        }
        return aVar;
    }

    void a() {
        int size;
        b[] bVarArr;
        while (true) {
            synchronized (this.f787b) {
                size = this.d.size();
                if (size <= 0) {
                    return;
                }
                bVarArr = new b[size];
                this.d.toArray(bVarArr);
                this.d.clear();
            }
            for (int i = 0; i < size; i++) {
                b bVar = bVarArr[i];
                int size2 = bVar.f791b.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    c cVar = bVar.f791b.get(i2);
                    if (!cVar.f794c) {
                        cVar.f793b.onReceive(this.f786a, bVar.f790a);
                    }
                }
            }
        }
    }

    public void c(@NonNull BroadcastReceiver broadcastReceiver, @NonNull IntentFilter intentFilter) {
        synchronized (this.f787b) {
            c cVar = new c(intentFilter, broadcastReceiver);
            ArrayList<c> arrayList = this.f787b.get(broadcastReceiver);
            if (arrayList == null) {
                arrayList = new ArrayList<>(1);
                this.f787b.put(broadcastReceiver, arrayList);
            }
            arrayList.add(cVar);
            for (int i = 0; i < intentFilter.countActions(); i++) {
                String action = intentFilter.getAction(i);
                ArrayList<c> arrayList2 = this.f788c.get(action);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>(1);
                    this.f788c.put(action, arrayList2);
                }
                arrayList2.add(cVar);
            }
        }
    }

    public void d(@NonNull BroadcastReceiver broadcastReceiver) {
        synchronized (this.f787b) {
            ArrayList<c> arrayListRemove = this.f787b.remove(broadcastReceiver);
            if (arrayListRemove == null) {
                return;
            }
            for (int size = arrayListRemove.size() - 1; size >= 0; size--) {
                c cVar = arrayListRemove.get(size);
                cVar.f794c = true;
                for (int i = 0; i < cVar.f792a.countActions(); i++) {
                    String action = cVar.f792a.getAction(i);
                    ArrayList<c> arrayList = this.f788c.get(action);
                    if (arrayList != null) {
                        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                            c cVar2 = arrayList.get(size2);
                            if (cVar2.f793b == broadcastReceiver) {
                                cVar2.f794c = true;
                                arrayList.remove(size2);
                            }
                        }
                        if (arrayList.size() <= 0) {
                            this.f788c.remove(action);
                        }
                    }
                }
            }
        }
    }
}
