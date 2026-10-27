package androidx.core.content.pm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutInfoCompatSaver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ShortcutManagerCompat {
    static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    public static final String EXTRA_SHORTCUT_ID = "android.intent.extra.shortcut.ID";
    static final String INSTALL_SHORTCUT_PERMISSION = "com.android.launcher.permission.INSTALL_SHORTCUT";
    private static volatile ShortcutInfoCompatSaver<?> sShortcutInfoCompatSaver;

    private ShortcutManagerCompat() {
    }

    public static boolean isRequestPinShortcutSupported(Context context) {
        return ((ShortcutManager) context.getSystemService(ShortcutManager.class)).isRequestPinShortcutSupported();
    }

    public static boolean requestPinShortcut(Context context, ShortcutInfoCompat shortcutInfoCompat, IntentSender intentSender) {
        return ((ShortcutManager) context.getSystemService(ShortcutManager.class)).requestPinShortcut(shortcutInfoCompat.toShortcutInfo(), intentSender);
    }

    /* JADX INFO: renamed from: androidx.core.content.pm.ShortcutManagerCompat$1, reason: invalid class name */
    class AnonymousClass1 extends BroadcastReceiver {
        final /* synthetic */ IntentSender val$callback;

        AnonymousClass1(IntentSender intentSender) {
            this.val$callback = intentSender;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                this.val$callback.sendIntent(context, 0, null, null, null);
            } catch (IntentSender.SendIntentException unused) {
            }
        }
    }

    public static Intent createShortcutResultIntent(Context context, ShortcutInfoCompat shortcutInfoCompat) {
        Intent intentCreateShortcutResultIntent = ((ShortcutManager) context.getSystemService(ShortcutManager.class)).createShortcutResultIntent(shortcutInfoCompat.toShortcutInfo());
        if (intentCreateShortcutResultIntent == null) {
            intentCreateShortcutResultIntent = new Intent();
        }
        return shortcutInfoCompat.addToIntent(intentCreateShortcutResultIntent);
    }

    public static boolean addDynamicShortcuts(Context context, List<ShortcutInfoCompat> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<ShortcutInfoCompat> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toShortcutInfo());
        }
        if (!((ShortcutManager) context.getSystemService(ShortcutManager.class)).addDynamicShortcuts(arrayList)) {
            return false;
        }
        getShortcutInfoSaverInstance(context).addShortcuts(list);
        return true;
    }

    public static int getMaxShortcutCountPerActivity(Context context) {
        return ((ShortcutManager) context.getSystemService(ShortcutManager.class)).getMaxShortcutCountPerActivity();
    }

    public static List<ShortcutInfoCompat> getDynamicShortcuts(Context context) {
        List<ShortcutInfo> dynamicShortcuts = ((ShortcutManager) context.getSystemService(ShortcutManager.class)).getDynamicShortcuts();
        ArrayList arrayList = new ArrayList(dynamicShortcuts.size());
        Iterator<ShortcutInfo> it = dynamicShortcuts.iterator();
        while (it.hasNext()) {
            arrayList.add(new ShortcutInfoCompat.Builder(context, it.next()).build());
        }
        return arrayList;
    }

    public static boolean updateShortcuts(Context context, List<ShortcutInfoCompat> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<ShortcutInfoCompat> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toShortcutInfo());
        }
        if (!((ShortcutManager) context.getSystemService(ShortcutManager.class)).updateShortcuts(arrayList)) {
            return false;
        }
        getShortcutInfoSaverInstance(context).addShortcuts(list);
        return true;
    }

    public static void removeDynamicShortcuts(Context context, List<String> list) {
        ((ShortcutManager) context.getSystemService(ShortcutManager.class)).removeDynamicShortcuts(list);
        getShortcutInfoSaverInstance(context).removeShortcuts(list);
    }

    public static void removeAllDynamicShortcuts(Context context) {
        ((ShortcutManager) context.getSystemService(ShortcutManager.class)).removeAllDynamicShortcuts();
        getShortcutInfoSaverInstance(context).removeAllShortcuts();
    }

    private static ShortcutInfoCompatSaver<?> getShortcutInfoSaverInstance(Context context) {
        if (sShortcutInfoCompatSaver == null) {
            try {
                sShortcutInfoCompatSaver = (ShortcutInfoCompatSaver) Class.forName("androidx.sharetarget.ShortcutInfoCompatSaverImpl", false, ShortcutManagerCompat.class.getClassLoader()).getMethod("getInstance", Context.class).invoke(null, context);
            } catch (Exception unused) {
            }
            if (sShortcutInfoCompatSaver == null) {
                sShortcutInfoCompatSaver = new ShortcutInfoCompatSaver.NoopImpl();
            }
        }
        return sShortcutInfoCompatSaver;
    }
}
