package api.requests.com.neusoft.onecore.pos.sensordata;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqDataProviderVersion extends BaseRequest {
    private static final long serialVersionUID = -1477222755;

    @SerializedName("args")
    private Arguments arguments;

    public ReqDataProviderVersion() {
        super(EMessageType.Request, "Positioning", "com.neusoft.onecore.pos.SensorData.DataProviderVersion");
        this.arguments = new Arguments();
    }

    public ReqDataProviderVersion(Long l, Long l2, Long l3, Long l4, Long l5, Long l6, String str) {
        this();
        this.arguments.setMajor(l);
        this.arguments.setMinor(l2);
        this.arguments.setPatch(l3);
        this.arguments.setDay(l4);
        this.arguments.setMonth(l5);
        this.arguments.setYear(l6);
        this.arguments.setComment(str);
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

        @SerializedName("Major")
        private Long major = 0L;

        @SerializedName("Minor")
        private Long minor = 0L;

        @SerializedName("Patch")
        private Long patch = 0L;

        @SerializedName("Day")
        private Long day = 0L;

        @SerializedName("Month")
        private Long month = 0L;

        @SerializedName("Year")
        private Long year = 0L;

        @SerializedName("Comment")
        private String comment = "";

        public Arguments() {
        }

        public Arguments(Long l, Long l2, Long l3, Long l4, Long l5, Long l6, String str) {
            setMajor(l);
            setMinor(l2);
            setPatch(l3);
            setDay(l4);
            setMonth(l5);
            setYear(l6);
            setComment(str);
        }

        public Long getMajor() {
            return this.major;
        }

        public Arguments setMajor(Long l) {
            if (l == null) {
                throw new NullPointerException("major cannot be null");
            }
            this.major = l;
            return this;
        }

        public Long getMinor() {
            return this.minor;
        }

        public Arguments setMinor(Long l) {
            if (l == null) {
                throw new NullPointerException("minor cannot be null");
            }
            this.minor = l;
            return this;
        }

        public Long getPatch() {
            return this.patch;
        }

        public Arguments setPatch(Long l) {
            if (l == null) {
                throw new NullPointerException("patch cannot be null");
            }
            this.patch = l;
            return this;
        }

        public Long getDay() {
            return this.day;
        }

        public Arguments setDay(Long l) {
            if (l == null) {
                throw new NullPointerException("day cannot be null");
            }
            this.day = l;
            return this;
        }

        public Long getMonth() {
            return this.month;
        }

        public Arguments setMonth(Long l) {
            if (l == null) {
                throw new NullPointerException("month cannot be null");
            }
            this.month = l;
            return this;
        }

        public Long getYear() {
            return this.year;
        }

        public Arguments setYear(Long l) {
            if (l == null) {
                throw new NullPointerException("year cannot be null");
            }
            this.year = l;
            return this;
        }

        public String getComment() {
            return this.comment;
        }

        public Arguments setComment(String str) {
            if (str == null) {
                throw new NullPointerException("comment cannot be null");
            }
            this.comment = str;
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
            return super.equals(obj) && arguments.major.equals(this.major) && arguments.minor.equals(this.minor) && arguments.patch.equals(this.patch) && arguments.day.equals(this.day) && arguments.month.equals(this.month) && arguments.year.equals(this.year) && arguments.comment.equals(this.comment);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.major, this.minor, this.patch, this.day, this.month, this.year, this.comment);
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
        if (obj instanceof ReqDataProviderVersion) {
            return super.equals(obj) && ((ReqDataProviderVersion) obj).arguments.equals(this.arguments);
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
