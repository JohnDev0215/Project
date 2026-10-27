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
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import com.byd.music.fragment.localMusic.ui.LocalMusicViewModel;
import com.byd.music.utils.binding.BindingCommand;
import com.byd.music.utils.binding.BindingUtils;
import com.byd.music.view.CollectLottieView;
import com.byd.music.view.musicPlayingSlideView.PlayingSlideAnimView;

/* JADX INFO: loaded from: classes5.dex */
public class ItemMusiclistLocalMusicBindingImpl extends ItemMusiclistLocalMusicBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.view2, 9);
    }

    public ItemMusiclistLocalMusicBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 10, sIncludes, sViewsWithIds));
    }

    private ItemMusiclistLocalMusicBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
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
        if (BR.viewModel == i) {
            setViewModel((LocalMusicViewModel) obj);
        } else {
            if (BR.localSdUsbMusicBean != i) {
                return false;
            }
            setLocalSdUsbMusicBean((LocalMusicBean) obj);
        }
        return true;
    }

    @Override // com.byd.music.databinding.ItemMusiclistLocalMusicBinding
    public void setViewModel(LocalMusicViewModel localMusicViewModel) {
        this.mViewModel = localMusicViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // com.byd.music.databinding.ItemMusiclistLocalMusicBinding
    public void setLocalSdUsbMusicBean(LocalMusicBean localMusicBean) {
        this.mLocalSdUsbMusicBean = localMusicBean;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(BR.localSdUsbMusicBean);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeViewModelLocalSdUsbMusicBeansList((ObservableList) obj, i2);
        }
        if (i == 1) {
            return onChangeViewModelIsStartAnimationLocalSdUsbMusicBean((ObservableBoolean) obj, i2);
        }
        if (i == 2) {
            return onChangeMusicPlayHelperGetInstanceCurrentLocalMusicMaps((ObservableMap) obj, i2);
        }
        if (i == 3) {
            return onChangeMusicPlayHelperGetInstanceIsPlayingMaps((ObservableMap) obj, i2);
        }
        if (i == 4) {
            return onChangeViewModelIsShowOperateUi((ObservableBoolean) obj, i2);
        }
        if (i != 5) {
            return false;
        }
        return onChangeViewModelLocalSdUsbMusicDelList((ObservableList) obj, i2);
    }

    private boolean onChangeViewModelLocalSdUsbMusicBeansList(ObservableList<LocalMusicBean> observableList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelIsStartAnimationLocalSdUsbMusicBean(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeMusicPlayHelperGetInstanceCurrentLocalMusicMaps(ObservableMap<Integer, LocalMusicBean> observableMap, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeMusicPlayHelperGetInstanceIsPlayingMaps(ObservableMap<Integer, Boolean> observableMap, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelIsShowOperateUi(ObservableBoolean observableBoolean, int i) {
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

    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v8 */
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
    protected void executeBindings() {
        long j;
        boolean zSafeUnbox;
        LocalMusicBean localMusicBean;
        ObservableList observableList;
        boolean z;
        BindingCommand<LocalMusicBean> bindingCommand;
        BindingCommand<LocalMusicBean> bindingCommand2;
        BindingCommand<LocalMusicBean> bindingCommand3;
        BindingCommand<LocalMusicBean> bindingCommand4;
        ObservableList observableList2;
        BindingCommand<LocalMusicBean> bindingCommand5;
        boolean z2;
        boolean z3;
        boolean z4;
        long j2;
        boolean zIsPlayIngMusic;
        boolean zContains;
        ObservableBoolean observableBooleanIsStartAnimation;
        boolean z5;
        String str;
        String str2;
        int i;
        String str3;
        boolean zIsCollect;
        boolean z6;
        int i2;
        int i3;
        boolean z7;
        ?? r9;
        String artist;
        String title;
        String strValueOf;
        boolean z8;
        int i4;
        ObservableBoolean observableBoolean;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        LocalMusicViewModel localMusicViewModel = this.mViewModel;
        LocalMusicBean localMusicBean2 = this.mLocalSdUsbMusicBean;
        if ((j & 460) != 0) {
            ObservableMap observableMap = MusicPlayHelper.getInstance().currentLocalMusicMaps;
            ObservableMap isPlayingMaps = MusicPlayHelper.getInstance().getIsPlayingMaps();
            updateRegistration(2, observableMap);
            updateRegistration(3, isPlayingMaps);
            localMusicBean = observableMap != null ? observableMap.get(0) : null;
            zSafeUnbox = ViewDataBinding.safeUnbox(isPlayingMaps != null ? isPlayingMaps.get(0) : null);
        } else {
            zSafeUnbox = false;
            localMusicBean = null;
        }
        if ((511 & j) != 0) {
            if ((j & 449) != 0) {
                observableList2 = localMusicViewModel != null ? localMusicViewModel.localSdUsbMusicBeansList : null;
                updateRegistration(0, observableList2);
            } else {
                observableList2 = null;
            }
            if ((j & 448) == 0 || localMusicViewModel == null) {
                bindingCommand = null;
                bindingCommand2 = null;
                bindingCommand3 = null;
                bindingCommand4 = null;
                bindingCommand5 = null;
            } else {
                bindingCommand3 = localMusicViewModel.listMusicLocalSdUsbClick;
                bindingCommand4 = localMusicViewModel.itemSelectDelCmd;
                bindingCommand = localMusicViewModel.collectCliclCmd;
                bindingCommand2 = localMusicViewModel.listMusicLocalSdUsbLongClick;
                bindingCommand5 = localMusicViewModel.itemDelCmd;
            }
            if ((j & 336) != 0) {
                if (localMusicViewModel != null) {
                    observableBoolean = localMusicViewModel.isShowOperateUi;
                    i4 = 4;
                } else {
                    i4 = 4;
                    observableBoolean = null;
                }
                updateRegistration(i4, observableBoolean);
                z = observableBoolean != null ? observableBoolean.get() : false;
                z2 = !z;
            } else {
                z = false;
                z2 = false;
            }
            if ((j & 480) != 0) {
                if (localMusicViewModel != null) {
                    observableList = localMusicViewModel.localSdUsbMusicDelList;
                    z8 = z;
                } else {
                    z8 = z;
                    observableList = null;
                }
                updateRegistration(5, observableList);
                z = z8;
            } else {
                observableList = null;
            }
        } else {
            observableList = null;
            z = false;
            bindingCommand = null;
            bindingCommand2 = null;
            bindingCommand3 = null;
            bindingCommand4 = null;
            observableList2 = null;
            bindingCommand5 = null;
            z2 = false;
        }
        if ((j & 495) != 0) {
            long j3 = j & 384;
            if (j3 != 0) {
                if (localMusicBean2 != null) {
                    zIsCollect = localMusicBean2.isCollect();
                    artist = localMusicBean2.getArtist();
                    title = localMusicBean2.getTitle();
                } else {
                    zIsCollect = false;
                    artist = null;
                    title = null;
                }
                if (j3 != 0) {
                    j = zIsCollect ? j | 1024 : j | 512;
                }
            } else {
                zIsCollect = false;
                artist = null;
                title = null;
            }
            boolean zEquals = ((j & 388) == 0 || localMusicBean2 == null) ? false : localMusicBean2.equals(localMusicBean);
            z4 = zSafeUnbox;
            if ((j & 450) != 0) {
                if (localMusicViewModel != null) {
                    z3 = z;
                    observableBooleanIsStartAnimation = localMusicViewModel.isStartAnimation(localMusicBean2);
                } else {
                    z3 = z;
                    observableBooleanIsStartAnimation = null;
                }
                updateRegistration(1, observableBooleanIsStartAnimation);
                if (observableBooleanIsStartAnimation != null) {
                    observableBooleanIsStartAnimation.get();
                }
            } else {
                z3 = z;
                observableBooleanIsStartAnimation = null;
            }
            if ((j & 449) != 0) {
                strValueOf = String.valueOf((observableList2 != null ? observableList2.indexOf(localMusicBean2) : 0) + 1);
            } else {
                strValueOf = null;
            }
            long j4 = j & 480;
            if (j4 != 0) {
                zContains = observableList != null ? observableList.contains(localMusicBean2) : false;
                if (j4 != 0) {
                    j = zContains ? j | 16384 : j | 8192;
                }
            } else {
                zContains = false;
            }
            j2 = 0;
            if ((j & 460) != 0) {
                zIsPlayIngMusic = localMusicViewModel != null ? localMusicViewModel.isPlayIngMusic(localMusicBean2, localMusicBean) : false;
                if ((j & 452) != 0) {
                    j |= zIsPlayIngMusic ? 4096L : 2048L;
                }
                if ((j & 452) != 0) {
                    z5 = zEquals;
                    i = zIsPlayIngMusic ? 4 : 0;
                    str2 = artist;
                    str3 = strValueOf;
                    str = title;
                } else {
                    str3 = strValueOf;
                    z5 = zEquals;
                    str2 = artist;
                    str = title;
                }
            } else {
                str3 = strValueOf;
                z5 = zEquals;
                str2 = artist;
                str = title;
                zIsPlayIngMusic = false;
            }
            i = 0;
        } else {
            z3 = z;
            z4 = zSafeUnbox;
            j2 = 0;
            zIsPlayIngMusic = false;
            zContains = false;
            observableBooleanIsStartAnimation = null;
            z5 = false;
            str = null;
            str2 = null;
            i = 0;
            str3 = null;
            zIsCollect = false;
        }
        int i5 = (j & 512) != j2 ? R.drawable.icon_dark_media_playcollection_s_48 : 0;
        int i6 = (j & 1024) != j2 ? R.drawable.icon_dark_media_playcollection_s_sel_48 : 0;
        int i7 = (j & 8192) != j2 ? R.drawable.multi_nromal_regular_s : 0;
        int i8 = (j & 16384) != j2 ? R.drawable.multi_pitch_regular_s : 0;
        long j5 = j & 384;
        if (j5 != j2) {
            if (zIsCollect) {
                i5 = i6;
            }
            z6 = zIsPlayIngMusic;
            i2 = i5;
        } else {
            z6 = zIsPlayIngMusic;
            i2 = 0;
        }
        long j6 = j & 480;
        if (j6 != j2) {
            if (zContains) {
                i7 = i8;
            }
            i3 = i7;
        } else {
            i3 = 0;
        }
        if ((j & 450) != j2) {
            z7 = z5;
            BindingUtils.setImageViewCollectAnimation(this.collectLottiesView, observableBooleanIsStartAnimation);
        } else {
            z7 = z5;
        }
        if ((j & 448) != j2) {
            r9 = 0;
            BindingUtils.onClickCommand(this.ivDelete, bindingCommand5, false, localMusicBean2, false);
            BindingUtils.onClickCommand(this.ivLike, bindingCommand, false, localMusicBean2, false);
            BindingUtils.onClickCommand(this.ivMultiSelect, bindingCommand4, false, localMusicBean2, false);
            BindingUtils.onClickCommand(this.mboundView0, bindingCommand3, false, localMusicBean2, false);
            BindingUtils.onLongClickCommand(this.mboundView0, bindingCommand2, localMusicBean2);
        } else {
            r9 = 0;
        }
        if ((336 & j) != 0) {
            BindingUtils.setVisiable(this.ivDelete, z2, r9, r9, r9);
            BindingUtils.setVisiable(this.ivLike, z2, r9, r9, r9);
            BindingUtils.setVisiable(this.ivMultiSelect, z3, r9, r9, r9);
        }
        if (j5 != j2) {
            BindingUtils.setImageRes(this.ivLike, i2);
            String str4 = (String) null;
            BindingUtils.setKeyWordColor(this.tvAnnouncer, str2, str4);
            BindingUtils.setKeyWordColor(this.tvSongName, str, str4);
        }
        if (j6 != j2) {
            BindingUtils.setImageRes(this.ivMultiSelect, i3);
        }
        if ((j & 388) != 0) {
            BindingUtils.setSelected(this.mboundView0, z7);
        }
        if ((460 & j) != 0) {
            BindingUtils.setMusicSlidePlaying(this.playSlideView, z4, z6);
        }
        if ((j & 449) != 0) {
            TextViewBindingAdapter.setText(this.tvIndex, str3);
        }
        if ((j & 452) != 0) {
            this.tvIndex.setVisibility(i);
        }
    }
}
