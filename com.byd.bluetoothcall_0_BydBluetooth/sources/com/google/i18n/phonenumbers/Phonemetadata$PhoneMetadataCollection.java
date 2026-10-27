package com.google.i18n.phonenumbers;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class Phonemetadata$PhoneMetadataCollection implements Externalizable {
    private static final long serialVersionUID = 1;
    private List<Phonemetadata$PhoneMetadata> metadata_ = new ArrayList();

    public static final class Builder extends Phonemetadata$PhoneMetadataCollection {
        public Phonemetadata$PhoneMetadataCollection build() {
            return this;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Phonemetadata$PhoneMetadataCollection addMetadata(Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata) {
        Objects.requireNonNull(phonemetadata$PhoneMetadata);
        this.metadata_.add(phonemetadata$PhoneMetadata);
        return this;
    }

    public Phonemetadata$PhoneMetadataCollection clear() {
        this.metadata_.clear();
        return this;
    }

    public int getMetadataCount() {
        return this.metadata_.size();
    }

    public List<Phonemetadata$PhoneMetadata> getMetadataList() {
        return this.metadata_;
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput objectInput) throws IOException {
        int i = objectInput.readInt();
        for (int i2 = 0; i2 < i; i2++) {
            Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata = new Phonemetadata$PhoneMetadata();
            phonemetadata$PhoneMetadata.readExternal(objectInput);
            this.metadata_.add(phonemetadata$PhoneMetadata);
        }
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        int metadataCount = getMetadataCount();
        objectOutput.writeInt(metadataCount);
        for (int i = 0; i < metadataCount; i++) {
            this.metadata_.get(i).writeExternal(objectOutput);
        }
    }
}
