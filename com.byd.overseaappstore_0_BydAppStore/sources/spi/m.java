package spi;

import android.content.ClipData;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCallerKt;
import androidx.core.util.Predicate;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class m implements ActivityResultCallback, Predicate {
    public final /* synthetic */ Object a;

    public /* synthetic */ m(Object obj) {
        this.a = obj;
    }

    @Override // androidx.activity.result.ActivityResultCallback
    public final void onActivityResult(Object obj) {
        ActivityResultCallerKt.m3registerForActivityResult$lambda0((Function1) this.a, obj);
    }

    @Override // androidx.core.util.Predicate
    public final boolean test(Object obj) {
        return ((java.util.function.Predicate) this.a).test((ClipData.Item) obj);
    }
}
