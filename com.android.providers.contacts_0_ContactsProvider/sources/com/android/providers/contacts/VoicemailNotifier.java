package com.android.providers.contacts;

import android.app.BroadcastOptions;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Binder;
import android.util.ArraySet;
import android.util.Log;
import com.google.android.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class VoicemailNotifier {
    private final Uri mBaseUri;
    private final Context mContext;
    private final Set<String> mIntentActions = new ArraySet();
    private final Set<String> mModifiedPackages = new ArraySet();
    private final Set<Uri> mUris = new ArraySet();
    private final VoicemailPermissions mVoicemailPermissions;

    public VoicemailNotifier(Context context, Uri uri) {
        this.mContext = context;
        this.mBaseUri = uri;
        this.mVoicemailPermissions = new VoicemailPermissions(context);
    }

    public void addIntentActions(String str) {
        this.mIntentActions.add(str);
    }

    public void addModifiedPackages(Collection<String> collection) {
        this.mModifiedPackages.addAll(collection);
    }

    public void addUri(Uri uri) {
        this.mUris.add(uri);
    }

    public void sendNotification() {
        Iterator<String> it;
        Uri next = this.mUris.size() == 1 ? this.mUris.iterator().next() : this.mBaseUri;
        this.mContext.getContentResolver().notifyChange(next, (ContentObserver) null, true);
        Collection<String> callingPackages = getCallingPackages();
        Iterator<String> it2 = this.mIntentActions.iterator();
        while (it2.hasNext()) {
            String next2 = it2.next();
            boolean zEquals = next2.equals("android.intent.action.PROVIDER_CHANGED");
            Log.i("VoicemailNotifier", "receivers for " + next2 + " :" + getBroadcastReceiverComponents(next2, next));
            for (ComponentName componentName : getBroadcastReceiverComponents(next2, next)) {
                boolean zPackageHasReadAccess = this.mVoicemailPermissions.packageHasReadAccess(componentName.getPackageName());
                boolean zPackageHasOwnVoicemailAccess = this.mVoicemailPermissions.packageHasOwnVoicemailAccess(componentName.getPackageName());
                if (zPackageHasReadAccess || (this.mModifiedPackages.contains(componentName.getPackageName()) && zPackageHasOwnVoicemailAccess)) {
                    Intent intent = new Intent(next2, next);
                    intent.setComponent(componentName);
                    if (zEquals && callingPackages != null) {
                        intent.putExtra("com.android.voicemail.extra.SELF_CHANGE", callingPackages.contains(componentName.getPackageName()));
                    }
                    if (next2.equals("android.intent.action.NEW_VOICEMAIL")) {
                        BroadcastOptions broadcastOptionsMakeBasic = BroadcastOptions.makeBasic();
                        it = it2;
                        broadcastOptionsMakeBasic.setTemporaryAppWhitelistDuration(10000L);
                        Log.i("VoicemailNotifier", String.format("sendNotification: allowMillis=%d, pkg=%s", 10000L, componentName.getPackageName()));
                        this.mContext.sendBroadcast(intent, "com.android.voicemail.permission.READ_VOICEMAIL", broadcastOptionsMakeBasic.toBundle());
                    } else {
                        it = it2;
                        this.mContext.sendBroadcast(intent);
                    }
                    Object[] objArr = new Object[4];
                    objArr[0] = intent.getAction();
                    objArr[1] = intent.getData();
                    objArr[2] = componentName.getClassName();
                    objArr[3] = intent.hasExtra("com.android.voicemail.extra.SELF_CHANGE") ? Boolean.valueOf(intent.getBooleanExtra("com.android.voicemail.extra.SELF_CHANGE", false)) : null;
                    Log.v("VoicemailNotifier", String.format("Sent intent. act:%s, url:%s, comp:%s, self_change:%s", objArr));
                    it2 = it;
                }
            }
        }
        this.mIntentActions.clear();
        this.mModifiedPackages.clear();
        this.mUris.clear();
    }

    private Collection<String> getCallingPackages() {
        int callingUid = Binder.getCallingUid();
        if (callingUid == 0) {
            return null;
        }
        return Lists.newArrayList(this.mContext.getPackageManager().getPackagesForUid(callingUid));
    }

    private List<ComponentName> getBroadcastReceiverComponents(String str, Uri uri) {
        Intent intent = new Intent(str, uri);
        ArrayList arrayList = new ArrayList();
        Iterator<ResolveInfo> it = this.mContext.getPackageManager().queryBroadcastReceivers(intent, 0).iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = it.next().activityInfo;
            arrayList.add(new ComponentName(activityInfo.packageName, activityInfo.name));
        }
        return arrayList;
    }
}
