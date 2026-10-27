package d0;

import android.app.Notification;
import android.app.RemoteInput;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import androidx.core.graphics.drawable.IconCompat;
import d0.e;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: NotificationCompatBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Notification.Builder f4320a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e.c f4321b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public RemoteViews f4322c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public RemoteViews f4323d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List<Bundle> f4324e = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Bundle f4325f = new Bundle();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4326g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public RemoteViews f4327h;

    public f(e.c cVar) {
        ArrayList<String> arrayList;
        this.f4321b = cVar;
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 26) {
            this.f4320a = new Notification.Builder(cVar.f4294a, cVar.I);
        } else {
            this.f4320a = new Notification.Builder(cVar.f4294a);
        }
        Notification notification = cVar.P;
        this.f4320a.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, cVar.f4301h).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(cVar.f4297d).setContentText(cVar.f4298e).setContentInfo(cVar.f4303j).setContentIntent(cVar.f4299f).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(cVar.f4300g, (notification.flags & 128) != 0).setLargeIcon(cVar.f4302i).setNumber(cVar.f4304k).setProgress(cVar.f4311r, cVar.f4312s, cVar.f4313t);
        if (i8 < 21) {
            this.f4320a.setSound(notification.sound, notification.audioStreamType);
        }
        if (i8 >= 16) {
            this.f4320a.setSubText(cVar.f4309p).setUsesChronometer(cVar.f4307n).setPriority(cVar.f4305l);
            Iterator<e.a> it = cVar.f4295b.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
            Bundle bundle = cVar.B;
            if (bundle != null) {
                this.f4325f.putAll(bundle);
            }
            if (Build.VERSION.SDK_INT < 20) {
                if (cVar.f4317x) {
                    this.f4325f.putBoolean("android.support.localOnly", true);
                }
                String str = cVar.f4314u;
                if (str != null) {
                    this.f4325f.putString("android.support.groupKey", str);
                    if (cVar.f4315v) {
                        this.f4325f.putBoolean("android.support.isGroupSummary", true);
                    } else {
                        this.f4325f.putBoolean("android.support.useSideChannel", true);
                    }
                }
                String str2 = cVar.f4316w;
                if (str2 != null) {
                    this.f4325f.putString("android.support.sortKey", str2);
                }
            }
            this.f4322c = cVar.F;
            this.f4323d = cVar.G;
        }
        int i9 = Build.VERSION.SDK_INT;
        if (i9 >= 19) {
            this.f4320a.setShowWhen(cVar.f4306m);
            if (i9 < 21 && (arrayList = cVar.R) != null && !arrayList.isEmpty()) {
                Bundle bundle2 = this.f4325f;
                ArrayList<String> arrayList2 = cVar.R;
                bundle2.putStringArray("android.people", (String[]) arrayList2.toArray(new String[arrayList2.size()]));
            }
        }
        if (i9 >= 20) {
            this.f4320a.setLocalOnly(cVar.f4317x).setGroup(cVar.f4314u).setGroupSummary(cVar.f4315v).setSortKey(cVar.f4316w);
            this.f4326g = cVar.M;
        }
        if (i9 >= 21) {
            this.f4320a.setCategory(cVar.A).setColor(cVar.C).setVisibility(cVar.D).setPublicVersion(cVar.E).setSound(notification.sound, notification.audioAttributes);
            Iterator<String> it2 = cVar.R.iterator();
            while (it2.hasNext()) {
                this.f4320a.addPerson(it2.next());
            }
            this.f4327h = cVar.H;
            if (cVar.f4296c.size() > 0) {
                Bundle bundle3 = cVar.b().getBundle("android.car.EXTENSIONS");
                bundle3 = bundle3 == null ? new Bundle() : bundle3;
                Bundle bundle4 = new Bundle();
                for (int i10 = 0; i10 < cVar.f4296c.size(); i10++) {
                    bundle4.putBundle(Integer.toString(i10), g.b(cVar.f4296c.get(i10)));
                }
                bundle3.putBundle("invisible_actions", bundle4);
                cVar.b().putBundle("android.car.EXTENSIONS", bundle3);
                this.f4325f.putBundle("android.car.EXTENSIONS", bundle3);
            }
        }
        int i11 = Build.VERSION.SDK_INT;
        if (i11 >= 24) {
            this.f4320a.setExtras(cVar.B).setRemoteInputHistory(cVar.f4310q);
            RemoteViews remoteViews = cVar.F;
            if (remoteViews != null) {
                this.f4320a.setCustomContentView(remoteViews);
            }
            RemoteViews remoteViews2 = cVar.G;
            if (remoteViews2 != null) {
                this.f4320a.setCustomBigContentView(remoteViews2);
            }
            RemoteViews remoteViews3 = cVar.H;
            if (remoteViews3 != null) {
                this.f4320a.setCustomHeadsUpContentView(remoteViews3);
            }
        }
        if (i11 >= 26) {
            this.f4320a.setBadgeIconType(cVar.J).setShortcutId(cVar.K).setTimeoutAfter(cVar.L).setGroupAlertBehavior(cVar.M);
            if (cVar.f4319z) {
                this.f4320a.setColorized(cVar.f4318y);
            }
            if (!TextUtils.isEmpty(cVar.I)) {
                this.f4320a.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
        if (i11 >= 29) {
            this.f4320a.setAllowSystemGeneratedContextualActions(cVar.N);
            this.f4320a.setBubbleMetadata(e.b.a(cVar.O));
        }
        if (cVar.Q) {
            if (this.f4321b.f4315v) {
                this.f4326g = 2;
            } else {
                this.f4326g = 1;
            }
            this.f4320a.setVibrate(null);
            this.f4320a.setSound(null);
            int i12 = notification.defaults & (-2);
            notification.defaults = i12;
            int i13 = i12 & (-3);
            notification.defaults = i13;
            this.f4320a.setDefaults(i13);
            if (i11 >= 26) {
                if (TextUtils.isEmpty(this.f4321b.f4314u)) {
                    this.f4320a.setGroup("silent");
                }
                this.f4320a.setGroupAlertBehavior(this.f4326g);
            }
        }
    }

    public final void a(e.a aVar) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 < 20) {
            if (i8 >= 16) {
                this.f4324e.add(g.f(this.f4320a, aVar));
                return;
            }
            return;
        }
        IconCompat iconCompatE = aVar.e();
        Notification.Action.Builder builder = i8 >= 23 ? new Notification.Action.Builder(iconCompatE != null ? iconCompatE.l() : null, aVar.i(), aVar.a()) : new Notification.Action.Builder(iconCompatE != null ? iconCompatE.c() : 0, aVar.i(), aVar.a());
        if (aVar.f() != null) {
            for (RemoteInput remoteInput : h.b(aVar.f())) {
                builder.addRemoteInput(remoteInput);
            }
        }
        Bundle bundle = aVar.d() != null ? new Bundle(aVar.d()) : new Bundle();
        bundle.putBoolean("android.support.allowGeneratedReplies", aVar.b());
        int i9 = Build.VERSION.SDK_INT;
        if (i9 >= 24) {
            builder.setAllowGeneratedReplies(aVar.b());
        }
        bundle.putInt("android.support.action.semanticAction", aVar.g());
        if (i9 >= 28) {
            builder.setSemanticAction(aVar.g());
        }
        if (i9 >= 29) {
            builder.setContextual(aVar.j());
        }
        bundle.putBoolean("android.support.action.showsUserInterface", aVar.h());
        builder.addExtras(bundle);
        this.f4320a.addAction(builder.build());
    }

    public Notification b() {
        e.d dVar = this.f4321b.f4308o;
        if (dVar != null || dVar != null) {
            throw null;
        }
        Notification notificationC = c();
        e.c cVar = this.f4321b;
        RemoteViews remoteViews = cVar.F;
        if (remoteViews != null) {
            notificationC.contentView = remoteViews;
        }
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 16 && dVar != null) {
            throw null;
        }
        if (i8 >= 21 && dVar != null) {
            e.d dVar2 = cVar.f4308o;
            throw null;
        }
        if (i8 < 16 || dVar == null || e.a(notificationC) == null) {
            return notificationC;
        }
        throw null;
    }

    public Notification c() {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 26) {
            return this.f4320a.build();
        }
        if (i8 >= 24) {
            Notification notificationBuild = this.f4320a.build();
            if (this.f4326g != 0) {
                if (notificationBuild.getGroup() != null && (notificationBuild.flags & 512) != 0 && this.f4326g == 2) {
                    d(notificationBuild);
                }
                if (notificationBuild.getGroup() != null && (notificationBuild.flags & 512) == 0 && this.f4326g == 1) {
                    d(notificationBuild);
                }
            }
            return notificationBuild;
        }
        if (i8 >= 21) {
            this.f4320a.setExtras(this.f4325f);
            Notification notificationBuild2 = this.f4320a.build();
            RemoteViews remoteViews = this.f4322c;
            if (remoteViews != null) {
                notificationBuild2.contentView = remoteViews;
            }
            RemoteViews remoteViews2 = this.f4323d;
            if (remoteViews2 != null) {
                notificationBuild2.bigContentView = remoteViews2;
            }
            RemoteViews remoteViews3 = this.f4327h;
            if (remoteViews3 != null) {
                notificationBuild2.headsUpContentView = remoteViews3;
            }
            if (this.f4326g != 0) {
                if (notificationBuild2.getGroup() != null && (notificationBuild2.flags & 512) != 0 && this.f4326g == 2) {
                    d(notificationBuild2);
                }
                if (notificationBuild2.getGroup() != null && (notificationBuild2.flags & 512) == 0 && this.f4326g == 1) {
                    d(notificationBuild2);
                }
            }
            return notificationBuild2;
        }
        if (i8 >= 20) {
            this.f4320a.setExtras(this.f4325f);
            Notification notificationBuild3 = this.f4320a.build();
            RemoteViews remoteViews4 = this.f4322c;
            if (remoteViews4 != null) {
                notificationBuild3.contentView = remoteViews4;
            }
            RemoteViews remoteViews5 = this.f4323d;
            if (remoteViews5 != null) {
                notificationBuild3.bigContentView = remoteViews5;
            }
            if (this.f4326g != 0) {
                if (notificationBuild3.getGroup() != null && (notificationBuild3.flags & 512) != 0 && this.f4326g == 2) {
                    d(notificationBuild3);
                }
                if (notificationBuild3.getGroup() != null && (notificationBuild3.flags & 512) == 0 && this.f4326g == 1) {
                    d(notificationBuild3);
                }
            }
            return notificationBuild3;
        }
        if (i8 >= 19) {
            SparseArray<Bundle> sparseArrayA = g.a(this.f4324e);
            if (sparseArrayA != null) {
                this.f4325f.putSparseParcelableArray("android.support.actionExtras", sparseArrayA);
            }
            this.f4320a.setExtras(this.f4325f);
            Notification notificationBuild4 = this.f4320a.build();
            RemoteViews remoteViews6 = this.f4322c;
            if (remoteViews6 != null) {
                notificationBuild4.contentView = remoteViews6;
            }
            RemoteViews remoteViews7 = this.f4323d;
            if (remoteViews7 != null) {
                notificationBuild4.bigContentView = remoteViews7;
            }
            return notificationBuild4;
        }
        if (i8 < 16) {
            return this.f4320a.getNotification();
        }
        Notification notificationBuild5 = this.f4320a.build();
        Bundle bundleA = e.a(notificationBuild5);
        Bundle bundle = new Bundle(this.f4325f);
        for (String str : this.f4325f.keySet()) {
            if (bundleA.containsKey(str)) {
                bundle.remove(str);
            }
        }
        bundleA.putAll(bundle);
        SparseArray<Bundle> sparseArrayA2 = g.a(this.f4324e);
        if (sparseArrayA2 != null) {
            e.a(notificationBuild5).putSparseParcelableArray("android.support.actionExtras", sparseArrayA2);
        }
        RemoteViews remoteViews8 = this.f4322c;
        if (remoteViews8 != null) {
            notificationBuild5.contentView = remoteViews8;
        }
        RemoteViews remoteViews9 = this.f4323d;
        if (remoteViews9 != null) {
            notificationBuild5.bigContentView = remoteViews9;
        }
        return notificationBuild5;
    }

    public final void d(Notification notification) {
        notification.sound = null;
        notification.vibrate = null;
        int i8 = notification.defaults & (-2);
        notification.defaults = i8;
        notification.defaults = i8 & (-3);
    }
}
