package com.byd.music.fragment.localMusic;

import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.base.BaseFragment;
import com.byd.music.databinding.FragmentLocalMusiclistBinding;
import com.byd.music.utils.LogUtil;

/* JADX INFO: loaded from: classes5.dex */
public class LocalMusicListFragment extends BaseFragment<FragmentLocalMusiclistBinding, LocalMusicListViewModel> {
    private LocalItemClik localItemClik;

    interface LocalItemClik {
        void localMusicItemLongCanclClick();

        void localMusicItemLongClick();
    }

    @Override // com.byd.music.base.BaseFragment
    protected boolean disableBackground() {
        return false;
    }

    @Override // com.byd.music.base.BaseFragment
    protected void initData() {
    }

    @Override // com.byd.music.base.BaseFragment
    public boolean onBack() {
        return false;
    }

    @Override // com.byd.music.base.BaseFragment
    protected void onLazyInit() {
    }

    LocalMusicListFragment() {
    }

    public LocalMusicListFragment(LocalItemClik localItemClik) {
        this.localItemClik = localItemClik;
    }

    @Override // com.byd.music.base.BaseFragment
    protected int layoutId() {
        return R.layout.fragment_local_musiclist;
    }

    @Override // com.byd.music.base.BaseFragment
    protected int viewModelId() {
        return BR.viewModel;
    }

    @Override // com.byd.music.base.BaseFragment
    protected void initView() {
        ((TextView) ((FragmentLocalMusiclistBinding) this.dataBinding).loadingView.findViewById(R.id.tv_load)).setText(R.string.scanning);
        setLoadingView(true);
        ((LocalMusicListViewModel) this.viewModel).getLoadingEvent().observe(this, new Observer<Boolean>() { // from class: com.byd.music.fragment.localMusic.LocalMusicListFragment.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(Boolean bool) {
                LogUtil.d("Alvin", "onChanged: " + bool);
                LocalMusicListFragment.this.setLoadingView(bool.booleanValue());
                if (bool.booleanValue()) {
                    return;
                }
                ((LocalMusicListViewModel) LocalMusicListFragment.this.viewModel).addListMusic();
            }
        });
        ((LocalMusicListViewModel) this.viewModel).longClickEvent.observe(this, new Observer() { // from class: com.byd.music.fragment.localMusic.LocalMusicListFragment.2
            @Override // androidx.lifecycle.Observer
            public void onChanged(Object obj) {
                LocalMusicListFragment.this.localItemClik.localMusicItemLongClick();
                ((FragmentLocalMusiclistBinding) LocalMusicListFragment.this.dataBinding).clOperate.setVisibility(0);
            }
        });
    }
}
