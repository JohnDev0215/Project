package b.p.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/* JADX INFO: compiled from: LocalBroadcastManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f2984a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static a f2985b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f2986c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashMap<BroadcastReceiver, ArrayList<c>> f2987d = new HashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final HashMap<String, ArrayList<c>> f2988e = new HashMap<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList<b> f2989f = new ArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Handler f2990g;

    /* JADX INFO: renamed from: b.p.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LocalBroadcastManager.java */
    public class HandlerC0055a extends Handler {
        public HandlerC0055a(Looper looper) {
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

    /* JADX INFO: compiled from: LocalBroadcastManager.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Intent f2992a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final ArrayList<c> f2993b;

        public b(Intent intent, ArrayList<c> arrayList) {
            this.f2992a = intent;
            this.f2993b = arrayList;
        }
    }

    /* JADX INFO: compiled from: LocalBroadcastManager.java */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final IntentFilter f2994a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final BroadcastReceiver f2995b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f2996c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f2997d;

        public c(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
            this.f2994a = intentFilter;
            this.f2995b = broadcastReceiver;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Receiver{");
            sb.append(this.f2995b);
            sb.append(" filter=");
            sb.append(this.f2994a);
            if (this.f2997d) {
                sb.append(" DEAD");
            }
            sb.append("}");
            return sb.toString();
        }
    }

    public a(Context context) {
        this.f2986c = context;
        this.f2990g = new HandlerC0055a(context.getMainLooper());
    }

    public static a b(Context context) {
        a aVar;
        synchronized (f2984a) {
            if (f2985b == null) {
                f2985b = new a(context.getApplicationContext());
            }
            aVar = f2985b;
        }
        return aVar;
    }

    public void a() {
        int size;
        b[] bVarArr;
        while (true) {
            synchronized (this.f2987d) {
                size = this.f2989f.size();
                if (size <= 0) {
                    return;
                }
                bVarArr = new b[size];
                this.f2989f.toArray(bVarArr);
                this.f2989f.clear();
            }
            for (int i2 = 0; i2 < size; i2++) {
                b bVar = bVarArr[i2];
                int size2 = bVar.f2993b.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    c cVar = bVar.f2993b.get(i3);
                    if (!cVar.f2997d) {
                        cVar.f2995b.onReceive(this.f2986c, bVar.f2992a);
                    }
                }
            }
        }
    }

    public void c(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        synchronized (this.f2987d) {
            c cVar = new c(intentFilter, broadcastReceiver);
            ArrayList<c> arrayList = this.f2987d.get(broadcastReceiver);
            if (arrayList == null) {
                arrayList = new ArrayList<>(1);
                this.f2987d.put(broadcastReceiver, arrayList);
            }
            arrayList.add(cVar);
            for (int i2 = 0; i2 < intentFilter.countActions(); i2++) {
                String action = intentFilter.getAction(i2);
                ArrayList<c> arrayList2 = this.f2988e.get(action);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>(1);
                    this.f2988e.put(action, arrayList2);
                }
                arrayList2.add(cVar);
            }
        }
    }

    public boolean d(Intent intent) {
        int i2;
        String str;
        ArrayList arrayList;
        ArrayList<c> arrayList2;
        String str2;
        synchronized (this.f2987d) {
            String action = intent.getAction();
            String strResolveTypeIfNeeded = intent.resolveTypeIfNeeded(this.f2986c.getContentResolver());
            Uri data = intent.getData();
            String scheme = intent.getScheme();
            Set<String> categories = intent.getCategories();
            boolean z = (intent.getFlags() & 8) != 0;
            if (z) {
                Log.v("LocalBroadcastManager", "Resolving type " + strResolveTypeIfNeeded + " scheme " + scheme + " of intent " + intent);
            }
            ArrayList<c> arrayList3 = this.f2988e.get(intent.getAction());
            if (arrayList3 != null) {
                if (z) {
                    Log.v("LocalBroadcastManager", "Action list: " + arrayList3);
                }
                ArrayList arrayList4 = null;
                int i3 = 0;
                while (i3 < arrayList3.size()) {
                    c cVar = arrayList3.get(i3);
                    if (z) {
                        Log.v("LocalBroadcastManager", "Matching against filter " + cVar.f2994a);
                    }
                    if (cVar.f2996c) {
                        if (z) {
                            Log.v("LocalBroadcastManager", "  Filter's target already added");
                        }
                        i2 = i3;
                        arrayList2 = arrayList3;
                        str = action;
                        str2 = strResolveTypeIfNeeded;
                        arrayList = arrayList4;
                    } else {
                        i2 = i3;
                        str = action;
                        arrayList = arrayList4;
                        arrayList2 = arrayList3;
                        str2 = strResolveTypeIfNeeded;
                        int iMatch = cVar.f2994a.match(action, strResolveTypeIfNeeded, scheme, data, categories, "LocalBroadcastManager");
                        if (iMatch >= 0) {
                            if (z) {
                                Log.v("LocalBroadcastManager", "  Filter matched!  match=0x" + Integer.toHexString(iMatch));
                            }
                            arrayList4 = arrayList == null ? new ArrayList() : arrayList;
                            arrayList4.add(cVar);
                            cVar.f2996c = true;
                            i3 = i2 + 1;
                            action = str;
                            arrayList3 = arrayList2;
                            strResolveTypeIfNeeded = str2;
                        } else if (z) {
                            Log.v("LocalBroadcastManager", "  Filter did not match: " + (iMatch != -4 ? iMatch != -3 ? iMatch != -2 ? iMatch != -1 ? "unknown reason" : "type" : "data" : "action" : "category"));
                        }
                    }
                    arrayList4 = arrayList;
                    i3 = i2 + 1;
                    action = str;
                    arrayList3 = arrayList2;
                    strResolveTypeIfNeeded = str2;
                }
                ArrayList arrayList5 = arrayList4;
                if (arrayList5 != null) {
                    for (int i4 = 0; i4 < arrayList5.size(); i4++) {
                        ((c) arrayList5.get(i4)).f2996c = false;
                    }
                    this.f2989f.add(new b(intent, arrayList5));
                    if (!this.f2990g.hasMessages(1)) {
                        this.f2990g.sendEmptyMessage(1);
                    }
                    return true;
                }
            }
            return false;
        }
    }

    public void e(BroadcastReceiver broadcastReceiver) {
        synchronized (this.f2987d) {
            ArrayList<c> arrayListRemove = this.f2987d.remove(broadcastReceiver);
            if (arrayListRemove == null) {
                return;
            }
            for (int size = arrayListRemove.size() - 1; size >= 0; size--) {
                c cVar = arrayListRemove.get(size);
                cVar.f2997d = true;
                for (int i2 = 0; i2 < cVar.f2994a.countActions(); i2++) {
                    String action = cVar.f2994a.getAction(i2);
                    ArrayList<c> arrayList = this.f2988e.get(action);
                    if (arrayList != null) {
                        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                            c cVar2 = arrayList.get(size2);
                            if (cVar2.f2995b == broadcastReceiver) {
                                cVar2.f2997d = true;
                                arrayList.remove(size2);
                            }
                        }
                        if (arrayList.size() <= 0) {
                            this.f2988e.remove(action);
                        }
                    }
                }
            }
        }
    }
}
