package com.amazon.music.inappmessaging.ui.notification;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amazon.music.inappmessaging.R;

/* JADX INFO: loaded from: classes4.dex */
public class Ribbon extends RelativeLayout {
    private static final int DEFAULT_HEADER_SIZE_PX = 20;
    private static final int INVALID_ACTION = -1;
    private ImageView action1;
    private int action1Attr;
    private ImageView action2;
    private int action2Attr;
    private int headerColorAttr;
    private int headerSizePxAttr;
    private TextView headerText;
    private String headerTextAttr;
    private ImageView separator;
    private int separatorColorAttr;
    private static final int DEFAULT_HEADER_COLOR = Color.parseColor("#FFFFFF");
    private static final int DEFAULT_SEPARATOR_COLOR = Color.parseColor("#BDBDBD");

    private boolean isActionAttrValid(final int actionAttr) {
        return actionAttr > -1;
    }

    public enum Duration {
        SHORT(5),
        LONG(10),
        INDEFINITE(-1);

        int time;

        Duration(int time) {
            this.time = time;
        }
    }

    public Ribbon(Context context) {
        this(context, null);
    }

    public Ribbon(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.Ribbon);
            this.headerTextAttr = typedArrayObtainStyledAttributes.getString(R.styleable.Ribbon_ribbonHeaderText);
            this.headerSizePxAttr = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.Ribbon_ribbonHeaderSize, 20);
            this.headerColorAttr = typedArrayObtainStyledAttributes.getColor(R.styleable.Ribbon_ribbonHeaderColor, DEFAULT_HEADER_COLOR);
            this.separatorColorAttr = typedArrayObtainStyledAttributes.getColor(R.styleable.Ribbon_ribbonSeparatorColor, DEFAULT_SEPARATOR_COLOR);
            this.action1Attr = typedArrayObtainStyledAttributes.getResourceId(R.styleable.Ribbon_ribbonAction1, -1);
            this.action2Attr = typedArrayObtainStyledAttributes.getResourceId(R.styleable.Ribbon_ribbonAction2, -1);
            typedArrayObtainStyledAttributes.recycle();
        }
        bindView();
    }

    public Ribbon setAction1(final View.OnClickListener listener) {
        this.headerText.setOnClickListener(listener);
        this.action1.setVisibility(0);
        this.action1.setOnClickListener(listener);
        return this;
    }

    public Ribbon setAction2(final View.OnClickListener listener) {
        this.action2.setOnClickListener(listener);
        this.action2.setVisibility(0);
        this.separator.setVisibility(0);
        return this;
    }

    public Ribbon setHeaderText(final String value) {
        this.headerTextAttr = value;
        this.headerText.setText(value);
        return this;
    }

    private void bindView() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.notification_ribbon, (ViewGroup) this, true);
        bindHeader(viewInflate);
        bindSeparator(viewInflate);
        bindAction(viewInflate);
    }

    private void bindHeader(final View parentView) {
        TextView textView = (TextView) parentView.findViewById(R.id.ribbon_header);
        this.headerText = textView;
        textView.setText(this.headerTextAttr);
        this.headerText.setTextSize(0, this.headerSizePxAttr);
        this.headerText.setTextColor(this.headerColorAttr);
    }

    private void bindSeparator(final View parentView) {
        ImageView imageView = (ImageView) parentView.findViewById(R.id.ribbon_separator);
        this.separator = imageView;
        imageView.setBackgroundColor(this.separatorColorAttr);
    }

    private void bindAction(final View parentView) {
        this.action1 = (ImageView) parentView.findViewById(R.id.ribbon_action_1);
        this.action2 = (ImageView) parentView.findViewById(R.id.ribbon_action_2);
        bindAction1();
        bindAction2();
    }

    private void bindAction1() {
        if (isActionAttrValid(this.action1Attr)) {
            this.action1.setImageResource(this.action1Attr);
        }
    }

    private void bindAction2() {
        if (isActionAttrValid(this.action2Attr)) {
            this.action2.setBackgroundResource(this.action2Attr);
        }
    }
}
