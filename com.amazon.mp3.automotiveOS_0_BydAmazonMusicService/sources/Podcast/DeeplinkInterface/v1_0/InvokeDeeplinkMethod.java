package Podcast.DeeplinkInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class InvokeDeeplinkMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.DeeplinkInterface.v1_0.InvokeDeeplinkMethod");
    private List<Method> after;
    private boolean backStackRequired;
    private List<Method> before;
    private List<String> clientStates;
    private String deeplinkUrl;
    private Map<String, String> extra;
    private LatencyMetricElement latencyMetricElement;
    private List<Method> onError;
    private List<Method> onSuccess;
    private String preset;
    private String target;
    private String url;

    public static class Builder extends Method.Builder {
        protected List<Method> after;
        protected boolean backStackRequired;
        protected List<Method> before;
        protected List<String> clientStates;
        protected String deeplinkUrl;
        protected Map<String, String> extra;
        protected LatencyMetricElement latencyMetricElement;
        protected List<Method> onError;
        protected List<Method> onSuccess;
        protected String preset;
        protected String target;
        protected String url;

        public InvokeDeeplinkMethod build() {
            InvokeDeeplinkMethod invokeDeeplinkMethod = new InvokeDeeplinkMethod();
            populate(invokeDeeplinkMethod);
            return invokeDeeplinkMethod;
        }

        protected void populate(InvokeDeeplinkMethod invokeDeeplinkMethod) {
            super.populate((Method) invokeDeeplinkMethod);
            invokeDeeplinkMethod.setDeeplinkUrl(this.deeplinkUrl);
            invokeDeeplinkMethod.setTarget(this.target);
            invokeDeeplinkMethod.setPreset(this.preset);
            invokeDeeplinkMethod.setUrl(this.url);
            invokeDeeplinkMethod.setExtra(this.extra);
            invokeDeeplinkMethod.setBefore(this.before);
            invokeDeeplinkMethod.setOnError(this.onError);
            invokeDeeplinkMethod.setAfter(this.after);
            invokeDeeplinkMethod.setOnSuccess(this.onSuccess);
            invokeDeeplinkMethod.setLatencyMetricElement(this.latencyMetricElement);
            invokeDeeplinkMethod.setBackStackRequired(this.backStackRequired);
            invokeDeeplinkMethod.setClientStates(this.clientStates);
        }

        public Builder withAfter(List<Method> list) {
            this.after = list;
            return this;
        }

        public Builder withBackStackRequired(boolean z) {
            this.backStackRequired = z;
            return this;
        }

        public Builder withBefore(List<Method> list) {
            this.before = list;
            return this;
        }

        public Builder withClientStates(List<String> list) {
            this.clientStates = list;
            return this;
        }

        public Builder withDeeplinkUrl(String str) {
            this.deeplinkUrl = str;
            return this;
        }

        public Builder withExtra(Map<String, String> map) {
            this.extra = map;
            return this;
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        public Builder withLatencyMetricElement(LatencyMetricElement latencyMetricElement) {
            this.latencyMetricElement = latencyMetricElement;
            return this;
        }

        public Builder withOnError(List<Method> list) {
            this.onError = list;
            return this;
        }

        public Builder withOnSuccess(List<Method> list) {
            this.onSuccess = list;
            return this;
        }

        public Builder withPreset(String str) {
            this.preset = str;
            return this;
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withQueue(Queue queue) {
            super.withQueue(queue);
            return this;
        }

        public Builder withTarget(String str) {
            this.target = str;
            return this;
        }

        public Builder withUrl(String str) {
            this.url = str;
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
    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof InvokeDeeplinkMethod)) {
            return 1;
        }
        InvokeDeeplinkMethod invokeDeeplinkMethod = (InvokeDeeplinkMethod) sOAObject;
        String deeplinkUrl = getDeeplinkUrl();
        String deeplinkUrl2 = invokeDeeplinkMethod.getDeeplinkUrl();
        if (deeplinkUrl != deeplinkUrl2) {
            if (deeplinkUrl == null) {
                return -1;
            }
            if (deeplinkUrl2 == null) {
                return 1;
            }
            int iCompareTo = deeplinkUrl.compareTo(deeplinkUrl2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String target = getTarget();
        String target2 = invokeDeeplinkMethod.getTarget();
        if (target != target2) {
            if (target == null) {
                return -1;
            }
            if (target2 == null) {
                return 1;
            }
            int iCompareTo2 = target.compareTo(target2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String preset = getPreset();
        String preset2 = invokeDeeplinkMethod.getPreset();
        if (preset != preset2) {
            if (preset == null) {
                return -1;
            }
            if (preset2 == null) {
                return 1;
            }
            int iCompareTo3 = preset.compareTo(preset2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String url = getUrl();
        String url2 = invokeDeeplinkMethod.getUrl();
        if (url != url2) {
            if (url == null) {
                return -1;
            }
            if (url2 == null) {
                return 1;
            }
            int iCompareTo4 = url.compareTo(url2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Map<String, String> extra = getExtra();
        Map<String, String> extra2 = invokeDeeplinkMethod.getExtra();
        if (extra != extra2) {
            if (extra == null) {
                return -1;
            }
            if (extra2 == null) {
                return 1;
            }
            if (extra instanceof Comparable) {
                int iCompareTo5 = ((Comparable) extra).compareTo(extra2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!extra.equals(extra2)) {
                int iHashCode = extra.hashCode();
                int iHashCode2 = extra2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<Method> before = getBefore();
        List<Method> before2 = invokeDeeplinkMethod.getBefore();
        if (before != before2) {
            if (before == null) {
                return -1;
            }
            if (before2 == null) {
                return 1;
            }
            if (before instanceof Comparable) {
                int iCompareTo6 = ((Comparable) before).compareTo(before2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!before.equals(before2)) {
                int iHashCode3 = before.hashCode();
                int iHashCode4 = before2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        List<Method> onError = getOnError();
        List<Method> onError2 = invokeDeeplinkMethod.getOnError();
        if (onError != onError2) {
            if (onError == null) {
                return -1;
            }
            if (onError2 == null) {
                return 1;
            }
            if (onError instanceof Comparable) {
                int iCompareTo7 = ((Comparable) onError).compareTo(onError2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!onError.equals(onError2)) {
                int iHashCode5 = onError.hashCode();
                int iHashCode6 = onError2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        List<Method> after = getAfter();
        List<Method> after2 = invokeDeeplinkMethod.getAfter();
        if (after != after2) {
            if (after == null) {
                return -1;
            }
            if (after2 == null) {
                return 1;
            }
            if (after instanceof Comparable) {
                int iCompareTo8 = ((Comparable) after).compareTo(after2);
                if (iCompareTo8 != 0) {
                    return iCompareTo8;
                }
            } else if (!after.equals(after2)) {
                int iHashCode7 = after.hashCode();
                int iHashCode8 = after2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        List<Method> onSuccess = getOnSuccess();
        List<Method> onSuccess2 = invokeDeeplinkMethod.getOnSuccess();
        if (onSuccess != onSuccess2) {
            if (onSuccess == null) {
                return -1;
            }
            if (onSuccess2 == null) {
                return 1;
            }
            if (onSuccess instanceof Comparable) {
                int iCompareTo9 = ((Comparable) onSuccess).compareTo(onSuccess2);
                if (iCompareTo9 != 0) {
                    return iCompareTo9;
                }
            } else if (!onSuccess.equals(onSuccess2)) {
                int iHashCode9 = onSuccess.hashCode();
                int iHashCode10 = onSuccess2.hashCode();
                if (iHashCode9 < iHashCode10) {
                    return -1;
                }
                if (iHashCode9 > iHashCode10) {
                    return 1;
                }
            }
        }
        LatencyMetricElement latencyMetricElement = getLatencyMetricElement();
        LatencyMetricElement latencyMetricElement2 = invokeDeeplinkMethod.getLatencyMetricElement();
        if (latencyMetricElement != latencyMetricElement2) {
            if (latencyMetricElement == null) {
                return -1;
            }
            if (latencyMetricElement2 == null) {
                return 1;
            }
            int iCompareTo10 = latencyMetricElement.compareTo(latencyMetricElement2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        if (!isBackStackRequired() && invokeDeeplinkMethod.isBackStackRequired()) {
            return -1;
        }
        if (isBackStackRequired() && !invokeDeeplinkMethod.isBackStackRequired()) {
            return 1;
        }
        List<String> clientStates = getClientStates();
        List<String> clientStates2 = invokeDeeplinkMethod.getClientStates();
        if (clientStates != clientStates2) {
            if (clientStates == null) {
                return -1;
            }
            if (clientStates2 == null) {
                return 1;
            }
            if (clientStates instanceof Comparable) {
                int iCompareTo11 = ((Comparable) clientStates).compareTo(clientStates2);
                if (iCompareTo11 != 0) {
                    return iCompareTo11;
                }
            } else if (!clientStates.equals(clientStates2)) {
                int iHashCode11 = clientStates.hashCode();
                int iHashCode12 = clientStates2.hashCode();
                if (iHashCode11 < iHashCode12) {
                    return -1;
                }
                if (iHashCode11 > iHashCode12) {
                    return 1;
                }
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof InvokeDeeplinkMethod)) {
            return false;
        }
        InvokeDeeplinkMethod invokeDeeplinkMethod = (InvokeDeeplinkMethod) obj;
        return super.equals(obj) && internalEqualityCheck(getDeeplinkUrl(), invokeDeeplinkMethod.getDeeplinkUrl()) && internalEqualityCheck(getTarget(), invokeDeeplinkMethod.getTarget()) && internalEqualityCheck(getPreset(), invokeDeeplinkMethod.getPreset()) && internalEqualityCheck(getUrl(), invokeDeeplinkMethod.getUrl()) && internalEqualityCheck(getExtra(), invokeDeeplinkMethod.getExtra()) && internalEqualityCheck(getBefore(), invokeDeeplinkMethod.getBefore()) && internalEqualityCheck(getOnError(), invokeDeeplinkMethod.getOnError()) && internalEqualityCheck(getAfter(), invokeDeeplinkMethod.getAfter()) && internalEqualityCheck(getOnSuccess(), invokeDeeplinkMethod.getOnSuccess()) && internalEqualityCheck(getLatencyMetricElement(), invokeDeeplinkMethod.getLatencyMetricElement()) && internalEqualityCheck(Boolean.valueOf(isBackStackRequired()), Boolean.valueOf(invokeDeeplinkMethod.isBackStackRequired())) && internalEqualityCheck(getClientStates(), invokeDeeplinkMethod.getClientStates());
    }

    public List<Method> getAfter() {
        return this.after;
    }

    public List<Method> getBefore() {
        return this.before;
    }

    public List<String> getClientStates() {
        return this.clientStates;
    }

    public String getDeeplinkUrl() {
        return this.deeplinkUrl;
    }

    public Map<String, String> getExtra() {
        return this.extra;
    }

    public LatencyMetricElement getLatencyMetricElement() {
        return this.latencyMetricElement;
    }

    public List<Method> getOnError() {
        return this.onError;
    }

    public List<Method> getOnSuccess() {
        return this.onSuccess;
    }

    public String getPreset() {
        return this.preset;
    }

    public String getTarget() {
        return this.target;
    }

    public String getUrl() {
        return this.url;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getDeeplinkUrl(), getTarget(), getPreset(), getUrl(), getExtra(), getBefore(), getOnError(), getAfter(), getOnSuccess(), getLatencyMetricElement(), Boolean.valueOf(isBackStackRequired()), getClientStates());
    }

    public boolean isBackStackRequired() {
        return this.backStackRequired;
    }

    public void setAfter(List<Method> list) {
        this.after = list;
    }

    public void setBackStackRequired(boolean z) {
        this.backStackRequired = z;
    }

    public void setBefore(List<Method> list) {
        this.before = list;
    }

    public void setClientStates(List<String> list) {
        this.clientStates = list;
    }

    public void setDeeplinkUrl(String str) {
        this.deeplinkUrl = str;
    }

    public void setExtra(Map<String, String> map) {
        this.extra = map;
    }

    public void setLatencyMetricElement(LatencyMetricElement latencyMetricElement) {
        this.latencyMetricElement = latencyMetricElement;
    }

    public void setOnError(List<Method> list) {
        this.onError = list;
    }

    public void setOnSuccess(List<Method> list) {
        this.onSuccess = list;
    }

    public void setPreset(String str) {
        this.preset = str;
    }

    public void setTarget(String str) {
        this.target = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
