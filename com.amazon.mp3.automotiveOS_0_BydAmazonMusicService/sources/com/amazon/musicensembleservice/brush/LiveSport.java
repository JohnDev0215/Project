package com.amazon.musicensembleservice.brush;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class LiveSport extends MusicEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.LiveSport");
    private Image image;
    private String programId;
    private String subtitle;
    private String target;
    private String title;
    private String type;

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
        if (!(entity instanceof LiveSport)) {
            return 1;
        }
        LiveSport liveSport = (LiveSport) entity;
        String title = getTitle();
        String title2 = liveSport.getTitle();
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
        String target = getTarget();
        String target2 = liveSport.getTarget();
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
        Image image = getImage();
        Image image2 = liveSport.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo3 = image.compareTo(image2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String programId = getProgramId();
        String programId2 = liveSport.getProgramId();
        if (programId != programId2) {
            if (programId == null) {
                return -1;
            }
            if (programId2 == null) {
                return 1;
            }
            int iCompareTo4 = programId.compareTo(programId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String subtitle = getSubtitle();
        String subtitle2 = liveSport.getSubtitle();
        if (subtitle != subtitle2) {
            if (subtitle == null) {
                return -1;
            }
            if (subtitle2 == null) {
                return 1;
            }
            int iCompareTo5 = subtitle.compareTo(subtitle2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String type = getType();
        String type2 = liveSport.getType();
        if (type != type2) {
            if (type == null) {
                return -1;
            }
            if (type2 == null) {
                return 1;
            }
            int iCompareTo6 = type.compareTo(type2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof LiveSport)) {
            return false;
        }
        LiveSport liveSport = (LiveSport) obj;
        return super.equals(obj) && internalEqualityCheck(getTitle(), liveSport.getTitle()) && internalEqualityCheck(getTarget(), liveSport.getTarget()) && internalEqualityCheck(getImage(), liveSport.getImage()) && internalEqualityCheck(getProgramId(), liveSport.getProgramId()) && internalEqualityCheck(getSubtitle(), liveSport.getSubtitle()) && internalEqualityCheck(getType(), liveSport.getType());
    }

    public Image getImage() {
        return this.image;
    }

    public String getProgramId() {
        return this.programId;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getTarget() {
        return this.target;
    }

    public String getTitle() {
        return this.title;
    }

    public String getType() {
        return this.type;
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTitle(), getTarget(), getImage(), getProgramId(), getSubtitle(), getType());
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setProgramId(String str) {
        this.programId = str;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public void setTarget(String str) {
        this.target = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}
