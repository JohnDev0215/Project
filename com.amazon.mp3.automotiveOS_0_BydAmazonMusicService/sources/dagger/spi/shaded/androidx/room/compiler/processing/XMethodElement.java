package dagger.spi.shaded.androidx.room.compiler.processing;

import com.amazon.identity.auth.device.api.MultipleAccountManager;
import dagger.spi.shaded.androidx.room.compiler.processing.util.NamingUtilsKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: XMethodElement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H&J\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005H&J\b\u0010\b\u001a\u00020\u0002H\u0016R\u0014\u0010\f\u001a\u00020\t8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\r8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0014\u001a\u00020\u00118&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0015À\u0006\u0001"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/XMethodElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XExecutableElement;", "", "isSuspendFunction", "other", "Ldagger/spi/shaded/androidx/room/compiler/processing/XTypeElement;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER, "overrides", "hasValidJvmSourceName", "", "getJvmName", "()Ljava/lang/String;", "jvmName", "", "getExecutableType", "()Landroidx/room/compiler/processing/XMethodType;", "executableType", "Ldagger/spi/shaded/androidx/room/compiler/processing/XType;", "getReturnType", "()Landroidx/room/compiler/processing/XType;", "returnType", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public interface XMethodElement extends XExecutableElement {
    String getJvmName();

    boolean isSuspendFunction();

    boolean overrides(XMethodElement other, XTypeElement owner);

    default boolean hasValidJvmSourceName() {
        return NamingUtilsKt.isValidJavaSourceName(getJvmName());
    }
}
