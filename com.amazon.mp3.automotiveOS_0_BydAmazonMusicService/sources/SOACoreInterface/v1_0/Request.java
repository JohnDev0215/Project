package SOACoreInterface.v1_0;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class Request implements Comparable<Request> {
    private static final int classNameHashCode = internalHashCodeCompute("SOACoreInterface.v1_0.Request");
    private Map<String, ClientState> clientStates;
    private Map<String, String> extra;
    private Identity identity;
    private String preset;

    public static class Builder {
        protected Map<String, ClientState> clientStates;
        protected Map<String, String> extra;
        protected Identity identity;
        protected String preset;

        public Request build() {
            Request request = new Request();
            populate(request);
            return request;
        }

        protected void populate(Request request) {
            request.setClientStates(this.clientStates);
            request.setPreset(this.preset);
            request.setExtra(this.extra);
            request.setIdentity(this.identity);
        }

        public Builder withClientStates(Map<String, ClientState> map) {
            this.clientStates = map;
            return this;
        }

        public Builder withExtra(Map<String, String> map) {
            this.extra = map;
            return this;
        }

        public Builder withIdentity(Identity identity) {
            this.identity = identity;
            return this;
        }

        public Builder withPreset(String str) {
            this.preset = str;
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

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Request request) {
        if (request == null) {
            return -1;
        }
        if (request == this) {
            return 0;
        }
        Map<String, ClientState> clientStates = getClientStates();
        Map<String, ClientState> clientStates2 = request.getClientStates();
        if (clientStates != clientStates2) {
            if (clientStates == null) {
                return -1;
            }
            if (clientStates2 == null) {
                return 1;
            }
            if (clientStates instanceof Comparable) {
                int iCompareTo = ((Comparable) clientStates).compareTo(clientStates2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!clientStates.equals(clientStates2)) {
                int iHashCode = clientStates.hashCode();
                int iHashCode2 = clientStates2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String preset = getPreset();
        String preset2 = request.getPreset();
        if (preset != preset2) {
            if (preset == null) {
                return -1;
            }
            if (preset2 == null) {
                return 1;
            }
            int iCompareTo2 = preset.compareTo(preset2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Map<String, String> extra = getExtra();
        Map<String, String> extra2 = request.getExtra();
        if (extra != extra2) {
            if (extra == null) {
                return -1;
            }
            if (extra2 == null) {
                return 1;
            }
            if (extra instanceof Comparable) {
                int iCompareTo3 = ((Comparable) extra).compareTo(extra2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!extra.equals(extra2)) {
                int iHashCode3 = extra.hashCode();
                int iHashCode4 = extra2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        Identity identity = getIdentity();
        Identity identity2 = request.getIdentity();
        if (identity != identity2) {
            if (identity == null) {
                return -1;
            }
            if (identity2 == null) {
                return 1;
            }
            int iCompareTo4 = identity.compareTo(identity2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Request)) {
            return false;
        }
        Request request = (Request) obj;
        return internalEqualityCheck(getClientStates(), request.getClientStates()) && internalEqualityCheck(getPreset(), request.getPreset()) && internalEqualityCheck(getExtra(), request.getExtra()) && internalEqualityCheck(getIdentity(), request.getIdentity());
    }

    public Map<String, ClientState> getClientStates() {
        return this.clientStates;
    }

    public Map<String, String> getExtra() {
        return this.extra;
    }

    public Identity getIdentity() {
        return this.identity;
    }

    public String getPreset() {
        return this.preset;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getClientStates(), getPreset(), getExtra(), getIdentity());
    }

    public void setClientStates(Map<String, ClientState> map) {
        this.clientStates = map;
    }

    public void setExtra(Map<String, String> map) {
        this.extra = map;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public void setPreset(String str) {
        this.preset = str;
    }
}
