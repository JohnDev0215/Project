package com.amazon.podcast.views;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.PopupWindow;
import com.amazon.podcast.R$dimen;

/* JADX INFO: loaded from: classes5.dex */
public final class PopUpUtils {
    public static boolean shouldUsePopUp(final Resources resources) {
        int i = resources.getConfiguration().orientation;
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        float fConvertPixelsToDp = convertPixelsToDp(displayMetrics.widthPixels, resources);
        return (i == 1 && (fConvertPixelsToDp > 480.0f ? 1 : (fConvertPixelsToDp == 480.0f ? 0 : -1)) > 0) || (i == 2 && (fConvertPixelsToDp > 480.0f ? 1 : (fConvertPixelsToDp == 480.0f ? 0 : -1)) > 0 && (convertPixelsToDp((float) displayMetrics.heightPixels, resources) > 540.0f ? 1 : (convertPixelsToDp((float) displayMetrics.heightPixels, resources) == 540.0f ? 0 : -1)) > 0);
    }

    public static void placeRefinementPopUpWindow(final View source, final PopupWindow refinementOptionsPopUp, final int popUpWidth, final int popUpHeight) {
        boolean zOutOfBoundsRightFromLeft = outOfBoundsRightFromLeft(source, popUpWidth);
        boolean zOutOfBoundsLeftFromRight = outOfBoundsLeftFromRight(source, popUpWidth);
        boolean zOutOfBoundsBottom = outOfBoundsBottom(source, popUpHeight);
        boolean zOutOfBoundsTop = outOfBoundsTop(source, popUpHeight);
        if (!zOutOfBoundsBottom && !zOutOfBoundsRightFromLeft) {
            placePopUpToBottomLeft(source, refinementOptionsPopUp);
            return;
        }
        if (!zOutOfBoundsBottom && !zOutOfBoundsLeftFromRight) {
            placePopUpToBottomRight(source, refinementOptionsPopUp);
            return;
        }
        if (!zOutOfBoundsTop && !zOutOfBoundsRightFromLeft) {
            placePopUpTopLeft(source, refinementOptionsPopUp, popUpWidth, popUpHeight);
        } else if (!zOutOfBoundsTop && !zOutOfBoundsLeftFromRight) {
            placePopUpTopRight(source, refinementOptionsPopUp, popUpHeight);
        } else {
            placePopUpCenterScreen(source, refinementOptionsPopUp);
        }
    }

    public static void placeContextMenuPopUpWindow(final View source, final PopupWindow popup, final int popUpWidth, final int popUpHeight) {
        boolean zOutOfBoundsRight = outOfBoundsRight(source, popUpWidth);
        boolean zOutOfBoundsLeft = outOfBoundsLeft(source, popUpWidth);
        boolean zOutOfBoundsBottom = outOfBoundsBottom(source, popUpHeight);
        boolean zOutOfBoundsTop = outOfBoundsTop(source, popUpHeight);
        if (!zOutOfBoundsBottom && !zOutOfBoundsLeft) {
            placePopUpBottomLeft(source, popup, popUpWidth);
            return;
        }
        if (!zOutOfBoundsTop && !zOutOfBoundsLeft) {
            placePopUpTopLeft(source, popup, popUpWidth, popUpHeight);
            return;
        }
        if (!zOutOfBoundsBottom && !zOutOfBoundsRight) {
            placePopUpBottomRight(source, popup);
            return;
        }
        if (!zOutOfBoundsTop && !zOutOfBoundsRight) {
            placePopUpTopRight(source, popup, popUpHeight);
            return;
        }
        if (!zOutOfBoundsLeft) {
            placePopUpLeft(source, popup, popUpWidth, popUpHeight);
            return;
        }
        if (!zOutOfBoundsRight) {
            placePopUpRight(source, popup, popUpHeight);
            return;
        }
        if (!zOutOfBoundsBottom) {
            placePopUpBottom(source, popup, popUpHeight);
        } else if (!zOutOfBoundsTop) {
            placePopUpTop(source, popup, popUpWidth, popUpHeight);
        } else {
            placePopUpCenterScreen(source, popup);
        }
    }

    public static void placePtcBottomSheetPopUpWindow(final View source, final PopupWindow popup, final int popUpWidth, final int popUpHeight) {
        boolean zOutOfBoundsRight = outOfBoundsRight(source, popUpWidth);
        boolean zOutOfBoundsLeft = outOfBoundsLeft(source, popUpWidth);
        boolean zOutOfBoundsBottom = outOfBoundsBottom(source, popUpHeight);
        boolean zOutOfBoundsTop = outOfBoundsTop(source, popUpHeight);
        if (!zOutOfBoundsTop && !zOutOfBoundsLeft) {
            placeVariableHeightPopUpTopLeft(source, popup, popUpWidth, popUpHeight);
            return;
        }
        if (!zOutOfBoundsTop && !zOutOfBoundsRight) {
            placeVariableHeightPopUpTopRight(source, popup, popUpHeight);
            return;
        }
        if (!zOutOfBoundsLeft) {
            placePopUpLeft(source, popup, popUpWidth, popUpHeight);
            return;
        }
        if (!zOutOfBoundsRight) {
            placePopUpRight(source, popup, popUpHeight);
            return;
        }
        if (!zOutOfBoundsBottom) {
            placePopUpBottom(source, popup, popUpHeight);
        } else if (!zOutOfBoundsTop) {
            placePopUpTop(source, popup, popUpWidth, popUpHeight);
        } else {
            placePopUpCenterScreen(source, popup);
        }
    }

