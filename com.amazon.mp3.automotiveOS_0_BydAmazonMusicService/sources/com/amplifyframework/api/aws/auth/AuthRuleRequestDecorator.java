package com.amplifyframework.api.aws.auth;

import androidx.annotation.NonNull;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.exceptions.CognitoParameterInvalidException;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.util.CognitoJWTParser;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.api.aws.ApiAuthProviders;
import com.amplifyframework.api.aws.AppSyncGraphQLRequest;
import com.amplifyframework.api.aws.AuthorizationType;
import com.amplifyframework.api.aws.sigv4.CognitoUserPoolsAuthProvider;
import com.amplifyframework.api.aws.sigv4.DefaultCognitoUserPoolsAuthProvider;
import com.amplifyframework.api.aws.sigv4.OidcAuthProvider;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.core.model.AuthRule;
import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.ModelOperation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
public final class AuthRuleRequestDecorator {
    private final ApiAuthProviders authProvider;

    public AuthRuleRequestDecorator(@NonNull ApiAuthProviders apiAuthProviders) {
        Objects.requireNonNull(apiAuthProviders);
        this.authProvider = apiAuthProviders;
    }

    public <R> GraphQLRequest<R> decorate(@NonNull GraphQLRequest<R> graphQLRequest, @NonNull AuthorizationType authorizationType) throws ApiException {
        if (!(graphQLRequest instanceof AppSyncGraphQLRequest)) {
            return graphQLRequest;
        }
        AppSyncGraphQLRequest appSyncGraphQLRequest = (AppSyncGraphQLRequest) graphQLRequest;
        AuthRule authRule = null;
        HashMap map = new HashMap();
        for (AuthRule authRule2 : appSyncGraphQLRequest.getModelSchema().getAuthRules()) {
            if (isReadRestrictingOwner(authRule2)) {
                if (authRule != null) {
                    throw new ApiException.ApiAuthException("Detected multiple owner type auth rules with a READ operation", "We currently do not support this use case. Please limit your type to just one owner auth rule with a READ operation restriction.");
                }
                authRule = authRule2;
            } else if (isReadRestrictingStaticGroup(authRule2)) {
                String groupClaimOrDefault = authRule2.getGroupClaimOrDefault();
                List<String> groups = authRule2.getGroups();
                Set<String> set = map.get(groupClaimOrDefault);
                if (set != null) {
                    set.addAll(groups);
                } else {
                    map.put(groupClaimOrDefault, new HashSet(groups));
                }
            }
        }
        if (authRule == null || !userNotInReadRestrictingGroups(map, authorizationType)) {
            return graphQLRequest;
        }
        String identityClaimOrDefault = authRule.getIdentityClaimOrDefault();
        try {
            return appSyncGraphQLRequest.newBuilder().variable(authRule.getOwnerFieldOrDefault(), "String!", getIdentityValue(identityClaimOrDefault, authorizationType)).build();
        } catch (AmplifyException e) {
            throw new ApiException.ApiAuthException("Failed to set owner field on AppSyncGraphQLRequest.", e, AmplifyException.REPORT_BUG_TO_AWS_SUGGESTION);
        }
    }

    private boolean isReadRestrictingOwner(AuthRule authRule) {
        return AuthStrategy.OWNER.equals(authRule.getAuthStrategy()) && authRule.getOperationsOrDefault().contains(ModelOperation.READ);
    }

    private boolean isReadRestrictingStaticGroup(AuthRule authRule) {
        return AuthStrategy.GROUPS.equals(authRule.getAuthStrategy()) && !authRule.getGroups().isEmpty() && authRule.getOperationsOrDefault().contains(ModelOperation.READ);
    }

    private String getIdentityValue(String str, AuthorizationType authorizationType) throws ApiException {
        try {
            return CognitoJWTParser.getPayload(getAuthToken(authorizationType)).getString(str);
        } catch (CognitoParameterInvalidException e) {
            throw new ApiException.ApiAuthException("Failed to parse the ID token for identity claim: " + e.getMessage(), "Please verify the validity of token vended by the registered auth provider.");
        } catch (JSONException unused) {
            throw new ApiException.ApiAuthException("Attempted to subscribe to a model with owner-based authorization without " + str + " which was specified (or defaulted to) as the identity claim.", "If you did not specify a custom identityClaim in your schema, make sure you are logged in. If you did, check that the value you specified in your schema is present in the access key.");
        }
    }

    private ArrayList<String> getUserGroups(String str, AuthorizationType authorizationType) throws ApiException {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            JSONObject payload = CognitoJWTParser.getPayload(getAuthToken(authorizationType));
            if (payload.has(str)) {
                JSONArray jSONArray = payload.getJSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.getString(i));
                }
            }
            return arrayList;
        } catch (CognitoParameterInvalidException e) {
            throw new ApiException("Failed to parse the ID token for group claim: " + e.getMessage(), "Please verify the validity of token vended by the registered auth provider.");
        } catch (JSONException e2) {
            throw new ApiException("Failed obtain group claim from the parsed JWT token.", e2, AmplifyException.REPORT_BUG_TO_AWS_SUGGESTION);
        }
    }

    private boolean userNotInReadRestrictingGroups(Map<String, Set<String>> map, AuthorizationType authorizationType) throws ApiException {
        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            if (!Collections.disjoint(getUserGroups(entry.getKey(), authorizationType), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: com.amplifyframework.api.aws.auth.AuthRuleRequestDecorator$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$api$aws$AuthorizationType;

        static {
            int[] iArr = new int[AuthorizationType.values().length];
            $SwitchMap$com$amplifyframework$api$aws$AuthorizationType = iArr;
            try {
                iArr[AuthorizationType.AMAZON_COGNITO_USER_POOLS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$aws$AuthorizationType[AuthorizationType.OPENID_CONNECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$aws$AuthorizationType[AuthorizationType.API_KEY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$aws$AuthorizationType[AuthorizationType.AWS_IAM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$aws$AuthorizationType[AuthorizationType.NONE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private String getAuthToken(AuthorizationType authorizationType) throws ApiException {
        int i = AnonymousClass1.$SwitchMap$com$amplifyframework$api$aws$AuthorizationType[authorizationType.ordinal()];
        if (i == 1) {
            CognitoUserPoolsAuthProvider cognitoUserPoolsAuthProvider = this.authProvider.getCognitoUserPoolsAuthProvider();
            if (cognitoUserPoolsAuthProvider == null) {
                cognitoUserPoolsAuthProvider = new DefaultCognitoUserPoolsAuthProvider();
            }
            return cognitoUserPoolsAuthProvider.getLatestAuthToken();
        }
        if (i == 2) {
            OidcAuthProvider oidcAuthProvider = this.authProvider.getOidcAuthProvider();
            if (oidcAuthProvider == null) {
                throw new ApiException.ApiAuthException("OidcAuthProvider interface is not implemented.", "Configure AWSApiPlugin with ApiAuthProviders containing an implementation of OidcAuthProvider interface that can vend a valid JWT token.");
            }
            return oidcAuthProvider.getLatestAuthToken();
        }
        throw new ApiException.ApiAuthException("Tried to use owner/group-based authorization on an API that is not configured with either Cognito User Pools or OpenID Connect.", "Verify that the API is configured with either Cognito User Pools or OpenID Connect. @auth with owner/group-based authorization is not supported for other modes.");
    }
}
