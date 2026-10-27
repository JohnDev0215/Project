package defpackage;

import android.app.Notification;
import android.app.Person;
import android.app.RemoteInput;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat$Builder;
import androidx.core.app.a;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: NotificationCompatBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public final class oo1 {
    public final Context a;
    public final Notification.Builder b;
    public final NotificationCompat$Builder c;
    public final Bundle d;
    public final int e;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [android.content.Context, android.content.res.Resources] */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public oo1(NotificationCompat$Builder notificationCompat$Builder) {
        String str;
        String str2;
        String str3;
        Notification notification;
        ArrayList<a> arrayList;
        String str4;
        String str5;
        Notification notification2;
        String str6;
        Bundle[] bundleArr;
        int i;
        Iterator<a> it;
        int i2;
        oo1 oo1Var = this;
        new ArrayList();
        oo1Var.d = new Bundle();
        oo1Var.c = notificationCompat$Builder;
        Context context = notificationCompat$Builder.a;
        oo1Var.a = context;
        String str7 = notificationCompat$Builder.x;
        Notification.Builder builder = new Notification.Builder(context, str7);
        oo1Var.b = builder;
        Notification notification3 = notificationCompat$Builder.A;
        Icon icon = 0;
        int i3 = 0;
        builder.setWhen(notification3.when).setSmallIcon(notification3.icon, notification3.iconLevel).setContent(notification3.contentView).setTicker(notification3.tickerText, null).setVibrate(notification3.vibrate).setLights(notification3.ledARGB, notification3.ledOnMS, notification3.ledOffMS).setOngoing((notification3.flags & 2) != 0).setOnlyAlertOnce((notification3.flags & 8) != 0).setAutoCancel((notification3.flags & 16) != 0).setDefaults(notification3.defaults).setContentTitle(notificationCompat$Builder.e).setContentText(notificationCompat$Builder.f).setContentInfo(null).setContentIntent(notificationCompat$Builder.g).setDeleteIntent(notification3.deleteIntent).setFullScreenIntent(null, (notification3.flags & 128) != 0).setNumber(0).setProgress(notificationCompat$Builder.m, notificationCompat$Builder.n, notificationCompat$Builder.o);
        IconCompat iconCompat = notificationCompat$Builder.h;
        builder.setLargeIcon(iconCompat == null ? null : iconCompat.g(context));
        builder.setSubText(notificationCompat$Builder.l).setUsesChronometer(false).setPriority(notificationCompat$Builder.i);
        Iterator<a> it2 = notificationCompat$Builder.b.iterator();
        while (true) {
            str = "";
            str2 = "android.support.allowGeneratedReplies";
            if (!it2.hasNext()) {
                break;
            }
            a next = it2.next();
            if (next.b == null && (i2 = next.h) != 0) {
                next.b = IconCompat.b(icon, "", i2);
            }
            IconCompat iconCompat2 = next.b;
            Notification.Action.Builder builder2 = new Notification.Action.Builder(iconCompat2 != 0 ? iconCompat2.g(icon) : icon, next.i, next.j);
            oa2[] oa2VarArr = next.c;
            if (oa2VarArr != null) {
                int length = oa2VarArr.length;
                RemoteInput[] remoteInputArr = new RemoteInput[length];
                while (i3 < oa2VarArr.length) {
                    oa2 oa2Var = oa2VarArr[i3];
                    RemoteInput.Builder builderAddExtras = new RemoteInput.Builder(oa2Var.a).setLabel(oa2Var.b).setChoices(oa2Var.c).setAllowFreeFormInput(oa2Var.d).addExtras(oa2Var.f);
                    Set<String> set = oa2Var.g;
                    if (set != null) {
                        Iterator<String> it3 = set.iterator();
                        while (it3.hasNext()) {
                            builderAddExtras.setAllowDataType(it3.next(), true);
                            it2 = it2;
                        }
                    }
                    builderAddExtras.setEditChoicesBeforeSending(oa2Var.e);
                    remoteInputArr[i3] = builderAddExtras.build();
                    i3++;
                    it2 = it2;
                }
                it = it2;
                for (int i4 = 0; i4 < length; i4++) {
                    builder2.addRemoteInput(remoteInputArr[i4]);
                }
            } else {
                it = it2;
            }
            Bundle bundle = next.a;
            Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            boolean z = next.d;
            bundle2.putBoolean("android.support.allowGeneratedReplies", z);
            int i5 = Build.VERSION.SDK_INT;
            builder2.setAllowGeneratedReplies(z);
            int i6 = next.f;
            bundle2.putInt("android.support.action.semanticAction", i6);
            builder2.setSemanticAction(i6);
            builder2.setContextual(next.g);
            if (i5 >= 31) {
                builder2.setAuthenticationRequired(next.k);
            }
            bundle2.putBoolean("android.support.action.showsUserInterface", next.e);
            builder2.addExtras(bundle2);
            oo1Var.b.addAction(builder2.build());
            icon = 0;
            i3 = 0;
            it2 = it;
        }
        Bundle bundle3 = notificationCompat$Builder.t;
        if (bundle3 != null) {
            oo1Var.d.putAll(bundle3);
        }
        oo1Var.b.setShowWhen(notificationCompat$Builder.j);
        oo1Var.b.setLocalOnly(notificationCompat$Builder.r);
        oo1Var.b.setGroup(notificationCompat$Builder.p);
        oo1Var.b.setSortKey(null);
        oo1Var.b.setGroupSummary(notificationCompat$Builder.q);
        oo1Var.e = 0;
        oo1Var.b.setCategory(notificationCompat$Builder.s);
        oo1Var.b.setColor(notificationCompat$Builder.u);
        oo1Var.b.setVisibility(notificationCompat$Builder.v);
        oo1Var.b.setPublicVersion(notificationCompat$Builder.w);
        oo1Var.b.setSound(notification3.sound, notification3.audioAttributes);
        ArrayList<String> arrayList2 = notificationCompat$Builder.D;
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            Iterator<String> it4 = arrayList2.iterator();
            while (it4.hasNext()) {
                oo1Var.b.addPerson(it4.next());
            }
        }
        ArrayList<a> arrayList3 = notificationCompat$Builder.d;
        if (arrayList3.size() > 0) {
            if (notificationCompat$Builder.t == null) {
                notificationCompat$Builder.t = new Bundle();
            }
            Bundle bundle4 = notificationCompat$Builder.t.getBundle("android.car.EXTENSIONS");
            bundle4 = bundle4 == null ? new Bundle() : bundle4;
            Bundle bundle5 = new Bundle(bundle4);
            Bundle bundle6 = new Bundle();
            int i7 = 0;
            while (i7 < arrayList3.size()) {
                String string = Integer.toString(i7);
                a aVar = arrayList3.get(i7);
                Object obj = po1.a;
                Bundle bundle7 = new Bundle();
                if (aVar.b != null || (i = aVar.h) == 0) {
                    arrayList = arrayList3;
                } else {
                    arrayList = arrayList3;
                    aVar.b = IconCompat.b(null, str, i);
                }
                IconCompat iconCompat3 = aVar.b;
                bundle7.putInt("icon", iconCompat3 != null ? iconCompat3.c() : 0);
                bundle7.putCharSequence("title", aVar.i);
                bundle7.putParcelable("actionIntent", aVar.j);
                Bundle bundle8 = aVar.a;
                Bundle bundle9 = bundle8 != null ? new Bundle(bundle8) : new Bundle();
                bundle9.putBoolean(str2, aVar.d);
                bundle7.putBundle("extras", bundle9);
                oa2[] oa2VarArr2 = aVar.c;
                if (oa2VarArr2 == null) {
                    bundleArr = null;
                    str6 = str7;
                    notification2 = notification3;
                    str4 = str;
                    str5 = str2;
                } else {
                    str4 = str;
                    Bundle[] bundleArr2 = new Bundle[oa2VarArr2.length];
                    str5 = str2;
                    int i8 = 0;
                    notification2 = notification3;
                    while (i8 < oa2VarArr2.length) {
                        oa2 oa2Var2 = oa2VarArr2[i8];
                        oa2[] oa2VarArr3 = oa2VarArr2;
                        Bundle bundle10 = new Bundle();
                        String str8 = str7;
                        bundle10.putString("resultKey", oa2Var2.a);
                        bundle10.putCharSequence("label", oa2Var2.b);
                        bundle10.putCharSequenceArray("choices", oa2Var2.c);
                        bundle10.putBoolean("allowFreeFormInput", oa2Var2.d);
                        bundle10.putBundle("extras", oa2Var2.f);
                        Set<String> set2 = oa2Var2.g;
                        if (set2 != null && !set2.isEmpty()) {
                            ArrayList<String> arrayList4 = new ArrayList<>(set2.size());
                            Iterator<String> it5 = set2.iterator();
                            while (it5.hasNext()) {
                                arrayList4.add(it5.next());
                            }
                            bundle10.putStringArrayList("allowedDataTypes", arrayList4);
                        }
                        bundleArr2[i8] = bundle10;
                        i8++;
                        oa2VarArr2 = oa2VarArr3;
                        str7 = str8;
                    }
                    str6 = str7;
                    bundleArr = bundleArr2;
                }
                bundle7.putParcelableArray("remoteInputs", bundleArr);
                bundle7.putBoolean("showsUserInterface", aVar.e);
                bundle7.putInt("semanticAction", aVar.f);
                bundle6.putBundle(string, bundle7);
                i7++;
                arrayList3 = arrayList;
                str = str4;
                notification3 = notification2;
                str2 = str5;
                str7 = str6;
            }
            str3 = str7;
            notification = notification3;
            bundle4.putBundle("invisible_actions", bundle6);
            bundle5.putBundle("invisible_actions", bundle6);
            if (notificationCompat$Builder.t == null) {
                notificationCompat$Builder.t = new Bundle();
            }
            notificationCompat$Builder.t.putBundle("android.car.EXTENSIONS", bundle4);
            oo1Var = this;
            oo1Var.d.putBundle("android.car.EXTENSIONS", bundle5);
        } else {
            str3 = str7;
            notification = notification3;
        }
        Icon icon2 = notificationCompat$Builder.C;
        if (icon2 != null) {
            oo1Var.b.setSmallIcon(icon2);
        }
        oo1Var.b.setExtras(notificationCompat$Builder.t);
        oo1Var.b.setRemoteInputHistory(null);
        oo1Var.b.setBadgeIconType(0);
        oo1Var.b.setSettingsText(null);
        oo1Var.b.setShortcutId(null);
        oo1Var.b.setTimeoutAfter(notificationCompat$Builder.y);
        oo1Var.b.setGroupAlertBehavior(0);
        if (!TextUtils.isEmpty(str3)) {
            oo1Var.b.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
        }
        for (s02 s02Var : notificationCompat$Builder.c) {
            Notification.Builder builder3 = oo1Var.b;
            s02Var.getClass();
            builder3.addPerson(new Person.Builder().setName(null).setIcon(null).setUri(null).setKey(null).setBot(false).setImportant(false).build());
        }
        oo1Var.b.setAllowSystemGeneratedContextualActions(notificationCompat$Builder.z);
        oo1Var.b.setBubbleMetadata(null);
        if (notificationCompat$Builder.B) {
            if (oo1Var.c.q) {
                oo1Var.e = 2;
            } else {
                oo1Var.e = 1;
            }
            oo1Var.b.setVibrate(null);
            oo1Var.b.setSound(null);
            Notification notification4 = notification;
            int i9 = notification4.defaults & (-2) & (-3);
            notification4.defaults = i9;
            oo1Var.b.setDefaults(i9);
            if (TextUtils.isEmpty(oo1Var.c.p)) {
                oo1Var.b.setGroup("silent");
            }
            oo1Var.b.setGroupAlertBehavior(oo1Var.e);
        }
    }
}
