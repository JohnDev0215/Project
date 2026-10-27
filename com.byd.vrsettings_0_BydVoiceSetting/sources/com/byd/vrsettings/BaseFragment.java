package com.byd.vrsettings;

import android.content.res.Configuration;
import androidx.fragment.app.Fragment;
import com.byd.vrsettings.eventbus.OnConfigChangeEvent;
import com.byd.vrsettings.eventbus.SwitchEvent;
import com.byd.vrsettings.service.OtaDataManager;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseFragment extends Fragment {
    protected abstract void onConfigChangeUI(Configuration configuration);

    protected abstract void reloadData();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void switchLanguage(SwitchEvent switchEvent) {
        OtaDataManager.switchAppLanguage(getActivity());
        reloadData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnConfigChangeEvent(OnConfigChangeEvent onConfigChangeEvent) {
        onConfigChangeUI(onConfigChangeEvent.getNewConfig());
    }
}
