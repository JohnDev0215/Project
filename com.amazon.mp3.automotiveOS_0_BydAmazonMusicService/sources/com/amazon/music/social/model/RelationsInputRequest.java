package com.amazon.music.social.model;

import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RelationsInputRequest {
    private final String deviceId;
    private final String deviceType;
    private final boolean isChildRequest;
    private final List<Relation> relations;

    private RelationsInputRequest(String str, String str2, boolean z, List<Relation> list) {
        this.deviceId = str;
        this.deviceType = str2;
        this.isChildRequest = z;
        this.relations = list;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public List<Relation> getRelations() {
        return this.relations;
    }

    public boolean getChildRequest() {
        return this.isChildRequest;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String deviceId;
        private String deviceType;
        private long initBits;
        private boolean isChildRequest;
        private List<Relation> relations;

        private Builder() {
            this.initBits = 3L;
        }

        public final Builder deviceId(String str) {
            this.deviceId = (String) RelationsInputRequest.requireNonNull(str, Splash.PARAMS_DEVICE_ID);
            this.initBits &= -2;
            return this;
        }

        public final Builder deviceType(String str) {
            this.deviceType = (String) RelationsInputRequest.requireNonNull(str, Splash.PARAMS_DEVICE_TYPE);
            this.initBits &= -3;
            return this;
        }

        public final Builder isChildRequest(boolean z) {
            this.isChildRequest = z;
            return this;
        }

        public final Builder relations(List<Relation> list) {
            this.relations = list;
            return this;
        }

        public RelationsInputRequest build() throws IllegalStateException {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new RelationsInputRequest(this.deviceId, this.deviceType, this.isChildRequest, this.relations);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(Splash.PARAMS_DEVICE_ID);
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add(Splash.PARAMS_DEVICE_TYPE);
            }
            return "Cannot build RelationsInputRequest, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }
}
