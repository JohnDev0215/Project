package dagger.spi.shaded.androidx.room.compiler.processing.ksp;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.devtools.ksp.symbol.KSAnnotation;
import dagger.spi.shaded.androidx.room.compiler.processing.InternalXAnnotation;
import dagger.spi.shaded.androidx.room.compiler.processing.XAnnotationValue;
import dagger.spi.shaded.androidx.room.compiler.processing.XType;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;

/* JADX INFO: compiled from: KspAnnotation.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u0011\u001a\u00020\f8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R!\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00130\u00128VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u000e\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u001b\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001e"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspAnnotation;", "Ldagger/spi/shaded/androidx/room/compiler/processing/InternalXAnnotation;", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspProcessingEnv;", "env", "Ldagger/spi/shaded/androidx/room/compiler/processing/ksp/KspProcessingEnv;", "getEnv", "()Landroidx/room/compiler/processing/ksp/KspProcessingEnv;", "Lcom/google/devtools/ksp/symbol/KSAnnotation;", "ksAnnotated", "Lcom/google/devtools/ksp/symbol/KSAnnotation;", "getKsAnnotated", "()Lcom/google/devtools/ksp/symbol/KSAnnotation;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XType;", "type$delegate", "Lkotlin/Lazy;", "getType", "()Landroidx/room/compiler/processing/XType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "", "Ldagger/spi/shaded/androidx/room/compiler/processing/XAnnotationValue;", "annotationValues$delegate", "getAnnotationValues", "()Ljava/util/List;", "annotationValues", "", "getName", "()Ljava/lang/String;", "name", "<init>", "(Landroidx/room/compiler/processing/ksp/KspProcessingEnv;Lcom/google/devtools/ksp/symbol/KSAnnotation;)V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public final class KspAnnotation extends InternalXAnnotation {

    /* JADX INFO: renamed from: annotationValues$delegate, reason: from kotlin metadata */
    private final Lazy annotationValues;
    private final KspProcessingEnv env;
    private final KSAnnotation ksAnnotated;

    /* JADX INFO: renamed from: type$delegate, reason: from kotlin metadata */
    private final Lazy type;

    public final KspProcessingEnv getEnv() {
        return this.env;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XAnnotation
    public String getName() {
        return this.ksAnnotated.getShortName().asString();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XAnnotation
    public XType getType() {
        return (XType) this.type.getValue();
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XAnnotation
    public List<XAnnotationValue> getAnnotationValues() {
        return (List) this.annotationValues.getValue();
    }
}
