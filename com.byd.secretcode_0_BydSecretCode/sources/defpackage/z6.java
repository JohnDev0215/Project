package defpackage;

import android.app.Notification;
import android.app.RemoteInput;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import androidx.core.graphics.drawable.IconCompat;
import defpackage.y6;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: NotificationCompatBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class z6 {
    public final Notification.Builder a;
    public final y6.c b;
    public RemoteViews c;
    public RemoteViews d;
    public final List<Bundle> e = new ArrayList();
    public final Bundle f = new Bundle();
    public int g;
    public RemoteViews h;

    public z6(y6.c cVar) {
        ArrayList<String> arrayList;
        this.b = cVar;
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            this.a = new Notification.Builder(cVar.a, cVar.I);
        } else {
            this.a = new Notification.Builder(cVar.a);
        }
        Notification notification = cVar.P;
        this.a.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, cVar.h).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(cVar.d).setContentText(cVar.e).setContentInfo(cVar.j).setContentIntent(cVar.f).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(cVar.g, (notification.flags & 128) != 0).setLargeIcon(cVar.i).setNumber(cVar.k).setProgress(cVar.r, cVar.s, cVar.t);
        if (i < 21) {
            this.a.setSound(notification.sound, notification.audioStreamType);
        }
        if (i >= 16) {
            this.a.setSubText(cVar.p).setUsesChronometer(cVar.n).setPriority(cVar.l);
            Iterator<y6.a> it = cVar.b.iterator();
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
            this.c = cVar.F;
            this.d = cVar.G;
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 19) {
            this.a.setShowWhen(cVar.m);
            if (i2 < 21 && (arrayList = cVar.R) != null && !arrayList.isEmpty()) {
                Bundle bundle2 = this.f;
                ArrayList<String> arrayList2 = cVar.R;
                bundle2.putStringArray("android.people", (String[]) arrayList2.toArray(new String[arrayList2.size()]));
            }
        }
        if (i2 >= 20) {
            this.a.setLocalOnly(cVar.x).setGroup(cVar.u).setGroupSummary(cVar.v).setSortKey(cVar.w);
            this.g = cVar.M;
        }
        if (i2 >= 21) {
            this.a.setCategory(cVar.A).setColor(cVar.C).setVisibility(cVar.D).setPublicVersion(cVar.E).setSound(notification.sound, notification.audioAttributes);
            Iterator<String> it2 = cVar.R.iterator();
            while (it2.hasNext()) {
                this.a.addPerson(it2.next());
            }
            this.h = cVar.H;
            if (cVar.c.size() > 0) {
                Bundle bundle3 = cVar.b().getBundle("android.car.EXTENSIONS");
                bundle3 = bundle3 == null ? new Bundle() : bundle3;
                Bundle bundle4 = new Bundle();
                for (int i3 = 0; i3 < cVar.c.size(); i3++) {
                    bundle4.putBundle(Integer.toString(i3), a7.b(cVar.c.get(i3)));
                }
                bundle3.putBundle("invisible_actions", bundle4);
                cVar.b().putBundle("android.car.EXTENSIONS", bundle3);
                this.f.putBundle("android.car.EXTENSIONS", bundle3);
            }
        }
        int i4 = Build.VERSION.SDK_INT;
        if (i4 >= 24) {
            this.a.setExtras(cVar.B).setRemoteInputHistory(cVar.q);
            RemoteViews remoteViews = cVar.F;
            if (remoteViews != null) {
                this.a.setCustomContentView(remoteViews);
            }
            RemoteViews remoteViews2 = cVar.G;
            if (remoteViews2 != null) {
                this.a.setCustomBigContentView(remoteViews2);
            }
            RemoteViews remoteViews3 = cVar.H;
            if (remoteViews3 != null) {
                this.a.setCustomHeadsUpContentView(remoteViews3);
            }
        }
        if (i4 >= 26) {
            this.a.setBadgeIconType(cVar.J).setShortcutId(cVar.K).setTimeoutAfter(cVar.L).setGroupAlertBehavior(cVar.M);
            if (cVar.z) {
                this.a.setColorized(cVar.y);
            }
            if (!TextUtils.isEmpty(cVar.I)) {
                this.a.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
        if (i4 >= 29) {
            this.a.setAllowSystemGeneratedContextualActions(cVar.N);
            this.a.setBubbleMetadata(y6.b.a(cVar.O));
        }
        if (cVar.Q) {
            if (this.b.v) {
                this.g = 2;
            } else {
                this.g = 1;
            }
            this.a.setVibrate(null);
            this.a.setSound(null);
            int i5 = notification.defaults & (-2);
            notification.defaults = i5;
            int i6 = i5 & (-3);
            notification.defaults = i6;
            this.a.setDefaults(i6);
            if (i4 >= 26) {
                if (TextUtils.isEmpty(this.b.u)) {
                    this.a.setGroup("silent");
                }
                this.a.setGroupAlertBehavior(this.g);
            }
        }
    }

    public final void a(y6.a aVar) {
        int i = Build.VERSION.SDK_INT;
        if (i < 20) {
            if (i >= 16) {
                this.e.add(a7.f(this.a, aVar));
                return;
            }
            return;
        }
        IconCompat iconCompatE = aVar.e();
        Notification.Action.Builder builder = i >= 23 ? new Notification.Action.Builder(iconCompatE != null ? iconCompatE.l() : null, aVar.i(), aVar.a()) : new Notification.Action.Builder(iconCompatE != null ? iconCompatE.c() : 0, aVar.i(), aVar.a());
        if (aVar.f() != null) {
            for (RemoteInput remoteInput : b7.b(aVar.f())) {
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
        this.a.addAction(builder.build());
    }

    public Notification b() {
        y6.d dVar = this.b.o;
        if (dVar != null || dVar != null) {
            throw null;
        }
        Notification notificationC = c();
        y6.c cVar = this.b;
        RemoteViews remoteViews = cVar.F;
        if (remoteViews != null) {
            notificationC.contentView = remoteViews;
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 16 && dVar != null) {
            throw null;
        }
        if (i >= 21 && dVar != null) {
            y6.d dVar2 = cVar.o;
            throw null;
        }
        if (i < 16 || dVar == null || y6.a(notificationC) == null) {
            return notificationC;
        }
        throw null;
    }

    public Notification c() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            return this.a.build();
        }
        if (i >= 24) {
            Notification notificationBuild = this.a.build();
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
            this.a.setExtras(this.f);
            Notification notificationBuild2 = this.a.build();
            RemoteViews remoteViews = this.c;
            if (remoteViews != null) {
                notificationBuild2.contentView = remoteViews;
            }
            RemoteViews remoteViews2 = this.d;
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
            this.a.setExtras(this.f);
            Notification notificationBuild3 = this.a.build();
            RemoteViews remoteViews4 = this.c;
            if (remoteViews4 != null) {
                notificationBuild3.contentView = remoteViews4;
            }
            RemoteViews remoteViews5 = this.d;
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
            SparseArray<Bundle> sparseArrayA = a7.a(this.e);
            if (sparseArrayA != null) {
                this.f.putSparseParcelableArray("android.support.actionExtras", sparseArrayA);
            }
            this.a.setExtras(this.f);
            Notification notificationBuild4 = this.a.build();
            RemoteViews remoteViews6 = this.c;
            if (remoteViews6 != null) {
                notificationBuild4.contentView = remoteViews6;
            }
            RemoteViews remoteViews7 = this.d;
            if (remoteViews7 != null) {
                notificationBuild4.bigContentView = remoteViews7;
            }
            return notificationBuild4;
        }
        if (i < 16) {
            return this.a.getNotification();
        }
        Notification notificationBuild5 = this.a.build();
        Bundle bundleA = y6.a(notificationBuild5);
        Bundle bundle = new Bundle(this.f);
        for (String str : this.f.keySet()) {
            if (bundleA.containsKey(str)) {
                bundle.remove(str);
            }
        }
        bundleA.putAll(bundle);
        SparseArray<Bundle> sparseArrayA2 = a7.a(this.e);
        if (sparseArrayA2 != null) {
            y6.a(notificationBuild5).putSparseParcelableArray("android.support.actionExtras", sparseArrayA2);
        }
        RemoteViews remoteViews8 = this.c;
        if (remoteViews8 != null) {
            notificationBuild5.contentView = remoteViews8;
        }
        RemoteViews remoteViews9 = this.d;
        if (remoteViews9 != null) {
            notificationBuild5.bigContentView = remoteViews9;
        }
        return notificationBuild5;
    }

    public final void d(Notification notification) {
        notification.sound = null;
        notification.vibrate = null;
        int i = notification.defaults & (-2);
        notification.defaults = i;
        notification.defaults = i & (-3);
    }
}
