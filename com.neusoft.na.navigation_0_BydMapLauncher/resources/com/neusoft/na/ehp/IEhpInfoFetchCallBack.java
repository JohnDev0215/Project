/**
 * Project: Neusoft Automotives Navigation
 * Company: Neusoft Automotives
 *
 * (c) Copyright 2018-2021
 *
 * All rights reserved.
 */

package com.neusoft.na.ehp;

public interface IEhpInfoFetchCallBack
{
   // Output adasisv2 message.
   void onAdasisV2Message(long Msg);

   void MetaDataMsg(int cyclicCounter,
                    int countryCode, int regionCode, int drivingSide, int speedUnit,
                    int majorProtocolVersion, int minorProtocolVersion, int minorProtocolSubVersion, int hardwareVersion,
                    int mapProvider, int mapVersion_Year, int mapVersion_Quarter);

   void PositionMsg(int cyclicCounter,
                    int pathIndex, int Offset, int positionIndex,
                    int positionAge, int speed, int relativeHeading,
                    int positionProbability, int positionConfidence, int currentLane);

   void SegmentMsg(int cyclicCounter,
                   boolean retransmission, int pathIndex, int offset, boolean update,
                   int functionalRoadClass, int formOfWay,
                   int effectiveSpeedLimit, int effectiveSpeedLimitType,
                   int numberOfLanesInDrivingDirection, int numberOfLanesInOppositeDirection,
                   int tunnel, int bridge, int dividedRoad, int builtUpArea, int complexIntersection,
                   int relativeProbability, int partOfCalculatedRoute);

   void StubMsg(int cyclicCounter,
                boolean retransmission, int pathIndex, int offset, boolean update,
                int subPathIndex, int turnAngle, int relativeProbability,
                int functionalRoadClass, int formOfWay,
                int numberOfLanesInDrivingDirection, int numberOfLanesInOppositeDirection,
                int complexIntersection, int rightOfWay,
                int partOfCalculatedRoute, boolean lastStubAtOffset);

   void ProfileShortMsg(int cyclicCounter,
                        boolean retransmission, int pathIndex, int offset, boolean update,
                        int profileType, boolean controlPoint,
                        int value0,
                        int distance1, int value1,
                        int accuracy);

   void ProfileLongMsg(int cyclicCounter,
                       boolean retransmission, int pathIndex, int offset, boolean update,
                       int profileType, boolean controlPoint,
                       int value);
}
