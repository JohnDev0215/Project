package com.amazon.avod.fileio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ExternalStorageStatusChangeReceiver extends BroadcastReceiver {
    CopyOnWriteArraySet<ExternalStorageStatusChangeListener> mListeners = new CopyOnWriteArraySet<>();

    public void register(Context context) {
        context.registerReceiver(this, getIntentFilterForActions("android.intent.action.MEDIA_MOUNTED", "android.intent.action.MEDIA_UNMOUNTED"));
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.MEDIA_MOUNTED".equals(action)) {
            Iterator<ExternalStorageStatusChangeListener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onExternalStorageMounted();
            }
        } else if ("android.intent.action.MEDIA_UNMOUNTED".equals(action)) {
            Iterator<ExternalStorageStatusChangeListener> it2 = this.mListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onExternalStorageUnmounted();
            }
        }
    }

    public void addExternalStorageStatusChangeListener(@Nonnull ExternalStorageStatusChangeListener externalStorageStatusChangeListener) {
        Preconditions.checkNotNull(externalStorageStatusChangeListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.mListeners.add(externalStorageStatusChangeListener);
    }

    private IntentFilter getIntentFilterForActions(String... strArr) {
        IntentFilter intentFilter = new IntentFilter();
        for (String str : strArr) {
            intentFilter.addAction(str);
            intentFilter.addDataScheme("file");
        }
        return intentFilter;
    }
}
