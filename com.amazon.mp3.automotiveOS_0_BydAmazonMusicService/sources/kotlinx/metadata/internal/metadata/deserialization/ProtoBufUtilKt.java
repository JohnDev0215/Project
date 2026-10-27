package kotlinx.metadata.internal.metadata.deserialization;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.internal.protobuf.GeneratedMessageLite;

/* JADX INFO: compiled from: ProtoBufUtil.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001aA\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0005¢\u0006\u0002\u0010\u0006\u001aO\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00070\u00052\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"getExtensionOrNull", "T", "M", "Lkotlinx/metadata/internal/protobuf/GeneratedMessageLite$ExtendableMessage;", "extension", "Lkotlinx/metadata/internal/protobuf/GeneratedMessageLite$GeneratedExtension;", "(Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$ExtendableMessage;Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$GeneratedExtension;)Ljava/lang/Object;", "", "index", "", "(Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$ExtendableMessage;Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$GeneratedExtension;I)Ljava/lang/Object;", "metadata"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class ProtoBufUtilKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <M extends GeneratedMessageLite.ExtendableMessage<M>, T> T getExtensionOrNull(GeneratedMessageLite.ExtendableMessage<M> extendableMessage, GeneratedMessageLite.GeneratedExtension<M, T> extension) {
        Intrinsics.checkNotNullParameter(extendableMessage, "<this>");
        Intrinsics.checkNotNullParameter(extension, "extension");
        if (extendableMessage.hasExtension(extension)) {
            return (T) extendableMessage.getExtension(extension);
        }
        return null;
    }
}
