package com.android.inputmethod.latin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.util.Log;
import com.android.inputmethod.dictionarypack.DictionaryPackConstants;
import com.android.inputmethod.latin.utils.TargetPackageInfoGetterTask;

/* JADX INFO: loaded from: classes2.dex */
public final class DictionaryPackInstallBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "DictionaryPackInstallBroadcastReceiver";
    final LatinIME mService;

    public DictionaryPackInstallBroadcastReceiver() {
        Log.i(TAG, "Latin IME dictionary broadcast receiver instantiated from the framework.");
        this.mService = null;
    }

    public DictionaryPackInstallBroadcastReceiver(final LatinIME service) {
        this.mService = service;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String schemeSpecificPart;
        String action = intent.getAction();
        PackageManager packageManager = context.getPackageManager();
        if (action.equals("android.intent.action.PACKAGE_ADDED")) {
            if (this.mService == null) {
                Log.e(TAG, "Called with intent " + action + " but we don't know the service: this should never happen");
                return;
            }
            Uri data = intent.getData();
            if (data == null || (schemeSpecificPart = data.getSchemeSpecificPart()) == null) {
                return;
            }
            TargetPackageInfoGetterTask.removeCachedPackageInfo(schemeSpecificPart);
            try {
                ProviderInfo[] providerInfoArr = packageManager.getPackageInfo(schemeSpecificPart, 8).providers;
                if (providerInfoArr == null) {
                    return;
                }
                for (ProviderInfo providerInfo : providerInfoArr) {
                    if (DictionaryPackConstants.AUTHORITY.equals(providerInfo.authority)) {
                        this.mService.resetSuggestMainDict();
                        return;
                    }
                }
                return;
            } catch (PackageManager.NameNotFoundException unused) {
                return;
            }
        }
        if (action.equals("android.intent.action.PACKAGE_REMOVED") && !intent.getBooleanExtra("android.intent.extra.REPLACING", false)) {
            LatinIME latinIME = this.mService;
            if (latinIME == null) {
                Log.e(TAG, "Called with intent " + action + " but we don't know the service: this should never happen");
                return;
            } else {
                latinIME.resetSuggestMainDict();
                return;
            }
        }
        if (action.equals(DictionaryPackConstants.NEW_DICTIONARY_INTENT_ACTION)) {
            LatinIME latinIME2 = this.mService;
            if (latinIME2 == null) {
                Log.e(TAG, "Called with intent " + action + " but we don't know the service: this should never happen");
                return;
            } else {
                latinIME2.resetSuggestMainDict();
                return;
            }
        }
        if (action.equals(DictionaryPackConstants.UNKNOWN_DICTIONARY_PROVIDER_CLIENT)) {
            if (this.mService != null) {
                Log.e(TAG, "Called with intent " + action + " but we have a reference to the service: this should never happen");
                return;
            }
            String stringExtra = intent.getStringExtra(DictionaryPackConstants.DICTIONARY_PROVIDER_CLIENT_EXTRA);
            String string = context.getString(R.string.dictionary_pack_client_id);
            if (stringExtra.equals(string)) {
                BinaryDictionaryFileDumper.initializeClientRecordHelper(context, string);
            }
        }
    }
}
