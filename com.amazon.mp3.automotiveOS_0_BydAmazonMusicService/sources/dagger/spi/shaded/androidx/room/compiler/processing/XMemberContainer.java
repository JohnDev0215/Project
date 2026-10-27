package dagger.spi.shaded.androidx.room.compiler.processing;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.squareup.javapoet.ClassName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: XMemberContainer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0005\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0014\u0010\t\u001a\u00020\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\r\u001a\u0004\u0018\u00010\n8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/XMemberContainer;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XElement;", "", "getName", "()Ljava/lang/String;", "name", "Lcom/squareup/javapoet/ClassName;", "getClassName", "()Lcom/squareup/javapoet/ClassName;", "className", "Ldagger/spi/shaded/androidx/room/compiler/processing/XType;", "getType", "()Landroidx/room/compiler/processing/XType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public interface XMemberContainer extends XElement {
    ClassName getClassName();

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XElement
    default String getName() {
        if (this instanceof XTypeElement) {
            return getName();
        }
        String strSimpleName = getClassName().simpleName();
        Intrinsics.checkNotNullExpressionValue(strSimpleName, "className.simpleName()");
        return strSimpleName;
    }
}
