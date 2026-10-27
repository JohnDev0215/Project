package androidx.leanback.widget;

import android.animation.Animator;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.leanback.R;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class GuidanceStylist implements FragmentAnimationProvider {
    private TextView mBreadcrumbView;
    private TextView mDescriptionView;
    private View mGuidanceContainer;
    private ImageView mIconView;
    private TextView mTitleView;

    public static class Guidance {
        private final String mBreadcrumb;
        private final String mDescription;
        private final Drawable mIconDrawable;
        private final String mTitle;

        public Guidance(String title, String description, String breadcrumb, Drawable icon) {
            this.mBreadcrumb = breadcrumb;
            this.mTitle = title;
            this.mDescription = description;
            this.mIconDrawable = icon;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String getDescription() {
            return this.mDescription;
        }

        public String getBreadcrumb() {
            return this.mBreadcrumb;
        }

        public Drawable getIconDrawable() {
            return this.mIconDrawable;
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Guidance guidance) {
        View guidanceView = inflater.inflate(onProvideLayoutId(), container, false);
        this.mTitleView = (TextView) guidanceView.findViewById(R.id.guidance_title);
        this.mBreadcrumbView = (TextView) guidanceView.findViewById(R.id.guidance_breadcrumb);
        this.mDescriptionView = (TextView) guidanceView.findViewById(R.id.guidance_description);
        this.mIconView = (ImageView) guidanceView.findViewById(R.id.guidance_icon);
        this.mGuidanceContainer = guidanceView.findViewById(R.id.guidance_container);
        TextView textView = this.mTitleView;
        if (textView != null) {
            textView.setText(guidance.getTitle());
        }
        TextView textView2 = this.mBreadcrumbView;
        if (textView2 != null) {
            textView2.setText(guidance.getBreadcrumb());
        }
        TextView textView3 = this.mDescriptionView;
        if (textView3 != null) {
            textView3.setText(guidance.getDescription());
        }
        if (this.mIconView != null) {
            if (guidance.getIconDrawable() != null) {
                this.mIconView.setImageDrawable(guidance.getIconDrawable());
            } else {
                this.mIconView.setVisibility(8);
            }
        }
        View view = this.mGuidanceContainer;
        if (view != null) {
            CharSequence contentDescription = view.getContentDescription();
            if (TextUtils.isEmpty(contentDescription)) {
                StringBuilder builder = new StringBuilder();
                if (!TextUtils.isEmpty(guidance.getBreadcrumb())) {
                    builder.append(guidance.getBreadcrumb());
                    builder.append('\n');
                }
                if (!TextUtils.isEmpty(guidance.getTitle())) {
                    builder.append(guidance.getTitle());
                    builder.append('\n');
                }
                if (!TextUtils.isEmpty(guidance.getDescription())) {
                    builder.append(guidance.getDescription());
                    builder.append('\n');
                }
                this.mGuidanceContainer.setContentDescription(builder);
            }
        }
        return guidanceView;
    }

    public void onDestroyView() {
        this.mBreadcrumbView = null;
        this.mDescriptionView = null;
        this.mIconView = null;
        this.mTitleView = null;
    }

    public int onProvideLayoutId() {
        return R.layout.lb_guidance;
    }

    public TextView getTitleView() {
        return this.mTitleView;
    }

    public TextView getDescriptionView() {
        return this.mDescriptionView;
    }

    public TextView getBreadcrumbView() {
        return this.mBreadcrumbView;
    }

    public ImageView getIconView() {
        return this.mIconView;
    }

    @Override // androidx.leanback.widget.FragmentAnimationProvider
    public void onImeAppearing(List<Animator> animators) {
    }

    @Override // androidx.leanback.widget.FragmentAnimationProvider
    public void onImeDisappearing(List<Animator> animators) {
    }
}
