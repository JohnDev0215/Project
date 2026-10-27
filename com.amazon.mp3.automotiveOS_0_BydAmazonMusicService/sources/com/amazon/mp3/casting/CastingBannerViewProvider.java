package com.amazon.mp3.casting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.music.casting.CastingCategory;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.session.CastingConnectionCallback;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.castingviews.CastingUIMetricsLogger;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.views.library.binders.PersistentBannerBinder;
import com.amazon.music.views.library.models.PersistentBannerModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.PersistentBannerView;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CastingBannerViewProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000Q\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u0017\u0018\u0000 '2\u00020\u0001:\u0001'B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\b\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u0012\u0010\u001d\u001a\u00020\u001a2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0012H\u0002J\u0006\u0010\u001f\u001a\u00020\u001aJ\u000e\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\"J!\u0010#\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\t2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0002\u0010%J!\u0010&\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\t2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0002\u0010%R\u001d\u0010\u000b\u001a\u0004\u0018\u00010\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/amazon/mp3/casting/CastingBannerViewProvider;", "", "context", "Landroid/content/Context;", "parentView", "Landroid/view/ViewGroup;", "bannerView", "Lcom/amazon/music/views/library/views/PersistentBannerView;", "isRestingState", "", "(Landroid/content/Context;Landroid/view/ViewGroup;Lcom/amazon/music/views/library/views/PersistentBannerView;Ljava/lang/Boolean;)V", "bannerBinder", "Lcom/amazon/music/views/library/binders/PersistentBannerBinder;", "getBannerBinder", "()Lcom/amazon/music/views/library/binders/PersistentBannerBinder;", "bannerBinder$delegate", "Lkotlin/Lazy;", "bannerModel", "Lcom/amazon/music/views/library/models/PersistentBannerModel;", "getBannerModel", "()Lcom/amazon/music/views/library/models/PersistentBannerModel;", "bannerModel$delegate", "mCastingConnectionCallback", "com/amazon/mp3/casting/CastingBannerViewProvider$mCastingConnectionCallback$1", "Lcom/amazon/mp3/casting/CastingBannerViewProvider$mCastingConnectionCallback$1;", "bindBanner", "", "getBannerMessage", "", "onClick", "persistentBannerModel", "onDestroy", "onSaveInstanceState", "bundle", "Landroid/os/Bundle;", "show", "animate", "(ZLjava/lang/Boolean;)V", "showOnUiThread", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CastingBannerViewProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: bannerBinder$delegate, reason: from kotlin metadata */
    private final Lazy bannerBinder;

    /* JADX INFO: renamed from: bannerModel$delegate, reason: from kotlin metadata */
    private final Lazy bannerModel;
    private final PersistentBannerView bannerView;
    private final Context context;
    private final CastingBannerViewProvider$mCastingConnectionCallback$1 mCastingConnectionCallback;
    private final ViewGroup parentView;

    @JvmStatic
    public static final boolean getRestingState(Bundle bundle) {
        return INSTANCE.getRestingState(bundle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.amazon.mp3.casting.CastingBannerViewProvider$mCastingConnectionCallback$1, com.amazon.music.casting.session.CastingConnectionCallback] */
    public CastingBannerViewProvider(Context context, ViewGroup parentView, PersistentBannerView bannerView, final Boolean bool) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(parentView, "parentView");
        Intrinsics.checkNotNullParameter(bannerView, "bannerView");
        this.context = context;
        this.parentView = parentView;
        this.bannerView = bannerView;
        this.bannerModel = LazyKt__LazyJVMKt.lazy(new Function0<PersistentBannerModel>() { // from class: com.amazon.mp3.casting.CastingBannerViewProvider$bannerModel$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX INFO: renamed from: com.amazon.mp3.casting.CastingBannerViewProvider$bannerModel$2$1, reason: invalid class name */
            /* JADX INFO: compiled from: CastingBannerViewProvider.kt */
            @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
            /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<PersistentBannerModel, Unit> {
                AnonymousClass1(Object obj) {
                    super(1, obj, CastingBannerViewProvider.class, "onClick", "onClick(Lcom/amazon/music/views/library/models/PersistentBannerModel;)V", 0);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PersistentBannerModel persistentBannerModel) {
                    invoke2(persistentBannerModel);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PersistentBannerModel persistentBannerModel) {
                    ((CastingBannerViewProvider) this.receiver).onClick(persistentBannerModel);
                }
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PersistentBannerModel invoke() {
                return new PersistentBannerModel(null, this.this$0.getBannerMessage(), bool, new AnonymousClass1(this.this$0), PersistentBannerModel.PersistentBannerType.ACTIVE, 1, null);
            }
        });
        this.bannerBinder = LazyKt__LazyJVMKt.lazy(new Function0<PersistentBannerBinder>() { // from class: com.amazon.mp3.casting.CastingBannerViewProvider$bannerBinder$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PersistentBannerBinder invoke() {
                StyleSheet value = StyleSheetProvider.getStyleSheet().getValue();
                if (value == null) {
                    return null;
                }
                return new PersistentBannerBinder(value);
            }
        });
        ?? r2 = new CastingConnectionCallback() { // from class: com.amazon.mp3.casting.CastingBannerViewProvider$mCastingConnectionCallback$1
            @Override // com.amazon.music.casting.session.CastingConnectionCallback
            public void onConnected(CastingDevice device) {
                Intrinsics.checkNotNullParameter(device, "device");
                this.this$0.getBannerModel().setRestingState(Boolean.FALSE);
                this.this$0.showOnUiThread(true, Boolean.TRUE);
            }

            @Override // com.amazon.music.casting.session.CastingConnectionCallback
            public void onDisconnected(TerminationReason terminationReason) {
                CastingBannerViewProvider.showOnUiThread$default(this.this$0, false, null, 2, null);
            }

            @Override // com.amazon.music.casting.session.CastingConnectionCallback
            public void onError(CastingException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                CastingBannerViewProvider.showOnUiThread$default(this.this$0, false, null, 2, null);
            }
        };
        this.mCastingConnectionCallback = r2;
        StyleSheet value = StyleSheetProvider.getStyleSheet().getValue();
        if (value != null) {
            bannerView.initViewStyling(value);
        }
        boolean zIsCasting = CastingUtil.isCasting();
        CastingSessionManager castingSessionManager = CastingSessionManager.getInstance();
        if (zIsCasting && !CastingUtil.isAlexaCastingEnabled() && CastingCategory.CHROMECAST != castingSessionManager.getLastDevice().getCategory()) {
            castingSessionManager.disconnect(TerminationReason.USER_DISCONNECT);
            zIsCasting = false;
        }
        showOnUiThread(zIsCasting, Boolean.FALSE);
        CastingSessionManager.getInstance().registerListener((CastingConnectionCallback) r2);
    }

    /* JADX INFO: compiled from: CastingBannerViewProvider.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/casting/CastingBannerViewProvider$Companion;", "", "()V", "BANNER_STATE_KEY", "", "getRestingState", "", "bundle", "Landroid/os/Bundle;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final boolean getRestingState(Bundle bundle) {
            if (bundle == null) {
                return true;
            }
            return bundle.getBoolean("banner_state");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PersistentBannerModel getBannerModel() {
        return (PersistentBannerModel) this.bannerModel.getValue();
    }

    private final PersistentBannerBinder getBannerBinder() {
        return (PersistentBannerBinder) this.bannerBinder.getValue();
    }

    public final void onDestroy() {
        CastingSessionManager.getInstance().unregisterListener(this.mCastingConnectionCallback);
        PersistentBannerBinder bannerBinder = getBannerBinder();
        if (bannerBinder == null) {
            return;
        }
        bannerBinder.onDestroy();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        Boolean isRestingState = getBannerModel().getIsRestingState();
        if (isRestingState == null) {
            return;
        }
        bundle.putBoolean("banner_state", isRestingState.booleanValue());
    }

    static /* synthetic */ void showOnUiThread$default(CastingBannerViewProvider castingBannerViewProvider, boolean z, Boolean bool, int i, Object obj) {
        if ((i & 2) != 0) {
            bool = Boolean.FALSE;
        }
        castingBannerViewProvider.showOnUiThread(z, bool);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOnUiThread(final boolean show, final Boolean animate) {
        if (!Intrinsics.areEqual(Looper.getMainLooper(), Looper.myLooper())) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.mp3.casting.CastingBannerViewProvider$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    CastingBannerViewProvider.m446showOnUiThread$lambda2(this.f$0, show, animate);
                }
            });
        } else {
            show(show, animate);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showOnUiThread$lambda-2, reason: not valid java name */
    public static final void m446showOnUiThread$lambda2(CastingBannerViewProvider this$0, boolean z, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.show(z, bool);
    }

    private final void show(boolean show, Boolean animate) {
        if (show) {
            bindBanner();
            this.bannerView.setVisibility(0);
        }
        if (AmazonApplication.getCapabilities().getCastingIngressExperimentTreatment() == WeblabTreatment.T3) {
            this.bannerView.expandCustomHeight(show, SpacerKey.LARGE, false, true, this.parentView);
        } else {
            this.bannerView.expandHeight(show, animate, this.parentView);
        }
        if (show) {
            return;
        }
        this.bannerView.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getBannerMessage() {
        String string = this.context.getString(R.string.casting_connected_to_text, CastingUtil.getCastingDeviceName(this.context));
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…cted_to_text, deviceName)");
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onClick(PersistentBannerModel persistentBannerModel) {
        Intent startIntent = CastingFragment.getStartIntent(this.context);
        CastingUIMetricsLogger.recordCastingHeaderClickMetric();
        this.context.startActivity(startIntent);
    }

    private final void bindBanner() {
        getBannerModel().setText(getBannerMessage());
        PersistentBannerBinder bannerBinder = getBannerBinder();
        if (bannerBinder == null) {
            return;
        }
        bannerBinder.bind(this.bannerView, getBannerModel());
    }
}
