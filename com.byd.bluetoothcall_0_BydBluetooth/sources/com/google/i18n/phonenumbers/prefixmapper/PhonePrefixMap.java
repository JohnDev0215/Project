package com.google.i18n.phonenumbers.prefixmapper;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber$PhoneNumber;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class PhonePrefixMap implements Externalizable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Logger f4150b = Logger.getLogger(PhonePrefixMap.class.getName());
    private c phonePrefixMapStorage;
    private final PhoneNumberUtil phoneUtil = PhoneNumberUtil.k();

    private int a(int i, int i2, long j) {
        int i3 = 0;
        while (i <= i2) {
            i3 = (i + i2) >>> 1;
            long jD = this.phonePrefixMapStorage.d(i3);
            if (jD == j) {
                return i3;
            }
            if (jD > j) {
                i3--;
                i2 = i3;
            } else {
                i = i3 + 1;
            }
        }
        return i3;
    }

    private c b() {
        return new a();
    }

    private c c() {
        return new b();
    }

    private static int d(c cVar, SortedMap<Integer, String> sortedMap) throws IOException {
        cVar.f(sortedMap);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        cVar.g(objectOutputStream);
        objectOutputStream.flush();
        int size = byteArrayOutputStream.size();
        objectOutputStream.close();
        return size;
    }

    c getPhonePrefixMapStorage() {
        return this.phonePrefixMapStorage;
    }

    c getSmallerMapStorage(SortedMap<Integer, String> sortedMap) {
        try {
            c cVarC = c();
            int iD = d(cVarC, sortedMap);
            c cVarB = b();
            return iD < d(cVarB, sortedMap) ? cVarC : cVarB;
        } catch (IOException e2) {
            f4150b.severe(e2.getMessage());
            return c();
        }
    }

    String lookup(long j) {
        int iB = this.phonePrefixMapStorage.b();
        if (iB == 0) {
            return null;
        }
        int iA = iB - 1;
        SortedSet sortedSetC = this.phonePrefixMapStorage.c();
        while (sortedSetC.size() > 0) {
            Integer num = (Integer) sortedSetC.last();
            String strValueOf = String.valueOf(j);
            if (strValueOf.length() > num.intValue()) {
                j = Long.parseLong(strValueOf.substring(0, num.intValue()));
            }
            iA = a(0, iA, j);
            if (iA < 0) {
                return null;
            }
            if (j == this.phonePrefixMapStorage.d(iA)) {
                return this.phonePrefixMapStorage.a(iA);
            }
            sortedSetC = sortedSetC.headSet(num);
        }
        return null;
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput objectInput) throws IOException {
        if (objectInput.readBoolean()) {
            this.phonePrefixMapStorage = new b();
        } else {
            this.phonePrefixMapStorage = new a();
        }
        this.phonePrefixMapStorage.e(objectInput);
    }

    public void readPhonePrefixMap(SortedMap<Integer, String> sortedMap) {
        this.phonePrefixMapStorage = getSmallerMapStorage(sortedMap);
    }

    public String toString() {
        return this.phonePrefixMapStorage.toString();
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeBoolean(this.phonePrefixMapStorage instanceof b);
        this.phonePrefixMapStorage.g(objectOutput);
    }

    public String lookup(Phonenumber$PhoneNumber phonenumber$PhoneNumber) {
        return lookup(Long.parseLong(phonenumber$PhoneNumber.getCountryCode() + this.phoneUtil.o(phonenumber$PhoneNumber)));
    }
}
