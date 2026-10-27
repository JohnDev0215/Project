package com.byd.systemsettings.system;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.base.view.dialog.BaseDialogFragment;
import com.byd.carsettings.R;
import com.byd.systemsettings.security.SecurityPager;

/* JADX INFO: loaded from: classes.dex */
public class SystemSecurityDialog extends BaseDialogFragment {
    private ViewGroup llMasks;
    private SecurityPager pager;

    @Override // com.byd.base.view.dialog.BaseDialogFragment
    public int getLayoutResource() {
        return R.layout.system_security_dialog;
    }

    @Override // com.byd.base.view.dialog.BaseDialogFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.pager.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.ll_dialog_masks);
        this.llMasks = viewGroup;
        viewGroup.setOnClickListener(new View.OnClickListener() { // from class: com.byd.systemsettings.system.SystemSecurityDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SystemSecurityDialog.this.dismiss();
            }
        });
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dialog_content);
        SecurityPager securityPager = new SecurityPager(getContext());
        this.pager = securityPager;
        securityPager.setFragment(this);
        frameLayout.addView(this.pager.getRootView());
        setAnim(this.llMasks, frameLayout);
        if (this.windowTitle == null) {
            setWindowTitle(getContext(), getClass().getName());
        }
    }
}
