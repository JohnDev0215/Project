package androidx.media;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.browse.MediaBrowser;
import android.media.session.MediaSessionManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import defpackage.m8;
import defpackage.qv1;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class MediaBrowserServiceCompat extends Service {
    public f e;
    public final b f = new b("android.media.session.MediaController", -1, -1, null);
    public final ArrayList<b> g = new ArrayList<>();
    public final m8<IBinder, b> h = new m8<>();
    public final k i = new k(this);

    public static final class a {
        public final String a;
        public final Bundle b;

        public a(Bundle bundle, String str) {
            if (str == null) {
                throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead");
            }
            this.a = str;
            this.b = bundle;
        }
    }

    public class b implements IBinder.DeathRecipient {
        public final String a;
        public final int b;
        public final int c;
        public final i d;
        public final HashMap<String, List<qv1<IBinder, Bundle>>> e = new HashMap<>();
        public a f;

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                b bVar = b.this;
                MediaBrowserServiceCompat.this.h.remove(((j) bVar.d).a());
            }
        }

        public b(String str, int i, int i2, i iVar) {
            this.a = str;
            this.b = i;
            this.c = i2;
            if (str == null) {
                throw new NullPointerException("package shouldn't be null");
            }
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("packageName should be nonempty");
            }
            new MediaSessionManager.RemoteUserInfo(str, i, i2);
            this.d = iVar;
        }

        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
            MediaBrowserServiceCompat.this.i.post(new a());
        }
    }

    public class c {
        public final ArrayList a = new ArrayList();
        public a b;
        public Messenger c;

        public class a extends MediaBrowserService {
            public a(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
                attachBaseContext(mediaBrowserServiceCompat);
            }

            @Override // android.service.media.MediaBrowserService
            @SuppressLint({"SyntheticAccessor"})
            public final MediaBrowserService.BrowserRoot onGetRoot(String str, int i, Bundle bundle) {
                Bundle bundle2;
                a aVar;
                MediaSessionCompat.ensureClassLoader(bundle);
                Bundle bundle3 = bundle == null ? null : new Bundle(bundle);
                c cVar = c.this;
                MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
                int i2 = -1;
                if (bundle3 == null || bundle3.getInt("extra_client_version", 0) == 0) {
                    bundle2 = null;
                } else {
                    bundle3.remove("extra_client_version");
                    cVar.c = new Messenger(mediaBrowserServiceCompat.i);
                    bundle2 = new Bundle();
                    bundle2.putInt("extra_service_version", 2);
                    bundle2.putBinder("extra_messenger", cVar.c.getBinder());
                    cVar.a.add(bundle2);
                    i2 = bundle3.getInt("extra_calling_pid", -1);
                    bundle3.remove("extra_calling_pid");
                }
                b bVar = MediaBrowserServiceCompat.this.new b(str, i2, i, null);
                mediaBrowserServiceCompat.getClass();
                a aVarA = mediaBrowserServiceCompat.a();
                if (aVarA == null) {
                    aVar = null;
                } else {
                    if (cVar.c != null) {
                        mediaBrowserServiceCompat.g.add(bVar);
                    }
                    Bundle bundle4 = aVarA.b;
                    if (bundle2 == null) {
                        bundle2 = bundle4;
                    } else if (bundle4 != null) {
                        bundle2.putAll(bundle4);
                    }
                    aVar = new a(bundle2, aVarA.a);
                }
                if (aVar == null) {
                    return null;
                }
                return new MediaBrowserService.BrowserRoot(aVar.a, aVar.b);
            }

            @Override // android.service.media.MediaBrowserService
            public final void onLoadChildren(String str, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> result) {
                c cVar = c.this;
                cVar.getClass();
                MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
                b bVar = mediaBrowserServiceCompat.f;
                mediaBrowserServiceCompat.b();
            }
        }

        public c() {
        }
    }

    public class d extends c {

        public class a extends c.a {
            public a(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
                super(mediaBrowserServiceCompat);
            }

            @Override // android.service.media.MediaBrowserService
            public final void onLoadItem(String str, MediaBrowserService.Result<MediaBrowser.MediaItem> result) {
                g gVar = new g(result);
                b bVar = MediaBrowserServiceCompat.this.f;
                gVar.a(null);
            }
        }

        public d() {
            super();
        }
    }

    public class e extends d {

        public class a extends d.a {
            public a(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
                super(mediaBrowserServiceCompat);
            }

            @Override // android.service.media.MediaBrowserService
            public final void onLoadChildren(String str, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> result, Bundle bundle) {
                MediaSessionCompat.ensureClassLoader(bundle);
                e eVar = e.this;
                MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
                b bVar = mediaBrowserServiceCompat.f;
                mediaBrowserServiceCompat.b();
                MediaBrowserServiceCompat.this.getClass();
            }
        }

        public e() {
            super();
        }

        public final void a() {
            a aVar = new a(MediaBrowserServiceCompat.this);
            this.b = aVar;
            aVar.onCreate();
        }
    }

    public class f extends e {
        public f(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
            super();
        }
    }

    public static class g<T> {
        public final MediaBrowserService.Result a;

        public g(MediaBrowserService.Result result) {
            this.a = result;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void a(T t) {
            boolean z = t instanceof List;
            ArrayList arrayList = null;
            MediaBrowserService.Result result = this.a;
            if (!z) {
                if (!(t instanceof Parcel)) {
                    result.sendResult(null);
                    return;
                }
                Parcel parcel = (Parcel) t;
                parcel.setDataPosition(0);
                result.sendResult(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
                parcel.recycle();
                return;
            }
            List<Parcel> list = (List) t;
            if (list != null) {
                arrayList = new ArrayList(list.size());
                for (Parcel parcel2 : list) {
                    parcel2.setDataPosition(0);
                    arrayList.add((MediaBrowser.MediaItem) MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel2));
                    parcel2.recycle();
                }
            }
            result.sendResult(arrayList);
        }
    }

    public class h {
        public h() {
        }
    }

    public interface i {
    }

    public static class j implements i {
        public final Messenger a;

        public j(Messenger messenger) {
            this.a = messenger;
        }

        public final IBinder a() {
            return this.a.getBinder();
        }
    }

    public final class k extends Handler {
        public final h a;

        public k(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
            this.a = mediaBrowserServiceCompat.new h();
        }

        public final void a(Runnable runnable) {
            if (Thread.currentThread() == getLooper().getThread()) {
                runnable.run();
            } else {
                post(runnable);
            }
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Bundle data = message.getData();
            int i = message.what;
            h hVar = this.a;
            switch (i) {
                case 1:
                    Bundle bundle = data.getBundle("data_root_hints");
                    MediaSessionCompat.ensureClassLoader(bundle);
                    String string = data.getString("data_package_name");
                    int i2 = data.getInt("data_calling_pid");
                    int i3 = data.getInt("data_calling_uid");
                    j jVar = new j(message.replyTo);
                    MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
                    boolean z = false;
                    if (string == null) {
                        mediaBrowserServiceCompat.getClass();
                    } else {
                        String[] packagesForUid = mediaBrowserServiceCompat.getPackageManager().getPackagesForUid(i3);
                        int length = packagesForUid.length;
                        int i4 = 0;
                        while (true) {
                            if (i4 < length) {
                                if (packagesForUid[i4].equals(string)) {
                                    z = true;
                                } else {
                                    i4++;
                                }
                            }
                        }
                    }
                    if (z) {
                        mediaBrowserServiceCompat.i.a(new androidx.media.a(i2, i3, bundle, hVar, jVar, string));
                        return;
                    }
                    throw new IllegalArgumentException("Package/uid mismatch: uid=" + i3 + " package=" + string);
                case 2:
                    MediaBrowserServiceCompat.this.i.a(new androidx.media.b(hVar, new j(message.replyTo)));
                    return;
                case 3:
                    Bundle bundle2 = data.getBundle("data_options");
                    MediaSessionCompat.ensureClassLoader(bundle2);
                    MediaBrowserServiceCompat.this.i.a(new androidx.media.c(hVar, new j(message.replyTo), data.getString("data_media_item_id"), data.getBinder("data_callback_token"), bundle2));
                    return;
                case 4:
                    MediaBrowserServiceCompat.this.i.a(new androidx.media.d(hVar, new j(message.replyTo), data.getString("data_media_item_id"), data.getBinder("data_callback_token")));
                    return;
                case 5:
                    String string2 = data.getString("data_media_item_id");
                    ResultReceiver resultReceiver = (ResultReceiver) data.getParcelable("data_result_receiver");
                    j jVar2 = new j(message.replyTo);
                    hVar.getClass();
                    if (TextUtils.isEmpty(string2) || resultReceiver == null) {
                        return;
                    }
                    MediaBrowserServiceCompat.this.i.a(new androidx.media.e(hVar, jVar2, string2, resultReceiver));
                    return;
                case 6:
                    Bundle bundle3 = data.getBundle("data_root_hints");
                    MediaSessionCompat.ensureClassLoader(bundle3);
                    j jVar3 = new j(message.replyTo);
                    String string3 = data.getString("data_package_name");
                    MediaBrowserServiceCompat.this.i.a(new androidx.media.f(data.getInt("data_calling_uid"), data.getInt("data_calling_pid"), bundle3, hVar, jVar3, string3));
                    return;
                case 7:
                    MediaBrowserServiceCompat.this.i.a(new androidx.media.g(hVar, new j(message.replyTo)));
                    return;
                case 8:
                    Bundle bundle4 = data.getBundle("data_search_extras");
                    MediaSessionCompat.ensureClassLoader(bundle4);
                    String string4 = data.getString("data_search_query");
                    ResultReceiver resultReceiver2 = (ResultReceiver) data.getParcelable("data_result_receiver");
                    j jVar4 = new j(message.replyTo);
                    hVar.getClass();
                    if (TextUtils.isEmpty(string4) || resultReceiver2 == null) {
                        return;
                    }
                    MediaBrowserServiceCompat.this.i.a(new androidx.media.h(hVar, jVar4, string4, bundle4, resultReceiver2));
                    return;
                case 9:
                    Bundle bundle5 = data.getBundle("data_custom_action_extras");
                    MediaSessionCompat.ensureClassLoader(bundle5);
                    String string5 = data.getString("data_custom_action");
                    ResultReceiver resultReceiver3 = (ResultReceiver) data.getParcelable("data_result_receiver");
                    j jVar5 = new j(message.replyTo);
                    hVar.getClass();
                    if (TextUtils.isEmpty(string5) || resultReceiver3 == null) {
                        return;
                    }
                    MediaBrowserServiceCompat.this.i.a(new androidx.media.i(hVar, jVar5, string5, bundle5, resultReceiver3));
                    return;
                default:
                    Log.w("MBServiceCompat", "Unhandled message: " + message + "\n  Service version: 2\n  Client version: " + message.arg1);
                    return;
            }
        }

        @Override // android.os.Handler
        public final boolean sendMessageAtTime(Message message, long j) {
            Bundle data = message.getData();
            data.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            data.putInt("data_calling_uid", Binder.getCallingUid());
            int callingPid = Binder.getCallingPid();
            if (callingPid > 0) {
                data.putInt("data_calling_pid", callingPid);
            } else if (!data.containsKey("data_calling_pid")) {
                data.putInt("data_calling_pid", -1);
            }
            return super.sendMessageAtTime(message, j);
        }
    }

    public abstract a a();

    public abstract void b();

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.e.b.onBind(intent);
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        f fVar = new f(this);
        this.e = fVar;
        fVar.a();
    }

    @Override // android.app.Service
    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }
}
