package com.byd.music.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableInt;
import androidx.databinding.ViewDataBinding;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.fragment.localMusic.activity.DialogActivityConfirmViewModel;

/* JADX INFO: loaded from: classes5.dex */
public class ActivityLocalDialogConfirmBindingImpl extends ActivityLocalDialogConfirmBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tv_delete, 3);
        sViewsWithIds.put(R.id.view, 4);
        sViewsWithIds.put(R.id.tv_progress, 5);
        sViewsWithIds.put(R.id.tv_cancel_delete, 6);
    }

    public ActivityLocalDialogConfirmBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 7, sIncludes, sViewsWithIds));
    }

    private ActivityLocalDialogConfirmBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ConstraintLayout) objArr[1], (SeekBar) objArr[2], (TextView) objArr[6], (TextView) objArr[3], (TextView) objArr[5], (View) objArr[4]);
        this.mDirtyFlags = -1L;
        this.clContent.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.seekBar.setTag(null);
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
        setViewModel((DialogActivityConfirmViewModel) obj);
        return true;
    }

    @Override // com.byd.music.databinding.ActivityLocalDialogConfirmBinding
    public void setViewModel(DialogActivityConfirmViewModel dialogActivityConfirmViewModel) {
        this.mViewModel = dialogActivityConfirmViewModel;
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
        return onChangeViewModelGetSbarMax((ObservableInt) obj, i2);
    }

    private boolean onChangeViewModelGetSbarMax(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            r12 = this;
            monitor-enter(r12)
            long r0 = r12.mDirtyFlags     // Catch: java.lang.Throwable -> L47
            r2 = 0
            r12.mDirtyFlags = r2     // Catch: java.lang.Throwable -> L47
            monitor-exit(r12)     // Catch: java.lang.Throwable -> L47
            com.byd.music.fragment.localMusic.activity.DialogActivityConfirmViewModel r4 = r12.mViewModel
            r5 = 7
            long r5 = r5 & r0
            int r5 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            r6 = 6
            r8 = 0
            r9 = 0
            if (r5 == 0) goto L33
            long r10 = r0 & r6
            int r10 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r10 == 0) goto L20
            if (r4 == 0) goto L20
            com.byd.music.utils.binding.BindingCommand r10 = r4.clContentCommand
            goto L21
        L20:
            r10 = r8
        L21:
            if (r4 == 0) goto L28
            androidx.databinding.ObservableInt r4 = r4.getSbarMax()
            goto L29
        L28:
            r4 = r8
        L29:
            r12.updateRegistration(r9, r4)
            if (r4 == 0) goto L34
            int r4 = r4.get()
            goto L35
        L33:
            r10 = r8
        L34:
            r4 = r9
        L35:
            long r0 = r0 & r6
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L3f
            androidx.constraintlayout.widget.ConstraintLayout r0 = r12.clContent
            com.byd.music.utils.binding.BindingUtils.onClickCommand(r0, r10, r9, r8, r9)
        L3f:
            if (r5 == 0) goto L46
            android.widget.SeekBar r0 = r12.seekBar
            r0.setMax(r4)
        L46:
            return
        L47:
            r0 = move-exception
            monitor-exit(r12)     // Catch: java.lang.Throwable -> L47
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.databinding.ActivityLocalDialogConfirmBindingImpl.executeBindings():void");
    }
}
