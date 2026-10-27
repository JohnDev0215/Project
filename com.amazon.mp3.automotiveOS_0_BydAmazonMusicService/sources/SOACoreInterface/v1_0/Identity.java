package SOACoreInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class Identity extends SOAObject {
    private static final int classNameHashCode = internalHashCodeCompute("SOACoreInterface.v1_0.Identity");
    private AccountIdentity account;
    private ApplicationIdentity application;
    private DeviceIdentity device;
    private RequestIdentity request;
    private UserIdentity user;

    public static class Builder extends SOAObject.Builder {
        protected AccountIdentity account;
        protected ApplicationIdentity application;
        protected DeviceIdentity device;
        protected RequestIdentity request;
        protected UserIdentity user;

        public Identity build() {
            Identity identity = new Identity();
            populate(identity);
            return identity;
        }

        protected void populate(Identity identity) {
            super.populate((SOAObject) identity);
            identity.setDevice(this.device);
            identity.setAccount(this.account);
            identity.setRequest(this.request);
            identity.setApplication(this.application);
            identity.setUser(this.user);
        }

        public Builder withAccount(AccountIdentity accountIdentity) {
            this.account = accountIdentity;
            return this;
        }

        public Builder withApplication(ApplicationIdentity applicationIdentity) {
            this.application = applicationIdentity;
            return this;
        }

        public Builder withDevice(DeviceIdentity deviceIdentity) {
            this.device = deviceIdentity;
            return this;
        }

        public Builder withRequest(RequestIdentity requestIdentity) {
            this.request = requestIdentity;
            return this;
        }

        public Builder withUser(UserIdentity userIdentity) {
            this.user = userIdentity;
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
        if (!(sOAObject instanceof Identity)) {
            return 1;
        }
        Identity identity = (Identity) sOAObject;
        DeviceIdentity device = getDevice();
        DeviceIdentity device2 = identity.getDevice();
        if (device != device2) {
            if (device == null) {
                return -1;
            }
            if (device2 == null) {
                return 1;
            }
            int iCompareTo = device.compareTo(device2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        AccountIdentity account = getAccount();
        AccountIdentity account2 = identity.getAccount();
        if (account != account2) {
            if (account == null) {
                return -1;
            }
            if (account2 == null) {
                return 1;
            }
            int iCompareTo2 = account.compareTo(account2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        RequestIdentity request = getRequest();
        RequestIdentity request2 = identity.getRequest();
        if (request != request2) {
            if (request == null) {
                return -1;
            }
            if (request2 == null) {
                return 1;
            }
            int iCompareTo3 = request.compareTo(request2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        ApplicationIdentity application = getApplication();
        ApplicationIdentity application2 = identity.getApplication();
        if (application != application2) {
            if (application == null) {
                return -1;
            }
            if (application2 == null) {
                return 1;
            }
            int iCompareTo4 = application.compareTo(application2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        UserIdentity user = getUser();
        UserIdentity user2 = identity.getUser();
        if (user != user2) {
            if (user == null) {
                return -1;
            }
            if (user2 == null) {
                return 1;
            }
            int iCompareTo5 = user.compareTo(user2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof Identity)) {
            return false;
        }
        Identity identity = (Identity) obj;
        return super.equals(obj) && internalEqualityCheck(getDevice(), identity.getDevice()) && internalEqualityCheck(getAccount(), identity.getAccount()) && internalEqualityCheck(getRequest(), identity.getRequest()) && internalEqualityCheck(getApplication(), identity.getApplication()) && internalEqualityCheck(getUser(), identity.getUser());
    }

    public AccountIdentity getAccount() {
        return this.account;
    }

    public ApplicationIdentity getApplication() {
        return this.application;
    }

    public DeviceIdentity getDevice() {
        return this.device;
    }

    public RequestIdentity getRequest() {
        return this.request;
    }

    public UserIdentity getUser() {
        return this.user;
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getDevice(), getAccount(), getRequest(), getApplication(), getUser());
    }

    public void setAccount(AccountIdentity accountIdentity) {
        this.account = accountIdentity;
    }

    public void setApplication(ApplicationIdentity applicationIdentity) {
        this.application = applicationIdentity;
    }

    public void setDevice(DeviceIdentity deviceIdentity) {
        this.device = deviceIdentity;
    }

    public void setRequest(RequestIdentity requestIdentity) {
        this.request = requestIdentity;
    }

    public void setUser(UserIdentity userIdentity) {
        this.user = userIdentity;
    }
}
