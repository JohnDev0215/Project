package com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.elements;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amazon.music.explore.R;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageRelativeLayout;
import com.squareup.picasso.Picasso;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class LabelView extends SwipeablePageRelativeLayout {
    private static final String ELLIPSES = "...";
    private static final int LABEL_MAX_CHARS = 25;
    private final Context context;
    private LinearLayout icons;
    private final boolean isFullScreen;
    private final boolean isStage;
    private TextView label;
    private final Resources resources;

    public LabelView(Context context, boolean z, boolean z2) {
        super(context);
        this.context = context;
        this.isStage = z;
        this.isFullScreen = z2;
        this.resources = getResources();
        init();
    }

    private void init() {
        View.inflate(this.context, R.layout.swipeable_pages_label_view, this);
        this.label = (TextView) findViewById(R.id.swipeable_pages_label);
        this.icons = (LinearLayout) findViewById(R.id.swipeable_pages_label_icons);
        setFontFamily();
    }

    public void setText(String str, boolean z) {
        if (StringUtils.isEmpty(str)) {
            this.label.setVisibility(8);
            return;
        }
        if (z) {
            if (str.length() > 25) {
                str = str.substring(0, 25) + ELLIPSES;
            }
            this.label.setText(str);
        } else {
            this.label.setText(str);
        }
        this.label.setVisibility(0);
    }

    public LinearLayout getIcons() {
        return this.icons;
    }

    public void setIcons(List<String> list, boolean z, int i) {
        if (list != null && list.isEmpty()) {
            this.icons.setVisibility(8);
            return;
        }
        if (!z) {
            i = this.resources.getDimensionPixelSize(R.dimen.icon_small);
        }
        int dimensionPixelSize = this.resources.getDimensionPixelSize(R.dimen.spacer_micro);
        this.icons.getLayoutParams().width = list.size() * (i + dimensionPixelSize);
        this.icons.setVisibility(0);
        for (String str : list) {
            ImageView imageView = new ImageView(this.context);
            Picasso.get().load(str).into(imageView);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i);
            if (z) {
                layoutParams.gravity = 16;
            }
            layoutParams.rightMargin = dimensionPixelSize;
            imageView.setLayoutParams(layoutParams);
            this.icons.addView(imageView);
        }
    }

    public void setMaxWidth(int i) {
        this.label.setMaxWidth(i);
    }

    public void updateMarginBottom(int i) {
        ((RelativeLayout.LayoutParams) this.label.getLayoutParams()).bottomMargin = i;
    }

    public void overrideForStage(float f, float f2) {
        overrideLabelTextLayout();
        overrideLabelFontSize(f);
        overrideLabelLineHeight(f, f2);
        overrideFontFamily();
    }

    private void overrideLabelTextLayout() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ((RelativeLayout) findViewById(R.id.swipeable_pages_label_container)).getLayoutParams();
        layoutParams.addRule(13, -1);
        layoutParams.height = -2;
        ((RelativeLayout.LayoutParams) this.label.getLayoutParams()).addRule(15, -1);
        this.label.setTextAlignment(4);
        this.label.setIncludeFontPadding(false);
    }

    private void overrideLabelFontSize(float f) {
        this.label.setTextSize(0, f);
    }

    private void overrideLabelLineHeight(float f, float f2) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.label.setLineHeight((int) f);
        } else {
            this.label.setLineSpacing(f - f2, 1.0f);
        }
    }

    private void overrideFontFamily() {
        this.label.setTypeface(Typeface.createFromAsset(this.resources.getAssets(), this.resources.getString(R.string.font_amazon_ember_bold)));
    }

    private void setFontFamily() {
        this.label.setTypeface(Typeface.createFromAsset(this.resources.getAssets(), this.resources.getString(R.string.font_amazon_ember_display_condensed)));
    }
}
