package dagger.spi.shaded.androidx.room.compiler.processing;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.TypeName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: XProcessingEnv.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u0000 '2\u00020\u0001:\u0002('J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0006H&J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\fH\u0016J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\fH\u0016J+\u0010\u0011\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00042\u0012\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u000f\"\u00020\u0006H&¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0016\u001a\u00020\u00138&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u001a\u001a\u00020\u00178&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001e\u001a\u00020\u001b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\"\u001a\u00020\u001f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0014\u0010&\u001a\u00020#8&X¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006)À\u0006\u0001"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnv;", "", "", "qName", "Ldagger/spi/shaded/androidx/room/compiler/processing/XTypeElement;", "findTypeElement", "Ldagger/spi/shaded/androidx/room/compiler/processing/XType;", "findType", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Ldagger/spi/shaded/androidx/room/compiler/processing/XArrayType;", "getArrayType", "requireTypeElement", "Lcom/squareup/javapoet/TypeName;", "typeName", "requireType", "", "types", "getDeclaredType", "(Landroidx/room/compiler/processing/XTypeElement;[Landroidx/room/compiler/processing/XType;)Landroidx/room/compiler/processing/XType;", "", "getJvmVersion", "()I", "jvmVersion", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnv$Backend;", "getBackend", "()Landroidx/room/compiler/processing/XProcessingEnv$Backend;", "backend", "Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnvConfig;", "getConfig", "()Landroidx/room/compiler/processing/XProcessingEnvConfig;", "config", "Ldagger/spi/shaded/androidx/room/compiler/processing/XFiler;", "getFiler", "()Landroidx/room/compiler/processing/XFiler;", "filer", "Ldagger/spi/shaded/androidx/room/compiler/processing/XMessager;", "getMessager", "()Landroidx/room/compiler/processing/XMessager;", "messager", "Companion", "Backend", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public interface XProcessingEnv {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: XProcessingEnv.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnv$Backend;", "", "(Ljava/lang/String;I)V", "JAVAC", "KSP", "room-compiler-processing"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public enum Backend {
        JAVAC,
        KSP
    }

    XType findType(String qName);

    XTypeElement findTypeElement(String qName);

    XArrayType getArrayType(XType type);

    Backend getBackend();

    XProcessingEnvConfig getConfig();

    XType getDeclaredType(XTypeElement xTypeElement, XType... xTypeArr);

    int getJvmVersion();

    default XTypeElement requireTypeElement(String qName) {
        Intrinsics.checkNotNullParameter(qName, "qName");
        XTypeElement xTypeElementFindTypeElement = findTypeElement(qName);
        if (xTypeElementFindTypeElement != null) {
            return xTypeElementFindTypeElement;
        }
        throw new IllegalStateException(("Cannot find required type element " + qName).toString());
    }

    default XType requireType(TypeName typeName) {
        Intrinsics.checkNotNullParameter(typeName, "typeName");
        XType xTypeFindType = findType(typeName);
        if (xTypeFindType != null) {
            return xTypeFindType;
        }
        throw new IllegalStateException(("cannot find required type " + typeName).toString());
    }

    default XType findType(TypeName typeName) {
        Intrinsics.checkNotNullParameter(typeName, "typeName");
        if (typeName instanceof ArrayTypeName) {
            TypeName typeName2 = ((ArrayTypeName) typeName).componentType;
            Intrinsics.checkNotNullExpressionValue(typeName2, "typeName.componentType");
            XType xTypeFindType = findType(typeName2);
            if (xTypeFindType != null) {
                return getArrayType(xTypeFindType);
            }
            return null;
        }
        String string = typeName.toString();
        Intrinsics.checkNotNullExpressionValue(string, "typeName.toString()");
        return findType(string);
    }

    default XTypeElement requireTypeElement(TypeName typeName) {
        Intrinsics.checkNotNullParameter(typeName, "typeName");
        String string = typeName.toString();
        Intrinsics.checkNotNullExpressionValue(string, "typeName.toString()");
        return requireTypeElement(string);
    }

    /* JADX INFO: compiled from: XProcessingEnv.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/XProcessingEnv$Companion;", "", "<init>", "()V", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }
}
