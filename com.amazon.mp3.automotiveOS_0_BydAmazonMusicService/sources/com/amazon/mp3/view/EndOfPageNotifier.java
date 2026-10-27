package com.amazon.mp3.view;

import android.widget.AbsListView;
import com.amazon.mp3.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public class EndOfPageNotifier implements AbsListView.OnScrollListener {
    private static final String TAG = EndOfPageNotifier.class.getSimpleName();
    private int mNLastItems;
    private Collection<EndOfPageListener> mEndOfPageListeners = new ArrayList();
    private boolean mWasLastScrollPastTrigger = false;
    private int mScrollTrigger = 5;

    public interface EndOfPageListener {
        void onEndOfPage();
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {
    }

    public void addEndOfPageListener(EndOfPageListener endOfPageListener) {
        if (this.mEndOfPageListeners.contains(endOfPageListener)) {
            return;
        }
        this.mEndOfPageListeners.add(endOfPageListener);
    }

    public void removeEndOfPageListener(EndOfPageListener scrollDownListener) {
        this.mEndOfPageListeners.remove(scrollDownListener);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(final AbsListView view, final int scrollState) {
        boolean z = true;
        int count = view.getCount() - (view.getLastVisiblePosition() + 1);
        int i = this.mScrollTrigger;
        if (count > i && i <= view.getCount()) {
            z = false;
        }
        if ((!this.mWasLastScrollPastTrigger || this.mNLastItems != count) && z) {
            notifyListeners();
        }
        this.mNLastItems = count;
        this.mWasLastScrollPastTrigger = z;
    }

    private void notifyListeners() {
        Log.debug(TAG, "EndOfPage triggered");
        Iterator<EndOfPageListener> it = this.mEndOfPageListeners.iterator();
        while (it.hasNext()) {
            it.next().onEndOfPage();
        }
    }
}
