package com.byd.music.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.beans.RadioDBBean;
import com.byd.music.beans.RadioEpgDBBean;
import com.byd.music.fragment.radio.EpgListViewModel;
import com.byd.music.service.RadioServiceWrapper;
import com.byd.music.utils.binding.BindingUtils;
import me.tatarka.bindingcollectionadapter2.BindingCollectionAdapters;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList;

/* JADX INFO: loaded from: classes5.dex */
public class FragmentEpgListBindingImpl extends FragmentEpgListBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ImageView mboundView3;
    private final TextView mboundView4;
    private final ImageView mboundView5;
    private final TextView mboundView6;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.btnBack, 7);
    }

    public FragmentEpgListBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }

    private FragmentEpgListBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, (ImageButton) objArr[7], (RecyclerView) objArr[1], (RecyclerView) objArr[2]);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        ImageView imageView = (ImageView) objArr[3];
        this.mboundView3 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) objArr[4];
        this.mboundView4 = textView;
        textView.setTag(null);
        ImageView imageView2 = (ImageView) objArr[5];
        this.mboundView5 = imageView2;
        imageView2.setTag(null);
        TextView textView2 = (TextView) objArr[6];
        this.mboundView6 = textView2;
        textView2.setTag(null);
        this.rvCurrentDabList.setTag(null);
        this.rvCurrentEPGList.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
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
        setViewModel((EpgListViewModel) obj);
        return true;
    }

    @Override // com.byd.music.databinding.FragmentEpgListBinding
    public void setViewModel(EpgListViewModel epgListViewModel) {
        this.mViewModel = epgListViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeRadioServiceWrapperGetInstanceDabValidList((ObservableArrayList) obj, i2);
        }
        if (i != 1) {
            return false;
        }
        return onChangeViewModelCurrentEpgList((DiffObservableList) obj, i2);
    }

    private boolean onChangeRadioServiceWrapperGetInstanceDabValidList(ObservableArrayList<RadioDBBean> observableArrayList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelCurrentEpgList(DiffObservableList<RadioEpgDBBean> diffObservableList, int i) {
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
        boolean z;
        boolean z2;
        OnItemBind<RadioDBBean> onItemBind;
        DiffObservableList<RadioEpgDBBean> diffObservableList;
        ItemBinding<RadioEpgDBBean> itemBinding;
        ObservableArrayList<RadioDBBean> observableArrayList;
        boolean z3;
        ItemBinding<RadioEpgDBBean> itemBinding2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        EpgListViewModel epgListViewModel = this.mViewModel;
        long j2 = j & 15;
        if (j2 != 0) {
            ObservableArrayList<RadioDBBean> observableArrayList2 = RadioServiceWrapper.getInstance().dabValidList;
            updateRegistration(0, observableArrayList2);
            int size = observableArrayList2 != null ? observableArrayList2.size() : 0;
            boolean z4 = size != 0;
            if (j2 != 0) {
                j = z4 ? j | 128 : j | 64;
            }
            z = (j & 9) != 0 && size == 0;
            if ((j & 14) != 0) {
                if (epgListViewModel != null) {
                    diffObservableList = epgListViewModel.currentEpgList;
                    itemBinding2 = epgListViewModel.currentEpgBinding;
                } else {
                    itemBinding2 = null;
                    diffObservableList = null;
                }
                updateRegistration(1, diffObservableList);
            } else {
                itemBinding2 = null;
                diffObservableList = null;
            }
            if ((j & 13) == 0 || epgListViewModel == null) {
                itemBinding = itemBinding2;
                observableArrayList = observableArrayList2;
                z2 = z4;
                onItemBind = null;
            } else {
                onItemBind = epgListViewModel.dabItemBinding;
                itemBinding = itemBinding2;
                observableArrayList = observableArrayList2;
                z2 = z4;
            }
        } else {
            z = false;
            z2 = false;
            onItemBind = null;
            diffObservableList = null;
            itemBinding = null;
            observableArrayList = null;
        }
        long j3 = 128 & j;
        if (j3 != 0) {
            if (epgListViewModel != null) {
                diffObservableList = epgListViewModel.currentEpgList;
            }
            updateRegistration(1, diffObservableList);
            z3 = (diffObservableList != null ? diffObservableList.size() : 0) == 0;
            if (j3 != 0) {
                j |= z3 ? 32L : 16L;
            }
        } else {
            z3 = false;
        }
        long j4 = 15 & j;
        if (j4 == 0 || !z2) {
            z3 = false;
        }
        if ((9 & j) != 0) {
            BindingUtils.setVisiable(this.mboundView3, z, false, 0, 0);
            BindingUtils.setVisiable(this.mboundView4, z, false, 0, 0);
            BindingUtils.setVisiable(this.rvCurrentDabList, z2, false, 0, 0);
            BindingUtils.setVisiable(this.rvCurrentEPGList, z2, false, 0, 0);
        }
        if (j4 != 0) {
            BindingUtils.setVisiable(this.mboundView5, z3, false, 0, 0);
            BindingUtils.setVisiable(this.mboundView6, z3, false, 0, 0);
        }
        if ((13 & j) != 0) {
            BindingRecyclerViewAdapters.setAdapter(this.rvCurrentDabList, BindingCollectionAdapters.toItemBinding(onItemBind), observableArrayList, (BindingRecyclerViewAdapter) null, (BindingRecyclerViewAdapter.ItemIds) null, (BindingRecyclerViewAdapter.ViewHolderFactory) null, (AsyncDifferConfig) null);
        }
        if ((j & 14) != 0) {
            BindingRecyclerViewAdapters.setAdapter(this.rvCurrentEPGList, itemBinding, diffObservableList, (BindingRecyclerViewAdapter) null, (BindingRecyclerViewAdapter.ItemIds) null, (BindingRecyclerViewAdapter.ViewHolderFactory) null, (AsyncDifferConfig) null);
        }
    }
}
