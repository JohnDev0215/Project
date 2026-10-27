package SOATemplateListInterface.v1_0;

import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class Template extends SOAObject {
    private static final int classNameHashCode = internalHashCodeCompute("SOATemplateListInterface.v1_0.Template");
    private Long cacheSeconds;
    private LatencyMetricElement latencyMetricElement;
    private String launchMode;
    private List<Method> onBound;
    private List<Method> onCreated;
    private List<Method> onDismissed;
    private List<Method> onViewed;
    private String pageType;

    public static abstract class Builder extends SOAObject.Builder {
        protected Long cacheSeconds;
        protected LatencyMetricElement latencyMetricElement;
        protected String launchMode;
        protected List<Method> onBound;
        protected List<Method> onCreated;
        protected List<Method> onDismissed;
        protected List<Method> onViewed;
        protected String pageType;

        protected void populate(Template template) {
            super.populate((SOAObject) template);
            template.setLatencyMetricElement(this.latencyMetricElement);
            template.setCacheSeconds(this.cacheSeconds);
            template.setOnCreated(this.onCreated);
            template.setOnViewed(this.onViewed);
            template.setLaunchMode(this.launchMode);
            template.setPageType(this.pageType);
            template.setOnBound(this.onBound);
            template.setOnDismissed(this.onDismissed);
        }

        public Builder withCacheSeconds(Long l) {
            this.cacheSeconds = l;
            return this;
        }

        public Builder withLatencyMetricElement(LatencyMetricElement latencyMetricElement) {
            this.latencyMetricElement = latencyMetricElement;
            return this;
        }

        public Builder withLaunchMode(String str) {
            this.launchMode = str;
            return this;
        }

        public Builder withOnBound(List<Method> list) {
            this.onBound = list;
            return this;
        }

        public Builder withOnCreated(List<Method> list) {
            this.onCreated = list;
            return this;
        }

        public Builder withOnDismissed(List<Method> list) {
            this.onDismissed = list;
            return this;
        }

        public Builder withOnViewed(List<Method> list) {
            this.onViewed = list;
            return this;
        }

        @Deprecated
        public Builder withPageType(String str) {
            this.pageType = str;
            return this;
        }
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
        if (!(sOAObject instanceof Template)) {
            return 1;
        }
        Template template = (Template) sOAObject;
        LatencyMetricElement latencyMetricElement = getLatencyMetricElement();
        LatencyMetricElement latencyMetricElement2 = template.getLatencyMetricElement();
        if (latencyMetricElement != latencyMetricElement2) {
            if (latencyMetricElement == null) {
                return -1;
            }
            if (latencyMetricElement2 == null) {
                return 1;
            }
            int iCompareTo = latencyMetricElement.compareTo(latencyMetricElement2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Long cacheSeconds = getCacheSeconds();
        Long cacheSeconds2 = template.getCacheSeconds();
        if (cacheSeconds != cacheSeconds2) {
            if (cacheSeconds == null) {
                return -1;
            }
            if (cacheSeconds2 == null) {
                return 1;
            }
            int iCompareTo2 = cacheSeconds.compareTo(cacheSeconds2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<Method> onCreated = getOnCreated();
        List<Method> onCreated2 = template.getOnCreated();
        if (onCreated != onCreated2) {
            if (onCreated == null) {
                return -1;
            }
            if (onCreated2 == null) {
                return 1;
            }
            if (onCreated instanceof Comparable) {
                int iCompareTo3 = ((Comparable) onCreated).compareTo(onCreated2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!onCreated.equals(onCreated2)) {
                int iHashCode = onCreated.hashCode();
                int iHashCode2 = onCreated2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<Method> onViewed = getOnViewed();
        List<Method> onViewed2 = template.getOnViewed();
        if (onViewed != onViewed2) {
            if (onViewed == null) {
                return -1;
            }
            if (onViewed2 == null) {
                return 1;
            }
            if (onViewed instanceof Comparable) {
                int iCompareTo4 = ((Comparable) onViewed).compareTo(onViewed2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!onViewed.equals(onViewed2)) {
                int iHashCode3 = onViewed.hashCode();
                int iHashCode4 = onViewed2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String launchMode = getLaunchMode();
        String launchMode2 = template.getLaunchMode();
        if (launchMode != launchMode2) {
            if (launchMode == null) {
                return -1;
            }
            if (launchMode2 == null) {
                return 1;
            }
            int iCompareTo5 = launchMode.compareTo(launchMode2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String pageType = getPageType();
        String pageType2 = template.getPageType();
        if (pageType != pageType2) {
            if (pageType == null) {
                return -1;
            }
            if (pageType2 == null) {
                return 1;
            }
            int iCompareTo6 = pageType.compareTo(pageType2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        List<Method> onBound = getOnBound();
        List<Method> onBound2 = template.getOnBound();
        if (onBound != onBound2) {
            if (onBound == null) {
                return -1;
            }
            if (onBound2 == null) {
                return 1;
            }
            if (onBound instanceof Comparable) {
                int iCompareTo7 = ((Comparable) onBound).compareTo(onBound2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!onBound.equals(onBound2)) {
                int iHashCode5 = onBound.hashCode();
                int iHashCode6 = onBound2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        List<Method> onDismissed = getOnDismissed();
        List<Method> onDismissed2 = template.getOnDismissed();
        if (onDismissed != onDismissed2) {
            if (onDismissed == null) {
                return -1;
            }
            if (onDismissed2 == null) {
                return 1;
            }
            if (onDismissed instanceof Comparable) {
                int iCompareTo8 = ((Comparable) onDismissed).compareTo(onDismissed2);
                if (iCompareTo8 != 0) {
                    return iCompareTo8;
                }
            } else if (!onDismissed.equals(onDismissed2)) {
                int iHashCode7 = onDismissed.hashCode();
                int iHashCode8 = onDismissed2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof Template)) {
            return false;
        }
        Template template = (Template) obj;
        return super.equals(obj) && internalEqualityCheck(getLatencyMetricElement(), template.getLatencyMetricElement()) && internalEqualityCheck(getCacheSeconds(), template.getCacheSeconds()) && internalEqualityCheck(getOnCreated(), template.getOnCreated()) && internalEqualityCheck(getOnViewed(), template.getOnViewed()) && internalEqualityCheck(getLaunchMode(), template.getLaunchMode()) && internalEqualityCheck(getPageType(), template.getPageType()) && internalEqualityCheck(getOnBound(), template.getOnBound()) && internalEqualityCheck(getOnDismissed(), template.getOnDismissed());
    }

    public Long getCacheSeconds() {
        return this.cacheSeconds;
    }

    public LatencyMetricElement getLatencyMetricElement() {
        return this.latencyMetricElement;
    }

    public String getLaunchMode() {
        return this.launchMode;
    }

    public List<Method> getOnBound() {
        return this.onBound;
    }

    public List<Method> getOnCreated() {
        return this.onCreated;
    }

    public List<Method> getOnDismissed() {
        return this.onDismissed;
    }

    public List<Method> getOnViewed() {
        return this.onViewed;
    }

    @Deprecated
    public String getPageType() {
        return this.pageType;
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getLatencyMetricElement(), getCacheSeconds(), getOnCreated(), getOnViewed(), getLaunchMode(), getPageType(), getOnBound(), getOnDismissed());
    }

    public void setCacheSeconds(Long l) {
        this.cacheSeconds = l;
    }

    public void setLatencyMetricElement(LatencyMetricElement latencyMetricElement) {
        this.latencyMetricElement = latencyMetricElement;
    }

    public void setLaunchMode(String str) {
        this.launchMode = str;
    }

    public void setOnBound(List<Method> list) {
        this.onBound = list;
    }

    public void setOnCreated(List<Method> list) {
        this.onCreated = list;
    }

    public void setOnDismissed(List<Method> list) {
        this.onDismissed = list;
    }

    public void setOnViewed(List<Method> list) {
        this.onViewed = list;
    }

    @Deprecated
    public void setPageType(String str) {
        this.pageType = str;
    }
}
