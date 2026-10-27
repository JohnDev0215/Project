package com.android.car.developeroptions.accounts;

import android.accounts.Account;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.utils.ThreadUtils;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AvatarViewMixin implements LifecycleObserver {
    static final Intent INTENT_GET_ACCOUNT_DATA = new Intent("android.content.action.SETTINGS_ACCOUNT_DATA");
    private final ActivityManager mActivityManager;
    private final MutableLiveData<Bitmap> mAvatarImage;
    private final ImageView mAvatarView;
    private final Context mContext;

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        if (!this.mContext.getResources().getBoolean(R.bool.config_show_avatar_in_homepage)) {
            Log.d("AvatarViewMixin", "Feature disabled by config. Skipping");
            return;
        }
        if (this.mActivityManager.isLowRamDevice()) {
            Log.d("AvatarViewMixin", "Feature disabled on low ram device. Skipping");
        } else if (hasAccount()) {
            loadAccount();
        } else {
            this.mAvatarView.setImageResource(R.drawable.ic_account_circle_24dp);
        }
    }

    boolean hasAccount() {
        Account[] accounts = FeatureFactory.getFactory(this.mContext).getAccountFeatureProvider().getAccounts(this.mContext);
        return accounts != null && accounts.length > 0;
    }

    private void loadAccount() {
        final String strQueryProviderAuthority = queryProviderAuthority();
        if (TextUtils.isEmpty(strQueryProviderAuthority)) {
            return;
        }
        ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.car.developeroptions.accounts.-$$Lambda$AvatarViewMixin$V28IEkiIp7iyAkBlkt9Sk73n2j0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$loadAccount$2$AvatarViewMixin(strQueryProviderAuthority);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$loadAccount$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$loadAccount$2$AvatarViewMixin(String str) {
        Bundle bundleCall = this.mContext.getContentResolver().call(new Uri.Builder().scheme("content").authority(str).build(), "getAccountAvatar", (String) null, (Bundle) null);
        Bitmap bitmap = (Bitmap) bundleCall.getParcelable("account_avatar");
        bundleCall.getString("account_name", "");
        this.mAvatarImage.postValue(bitmap);
    }

    String queryProviderAuthority() {
        List<ResolveInfo> listQueryIntentContentProviders = this.mContext.getPackageManager().queryIntentContentProviders(INTENT_GET_ACCOUNT_DATA, 1048576);
        if (listQueryIntentContentProviders.size() == 1) {
            return listQueryIntentContentProviders.get(0).providerInfo.authority;
        }
        Log.w("AvatarViewMixin", "The size of the provider is " + listQueryIntentContentProviders.size());
        return null;
    }
}
