package SOACoreInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class DeviceIdentity extends SOAObject {
    private static final int classNameHashCode = internalHashCodeCompute("SOACoreInterface.v1_0.DeviceIdentity");
    private String height;
    private String id;
    private String language;
    private String manufacturer;
    private String model;
    private String osVersion;
    private String timeZone;
    private String typeId;
    private String width;

    public static class Builder extends SOAObject.Builder {
        protected String height;
        protected String id;
        protected String language;
        protected String manufacturer;
        protected String model;
        protected String osVersion;
        protected String timeZone;
        protected String typeId;
        protected String width;

        public DeviceIdentity build() {
            DeviceIdentity deviceIdentity = new DeviceIdentity();
            populate(deviceIdentity);
            return deviceIdentity;
        }

        protected void populate(DeviceIdentity deviceIdentity) {
            super.populate((SOAObject) deviceIdentity);
            deviceIdentity.setLanguage(this.language);
            deviceIdentity.setId(this.id);
            deviceIdentity.setTimeZone(this.timeZone);
            deviceIdentity.setTypeId(this.typeId);
            deviceIdentity.setManufacturer(this.manufacturer);
            deviceIdentity.setWidth(this.width);
            deviceIdentity.setModel(this.model);
            deviceIdentity.setOsVersion(this.osVersion);
            deviceIdentity.setHeight(this.height);
        }

        public Builder withHeight(String str) {
            this.height = str;
            return this;
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }

        public Builder withLanguage(String str) {
            this.language = str;
            return this;
        }

        public Builder withManufacturer(String str) {
            this.manufacturer = str;
            return this;
        }

        public Builder withModel(String str) {
            this.model = str;
            return this;
        }

        public Builder withOsVersion(String str) {
            this.osVersion = str;
            return this;
        }

        public Builder withTimeZone(String str) {
            this.timeZone = str;
            return this;
        }

        public Builder withTypeId(String str) {
            this.typeId = str;
            return this;
        }

        public Builder withWidth(String str) {
            this.width = str;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof DeviceIdentity)) {
            return 1;
        }
        DeviceIdentity deviceIdentity = (DeviceIdentity) sOAObject;
        String language = getLanguage();
        String language2 = deviceIdentity.getLanguage();
        if (language != language2) {
            if (language == null) {
                return -1;
            }
            if (language2 == null) {
                return 1;
            }
            int iCompareTo = language.compareTo(language2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String id = getId();
        String id2 = deviceIdentity.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo2 = id.compareTo(id2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String timeZone = getTimeZone();
        String timeZone2 = deviceIdentity.getTimeZone();
        if (timeZone != timeZone2) {
            if (timeZone == null) {
                return -1;
            }
            if (timeZone2 == null) {
                return 1;
            }
            int iCompareTo3 = timeZone.compareTo(timeZone2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String typeId = getTypeId();
        String typeId2 = deviceIdentity.getTypeId();
        if (typeId != typeId2) {
            if (typeId == null) {
                return -1;
            }
            if (typeId2 == null) {
                return 1;
            }
            int iCompareTo4 = typeId.compareTo(typeId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String manufacturer = getManufacturer();
        String manufacturer2 = deviceIdentity.getManufacturer();
        if (manufacturer != manufacturer2) {
            if (manufacturer == null) {
                return -1;
            }
            if (manufacturer2 == null) {
                return 1;
            }
            int iCompareTo5 = manufacturer.compareTo(manufacturer2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String width = getWidth();
        String width2 = deviceIdentity.getWidth();
        if (width != width2) {
            if (width == null) {
                return -1;
            }
            if (width2 == null) {
                return 1;
            }
            int iCompareTo6 = width.compareTo(width2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String model = getModel();
        String model2 = deviceIdentity.getModel();
        if (model != model2) {
            if (model == null) {
                return -1;
            }
            if (model2 == null) {
                return 1;
            }
            int iCompareTo7 = model.compareTo(model2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String osVersion = getOsVersion();
        String osVersion2 = deviceIdentity.getOsVersion();
        if (osVersion != osVersion2) {
            if (osVersion == null) {
                return -1;
            }
            if (osVersion2 == null) {
                return 1;
            }
            int iCompareTo8 = osVersion.compareTo(osVersion2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String height = getHeight();
        String height2 = deviceIdentity.getHeight();
        if (height != height2) {
            if (height == null) {
                return -1;
            }
            if (height2 == null) {
                return 1;
            }
            int iCompareTo9 = height.compareTo(height2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof DeviceIdentity)) {
            return false;
        }
        DeviceIdentity deviceIdentity = (DeviceIdentity) obj;
        return super.equals(obj) && internalEqualityCheck(getLanguage(), deviceIdentity.getLanguage()) && internalEqualityCheck(getId(), deviceIdentity.getId()) && internalEqualityCheck(getTimeZone(), deviceIdentity.getTimeZone()) && internalEqualityCheck(getTypeId(), deviceIdentity.getTypeId()) && internalEqualityCheck(getManufacturer(), deviceIdentity.getManufacturer()) && internalEqualityCheck(getWidth(), deviceIdentity.getWidth()) && internalEqualityCheck(getModel(), deviceIdentity.getModel()) && internalEqualityCheck(getOsVersion(), deviceIdentity.getOsVersion()) && internalEqualityCheck(getHeight(), deviceIdentity.getHeight());
    }

    public String getHeight() {
        return this.height;
    }

    public String getId() {
        return this.id;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getModel() {
        return this.model;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public String getTypeId() {
        return this.typeId;
    }

    public String getWidth() {
        return this.width;
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getLanguage(), getId(), getTimeZone(), getTypeId(), getManufacturer(), getWidth(), getModel(), getOsVersion(), getHeight());
    }

    public void setHeight(String str) {
        this.height = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public void setManufacturer(String str) {
        this.manufacturer = str;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    public void setTypeId(String str) {
        this.typeId = str;
    }

    public void setWidth(String str) {
        this.width = str;
    }
}
