package api.requests.com.neusoft.common.commonsysapiinverse;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.math.BigInteger;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetSystemTimeAndWeekDay extends BaseRequest {
    private static final long serialVersionUID = -1478145157;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetSystemTimeAndWeekDay() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.SetSystemTimeAndWeekDay");
        this.arguments = new Arguments();
    }

    public ReqSetSystemTimeAndWeekDay(BigInteger bigInteger, Integer num) {
        this();
        this.arguments.setSystemTime(bigInteger);
        this.arguments.setWeekDay(num);
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

        @SerializedName("SystemTime")
        private BigInteger systemTime = BigInteger.ZERO;

        @SerializedName("WeekDay")
        private Integer weekDay = 0;

        public Arguments() {
        }

        public Arguments(BigInteger bigInteger, Integer num) {
            setSystemTime(bigInteger);
            setWeekDay(num);
        }

        public BigInteger getSystemTime() {
            return this.systemTime;
        }

        public Arguments setSystemTime(BigInteger bigInteger) {
            if (bigInteger == null) {
                throw new NullPointerException("systemTime cannot be null");
            }
            this.systemTime = bigInteger;
            return this;
        }

        public Integer getWeekDay() {
            return this.weekDay;
        }

        public Arguments setWeekDay(Integer num) {
            if (num == null) {
                throw new NullPointerException("weekDay cannot be null");
            }
            this.weekDay = num;
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
            return super.equals(obj) && arguments.systemTime.equals(this.systemTime) && arguments.weekDay.equals(this.weekDay);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.systemTime, this.weekDay);
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
        if (obj instanceof ReqSetSystemTimeAndWeekDay) {
            return super.equals(obj) && ((ReqSetSystemTimeAndWeekDay) obj).arguments.equals(this.arguments);
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
