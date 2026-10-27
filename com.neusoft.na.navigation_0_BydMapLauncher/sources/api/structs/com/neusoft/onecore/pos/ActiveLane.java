package api.structs.com.neusoft.onecore.pos;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ActiveLane implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137658;

    @SerializedName("ActiveLane")
    private Integer activeLane = 0;

    @SerializedName("Probability")
    private Float probability = Float.valueOf(0.0f);

    @SerializedName("LaneChangeEvent")
    private LaneChangeEventType laneChangeEvent = LaneChangeEventType.NoChange;

    public ActiveLane() {
    }

    public ActiveLane(Integer num, Float f, LaneChangeEventType laneChangeEventType) {
        setActiveLane(num);
        setProbability(f);
        setLaneChangeEvent(laneChangeEventType);
    }

    public Integer getActiveLane() {
        return this.activeLane;
    }

    public ActiveLane setActiveLane(Integer num) {
        if (num == null) {
            throw new NullPointerException("activeLane cannot be null");
        }
        this.activeLane = num;
        return this;
    }

    public Float getProbability() {
        return this.probability;
    }

    public ActiveLane setProbability(Float f) {
        if (f == null) {
            throw new NullPointerException("probability cannot be null");
        }
        this.probability = f;
        return this;
    }

    public LaneChangeEventType getLaneChangeEvent() {
        return this.laneChangeEvent;
    }

    public ActiveLane setLaneChangeEvent(LaneChangeEventType laneChangeEventType) {
        this.laneChangeEvent = laneChangeEventType;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ActiveLane activeLane = (ActiveLane) obj;
        return Objects.equals(this.activeLane, activeLane.activeLane) && Objects.equals(this.probability, activeLane.probability) && Objects.equals(this.laneChangeEvent, activeLane.laneChangeEvent);
    }

    public int hashCode() {
        return Objects.hash(this.activeLane, this.probability, this.laneChangeEvent);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
