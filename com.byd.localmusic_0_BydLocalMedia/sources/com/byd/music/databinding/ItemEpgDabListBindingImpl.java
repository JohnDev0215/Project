package com.byd.music.databinding;

import android.hardware.radio.RadioManager;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.Converters;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.databinding.adapters.ViewBindingAdapter;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.beans.RadioDBBean;
import com.byd.music.beans.RadioEpgDBBean;
import com.byd.music.fragment.radio.EpgListViewModel;
import com.byd.music.service.RadioServiceWrapper;
import com.byd.music.utils.binding.BindingCommand;
import com.byd.music.utils.binding.BindingUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ItemEpgDabListBindingImpl extends ItemEpgDabListBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    public ItemEpgDabListBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private ItemEpgDabListBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, (ImageView) objArr[2], (TextView) objArr[1]);
        this.mDirtyFlags = -1L;
        this.clock.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.text.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
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
        if (BR.setBean == i) {
            setSetBean((RadioDBBean) obj);
        } else {
            if (BR.viewModel != i) {
                return false;
            }
            setViewModel((EpgListViewModel) obj);
        }
        return true;
    }

    @Override // com.byd.music.databinding.ItemEpgDabListBinding
    public void setSetBean(RadioDBBean radioDBBean) {
        this.mSetBean = radioDBBean;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.setBean);
        super.requestRebind();
    }

    @Override // com.byd.music.databinding.ItemEpgDabListBinding
    public void setViewModel(EpgListViewModel epgListViewModel) {
        this.mViewModel = epgListViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeViewModelSubscribeEpgList((ObservableArrayList) obj, i2);
        }
        if (i != 1) {
            return false;
        }
        return onChangeViewModelCurrentDabInfo((ObservableField) obj, i2);
    }

    private boolean onChangeViewModelSubscribeEpgList(ObservableArrayList<RadioEpgDBBean> observableArrayList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelCurrentDabInfo(ObservableField<RadioManager.ProgramInfo> observableField, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        int i;
        BindingCommand<RadioDBBean> bindingCommand;
        ObservableArrayList<RadioEpgDBBean> observableArrayList;
        String str;
        int colorFromResource;
        ObservableArrayList<RadioEpgDBBean> observableArrayList2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        RadioDBBean radioDBBean = this.mSetBean;
        EpgListViewModel epgListViewModel = this.mViewModel;
        if ((31 & j) != 0) {
            String radioName = ((j & 20) == 0 || radioDBBean == null) ? null : radioDBBean.getRadioName();
            long j2 = j & 30;
            if (j2 != 0) {
                long dab_sid = radioDBBean != null ? radioDBBean.getDAB_SID() : 0L;
                ObservableField<RadioManager.ProgramInfo> observableField = epgListViewModel != null ? epgListViewModel.currentDabInfo : null;
                updateRegistration(1, observableField);
                boolean z = RadioServiceWrapper.getDabSID(observableField != null ? observableField.get() : null) == dab_sid;
                if (j2 != 0) {
                    j |= z ? 64L : 32L;
                }
                colorFromResource = getColorFromResource(this.mboundView0, z ? R.color.color_epg_selected : R.color.color_epg_no_select);
            } else {
                colorFromResource = 0;
            }
            if ((j & 29) != 0) {
                observableArrayList2 = epgListViewModel != null ? epgListViewModel.subscribeEpgList : null;
                updateRegistration(0, observableArrayList2);
            } else {
                observableArrayList2 = null;
            }
            if ((j & 28) == 0 || epgListViewModel == null) {
                i = colorFromResource;
                observableArrayList = observableArrayList2;
                str = radioName;
                bindingCommand = null;
            } else {
                String str2 = radioName;
                bindingCommand = epgListViewModel.dabItemClickCommand;
                i = colorFromResource;
                observableArrayList = observableArrayList2;
                str = str2;
            }
        } else {
            i = 0;
            bindingCommand = null;
            observableArrayList = null;
            str = null;
        }
        if ((j & 29) != 0) {
            BindingUtils.setDabSubscribeVisible(this.clock, radioDBBean, observableArrayList);
        }
        if ((j & 30) != 0) {
            ViewBindingAdapter.setBackground(this.mboundView0, Converters.convertColorToDrawable(i));
        }
        if ((28 & j) != 0) {
            BindingUtils.onClickCommand(this.mboundView0, bindingCommand, false, radioDBBean, false);
        }
        if ((j & 20) != 0) {
            TextViewBindingAdapter.setText(this.text, str);
        }
    }
}
