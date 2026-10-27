package b1;

import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import b1.f;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: MediaBrowserServiceCompatApi26.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Field f2037a;

    /* JADX INFO: compiled from: MediaBrowserServiceCompatApi26.java */
    public static class a extends f.a {
        public a(Context context, c cVar) {
            super(context, cVar);
        }

        @Override // android.service.media.MediaBrowserService
        public void onLoadChildren(String str, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> result, Bundle bundle) {
            MediaSessionCompat.a(bundle);
            ((c) this.f2035d).e(str, new b(result), bundle);
        }
    }

    /* JADX INFO: compiled from: MediaBrowserServiceCompatApi26.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public MediaBrowserService.Result f2038a;

        public b(MediaBrowserService.Result result) {
            this.f2038a = result;
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

        public void b(List<Parcel> list, int i8) {
            try {
                g.f2037a.setInt(this.f2038a, i8);
            } catch (IllegalAccessException e8) {
                Log.w("MBSCompatApi26", e8);
            }
            this.f2038a.sendResult(a(list));
        }
    }

    /* JADX INFO: compiled from: MediaBrowserServiceCompatApi26.java */
    public interface c extends f.b {
        void e(String str, b bVar, Bundle bundle);
    }

    static {
        try {
            Field declaredField = MediaBrowserService.Result.class.getDeclaredField("mFlags");
            f2037a = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e8) {
            Log.w("MBSCompatApi26", e8);
        }
    }

    public static Object a(Context context, c cVar) {
        return new a(context, cVar);
    }
}
