package androidx.core.app;

import android.app.Notification;
import android.app.RemoteInput;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import androidx.core.app.e;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: NotificationCompatBuilder.java */
/* JADX INFO: loaded from: classes.dex */
class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Notification.Builder f860a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e.c f861b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private RemoteViews f862c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private RemoteViews f863d;
    private final List<Bundle> e = new ArrayList();
    private final Bundle f = new Bundle();
    private int g;
    private RemoteViews h;

    f(e.c cVar) {
        ArrayList<String> arrayList;
        this.f861b = cVar;
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            this.f860a = new Notification.Builder(cVar.f856a, cVar.I);
        } else {
            this.f860a = new Notification.Builder(cVar.f856a);
        }
        Notification notification = cVar.P;
        this.f860a.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, cVar.h).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(cVar.f859d).setContentText(cVar.e).setContentInfo(cVar.j).setContentIntent(cVar.f).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(cVar.g, (notification.flags & 128) != 0).setLargeIcon(cVar.i).setNumber(cVar.k).setProgress(cVar.r, cVar.s, cVar.t);
        if (i < 21) {
            this.f860a.setSound(notification.sound, notification.audioStreamType);
        }
        if (i >= 16) {
            this.f860a.setSubText(cVar.p).setUsesChronometer(cVar.n).setPriority(cVar.l);
            Iterator<e.a> it = cVar.f857b.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
            Bundle bundle = cVar.B;
            if (bundle != null) {
                this.f.putAll(bundle);
            }
            if (Build.VERSION.SDK_INT < 20) {
                if (cVar.x) {
                    this.f.putBoolean("android.support.localOnly", true);
                }
                String str = cVar.u;
                if (str != null) {
                    this.f.putString("android.support.groupKey", str);
                    if (cVar.v) {
                        this.f.putBoolean("android.support.isGroupSummary", true);
                    } else {
                        this.f.putBoolean("android.support.useSideChannel", true);
                    }
                }
                String str2 = cVar.w;
                if (str2 != null) {
                    this.f.putString("android.support.sortKey", str2);
                }
            }
            this.f862c = cVar.F;
            this.f863d = cVar.G;
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 19) {
            this.f860a.setShowWhen(cVar.m);
            if (i2 < 21 && (arrayList = cVar.R) != null && !arrayList.isEmpty()) {
                Bundle bundle2 = this.f;
                ArrayList<String> arrayList2 = cVar.R;
                bundle2.putStringArray("android.people", (String[]) arrayList2.toArray(new String[arrayList2.size()]));
            }
        }
        if (i2 >= 20) {
            this.f860a.setLocalOnly(cVar.x).setGroup(cVar.u).setGroupSummary(cVar.v).setSortKey(cVar.w);
            this.g = cVar.M;
        }
        if (i2 >= 21) {
            this.f860a.setCategory(cVar.A).setColor(cVar.C).setVisibility(cVar.D).setPublicVersion(cVar.E).setSound(notification.sound, notification.audioAttributes);
            Iterator<String> it2 = cVar.R.iterator();
            while (it2.hasNext()) {
                this.f860a.addPerson(it2.next());
            }
            this.h = cVar.H;
            if (cVar.f858c.size() > 0) {
                Bundle bundle3 = cVar.b().getBundle("android.car.EXTENSIONS");
                bundle3 = bundle3 == null ? new Bundle() : bundle3;
                Bundle bundle4 = new Bundle();
                for (int i3 = 0; i3 < cVar.f858c.size(); i3++) {
                    bundle4.putBundle(Integer.toString(i3), g.b(cVar.f858c.get(i3)));
                }
                bundle3.putBundle("invisible_actions", bundle4);
                cVar.b().putBundle("android.car.EXTENSIONS", bundle3);
                this.f.putBundle("android.car.EXTENSIONS", bundle3);
            }
        }
        int i4 = Build.VERSION.SDK_INT;
        if (i4 >= 24) {
            this.f860a.setExtras(cVar.B).setRemoteInputHistory(cVar.q);
            RemoteViews remoteViews = cVar.F;
            if (remoteViews != null) {
                this.f860a.setCustomContentView(remoteViews);
            }
            RemoteViews remoteViews2 = cVar.G;
            if (remoteViews2 != null) {
                this.f860a.setCustomBigContentView(remoteViews2);
            }
            RemoteViews remoteViews3 = cVar.H;
            if (remoteViews3 != null) {
                this.f860a.setCustomHeadsUpContentView(remoteViews3);
            }
        }
        if (i4 >= 26) {
            this.f860a.setBadgeIconType(cVar.J).setShortcutId(cVar.K).setTimeoutAfter(cVar.L).setGroupAlertBehavior(cVar.M);
            if (cVar.z) {
                this.f860a.setColorized(cVar.y);
            }
            if (!TextUtils.isEmpty(cVar.I)) {
                this.f860a.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
        if (i4 >= 29) {
            this.f860a.setAllowSystemGeneratedContextualActions(cVar.N);
            this.f860a.setBubbleMetadata(e.b.a(cVar.O));
        }
        if (cVar.Q) {
            if (this.f861b.v) {
                this.g = 2;
            } else {
                this.g = 1;
            }
            this.f860a.setVibrate(null);
            this.f860a.setSound(null);
            int i5 = notification.defaults & (-2);
            notification.defaults = i5;
            int i6 = i5 & (-3);
            notification.defaults = i6;
            this.f860a.setDefaults(i6);
            if (i4 >= 26) {
                if (TextUtils.isEmpty(this.f861b.u)) {
                    this.f860a.setGroup("silent");
                }
                this.f860a.setGroupAlertBehavior(this.g);
            }
        }
    }

    private void a(e.a aVar) {
        int i = Build.VERSION.SDK_INT;
        if (i < 20) {
            if (i >= 16) {
                this.e.add(g.f(this.f860a, aVar));
                return;
            }
            return;
        }
        IconCompat iconCompatE = aVar.e();
        Notification.Action.Builder builder = i >= 23 ? new Notification.Action.Builder(iconCompatE != null ? iconCompatE.l() : null, aVar.i(), aVar.a()) : new Notification.Action.Builder(iconCompatE != null ? iconCompatE.c() : 0, aVar.i(), aVar.a());
        if (aVar.f() != null) {
            for (RemoteInput remoteInput : h.b(aVar.f())) {
                builder.addRemoteInput(remoteInput);
            }
        }
        Bundle bundle = aVar.d() != null ? new Bundle(aVar.d()) : new Bundle();
        bundle.putBoolean("android.support.allowGeneratedReplies", aVar.b());
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 24) {
            builder.setAllowGeneratedReplies(aVar.b());
        }
        bundle.putInt("android.support.action.semanticAction", aVar.g());
        if (i2 >= 28) {
            builder.setSemanticAction(aVar.g());
        }
        if (i2 >= 29) {
            builder.setContextual(aVar.j());
        }
        bundle.putBoolean("android.support.action.showsUserInterface", aVar.h());
        builder.addExtras(bundle);
        this.f860a.addAction(builder.build());
    }

    private void d(Notification notification) {
        notification.sound = null;
        notification.vibrate = null;
        int i = notification.defaults & (-2);
        notification.defaults = i;
        notification.defaults = i & (-3);
    }

    public Notification b() {
        e.d dVar = this.f861b.o;
        if (dVar != null || dVar != null) {
            throw null;
        }
        Notification notificationC = c();
        e.c cVar = this.f861b;
        RemoteViews remoteViews = cVar.F;
        if (remoteViews != null) {
            notificationC.contentView = remoteViews;
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 16 && dVar != null) {
            throw null;
        }
        if (i >= 21 && dVar != null) {
            e.d dVar2 = cVar.o;
            throw null;
        }
        if (i < 16 || dVar == null || e.a(notificationC) == null) {
            return notificationC;
        }
        throw null;
    }

    protected Notification c() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            return this.f860a.build();
        }
        if (i >= 24) {
            Notification notificationBuild = this.f860a.build();
            if (this.g != 0) {
                if (notificationBuild.getGroup() != null && (notificationBuild.flags & 512) != 0 && this.g == 2) {
                    d(notificationBuild);
                }
                if (notificationBuild.getGroup() != null && (notificationBuild.flags & 512) == 0 && this.g == 1) {
                    d(notificationBuild);
                }
            }
            return notificationBuild;
        }
        if (i >= 21) {
            this.f860a.setExtras(this.f);
            Notification notificationBuild2 = this.f860a.build();
            RemoteViews remoteViews = this.f862c;
            if (remoteViews != null) {
                notificationBuild2.contentView = remoteViews;
            }
            RemoteViews remoteViews2 = this.f863d;
            if (remoteViews2 != null) {
                notificationBuild2.bigContentView = remoteViews2;
            }
            RemoteViews remoteViews3 = this.h;
            if (remoteViews3 != null) {
                notificationBuild2.headsUpContentView = remoteViews3;
            }
            if (this.g != 0) {
                if (notificationBuild2.getGroup() != null && (notificationBuild2.flags & 512) != 0 && this.g == 2) {
                    d(notificationBuild2);
                }
                if (notificationBuild2.getGroup() != null && (notificationBuild2.flags & 512) == 0 && this.g == 1) {
                    d(notificationBuild2);
                }
            }
            return notificationBuild2;
        }
        if (i >= 20) {
            this.f860a.setExtras(this.f);
            Notification notificationBuild3 = this.f860a.build();
            RemoteViews remoteViews4 = this.f862c;
            if (remoteViews4 != null) {
                notificationBuild3.contentView = remoteViews4;
            }
            RemoteViews remoteViews5 = this.f863d;
            if (remoteViews5 != null) {
                notificationBuild3.bigContentView = remoteViews5;
            }
            if (this.g != 0) {
                if (notificationBuild3.getGroup() != null && (notificationBuild3.flags & 512) != 0 && this.g == 2) {
                    d(notificationBuild3);
                }
                if (notificationBuild3.getGroup() != null && (notificationBuild3.flags & 512) == 0 && this.g == 1) {
                    d(notificationBuild3);
                }
            }
            return notificationBuild3;
        }
        if (i >= 19) {
            SparseArray<Bundle> sparseArrayA = g.a(this.e);
            if (sparseArrayA != null) {
                this.f.putSparseParcelableArray("android.support.actionExtras", sparseArrayA);
            }
            this.f860a.setExtras(this.f);
            Notification notificationBuild4 = this.f860a.build();
            RemoteViews remoteViews6 = this.f862c;
            if (remoteViews6 != null) {
                notificationBuild4.contentView = remoteViews6;
            }
            RemoteViews remoteViews7 = this.f863d;
            if (remoteViews7 != null) {
                notificationBuild4.bigContentView = remoteViews7;
            }
            return notificationBuild4;
        }
        if (i < 16) {
            return this.f860a.getNotification();
        }
        Notification notificationBuild5 = this.f860a.build();
        Bundle bundleA = e.a(notificationBuild5);
        Bundle bundle = new Bundle(this.f);
        for (String str : this.f.keySet()) {
            if (bundleA.containsKey(str)) {
                bundle.remove(str);
            }
        }
        bundleA.putAll(bundle);
        SparseArray<Bundle> sparseArrayA2 = g.a(this.e);
        if (sparseArrayA2 != null) {
            e.a(notificationBuild5).putSparseParcelableArray("android.support.actionExtras", sparseArrayA2);
        }
        RemoteViews remoteViews8 = this.f862c;
        if (remoteViews8 != null) {
            notificationBuild5.contentView = remoteViews8;
        }
        RemoteViews remoteViews9 = this.f863d;
        if (remoteViews9 != null) {
            notificationBuild5.bigContentView = remoteViews9;
        }
        return notificationBuild5;
    }
}
