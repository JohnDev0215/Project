package com.amazon.mp3.customerprofile.viewModels;

import androidx.lifecycle.ViewModel;
import com.amazon.music.views.library.binders.ImageBinder;
import com.amazon.music.views.library.models.CustomerProfileModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EditProfileViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/customerprofile/viewModels/EditProfileViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "callbackHandler", "Lcom/amazon/mp3/customerprofile/viewModels/EditProfileViewModel$EditProfileCallback;", "getCallbackHandler", "()Lcom/amazon/mp3/customerprofile/viewModels/EditProfileViewModel$EditProfileCallback;", "setCallbackHandler", "(Lcom/amazon/mp3/customerprofile/viewModels/EditProfileViewModel$EditProfileCallback;)V", "customerProfileModel", "Lcom/amazon/music/views/library/models/CustomerProfileModel;", "getCustomerProfileModel", "()Lcom/amazon/music/views/library/models/CustomerProfileModel;", "setCustomerProfileModel", "(Lcom/amazon/music/views/library/models/CustomerProfileModel;)V", "imageBinder", "Lcom/amazon/music/views/library/binders/ImageBinder;", "getImageBinder", "()Lcom/amazon/music/views/library/binders/ImageBinder;", "setImageBinder", "(Lcom/amazon/music/views/library/binders/ImageBinder;)V", "updateProfileModelState", "", "profileAvatarUrl", "", "EditProfileCallback", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EditProfileViewModel extends ViewModel {
    private EditProfileCallback callbackHandler;
    private CustomerProfileModel customerProfileModel;
    private ImageBinder imageBinder;

    /* JADX INFO: compiled from: EditProfileViewModel.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/customerprofile/viewModels/EditProfileViewModel$EditProfileCallback;", "", "onDismiss", "", "currentViewModel", "Lcom/amazon/mp3/customerprofile/viewModels/EditProfileViewModel;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface EditProfileCallback {
        void onDismiss(EditProfileViewModel currentViewModel);
    }

    public final CustomerProfileModel getCustomerProfileModel() {
        return this.customerProfileModel;
    }

    public final void setCustomerProfileModel(CustomerProfileModel customerProfileModel) {
        this.customerProfileModel = customerProfileModel;
    }

    public final ImageBinder getImageBinder() {
        return this.imageBinder;
    }

    public final void setImageBinder(ImageBinder imageBinder) {
        this.imageBinder = imageBinder;
    }

    public final EditProfileCallback getCallbackHandler() {
        return this.callbackHandler;
    }

    public final void setCallbackHandler(EditProfileCallback editProfileCallback) {
        this.callbackHandler = editProfileCallback;
    }

    public final void updateProfileModelState(String profileAvatarUrl) {
        Intrinsics.checkNotNullParameter(profileAvatarUrl, "profileAvatarUrl");
        CustomerProfileModel customerProfileModel = this.customerProfileModel;
        if (customerProfileModel == null || customerProfileModel == null) {
            return;
        }
        customerProfileModel.setProfileAvatarUrl(profileAvatarUrl);
    }
}
