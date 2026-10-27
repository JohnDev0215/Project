package b1;

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
        public final String f2033a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Bundle f2034b;
    }

    /* JADX INFO: compiled from: MediaBrowserServiceCompatApi21.java */
    public static class b extends MediaBrowserService {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final d f2035d;

        public b(Context context, d dVar) {
            attachBaseContext(context);
            this.f2035d = dVar;
        }

        @Override // android.service.media.MediaBrowserService
        public MediaBrowserService.BrowserRoot onGetRoot(String str, int i8, Bundle bundle) {
            MediaSessionCompat.a(bundle);
            a aVarF = this.f2035d.f(str, i8, bundle == null ? null : new Bundle(bundle));
            if (aVarF == null) {
                return null;
            }
            return new MediaBrowserService.BrowserRoot(aVarF.f2033a, aVarF.f2034b);
        }

        @Override // android.service.media.MediaBrowserService
        public void onLoadChildren(String str, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> result) {
            this.f2035d.b(str, new c<>(result));
        }
    }

    /* JADX INFO: compiled from: MediaBrowserServiceCompatApi21.java */
    public static class c<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public MediaBrowserService.Result f2036a;

        public c(MediaBrowserService.Result result) {
            this.f2036a = result;
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
        public void b(T t7) {
            if (t7 instanceof List) {
                this.f2036a.sendResult(a((List) t7));
                return;
            }
            if (!(t7 instanceof Parcel)) {
                this.f2036a.sendResult(null);
                return;
            }
            Parcel parcel = (Parcel) t7;
            parcel.setDataPosition(0);
            this.f2036a.sendResult(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
            parcel.recycle();
        }
    }

    /* JADX INFO: compiled from: MediaBrowserServiceCompatApi21.java */
    public interface d {
        void b(String str, c<List<Parcel>> cVar);

        a f(String str, int i8, Bundle bundle);
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
