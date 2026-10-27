package com.byd.systemsettings.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.Layout;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.byd.systemsettings.R;
import m4.d;
import s2.a;

/* JADX INFO: loaded from: classes2.dex */
public class LinearItem extends FrameLayout implements a {
    public static final int INVALID = 0;
    public static final int MAX_EXECUTE_STRATEGY = 10;
    public static final String TAG = "LinearItem";
    public static final float TEXT_LEFT_MAX_RATE = 0.389f;
    public int bottomLineColorId;
    public int bottomLineMargin;
    public View content;
    public int contentMarginLeft;
    public int contentMarginRight;
    public boolean curSingleLine;
    public TextViewHolder holderL;
    public TextViewHolder holderR;
    public boolean isPwdHide;
    public ImageView ivRight;
    public View llRight;
    public boolean logCipher;
    public ViewTreeObserver.OnGlobalLayoutListener mLayoutListener;
    public RelayoutState mState;
    public boolean pwdRight;
    public boolean showBottomLine;
    public boolean showImgRight;
    public boolean showOnLog;
    public int strategyExecuteCount;
    public int textLeftColorId;
    public int textLeftId;
    public int textLeftSize;
    public int textRightColorId;
    public int textRightId;
    public int textRightSize;
    public TextView tvLeft;
    public TextView tvRight;
    public View vLineBottom;

