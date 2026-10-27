package i3;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: MediaManager.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final s f6938a;

    /* JADX INFO: compiled from: MediaManager.java */
    class a extends MediaBrowserCompat.l {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ q4.f f6939d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ MediaBrowserCompat f6940e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ String f6941f;

        a(q4.f fVar, MediaBrowserCompat mediaBrowserCompat, String str) {
            this.f6939d = fVar;
            this.f6940e = mediaBrowserCompat;
            this.f6941f = str;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.l
        public void a(String str, List<MediaBrowserCompat.MediaItem> list) {
            h3.i.b("MediaManager").i("onChildrenLoaded(no bundle) parentId:%s", str);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.l
        public void b(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
            h3.i.b("MediaManager").b("onChildrenLoaded: id= " + j.this.f6938a.v() + ", parentId=" + str + ",item size=" + list.size() + "thread:" + Thread.currentThread().getName(), new Object[0]);
            MediaControllerCompat mediaControllerCompatY = j.this.f6938a.y();
            if (list.isEmpty()) {
                if (mediaControllerCompatY != null) {
                    PlaybackStateCompat playbackStateCompatE = mediaControllerCompatY.e();
                    h3.i.b("MediaManager").b("playbackState:" + playbackStateCompatE, new Object[0]);
                    if (playbackStateCompatE != null && playbackStateCompatE.k() == 3 && playbackStateCompatE.p() != null) {
                        this.f6939d.a(new k3.a(playbackStateCompatE.m().toString(), playbackStateCompatE.p()));
                        try {
                            this.f6940e.i(this.f6941f);
                            return;
                        } catch (Exception e7) {
                            h3.i.b("MediaManager").e(e7, "unsubscribe " + this.f6941f, new Object[0]);
                            return;
                        }
                    }
                } else {
                    h3.i.b("MediaManager").i("parentId: %s, MediaControllerCompat == null", str);
                }
            }
            this.f6939d.b(list);
            try {
                this.f6940e.i(this.f6941f);
            } catch (Exception e8) {
                h3.i.b("MediaManager").e(e8, "unsubscribe " + this.f6941f, new Object[0]);
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.l
        public void c(String str) {
            h3.i.b("MediaManager").d("onError(no bundle) parentId:%s", str);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.l
        public void d(String str, Bundle bundle) {
            try {
                this.f6940e.i(this.f6941f);
            } catch (Exception e7) {
                h3.i.b("MediaManager").e(e7, "onError unsubscribe " + this.f6941f, new Object[0]);
            }
            h3.i.b("MediaManager").d("onError: parentId=" + str, new Object[0]);
            MediaControllerCompat mediaControllerCompatY = j.this.f6938a.y();
            if (mediaControllerCompatY != null) {
                PlaybackStateCompat playbackStateCompatE = mediaControllerCompatY.e();
                if (playbackStateCompatE != null && playbackStateCompatE.k() == 3 && playbackStateCompatE.p() != null) {
                    this.f6939d.a(new k3.a(playbackStateCompatE.m().toString(), playbackStateCompatE.p()));
                    return;
                }
            } else {
                h3.i.b("MediaManager").d("onError: parentId: %s, MediaControllerCompat == null", str);
            }
            this.f6939d.a(new Exception("onChildrenLoaded onError: parentId=" + str));
        }
    }

    /* JADX INFO: compiled from: MediaManager.java */
    class b extends MediaBrowserCompat.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ q4.f f6943a;

        b(q4.f fVar) {
            this.f6943a = fVar;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void a(String str, Bundle bundle) {
            StringBuilder sb = new StringBuilder();
            sb.append("search error: query=");
            sb.append(str == null ? 0 : str.length());
            String string = sb.toString();
            h3.i.b("MediaManager").d(string, new Object[0]);
            this.f6943a.a(new Exception(string));
        }

        @Override // android.support.v4.media.MediaBrowserCompat.i
        public void b(String str, Bundle bundle, List<MediaBrowserCompat.MediaItem> list) {
            h3.l lVarB = h3.i.b("MediaManager");
            StringBuilder sb = new StringBuilder();
            sb.append("onSearchResult: query=");
            sb.append(str == null ? 0 : str.length());
            sb.append(", appId=");
            sb.append(j.this.f6938a.v());
            sb.append(", size=");
            sb.append(list.size());
            lVarB.g(sb.toString());
            this.f6943a.b(list);
        }
    }

    /* JADX INFO: compiled from: MediaManager.java */
    class c implements q4.g<MediaBrowserCompat> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ MediaBrowserCompat.MediaItem f6945e;

        c(MediaBrowserCompat.MediaItem mediaItem) {
            this.f6945e = mediaItem;
        }

        @Override // q4.g
        public void a(Throwable th) {
            h3.i.b("MediaManager").e(th, "p source:%s, parentId:%s, mediaControllerCompat is null", j.this.f6938a.w(), this.f6945e.j());
        }

        @Override // q4.g
        public void c(t4.b bVar) {
        }

        @Override // q4.g
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(MediaBrowserCompat mediaBrowserCompat) {
            h3.i.b("MediaManager").c("p source:%s, parentId:%s", j.this.f6938a.w(), this.f6945e.j());
            if (j.this.f6938a.y() != null) {
                j.this.f6938a.y().i().c(this.f6945e.j(), this.f6945e.h().c());
            } else {
                h3.i.b("MediaManager").d("p failed, mediaController is null", new Object[0]);
            }
        }
    }

    public j(s sVar) {
        this.f6938a = sVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ q4.i j(String str) {
        return t(str, new Bundle());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k(MediaBrowserCompat mediaBrowserCompat, String str, Bundle bundle, q4.f fVar) {
        try {
            h3.i.b("MediaManager").g("subscribe on Thread:" + Thread.currentThread().getName());
            mediaBrowserCompat.h(str, bundle, new a(fVar, mediaBrowserCompat, str));
        } catch (Exception e7) {
            fVar.b(new ArrayList());
            h3.i.b("MediaManager").e(e7, "subscribe " + str, new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ q4.i l(final String str, final Bundle bundle, final MediaBrowserCompat mediaBrowserCompat) {
        return q4.e.b(new q4.h() { // from class: i3.e
            @Override // q4.h
            public final void a(q4.f fVar) {
                this.f6926a.k(mediaBrowserCompat, str, bundle, fVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m(MediaBrowserCompat mediaBrowserCompat, String str, Bundle bundle, q4.f fVar) {
        mediaBrowserCompat.f(str, bundle, new b(fVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ q4.i n(final String str, final Bundle bundle, final MediaBrowserCompat mediaBrowserCompat) {
        return q4.e.b(new q4.h() { // from class: i3.d
            @Override // q4.h
            public final void a(q4.f fVar) {
                this.f6922a.m(mediaBrowserCompat, str, bundle, fVar);
            }
        });
    }

    private void o(Activity activity, String str, String str2) {
        Intent intent = new Intent();
        if (TextUtils.isEmpty(str)) {
            Intent launchIntentForPackage = activity.getPackageManager().getLaunchIntentForPackage(str2);
            if (launchIntentForPackage != null) {
                intent = launchIntentForPackage;
            }
        } else {
            intent.setComponent(new ComponentName(str2, str));
        }
        try {
            activity.startActivity(intent);
            h3.i.b("MediaManager").g("openSettingActivity");
        } catch (Exception e7) {
            h3.i.b("MediaManager").e(e7, " to setting activity fail", new Object[0]);
        }
    }

    public q4.e<String> f() {
        return this.f6938a.x().e(new v4.e() { // from class: i3.i
            @Override // v4.e
            public final Object a(Object obj) {
                return ((MediaBrowserCompat) obj).c();
            }
        });
    }

    public q4.e<List<MediaBrowserCompat.MediaItem>> g() {
        return f().c(new v4.e() { // from class: i3.f
            @Override // v4.e
            public final Object a(Object obj) {
                return this.f6930a.j((String) obj);
            }
        });
    }

    public boolean h() {
        MediaControllerCompat mediaControllerCompatY = this.f6938a.y();
        if (mediaControllerCompatY == null) {
            h3.i.b("MediaManager").g("isLogin MediaControllerCompat == null");
            return false;
        }
        PlaybackStateCompat playbackStateCompatE = mediaControllerCompatY.e();
        h3.i.b("MediaManager").b("playbackState:" + playbackStateCompatE, new Object[0]);
        if (playbackStateCompatE == null) {
            return false;
        }
        return playbackStateCompatE.k() != 3 || playbackStateCompatE.p() == null;
    }

    public boolean i() {
        return this.f6938a.y() != null;
    }

    public boolean p(Activity activity) {
        PendingIntent pendingIntent;
        MediaControllerCompat mediaControllerCompatY = this.f6938a.y();
        if (mediaControllerCompatY == null) {
            h3.i.b("MediaManager").d("controllerCompat is null", new Object[0]);
            return false;
        }
        PlaybackStateCompat playbackStateCompatE = mediaControllerCompatY.e();
        h3.i.b("MediaManager").g("playbackState:" + playbackStateCompatE);
        if (playbackStateCompatE != null && playbackStateCompatE.k() == 3 && playbackStateCompatE.p() != null) {
            try {
                pendingIntent = (PendingIntent) playbackStateCompatE.p().getParcelable("android.media.extras.ERROR_RESOLUTION_ACTION_INTENT");
            } catch (Exception e7) {
                h3.i.b("MediaManager").e(e7, "pendingIntent.send error:", new Object[0]);
            }
            if (pendingIntent == null) {
                h3.i.b("MediaManager").d("openLoginActivity pendingIntent is null", new Object[0]);
                return false;
            }
            ActivityOptions activityOptionsMakeBasic = ActivityOptions.makeBasic();
            if (Build.VERSION.SDK_INT >= 34) {
                activityOptionsMakeBasic.setPendingIntentBackgroundActivityStartMode(1);
                activity.startIntentSender(pendingIntent.getIntentSender(), new Intent(), 268435456, 536870912, 0, activityOptionsMakeBasic.toBundle());
            } else {
                activity.startIntentSender(pendingIntent.getIntentSender(), new Intent(), 268435456, 536870912, 0);
            }
            h3.i.b("MediaManager").g("openLoginActivity");
            return true;
        }
        h3.l lVarB = h3.i.b("MediaManager");
        StringBuilder sb = new StringBuilder();
        sb.append("openLoginActivity error; playbackState is null: ");
        sb.append(playbackStateCompatE == null);
        lVarB.d(sb.toString(), new Object[0]);
        if (playbackStateCompatE != null) {
            h3.i.b("MediaManager").d("openLoginActivity error; errorCode : " + playbackStateCompatE.k() + " extra = " + playbackStateCompatE.p(), new Object[0]);
        }
        return false;
    }

    public void q(Activity activity) {
        s sVar = this.f6938a;
        if (sVar != null) {
            String strZ = sVar.z();
            if (TextUtils.isEmpty(strZ)) {
                return;
            }
            List<ResolveInfo> listQueryIntentActivities = activity.getPackageManager().queryIntentActivities(new Intent("android.intent.action.APPLICATION_PREFERENCES"), 131072);
            if (listQueryIntentActivities == null || listQueryIntentActivities.isEmpty()) {
                return;
            }
            for (int i7 = 0; i7 < listQueryIntentActivities.size(); i7++) {
                ResolveInfo resolveInfo = listQueryIntentActivities.get(i7);
                if (TextUtils.equals(strZ, resolveInfo.activityInfo.packageName)) {
                    ActivityInfo activityInfo = resolveInfo.activityInfo;
                    o(activity, activityInfo.name, activityInfo.packageName);
                }
            }
        }
    }

    public void r(MediaBrowserCompat.MediaItem mediaItem) {
        this.f6938a.x().f(s4.a.a()).h(e5.a.b()).a(new c(mediaItem));
    }

    public q4.e<List<MediaBrowserCompat.MediaItem>> s(MediaBrowserCompat.MediaItem mediaItem) {
        Bundle bundle = new Bundle();
        Bundle bundleC = mediaItem.h().c();
        if (bundleC != null) {
            bundle.putAll(bundleC);
        }
        return t(i3.c.c(mediaItem), bundle);
    }

    public q4.e<List<MediaBrowserCompat.MediaItem>> t(final String str, final Bundle bundle) {
        return this.f6938a.x().c(new v4.e() { // from class: i3.g
            @Override // v4.e
            public final Object a(Object obj) {
                return this.f6931a.l(str, bundle, (MediaBrowserCompat) obj);
            }
        });
    }

    public q4.e<List<MediaBrowserCompat.MediaItem>> u(String str) {
        return v(str, new Bundle());
    }

    public q4.e<List<MediaBrowserCompat.MediaItem>> v(final String str, final Bundle bundle) {
        return this.f6938a.x().c(new v4.e() { // from class: i3.h
            @Override // v4.e
            public final Object a(Object obj) {
                return this.f6934a.n(str, bundle, (MediaBrowserCompat) obj);
            }
        });
    }
}
