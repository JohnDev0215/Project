package com.spotify.connectivity.auth;

import java.util.List;
import p.hf2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public interface CredentialsStorage {

    public static final class StoredCredentialsAndUsername {
        private String canonicalUsername;
        private SerializableCredentials credentials;
        private boolean rememberMe;

        public StoredCredentialsAndUsername(SerializableCredentials serializableCredentials, String str, boolean z) {
            v03.j(serializableCredentials, "credentials");
            v03.j(str, "canonicalUsername");
            this.credentials = serializableCredentials;
            this.canonicalUsername = str;
            this.rememberMe = z;
        }

        public static /* synthetic */ StoredCredentialsAndUsername copy$default(StoredCredentialsAndUsername storedCredentialsAndUsername, SerializableCredentials serializableCredentials, String str, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                serializableCredentials = storedCredentialsAndUsername.credentials;
            }
            if ((i & 2) != 0) {
                str = storedCredentialsAndUsername.canonicalUsername;
            }
            if ((i & 4) != 0) {
                z = storedCredentialsAndUsername.rememberMe;
            }
            return storedCredentialsAndUsername.copy(serializableCredentials, str, z);
        }

        public final SerializableCredentials component1() {
            return this.credentials;
        }

        public final String component2() {
            return this.canonicalUsername;
        }

        public final boolean component3() {
            return this.rememberMe;
        }

        public final StoredCredentialsAndUsername copy(SerializableCredentials serializableCredentials, String str, boolean z) {
            v03.j(serializableCredentials, "credentials");
            v03.j(str, "canonicalUsername");
            return new StoredCredentialsAndUsername(serializableCredentials, str, z);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StoredCredentialsAndUsername)) {
                return false;
            }
            StoredCredentialsAndUsername storedCredentialsAndUsername = (StoredCredentialsAndUsername) obj;
            return v03.b(this.credentials, storedCredentialsAndUsername.credentials) && v03.b(this.canonicalUsername, storedCredentialsAndUsername.canonicalUsername) && this.rememberMe == storedCredentialsAndUsername.rememberMe;
        }

        public final String getCanonicalUsername() {
            return this.canonicalUsername;
        }

        public final SerializableCredentials getCredentials() {
            return this.credentials;
        }

        public final boolean getRememberMe() {
            return this.rememberMe;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v2, types: [int] */
        /* JADX WARN: Type inference failed for: r1v3 */
        /* JADX WARN: Type inference failed for: r1v4 */
        public int hashCode() {
            int iB = hf2.b(this.canonicalUsername, this.credentials.hashCode() * 31, 31);
            boolean z = this.rememberMe;
            ?? r1 = z;
            if (z) {
                r1 = 1;
            }
            return iB + r1;
        }

        public final void setCanonicalUsername(String str) {
            v03.j(str, "<set-?>");
            this.canonicalUsername = str;
        }

        public final void setCredentials(SerializableCredentials serializableCredentials) {
            v03.j(serializableCredentials, "<set-?>");
            this.credentials = serializableCredentials;
        }

        public final void setRememberMe(boolean z) {
            this.rememberMe = z;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("StoredCredentialsAndUsername(credentials=");
            sb.append(this.credentials);
            sb.append(", canonicalUsername=");
            sb.append(this.canonicalUsername);
            sb.append(", rememberMe=");
            return u4.n(sb, this.rememberMe, ')');
        }
    }

    void deleteStoredCredentials();

    void forgetRememberMe(String str);

    boolean getRememberMeMode();

    List<String> getSavedUsernames();

    StoredCredentialsAndUsername getStoredCredentials();

    SerializableCredentials getStoredCredentialsForUser(String str);

    void saveLoginDetails(SerializableCredentials serializableCredentials, String str, boolean z);

    void setRememberMeMode(String str, boolean z);
}
