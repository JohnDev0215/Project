package com.amplifyframework.devmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.amplifyframework.core.R;

/* JADX INFO: loaded from: classes5.dex */
public final class DevMenuDeviceFragment extends Fragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.dev_menu_fragment_device, viewGroup, false);
        ((TextView) viewInflate.findViewById(R.id.device_info_text)).setText(new DeviceInfo().toString());
        return viewInflate;
    }
}
