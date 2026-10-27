package com.amazon.stratus;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class ListDevicesByCustomerIdResponse implements Comparable<ListDevicesByCustomerIdResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.ListDevicesByCustomerIdResponse");
    private Date deleteDevicesAvailableDate;
    private List<Device> devices;
    private Date lastDeleteDevicesDate;
    private Integer remainingDevices;
    private Map<String, Integer> remainingDevicesForCapabilities;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ListDevicesByCustomerIdResponse listDevicesByCustomerIdResponse) {
        if (listDevicesByCustomerIdResponse == null) {
            return -1;
        }
        if (listDevicesByCustomerIdResponse == this) {
            return 0;
        }
        Date deleteDevicesAvailableDate = getDeleteDevicesAvailableDate();
        Date deleteDevicesAvailableDate2 = listDevicesByCustomerIdResponse.getDeleteDevicesAvailableDate();
        if (deleteDevicesAvailableDate != deleteDevicesAvailableDate2) {
            if (deleteDevicesAvailableDate == null) {
                return -1;
            }
            if (deleteDevicesAvailableDate2 == null) {
                return 1;
            }
            int iCompareTo = deleteDevicesAvailableDate.compareTo(deleteDevicesAvailableDate2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Map<String, Integer> remainingDevicesForCapabilities = getRemainingDevicesForCapabilities();
        Map<String, Integer> remainingDevicesForCapabilities2 = listDevicesByCustomerIdResponse.getRemainingDevicesForCapabilities();
        if (remainingDevicesForCapabilities != remainingDevicesForCapabilities2) {
            if (remainingDevicesForCapabilities == null) {
                return -1;
            }
            if (remainingDevicesForCapabilities2 == null) {
                return 1;
            }
            if (remainingDevicesForCapabilities instanceof Comparable) {
                int iCompareTo2 = ((Comparable) remainingDevicesForCapabilities).compareTo(remainingDevicesForCapabilities2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!remainingDevicesForCapabilities.equals(remainingDevicesForCapabilities2)) {
                int iHashCode = remainingDevicesForCapabilities.hashCode();
                int iHashCode2 = remainingDevicesForCapabilities2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Integer remainingDevices = getRemainingDevices();
        Integer remainingDevices2 = listDevicesByCustomerIdResponse.getRemainingDevices();
        if (remainingDevices != remainingDevices2) {
            if (remainingDevices == null) {
                return -1;
            }
            if (remainingDevices2 == null) {
                return 1;
            }
            int iCompareTo3 = remainingDevices.compareTo(remainingDevices2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Date lastDeleteDevicesDate = getLastDeleteDevicesDate();
        Date lastDeleteDevicesDate2 = listDevicesByCustomerIdResponse.getLastDeleteDevicesDate();
        if (lastDeleteDevicesDate != lastDeleteDevicesDate2) {
            if (lastDeleteDevicesDate == null) {
                return -1;
            }
            if (lastDeleteDevicesDate2 == null) {
                return 1;
            }
            int iCompareTo4 = lastDeleteDevicesDate.compareTo(lastDeleteDevicesDate2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        List<Device> devices = getDevices();
        List<Device> devices2 = listDevicesByCustomerIdResponse.getDevices();
        if (devices != devices2) {
            if (devices == null) {
                return -1;
            }
            if (devices2 == null) {
                return 1;
            }
            if (devices instanceof Comparable) {
                int iCompareTo5 = ((Comparable) devices).compareTo(devices2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!devices.equals(devices2)) {
                int iHashCode3 = devices.hashCode();
                int iHashCode4 = devices2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ListDevicesByCustomerIdResponse)) {
            return false;
        }
        ListDevicesByCustomerIdResponse listDevicesByCustomerIdResponse = (ListDevicesByCustomerIdResponse) obj;
        return internalEqualityCheck(getDeleteDevicesAvailableDate(), listDevicesByCustomerIdResponse.getDeleteDevicesAvailableDate()) && internalEqualityCheck(getRemainingDevicesForCapabilities(), listDevicesByCustomerIdResponse.getRemainingDevicesForCapabilities()) && internalEqualityCheck(getRemainingDevices(), listDevicesByCustomerIdResponse.getRemainingDevices()) && internalEqualityCheck(getLastDeleteDevicesDate(), listDevicesByCustomerIdResponse.getLastDeleteDevicesDate()) && internalEqualityCheck(getDevices(), listDevicesByCustomerIdResponse.getDevices());
    }

    public Date getDeleteDevicesAvailableDate() {
        return this.deleteDevicesAvailableDate;
    }

    public List<Device> getDevices() {
        return this.devices;
    }

    public Date getLastDeleteDevicesDate() {
        return this.lastDeleteDevicesDate;
    }

    public Integer getRemainingDevices() {
        return this.remainingDevices;
    }

    public Map<String, Integer> getRemainingDevicesForCapabilities() {
        return this.remainingDevicesForCapabilities;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getDeleteDevicesAvailableDate(), getRemainingDevicesForCapabilities(), getRemainingDevices(), getLastDeleteDevicesDate(), getDevices());
    }

    public void setDeleteDevicesAvailableDate(Date date) {
        this.deleteDevicesAvailableDate = date;
    }

    public void setDevices(List<Device> list) {
        this.devices = list;
    }

    public void setLastDeleteDevicesDate(Date date) {
        this.lastDeleteDevicesDate = date;
    }

    public void setRemainingDevices(Integer num) {
        this.remainingDevices = num;
    }

    public void setRemainingDevicesForCapabilities(Map<String, Integer> map) {
        this.remainingDevicesForCapabilities = map;
    }
}
