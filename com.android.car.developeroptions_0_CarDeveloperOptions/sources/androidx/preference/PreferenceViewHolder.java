package androidx.preference;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class PreferenceViewHolder extends RecyclerView.ViewHolder {
    private Drawable mBackground;
    private final SparseArray<View> mCachedViews;
    private boolean mDividerAllowedAbove;
    private boolean mDividerAllowedBelow;
    private ColorStateList mTitleTextColors;

    PreferenceViewHolder(View view) {
        super(view);
        this.mCachedViews = new SparseArray<>(4);
        TextView textView = (TextView) view.findViewById(R.id.title);
        this.mCachedViews.put(R.id.title, textView);
        this.mCachedViews.put(R.id.summary, view.findViewById(R.id.summary));
        this.mCachedViews.put(R.id.icon, view.findViewById(R.id.icon));
        SparseArray<View> sparseArray = this.mCachedViews;
        int i = R$id.icon_frame;
        sparseArray.put(i, view.findViewById(i));
        this.mCachedViews.put(R.id.icon_frame, view.findViewById(R.id.icon_frame));
        this.mBackground = view.getBackground();
        if (textView != null) {
            this.mTitleTextColors = textView.getTextColors();
        }
    }

    public View findViewById(int i) {
        View view = this.mCachedViews.get(i);
        if (view != null) {
            return view;
        }
        View viewFindViewById = this.itemView.findViewById(i);
        if (viewFindViewById != null) {
            this.mCachedViews.put(i, viewFindViewById);
        }
        return viewFindViewById;
    }

    public boolean isDividerAllowedAbove() {
        return this.mDividerAllowedAbove;
    }

    public void setDividerAllowedAbove(boolean z) {
        this.mDividerAllowedAbove = z;
    }

    public boolean isDividerAllowedBelow() {
        return this.mDividerAllowedBelow;
    }

    public void setDividerAllowedBelow(boolean z) {
        this.mDividerAllowedBelow = z;
    }

    void resetState() {
        Drawable background = this.itemView.getBackground();
        Drawable drawable = this.mBackground;
        if (background != drawable) {
            ViewCompat.setBackground(this.itemView, drawable);
        }
        TextView textView = (TextView) findViewById(R.id.title);
        if (textView == null || this.mTitleTextColors == null || textView.getTextColors().equals(this.mTitleTextColors)) {
            return;
        }
        textView.setTextColor(this.mTitleTextColors);
    }
}
