package com.byd.music.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableInt;
import androidx.databinding.ViewDataBinding;
import com.byd.music.BR;
import com.byd.music.activity.main.MediaMainViewModel;

/* JADX INFO: loaded from: classes5.dex */
public class ItemSourceHorizontalBindingImpl extends ItemSourceHorizontalBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    public ItemSourceHorizontalBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private ItemSourceHorizontalBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ImageView) objArr[1], (TextView) objArr[2]);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.sourceImage.setTag(null);
        this.sourceTitle.setTag(null);
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
        if (BR.titleId == i) {
            setTitleId((Integer) obj);
        } else {
            if (BR.viewModel != i) {
                return false;
            }
            setViewModel((MediaMainViewModel) obj);
        }
        return true;
    }

    @Override // com.byd.music.databinding.ItemSourceHorizontalBinding
    public void setTitleId(Integer num) {
        this.mTitleId = num;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.titleId);
        super.requestRebind();
    }

    @Override // com.byd.music.databinding.ItemSourceHorizontalBinding
    public void setViewModel(MediaMainViewModel mediaMainViewModel) {
        this.mViewModel = mediaMainViewModel;
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
        return onChangeViewModelCheckedPosition((ObservableInt) obj, i2);
    }

    private boolean onChangeViewModelCheckedPosition(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            r19 = this;
            r1 = r19
            monitor-enter(r19)
            long r2 = r1.mDirtyFlags     // Catch: java.lang.Throwable -> L92
            r4 = 0
            r1.mDirtyFlags = r4     // Catch: java.lang.Throwable -> L92
            monitor-exit(r19)     // Catch: java.lang.Throwable -> L92
            java.lang.Integer r0 = r1.mTitleId
            com.byd.music.activity.main.MediaMainViewModel r6 = r1.mViewModel
            r7 = 15
            long r7 = r7 & r2
            int r7 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            r8 = 14
            r10 = 10
            r13 = 0
            if (r7 == 0) goto L6a
            long r14 = r2 & r10
            int r14 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r14 == 0) goto L25
            int r14 = androidx.databinding.ViewDataBinding.safeUnbox(r0)
            goto L26
        L25:
            r14 = r13
        L26:
            if (r6 == 0) goto L2d
            java.util.List<java.lang.Integer> r15 = r6.titleList
            androidx.databinding.ObservableInt r12 = r6.checkedPosition
            goto L2f
        L2d:
            r12 = 0
            r15 = 0
        L2f:
            r1.updateRegistration(r13, r12)
            if (r15 == 0) goto L39
            int r15 = r15.indexOf(r0)
            goto L3a
        L39:
            r15 = r13
        L3a:
            if (r12 == 0) goto L41
            int r12 = r12.get()
            goto L42
        L41:
            r12 = r13
        L42:
            if (r12 != r15) goto L46
            r12 = 1
            goto L47
        L46:
            r12 = r13
        L47:
            long r17 = r2 & r8
            int r17 = (r17 > r4 ? 1 : (r17 == r4 ? 0 : -1))
            if (r17 == 0) goto L68
            if (r6 == 0) goto L54
            com.byd.music.utils.binding.BindingCommand<java.lang.Integer> r10 = r6.sourceClickCommand
            java.util.List<java.lang.Integer> r6 = r6.sourceImageList
            goto L56
        L54:
            r6 = 0
            r10 = 0
        L56:
            if (r6 == 0) goto L61
            java.lang.Object r6 = r6.get(r15)
            java.lang.Integer r6 = (java.lang.Integer) r6
            r16 = r6
            goto L63
        L61:
            r16 = 0
        L63:
            int r6 = androidx.databinding.ViewDataBinding.safeUnbox(r16)
            goto L6e
        L68:
            r6 = r13
            goto L6d
        L6a:
            r6 = r13
            r12 = r6
            r14 = r12
        L6d:
            r10 = 0
        L6e:
            if (r7 == 0) goto L75
            androidx.constraintlayout.widget.ConstraintLayout r7 = r1.mboundView0
            com.byd.music.utils.binding.BindingUtils.setSelected(r7, r12)
        L75:
            long r7 = r2 & r8
            int r7 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r7 == 0) goto L85
            androidx.constraintlayout.widget.ConstraintLayout r7 = r1.mboundView0
            com.byd.music.utils.binding.BindingUtils.onClickCommand(r7, r10, r13, r0, r13)
            android.widget.ImageView r0 = r1.sourceImage
            com.byd.music.utils.binding.BindingUtils.setImageRes(r0, r6)
        L85:
            r6 = 10
            long r2 = r2 & r6
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L91
            android.widget.TextView r0 = r1.sourceTitle
            r0.setText(r14)
        L91:
            return
        L92:
            r0 = move-exception
            monitor-exit(r19)     // Catch: java.lang.Throwable -> L92
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.databinding.ItemSourceHorizontalBindingImpl.executeBindings():void");
    }
}
