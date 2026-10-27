package api.structs.com.neusoft.onecore.pos;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class CameraBasedLineInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137658;

    @SerializedName("LaneWidth")
    private Float laneWidth;

    @SerializedName("LineType")
    private LineType lineType = LineType.Invalid;

    @SerializedName("Probability")
    private Float probability;

    public CameraBasedLineInfo() {
        Float fValueOf = Float.valueOf(0.0f);
        this.probability = fValueOf;
        this.laneWidth = fValueOf;
    }

    public CameraBasedLineInfo(LineType lineType, Float f, Float f2) {
        Float fValueOf = Float.valueOf(0.0f);
        this.probability = fValueOf;
        this.laneWidth = fValueOf;
        setLineType(lineType);
        setProbability(f);
        setLaneWidth(f2);
    }

    public LineType getLineType() {
        return this.lineType;
    }

    public CameraBasedLineInfo setLineType(LineType lineType) {
        this.lineType = lineType;
        return this;
    }

    public Float getProbability() {
        return this.probability;
    }

    public CameraBasedLineInfo setProbability(Float f) {
        if (f == null) {
            throw new NullPointerException("probability cannot be null");
        }
        this.probability = f;
        return this;
    }

    public Float getLaneWidth() {
        return this.laneWidth;
    }

    public CameraBasedLineInfo setLaneWidth(Float f) {
        if (f == null) {
            throw new NullPointerException("laneWidth cannot be null");
        }
        this.laneWidth = f;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CameraBasedLineInfo cameraBasedLineInfo = (CameraBasedLineInfo) obj;
        return Objects.equals(this.lineType, cameraBasedLineInfo.lineType) && Objects.equals(this.probability, cameraBasedLineInfo.probability) && Objects.equals(this.laneWidth, cameraBasedLineInfo.laneWidth);
    }

    public int hashCode() {
        return Objects.hash(this.lineType, this.probability, this.laneWidth);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
