package com.android.car.developeroptions.vpn2;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.IConnectivityManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.security.KeyStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.car.developeroptions.vpn2.ConfirmLockdownFragment;
import com.android.internal.net.LegacyVpnInfo;
import com.android.internal.net.VpnProfile;

/* JADX INFO: loaded from: classes.dex */
public class ConfigDialogFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener, DialogInterface.OnShowListener, View.OnClickListener, ConfirmLockdownFragment.ConfirmLockdownListener {
    private Context mContext;
    private final IConnectivityManager mService = IConnectivityManager.Stub.asInterface(ServiceManager.getService("connectivity"));

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 545;
    }

    public static void show(VpnSettings vpnSettings, VpnProfile vpnProfile, boolean z, boolean z2) {
        if (vpnSettings.isAdded()) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("profile", vpnProfile);
            bundle.putBoolean("editing", z);
            bundle.putBoolean("exists", z2);
            ConfigDialogFragment configDialogFragment = new ConfigDialogFragment();
            configDialogFragment.setArguments(bundle);
            configDialogFragment.setTargetFragment(vpnSettings, 0);
            configDialogFragment.show(vpnSettings.getFragmentManager(), "vpnconfigdialog");
        }
    }

    @Override // com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment, com.android.settingslib.core.lifecycle.ObservableDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        ConfigDialog configDialog = new ConfigDialog(getActivity(), this, arguments.getParcelable("profile"), arguments.getBoolean("editing"), arguments.getBoolean("exists"));
        configDialog.setOnShowListener(this);
        return configDialog;
    }

    @Override // android.content.DialogInterface.OnShowListener
    public void onShow(DialogInterface dialogInterface) {
        ((AlertDialog) getDialog()).getButton(-1).setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        onClick(getDialog(), -1);
    }

    @Override // com.android.car.developeroptions.vpn2.ConfirmLockdownFragment.ConfirmLockdownListener
    public void onConfirmLockdown(Bundle bundle, boolean z, boolean z2) {
        connect((VpnProfile) bundle.getParcelable("profile"), z);
        dismiss();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        ConfigDialog configDialog = (ConfigDialog) getDialog();
        Parcelable profile = configDialog.getProfile();
        if (i == -1) {
            boolean zIsVpnAlwaysOn = configDialog.isVpnAlwaysOn();
            boolean z = zIsVpnAlwaysOn || !configDialog.isEditing();
            boolean zIsAnyLockdownActive = VpnUtils.isAnyLockdownActive(this.mContext);
            try {
                boolean zIsVpnActive = VpnUtils.isVpnActive(this.mContext);
                if (z && !isConnected(profile) && ConfirmLockdownFragment.shouldShow(zIsVpnActive, zIsAnyLockdownActive, zIsVpnAlwaysOn)) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("profile", profile);
                    ConfirmLockdownFragment.show(this, zIsVpnActive, zIsVpnAlwaysOn, zIsAnyLockdownActive, zIsVpnAlwaysOn, bundle);
                } else if (z) {
                    connect(profile, zIsVpnAlwaysOn);
                } else {
                    save(profile, false);
                }
            } catch (RemoteException e) {
                Log.w("ConfigDialogFragment", "Failed to check active VPN state. Skipping.", e);
            }
        } else if (i == -3) {
            if (!disconnect(profile)) {
                Log.e("ConfigDialogFragment", "Failed to disconnect VPN. Leaving profile in keystore.");
                return;
            }
            KeyStore.getInstance().delete("VPN_" + ((VpnProfile) profile).key, -1);
            updateLockdownVpn(false, profile);
        }
        dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        dismiss();
        super.onCancel(dialogInterface);
    }

    private void updateLockdownVpn(boolean z, VpnProfile vpnProfile) {
        if (z) {
            if (!vpnProfile.isValidLockdownProfile()) {
                Toast.makeText(this.mContext, R.string.vpn_lockdown_config_error, 1).show();
                return;
            } else {
                ConnectivityManager.from(this.mContext).setAlwaysOnVpnPackageForUser(UserHandle.myUserId(), null, false, null);
                VpnUtils.setLockdownVpn(this.mContext, vpnProfile.key);
                return;
            }
        }
        if (VpnUtils.isVpnLockdown(vpnProfile.key)) {
            VpnUtils.clearLockdownVpn(this.mContext);
        }
    }

    private void save(VpnProfile vpnProfile, boolean z) {
        KeyStore.getInstance().put("VPN_" + vpnProfile.key, vpnProfile.encode(), -1, 0);
        disconnect(vpnProfile);
        updateLockdownVpn(z, vpnProfile);
    }

    private void connect(VpnProfile vpnProfile, boolean z) {
        save(vpnProfile, z);
        if (VpnUtils.isVpnLockdown(vpnProfile.key)) {
            return;
        }
        VpnUtils.clearLockdownVpn(this.mContext);
        try {
            this.mService.startLegacyVpn(vpnProfile);
        } catch (RemoteException e) {
            Log.e("ConfigDialogFragment", "Failed to connect", e);
        } catch (IllegalStateException unused) {
            Toast.makeText(this.mContext, R.string.vpn_no_network, 1).show();
        }
    }

    private boolean disconnect(VpnProfile vpnProfile) {
        try {
            if (isConnected(vpnProfile)) {
                return VpnUtils.disconnectLegacyVpn(getContext());
            }
            return true;
        } catch (RemoteException e) {
            Log.e("ConfigDialogFragment", "Failed to disconnect", e);
            return false;
        }
    }

    private boolean isConnected(VpnProfile vpnProfile) throws RemoteException {
        LegacyVpnInfo legacyVpnInfo = this.mService.getLegacyVpnInfo(UserHandle.myUserId());
        return legacyVpnInfo != null && vpnProfile.key.equals(legacyVpnInfo.key);
    }
}
