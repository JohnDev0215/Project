package androidx.room;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import androidx.room.e;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class MultiInstanceInvalidationService extends Service {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1302b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final HashMap<Integer, String> f1303c = new HashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final RemoteCallbackList<d> f1304d = new a();
    private final e.a e = new b();

    class a extends RemoteCallbackList<d> {
        a() {
        }

        @Override // android.os.RemoteCallbackList
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onCallbackDied(d dVar, Object obj) {
            MultiInstanceInvalidationService.this.f1303c.remove(Integer.valueOf(((Integer) obj).intValue()));
        }
    }

    class b extends e.a {
        b() {
        }

        @Override // androidx.room.e
        public int c(d dVar, String str) {
            if (str == null) {
                return 0;
            }
            synchronized (MultiInstanceInvalidationService.this.f1304d) {
                MultiInstanceInvalidationService multiInstanceInvalidationService = MultiInstanceInvalidationService.this;
                int i = multiInstanceInvalidationService.f1302b + 1;
                multiInstanceInvalidationService.f1302b = i;
                if (multiInstanceInvalidationService.f1304d.register(dVar, Integer.valueOf(i))) {
                    MultiInstanceInvalidationService.this.f1303c.put(Integer.valueOf(i), str);
                    return i;
                }
                MultiInstanceInvalidationService multiInstanceInvalidationService2 = MultiInstanceInvalidationService.this;
                multiInstanceInvalidationService2.f1302b--;
                return 0;
            }
        }

        @Override // androidx.room.e
        public void e(int i, String[] strArr) {
            synchronized (MultiInstanceInvalidationService.this.f1304d) {
                String str = MultiInstanceInvalidationService.this.f1303c.get(Integer.valueOf(i));
                if (str == null) {
                    Log.w("ROOM", "Remote invalidation client ID not registered");
                    return;
                }
                int iBeginBroadcast = MultiInstanceInvalidationService.this.f1304d.beginBroadcast();
                for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
                    try {
                        int iIntValue = ((Integer) MultiInstanceInvalidationService.this.f1304d.getBroadcastCookie(i2)).intValue();
                        String str2 = MultiInstanceInvalidationService.this.f1303c.get(Integer.valueOf(iIntValue));
                        if (i != iIntValue && str.equals(str2)) {
                            try {
                                ((d) MultiInstanceInvalidationService.this.f1304d.getBroadcastItem(i2)).f(strArr);
                            } catch (RemoteException e) {
                                Log.w("ROOM", "Error invoking a remote callback", e);
                            }
                        }
                    } finally {
                        MultiInstanceInvalidationService.this.f1304d.finishBroadcast();
                    }
                }
            }
        }

        @Override // androidx.room.e
        public void h(d dVar, int i) {
            synchronized (MultiInstanceInvalidationService.this.f1304d) {
                MultiInstanceInvalidationService.this.f1304d.unregister(dVar);
                MultiInstanceInvalidationService.this.f1303c.remove(Integer.valueOf(i));
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.e;
    }
}
