package com.neusoft.na.common;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class LaneLineInfo implements Parcelable {
    public static final Parcelable.Creator<LaneLineInfo> CREATOR = new Parcelable.Creator<LaneLineInfo>() { // from class: com.neusoft.na.common.LaneLineInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LaneLineInfo createFromParcel(Parcel parcel) {
            return new LaneLineInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LaneLineInfo[] newArray(int i) {
            return new LaneLineInfo[i];
        }
    };
    private StatisInfo m_communication;
    private List<LaneLine> m_lanelinesList;
    private int m_timestamp;
    private StatisInfo m_vision;

    public enum EnColor {
        COLOR_UNKNOWN,
        COLOR_YELLOW,
        COLOR_WHITE
    }

    public enum EnConfidence {
        CONFIDENCE_UNKNOWN,
        CONFIDENCE_LOW,
        CONFIDENCE_MEDIUM,
        CONFIDENCE_HIGH,
        CONFIDENCE_LOWHS,
        CONFIDENCE_MEDIUMHS,
        CONFIDENCE_HIGHHS
    }

    public enum EnType {
        TYPE_UNKNOWN,
        TYPE_SOLID,
        TYPE_DASHED,
        TYPE_DOUBLELINE,
        TYPE_DOUBLESOLIDDASHED,
        TYPE_DOUBLEDASHEDSOLID,
        TYPE_DOUBLESOLIDSOLID,
        TYPE_DOUBLEDASHEDDASHED,
        TYPE_DECELERATION,
        TYPE_HOVLANE,
        TYPE_LINEROADEDGE,
        TYPE_CURB,
        TYPE_CONESPOLES,
        TYPE_BOTTSDOTS,
        TYPE_BARRIER,
        TYPE_ROADEDGE
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LaneLineInfo() {
        this.m_vision = new StatisInfo(0, 0, 0);
        this.m_communication = new StatisInfo(0, 0, 0);
        this.m_lanelinesList = new ArrayList();
    }

    protected LaneLineInfo(Parcel parcel) {
        this.m_vision = new StatisInfo(0, 0, 0);
        this.m_communication = new StatisInfo(0, 0, 0);
        this.m_lanelinesList = new ArrayList();
        this.m_timestamp = parcel.readInt();
        this.m_vision = (StatisInfo) parcel.readParcelable(StatisInfo.class.getClassLoader());
        this.m_communication = (StatisInfo) parcel.readParcelable(StatisInfo.class.getClassLoader());
        parcel.readTypedList(this.m_lanelinesList, LaneLine.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.m_timestamp);
        parcel.writeParcelable(this.m_vision, i);
        parcel.writeParcelable(this.m_communication, i);
        parcel.writeTypedList(this.m_lanelinesList);
    }

    public void setTimestamp(int i) {
        this.m_timestamp = i;
    }

    public void setVisionStatisInfo(int i, int i2, int i3) {
        this.m_vision.costTime = i;
        this.m_vision.fps = i2;
        this.m_vision.avFps = i3;
    }

    public void setCommunicationStatisInfo(int i, int i2, int i3) {
        this.m_communication.avFps = i3;
        this.m_communication.costTime = i;
        this.m_communication.fps = i2;
    }

    public void addLaneLineList(LaneLine laneLine) {
        this.m_lanelinesList.add(laneLine);
    }

    public int getTimeStamp() {
        return this.m_timestamp;
    }

    public int getVisionCostTime() {
        return this.m_vision.costTime;
    }

    public int getVisionFps() {
        return this.m_vision.fps;
    }

    public int getVisionAvFps() {
        return this.m_vision.avFps;
    }

    public int getCommunicationCostTime() {
        return this.m_communication.costTime;
    }

    public int getCommunicationFps() {
        return this.m_communication.fps;
    }

    public int getCommunicationAvFps() {
        return this.m_communication.avFps;
    }

    public List<LaneLine> getLaneLineList() {
        return this.m_lanelinesList;
    }

    public int getLaneLineId(int i) {
        return this.m_lanelinesList.get(i).id;
    }

    public EnColor getLaneLineColor(int i) {
        return this.m_lanelinesList.get(i).color;
    }

    public EnType getLaneLineType(int i) {
        return this.m_lanelinesList.get(i).type;
    }

    public int getLaneLineWidth(int i) {
        return this.m_lanelinesList.get(i).width;
    }

    public float getLaneLinePointStartX(int i) {
        return this.m_lanelinesList.get(i).start.x;
    }

    public float getLaneLinePointStartY(int i) {
        return this.m_lanelinesList.get(i).start.y;
    }

    public float getLaneLinePointStartZ(int i) {
        return this.m_lanelinesList.get(i).start.z;
    }

    public float getLaneLinePointEndX(int i) {
        return this.m_lanelinesList.get(i).end.x;
    }

    public float getLaneLinePointEndY(int i) {
        return this.m_lanelinesList.get(i).end.y;
    }

    public float getLaneLinePointEndZ(int i) {
        return this.m_lanelinesList.get(i).end.z;
    }

    public int getLaneLineChange(int i) {
        return this.m_lanelinesList.get(i).change;
    }

    public EnConfidence getLaneLineConfidence(int i) {
        return this.m_lanelinesList.get(i).confidence;
    }

    public static class LaneLine implements Parcelable {
        public static final Parcelable.Creator<LaneLine> CREATOR = new Parcelable.Creator<LaneLine>() { // from class: com.neusoft.na.common.LaneLineInfo.LaneLine.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LaneLine createFromParcel(Parcel parcel) {
                return new LaneLine(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LaneLine[] newArray(int i) {
                return new LaneLine[i];
            }
        };
        private int change;
        private List<Float> coeffsList;
        private EnColor color;
        private EnConfidence confidence;
        private Point end;
        private int id;
        private Point start;
        private EnType type;
        private int width;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public LaneLine(int i, EnColor enColor, EnType enType, List<Float> list, int i2, Point point, Point point2, int i3, EnConfidence enConfidence) {
            this.id = i;
            this.color = enColor;
            this.type = enType;
            this.coeffsList = list;
            this.width = i2;
            this.start = point;
            this.end = point2;
            this.change = i3;
            this.confidence = enConfidence;
        }

        protected LaneLine(Parcel parcel) {
            this.id = parcel.readInt();
            int i = parcel.readInt();
            if (i == 0) {
                this.color = EnColor.COLOR_UNKNOWN;
            } else if (i == 1) {
                this.color = EnColor.COLOR_YELLOW;
            } else if (i == 2) {
                this.color = EnColor.COLOR_WHITE;
            }
            switch (parcel.readInt()) {
                case 0:
                    this.type = EnType.TYPE_UNKNOWN;
                    break;
                case 1:
                    this.type = EnType.TYPE_SOLID;
                    break;
                case 2:
                    this.type = EnType.TYPE_DASHED;
                    break;
                case 3:
                    this.type = EnType.TYPE_DOUBLELINE;
                    break;
                case 4:
                    this.type = EnType.TYPE_BOTTSDOTS;
                    break;
                case 5:
                    this.type = EnType.TYPE_BARRIER;
                    break;
                case 6:
                    this.type = EnType.TYPE_ROADEDGE;
                    break;
            }
            this.coeffsList = parcel.readArrayList(Float.class.getClassLoader());
            this.width = parcel.readInt();
            this.start = (Point) parcel.readParcelable(Point.class.getClassLoader());
            this.end = (Point) parcel.readParcelable(Point.class.getClassLoader());
            this.change = parcel.readInt();
            switch (parcel.readInt()) {
                case 0:
                    this.confidence = EnConfidence.CONFIDENCE_UNKNOWN;
                    break;
                case 1:
                    this.confidence = EnConfidence.CONFIDENCE_LOW;
                    break;
                case 2:
                    this.confidence = EnConfidence.CONFIDENCE_MEDIUM;
                    break;
                case 3:
                    this.confidence = EnConfidence.CONFIDENCE_HIGH;
                    break;
                case 4:
                    this.confidence = EnConfidence.CONFIDENCE_LOWHS;
                    break;
                case 5:
                    this.confidence = EnConfidence.CONFIDENCE_MEDIUMHS;
                    break;
                case 6:
                    this.confidence = EnConfidence.CONFIDENCE_HIGHHS;
                    break;
            }
        }

        public List<Float> getCoeffsList() {
            return this.coeffsList;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.id);
            parcel.writeInt(this.color.ordinal());
            parcel.writeInt(this.type.ordinal());
            parcel.writeList(this.coeffsList);
            parcel.writeInt(this.width);
            parcel.writeParcelable(this.start, i);
            parcel.writeParcelable(this.end, i);
            parcel.writeInt(this.change);
            parcel.writeInt(this.confidence.ordinal());
        }
    }

    public static class StatisInfo implements Parcelable {
        public static final Parcelable.Creator<StatisInfo> CREATOR = new Parcelable.Creator<StatisInfo>() { // from class: com.neusoft.na.common.LaneLineInfo.StatisInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public StatisInfo createFromParcel(Parcel parcel) {
                return new StatisInfo(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public StatisInfo[] newArray(int i) {
                return new StatisInfo[i];
            }
        };
        private int avFps;
        private int costTime;
        private int fps;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public StatisInfo(int i, int i2, int i3) {
            this.costTime = i;
            this.fps = i2;
            this.avFps = i3;
        }

        protected StatisInfo(Parcel parcel) {
            this.costTime = parcel.readInt();
            this.fps = parcel.readInt();
            this.avFps = parcel.readInt();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.costTime);
            parcel.writeInt(this.fps);
            parcel.writeInt(this.avFps);
        }
    }

    public static class Point implements Parcelable {
        public static final Parcelable.Creator<Point> CREATOR = new Parcelable.Creator<Point>() { // from class: com.neusoft.na.common.LaneLineInfo.Point.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Point createFromParcel(Parcel parcel) {
                return new Point(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Point[] newArray(int i) {
                return new Point[i];
            }
        };
        private float x;
        private float y;
        private float z;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public Point(float f, float f2, float f3) {
            this.x = f;
            this.y = f2;
            this.z = f3;
        }

        protected Point(Parcel parcel) {
            this.x = parcel.readFloat();
            this.y = parcel.readFloat();
            this.z = parcel.readFloat();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(this.x);
            parcel.writeFloat(this.y);
            parcel.writeFloat(this.z);
        }
    }
}
