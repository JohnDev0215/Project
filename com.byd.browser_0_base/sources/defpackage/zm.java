package defpackage;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.text.TextUtils;
import defpackage.xp;
import defpackage.ym;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.CollectionUtil;

/* JADX INFO: compiled from: ChannelsInitializer.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zm {
    public final so1 a;
    public Resources b;

    public zm(so1 so1Var, Resources resources) {
        this.a = so1Var;
        this.b = resources;
    }

    public final void a(Collection collection, Collection collection2) {
        ym.a aVar;
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            ym.b bVar = xp.a.a.get((String) it.next());
            if (bVar != null) {
                NotificationChannelGroup notificationChannelGroup = new NotificationChannelGroup(bVar.a, this.b.getString(bVar.b));
                map.put(notificationChannelGroup.getId(), notificationChannelGroup);
            }
        }
        Iterator it2 = collection2.iterator();
        while (it2.hasNext()) {
            String str = (String) it2.next();
            if (xn2.e(str) || TextUtils.equals(str, "default_channel_id")) {
                aVar = null;
            } else {
                aVar = xp.b.a.get(str);
                if (aVar == null) {
                    throw new IllegalStateException("Could not initialize channel: ".concat(str));
                }
            }
            if (aVar != null) {
                Map<String, ym.b> map3 = xp.a.a;
                String str2 = aVar.d;
                ym.b bVar2 = map3.get(str2);
                NotificationChannelGroup notificationChannelGroup2 = new NotificationChannelGroup(bVar2.a, this.b.getString(bVar2.b));
                NotificationChannel notificationChannel = new NotificationChannel(aVar.a, this.b.getString(aVar.b), aVar.c);
                notificationChannel.setGroup(str2);
                notificationChannel.setShowBadge(aVar.e);
                if (aVar.f) {
                    notificationChannel.setSound(null, new AudioAttributes.Builder().setContentType(4).setUsage(5).build());
                }
                map.put(notificationChannelGroup2.getId(), notificationChannelGroup2);
                map2.put(notificationChannel.getId(), notificationChannel);
            }
        }
        Collection collectionValues = map.values();
        so1 so1Var = this.a;
        Objects.requireNonNull(so1Var);
        CollectionUtil.a(collectionValues, new l51(7, so1Var));
        CollectionUtil.a(map2.values(), new x10(26, so1Var));
    }

    public final void b() {
        HashSet hashSet = new HashSet();
        Iterator<String> it = xp.b.b.iterator();
        while (it.hasNext()) {
            hashSet.add(xp.b.a.get(it.next()).d);
        }
        a(hashSet, xp.b.b);
    }

    public final void c(Resources resources) {
        this.b = resources;
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        so1 so1Var = this.a;
        Iterator<NotificationChannelGroup> it = so1Var.h().iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getId());
        }
        Iterator<NotificationChannel> it2 = so1Var.b().iterator();
        while (it2.hasNext()) {
            hashSet2.add(it2.next().getId());
        }
        hashSet.retainAll(xp.a.a.keySet());
        hashSet2.retainAll(xp.b.a.keySet());
        a(hashSet, hashSet2);
    }
}
