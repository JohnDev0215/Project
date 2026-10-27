package Podcast.Touch.CardNowPlayingTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FollowButtonElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.FollowButtonElement");
    private String followText;
    private String id;
    private List<Method> onFollow;
    private List<Method> onUnfollow;
    private String unfollowText;

    public static class Builder extends TemplateElement.Builder {
        protected String followText;
        protected String id;
        protected List<Method> onFollow;
        protected List<Method> onUnfollow;
        protected String unfollowText;

        public FollowButtonElement build() {
            FollowButtonElement followButtonElement = new FollowButtonElement();
            populate(followButtonElement);
            return followButtonElement;
        }

        protected void populate(FollowButtonElement followButtonElement) {
            super.populate((TemplateElement) followButtonElement);
            followButtonElement.setOnFollow(this.onFollow);
            followButtonElement.setId(this.id);
            followButtonElement.setFollowText(this.followText);
            followButtonElement.setUnfollowText(this.unfollowText);
            followButtonElement.setOnUnfollow(this.onUnfollow);
        }

        public Builder withFollowText(String str) {
            this.followText = str;
            return this;
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }

        public Builder withOnFollow(List<Method> list) {
            this.onFollow = list;
            return this;
        }

        public Builder withOnUnfollow(List<Method> list) {
            this.onUnfollow = list;
            return this;
        }

        public Builder withUnfollowText(String str) {
            this.unfollowText = str;
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
        if (!(sOAObject instanceof FollowButtonElement)) {
            return 1;
        }
        FollowButtonElement followButtonElement = (FollowButtonElement) sOAObject;
        List<Method> onFollow = getOnFollow();
        List<Method> onFollow2 = followButtonElement.getOnFollow();
        if (onFollow != onFollow2) {
            if (onFollow == null) {
                return -1;
            }
            if (onFollow2 == null) {
                return 1;
            }
            if (onFollow instanceof Comparable) {
                int iCompareTo = ((Comparable) onFollow).compareTo(onFollow2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!onFollow.equals(onFollow2)) {
                int iHashCode = onFollow.hashCode();
                int iHashCode2 = onFollow2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String id = getId();
        String id2 = followButtonElement.getId();
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
        String followText = getFollowText();
        String followText2 = followButtonElement.getFollowText();
        if (followText != followText2) {
            if (followText == null) {
                return -1;
            }
            if (followText2 == null) {
                return 1;
            }
            int iCompareTo3 = followText.compareTo(followText2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String unfollowText = getUnfollowText();
        String unfollowText2 = followButtonElement.getUnfollowText();
        if (unfollowText != unfollowText2) {
            if (unfollowText == null) {
                return -1;
            }
            if (unfollowText2 == null) {
                return 1;
            }
            int iCompareTo4 = unfollowText.compareTo(unfollowText2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        List<Method> onUnfollow = getOnUnfollow();
        List<Method> onUnfollow2 = followButtonElement.getOnUnfollow();
        if (onUnfollow != onUnfollow2) {
            if (onUnfollow == null) {
                return -1;
            }
            if (onUnfollow2 == null) {
                return 1;
            }
            if (onUnfollow instanceof Comparable) {
                int iCompareTo5 = ((Comparable) onUnfollow).compareTo(onUnfollow2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!onUnfollow.equals(onUnfollow2)) {
                int iHashCode3 = onUnfollow.hashCode();
                int iHashCode4 = onUnfollow2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof FollowButtonElement)) {
            return false;
        }
        FollowButtonElement followButtonElement = (FollowButtonElement) obj;
        return super.equals(obj) && internalEqualityCheck(getOnFollow(), followButtonElement.getOnFollow()) && internalEqualityCheck(getId(), followButtonElement.getId()) && internalEqualityCheck(getFollowText(), followButtonElement.getFollowText()) && internalEqualityCheck(getUnfollowText(), followButtonElement.getUnfollowText()) && internalEqualityCheck(getOnUnfollow(), followButtonElement.getOnUnfollow());
    }

    public String getFollowText() {
        return this.followText;
    }

    public String getId() {
        return this.id;
    }

    public List<Method> getOnFollow() {
        return this.onFollow;
    }

    public List<Method> getOnUnfollow() {
        return this.onUnfollow;
    }

    public String getUnfollowText() {
        return this.unfollowText;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnFollow(), getId(), getFollowText(), getUnfollowText(), getOnUnfollow());
    }

    public void setFollowText(String str) {
        this.followText = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setOnFollow(List<Method> list) {
        this.onFollow = list;
    }

    public void setOnUnfollow(List<Method> list) {
        this.onUnfollow = list;
    }

    public void setUnfollowText(String str) {
        this.unfollowText = str;
    }
}
