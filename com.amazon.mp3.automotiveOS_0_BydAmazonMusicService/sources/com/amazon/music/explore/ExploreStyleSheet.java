package com.amazon.music.explore;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextPaint;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import com.amazon.music.skyfire.ui.utils.ScreenUtils;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.LandscapeType;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.GridSize;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreStyleSheet {
    protected final StyleSheet bauhausStyleSheet;
    protected final Context context;
    protected final Resources resources;

    public ExploreStyleSheet(Context context, StyleSheet styleSheet) {
        this.context = context;
        this.resources = context.getResources();
        this.bauhausStyleSheet = styleSheet;
    }

    public static void getOptimallySizedFontForLongestWord(final TextView textView, final float f, final float f2) {
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.music.explore.ExploreStyleSheet.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                TextPaint paint = textView.getPaint();
                float f3 = f;
                int width = textView.getWidth();
                for (String str : textView.getText().toString().split(" ")) {
                    while (f3 >= f2) {
                        paint.setTextSize(f3);
                        if (paint.measureText(str) <= width) {
                            break;
                        } else {
                            f3 -= textView.getResources().getDisplayMetrics().density;
                        }
                    }
                    if (f3 == f2) {
                        break;
                    }
                }
                TextView textView2 = textView;
                textView2.setText(textView2.getText());
            }
        });
    }

    public static boolean isXL(Context context) {
        return ScreenUtils.getScreenWidth(context) >= context.getResources().getDimensionPixelSize(R.dimen.screen_size_1024);
    }

    protected ScreenSizeKey getScreenSizeKey() {
        return this.bauhausStyleSheet.getScreenSizeKey(getScreenWidth());
    }

    protected ScreenSizeKey getScreenSizeKey(int i) {
        return this.bauhausStyleSheet.getScreenSizeKey(i);
    }

    protected int getScreenHeight() {
        return ScreenUtils.getSafeAreaHeight(this.context);
    }

    public LandscapeType getLandscapeType() {
        return this.bauhausStyleSheet.getLandscapeType(this.context);
    }

    protected boolean isMobileLandscape() {
        return getLandscapeType() == LandscapeType.MOBILE_LANDSCAPE;
    }

    public Integer getSpacerInPixels(SpacerKey spacerKey) {
        return this.bauhausStyleSheet.getSpacerInPixels(spacerKey);
    }

    public GridSize getGridSize() {
        return this.bauhausStyleSheet.getGridSize();
    }

    public int getScreenWidth() {
        return ScreenUtils.getScreenWidth(this.context);
    }
}
