package com.byd.music.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.fragment.localMusic.LocalMusicListViewModel;
import com.byd.music.view.LoadingView;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/* JADX INFO: loaded from: classes5.dex */
public class FragmentLocalMusiclistBindingImpl extends FragmentLocalMusiclistBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.cl_operate, 2);
        sViewsWithIds.put(R.id.tv_delete_all_tips, 3);
        sViewsWithIds.put(R.id.iv_select_all, 4);
        sViewsWithIds.put(R.id.tv_select_all, 5);
        sViewsWithIds.put(R.id.iv_delete, 6);
        sViewsWithIds.put(R.id.tv_delete, 7);
        sViewsWithIds.put(R.id.iv_cancel_select, 8);
        sViewsWithIds.put(R.id.tv_cancel_select, 9);
        sViewsWithIds.put(R.id.loading_view, 10);
    }

    public FragmentLocalMusiclistBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 11, sIncludes, sViewsWithIds));
    }

    private FragmentLocalMusiclistBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ConstraintLayout) objArr[2], (ImageView) objArr[8], (ImageView) objArr[6], (ImageView) objArr[4], (LoadingView) objArr[10], (RecyclerView) objArr[1], (TextView) objArr[9], (TextView) objArr[7], (TextView) objArr[3], (TextView) objArr[5]);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.recyclerView.setTag(null);
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
        setViewModel((LocalMusicListViewModel) obj);
        return true;
    }

    @Override // com.byd.music.databinding.FragmentLocalMusiclistBinding
    public void setViewModel(LocalMusicListViewModel localMusicListViewModel) {
        this.mViewModel = localMusicListViewModel;
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
        return onChangeViewModelLocalMusicBeansList((ObservableList) obj, i2);
    }

    private boolean onChangeViewModelLocalMusicBeansList(ObservableList<LocalMusicBean> observableList, int i) {
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
        ItemBinding<LocalMusicBean> itemBinding;
        ObservableList<LocalMusicBean> observableList;
        ItemBinding<LocalMusicBean> itemBinding2;
        ObservableList<LocalMusicBean> observableList2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        LocalMusicListViewModel localMusicListViewModel = this.mViewModel;
        long j2 = j & 7;
        if (j2 != 0) {
            if (localMusicListViewModel != null) {
                itemBinding2 = localMusicListViewModel.localMusicBeanItemBinding;
                observableList2 = localMusicListViewModel.localMusicBeansList;
            } else {
                itemBinding2 = null;
                observableList2 = null;
            }
            updateRegistration(0, observableList2);
            itemBinding = itemBinding2;
            observableList = observableList2;
        } else {
            itemBinding = null;
            observableList = null;
        }
        if (j2 != 0) {
            BindingRecyclerViewAdapters.setAdapter(this.recyclerView, itemBinding, observableList, (BindingRecyclerViewAdapter) null, (BindingRecyclerViewAdapter.ItemIds) null, (BindingRecyclerViewAdapter.ViewHolderFactory) null, (AsyncDifferConfig) null);
        }
    }
}
