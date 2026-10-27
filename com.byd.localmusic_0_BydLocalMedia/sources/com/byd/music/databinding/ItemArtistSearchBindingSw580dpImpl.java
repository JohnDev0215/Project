package com.byd.music.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.byd.music.BR;
import com.byd.music.beans.AlbumBean;
import com.byd.music.fragment.localMusic.search.LocalMusicSearchViewModel;
import com.google.android.material.imageview.ShapeableImageView;

/* JADX INFO: loaded from: classes5.dex */
public class ItemArtistSearchBindingSw580dpImpl extends ItemArtistSearchBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ShapeableImageView mboundView1;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public ItemArtistSearchBindingSw580dpImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private ItemArtistSearchBindingSw580dpImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (TextView) objArr[2]);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        ShapeableImageView shapeableImageView = (ShapeableImageView) objArr[1];
        this.mboundView1 = shapeableImageView;
        shapeableImageView.setTag(null);
        this.tvAlbumName.setTag(null);
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
        if (BR.localSearchAlbumBean == i) {
            setLocalSearchAlbumBean((AlbumBean) obj);
        } else {
            if (BR.viewModel != i) {
                return false;
            }
            setViewModel((LocalMusicSearchViewModel) obj);
        }
        return true;
    }

    @Override // com.byd.music.databinding.ItemArtistSearchBinding
    public void setLocalSearchAlbumBean(AlbumBean albumBean) {
        this.mLocalSearchAlbumBean = albumBean;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.localSearchAlbumBean);
        super.requestRebind();
    }

    @Override // com.byd.music.databinding.ItemArtistSearchBinding
    public void setViewModel(LocalMusicSearchViewModel localMusicSearchViewModel) {
        this.mViewModel = localMusicSearchViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0045  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            r15 = this;
            monitor-enter(r15)
            long r0 = r15.mDirtyFlags     // Catch: java.lang.Throwable -> L62
            r2 = 0
            r15.mDirtyFlags = r2     // Catch: java.lang.Throwable -> L62
            monitor-exit(r15)     // Catch: java.lang.Throwable -> L62
            com.byd.music.beans.AlbumBean r4 = r15.mLocalSearchAlbumBean
            com.byd.music.fragment.localMusic.search.LocalMusicSearchViewModel r5 = r15.mViewModel
            r6 = 7
            long r6 = r6 & r0
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            r7 = 5
            r9 = 0
            r10 = 0
            if (r6 == 0) goto L48
            if (r4 == 0) goto L1e
            java.lang.String r11 = r4.getName()
            goto L1f
        L1e:
            r11 = r10
        L1f:
            if (r5 == 0) goto L26
            java.lang.String r12 = r5.curKeyWord
            com.byd.music.utils.binding.BindingCommand<com.byd.music.beans.AlbumBean> r5 = r5.listAlbumLocalSearchClick
            goto L28
        L26:
            r5 = r10
            r12 = r5
        L28:
            long r13 = r0 & r7
            int r13 = (r13 > r2 ? 1 : (r13 == r2 ? 0 : -1))
            if (r13 == 0) goto L45
            if (r4 == 0) goto L35
            java.util.List r13 = r4.getmAllMusics()
            goto L36
        L35:
            r13 = r10
        L36:
            if (r13 == 0) goto L3e
            java.lang.Object r10 = r13.get(r9)
            com.byd.music.beans.LocalMusicBean r10 = (com.byd.music.beans.LocalMusicBean) r10
        L3e:
            if (r10 == 0) goto L45
            long r13 = r10.getAlbumId()
            goto L46
        L45:
            r13 = r2
        L46:
            r10 = r5
            goto L4b
        L48:
            r13 = r2
            r11 = r10
            r12 = r11
        L4b:
            if (r6 == 0) goto L57
            androidx.constraintlayout.widget.ConstraintLayout r5 = r15.mboundView0
            com.byd.music.utils.binding.BindingUtils.onClickCommand(r5, r10, r9, r4, r9)
            android.widget.TextView r4 = r15.tvAlbumName
            com.byd.music.utils.binding.BindingUtils.setKeyWordColor(r4, r11, r12)
        L57:
            long r0 = r0 & r7
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L61
            com.google.android.material.imageview.ShapeableImageView r0 = r15.mboundView1
            com.byd.music.utils.binding.BindingUtils.setImageBitmapFromId(r0, r13)
        L61:
            return
        L62:
            r0 = move-exception
            monitor-exit(r15)     // Catch: java.lang.Throwable -> L62
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.databinding.ItemArtistSearchBindingSw580dpImpl.executeBindings():void");
    }
}
