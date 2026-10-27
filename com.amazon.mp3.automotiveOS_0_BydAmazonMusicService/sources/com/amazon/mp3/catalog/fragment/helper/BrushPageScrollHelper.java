package com.amazon.mp3.catalog.fragment.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.util.Pair;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.catalog.fragment.BrushFragment;

/* JADX INFO: loaded from: classes3.dex */
public class BrushPageScrollHelper {
    private static final String TAG = "BrushPageScrollHelper";
    private Context context;
    private String pageOffsetKey;
    private String pagePositionKey;

    public BrushPageScrollHelper(Context context, @NonNull String uri) {
        StringBuilder sb = new StringBuilder();
        String str = TAG;
        sb.append(str);
        sb.append("_scroll_position");
        this.pagePositionKey = sb.toString();
        this.pageOffsetKey = str + "_scroll_top_offset";
        this.context = context;
        this.pagePositionKey = uri + this.pagePositionKey;
        this.pageOffsetKey = uri + this.pageOffsetKey;
    }

    public void saveCurrentScrollPostition(int scrollPosition, int topScrollOffSet) {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(BrushFragment.class.getSimpleName(), 0);
        sharedPreferences.edit().putInt(this.pagePositionKey, scrollPosition).apply();
        sharedPreferences.edit().putInt(this.pageOffsetKey, topScrollOffSet).apply();
    }

    public void reset() {
        saveCurrentScrollPostition(0, 0);
    }

    public Pair<Integer, Integer> getLastScrollPosition() {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(BrushFragment.class.getSimpleName(), 0);
        return new Pair<>(Integer.valueOf(sharedPreferences.getInt(this.pagePositionKey, 0)), Integer.valueOf(sharedPreferences.getInt(this.pageOffsetKey, 0)));
    }

    public static int getTopScrollOffset(RecyclerView recyclerView, int index) {
        View childAt;
        if (recyclerView == null || (childAt = recyclerView.getChildAt(index)) == null) {
            return 0;
        }
        Rect rect = new Rect();
        int top = childAt.getTop();
        RecyclerView.State state = new RecyclerView.State();
        int iMax = 0;
        for (int i = 0; i < recyclerView.getItemDecorationCount(); i++) {
            recyclerView.getItemDecorationAt(i).getItemOffsets(rect, childAt, recyclerView, state);
            iMax = Math.max(iMax, rect.top);
            rect.top = 0;
        }
        return top - iMax;
    }
}
