package com.amazon.mp3.find.view;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.SearchView;
import androidx.appcompat.widget.AppCompatImageView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.OneTimeExperience;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.ToolTipDialog;
import com.amazon.music.views.library.views.presentation.TooltipExperienceDecorator;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FindLandingATCSkipTooltipExperience.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00192\u00020\u00012\u00020\u0002:\u0001\u0019B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0015\u001a\u00020\rH\u0016J\u001a\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u001a\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00132\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/find/view/FindLandingATCSkipTooltipExperience;", "Lcom/amazon/music/views/library/views/presentation/TooltipExperienceDecorator;", "Lcom/amazon/mp3/util/OneTimeExperience;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "bauhausTooltip", "Lcom/amazon/music/views/library/views/ToolTipDialog;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "tooltipDisposable", "Lio/reactivex/disposables/Disposable;", "dismissTooltip", "", "dismissPermanently", "", "getOffsetX", "", "actionBar", "Landroid/view/View;", "getOffsetY", "onDestroy", "show", "showTooltip", "view", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FindLandingATCSkipTooltipExperience extends OneTimeExperience implements TooltipExperienceDecorator {
    private static final String TAG;
    private ToolTipDialog bauhausTooltip;
    private StyleSheet styleSheet;
    private Disposable tooltipDisposable;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FindLandingATCSkipTooltipExperience(Context context) {
        super("find_landing_tooltip_experience", context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    static {
        String simpleName = FindLandingATCSkipTooltipExperience.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "FindLandingATCSkipToolti…ce::class.java.simpleName");
        TAG = simpleName;
    }

    @Override // com.amazon.mp3.util.OneTimeExperience, com.amazon.music.views.library.views.presentation.TooltipExperienceDecorator
    public void onDestroy() {
        ToolTipDialog toolTipDialog = this.bauhausTooltip;
        if (toolTipDialog != null) {
            toolTipDialog.dismiss();
        }
        this.bauhausTooltip = null;
        Disposable disposable = this.tooltipDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.tooltipDisposable = null;
        super.onDestroy();
    }

    @Override // com.amazon.music.views.library.views.presentation.TooltipExperienceDecorator
    public void dismissTooltip(boolean dismissPermanently) {
        throw new NotImplementedError(Intrinsics.stringPlus("An operation is not implemented: ", "Not yet implemented"));
    }

    @Override // com.amazon.music.views.library.views.presentation.TooltipExperienceDecorator
    public void showTooltip(final View view, final StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.styleSheet = styleSheet;
        this.tooltipDisposable = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.find.view.FindLandingATCSkipTooltipExperience$$ExternalSyntheticLambda0
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                FindLandingATCSkipTooltipExperience.m1025showTooltip$lambda0(this.f$0, observableEmitter);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.find.view.FindLandingATCSkipTooltipExperience$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                FindLandingATCSkipTooltipExperience.m1026showTooltip$lambda2(this.f$0, view, styleSheet, (Boolean) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.find.view.FindLandingATCSkipTooltipExperience$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                FindLandingATCSkipTooltipExperience.m1027showTooltip$lambda3((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTooltip$lambda-0, reason: not valid java name */
    public static final void m1025showTooltip$lambda0(FindLandingATCSkipTooltipExperience this$0, ObservableEmitter emitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        emitter.onNext(Boolean.valueOf(this$0.canPresentExperience()));
        emitter.onComplete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTooltip$lambda-2, reason: not valid java name */
    public static final void m1026showTooltip$lambda2(FindLandingATCSkipTooltipExperience this$0, View view, StyleSheet styleSheet, Boolean success) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        if (success == null) {
            return;
        }
        success.booleanValue();
        Intrinsics.checkNotNullExpressionValue(success, "success");
        if (success.booleanValue()) {
            this$0.show(view, styleSheet);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTooltip$lambda-3, reason: not valid java name */
    public static final void m1027showTooltip$lambda3(Throwable th) {
        Log.error(TAG, th.toString());
    }

    private final void show(View actionBar, StyleSheet styleSheet) {
        if (styleSheet == null) {
            return;
        }
        ToolTipDialog toolTipDialog = this.bauhausTooltip;
        if (toolTipDialog != null) {
            toolTipDialog.dismiss();
        }
        Context context = actionBar.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "actionBar.context");
        ToolTipDialog text = new ToolTipDialog(context, styleSheet).setPosition(ToolTipDialog.Position.BOTTOM).setAnchorPoint(getOffsetX(actionBar), getOffsetY(actionBar)).setText(R.string.find_first_time_ATC_skip_tooltip);
        this.bauhausTooltip = text;
        if (text != null) {
            text.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.amazon.mp3.find.view.FindLandingATCSkipTooltipExperience$$ExternalSyntheticLambda3
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    FindLandingATCSkipTooltipExperience.m1023show$lambda6$lambda4(this.f$0, dialogInterface);
                }
            });
        }
        ToolTipDialog toolTipDialog2 = this.bauhausTooltip;
        if (toolTipDialog2 != null) {
            toolTipDialog2.setToolTipClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.find.view.FindLandingATCSkipTooltipExperience$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FindLandingATCSkipTooltipExperience.m1024show$lambda6$lambda5(this.f$0, view);
                }
            });
        }
        ToolTipDialog toolTipDialog3 = this.bauhausTooltip;
        if (toolTipDialog3 == null) {
            return;
        }
        toolTipDialog3.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: show$lambda-6$lambda-4, reason: not valid java name */
    public static final void m1023show$lambda6$lambda4(FindLandingATCSkipTooltipExperience this$0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.disableExperiencePresentation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: show$lambda-6$lambda-5, reason: not valid java name */
    public static final void m1024show$lambda6$lambda5(FindLandingATCSkipTooltipExperience this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ToolTipDialog toolTipDialog = this$0.bauhausTooltip;
        if (toolTipDialog == null) {
            return;
        }
        toolTipDialog.dismiss();
    }

    private final int getOffsetX(View actionBar) {
        SearchView searchView = (SearchView) actionBar.findViewById(R.id.search_view);
        AppCompatImageView appCompatImageView = (AppCompatImageView) searchView.findViewById(searchView.getContext().getResources().getIdentifier("android:id/search_mag_icon", null, null));
        return actionBar.getLeft() + appCompatImageView.getLeft() + ((appCompatImageView.getRight() - appCompatImageView.getLeft()) / 2);
    }

    private final int getOffsetY(View actionBar) {
        return actionBar.getTop() + ((SearchView) actionBar.findViewById(R.id.search_view)).getBottom();
    }
}
