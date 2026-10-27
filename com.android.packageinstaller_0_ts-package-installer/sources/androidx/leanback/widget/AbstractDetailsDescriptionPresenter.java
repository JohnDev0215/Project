package androidx.leanback.widget;

import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.leanback.R;
import androidx.leanback.widget.Presenter;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractDetailsDescriptionPresenter extends Presenter {
    protected abstract void onBindDescription(ViewHolder viewHolder, Object obj);

    public static class ViewHolder extends Presenter.ViewHolder {
        final TextView mBody;
        final Paint.FontMetricsInt mBodyFontMetricsInt;
        final int mBodyLineSpacing;
        final int mBodyMaxLines;
        final int mBodyMinLines;
        private ViewTreeObserver.OnPreDrawListener mPreDrawListener;
        final TextView mSubtitle;
        final Paint.FontMetricsInt mSubtitleFontMetricsInt;
        final TextView mTitle;
        final Paint.FontMetricsInt mTitleFontMetricsInt;
        final int mTitleLineSpacing;
        final int mTitleMargin;
        final int mTitleMaxLines;
        final int mUnderSubtitleBaselineMargin;
        final int mUnderTitleBaselineMargin;

        public ViewHolder(View view) {
            super(view);
            this.mTitle = (TextView) view.findViewById(R.id.lb_details_description_title);
            this.mSubtitle = (TextView) view.findViewById(R.id.lb_details_description_subtitle);
            this.mBody = (TextView) view.findViewById(R.id.lb_details_description_body);
            Paint.FontMetricsInt titleFontMetricsInt = getFontMetricsInt(this.mTitle);
            int titleAscent = view.getResources().getDimensionPixelSize(R.dimen.lb_details_description_title_baseline);
            this.mTitleMargin = titleFontMetricsInt.ascent + titleAscent;
            this.mUnderTitleBaselineMargin = view.getResources().getDimensionPixelSize(R.dimen.lb_details_description_under_title_baseline_margin);
            this.mUnderSubtitleBaselineMargin = view.getResources().getDimensionPixelSize(R.dimen.lb_details_description_under_subtitle_baseline_margin);
            this.mTitleLineSpacing = view.getResources().getDimensionPixelSize(R.dimen.lb_details_description_title_line_spacing);
            this.mBodyLineSpacing = view.getResources().getDimensionPixelSize(R.dimen.lb_details_description_body_line_spacing);
            this.mBodyMaxLines = view.getResources().getInteger(R.integer.lb_details_description_body_max_lines);
            this.mBodyMinLines = view.getResources().getInteger(R.integer.lb_details_description_body_min_lines);
            this.mTitleMaxLines = this.mTitle.getMaxLines();
            this.mTitleFontMetricsInt = getFontMetricsInt(this.mTitle);
            this.mSubtitleFontMetricsInt = getFontMetricsInt(this.mSubtitle);
            this.mBodyFontMetricsInt = getFontMetricsInt(this.mBody);
            this.mTitle.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: androidx.leanback.widget.AbstractDetailsDescriptionPresenter.ViewHolder.1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    ViewHolder.this.addPreDrawListener();
                }
            });
        }

        void addPreDrawListener() {
            if (this.mPreDrawListener != null) {
                return;
            }
            this.mPreDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: androidx.leanback.widget.AbstractDetailsDescriptionPresenter.ViewHolder.2
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    if (ViewHolder.this.mSubtitle.getVisibility() == 0 && ViewHolder.this.mSubtitle.getTop() > ViewHolder.this.view.getHeight() && ViewHolder.this.mTitle.getLineCount() > 1) {
                        ViewHolder.this.mTitle.setMaxLines(ViewHolder.this.mTitle.getLineCount() - 1);
                        return false;
                    }
                    int titleLines = ViewHolder.this.mTitle.getLineCount();
                    ViewHolder viewHolder = ViewHolder.this;
                    int maxLines = titleLines > 1 ? viewHolder.mBodyMinLines : viewHolder.mBodyMaxLines;
                    if (ViewHolder.this.mBody.getMaxLines() != maxLines) {
                        ViewHolder.this.mBody.setMaxLines(maxLines);
                        return false;
                    }
                    ViewHolder.this.removePreDrawListener();
                    return true;
                }
            };
            this.view.getViewTreeObserver().addOnPreDrawListener(this.mPreDrawListener);
        }

        void removePreDrawListener() {
            if (this.mPreDrawListener != null) {
                this.view.getViewTreeObserver().removeOnPreDrawListener(this.mPreDrawListener);
                this.mPreDrawListener = null;
            }
        }

        public TextView getTitle() {
            return this.mTitle;
        }

        public TextView getSubtitle() {
            return this.mSubtitle;
        }

        public TextView getBody() {
            return this.mBody;
        }

        private Paint.FontMetricsInt getFontMetricsInt(TextView textView) {
            Paint paint = new Paint(1);
            paint.setTextSize(textView.getTextSize());
            paint.setTypeface(textView.getTypeface());
            return paint.getFontMetricsInt();
        }
    }

    @Override // androidx.leanback.widget.Presenter
    public final ViewHolder onCreateViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lb_details_description, parent, false);
        return new ViewHolder(v);
    }

    @Override // androidx.leanback.widget.Presenter
    public final void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        ViewHolder vh = (ViewHolder) viewHolder;
        onBindDescription(vh, item);
        boolean hasTitle = true;
        if (TextUtils.isEmpty(vh.mTitle.getText())) {
            vh.mTitle.setVisibility(8);
            hasTitle = false;
        } else {
            vh.mTitle.setVisibility(0);
            vh.mTitle.setLineSpacing((vh.mTitleLineSpacing - vh.mTitle.getLineHeight()) + vh.mTitle.getLineSpacingExtra(), vh.mTitle.getLineSpacingMultiplier());
            vh.mTitle.setMaxLines(vh.mTitleMaxLines);
        }
        setTopMargin(vh.mTitle, vh.mTitleMargin);
        boolean hasSubtitle = true;
        if (TextUtils.isEmpty(vh.mSubtitle.getText())) {
            vh.mSubtitle.setVisibility(8);
            hasSubtitle = false;
        } else {
            vh.mSubtitle.setVisibility(0);
            if (hasTitle) {
                setTopMargin(vh.mSubtitle, (vh.mUnderTitleBaselineMargin + vh.mSubtitleFontMetricsInt.ascent) - vh.mTitleFontMetricsInt.descent);
            } else {
                setTopMargin(vh.mSubtitle, 0);
            }
        }
        if (TextUtils.isEmpty(vh.mBody.getText())) {
            vh.mBody.setVisibility(8);
            return;
        }
        vh.mBody.setVisibility(0);
        vh.mBody.setLineSpacing((vh.mBodyLineSpacing - vh.mBody.getLineHeight()) + vh.mBody.getLineSpacingExtra(), vh.mBody.getLineSpacingMultiplier());
        if (hasSubtitle) {
            setTopMargin(vh.mBody, (vh.mUnderSubtitleBaselineMargin + vh.mBodyFontMetricsInt.ascent) - vh.mSubtitleFontMetricsInt.descent);
        } else if (hasTitle) {
            setTopMargin(vh.mBody, (vh.mUnderTitleBaselineMargin + vh.mBodyFontMetricsInt.ascent) - vh.mTitleFontMetricsInt.descent);
        } else {
            setTopMargin(vh.mBody, 0);
        }
    }

    @Override // androidx.leanback.widget.Presenter
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    }

    @Override // androidx.leanback.widget.Presenter
    public void onViewAttachedToWindow(Presenter.ViewHolder holder) {
        ViewHolder vh = (ViewHolder) holder;
        vh.addPreDrawListener();
        super.onViewAttachedToWindow(holder);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onViewDetachedFromWindow(Presenter.ViewHolder holder) {
        ViewHolder vh = (ViewHolder) holder;
        vh.removePreDrawListener();
        super.onViewDetachedFromWindow(holder);
    }

    private void setTopMargin(TextView textView, int topMargin) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
        lp.topMargin = topMargin;
        textView.setLayoutParams(lp);
    }
}
