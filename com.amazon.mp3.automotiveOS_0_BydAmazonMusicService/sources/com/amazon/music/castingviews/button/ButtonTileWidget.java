package com.amazon.music.castingviews.button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amazon.music.castingviews.R$drawable;
import com.amazon.music.castingviews.R$id;
import com.amazon.music.castingviews.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;

/* JADX INFO: loaded from: classes4.dex */
public class ButtonTileWidget extends RelativeLayout {
    private TextView tileTextView;

    public ButtonTileWidget(Context context) {
        this(context, null);
    }

    public ButtonTileWidget(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ButtonTileWidget(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public void setText(String str) {
        this.tileTextView.setText(str);
    }

    public void setText(int i) {
        this.tileTextView.setText(i);
    }

    private void init(Context context) {
        View.inflate(context, R$layout.casting_troubleshooting_tile, this);
        this.tileTextView = (TextView) findViewById(R$id.helpTileText);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (z) {
            setAlpha(1.0f);
        } else {
            setAlpha(0.3f);
        }
    }

    public void initStyle(StyleSheet styleSheet) {
        BaseButton baseButton = (BaseButton) findViewById(R$id.helpButton);
        if (styleSheet != null) {
            BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.PRIMARY);
            if (iconBuilder != null) {
                iconBuilder.withIcon(getResources().getDrawable(R$drawable.ic_alerts_help)).applyStyle(baseButton);
            }
            FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.PRIMARY);
            if (fontStyle != null) {
                FontUtil.INSTANCE.applyFontStyle(this.tileTextView, fontStyle);
            }
        }
    }
}
