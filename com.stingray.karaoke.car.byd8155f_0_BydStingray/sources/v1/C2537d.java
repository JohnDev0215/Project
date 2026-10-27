package v1;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;
import h2.AbstractC1343A;
import h3.y;
import java.util.ArrayList;
import java.util.Iterator;
import k3.A0;
import l3.L;

/* JADX INFO: renamed from: v1.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2537d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f22949a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f22951e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f22952f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public PendingIntent f22953g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public IconCompat f22954h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f22956j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public y f22957k;
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Bundle f22958m;
    public final String o;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f22961q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Notification f22962r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final ArrayList f22963s;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f22950b = new ArrayList();
    public final ArrayList c = new ArrayList();
    public final ArrayList d = new ArrayList();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f22955i = true;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f22959n = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f22960p = 0;

    public C2537d(Context context) {
        Notification notification = new Notification();
        this.f22962r = notification;
        this.f22949a = context;
        this.o = "default_channel_id";
        notification.when = System.currentTimeMillis();
        notification.audioStreamType = -1;
        this.f22963s = new ArrayList();
        this.f22961q = true;
    }

    public final void a(C2536c c2536c) {
        this.f22950b.add(c2536c);
    }

    public final Notification b() {
        int i7;
        ArrayList arrayList;
        int i10;
        int i11;
        new ArrayList();
        Bundle bundle = new Bundle();
        Context context = this.f22949a;
        String str = this.o;
        Notification.Builder builder = new Notification.Builder(context, str);
        Notification notification = this.f22962r;
        builder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, null).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(this.f22951e).setContentText(this.f22952f).setContentInfo(null).setContentIntent(this.f22953g).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(null, (notification.flags & 128) != 0).setNumber(0).setProgress(0, 0, false);
        IconCompat iconCompat = this.f22954h;
        builder.setLargeIcon(iconCompat == null ? null : iconCompat.e(context));
        builder.setSubText(null).setUsesChronometer(this.f22956j).setPriority(0);
        for (C2536c c2536c : this.f22950b) {
            if (c2536c.f22945b == null && (i11 = c2536c.f22946e) != 0) {
                c2536c.f22945b = IconCompat.b(null, "", i11);
            }
            IconCompat iconCompat2 = c2536c.f22945b;
            Notification.Action.Builder builder2 = new Notification.Action.Builder(iconCompat2 != null ? iconCompat2.e(null) : null, c2536c.f22947f, c2536c.f22948g);
            Bundle bundle2 = c2536c.f22944a;
            Bundle bundle3 = bundle2 != null ? new Bundle(bundle2) : new Bundle();
            boolean z6 = c2536c.c;
            bundle3.putBoolean("android.support.allowGeneratedReplies", z6);
            int i12 = Build.VERSION.SDK_INT;
            builder2.setAllowGeneratedReplies(z6);
            bundle3.putInt("android.support.action.semanticAction", 0);
            builder2.setSemanticAction(0);
            builder2.setContextual(false);
            if (i12 >= 31) {
                AbstractC2538e.a(builder2);
            }
            bundle3.putBoolean("android.support.action.showsUserInterface", c2536c.d);
            builder2.addExtras(bundle3);
            builder.addAction(builder2.build());
        }
        Bundle bundle4 = this.f22958m;
        if (bundle4 != null) {
            bundle.putAll(bundle4);
        }
        builder.setShowWhen(this.f22955i);
        builder.setLocalOnly(false);
        builder.setGroup(this.l);
        builder.setSortKey(null);
        builder.setGroupSummary(false);
        builder.setCategory(null);
        builder.setColor(0);
        builder.setVisibility(this.f22959n);
        builder.setPublicVersion(null);
        builder.setSound(notification.sound, notification.audioAttributes);
        ArrayList arrayList2 = this.f22963s;
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                builder.addPerson((String) it.next());
            }
        }
        ArrayList arrayList3 = this.d;
        if (arrayList3.size() > 0) {
            if (this.f22958m == null) {
                this.f22958m = new Bundle();
            }
            Bundle bundle5 = this.f22958m.getBundle("android.car.EXTENSIONS");
            if (bundle5 == null) {
                bundle5 = new Bundle();
            }
            Bundle bundle6 = new Bundle(bundle5);
            Bundle bundle7 = new Bundle();
            int i13 = 0;
            while (i13 < arrayList3.size()) {
                String string = Integer.toString(i13);
                C2536c c2536c2 = (C2536c) arrayList3.get(i13);
                Bundle bundle8 = new Bundle();
                if (c2536c2.f22945b != null || (i10 = c2536c2.f22946e) == 0) {
                    arrayList = arrayList3;
                } else {
                    arrayList = arrayList3;
                    c2536c2.f22945b = IconCompat.b(null, "", i10);
                }
                IconCompat iconCompat3 = c2536c2.f22945b;
                bundle8.putInt("icon", iconCompat3 != null ? iconCompat3.c() : 0);
                bundle8.putCharSequence("title", c2536c2.f22947f);
                bundle8.putParcelable("actionIntent", c2536c2.f22948g);
                Bundle bundle9 = c2536c2.f22944a;
                Bundle bundle10 = bundle9 != null ? new Bundle(bundle9) : new Bundle();
                bundle10.putBoolean("android.support.allowGeneratedReplies", c2536c2.c);
                bundle8.putBundle("extras", bundle10);
                bundle8.putParcelableArray("remoteInputs", null);
                bundle8.putBoolean("showsUserInterface", c2536c2.d);
                bundle8.putInt("semanticAction", 0);
                bundle7.putBundle(string, bundle8);
                i13++;
                arrayList3 = arrayList;
            }
            bundle5.putBundle("invisible_actions", bundle7);
            bundle6.putBundle("invisible_actions", bundle7);
            if (this.f22958m == null) {
                this.f22958m = new Bundle();
            }
            this.f22958m.putBundle("android.car.EXTENSIONS", bundle5);
            bundle.putBundle("android.car.EXTENSIONS", bundle6);
        }
        int i14 = Build.VERSION.SDK_INT;
        builder.setExtras(this.f22958m);
        builder.setRemoteInputHistory(null);
        builder.setBadgeIconType(0);
        builder.setSettingsText(null);
        builder.setShortcutId(null);
        builder.setTimeoutAfter(0L);
        builder.setGroupAlertBehavior(0);
        if (!TextUtils.isEmpty(str)) {
            builder.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
        }
        Iterator it2 = this.c.iterator();
        if (it2.hasNext()) {
            it2.next().getClass();
            throw new ClassCastException();
        }
        builder.setAllowSystemGeneratedContextualActions(this.f22961q);
        builder.setBubbleMetadata(null);
        if (i14 >= 31 && (i7 = this.f22960p) != 0) {
            AbstractC2538e.b(builder, i7);
        }
        y yVar = this.f22957k;
        if (yVar != null) {
            Notification.MediaStyle mediaStyle = new Notification.MediaStyle();
            A0 a02 = (A0) yVar.f15500n;
            Notification.MediaStyle mediaSession = mediaStyle.setMediaSession((MediaSession.Token) ((L) a02.f17714a.f17801h.f17847j.f18537m).c.f18534m);
            int[] iArr = (int[]) yVar.o;
            if (iArr != null) {
                mediaSession.setShowActionsInCompactView(iArr);
            }
            int i15 = AbstractC1343A.f15195a;
            builder.setStyle(mediaSession);
            Bundle bundle11 = new Bundle();
            bundle11.putBundle("androidx.media3.session", a02.f17714a.f17803j.b());
            builder.addExtras(bundle11);
        }
        Notification notificationBuild = builder.build();
        if (yVar != null) {
            this.f22957k.getClass();
        }
        if (yVar != null) {
            Bundle bundle12 = notificationBuild.extras;
        }
        return notificationBuild;
    }

    public final void c(Bitmap bitmap) {
        IconCompat iconCompat;
        if (bitmap == null) {
            iconCompat = null;
        } else {
            IconCompat iconCompat2 = new IconCompat(1);
            iconCompat2.f11743b = bitmap;
            iconCompat = iconCompat2;
        }
        this.f22954h = iconCompat;
    }

    public final void d(y yVar) {
        if (this.f22957k != yVar) {
            this.f22957k = yVar;
            if (((C2537d) yVar.f15499m) != this) {
                yVar.f15499m = this;
                d(yVar);
            }
        }
    }
}
