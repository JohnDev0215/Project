package com.amazon.music.find.skyfire.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.AlexaButtonElement;
import Touch.WidgetsInterface.v1_0.SearchBarElement;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import com.amazon.music.find.view.FindSearchBarView;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.widgets.binders.ItemElementBinder;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchBarElementItemBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/find/skyfire/binders/SearchBarElementBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ItemElementBinder;", "LTouch/WidgetsInterface/v1_0/SearchBarElement;", "Lcom/amazon/music/find/view/FindSearchBarView;", "()V", "bind", "", "element", "view", "width", "", "createView", "parent", "Landroid/view/ViewGroup;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchBarElementBinder extends ItemElementBinder<SearchBarElement, FindSearchBarView> {
    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public FindSearchBarView createView(ViewGroup parent) {
        return new FindSearchBarView((FragmentActivity) getContext());
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(final SearchBarElement element, FindSearchBarView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        View.OnKeyListener onKeyListener = new View.OnKeyListener() { // from class: com.amazon.music.find.skyfire.binders.SearchBarElementBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view2, int i, KeyEvent keyEvent) {
                return SearchBarElementBinder.m2055bind$lambda0(this.f$0, element, view2, i, keyEvent);
            }
        };
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.music.find.skyfire.binders.SearchBarElementBinder$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SearchBarElementBinder.m2056bind$lambda1(this.f$0, element, view2);
            }
        };
        View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.amazon.music.find.skyfire.binders.SearchBarElementBinder$$ExternalSyntheticLambda2
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view2, boolean z) {
                SearchBarElementBinder.m2057bind$lambda2(this.f$0, element, view2, z);
            }
        };
        view.setSearchViewOnKeyListener(onKeyListener);
        view.setSearchViewOnClickListener(onClickListener);
        view.setSearchViewFocusChangeListener(onFocusChangeListener);
        final AlexaButtonElement alexaButtonElementAlexaButton = element.alexaButton();
        if (alexaButtonElementAlexaButton != null) {
            view.setAlexaButtonListener(new View.OnClickListener() { // from class: com.amazon.music.find.skyfire.binders.SearchBarElementBinder$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    SearchBarElementBinder.m2058bind$lambda4$lambda3(this.f$0, alexaButtonElementAlexaButton, view2);
                }
            });
        }
        String strQueryHint = element.queryHint();
        if (strQueryHint == null) {
            return;
        }
        view.setQueryHint(strQueryHint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-0, reason: not valid java name */
    public static final boolean m2055bind$lambda0(SearchBarElementBinder this$0, SearchBarElement element, View view, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(element, "$element");
        if ((keyEvent != null && keyEvent.getKeyCode() == 66) && keyEvent.getAction() == 0) {
            MethodsDispatcher<Method> methodsDispatcher = this$0.getMethodsDispatcher();
            String ownerId = this$0.getOwnerId();
            List<Method> listOnQueryEntered = element.onQueryEntered();
            Intrinsics.checkNotNullExpressionValue(listOnQueryEntered, "element.onQueryEntered()");
            methodsDispatcher.dispatchMethods(ownerId, listOnQueryEntered);
        } else {
            MethodsDispatcher<Method> methodsDispatcher2 = this$0.getMethodsDispatcher();
            String ownerId2 = this$0.getOwnerId();
            List<Method> listOnQueryChanged = element.onQueryChanged();
            Intrinsics.checkNotNullExpressionValue(listOnQueryChanged, "element.onQueryChanged()");
            methodsDispatcher2.dispatchMethods(ownerId2, listOnQueryChanged);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-1, reason: not valid java name */
    public static final void m2056bind$lambda1(SearchBarElementBinder this$0, SearchBarElement element, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(element, "$element");
        MethodsDispatcher<Method> methodsDispatcher = this$0.getMethodsDispatcher();
        String ownerId = this$0.getOwnerId();
        List<Method> listOnInputSelected = element.onInputSelected();
        Intrinsics.checkNotNullExpressionValue(listOnInputSelected, "element.onInputSelected()");
        methodsDispatcher.dispatchMethods(ownerId, listOnInputSelected);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-2, reason: not valid java name */
    public static final void m2057bind$lambda2(SearchBarElementBinder this$0, SearchBarElement element, View view, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(element, "$element");
        if (z) {
            MethodsDispatcher<Method> methodsDispatcher = this$0.getMethodsDispatcher();
            String ownerId = this$0.getOwnerId();
            List<Method> listOnQueryChanged = element.onQueryChanged();
            Intrinsics.checkNotNullExpressionValue(listOnQueryChanged, "element.onQueryChanged()");
            methodsDispatcher.dispatchMethods(ownerId, listOnQueryChanged);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-4$lambda-3, reason: not valid java name */
    public static final void m2058bind$lambda4$lambda3(SearchBarElementBinder this$0, AlexaButtonElement alexaButton, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(alexaButton, "$alexaButton");
        MethodsDispatcher<Method> methodsDispatcher = this$0.getMethodsDispatcher();
        String ownerId = this$0.getOwnerId();
        List<Method> listOnItemSelected = alexaButton.onItemSelected();
        Intrinsics.checkNotNullExpressionValue(listOnItemSelected, "alexaButton.onItemSelected()");
        methodsDispatcher.dispatchMethods(ownerId, listOnItemSelected);
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ItemElementBinder
    public void bind(SearchBarElement element, FindSearchBarView view, int width) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        bind(element, view);
    }
}
