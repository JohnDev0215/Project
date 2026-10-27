package defpackage;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import defpackage.ir;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.share.a;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: AndroidCustomActionProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class r4 extends ir {
    public static final Integer l = 112;
    public static a m;
    public static String n;

    /* JADX INFO: compiled from: AndroidCustomActionProvider.java */
    public static class a extends BroadcastReceiver {
        public final HashMap a = new HashMap();
        public final ArrayList b = new ArrayList();
        public final a.b c;

        public a(Activity activity, ArrayList arrayList, a.b bVar) {
            Parcelable parcelable;
            this.c = bVar;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ir.a aVar = (ir.a) it.next();
                String str = aVar.d;
                String string = activity.getString(aVar.b);
                Intent intent = new Intent(r4.g());
                intent.setPackage(activity.getPackageName());
                intent.putExtra("EXTRA_SHARE_CUSTOM_ACTION", str);
                if (Build.VERSION.SDK_INT < 33) {
                    q11.a(intent);
                }
                int iIntValue = r4.l.intValue();
                int i = aVar.a;
                PendingIntent broadcast = PendingIntent.getBroadcast(activity, iIntValue + i, intent, 335544320);
                Icon iconCreateWithResource = Icon.createWithResource(activity, i);
                try {
                    Class<?> cls = Class.forName("android.service.chooser.ChooserAction$Builder");
                    parcelable = (Parcelable) cls.getMethod("build", new Class[0]).invoke(cls.getConstructor(Icon.class, CharSequence.class, PendingIntent.class).newInstance(iconCreateWithResource, string, broadcast), new Object[0]);
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                    Log.w("cr_AndroidShare", "Building ChooserAction failed.", e);
                    parcelable = null;
                }
                this.a.put(aVar.d, aVar.e.p1(null));
                this.b.add(parcelable);
            }
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (yj.a() || q11.g(intent)) {
                Object obj = ThreadUtils.a;
                a aVar = r4.m;
                if (aVar != null) {
                    r4.n = null;
                    xz.a.unregisterReceiver(aVar);
                    r4.m = null;
                }
                String strR = q11.r(intent, "EXTRA_SHARE_CUSTOM_ACTION");
                HashMap map = this.a;
                if (map.containsKey(strR)) {
                    ((Runnable) map.get(strR)).run();
                } else {
                    Log.w("cr_AndroidShare", "Selected custom action can not be found.");
                }
                ComponentName componentName = (ComponentName) intent.getParcelableExtra("android.intent.extra.CHOSEN_COMPONENT");
                a.b bVar = this.c;
                if (bVar != null) {
                    bVar.a(componentName);
                }
            }
        }
    }

    public r4(Activity activity, WindowAndroid windowAndroid, nw2<Tab> nw2Var, ji jiVar, org.chromium.components.browser_ui.share.a aVar, boolean z, rq rqVar, xe3 xe3Var, String str, Profile profile) {
        super(activity, windowAndroid, nw2Var, jiVar, aVar, z, rqVar, xe3Var, str, profile);
    }

    public static String g() {
        if (n == null) {
            n = xz.a.getPackageName() + "/" + r4.class.getName() + "_ACTION";
        }
        return n;
    }

    @Override // defpackage.ir
    public final ir.a b() {
        return null;
    }

    @Override // defpackage.ir
    public final ir.a c() {
        return null;
    }
}
