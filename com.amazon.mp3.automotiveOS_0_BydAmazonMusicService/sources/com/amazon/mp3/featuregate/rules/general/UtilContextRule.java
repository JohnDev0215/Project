package com.amazon.mp3.featuregate.rules.general;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.music.platform.featuregate.Rule;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UtilContextRule.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B%\b\u0016\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0005H\u0016R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/featuregate/rules/general/UtilContextRule;", "Lcom/amazon/music/platform/featuregate/Rule;", "utilMethod", "Lkotlin/Function1;", "Landroid/content/Context;", "", "condition", "(Lkotlin/jvm/functions/Function1;Z)V", "isSatisfied", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UtilContextRule implements Rule {
    private boolean condition;
    private Function1<? super Context, Boolean> utilMethod;

    public UtilContextRule(Function1<? super Context, Boolean> utilMethod, boolean z) {
        Intrinsics.checkNotNullParameter(utilMethod, "utilMethod");
        this.utilMethod = utilMethod;
        this.condition = z;
    }

    public /* synthetic */ UtilContextRule(Function1 function1, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function1, (i & 2) != 0 ? true : z);
    }

    @Override // com.amazon.music.platform.featuregate.Rule
    /* JADX INFO: renamed from: isSatisfied */
    public boolean getValue() {
        Function1<? super Context, Boolean> function1 = this.utilMethod;
        Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplication().applicationContext");
        return function1.invoke(applicationContext).booleanValue() == this.condition;
    }
}
