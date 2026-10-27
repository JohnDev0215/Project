package com.android.provision;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.android.provision.base.BaseFragment;
import com.android.provision.utils.FastClickUtil;
import com.android.provision.utils.StyleUtil;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class SafetyWarnFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = "SafetyWarnFragment";
    private Button tvOk;

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BydLogHelper.logW(TAG, "onCreateView");
        return inflater.inflate(StyleUtil.isDenza() ? R.layout.denza_activity_safety_warn : R.layout.activity_safety_warn, container, false);
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSafetyWarningView(view);
    }

    private void initSafetyWarningView(View view) {
        Button button = (Button) view.findViewById(R.id.tv_ok);
        this.tvOk = button;
        button.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.tv_ok) {
            return;
        }
        reset();
    }

    public void reset() {
        BydLogHelper.logW(TAG, "click ok");
        if (FastClickUtil.isFastClick()) {
            return;
        }
        ((DefaultActivity) getActivity()).cancelTips();
        BootGuideManager.getInstance().setSafetyWarnFinished();
        BootGuideManager.getInstance().finishSetUpWizard(getActivity());
        getActivity().finish();
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        BydLogHelper.logW(TAG, "onPause");
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        BydLogHelper.logW(TAG, "onStop");
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        BydLogHelper.logW(TAG, "onDestroy");
    }
}
