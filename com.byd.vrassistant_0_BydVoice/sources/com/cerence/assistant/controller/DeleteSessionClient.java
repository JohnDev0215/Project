package com.cerence.assistant.controller;

import cerence.ark.assistant.common.Logger;
import com.cerence.cloudservices.api.services.session.v1.DeleteAll;
import com.cerence.cloudservices.api.services.session.v1.DeleteSessionRequest;
import com.cerence.cloudservices.api.services.session.v1.DeleteSessionResponse;
import com.cerence.cloudservices.api.services.session.v1.SessionServiceGrpc;
import com.google.rpc.Status;
import io.grpc.ManagedChannel;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public final class DeleteSessionClient {
    private static final String TAG = "DeleteSessionClient";
    private final SessionServiceGrpc.SessionServiceBlockingStub blockingStub;
    private final ManagedChannel channel;
    private final int connectTimeout;
    private final DeleteSessionRequest request;

    public DeleteSessionClient(GrpcClient grpcClient, String str, String str2) {
        this.connectTimeout = grpcClient.getCloudConfig().getCONNECTION_TIMEOUT();
        ManagedChannel channel = grpcClient.getChannel();
        this.channel = channel;
        this.blockingStub = SessionServiceGrpc.newBlockingStub(channel);
        this.request = DeleteSessionRequest.newBuilder().setAll(DeleteAll.newBuilder().setUserId(str).setDeviceId(str2).build()).build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int execute() throws InterruptedException {
        Iterator<DeleteSessionResponse> itDeleteSession = ((SessionServiceGrpc.SessionServiceBlockingStub) this.blockingStub.withDeadlineAfter(10L, TimeUnit.SECONDS)).deleteSession(this.request);
        int i = -10;
        while (true) {
            if (!itDeleteSession.hasNext()) {
                break;
            }
            DeleteSessionResponse next = itDeleteSession.next();
            String str = TAG;
            Logger.debug(str, String.format("**GRPC_deleteSession** deleteSession response = %s", next.toString()));
            if (next.hasStatus()) {
                Status status = next.getStatus();
                int code = status.getCode();
                Logger.debug(str, String.format("**GRPC_deleteSession** Status.code = %s, Status.message = %s", Integer.valueOf(code), status.getMessage()));
                if (code != 0) {
                    Logger.error(str, String.format("**GRPC_deleteSession** GRPC ERROR, Status.code = %s, Status.message = %s", Integer.valueOf(code), status.getMessage()));
                    i = code;
                    break;
                }
                i = code;
            } else {
                Logger.error(str, "**GRPC_deleteSession** google.rpc.Status not exist!");
                i = -11;
                break;
            }
        }
        String str2 = TAG;
        Logger.debug(str2, "**GRPC_deleteSession** Shutting down the channel..");
        if (this.channel.shutdownNow().awaitTermination(this.connectTimeout, TimeUnit.SECONDS)) {
            Logger.debug(str2, "**GRPC_deleteSession** Channel is terminated.");
        } else {
            Logger.debug(str2, String.format("**GRPC_deleteSession** Channel could not be closed in %ss.", Integer.valueOf(this.connectTimeout)));
        }
        return i;
    }
}
