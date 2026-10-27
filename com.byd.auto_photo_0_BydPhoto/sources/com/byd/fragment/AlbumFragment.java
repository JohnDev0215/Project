package com.byd.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import b.a.e.b;
import com.byd.auto_photo.MainActivity;
import com.byd.auto_photo.R;
import com.byd.util.w;

/* JADX INFO: loaded from: classes2.dex */
public class AlbumFragment extends AlbumBaseFragment implements MainActivity.c {
    @Override // com.byd.fragment.AlbumBaseFragment
    protected void configNewAlbumDialog(NewFolderOrRenameFragment newFolderOrRenameFragment) {
        newFolderOrRenameFragment.setSoftInputMode(35);
    }

    @Override // com.byd.fragment.AlbumBaseFragment
    protected void createChildMediaGridFragment() {
        MediaFileGridFragment mediaFileGridFragment = new MediaFileGridFragment();
        this.mediaFileGridFragment = mediaFileGridFragment;
        mediaFileGridFragment.setHeaderMode(2);
    }

    @Override // com.byd.fragment.AlbumBaseFragment
    protected int getAlbumShowGridWidth() {
        Resources resources = getActivity().getResources();
        int i = resources.getConfiguration().orientation;
        int mainContainerWidth = getMainContainerWidth();
        if (i == 2) {
            mainContainerWidth -= resources.getDimensionPixelSize(R.dimen.tab_size);
        }
        return mainContainerWidth - (resources.getDimensionPixelSize(R.dimen.content_padding) * 2);
    }

    @Override // com.byd.fragment.AlbumBaseFragment
    protected int getItemHeight() {
        return getResources().getDimensionPixelSize(R.dimen.dp_115_5);
    }

    @Override // com.byd.ui.a
    protected int getLayout() {
        return R.layout.fragment_album_photo3;
    }

    @Override // com.byd.fragment.AlbumBaseFragment
    protected int getNumColumns(int i) {
        return i == 2 ? 6 : 4;
    }

    @Override // com.byd.fragment.AlbumBaseFragment, com.byd.fragment.SubBaseFragment, com.byd.ui.a, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ((MainActivity) getActivity()).g1(this);
    }

    @Override // com.byd.fragment.AlbumBaseFragment, com.byd.fragment.SubBaseFragment, com.byd.ui.a, androidx.fragment.app.Fragment
    public void onDestroy() {
        ((MainActivity) getActivity()).k1(this);
        super.onDestroy();
    }

    @Override // com.byd.fragment.AlbumBaseFragment
    protected void onSelectSizeChange(int i) {
        super.onSelectSizeChange(i);
        this.searchAndSelectFragment.onSelectedSizeChange(i);
    }

    @Override // com.byd.auto_photo.MainActivity.c
    public void onThemeChange() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            w.c("AlbumFragment on theme change : activity is null!!");
            return;
        }
        b bVar = this.adapter;
        if (bVar != null) {
            bVar.s(activity);
        }
    }
}
