package a1;

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
    public static final Object f40a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static a f41b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f42c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashMap<BroadcastReceiver, ArrayList<c>> f43d = new HashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final HashMap<String, ArrayList<c>> f44e = new HashMap<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList<b> f45f = new ArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Handler f46g;

    /* JADX INFO: renamed from: a1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LocalBroadcastManager.java */
    public class HandlerC0001a extends Handler {
        public HandlerC0001a(Looper looper) {
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
        public final Intent f48a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final ArrayList<c> f49b;

        public b(Intent intent, ArrayList<c> arrayList) {
            this.f48a = intent;
            this.f49b = arrayList;
        }
    }

    /* JADX INFO: compiled from: LocalBroadcastManager.java */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final IntentFilter f50a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final BroadcastReceiver f51b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f52c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f53d;

        public c(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
            this.f50a = intentFilter;
            this.f51b = broadcastReceiver;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Receiver{");
            sb.append(this.f51b);
            sb.append(" filter=");
            sb.append(this.f50a);
            if (this.f53d) {
                sb.append(" DEAD");
            }
            sb.append("}");
            return sb.toString();
        }
    }

    public a(Context context) {
        this.f42c = context;
        this.f46g = new HandlerC0001a(context.getMainLooper());
    }

    public static a b(Context context) {
        a aVar;
        synchronized (f40a) {
            if (f41b == null) {
                f41b = new a(context.getApplicationContext());
            }
            aVar = f41b;
        }
        return aVar;
    }

    public void a() {
        int size;
        b[] bVarArr;
        while (true) {
            synchronized (this.f43d) {
                size = this.f45f.size();
                if (size <= 0) {
                    return;
                }
                bVarArr = new b[size];
                this.f45f.toArray(bVarArr);
                this.f45f.clear();
            }
            for (int i8 = 0; i8 < size; i8++) {
                b bVar = bVarArr[i8];
                int size2 = bVar.f49b.size();
                for (int i9 = 0; i9 < size2; i9++) {
                    c cVar = bVar.f49b.get(i9);
                    if (!cVar.f53d) {
                        cVar.f51b.onReceive(this.f42c, bVar.f48a);
                    }
                }
            }
        }
    }

    public void c(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        synchronized (this.f43d) {
            c cVar = new c(intentFilter, broadcastReceiver);
            ArrayList<c> arrayList = this.f43d.get(broadcastReceiver);
            if (arrayList == null) {
                arrayList = new ArrayList<>(1);
                this.f43d.put(broadcastReceiver, arrayList);
            }
            arrayList.add(cVar);
            for (int i8 = 0; i8 < intentFilter.countActions(); i8++) {
                String action = intentFilter.getAction(i8);
                ArrayList<c> arrayList2 = this.f44e.get(action);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>(1);
                    this.f44e.put(action, arrayList2);
                }
                arrayList2.add(cVar);
            }
        }
    }

    public boolean d(Intent intent) {
        int i8;
        String str;
        ArrayList arrayList;
        ArrayList<c> arrayList2;
        String str2;
        synchronized (this.f43d) {
            String action = intent.getAction();
            String strResolveTypeIfNeeded = intent.resolveTypeIfNeeded(this.f42c.getContentResolver());
            Uri data = intent.getData();
            String scheme = intent.getScheme();
            Set<String> categories = intent.getCategories();
            boolean z7 = (intent.getFlags() & 8) != 0;
            if (z7) {
                Log.v("LocalBroadcastManager", "Resolving type " + strResolveTypeIfNeeded + " scheme " + scheme + " of intent " + intent);
            }
            ArrayList<c> arrayList3 = this.f44e.get(intent.getAction());
            if (arrayList3 != null) {
                if (z7) {
                    Log.v("LocalBroadcastManager", "Action list: " + arrayList3);
                }
                ArrayList arrayList4 = null;
                int i9 = 0;
                while (i9 < arrayList3.size()) {
                    c cVar = arrayList3.get(i9);
                    if (z7) {
                        Log.v("LocalBroadcastManager", "Matching against filter " + cVar.f50a);
                    }
                    if (cVar.f52c) {
                        if (z7) {
                            Log.v("LocalBroadcastManager", "  Filter's target already added");
                        }
                        i8 = i9;
                        arrayList2 = arrayList3;
                        str = action;
                        str2 = strResolveTypeIfNeeded;
                        arrayList = arrayList4;
                    } else {
                        i8 = i9;
                        str = action;
                        arrayList = arrayList4;
                        arrayList2 = arrayList3;
                        str2 = strResolveTypeIfNeeded;
                        int iMatch = cVar.f50a.match(action, strResolveTypeIfNeeded, scheme, data, categories, "LocalBroadcastManager");
                        if (iMatch >= 0) {
                            if (z7) {
                                Log.v("LocalBroadcastManager", "  Filter matched!  match=0x" + Integer.toHexString(iMatch));
                            }
                            arrayList4 = arrayList == null ? new ArrayList() : arrayList;
                            arrayList4.add(cVar);
                            cVar.f52c = true;
                            i9 = i8 + 1;
                            action = str;
                            arrayList3 = arrayList2;
                            strResolveTypeIfNeeded = str2;
                        } else if (z7) {
                            Log.v("LocalBroadcastManager", "  Filter did not match: " + (iMatch != -4 ? iMatch != -3 ? iMatch != -2 ? iMatch != -1 ? "unknown reason" : "type" : "data" : "action" : "category"));
                        }
                    }
                    arrayList4 = arrayList;
                    i9 = i8 + 1;
                    action = str;
                    arrayList3 = arrayList2;
                    strResolveTypeIfNeeded = str2;
                }
                ArrayList arrayList5 = arrayList4;
                if (arrayList5 != null) {
                    for (int i10 = 0; i10 < arrayList5.size(); i10++) {
                        ((c) arrayList5.get(i10)).f52c = false;
                    }
                    this.f45f.add(new b(intent, arrayList5));
                    if (!this.f46g.hasMessages(1)) {
                        this.f46g.sendEmptyMessage(1);
                    }
                    return true;
                }
            }
            return false;
        }
    }

    public void e(BroadcastReceiver broadcastReceiver) {
        synchronized (this.f43d) {
            ArrayList<c> arrayListRemove = this.f43d.remove(broadcastReceiver);
            if (arrayListRemove == null) {
                return;
            }
            for (int size = arrayListRemove.size() - 1; size >= 0; size--) {
                c cVar = arrayListRemove.get(size);
                cVar.f53d = true;
                for (int i8 = 0; i8 < cVar.f50a.countActions(); i8++) {
                    String action = cVar.f50a.getAction(i8);
                    ArrayList<c> arrayList = this.f44e.get(action);
                    if (arrayList != null) {
                        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                            c cVar2 = arrayList.get(size2);
                            if (cVar2.f51b == broadcastReceiver) {
                                cVar2.f53d = true;
                                arrayList.remove(size2);
                            }
                        }
                        if (arrayList.size() <= 0) {
                            this.f44e.remove(action);
                        }
                    }
                }
            }
        }
    }
}
