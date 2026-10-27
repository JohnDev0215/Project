package androidx.leanback.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.leanback.R;
import androidx.leanback.widget.SearchOrbView;
import androidx.leanback.widget.TitleViewAdapter;

/* JADX INFO: loaded from: classes.dex */
public class TitleView extends FrameLayout implements TitleViewAdapter.Provider {
    private int flags;
    private ImageView mBadgeView;
    private boolean mHasSearchListener;
    private SearchOrbView mSearchOrbView;
    private TextView mTextView;
    private final TitleViewAdapter mTitleViewAdapter;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.browseTitleViewStyle);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.flags = 6;
        this.mHasSearchListener = false;
        this.mTitleViewAdapter = new TitleViewAdapter() { // from class: androidx.leanback.widget.TitleView.1
            @Override // androidx.leanback.widget.TitleViewAdapter
            public View getSearchAffordanceView() {
                return TitleView.this.getSearchAffordanceView();
            }

            @Override // androidx.leanback.widget.TitleViewAdapter
            public void setOnSearchClickedListener(View.OnClickListener listener) {
                TitleView.this.setOnSearchClickedListener(listener);
            }

            @Override // androidx.leanback.widget.TitleViewAdapter
            public void setAnimationEnabled(boolean enable) {
                TitleView.this.enableAnimation(enable);
            }

            @Override // androidx.leanback.widget.TitleViewAdapter
            public Drawable getBadgeDrawable() {
                return TitleView.this.getBadgeDrawable();
            }

            @Override // androidx.leanback.widget.TitleViewAdapter
            public SearchOrbView.Colors getSearchAffordanceColors() {
                return TitleView.this.getSearchAffordanceColors();
            }

            @Override // androidx.leanback.widget.TitleViewAdapter
            public CharSequence getTitle() {
                return TitleView.this.getTitle();
            }

            @Override // androidx.leanback.widget.TitleViewAdapter
            public void setBadgeDrawable(Drawable drawable) {
                TitleView.this.setBadgeDrawable(drawable);
            }

            @Override // androidx.leanback.widget.TitleViewAdapter
            public void setSearchAffordanceColors(SearchOrbView.Colors colors) {
                TitleView.this.setSearchAffordanceColors(colors);
            }

            @Override // androidx.leanback.widget.TitleViewAdapter
            public void setTitle(CharSequence titleText) {
                TitleView.this.setTitle(titleText);
            }

            @Override // androidx.leanback.widget.TitleViewAdapter
            public void updateComponentsVisibility(int flags) {
                TitleView.this.updateComponentsVisibility(flags);
            }
        };
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.lb_title_view, this);
        this.mBadgeView = (ImageView) rootView.findViewById(R.id.title_badge);
        this.mTextView = (TextView) rootView.findViewById(R.id.title_text);
        this.mSearchOrbView = (SearchOrbView) rootView.findViewById(R.id.title_orb);
        setClipToPadding(false);
        setClipChildren(false);
    }

    public void setTitle(CharSequence titleText) {
        this.mTextView.setText(titleText);
        updateBadgeVisibility();
    }

    public CharSequence getTitle() {
        return this.mTextView.getText();
    }

    public void setBadgeDrawable(Drawable drawable) {
        this.mBadgeView.setImageDrawable(drawable);
        updateBadgeVisibility();
    }

    public Drawable getBadgeDrawable() {
        return this.mBadgeView.getDrawable();
    }

    public void setOnSearchClickedListener(View.OnClickListener listener) {
        this.mHasSearchListener = listener != null;
        this.mSearchOrbView.setOnOrbClickedListener(listener);
        updateSearchOrbViewVisiblity();
    }

    public View getSearchAffordanceView() {
        return this.mSearchOrbView;
    }

    public void setSearchAffordanceColors(SearchOrbView.Colors colors) {
        this.mSearchOrbView.setOrbColors(colors);
    }

    public SearchOrbView.Colors getSearchAffordanceColors() {
        return this.mSearchOrbView.getOrbColors();
    }

    public void enableAnimation(boolean enable) {
        SearchOrbView searchOrbView = this.mSearchOrbView;
        searchOrbView.enableOrbColorAnimation(enable && searchOrbView.hasFocus());
    }

    public void updateComponentsVisibility(int flags) {
        this.flags = flags;
        if ((flags & 2) == 2) {
            updateBadgeVisibility();
        } else {
            this.mBadgeView.setVisibility(8);
            this.mTextView.setVisibility(8);
        }
        updateSearchOrbViewVisiblity();
    }

    private void updateSearchOrbViewVisiblity() {
        int i = 4;
        if (this.mHasSearchListener && (this.flags & 4) == 4) {
            i = 0;
        }
        int visibility = i;
        this.mSearchOrbView.setVisibility(visibility);
    }

    private void updateBadgeVisibility() {
        Drawable drawable = this.mBadgeView.getDrawable();
        if (drawable != null) {
            this.mBadgeView.setVisibility(0);
            this.mTextView.setVisibility(8);
        } else {
            this.mBadgeView.setVisibility(8);
            this.mTextView.setVisibility(0);
        }
    }

    @Override // androidx.leanback.widget.TitleViewAdapter.Provider
    public TitleViewAdapter getTitleViewAdapter() {
        return this.mTitleViewAdapter;
    }
}
