package com.amazon.podcast.views;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$integer;
import com.amazon.podcast.R$layout;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class ToolTipView {
    private final View contentView;
    private final ToolTipOrientation direction;
    private final boolean showCloseButton;
    private final TextView toolTipTextView;
    private final PopupWindow toolTipView;
    private final int TOOLTIP_DISPLAY_DURATION = 2000;
    private final int TOOLTIP_MAX_WIDTH = 450;
    private final Handler handler = new Handler();

    public enum ToolTipOrientation {
        TOP,
        BOTTOM
    }

    public ToolTipView(final Context context, final ToolTipOrientation direction, final boolean showCloseButton, final boolean multilineText) {
        this.direction = direction;
        this.showCloseButton = showCloseButton;
        int integer = context.getResources().getInteger(R$integer.tooltip_width_layout_params);
        PopupWindow popupWindow = new PopupWindow(context);
        this.toolTipView = popupWindow;
        popupWindow.setHeight(-2);
        popupWindow.setWidth(integer);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(false);
        popupWindow.setBackgroundDrawable(null);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        int i = R$layout.tooltip_view_top;
        int i2 = AnonymousClass1.$SwitchMap$com$amazon$podcast$views$ToolTipView$ToolTipOrientation[direction.ordinal()];
        if (i2 != 1 && i2 == 2) {
            i = R$layout.onboarding_view_bottom;
        }
        View viewInflate = layoutInflater.inflate(i, (ViewGroup) null);
        this.contentView = viewInflate;
        viewInflate.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.ToolTipView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$0(view);
            }
        });
        TextView textView = (TextView) viewInflate.findViewById(R$id.tooltip_text);
        this.toolTipTextView = textView;
        if (multilineText) {
            textView.setMaxWidth(450);
        }
        popupWindow.setContentView(viewInflate);
        ImageView imageView = (ImageView) viewInflate.findViewById(R$id.exit_view);
        if (!showCloseButton || imageView == null) {
            return;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.ToolTipView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$1(view);
            }
        });
        imageView.setVisibility(0);
    }

    /* JADX INFO: renamed from: com.amazon.podcast.views.ToolTipView$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$podcast$views$ToolTipView$ToolTipOrientation;

        static {
            int[] iArr = new int[ToolTipOrientation.values().length];
            $SwitchMap$com$amazon$podcast$views$ToolTipView$ToolTipOrientation = iArr;
            try {
                iArr[ToolTipOrientation.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$podcast$views$ToolTipView$ToolTipOrientation[ToolTipOrientation.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view) {
        dismissToolTip();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(View view) {
        dismissToolTip();
    }

    public void bind(final String toolTipText) {
        this.toolTipTextView.setText(toolTipText);
    }

    public boolean showToolTipForView(final View view) {
        int iHeight;
        int iCenterX;
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        if (iArr[0] == 0 && iArr[1] == 0) {
            return false;
        }
        this.contentView.measure(-2, -2);
        int i = iArr[0];
        Rect rect = new Rect(i, iArr[1], view.getWidth() + i, iArr[1] + view.getHeight());
        int measuredWidth = this.contentView.getMeasuredWidth();
        int measuredHeight = this.contentView.getMeasuredHeight();
        int i2 = AnonymousClass1.$SwitchMap$com$amazon$podcast$views$ToolTipView$ToolTipOrientation[this.direction.ordinal()];
        if (i2 == 1) {
            int iCenterX2 = rect.centerX() - (measuredWidth / 2);
            iHeight = rect.top - measuredHeight;
            iCenterX = iCenterX2;
        } else if (i2 != 2) {
            iHeight = 0;
            iCenterX = 0;
        } else {
            iCenterX = rect.centerX() - (measuredWidth / 2);
            iHeight = rect.bottom - (rect.height() / 2);
        }
        this.toolTipView.showAtLocation(view, 0, iCenterX, iHeight);
        if (!this.showCloseButton) {
            Handler handler = this.handler;
            final PopupWindow popupWindow = this.toolTipView;
            Objects.requireNonNull(popupWindow);
            handler.postDelayed(new Runnable() { // from class: com.amazon.podcast.views.ToolTipView$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    popupWindow.dismiss();
                }
            }, 2000L);
        }
        return true;
    }

    public void dismissToolTip() {
        PopupWindow popupWindow = this.toolTipView;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    public Boolean isDismissed() {
        return Boolean.valueOf(!this.toolTipView.isShowing());
    }
}
