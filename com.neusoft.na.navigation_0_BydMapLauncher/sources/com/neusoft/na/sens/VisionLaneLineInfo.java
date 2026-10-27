package com.neusoft.na.sens;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class VisionLaneLineInfo {
    private StatisInfo m_communication;
    private StatisInfo m_vision;
    private int m_timestamp = 0;
    private List<LaneLine> m_lanelinesList = new ArrayList();

    public enum EnColor {
        COLOR_UNKNOWN,
        COLOR_YELLOW,
        COLOR_WHITE,
        COLOR_BLUE
    }

    public enum EnConfidence {
        CONFIDENCE_UNKNOWN,
        CONFIDENCE_LOWLKA,
        CONFIDENCE_MEDIUMLKA,
        CONFIDENCE_HIGHLKA,
        CONFIDENCE_LOWTJA,
        CONFIDENCE_MEDIUMTJA,
        CONFIDENCE_HIGHTJA
    }

    public enum EnType {
        TYPE_UNKNOWN,
        TYPE_SOLID,
        TYPE_DASHED,
        TYPE_DOUBLE_LINE,
        TYPE_DOUBLE_SOLID_DASHED,
        TYPE_DOUBLE_DASHED_SOLID,
        TYPE_DOUBLE_SOLID_SOLID,
        TYPE_DOUBLE_DASHED_DASHED,
        TYPE_DECELERATION,
        TYPE_HOVLANE,
        TYPE_LINE_ROAD_EDGE,
        TYPE_LINE_CURB,
        TYPE_LINE_CONESPOLES,
        TYPE_BOTTSDOTS,
        TYPE_BARRIER,
        TYPE_ROADEDGE
    }

    public class Point {
        private float x;
        private float y;
        private float z;

        public Point(float f, float f2, float f3) {
            this.x = f;
            this.y = f2;
            this.z = f3;
        }
    }

    public class StatisInfo {
        private int avFps;
        private int costTime;
        private int fps;

        public StatisInfo(int i, int i2, int i3) {
            this.costTime = i;
            this.fps = i2;
            this.avFps = i3;
        }
    }

    public class LaneLine {
        private int change;
        private List<Float> coeffsList;
        private EnColor color;
        private EnConfidence confidence;
        private Point end;
        private int id;
        private Point start;
        private EnType type;
        private float width;

        public LaneLine(int i, EnColor enColor, EnType enType, List<Float> list, float f, Point point, Point point2, int i2, EnConfidence enConfidence) {
            this.id = i;
            this.color = enColor;
            this.type = enType;
            this.coeffsList = list;
            this.width = f;
            this.start = point;
            this.end = point2;
            this.change = i2;
            this.confidence = enConfidence;
        }

        public List<Float> getCoeffsList() {
            return this.coeffsList;
        }
    }

    public void set_Timestamp(int i) {
        this.m_timestamp = i;
    }

    public void set_VisionStatisInfo(StatisInfo statisInfo) {
        this.m_vision = statisInfo;
    }

    public void set_CommunicationStatisInfo(StatisInfo statisInfo) {
        this.m_communication = statisInfo;
    }

    public void addLaneLineList(LaneLine laneLine) {
        this.m_lanelinesList.add(laneLine);
    }

    public int getTimeStamp() {
        return this.m_timestamp;
    }

    public int getVisionCostTime() {
        StatisInfo statisInfo = this.m_vision;
        if (statisInfo == null) {
            return 0;
        }
        return statisInfo.costTime;
    }

    public int getVisionFps() {
        StatisInfo statisInfo = this.m_vision;
        if (statisInfo == null) {
            return 0;
        }
        return statisInfo.fps;
    }

    public int getVisionAvFps() {
        StatisInfo statisInfo = this.m_vision;
        if (statisInfo == null) {
            return 0;
        }
        return statisInfo.avFps;
    }

    public int getCommunicationCostTime() {
        StatisInfo statisInfo = this.m_communication;
        if (statisInfo == null) {
            return 0;
        }
        return statisInfo.costTime;
    }

    public int getCommunicationFps() {
        StatisInfo statisInfo = this.m_communication;
        if (statisInfo == null) {
            return 0;
        }
        return statisInfo.fps;
    }

    public int getCommunicationAvFps() {
        StatisInfo statisInfo = this.m_communication;
        if (statisInfo == null) {
            return 0;
        }
        return statisInfo.avFps;
    }

    public List<LaneLine> getLaneLineList() {
        return this.m_lanelinesList;
    }

    public int getLaneLineId(int i) {
        return this.m_lanelinesList.get(i).id;
    }

    public int getLaneLineColor(int i) {
        return this.m_lanelinesList.get(i).color.ordinal();
    }

    public int getLaneLineType(int i) {
        return this.m_lanelinesList.get(i).type.ordinal();
    }

    public float getLaneLineWidth(int i) {
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

    public int getLaneLineConfidence(int i) {
        return this.m_lanelinesList.get(i).confidence.ordinal();
    }
}
