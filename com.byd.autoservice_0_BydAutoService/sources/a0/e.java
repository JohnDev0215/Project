package a0;

import a0.d;
import android.app.Notification;
import android.app.RemoteInput;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: NotificationCompatBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Notification.Builder f57a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d.c f58b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public RemoteViews f59c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public RemoteViews f60d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List<Bundle> f61e = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Bundle f62f = new Bundle();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f63g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public RemoteViews f64h;

    public e(d.c cVar) {
        ArrayList<String> arrayList;
        this.f58b = cVar;
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 26) {
            this.f57a = new Notification.Builder(cVar.f31a, cVar.I);
        } else {
            this.f57a = new Notification.Builder(cVar.f31a);
        }
        Notification notification = cVar.P;
        this.f57a.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, cVar.f38h).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(cVar.f34d).setContentText(cVar.f35e).setContentInfo(cVar.f40j).setContentIntent(cVar.f36f).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(cVar.f37g, (notification.flags & 128) != 0).setLargeIcon(cVar.f39i).setNumber(cVar.f41k).setProgress(cVar.f48r, cVar.f49s, cVar.f50t);
        if (i3 < 21) {
            this.f57a.setSound(notification.sound, notification.audioStreamType);
        }
        if (i3 >= 16) {
            this.f57a.setSubText(cVar.f46p).setUsesChronometer(cVar.f44n).setPriority(cVar.f42l);
            Iterator<d.a> it = cVar.f32b.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
            Bundle bundle = cVar.B;
            if (bundle != null) {
                this.f62f.putAll(bundle);
            }
            if (Build.VERSION.SDK_INT < 20) {
                if (cVar.f54x) {
                    this.f62f.putBoolean("android.support.localOnly", true);
                }
                String str = cVar.f51u;
                if (str != null) {
                    this.f62f.putString("android.support.groupKey", str);
                    if (cVar.f52v) {
                        this.f62f.putBoolean("android.support.isGroupSummary", true);
                    } else {
                        this.f62f.putBoolean("android.support.useSideChannel", true);
                    }
                }
                String str2 = cVar.f53w;
                if (str2 != null) {
                    this.f62f.putString("android.support.sortKey", str2);
                }
            }
            this.f59c = cVar.F;
            this.f60d = cVar.G;
        }
        int i4 = Build.VERSION.SDK_INT;
        if (i4 >= 19) {
            this.f57a.setShowWhen(cVar.f43m);
            if (i4 < 21 && (arrayList = cVar.R) != null && !arrayList.isEmpty()) {
                Bundle bundle2 = this.f62f;
                ArrayList<String> arrayList2 = cVar.R;
                bundle2.putStringArray("android.people", (String[]) arrayList2.toArray(new String[arrayList2.size()]));
            }
        }
        if (i4 >= 20) {
            this.f57a.setLocalOnly(cVar.f54x).setGroup(cVar.f51u).setGroupSummary(cVar.f52v).setSortKey(cVar.f53w);
            this.f63g = cVar.M;
        }
        if (i4 >= 21) {
            this.f57a.setCategory(cVar.A).setColor(cVar.C).setVisibility(cVar.D).setPublicVersion(cVar.E).setSound(notification.sound, notification.audioAttributes);
            Iterator<String> it2 = cVar.R.iterator();
            while (it2.hasNext()) {
                this.f57a.addPerson(it2.next());
            }
            this.f64h = cVar.H;
            if (cVar.f33c.size() > 0) {
                Bundle bundle3 = cVar.b().getBundle("android.car.EXTENSIONS");
                bundle3 = bundle3 == null ? new Bundle() : bundle3;
                Bundle bundle4 = new Bundle();
                for (int i5 = 0; i5 < cVar.f33c.size(); i5++) {
                    bundle4.putBundle(Integer.toString(i5), f.b(cVar.f33c.get(i5)));
                }
                bundle3.putBundle("invisible_actions", bundle4);
                cVar.b().putBundle("android.car.EXTENSIONS", bundle3);
                this.f62f.putBundle("android.car.EXTENSIONS", bundle3);
            }
        }
        int i6 = Build.VERSION.SDK_INT;
        if (i6 >= 24) {
            this.f57a.setExtras(cVar.B).setRemoteInputHistory(cVar.f47q);
            RemoteViews remoteViews = cVar.F;
            if (remoteViews != null) {
                this.f57a.setCustomContentView(remoteViews);
            }
            RemoteViews remoteViews2 = cVar.G;
            if (remoteViews2 != null) {
                this.f57a.setCustomBigContentView(remoteViews2);
            }
            RemoteViews remoteViews3 = cVar.H;
            if (remoteViews3 != null) {
                this.f57a.setCustomHeadsUpContentView(remoteViews3);
            }
        }
        if (i6 >= 26) {
            this.f57a.setBadgeIconType(cVar.J).setShortcutId(cVar.K).setTimeoutAfter(cVar.L).setGroupAlertBehavior(cVar.M);
            if (cVar.f56z) {
                this.f57a.setColorized(cVar.f55y);
            }
            if (!TextUtils.isEmpty(cVar.I)) {
                this.f57a.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
        if (i6 >= 29) {
            this.f57a.setAllowSystemGeneratedContextualActions(cVar.N);
            this.f57a.setBubbleMetadata(d.b.a(cVar.O));
        }
        if (cVar.Q) {
            if (this.f58b.f52v) {
                this.f63g = 2;
            } else {
                this.f63g = 1;
            }
            this.f57a.setVibrate(null);
            this.f57a.setSound(null);
            int i7 = notification.defaults & (-2);
            notification.defaults = i7;
            int i8 = i7 & (-3);
            notification.defaults = i8;
            this.f57a.setDefaults(i8);
            if (i6 >= 26) {
                if (TextUtils.isEmpty(this.f58b.f51u)) {
                    this.f57a.setGroup("silent");
                }
                this.f57a.setGroupAlertBehavior(this.f63g);
            }
        }
    }

    public final void a(d.a aVar) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 20) {
            if (i3 >= 16) {
                this.f61e.add(f.f(this.f57a, aVar));
                return;
            }
            return;
        }
        IconCompat iconCompatE = aVar.e();
        Notification.Action.Builder builder = i3 >= 23 ? new Notification.Action.Builder(iconCompatE != null ? iconCompatE.l() : null, aVar.i(), aVar.a()) : new Notification.Action.Builder(iconCompatE != null ? iconCompatE.c() : 0, aVar.i(), aVar.a());
        if (aVar.f() != null) {
            for (RemoteInput remoteInput : g.b(aVar.f())) {
                builder.addRemoteInput(remoteInput);
            }
        }
        Bundle bundle = aVar.d() != null ? new Bundle(aVar.d()) : new Bundle();
        bundle.putBoolean("android.support.allowGeneratedReplies", aVar.b());
        int i4 = Build.VERSION.SDK_INT;
        if (i4 >= 24) {
            builder.setAllowGeneratedReplies(aVar.b());
        }
        bundle.putInt("android.support.action.semanticAction", aVar.g());
        if (i4 >= 28) {
            builder.setSemanticAction(aVar.g());
        }
        if (i4 >= 29) {
            builder.setContextual(aVar.j());
        }
        bundle.putBoolean("android.support.action.showsUserInterface", aVar.h());
        builder.addExtras(bundle);
        this.f57a.addAction(builder.build());
    }

    public Notification b() {
        d.AbstractC0001d abstractC0001d = this.f58b.f45o;
        if (abstractC0001d != null || abstractC0001d != null) {
            throw null;
        }
        Notification notificationC = c();
        d.c cVar = this.f58b;
        RemoteViews remoteViews = cVar.F;
        if (remoteViews != null) {
            notificationC.contentView = remoteViews;
        }
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 16 && abstractC0001d != null) {
            throw null;
        }
        if (i3 >= 21 && abstractC0001d != null) {
            d.AbstractC0001d abstractC0001d2 = cVar.f45o;
            throw null;
        }
        if (i3 < 16 || abstractC0001d == null || d.a(notificationC) == null) {
            return notificationC;
        }
        throw null;
    }

    public Notification c() {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 26) {
            return this.f57a.build();
        }
        if (i3 >= 24) {
            Notification notificationBuild = this.f57a.build();
            if (this.f63g != 0) {
                if (notificationBuild.getGroup() != null && (notificationBuild.flags & 512) != 0 && this.f63g == 2) {
                    d(notificationBuild);
                }
                if (notificationBuild.getGroup() != null && (notificationBuild.flags & 512) == 0 && this.f63g == 1) {
                    d(notificationBuild);
                }
            }
            return notificationBuild;
        }
        if (i3 >= 21) {
            this.f57a.setExtras(this.f62f);
            Notification notificationBuild2 = this.f57a.build();
            RemoteViews remoteViews = this.f59c;
            if (remoteViews != null) {
                notificationBuild2.contentView = remoteViews;
            }
            RemoteViews remoteViews2 = this.f60d;
            if (remoteViews2 != null) {
                notificationBuild2.bigContentView = remoteViews2;
            }
            RemoteViews remoteViews3 = this.f64h;
            if (remoteViews3 != null) {
                notificationBuild2.headsUpContentView = remoteViews3;
            }
            if (this.f63g != 0) {
                if (notificationBuild2.getGroup() != null && (notificationBuild2.flags & 512) != 0 && this.f63g == 2) {
                    d(notificationBuild2);
                }
                if (notificationBuild2.getGroup() != null && (notificationBuild2.flags & 512) == 0 && this.f63g == 1) {
                    d(notificationBuild2);
                }
            }
            return notificationBuild2;
        }
        if (i3 >= 20) {
            this.f57a.setExtras(this.f62f);
            Notification notificationBuild3 = this.f57a.build();
            RemoteViews remoteViews4 = this.f59c;
            if (remoteViews4 != null) {
                notificationBuild3.contentView = remoteViews4;
            }
            RemoteViews remoteViews5 = this.f60d;
            if (remoteViews5 != null) {
                notificationBuild3.bigContentView = remoteViews5;
            }
            if (this.f63g != 0) {
                if (notificationBuild3.getGroup() != null && (notificationBuild3.flags & 512) != 0 && this.f63g == 2) {
                    d(notificationBuild3);
                }
                if (notificationBuild3.getGroup() != null && (notificationBuild3.flags & 512) == 0 && this.f63g == 1) {
                    d(notificationBuild3);
                }
            }
            return notificationBuild3;
        }
        if (i3 >= 19) {
            SparseArray<Bundle> sparseArrayA = f.a(this.f61e);
            if (sparseArrayA != null) {
                this.f62f.putSparseParcelableArray("android.support.actionExtras", sparseArrayA);
            }
            this.f57a.setExtras(this.f62f);
            Notification notificationBuild4 = this.f57a.build();
            RemoteViews remoteViews6 = this.f59c;
            if (remoteViews6 != null) {
                notificationBuild4.contentView = remoteViews6;
            }
            RemoteViews remoteViews7 = this.f60d;
            if (remoteViews7 != null) {
                notificationBuild4.bigContentView = remoteViews7;
            }
            return notificationBuild4;
        }
        if (i3 < 16) {
            return this.f57a.getNotification();
        }
        Notification notificationBuild5 = this.f57a.build();
        Bundle bundleA = d.a(notificationBuild5);
        Bundle bundle = new Bundle(this.f62f);
        for (String str : this.f62f.keySet()) {
            if (bundleA.containsKey(str)) {
                bundle.remove(str);
            }
        }
        bundleA.putAll(bundle);
        SparseArray<Bundle> sparseArrayA2 = f.a(this.f61e);
        if (sparseArrayA2 != null) {
            d.a(notificationBuild5).putSparseParcelableArray("android.support.actionExtras", sparseArrayA2);
        }
        RemoteViews remoteViews8 = this.f59c;
        if (remoteViews8 != null) {
            notificationBuild5.contentView = remoteViews8;
        }
        RemoteViews remoteViews9 = this.f60d;
        if (remoteViews9 != null) {
            notificationBuild5.bigContentView = remoteViews9;
        }
        return notificationBuild5;
    }

    public final void d(Notification notification) {
        notification.sound = null;
        notification.vibrate = null;
        int i3 = notification.defaults & (-2);
        notification.defaults = i3;
        notification.defaults = i3 & (-3);
    }
}
