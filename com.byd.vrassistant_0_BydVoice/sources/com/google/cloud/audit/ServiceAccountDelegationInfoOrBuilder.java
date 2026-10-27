package com.google.cloud.audit;

import com.google.cloud.audit.ServiceAccountDelegationInfo;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/* JADX INFO: loaded from: classes2.dex */
public interface ServiceAccountDelegationInfoOrBuilder extends MessageOrBuilder {
    ServiceAccountDelegationInfo.AuthorityCase getAuthorityCase();

    ServiceAccountDelegationInfo.FirstPartyPrincipal getFirstPartyPrincipal();

    ServiceAccountDelegationInfo.FirstPartyPrincipalOrBuilder getFirstPartyPrincipalOrBuilder();

    String getPrincipalSubject();

    ByteString getPrincipalSubjectBytes();

    ServiceAccountDelegationInfo.ThirdPartyPrincipal getThirdPartyPrincipal();

    ServiceAccountDelegationInfo.ThirdPartyPrincipalOrBuilder getThirdPartyPrincipalOrBuilder();

    boolean hasFirstPartyPrincipal();

    boolean hasThirdPartyPrincipal();
}
