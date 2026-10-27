package com.amazon.music.explore.widgets.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amazon.music.explore.R;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class ErrorView extends RelativeLayout {
    private TextView button;
    private TextView primaryText;
    private TextView secondaryText;

    public ErrorView(Context context) {
        super(context);
        init(context);
    }

    public ErrorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public ErrorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.error_view, this);
        this.primaryText = (TextView) findViewById(R.id.error_view_primary_text);
        this.secondaryText = (TextView) findViewById(R.id.error_view_secondary_text);
        this.button = (TextView) findViewById(R.id.error_view_button);
        setFontFamily();
    }

    public void setButtonClickListener(View.OnClickListener onClickListener) {
        this.button.setOnClickListener(onClickListener);
    }

    public void setPrimaryText(String str) {
        bindText(this.primaryText, str);
    }

    public void setSecondaryText(String str) {
        bindText(this.secondaryText, str);
    }

    public void setButtonText(String str) {
        bindText(this.button, str);
    }

    private void bindText(TextView textView, String str) {
        if (StringUtils.isNotEmpty(str)) {
            textView.setText(str);
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
    }

    private void setFontFamily() {
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(getResources().getAssets(), getResources().getString(R.string.font_amazon_ember_regular));
        this.primaryText.setTypeface(typefaceCreateFromAsset);
        this.secondaryText.setTypeface(typefaceCreateFromAsset);
        this.button.setTypeface(typefaceCreateFromAsset);
    }
}