    public static void placePopUpCenterScreen(final View button, final PopupWindow popUp) {
        popUp.showAtLocation(button.getRootView(), 17, 0, 0);
    }

    public static void placePopUpTop(final View button, final PopupWindow popUp, final int width, final int height) {
        popUp.showAsDropDown(button, (width / 2) * (-1), (button.getHeight() + height) * (-1));
    }

    public static void placePopUpBottom(final View button, final PopupWindow popUp, final int width) {
        popUp.showAsDropDown(button, (width / 2) * (-1), 0);
    }

    public static void placePopUpLeft(final View button, final PopupWindow popUp, final int width, final int height) {
        popUp.showAsDropDown(button, width * (-1), (height / 2) * (-1));
    }

    public static void placePopUpRight(final View button, final PopupWindow popUp, final int height) {
        popUp.showAsDropDown(button, button.getWidth(), (height / 2) * (-1));
    }

    public static void placePopUpBottomLeft(final View button, final PopupWindow popUp, final int width) {
        popUp.showAsDropDown(button, width * (-1), button.getHeight() * (-1));
    }

    public static void placePopUpTopLeft(final View button, final PopupWindow popUp, final int width, final int height) {
        int height2 = height - (button.getHeight() * 3);
        int[] iArr = new int[2];
        button.getLocationOnScreen(iArr);
        int i = height2 * (-1);
        if (iArr[1] + button.getHeight() + i + height >= Resources.getSystem().getDisplayMetrics().heightPixels) {
            popUp.showAtLocation(button.getRootView(), 0, iArr[0] - width, (iArr[1] + (button.getWidth() * 3)) - height);
            return;
        }
        popUp.showAsDropDown(button, width * (-1), i);
    }

    public static void placeVariableHeightPopUpTopLeft(final View button, final PopupWindow popUp, final int width, final int height) {
        int[] iArr = new int[2];
        button.getLocationOnScreen(iArr);
        popUp.showAtLocation(button.getRootView(), 0, iArr[0] - width, (iArr[1] + (button.getHeight() * 3)) - height);
    }

    public static void placePopUpBottomRight(final View button, final PopupWindow popUp) {
        popUp.showAsDropDown(button, button.getWidth(), button.getHeight() * (-1));
    }

    public static void placePopUpTopRight(final View button, final PopupWindow popUp, final int height) {
        popUp.showAsDropDown(button, button.getWidth(), (height - (button.getHeight() * 3)) * (-1));
    }

    public static void placePopUpToBottomLeft(final View button, final PopupWindow popUp) {
        popUp.showAsDropDown(button, 0, button.getHeight() * (-1));
    }

    public static void placePopUpToBottomRight(final View button, final PopupWindow popUp) {
        popUp.showAsDropDown(button, (button.getWidth() + 0) - popUp.getWidth(), button.getHeight() * (-1));
    }

    public static void placeVariableHeightPopUpTopRight(final View button, final PopupWindow popUp, final int height) {
        int[] iArr = new int[2];
        button.getLocationOnScreen(iArr);
        popUp.showAtLocation(button.getRootView(), 0, iArr[0] + button.getWidth(), (iArr[1] + (button.getHeight() * 3)) - height);
    }

    public static boolean outOfBoundsTop(final View source, final int heightToCompare) {
        int[] iArr = new int[2];
        source.getLocationOnScreen(iArr);
        return iArr[1] - heightToCompare <= 0;
    }

    public static boolean outOfBoundsBottom(final View source, final int heightToCompare) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int[] iArr = new int[2];
        source.getLocationOnScreen(iArr);
        return (iArr[1] + source.getHeight()) + heightToCompare >= displayMetrics.heightPixels;
    }

    public static boolean outOfBoundsRight(final View source, final int widthToCompare) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int[] iArr = new int[2];
        source.getLocationOnScreen(iArr);
        return (iArr[0] + source.getWidth()) + widthToCompare >= displayMetrics.widthPixels;
    }

    public static boolean outOfBoundsRightFromLeft(final View source, final int widthToCompare) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int[] iArr = new int[2];
        source.getLocationOnScreen(iArr);
        return iArr[0] + widthToCompare >= displayMetrics.widthPixels;
    }

    public static boolean outOfBoundsLeftFromRight(final View source, final int widthToCompare) {
        Resources.getSystem().getDisplayMetrics();
        int[] iArr = new int[2];
        source.getLocationOnScreen(iArr);
        return (iArr[0] + source.getWidth()) - widthToCompare <= 0;
    }

    public static boolean outOfBoundsLeft(final View source, final int widthToCompare) {
        int[] iArr = new int[2];
        source.getLocationOnScreen(iArr);
        return iArr[0] - widthToCompare <= 0;
    }

    public static float convertPixelsToDp(final float px, final Resources resources) {
        return px / (resources.getDisplayMetrics().densityDpi / 160.0f);
    }

    public static int computePopUpWidth(final View menuView, final Resources resources) {
        menuView.measure(0, 0);
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.podcast_context_menu_pop_up_max_width);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R$dimen.podcast_context_menu_pop_up_min_width);
        int measuredWidth = menuView.getMeasuredWidth();
        return measuredWidth >= dimensionPixelSize ? dimensionPixelSize : measuredWidth <= dimensionPixelSize2 ? dimensionPixelSize2 : measuredWidth;
    }
}
