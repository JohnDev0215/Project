package Podcast.PlaybackInterface.v1_0;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SetMediaMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.PlaybackInterface.v1_0.SetMediaMethod");
    private MediaActionsElement actions;
    private MediaControlsElement controls;
    private EpisodeOptionsElement episodeOptions;
    private MediaEventsElement events;
    private Boolean ignorable;
    private String mediaChangeReason;
    private MediaMetadataElement metadata;
    private List<Method> onRestore;
    private List<Method> onRestoreAndStart;

    public static class Builder extends Method.Builder {
        protected MediaActionsElement actions;
        protected MediaControlsElement controls;
        protected EpisodeOptionsElement episodeOptions;
        protected MediaEventsElement events;
        protected Boolean ignorable;
        protected String mediaChangeReason;
        protected MediaMetadataElement metadata;
        protected List<Method> onRestore;
        protected List<Method> onRestoreAndStart;

        public SetMediaMethod build() {
            SetMediaMethod setMediaMethod = new SetMediaMethod();
            populate(setMediaMethod);
            return setMediaMethod;
        }

        protected void populate(SetMediaMethod setMediaMethod) {
            super.populate((Method) setMediaMethod);
            setMediaMethod.setActions(this.actions);
            setMediaMethod.setIgnorable(this.ignorable);
            setMediaMethod.setMetadata(this.metadata);
            setMediaMethod.setEvents(this.events);
            setMediaMethod.setControls(this.controls);
            setMediaMethod.setOnRestore(this.onRestore);
            setMediaMethod.setEpisodeOptions(this.episodeOptions);
            setMediaMethod.setOnRestoreAndStart(this.onRestoreAndStart);
            setMediaMethod.setMediaChangeReason(this.mediaChangeReason);
        }

        public Builder withActions(MediaActionsElement mediaActionsElement) {
            this.actions = mediaActionsElement;
            return this;
        }

        public Builder withControls(MediaControlsElement mediaControlsElement) {
            this.controls = mediaControlsElement;
            return this;
        }

        public Builder withEpisodeOptions(EpisodeOptionsElement episodeOptionsElement) {
            this.episodeOptions = episodeOptionsElement;
            return this;
        }

        public Builder withEvents(MediaEventsElement mediaEventsElement) {
            this.events = mediaEventsElement;
            return this;
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        public Builder withIgnorable(Boolean bool) {
            this.ignorable = bool;
            return this;
        }

        public Builder withMediaChangeReason(String str) {
            this.mediaChangeReason = str;
            return this;
        }

        public Builder withMetadata(MediaMetadataElement mediaMetadataElement) {
            this.metadata = mediaMetadataElement;
            return this;
        }

        public Builder withOnRestore(List<Method> list) {
            this.onRestore = list;
            return this;
        }

        public Builder withOnRestoreAndStart(List<Method> list) {
            this.onRestoreAndStart = list;
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
        if (!(sOAObject instanceof SetMediaMethod)) {
            return 1;
        }
        SetMediaMethod setMediaMethod = (SetMediaMethod) sOAObject;
        MediaActionsElement actions = getActions();
        MediaActionsElement actions2 = setMediaMethod.getActions();
        if (actions != actions2) {
            if (actions == null) {
                return -1;
            }
            if (actions2 == null) {
                return 1;
            }
            int iCompareTo = actions.compareTo(actions2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsIgnorable = isIgnorable();
        Boolean boolIsIgnorable2 = setMediaMethod.isIgnorable();
        if (boolIsIgnorable != boolIsIgnorable2) {
            if (boolIsIgnorable == null) {
                return -1;
            }
            if (boolIsIgnorable2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsIgnorable.compareTo(boolIsIgnorable2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        MediaMetadataElement metadata = getMetadata();
        MediaMetadataElement metadata2 = setMediaMethod.getMetadata();
        if (metadata != metadata2) {
            if (metadata == null) {
                return -1;
            }
            if (metadata2 == null) {
                return 1;
            }
            int iCompareTo3 = metadata.compareTo(metadata2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        MediaEventsElement events = getEvents();
        MediaEventsElement events2 = setMediaMethod.getEvents();
        if (events != events2) {
            if (events == null) {
                return -1;
            }
            if (events2 == null) {
                return 1;
            }
            int iCompareTo4 = events.compareTo(events2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        MediaControlsElement controls = getControls();
        MediaControlsElement controls2 = setMediaMethod.getControls();
        if (controls != controls2) {
            if (controls == null) {
                return -1;
            }
            if (controls2 == null) {
                return 1;
            }
            int iCompareTo5 = controls.compareTo(controls2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        List<Method> onRestore = getOnRestore();
        List<Method> onRestore2 = setMediaMethod.getOnRestore();
        if (onRestore != onRestore2) {
            if (onRestore == null) {
                return -1;
            }
            if (onRestore2 == null) {
                return 1;
            }
            if (onRestore instanceof Comparable) {
                int iCompareTo6 = ((Comparable) onRestore).compareTo(onRestore2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!onRestore.equals(onRestore2)) {
                int iHashCode = onRestore.hashCode();
                int iHashCode2 = onRestore2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        EpisodeOptionsElement episodeOptions = getEpisodeOptions();
        EpisodeOptionsElement episodeOptions2 = setMediaMethod.getEpisodeOptions();
        if (episodeOptions != episodeOptions2) {
            if (episodeOptions == null) {
                return -1;
            }
            if (episodeOptions2 == null) {
                return 1;
            }
            int iCompareTo7 = episodeOptions.compareTo(episodeOptions2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        List<Method> onRestoreAndStart = getOnRestoreAndStart();
        List<Method> onRestoreAndStart2 = setMediaMethod.getOnRestoreAndStart();
        if (onRestoreAndStart != onRestoreAndStart2) {
            if (onRestoreAndStart == null) {
                return -1;
            }
            if (onRestoreAndStart2 == null) {
                return 1;
            }
            if (onRestoreAndStart instanceof Comparable) {
                int iCompareTo8 = ((Comparable) onRestoreAndStart).compareTo(onRestoreAndStart2);
                if (iCompareTo8 != 0) {
                    return iCompareTo8;
                }
            } else if (!onRestoreAndStart.equals(onRestoreAndStart2)) {
                int iHashCode3 = onRestoreAndStart.hashCode();
                int iHashCode4 = onRestoreAndStart2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String mediaChangeReason = getMediaChangeReason();
        String mediaChangeReason2 = setMediaMethod.getMediaChangeReason();
        if (mediaChangeReason != mediaChangeReason2) {
            if (mediaChangeReason == null) {
                return -1;
            }
            if (mediaChangeReason2 == null) {
                return 1;
            }
            int iCompareTo9 = mediaChangeReason.compareTo(mediaChangeReason2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof SetMediaMethod)) {
            return false;
        }
        SetMediaMethod setMediaMethod = (SetMediaMethod) obj;
        return super.equals(obj) && internalEqualityCheck(getActions(), setMediaMethod.getActions()) && internalEqualityCheck(isIgnorable(), setMediaMethod.isIgnorable()) && internalEqualityCheck(getMetadata(), setMediaMethod.getMetadata()) && internalEqualityCheck(getEvents(), setMediaMethod.getEvents()) && internalEqualityCheck(getControls(), setMediaMethod.getControls()) && internalEqualityCheck(getOnRestore(), setMediaMethod.getOnRestore()) && internalEqualityCheck(getEpisodeOptions(), setMediaMethod.getEpisodeOptions()) && internalEqualityCheck(getOnRestoreAndStart(), setMediaMethod.getOnRestoreAndStart()) && internalEqualityCheck(getMediaChangeReason(), setMediaMethod.getMediaChangeReason());
    }

    public MediaActionsElement getActions() {
        return this.actions;
    }

    public MediaControlsElement getControls() {
        return this.controls;
    }

    public EpisodeOptionsElement getEpisodeOptions() {
        return this.episodeOptions;
    }

    public MediaEventsElement getEvents() {
        return this.events;
    }

    public String getMediaChangeReason() {
        return this.mediaChangeReason;
    }

    public MediaMetadataElement getMetadata() {
        return this.metadata;
    }

    public List<Method> getOnRestore() {
        return this.onRestore;
    }

    public List<Method> getOnRestoreAndStart() {
        return this.onRestoreAndStart;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getActions(), isIgnorable(), getMetadata(), getEvents(), getControls(), getOnRestore(), getEpisodeOptions(), getOnRestoreAndStart(), getMediaChangeReason());
    }

    public Boolean isIgnorable() {
        return this.ignorable;
    }

    public void setActions(MediaActionsElement mediaActionsElement) {
        this.actions = mediaActionsElement;
    }

    public void setControls(MediaControlsElement mediaControlsElement) {
        this.controls = mediaControlsElement;
    }

    public void setEpisodeOptions(EpisodeOptionsElement episodeOptionsElement) {
        this.episodeOptions = episodeOptionsElement;
    }

    public void setEvents(MediaEventsElement mediaEventsElement) {
        this.events = mediaEventsElement;
    }

    public void setIgnorable(Boolean bool) {
        this.ignorable = bool;
    }

    public void setMediaChangeReason(String str) {
        this.mediaChangeReason = str;
    }

    public void setMetadata(MediaMetadataElement mediaMetadataElement) {
        this.metadata = mediaMetadataElement;
    }

    public void setOnRestore(List<Method> list) {
        this.onRestore = list;
    }

    public void setOnRestoreAndStart(List<Method> list) {
        this.onRestoreAndStart = list;
    }
}
