package com.google.api;

import com.google.protobuf.MessageOrBuilder;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface RoutingRuleOrBuilder extends MessageOrBuilder {
    RoutingParameter getRoutingParameters(int i);

    int getRoutingParametersCount();

    List<RoutingParameter> getRoutingParametersList();

    RoutingParameterOrBuilder getRoutingParametersOrBuilder(int i);

    List<? extends RoutingParameterOrBuilder> getRoutingParametersOrBuilderList();
}
