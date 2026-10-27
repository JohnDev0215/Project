package Podcast.PlaybackInterface.v1_0;

import Podcast.PlaybackInterface.v1_0.PlaybackElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SleepTimerElement extends PlaybackElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.PlaybackInterface.v1_0.SleepTimerElement");
    private List<Method> onItemSelected;

    public static class Builder extends PlaybackElement.Builder {
        protected List<Method> onItemSelected;

        public SleepTimerElement build() {
            SleepTimerElement sleepTimerElement = new SleepTimerElement();
            populate(sleepTimerElement);
            return sleepTimerElement;
        }

        protected void populate(SleepTimerElement sleepTimerElement) {
            super.populate((PlaybackElement) sleepTimerElement);
            sleepTimerElement.setOnItemSelected(this.onItemSelected);
        }

        public Builder withOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
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
    @Override // Podcast.PlaybackInterface.v1_0.PlaybackElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof SleepTimerElement)) {
            return 1;
        }
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = ((SleepTimerElement) sOAObject).getOnItemSelected();
        if (onItemSelected != onItemSelected2) {
            if (onItemSelected == null) {
                return -1;
            }
            if (onItemSelected2 == null) {
                return 1;
            }
            if (onItemSelected instanceof Comparable) {
                int iCompareTo = ((Comparable) onItemSelected).compareTo(onItemSelected2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!onItemSelected.equals(onItemSelected2)) {
                int iHashCode = onItemSelected.hashCode();
                int iHashCode2 = onItemSelected2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // Podcast.PlaybackInterface.v1_0.PlaybackElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof SleepTimerElement) {
            return super.equals(obj) && internalEqualityCheck(getOnItemSelected(), ((SleepTimerElement) obj).getOnItemSelected());
        }
        return false;
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    @Override // Podcast.PlaybackInterface.v1_0.PlaybackElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnItemSelected());
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }
}
