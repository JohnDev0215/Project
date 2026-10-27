package com.android.car.developeroptions.nfc;

import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.core.InstrumentedFragment;
import com.android.car.developeroptions.enterprise.ActionDisabledByAdminDialogHelper;
import com.android.car.developeroptions.widget.SwitchBar;
import com.android.settingslib.HelpUtils;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;

/* JADX INFO: loaded from: classes.dex */
public class AndroidBeam extends InstrumentedFragment implements SwitchBar.OnSwitchChangeListener {
    private boolean mBeamDisallowedByBase;
    private boolean mBeamDisallowedByOnlyAdmin;
    private NfcAdapter mNfcAdapter;
    private CharSequence mOldActivityTitle;
    private SwitchBar mSwitchBar;
    private View mView;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 69;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        this.mNfcAdapter = NfcAdapter.getDefaultAdapter(activity);
        PackageManager packageManager = activity.getPackageManager();
        if (this.mNfcAdapter == null || !packageManager.hasSystemFeature("android.sofware.nfc.beam")) {
            getActivity().finish();
        }
        setHasOptionsMenu(true);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        HelpUtils.prepareHelpMenuItem(getActivity(), menu, R.string.help_uri_beam, AndroidBeam.class.getName());
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(getActivity(), "no_outgoing_beam", UserHandle.myUserId());
        UserManager.get(getActivity());
        boolean zHasBaseUserRestriction = RestrictedLockUtilsInternal.hasBaseUserRestriction(getActivity(), "no_outgoing_beam", UserHandle.myUserId());
        this.mBeamDisallowedByBase = zHasBaseUserRestriction;
        if (!zHasBaseUserRestriction && enforcedAdminCheckIfRestrictionEnforced != null) {
            new ActionDisabledByAdminDialogHelper(getActivity()).prepareDialogBuilder("no_outgoing_beam", enforcedAdminCheckIfRestrictionEnforced).show();
            this.mBeamDisallowedByOnlyAdmin = true;
            return new View(getContext());
        }
        View viewInflate = layoutInflater.inflate(R.layout.preference_footer, viewGroup, false);
        this.mView = viewInflate;
        ((ImageView) viewInflate.findViewById(android.R.id.icon)).setImageResource(R.drawable.ic_info_outline_24dp);
        ((TextView) this.mView.findViewById(android.R.id.title)).setText(R.string.android_beam_explained);
        return this.mView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        this.mOldActivityTitle = settingsActivity.getActionBar().getTitle();
        SwitchBar switchBar = settingsActivity.getSwitchBar();
        this.mSwitchBar = switchBar;
        if (this.mBeamDisallowedByOnlyAdmin) {
            switchBar.hide();
        } else {
            switchBar.setChecked(!this.mBeamDisallowedByBase && this.mNfcAdapter.isNdefPushEnabled());
            this.mSwitchBar.addOnSwitchChangeListener(this);
            this.mSwitchBar.setEnabled(!this.mBeamDisallowedByBase);
            this.mSwitchBar.show();
        }
        settingsActivity.setTitle(R.string.android_beam_settings_title);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (this.mOldActivityTitle != null) {
            getActivity().getActionBar().setTitle(this.mOldActivityTitle);
        }
        if (this.mBeamDisallowedByOnlyAdmin) {
            return;
        }
        this.mSwitchBar.removeOnSwitchChangeListener(this);
        this.mSwitchBar.hide();
    }

    @Override // com.android.car.developeroptions.widget.SwitchBar.OnSwitchChangeListener
    public void onSwitchChanged(Switch r2, boolean z) {
        boolean zDisableNdefPush;
        this.mSwitchBar.setEnabled(false);
        if (z) {
            zDisableNdefPush = this.mNfcAdapter.enableNdefPush();
        } else {
            zDisableNdefPush = this.mNfcAdapter.disableNdefPush();
        }
        if (zDisableNdefPush) {
            this.mSwitchBar.setChecked(z);
        }
        this.mSwitchBar.setEnabled(true);
    }
}
