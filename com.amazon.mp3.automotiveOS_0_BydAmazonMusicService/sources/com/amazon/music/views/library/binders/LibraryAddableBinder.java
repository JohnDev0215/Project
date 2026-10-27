package com.amazon.music.views.library.binders;

import android.util.Log;
import android.view.View;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.base.LibraryAddableModel;
import com.amazon.music.views.library.providers.ContentEligibilityProvider;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.providers.TrackDownloadButtonProvider;
import com.amazon.music.views.library.views.LibraryAddableView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: LibraryAddableBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u000b\b&\u0018\u0000 #*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0005:\u0001#B+\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u001d\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u0001H\u0004¢\u0006\u0002\u0010\u0015J\u001d\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u0001H\u0004¢\u0006\u0002\u0010\u0015J%\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u00012\u0006\u0010\u0018\u001a\u00020\u0019H\u0016¢\u0006\u0002\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00028\u0001H\u0004¢\u0006\u0002\u0010\u001cJ\u0015\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00028\u0001H\u0004¢\u0006\u0002\u0010\u001cJ\u0015\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00028\u0001H\u0004¢\u0006\u0002\u0010\u001cJ\u0015\u0010\u001f\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010\u001cJ\u0015\u0010 \u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00028\u0001H\u0004¢\u0006\u0002\u0010\u001cJ%\u0010!\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u00012\u0006\u0010\u001f\u001a\u00020\u0010H\u0002¢\u0006\u0002\u0010\"R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/music/views/library/binders/LibraryAddableBinder;", "V", "Lcom/amazon/music/views/library/views/LibraryAddableView;", "M", "Lcom/amazon/music/views/library/models/base/LibraryAddableModel;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "userSubscription", "Lcom/amazon/music/subscription/UserSubscription;", "contentEligibilityProvider", "Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;", "rowButtonOnClickProvider", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "trackDownloadButtonProvider", "Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;", "(Lcom/amazon/music/subscription/UserSubscription;Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;)V", "isNightwingOnly", "", "bindLibraryState", "", "view", "model", "(Lcom/amazon/music/views/library/views/LibraryAddableView;Lcom/amazon/music/views/library/models/base/LibraryAddableModel;)V", "bindLibraryStateSynchronously", "handleStateChange", "payload", "", "(Lcom/amazon/music/views/library/views/LibraryAddableView;Lcom/amazon/music/views/library/models/base/LibraryAddableModel;Ljava/lang/Object;)V", "isDownloadFeatureEnabled", "(Lcom/amazon/music/views/library/models/base/LibraryAddableModel;)Z", "shouldDisplayPlaybackIcon", "shouldHidePlayTypeIconBasedOnTag", "shouldShowAddOrLikeButton", "shouldShowDownloadButton", "updateRowButton", "(Lcom/amazon/music/views/library/views/LibraryAddableView;Lcom/amazon/music/views/library/models/base/LibraryAddableModel;Z)V", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class LibraryAddableBinder<V extends LibraryAddableView, M extends LibraryAddableModel> implements BaseStateChangeBinder<V, M> {
    private static final String TAG = Reflection.getOrCreateKotlinClass(LibraryAddableBinder.class).getSimpleName();
    private final ContentEligibilityProvider contentEligibilityProvider;
    private final boolean isNightwingOnly;
    private final RowButtonOnClickProvider rowButtonOnClickProvider;
    private final TrackDownloadButtonProvider trackDownloadButtonProvider;

    public LibraryAddableBinder(UserSubscription userSubscription, ContentEligibilityProvider contentEligibilityProvider, RowButtonOnClickProvider rowButtonOnClickProvider, TrackDownloadButtonProvider trackDownloadButtonProvider) {
        Intrinsics.checkNotNullParameter(userSubscription, "userSubscription");
        this.contentEligibilityProvider = contentEligibilityProvider;
        this.rowButtonOnClickProvider = rowButtonOnClickProvider;
        this.trackDownloadButtonProvider = trackDownloadButtonProvider;
        this.isNightwingOnly = userSubscription.isNightwingOnly();
    }

    @Override // com.amazon.music.views.library.binders.BaseStateChangeBinder
    public void handleStateChange(V view, M model, Object payload) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (!Intrinsics.areEqual(payload, (Object) 0) || shouldDisplayPlaybackIcon(model) || isDownloadFeatureEnabled(model)) {
            return;
        }
        bindLibraryState(view, model);
    }

    protected final boolean shouldShowDownloadButton(M model) {
        Intrinsics.checkNotNullParameter(model, "model");
        TrackDownloadButtonProvider trackDownloadButtonProvider = this.trackDownloadButtonProvider;
        if (trackDownloadButtonProvider == null) {
            return false;
        }
        return trackDownloadButtonProvider.shouldShowDownloadIcon(model);
    }

    protected final boolean isDownloadFeatureEnabled(M model) {
        Intrinsics.checkNotNullParameter(model, "model");
        TrackDownloadButtonProvider trackDownloadButtonProvider = this.trackDownloadButtonProvider;
        if (trackDownloadButtonProvider == null) {
            return false;
        }
        return trackDownloadButtonProvider.isFeatureEnabled(model);
    }

    protected final boolean shouldDisplayPlaybackIcon(M model) {
        Intrinsics.checkNotNullParameter(model, "model");
        if (model instanceof HorizontalTileModel) {
            return ((HorizontalTileModel) model).getShouldDisplayPlaybackIcon();
        }
        return false;
    }

    protected final boolean shouldHidePlayTypeIconBasedOnTag(M model) {
        Intrinsics.checkNotNullParameter(model, "model");
        List<String> tags = model.getMetadata().getTags();
        if (tags == null) {
            return false;
        }
        return tags.contains("upsell-tag-no-playicon-widget");
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean shouldShowAddOrLikeButton(M r7) {
        /*
            r6 = this;
            boolean r0 = r6.shouldShowDownloadButton(r7)
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            com.amazon.music.views.library.providers.ContentEligibilityProvider r0 = r6.contentEligibilityProvider
            r2 = 0
            if (r0 != 0) goto Lf
            r0 = r2
            goto L17
        Lf:
            boolean r0 = r0.shouldBadgeStationArtwork(r7)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
        L17:
            r3 = 1
            if (r0 != 0) goto L2a
            boolean r0 = r6.isNightwingOnly
            if (r0 == 0) goto L28
            com.amazon.music.views.library.metadata.ContentMetadata r0 = r7.getMetadata()
            boolean r0 = r0 instanceof com.amazon.music.views.library.metadata.TrackMetadata
            if (r0 == 0) goto L28
            r0 = r3
            goto L2e
        L28:
            r0 = r1
            goto L2e
        L2a:
            boolean r0 = r0.booleanValue()
        L2e:
            com.amazon.music.views.library.providers.RowButtonOnClickProvider r4 = r6.rowButtonOnClickProvider
            if (r4 != 0) goto L34
            r4 = r1
            goto L38
        L34:
            boolean r4 = r4.isLikesProvider()
        L38:
            com.amazon.music.views.library.providers.ContentEligibilityProvider r5 = r6.contentEligibilityProvider
            if (r5 == 0) goto L51
            if (r5 != 0) goto L3f
            goto L47
        L3f:
            boolean r2 = r5.shouldDisplayHawkfireUpsell(r7)
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
        L47:
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L51
            if (r4 != 0) goto L51
            r2 = r3
            goto L52
        L51:
            r2 = r1
        L52:
            com.amazon.music.views.library.providers.RowButtonOnClickProvider r5 = r6.rowButtonOnClickProvider
            if (r5 != 0) goto L58
            r5 = r1
            goto L5c
        L58:
            boolean r5 = r5.shouldDisplayRowButton(r7)
        L5c:
            if (r4 == 0) goto L64
            if (r2 != 0) goto L63
            if (r5 == 0) goto L63
            r1 = r3
        L63:
            return r1
        L64:
            boolean r7 = r7.getEnableAdd()
            if (r7 == 0) goto L71
            if (r0 != 0) goto L71
            if (r2 != 0) goto L71
            if (r5 == 0) goto L71
            r1 = r3
        L71:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.views.library.binders.LibraryAddableBinder.shouldShowAddOrLikeButton(com.amazon.music.views.library.models.base.LibraryAddableModel):boolean");
    }

    private final void updateRowButton(final V view, final M model, boolean shouldShowAddOrLikeButton) {
        if (shouldShowAddOrLikeButton) {
            view.showRowButton(shouldShowAddOrLikeButton);
            if (model.getIsSuggestion()) {
                return;
            }
            view.setRowButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.LibraryAddableBinder$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    LibraryAddableBinder.m2337updateRowButton$lambda0(this.f$0, model, view, view2);
                }
            });
            RowButtonOnClickProvider rowButtonOnClickProvider = this.rowButtonOnClickProvider;
            boolean z = false;
            if (rowButtonOnClickProvider != null && rowButtonOnClickProvider.isLikesProvider()) {
                z = true;
            }
            if (z) {
                view.restyleLikeIcons(model.getIsLiked());
                return;
            }
            return;
        }
        view.disableRowButtonIcon();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateRowButton$lambda-0, reason: not valid java name */
    public static final void m2337updateRowButton$lambda0(LibraryAddableBinder this$0, LibraryAddableModel model, LibraryAddableView view, View view2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(view, "$view");
        RowButtonOnClickProvider rowButtonOnClickProvider = this$0.rowButtonOnClickProvider;
        if (rowButtonOnClickProvider == null) {
            return;
        }
        rowButtonOnClickProvider.onClickAction(model, view);
    }

    protected final void bindLibraryStateSynchronously(V view, M model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        updateRowButton(view, model, shouldShowAddOrLikeButton(model));
    }

    protected final void bindLibraryState(final V view, final M model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.music.views.library.binders.LibraryAddableBinder$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LibraryAddableBinder.m2334bindLibraryState$lambda1(this.f$0, model, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.music.views.library.binders.LibraryAddableBinder$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LibraryAddableBinder.m2335bindLibraryState$lambda2(this.f$0, view, model, (Boolean) obj);
            }
        }, new Action1() { // from class: com.amazon.music.views.library.binders.LibraryAddableBinder$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LibraryAddableBinder.m2336bindLibraryState$lambda3((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindLibraryState$lambda-1, reason: not valid java name */
    public static final void m2334bindLibraryState$lambda1(LibraryAddableBinder this$0, LibraryAddableModel model, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        subscriber.onNext(Boolean.valueOf(this$0.shouldShowAddOrLikeButton(model)));
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindLibraryState$lambda-2, reason: not valid java name */
    public static final void m2335bindLibraryState$lambda2(LibraryAddableBinder this$0, LibraryAddableView view, LibraryAddableModel model, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.updateRowButton(view, model, it.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindLibraryState$lambda-3, reason: not valid java name */
    public static final void m2336bindLibraryState$lambda3(Throwable th) {
        Log.e(TAG, Intrinsics.stringPlus("An error happened when binding library state. The error is: ", th.getMessage()));
    }
}
