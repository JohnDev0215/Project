package com.amazonaws.services.iotdata;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.regions.Region;
import com.amazonaws.services.iotdata.model.DeleteThingShadowRequest;
import com.amazonaws.services.iotdata.model.DeleteThingShadowResult;
import com.amazonaws.services.iotdata.model.GetRetainedMessageRequest;
import com.amazonaws.services.iotdata.model.GetRetainedMessageResult;
import com.amazonaws.services.iotdata.model.GetThingShadowRequest;
import com.amazonaws.services.iotdata.model.GetThingShadowResult;
import com.amazonaws.services.iotdata.model.ListNamedShadowsForThingRequest;
import com.amazonaws.services.iotdata.model.ListNamedShadowsForThingResult;
import com.amazonaws.services.iotdata.model.ListRetainedMessagesRequest;
import com.amazonaws.services.iotdata.model.ListRetainedMessagesResult;
import com.amazonaws.services.iotdata.model.PublishRequest;
import com.amazonaws.services.iotdata.model.UpdateThingShadowRequest;
import com.amazonaws.services.iotdata.model.UpdateThingShadowResult;

/* JADX INFO: loaded from: classes5.dex */
public interface AWSIotData {
    DeleteThingShadowResult deleteThingShadow(DeleteThingShadowRequest deleteThingShadowRequest) throws AmazonClientException;

    ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest amazonWebServiceRequest);

    GetRetainedMessageResult getRetainedMessage(GetRetainedMessageRequest getRetainedMessageRequest) throws AmazonClientException;

    GetThingShadowResult getThingShadow(GetThingShadowRequest getThingShadowRequest) throws AmazonClientException;

    ListNamedShadowsForThingResult listNamedShadowsForThing(ListNamedShadowsForThingRequest listNamedShadowsForThingRequest) throws AmazonClientException;

    ListRetainedMessagesResult listRetainedMessages(ListRetainedMessagesRequest listRetainedMessagesRequest) throws AmazonClientException;

    void publish(PublishRequest publishRequest) throws AmazonClientException;

    void setEndpoint(String str) throws IllegalArgumentException;

    void setRegion(Region region) throws IllegalArgumentException;

    void shutdown();

    UpdateThingShadowResult updateThingShadow(UpdateThingShadowRequest updateThingShadowRequest) throws AmazonClientException;
}
