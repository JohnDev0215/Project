package com.amazon.music.explore.widgets.views;

import Touch.WidgetsInterface.v1_0.PillNavigatorWidgetItemElement;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.amazon.music.explore.R;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.ui.foundations.views.BaseButton;

/* JADX INFO: loaded from: classes4.dex */
public class PillNavigatorWidgetItemView extends LinearLayout {
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private BaseButton pillButton;
    private PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement;

    public PillNavigatorWidgetItemView(String str, Context context, MethodsDispatcher methodsDispatcher, PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement) {
        super(context);
        this.ownerId = str;
        this.methodsDispatcher = methodsDispatcher;
        this.pillNavigatorWidgetItemElement = pillNavigatorWidgetItemElement;
        init(context);
    }

    private void init(Context context) {
        setLayoutParams();
        View.inflate(context, R.layout.pill_navigator_widget_item_view, this);
        BaseButton baseButton = (BaseButton) findViewById(R.id.xrayOverlayButton);
        this.pillButton = baseButton;
        baseButton.setText(this.pillNavigatorWidgetItemElement.primaryText());
    }

    public PillNavigatorWidgetItemElement getPillNavigatorWidgetItemElement() {
        return this.pillNavigatorWidgetItemElement;
    }

    public void onClicked() {
        this.methodsDispatcher.dispatchMethods(this.ownerId, this.pillNavigatorWidgetItemElement.onItemSelected());
    }

    public void onViewed() {
        this.methodsDispatcher.dispatchMethods(this.ownerId, this.pillNavigatorWidgetItemElement.onViewed());
    }

    private void setLayoutParams() {
        setLayoutParams(new LinearLayout.LayoutParams(-2, -2, 1.0f));
    }

    public BaseButton getPillButton() {
        return this.pillButton;
    }
}
