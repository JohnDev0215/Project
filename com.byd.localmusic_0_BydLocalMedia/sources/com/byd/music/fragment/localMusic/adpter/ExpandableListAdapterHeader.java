package com.byd.music.fragment.localMusic.adpter;

import android.widget.ExpandableListView;
import com.byd.music.view.HeaderExpandListview;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
abstract class ExpandableListAdapterHeader<T> extends ExpandableListAdapter<T> implements HeaderExpandListview.HeaderAdapter {
    public ExpandableListAdapterHeader(List<T> list, ExpandableListView expandableListView) {
        super(list, expandableListView);
    }
}
