package b.q;

import android.content.Context;
import android.content.Intent;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.support.v4.media.session.MediaSessionCompat;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: MediaBrowserServiceCompatApi21.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: compiled from: MediaBrowserServiceCompatApi21.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f3004a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Bundle f3005b;
    }

    /* JADX INFO: compiled from: MediaBrowserServiceCompatApi21.java */
    public static class b extends MediaBrowserService {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final d f3006b;

        public b(Context context, d dVar) {
            attachBaseContext(context);
            this.f3006b = dVar;
        }

        @Override // android.service.media.MediaBrowserService
        public MediaBrowserService.BrowserRoot onGetRoot(String str, int i2, Bundle bundle) {
            MediaSessionCompat.a(bundle);
            a aVarC = this.f3006b.c(str, i2, bundle == null ? null : new Bundle(bundle));
            if (aVarC == null) {
                return null;
            }
            return new MediaBrowserService.BrowserRoot(aVarC.f3004a, aVarC.f3005b);
        }

        @Override // android.service.media.MediaBrowserService
        public void onLoadChildren(String str, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> result) {
            this.f3006b.b(str, new c<>(result));
        }
    }

    /* JADX INFO: compiled from: MediaBrowserServiceCompatApi21.java */
    public static class c<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public MediaBrowserService.Result f3007a;

        public c(MediaBrowserService.Result result) {
            this.f3007a = result;
        }

        public List<MediaBrowser.MediaItem> a(List<Parcel> list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Parcel parcel : list) {
                parcel.setDataPosition(0);
                arrayList.add(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
                parcel.recycle();
            }
            return arrayList;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void b(T t) {
            if (t instanceof List) {
                this.f3007a.sendResult(a((List) t));
                return;
            }
            if (!(t instanceof Parcel)) {
                this.f3007a.sendResult(null);
                return;
            }
            Parcel parcel = (Parcel) t;
            parcel.setDataPosition(0);
            this.f3007a.sendResult(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
            parcel.recycle();
        }
    }

    /* JADX INFO: compiled from: MediaBrowserServiceCompatApi21.java */
    public interface d {
        void b(String str, c<List<Parcel>> cVar);

        a c(String str, int i2, Bundle bundle);
    }

    public static Object a(Context context, d dVar) {
        return new b(context, dVar);
    }

    public static IBinder b(Object obj, Intent intent) {
        return ((MediaBrowserService) obj).onBind(intent);
    }

    public static void c(Object obj) {
        ((MediaBrowserService) obj).onCreate();
    }
}
