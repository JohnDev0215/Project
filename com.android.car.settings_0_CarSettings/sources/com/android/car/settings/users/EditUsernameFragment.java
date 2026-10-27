package com.android.car.settings.users;

import android.content.pm.UserInfo;
import android.os.Bundle;
import android.os.UserManager;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import com.android.car.settings.R;
import com.android.car.settings.common.BaseFragment;
import com.android.car.ui.toolbar.MenuItem;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class EditUsernameFragment extends BaseFragment {
    private static final String USER_NAME_SET = "user_name_set";
    private MenuItem mCancelButton;
    private MenuItem mOkButton;
    private UserInfo mUserInfo;
    UserManager mUserManager;
    private EditText mUserNameEditText;

    public static EditUsernameFragment newInstance(UserInfo userInfo) {
        EditUsernameFragment userSettingsFragment = new EditUsernameFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("android.intent.extra.USER", userInfo);
        userSettingsFragment.setArguments(bundle);
        return userSettingsFragment;
    }

    @Override // com.android.car.settings.common.BaseFragment
    public List<MenuItem> getToolbarMenuItems() {
        return Arrays.asList(this.mCancelButton, this.mOkButton);
    }

    @Override // com.android.car.settings.common.BaseFragment
    protected int getLayoutId() {
        return R.layout.edit_username_fragment;
    }

    @Override // com.android.car.settings.common.BaseFragment
    protected int getTitleId() {
        return R.string.edit_user_name_title;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mUserInfo = getArguments().getParcelable("android.intent.extra.USER");
        this.mCancelButton = new MenuItem.Builder(getContext()).setTitle(android.R.string.cancel).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.users.-$$Lambda$EditUsernameFragment$oKLh9vC2LAah8XmfKWy0xrBN4oQ
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$0$EditUsernameFragment(menuItem);
            }
        }).build();
        this.mOkButton = new MenuItem.Builder(getContext()).setTitle(android.R.string.ok).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.users.-$$Lambda$EditUsernameFragment$m4KksnDoIwJvY4IGu0EYuAPYXLA
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$1$EditUsernameFragment(menuItem);
            }
        }).build();
    }

    public /* synthetic */ void lambda$onCreate$0$EditUsernameFragment(MenuItem i) {
        getActivity().onBackPressed();
    }

    public /* synthetic */ void lambda$onCreate$1$EditUsernameFragment(MenuItem i) {
        this.mUserManager.setUserName(this.mUserInfo.id, this.mUserNameEditText.getText().toString());
        Settings.Secure.putInt(getActivity().getContentResolver(), USER_NAME_SET, 1);
        getActivity().onBackPressed();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mUserNameEditText = (EditText) view.findViewById(R.id.user_name_text_edit);
    }

    @Override // com.android.car.settings.common.BaseFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (this.mUserManager == null) {
            this.mUserManager = UserManager.get(getContext());
        }
        configureUsernameEditing();
    }

    private void configureUsernameEditing() {
        this.mUserNameEditText.setText(this.mUserInfo.name);
        this.mUserNameEditText.setEnabled(true);
        this.mUserNameEditText.setSelectAllOnFocus(true);
        this.mUserNameEditText.addTextChangedListener(new TextWatcher() { // from class: com.android.car.settings.users.EditUsernameFragment.1
            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    EditUsernameFragment.this.mOkButton.setEnabled(false);
                    EditUsernameFragment.this.mUserNameEditText.setError(EditUsernameFragment.this.getString(R.string.name_input_blank_error));
                } else if (!TextUtils.isGraphic(s)) {
                    EditUsernameFragment.this.mOkButton.setEnabled(false);
                    EditUsernameFragment.this.mUserNameEditText.setError(EditUsernameFragment.this.getString(R.string.name_input_invalid_error));
                } else {
                    EditUsernameFragment.this.mOkButton.setEnabled(true);
                    EditUsernameFragment.this.mUserNameEditText.setError(null);
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
