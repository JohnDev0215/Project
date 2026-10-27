package com.byd.music.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.fragment.radio.EmptyViewModel;
import com.byd.music.utils.binding.BindingUtils;

/* JADX INFO: loaded from: classes5.dex */
public class FragmentDabSettingBindingImpl extends FragmentDabSettingBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.layoutTitle, 2);
        sViewsWithIds.put(R.id.btnBack, 3);
        sViewsWithIds.put(R.id.checkBoxScroll, 4);
        sViewsWithIds.put(R.id.checkBoxContainer, 5);
    }

    public FragmentDabSettingBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 6, sIncludes, sViewsWithIds));
    }

    private FragmentDabSettingBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ImageButton) objArr[3], (LinearLayout) objArr[5], (ScrollView) objArr[4], (View) objArr[1], (LinearLayout) objArr[2]);
        this.mDirtyFlags = -1L;
        this.dummyBottom.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, Object obj) {
        if (BR.viewModel != i) {
            return false;
        }
        setViewModel((EmptyViewModel) obj);
        return true;
    }

    @Override // com.byd.music.databinding.FragmentDabSettingBinding
    public void setViewModel(EmptyViewModel emptyViewModel) {
        this.mViewModel = emptyViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModelIsMultiMode((ObservableBoolean) obj, i2);
    }

    private boolean onChangeViewModelIsMultiMode(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        EmptyViewModel emptyViewModel = this.mViewModel;
        long j2 = j & 7;
        if (j2 != 0) {
            ObservableBoolean observableBoolean = emptyViewModel != null ? emptyViewModel.isMultiMode : null;
            updateRegistration(0, observableBoolean);
            z = !(observableBoolean != null ? observableBoolean.get() : false);
        } else {
            z = false;
        }
        if (j2 != 0) {
            BindingUtils.setVisiable(this.dummyBottom, z, false, 0, 0);
        }
    }
}
