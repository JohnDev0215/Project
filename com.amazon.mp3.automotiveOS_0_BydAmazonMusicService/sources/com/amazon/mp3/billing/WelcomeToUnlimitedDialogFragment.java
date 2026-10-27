package com.amazon.mp3.billing;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.util.BauhausDialogBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WelcomeToUnlimitedDialogFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0013\u0010\u0014J\u0012\u0010\u0006\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\f\u001a\u00020\u000b2\b\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u0010\u0010\r\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0007H\u0016R\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/billing/WelcomeToUnlimitedDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "Landroid/content/DialogInterface$OnClickListener;", "Landroid/os/Bundle;", "savedInstanceState", "Landroid/app/Dialog;", "onCreateDialog", "Landroid/content/DialogInterface;", "dialog", "", "p1", "", "onClick", "onDismiss", "Landroid/app/Activity;", "displayedActivity", "Landroid/app/Activity;", "getDisplayedActivity", "()Landroid/app/Activity;", "<init>", "(Landroid/app/Activity;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class WelcomeToUnlimitedDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    private final Activity displayedActivity;

    public WelcomeToUnlimitedDialogFragment(Activity displayedActivity) {
        Intrinsics.checkNotNullParameter(displayedActivity, "displayedActivity");
        this.displayedActivity = displayedActivity;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        return new BauhausDialogBuilder(contextRequireContext).setTitle(R.string.dmusic_hawkfire_google_in_app_billing_success_general_header).setMessage(R.string.dmusic_hawkfire_google_in_app_billing_success_upgrade_success).setPositiveButton(R.string.dmusic_hawkfire_google_in_app_billing_success_upgrade_success_primary_button_message, this).setOnDismissListener(this).create();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int p1) {
        if (dialog == null) {
            return;
        }
        onDismiss(dialog);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onDismiss(dialog);
        if (this.displayedActivity.isTaskRoot()) {
            Context applicationContext = this.displayedActivity.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "displayedActivity.applicationContext");
            BrushFragmentNavigation.navigateToHome(applicationContext);
        }
        MusicHomeActivity.startPrimeSectionWithRefresh(this.displayedActivity);
        this.displayedActivity.finish();
    }
}
