package com.amazon.musicensembleservice.brush;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class Commentary extends Entity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.Commentary");
    private String displayText;
    private String id;
    private Image image;
    private String playbackUrl;
    private String state;
    private String title;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicensembleservice.brush.Entity, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Entity entity) {
        if (entity == null) {
            return -1;
        }
        if (entity == this) {
            return 0;
        }
        if (!(entity instanceof Commentary)) {
            return 1;
        }
        Commentary commentary = (Commentary) entity;
        String id = getId();
        String id2 = commentary.getId();
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
        String state = getState();
        String state2 = commentary.getState();
        if (state != state2) {
            if (state == null) {
                return -1;
            }
            if (state2 == null) {
                return 1;
            }
            int iCompareTo2 = state.compareTo(state2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String playbackUrl = getPlaybackUrl();
        String playbackUrl2 = commentary.getPlaybackUrl();
        if (playbackUrl != playbackUrl2) {
            if (playbackUrl == null) {
                return -1;
            }
            if (playbackUrl2 == null) {
                return 1;
            }
            int iCompareTo3 = playbackUrl.compareTo(playbackUrl2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String displayText = getDisplayText();
        String displayText2 = commentary.getDisplayText();
        if (displayText != displayText2) {
            if (displayText == null) {
                return -1;
            }
            if (displayText2 == null) {
                return 1;
            }
            int iCompareTo4 = displayText.compareTo(displayText2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Image image = getImage();
        Image image2 = commentary.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo5 = image.compareTo(image2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String title = getTitle();
        String title2 = commentary.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo6 = title.compareTo(title2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof Commentary)) {
            return false;
        }
        Commentary commentary = (Commentary) obj;
        return super.equals(obj) && internalEqualityCheck(getId(), commentary.getId()) && internalEqualityCheck(getState(), commentary.getState()) && internalEqualityCheck(getPlaybackUrl(), commentary.getPlaybackUrl()) && internalEqualityCheck(getDisplayText(), commentary.getDisplayText()) && internalEqualityCheck(getImage(), commentary.getImage()) && internalEqualityCheck(getTitle(), commentary.getTitle());
    }

    public String getDisplayText() {
        return this.displayText;
    }

    public String getId() {
        return this.id;
    }

    public Image getImage() {
        return this.image;
    }

    public String getPlaybackUrl() {
        return this.playbackUrl;
    }

    public String getState() {
        return this.state;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getId(), getState(), getPlaybackUrl(), getDisplayText(), getImage(), getTitle());
    }

    public void setDisplayText(String str) {
        this.displayText = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setPlaybackUrl(String str) {
        this.playbackUrl = str;
    }

    public void setState(String str) {
        this.state = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
