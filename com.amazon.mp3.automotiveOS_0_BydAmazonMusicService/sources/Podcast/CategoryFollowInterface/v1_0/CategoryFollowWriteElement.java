package Podcast.CategoryFollowInterface.v1_0;

import SOAAppSyncInterface.v1_0.WriteElement;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class CategoryFollowWriteElement extends WriteElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.CategoryFollowInterface.v1_0.CategoryFollowWriteElement");
    private boolean followed;
    private String title;

    public static class Builder extends WriteElement.Builder {
        protected boolean followed;
        protected String title;

        public CategoryFollowWriteElement build() {
            CategoryFollowWriteElement categoryFollowWriteElement = new CategoryFollowWriteElement();
            populate(categoryFollowWriteElement);
            return categoryFollowWriteElement;
        }

        protected void populate(CategoryFollowWriteElement categoryFollowWriteElement) {
            super.populate((WriteElement) categoryFollowWriteElement);
            categoryFollowWriteElement.setFollowed(this.followed);
            categoryFollowWriteElement.setTitle(this.title);
        }

        public Builder withFollowed(boolean z) {
            this.followed = z;
            return this;
        }

        @Override // SOAAppSyncInterface.v1_0.WriteElement.Builder
        public Builder withId(String str) {
            super.withId(str);
            return this;
        }

        public Builder withTitle(String str) {
            this.title = str;
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
    @Override // SOAAppSyncInterface.v1_0.WriteElement, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated WriteElement writeElement) {
        if (writeElement == null) {
            return -1;
        }
        if (writeElement == this) {
            return 0;
        }
        if (!(writeElement instanceof CategoryFollowWriteElement)) {
            return 1;
        }
        CategoryFollowWriteElement categoryFollowWriteElement = (CategoryFollowWriteElement) writeElement;
        if (!isFollowed() && categoryFollowWriteElement.isFollowed()) {
            return -1;
        }
        if (isFollowed() && !categoryFollowWriteElement.isFollowed()) {
            return 1;
        }
        String title = getTitle();
        String title2 = categoryFollowWriteElement.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo = title.compareTo(title2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(writeElement);
    }

    @Override // SOAAppSyncInterface.v1_0.WriteElement
    public boolean equals(Object obj) {
        if (!(obj instanceof CategoryFollowWriteElement)) {
            return false;
        }
        CategoryFollowWriteElement categoryFollowWriteElement = (CategoryFollowWriteElement) obj;
        return super.equals(obj) && internalEqualityCheck(Boolean.valueOf(isFollowed()), Boolean.valueOf(categoryFollowWriteElement.isFollowed())) && internalEqualityCheck(getTitle(), categoryFollowWriteElement.getTitle());
    }

    public String getTitle() {
        return this.title;
    }

    @Override // SOAAppSyncInterface.v1_0.WriteElement
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), Boolean.valueOf(isFollowed()), getTitle());
    }

    public boolean isFollowed() {
        return this.followed;
    }

    public void setFollowed(boolean z) {
        this.followed = z;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
