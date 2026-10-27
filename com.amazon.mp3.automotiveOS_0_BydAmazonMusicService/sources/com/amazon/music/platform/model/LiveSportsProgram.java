package com.amazon.music.platform.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class LiveSportsProgram extends LiveSportsDocument {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.LiveSportsProgram");
    private List<String> contentProfiles;
    private Map<String, List<LiveSportsImageElement>> liveSportsImages;
    private String programId;
    private String programLink;
    private String programType;
    private Date startTime;
    private String title;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.platform.model.LiveSportsDocument, com.amazon.music.platform.model.Document, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Document document) {
        if (document == null) {
            return -1;
        }
        if (document == this) {
            return 0;
        }
        if (!(document instanceof LiveSportsProgram)) {
            return 1;
        }
        LiveSportsProgram liveSportsProgram = (LiveSportsProgram) document;
        String programType = getProgramType();
        String programType2 = liveSportsProgram.getProgramType();
        if (programType != programType2) {
            if (programType == null) {
                return -1;
            }
            if (programType2 == null) {
                return 1;
            }
            int iCompareTo = programType.compareTo(programType2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Map<String, List<LiveSportsImageElement>> liveSportsImages = getLiveSportsImages();
        Map<String, List<LiveSportsImageElement>> liveSportsImages2 = liveSportsProgram.getLiveSportsImages();
        if (liveSportsImages != liveSportsImages2) {
            if (liveSportsImages == null) {
                return -1;
            }
            if (liveSportsImages2 == null) {
                return 1;
            }
            if (liveSportsImages instanceof Comparable) {
                int iCompareTo2 = ((Comparable) liveSportsImages).compareTo(liveSportsImages2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!liveSportsImages.equals(liveSportsImages2)) {
                int iHashCode = liveSportsImages.hashCode();
                int iHashCode2 = liveSportsImages2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String programLink = getProgramLink();
        String programLink2 = liveSportsProgram.getProgramLink();
        if (programLink != programLink2) {
            if (programLink == null) {
                return -1;
            }
            if (programLink2 == null) {
                return 1;
            }
            int iCompareTo3 = programLink.compareTo(programLink2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Date startTime = getStartTime();
        Date startTime2 = liveSportsProgram.getStartTime();
        if (startTime != startTime2) {
            if (startTime == null) {
                return -1;
            }
            if (startTime2 == null) {
                return 1;
            }
            int iCompareTo4 = startTime.compareTo(startTime2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String title = getTitle();
        String title2 = liveSportsProgram.getTitle();
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
        String programId = getProgramId();
        String programId2 = liveSportsProgram.getProgramId();
        if (programId != programId2) {
            if (programId == null) {
                return -1;
            }
            if (programId2 == null) {
                return 1;
            }
            int iCompareTo6 = programId.compareTo(programId2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        List<String> contentProfiles = getContentProfiles();
        List<String> contentProfiles2 = liveSportsProgram.getContentProfiles();
        if (contentProfiles != contentProfiles2) {
            if (contentProfiles == null) {
                return -1;
            }
            if (contentProfiles2 == null) {
                return 1;
            }
            if (contentProfiles instanceof Comparable) {
                int iCompareTo7 = ((Comparable) contentProfiles).compareTo(contentProfiles2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!contentProfiles.equals(contentProfiles2)) {
                int iHashCode3 = contentProfiles.hashCode();
                int iHashCode4 = contentProfiles2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.LiveSportsDocument, com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof LiveSportsProgram)) {
            return false;
        }
        LiveSportsProgram liveSportsProgram = (LiveSportsProgram) obj;
        return super.equals(obj) && internalEqualityCheck(getProgramType(), liveSportsProgram.getProgramType()) && internalEqualityCheck(getLiveSportsImages(), liveSportsProgram.getLiveSportsImages()) && internalEqualityCheck(getProgramLink(), liveSportsProgram.getProgramLink()) && internalEqualityCheck(getStartTime(), liveSportsProgram.getStartTime()) && internalEqualityCheck(getTitle(), liveSportsProgram.getTitle()) && internalEqualityCheck(getProgramId(), liveSportsProgram.getProgramId()) && internalEqualityCheck(getContentProfiles(), liveSportsProgram.getContentProfiles());
    }

    public List<String> getContentProfiles() {
        return this.contentProfiles;
    }

    public Map<String, List<LiveSportsImageElement>> getLiveSportsImages() {
        return this.liveSportsImages;
    }

    public String getProgramId() {
        return this.programId;
    }

    public String getProgramLink() {
        return this.programLink;
    }

    public String getProgramType() {
        return this.programType;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.music.platform.model.LiveSportsDocument, com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getProgramType(), getLiveSportsImages(), getProgramLink(), getStartTime(), getTitle(), getProgramId(), getContentProfiles());
    }

    public void setContentProfiles(List<String> list) {
        this.contentProfiles = list;
    }

    public void setLiveSportsImages(Map<String, List<LiveSportsImageElement>> map) {
        this.liveSportsImages = map;
    }

    public void setProgramId(String str) {
        this.programId = str;
    }

    public void setProgramLink(String str) {
        this.programLink = str;
    }

    public void setProgramType(String str) {
        this.programType = str;
    }

    public void setStartTime(Date date) {
        this.startTime = date;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
