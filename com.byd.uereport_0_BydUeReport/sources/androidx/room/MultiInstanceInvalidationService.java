package androidx.room;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import d1.d;
import d1.e;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class MultiInstanceInvalidationService extends Service {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1729d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final HashMap<Integer, String> f1730e = new HashMap<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final RemoteCallbackList<d> f1731f = new a();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final e.a f1732g = new b();

    public class a extends RemoteCallbackList<d> {
        public a() {
        }

        @Override // android.os.RemoteCallbackList
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onCallbackDied(d dVar, Object obj) {
            MultiInstanceInvalidationService.this.f1730e.remove(Integer.valueOf(((Integer) obj).intValue()));
        }
    }

    public class b extends e.a {
        public b() {
        }

        @Override // d1.e
        public int K(d dVar, String str) {
            if (str == null) {
                return 0;
            }
            synchronized (MultiInstanceInvalidationService.this.f1731f) {
                MultiInstanceInvalidationService multiInstanceInvalidationService = MultiInstanceInvalidationService.this;
                int i8 = multiInstanceInvalidationService.f1729d + 1;
                multiInstanceInvalidationService.f1729d = i8;
                if (multiInstanceInvalidationService.f1731f.register(dVar, Integer.valueOf(i8))) {
                    MultiInstanceInvalidationService.this.f1730e.put(Integer.valueOf(i8), str);
                    return i8;
                }
                MultiInstanceInvalidationService multiInstanceInvalidationService2 = MultiInstanceInvalidationService.this;
                multiInstanceInvalidationService2.f1729d--;
                return 0;
            }
        }

        @Override // d1.e
        public void d0(int i8, String[] strArr) {
            synchronized (MultiInstanceInvalidationService.this.f1731f) {
                String str = MultiInstanceInvalidationService.this.f1730e.get(Integer.valueOf(i8));
                if (str == null) {
                    Log.w("ROOM", "Remote invalidation client ID not registered");
                    return;
                }
                int iBeginBroadcast = MultiInstanceInvalidationService.this.f1731f.beginBroadcast();
                for (int i9 = 0; i9 < iBeginBroadcast; i9++) {
                    try {
                        int iIntValue = ((Integer) MultiInstanceInvalidationService.this.f1731f.getBroadcastCookie(i9)).intValue();
                        String str2 = MultiInstanceInvalidationService.this.f1730e.get(Integer.valueOf(iIntValue));
                        if (i8 != iIntValue && str.equals(str2)) {
                            try {
                                ((d) MultiInstanceInvalidationService.this.f1731f.getBroadcastItem(i9)).A(strArr);
                            } catch (RemoteException e8) {
                                Log.w("ROOM", "Error invoking a remote callback", e8);
                            }
                        }
                    } finally {
                        MultiInstanceInvalidationService.this.f1731f.finishBroadcast();
                    }
                }
            }
        }

        @Override // d1.e
        public void p0(d dVar, int i8) {
            synchronized (MultiInstanceInvalidationService.this.f1731f) {
                MultiInstanceInvalidationService.this.f1731f.unregister(dVar);
                MultiInstanceInvalidationService.this.f1730e.remove(Integer.valueOf(i8));
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f1732g;
    }
}
