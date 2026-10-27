package dagger.spi.shaded.androidx.room.compiler.processing;

import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.squareup.javapoet.ClassName;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: XTypeElement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\n\bf\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004J\b\u0010\u0006\u001a\u00020\u0005H&J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u0007H&J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00000\u0007H&R\u0014\u0010\u0010\u001a\u00020\r8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0012\u001a\u00020\r8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0014\u0010\u0016\u001a\u00020\u00138&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0019\u001a\u00020\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001c\u001a\u0004\u0018\u00010\u00048&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u00008&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0018R\u0016\u0010\"\u001a\u0004\u0018\u00010\u001f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u001c\u0010&\u001a\u0004\u0018\u00010\u001f8VX\u0097\u0004¢\u0006\f\u0012\u0004\b$\u0010%\u001a\u0004\b#\u0010!R\u0014\u0010(\u001a\u00020\u001f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010!ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006)À\u0006\u0001"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/XTypeElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XHasModifiers;", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/XElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XMemberContainer;", "", "isInterface", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/XFieldElement;", "getDeclaredFields", "Ldagger/spi/shaded/androidx/room/compiler/processing/XMethodElement;", "getDeclaredMethods", "getSuperInterfaceElements", "", "getQualifiedName", "()Ljava/lang/String;", "qualifiedName", "getPackageName", CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, "Lcom/squareup/javapoet/ClassName;", "getClassName", "()Lcom/squareup/javapoet/ClassName;", "className", "getClosestMemberContainer", "()Landroidx/room/compiler/processing/XTypeElement;", "closestMemberContainer", "getEnclosingElement", "()Landroidx/room/compiler/processing/XMemberContainer;", "enclosingElement", "getEnclosingTypeElement", "enclosingTypeElement", "Ldagger/spi/shaded/androidx/room/compiler/processing/XType;", "getSuperClass", "()Landroidx/room/compiler/processing/XType;", "superClass", "getSuperType", "getSuperType$annotations", "()V", "superType", "getType", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public interface XTypeElement extends XHasModifiers, XElement, XMemberContainer {
    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XMemberContainer
    ClassName getClassName();

    List<XFieldElement> getDeclaredFields();

    List<XMethodElement> getDeclaredMethods();

    String getPackageName();

    String getQualifiedName();

    XType getSuperClass();

    List<XTypeElement> getSuperInterfaceElements();

    boolean isInterface();
}
