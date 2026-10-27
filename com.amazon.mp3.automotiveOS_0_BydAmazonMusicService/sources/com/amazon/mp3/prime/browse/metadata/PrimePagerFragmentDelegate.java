package com.amazon.mp3.prime.browse.metadata;

import android.view.View;
import android.widget.ScrollView;
import com.amazon.mp3.library.adapter.LibraryPagerAdapter;
import com.amazon.mp3.prime.browse.PrimeMusicPagerFragment;

/* JADX INFO: loaded from: classes4.dex */
public class PrimePagerFragmentDelegate implements PrimeMusicPagerFragment {
    private ScrollView mListView;
    private LibraryPagerAdapter.OnListViewCreatedListener mListViewCreatedListener;

    @Override // com.amazon.mp3.prime.browse.PrimeMusicPagerFragment
    public void setOnListViewCreatedListener(LibraryPagerAdapter.OnListViewCreatedListener listener) {
        this.mListViewCreatedListener = listener;
    }

    public void setListView(ScrollView listView) {
        this.mListView = listView;
    }

    @Override // com.amazon.mp3.prime.browse.PrimeMusicPagerFragment
    public void scrollToTop() {
        ScrollView scrollView = this.mListView;
        if (scrollView != null) {
            scrollView.smoothScrollTo(0, 0);
        }
    }

    @Override // com.amazon.mp3.prime.browse.PrimeMusicPagerFragment
    public void setupTouchListener(View.OnTouchListener listener) {
        ScrollView scrollView = this.mListView;
        if (scrollView != null) {
            scrollView.setOnTouchListener(listener);
        }
    }

    public void onListViewCreated() {
        LibraryPagerAdapter.OnListViewCreatedListener onListViewCreatedListener = this.mListViewCreatedListener;
        if (onListViewCreatedListener != null) {
            onListViewCreatedListener.onListViewCreated(this.mListView);
        }
    }
}
