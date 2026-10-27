package com.byd.music.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableList;
import androidx.databinding.ObservableMap;
import androidx.databinding.ViewDataBinding;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.fragment.localMusic.ui.LocalSDViewModel;
import com.byd.music.view.CollectLottieView;
import com.byd.music.view.musicPlayingSlideView.PlayingSlideAnimView;

/* JADX INFO: loaded from: classes5.dex */
public class ItemCollectMusiclistLocalSdBindingImpl extends ItemCollectMusiclistLocalSdBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.view2, 9);
    }

    public ItemCollectMusiclistLocalSdBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 10, sIncludes, sViewsWithIds));
    }

    private ItemCollectMusiclistLocalSdBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 6, (CollectLottieView) objArr[6], (ImageView) objArr[7], (ImageView) objArr[5], (ImageView) objArr[8], (PlayingSlideAnimView) objArr[4], (TextView) objArr[3], (TextView) objArr[1], (TextView) objArr[2], (View) objArr[9]);
        this.mDirtyFlags = -1L;
        this.collectLottiesView.setTag(null);
        this.ivDelete.setTag(null);
        this.ivLike.setTag(null);
        this.ivMultiSelect.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.playSlideView.setTag(null);
        this.tvAnnouncer.setTag(null);
        this.tvIndex.setTag(null);
        this.tvSongName.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256L;
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
        if (BR.collectLocalSdUsbMusicBean == i) {
            setCollectLocalSdUsbMusicBean((LocalMusicBean) obj);
        } else {
            if (BR.viewModel != i) {
                return false;
            }
            setViewModel((LocalSDViewModel) obj);
        }
        return true;
    }

    @Override // com.byd.music.databinding.ItemCollectMusiclistLocalSdBinding
    public void setCollectLocalSdUsbMusicBean(LocalMusicBean localMusicBean) {
        this.mCollectLocalSdUsbMusicBean = localMusicBean;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.collectLocalSdUsbMusicBean);
        super.requestRebind();
    }

    @Override // com.byd.music.databinding.ItemCollectMusiclistLocalSdBinding
    public void setViewModel(LocalSDViewModel localSDViewModel) {
        this.mViewModel = localSDViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeViewModelCollectLocalSdUsbMusicBeansList((ObservableList) obj, i2);
        }
        if (i == 1) {
            return onChangeMusicPlayHelperGetInstanceCurrentLocalMusicMaps((ObservableMap) obj, i2);
        }
        if (i == 2) {
            return onChangeMusicPlayHelperGetInstanceIsPlayingMaps((ObservableMap) obj, i2);
        }
        if (i == 3) {
            return onChangeViewModelIsShowOperateUi((ObservableBoolean) obj, i2);
        }
        if (i == 4) {
            return onChangeViewModelIsStartAnimationCollectLocalSdUsbMusicBean((ObservableBoolean) obj, i2);
        }
        if (i != 5) {
            return false;
        }
        return onChangeViewModelLocalSdUsbMusicDelList((ObservableList) obj, i2);
    }

    private boolean onChangeViewModelCollectLocalSdUsbMusicBeansList(ObservableList<LocalMusicBean> observableList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeMusicPlayHelperGetInstanceCurrentLocalMusicMaps(ObservableMap<Integer, LocalMusicBean> observableMap, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeMusicPlayHelperGetInstanceIsPlayingMaps(ObservableMap<Integer, Boolean> observableMap, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelIsShowOperateUi(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelIsStartAnimationCollectLocalSdUsbMusicBean(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelLocalSdUsbMusicDelList(ObservableList<LocalMusicBean> observableList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x016a  */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v4, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 695
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.databinding.ItemCollectMusiclistLocalSdBindingImpl.executeBindings():void");
    }
}
