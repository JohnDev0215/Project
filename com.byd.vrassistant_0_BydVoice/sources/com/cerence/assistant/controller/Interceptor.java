package com.cerence.assistant.controller;

import cerence.ark.assistant.common.Logger;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.ForwardingClientCallListener;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;

/* JADX INFO: loaded from: classes2.dex */
public class Interceptor implements ClientInterceptor {
    private static final String TAG = "DomainsAutoController";
    private String sessionId;
    private final String token;

    public Interceptor(String str) {
        this.token = str;
    }

    @Override // io.grpc.ClientInterceptor
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(channel.newCall(methodDescriptor, callOptions)) { // from class: com.cerence.assistant.controller.Interceptor.1
            @Override // io.grpc.ForwardingClientCall, io.grpc.ClientCall
            public void start(ClientCall.Listener<RespT> listener, Metadata metadata) {
                metadata.put(Metadata.Key.of("authorization", Metadata.ASCII_STRING_MARSHALLER), "bearer " + Interceptor.this.token);
                metadata.put(Metadata.Key.of("cerence-session-test-request", Metadata.ASCII_STRING_MARSHALLER), "master-onecloud--v1official-user-delete-no-obfuscation");
                super.start(new ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(listener) { // from class: com.cerence.assistant.controller.Interceptor.1.1
                    @Override // io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener, io.grpc.ForwardingClientCallListener, io.grpc.PartialForwardingClientCallListener, io.grpc.ClientCall.Listener
                    public void onHeaders(Metadata metadata2) {
                        Interceptor.this.sessionId = (String) metadata2.get(Metadata.Key.of("cerence-session-id", Metadata.ASCII_STRING_MARSHALLER));
                        String str = Interceptor.TAG;
                        Object[] objArr = new Object[1];
                        objArr[0] = Interceptor.this.sessionId != null ? Interceptor.this.sessionId : "<empty>";
                        Logger.debug(str, String.format("**GRPC_deleteSession** cerence-session-id: %s", objArr));
                        super.onHeaders(metadata2);
                    }
                }, metadata);
            }
        };
    }
}
