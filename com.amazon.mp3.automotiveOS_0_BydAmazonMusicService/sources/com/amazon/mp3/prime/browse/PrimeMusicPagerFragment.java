package com.amazon.mp3.prime.browse;

import android.view.View;
import com.amazon.mp3.library.adapter.LibraryPagerAdapter;

/* JADX INFO: loaded from: classes4.dex */
public interface PrimeMusicPagerFragment {
    void scrollToTop();

    void setOnListViewCreatedListener(LibraryPagerAdapter.OnListViewCreatedListener listener);

    void setupTouchListener(View.OnTouchListener listener);
}
