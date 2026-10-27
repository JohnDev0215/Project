package SOACoreInterface.v1_0;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class Csrf implements Comparable<Csrf> {
    private static final int classNameHashCode = internalHashCodeCompute("SOACoreInterface.v1_0.Csrf");
    private String rnd;
    private String token;
    private String ts;

    public static class Builder {
        protected String rnd;
        protected String token;
        protected String ts;

        public Csrf build() {
            Csrf csrf = new Csrf();
            populate(csrf);
            return csrf;
        }

        protected void populate(Csrf csrf) {
            csrf.setToken(this.token);
            csrf.setRnd(this.rnd);
            csrf.setTs(this.ts);
        }

        public Builder withRnd(String str) {
            this.rnd = str;
            return this;
        }

        public Builder withToken(String str) {
            this.token = str;
            return this;
        }

        public Builder withTs(String str) {
            this.ts = str;
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
    public int compareTo(@Deprecated Csrf csrf) {
        if (csrf == null) {
            return -1;
        }
        if (csrf == this) {
            return 0;
        }
        String token = getToken();
        String token2 = csrf.getToken();
        if (token != token2) {
            if (token == null) {
                return -1;
            }
            if (token2 == null) {
                return 1;
            }
            int iCompareTo = token.compareTo(token2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String rnd = getRnd();
        String rnd2 = csrf.getRnd();
        if (rnd != rnd2) {
            if (rnd == null) {
                return -1;
            }
            if (rnd2 == null) {
                return 1;
            }
            int iCompareTo2 = rnd.compareTo(rnd2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String ts = getTs();
        String ts2 = csrf.getTs();
        if (ts != ts2) {
            if (ts == null) {
                return -1;
            }
            if (ts2 == null) {
                return 1;
            }
            int iCompareTo3 = ts.compareTo(ts2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Csrf)) {
            return false;
        }
        Csrf csrf = (Csrf) obj;
        return internalEqualityCheck(getToken(), csrf.getToken()) && internalEqualityCheck(getRnd(), csrf.getRnd()) && internalEqualityCheck(getTs(), csrf.getTs());
    }

    public String getRnd() {
        return this.rnd;
    }

    public String getToken() {
        return this.token;
    }

    public String getTs() {
        return this.ts;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getToken(), getRnd(), getTs());
    }

    public void setRnd(String str) {
        this.rnd = str;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public void setTs(String str) {
        this.ts = str;
    }
}
