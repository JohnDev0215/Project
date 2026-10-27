package com.byd.music.view.commonAdapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.flexbox.FlexboxItemDecoration;

/* JADX INFO: loaded from: classes5.dex */
public class SearchTagItemDecoration extends FlexboxItemDecoration {
    private int horSpace;
    private int verSpace;

    public SearchTagItemDecoration(Context context, int i, int i2) {
        super(context);
        this.horSpace = i;
        this.verSpace = i2;
    }

    @Override // com.google.android.flexbox.FlexboxItemDecoration, androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.left = this.horSpace;
        rect.right = this.horSpace;
        rect.bottom = this.verSpace;
        rect.top = this.verSpace;
    }
}
