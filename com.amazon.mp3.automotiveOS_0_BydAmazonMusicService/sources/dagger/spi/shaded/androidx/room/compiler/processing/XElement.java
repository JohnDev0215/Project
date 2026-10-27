package dagger.spi.shaded.androidx.room.compiler.processing;

import kotlin.Metadata;

/* JADX INFO: compiled from: XElement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0005\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0014\u0010\t\u001a\u00020\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\f\u001a\u0004\u0018\u00010\u00008&X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/XElement;", "", "", "getName", "()Ljava/lang/String;", "name", "Ldagger/spi/shaded/androidx/room/compiler/processing/XMemberContainer;", "getClosestMemberContainer", "()Landroidx/room/compiler/processing/XMemberContainer;", "closestMemberContainer", "getEnclosingElement", "()Landroidx/room/compiler/processing/XElement;", "enclosingElement", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public interface XElement {
    XMemberContainer getClosestMemberContainer();

    XElement getEnclosingElement();

    String getName();
}
