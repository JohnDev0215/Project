package com.amazon.musicensembleservice.brush;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Genre extends MusicEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.Genre");
    private String browseId;
    private List<String> contentTiers;
    private Image image;
    private Boolean selected;
    private String title;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Entity entity) {
        if (entity == null) {
            return -1;
        }
        if (entity == this) {
            return 0;
        }
        if (!(entity instanceof Genre)) {
            return 1;
        }
        Genre genre = (Genre) entity;
        Image image = getImage();
        Image image2 = genre.getImage();
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
        Boolean boolIsSelected = isSelected();
        Boolean boolIsSelected2 = genre.isSelected();
        if (boolIsSelected != boolIsSelected2) {
            if (boolIsSelected == null) {
                return -1;
            }
            if (boolIsSelected2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsSelected.compareTo(boolIsSelected2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<String> contentTiers = getContentTiers();
        List<String> contentTiers2 = genre.getContentTiers();
        if (contentTiers != contentTiers2) {
            if (contentTiers == null) {
                return -1;
            }
            if (contentTiers2 == null) {
                return 1;
            }
            if (contentTiers instanceof Comparable) {
                int iCompareTo3 = ((Comparable) contentTiers).compareTo(contentTiers2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!contentTiers.equals(contentTiers2)) {
                int iHashCode = contentTiers.hashCode();
                int iHashCode2 = contentTiers2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String browseId = getBrowseId();
        String browseId2 = genre.getBrowseId();
        if (browseId != browseId2) {
            if (browseId == null) {
                return -1;
            }
            if (browseId2 == null) {
                return 1;
            }
            int iCompareTo4 = browseId.compareTo(browseId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String title = getTitle();
        String title2 = genre.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo5 = title.compareTo(title2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof Genre)) {
            return false;
        }
        Genre genre = (Genre) obj;
        return super.equals(obj) && internalEqualityCheck(getImage(), genre.getImage()) && internalEqualityCheck(isSelected(), genre.isSelected()) && internalEqualityCheck(getContentTiers(), genre.getContentTiers()) && internalEqualityCheck(getBrowseId(), genre.getBrowseId()) && internalEqualityCheck(getTitle(), genre.getTitle());
    }

    public String getBrowseId() {
        return this.browseId;
    }

    public List<String> getContentTiers() {
        return this.contentTiers;
    }

    public Image getImage() {
        return this.image;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getImage(), isSelected(), getContentTiers(), getBrowseId(), getTitle());
    }

    public Boolean isSelected() {
        return this.selected;
    }

    public void setBrowseId(String str) {
        this.browseId = str;
    }

    public void setContentTiers(List<String> list) {
        this.contentTiers = list;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setSelected(Boolean bool) {
        this.selected = bool;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
