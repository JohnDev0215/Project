package androidx.room;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import b.s.b;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class MultiInstanceInvalidationService extends Service {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1298b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashMap<Integer, String> f1299c = new HashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final RemoteCallbackList<b.s.a> f1300d = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b.a f1301e = new b();

    public class a extends RemoteCallbackList<b.s.a> {
        public a() {
        }

        @Override // android.os.RemoteCallbackList
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onCallbackDied(b.s.a aVar, Object obj) {
            MultiInstanceInvalidationService.this.f1299c.remove(Integer.valueOf(((Integer) obj).intValue()));
        }
    }

    public class b extends b.a {
        public b() {
        }

        @Override // b.s.b
        public void C(b.s.a aVar, int i2) {
            synchronized (MultiInstanceInvalidationService.this.f1300d) {
                MultiInstanceInvalidationService.this.f1300d.unregister(aVar);
                MultiInstanceInvalidationService.this.f1299c.remove(Integer.valueOf(i2));
            }
        }

        @Override // b.s.b
        public int h(b.s.a aVar, String str) {
            if (str == null) {
                return 0;
            }
            synchronized (MultiInstanceInvalidationService.this.f1300d) {
                MultiInstanceInvalidationService multiInstanceInvalidationService = MultiInstanceInvalidationService.this;
                int i2 = multiInstanceInvalidationService.f1298b + 1;
                multiInstanceInvalidationService.f1298b = i2;
                if (multiInstanceInvalidationService.f1300d.register(aVar, Integer.valueOf(i2))) {
                    MultiInstanceInvalidationService.this.f1299c.put(Integer.valueOf(i2), str);
                    return i2;
                }
                MultiInstanceInvalidationService multiInstanceInvalidationService2 = MultiInstanceInvalidationService.this;
                multiInstanceInvalidationService2.f1298b--;
                return 0;
            }
        }

        @Override // b.s.b
        public void s(int i2, String[] strArr) {
            synchronized (MultiInstanceInvalidationService.this.f1300d) {
                String str = MultiInstanceInvalidationService.this.f1299c.get(Integer.valueOf(i2));
                if (str == null) {
                    Log.w("ROOM", "Remote invalidation client ID not registered");
                    return;
                }
                int iBeginBroadcast = MultiInstanceInvalidationService.this.f1300d.beginBroadcast();
                for (int i3 = 0; i3 < iBeginBroadcast; i3++) {
                    try {
                        int iIntValue = ((Integer) MultiInstanceInvalidationService.this.f1300d.getBroadcastCookie(i3)).intValue();
                        String str2 = MultiInstanceInvalidationService.this.f1299c.get(Integer.valueOf(iIntValue));
                        if (i2 != iIntValue && str.equals(str2)) {
                            try {
                                ((b.s.a) MultiInstanceInvalidationService.this.f1300d.getBroadcastItem(i3)).y(strArr);
                            } catch (RemoteException e2) {
                                Log.w("ROOM", "Error invoking a remote callback", e2);
                            }
                        }
                    } finally {
                        MultiInstanceInvalidationService.this.f1300d.finishBroadcast();
                    }
                }
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f1301e;
    }
}
