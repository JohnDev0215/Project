package dagger.spi.shaded.androidx.room.compiler.processing.ksp;

import com.google.devtools.ksp.symbol.KSAnnotated;
import dagger.spi.shaded.androidx.room.compiler.processing.XElement;
import dagger.spi.shaded.androidx.room.compiler.processing.XEquality;
import kotlin.Lazy;
import kotlin.Metadata;

/* JADX INFO: compiled from: KspElement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b \u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u0013\u0010\u0006\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0096\u0002J\b\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\n\u001a\u00020\tH\u0016R\u001a\u0010\f\u001a\u00020\u000b8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR%\u0010\u0015\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00030\u00108FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0017\u001a\u00020\u00168\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XEquality;", "", "other", "", "equals", "", "hashCode", "", "toString", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspProcessingEnv;", "env", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspProcessingEnv;", "getEnv$room_compiler_processing", "()Landroidx/room/compiler/processing/ksp/KspProcessingEnv;", "", "equalityItems$delegate", "Lkotlin/Lazy;", "getEqualityItems", "()[Ljava/lang/Object;", "equalityItems", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "declaration", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "getDeclaration", "()Lcom/google/devtools/ksp/symbol/KSAnnotated;", "<init>", "(Landroidx/room/compiler/processing/ksp/KspProcessingEnv;Lcom/google/devtools/ksp/symbol/KSAnnotated;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public abstract class KspElement implements XElement, XEquality {
    private final KspProcessingEnv env;

    /* JADX INFO: renamed from: equalityItems$delegate, reason: from kotlin metadata */
    private final Lazy equalityItems;

    public abstract KSAnnotated getDeclaration();

    /* JADX INFO: renamed from: getEnv$room_compiler_processing, reason: from getter */
    public final KspProcessingEnv getEnv() {
        return this.env;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XEquality
    public final Object[] getEqualityItems() {
        return (Object[]) this.equalityItems.getValue();
    }

    public boolean equals(Object other) {
        return XEquality.INSTANCE.equals(this, other);
    }

    public int hashCode() {
        return XEquality.INSTANCE.hashCode(getEqualityItems());
    }

    public String toString() {
        return getDeclaration().toString();
    }
}
