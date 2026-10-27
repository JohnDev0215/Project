package com.amazon.music.views.library.views.presentation;

import android.view.View;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;

/* JADX INFO: compiled from: TooltipExperienceDecorator.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u001a\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&¨\u0006\f"}, d2 = {"Lcom/amazon/music/views/library/views/presentation/TooltipExperienceDecorator;", "", "dismissTooltip", "", "dismissPermanently", "", "onDestroy", "showTooltip", "view", "Landroid/view/View;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface TooltipExperienceDecorator {
    void dismissTooltip(boolean dismissPermanently);

    void onDestroy();

    void showTooltip(View view, StyleSheet styleSheet);
}
