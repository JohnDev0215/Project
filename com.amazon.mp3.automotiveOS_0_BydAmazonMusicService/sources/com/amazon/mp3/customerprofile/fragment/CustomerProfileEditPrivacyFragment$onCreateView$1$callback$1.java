package com.amazon.mp3.customerprofile.fragment;

import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.customerprofile.CustomerProfileService;
import com.amazon.mp3.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerProfileEditPrivacyFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016¨\u0006\u0007"}, d2 = {"com/amazon/mp3/customerprofile/fragment/CustomerProfileEditPrivacyFragment$onCreateView$1$callback$1", "Lcom/amazon/mp3/customerprofile/CustomerProfileService$Callback;", "onError", "", "exception", "Lcom/amazon/mp3/customerprofile/CustomerProfileService$UpdateException;", "onSuccess", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerProfileEditPrivacyFragment$onCreateView$1$callback$1 implements CustomerProfileService.Callback {
    final /* synthetic */ boolean $isChecked;
    final /* synthetic */ CustomerProfileEditPrivacyFragment this$0;

    CustomerProfileEditPrivacyFragment$onCreateView$1$callback$1(CustomerProfileEditPrivacyFragment customerProfileEditPrivacyFragment, boolean z) {
        this.this$0 = customerProfileEditPrivacyFragment;
        this.$isChecked = z;
    }

    @Override // com.amazon.mp3.customerprofile.CustomerProfileService.Callback
    public void onSuccess() {
        FragmentActivity activity = this.this$0.getActivity();
        if (activity == null) {
            return;
        }
        final CustomerProfileEditPrivacyFragment customerProfileEditPrivacyFragment = this.this$0;
        final boolean z = this.$isChecked;
        activity.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.customerprofile.fragment.CustomerProfileEditPrivacyFragment$onCreateView$1$callback$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CustomerProfileEditPrivacyFragment$onCreateView$1$callback$1.m795onSuccess$lambda0(customerProfileEditPrivacyFragment, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onSuccess$lambda-0, reason: not valid java name */
    public static final void m795onSuccess$lambda0(CustomerProfileEditPrivacyFragment this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.updateModelFollowPrivacy(z);
    }

    @Override // com.amazon.mp3.customerprofile.CustomerProfileService.Callback
    public void onError(CustomerProfileService.UpdateException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        Log.error(CustomerProfileEditPrivacyFragment.TAG, "Privacy settings failed to get updated due to service failure");
    }
}
