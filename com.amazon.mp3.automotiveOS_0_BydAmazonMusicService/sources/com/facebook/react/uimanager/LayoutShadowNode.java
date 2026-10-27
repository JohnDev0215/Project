package com.facebook.react.uimanager;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaDisplay;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaOverflow;
import com.facebook.yoga.YogaPositionType;
import com.facebook.yoga.YogaUnit;
import com.facebook.yoga.YogaWrap;
import com.google.firebase.perf.plugin.util.AsmString;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: loaded from: classes6.dex */
public class LayoutShadowNode extends ReactShadowNodeImpl {
    boolean mCollapsable;
    private final MutableYogaValue mTempYogaValue = new MutableYogaValue(null);

    @ReactProp(name = "onPointerEnter")
    public void setShouldNotifyPointerEnter(boolean z) {
    }

    @ReactProp(name = "onPointerLeave")
    public void setShouldNotifyPointerLeave(boolean z) {
    }

    @ReactProp(name = "onPointerMove")
    public void setShouldNotifyPointerMove(boolean z) {
    }

    private static class MutableYogaValue {
        YogaUnit unit;
        float value;

        /* synthetic */ MutableYogaValue(AnonymousClass1 anonymousClass1) {
            this();
        }

        private MutableYogaValue() {
        }

        void setFromDynamic(Dynamic dynamic) {
            if (dynamic.isNull()) {
                this.unit = YogaUnit.UNDEFINED;
                this.value = Float.NaN;
                return;
            }
            if (dynamic.getType() == ReadableType.String) {
                String strAsString = dynamic.asString();
                if (strAsString.equals(DebugKt.DEBUG_PROPERTY_VALUE_AUTO)) {
                    this.unit = YogaUnit.AUTO;
                    this.value = Float.NaN;
                    return;
                } else if (strAsString.endsWith("%")) {
                    this.unit = YogaUnit.PERCENT;
                    this.value = Float.parseFloat(strAsString.substring(0, strAsString.length() - 1));
                    return;
                } else {
                    throw new IllegalArgumentException("Unknown value: " + strAsString);
                }
            }
            this.unit = YogaUnit.POINT;
            this.value = PixelUtil.toPixelFromDIP(dynamic.asDouble());
        }
    }

