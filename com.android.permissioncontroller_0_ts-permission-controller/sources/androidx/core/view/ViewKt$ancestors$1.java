package androidx.core.view;

import android.view.ViewParent;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* JADX INFO: compiled from: View.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroid/view/ViewParent;", "kotlin.jvm.PlatformType", "p1", "invoke"}, k = 3, mv = {1, 1, 16})
final /* synthetic */ class ViewKt$ancestors$1 extends FunctionReference implements Function1<ViewParent, ViewParent> {
    public static final ViewKt$ancestors$1 INSTANCE = new ViewKt$ancestors$1();

    ViewKt$ancestors$1() {
        super(1);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "getParent";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(ViewParent.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "getParent()Landroid/view/ViewParent;";
    }

    @Override // kotlin.jvm.functions.Function1
    public final ViewParent invoke(ViewParent p1) {
        Intrinsics.checkParameterIsNotNull(p1, "p1");
        return p1.getParent();
    }
}
