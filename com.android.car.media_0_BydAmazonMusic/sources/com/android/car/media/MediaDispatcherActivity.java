package com.android.car.media;

import android.car.media.XCarMediaIntents;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.android.car.media.common.source.MediaSourceViewModel;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import spi.b7;

/* JADX INFO: loaded from: classes.dex */
public class MediaDispatcherActivity extends FragmentActivity {
    public static Set<String> a;

    /* JADX WARN: Type inference failed for: r2v2, types: [java.util.HashSet, java.util.Set<java.lang.String>] */
    public static boolean a(Resources resources, @Nullable b7 b7Var) {
        if (a == null) {
            HashSet hashSet = new HashSet();
            a = hashSet;
            hashSet.addAll(Arrays.asList(resources.getStringArray(com.android.car.media.main.R$array.custom_media_packages)));
        }
        return b7Var != null && a.contains(b7Var.a.flattenToString());
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        String stringExtra;
        String action;
        ComponentName componentNameUnflattenFromString;
        super.onCreate(bundle);
        Intent intent = getIntent();
        Intent intent2 = null;
        if (intent != null) {
            action = intent.getAction();
            stringExtra = intent.getStringExtra(XCarMediaIntents.EXTRA_MEDIA_COMPONENT);
        } else {
            stringExtra = null;
            action = null;
        }
        if (Log.isLoggable("MediaDispatcherActivity", 4)) {
            Log.i("MediaDispatcherActivity", "onCreate action: " + action + " component: " + stringExtra);
        }
        b7 b7VarA = (!"android.car.intent.action.MEDIA_TEMPLATE".equals(action) || stringExtra == null || (componentNameUnflattenFromString = ComponentName.unflattenFromString(stringExtra)) == null) ? null : b7.a(this, componentNameUnflattenFromString);
        if (b7VarA == null) {
            b7VarA = MediaSourceViewModel.a(getApplication(), 1).c.getValue();
        }
        if (b7VarA != null && a(getResources(), b7VarA)) {
            String packageName = b7VarA.a.getPackageName();
            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(packageName);
            if (Log.isLoggable("MediaDispatcherActivity", 3)) {
                StringBuilder sbN = spi.c.n("Getting launch intent for package : ", packageName);
                sbN.append(launchIntentForPackage != null ? " succeeded" : " failed");
                Log.d("MediaDispatcherActivity", sbN.toString());
            }
            intent2 = launchIntentForPackage;
        }
        if (intent2 == null) {
            intent2 = new Intent(this, (Class<?>) MediaActivity.class);
        }
        if (b7VarA != null) {
            intent2.putExtra(XCarMediaIntents.EXTRA_MEDIA_COMPONENT, b7VarA.a.flattenToString());
        }
        intent2.setFlags(67108864);
        startActivity(intent2);
        finish();
    }
}
