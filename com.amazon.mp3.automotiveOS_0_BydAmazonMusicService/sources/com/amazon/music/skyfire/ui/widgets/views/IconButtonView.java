package com.amazon.music.skyfire.ui.widgets.views;

import Touch.WidgetsInterface.v1_0.IconButtonElement;
import Touch.WidgetsInterface.v1_0.LabeledIconButtonElement;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.R;
import com.squareup.picasso.Picasso;

/* JADX INFO: loaded from: classes4.dex */
public class IconButtonView extends LinearLayout {
    private RelativeLayout button;
    private ImageView buttonBackground;
    private ImageView buttonImage;
    private TextView buttonLabel;

    public IconButtonView(Context context, int i) {
        super(context);
        init(context);
        setButtonSize(i);
    }

    public IconButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.IconButtonView, 0, 0);
        try {
            int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.IconButtonView_iconButtonSize, 0);
            this.buttonImage.setImageDrawable(typedArrayObtainStyledAttributes.getDrawable(R.styleable.IconButtonView_imageSrc));
            setButtonSize(dimensionPixelSize);
            setOnClickListener(null);
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    private void init(Context context) {
        View.inflate(context, R.layout.icon_button, this);
        this.button = (RelativeLayout) findViewById(R.id.icon_button);
        this.buttonBackground = (ImageView) findViewById(R.id.icon_button_background);
        this.buttonImage = (ImageView) findViewById(R.id.icon_button_image);
        this.buttonLabel = (TextView) findViewById(R.id.icon_button_label);
        setOrientation(1);
        setGravity(17);
    }

    public void bind(final IconButtonElement iconButtonElement, final String str, final MethodsDispatcher methodsDispatcher) {
        setImage(iconButtonElement.icon());
        if (iconButtonElement instanceof LabeledIconButtonElement) {
            setLabel(((LabeledIconButtonElement) iconButtonElement).label());
        }
        this.button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.views.IconButtonView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IconButtonView.lambda$bind$0(methodsDispatcher, str, iconButtonElement, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$bind$0(MethodsDispatcher methodsDispatcher, String str, IconButtonElement iconButtonElement, View view) {
        methodsDispatcher.dispatchMethods(str, iconButtonElement.onItemSelected());
    }

    public TextView getLabel() {
        return this.buttonLabel;
    }

    public void setButtonContentDescription(String str) {
        this.button.setContentDescription(str);
    }

    public void setImage(String str) {
        if (str == null || "".equals(str)) {
            this.button.setVisibility(8);
        } else {
            Picasso.get().load(str).into(this.buttonImage);
            this.button.setVisibility(0);
        }
    }

    private void setLabel(String str) {
        if (str == null || "".equals(str)) {
            this.buttonLabel.setVisibility(8);
        } else {
            this.buttonLabel.setText(str);
            this.buttonLabel.setVisibility(0);
        }
    }

    public void setButtonSize(int i) {
        this.buttonBackground.getLayoutParams().width = i;
        this.buttonBackground.getLayoutParams().height = i;
        int i2 = i / 2;
        this.buttonImage.getLayoutParams().width = i2;
        this.buttonImage.getLayoutParams().height = i2;
    }
}
