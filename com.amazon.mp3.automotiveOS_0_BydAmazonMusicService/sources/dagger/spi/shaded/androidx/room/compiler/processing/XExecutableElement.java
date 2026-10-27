package dagger.spi.shaded.androidx.room.compiler.processing;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: XExecutableElement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\f\u001a\u00020\t8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u000f\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/XExecutableElement;", "Ldagger/spi/shaded/androidx/room/compiler/processing/XHasModifiers;", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/XElement;", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/XExecutableParameterElement;", "getParameters", "()Ljava/util/List;", "parameters", "Ldagger/spi/shaded/androidx/room/compiler/processing/XMemberContainer;", "getEnclosingElement", "()Landroidx/room/compiler/processing/XMemberContainer;", "enclosingElement", "getExecutableType", "()Landroidx/room/compiler/processing/XExecutableType;", "executableType", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public interface XExecutableElement extends XHasModifiers, XElement {
    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XElement
    XMemberContainer getEnclosingElement();

    List<XExecutableParameterElement> getParameters();
}
