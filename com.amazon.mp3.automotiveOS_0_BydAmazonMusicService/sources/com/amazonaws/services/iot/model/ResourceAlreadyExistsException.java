package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonServiceException;

/* JADX INFO: loaded from: classes5.dex */
public class ResourceAlreadyExistsException extends AmazonServiceException {
    private static final long serialVersionUID = 1;
    private String resourceArn;
    private String resourceId;

    public ResourceAlreadyExistsException(String str) {
        super(str);
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(String str) {
        this.resourceId = str;
    }

    public String getResourceArn() {
        return this.resourceArn;
    }

    public void setResourceArn(String str) {
        this.resourceArn = str;
    }
}
