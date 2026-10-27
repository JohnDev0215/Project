package com.byd.systemsettings.ui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.byd.systemsettings.R;
import m4.d;
import s2.a;

/* JADX INFO: loaded from: classes2.dex */
public class LeftRightItem extends FrameLayout implements a {
    public static final int INVALID = 0;
    public static final String TAG = "LeftRightItem";
    public static final float TEXT_LEFT_MAX_RATE = 0.538f;
    public int bottomLineColorId;
    public int bottomLineMargin;
    public View content;
    public int contentMarginLeft;
    public int contentMarginRight;
    public boolean curSingleLine;
    public boolean isPwdHide;
    public View llRight;
    public boolean logCipher;
    public ViewTreeObserver.OnGlobalLayoutListener mLayoutListener;
    public RelayoutState mState;
    public boolean showBottomLine;
    public boolean showImgRight;
    public boolean showOnLog;
    public int textLeftColorId;
    public int textLeftId;
    public int textLeftSize;
    public int textRightColorId;
    public int textRightId;
    public int textRightSize;
    public TextView tvLeft;
    public TextView tvRight;
    public View vLineBottom;

    public enum RelayoutState {
        INITIAL(0),
        RELAYOUT(1),
        DO_NOTHING(2),
        CHANGE_WIDTH_L(3),
        CHANGE_HEIGHT_R(4),
        CHANGE_HEIGHT_R_3(5),
        BEFORE_COMPLETED(6),
        LAYOUT_COMPLETED(7);

        public int value;

        RelayoutState(int i8) {
            this.value = i8;
        }
    }

