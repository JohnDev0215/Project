package Podcast.Touch.BookmarkElementInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class BookmarkElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.BookmarkElementInterface.v1_0.BookmarkElement");
    private String id;
    private Long totalDurationMilliseconds;

    public static class Builder extends TemplateElement.Builder {
        protected String id;
        protected Long totalDurationMilliseconds;

        public BookmarkElement build() {
            BookmarkElement bookmarkElement = new BookmarkElement();
            populate(bookmarkElement);
            return bookmarkElement;
        }

        protected void populate(BookmarkElement bookmarkElement) {
            super.populate((TemplateElement) bookmarkElement);
            bookmarkElement.setId(this.id);
            bookmarkElement.setTotalDurationMilliseconds(this.totalDurationMilliseconds);
        }

        public Builder withId(String str) {
            this.id = str;
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
        String id = getId();
        String id2 = bookmarkElement.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo = id.compareTo(id2);
            if (iCompareTo != 0) {
                return iCompareTo;
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
            int iCompareTo2 = totalDurationMilliseconds.compareTo(totalDurationMilliseconds2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
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
        return super.equals(obj) && internalEqualityCheck(getId(), bookmarkElement.getId()) && internalEqualityCheck(getTotalDurationMilliseconds(), bookmarkElement.getTotalDurationMilliseconds());
    }

    public String getId() {
        return this.id;
    }

    public Long getTotalDurationMilliseconds() {
        return this.totalDurationMilliseconds;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getId(), getTotalDurationMilliseconds());
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setTotalDurationMilliseconds(Long l) {
        this.totalDurationMilliseconds = l;
    }
}
