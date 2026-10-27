package com.android.launcher3;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.Process;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.icons.BitmapInfo;
import com.android.launcher3.icons.GraphicsUtils;
import com.android.launcher3.icons.LauncherIcons;
import com.android.launcher3.shortcuts.DeepShortcutManager;
import com.android.launcher3.shortcuts.ShortcutKey;
import com.android.launcher3.util.PackageManagerHelper;
import com.android.launcher3.util.Preconditions;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

/* JADX INFO: loaded from: classes4.dex */
public class InstallShortcutReceiver extends BroadcastReceiver {
    private static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    private static final String APPS_PENDING_INSTALL = "apps_to_install";
    private static final String APP_SHORTCUT_TYPE_KEY = "isAppShortcut";
    private static final String APP_WIDGET_TYPE_KEY = "isAppWidget";
    private static final boolean DBG = false;
    private static final String DEEPSHORTCUT_TYPE_KEY = "isDeepShortcut";
    public static final int FLAG_ACTIVITY_PAUSED = 1;
    public static final int FLAG_BULK_ADD = 4;
    public static final int FLAG_DRAG_AND_DROP = 4;
    public static final int FLAG_LOADER_RUNNING = 2;
    private static final String ICON_KEY = "icon";
    private static final String ICON_RESOURCE_NAME_KEY = "iconResource";
    private static final String ICON_RESOURCE_PACKAGE_NAME_KEY = "iconResourcePackage";
    private static final String LAUNCH_INTENT_KEY = "intent.launch";
    private static final int MSG_ADD_TO_QUEUE = 1;
    private static final int MSG_FLUSH_QUEUE = 2;
    private static final String NAME_KEY = "name";
    public static final int NEW_SHORTCUT_BOUNCE_DURATION = 450;
    public static final int NEW_SHORTCUT_STAGGER_DELAY = 85;
    private static final String TAG = "InstallShortcutReceiver";
    private static final String USER_HANDLE_KEY = "userHandle";
    private static final Handler sHandler = new Handler(LauncherModel.getWorkerLooper()) { // from class: com.android.launcher3.InstallShortcutReceiver.1
        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = msg.what;
            if (i == 1) {
                Pair pair = (Pair) msg.obj;
                String strEncodeToString = ((PendingInstallShortcutInfo) pair.second).encodeToString();
                SharedPreferences prefs = Utilities.getPrefs((Context) pair.first);
                Set<String> stringSet = prefs.getStringSet(InstallShortcutReceiver.APPS_PENDING_INSTALL, null);
                HashSet hashSet = stringSet != null ? new HashSet(stringSet) : new HashSet(1);
                hashSet.add(strEncodeToString);
                prefs.edit().putStringSet(InstallShortcutReceiver.APPS_PENDING_INSTALL, hashSet).apply();
                return;
            }
            if (i != 2) {
                return;
            }
            Context context = (Context) msg.obj;
            LauncherModel model = LauncherAppState.getInstance(context).getModel();
            if (model.getCallback() == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            SharedPreferences prefs2 = Utilities.getPrefs(context);
            Set<String> stringSet2 = prefs2.getStringSet(InstallShortcutReceiver.APPS_PENDING_INSTALL, null);
            if (stringSet2 == null) {
                return;
            }
            LauncherAppsCompat launcherAppsCompat = LauncherAppsCompat.getInstance(context);
            Iterator<String> it = stringSet2.iterator();
            while (it.hasNext()) {
                PendingInstallShortcutInfo pendingInstallShortcutInfoDecode = InstallShortcutReceiver.decode(it.next(), context);
                if (pendingInstallShortcutInfoDecode != null) {
                    String intentPackage = InstallShortcutReceiver.getIntentPackage(pendingInstallShortcutInfoDecode.launchIntent);
                    if (TextUtils.isEmpty(intentPackage) || launcherAppsCompat.isPackageEnabledForProfile(intentPackage, pendingInstallShortcutInfoDecode.user)) {
                        arrayList.add(pendingInstallShortcutInfoDecode.getItemInfo());
                    }
                }
            }
            prefs2.edit().remove(InstallShortcutReceiver.APPS_PENDING_INSTALL).apply();
            if (arrayList.isEmpty()) {
                return;
            }
            model.addAndBindAddedWorkspaceItems(arrayList);
        }
    };
    private static int sInstallQueueDisabledFlags;

