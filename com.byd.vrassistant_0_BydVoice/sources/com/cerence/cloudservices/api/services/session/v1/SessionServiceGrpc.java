package com.cerence.cloudservices.api.services.session.v1;

import io.grpc.BindableService;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServiceDescriptor;
import io.grpc.protobuf.lite.ProtoLiteUtils;
import io.grpc.stub.AbstractAsyncStub;
import io.grpc.stub.AbstractBlockingStub;
import io.grpc.stub.AbstractFutureStub;
import io.grpc.stub.AbstractStub;
import io.grpc.stub.ClientCalls;
import io.grpc.stub.ServerCalls;
import io.grpc.stub.StreamObserver;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public final class SessionServiceGrpc {
    private static final int METHODID_DELETE_SESSION = 0;
    public static final String SERVICE_NAME = "cerence.cloudservices.api.services.session.v1.SessionService";
    private static volatile MethodDescriptor<DeleteSessionRequest, DeleteSessionResponse> getDeleteSessionMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private SessionServiceGrpc() {
    }

    public static MethodDescriptor<DeleteSessionRequest, DeleteSessionResponse> getDeleteSessionMethod() {
        MethodDescriptor<DeleteSessionRequest, DeleteSessionResponse> methodDescriptorBuild = getDeleteSessionMethod;
        if (methodDescriptorBuild == null) {
            synchronized (SessionServiceGrpc.class) {
                methodDescriptorBuild = getDeleteSessionMethod;
                if (methodDescriptorBuild == null) {
                    methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.SERVER_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "DeleteSession")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(DeleteSessionRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(DeleteSessionResponse.getDefaultInstance())).build();
                    getDeleteSessionMethod = methodDescriptorBuild;
                }
            }
        }
        return methodDescriptorBuild;
    }

    public static SessionServiceStub newStub(Channel channel) {
        return (SessionServiceStub) SessionServiceStub.newStub(new AbstractStub.StubFactory<SessionServiceStub>() { // from class: com.cerence.cloudservices.api.services.session.v1.SessionServiceGrpc.1
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public SessionServiceStub newStub(Channel channel2, CallOptions callOptions) {
                return new SessionServiceStub(channel2, callOptions);
            }
        }, channel);
    }

    public static SessionServiceBlockingStub newBlockingStub(Channel channel) {
        return (SessionServiceBlockingStub) SessionServiceBlockingStub.newStub(new AbstractStub.StubFactory<SessionServiceBlockingStub>() { // from class: com.cerence.cloudservices.api.services.session.v1.SessionServiceGrpc.2
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public SessionServiceBlockingStub newStub(Channel channel2, CallOptions callOptions) {
                return new SessionServiceBlockingStub(channel2, callOptions);
            }
        }, channel);
    }

    public static SessionServiceFutureStub newFutureStub(Channel channel) {
        return (SessionServiceFutureStub) SessionServiceFutureStub.newStub(new AbstractStub.StubFactory<SessionServiceFutureStub>() { // from class: com.cerence.cloudservices.api.services.session.v1.SessionServiceGrpc.3
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public SessionServiceFutureStub newStub(Channel channel2, CallOptions callOptions) {
                return new SessionServiceFutureStub(channel2, callOptions);
            }
        }, channel);
    }

    public interface AsyncService {
        default void deleteSession(DeleteSessionRequest deleteSessionRequest, StreamObserver<DeleteSessionResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(SessionServiceGrpc.getDeleteSessionMethod(), streamObserver);
        }
    }

    public static abstract class SessionServiceImplBase implements BindableService, AsyncService {
        @Override // io.grpc.BindableService
        public final ServerServiceDefinition bindService() {
            return SessionServiceGrpc.bindService(this);
        }
    }

    public static final class SessionServiceStub extends AbstractAsyncStub<SessionServiceStub> {
        private SessionServiceStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public SessionServiceStub build(Channel channel, CallOptions callOptions) {
            return new SessionServiceStub(channel, callOptions);
        }

        public void deleteSession(DeleteSessionRequest deleteSessionRequest, StreamObserver<DeleteSessionResponse> streamObserver) {
            ClientCalls.asyncServerStreamingCall(getChannel().newCall(SessionServiceGrpc.getDeleteSessionMethod(), getCallOptions()), deleteSessionRequest, streamObserver);
        }
    }

    public static final class SessionServiceBlockingStub extends AbstractBlockingStub<SessionServiceBlockingStub> {
        private SessionServiceBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public SessionServiceBlockingStub build(Channel channel, CallOptions callOptions) {
            return new SessionServiceBlockingStub(channel, callOptions);
        }

        public Iterator<DeleteSessionResponse> deleteSession(DeleteSessionRequest deleteSessionRequest) {
            return ClientCalls.blockingServerStreamingCall(getChannel(), SessionServiceGrpc.getDeleteSessionMethod(), getCallOptions(), deleteSessionRequest);
        }
    }

    public static final class SessionServiceFutureStub extends AbstractFutureStub<SessionServiceFutureStub> {
        private SessionServiceFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public SessionServiceFutureStub build(Channel channel, CallOptions callOptions) {
            return new SessionServiceFutureStub(channel, callOptions);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final AsyncService serviceImpl;

        MethodHandlers(AsyncService asyncService, int i) {
            this.serviceImpl = asyncService;
            this.methodId = i;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.grpc.stub.ServerCalls.UnaryMethod, io.grpc.stub.ServerCalls.UnaryRequestMethod, io.grpc.stub.ServerCalls.ServerStreamingMethod
        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            if (this.methodId == 0) {
                this.serviceImpl.deleteSession((DeleteSessionRequest) req, streamObserver);
                return;
            }
            throw new AssertionError();
        }

        @Override // io.grpc.stub.ServerCalls.ClientStreamingMethod, io.grpc.stub.ServerCalls.StreamingRequestMethod, io.grpc.stub.ServerCalls.BidiStreamingMethod
        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            throw new AssertionError();
        }
    }

    public static final ServerServiceDefinition bindService(AsyncService asyncService) {
        return ServerServiceDefinition.builder(getServiceDescriptor()).addMethod(getDeleteSessionMethod(), ServerCalls.asyncServerStreamingCall(new MethodHandlers(asyncService, 0))).build();
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptorBuild = serviceDescriptor;
        if (serviceDescriptorBuild == null) {
            synchronized (SessionServiceGrpc.class) {
                serviceDescriptorBuild = serviceDescriptor;
                if (serviceDescriptorBuild == null) {
                    serviceDescriptorBuild = ServiceDescriptor.newBuilder(SERVICE_NAME).addMethod(getDeleteSessionMethod()).build();
                    serviceDescriptor = serviceDescriptorBuild;
                }
            }
        }
        return serviceDescriptorBuild;
    }
}
