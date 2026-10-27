package api.requests.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.LocationType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqReqPOISearchByCategories extends BaseRequest {
    private static final long serialVersionUID = -1480909177;

    @SerializedName("args")
    private Arguments arguments;

    public ReqReqPOISearchByCategories() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.byd.BydNavApi.ReqPOISearchByCategories");
        this.arguments = new Arguments();
    }

    public ReqReqPOISearchByCategories(Integer num, LocationType locationType, String str) {
        this();
        this.arguments.setCategoryId(num);
        this.arguments.setLocationType(locationType);
        this.arguments.setPlaceName(str);
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

        @SerializedName("CategoryId")
        private Integer categoryId = 0;

        @SerializedName("LocationType")
        private LocationType locationType = LocationType.CurrentLocation;

        @SerializedName("PlaceName")
        private String placeName = "";

        public Arguments() {
        }

        public Arguments(Integer num, LocationType locationType, String str) {
            setCategoryId(num);
            setLocationType(locationType);
            setPlaceName(str);
        }

        public Integer getCategoryId() {
            return this.categoryId;
        }

        public Arguments setCategoryId(Integer num) {
            if (num == null) {
                throw new NullPointerException("categoryId cannot be null");
            }
            this.categoryId = num;
            return this;
        }

        public LocationType getLocationType() {
            return this.locationType;
        }

        public Arguments setLocationType(LocationType locationType) {
            this.locationType = locationType;
            return this;
        }

        public String getPlaceName() {
            return this.placeName;
        }

        public Arguments setPlaceName(String str) {
            if (str == null) {
                throw new NullPointerException("placeName cannot be null");
            }
            this.placeName = str;
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
            return super.equals(obj) && arguments.categoryId.equals(this.categoryId) && arguments.locationType.equals(this.locationType) && arguments.placeName.equals(this.placeName);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.categoryId, this.locationType, this.placeName);
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
        if (obj instanceof ReqReqPOISearchByCategories) {
            return super.equals(obj) && ((ReqReqPOISearchByCategories) obj).arguments.equals(this.arguments);
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
