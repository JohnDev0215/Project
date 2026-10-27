package com.amazon.mp3.customerprofile.viewModels;

import android.content.Context;
import android.net.Uri;
import androidx.lifecycle.ViewModel;
import com.amazon.mp3.customerprofile.CustomerProfileService;
import com.amazon.mp3.customerprofile.UpdateProfileAsyncTask;
import com.amazon.mp3.library.mylibrary.LibraryPreferences;
import com.amazon.music.views.library.models.CompactHeaderModel;
import com.amazon.music.views.library.models.CustomerProfileModel;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EditProfileAttributeViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000eJ\u000e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0012J\u0016\u0010\u0017\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u000eJP\u0010\u0019\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u000e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006 "}, d2 = {"Lcom/amazon/mp3/customerprofile/viewModels/EditProfileAttributeViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "customerProfileModel", "Lcom/amazon/music/views/library/models/CustomerProfileModel;", "getCustomerProfileModel", "()Lcom/amazon/music/views/library/models/CustomerProfileModel;", "setCustomerProfileModel", "(Lcom/amazon/music/views/library/models/CustomerProfileModel;)V", "saveToLocal", "", "imageUri", "Landroid/net/Uri;", "profileName", "", "context", "Landroid/content/Context;", "updateHandleModelState", "", "profileHandle", "selectedProfileHandle", "updateModelFollowPrivacy", "isChecked", "updateNameModelState", "selectedProfileName", "updateUserAttributeViaAsyncTask", "profileStatus", "shareListeningHistory", "callback", "Lcom/amazon/mp3/customerprofile/CustomerProfileService$Callback;", "imageRotation", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EditProfileAttributeViewModel extends ViewModel {
    private CustomerProfileModel customerProfileModel;

    public final CustomerProfileModel getCustomerProfileModel() {
        return this.customerProfileModel;
    }

    public final void setCustomerProfileModel(CustomerProfileModel customerProfileModel) {
        this.customerProfileModel = customerProfileModel;
    }

    public final boolean updateNameModelState(String profileName, String selectedProfileName) {
        Intrinsics.checkNotNullParameter(profileName, "profileName");
        Intrinsics.checkNotNullParameter(selectedProfileName, "selectedProfileName");
        CustomerProfileModel customerProfileModel = this.customerProfileModel;
        if (customerProfileModel == null) {
            return false;
        }
        if (customerProfileModel != null) {
            customerProfileModel.setProfileName(profileName);
        }
        CustomerProfileModel customerProfileModel2 = this.customerProfileModel;
        return !Intrinsics.areEqual(customerProfileModel2 == null ? null : customerProfileModel2.getProfileName(), selectedProfileName);
    }

    public final boolean updateHandleModelState(String profileHandle, String selectedProfileHandle) {
        Intrinsics.checkNotNullParameter(profileHandle, "profileHandle");
        Intrinsics.checkNotNullParameter(selectedProfileHandle, "selectedProfileHandle");
        CustomerProfileModel customerProfileModel = this.customerProfileModel;
        if (customerProfileModel == null) {
            return false;
        }
        if (customerProfileModel != null) {
            customerProfileModel.setProfileHandle(profileHandle);
        }
        CustomerProfileModel customerProfileModel2 = this.customerProfileModel;
        return !Intrinsics.areEqual(customerProfileModel2 == null ? null : customerProfileModel2.getProfileHandle(), selectedProfileHandle);
    }

    public final void saveToLocal(Uri imageUri, String profileName, Context context) {
        Intrinsics.checkNotNullParameter(profileName, "profileName");
        Intrinsics.checkNotNullParameter(context, "context");
        LibraryPreferences libraryPreferences = LibraryPreferences.INSTANCE;
        CompactHeaderModel profileModel = libraryPreferences.readProfileModel(context);
        if (profileModel != null) {
            libraryPreferences.writeProfileModel(context, new CompactHeaderModel(null, profileName, profileModel.getLabel(), String.valueOf(imageUri)));
        }
    }

    public final void updateModelFollowPrivacy(boolean isChecked) {
        String str;
        if (isChecked) {
            str = "PUBLIC";
        } else {
            if (isChecked) {
                throw new NoWhenBranchMatchedException();
            }
            str = "PRIVATE";
        }
        CustomerProfileModel customerProfileModel = this.customerProfileModel;
        if (customerProfileModel == null) {
            return;
        }
        customerProfileModel.setProfileStatus(str);
    }

    public final void updateUserAttributeViaAsyncTask(Context context, Uri imageUri, String profileName, String profileHandle, String profileStatus, String shareListeningHistory, CustomerProfileService.Callback callback, int imageRotation) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        new UpdateProfileAsyncTask(context, imageUri, profileName, profileHandle, profileStatus, shareListeningHistory, callback, imageRotation).execute(new Void[0]);
    }
}
