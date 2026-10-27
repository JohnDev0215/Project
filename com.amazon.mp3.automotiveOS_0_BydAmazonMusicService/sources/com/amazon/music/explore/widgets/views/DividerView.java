package com.amazon.music.explore.widgets.views;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.amazon.music.explore.R;
import com.amazon.ui.foundations.styles.ShadowStyle;
import com.amazon.ui.foundations.views.BaseImageShadow;

/* JADX INFO: loaded from: classes4.dex */
public class DividerView extends RelativeLayout {
    private final Context context;
    private ImageView divider;
    private final int height;
    private final Resources resources;
    private BaseImageShadow shadow;
    private final int width;

    public DividerView(Context context) {
        super(context);
        this.context = context;
        Resources resources = getResources();
        this.resources = resources;
        this.width = resources.getDimensionPixelSize(R.dimen.spacer_epic) * 2;
        this.height = resources.getDimensionPixelSize(R.dimen.spacer_micro);
        init();
    }

    private void init() {
        View.inflate(this.context, R.layout.divider_view, this);
        this.divider = (ImageView) findViewById(R.id.divider);
        this.shadow = (BaseImageShadow) findViewById(R.id.divider_shadow);
        setDimensions();
        addDropShadow(this.resources.getColor(R.color.secondary_glass_3), 0, this.resources.getDimensionPixelSize(R.dimen.spacer_2));
    }

    private void setDimensions() {
        this.divider.getLayoutParams().width = this.width;
        this.divider.getLayoutParams().height = this.height;
    }

    private void addDropShadow(int i, int i2, int i3) {
        ShadowStyle shadowStyle = new ShadowStyle(i, i2, i3);
        this.shadow.setSize(this.width, this.height);
        this.shadow.setStyle(shadowStyle);
        this.shadow.setCornerRadius(this.resources.getDimensionPixelSize(R.dimen.spacer_4));
    }
}
