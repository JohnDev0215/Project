package com.amazon.mp3.styles;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import com.amazon.mp3.util.Log;
import com.amazon.music.inappmessaging.external.DynamicMessageStyleSheetLoader;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

/* JADX INFO: compiled from: StyleSheetProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0007J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000eH\u0007J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\b\u0010\u0014\u001a\u00020\u0010H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/styles/StyleSheetProvider;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheetLiveData", "Landroidx/lifecycle/MutableLiveData;", "styleSheetPublisher", "Lrx/subjects/BehaviorSubject;", "getStyleSheet", "getStyleSheetObservable", "Lrx/Observable;", "initDefaultStyleSheet", "", "context", "Landroid/content/Context;", "initializeStyleSheet", "subscribeToInitCompleted", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StyleSheetProvider {
    private static StyleSheet styleSheet;
    private static final BehaviorSubject<StyleSheet> styleSheetPublisher;
    public static final StyleSheetProvider INSTANCE = new StyleSheetProvider();
    private static final String TAG = StyleSheetProvider.class.getSimpleName();
    private static final MutableLiveData<StyleSheet> styleSheetLiveData = new MutableLiveData<>();

    private StyleSheetProvider() {
    }

    static {
        BehaviorSubject<StyleSheet> behaviorSubjectCreate = BehaviorSubject.create();
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreate, "create()");
        styleSheetPublisher = behaviorSubjectCreate;
    }

    @JvmStatic
    public static final synchronized void initializeStyleSheet(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        INSTANCE.initDefaultStyleSheet(context);
    }

    @Deprecated(message = "Use the new getStyleSheetObservable() method")
    @JvmStatic
    public static final MutableLiveData<StyleSheet> getStyleSheet() {
        return styleSheetLiveData;
    }

    @JvmStatic
    public static final Observable<StyleSheet> getStyleSheetObservable() {
        Observable<StyleSheet> observableAsObservable = styleSheetPublisher.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "styleSheetPublisher.asObservable()");
        return observableAsObservable;
    }

    private final void initDefaultStyleSheet(Context context) {
        if (styleSheet == null) {
            styleSheet = new DefaultStyleSheet(context);
            Log.debug(TAG, "Create new DefaultStyleSheet instance");
            subscribeToInitCompleted();
        }
    }

    private final void subscribeToInitCompleted() {
        StyleSheet styleSheet2 = styleSheet;
        if (styleSheet2 == null) {
            return;
        }
        styleSheet2.initCompletable().subscribe(new Action0() { // from class: com.amazon.mp3.styles.StyleSheetProvider$$ExternalSyntheticLambda0
            @Override // rx.functions.Action0
            public final void call() {
                StyleSheetProvider.m1644subscribeToInitCompleted$lambda2$lambda0();
            }
        }, new Action1() { // from class: com.amazon.mp3.styles.StyleSheetProvider$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                StyleSheetProvider.m1645subscribeToInitCompleted$lambda2$lambda1((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: subscribeToInitCompleted$lambda-2$lambda-0, reason: not valid java name */
    public static final void m1644subscribeToInitCompleted$lambda2$lambda0() {
        DynamicMessageStyleSheetLoader.getInstance().setStyleSheet(styleSheet);
        styleSheetPublisher.onNext(styleSheet);
        styleSheetLiveData.postValue(styleSheet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: subscribeToInitCompleted$lambda-2$lambda-1, reason: not valid java name */
    public static final void m1645subscribeToInitCompleted$lambda2$lambda1(Throwable th) {
        Log.error(TAG, Intrinsics.stringPlus("Error occurred while initializing styleSheet: ", th));
    }
}
