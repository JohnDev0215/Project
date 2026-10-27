package com.amazon.music.platform.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class LiveSportsCompetition extends LiveSportsDocument {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.LiveSportsCompetition");
    private String competitionId;
    private String competitionLink;
    private Map<String, List<LiveSportsImageElement>> liveSportsImages;
    private String name;

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
        if (!(document instanceof LiveSportsCompetition)) {
            return 1;
        }
        LiveSportsCompetition liveSportsCompetition = (LiveSportsCompetition) document;
        String competitionLink = getCompetitionLink();
        String competitionLink2 = liveSportsCompetition.getCompetitionLink();
        if (competitionLink != competitionLink2) {
            if (competitionLink == null) {
                return -1;
            }
            if (competitionLink2 == null) {
                return 1;
            }
            int iCompareTo = competitionLink.compareTo(competitionLink2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Map<String, List<LiveSportsImageElement>> liveSportsImages = getLiveSportsImages();
        Map<String, List<LiveSportsImageElement>> liveSportsImages2 = liveSportsCompetition.getLiveSportsImages();
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
        String competitionId = getCompetitionId();
        String competitionId2 = liveSportsCompetition.getCompetitionId();
        if (competitionId != competitionId2) {
            if (competitionId == null) {
                return -1;
            }
            if (competitionId2 == null) {
                return 1;
            }
            int iCompareTo3 = competitionId.compareTo(competitionId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String name = getName();
        String name2 = liveSportsCompetition.getName();
        if (name != name2) {
            if (name == null) {
                return -1;
            }
            if (name2 == null) {
                return 1;
            }
            int iCompareTo4 = name.compareTo(name2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.LiveSportsDocument, com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof LiveSportsCompetition)) {
            return false;
        }
        LiveSportsCompetition liveSportsCompetition = (LiveSportsCompetition) obj;
        return super.equals(obj) && internalEqualityCheck(getCompetitionLink(), liveSportsCompetition.getCompetitionLink()) && internalEqualityCheck(getLiveSportsImages(), liveSportsCompetition.getLiveSportsImages()) && internalEqualityCheck(getCompetitionId(), liveSportsCompetition.getCompetitionId()) && internalEqualityCheck(getName(), liveSportsCompetition.getName());
    }

    public String getCompetitionId() {
        return this.competitionId;
    }

    public String getCompetitionLink() {
        return this.competitionLink;
    }

    public Map<String, List<LiveSportsImageElement>> getLiveSportsImages() {
        return this.liveSportsImages;
    }

    public String getName() {
        return this.name;
    }

    @Override // com.amazon.music.platform.model.LiveSportsDocument, com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getCompetitionLink(), getLiveSportsImages(), getCompetitionId(), getName());
    }

    public void setCompetitionId(String str) {
        this.competitionId = str;
    }

    public void setCompetitionLink(String str) {
        this.competitionLink = str;
    }

    public void setLiveSportsImages(Map<String, List<LiveSportsImageElement>> map) {
        this.liveSportsImages = map;
    }

    public void setName(String str) {
        this.name = str;
    }
}
