package com.amazon.mp3.fragment.contextmenu;

import android.view.Menu;
import android.view.MenuItem;
import androidx.lifecycle.ViewModel;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: compiled from: OverflowContextMenuViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b$\u0010%J\u0006\u0010\u0003\u001a\u00020\u0002J\u0014\u0010\u0007\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\b\u0010\b\u001a\u00020\u0002H\u0014R$\u0010\n\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR*\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R*\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0012\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u001d\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u001a8\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010 \u001a\u00020\u001f8\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#¨\u0006&"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/OverflowContextMenuViewModel;", "Landroidx/lifecycle/ViewModel;", "", "clear", "Lrx/functions/Action1;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "subscriber", "subscribeToStyleSheet", "onCleared", "Landroid/view/Menu;", "menu", "Landroid/view/Menu;", "getMenu", "()Landroid/view/Menu;", "setMenu", "(Landroid/view/Menu;)V", "Landroid/view/MenuItem;", "clickAction", "Lrx/functions/Action1;", "getClickAction", "()Lrx/functions/Action1;", "setClickAction", "(Lrx/functions/Action1;)V", "chainAction", "getChainAction", "setChainAction", "Lrx/Observable;", "styleSheetObservable", "Lrx/Observable;", "getStyleSheetObservable", "()Lrx/Observable;", "Lrx/subscriptions/CompositeSubscription;", "compositeSubscription", "Lrx/subscriptions/CompositeSubscription;", "getCompositeSubscription", "()Lrx/subscriptions/CompositeSubscription;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class OverflowContextMenuViewModel extends ViewModel {
    private Action1<MenuItem> chainAction;
    private Action1<MenuItem> clickAction;
    private Menu menu;
    private final Observable<StyleSheet> styleSheetObservable = StyleSheetProvider.getStyleSheetObservable();
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();

    public final Menu getMenu() {
        return this.menu;
    }

    public final void setMenu(Menu menu) {
        this.menu = menu;
    }

    public final Action1<MenuItem> getClickAction() {
        return this.clickAction;
    }

    public final void setClickAction(Action1<MenuItem> action1) {
        this.clickAction = action1;
    }

    public final Action1<MenuItem> getChainAction() {
        return this.chainAction;
    }

    public final void setChainAction(Action1<MenuItem> action1) {
        this.chainAction = action1;
    }

    public final void clear() {
        this.compositeSubscription.unsubscribe();
        this.menu = null;
        this.clickAction = null;
        this.chainAction = null;
    }

    public final void subscribeToStyleSheet(Action1<StyleSheet> subscriber) {
        Intrinsics.checkNotNullParameter(subscriber, "subscriber");
        this.compositeSubscription.add(this.styleSheetObservable.subscribe(subscriber));
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        clear();
        super.onCleared();
    }
}
