package net.lingala.zip4j.model;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CentralDirectory {
    private List<FileHeader> fileHeaders = new ArrayList();
    private DigitalSignature digitalSignature = new DigitalSignature();

    public DigitalSignature getDigitalSignature() {
        return this.digitalSignature;
    }

    public List<FileHeader> getFileHeaders() {
        return this.fileHeaders;
    }

    public void setDigitalSignature(DigitalSignature digitalSignature) {
        this.digitalSignature = digitalSignature;
    }

    public void setFileHeaders(List<FileHeader> list) {
        this.fileHeaders = list;
    }
}
