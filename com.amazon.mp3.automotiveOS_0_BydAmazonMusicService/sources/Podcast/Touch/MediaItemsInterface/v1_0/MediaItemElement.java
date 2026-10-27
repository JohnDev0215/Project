package Podcast.Touch.MediaItemsInterface.v1_0;

import Podcast.Touch.BookmarkElementInterface.v1_0.BookmarkElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MediaItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.MediaItemsInterface.v1_0.MediaItemElement");
    private BookmarkElement bookmark;
    private Long durationMilliseconds;
    private String image;
    private String mediaId;
    private List<Method> onItemSelected;

    public static class Builder extends TemplateElement.Builder {
        protected BookmarkElement bookmark;
        protected Long durationMilliseconds;
        protected String image;
        protected String mediaId;
        protected List<Method> onItemSelected;

        public MediaItemElement build() {
            MediaItemElement mediaItemElement = new MediaItemElement();
            populate(mediaItemElement);
            return mediaItemElement;
        }

        protected void populate(MediaItemElement mediaItemElement) {
            super.populate((TemplateElement) mediaItemElement);
            mediaItemElement.setImage(this.image);
            mediaItemElement.setOnItemSelected(this.onItemSelected);
            mediaItemElement.setMediaId(this.mediaId);
            mediaItemElement.setBookmark(this.bookmark);
            mediaItemElement.setDurationMilliseconds(this.durationMilliseconds);
        }

        public Builder withBookmark(BookmarkElement bookmarkElement) {
            this.bookmark = bookmarkElement;
            return this;
        }

        public Builder withDurationMilliseconds(Long l) {
            this.durationMilliseconds = l;
            return this;
        }

        public Builder withImage(String str) {
            this.image = str;
            return this;
        }

        public Builder withMediaId(String str) {
            this.mediaId = str;
            return this;
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
    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof MediaItemElement)) {
            return 1;
        }
        MediaItemElement mediaItemElement = (MediaItemElement) sOAObject;
        String image = getImage();
        String image2 = mediaItemElement.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo = image.compareTo(image2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = mediaItemElement.getOnItemSelected();
        if (onItemSelected != onItemSelected2) {
            if (onItemSelected == null) {
                return -1;
            }
            if (onItemSelected2 == null) {
                return 1;
            }
            if (onItemSelected instanceof Comparable) {
                int iCompareTo2 = ((Comparable) onItemSelected).compareTo(onItemSelected2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
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
        String mediaId = getMediaId();
        String mediaId2 = mediaItemElement.getMediaId();
        if (mediaId != mediaId2) {
            if (mediaId == null) {
                return -1;
            }
            if (mediaId2 == null) {
                return 1;
            }
            int iCompareTo3 = mediaId.compareTo(mediaId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        BookmarkElement bookmark = getBookmark();
        BookmarkElement bookmark2 = mediaItemElement.getBookmark();
        if (bookmark != bookmark2) {
            if (bookmark == null) {
                return -1;
            }
            if (bookmark2 == null) {
                return 1;
            }
            int iCompareTo4 = bookmark.compareTo(bookmark2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Long durationMilliseconds = getDurationMilliseconds();
        Long durationMilliseconds2 = mediaItemElement.getDurationMilliseconds();
        if (durationMilliseconds != durationMilliseconds2) {
            if (durationMilliseconds == null) {
                return -1;
            }
            if (durationMilliseconds2 == null) {
                return 1;
            }
            int iCompareTo5 = durationMilliseconds.compareTo(durationMilliseconds2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof MediaItemElement)) {
            return false;
        }
        MediaItemElement mediaItemElement = (MediaItemElement) obj;
        return super.equals(obj) && internalEqualityCheck(getImage(), mediaItemElement.getImage()) && internalEqualityCheck(getOnItemSelected(), mediaItemElement.getOnItemSelected()) && internalEqualityCheck(getMediaId(), mediaItemElement.getMediaId()) && internalEqualityCheck(getBookmark(), mediaItemElement.getBookmark()) && internalEqualityCheck(getDurationMilliseconds(), mediaItemElement.getDurationMilliseconds());
    }

    public BookmarkElement getBookmark() {
        return this.bookmark;
    }

    public Long getDurationMilliseconds() {
        return this.durationMilliseconds;
    }

    public String getImage() {
        return this.image;
    }

    public String getMediaId() {
        return this.mediaId;
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getImage(), getOnItemSelected(), getMediaId(), getBookmark(), getDurationMilliseconds());
    }

    public void setBookmark(BookmarkElement bookmarkElement) {
        this.bookmark = bookmarkElement;
    }

    public void setDurationMilliseconds(Long l) {
        this.durationMilliseconds = l;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setMediaId(String str) {
        this.mediaId = str;
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }
}
