package com.amazon.music.skyfire.ui.widgets.views;

import Touch.WidgetsInterface.v1_0.PillNavigatorWidgetItemElement;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.ContextKt;

/* JADX INFO: loaded from: classes4.dex */
public class SpotlightCarouselSlideView extends RelativeLayout {
    private TextView primaryCallToAction;
    private TextView primaryText;
    private float screenWidthInDp;
    private TextView secondaryCallToAction;
    private TextView secondaryText;
    private TextView tertiaryText;

    public SpotlightCarouselSlideView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.spotlight_carousel_slide, this);
        this.primaryText = (TextView) findViewById(R.id.spotlight_carousel_slide_primary_text);
        this.secondaryText = (TextView) findViewById(R.id.spotlight_carousel_slide_secondary_text);
        this.tertiaryText = (TextView) findViewById(R.id.spotlight_carousel_slide_tertiary_text);
        this.primaryCallToAction = (TextView) findViewById(R.id.spotlight_carousel_slide_primary_call_to_action);
        this.secondaryCallToAction = (TextView) findViewById(R.id.spotlight_carousel_slide_secondary_call_to_action);
        getScreenWidthInDp();
    }

    public void setPrimaryText(String str) {
        scaleTextSizeByScreenWidth(this.primaryText, getResources().getDimension(R.dimen.spotlight_carousel_slide_primary_text_size), getResources().getDimension(R.dimen.spotlight_carousel_slide_primary_text_line_space));
        bindText(this.primaryText, str);
    }

    public void setSecondaryText(String str) {
        scaleTextSizeByScreenWidth(this.secondaryText, getResources().getDimension(R.dimen.spotlight_carousel_slide_secondary_text_size), getResources().getDimension(R.dimen.spotlight_carousel_slide_secondary_text_line_space));
        bindText(this.secondaryText, str);
    }

    public void setTertiaryText(String str) {
        scaleTextSizeByScreenWidth(this.tertiaryText, getResources().getDimension(R.dimen.spotlight_carousel_slide_tertiary_text_size), getResources().getDimension(R.dimen.spotlight_carousel_slide_tertiary_text_line_space));
        bindText(this.tertiaryText, str);
    }

    private void scaleTextSizeByScreenWidth(TextView textView, float f, float f2) {
        if (shouldScale()) {
            textView.setTextSize(1, getScaledSizeInDP(f));
            textView.setLineSpacing(getScaledSizeInDP(f2), 1.0f);
        }
    }

    public void setPrimaryCallToAction(String str, MethodsDispatcher methodsDispatcher, PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement) {
        setCallToAction(str, methodsDispatcher, pillNavigatorWidgetItemElement, this.primaryCallToAction);
    }

    public void setSecondaryCallToAction(String str, MethodsDispatcher methodsDispatcher, PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement) {
        setCallToAction(str, methodsDispatcher, pillNavigatorWidgetItemElement, this.secondaryCallToAction);
    }

    private void setCallToAction(final String str, final MethodsDispatcher methodsDispatcher, final PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement, TextView textView) {
        if (pillNavigatorWidgetItemElement == null) {
            textView.setVisibility(8);
            return;
        }
        scaleTextSizeByScreenWidth(textView, getResources().getDimension(R.dimen.spotlight_carousel_call_to_action_button_text_size), getResources().getDimension(R.dimen.spotlight_carousel_call_to_action_button_text_line_space));
        bindText(textView, pillNavigatorWidgetItemElement.primaryText());
        textView.setTypeface(Typeface.createFromAsset(getResources().getAssets(), getResources().getString(R.string.font_amazon_ember_regular)));
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.views.SpotlightCarouselSlideView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SpotlightCarouselSlideView.lambda$setCallToAction$0(methodsDispatcher, str, pillNavigatorWidgetItemElement, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setCallToAction$0(MethodsDispatcher methodsDispatcher, String str, PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement, View view) {
        methodsDispatcher.dispatchMethods(str, pillNavigatorWidgetItemElement.onItemSelected());
    }

    private void bindText(TextView textView, String str) {
        if (str == null || str.length() == 0) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            textView.setText(str);
        }
    }

    private float getScaledSizeInDP(float f) {
        return ((f / getResources().getDisplayMetrics().density) * this.screenWidthInDp) / 414.0f;
    }

    private boolean shouldScale() {
        return this.screenWidthInDp < 414.0f;
    }

    private void getScreenWidthInDp() {
        this.screenWidthInDp = ContextKt.getScreenWidth(getContext()) / getResources().getDisplayMetrics().density;
    }
}
