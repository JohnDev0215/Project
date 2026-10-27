package Podcast.Touch.CompactDetailTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BookmarkElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.CompactDetailTemplateInterface.v1_0.BookmarkElement");
    private String id;
    private List<Method> onPauseSelected;
    private List<Method> onPlaySelected;
    private List<Method> onResumeSelected;
    private Long totalDurationMilliseconds;

    public static class Builder extends TemplateElement.Builder {
        protected String id;
        protected List<Method> onPauseSelected;
        protected List<Method> onPlaySelected;
        protected List<Method> onResumeSelected;
        protected Long totalDurationMilliseconds;

        public BookmarkElement build() {
            BookmarkElement bookmarkElement = new BookmarkElement();
            populate(bookmarkElement);
            return bookmarkElement;
        }

        protected void populate(BookmarkElement bookmarkElement) {
            super.populate((TemplateElement) bookmarkElement);
            bookmarkElement.setOnPauseSelected(this.onPauseSelected);
            bookmarkElement.setId(this.id);
            bookmarkElement.setOnResumeSelected(this.onResumeSelected);
            bookmarkElement.setTotalDurationMilliseconds(this.totalDurationMilliseconds);
            bookmarkElement.setOnPlaySelected(this.onPlaySelected);
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }

        public Builder withOnPauseSelected(List<Method> list) {
            this.onPauseSelected = list;
            return this;
        }

        public Builder withOnPlaySelected(List<Method> list) {
            this.onPlaySelected = list;
            return this;
        }

        public Builder withOnResumeSelected(List<Method> list) {
            this.onResumeSelected = list;
            return this;
        }

        public Builder withTotalDurationMilliseconds(Long l) {
            this.totalDurationMilliseconds = l;
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
        if (!(sOAObject instanceof BookmarkElement)) {
            return 1;
        }
        BookmarkElement bookmarkElement = (BookmarkElement) sOAObject;
        List<Method> onPauseSelected = getOnPauseSelected();
        List<Method> onPauseSelected2 = bookmarkElement.getOnPauseSelected();
        if (onPauseSelected != onPauseSelected2) {
            if (onPauseSelected == null) {
                return -1;
            }
            if (onPauseSelected2 == null) {
                return 1;
            }
            if (onPauseSelected instanceof Comparable) {
                int iCompareTo = ((Comparable) onPauseSelected).compareTo(onPauseSelected2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!onPauseSelected.equals(onPauseSelected2)) {
                int iHashCode = onPauseSelected.hashCode();
                int iHashCode2 = onPauseSelected2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String id = getId();
        String id2 = bookmarkElement.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo2 = id.compareTo(id2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<Method> onResumeSelected = getOnResumeSelected();
        List<Method> onResumeSelected2 = bookmarkElement.getOnResumeSelected();
        if (onResumeSelected != onResumeSelected2) {
            if (onResumeSelected == null) {
                return -1;
            }
            if (onResumeSelected2 == null) {
                return 1;
            }
            if (onResumeSelected instanceof Comparable) {
                int iCompareTo3 = ((Comparable) onResumeSelected).compareTo(onResumeSelected2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!onResumeSelected.equals(onResumeSelected2)) {
                int iHashCode3 = onResumeSelected.hashCode();
                int iHashCode4 = onResumeSelected2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        Long totalDurationMilliseconds = getTotalDurationMilliseconds();
        Long totalDurationMilliseconds2 = bookmarkElement.getTotalDurationMilliseconds();
        if (totalDurationMilliseconds != totalDurationMilliseconds2) {
            if (totalDurationMilliseconds == null) {
                return -1;
            }
            if (totalDurationMilliseconds2 == null) {
                return 1;
            }
            int iCompareTo4 = totalDurationMilliseconds.compareTo(totalDurationMilliseconds2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        List<Method> onPlaySelected = getOnPlaySelected();
        List<Method> onPlaySelected2 = bookmarkElement.getOnPlaySelected();
        if (onPlaySelected != onPlaySelected2) {
            if (onPlaySelected == null) {
                return -1;
            }
            if (onPlaySelected2 == null) {
                return 1;
            }
            if (onPlaySelected instanceof Comparable) {
                int iCompareTo5 = ((Comparable) onPlaySelected).compareTo(onPlaySelected2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!onPlaySelected.equals(onPlaySelected2)) {
                int iHashCode5 = onPlaySelected.hashCode();
                int iHashCode6 = onPlaySelected2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof BookmarkElement)) {
            return false;
        }
        BookmarkElement bookmarkElement = (BookmarkElement) obj;
        return super.equals(obj) && internalEqualityCheck(getOnPauseSelected(), bookmarkElement.getOnPauseSelected()) && internalEqualityCheck(getId(), bookmarkElement.getId()) && internalEqualityCheck(getOnResumeSelected(), bookmarkElement.getOnResumeSelected()) && internalEqualityCheck(getTotalDurationMilliseconds(), bookmarkElement.getTotalDurationMilliseconds()) && internalEqualityCheck(getOnPlaySelected(), bookmarkElement.getOnPlaySelected());
    }

    public String getId() {
        return this.id;
    }

    public List<Method> getOnPauseSelected() {
        return this.onPauseSelected;
    }

    public List<Method> getOnPlaySelected() {
        return this.onPlaySelected;
    }

    public List<Method> getOnResumeSelected() {
        return this.onResumeSelected;
    }

    public Long getTotalDurationMilliseconds() {
        return this.totalDurationMilliseconds;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnPauseSelected(), getId(), getOnResumeSelected(), getTotalDurationMilliseconds(), getOnPlaySelected());
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setOnPauseSelected(List<Method> list) {
        this.onPauseSelected = list;
    }

    public void setOnPlaySelected(List<Method> list) {
        this.onPlaySelected = list;
    }

    public void setOnResumeSelected(List<Method> list) {
        this.onResumeSelected = list;
    }

    public void setTotalDurationMilliseconds(Long l) {
        this.totalDurationMilliseconds = l;
    }
}
