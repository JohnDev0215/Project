package com.amazon.music.find.utils;

import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: RxJavaUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/music/find/utils/RxJavaUtils;", "", "()V", "TAG", "", "logger", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "configureRxJavaGlobalErrorHandler", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RxJavaUtils {
    private static final String TAG = "SPL";
    public static final RxJavaUtils INSTANCE = new RxJavaUtils();
    private static final Logger logger = LoggerFactory.getLogger(RxJavaUtils.class.getName());

    private RxJavaUtils() {
    }

    public final void configureRxJavaGlobalErrorHandler() {
        RxJavaPlugins.setErrorHandler(new Consumer() { // from class: com.amazon.music.find.utils.RxJavaUtils$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                RxJavaUtils.m2063configureRxJavaGlobalErrorHandler$lambda0((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: configureRxJavaGlobalErrorHandler$lambda-0, reason: not valid java name */
    public static final void m2063configureRxJavaGlobalErrorHandler$lambda0(Throwable th) {
        Logger logger2 = logger;
        String strStringPlus = Intrinsics.stringPlus("SPL::RxJavaPlugins. Message=", th.getMessage());
        if (strStringPlus == null) {
            strStringPlus = "";
        }
        logger2.error(strStringPlus);
    }
}
