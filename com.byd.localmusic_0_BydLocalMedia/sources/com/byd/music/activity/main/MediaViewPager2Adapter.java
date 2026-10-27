package com.byd.music.activity.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.byd.music.R;
import com.byd.music.fragment.bluetooth.BlueToothFragment;
import com.byd.music.fragment.localMusic.ui.LocalMusicFragment;
import com.byd.music.fragment.localMusic.ui.LocalSDFragment;
import com.byd.music.fragment.localMusic.ui.LocalUsbFragment;
import com.byd.music.fragment.radio.HolderFragment;
import com.byd.music.utils.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class MediaViewPager2Adapter extends FragmentStateAdapter {
    private static final String TAG = "MediaViewPager2Adapter";
    private List<Integer> titleList;

    public MediaViewPager2Adapter(FragmentActivity fragmentActivity, List<Integer> list) {
        super(fragmentActivity);
        this.titleList = new ArrayList();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            this.titleList.add(it.next());
        }
        LogUtil.d(TAG, "MediaViewPager2Adapter init list: " + list.size());
    }

    public Fragment getTargetPositionFragment(int i) {
        Integer num = this.titleList.get(i);
        if (num.intValue() == R.string.module_name_fm) {
            return new HolderFragment(0);
        }
        if (num.intValue() == R.string.module_name_am) {
            return new HolderFragment(1);
        }
        if (num.intValue() == R.string.module_name_dab) {
            return new HolderFragment(2);
        }
        if (num.intValue() == R.string.bluetooth_music) {
            return new BlueToothFragment();
        }
        if (num.intValue() == R.string.local_music) {
            return new LocalMusicFragment();
        }
        if (num.intValue() == R.string.local_storage_sdcard) {
            return new LocalSDFragment();
        }
        if (num.intValue() == R.string.local_storage_usb) {
            return new LocalUsbFragment();
        }
        return new LocalMusicFragment();
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    public Fragment createFragment(int i) {
        Fragment targetPositionFragment = getTargetPositionFragment(i);
        LogUtil.e(TAG, "createFragment return class = " + targetPositionFragment.getClass() + " for position = " + i);
        return targetPositionFragment;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return getSize();
    }

    private int getSize() {
        LogUtil.d(TAG, "getSize:" + this.titleList.size());
        return this.titleList.size();
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        LogUtil.d(TAG, "getItemId position = " + i + ",this:" + this);
        return i;
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    public boolean containsItem(long j) {
        return j >= 0 && j < ((long) getItemCount());
    }
}
