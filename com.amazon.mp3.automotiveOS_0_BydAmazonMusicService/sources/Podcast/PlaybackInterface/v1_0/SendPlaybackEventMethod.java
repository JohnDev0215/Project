package Podcast.PlaybackInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class SendPlaybackEventMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.PlaybackInterface.v1_0.SendPlaybackEventMethod");
    private String playbackEvent;

    public static class Builder extends Method.Builder {
        protected String playbackEvent;

        public SendPlaybackEventMethod build() {
            SendPlaybackEventMethod sendPlaybackEventMethod = new SendPlaybackEventMethod();
            populate(sendPlaybackEventMethod);
            return sendPlaybackEventMethod;
        }

        protected void populate(SendPlaybackEventMethod sendPlaybackEventMethod) {
            super.populate((Method) sendPlaybackEventMethod);
            sendPlaybackEventMethod.setPlaybackEvent(this.playbackEvent);
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        public Builder withPlaybackEvent(String str) {
            this.playbackEvent = str;
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
        if (!(sOAObject instanceof SendPlaybackEventMethod)) {
            return 1;
        }
        String playbackEvent = getPlaybackEvent();
        String playbackEvent2 = ((SendPlaybackEventMethod) sOAObject).getPlaybackEvent();
        if (playbackEvent != playbackEvent2) {
            if (playbackEvent == null) {
                return -1;
            }
            if (playbackEvent2 == null) {
                return 1;
            }
            int iCompareTo = playbackEvent.compareTo(playbackEvent2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof SendPlaybackEventMethod) {
            return super.equals(obj) && internalEqualityCheck(getPlaybackEvent(), ((SendPlaybackEventMethod) obj).getPlaybackEvent());
        }
        return false;
    }

    public String getPlaybackEvent() {
        return this.playbackEvent;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPlaybackEvent());
    }

    public void setPlaybackEvent(String str) {
        this.playbackEvent = str;
    }
}