    public static void removeFromInstallQueue(Context context, HashSet<String> packageNames, UserHandle user) {
        if (packageNames.isEmpty()) {
            return;
        }
        Preconditions.assertWorkerThread();
        SharedPreferences prefs = Utilities.getPrefs(context);
        Set<String> stringSet = prefs.getStringSet(APPS_PENDING_INSTALL, null);
        if (stringSet == null || stringSet.isEmpty()) {
            return;
        }
        HashSet hashSet = new HashSet(stringSet);
        Iterator<String> it = hashSet.iterator();
        while (it.hasNext()) {
            try {
                Decoder decoder = new Decoder(it.next(), context);
                if (packageNames.contains(getIntentPackage(decoder.launcherIntent)) && user.equals(decoder.user)) {
                    it.remove();
                }
            } catch (URISyntaxException | JSONException e) {
                Log.d(TAG, "Exception reading shortcut to add: " + e);
                it.remove();
            }
        }
        prefs.edit().putStringSet(APPS_PENDING_INSTALL, hashSet).apply();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent data) {
        PendingInstallShortcutInfo pendingInstallShortcutInfoCreatePendingInfo;
        if (ACTION_INSTALL_SHORTCUT.equals(data.getAction()) && (pendingInstallShortcutInfoCreatePendingInfo = createPendingInfo(context, data)) != null) {
            if (!pendingInstallShortcutInfoCreatePendingInfo.isLauncherActivity() && !new PackageManagerHelper(context).hasPermissionForActivity(pendingInstallShortcutInfoCreatePendingInfo.launchIntent, null)) {
                Log.e(TAG, "Ignoring malicious intent " + pendingInstallShortcutInfoCreatePendingInfo.launchIntent.toUri(0));
            } else {
                queuePendingShortcutInfo(pendingInstallShortcutInfoCreatePendingInfo, context);
            }
        }
    }

    private static boolean isValidExtraType(Intent intent, String key, Class type) {
        Parcelable parcelableExtra = intent.getParcelableExtra(key);
        return parcelableExtra == null || type.isInstance(parcelableExtra);
    }

    private static PendingInstallShortcutInfo createPendingInfo(Context context, Intent data) {
        if (isValidExtraType(data, Intent.EXTRA_SHORTCUT_INTENT, Intent.class) && isValidExtraType(data, Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.class) && isValidExtraType(data, Intent.EXTRA_SHORTCUT_ICON, Bitmap.class)) {
            PendingInstallShortcutInfo pendingInstallShortcutInfo = new PendingInstallShortcutInfo(data, Process.myUserHandle(), context);
            if (pendingInstallShortcutInfo.launchIntent != null && pendingInstallShortcutInfo.label != null) {
                return convertToLauncherActivityIfPossible(pendingInstallShortcutInfo);
            }
        }
        return null;
    }

    public static WorkspaceItemInfo fromShortcutIntent(Context context, Intent data) {
        PendingInstallShortcutInfo pendingInstallShortcutInfoCreatePendingInfo = createPendingInfo(context, data);
        if (pendingInstallShortcutInfoCreatePendingInfo == null) {
            return null;
        }
        return (WorkspaceItemInfo) pendingInstallShortcutInfoCreatePendingInfo.getItemInfo().first;
    }

    public static WorkspaceItemInfo fromActivityInfo(LauncherActivityInfo info, Context context) {
        return (WorkspaceItemInfo) new PendingInstallShortcutInfo(info, context).getItemInfo().first;
    }

    public static void queueShortcut(ShortcutInfo info, Context context) {
        queuePendingShortcutInfo(new PendingInstallShortcutInfo(info, context), context);
    }

    public static void queueWidget(AppWidgetProviderInfo info, int widgetId, Context context) {
        queuePendingShortcutInfo(new PendingInstallShortcutInfo(info, widgetId, context), context);
    }

    public static void queueActivityInfo(LauncherActivityInfo activity, Context context) {
        queuePendingShortcutInfo(new PendingInstallShortcutInfo(activity, context), context);
    }

