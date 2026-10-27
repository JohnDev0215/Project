package com.android.car.settings.system;

import android.content.Intent;
import android.os.Bundle;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.security.CheckLockActivity;
import com.android.car.ui.toolbar.MenuItem;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ResetNetworkFragment extends SettingsFragment {
    private static final int REQUEST_CODE = 123;
    private MenuItem mResetButton;

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.reset_network_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment
    public List<MenuItem> getToolbarMenuItems() {
        return Collections.singletonList(this.mResetButton);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mResetButton = new MenuItem.Builder(getContext()).setTitle(R.string.reset_network_button_text).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.system.-$$Lambda$ResetNetworkFragment$_kRFJNbqgXJwa7geDQAaY_ewCpo
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$0$ResetNetworkFragment(menuItem);
            }
        }).build();
    }

    public /* synthetic */ void lambda$onCreate$0$ResetNetworkFragment(MenuItem i) {
        startActivityForResult(new Intent(getContext(), (Class<?>) CheckLockActivity.class), 123);
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == -1) {
            launchFragment(new ResetNetworkConfirmFragment());
        }
    }
}
