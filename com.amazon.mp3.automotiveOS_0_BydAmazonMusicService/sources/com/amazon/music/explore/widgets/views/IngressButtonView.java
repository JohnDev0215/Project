package com.amazon.music.explore.widgets.views;

import Touch.WidgetsInterface.v1_0.IngressButtonElement;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amazon.music.explore.R;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.squareup.picasso.Picasso;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class IngressButtonView extends LinearLayout {
    private ImageView headerIcon;
    private TextView headerText;
    private IngressButtonElement ingressButtonElement;
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;

    public IngressButtonView(String str, Context context, MethodsDispatcher methodsDispatcher, IngressButtonElement ingressButtonElement) {
        super(context);
        this.ownerId = str;
        this.methodsDispatcher = methodsDispatcher;
        this.ingressButtonElement = ingressButtonElement;
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.ingress_button_view, this);
        this.headerIcon = (ImageView) findViewById(R.id.ingress_header_icon);
        this.headerText = (TextView) findViewById(R.id.ingress_header_text);
        setHeaderIcon(this.ingressButtonElement.icon());
        setHeaderText(this.ingressButtonElement.text());
    }

    public void onClicked() {
        this.methodsDispatcher.dispatchMethods(this.ownerId, this.ingressButtonElement.onItemSelected());
    }

    public void onViewed() {
        this.methodsDispatcher.dispatchMethods(this.ownerId, this.ingressButtonElement.onViewed());
    }

    private void setHeaderIcon(String str) {
        if (StringUtils.isNotEmpty(str)) {
            Picasso.get().load(str).into(this.headerIcon);
            this.headerIcon.setVisibility(0);
        } else {
            this.headerIcon.setVisibility(8);
        }
    }

    private void setHeaderText(String str) {
        if (StringUtils.isNotEmpty(str)) {
            this.headerText.setText(str);
            this.headerText.setVisibility(0);
        } else {
            this.headerText.setVisibility(8);
        }
    }
}
