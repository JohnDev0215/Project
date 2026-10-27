package dagger.spi.shaded.androidx.room.compiler.processing;

import com.amazon.identity.auth.device.utils.AccountConstants;
import dagger.spi.shaded.androidx.room.compiler.processing.DeclarationCollectorKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: DeclarationCollector.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
@DebugMetadata(c = "dagger.spi.shaded.androidx.room.compiler.processing.DeclarationCollectorKt$collectFieldsIncludingPrivateSupers$1", f = "DeclarationCollector.kt", i = {0, 0, 0}, l = {31, 34}, m = "invokeSuspend$yieldAllFields", n = {"$this$invokeSuspend_u24yieldAllFields", "existingFieldNames", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE}, s = {"L$0", "L$1", "L$2"})
final class DeclarationCollectorKt$collectFieldsIncludingPrivateSupers$1$yieldAllFields$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    DeclarationCollectorKt$collectFieldsIncludingPrivateSupers$1$yieldAllFields$1(Continuation<? super DeclarationCollectorKt$collectFieldsIncludingPrivateSupers$1$yieldAllFields$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return DeclarationCollectorKt.C04691.invokeSuspend$yieldAllFields(null, null, null, this);
    }
}
