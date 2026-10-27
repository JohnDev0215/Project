package com.amazonaws.mobile.client.internal.oauth2;

import com.amazon.identity.auth.device.token.CentralTokenManagementCommunication;
import com.amazon.identity.auth.device.utils.AccountConstants;

/* JADX INFO: compiled from: OAuth2Client.java */
/* JADX INFO: loaded from: classes5.dex */
class OAuth2Constants {
    public static final String GRANT_TYPE = "grant_type";
    public static final String SCOPES = "scopes";

    OAuth2Constants() {
    }

    /* JADX INFO: compiled from: OAuth2Client.java */
    enum TokenResponseFields {
        ACCESS_TOKEN("access_token"),
        ID_TOKEN("id_token"),
        REFRESH_TOKEN("refresh_token"),
        TOKEN_TYPE(CentralTokenManagementCommunication.GetTokenForActorCommand.KEY_TOKEN_TYPE),
        EXPIRES_IN("expires_in"),
        SCOPES(OAuth2Constants.SCOPES),
        ERROR("error"),
        ERROR_DESCRIPTION("error_description"),
        ERROR_URI("error_uri");

        private final String value;

        TokenResponseFields(String str) {
            this.value = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }
    }

    /* JADX INFO: compiled from: OAuth2Client.java */
    enum TokenRequestFields {
        ACCESS_TOKEN("access_token"),
        ID_TOKEN("id_token");

        private final String value;

        TokenRequestFields(String str) {
            this.value = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }
    }

    /* JADX INFO: compiled from: OAuth2Client.java */
    enum GrantTypes {
        AUTHORIZATION_CODE(AccountConstants.KEY_AUTHORIZATION_CODE),
        REFRESH_TOKEN("refresh_token");

        private final String value;

        GrantTypes(String str) {
            this.value = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }
    }
}
