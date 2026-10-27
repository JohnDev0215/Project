package com.amazon.mp3.customerprofile;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.customerprofile.CustomerProfileService;
import com.amazon.mp3.util.BauhausToastUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: CustomerProfileDialogFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 72\u00020\u0001:\u00017B\u0007\b\u0016¢\u0006\u0002\u0010\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001cH\u0002J\b\u0010\u001e\u001a\u00020\u001cH\u0002J\u0012\u0010\u001f\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J&\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010(\u001a\u00020\u001cH\u0016J\u0010\u0010)\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020+H\u0016J\u0010\u0010,\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020!H\u0016J\b\u0010.\u001a\u00020\u001cH\u0016J\b\u0010/\u001a\u00020\u001cH\u0016J\u0010\u00100\u001a\u00020\u001c2\u0006\u00101\u001a\u00020\u0015H\u0002J\u0010\u00102\u001a\u00020\u001c2\u0006\u00103\u001a\u00020\u0007H\u0002J\b\u00104\u001a\u00020\u001cH\u0002J\u0010\u00105\u001a\u00020\u001c2\u0006\u00106\u001a\u00020\u0007H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lcom/amazon/mp3/customerprofile/CustomerProfileDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "LOG", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "PRIVATE_STATUS", "", "closeButtonView", "Landroid/widget/ImageView;", "confirmationButtonView", "Landroid/widget/Button;", "dialogFragmentMessageView", "Landroid/widget/TextView;", "handler", "Landroid/os/Handler;", "mButtonTextResourceId", "", "mMessageResourceId", "mProfileStatusOnConfirm", "onErrorCallback", "Ljava/lang/Runnable;", "onSuccessCallback", "spinnerView", "Landroid/widget/ProgressBar;", "updateProfileAsyncTask", "Lcom/amazon/mp3/customerprofile/UpdateProfileAsyncTask;", "confirmationAction", "", "hideConfirmationButton", "hideSpinner", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "onSaveInstanceState", "outState", "onStart", "onStop", "runOnUIThread", "action", "setProfileStatus", "profileStatus", "showSpinner", "updateLocalCustomerStatus", NotificationCompat.CATEGORY_STATUS, "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerProfileDialogFragment extends DialogFragment {
    private ImageView closeButtonView;
    private Button confirmationButtonView;
    private TextView dialogFragmentMessageView;
    private int mButtonTextResourceId;
    private int mMessageResourceId;
    private ProgressBar spinnerView;
    private UpdateProfileAsyncTask updateProfileAsyncTask;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String BUNDLE_MESSAGE_RESOURCE_KEY = "bundleMessageResourceID";
    private static final String BUNDLE_BUTTON_TEXT_RESOURCE_KEY = "bundleButtonTextResourceID";
    private static final String BUNDLE_DIALOG_ACTION_RESOURCE_KEY = "bundleDialogAction";
    private final Logger LOG = LoggerFactory.getLogger((Class<?>) CustomerProfileDialogFragment.class);
    private final String PRIVATE_STATUS = "PRIVATE";
    private Runnable onSuccessCallback = new Runnable() { // from class: com.amazon.mp3.customerprofile.CustomerProfileDialogFragment$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            CustomerProfileDialogFragment.m786onSuccessCallback$lambda0(this.f$0);
        }
    };
    private String mProfileStatusOnConfirm = "PRIVATE";
    private Runnable onErrorCallback = new Runnable() { // from class: com.amazon.mp3.customerprofile.CustomerProfileDialogFragment$$ExternalSyntheticLambda1
        @Override // java.lang.Runnable
        public final void run() {
            CustomerProfileDialogFragment.m785onErrorCallback$lambda1(this.f$0);
        }
    };
    private final Handler handler = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onSuccessCallback$lambda-0, reason: not valid java name */
    public static final void m786onSuccessCallback$lambda0(CustomerProfileDialogFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
        this$0.hideSpinner();
    }

    /* JADX INFO: compiled from: CustomerProfileDialogFragment.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/customerprofile/CustomerProfileDialogFragment$Companion;", "", "()V", "BUNDLE_BUTTON_TEXT_RESOURCE_KEY", "", "getBUNDLE_BUTTON_TEXT_RESOURCE_KEY", "()Ljava/lang/String;", "BUNDLE_DIALOG_ACTION_RESOURCE_KEY", "getBUNDLE_DIALOG_ACTION_RESOURCE_KEY", "BUNDLE_MESSAGE_RESOURCE_KEY", "getBUNDLE_MESSAGE_RESOURCE_KEY", "createDialog", "Lcom/amazon/mp3/customerprofile/CustomerProfileDialogFragment;", "messageResourceId", "", "buttonTextResourceId", "makeProfileStatus", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getBUNDLE_MESSAGE_RESOURCE_KEY() {
            return CustomerProfileDialogFragment.BUNDLE_MESSAGE_RESOURCE_KEY;
        }

        public final String getBUNDLE_BUTTON_TEXT_RESOURCE_KEY() {
            return CustomerProfileDialogFragment.BUNDLE_BUTTON_TEXT_RESOURCE_KEY;
        }

        public final String getBUNDLE_DIALOG_ACTION_RESOURCE_KEY() {
            return CustomerProfileDialogFragment.BUNDLE_DIALOG_ACTION_RESOURCE_KEY;
        }

        public final CustomerProfileDialogFragment createDialog(int messageResourceId, int buttonTextResourceId, String makeProfileStatus) {
            Intrinsics.checkNotNullParameter(makeProfileStatus, "makeProfileStatus");
            CustomerProfileDialogFragment customerProfileDialogFragment = new CustomerProfileDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(getBUNDLE_MESSAGE_RESOURCE_KEY(), messageResourceId);
            bundle.putInt(getBUNDLE_BUTTON_TEXT_RESOURCE_KEY(), buttonTextResourceId);
            bundle.putString(getBUNDLE_DIALOG_ACTION_RESOURCE_KEY(), makeProfileStatus);
            customerProfileDialogFragment.setArguments(bundle);
            return customerProfileDialogFragment;
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, R.style.ProfileDialogFragment);
        if (savedInstanceState == null) {
            savedInstanceState = getArguments();
        }
        if (savedInstanceState != null) {
            this.mMessageResourceId = savedInstanceState.getInt(BUNDLE_MESSAGE_RESOURCE_KEY);
            this.mButtonTextResourceId = savedInstanceState.getInt(BUNDLE_BUTTON_TEXT_RESOURCE_KEY);
            String string = savedInstanceState.getString(BUNDLE_DIALOG_ACTION_RESOURCE_KEY);
            if (string != null) {
                this.mProfileStatusOnConfirm = string;
            }
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putInt(BUNDLE_MESSAGE_RESOURCE_KEY, this.mMessageResourceId);
        String str = BUNDLE_BUTTON_TEXT_RESOURCE_KEY;
        outState.putInt(str, this.mButtonTextResourceId);
        outState.putString(str, this.mProfileStatusOnConfirm);
        super.onSaveInstanceState(outState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onErrorCallback$lambda-1, reason: not valid java name */
    public static final void m785onErrorCallback$lambda1(CustomerProfileDialogFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.hideSpinner();
        Context context = this$0.getContext();
        BauhausToastUtils.showTextToast(this$0.getActivity(), context == null ? null : context.getString(R.string.dmusic_edit_profile_update_fail_text), 0);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button button;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        super.onCreateView(inflater, container, savedInstanceState);
        View viewInflate = inflater.inflate(R.layout.customer_profile_dialog_layout, container, false);
        View viewFindViewById = viewInflate.findViewById(R.id.edit_profile_close_button);
        if (viewFindViewById == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.closeButtonView = (ImageView) viewFindViewById;
        View viewFindViewById2 = viewInflate.findViewById(R.id.edit_profile_confirmation_button);
        if (viewFindViewById2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.Button");
        }
        this.confirmationButtonView = (Button) viewFindViewById2;
        View viewFindViewById3 = viewInflate.findViewById(R.id.edit_profile_progress_bar);
        if (viewFindViewById3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.ProgressBar");
        }
        this.spinnerView = (ProgressBar) viewFindViewById3;
        View viewFindViewById4 = viewInflate.findViewById(R.id.customer_profile_dialog_fragment_message);
        if (viewFindViewById4 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
        }
        TextView textView = (TextView) viewFindViewById4;
        this.dialogFragmentMessageView = textView;
        if (this.mMessageResourceId != 0) {
            textView.setText(getResources().getString(this.mMessageResourceId));
        }
        if (this.mButtonTextResourceId != 0 && (button = this.confirmationButtonView) != null) {
            button.setText(getResources().getString(this.mButtonTextResourceId));
        }
        ImageView imageView = this.closeButtonView;
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.customerprofile.CustomerProfileDialogFragment$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomerProfileDialogFragment.m783onCreateView$lambda2(this.f$0, view);
                }
            });
        }
        Button button2 = this.confirmationButtonView;
        if (button2 != null) {
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.customerprofile.CustomerProfileDialogFragment$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomerProfileDialogFragment.m784onCreateView$lambda3(this.f$0, view);
                }
            });
        }
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-2, reason: not valid java name */
    public static final void m783onCreateView$lambda2(CustomerProfileDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-3, reason: not valid java name */
    public static final void m784onCreateView$lambda3(CustomerProfileDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.confirmationAction();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            Dialog dialog = getDialog();
            Intrinsics.checkNotNull(dialog);
            Window window = dialog.getWindow();
            if (window == null) {
                return;
            }
            window.setLayout(-1, -1);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onDismiss(dialog);
        hideSpinner();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.handler.removeCallbacks(this.onErrorCallback);
        this.handler.removeCallbacks(this.onSuccessCallback);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        UpdateProfileAsyncTask updateProfileAsyncTask = this.updateProfileAsyncTask;
        if (updateProfileAsyncTask != null) {
            updateProfileAsyncTask.cancel(true);
        }
        this.closeButtonView = null;
        this.confirmationButtonView = null;
        this.spinnerView = null;
        this.dialogFragmentMessageView = null;
    }

    private final void confirmationAction() {
        hideConfirmationButton();
        showSpinner();
        setProfileStatus(this.mProfileStatusOnConfirm);
    }

    private final void showSpinner() {
        ProgressBar progressBar = this.spinnerView;
        if (progressBar == null) {
            return;
        }
        progressBar.setVisibility(0);
    }

    private final void hideSpinner() {
        ProgressBar progressBar = this.spinnerView;
        if (progressBar == null) {
            return;
        }
        progressBar.setVisibility(8);
    }

    private final void hideConfirmationButton() {
        Button button = this.confirmationButtonView;
        if (button == null) {
            return;
        }
        button.setVisibility(8);
    }

    private final void setProfileStatus(final String profileStatus) {
        UpdateProfileAsyncTask updateProfileAsyncTask = new UpdateProfileAsyncTask(getContext(), null, CustomerProfileDetailManager.getInstance().getProfileName(), null, profileStatus, CustomerProfileDetailManager.getInstance().getProfileModel().getShareListeningHistory(), new CustomerProfileService.Callback() { // from class: com.amazon.mp3.customerprofile.CustomerProfileDialogFragment$setProfileStatus$callback$1
            @Override // com.amazon.mp3.customerprofile.CustomerProfileService.Callback
            public void onSuccess() {
                this.this$0.updateLocalCustomerStatus(profileStatus);
                CustomerProfileDialogFragment customerProfileDialogFragment = this.this$0;
                customerProfileDialogFragment.runOnUIThread(customerProfileDialogFragment.onSuccessCallback);
            }

            @Override // com.amazon.mp3.customerprofile.CustomerProfileService.Callback
            public void onError(CustomerProfileService.UpdateException exception) {
                Intrinsics.checkNotNullParameter(exception, "exception");
                this.this$0.LOG.debug("Error changing profile status, exception code:" + exception + ".code");
                CustomerProfileDialogFragment customerProfileDialogFragment = this.this$0;
                customerProfileDialogFragment.runOnUIThread(customerProfileDialogFragment.onErrorCallback);
            }
        }, 0);
        this.updateProfileAsyncTask = updateProfileAsyncTask;
        updateProfileAsyncTask.execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateLocalCustomerStatus(String status) {
        CustomerProfileDetailManager.getInstance().setProfileStatus(status);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void runOnUIThread(Runnable action) {
        if (Intrinsics.areEqual(Looper.getMainLooper().getThread(), Thread.currentThread())) {
            action.run();
        } else {
            this.handler.post(action);
        }
    }
}
