package Podcast.Touch.PodcastDetailTemplateInterface.v1_0;

import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionElement;
import Podcast.RefinementOptionsInterface.v1_0.ToggleRefinementOptionElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RefinementBarElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PodcastDetailTemplateInterface.v1_0.RefinementBarElement");
    private String clientStateName;
    private ToggleRefinementOptionElement downloadFilter;
    private List<Method> onChange;
    private List<Method> onChangeWithDownloadFilter;
    private String podcastId;
    private CheckBoxRefinementOptionElement seasonOptions;
    private CheckBoxRefinementOptionElement sortingOptions;
    private Integer timeout;
    private ToggleRefinementOptionElement unplayedFilter;

    public static class Builder extends TemplateElement.Builder {
        protected String clientStateName;
        protected ToggleRefinementOptionElement downloadFilter;
        protected List<Method> onChange;
        protected List<Method> onChangeWithDownloadFilter;
        protected String podcastId;
        protected CheckBoxRefinementOptionElement seasonOptions;
        protected CheckBoxRefinementOptionElement sortingOptions;
        protected Integer timeout;
        protected ToggleRefinementOptionElement unplayedFilter;

        public RefinementBarElement build() {
            RefinementBarElement refinementBarElement = new RefinementBarElement();
            populate(refinementBarElement);
            return refinementBarElement;
        }

        protected void populate(RefinementBarElement refinementBarElement) {
            super.populate((TemplateElement) refinementBarElement);
            refinementBarElement.setOnChangeWithDownloadFilter(this.onChangeWithDownloadFilter);
            refinementBarElement.setSeasonOptions(this.seasonOptions);
            refinementBarElement.setDownloadFilter(this.downloadFilter);
            refinementBarElement.setTimeout(this.timeout);
            refinementBarElement.setPodcastId(this.podcastId);
            refinementBarElement.setSortingOptions(this.sortingOptions);
            refinementBarElement.setOnChange(this.onChange);
            refinementBarElement.setClientStateName(this.clientStateName);
            refinementBarElement.setUnplayedFilter(this.unplayedFilter);
        }

        public Builder withClientStateName(String str) {
            this.clientStateName = str;
            return this;
        }

        public Builder withDownloadFilter(ToggleRefinementOptionElement toggleRefinementOptionElement) {
            this.downloadFilter = toggleRefinementOptionElement;
            return this;
        }

        public Builder withOnChange(List<Method> list) {
            this.onChange = list;
            return this;
        }

        public Builder withOnChangeWithDownloadFilter(List<Method> list) {
            this.onChangeWithDownloadFilter = list;
            return this;
        }

        public Builder withPodcastId(String str) {
            this.podcastId = str;
            return this;
        }

        public Builder withSeasonOptions(CheckBoxRefinementOptionElement checkBoxRefinementOptionElement) {
            this.seasonOptions = checkBoxRefinementOptionElement;
            return this;
        }

        public Builder withSortingOptions(CheckBoxRefinementOptionElement checkBoxRefinementOptionElement) {
            this.sortingOptions = checkBoxRefinementOptionElement;
            return this;
        }

        public Builder withTimeout(Integer num) {
            this.timeout = num;
            return this;
        }

        public Builder withUnplayedFilter(ToggleRefinementOptionElement toggleRefinementOptionElement) {
            this.unplayedFilter = toggleRefinementOptionElement;
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
    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof RefinementBarElement)) {
            return 1;
        }
        RefinementBarElement refinementBarElement = (RefinementBarElement) sOAObject;
        List<Method> onChangeWithDownloadFilter = getOnChangeWithDownloadFilter();
        List<Method> onChangeWithDownloadFilter2 = refinementBarElement.getOnChangeWithDownloadFilter();
        if (onChangeWithDownloadFilter != onChangeWithDownloadFilter2) {
            if (onChangeWithDownloadFilter == null) {
                return -1;
            }
            if (onChangeWithDownloadFilter2 == null) {
                return 1;
            }
            if (onChangeWithDownloadFilter instanceof Comparable) {
                int iCompareTo = ((Comparable) onChangeWithDownloadFilter).compareTo(onChangeWithDownloadFilter2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!onChangeWithDownloadFilter.equals(onChangeWithDownloadFilter2)) {
                int iHashCode = onChangeWithDownloadFilter.hashCode();
                int iHashCode2 = onChangeWithDownloadFilter2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        CheckBoxRefinementOptionElement seasonOptions = getSeasonOptions();
        CheckBoxRefinementOptionElement seasonOptions2 = refinementBarElement.getSeasonOptions();
        if (seasonOptions != seasonOptions2) {
            if (seasonOptions == null) {
                return -1;
            }
            if (seasonOptions2 == null) {
                return 1;
            }
            int iCompareTo2 = seasonOptions.compareTo(seasonOptions2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        ToggleRefinementOptionElement downloadFilter = getDownloadFilter();
        ToggleRefinementOptionElement downloadFilter2 = refinementBarElement.getDownloadFilter();
        if (downloadFilter != downloadFilter2) {
            if (downloadFilter == null) {
                return -1;
            }
            if (downloadFilter2 == null) {
                return 1;
            }
            int iCompareTo3 = downloadFilter.compareTo(downloadFilter2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Integer timeout = getTimeout();
        Integer timeout2 = refinementBarElement.getTimeout();
        if (timeout != timeout2) {
            if (timeout == null) {
                return -1;
            }
            if (timeout2 == null) {
                return 1;
            }
            int iCompareTo4 = timeout.compareTo(timeout2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String podcastId = getPodcastId();
        String podcastId2 = refinementBarElement.getPodcastId();
        if (podcastId != podcastId2) {
            if (podcastId == null) {
                return -1;
            }
            if (podcastId2 == null) {
                return 1;
            }
            int iCompareTo5 = podcastId.compareTo(podcastId2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        CheckBoxRefinementOptionElement sortingOptions = getSortingOptions();
        CheckBoxRefinementOptionElement sortingOptions2 = refinementBarElement.getSortingOptions();
        if (sortingOptions != sortingOptions2) {
            if (sortingOptions == null) {
                return -1;
            }
            if (sortingOptions2 == null) {
                return 1;
            }
            int iCompareTo6 = sortingOptions.compareTo(sortingOptions2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        List<Method> onChange = getOnChange();
        List<Method> onChange2 = refinementBarElement.getOnChange();
        if (onChange != onChange2) {
            if (onChange == null) {
                return -1;
            }
            if (onChange2 == null) {
                return 1;
            }
            if (onChange instanceof Comparable) {
                int iCompareTo7 = ((Comparable) onChange).compareTo(onChange2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!onChange.equals(onChange2)) {
                int iHashCode3 = onChange.hashCode();
                int iHashCode4 = onChange2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String clientStateName = getClientStateName();
        String clientStateName2 = refinementBarElement.getClientStateName();
        if (clientStateName != clientStateName2) {
            if (clientStateName == null) {
                return -1;
            }
            if (clientStateName2 == null) {
                return 1;
            }
            int iCompareTo8 = clientStateName.compareTo(clientStateName2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        ToggleRefinementOptionElement unplayedFilter = getUnplayedFilter();
        ToggleRefinementOptionElement unplayedFilter2 = refinementBarElement.getUnplayedFilter();
        if (unplayedFilter != unplayedFilter2) {
            if (unplayedFilter == null) {
                return -1;
            }
            if (unplayedFilter2 == null) {
                return 1;
            }
            int iCompareTo9 = unplayedFilter.compareTo(unplayedFilter2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof RefinementBarElement)) {
            return false;
        }
        RefinementBarElement refinementBarElement = (RefinementBarElement) obj;
        return super.equals(obj) && internalEqualityCheck(getOnChangeWithDownloadFilter(), refinementBarElement.getOnChangeWithDownloadFilter()) && internalEqualityCheck(getSeasonOptions(), refinementBarElement.getSeasonOptions()) && internalEqualityCheck(getDownloadFilter(), refinementBarElement.getDownloadFilter()) && internalEqualityCheck(getTimeout(), refinementBarElement.getTimeout()) && internalEqualityCheck(getPodcastId(), refinementBarElement.getPodcastId()) && internalEqualityCheck(getSortingOptions(), refinementBarElement.getSortingOptions()) && internalEqualityCheck(getOnChange(), refinementBarElement.getOnChange()) && internalEqualityCheck(getClientStateName(), refinementBarElement.getClientStateName()) && internalEqualityCheck(getUnplayedFilter(), refinementBarElement.getUnplayedFilter());
    }

    public String getClientStateName() {
        return this.clientStateName;
    }

    public ToggleRefinementOptionElement getDownloadFilter() {
        return this.downloadFilter;
    }

    public List<Method> getOnChange() {
        return this.onChange;
    }

    public List<Method> getOnChangeWithDownloadFilter() {
        return this.onChangeWithDownloadFilter;
    }

    public String getPodcastId() {
        return this.podcastId;
    }

    public CheckBoxRefinementOptionElement getSeasonOptions() {
        return this.seasonOptions;
    }

    public CheckBoxRefinementOptionElement getSortingOptions() {
        return this.sortingOptions;
    }

    public Integer getTimeout() {
        return this.timeout;
    }

    public ToggleRefinementOptionElement getUnplayedFilter() {
        return this.unplayedFilter;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnChangeWithDownloadFilter(), getSeasonOptions(), getDownloadFilter(), getTimeout(), getPodcastId(), getSortingOptions(), getOnChange(), getClientStateName(), getUnplayedFilter());
    }

    public void setClientStateName(String str) {
        this.clientStateName = str;
    }

    public void setDownloadFilter(ToggleRefinementOptionElement toggleRefinementOptionElement) {
        this.downloadFilter = toggleRefinementOptionElement;
    }

    public void setOnChange(List<Method> list) {
        this.onChange = list;
    }

    public void setOnChangeWithDownloadFilter(List<Method> list) {
        this.onChangeWithDownloadFilter = list;
    }

    public void setPodcastId(String str) {
        this.podcastId = str;
    }

    public void setSeasonOptions(CheckBoxRefinementOptionElement checkBoxRefinementOptionElement) {
        this.seasonOptions = checkBoxRefinementOptionElement;
    }

    public void setSortingOptions(CheckBoxRefinementOptionElement checkBoxRefinementOptionElement) {
        this.sortingOptions = checkBoxRefinementOptionElement;
    }

    public void setTimeout(Integer num) {
        this.timeout = num;
    }

    public void setUnplayedFilter(ToggleRefinementOptionElement toggleRefinementOptionElement) {
        this.unplayedFilter = toggleRefinementOptionElement;
    }
}
