package Podcast.PlaybackInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PlayLocalEpisodesMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.PlaybackInterface.v1_0.PlayLocalEpisodesMethod");
    private String episodeId;
    private List<Method> onLocalEpisodeFound;
    private List<Method> onLocalEpisodeNotFound;

    public static class Builder extends Method.Builder {
        protected String episodeId;
        protected List<Method> onLocalEpisodeFound;
        protected List<Method> onLocalEpisodeNotFound;

        public PlayLocalEpisodesMethod build() {
            PlayLocalEpisodesMethod playLocalEpisodesMethod = new PlayLocalEpisodesMethod();
            populate(playLocalEpisodesMethod);
            return playLocalEpisodesMethod;
        }

        protected void populate(PlayLocalEpisodesMethod playLocalEpisodesMethod) {
            super.populate((Method) playLocalEpisodesMethod);
            playLocalEpisodesMethod.setOnLocalEpisodeNotFound(this.onLocalEpisodeNotFound);
            playLocalEpisodesMethod.setEpisodeId(this.episodeId);
            playLocalEpisodesMethod.setOnLocalEpisodeFound(this.onLocalEpisodeFound);
        }

        public Builder withEpisodeId(String str) {
            this.episodeId = str;
            return this;
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        public Builder withOnLocalEpisodeFound(List<Method> list) {
            this.onLocalEpisodeFound = list;
            return this;
        }

        public Builder withOnLocalEpisodeNotFound(List<Method> list) {
            this.onLocalEpisodeNotFound = list;
            return this;
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withQueue(Queue queue) {
            super.withQueue(queue);
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
    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof PlayLocalEpisodesMethod)) {
            return 1;
        }
        PlayLocalEpisodesMethod playLocalEpisodesMethod = (PlayLocalEpisodesMethod) sOAObject;
        List<Method> onLocalEpisodeNotFound = getOnLocalEpisodeNotFound();
        List<Method> onLocalEpisodeNotFound2 = playLocalEpisodesMethod.getOnLocalEpisodeNotFound();
        if (onLocalEpisodeNotFound != onLocalEpisodeNotFound2) {
            if (onLocalEpisodeNotFound == null) {
                return -1;
            }
            if (onLocalEpisodeNotFound2 == null) {
                return 1;
            }
            if (onLocalEpisodeNotFound instanceof Comparable) {
                int iCompareTo = ((Comparable) onLocalEpisodeNotFound).compareTo(onLocalEpisodeNotFound2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!onLocalEpisodeNotFound.equals(onLocalEpisodeNotFound2)) {
                int iHashCode = onLocalEpisodeNotFound.hashCode();
                int iHashCode2 = onLocalEpisodeNotFound2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String episodeId = getEpisodeId();
        String episodeId2 = playLocalEpisodesMethod.getEpisodeId();
        if (episodeId != episodeId2) {
            if (episodeId == null) {
                return -1;
            }
            if (episodeId2 == null) {
                return 1;
            }
            int iCompareTo2 = episodeId.compareTo(episodeId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<Method> onLocalEpisodeFound = getOnLocalEpisodeFound();
        List<Method> onLocalEpisodeFound2 = playLocalEpisodesMethod.getOnLocalEpisodeFound();
        if (onLocalEpisodeFound != onLocalEpisodeFound2) {
            if (onLocalEpisodeFound == null) {
                return -1;
            }
            if (onLocalEpisodeFound2 == null) {
                return 1;
            }
            if (onLocalEpisodeFound instanceof Comparable) {
                int iCompareTo3 = ((Comparable) onLocalEpisodeFound).compareTo(onLocalEpisodeFound2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!onLocalEpisodeFound.equals(onLocalEpisodeFound2)) {
                int iHashCode3 = onLocalEpisodeFound.hashCode();
                int iHashCode4 = onLocalEpisodeFound2.hashCode();
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

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof PlayLocalEpisodesMethod)) {
            return false;
        }
        PlayLocalEpisodesMethod playLocalEpisodesMethod = (PlayLocalEpisodesMethod) obj;
        return super.equals(obj) && internalEqualityCheck(getOnLocalEpisodeNotFound(), playLocalEpisodesMethod.getOnLocalEpisodeNotFound()) && internalEqualityCheck(getEpisodeId(), playLocalEpisodesMethod.getEpisodeId()) && internalEqualityCheck(getOnLocalEpisodeFound(), playLocalEpisodesMethod.getOnLocalEpisodeFound());
    }

    public String getEpisodeId() {
        return this.episodeId;
    }

    public List<Method> getOnLocalEpisodeFound() {
        return this.onLocalEpisodeFound;
    }

    public List<Method> getOnLocalEpisodeNotFound() {
        return this.onLocalEpisodeNotFound;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnLocalEpisodeNotFound(), getEpisodeId(), getOnLocalEpisodeFound());
    }

    public void setEpisodeId(String str) {
        this.episodeId = str;
    }

    public void setOnLocalEpisodeFound(List<Method> list) {
        this.onLocalEpisodeFound = list;
    }

    public void setOnLocalEpisodeNotFound(List<Method> list) {
        this.onLocalEpisodeNotFound = list;
    }
}
