package com.amazon.mp3.customerprofile.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.customerprofile.CustomerProfileMetrics;
import com.amazon.mp3.customerprofile.viewModels.EditProfileAttributeViewModel;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.views.library.models.CustomerProfileModel;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerProfileEditNameFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0014J\b\u0010\t\u001a\u00020\bH\u0014J\u0012\u0010\n\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0012\u0010\r\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\u000e\u001a\u00020\bH\u0014J\u001a\u0010\u000f\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\bH\u0014J\u0010\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/customerprofile/fragment/CustomerProfileEditNameFragment;", "Lcom/amazon/mp3/customerprofile/fragment/AbstractEditProfileAttributesFragment;", "()V", "profileNameChanged", "", "viewModel", "Lcom/amazon/mp3/customerprofile/viewModels/EditProfileAttributeViewModel;", "addTextChangedListener", "", "initTextForTextViews", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "saveProfileAttribute", "saveToLocal", "imageUri", "Landroid/net/Uri;", "profileName", "", "setSelectedProfileAttribute", "setSelectedProfileName", "updateAttributeModelState", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerProfileEditNameFragment extends AbstractEditProfileAttributesFragment {
    private static String TAG = CustomerProfileEditNameFragment.class.getSimpleName();
    private boolean profileNameChanged;
    private EditProfileAttributeViewModel viewModel;

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EditProfileAttributeViewModel editProfileAttributeViewModel = (EditProfileAttributeViewModel) new ViewModelProvider(this).get(EditProfileAttributeViewModel.class);
        this.viewModel = editProfileAttributeViewModel;
        if (editProfileAttributeViewModel == null) {
            return;
        }
        Bundle arguments = getArguments();
        Serializable serializable = arguments == null ? null : arguments.getSerializable("CustomerProfileModel");
        if (serializable == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.CustomerProfileModel");
        }
        editProfileAttributeViewModel.setCustomerProfileModel((CustomerProfileModel) serializable);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createTopBar(getActivity(), getResources().getString(R.string.dmusic_amazon_music_editprofile_subpage_nameheader));
    }

    @Override // com.amazon.mp3.customerprofile.fragment.AbstractEditProfileAttributesFragment
    protected void initTextForTextViews() {
        TextView mAttributeTitleView = getMAttributeTitleView();
        TextView mEditProfileMessageView = getMEditProfileMessageView();
        if (mAttributeTitleView != null) {
            mAttributeTitleView.setText(getResources().getString(R.string.dmusic_amazon_music_editprofile_subpage_name));
        }
        if (mEditProfileMessageView == null) {
            return;
        }
        mEditProfileMessageView.setText(getResources().getString(R.string.dmusic_amazon_music_editprofile_subpage_name_text));
    }

    @Override // com.amazon.mp3.customerprofile.fragment.AbstractEditProfileAttributesFragment
    protected void setSelectedProfileAttribute() {
        EditProfileAttributeViewModel editProfileAttributeViewModel;
        CustomerProfileModel customerProfileModel;
        String profileName;
        super.setSelectedProfileAttribute();
        EditProfileAttributeViewModel editProfileAttributeViewModel2 = this.viewModel;
        if ((editProfileAttributeViewModel2 == null ? null : editProfileAttributeViewModel2.getCustomerProfileModel()) == null || (editProfileAttributeViewModel = this.viewModel) == null || (customerProfileModel = editProfileAttributeViewModel.getCustomerProfileModel()) == null || (profileName = customerProfileModel.getProfileName()) == null) {
            return;
        }
        setSelectedProfileName(profileName);
    }

    @Override // com.amazon.mp3.customerprofile.fragment.AbstractEditProfileAttributesFragment
    protected void addTextChangedListener() {
        super.addTextChangedListener();
        EditText mEditAttributeView = getMEditAttributeView();
        if (mEditAttributeView == null) {
            return;
        }
        mEditAttributeView.addTextChangedListener(new TextWatcher() { // from class: com.amazon.mp3.customerprofile.fragment.CustomerProfileEditNameFragment$addTextChangedListener$$inlined$addTextChangedListener$default$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                CustomerProfileModel customerProfileModel;
                EditProfileAttributeViewModel editProfileAttributeViewModel = this.this$0.viewModel;
                String profileName = null;
                if ((editProfileAttributeViewModel == null ? null : editProfileAttributeViewModel.getCustomerProfileModel()) != null) {
                    CustomerProfileEditNameFragment customerProfileEditNameFragment = this.this$0;
                    EditProfileAttributeViewModel editProfileAttributeViewModel2 = customerProfileEditNameFragment.viewModel;
                    if (editProfileAttributeViewModel2 != null && (customerProfileModel = editProfileAttributeViewModel2.getCustomerProfileModel()) != null) {
                        profileName = customerProfileModel.getProfileName();
                    }
                    customerProfileEditNameFragment.profileNameChanged = !Intrinsics.areEqual(profileName, String.valueOf(s));
                    CustomerProfileEditNameFragment customerProfileEditNameFragment2 = this.this$0;
                    customerProfileEditNameFragment2.updateSaveButtonState(customerProfileEditNameFragment2.profileNameChanged);
                }
            }
        });
    }

    @Override // com.amazon.mp3.customerprofile.fragment.AbstractEditProfileAttributesFragment
    protected void saveProfileAttribute() {
        CustomerProfileModel customerProfileModel;
        EditProfileAttributeViewModel editProfileAttributeViewModel;
        super.saveProfileAttribute();
        CustomerProfileMetrics.emitUiClickMetric(ActionType.SAVE_NAME);
        String selectedAttributeValue = getSelectedAttributeValue();
        hideInvalidAttributeErrorMessage();
        int profileAvatarRotation = 0;
        enableDisableSaveButton(false);
        CustomerProfileEditNameFragment$saveProfileAttribute$callback$1 customerProfileEditNameFragment$saveProfileAttribute$callback$1 = new CustomerProfileEditNameFragment$saveProfileAttribute$callback$1(this, selectedAttributeValue);
        EditProfileAttributeViewModel editProfileAttributeViewModel2 = this.viewModel;
        if (editProfileAttributeViewModel2 != null && (customerProfileModel = editProfileAttributeViewModel2.getCustomerProfileModel()) != null) {
            profileAvatarRotation = customerProfileModel.getProfileAvatarRotation();
        }
        int i = profileAvatarRotation;
        Context context = getContext();
        if (context == null || (editProfileAttributeViewModel = this.viewModel) == null) {
            return;
        }
        editProfileAttributeViewModel.updateUserAttributeViaAsyncTask(context, null, selectedAttributeValue, null, null, null, customerProfileEditNameFragment$saveProfileAttribute$callback$1, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAttributeModelState(String profileName) {
        EditProfileAttributeViewModel editProfileAttributeViewModel = this.viewModel;
        Boolean boolValueOf = editProfileAttributeViewModel == null ? null : Boolean.valueOf(editProfileAttributeViewModel.updateNameModelState(profileName, getSelectedAttributeValue()));
        if (boolValueOf != null) {
            this.profileNameChanged = boolValueOf.booleanValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveToLocal(Uri imageUri, String profileName) {
        EditProfileAttributeViewModel editProfileAttributeViewModel;
        Context context = getContext();
        if (context == null || (editProfileAttributeViewModel = this.viewModel) == null) {
            return;
        }
        editProfileAttributeViewModel.saveToLocal(imageUri, profileName, context);
    }

    private final void setSelectedProfileName(String profileName) {
        setTextForEditText(profileName);
    }
}
