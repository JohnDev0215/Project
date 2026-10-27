package org.chromium.components.web_contents_delegate_android;

import android.graphics.Point;
import android.graphics.RectF;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.content.browser.ContentViewCore;
import org.chromium.content.browser.RenderCoordinates;

/* JADX INFO: loaded from: classes3.dex */
class ValidationMessageBubble {
    private PopupWindow mPopup;

    private static ValidationMessageBubble createAndShow(ContentViewCore contentViewCore, int i, int i2, int i3, int i4, String str, String str2) {
        return new ValidationMessageBubble(contentViewCore, makePixRectInScreen(contentViewCore, i, i2, i3, i4), str, str2);
    }

    private ValidationMessageBubble(ContentViewCore contentViewCore, RectF rectF, String str, String str2) {
        ViewGroup viewGroup = (ViewGroup) View.inflate(contentViewCore.getContext(), R.layout.validation_message_bubble, null);
        this.mPopup = new PopupWindow(viewGroup);
        updateTextViews(viewGroup, str, str2);
        measure(contentViewCore.getRenderCoordinates());
        Point pointAdjustWindowPosition = adjustWindowPosition(contentViewCore, (int) (rectF.centerX() - getAnchorOffset()), (int) rectF.bottom);
        this.mPopup.showAtLocation(contentViewCore.getContainerView(), 0, pointAdjustWindowPosition.x, pointAdjustWindowPosition.y);
    }

    private void close() {
        PopupWindow popupWindow = this.mPopup;
        if (popupWindow == null) {
            return;
        }
        popupWindow.dismiss();
        this.mPopup = null;
    }

    private void setPositionRelativeToAnchor(ContentViewCore contentViewCore, int i, int i2, int i3, int i4) {
        RectF rectFMakePixRectInScreen = makePixRectInScreen(contentViewCore, i, i2, i3, i4);
        Point pointAdjustWindowPosition = adjustWindowPosition(contentViewCore, (int) (rectFMakePixRectInScreen.centerX() - getAnchorOffset()), (int) rectFMakePixRectInScreen.bottom);
        this.mPopup.update(pointAdjustWindowPosition.x, pointAdjustWindowPosition.y, this.mPopup.getWidth(), this.mPopup.getHeight());
    }

    private static RectF makePixRectInScreen(ContentViewCore contentViewCore, int i, int i2, int i3, int i4) {
        RenderCoordinates renderCoordinates = contentViewCore.getRenderCoordinates();
        float webViewOffsetYPixInScreen = getWebViewOffsetYPixInScreen(contentViewCore);
        return new RectF(renderCoordinates.fromLocalCssToPix(i), renderCoordinates.fromLocalCssToPix(i2) + webViewOffsetYPixInScreen, renderCoordinates.fromLocalCssToPix(i + i3), renderCoordinates.fromLocalCssToPix(i2 + i4) + webViewOffsetYPixInScreen);
    }

    private static float getWebViewOffsetYPixInScreen(ContentViewCore contentViewCore) {
        contentViewCore.getContainerView().getLocationOnScreen(new int[2]);
        return r0[1] + contentViewCore.getRenderCoordinates().getContentOffsetYPix();
    }

    private static void updateTextViews(ViewGroup viewGroup, String str, String str2) {
        ((TextView) viewGroup.findViewById(R.id.main_text)).setText(str);
        TextView textView = (TextView) viewGroup.findViewById(R.id.sub_text);
        if (!TextUtils.isEmpty(str2)) {
            textView.setText(str2);
        } else {
            ((ViewGroup) textView.getParent()).removeView(textView);
        }
    }

    private void measure(RenderCoordinates renderCoordinates) {
        this.mPopup.setHeight(-2);
        this.mPopup.setWidth(-2);
        this.mPopup.getContentView().setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        this.mPopup.getContentView().measure(View.MeasureSpec.makeMeasureSpec(renderCoordinates.getLastFrameViewportWidthPixInt(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(renderCoordinates.getLastFrameViewportHeightPixInt(), Integer.MIN_VALUE));
    }

    private float getAnchorOffset() {
        View contentView = this.mPopup.getContentView();
        int measuredWidth = contentView.getMeasuredWidth();
        int measuredWidth2 = contentView.findViewById(R.id.arrow_image).getMeasuredWidth();
        return ApiCompatibilityUtils.isLayoutRtl(contentView) ? ((measuredWidth * 3) / 4) - (measuredWidth2 / 2) : (measuredWidth / 4) + (measuredWidth2 / 2);
    }

    private Point adjustWindowPosition(ContentViewCore contentViewCore, int i, int i2) {
        RenderCoordinates renderCoordinates = contentViewCore.getRenderCoordinates();
        int lastFrameViewportWidthPixInt = renderCoordinates.getLastFrameViewportWidthPixInt();
        int webViewOffsetYPixInScreen = ((int) getWebViewOffsetYPixInScreen(contentViewCore)) + renderCoordinates.getLastFrameViewportHeightPixInt();
        int measuredWidth = this.mPopup.getContentView().getMeasuredWidth();
        int measuredHeight = this.mPopup.getContentView().getMeasuredHeight();
        if (i < 0) {
            i = 0;
        } else if (i + measuredWidth > lastFrameViewportWidthPixInt) {
            i = lastFrameViewportWidthPixInt - measuredWidth;
        }
        if (i2 + measuredHeight > webViewOffsetYPixInScreen) {
            i2 = webViewOffsetYPixInScreen - measuredHeight;
        }
        return new Point(i, i2);
    }
}
