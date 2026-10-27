package com.amazonaws.services.iotdata;

import com.amazonaws.AmazonWebServiceClient;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.AmazonWebServiceResponse;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Request;
import com.amazonaws.Response;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.handlers.HandlerChainFactory;
import com.amazonaws.http.ExecutionContext;
import com.amazonaws.http.HttpClient;
import com.amazonaws.http.HttpResponseHandler;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.http.JsonResponseHandler;
import com.amazonaws.http.UrlHttpClient;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.metrics.RequestMetricCollector;
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
import com.amazonaws.services.iotdata.model.transform.ConflictExceptionUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.DeleteThingShadowRequestMarshaller;
import com.amazonaws.services.iotdata.model.transform.DeleteThingShadowResultJsonUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.GetRetainedMessageRequestMarshaller;
import com.amazonaws.services.iotdata.model.transform.GetRetainedMessageResultJsonUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.GetThingShadowRequestMarshaller;
import com.amazonaws.services.iotdata.model.transform.GetThingShadowResultJsonUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.InternalFailureExceptionUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.InvalidRequestExceptionUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.ListNamedShadowsForThingRequestMarshaller;
import com.amazonaws.services.iotdata.model.transform.ListNamedShadowsForThingResultJsonUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.ListRetainedMessagesRequestMarshaller;
import com.amazonaws.services.iotdata.model.transform.ListRetainedMessagesResultJsonUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.MethodNotAllowedExceptionUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.PublishRequestMarshaller;
import com.amazonaws.services.iotdata.model.transform.RequestEntityTooLargeExceptionUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.ResourceNotFoundExceptionUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.ServiceUnavailableExceptionUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.ThrottlingExceptionUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.UnauthorizedExceptionUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.UnsupportedDocumentEncodingExceptionUnmarshaller;
import com.amazonaws.services.iotdata.model.transform.UpdateThingShadowRequestMarshaller;
import com.amazonaws.services.iotdata.model.transform.UpdateThingShadowResultJsonUnmarshaller;
import com.amazonaws.transform.JsonErrorUnmarshaller;
import com.amazonaws.util.AWSRequestMetrics;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AWSIotDataClient extends AmazonWebServiceClient implements AWSIotData {
    private AWSCredentialsProvider awsCredentialsProvider;
    protected List<JsonErrorUnmarshaller> jsonErrorUnmarshallers;

    private static ClientConfiguration adjustClientConfiguration(ClientConfiguration clientConfiguration) {
        return clientConfiguration;
    }

    @Deprecated
    public AWSIotDataClient() {
        this(new DefaultAWSCredentialsProviderChain(), new ClientConfiguration());
    }

    @Deprecated
    public AWSIotDataClient(ClientConfiguration clientConfiguration) {
        this(new DefaultAWSCredentialsProviderChain(), clientConfiguration);
    }

    public AWSIotDataClient(AWSCredentials aWSCredentials) {
        this(aWSCredentials, new ClientConfiguration());
    }

    public AWSIotDataClient(AWSCredentials aWSCredentials, ClientConfiguration clientConfiguration) {
        this(new StaticCredentialsProvider(aWSCredentials), clientConfiguration);
    }

    public AWSIotDataClient(AWSCredentialsProvider aWSCredentialsProvider) {
        this(aWSCredentialsProvider, new ClientConfiguration());
    }

    public AWSIotDataClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration) {
        this(aWSCredentialsProvider, clientConfiguration, new UrlHttpClient(clientConfiguration));
    }

    @Deprecated
    public AWSIotDataClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration, RequestMetricCollector requestMetricCollector) {
        super(adjustClientConfiguration(clientConfiguration), requestMetricCollector);
        this.awsCredentialsProvider = aWSCredentialsProvider;
        init();
    }

    public AWSIotDataClient(AWSCredentialsProvider aWSCredentialsProvider, ClientConfiguration clientConfiguration, HttpClient httpClient) {
        super(adjustClientConfiguration(clientConfiguration), httpClient);
        this.awsCredentialsProvider = aWSCredentialsProvider;
        init();
    }

    private void init() {
        ArrayList arrayList = new ArrayList();
        this.jsonErrorUnmarshallers = arrayList;
        arrayList.add(new ConflictExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InternalFailureExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new InvalidRequestExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new MethodNotAllowedExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new RequestEntityTooLargeExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ResourceNotFoundExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ServiceUnavailableExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new ThrottlingExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new UnauthorizedExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new UnsupportedDocumentEncodingExceptionUnmarshaller());
        this.jsonErrorUnmarshallers.add(new JsonErrorUnmarshaller());
        setServiceNameIntern("iotdata");
        setEndpoint("data.iot.us-east-1.amazonaws.com");
        this.endpointPrefix = "data.iot";
        HandlerChainFactory handlerChainFactory = new HandlerChainFactory();
        this.requestHandler2s.addAll(handlerChainFactory.newRequestHandlerChain("/com/amazonaws/services/iotdata/request.handlers"));
        this.requestHandler2s.addAll(handlerChainFactory.newRequestHandler2Chain("/com/amazonaws/services/iotdata/request.handler2s"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iotdata.AWSIotDataClient] */
    @Override // com.amazonaws.services.iotdata.AWSIotData
    public DeleteThingShadowResult deleteThingShadow(DeleteThingShadowRequest deleteThingShadowRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(deleteThingShadowRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<DeleteThingShadowRequest> requestMarshall = new DeleteThingShadowRequestMarshaller().marshall(deleteThingShadowRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new DeleteThingShadowResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        DeleteThingShadowResult deleteThingShadowResult = (DeleteThingShadowResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return deleteThingShadowResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = deleteThingShadowRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iotdata.AWSIotDataClient] */
    @Override // com.amazonaws.services.iotdata.AWSIotData
    public GetRetainedMessageResult getRetainedMessage(GetRetainedMessageRequest getRetainedMessageRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(getRetainedMessageRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<GetRetainedMessageRequest> requestMarshall = new GetRetainedMessageRequestMarshaller().marshall(getRetainedMessageRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new GetRetainedMessageResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        GetRetainedMessageResult getRetainedMessageResult = (GetRetainedMessageResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return getRetainedMessageResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = getRetainedMessageRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iotdata.AWSIotDataClient] */
    @Override // com.amazonaws.services.iotdata.AWSIotData
    public GetThingShadowResult getThingShadow(GetThingShadowRequest getThingShadowRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(getThingShadowRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<GetThingShadowRequest> requestMarshall = new GetThingShadowRequestMarshaller().marshall(getThingShadowRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new GetThingShadowResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        GetThingShadowResult getThingShadowResult = (GetThingShadowResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return getThingShadowResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = getThingShadowRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iotdata.AWSIotDataClient] */
    @Override // com.amazonaws.services.iotdata.AWSIotData
    public ListNamedShadowsForThingResult listNamedShadowsForThing(ListNamedShadowsForThingRequest listNamedShadowsForThingRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listNamedShadowsForThingRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListNamedShadowsForThingRequest> requestMarshall = new ListNamedShadowsForThingRequestMarshaller().marshall(listNamedShadowsForThingRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListNamedShadowsForThingResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListNamedShadowsForThingResult listNamedShadowsForThingResult = (ListNamedShadowsForThingResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listNamedShadowsForThingResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listNamedShadowsForThingRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iotdata.AWSIotDataClient] */
    @Override // com.amazonaws.services.iotdata.AWSIotData
    public ListRetainedMessagesResult listRetainedMessages(ListRetainedMessagesRequest listRetainedMessagesRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(listRetainedMessagesRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<ListRetainedMessagesRequest> requestMarshall = new ListRetainedMessagesRequestMarshaller().marshall(listRetainedMessagesRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new ListRetainedMessagesResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        ListRetainedMessagesResult listRetainedMessagesResult = (ListRetainedMessagesResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return listRetainedMessagesResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = listRetainedMessagesRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iotdata.AWSIotDataClient] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceRequest, com.amazonaws.services.iotdata.model.PublishRequest] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [com.amazonaws.Request] */
    @Override // com.amazonaws.services.iotdata.AWSIotData
    public void publish(PublishRequest publishRequest) throws Throwable {
        Request<PublishRequest> requestMarshall;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(publishRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    requestMarshall = new PublishRequestMarshaller().marshall((PublishRequest) publishRequest);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                    awsRequestMetrics.endEvent(field2);
                    invoke(requestMarshall, new JsonResponseHandler(null), executionContextCreateExecutionContext);
                    awsRequestMetrics.endEvent(field);
                    endClientExecution(awsRequestMetrics, requestMarshall, null, true);
                } catch (Throwable th2) {
                    th = th2;
                    awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                publishRequest = 0;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, publishRequest, null, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, publishRequest, null, true);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.amazonaws.Request] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.amazonaws.AmazonWebServiceClient, com.amazonaws.services.iotdata.AWSIotDataClient] */
    @Override // com.amazonaws.services.iotdata.AWSIotData
    public UpdateThingShadowResult updateThingShadow(UpdateThingShadowRequest updateThingShadowRequest) throws Throwable {
        Response response;
        ExecutionContext executionContextCreateExecutionContext = createExecutionContext(updateThingShadowRequest);
        AWSRequestMetrics awsRequestMetrics = executionContextCreateExecutionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.ClientExecuteTime;
        awsRequestMetrics.startEvent(field);
        ?? r4 = 0;
        try {
            try {
                AWSRequestMetrics.Field field2 = AWSRequestMetrics.Field.RequestMarshallTime;
                awsRequestMetrics.startEvent(field2);
                try {
                    Request<UpdateThingShadowRequest> requestMarshall = new UpdateThingShadowRequestMarshaller().marshall(updateThingShadowRequest);
                    try {
                        requestMarshall.setAWSRequestMetrics(awsRequestMetrics);
                        awsRequestMetrics.endEvent(field2);
                        Response responseInvoke = invoke(requestMarshall, new JsonResponseHandler(new UpdateThingShadowResultJsonUnmarshaller()), executionContextCreateExecutionContext);
                        UpdateThingShadowResult updateThingShadowResult = (UpdateThingShadowResult) responseInvoke.getAwsResponse();
                        awsRequestMetrics.endEvent(field);
                        endClientExecution(awsRequestMetrics, requestMarshall, responseInvoke, true);
                        return updateThingShadowResult;
                    } catch (Throwable th) {
                        th = th;
                        awsRequestMetrics.endEvent(AWSRequestMetrics.Field.RequestMarshallTime);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                response = null;
                awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
                endClientExecution(awsRequestMetrics, r4, response, true);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            r4 = updateThingShadowRequest;
            response = null;
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.ClientExecuteTime);
            endClientExecution(awsRequestMetrics, r4, response, true);
            throw th;
        }
    }

    @Override // com.amazonaws.services.iotdata.AWSIotData
    @Deprecated
    public ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest amazonWebServiceRequest) {
        return this.client.getResponseMetadataForRequest(amazonWebServiceRequest);
    }

    private <X, Y extends AmazonWebServiceRequest> Response<X> invoke(Request<Y> request, HttpResponseHandler<AmazonWebServiceResponse<X>> httpResponseHandler, ExecutionContext executionContext) {
        request.setEndpoint(this.endpoint);
        request.setTimeOffset(this.timeOffset);
        AWSRequestMetrics awsRequestMetrics = executionContext.getAwsRequestMetrics();
        AWSRequestMetrics.Field field = AWSRequestMetrics.Field.CredentialsRequestTime;
        awsRequestMetrics.startEvent(field);
        try {
            AWSCredentials credentials = this.awsCredentialsProvider.getCredentials();
            awsRequestMetrics.endEvent(field);
            AmazonWebServiceRequest originalRequest = request.getOriginalRequest();
            if (originalRequest != null && originalRequest.getRequestCredentials() != null) {
                credentials = originalRequest.getRequestCredentials();
            }
            executionContext.setCredentials(credentials);
            return this.client.execute(request, httpResponseHandler, new JsonErrorResponseHandler(this.jsonErrorUnmarshallers), executionContext);
        } catch (Throwable th) {
            awsRequestMetrics.endEvent(AWSRequestMetrics.Field.CredentialsRequestTime);
            throw th;
        }
    }
}