    /* JADX INFO: renamed from: com.byd.systemsettings.ui.widget.LinearItem$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$com$byd$systemsettings$ui$widget$LinearItem$RelayoutState;

        static {
            int[] iArr = new int[RelayoutState.values().length];
            $SwitchMap$com$byd$systemsettings$ui$widget$LinearItem$RelayoutState = iArr;
            try {
                iArr[RelayoutState.INITIAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$ui$widget$LinearItem$RelayoutState[RelayoutState.RELAYOUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$ui$widget$LinearItem$RelayoutState[RelayoutState.SINGLE_LINE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$ui$widget$LinearItem$RelayoutState[RelayoutState.MUTI_LINE_L.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$ui$widget$LinearItem$RelayoutState[RelayoutState.WRAP_LINE_WIDTH_L.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$ui$widget$LinearItem$RelayoutState[RelayoutState.MUTI_LINE_R.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public enum RelayoutState {
        INITIAL(0),
        RELAYOUT(1),
        DO_NOTHING_CHECKED_L(2),
        SINGLE_LINE(3),
        SINGLE_LINE_COMPLETED(4),
        MUTI_LINE_L(5),
        WRAP_LINE_WIDTH_L(6),
        DO_NOTHING_CHECKED_R(7),
        MUTI_LINE_R(8),
        WRAP_LINE_WIDTH_R(9),
        LAYOUT_COMPLETED(10);

        public int value;

        RelayoutState(int i8) {
            this.value = i8;
        }
    }

    public class TextViewHolder {
        public float lineMaxWidth;

        private TextViewHolder() {
            this.lineMaxWidth = 0.0f;
        }
    }

    public LinearItem(Context context) {
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
        this.showBottomLine = false;
        this.isPwdHide = true;
        this.pwdRight = false;
        this.logCipher = false;
        this.holderL = new TextViewHolder();
        this.holderR = new TextViewHolder();
        this.curSingleLine = false;
        this.strategyExecuteCount = 0;
        this.showOnLog = true;
        this.mLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.byd.systemsettings.ui.widget.LinearItem.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                LinearItem linearItem;
                int i8;
                if (!LinearItem.this.isShown() || (i8 = (linearItem = LinearItem.this).strategyExecuteCount) > 10) {
                    return;
                }
                linearItem.strategyExecuteCount = i8 + 1;
                linearItem.multilingualStrategy();
            }
        };
        initViews(context);
    }

    @SuppressLint({"NewApi"})
    private void changeTextViewWidthByMutiLine(TextView textView, int i8) {
        if (textView == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        if (textView == this.tvLeft) {
            this.mState = RelayoutState.MUTI_LINE_L;
        } else {
            this.mState = RelayoutState.MUTI_LINE_R;
        }
        textView.setSingleLine(false);
        int measuredWidth = (int) (i8 * 0.389f);
        TextView textView2 = this.tvLeft;
        if (textView != textView2) {
            measuredWidth = (measuredWidth * 2) - textView2.getMeasuredWidth();
        }
        layoutParams.width = measuredWidth;
        textView.setLayoutParams(layoutParams);
    }

    private void checkAndChangeWidth(TextView textView, TextView textView2) {
        if (textView == null || textView2 == null) {
            return;
        }
        textView.setSingleLine(true);
        textView2.setSingleLine(true);
        ViewGroup viewGroup = (ViewGroup) textView.getParent();
        float fMeasureText = textView.getPaint().measureText(textView.getText().toString());
        float fMeasureText2 = textView2.getPaint().measureText(textView2.getText().toString());
        float measuredWidth = textView2.getMeasuredWidth();
        int measuredWidth2 = viewGroup.getMeasuredWidth();
        float f8 = measuredWidth2 * 1.0f;
        if ((Math.max(measuredWidth, fMeasureText2) + fMeasureText) / f8 < 0.778f) {
            this.mState = RelayoutState.DO_NOTHING_CHECKED_L;
            return;
        }
        if (measuredWidth > fMeasureText2) {
            x3.a.a(TAG, "---checkAndChangeWidth---ItemTag: " + getItemTag() + ", viewWidthR: " + measuredWidth + ", measuredWidthR: " + fMeasureText2);
            resetSingleLine(this.tvLeft, this.tvRight);
            return;
        }
        float f9 = fMeasureText / f8;
        x3.a.a(TAG, "---checkAndChangeWidth---rateL: " + f9);
        if (f9 > 0.389f) {
            changeTextViewWidthByMutiLine(textView, measuredWidth2);
        } else {
            checkAndChangeWidthR(this.tvLeft, this.tvRight);
        }
    }

    private void checkAndChangeWidthR(TextView textView, TextView textView2) {
        if (textView == null || textView2 == null) {
            return;
        }
        textView2.setSingleLine(true);
        ViewGroup viewGroup = (ViewGroup) textView.getParent();
        float f8 = this.holderL.lineMaxWidth;
        float fMeasureText = textView2.getPaint().measureText(textView2.getText().toString());
        int measuredWidth = viewGroup.getMeasuredWidth();
        float f9 = measuredWidth * 1.0f;
        if ((f8 + fMeasureText) / f9 < 0.778f) {
            this.mState = RelayoutState.DO_NOTHING_CHECKED_R;
            return;
        }
        float f10 = fMeasureText / f9;
        x3.a.a(TAG, "---checkAndChangeWidthR---rateR: " + f10);
        if (f10 > 0.389f) {
            changeTextViewWidthByMutiLine(textView2, measuredWidth);
        }
    }

    private void checkAndWrapLineWidth(TextView textView, TextViewHolder textViewHolder) {
        if (textView == null || textView.getLayout() == null) {
            return;
        }
        Layout layout = textView.getLayout();
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        int lineCount = layout.getLineCount();
        if (lineCount > 1) {
            for (int i8 = 0; i8 < lineCount; i8++) {
                float lineWidth = textView.getLayout().getLineWidth(i8);
                if (textViewHolder.lineMaxWidth < lineWidth || lineWidth <= 0.0f) {
                    textViewHolder.lineMaxWidth = lineWidth;
                }
            }
            layoutParams.width = (int) textViewHolder.lineMaxWidth;
        } else {
            layoutParams.width = -2;
        }
        textView.setLayoutParams(layoutParams);
        if (textView == this.tvLeft) {
            this.mState = RelayoutState.WRAP_LINE_WIDTH_L;
        } else {
            this.mState = RelayoutState.WRAP_LINE_WIDTH_R;
        }
    }

    private void resetSingleLine(TextView textView, TextView textView2) {
        this.mState = RelayoutState.SINGLE_LINE;
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        layoutParams.width = -2;
        textView.setSingleLine(true);
        ViewGroup.LayoutParams layoutParams2 = textView2.getLayoutParams();
        layoutParams2.width = -2;
        textView2.setSingleLine(true);
        textView.setLayoutParams(layoutParams);
        textView2.setLayoutParams(layoutParams2);
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

    private boolean updateLineState(String str) {
        int marginStart;
        int width = ((ViewGroup) this.tvLeft.getParent()).getWidth();
        float fMeasureText = this.tvLeft.getPaint().measureText(this.tvLeft.getText().toString()) + this.tvRight.getPaint().measureText(str);
        ViewGroup.LayoutParams layoutParams = this.tvLeft.getLayoutParams();
        int marginStart2 = layoutParams instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams).getMarginStart() : 0;
        ViewGroup.LayoutParams layoutParams2 = this.llRight.getLayoutParams();
        if (layoutParams2 instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams2;
            marginStart = marginLayoutParams.getMarginStart() + marginLayoutParams.getMarginEnd();
        } else {
            marginStart = 0;
        }
        return fMeasureText < ((float) (width - ((this.ivRight.getWidth() + marginStart2) + marginStart)));
    }

    public boolean checkNothingTodo() {
        RelayoutState relayoutState = this.mState;
        if (relayoutState != RelayoutState.DO_NOTHING_CHECKED_L && relayoutState != RelayoutState.DO_NOTHING_CHECKED_R && relayoutState != RelayoutState.SINGLE_LINE_COMPLETED && relayoutState != RelayoutState.WRAP_LINE_WIDTH_R && relayoutState != RelayoutState.LAYOUT_COMPLETED) {
            return false;
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver == null) {
            return true;
        }
        viewTreeObserver.removeOnGlobalLayoutListener(this.mLayoutListener);
        return true;
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
        if (this.pwdRight) {
            return sb.toString();
        }
        TextView textView2 = this.tvRight;
        if (textView2 != null) {
            String string = textView2.getText().toString();
            if (this.logCipher) {
                string = d.f(string);
            }
            sb.append(string);
        }
        return sb.toString();
    }

    public int getLayoutId() {
        return R.layout.item_linear;
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
        this.tvLeft = (TextView) view.findViewById(R.id.tv_left_item_linear);
        this.tvRight = (TextView) this.content.findViewById(R.id.tv_right_item_linear);
        this.ivRight = (ImageView) this.content.findViewById(R.id.iv_right_item_linear);
        this.llRight = this.content.findViewById(R.id.ll_right_item_linear);
        this.vLineBottom = this.content.findViewById(R.id.v_line_botoom_item_linear);
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
        this.ivRight.setVisibility(this.showImgRight ? 0 : 8);
        this.vLineBottom.setVisibility(this.showBottomLine ? 0 : 8);
        addView(this.content);
        this.mState = RelayoutState.INITIAL;
        getViewTreeObserver().addOnGlobalLayoutListener(this.mLayoutListener);
    }

    public /* bridge */ /* synthetic */ boolean isInvalid() {
        return super.isInvalid();
    }

