package com.android.permissioncontroller.incident;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public interface RestrictedImageSetProtoOrBuilder extends MessageLiteOrBuilder {
    String getCategory();

    ByteString getCategoryBytes();

    RestrictedImageProto getImages(int i);

    int getImagesCount();

    List<RestrictedImageProto> getImagesList();

    ByteString getMetadata();

    boolean hasCategory();

    boolean hasMetadata();
}
