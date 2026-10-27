package com.android.permissioncontroller.incident;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public interface IncidentMinimalOrBuilder extends MessageLiteOrBuilder {
    IncidentHeaderProto getHeader(int i);

    int getHeaderCount();

    List<IncidentHeaderProto> getHeaderList();

    RestrictedImagesDumpProto getRestrictedImagesSection();

    boolean hasRestrictedImagesSection();
}