    public boolean isRightTextHideType() {
        return this.isPwdHide;
    }

    public void multilingualStrategy() {
        x3.a.a(TAG, "---multilingualStrategy---mState: " + this.mState + ", strategyExecuteCount: " + this.strategyExecuteCount + ", ItemTag: " + getItemTag());
        if (checkNothingTodo()) {
            return;
        }
        switch (AnonymousClass2.$SwitchMap$com$byd$systemsettings$ui$widget$LinearItem$RelayoutState[this.mState.ordinal()]) {
            case 1:
            case 2:
            case 3:
                checkAndChangeWidth(this.tvLeft, this.tvRight);
                break;
            case 4:
                checkAndWrapLineWidth(this.tvLeft, this.holderL);
                break;
            case 5:
                checkAndChangeWidthR(this.tvLeft, this.tvRight);
                break;
            case 6:
                checkAndWrapLineWidth(this.tvRight, this.holderR);
                break;
        }
        this.curSingleLine = updateLineState(this.tvRight.getText().toString());
        if (this.mState == RelayoutState.SINGLE_LINE) {
            this.mState = RelayoutState.SINGLE_LINE_COMPLETED;
        }
        if (this.mState == RelayoutState.WRAP_LINE_WIDTH_R) {
            this.mState = RelayoutState.LAYOUT_COMPLETED;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.removeOnGlobalLayoutListener(this.mLayoutListener);
        }
        this.strategyExecuteCount = 0;
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        super.onLayout(z7, i8, i9, i10, i11);
    }

    public /* bridge */ /* synthetic */ void registerInvalidStateChangedCallback(a.InterfaceC0117a interfaceC0117a) {
        super.registerInvalidStateChangedCallback(interfaceC0117a);
    }

    public /* bridge */ /* synthetic */ void setInvalid(boolean z7) {
        super.setInvalid(z7);
    }

