package com.amazon.music.views.library.views.presentation;

import android.view.View;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.firebase.perf.plugin.util.AsmString;
import kotlin.Metadata;

/* JADX INFO: compiled from: ActionBarDecorator.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J7\u0010\b\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002H&¢\u0006\u0004\b\b\u0010\tJ\b\u0010\n\u001a\u00020\u0007H&R\u0014\u0010\u000e\u001a\u00020\u000b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0010\u001a\u00020\u000b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u00118&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0016\u001a\u00020\u00158&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u00020\u00158&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\u001c\u0010\u001c\u001a\u00020\u00158&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u0017\"\u0004\b\u001d\u0010\u0019R\u001c\u0010\u001e\u001a\u00020\u00158&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019R\u001e\u0010%\u001a\u0004\u0018\u00010 8&@&X¦\u000e¢\u0006\f\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u0006&"}, d2 = {"Lcom/amazon/music/views/library/views/presentation/ActionBarDecorator;", "", "", AsmString.METHOD_FIREBASE_PERF_TRACE_START, "top", "end", "bottom", "", "setSearchBarMargins", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "addTopMargin", "Lcom/amazon/ui/foundations/views/BaseButton;", "getBackButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "backButton", "getActionButton", "actionButton", "Landroid/view/View;", "getSearchBar", "()Landroid/view/View;", "searchBar", "", "isBackButtonVisible", "()Z", "setBackButtonVisible", "(Z)V", "isActionBarVisible", "setActionBarVisible", "isFeedButtonVisible", "setFeedButtonVisible", "isLogoVisible", "setLogoVisible", "", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "title", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public interface ActionBarDecorator {
    void addTopMargin();

    BaseButton getActionButton();

    BaseButton getBackButton();

    View getSearchBar();

    void setActionBarVisible(boolean z);

    void setBackButtonVisible(boolean z);

    void setFeedButtonVisible(boolean z);

    void setLogoVisible(boolean z);

    void setSearchBarMargins(Integer start, Integer top, Integer end, Integer bottom);

    void setTitle(String str);
}
