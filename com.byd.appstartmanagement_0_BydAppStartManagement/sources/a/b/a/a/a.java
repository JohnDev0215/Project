package a.b.a.a;

import android.content.ComponentName;
import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.util.List;

/* JADX INFO: compiled from: MediaBrowserCompatApi21.java */
/* JADX INFO: loaded from: classes.dex */
@RequiresApi(21)
public class a {

    /* JADX INFO: renamed from: a.b.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MediaBrowserCompatApi21.java */
    public interface InterfaceC0001a {
        void a();

        void b();

        void c();
    }

    /* JADX INFO: compiled from: MediaBrowserCompatApi21.java */
    public static class b<T extends InterfaceC0001a> extends MediaBrowser.ConnectionCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final T f51a;

        public b(T t) {
            this.f51a = t;
        }

        @Override // android.media.browse.MediaBrowser.ConnectionCallback
        public void onConnected() {
            this.f51a.a();
        }

        @Override // android.media.browse.MediaBrowser.ConnectionCallback
        public void onConnectionFailed() {
            this.f51a.b();
        }

        @Override // android.media.browse.MediaBrowser.ConnectionCallback
        public void onConnectionSuspended() {
            this.f51a.c();
        }
    }

    /* JADX INFO: compiled from: MediaBrowserCompatApi21.java */
    public static class c {
        public static Object a(Object obj) {
            return ((MediaBrowser.MediaItem) obj).getDescription();
        }

        public static int b(Object obj) {
            return ((MediaBrowser.MediaItem) obj).getFlags();
        }
    }

    /* JADX INFO: compiled from: MediaBrowserCompatApi21.java */
    public interface d {
        void c(@NonNull String str, List<?> list);

        void d(@NonNull String str);
    }

    /* JADX INFO: compiled from: MediaBrowserCompatApi21.java */
    public static class e<T extends d> extends MediaBrowser.SubscriptionCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final T f52a;

        public e(T t) {
            this.f52a = t;
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onChildrenLoaded(@NonNull String str, List<MediaBrowser.MediaItem> list) {
            this.f52a.c(str, list);
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onError(@NonNull String str) {
            this.f52a.d(str);
        }
    }

    public static void a(Object obj) {
        ((MediaBrowser) obj).connect();
    }

    public static Object b(Context context, ComponentName componentName, Object obj, Bundle bundle) {
        return new MediaBrowser(context, componentName, (MediaBrowser.ConnectionCallback) obj, bundle);
    }

    public static Object c(InterfaceC0001a interfaceC0001a) {
        return new b(interfaceC0001a);
    }

    public static Object d(d dVar) {
        return new e(dVar);
    }

    public static void e(Object obj) {
        ((MediaBrowser) obj).disconnect();
    }

    public static Bundle f(Object obj) {
        return ((MediaBrowser) obj).getExtras();
    }

    public static Object g(Object obj) {
        return ((MediaBrowser) obj).getSessionToken();
    }
}
