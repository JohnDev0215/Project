package com.amazon.mp3.billing;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausDialogBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BillingErrorDialogFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0017\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0014\u0010\u0015J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/billing/BillingErrorDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "Landroid/os/Bundle;", "savedInstanceState", "Landroid/app/Dialog;", "onCreateDialog", "Landroid/content/DialogInterface;", "dialog", "", "onDismiss", "", "errorMessage", "Ljava/lang/String;", "getErrorMessage", "()Ljava/lang/String;", "Landroid/app/Activity;", "displayedActivity", "Landroid/app/Activity;", "getDisplayedActivity", "()Landroid/app/Activity;", "<init>", "(Ljava/lang/String;Landroid/app/Activity;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BillingErrorDialogFragment extends DialogFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Activity displayedActivity;
    private final String errorMessage;

    public BillingErrorDialogFragment(String errorMessage, Activity displayedActivity) {
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        Intrinsics.checkNotNullParameter(displayedActivity, "displayedActivity");
        this.errorMessage = errorMessage;
        this.displayedActivity = displayedActivity;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        return new BauhausDialogBuilder(contextRequireContext).setTitle(R.string.dmusic_hawkfire_google_in_app_billing_error_general_header).setOnDismissListener(this).setMessage(this.errorMessage).create();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onDismiss(dialog);
        this.displayedActivity.finish();
    }

    /* JADX INFO: compiled from: BillingErrorDialogFragment.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ\u0016\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/billing/BillingErrorDialogFragment$Companion;", "", "", "amazonMusicTerritory", "Landroid/app/Activity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/mp3/billing/BillingErrorDialogFragment;", "createTerritoryMismatchDialog", "createPendingPurchaseDialog", "createAlreadySubscribedDialog", "createSubmissionRetryErrorDialog", "createGeneralFailureDialog", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BillingErrorDialogFragment createTerritoryMismatchDialog(String amazonMusicTerritory, Activity activity) {
            Intrinsics.checkNotNullParameter(amazonMusicTerritory, "amazonMusicTerritory");
            Intrinsics.checkNotNullParameter(activity, "activity");
            String string = activity.getString(R.string.dmusic_hawkfire_google_in_app_billing_error_territory_mismatch, amazonMusicTerritory);
            Intrinsics.checkNotNullExpressionValue(string, "activity.getString(R.str…    amazonMusicTerritory)");
            return new BillingErrorDialogFragment(string, activity);
        }

        public final BillingErrorDialogFragment createPendingPurchaseDialog(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            String string = activity.getString(R.string.dmusic_hawkfire_google_in_app_billing_error_pending_purchase);
            Intrinsics.checkNotNullExpressionValue(string, "activity.getString(R.str…g_error_pending_purchase)");
            return new BillingErrorDialogFragment(string, activity);
        }

        public final BillingErrorDialogFragment createAlreadySubscribedDialog(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            String string = activity.getString(R.string.dmusic_hawkfire_google_in_app_billing_error_already_subscribed);
            Intrinsics.checkNotNullExpressionValue(string, "activity.getString(R.str…error_already_subscribed)");
            return new BillingErrorDialogFragment(string, activity);
        }

        public final BillingErrorDialogFragment createSubmissionRetryErrorDialog(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            String string = activity.getString(R.string.dmusic_hawkfire_google_in_app_billing_error_purchase_submission_error_will_retry);
            Intrinsics.checkNotNullExpressionValue(string, "activity.getString(R.str…mission_error_will_retry)");
            return new BillingErrorDialogFragment(string, activity);
        }

        public final BillingErrorDialogFragment createGeneralFailureDialog(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            String string = activity.getString(R.string.dmusic_hawkfire_google_in_app_billing_error_general_failure);
            Intrinsics.checkNotNullExpressionValue(string, "activity.getString(R.str…ng_error_general_failure)");
            return new BillingErrorDialogFragment(string, activity);
        }
    }
}