    public LeftRightItem(Context context) {
        super(context);
        this.textLeftId = 0;
        this.textRightId = 0;
        this.textLeftColorId = 0;
        this.textRightColorId = 0;
        this.textLeftSize = 0;
        this.textRightSize = 0;
        this.contentMarginLeft = 0;
        this.contentMarginRight = 0;
        this.bottomLineMargin = 0;
        this.bottomLineColorId = 0;
        this.showImgRight = true;
        this.showBottomLine = true;
        this.isPwdHide = true;
        this.logCipher = false;
        this.curSingleLine = false;
        this.showOnLog = true;
        this.mLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.byd.systemsettings.ui.widget.LeftRightItem.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (LeftRightItem.this.isShown()) {
                    LeftRightItem.this.multilingualStrategy();
                }
            }
        };
        initViews(context);
    }

    private void checkAndChangeParentHeight(TextView textView) {
        if (textView == null) {
            this.mState = RelayoutState.DO_NOTHING;
            return;
        }
        int lineCount = textView.getLineCount();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (lineCount <= 2) {
            int dimension = (int) textView.getResources().getDimension(R.dimen.dp_60);
            if (layoutParams.height > dimension) {
                layoutParams.height = dimension;
            }
            this.mState = RelayoutState.CHANGE_HEIGHT_R;
        } else {
            x3.a.a(TAG, "---checkAndChangeParentHeight---ItemTag: " + getItemTag() + ", LineCount: " + textView.getLineCount());
            layoutParams.height = (textView.getLineHeight() * lineCount) + (((int) textView.getResources().getDimension(R.dimen.dp_8)) * 2);
            this.mState = RelayoutState.CHANGE_HEIGHT_R_3;
        }
        if (layoutParams.height > 0) {
            setLayoutParams(layoutParams);
        }
        this.mState = RelayoutState.BEFORE_COMPLETED;
    }

    private boolean checkAndChangeWidth(TextView textView) {
        if (textView == null) {
            return false;
        }
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        ViewGroup viewGroup = (ViewGroup) textView.getParent();
        int measuredWidth = textView.getMeasuredWidth();
        float measuredWidth2 = viewGroup.getMeasuredWidth();
        if (measuredWidth / (1.0f * measuredWidth2) < 0.538f) {
            this.mState = RelayoutState.DO_NOTHING;
            return false;
        }
        this.mState = RelayoutState.CHANGE_WIDTH_L;
        layoutParams.width = (int) (measuredWidth2 * 0.538f);
        textView.setSingleLine(false);
        textView.setLayoutParams(layoutParams);
        return true;
    }

    private void setMargin(View view, int i8, int i9) {
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            if (i8 >= 0) {
                marginLayoutParams.setMarginStart(i8);
            }
            if (i9 >= 0) {
                marginLayoutParams.setMarginEnd(i9);
            }
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public String getItemTag() {
        StringBuilder sb = new StringBuilder();
        TextView textView = this.tvLeft;
        if (textView != null) {
            sb.append(textView.getText());
        }
        if (!sb.toString().isEmpty()) {
            sb.append("-->");
        }
        TextView textView2 = this.tvRight;
        if (textView2 != null) {
            String string = textView2.getText().toString();
            if (this.logCipher) {
                string = d.f(string);
            }
            if (!this.showOnLog) {
                string = "";
            }
            sb.append(string);
        }
        return sb.toString();
    }

    public int getLayoutId() {
        return getContext().getApplicationContext().getResources().getConfiguration().getLayoutDirection() == 1 ? R.layout.item_left_right_rtl : R.layout.item_left_right;
    }

    public TextView getTvRight() {
        return this.tvRight;
    }

    public void initAttrs(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.linear_item);
        this.textLeftId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.linear_item_textLeft, this.textLeftId);
        this.textRightId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.linear_item_textRight, this.textRightId);
        this.showImgRight = typedArrayObtainStyledAttributes.getBoolean(R.styleable.linear_item_showImgRight, this.showImgRight);
        this.showBottomLine = typedArrayObtainStyledAttributes.getBoolean(R.styleable.linear_item_showBottomLine, this.showBottomLine);
        this.textLeftColorId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.linear_item_textLeftColor, this.textLeftColorId);
        this.textRightColorId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.linear_item_textRightColor, this.textRightColorId);
        this.textLeftSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.linear_item_textLeftSize, this.textLeftSize);
        this.textRightSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.linear_item_textRightSize, this.textRightSize);
        this.contentMarginLeft = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.linear_item_contentMarginLeft, this.contentMarginLeft);
        this.contentMarginRight = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.linear_item_contentMarginRight, this.contentMarginRight);
        this.bottomLineMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.linear_item_bottomLineMargin, this.bottomLineMargin);
        this.bottomLineColorId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.linear_item_bottomLineColor, this.bottomLineColorId);
        typedArrayObtainStyledAttributes.recycle();
    }

    public void initSubViews() {
        View view = this.content;
        if (view == null) {
            return;
        }
        this.tvLeft = (TextView) view.findViewById(R.id.tv_left_item_left_right);
        this.tvRight = (TextView) this.content.findViewById(R.id.tv_right_item_left_right);
        this.llRight = this.content.findViewById(R.id.ll_right_item_left_right);
        this.vLineBottom = this.content.findViewById(R.id.v_line_botoom_item_left_right);
    }

    public void initViews(Context context) {
        this.content = LayoutInflater.from(context).inflate(getLayoutId(), (ViewGroup) null);
        initSubViews();
        Resources resources = getResources();
        int i8 = this.textLeftId;
        if (i8 != 0) {
            this.tvLeft.setText(resources.getString(i8));
        }
        int i9 = this.textRightId;
        if (i9 != 0) {
            this.tvRight.setText(resources.getString(i9));
        }
        int i10 = this.textLeftColorId;
        if (i10 != 0) {
            this.tvLeft.setTextColor(resources.getColor(i10));
        }
        int i11 = this.textRightColorId;
        if (i11 != 0) {
            this.tvRight.setTextColor(resources.getColor(i11));
        }
        int i12 = this.textLeftSize;
        if (i12 != 0) {
            this.tvLeft.setTextSize(0, i12);
        }
        int i13 = this.textRightSize;
        if (i13 != 0) {
            this.tvRight.setTextSize(0, i13);
        }
        int i14 = this.contentMarginLeft;
        if (i14 != 0) {
            setMargin(this.tvLeft, i14, -1);
        }
        int i15 = this.contentMarginRight;
        if (i15 != 0) {
            setMargin(this.llRight, -1, i15);
        }
        int i16 = this.bottomLineMargin;
        if (i16 != 0) {
            setMargin(this.vLineBottom, i16, i16);
        }
        int i17 = this.bottomLineColorId;
        if (i17 != 0) {
            this.vLineBottom.setBackgroundColor(resources.getColor(i17));
        }
        this.vLineBottom.setVisibility(this.showBottomLine ? 0 : 8);
        addView(this.content);
        this.mState = RelayoutState.INITIAL;
        getViewTreeObserver().addOnGlobalLayoutListener(this.mLayoutListener);
    }

    @Override // s2.a
    public /* bridge */ /* synthetic */ boolean isInvalid() {
        return super.isInvalid();
    }

    public boolean isRightTextHideType() {
        return this.isPwdHide;
    }

    public void multilingualStrategy() {
        RelayoutState relayoutState;
        x3.a.a(TAG, "---multilingualStrategy---mState: " + this.mState + ", ItemTag: " + getItemTag());
        RelayoutState relayoutState2 = this.mState;
        if (relayoutState2 == RelayoutState.DO_NOTHING || relayoutState2 == (relayoutState = RelayoutState.LAYOUT_COMPLETED)) {
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver != null) {
                viewTreeObserver.removeOnGlobalLayoutListener(this.mLayoutListener);
                return;
            }
            return;
        }
        checkAndChangeWidth(this.tvLeft);
        checkAndChangeParentHeight(this.tvRight);
        if (this.mState == RelayoutState.BEFORE_COMPLETED) {
            this.mState = relayoutState;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.removeOnGlobalLayoutListener(this.mLayoutListener);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        super.onLayout(z7, i8, i9, i10, i11);
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

    public void setLogCipher(boolean z7) {
        this.logCipher = z7;
    }

    public void setRightMaxLine(int i8) {
        TextView textView = this.tvRight;
        if (textView != null) {
            textView.setSingleLine(false);
            this.tvRight.setMaxLines(i8);
        }
    }

    public void setRightText(String str) {
        TextView textView;
        if (str == null || (textView = this.tvRight) == null || str.equals(textView.getText().toString().trim())) {
            return;
        }
        this.tvRight.setText(str);
        x3.a.a(TAG, "---setRightText---do relayout.");
        this.mState = RelayoutState.RELAYOUT;
        getViewTreeObserver().addOnGlobalLayoutListener(this.mLayoutListener);
        checkAndChangeParentHeight(this.tvRight);
    }

    public void setRightTextSingleLine() {
        TextView textView = this.tvRight;
        if (textView != null) {
            textView.setSingleLine(true);
            this.tvRight.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        }
    }

    public void setRightTextType(boolean z7) {
        TextView textView = this.tvRight;
        if (textView == null) {
            return;
        }
        this.isPwdHide = z7;
        if (z7) {
            textView.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            textView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }

    public void setShowOnLog(boolean z7) {
        this.showOnLog = z7;
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

    public LeftRightItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.textLeftId = 0;
        this.textRightId = 0;
        this.textLeftColorId = 0;
        this.textRightColorId = 0;
        this.textLeftSize = 0;
        this.textRightSize = 0;
        this.contentMarginLeft = 0;
        this.contentMarginRight = 0;
        this.bottomLineMargin = 0;
        this.bottomLineColorId = 0;
        this.showImgRight = true;
        this.showBottomLine = true;
        this.isPwdHide = true;
        this.logCipher = false;
        this.curSingleLine = false;
        this.showOnLog = true;
        this.mLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.byd.systemsettings.ui.widget.LeftRightItem.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (LeftRightItem.this.isShown()) {
                    LeftRightItem.this.multilingualStrategy();
                }
            }
        };
        initAttrs(context, attributeSet);
        initViews(context);
    }

    public LeftRightItem(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.textLeftId = 0;
        this.textRightId = 0;
        this.textLeftColorId = 0;
        this.textRightColorId = 0;
        this.textLeftSize = 0;
        this.textRightSize = 0;
        this.contentMarginLeft = 0;
        this.contentMarginRight = 0;
        this.bottomLineMargin = 0;
        this.bottomLineColorId = 0;
        this.showImgRight = true;
        this.showBottomLine = true;
        this.isPwdHide = true;
        this.logCipher = false;
        this.curSingleLine = false;
        this.showOnLog = true;
        this.mLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.byd.systemsettings.ui.widget.LeftRightItem.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (LeftRightItem.this.isShown()) {
                    LeftRightItem.this.multilingualStrategy();
                }
            }
        };
        initAttrs(context, attributeSet);
        initViews(context);
    }

    public LeftRightItem(Context context, AttributeSet attributeSet, int i8, int i9) {
        super(context, attributeSet, i8, i9);
        this.textLeftId = 0;
        this.textRightId = 0;
        this.textLeftColorId = 0;
        this.textRightColorId = 0;
        this.textLeftSize = 0;
        this.textRightSize = 0;
        this.contentMarginLeft = 0;
        this.contentMarginRight = 0;
        this.bottomLineMargin = 0;
        this.bottomLineColorId = 0;
        this.showImgRight = true;
        this.showBottomLine = true;
        this.isPwdHide = true;
        this.logCipher = false;
        this.curSingleLine = false;
        this.showOnLog = true;
        this.mLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.byd.systemsettings.ui.widget.LeftRightItem.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (LeftRightItem.this.isShown()) {
                    LeftRightItem.this.multilingualStrategy();
                }
            }
        };
        initAttrs(context, attributeSet);
        initViews(context);
    }
}
