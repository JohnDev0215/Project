package androidx.leanback.widget;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.leanback.R;
import androidx.leanback.widget.RowPresenter;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractMediaListHeaderPresenter extends RowPresenter {
    private int mBackgroundColor;
    private boolean mBackgroundColorSet;
    private final Context mContext;

    protected abstract void onBindMediaListHeaderViewHolder(ViewHolder viewHolder, Object obj);

    public static class ViewHolder extends RowPresenter.ViewHolder {
        private final TextView mHeaderView;

        public ViewHolder(View view) {
            super(view);
            this.mHeaderView = (TextView) view.findViewById(R.id.mediaListHeader);
        }

        public TextView getHeaderView() {
            return this.mHeaderView;
        }
    }

    public AbstractMediaListHeaderPresenter(Context context, int mThemeResId) {
        this.mBackgroundColor = 0;
        this.mContext = new ContextThemeWrapper(context.getApplicationContext(), mThemeResId);
        setHeaderPresenter(null);
    }

    public AbstractMediaListHeaderPresenter() {
        this.mBackgroundColor = 0;
        this.mContext = null;
        setHeaderPresenter(null);
    }

    @Override // androidx.leanback.widget.RowPresenter
    public boolean isUsingDefaultSelectEffect() {
        return false;
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected RowPresenter.ViewHolder createRowViewHolder(ViewGroup parent) {
        Context context = this.mContext;
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.lb_media_list_header, parent, false);
        view.setFocusable(false);
        view.setFocusableInTouchMode(false);
        ViewHolder vh = new ViewHolder(view);
        if (this.mBackgroundColorSet) {
            vh.view.setBackgroundColor(this.mBackgroundColor);
        }
        return vh;
    }

    @Override // androidx.leanback.widget.RowPresenter
    protected void onBindRowViewHolder(RowPresenter.ViewHolder vh, Object item) {
        super.onBindRowViewHolder(vh, item);
        onBindMediaListHeaderViewHolder((ViewHolder) vh, item);
    }

    public void setBackgroundColor(int color) {
        this.mBackgroundColorSet = true;
        this.mBackgroundColor = color;
    }
}
