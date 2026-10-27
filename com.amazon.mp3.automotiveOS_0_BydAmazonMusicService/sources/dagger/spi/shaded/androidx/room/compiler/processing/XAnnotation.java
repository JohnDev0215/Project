package dagger.spi.shaded.androidx.room.compiler.processing;

import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: XAnnotation.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0005\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\u000e\u001a\u00020\u000b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0012\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0013À\u0006\u0001"}, d2 = {"Ldagger/spi/shaded/androidx/room/compiler/processing/XAnnotation;", "", "", "getName", "()Ljava/lang/String;", "name", "", "Ldagger/spi/shaded/androidx/room/compiler/processing/XAnnotationValue;", "getAnnotationValues", "()Ljava/util/List;", "annotationValues", "Ldagger/spi/shaded/androidx/room/compiler/processing/XType;", "getType", "()Landroidx/room/compiler/processing/XType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Ldagger/spi/shaded/androidx/room/compiler/processing/XTypeElement;", "getTypeElement", "()Landroidx/room/compiler/processing/XTypeElement;", "typeElement", "room-compiler-processing"}, k = 1, mv = {1, 7, 1})
public interface XAnnotation {
    List<XAnnotationValue> getAnnotationValues();

    String getName();

    XType getType();
}
