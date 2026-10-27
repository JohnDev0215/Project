package a.d.a.b;

import android.content.ComponentName;
import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import java.util.List;

/* JADX INFO: compiled from: MediaBrowserCompatApi21.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a.d.a.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MediaBrowserCompatApi21.java */
    public interface InterfaceC0002a {
        void a();

        void b();

        void c();
    }

    /* JADX INFO: compiled from: MediaBrowserCompatApi21.java */
    public static class b<T extends InterfaceC0002a> extends MediaBrowser.ConnectionCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final T f56a;

        public b(T t) {
            this.f56a = t;
        }

        @Override // android.media.browse.MediaBrowser.ConnectionCallback
        public void onConnected() {
            this.f56a.b();
        }

        @Override // android.media.browse.MediaBrowser.ConnectionCallback
        public void onConnectionFailed() {
            this.f56a.c();
        }

        @Override // android.media.browse.MediaBrowser.ConnectionCallback
        public void onConnectionSuspended() {
            this.f56a.a();
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
        void c(String str, List<?> list);

        void d(String str);
    }

    /* JADX INFO: compiled from: MediaBrowserCompatApi21.java */
    public static class e<T extends d> extends MediaBrowser.SubscriptionCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final T f57a;

        public e(T t) {
            this.f57a = t;
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowser.MediaItem> list) {
            this.f57a.c(str, list);
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onError(String str) {
            this.f57a.d(str);
        }
    }

    public static void a(Object obj) {
        ((MediaBrowser) obj).connect();
    }

    public static Object b(Context context, ComponentName componentName, Object obj, Bundle bundle) {
        return new MediaBrowser(context, componentName, (MediaBrowser.ConnectionCallback) obj, bundle);
    }

    public static Object c(InterfaceC0002a interfaceC0002a) {
        return new b(interfaceC0002a);
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
