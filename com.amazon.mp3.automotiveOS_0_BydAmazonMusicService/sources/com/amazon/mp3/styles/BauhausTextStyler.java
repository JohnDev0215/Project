package com.amazon.mp3.styles;

import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.view.Menu;
import android.view.MenuItem;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.functions.Action1;

/* JADX INFO: compiled from: BauhausTextStyler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\t\b\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006R$\u0010\n\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/styles/BauhausTextStyler;", "", "Landroid/view/Menu;", "menu", "", "styleMenu", "", "text", "styleText", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "setStyleSheet", "(Lcom/amazon/music/views/library/styles/StyleSheet;)V", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BauhausTextStyler {
    private StyleSheet styleSheet;

    public BauhausTextStyler() {
        StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.styles.BauhausTextStyler$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BauhausTextStyler.m1642_init_$lambda0(this.f$0, (StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m1642_init_$lambda0(BauhausTextStyler this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.styleSheet = styleSheet;
    }

    public final CharSequence styleText(CharSequence text) {
        FontStyle fontStyle;
        Intrinsics.checkNotNullParameter(text, "text");
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null || (fontStyle = styleSheet.getFontStyle(FontStyleKey.PRIMARY)) == null) {
            return text;
        }
        Typeface typeface = fontStyle.getTypeface();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        spannableStringBuilder.setSpan(new CustomTypefaceSpan(typeface), 0, spannableStringBuilder.length(), 0);
        return spannableStringBuilder;
    }

    public final void styleMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        int size = menu.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = menu.getItem(i);
            Intrinsics.checkNotNullExpressionValue(item, "getItem(index)");
            CharSequence title = item.getTitle();
            if (title != null) {
                item.setTitle(styleText(title));
            }
        }
    }
}
