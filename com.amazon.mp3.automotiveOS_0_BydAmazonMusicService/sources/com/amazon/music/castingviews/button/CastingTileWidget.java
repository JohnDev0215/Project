package com.amazon.music.castingviews.button;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.amazon.music.castingviews.R$color;
import com.amazon.music.castingviews.R$dimen;
import com.amazon.music.castingviews.R$drawable;
import com.amazon.music.castingviews.R$id;
import com.amazon.music.castingviews.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;

/* JADX INFO: loaded from: classes4.dex */
public class CastingTileWidget extends RelativeLayout {
    private Button button;
    private ImageView imageIcon;
    private int index;
    private boolean initialized;
    private ProgressBar spinner;
    private TextView subtitleTextView;
    private View tileContainer;
    private RelativeLayout titleContainer;
    private TextView titleTextView;
    private boolean wrapTitle;

    @TargetApi(21)
    public CastingTileWidget(Context context, AttributeSet attributeSet, int i, int i2, boolean z) {
        super(context, attributeSet, i, i2);
        this.initialized = false;
        this.wrapTitle = z;
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R$layout.casting_tile, this);
        this.tileContainer = findViewById(R$id.tileContainer);
        this.titleContainer = (RelativeLayout) findViewById(R$id.titleGroup);
        this.imageIcon = (ImageView) findViewById(R$id.imageIcon);
        this.titleTextView = (TextView) findViewById(R$id.title);
        this.subtitleTextView = (TextView) findViewById(R$id.subtitle);
        this.button = (Button) findViewById(R$id.button);
        this.spinner = (ProgressBar) findViewById(R$id.loading_spinner);
        if (this.wrapTitle) {
            this.button.setVisibility(8);
            this.spinner.setVisibility(8);
            this.titleTextView.setSingleLine(false);
            this.titleTextView.setMaxLines(2);
            this.button.setBackground(ContextCompat.getDrawable(context, R$drawable.casting_round_button_custom));
            this.button.setTextColor(ContextCompat.getColor(context, R$color.vivace_blue));
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.button.getLayoutParams();
            layoutParams.width = context.getResources().getDimensionPixelSize(R$dimen.casting_button_width);
            layoutParams.height = context.getResources().getDimensionPixelSize(R$dimen.casting_button_height);
            this.button.setLayoutParams(layoutParams);
        }
    }

    public View getTileContainer() {
        return this.tileContainer;
    }

    public ImageView getImageIconView() {
        return this.imageIcon;
    }

    public void setTitle(String str) {
        if (str != null) {
            this.titleTextView.setText(str);
        }
        this.titleTextView.setVisibility(!TextUtils.isEmpty(str) ? 0 : 8);
    }

    public void setSubtitle(String str) {
        if (str != null) {
            this.subtitleTextView.setText(str);
        }
        this.subtitleTextView.setVisibility(!TextUtils.isEmpty(str) ? 0 : 8);
    }

    public void setTitleColor(int i) {
        this.titleTextView.setTextColor(i);
    }

    public void setSubtitleColor(int i) {
        this.subtitleTextView.setTextColor(i);
    }

    public Button getButton() {
        return this.button;
    }

    public void showButton(boolean z) {
        if (this.wrapTitle) {
            this.button.setVisibility(z ? 0 : 8);
        } else {
            this.button.setVisibility(z ? 0 : 4);
        }
    }

    public void showSpinner(boolean z) {
        if (this.wrapTitle) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.titleContainer.getLayoutParams();
            if (z) {
                layoutParams.removeRule(0);
                layoutParams.addRule(0, R$id.loading_spinner);
            } else {
                layoutParams.removeRule(0);
                layoutParams.addRule(0, R$id.button);
            }
            this.spinner.setVisibility(z ? 0 : 8);
            return;
        }
        this.spinner.setVisibility(z ? 0 : 4);
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public int getIndex() {
        return this.index;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        if (z) {
            setAlpha(1.0f);
        } else {
            setAlpha(0.3f);
        }
    }

    public void initStyle(StyleSheet styleSheet) {
        if (styleSheet == null || this.initialized) {
            return;
        }
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        FontStyle fontStyle2 = styleSheet.getFontStyle(FontStyleKey.TERTIARY);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(this.titleTextView, fontStyle);
        }
        if (fontStyle2 != null) {
            FontUtil.INSTANCE.applyFontStyle(this.subtitleTextView, fontStyle2);
        }
        if (this.wrapTitle) {
            this.initialized = true;
        }
    }
}
