package com.amazon.mp3.auto.carmode.detection;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;

/* JADX INFO: compiled from: SpecializedCarDetector.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000e\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0013\u0010\u0014J(\u0010\t\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0004J\b\u0010\n\u001a\u00020\u0007H$J\b\u0010\u000b\u001a\u00020\u0002H\u0004R\u0016\u0010\f\u001a\u00020\u00028\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\f\u0010\rR4\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00048\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\b\b\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/auto/carmode/detection/SpecializedCarDetector;", "", "Landroid/content/Context;", "context", "Lkotlin/reflect/KFunction2;", "", "Lcom/amazon/mp3/auto/carmode/detection/CarDetectionSource;", "", "updateCarStatus", "init", "registerListeners", "getContext", "mContext", "Landroid/content/Context;", "Lkotlin/reflect/KFunction;", "getUpdateCarStatus", "()Lkotlin/reflect/KFunction;", "setUpdateCarStatus", "(Lkotlin/reflect/KFunction;)V", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public abstract class SpecializedCarDetector {
    private Context mContext;
    protected KFunction<Unit> updateCarStatus;

    protected abstract void registerListeners();

    protected final KFunction<Unit> getUpdateCarStatus() {
        KFunction<Unit> kFunction = this.updateCarStatus;
        if (kFunction != null) {
            return kFunction;
        }
        Intrinsics.throwUninitializedPropertyAccessException("updateCarStatus");
        return null;
    }

    protected final void setUpdateCarStatus(KFunction<Unit> kFunction) {
        Intrinsics.checkNotNullParameter(kFunction, "<set-?>");
        this.updateCarStatus = kFunction;
    }

    public final void init(Context context, KFunction<Unit> updateCarStatus) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(updateCarStatus, "updateCarStatus");
        this.mContext = context;
        setUpdateCarStatus(updateCarStatus);
        registerListeners();
    }

    protected final Context getContext() {
        Context context = this.mContext;
        if (context != null) {
            return context;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mContext");
        return null;
    }
}
