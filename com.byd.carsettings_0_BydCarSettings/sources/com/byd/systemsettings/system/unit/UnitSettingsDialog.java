package com.byd.systemsettings.system.unit;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.b.m.c.a;
import com.byd.base.view.dialog.BaseDialogFragment;
import com.byd.carsettings.R;

/* JADX INFO: loaded from: classes.dex */
public class UnitSettingsDialog extends BaseDialogFragment {
    public static final String TAG = "UnitSettingsDialog";
    public ViewGroup llMasks;
    public UnitSettingsPager pager;

    @Override // com.byd.base.view.dialog.BaseDialogFragment
    public int getLayoutResource() {
        return R.layout.unit_setting_dialog;
    }

    @Override // com.byd.base.view.dialog.BaseDialogFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.pager.onDestroy();
        a.a(TAG, "----onDestroy-------");
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        a.a(TAG, "----onViewCreated-------");
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.ll_dialog_masks);
        this.llMasks = viewGroup;
        viewGroup.setOnClickListener(new View.OnClickListener() { // from class: com.byd.systemsettings.system.unit.UnitSettingsDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                UnitSettingsDialog.this.dismiss();
            }
        });
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dialog_content);
        UnitSettingsPager unitSettingsPager = new UnitSettingsPager(getContext());
        this.pager = unitSettingsPager;
        unitSettingsPager.setFragment(this);
        frameLayout.addView(this.pager.getRootView());
        this.mWidth = (int) getResources().getDimension(R.dimen.dp_560);
        this.mHeight = (int) getResources().getDimension(R.dimen.dp_544);
        setAnim(this.llMasks, frameLayout);
        if (this.windowTitle == null) {
            setWindowTitle(getContext(), getClass().getName());
        }
    }
}
