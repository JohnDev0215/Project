package com.android.packageinstaller.television;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.android.packageinstaller.PackageUtil;
import com.android.packageinstaller.R;
import com.android.packageinstaller.television.UninstallAppProgress;

/* JADX INFO: loaded from: classes2.dex */
public class UninstallAppProgressFragment extends Fragment implements View.OnClickListener, UninstallAppProgress.ProgressFragment {
    private static final String TAG = "UninstallAppProgressF";
    private Button mDeviceManagerButton;
    private Button mOkButton;
    private Button mUsersButton;

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.uninstall_progress, container, false);
        View snippetView = root.findViewById(R.id.app_snippet);
        PackageUtil.initSnippetForInstalledApp(getContext(), ((UninstallAppProgress) getActivity()).getAppInfo(), snippetView);
        this.mDeviceManagerButton = (Button) root.findViewById(R.id.device_manager_button);
        this.mUsersButton = (Button) root.findViewById(R.id.users_button);
        this.mDeviceManagerButton.setVisibility(8);
        this.mDeviceManagerButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.packageinstaller.television.UninstallAppProgressFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.android.settings", "com.android.settings.Settings$DeviceAdminSettingsActivity");
                intent.setFlags(1342177280);
                UninstallAppProgressFragment.this.startActivity(intent);
                UninstallAppProgressFragment.this.getActivity().finish();
            }
        });
        this.mUsersButton.setVisibility(8);
        this.mUsersButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.packageinstaller.television.UninstallAppProgressFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent intent = new Intent("android.settings.USER_SETTINGS");
                intent.setFlags(1342177280);
                UninstallAppProgressFragment.this.startActivity(intent);
                UninstallAppProgressFragment.this.getActivity().finish();
            }
        });
        Button button = (Button) root.findViewById(R.id.ok_button);
        this.mOkButton = button;
        button.setOnClickListener(this);
        return root;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        UninstallAppProgress activity = (UninstallAppProgress) getActivity();
        if (v == this.mOkButton && activity != null) {
            Log.i(TAG, "Finished uninstalling pkg: " + activity.getAppInfo().packageName);
            activity.setResultAndFinish();
        }
    }

    @Override // com.android.packageinstaller.television.UninstallAppProgress.ProgressFragment
    public void setUsersButtonVisible(boolean visible) {
        this.mUsersButton.setVisibility(visible ? 0 : 8);
    }

    @Override // com.android.packageinstaller.television.UninstallAppProgress.ProgressFragment
    public void setDeviceManagerButtonVisible(boolean visible) {
        this.mDeviceManagerButton.setVisibility(visible ? 0 : 8);
    }

    @Override // com.android.packageinstaller.television.UninstallAppProgress.ProgressFragment
    public void showCompletion(CharSequence statusText) {
        View root = getView();
        root.findViewById(R.id.progress_view).setVisibility(8);
        root.findViewById(R.id.status_view).setVisibility(0);
        ((TextView) root.findViewById(R.id.status_text)).setText(statusText);
        root.findViewById(R.id.ok_panel).setVisibility(0);
    }
}
