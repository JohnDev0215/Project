package com.google.cloud.location;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface ListLocationsResponseOrBuilder extends MessageOrBuilder {
    Location getLocations(int i);

    int getLocationsCount();

    List<Location> getLocationsList();

    LocationOrBuilder getLocationsOrBuilder(int i);

    List<? extends LocationOrBuilder> getLocationsOrBuilderList();

    String getNextPageToken();

    ByteString getNextPageTokenBytes();
}
