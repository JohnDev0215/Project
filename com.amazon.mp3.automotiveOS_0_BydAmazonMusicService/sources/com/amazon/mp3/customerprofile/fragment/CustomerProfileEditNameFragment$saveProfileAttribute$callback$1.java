package com.amazon.mp3.customerprofile.fragment;

import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.customerprofile.CustomerProfileService;
import com.amazon.mp3.customerprofile.viewModels.EditProfileAttributeViewModel;
import com.amazon.mp3.util.Log;
import com.amazon.music.views.library.models.CustomerProfileModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerProfileEditNameFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016¨\u0006\u0007"}, d2 = {"com/amazon/mp3/customerprofile/fragment/CustomerProfileEditNameFragment$saveProfileAttribute$callback$1", "Lcom/amazon/mp3/customerprofile/CustomerProfileService$Callback;", "onError", "", "exception", "Lcom/amazon/mp3/customerprofile/CustomerProfileService$UpdateException;", "onSuccess", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerProfileEditNameFragment$saveProfileAttribute$callback$1 implements CustomerProfileService.Callback {
    final /* synthetic */ String $profileName;
    final /* synthetic */ CustomerProfileEditNameFragment this$0;

    CustomerProfileEditNameFragment$saveProfileAttribute$callback$1(CustomerProfileEditNameFragment customerProfileEditNameFragment, String str) {
        this.this$0 = customerProfileEditNameFragment;
        this.$profileName = str;
    }

    @Override // com.amazon.mp3.customerprofile.CustomerProfileService.Callback
    public void onSuccess() {
        FragmentActivity activity = this.this$0.getActivity();
        if (activity == null) {
            return;
        }
        final CustomerProfileEditNameFragment customerProfileEditNameFragment = this.this$0;
        final String str = this.$profileName;
        activity.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.customerprofile.fragment.CustomerProfileEditNameFragment$saveProfileAttribute$callback$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CustomerProfileEditNameFragment$saveProfileAttribute$callback$1.m792onSuccess$lambda0(customerProfileEditNameFragment, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onSuccess$lambda-0, reason: not valid java name */
    public static final void m792onSuccess$lambda0(CustomerProfileEditNameFragment this$0, String profileName) {
        FragmentManager supportFragmentManager;
        CustomerProfileModel customerProfileModel;
        String profileAvatarUrl;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(profileName, "$profileName");
        this$0.updateAttributeModelState(profileName);
        EditProfileAttributeViewModel editProfileAttributeViewModel = this$0.viewModel;
        Uri uri = null;
        if (editProfileAttributeViewModel != null && (customerProfileModel = editProfileAttributeViewModel.getCustomerProfileModel()) != null && (profileAvatarUrl = customerProfileModel.getProfileAvatarUrl()) != null) {
            uri = Uri.parse(profileAvatarUrl);
            Intrinsics.checkNotNullExpressionValue(uri, "parse(this)");
        }
        this$0.saveToLocal(uri, profileName);
        FragmentActivity activity = this$0.getActivity();
        if (activity == null || (supportFragmentManager = activity.getSupportFragmentManager()) == null) {
            return;
        }
        supportFragmentManager.popBackStack();
    }

    @Override // com.amazon.mp3.customerprofile.CustomerProfileService.Callback
    public void onError(CustomerProfileService.UpdateException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        if (exception.isInvalidNameException()) {
            String string = this.this$0.getResources().getString(R.string.dmusic_edit_profile_name_title_error);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…profile_name_title_error)");
            this.this$0.showInvalidAttributeErrorMessage(string);
        } else {
            this.this$0.showTextToast();
        }
        Log.error(CustomerProfileEditNameFragment.TAG, exception.toString());
    }
}