    public static HashSet<ShortcutKey> getPendingShortcuts(Context context) {
        HashSet<ShortcutKey> hashSet = new HashSet<>();
        Set<String> stringSet = Utilities.getPrefs(context).getStringSet(APPS_PENDING_INSTALL, null);
        if (stringSet != null && !stringSet.isEmpty()) {
            Iterator<String> it = stringSet.iterator();
            while (it.hasNext()) {
                try {
                    Decoder decoder = new Decoder(it.next(), context);
                    if (decoder.optBoolean(DEEPSHORTCUT_TYPE_KEY)) {
                        hashSet.add(ShortcutKey.fromIntent(decoder.launcherIntent, decoder.user));
                    }
                } catch (URISyntaxException | JSONException e) {
                    Log.d(TAG, "Exception reading shortcut to add: " + e);
                }
            }
        }
        return hashSet;
    }

    private static void queuePendingShortcutInfo(PendingInstallShortcutInfo info, Context context) {
        Message.obtain(sHandler, 1, Pair.create(context, info)).sendToTarget();
        flushInstallQueue(context);
    }

    public static void enableInstallQueue(int flag) {
        sInstallQueueDisabledFlags = flag | sInstallQueueDisabledFlags;
    }

    public static void disableAndFlushInstallQueue(int flag, Context context) {
        sInstallQueueDisabledFlags = (~flag) & sInstallQueueDisabledFlags;
        flushInstallQueue(context);
    }

    static void flushInstallQueue(Context context) {
        if (sInstallQueueDisabledFlags != 0) {
            return;
        }
        Message.obtain(sHandler, 2, context.getApplicationContext()).sendToTarget();
    }

