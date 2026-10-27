package com.google.api;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface ProjectPropertiesOrBuilder extends MessageOrBuilder {
    Property getProperties(int i);

    int getPropertiesCount();

    List<Property> getPropertiesList();

    PropertyOrBuilder getPropertiesOrBuilder(int i);

    List<? extends PropertyOrBuilder> getPropertiesOrBuilderList();
}
