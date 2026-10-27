package io.grpc.protobuf;

import com.google.protobuf.Descriptors;
import javax.annotation.CheckReturnValue;

/* JADX INFO: loaded from: classes3.dex */
public interface ProtoMethodDescriptorSupplier extends ProtoServiceDescriptorSupplier {
    @CheckReturnValue
    Descriptors.MethodDescriptor getMethodDescriptor();
}
