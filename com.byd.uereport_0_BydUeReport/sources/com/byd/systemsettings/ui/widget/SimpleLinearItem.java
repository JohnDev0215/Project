package com.byd.systemsettings.ui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.byd.systemsettings.R;
import s2.a;

/* JADX INFO: loaded from: classes2.dex */
public class SimpleLinearItem extends FrameLayout implements a {
    public static final int INVALID = 0;
    public int bottomLineColorId;
    public int bottomLineMargin;
    public View content;
    public int contentMarginLeft;
    public ImageView ivRight;
    public boolean showBottomLine;
    public boolean showImgRight;
    public int textLeftColorId;
    public int textLeftId;
    public int textLeftSize;
    public TextView tvLeft;
    public View vLineBottom;

    public SimpleLinearItem(Context context) {
        super(context);
        this.textLeftId = 0;
        this.textLeftColorId = 0;
        this.textLeftSize = 0;
        this.contentMarginLeft = 0;
        this.bottomLineMargin = 0;
        this.bottomLineColorId = 0;
        this.showImgRight = true;
        this.showBottomLine = false;
        initViews(context);
    }

    private void setMargin(View view, int i8, int i9) {
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            if (i8 >= 0) {
                m4.a.e(marginLayoutParams, "startMargin", Integer.valueOf(i8));
            }
            if (i9 >= 0) {
                m4.a.e(marginLayoutParams, "endMargin", Integer.valueOf(i9));
            }
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public int getLayoutId() {
        return R.layout.item_simple_linear;
    }

    public void initAttrs(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.linear_item);
        this.textLeftId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.linear_item_textLeft, this.textLeftId);
        this.showImgRight = typedArrayObtainStyledAttributes.getBoolean(R.styleable.linear_item_showImgRight, this.showImgRight);
        this.showBottomLine = typedArrayObtainStyledAttributes.getBoolean(R.styleable.linear_item_showBottomLine, this.showBottomLine);
        this.textLeftColorId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.linear_item_textLeftColor, this.textLeftColorId);
        this.textLeftSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.linear_item_textLeftSize, this.textLeftSize);
        this.contentMarginLeft = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.linear_item_contentMarginLeft, this.contentMarginLeft);
        this.bottomLineMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.linear_item_bottomLineMargin, this.bottomLineMargin);
        this.bottomLineColorId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.linear_item_bottomLineColor, this.bottomLineColorId);
        typedArrayObtainStyledAttributes.recycle();
    }

    public void initViews(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(getLayoutId(), (ViewGroup) null);
        this.content = viewInflate;
        this.tvLeft = (TextView) viewInflate.findViewById(R.id.tv_left_item_simple_linear);
        this.ivRight = (ImageView) this.content.findViewById(R.id.iv_right_item_simple_linear);
        this.vLineBottom = this.content.findViewById(R.id.v_line_botoom_item_simple_linear);
        Resources resources = getResources();
        int i8 = this.textLeftId;
        if (i8 != 0) {
            this.tvLeft.setText(resources.getString(i8));
        }
        int i9 = this.textLeftColorId;
        if (i9 != 0) {
            this.tvLeft.setTextColor(resources.getColor(i9));
        }
        int i10 = this.textLeftSize;
        if (i10 != 0) {
            this.tvLeft.setTextSize(0, i10);
        }
        int i11 = this.contentMarginLeft;
        if (i11 != 0) {
            setMargin(this.tvLeft, i11, -1);
        }
        int i12 = this.bottomLineMargin;
        if (i12 != 0) {
            setMargin(this.vLineBottom, i12, i12);
        }
        int i13 = this.bottomLineColorId;
        if (i13 != 0) {
            this.vLineBottom.setBackgroundColor(resources.getColor(i13));
        }
        this.ivRight.setVisibility(this.showImgRight ? 0 : 8);
        this.vLineBottom.setVisibility(this.showBottomLine ? 0 : 8);
        addView(this.content);
    }

    @Override // s2.a
    public /* bridge */ /* synthetic */ boolean isInvalid() {
        return super.isInvalid();
    }

    @Override // s2.a
    public /* bridge */ /* synthetic */ void registerInvalidStateChangedCallback(a.InterfaceC0117a interfaceC0117a) {
        super.registerInvalidStateChangedCallback(interfaceC0117a);
    }

    @Override // s2.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z7) {
        super.setInvalid(z7);
    }

    @Override // s2.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z7, boolean z8) {
        super.setInvalid(z7, z8);
    }

    public void setLeftText(String str) {
        TextView textView = this.tvLeft;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void setView(View view) {
        if (view == null) {
            return;
        }
        removeAllViews();
        addView(view);
    }

    @Override // s2.a
    public /* bridge */ /* synthetic */ void unregisterInvalidStateChangedCallback() {
        super.unregisterInvalidStateChangedCallback();
    }

    public SimpleLinearItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.textLeftId = 0;
        this.textLeftColorId = 0;
        this.textLeftSize = 0;
        this.contentMarginLeft = 0;
        this.bottomLineMargin = 0;
        this.bottomLineColorId = 0;
        this.showImgRight = true;
        this.showBottomLine = false;
        initAttrs(context, attributeSet);
        initViews(context);
    }

    public SimpleLinearItem(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.textLeftId = 0;
        this.textLeftColorId = 0;
        this.textLeftSize = 0;
        this.contentMarginLeft = 0;
        this.bottomLineMargin = 0;
        this.bottomLineColorId = 0;
        this.showImgRight = true;
        this.showBottomLine = false;
        initAttrs(context, attributeSet);
        initViews(context);
    }

    public SimpleLinearItem(Context context, AttributeSet attributeSet, int i8, int i9) {
        super(context, attributeSet, i8, i9);
        this.textLeftId = 0;
        this.textLeftColorId = 0;
        this.textLeftSize = 0;
        this.contentMarginLeft = 0;
        this.bottomLineMargin = 0;
        this.bottomLineColorId = 0;
        this.showImgRight = true;
        this.showBottomLine = false;
        initAttrs(context, attributeSet);
        initViews(context);
    }
}
