package com.android.internal.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.internal.util.Preconditions;
import com.android.internal.widget.LockPatternView;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class LockscreenCredential implements Parcelable, AutoCloseable {
    public static final Parcelable.Creator<LockscreenCredential> CREATOR = new Parcelable.Creator<LockscreenCredential>() { // from class: com.android.internal.widget.LockscreenCredential.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LockscreenCredential createFromParcel(Parcel parcel) {
            return new LockscreenCredential(parcel.readInt(), parcel.createByteArray());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LockscreenCredential[] newArray(int i) {
            return new LockscreenCredential[i];
        }
    };
    private byte[] mCredential;
    private final int mType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private LockscreenCredential(int i, byte[] bArr) {
        Objects.requireNonNull(bArr);
        if (i == -1) {
            Preconditions.checkArgument(bArr.length == 0);
        } else {
            Preconditions.checkArgument(i == 3 || i == 4 || i == 1);
            Preconditions.checkArgument(bArr.length > 0);
        }
        this.mType = i;
        this.mCredential = bArr;
    }

    public static LockscreenCredential createNone() {
        return new LockscreenCredential(-1, new byte[0]);
    }

    public static LockscreenCredential createPattern(List<LockPatternView.Cell> list) {
        return new LockscreenCredential(1, LockPatternUtils.patternToByteArray(list));
    }

    public static LockscreenCredential createPassword(CharSequence charSequence) {
        return new LockscreenCredential(4, charSequenceToByteArray(charSequence));
    }

    public static LockscreenCredential createManagedPassword(byte[] bArr) {
        return new LockscreenCredential(4, Arrays.copyOf(bArr, bArr.length));
    }

    public static LockscreenCredential createPin(CharSequence charSequence) {
        return new LockscreenCredential(3, charSequenceToByteArray(charSequence));
    }

    public static LockscreenCredential createPasswordOrNone(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return createNone();
        }
        return createPassword(charSequence);
    }

    public static LockscreenCredential createPinOrNone(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return createNone();
        }
        return createPin(charSequence);
    }

    private void ensureNotZeroized() {
        Preconditions.checkState(this.mCredential != null, "Credential is already zeroized");
    }

    public int getType() {
        ensureNotZeroized();
        return this.mType;
    }

    public byte[] getCredential() {
        ensureNotZeroized();
        return this.mCredential;
    }

    public int getStorageCryptType() {
        if (isNone()) {
            return 1;
        }
        if (isPattern()) {
            return 2;
        }
        if (isPin()) {
            return 3;
        }
        if (isPassword()) {
            return 0;
        }
        throw new IllegalStateException("Unhandled credential type");
    }

    public boolean isNone() {
        ensureNotZeroized();
        return this.mType == -1;
    }

    public boolean isPattern() {
        ensureNotZeroized();
        return this.mType == 1;
    }

    public boolean isPin() {
        ensureNotZeroized();
        return this.mType == 3;
    }

    public boolean isPassword() {
        ensureNotZeroized();
        return this.mType == 4;
    }

    public int size() {
        ensureNotZeroized();
        return this.mCredential.length;
    }

    public LockscreenCredential duplicate() {
        int i = this.mType;
        byte[] bArr = this.mCredential;
        return new LockscreenCredential(i, bArr != null ? Arrays.copyOf(bArr, bArr.length) : null);
    }

    public void zeroize() {
        byte[] bArr = this.mCredential;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
            this.mCredential = null;
        }
    }

    public void checkLength() {
        if (isNone()) {
            return;
        }
        if (isPattern()) {
            if (size() < 4) {
                throw new IllegalArgumentException("pattern must not be null and at least 4 dots long.");
            }
        } else if ((isPassword() || isPin()) && size() < 4) {
            throw new IllegalArgumentException("password must not be null and at least of length 4");
        }
    }

    public boolean checkAgainstStoredType(int i) {
        return i == 2 ? getType() == 4 || getType() == 3 : getType() == i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mType);
        parcel.writeByteArray(this.mCredential);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        zeroize();
    }

    public int hashCode() {
        return ((this.mType + 17) * 31) + this.mCredential.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LockscreenCredential)) {
            return false;
        }
        LockscreenCredential lockscreenCredential = (LockscreenCredential) obj;
        return this.mType == lockscreenCredential.mType && Arrays.equals(this.mCredential, lockscreenCredential.mCredential);
    }

    private static byte[] charSequenceToByteArray(CharSequence charSequence) {
        if (charSequence == null) {
            return new byte[0];
        }
        byte[] bArr = new byte[charSequence.length()];
        for (int i = 0; i < charSequence.length(); i++) {
            bArr[i] = (byte) charSequence.charAt(i);
        }
        return bArr;
    }
}
