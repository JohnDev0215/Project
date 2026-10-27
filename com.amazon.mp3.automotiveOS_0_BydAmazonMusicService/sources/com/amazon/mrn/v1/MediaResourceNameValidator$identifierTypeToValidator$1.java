package com.amazon.mrn.v1;

import com.amazon.mrn.v1.MediaResourceNameValidator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MediaResourceNameValidator.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
/* synthetic */ class MediaResourceNameValidator$identifierTypeToValidator$1 extends FunctionReferenceImpl implements Function1<String, Unit> {
    MediaResourceNameValidator$identifierTypeToValidator$1(Object obj) {
        super(1, obj, MediaResourceNameValidator.MrnValueValidator.class, "uuidValidator", "uuidValidator(Ljava/lang/String;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((MediaResourceNameValidator.MrnValueValidator) this.receiver).uuidValidator(p0);
    }
}
