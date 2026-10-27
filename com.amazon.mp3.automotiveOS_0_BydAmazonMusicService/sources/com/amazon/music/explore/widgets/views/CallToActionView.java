package com.amazon.music.explore.widgets.views;

import Touch.WidgetsInterface.v1_0.CallToActionElement;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amazon.music.explore.R;
import com.amazon.music.skyfire.platform.MethodsDispatcher;

/* JADX INFO: loaded from: classes4.dex */
public class CallToActionView extends RelativeLayout {
    private Button button;
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private TextView title;

    public CallToActionView(Context context, MethodsDispatcher methodsDispatcher, String str) {
        super(context);
        this.methodsDispatcher = methodsDispatcher;
        this.ownerId = str;
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.call_to_action, this);
        this.title = (TextView) findViewById(R.id.call_to_action_title);
        this.button = (Button) findViewById(R.id.call_to_action_button);
    }

    public void bind(final CallToActionElement callToActionElement) {
        this.title.setText(callToActionElement.title());
        this.button.setText(callToActionElement.callToAction().primaryText());
        this.button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.widgets.views.CallToActionView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$bind$0(callToActionElement, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$bind$0(CallToActionElement callToActionElement, View view) {
        this.methodsDispatcher.dispatchMethods(this.ownerId, callToActionElement.callToAction().onItemSelected());
    }
}
