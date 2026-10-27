package api.structs.com.neusoft.onecore.pos.sensordata;

import api.structs.com.neusoft.types.common.geopostypes.GeoPos2D;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class MapMatchResult implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1477222755;

    @SerializedName("BifurcationAhead")
    private Boolean bifurcationAhead;

    @SerializedName("Heading")
    private Float heading;

    @SerializedName("InTunnel")
    private Boolean inTunnel;

    @SerializedName("LeftHandTraffic")
    private Boolean leftHandTraffic;

    @SerializedName("MapMatchStatus")
    private MapMatchStatus mapMatchStatus;

    @SerializedName("NumberOfCandidates")
    private Integer numberOfCandidates;

    @SerializedName("NumberOfLanes")
    private Integer numberOfLanes;

    @SerializedName("OneWay")
    private Boolean oneWay;

    @SerializedName("Quality")
    private Float quality;

    @SerializedName("Header")
    private Header header = new Header();

    @SerializedName("Position")
    private GeoPos2D position = new GeoPos2D();

    public MapMatchResult() {
        Float fValueOf = Float.valueOf(0.0f);
        this.heading = fValueOf;
        this.inTunnel = false;
        this.bifurcationAhead = false;
        this.leftHandTraffic = false;
        this.oneWay = false;
        this.mapMatchStatus = MapMatchStatus.INVALID;
        this.numberOfCandidates = 0;
        this.quality = fValueOf;
        this.numberOfLanes = 0;
    }

    public MapMatchResult(Header header, GeoPos2D geoPos2D, Float f, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, MapMatchStatus mapMatchStatus, Integer num, Float f2, Integer num2) {
        Float fValueOf = Float.valueOf(0.0f);
        this.heading = fValueOf;
        this.inTunnel = false;
        this.bifurcationAhead = false;
        this.leftHandTraffic = false;
        this.oneWay = false;
        this.mapMatchStatus = MapMatchStatus.INVALID;
        this.numberOfCandidates = 0;
        this.quality = fValueOf;
        this.numberOfLanes = 0;
        setHeader(header);
        setPosition(geoPos2D);
        setHeading(f);
        setInTunnel(bool);
        setBifurcationAhead(bool2);
        setLeftHandTraffic(bool3);
        setOneWay(bool4);
        setMapMatchStatus(mapMatchStatus);
        setNumberOfCandidates(num);
        setQuality(f2);
        setNumberOfLanes(num2);
    }

    public Header getHeader() {
        return this.header;
    }

    public MapMatchResult setHeader(Header header) {
        this.header = header;
        return this;
    }

    public GeoPos2D getPosition() {
        return this.position;
    }

    public MapMatchResult setPosition(GeoPos2D geoPos2D) {
        this.position = geoPos2D;
        return this;
    }

    public Float getHeading() {
        return this.heading;
    }

    public MapMatchResult setHeading(Float f) {
        if (f == null) {
            throw new NullPointerException("heading cannot be null");
        }
        this.heading = f;
        return this;
    }

    public Boolean getInTunnel() {
        return this.inTunnel;
    }

    public MapMatchResult setInTunnel(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("inTunnel cannot be null");
        }
        this.inTunnel = bool;
        return this;
    }

    public Boolean getBifurcationAhead() {
        return this.bifurcationAhead;
    }

    public MapMatchResult setBifurcationAhead(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("bifurcationAhead cannot be null");
        }
        this.bifurcationAhead = bool;
        return this;
    }

    public Boolean getLeftHandTraffic() {
        return this.leftHandTraffic;
    }

    public MapMatchResult setLeftHandTraffic(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("leftHandTraffic cannot be null");
        }
        this.leftHandTraffic = bool;
        return this;
    }

    public Boolean getOneWay() {
        return this.oneWay;
    }

    public MapMatchResult setOneWay(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("oneWay cannot be null");
        }
        this.oneWay = bool;
        return this;
    }

    public MapMatchStatus getMapMatchStatus() {
        return this.mapMatchStatus;
    }

    public MapMatchResult setMapMatchStatus(MapMatchStatus mapMatchStatus) {
        this.mapMatchStatus = mapMatchStatus;
        return this;
    }

    public Integer getNumberOfCandidates() {
        return this.numberOfCandidates;
    }

    public MapMatchResult setNumberOfCandidates(Integer num) {
        if (num == null) {
            throw new NullPointerException("numberOfCandidates cannot be null");
        }
        this.numberOfCandidates = num;
        return this;
    }

    public Float getQuality() {
        return this.quality;
    }

    public MapMatchResult setQuality(Float f) {
        if (f == null) {
            throw new NullPointerException("quality cannot be null");
        }
        this.quality = f;
        return this;
    }

    public Integer getNumberOfLanes() {
        return this.numberOfLanes;
    }

    public MapMatchResult setNumberOfLanes(Integer num) {
        if (num == null) {
            throw new NullPointerException("numberOfLanes cannot be null");
        }
        this.numberOfLanes = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MapMatchResult mapMatchResult = (MapMatchResult) obj;
        return Objects.equals(this.header, mapMatchResult.header) && Objects.equals(this.position, mapMatchResult.position) && Objects.equals(this.heading, mapMatchResult.heading) && Objects.equals(this.inTunnel, mapMatchResult.inTunnel) && Objects.equals(this.bifurcationAhead, mapMatchResult.bifurcationAhead) && Objects.equals(this.leftHandTraffic, mapMatchResult.leftHandTraffic) && Objects.equals(this.oneWay, mapMatchResult.oneWay) && Objects.equals(this.mapMatchStatus, mapMatchResult.mapMatchStatus) && Objects.equals(this.numberOfCandidates, mapMatchResult.numberOfCandidates) && Objects.equals(this.quality, mapMatchResult.quality) && Objects.equals(this.numberOfLanes, mapMatchResult.numberOfLanes);
    }

    public int hashCode() {
        return Objects.hash(this.header, this.position, this.heading, this.inTunnel, this.bifurcationAhead, this.leftHandTraffic, this.oneWay, this.mapMatchStatus, this.numberOfCandidates, this.quality, this.numberOfLanes);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
