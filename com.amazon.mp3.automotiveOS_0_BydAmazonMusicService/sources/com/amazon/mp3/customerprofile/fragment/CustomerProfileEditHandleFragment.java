package com.amazon.mp3.customerprofile.fragment;

import android.content.Context;
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
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: CustomerProfileEditHandleFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0014J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\f\u001a\u00020\bH\u0014J\u0012\u0010\r\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0012\u0010\u0010\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0011\u001a\u00020\bH\u0014J\b\u0010\u0012\u001a\u00020\bH\u0014J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/customerprofile/fragment/CustomerProfileEditHandleFragment;", "Lcom/amazon/mp3/customerprofile/fragment/AbstractEditProfileAttributesFragment;", "()V", "profileHandleChanged", "", "viewModel", "Lcom/amazon/mp3/customerprofile/viewModels/EditProfileAttributeViewModel;", "addTextChangedListener", "", "getFinalHandle", "", "profileHandle", "initTextForTextViews", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "saveProfileAttribute", "setSelectedProfileAttribute", "setSelectedProfileHandle", "updateAttributeModelState", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerProfileEditHandleFragment extends AbstractEditProfileAttributesFragment {
    private boolean profileHandleChanged;
    private EditProfileAttributeViewModel viewModel;
    private static String TAG = CustomerProfileEditHandleFragment.class.getSimpleName();
    private static String HANDLE_SYMBOL = "@";

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

    @Override // com.amazon.mp3.customerprofile.fragment.AbstractEditProfileAttributesFragment
    protected void initTextForTextViews() {
        TextView mAttributeTitleView = getMAttributeTitleView();
        TextView mEditProfileMessageView = getMEditProfileMessageView();
        if (mAttributeTitleView != null) {
            mAttributeTitleView.setText(getResources().getString(R.string.dmusic_amazon_music_editprofile_subpage_handle));
        }
        if (mEditProfileMessageView == null) {
            return;
        }
        mEditProfileMessageView.setText(getResources().getString(R.string.dmusic_amazon_music_editprofile_subpage_handle_text));
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createTopBar(getActivity(), getResources().getString(R.string.dmusic_amazon_music_editprofile_subpage_handleheader));
    }

    @Override // com.amazon.mp3.customerprofile.fragment.AbstractEditProfileAttributesFragment
    protected void setSelectedProfileAttribute() {
        EditProfileAttributeViewModel editProfileAttributeViewModel;
        CustomerProfileModel customerProfileModel;
        String profileHandle;
        super.setSelectedProfileAttribute();
        EditProfileAttributeViewModel editProfileAttributeViewModel2 = this.viewModel;
        if ((editProfileAttributeViewModel2 == null ? null : editProfileAttributeViewModel2.getCustomerProfileModel()) == null || (editProfileAttributeViewModel = this.viewModel) == null || (customerProfileModel = editProfileAttributeViewModel.getCustomerProfileModel()) == null || (profileHandle = customerProfileModel.getProfileHandle()) == null) {
            return;
        }
        setSelectedProfileHandle(profileHandle);
    }

    @Override // com.amazon.mp3.customerprofile.fragment.AbstractEditProfileAttributesFragment
    protected void addTextChangedListener() {
        super.addTextChangedListener();
        EditText mEditAttributeView = getMEditAttributeView();
        if (mEditAttributeView == null) {
            return;
        }
        mEditAttributeView.addTextChangedListener(new TextWatcher() { // from class: com.amazon.mp3.customerprofile.fragment.CustomerProfileEditHandleFragment$addTextChangedListener$$inlined$addTextChangedListener$default$1
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
                String profileHandle = null;
                if ((editProfileAttributeViewModel == null ? null : editProfileAttributeViewModel.getCustomerProfileModel()) != null) {
                    String finalHandle = this.this$0.getFinalHandle(String.valueOf(s));
                    CustomerProfileEditHandleFragment customerProfileEditHandleFragment = this.this$0;
                    EditProfileAttributeViewModel editProfileAttributeViewModel2 = customerProfileEditHandleFragment.viewModel;
                    if (editProfileAttributeViewModel2 != null && (customerProfileModel = editProfileAttributeViewModel2.getCustomerProfileModel()) != null) {
                        profileHandle = customerProfileModel.getProfileHandle();
                    }
                    customerProfileEditHandleFragment.profileHandleChanged = !Intrinsics.areEqual(profileHandle, finalHandle);
                    CustomerProfileEditHandleFragment customerProfileEditHandleFragment2 = this.this$0;
                    customerProfileEditHandleFragment2.updateSaveButtonState(customerProfileEditHandleFragment2.profileHandleChanged);
                }
            }
        });
    }

    private final void setSelectedProfileHandle(String profileHandle) {
        if (profileHandle.length() == 0) {
            EditText mEditAttributeView = getMEditAttributeView();
            if (mEditAttributeView == null) {
                return;
            }
            mEditAttributeView.setHint(getResources().getString(R.string.dmusic_amazon_music_handle));
            return;
        }
        setTextForEditText(Intrinsics.stringPlus(HANDLE_SYMBOL, profileHandle));
    }

    @Override // com.amazon.mp3.customerprofile.fragment.AbstractEditProfileAttributesFragment
    protected void saveProfileAttribute() {
        CustomerProfileModel customerProfileModel;
        EditProfileAttributeViewModel editProfileAttributeViewModel;
        super.saveProfileAttribute();
        CustomerProfileMetrics.emitUiClickMetric(ActionType.SAVE_HANDLE);
        String selectedAttributeValue = getSelectedAttributeValue();
        hideInvalidAttributeErrorMessage();
        int profileAvatarRotation = 0;
        enableDisableSaveButton(false);
        CustomerProfileEditHandleFragment$saveProfileAttribute$callback$1 customerProfileEditHandleFragment$saveProfileAttribute$callback$1 = new CustomerProfileEditHandleFragment$saveProfileAttribute$callback$1(this, selectedAttributeValue);
        EditProfileAttributeViewModel editProfileAttributeViewModel2 = this.viewModel;
        if (editProfileAttributeViewModel2 != null && (customerProfileModel = editProfileAttributeViewModel2.getCustomerProfileModel()) != null) {
            profileAvatarRotation = customerProfileModel.getProfileAvatarRotation();
        }
        int i = profileAvatarRotation;
        String finalHandle = getFinalHandle(selectedAttributeValue);
        Context context = getContext();
        if (context == null || (editProfileAttributeViewModel = this.viewModel) == null) {
            return;
        }
        editProfileAttributeViewModel.updateUserAttributeViaAsyncTask(context, null, null, finalHandle, null, null, customerProfileEditHandleFragment$saveProfileAttribute$callback$1, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAttributeModelState(String profileHandle) {
        String finalHandle = getFinalHandle(profileHandle);
        EditProfileAttributeViewModel editProfileAttributeViewModel = this.viewModel;
        Boolean boolValueOf = editProfileAttributeViewModel == null ? null : Boolean.valueOf(editProfileAttributeViewModel.updateHandleModelState(finalHandle, getSelectedAttributeValue()));
        if (boolValueOf != null) {
            this.profileHandleChanged = boolValueOf.booleanValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getFinalHandle(String profileHandle) {
        if (!StringsKt__StringsJVMKt.startsWith$default(profileHandle, HANDLE_SYMBOL, false, 2, null)) {
            return profileHandle;
        }
        String strSubstring = profileHandle.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
        return strSubstring;
    }
}
