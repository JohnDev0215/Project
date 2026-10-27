package com.amazon.mp3.util.extensions;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;

/* JADX INFO: compiled from: Activity.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\f\u0010\u0002\u001a\u00020\u0001*\u0004\u0018\u00010\u0000\u001a\u0016\u0010\u0005\u001a\u00020\u0001*\u0004\u0018\u00010\u00002\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u001a\u0014\u0010\b\u001a\u00020\u0007*\u0004\u0018\u00010\u00002\u0006\u0010\u0006\u001a\u00020\u0001\u001a\f\u0010\t\u001a\u00020\u0001*\u0004\u0018\u00010\u0000¨\u0006\n"}, d2 = {"Landroid/app/Activity;", "", "isAvailable", "Landroidx/fragment/app/DialogFragment;", "dialogFragment", "showDialogFragment", "enabled", "", "setImmersiveMode", "inImmersiveMode", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0})
public final class ActivityKt {
    public static final boolean isAvailable(Activity activity) {
        return (activity == null || activity.isFinishing() || activity.isDestroyed()) ? false : true;
    }

    public static final boolean showDialogFragment(Activity activity, DialogFragment dialogFragment) {
        if (dialogFragment == null) {
            return false;
        }
        FragmentActivity fragmentActivity = activity instanceof FragmentActivity ? (FragmentActivity) activity : null;
        if (fragmentActivity == null || !isAvailable(fragmentActivity)) {
            return false;
        }
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        if (supportFragmentManager == null) {
            throw new KotlinNothingValueException();
        }
        dialogFragment.show(supportFragmentManager, dialogFragment.getClass().getSimpleName());
        return true;
    }

    public static final void setImmersiveMode(Activity activity, boolean z) {
        Window window;
        View decorView;
        int systemUiVisibility;
        if (activity == null || (window = activity.getWindow()) == null || (decorView = window.getDecorView()) == null) {
            return;
        }
        if (z) {
            systemUiVisibility = decorView.getSystemUiVisibility() | 5894;
        } else {
            systemUiVisibility = decorView.getSystemUiVisibility() & (-5895);
        }
        decorView.setSystemUiVisibility(systemUiVisibility);
    }

    public static final boolean inImmersiveMode(Activity activity) {
        Window window;
        View decorView;
        return (activity == null || (window = activity.getWindow()) == null || (decorView = window.getDecorView()) == null || (decorView.getSystemUiVisibility() | 5894) != decorView.getSystemUiVisibility()) ? false : true;
    }
}
