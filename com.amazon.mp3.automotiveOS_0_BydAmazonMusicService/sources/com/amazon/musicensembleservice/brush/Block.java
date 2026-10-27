package com.amazon.musicensembleservice.brush;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Block implements Comparable<Block> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.Block");
    private String blockRef;
    private String caption;
    private Content content;
    private List<Image> images;
    private String moreText;
    private String renderingType;
    private String subtitle;
    private List<String> tags;
    private String title;
    private String uri;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Block block) {
        if (block == null) {
            return -1;
        }
        if (block == this) {
            return 0;
        }
        String moreText = getMoreText();
        String moreText2 = block.getMoreText();
        if (moreText != moreText2) {
            if (moreText == null) {
                return -1;
            }
            if (moreText2 == null) {
                return 1;
            }
            int iCompareTo = moreText.compareTo(moreText2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String caption = getCaption();
        String caption2 = block.getCaption();
        if (caption != caption2) {
            if (caption == null) {
                return -1;
            }
            if (caption2 == null) {
                return 1;
            }
            int iCompareTo2 = caption.compareTo(caption2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Content content = getContent();
        Content content2 = block.getContent();
        if (content != content2) {
            if (content == null) {
                return -1;
            }
            if (content2 == null) {
                return 1;
            }
            int iCompareTo3 = content.compareTo(content2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String renderingType = getRenderingType();
        String renderingType2 = block.getRenderingType();
        if (renderingType != renderingType2) {
            if (renderingType == null) {
                return -1;
            }
            if (renderingType2 == null) {
                return 1;
            }
            int iCompareTo4 = renderingType.compareTo(renderingType2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        List<String> tags = getTags();
        List<String> tags2 = block.getTags();
        if (tags != tags2) {
            if (tags == null) {
                return -1;
            }
            if (tags2 == null) {
                return 1;
            }
            if (tags instanceof Comparable) {
                int iCompareTo5 = ((Comparable) tags).compareTo(tags2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!tags.equals(tags2)) {
                int iHashCode = tags.hashCode();
                int iHashCode2 = tags2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String subtitle = getSubtitle();
        String subtitle2 = block.getSubtitle();
        if (subtitle != subtitle2) {
            if (subtitle == null) {
                return -1;
            }
            if (subtitle2 == null) {
                return 1;
            }
            int iCompareTo6 = subtitle.compareTo(subtitle2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String title = getTitle();
        String title2 = block.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo7 = title.compareTo(title2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String blockRef = getBlockRef();
        String blockRef2 = block.getBlockRef();
        if (blockRef != blockRef2) {
            if (blockRef == null) {
                return -1;
            }
            if (blockRef2 == null) {
                return 1;
            }
            int iCompareTo8 = blockRef.compareTo(blockRef2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        List<Image> images = getImages();
        List<Image> images2 = block.getImages();
        if (images != images2) {
            if (images == null) {
                return -1;
            }
            if (images2 == null) {
                return 1;
            }
            if (images instanceof Comparable) {
                int iCompareTo9 = ((Comparable) images).compareTo(images2);
                if (iCompareTo9 != 0) {
                    return iCompareTo9;
                }
            } else if (!images.equals(images2)) {
                int iHashCode3 = images.hashCode();
                int iHashCode4 = images2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String uri = getUri();
        String uri2 = block.getUri();
        if (uri != uri2) {
            if (uri == null) {
                return -1;
            }
            if (uri2 == null) {
                return 1;
            }
            int iCompareTo10 = uri.compareTo(uri2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Block)) {
            return false;
        }
        Block block = (Block) obj;
        return internalEqualityCheck(getMoreText(), block.getMoreText()) && internalEqualityCheck(getCaption(), block.getCaption()) && internalEqualityCheck(getContent(), block.getContent()) && internalEqualityCheck(getRenderingType(), block.getRenderingType()) && internalEqualityCheck(getTags(), block.getTags()) && internalEqualityCheck(getSubtitle(), block.getSubtitle()) && internalEqualityCheck(getTitle(), block.getTitle()) && internalEqualityCheck(getBlockRef(), block.getBlockRef()) && internalEqualityCheck(getImages(), block.getImages()) && internalEqualityCheck(getUri(), block.getUri());
    }

    public String getBlockRef() {
        return this.blockRef;
    }

    public String getCaption() {
        return this.caption;
    }

    public Content getContent() {
        return this.content;
    }

    public List<Image> getImages() {
        return this.images;
    }

    public String getMoreText() {
        return this.moreText;
    }

    public String getRenderingType() {
        return this.renderingType;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUri() {
        return this.uri;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getMoreText(), getCaption(), getContent(), getRenderingType(), getTags(), getSubtitle(), getTitle(), getBlockRef(), getImages(), getUri());
    }

    public void setBlockRef(String str) {
        this.blockRef = str;
    }

    public void setCaption(String str) {
        this.caption = str;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public void setImages(List<Image> list) {
        this.images = list;
    }

    public void setMoreText(String str) {
        this.moreText = str;
    }

    public void setRenderingType(String str) {
        this.renderingType = str;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public void setTags(List<String> list) {
        this.tags = list;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setUri(String str) {
        this.uri = str;
    }
}
