package api.requests.com.neusoft.onecore.pos.sensordata;

import api.structs.com.neusoft.onecore.pos.ActiveLane;
import api.structs.com.neusoft.onecore.pos.CameraBasedLineInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetCameraBasedLaneInfo extends BaseRequest {
    private static final long serialVersionUID = -1477222719;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetCameraBasedLaneInfo() {
        super(EMessageType.Request, "Positioning", "com.neusoft.onecore.pos.SensorData.SetCameraBasedLaneInfo");
        this.arguments = new Arguments();
    }

    public ReqSetCameraBasedLaneInfo(List<CameraBasedLineInfo> list, ActiveLane activeLane) {
        this();
        this.arguments.setCameraBasedLineInfoList(list);
        this.arguments.setActiveLane(activeLane);
    }

    public Arguments getArguments() {
        return this.arguments;
    }

    public void setArguments(Arguments arguments) {
        if (arguments != null) {
            this.arguments = arguments;
            return;
        }
        throw new IllegalArgumentException("Arguments mustn't be null!");
    }

    public static class Arguments {

        @SerializedName("CameraBasedLineInfoList")
        private List<CameraBasedLineInfo> cameraBasedLineInfoList = new ArrayList();

        @SerializedName("ActiveLane")
        private ActiveLane activeLane = new ActiveLane();

        public Arguments() {
        }

        public Arguments(List<CameraBasedLineInfo> list, ActiveLane activeLane) {
            setCameraBasedLineInfoList(list);
            setActiveLane(activeLane);
        }

        public List<CameraBasedLineInfo> getCameraBasedLineInfoList() {
            return this.cameraBasedLineInfoList;
        }

        public Arguments setCameraBasedLineInfoList(List<CameraBasedLineInfo> list) {
            if (list == null) {
                throw new NullPointerException("cameraBasedLineInfoList cannot be null");
            }
            this.cameraBasedLineInfoList = list;
            return this;
        }

        public Arguments setCameraBasedLineInfoList(CameraBasedLineInfo... cameraBasedLineInfoArr) {
            return setCameraBasedLineInfoList(Arrays.asList(cameraBasedLineInfoArr));
        }

        public ActiveLane getActiveLane() {
            return this.activeLane;
        }

        public Arguments setActiveLane(ActiveLane activeLane) {
            this.activeLane = activeLane;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Arguments)) {
                return false;
            }
            Arguments arguments = (Arguments) obj;
            return super.equals(obj) && arguments.cameraBasedLineInfoList.equals(this.cameraBasedLineInfoList) && arguments.activeLane.equals(this.activeLane);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.cameraBasedLineInfoList, this.activeLane);
        }

        public String toString() {
            return EProjectInterfaceType.OneCore.getGson().toJson(this);
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ReqSetCameraBasedLaneInfo) {
            return super.equals(obj) && ((ReqSetCameraBasedLaneInfo) obj).arguments.equals(this.arguments);
        }
        return false;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.arguments);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public EProjectInterfaceType getProjectInterfaceType() {
        return EProjectInterfaceType.OneCore;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public String toString() {
        return getProjectInterfaceType().getGson().toJson(this);
    }
}
