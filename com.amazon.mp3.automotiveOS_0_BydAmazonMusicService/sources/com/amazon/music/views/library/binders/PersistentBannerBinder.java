package com.amazon.music.views.library.binders;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import com.amazon.music.views.library.models.PersistentBannerModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.PersistentBannerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: PersistentBannerBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0003H\u0016J\u0010\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J \u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0006\u0010\u001b\u001a\u00020\u0012J\u0018\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0003H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/views/library/binders/PersistentBannerBinder;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "Lcom/amazon/music/views/library/views/PersistentBannerView;", "Lcom/amazon/music/views/library/models/PersistentBannerModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Lcom/amazon/music/views/library/styles/StyleSheet;)V", "handler", "Landroid/os/Handler;", "isTransitioningState", "", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "transitionDelay", "", "bind", "", "view", "model", "createView", "context", "Landroid/content/Context;", "handleStateChange", "payload", "", "onDestroy", "setStateTransitionDelay", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PersistentBannerBinder implements BaseStateChangeBinder<PersistentBannerView, PersistentBannerModel> {
    private final Handler handler;
    private boolean isTransitioningState;
    private final StyleSheet styleSheet;
    private final long transitionDelay;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<PersistentBannerModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(PersistentBannerModel.class);
    }

    public PersistentBannerBinder(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.transitionDelay = 3000L;
        this.handler = new Handler();
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public PersistentBannerView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        PersistentBannerView persistentBannerView = new PersistentBannerView(context, null, 0, 6, null);
        persistentBannerView.initViewStyling(this.styleSheet);
        return persistentBannerView;
    }

    @Override // com.amazon.music.views.library.binders.BaseStateChangeBinder
    public void handleStateChange(PersistentBannerView view, PersistentBannerModel model, Object payload) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (Intrinsics.areEqual(payload, (Object) 5)) {
            view.setPrimaryText(model.getText());
        }
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(PersistentBannerView view, final PersistentBannerModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        view.initViewStyling(this.styleSheet);
        view.setPrimaryText(model.getText());
        PersistentBannerModel.PersistentBannerType type = model.getType();
        Boolean isRestingState = model.getIsRestingState();
        Boolean bool = Boolean.TRUE;
        view.setStyle(type, Intrinsics.areEqual(isRestingState, bool));
        view.setClickable(true);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.PersistentBannerBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PersistentBannerBinder.m2349bind$lambda0(model, view2);
            }
        });
        if (Intrinsics.areEqual(model.getIsRestingState(), bool)) {
            return;
        }
        setStateTransitionDelay(view, model);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-0, reason: not valid java name */
    public static final void m2349bind$lambda0(PersistentBannerModel model, View view) {
        Intrinsics.checkNotNullParameter(model, "$model");
        model.getOnClick().invoke(model);
    }

    private final synchronized void setStateTransitionDelay(final PersistentBannerView view, final PersistentBannerModel model) {
        if (this.isTransitioningState) {
            return;
        }
        this.isTransitioningState = true;
        this.handler.postDelayed(new Runnable() { // from class: com.amazon.music.views.library.binders.PersistentBannerBinder$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                PersistentBannerBinder.m2350setStateTransitionDelay$lambda1(this.f$0, model, view);
            }
        }, this.transitionDelay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setStateTransitionDelay$lambda-1, reason: not valid java name */
    public static final void m2350setStateTransitionDelay$lambda1(PersistentBannerBinder this$0, PersistentBannerModel model, PersistentBannerView view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(view, "$view");
        this$0.isTransitioningState = false;
        model.setRestingState(Boolean.TRUE);
        view.transitionToRestingState();
    }

    public final void onDestroy() {
        this.handler.removeCallbacksAndMessages(null);
    }
}