    @ReactProp(name = "width")
    public void setWidth(Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        this.mTempYogaValue.setFromDynamic(dynamic);
        int i = AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaUnit[this.mTempYogaValue.unit.ordinal()];
        if (i == 1 || i == 2) {
            setStyleWidth(this.mTempYogaValue.value);
        } else if (i == 3) {
            setStyleWidthAuto();
        } else if (i == 4) {
            setStyleWidthPercent(this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    /* JADX INFO: renamed from: com.facebook.react.uimanager.LayoutShadowNode$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$yoga$YogaUnit;

        static {
            int[] iArr = new int[YogaUnit.values().length];
            $SwitchMap$com$facebook$yoga$YogaUnit = iArr;
            try {
                iArr[YogaUnit.POINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaUnit[YogaUnit.UNDEFINED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaUnit[YogaUnit.AUTO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaUnit[YogaUnit.PERCENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @ReactProp(name = "minWidth")
    public void setMinWidth(Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        this.mTempYogaValue.setFromDynamic(dynamic);
        int i = AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaUnit[this.mTempYogaValue.unit.ordinal()];
        if (i == 1 || i == 2) {
            setStyleMinWidth(this.mTempYogaValue.value);
        } else if (i == 4) {
            setStyleMinWidthPercent(this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @ReactProp(name = "collapsable")
    public void setCollapsable(boolean z) {
        this.mCollapsable = z;
    }

    @ReactProp(name = "maxWidth")
    public void setMaxWidth(Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        this.mTempYogaValue.setFromDynamic(dynamic);
        int i = AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaUnit[this.mTempYogaValue.unit.ordinal()];
        if (i == 1 || i == 2) {
            setStyleMaxWidth(this.mTempYogaValue.value);
        } else if (i == 4) {
            setStyleMaxWidthPercent(this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @ReactProp(name = "height")
    public void setHeight(Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        this.mTempYogaValue.setFromDynamic(dynamic);
        int i = AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaUnit[this.mTempYogaValue.unit.ordinal()];
        if (i == 1 || i == 2) {
            setStyleHeight(this.mTempYogaValue.value);
        } else if (i == 3) {
            setStyleHeightAuto();
        } else if (i == 4) {
            setStyleHeightPercent(this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @ReactProp(name = "minHeight")
    public void setMinHeight(Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        this.mTempYogaValue.setFromDynamic(dynamic);
        int i = AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaUnit[this.mTempYogaValue.unit.ordinal()];
        if (i == 1 || i == 2) {
            setStyleMinHeight(this.mTempYogaValue.value);
        } else if (i == 4) {
            setStyleMinHeightPercent(this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @ReactProp(name = "maxHeight")
    public void setMaxHeight(Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        this.mTempYogaValue.setFromDynamic(dynamic);
        int i = AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaUnit[this.mTempYogaValue.unit.ordinal()];
        if (i == 1 || i == 2) {
            setStyleMaxHeight(this.mTempYogaValue.value);
        } else if (i == 4) {
            setStyleMaxHeightPercent(this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    @ReactProp(defaultFloat = 0.0f, name = "flex")
    public void setFlex(float f) {
        if (isVirtual()) {
            return;
        }
        super.setFlex(f);
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    @ReactProp(defaultFloat = 0.0f, name = "flexGrow")
    public void setFlexGrow(float f) {
        if (isVirtual()) {
            return;
        }
        super.setFlexGrow(f);
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    @ReactProp(defaultFloat = FloatCompanionObject.NaN, name = "rowGap")
    public void setRowGap(float f) {
        if (isVirtual()) {
            return;
        }
        super.setRowGap(PixelUtil.toPixelFromDIP(f));
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    @ReactProp(defaultFloat = FloatCompanionObject.NaN, name = "columnGap")
    public void setColumnGap(float f) {
        if (isVirtual()) {
            return;
        }
        super.setColumnGap(PixelUtil.toPixelFromDIP(f));
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    @ReactProp(defaultFloat = FloatCompanionObject.NaN, name = "gap")
    public void setGap(float f) {
        if (isVirtual()) {
            return;
        }
        super.setGap(PixelUtil.toPixelFromDIP(f));
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    @ReactProp(defaultFloat = 0.0f, name = "flexShrink")
    public void setFlexShrink(float f) {
        if (isVirtual()) {
            return;
        }
        super.setFlexShrink(f);
    }

    @ReactProp(name = "flexBasis")
    public void setFlexBasis(Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        this.mTempYogaValue.setFromDynamic(dynamic);
        int i = AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaUnit[this.mTempYogaValue.unit.ordinal()];
        if (i == 1 || i == 2) {
            setFlexBasis(this.mTempYogaValue.value);
        } else if (i == 3) {
            setFlexBasisAuto();
        } else if (i == 4) {
            setFlexBasisPercent(this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @ReactProp(defaultFloat = FloatCompanionObject.NaN, name = "aspectRatio")
    public void setAspectRatio(float f) {
        setStyleAspectRatio(f);
    }

    @ReactProp(name = "flexDirection")
    public void setFlexDirection(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setFlexDirection(YogaFlexDirection.COLUMN);
            return;
        }
        switch (str) {
            case "row-reverse":
                setFlexDirection(YogaFlexDirection.ROW_REVERSE);
                return;
            case "column":
                setFlexDirection(YogaFlexDirection.COLUMN);
                return;
            case "row":
                setFlexDirection(YogaFlexDirection.ROW);
                return;
            case "column-reverse":
                setFlexDirection(YogaFlexDirection.COLUMN_REVERSE);
                return;
            default:
                throw new JSApplicationIllegalArgumentException("invalid value for flexDirection: " + str);
        }
    }

    @ReactProp(name = "flexWrap")
    public void setFlexWrap(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setFlexWrap(YogaWrap.NO_WRAP);
            return;
        }
        switch (str) {
            case "nowrap":
                setFlexWrap(YogaWrap.NO_WRAP);
                return;
            case "wrap-reverse":
                setFlexWrap(YogaWrap.WRAP_REVERSE);
                return;
            case "wrap":
                setFlexWrap(YogaWrap.WRAP);
                return;
            default:
                throw new JSApplicationIllegalArgumentException("invalid value for flexWrap: " + str);
        }
    }

    @ReactProp(name = "alignSelf")
    public void setAlignSelf(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setAlignSelf(YogaAlign.AUTO);
            return;
        }
        switch (str) {
            case "stretch":
                setAlignSelf(YogaAlign.STRETCH);
                return;
            case "baseline":
                setAlignSelf(YogaAlign.BASELINE);
                return;
            case "center":
                setAlignSelf(YogaAlign.CENTER);
                return;
            case "flex-start":
                setAlignSelf(YogaAlign.FLEX_START);
                return;
            case "auto":
                setAlignSelf(YogaAlign.AUTO);
                return;
            case "space-between":
                setAlignSelf(YogaAlign.SPACE_BETWEEN);
                return;
            case "flex-end":
                setAlignSelf(YogaAlign.FLEX_END);
                return;
            case "space-around":
                setAlignSelf(YogaAlign.SPACE_AROUND);
                return;
            default:
                throw new JSApplicationIllegalArgumentException("invalid value for alignSelf: " + str);
        }
    }

    @ReactProp(name = "alignItems")
    public void setAlignItems(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setAlignItems(YogaAlign.STRETCH);
            return;
        }
        switch (str) {
            case "stretch":
                setAlignItems(YogaAlign.STRETCH);
                return;
            case "baseline":
                setAlignItems(YogaAlign.BASELINE);
                return;
            case "center":
                setAlignItems(YogaAlign.CENTER);
                return;
            case "flex-start":
                setAlignItems(YogaAlign.FLEX_START);
                return;
            case "auto":
                setAlignItems(YogaAlign.AUTO);
                return;
            case "space-between":
                setAlignItems(YogaAlign.SPACE_BETWEEN);
                return;
            case "flex-end":
                setAlignItems(YogaAlign.FLEX_END);
                return;
            case "space-around":
                setAlignItems(YogaAlign.SPACE_AROUND);
                return;
            default:
                throw new JSApplicationIllegalArgumentException("invalid value for alignItems: " + str);
        }
    }

    @ReactProp(name = "alignContent")
    public void setAlignContent(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setAlignContent(YogaAlign.FLEX_START);
            return;
        }
        switch (str) {
            case "stretch":
                setAlignContent(YogaAlign.STRETCH);
                return;
            case "baseline":
                setAlignContent(YogaAlign.BASELINE);
                return;
            case "center":
                setAlignContent(YogaAlign.CENTER);
                return;
            case "flex-start":
                setAlignContent(YogaAlign.FLEX_START);
                return;
            case "auto":
                setAlignContent(YogaAlign.AUTO);
                return;
            case "space-between":
                setAlignContent(YogaAlign.SPACE_BETWEEN);
                return;
            case "flex-end":
                setAlignContent(YogaAlign.FLEX_END);
                return;
            case "space-around":
                setAlignContent(YogaAlign.SPACE_AROUND);
                return;
            default:
                throw new JSApplicationIllegalArgumentException("invalid value for alignContent: " + str);
        }
    }

    @ReactProp(name = "justifyContent")
    public void setJustifyContent(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setJustifyContent(YogaJustify.FLEX_START);
            return;
        }
        switch (str) {
            case "center":
                setJustifyContent(YogaJustify.CENTER);
                return;
            case "flex-start":
                setJustifyContent(YogaJustify.FLEX_START);
                return;
            case "space-between":
                setJustifyContent(YogaJustify.SPACE_BETWEEN);
                return;
            case "flex-end":
                setJustifyContent(YogaJustify.FLEX_END);
                return;
            case "space-around":
                setJustifyContent(YogaJustify.SPACE_AROUND);
                return;
            case "space-evenly":
                setJustifyContent(YogaJustify.SPACE_EVENLY);
                return;
            default:
                throw new JSApplicationIllegalArgumentException("invalid value for justifyContent: " + str);
        }
    }

    @ReactProp(name = "overflow")
    public void setOverflow(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setOverflow(YogaOverflow.VISIBLE);
            return;
        }
        switch (str) {
            case "hidden":
                setOverflow(YogaOverflow.HIDDEN);
                return;
            case "scroll":
                setOverflow(YogaOverflow.SCROLL);
                return;
            case "visible":
                setOverflow(YogaOverflow.VISIBLE);
                return;
            default:
                throw new JSApplicationIllegalArgumentException("invalid value for overflow: " + str);
        }
    }

    @ReactProp(name = "display")
    public void setDisplay(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setDisplay(YogaDisplay.FLEX);
            return;
        }
        if (str.equals("flex")) {
            setDisplay(YogaDisplay.FLEX);
        } else {
            if (str.equals("none")) {
                setDisplay(YogaDisplay.NONE);
                return;
            }
            throw new JSApplicationIllegalArgumentException("invalid value for display: " + str);
        }
    }

    @ReactPropGroup(names = {"margin", "marginVertical", "marginHorizontal", "marginStart", "marginEnd", "marginTop", "marginBottom", "marginLeft", "marginRight"})
    public void setMargins(int i, Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        int iMaybeTransformLeftRightToStartEnd = maybeTransformLeftRightToStartEnd(ViewProps.PADDING_MARGIN_SPACING_TYPES[i]);
        this.mTempYogaValue.setFromDynamic(dynamic);
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaUnit[this.mTempYogaValue.unit.ordinal()];
        if (i2 == 1 || i2 == 2) {
            setMargin(iMaybeTransformLeftRightToStartEnd, this.mTempYogaValue.value);
        } else if (i2 == 3) {
            setMarginAuto(iMaybeTransformLeftRightToStartEnd);
        } else if (i2 == 4) {
            setMarginPercent(iMaybeTransformLeftRightToStartEnd, this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @ReactPropGroup(names = {"padding", "paddingVertical", "paddingHorizontal", "paddingStart", "paddingEnd", "paddingTop", "paddingBottom", "paddingLeft", "paddingRight"})
    public void setPaddings(int i, Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        int iMaybeTransformLeftRightToStartEnd = maybeTransformLeftRightToStartEnd(ViewProps.PADDING_MARGIN_SPACING_TYPES[i]);
        this.mTempYogaValue.setFromDynamic(dynamic);
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaUnit[this.mTempYogaValue.unit.ordinal()];
        if (i2 == 1 || i2 == 2) {
            setPadding(iMaybeTransformLeftRightToStartEnd, this.mTempYogaValue.value);
        } else if (i2 == 4) {
            setPaddingPercent(iMaybeTransformLeftRightToStartEnd, this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @ReactPropGroup(defaultFloat = FloatCompanionObject.NaN, names = {"borderWidth", "borderStartWidth", "borderEndWidth", "borderTopWidth", "borderBottomWidth", "borderLeftWidth", "borderRightWidth"})
    public void setBorderWidths(int i, float f) {
        if (isVirtual()) {
            return;
        }
        setBorder(maybeTransformLeftRightToStartEnd(ViewProps.BORDER_SPACING_TYPES[i]), PixelUtil.toPixelFromDIP(f));
    }

    @ReactPropGroup(names = {AsmString.METHOD_FIREBASE_PERF_TRACE_START, "end", "left", "right", "top", "bottom"})
    public void setPositionValues(int i, Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        int iMaybeTransformLeftRightToStartEnd = maybeTransformLeftRightToStartEnd(new int[]{4, 5, 0, 2, 1, 3}[i]);
        this.mTempYogaValue.setFromDynamic(dynamic);
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaUnit[this.mTempYogaValue.unit.ordinal()];
        if (i2 == 1 || i2 == 2) {
            setPosition(iMaybeTransformLeftRightToStartEnd, this.mTempYogaValue.value);
        } else if (i2 == 4) {
            setPositionPercent(iMaybeTransformLeftRightToStartEnd, this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    private int maybeTransformLeftRightToStartEnd(int i) {
        if (!I18nUtil.getInstance().doLeftAndRightSwapInRTL(getThemedContext())) {
            return i;
        }
        if (i == 0) {
            return 4;
        }
        if (i != 2) {
            return i;
        }
        return 5;
    }

    @ReactProp(name = "position")
    public void setPosition(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setPositionType(YogaPositionType.RELATIVE);
            return;
        }
        switch (str) {
            case "static":
                setPositionType(YogaPositionType.STATIC);
                return;
            case "relative":
                setPositionType(YogaPositionType.RELATIVE);
                return;
            case "absolute":
                setPositionType(YogaPositionType.ABSOLUTE);
                return;
            default:
                throw new JSApplicationIllegalArgumentException("invalid value for position: " + str);
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl
    @ReactProp(name = "onLayout")
    public void setShouldNotifyOnLayout(boolean z) {
        super.setShouldNotifyOnLayout(z);
    }
}
