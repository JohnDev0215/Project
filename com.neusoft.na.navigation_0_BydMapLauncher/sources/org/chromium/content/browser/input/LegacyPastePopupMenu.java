package org.chromium.content.browser.input;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import org.chromium.content.browser.input.PastePopupMenu;

/* JADX INFO: loaded from: classes3.dex */
public class LegacyPastePopupMenu implements View.OnClickListener, PastePopupMenu {
    private final PopupWindow mContainer;
    private final Context mContext;
    private final PastePopupMenu.PastePopupMenuDelegate mDelegate;
    private final int mLineOffsetY;
    private final View mParent;
    private View mPasteView;
    private final int mPasteViewLayout;
    private int mRawPositionX;
    private int mRawPositionY;
    private int mStatusBarHeight;
    private final int mWidthOffsetX;

    public LegacyPastePopupMenu(Context context, View view, final PastePopupMenu.PastePopupMenuDelegate pastePopupMenuDelegate) {
        this.mParent = view;
        this.mDelegate = pastePopupMenuDelegate;
        this.mContext = context;
        PopupWindow popupWindow = new PopupWindow(context, (AttributeSet) null, R.attr.textSelectHandleWindowStyle);
        this.mContainer = popupWindow;
        popupWindow.setSplitTouchEnabled(true);
        popupWindow.setClippingEnabled(false);
        popupWindow.setAnimationStyle(0);
        popupWindow.setWidth(-2);
        popupWindow.setHeight(-2);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: org.chromium.content.browser.input.LegacyPastePopupMenu.1
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                pastePopupMenuDelegate.onDismiss();
            }
        });
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{R.attr.textEditPasteWindowLayout});
        this.mPasteViewLayout = typedArrayObtainStyledAttributes.getResourceId(typedArrayObtainStyledAttributes.getIndex(0), 0);
        typedArrayObtainStyledAttributes.recycle();
        this.mLineOffsetY = (int) TypedValue.applyDimension(1, 5.0f, context.getResources().getDisplayMetrics());
        this.mWidthOffsetX = (int) TypedValue.applyDimension(1, 30.0f, context.getResources().getDisplayMetrics());
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            this.mStatusBarHeight = context.getResources().getDimensionPixelSize(identifier);
        }
    }

    @Override // org.chromium.content.browser.input.PastePopupMenu
    public void show(int i, int i2) {
        updateContent();
        positionAt(i, i2);
    }

    @Override // org.chromium.content.browser.input.PastePopupMenu
    public void hide() {
        this.mContainer.dismiss();
    }

    @Override // org.chromium.content.browser.input.PastePopupMenu
    public boolean isShowing() {
        return this.mContainer.isShowing();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        paste();
        hide();
    }

    private void positionAt(int i, int i2) {
        int iMin;
        if (this.mRawPositionX == i && this.mRawPositionY == i2 && isShowing()) {
            return;
        }
        this.mRawPositionX = i;
        this.mRawPositionY = i2;
        View contentView = this.mContainer.getContentView();
        int measuredWidth = contentView.getMeasuredWidth();
        int measuredHeight = contentView.getMeasuredHeight();
        int i3 = (int) (i - (measuredWidth / 2.0f));
        int i4 = (i2 - measuredHeight) - this.mLineOffsetY;
        int i5 = this.mParent.getSystemUiVisibility() == 0 ? this.mStatusBarHeight : 0;
        int i6 = this.mContext.getResources().getDisplayMetrics().widthPixels;
        if (i4 < i5) {
            i4 = i4 + measuredHeight + this.mLineOffsetY;
            int i7 = this.mWidthOffsetX / 2;
            if (i + measuredWidth < i6) {
                iMin = i3 + i7 + (measuredWidth / 2);
            } else {
                iMin = i3 - (i7 + (measuredWidth / 2));
            }
        } else {
            iMin = Math.min(i6 - measuredWidth, Math.max(0, i3));
        }
        int[] iArr = new int[2];
        this.mParent.getLocationInWindow(iArr);
        int i8 = iMin + iArr[0];
        int i9 = i4 + iArr[1];
        if (!isShowing()) {
            this.mContainer.showAtLocation(this.mParent, 0, i8, i9);
        } else {
            this.mContainer.update(i8, i9, -1, -1);
        }
    }

    private void updateContent() {
        if (this.mPasteView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
            if (layoutInflater != null) {
                this.mPasteView = layoutInflater.inflate(this.mPasteViewLayout, (ViewGroup) null);
            }
            if (this.mPasteView == null) {
                throw new IllegalArgumentException("Unable to inflate TextEdit paste window");
            }
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            this.mPasteView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            this.mPasteView.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            this.mPasteView.setOnClickListener(this);
        }
        this.mContainer.setContentView(this.mPasteView);
    }

    private void paste() {
        this.mDelegate.paste();
    }
}
