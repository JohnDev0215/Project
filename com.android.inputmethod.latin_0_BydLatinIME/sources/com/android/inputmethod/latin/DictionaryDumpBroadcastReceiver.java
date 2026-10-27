package com.android.inputmethod.latin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class DictionaryDumpBroadcastReceiver extends BroadcastReceiver {
    public static final String DICTIONARY_DUMP_INTENT_ACTION = "com.android.inputmethod.latin.DICT_DUMP";
    public static final String DICTIONARY_NAME_KEY = "dictName";
    private static final String DOMAIN = "com.android.inputmethod.latin";
    private static final String TAG = "DictionaryDumpBroadcastReceiver";
    final LatinIME mLatinIme;

    public DictionaryDumpBroadcastReceiver(final LatinIME latinIme) {
        this.mLatinIme = latinIme;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(DICTIONARY_DUMP_INTENT_ACTION)) {
            String stringExtra = intent.getStringExtra(DICTIONARY_NAME_KEY);
            if (stringExtra == null) {
                Log.e(TAG, "Received dictionary dump intent action but the dictionary name is not set.");
            } else {
                this.mLatinIme.dumpDictionaryForDebug(stringExtra);
            }
        }
    }
}
