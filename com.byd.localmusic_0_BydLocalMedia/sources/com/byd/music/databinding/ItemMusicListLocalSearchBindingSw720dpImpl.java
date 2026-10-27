package com.byd.music.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.fragment.localMusic.search.LocalMusicSearchViewModel;

/* JADX INFO: loaded from: classes5.dex */
public class ItemMusicListLocalSearchBindingSw720dpImpl extends ItemMusicListLocalSearchBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.view2, 5);
    }

    public ItemMusicListLocalSearchBindingSw720dpImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 6, sIncludes, sViewsWithIds));
    }

    private ItemMusicListLocalSearchBindingSw720dpImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (TextView) objArr[3], (TextView) objArr[1], (TextView) objArr[2], (TextView) objArr[4], (View) objArr[5]);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.tvAnnouncer.setTag(null);
        this.tvIndex.setTag(null);
        this.tvSongName.setTag(null);
        this.tvStorageType.setTag(null);
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
        if (BR.localSearchMusicBean == i) {
            setLocalSearchMusicBean((LocalMusicBean) obj);
        } else {
            if (BR.viewModel != i) {
                return false;
            }
            setViewModel((LocalMusicSearchViewModel) obj);
        }
        return true;
    }

    @Override // com.byd.music.databinding.ItemMusicListLocalSearchBinding
    public void setLocalSearchMusicBean(LocalMusicBean localMusicBean) {
        this.mLocalSearchMusicBean = localMusicBean;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.localSearchMusicBean);
        super.requestRebind();
    }

    @Override // com.byd.music.databinding.ItemMusicListLocalSearchBinding
    public void setViewModel(LocalMusicSearchViewModel localMusicSearchViewModel) {
        this.mViewModel = localMusicSearchViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModelLocalSearchMusicBeansList((ObservableList) obj, i2);
    }

    private boolean onChangeViewModelLocalSearchMusicBeansList(ObservableList<LocalMusicBean> observableList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0059  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            r20 = this;
            r1 = r20
            monitor-enter(r20)
            long r2 = r1.mDirtyFlags     // Catch: java.lang.Throwable -> L9b
            r4 = 0
            r1.mDirtyFlags = r4     // Catch: java.lang.Throwable -> L9b
            monitor-exit(r20)     // Catch: java.lang.Throwable -> L9b
            com.byd.music.beans.LocalMusicBean r0 = r1.mLocalSearchMusicBean
            com.byd.music.fragment.localMusic.search.LocalMusicSearchViewModel r6 = r1.mViewModel
            r7 = 15
            long r7 = r7 & r2
            int r7 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            r8 = 10
            r10 = 14
            r12 = 0
            if (r7 == 0) goto L65
            long r14 = r2 & r10
            int r14 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r14 == 0) goto L31
            if (r0 == 0) goto L27
            java.lang.String r14 = r0.getTitle()
            goto L28
        L27:
            r14 = 0
        L28:
            if (r6 == 0) goto L2f
            com.byd.music.utils.binding.BindingCommand<com.byd.music.beans.LocalMusicBean> r15 = r6.listMusicLocalSearchClick
            java.lang.String r13 = r6.curKeyWord
            goto L34
        L2f:
            r13 = 0
            goto L33
        L31:
            r13 = 0
            r14 = 0
        L33:
            r15 = 0
        L34:
            long r16 = r2 & r8
            int r16 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r16 == 0) goto L45
            if (r0 == 0) goto L45
            java.lang.String r16 = r0.getStorageString()
            java.lang.String r17 = r0.getArtist()
            goto L49
        L45:
            r16 = 0
            r17 = 0
        L49:
            if (r6 == 0) goto L4e
            androidx.databinding.ObservableList<com.byd.music.beans.LocalMusicBean> r6 = r6.localSearchMusicBeansList
            goto L4f
        L4e:
            r6 = 0
        L4f:
            r1.updateRegistration(r12, r6)
            if (r6 == 0) goto L59
            int r6 = r6.indexOf(r0)
            goto L5a
        L59:
            r6 = r12
        L5a:
            int r6 = r6 + 1
            java.lang.String r6 = java.lang.String.valueOf(r6)
            r18 = r16
            r19 = r17
            goto L6d
        L65:
            r6 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r18 = 0
            r19 = 0
        L6d:
            long r10 = r10 & r2
            int r10 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r10 == 0) goto L7c
            androidx.constraintlayout.widget.ConstraintLayout r10 = r1.mboundView0
            com.byd.music.utils.binding.BindingUtils.onClickCommand(r10, r15, r12, r0, r12)
            android.widget.TextView r0 = r1.tvSongName
            com.byd.music.utils.binding.BindingUtils.setKeyWordColor(r0, r14, r13)
        L7c:
            long r2 = r2 & r8
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L93
            android.widget.TextView r0 = r1.tvAnnouncer
            r2 = 0
            r13 = r2
            java.lang.String r13 = (java.lang.String) r13
            r2 = r19
            com.byd.music.utils.binding.BindingUtils.setKeyWordColor(r0, r2, r13)
            android.widget.TextView r0 = r1.tvStorageType
            r2 = r18
            com.byd.music.utils.binding.BindingUtils.setKeyWordColor(r0, r2, r13)
        L93:
            if (r7 == 0) goto L9a
            android.widget.TextView r0 = r1.tvIndex
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r6)
        L9a:
            return
        L9b:
            r0 = move-exception
            monitor-exit(r20)     // Catch: java.lang.Throwable -> L9b
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.databinding.ItemMusicListLocalSearchBindingSw720dpImpl.executeBindings():void");
    }
}
