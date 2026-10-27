package b.f.e;

import android.app.Notification;
import android.app.RemoteInput;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import androidx.core.graphics.drawable.IconCompat;
import b.f.e.e;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: NotificationCompatBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Notification.Builder f1596a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e.c f1597b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public RemoteViews f1598c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public RemoteViews f1599d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List<Bundle> f1600e = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Bundle f1601f = new Bundle();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1602g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public RemoteViews f1603h;

    public f(e.c cVar) {
        ArrayList<String> arrayList;
        this.f1597b = cVar;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            this.f1596a = new Notification.Builder(cVar.f1587a, cVar.I);
        } else {
            this.f1596a = new Notification.Builder(cVar.f1587a);
        }
        Notification notification = cVar.P;
        this.f1596a.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, cVar.f1594h).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(cVar.f1590d).setContentText(cVar.f1591e).setContentInfo(cVar.j).setContentIntent(cVar.f1592f).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(cVar.f1593g, (notification.flags & 128) != 0).setLargeIcon(cVar.f1595i).setNumber(cVar.k).setProgress(cVar.r, cVar.s, cVar.t);
        if (i2 < 21) {
            this.f1596a.setSound(notification.sound, notification.audioStreamType);
        }
        if (i2 >= 16) {
            this.f1596a.setSubText(cVar.p).setUsesChronometer(cVar.n).setPriority(cVar.l);
            Iterator<e.a> it = cVar.f1588b.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
            Bundle bundle = cVar.B;
            if (bundle != null) {
                this.f1601f.putAll(bundle);
            }
            if (Build.VERSION.SDK_INT < 20) {
                if (cVar.x) {
                    this.f1601f.putBoolean("android.support.localOnly", true);
                }
                String str = cVar.u;
                if (str != null) {
                    this.f1601f.putString("android.support.groupKey", str);
                    if (cVar.v) {
                        this.f1601f.putBoolean("android.support.isGroupSummary", true);
                    } else {
                        this.f1601f.putBoolean("android.support.useSideChannel", true);
                    }
                }
                String str2 = cVar.w;
                if (str2 != null) {
                    this.f1601f.putString("android.support.sortKey", str2);
                }
            }
            this.f1598c = cVar.F;
            this.f1599d = cVar.G;
        }
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 19) {
            this.f1596a.setShowWhen(cVar.m);
            if (i3 < 21 && (arrayList = cVar.R) != null && !arrayList.isEmpty()) {
                Bundle bundle2 = this.f1601f;
                ArrayList<String> arrayList2 = cVar.R;
                bundle2.putStringArray("android.people", (String[]) arrayList2.toArray(new String[arrayList2.size()]));
            }
        }
        if (i3 >= 20) {
            this.f1596a.setLocalOnly(cVar.x).setGroup(cVar.u).setGroupSummary(cVar.v).setSortKey(cVar.w);
            this.f1602g = cVar.M;
        }
        if (i3 >= 21) {
            this.f1596a.setCategory(cVar.A).setColor(cVar.C).setVisibility(cVar.D).setPublicVersion(cVar.E).setSound(notification.sound, notification.audioAttributes);
            Iterator<String> it2 = cVar.R.iterator();
            while (it2.hasNext()) {
                this.f1596a.addPerson(it2.next());
            }
            this.f1603h = cVar.H;
            if (cVar.f1589c.size() > 0) {
                Bundle bundle3 = cVar.b().getBundle("android.car.EXTENSIONS");
                bundle3 = bundle3 == null ? new Bundle() : bundle3;
                Bundle bundle4 = new Bundle();
                for (int i4 = 0; i4 < cVar.f1589c.size(); i4++) {
                    bundle4.putBundle(Integer.toString(i4), g.b(cVar.f1589c.get(i4)));
                }
                bundle3.putBundle("invisible_actions", bundle4);
                cVar.b().putBundle("android.car.EXTENSIONS", bundle3);
                this.f1601f.putBundle("android.car.EXTENSIONS", bundle3);
            }
        }
        int i5 = Build.VERSION.SDK_INT;
        if (i5 >= 24) {
            this.f1596a.setExtras(cVar.B).setRemoteInputHistory(cVar.q);
            RemoteViews remoteViews = cVar.F;
            if (remoteViews != null) {
                this.f1596a.setCustomContentView(remoteViews);
            }
            RemoteViews remoteViews2 = cVar.G;
            if (remoteViews2 != null) {
                this.f1596a.setCustomBigContentView(remoteViews2);
            }
            RemoteViews remoteViews3 = cVar.H;
            if (remoteViews3 != null) {
                this.f1596a.setCustomHeadsUpContentView(remoteViews3);
            }
        }
        if (i5 >= 26) {
            this.f1596a.setBadgeIconType(cVar.J).setShortcutId(cVar.K).setTimeoutAfter(cVar.L).setGroupAlertBehavior(cVar.M);
            if (cVar.z) {
                this.f1596a.setColorized(cVar.y);
            }
            if (!TextUtils.isEmpty(cVar.I)) {
                this.f1596a.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
        if (i5 >= 29) {
            this.f1596a.setAllowSystemGeneratedContextualActions(cVar.N);
            this.f1596a.setBubbleMetadata(e.b.a(cVar.O));
        }
        if (cVar.Q) {
            if (this.f1597b.v) {
                this.f1602g = 2;
            } else {
                this.f1602g = 1;
            }
            this.f1596a.setVibrate(null);
            this.f1596a.setSound(null);
            int i6 = notification.defaults & (-2);
            notification.defaults = i6;
            int i7 = i6 & (-3);
            notification.defaults = i7;
            this.f1596a.setDefaults(i7);
            if (i5 >= 26) {
                if (TextUtils.isEmpty(this.f1597b.u)) {
                    this.f1596a.setGroup("silent");
                }
                this.f1596a.setGroupAlertBehavior(this.f1602g);
            }
        }
    }

    public final void a(e.a aVar) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 20) {
            if (i2 >= 16) {
                this.f1600e.add(g.f(this.f1596a, aVar));
                return;
            }
            return;
        }
        IconCompat iconCompatE = aVar.e();
        Notification.Action.Builder builder = i2 >= 23 ? new Notification.Action.Builder(iconCompatE != null ? iconCompatE.l() : null, aVar.i(), aVar.a()) : new Notification.Action.Builder(iconCompatE != null ? iconCompatE.c() : 0, aVar.i(), aVar.a());
        if (aVar.f() != null) {
            for (RemoteInput remoteInput : h.b(aVar.f())) {
                builder.addRemoteInput(remoteInput);
            }
        }
        Bundle bundle = aVar.d() != null ? new Bundle(aVar.d()) : new Bundle();
        bundle.putBoolean("android.support.allowGeneratedReplies", aVar.b());
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 24) {
            builder.setAllowGeneratedReplies(aVar.b());
        }
        bundle.putInt("android.support.action.semanticAction", aVar.g());
        if (i3 >= 28) {
            builder.setSemanticAction(aVar.g());
        }
        if (i3 >= 29) {
            builder.setContextual(aVar.j());
        }
        bundle.putBoolean("android.support.action.showsUserInterface", aVar.h());
        builder.addExtras(bundle);
        this.f1596a.addAction(builder.build());
    }

    public Notification b() {
        e.d dVar = this.f1597b.o;
        if (dVar != null || dVar != null) {
            throw null;
        }
        Notification notificationC = c();
        e.c cVar = this.f1597b;
        RemoteViews remoteViews = cVar.F;
        if (remoteViews != null) {
            notificationC.contentView = remoteViews;
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 16 && dVar != null) {
            throw null;
        }
        if (i2 >= 21 && dVar != null) {
            e.d dVar2 = cVar.o;
            throw null;
        }
        if (i2 < 16 || dVar == null || e.a(notificationC) == null) {
            return notificationC;
        }
        throw null;
    }

    public Notification c() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            return this.f1596a.build();
        }
        if (i2 >= 24) {
            Notification notificationBuild = this.f1596a.build();
            if (this.f1602g != 0) {
                if (notificationBuild.getGroup() != null && (notificationBuild.flags & 512) != 0 && this.f1602g == 2) {
                    d(notificationBuild);
                }
                if (notificationBuild.getGroup() != null && (notificationBuild.flags & 512) == 0 && this.f1602g == 1) {
                    d(notificationBuild);
                }
            }
            return notificationBuild;
        }
        if (i2 >= 21) {
            this.f1596a.setExtras(this.f1601f);
            Notification notificationBuild2 = this.f1596a.build();
            RemoteViews remoteViews = this.f1598c;
            if (remoteViews != null) {
                notificationBuild2.contentView = remoteViews;
            }
            RemoteViews remoteViews2 = this.f1599d;
            if (remoteViews2 != null) {
                notificationBuild2.bigContentView = remoteViews2;
            }
            RemoteViews remoteViews3 = this.f1603h;
            if (remoteViews3 != null) {
                notificationBuild2.headsUpContentView = remoteViews3;
            }
            if (this.f1602g != 0) {
                if (notificationBuild2.getGroup() != null && (notificationBuild2.flags & 512) != 0 && this.f1602g == 2) {
                    d(notificationBuild2);
                }
                if (notificationBuild2.getGroup() != null && (notificationBuild2.flags & 512) == 0 && this.f1602g == 1) {
                    d(notificationBuild2);
                }
            }
            return notificationBuild2;
        }
        if (i2 >= 20) {
            this.f1596a.setExtras(this.f1601f);
            Notification notificationBuild3 = this.f1596a.build();
            RemoteViews remoteViews4 = this.f1598c;
            if (remoteViews4 != null) {
                notificationBuild3.contentView = remoteViews4;
            }
            RemoteViews remoteViews5 = this.f1599d;
            if (remoteViews5 != null) {
                notificationBuild3.bigContentView = remoteViews5;
            }
            if (this.f1602g != 0) {
                if (notificationBuild3.getGroup() != null && (notificationBuild3.flags & 512) != 0 && this.f1602g == 2) {
                    d(notificationBuild3);
                }
                if (notificationBuild3.getGroup() != null && (notificationBuild3.flags & 512) == 0 && this.f1602g == 1) {
                    d(notificationBuild3);
                }
            }
            return notificationBuild3;
        }
        if (i2 >= 19) {
            SparseArray<Bundle> sparseArrayA = g.a(this.f1600e);
            if (sparseArrayA != null) {
                this.f1601f.putSparseParcelableArray("android.support.actionExtras", sparseArrayA);
            }
            this.f1596a.setExtras(this.f1601f);
            Notification notificationBuild4 = this.f1596a.build();
            RemoteViews remoteViews6 = this.f1598c;
            if (remoteViews6 != null) {
                notificationBuild4.contentView = remoteViews6;
            }
            RemoteViews remoteViews7 = this.f1599d;
            if (remoteViews7 != null) {
                notificationBuild4.bigContentView = remoteViews7;
            }
            return notificationBuild4;
        }
        if (i2 < 16) {
            return this.f1596a.getNotification();
        }
        Notification notificationBuild5 = this.f1596a.build();
        Bundle bundleA = e.a(notificationBuild5);
        Bundle bundle = new Bundle(this.f1601f);
        for (String str : this.f1601f.keySet()) {
            if (bundleA.containsKey(str)) {
                bundle.remove(str);
            }
        }
        bundleA.putAll(bundle);
        SparseArray<Bundle> sparseArrayA2 = g.a(this.f1600e);
        if (sparseArrayA2 != null) {
            e.a(notificationBuild5).putSparseParcelableArray("android.support.actionExtras", sparseArrayA2);
        }
        RemoteViews remoteViews8 = this.f1598c;
        if (remoteViews8 != null) {
            notificationBuild5.contentView = remoteViews8;
        }
        RemoteViews remoteViews9 = this.f1599d;
        if (remoteViews9 != null) {
            notificationBuild5.bigContentView = remoteViews9;
        }
        return notificationBuild5;
    }

    public final void d(Notification notification) {
        notification.sound = null;
        notification.vibrate = null;
        int i2 = notification.defaults & (-2);
        notification.defaults = i2;
        notification.defaults = i2 & (-3);
    }
}
