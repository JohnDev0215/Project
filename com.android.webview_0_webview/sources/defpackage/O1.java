package defpackage;

import android.app.Notification;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class O1 {
    public final Notification.Builder a;
    public final M1 b;
    public RemoteViews c;
    public RemoteViews d;
    public final List e;
    public final Bundle f;
    public int g;
    public RemoteViews h;

    public O1(M1 m1) {
        int i = Build.VERSION.SDK_INT;
        this.e = new ArrayList();
        this.f = new Bundle();
        this.b = m1;
        if (i >= 26) {
            this.a = new Notification.Builder(m1.a, m1.q);
        } else {
            this.a = new Notification.Builder(m1.a);
        }
        Notification notification = m1.t;
        this.a.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, null).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(m1.d).setContentText(m1.e).setContentInfo(null).setContentIntent(m1.f).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(null, (notification.flags & 128) != 0).setLargeIcon((Bitmap) null).setNumber(0).setProgress(m1.j, m1.k, m1.l);
        this.a.setSubText(null).setUsesChronometer(false).setPriority(m1.g);
        for (K1 k1 : m1.b) {
            Notification.Action.Builder builder = new Notification.Action.Builder(k1.g, k1.h, k1.i);
            Bundle bundle = k1.a != null ? new Bundle(k1.a) : new Bundle();
            bundle.putBoolean("android.support.allowGeneratedReplies", k1.d);
            if (i >= 24) {
                builder.setAllowGeneratedReplies(k1.d);
            }
            bundle.putInt("android.support.action.semanticAction", 0);
            if (i >= 28) {
                builder.setSemanticAction(0);
            }
            bundle.putBoolean("android.support.action.showsUserInterface", k1.e);
            builder.addExtras(bundle);
            this.a.addAction(builder.build());
        }
        Bundle bundle2 = m1.n;
        if (bundle2 != null) {
            this.f.putAll(bundle2);
        }
        this.c = null;
        this.d = null;
        this.a.setShowWhen(m1.h);
        this.a.setLocalOnly(m1.m).setGroup(null).setGroupSummary(false).setSortKey(null);
        this.g = 0;
        this.a.setCategory(null).setColor(0).setVisibility(0).setPublicVersion(null).setSound(notification.sound, notification.audioAttributes);
        Iterator it = m1.u.iterator();
        while (it.hasNext()) {
            this.a.addPerson((String) it.next());
        }
        this.h = null;
        if (m1.c.size() > 0) {
            if (m1.n == null) {
                m1.n = new Bundle();
            }
            Bundle bundle3 = m1.n.getBundle("android.car.EXTENSIONS");
            bundle3 = bundle3 == null ? new Bundle() : bundle3;
            Bundle bundle4 = new Bundle();
            for (int i2 = 0; i2 < m1.c.size(); i2++) {
                bundle4.putBundle(Integer.toString(i2), P1.a((K1) m1.c.get(i2)));
            }
            bundle3.putBundle("invisible_actions", bundle4);
            if (m1.n == null) {
                m1.n = new Bundle();
            }
            m1.n.putBundle("android.car.EXTENSIONS", bundle3);
            this.f.putBundle("android.car.EXTENSIONS", bundle3);
        }
        if (i >= 24) {
            this.a.setExtras(m1.n).setRemoteInputHistory(null);
        }
        if (i >= 26) {
            this.a.setBadgeIconType(0).setShortcutId(null).setTimeoutAfter(0L).setGroupAlertBehavior(0);
            if (TextUtils.isEmpty(m1.q)) {
                return;
            }
            this.a.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
        }
    }
}
