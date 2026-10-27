package com.amazon.mp3.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TooltipView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001f2\u00020\u0001:\u0002 \u001fB\u0019\b\u0016\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001d\u0010\u001eJ\u0006\u0010\u0003\u001a\u00020\u0002J0\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0014R\"\u0010\r\u001a\u00020\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006!"}, d2 = {"Lcom/amazon/mp3/view/TooltipView;", "Landroid/widget/RelativeLayout;", "", "init", "", "changed", "", "l", "t", "r", "b", "onLayout", "Lcom/amazon/mp3/view/TooltipView$ArrowPosition;", "position", "Lcom/amazon/mp3/view/TooltipView$ArrowPosition;", "getPosition", "()Lcom/amazon/mp3/view/TooltipView$ArrowPosition;", "setPosition", "(Lcom/amazon/mp3/view/TooltipView$ArrowPosition;)V", "arrowOffset", "I", "getArrowOffset", "()I", "setArrowOffset", "(I)V", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Companion", "ArrowPosition", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class TooltipView extends RelativeLayout {
    private int arrowOffset;
    private ArrowPosition position;
    private static final String TAG = TooltipView.class.getSimpleName();
    private static final int DEFAULT_OFFSET = 20;

    /* JADX INFO: compiled from: TooltipView.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/view/TooltipView$ArrowPosition;", "", "(Ljava/lang/String;I)V", "TOP_LEFT", "TOP_CENTER", "TOP_RIGHT", "BOTTOM_LEFT", "BOTTOM_CENTER", "BOTTOM_RIGHT", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum ArrowPosition {
        TOP_LEFT,
        TOP_CENTER,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_CENTER,
        BOTTOM_RIGHT
    }

    /* JADX INFO: compiled from: TooltipView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ArrowPosition.values().length];
            iArr[ArrowPosition.TOP_LEFT.ordinal()] = 1;
            iArr[ArrowPosition.TOP_CENTER.ordinal()] = 2;
            iArr[ArrowPosition.TOP_RIGHT.ordinal()] = 3;
            iArr[ArrowPosition.BOTTOM_LEFT.ordinal()] = 4;
            iArr[ArrowPosition.BOTTOM_CENTER.ordinal()] = 5;
            iArr[ArrowPosition.BOTTOM_RIGHT.ordinal()] = 6;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final ArrowPosition getPosition() {
        return this.position;
    }

    public final void setPosition(ArrowPosition arrowPosition) {
        Intrinsics.checkNotNullParameter(arrowPosition, "<set-?>");
        this.position = arrowPosition;
    }

    public final int getArrowOffset() {
        return this.arrowOffset;
    }

    public final void setArrowOffset(int i) {
        this.arrowOffset = i;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TooltipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.position = ArrowPosition.TOP_RIGHT;
        this.arrowOffset = DEFAULT_OFFSET;
        init();
    }

    public final void init() {
        View.inflate(getContext(), R.layout.tooltip_displayer_view, this);
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        TextView textView = (TextView) findViewById(R.id.tooltip_displayer_text);
        ImageView imageView = (ImageView) findViewById(R.id.tooltip_rectangle);
        ImageView imageView2 = (ImageView) findViewById(R.id.tooltip_arrow_top);
        ImageView imageView3 = (ImageView) findViewById(R.id.tooltip_arrow_bottom);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        int dimension = (int) getResources().getDimension(R.dimen.spacing_20dp);
        int i = layoutParams2.width;
        layoutParams2.height = textView.getHeight() + dimension;
        imageView.setLayoutParams(layoutParams2);
        ViewGroup.LayoutParams layoutParams3 = imageView2.getLayoutParams();
        if (layoutParams3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        }
        RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) layoutParams3;
        ViewGroup.LayoutParams layoutParams5 = imageView3.getLayoutParams();
        if (layoutParams5 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        }
        RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) layoutParams5;
        switch (WhenMappings.$EnumSwitchMapping$0[this.position.ordinal()]) {
            case 1:
                imageView3.setVisibility(8);
                layoutParams4.addRule(18, imageView.getId());
                layoutParams4.setMarginStart(this.arrowOffset);
                imageView2.setLayoutParams(layoutParams4);
                return;
            case 2:
                imageView3.setVisibility(8);
                layoutParams4.addRule(18, imageView.getId());
                layoutParams4.setMarginStart(i / 2);
                imageView2.setLayoutParams(layoutParams4);
                return;
            case 3:
                imageView3.setVisibility(8);
                layoutParams4.addRule(19, imageView.getId());
                layoutParams4.setMarginEnd(this.arrowOffset);
                imageView2.setLayoutParams(layoutParams4);
                return;
            case 4:
                layoutParams2.setMargins(0, 0, 0, 0);
                imageView.setLayoutParams(layoutParams2);
                imageView2.setVisibility(8);
                layoutParams4.addRule(18, imageView.getId());
                layoutParams6.setMarginStart(this.arrowOffset);
                imageView3.setLayoutParams(layoutParams6);
                return;
            case 5:
                layoutParams2.setMargins(0, 0, 0, 0);
                imageView.setLayoutParams(layoutParams2);
                imageView2.setVisibility(8);
                layoutParams4.addRule(18, imageView.getId());
                layoutParams6.setMarginStart(i / 2);
                imageView3.setLayoutParams(layoutParams6);
                return;
            case 6:
                layoutParams2.setMargins(0, 0, 0, 0);
                imageView.setLayoutParams(layoutParams2);
                imageView2.setVisibility(8);
                layoutParams4.addRule(19, imageView.getId());
                layoutParams6.setMarginEnd(this.arrowOffset);
                imageView3.setLayoutParams(layoutParams6);
                return;
            default:
                return;
        }
    }
}
