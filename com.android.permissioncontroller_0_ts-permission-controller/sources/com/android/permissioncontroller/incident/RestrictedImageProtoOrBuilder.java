package com.android.permissioncontroller.incident;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

/* JADX INFO: loaded from: classes8.dex */
public interface RestrictedImageProtoOrBuilder extends MessageLiteOrBuilder {
    ByteString getImageData();

    ByteString getMetadata();

    String getMimeType();

    ByteString getMimeTypeBytes();

    boolean hasImageData();

    boolean hasMetadata();

    boolean hasMimeType();
}
