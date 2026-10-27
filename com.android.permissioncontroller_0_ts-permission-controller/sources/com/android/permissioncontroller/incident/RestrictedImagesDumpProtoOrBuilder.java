package com.android.permissioncontroller.incident;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public interface RestrictedImagesDumpProtoOrBuilder extends MessageLiteOrBuilder {
    RestrictedImageSetProto getSets(int i);

    int getSetsCount();

    List<RestrictedImageSetProto> getSetsList();
}
