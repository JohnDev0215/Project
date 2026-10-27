package com.amazon.mp3.customerprofile.fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amazon.mp3.customerprofile.CustomerProfileMetrics;
import com.amazon.mp3.customerprofile.CustomerProfileService;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerProfileEditHandleFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016¨\u0006\u0007"}, d2 = {"com/amazon/mp3/customerprofile/fragment/CustomerProfileEditHandleFragment$saveProfileAttribute$callback$1", "Lcom/amazon/mp3/customerprofile/CustomerProfileService$Callback;", "onError", "", "exception", "Lcom/amazon/mp3/customerprofile/CustomerProfileService$UpdateException;", "onSuccess", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerProfileEditHandleFragment$saveProfileAttribute$callback$1 implements CustomerProfileService.Callback {
    final /* synthetic */ String $profileHandle;
    final /* synthetic */ CustomerProfileEditHandleFragment this$0;

    CustomerProfileEditHandleFragment$saveProfileAttribute$callback$1(CustomerProfileEditHandleFragment customerProfileEditHandleFragment, String str) {
        this.this$0 = customerProfileEditHandleFragment;
        this.$profileHandle = str;
    }

    @Override // com.amazon.mp3.customerprofile.CustomerProfileService.Callback
    public void onSuccess() {
        FragmentActivity activity = this.this$0.getActivity();
        if (activity == null) {
            return;
        }
        final CustomerProfileEditHandleFragment customerProfileEditHandleFragment = this.this$0;
        final String str = this.$profileHandle;
        activity.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.customerprofile.fragment.CustomerProfileEditHandleFragment$saveProfileAttribute$callback$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CustomerProfileEditHandleFragment$saveProfileAttribute$callback$1.m790onSuccess$lambda0(customerProfileEditHandleFragment, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onSuccess$lambda-0, reason: not valid java name */
    public static final void m790onSuccess$lambda0(CustomerProfileEditHandleFragment this$0, String profileHandle) {
        FragmentManager supportFragmentManager;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(profileHandle, "$profileHandle");
        CustomerProfileMetrics.emitUiClickMetric(ActionType.SAVE_HANDLE_SUCCESS);
        this$0.updateAttributeModelState(profileHandle);
        FragmentActivity activity = this$0.getActivity();
        if (activity == null || (supportFragmentManager = activity.getSupportFragmentManager()) == null) {
            return;
        }
        supportFragmentManager.popBackStack();
    }

    @Override // com.amazon.mp3.customerprofile.CustomerProfileService.Callback
    public void onError(CustomerProfileService.UpdateException exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        CustomerProfileMetrics.emitUiClickMetric(ActionType.SAVE_HANDLE_FAIL);
        this.this$0.showTextToast();
        Log.error(CustomerProfileEditHandleFragment.TAG, exception.toString());
    }
}
