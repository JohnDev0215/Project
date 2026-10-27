package androidx.leanback.widget;

import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.leanback.R;
import androidx.leanback.widget.Presenter;

/* JADX INFO: loaded from: classes3.dex */
public class RowHeaderPresenter extends Presenter {
    private final boolean mAnimateSelect;
    private final Paint mFontMeasurePaint;
    private final int mLayoutResourceId;
    private boolean mNullItemVisibilityGone;

    public RowHeaderPresenter() {
        this(R.layout.lb_row_header);
    }

    public RowHeaderPresenter(int layoutResourceId) {
        this(layoutResourceId, true);
    }

    public RowHeaderPresenter(int layoutResourceId, boolean animateSelect) {
        this.mFontMeasurePaint = new Paint(1);
        this.mLayoutResourceId = layoutResourceId;
        this.mAnimateSelect = animateSelect;
    }

    public void setNullItemVisibilityGone(boolean nullItemVisibilityGone) {
        this.mNullItemVisibilityGone = nullItemVisibilityGone;
    }

    public boolean isNullItemVisibilityGone() {
        return this.mNullItemVisibilityGone;
    }

    public static class ViewHolder extends Presenter.ViewHolder {
        TextView mDescriptionView;
        int mOriginalTextColor;
        float mSelectLevel;
        RowHeaderView mTitleView;
        float mUnselectAlpha;

        public ViewHolder(View view) {
            super(view);
            this.mTitleView = (RowHeaderView) view.findViewById(R.id.row_header);
            this.mDescriptionView = (TextView) view.findViewById(R.id.row_header_description);
            initColors();
        }

        public ViewHolder(RowHeaderView view) {
            super(view);
            this.mTitleView = view;
            initColors();
        }

        void initColors() {
            RowHeaderView rowHeaderView = this.mTitleView;
            if (rowHeaderView != null) {
                this.mOriginalTextColor = rowHeaderView.getCurrentTextColor();
            }
            this.mUnselectAlpha = this.view.getResources().getFraction(R.fraction.lb_browse_header_unselect_alpha, 1, 1);
        }

        public final float getSelectLevel() {
            return this.mSelectLevel;
        }
    }

    @Override // androidx.leanback.widget.Presenter
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View root = LayoutInflater.from(parent.getContext()).inflate(this.mLayoutResourceId, parent, false);
        ViewHolder viewHolder = new ViewHolder(root);
        if (this.mAnimateSelect) {
            setSelectLevel(viewHolder, 0.0f);
        }
        return viewHolder;
    }

    @Override // androidx.leanback.widget.Presenter
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        HeaderItem headerItem = item == null ? null : ((Row) item).getHeaderItem();
        ViewHolder vh = (ViewHolder) viewHolder;
        if (headerItem == null) {
            if (vh.mTitleView != null) {
                vh.mTitleView.setText((CharSequence) null);
            }
            if (vh.mDescriptionView != null) {
                vh.mDescriptionView.setText((CharSequence) null);
            }
            viewHolder.view.setContentDescription(null);
            if (this.mNullItemVisibilityGone) {
                viewHolder.view.setVisibility(8);
                return;
            }
            return;
        }
        if (vh.mTitleView != null) {
            vh.mTitleView.setText(headerItem.getName());
        }
        if (vh.mDescriptionView != null) {
            if (TextUtils.isEmpty(headerItem.getDescription())) {
                vh.mDescriptionView.setVisibility(8);
            } else {
                vh.mDescriptionView.setVisibility(0);
            }
            vh.mDescriptionView.setText(headerItem.getDescription());
        }
        viewHolder.view.setContentDescription(headerItem.getContentDescription());
        viewHolder.view.setVisibility(0);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        ViewHolder vh = (ViewHolder) viewHolder;
        if (vh.mTitleView != null) {
            vh.mTitleView.setText((CharSequence) null);
        }
        if (vh.mDescriptionView != null) {
            vh.mDescriptionView.setText((CharSequence) null);
        }
        if (this.mAnimateSelect) {
            setSelectLevel((ViewHolder) viewHolder, 0.0f);
        }
    }

    public final void setSelectLevel(ViewHolder holder, float selectLevel) {
        holder.mSelectLevel = selectLevel;
        onSelectLevelChanged(holder);
    }

    protected void onSelectLevelChanged(ViewHolder holder) {
        if (this.mAnimateSelect) {
            holder.view.setAlpha(holder.mUnselectAlpha + (holder.mSelectLevel * (1.0f - holder.mUnselectAlpha)));
        }
    }

    public int getSpaceUnderBaseline(ViewHolder holder) {
        int space = holder.view.getPaddingBottom();
        if (holder.view instanceof TextView) {
            return space + ((int) getFontDescent((TextView) holder.view, this.mFontMeasurePaint));
        }
        return space;
    }

    protected static float getFontDescent(TextView textView, Paint fontMeasurePaint) {
        if (fontMeasurePaint.getTextSize() != textView.getTextSize()) {
            fontMeasurePaint.setTextSize(textView.getTextSize());
        }
        if (fontMeasurePaint.getTypeface() != textView.getTypeface()) {
            fontMeasurePaint.setTypeface(textView.getTypeface());
        }
        return fontMeasurePaint.descent();
    }
}
