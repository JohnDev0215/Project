package com.android.car.settings.system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.settings.R;
import com.android.car.settings.common.ActivityResultCallback;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.security.CheckLockActivity;
import com.android.car.ui.toolbar.MenuItem;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class MasterClearFragment extends SettingsFragment implements ActivityResultCallback {
    static final int CHECK_LOCK_REQUEST_CODE = 88;
    private MenuItem mMasterClearButton;

    @Override // com.android.car.settings.common.SettingsFragment
    public List<MenuItem> getToolbarMenuItems() {
        return Collections.singletonList(this.mMasterClearButton);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mMasterClearButton = new MenuItem.Builder(getContext()).setTitle(R.string.master_clear_button_text).setEnabled(false).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.system.-$$Lambda$MasterClearFragment$gd7WXT57VzYAg85BhgRfuCG_7QE
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$0$MasterClearFragment(menuItem);
            }
        }).build();
    }

    public /* synthetic */ void lambda$onCreate$0$MasterClearFragment(MenuItem i) {
        startActivityForResult(new Intent(getContext(), (Class<?>) CheckLockActivity.class), 88, this);
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.master_clear_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final RecyclerView recyclerView = getListView();
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.android.car.settings.system.MasterClearFragment.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                MasterClearFragment.this.mMasterClearButton.setEnabled(MasterClearFragment.this.isAtEnd());
                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: com.android.car.settings.system.-$$Lambda$MasterClearFragment$bEMCqgKFLrTqKUNGjo1c67at8yU
            @Override // android.view.View.OnScrollChangeListener
            public final void onScrollChange(View view, int i, int i2, int i3, int i4) {
                this.f$0.lambda$onActivityCreated$1$MasterClearFragment(view, i, i2, i3, i4);
            }
        });
    }

    public /* synthetic */ void lambda$onActivityCreated$1$MasterClearFragment(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (isAtEnd()) {
            this.mMasterClearButton.setEnabled(true);
        }
    }

    @Override // com.android.car.settings.common.ActivityResultCallback
    public void processActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 88 && resultCode == -1) {
            launchFragment(new MasterClearConfirmFragment());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAtEnd() {
        RecyclerView recyclerView = getListView();
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null || layoutManager.getChildCount() == 0) {
            return true;
        }
        int childCount = layoutManager.getChildCount();
        View lastVisibleChild = layoutManager.getChildAt(childCount - 1);
        return layoutManager.getPosition(lastVisibleChild) == layoutManager.getItemCount() - 1 && layoutManager.getDecoratedBottom(lastVisibleChild) <= layoutManager.getHeight();
    }
}
