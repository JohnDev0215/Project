package SOACoreInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class RequestIdentity extends SOAObject {
    private static final int classNameHashCode = internalHashCodeCompute("SOACoreInterface.v1_0.RequestIdentity");
    private Csrf csrf;
    private String domain;
    private String id;
    private String ipAddress;
    private String sessionId;
    private long timestamp;

    public static class Builder extends SOAObject.Builder {
        protected Csrf csrf;
        protected String domain;
        protected String id;
        protected String ipAddress;
        protected String sessionId;
        protected long timestamp;

        public RequestIdentity build() {
            RequestIdentity requestIdentity = new RequestIdentity();
            populate(requestIdentity);
            return requestIdentity;
        }

        protected void populate(RequestIdentity requestIdentity) {
            super.populate((SOAObject) requestIdentity);
            requestIdentity.setDomain(this.domain);
            requestIdentity.setIpAddress(this.ipAddress);
            requestIdentity.setId(this.id);
            requestIdentity.setSessionId(this.sessionId);
            requestIdentity.setTimestamp(this.timestamp);
            requestIdentity.setCsrf(this.csrf);
        }

        public Builder withCsrf(Csrf csrf) {
            this.csrf = csrf;
            return this;
        }

        public Builder withDomain(String str) {
            this.domain = str;
            return this;
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }

        public Builder withIpAddress(String str) {
            this.ipAddress = str;
            return this;
        }

        public Builder withSessionId(String str) {
            this.sessionId = str;
            return this;
        }

        public Builder withTimestamp(long j) {
            this.timestamp = j;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof RequestIdentity)) {
            return 1;
        }
        RequestIdentity requestIdentity = (RequestIdentity) sOAObject;
        String domain = getDomain();
        String domain2 = requestIdentity.getDomain();
        if (domain != domain2) {
            if (domain == null) {
                return -1;
            }
            if (domain2 == null) {
                return 1;
            }
            int iCompareTo = domain.compareTo(domain2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String ipAddress = getIpAddress();
        String ipAddress2 = requestIdentity.getIpAddress();
        if (ipAddress != ipAddress2) {
            if (ipAddress == null) {
                return -1;
            }
            if (ipAddress2 == null) {
                return 1;
            }
            int iCompareTo2 = ipAddress.compareTo(ipAddress2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String id = getId();
        String id2 = requestIdentity.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo3 = id.compareTo(id2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String sessionId = getSessionId();
        String sessionId2 = requestIdentity.getSessionId();
        if (sessionId != sessionId2) {
            if (sessionId == null) {
                return -1;
            }
            if (sessionId2 == null) {
                return 1;
            }
            int iCompareTo4 = sessionId.compareTo(sessionId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        if (getTimestamp() < requestIdentity.getTimestamp()) {
            return -1;
        }
        if (getTimestamp() > requestIdentity.getTimestamp()) {
            return 1;
        }
        Csrf csrf = getCsrf();
        Csrf csrf2 = requestIdentity.getCsrf();
        if (csrf != csrf2) {
            if (csrf == null) {
                return -1;
            }
            if (csrf2 == null) {
                return 1;
            }
            int iCompareTo5 = csrf.compareTo(csrf2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof RequestIdentity)) {
            return false;
        }
        RequestIdentity requestIdentity = (RequestIdentity) obj;
        return super.equals(obj) && internalEqualityCheck(getDomain(), requestIdentity.getDomain()) && internalEqualityCheck(getIpAddress(), requestIdentity.getIpAddress()) && internalEqualityCheck(getId(), requestIdentity.getId()) && internalEqualityCheck(getSessionId(), requestIdentity.getSessionId()) && internalEqualityCheck(Long.valueOf(getTimestamp()), Long.valueOf(requestIdentity.getTimestamp())) && internalEqualityCheck(getCsrf(), requestIdentity.getCsrf());
    }

    public Csrf getCsrf() {
        return this.csrf;
    }

    public String getDomain() {
        return this.domain;
    }

    public String getId() {
        return this.id;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getDomain(), getIpAddress(), getId(), getSessionId(), Long.valueOf(getTimestamp()), getCsrf());
    }

    public void setCsrf(Csrf csrf) {
        this.csrf = csrf;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setIpAddress(String str) {
        this.ipAddress = str;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }
}
