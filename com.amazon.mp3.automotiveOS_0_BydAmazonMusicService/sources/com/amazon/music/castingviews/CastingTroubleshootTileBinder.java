package com.amazon.music.castingviews;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.amazon.music.castingviews.button.ButtonTileModel;
import com.amazon.music.castingviews.button.ButtonTileWidget;
import com.amazon.music.events.types.Orientation;
import com.amazon.music.view.adapter.UniversalBinder;
import com.amazon.music.views.library.styles.StyleSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class CastingTroubleshootTileBinder implements UniversalBinder<ButtonTileWidget, ButtonTileModel> {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) CastingTroubleshootTileBinder.class);
    private final Context activityContext;
    private final String helpUrl;
    private View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.music.castingviews.CastingTroubleshootTileBinder.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CastingUIMetricsLogger.recordTroubleshootButtonClickMetric();
            if (CastingTroubleshootTileBinder.this.activityContext == null || CastingTroubleshootTileBinder.this.helpUrl == null) {
                CastingTroubleshootTileBinder.LOG.error("Cannot open casting help url: " + CastingTroubleshootTileBinder.this.helpUrl + " with context: " + CastingTroubleshootTileBinder.this.activityContext);
                return;
            }
            CastingTroubleshootTileBinder.LOG.debug("Opening casting help url: " + CastingTroubleshootTileBinder.this.helpUrl);
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(CastingTroubleshootTileBinder.this.helpUrl));
            CastingUIMetricsLogger.recordTroubleshootPageView(view.getResources().getConfiguration().orientation == 1 ? Orientation.PORTRAIT : Orientation.LANDSCAPE);
            CastingTroubleshootTileBinder.this.activityContext.startActivity(intent);
        }
    };
    private StyleSheet styleSheet;

    CastingTroubleshootTileBinder(Context context, String str, StyleSheet styleSheet) {
        this.activityContext = context;
        this.helpUrl = str;
        this.styleSheet = styleSheet;
    }

    @Override // com.amazon.music.view.adapter.UniversalBinder
    public Class<ButtonTileModel> getModelClass() {
        return ButtonTileModel.class;
    }

    @Override // com.amazon.music.view.adapter.UniversalBinder
    public ButtonTileWidget createView(Context context) {
        return new ButtonTileWidget(context);
    }

    @Override // com.amazon.music.view.adapter.UniversalBinder
    public void bind(ButtonTileWidget buttonTileWidget, ButtonTileModel buttonTileModel) {
        if (buttonTileModel.textResId.isPresent()) {
            String string = buttonTileWidget.getResources().getString(buttonTileModel.textResId.get().intValue());
            buttonTileWidget.setText(string);
            buttonTileWidget.setContentDescription(buttonTileWidget.getResources().getString(R$string.content_description_button_suffix, string));
        }
        buttonTileWidget.setOnClickListener(this.onClickListener);
        buttonTileWidget.setTag(buttonTileModel);
        buttonTileWidget.initStyle(this.styleSheet);
    }
}