    static CharSequence ensureValidName(Context context, Intent intent, CharSequence name) {
        if (name != null) {
            return name;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getActivityInfo(intent.getComponent(), 0).loadLabel(packageManager);
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public static class PendingInstallShortcutInfo {
        final LauncherActivityInfo activityInfo;
        final Intent data;
        final String label;
        final Intent launchIntent;
        final Context mContext;
        final AppWidgetProviderInfo providerInfo;
        final ShortcutInfo shortcutInfo;
        final UserHandle user;

        public PendingInstallShortcutInfo(Intent data, UserHandle user, Context context) {
            this.activityInfo = null;
            this.shortcutInfo = null;
            this.providerInfo = null;
            this.data = data;
            this.user = user;
            this.mContext = context;
            this.launchIntent = (Intent) data.getParcelableExtra(Intent.EXTRA_SHORTCUT_INTENT);
            this.label = data.getStringExtra(Intent.EXTRA_SHORTCUT_NAME);
        }

        public PendingInstallShortcutInfo(LauncherActivityInfo info, Context context) {
            this.activityInfo = info;
            this.shortcutInfo = null;
            this.providerInfo = null;
            this.data = null;
            this.user = info.getUser();
            this.mContext = context;
            this.launchIntent = AppInfo.makeLaunchIntent(info);
            this.label = info.getLabel().toString();
        }

        public PendingInstallShortcutInfo(ShortcutInfo info, Context context) {
            this.activityInfo = null;
            this.shortcutInfo = info;
            this.providerInfo = null;
            this.data = null;
            this.mContext = context;
            this.user = info.getUserHandle();
            this.launchIntent = ShortcutKey.makeIntent(info);
            this.label = info.getShortLabel().toString();
        }

        public PendingInstallShortcutInfo(AppWidgetProviderInfo info, int widgetId, Context context) {
            this.activityInfo = null;
            this.shortcutInfo = null;
            this.providerInfo = info;
            this.data = null;
            this.mContext = context;
            this.user = info.getProfile();
            this.launchIntent = new Intent().setComponent(info.provider).putExtra("appWidgetId", widgetId);
            this.label = info.label;
        }

        public String encodeToString() {
            try {
                if (this.activityInfo != null) {
                    return new JSONStringer().object().key(InstallShortcutReceiver.LAUNCH_INTENT_KEY).value(this.launchIntent.toUri(0)).key(InstallShortcutReceiver.APP_SHORTCUT_TYPE_KEY).value(true).key(InstallShortcutReceiver.USER_HANDLE_KEY).value(UserManagerCompat.getInstance(this.mContext).getSerialNumberForUser(this.user)).endObject().toString();
                }
                if (this.shortcutInfo != null) {
                    return new JSONStringer().object().key(InstallShortcutReceiver.LAUNCH_INTENT_KEY).value(this.launchIntent.toUri(0)).key(InstallShortcutReceiver.DEEPSHORTCUT_TYPE_KEY).value(true).key(InstallShortcutReceiver.USER_HANDLE_KEY).value(UserManagerCompat.getInstance(this.mContext).getSerialNumberForUser(this.user)).endObject().toString();
                }
                if (this.providerInfo != null) {
                    return new JSONStringer().object().key(InstallShortcutReceiver.LAUNCH_INTENT_KEY).value(this.launchIntent.toUri(0)).key(InstallShortcutReceiver.APP_WIDGET_TYPE_KEY).value(true).key(InstallShortcutReceiver.USER_HANDLE_KEY).value(UserManagerCompat.getInstance(this.mContext).getSerialNumberForUser(this.user)).endObject().toString();
                }
                if (this.launchIntent.getAction() == null) {
                    this.launchIntent.setAction("android.intent.action.VIEW");
                } else if (this.launchIntent.getAction().equals(Intent.ACTION_MAIN) && this.launchIntent.getCategories() != null && this.launchIntent.getCategories().contains(Intent.CATEGORY_LAUNCHER)) {
                    this.launchIntent.addFlags(270532608);
                }
                String string = InstallShortcutReceiver.ensureValidName(this.mContext, this.launchIntent, this.label).toString();
                Bitmap bitmap = (Bitmap) this.data.getParcelableExtra(Intent.EXTRA_SHORTCUT_ICON);
                Intent.ShortcutIconResource shortcutIconResource = (Intent.ShortcutIconResource) this.data.getParcelableExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE);
                JSONStringer jSONStringerValue = new JSONStringer().object().key(InstallShortcutReceiver.LAUNCH_INTENT_KEY).value(this.launchIntent.toUri(0)).key("name").value(string);
                if (bitmap != null) {
                    byte[] bArrFlattenBitmap = GraphicsUtils.flattenBitmap(bitmap);
                    jSONStringerValue = jSONStringerValue.key("icon").value(Base64.encodeToString(bArrFlattenBitmap, 0, bArrFlattenBitmap.length, 0));
                }
                if (shortcutIconResource != null) {
                    jSONStringerValue = jSONStringerValue.key("iconResource").value(shortcutIconResource.resourceName).key(InstallShortcutReceiver.ICON_RESOURCE_PACKAGE_NAME_KEY).value(shortcutIconResource.packageName);
                }
                return jSONStringerValue.endObject().toString();
            } catch (JSONException e) {
                Log.d(InstallShortcutReceiver.TAG, "Exception when adding shortcut: " + e);
                return null;
            }
        }

        public Pair<ItemInfo, Object> getItemInfo() {
            if (this.activityInfo != null) {
                AppInfo appInfo = new AppInfo(this.mContext, this.activityInfo, this.user);
                final LauncherAppState launcherAppState = LauncherAppState.getInstance(this.mContext);
                appInfo.title = "";
                appInfo.applyFrom(launcherAppState.getIconCache().getDefaultIcon(this.user));
                final WorkspaceItemInfo workspaceItemInfoMakeWorkspaceItem = appInfo.makeWorkspaceItem();
                if (Looper.myLooper() == LauncherModel.getWorkerLooper()) {
                    launcherAppState.getIconCache().getTitleAndIcon(workspaceItemInfoMakeWorkspaceItem, this.activityInfo, false);
                } else {
                    launcherAppState.getModel().updateAndBindWorkspaceItem(new Supplier() { // from class: com.android.launcher3.-$$Lambda$InstallShortcutReceiver$PendingInstallShortcutInfo$d0-wkt6cox6M_tOych9mKnspgTM
                        @Override // java.util.function.Supplier
                        public final Object get() {
                            return this.f$0.lambda$getItemInfo$0$InstallShortcutReceiver$PendingInstallShortcutInfo(launcherAppState, workspaceItemInfoMakeWorkspaceItem);
                        }
                    });
                }
                return Pair.create(workspaceItemInfoMakeWorkspaceItem, this.activityInfo);
            }
            if (this.shortcutInfo != null) {
                WorkspaceItemInfo workspaceItemInfo = new WorkspaceItemInfo(this.shortcutInfo, this.mContext);
                LauncherIcons launcherIconsObtain = LauncherIcons.obtain(this.mContext);
                workspaceItemInfo.applyFrom(launcherIconsObtain.createShortcutIcon(this.shortcutInfo));
                launcherIconsObtain.recycle();
                return Pair.create(workspaceItemInfo, this.shortcutInfo);
            }
            AppWidgetProviderInfo appWidgetProviderInfo = this.providerInfo;
            if (appWidgetProviderInfo != null) {
                LauncherAppWidgetProviderInfo launcherAppWidgetProviderInfoFromProviderInfo = LauncherAppWidgetProviderInfo.fromProviderInfo(this.mContext, appWidgetProviderInfo);
                LauncherAppWidgetInfo launcherAppWidgetInfo = new LauncherAppWidgetInfo(this.launchIntent.getIntExtra("appWidgetId", 0), launcherAppWidgetProviderInfoFromProviderInfo.provider);
                InvariantDeviceProfile idp = LauncherAppState.getIDP(this.mContext);
                launcherAppWidgetInfo.minSpanX = launcherAppWidgetProviderInfoFromProviderInfo.minSpanX;
                launcherAppWidgetInfo.minSpanY = launcherAppWidgetProviderInfoFromProviderInfo.minSpanY;
                launcherAppWidgetInfo.spanX = Math.min(launcherAppWidgetProviderInfoFromProviderInfo.spanX, idp.numColumns);
                launcherAppWidgetInfo.spanY = Math.min(launcherAppWidgetProviderInfoFromProviderInfo.spanY, idp.numRows);
                return Pair.create(launcherAppWidgetInfo, this.providerInfo);
            }
            return Pair.create(InstallShortcutReceiver.createWorkspaceItemInfo(this.data, LauncherAppState.getInstance(this.mContext)), null);
        }

        public /* synthetic */ WorkspaceItemInfo lambda$getItemInfo$0$InstallShortcutReceiver$PendingInstallShortcutInfo(LauncherAppState launcherAppState, WorkspaceItemInfo workspaceItemInfo) {
            launcherAppState.getIconCache().getTitleAndIcon(workspaceItemInfo, this.activityInfo, false);
            return workspaceItemInfo;
        }

        public boolean isLauncherActivity() {
            return this.activityInfo != null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getIntentPackage(Intent intent) {
        return intent.getComponent() == null ? intent.getPackage() : intent.getComponent().getPackageName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static PendingInstallShortcutInfo decode(String encoded, Context context) {
        try {
            Decoder decoder = new Decoder(encoded, context);
            if (decoder.optBoolean(APP_SHORTCUT_TYPE_KEY)) {
                LauncherActivityInfo launcherActivityInfoResolveActivity = LauncherAppsCompat.getInstance(context).resolveActivity(decoder.launcherIntent, decoder.user);
                if (launcherActivityInfoResolveActivity == null) {
                    return null;
                }
                return new PendingInstallShortcutInfo(launcherActivityInfoResolveActivity, context);
            }
            if (decoder.optBoolean(DEEPSHORTCUT_TYPE_KEY)) {
                List<ShortcutInfo> listQueryForFullDetails = DeepShortcutManager.getInstance(context).queryForFullDetails(decoder.launcherIntent.getPackage(), Arrays.asList(decoder.launcherIntent.getStringExtra(ShortcutKey.EXTRA_SHORTCUT_ID)), decoder.user);
                if (listQueryForFullDetails.isEmpty()) {
                    return null;
                }
                return new PendingInstallShortcutInfo(listQueryForFullDetails.get(0), context);
            }
            if (decoder.optBoolean(APP_WIDGET_TYPE_KEY)) {
                int intExtra = decoder.launcherIntent.getIntExtra("appWidgetId", 0);
                AppWidgetProviderInfo appWidgetInfo = AppWidgetManager.getInstance(context).getAppWidgetInfo(intExtra);
                if (appWidgetInfo != null && appWidgetInfo.provider.equals(decoder.launcherIntent.getComponent()) && appWidgetInfo.getProfile().equals(decoder.user)) {
                    return new PendingInstallShortcutInfo(appWidgetInfo, intExtra, context);
                }
                return null;
            }
            Intent intent = new Intent();
            intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, decoder.launcherIntent);
            intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, decoder.getString("name"));
            String strOptString = decoder.optString("icon");
            String strOptString2 = decoder.optString("iconResource");
            String strOptString3 = decoder.optString(ICON_RESOURCE_PACKAGE_NAME_KEY);
            if (strOptString != null && !strOptString.isEmpty()) {
                byte[] bArrDecode = Base64.decode(strOptString, 0);
                intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length));
            } else if (strOptString2 != null && !strOptString2.isEmpty()) {
                Intent.ShortcutIconResource shortcutIconResource = new Intent.ShortcutIconResource();
                shortcutIconResource.resourceName = strOptString2;
                shortcutIconResource.packageName = strOptString3;
                intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, shortcutIconResource);
            }
            return new PendingInstallShortcutInfo(intent, decoder.user, context);
        } catch (URISyntaxException | JSONException e) {
            Log.d(TAG, "Exception reading shortcut to add: " + e);
            return null;
        }
    }

    private static class Decoder extends JSONObject {
        public final Intent launcherIntent;
        public final UserHandle user;

        private Decoder(String encoded, Context context) throws JSONException, URISyntaxException {
            UserHandle userHandleMyUserHandle;
            super(encoded);
            this.launcherIntent = Intent.parseUri(getString(InstallShortcutReceiver.LAUNCH_INTENT_KEY), 0);
            if (has(InstallShortcutReceiver.USER_HANDLE_KEY)) {
                userHandleMyUserHandle = UserManagerCompat.getInstance(context).getUserForSerialNumber(getLong(InstallShortcutReceiver.USER_HANDLE_KEY));
            } else {
                userHandleMyUserHandle = Process.myUserHandle();
            }
            this.user = userHandleMyUserHandle;
            if (userHandleMyUserHandle == null) {
                throw new JSONException("Invalid user");
            }
        }
    }

    private static PendingInstallShortcutInfo convertToLauncherActivityIfPossible(PendingInstallShortcutInfo original) {
        LauncherActivityInfo launcherActivityInfoResolveActivity;
        return (original.isLauncherActivity() || !Utilities.isLauncherAppTarget(original.launchIntent) || (launcherActivityInfoResolveActivity = LauncherAppsCompat.getInstance(original.mContext).resolveActivity(original.launchIntent, original.user)) == null) ? original : new PendingInstallShortcutInfo(launcherActivityInfoResolveActivity, original.mContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static WorkspaceItemInfo createWorkspaceItemInfo(Intent data, LauncherAppState app) {
        Intent intent = (Intent) data.getParcelableExtra(Intent.EXTRA_SHORTCUT_INTENT);
        String stringExtra = data.getStringExtra(Intent.EXTRA_SHORTCUT_NAME);
        Parcelable parcelableExtra = data.getParcelableExtra(Intent.EXTRA_SHORTCUT_ICON);
        BitmapInfo bitmapInfoCreateIconBitmap = null;
        if (intent == null) {
            Log.e(TAG, "Can't construct WorkspaceItemInfo with null intent");
            return null;
        }
        WorkspaceItemInfo workspaceItemInfo = new WorkspaceItemInfo();
        workspaceItemInfo.user = Process.myUserHandle();
        LauncherIcons launcherIconsObtain = LauncherIcons.obtain(app.getContext());
        if (parcelableExtra instanceof Bitmap) {
            bitmapInfoCreateIconBitmap = launcherIconsObtain.createIconBitmap((Bitmap) parcelableExtra);
        } else {
            Parcelable parcelableExtra2 = data.getParcelableExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE);
            if (parcelableExtra2 instanceof Intent.ShortcutIconResource) {
                workspaceItemInfo.iconResource = (Intent.ShortcutIconResource) parcelableExtra2;
                bitmapInfoCreateIconBitmap = launcherIconsObtain.createIconBitmap(workspaceItemInfo.iconResource);
            }
        }
        launcherIconsObtain.recycle();
        if (bitmapInfoCreateIconBitmap == null) {
            bitmapInfoCreateIconBitmap = app.getIconCache().getDefaultIcon(workspaceItemInfo.user);
        }
        workspaceItemInfo.applyFrom(bitmapInfoCreateIconBitmap);
        workspaceItemInfo.title = Utilities.trim(stringExtra);
        workspaceItemInfo.contentDescription = app.getContext().getPackageManager().getUserBadgedLabel(workspaceItemInfo.title, workspaceItemInfo.user);
        workspaceItemInfo.intent = intent;
        return workspaceItemInfo;
    }
}
