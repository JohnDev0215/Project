package com.android.car.settings.common;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes5.dex */
public interface FragmentController {
    DialogFragment findDialogByTag(String str);

    void goBack();

    void launchFragment(Fragment fragment);

    void showDialog(DialogFragment dialogFragment, String str);

    void startActivityForResult(Intent intent, int i, ActivityResultCallback activityResultCallback);

    void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, Bundle bundle, ActivityResultCallback activityResultCallback) throws IntentSender.SendIntentException;
}