    public /* bridge */ /* synthetic */ void setInvalid(boolean z7, boolean z8) {
        super.setInvalid(z7, z8);
    }

    public void setLeftText(String str) {
        TextView textView = this.tvLeft;
        if (textView == null || str == null) {
            return;
        }
        if (str.trim().equals(textView.getText().toString().trim())) {
            return;
        }
        this.strategyExecuteCount = 0;
        this.tvLeft.setText(str);
        this.mState = RelayoutState.RELAYOUT;
        requestLayout();
    }

    public void setLogCipher(boolean z7) {
        this.logCipher = z7;
    }

    public void setPwdRight(boolean z7) {
        this.pwdRight = z7;
    }

    public void setRightMaxLine(int i8) {
        TextView textView = this.tvRight;
        if (textView != null) {
            textView.setSingleLine(false);
            this.tvRight.setMaxLines(i8);
        }
    }

    public void setRightText(String str) {
        TextView textView = this.tvRight;
        if (textView == null || str == null || str.trim().equals(textView.getText().toString().trim())) {
            return;
        }
        this.strategyExecuteCount = 0;
        this.tvRight.setText(str);
        boolean zUpdateLineState = updateLineState(str);
        x3.a.a(TAG, "---setRightText---curSingleLine: " + this.curSingleLine + ", singleLine: " + zUpdateLineState + ", ItemTag: " + getItemTag());
        if (this.curSingleLine != zUpdateLineState) {
            this.mState = RelayoutState.RELAYOUT;
            getViewTreeObserver().addOnGlobalLayoutListener(this.mLayoutListener);
            requestLayout();
        }
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

    public /* bridge */ /* synthetic */ void unregisterInvalidStateChangedCallback() {
        super.unregisterInvalidStateChangedCallback();
    }

    public LinearItem(Context context, AttributeSet attributeSet) {
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
        this.showBottomLine = false;
        this.isPwdHide = true;
        this.pwdRight = false;
        this.logCipher = false;
        this.holderL = new TextViewHolder();
        this.holderR = new TextViewHolder();
        this.curSingleLine = false;
        this.strategyExecuteCount = 0;
        this.showOnLog = true;
        this.mLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.byd.systemsettings.ui.widget.LinearItem.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                LinearItem linearItem;
                int i8;
                if (!LinearItem.this.isShown() || (i8 = (linearItem = LinearItem.this).strategyExecuteCount) > 10) {
                    return;
                }
                linearItem.strategyExecuteCount = i8 + 1;
                linearItem.multilingualStrategy();
            }
        };
        initAttrs(context, attributeSet);
        initViews(context);
    }

    public LinearItem(Context context, AttributeSet attributeSet, int i8) {
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
        this.showBottomLine = false;
        this.isPwdHide = true;
        this.pwdRight = false;
        this.logCipher = false;
        this.holderL = new TextViewHolder();
        this.holderR = new TextViewHolder();
        this.curSingleLine = false;
        this.strategyExecuteCount = 0;
        this.showOnLog = true;
        this.mLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.byd.systemsettings.ui.widget.LinearItem.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                LinearItem linearItem;
                int i82;
                if (!LinearItem.this.isShown() || (i82 = (linearItem = LinearItem.this).strategyExecuteCount) > 10) {
                    return;
                }
                linearItem.strategyExecuteCount = i82 + 1;
                linearItem.multilingualStrategy();
            }
        };
        initAttrs(context, attributeSet);
        initViews(context);
    }

    public LinearItem(Context context, AttributeSet attributeSet, int i8, int i9) {
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
        this.showBottomLine = false;
        this.isPwdHide = true;
        this.pwdRight = false;
        this.logCipher = false;
        this.holderL = new TextViewHolder();
        this.holderR = new TextViewHolder();
        this.curSingleLine = false;
        this.strategyExecuteCount = 0;
        this.showOnLog = true;
        this.mLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.byd.systemsettings.ui.widget.LinearItem.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                LinearItem linearItem;
                int i82;
                if (!LinearItem.this.isShown() || (i82 = (linearItem = LinearItem.this).strategyExecuteCount) > 10) {
                    return;
                }
                linearItem.strategyExecuteCount = i82 + 1;
                linearItem.multilingualStrategy();
            }
        };
        initAttrs(context, attributeSet);
        initViews(context);
    }
}
