package android.os;

import android.net.TrafficStats;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import android.util.proto.ProtoOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class Bundle extends BaseBundle implements Cloneable, Parcelable {
    public static final Parcelable.Creator<Bundle> CREATOR;
    public static final Bundle EMPTY;
    static final int FLAG_ALLOW_FDS = 1024;
    static final int FLAG_HAS_FDS = 256;
    static final int FLAG_HAS_FDS_KNOWN = 512;
    public static final Bundle STRIPPED;

    static {
        Bundle bundle = new Bundle();
        EMPTY = bundle;
        bundle.mMap = ArrayMap.EMPTY;
        Bundle bundle2 = new Bundle();
        STRIPPED = bundle2;
        bundle2.putInt("STRIPPED", 1);
        CREATOR = new Parcelable.Creator<Bundle>() { // from class: android.os.Bundle.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Bundle createFromParcel(Parcel parcel) {
                return parcel.readBundle();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Bundle[] newArray(int i) {
                return new Bundle[i];
            }
        };
    }

    public Bundle() {
        this.mFlags = 1536;
    }

    public Bundle(Parcel parcel) {
        super(parcel);
        this.mFlags = 1024;
        maybePrefillHasFds();
    }

    public Bundle(Parcel parcel, int i) {
        super(parcel, i);
        this.mFlags = 1024;
        maybePrefillHasFds();
    }

    private void maybePrefillHasFds() {
        if (this.mParcelledData != null) {
            if (this.mParcelledData.hasFileDescriptors()) {
                this.mFlags |= 768;
            } else {
                this.mFlags |= 512;
            }
        }
    }

    public Bundle(ClassLoader classLoader) {
        super(classLoader);
        this.mFlags = 1536;
    }

    public Bundle(int i) {
        super(i);
        this.mFlags = 1536;
    }

    public Bundle(Bundle bundle) {
        super(bundle);
        this.mFlags = bundle.mFlags;
    }

    public Bundle(PersistableBundle persistableBundle) {
        super(persistableBundle);
        this.mFlags = 1536;
    }

    Bundle(boolean z) {
        super(z);
    }

    public static Bundle forPair(String str, String str2) {
        Bundle bundle = new Bundle(1);
        bundle.putString(str, str2);
        return bundle;
    }

    @Override // android.os.BaseBundle
    public void setClassLoader(ClassLoader classLoader) {
        super.setClassLoader(classLoader);
    }

    @Override // android.os.BaseBundle
    public ClassLoader getClassLoader() {
        return super.getClassLoader();
    }

    public boolean setAllowFds(boolean z) {
        boolean z2 = (this.mFlags & 1024) != 0;
        if (z) {
            this.mFlags |= 1024;
        } else {
            this.mFlags &= -1025;
        }
        return z2;
    }

    public void setDefusable(boolean z) {
        if (z) {
            this.mFlags |= 1;
        } else {
            this.mFlags &= -2;
        }
    }

    public static Bundle setDefusable(Bundle bundle, boolean z) {
        if (bundle != null) {
            bundle.setDefusable(z);
        }
        return bundle;
    }

    public Object clone() {
        return new Bundle(this);
    }

    public Bundle deepCopy() {
        Bundle bundle = new Bundle(false);
        bundle.copyInternal(this, true);
        return bundle;
    }

    @Override // android.os.BaseBundle
    public void clear() {
        super.clear();
        this.mFlags = 1536;
    }

    @Override // android.os.BaseBundle
    public void remove(String str) {
        super.remove(str);
        if ((this.mFlags & 256) != 0) {
            this.mFlags &= -513;
        }
    }

    public void putAll(Bundle bundle) {
        unparcel();
        bundle.unparcel();
        this.mMap.putAll((ArrayMap<? extends String, ? extends Object>) bundle.mMap);
        if ((bundle.mFlags & 256) != 0) {
            this.mFlags |= 256;
        }
        if ((bundle.mFlags & 512) == 0) {
            this.mFlags &= -513;
        }
    }

    public int getSize() {
        if (this.mParcelledData != null) {
            return this.mParcelledData.dataSize();
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00ad  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean hasFileDescriptors() {
        /*
            r7 = this;
            int r0 = r7.mFlags
            r0 = r0 & 512(0x200, float:7.17E-43)
            r1 = 0
            r2 = 1
            if (r0 != 0) goto Lb9
            android.os.Parcel r0 = r7.mParcelledData
            if (r0 == 0) goto L1a
            android.os.Parcel r0 = r7.mParcelledData
            boolean r0 = r0.hasFileDescriptors()
            if (r0 == 0) goto L17
        L14:
            r3 = r2
            goto La4
        L17:
            r3 = r1
            goto La4
        L1a:
            android.util.ArrayMap<java.lang.String, java.lang.Object> r0 = r7.mMap
            int r0 = r0.size()
            int r0 = r0 - r2
            r3 = r1
        L22:
            if (r0 < 0) goto La4
            android.util.ArrayMap<java.lang.String, java.lang.Object> r4 = r7.mMap
            java.lang.Object r4 = r4.valueAt(r0)
            boolean r5 = r4 instanceof android.os.Parcelable
            if (r5 == 0) goto L38
            android.os.Parcelable r4 = (android.os.Parcelable) r4
            int r4 = r4.describeContents()
            r4 = r4 & r2
            if (r4 == 0) goto La0
            goto L14
        L38:
            boolean r5 = r4 instanceof android.os.Parcelable[]
            if (r5 == 0) goto L51
            android.os.Parcelable[] r4 = (android.os.Parcelable[]) r4
            int r5 = r4.length
            int r5 = r5 - r2
        L40:
            if (r5 < 0) goto La0
            r6 = r4[r5]
            if (r6 == 0) goto L4e
            int r6 = r6.describeContents()
            r6 = r6 & r2
            if (r6 == 0) goto L4e
            goto L9b
        L4e:
            int r5 = r5 + (-1)
            goto L40
        L51:
            boolean r5 = r4 instanceof android.util.SparseArray
            if (r5 == 0) goto L71
            android.util.SparseArray r4 = (android.util.SparseArray) r4
            int r5 = r4.size()
            int r5 = r5 - r2
        L5c:
            if (r5 < 0) goto La0
            java.lang.Object r6 = r4.valueAt(r5)
            android.os.Parcelable r6 = (android.os.Parcelable) r6
            if (r6 == 0) goto L6e
            int r6 = r6.describeContents()
            r6 = r6 & r2
            if (r6 == 0) goto L6e
            goto L9b
        L6e:
            int r5 = r5 + (-1)
            goto L5c
        L71:
            boolean r5 = r4 instanceof java.util.ArrayList
            if (r5 == 0) goto La0
            java.util.ArrayList r4 = (java.util.ArrayList) r4
            boolean r5 = r4.isEmpty()
            if (r5 != 0) goto La0
            java.lang.Object r5 = r4.get(r1)
            boolean r5 = r5 instanceof android.os.Parcelable
            if (r5 == 0) goto La0
            int r5 = r4.size()
            int r5 = r5 - r2
        L8a:
            if (r5 < 0) goto La0
            java.lang.Object r6 = r4.get(r5)
            android.os.Parcelable r6 = (android.os.Parcelable) r6
            if (r6 == 0) goto L9d
            int r6 = r6.describeContents()
            r6 = r6 & r2
            if (r6 == 0) goto L9d
        L9b:
            r3 = r2
            goto La0
        L9d:
            int r5 = r5 + (-1)
            goto L8a
        La0:
            int r0 = r0 + (-1)
            goto L22
        La4:
            if (r3 == 0) goto Lad
            int r0 = r7.mFlags
            r0 = r0 | 256(0x100, float:3.59E-43)
            r7.mFlags = r0
            goto Lb3
        Lad:
            int r0 = r7.mFlags
            r0 = r0 & (-257(0xfffffffffffffeff, float:NaN))
            r7.mFlags = r0
        Lb3:
            int r0 = r7.mFlags
            r0 = r0 | 512(0x200, float:7.17E-43)
            r7.mFlags = r0
        Lb9:
            int r0 = r7.mFlags
            r0 = r0 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto Lc0
            r1 = r2
        Lc0:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.os.Bundle.hasFileDescriptors():boolean");
    }

    public Bundle filterValues() {
        Bundle bundle;
        unparcel();
        if (this.mMap != null) {
            ArrayMap<String, Object> arrayMap = this.mMap;
            bundle = this;
            for (int size = arrayMap.size() - 1; size >= 0; size--) {
                Object objValueAt = arrayMap.valueAt(size);
                if (!PersistableBundle.isValidType(objValueAt)) {
                    if (objValueAt instanceof Bundle) {
                        Bundle bundleFilterValues = ((Bundle) objValueAt).filterValues();
                        if (bundleFilterValues != objValueAt) {
                            if (arrayMap == this.mMap) {
                                Bundle bundle2 = new Bundle(this);
                                bundle = bundle2;
                                arrayMap = bundle2.mMap;
                            }
                            arrayMap.setValueAt(size, bundleFilterValues);
                        }
                    } else if (!objValueAt.getClass().getName().startsWith("android.")) {
                        if (arrayMap == this.mMap) {
                            bundle = new Bundle(this);
                            arrayMap = bundle.mMap;
                        }
                        arrayMap.removeAt(size);
                    }
                }
            }
        } else {
            bundle = this;
        }
        this.mFlags |= 512;
        this.mFlags &= TrafficStats.TAG_NETWORK_STACK_RANGE_END;
        return bundle;
    }

    @Override // android.os.BaseBundle
    public void putObject(String str, Object obj) {
        if (obj instanceof Byte) {
            putByte(str, ((Byte) obj).byteValue());
            return;
        }
        if (obj instanceof Character) {
            putChar(str, ((Character) obj).charValue());
            return;
        }
        if (obj instanceof Short) {
            putShort(str, ((Short) obj).shortValue());
            return;
        }
        if (obj instanceof Float) {
            putFloat(str, ((Float) obj).floatValue());
            return;
        }
        if (obj instanceof CharSequence) {
            putCharSequence(str, (CharSequence) obj);
            return;
        }
        if (obj instanceof Parcelable) {
            putParcelable(str, (Parcelable) obj);
            return;
        }
        if (obj instanceof Size) {
            putSize(str, (Size) obj);
            return;
        }
        if (obj instanceof SizeF) {
            putSizeF(str, (SizeF) obj);
            return;
        }
        if (obj instanceof Parcelable[]) {
            putParcelableArray(str, (Parcelable[]) obj);
            return;
        }
        if (obj instanceof ArrayList) {
            putParcelableArrayList(str, (ArrayList) obj);
            return;
        }
        if (obj instanceof List) {
            putParcelableList(str, (List) obj);
            return;
        }
        if (obj instanceof SparseArray) {
            putSparseParcelableArray(str, (SparseArray) obj);
            return;
        }
        if (obj instanceof Serializable) {
            putSerializable(str, (Serializable) obj);
            return;
        }
        if (obj instanceof byte[]) {
            putByteArray(str, (byte[]) obj);
            return;
        }
        if (obj instanceof short[]) {
            putShortArray(str, (short[]) obj);
            return;
        }
        if (obj instanceof char[]) {
            putCharArray(str, (char[]) obj);
            return;
        }
        if (obj instanceof float[]) {
            putFloatArray(str, (float[]) obj);
            return;
        }
        if (obj instanceof CharSequence[]) {
            putCharSequenceArray(str, (CharSequence[]) obj);
            return;
        }
        if (obj instanceof Bundle) {
            putBundle(str, (Bundle) obj);
            return;
        }
        if (obj instanceof Binder) {
            putBinder(str, (Binder) obj);
        } else if (obj instanceof IBinder) {
            putIBinder(str, (IBinder) obj);
        } else {
            super.putObject(str, obj);
        }
    }

    @Override // android.os.BaseBundle
    public void putByte(String str, byte b) {
        super.putByte(str, b);
    }

    @Override // android.os.BaseBundle
    public void putChar(String str, char c) {
        super.putChar(str, c);
    }

    @Override // android.os.BaseBundle
    public void putShort(String str, short s) {
        super.putShort(str, s);
    }

    @Override // android.os.BaseBundle
    public void putFloat(String str, float f) {
        super.putFloat(str, f);
    }

    @Override // android.os.BaseBundle
    public void putCharSequence(String str, CharSequence charSequence) {
        super.putCharSequence(str, charSequence);
    }

    public void putParcelable(String str, Parcelable parcelable) {
        unparcel();
        this.mMap.put(str, parcelable);
        this.mFlags &= -513;
    }

    public void putSize(String str, Size size) {
        unparcel();
        this.mMap.put(str, size);
    }

    public void putSizeF(String str, SizeF sizeF) {
        unparcel();
        this.mMap.put(str, sizeF);
    }

    public void putParcelableArray(String str, Parcelable[] parcelableArr) {
        unparcel();
        this.mMap.put(str, parcelableArr);
        this.mFlags &= -513;
    }

    public void putParcelableArrayList(String str, ArrayList<? extends Parcelable> arrayList) {
        unparcel();
        this.mMap.put(str, arrayList);
        this.mFlags &= -513;
    }

    public void putParcelableList(String str, List<? extends Parcelable> list) {
        unparcel();
        this.mMap.put(str, list);
        this.mFlags &= -513;
    }

    public void putSparseParcelableArray(String str, SparseArray<? extends Parcelable> sparseArray) {
        unparcel();
        this.mMap.put(str, sparseArray);
        this.mFlags &= -513;
    }

    @Override // android.os.BaseBundle
    public void putIntegerArrayList(String str, ArrayList<Integer> arrayList) {
        super.putIntegerArrayList(str, arrayList);
    }

    @Override // android.os.BaseBundle
    public void putStringArrayList(String str, ArrayList<String> arrayList) {
        super.putStringArrayList(str, arrayList);
    }

    @Override // android.os.BaseBundle
    public void putCharSequenceArrayList(String str, ArrayList<CharSequence> arrayList) {
        super.putCharSequenceArrayList(str, arrayList);
    }

    @Override // android.os.BaseBundle
    public void putSerializable(String str, Serializable serializable) {
        super.putSerializable(str, serializable);
    }

    @Override // android.os.BaseBundle
    public void putByteArray(String str, byte[] bArr) {
        super.putByteArray(str, bArr);
    }

    @Override // android.os.BaseBundle
    public void putShortArray(String str, short[] sArr) {
        super.putShortArray(str, sArr);
    }

    @Override // android.os.BaseBundle
    public void putCharArray(String str, char[] cArr) {
        super.putCharArray(str, cArr);
    }

    @Override // android.os.BaseBundle
    public void putFloatArray(String str, float[] fArr) {
        super.putFloatArray(str, fArr);
    }

    @Override // android.os.BaseBundle
    public void putCharSequenceArray(String str, CharSequence[] charSequenceArr) {
        super.putCharSequenceArray(str, charSequenceArr);
    }

    public void putBundle(String str, Bundle bundle) {
        unparcel();
        this.mMap.put(str, bundle);
    }

    public void putBinder(String str, IBinder iBinder) {
        unparcel();
        this.mMap.put(str, iBinder);
    }

    @Deprecated
    public void putIBinder(String str, IBinder iBinder) {
        unparcel();
        this.mMap.put(str, iBinder);
    }

    @Override // android.os.BaseBundle
    public byte getByte(String str) {
        return super.getByte(str);
    }

    @Override // android.os.BaseBundle
    public Byte getByte(String str, byte b) {
        return super.getByte(str, b);
    }

    @Override // android.os.BaseBundle
    public char getChar(String str) {
        return super.getChar(str);
    }

    @Override // android.os.BaseBundle
    public char getChar(String str, char c) {
        return super.getChar(str, c);
    }

    @Override // android.os.BaseBundle
    public short getShort(String str) {
        return super.getShort(str);
    }

    @Override // android.os.BaseBundle
    public short getShort(String str, short s) {
        return super.getShort(str, s);
    }

    @Override // android.os.BaseBundle
    public float getFloat(String str) {
        return super.getFloat(str);
    }

    @Override // android.os.BaseBundle
    public float getFloat(String str, float f) {
        return super.getFloat(str, f);
    }

    @Override // android.os.BaseBundle
    public CharSequence getCharSequence(String str) {
        return super.getCharSequence(str);
    }

    @Override // android.os.BaseBundle
    public CharSequence getCharSequence(String str, CharSequence charSequence) {
        return super.getCharSequence(str, charSequence);
    }

    public Size getSize(String str) {
        unparcel();
        Object obj = this.mMap.get(str);
        try {
            return (Size) obj;
        } catch (ClassCastException e) {
            typeWarning(str, obj, "Size", e);
            return null;
        }
    }

    public SizeF getSizeF(String str) {
        unparcel();
        Object obj = this.mMap.get(str);
        try {
            return (SizeF) obj;
        } catch (ClassCastException e) {
            typeWarning(str, obj, "SizeF", e);
            return null;
        }
    }

    public Bundle getBundle(String str) {
        unparcel();
        Object obj = this.mMap.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (Bundle) obj;
        } catch (ClassCastException e) {
            typeWarning(str, obj, "Bundle", e);
            return null;
        }
    }

    public <T extends Parcelable> T getParcelable(String str) {
        unparcel();
        Object obj = this.mMap.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (T) obj;
        } catch (ClassCastException e) {
            typeWarning(str, obj, "Parcelable", e);
            return null;
        }
    }

    public Parcelable[] getParcelableArray(String str) {
        unparcel();
        Object obj = this.mMap.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (Parcelable[]) obj;
        } catch (ClassCastException e) {
            typeWarning(str, obj, "Parcelable[]", e);
            return null;
        }
    }

    public <T extends Parcelable> ArrayList<T> getParcelableArrayList(String str) {
        unparcel();
        Object obj = this.mMap.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (ArrayList) obj;
        } catch (ClassCastException e) {
            typeWarning(str, obj, "ArrayList", e);
            return null;
        }
    }

    public <T extends Parcelable> SparseArray<T> getSparseParcelableArray(String str) {
        unparcel();
        Object obj = this.mMap.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (SparseArray) obj;
        } catch (ClassCastException e) {
            typeWarning(str, obj, "SparseArray", e);
            return null;
        }
    }

    @Override // android.os.BaseBundle
    public Serializable getSerializable(String str) {
        return super.getSerializable(str);
    }

    @Override // android.os.BaseBundle
    public ArrayList<Integer> getIntegerArrayList(String str) {
        return super.getIntegerArrayList(str);
    }

    @Override // android.os.BaseBundle
    public ArrayList<String> getStringArrayList(String str) {
        return super.getStringArrayList(str);
    }

    @Override // android.os.BaseBundle
    public ArrayList<CharSequence> getCharSequenceArrayList(String str) {
        return super.getCharSequenceArrayList(str);
    }

    @Override // android.os.BaseBundle
    public byte[] getByteArray(String str) {
        return super.getByteArray(str);
    }

    @Override // android.os.BaseBundle
    public short[] getShortArray(String str) {
        return super.getShortArray(str);
    }

    @Override // android.os.BaseBundle
    public char[] getCharArray(String str) {
        return super.getCharArray(str);
    }

    @Override // android.os.BaseBundle
    public float[] getFloatArray(String str) {
        return super.getFloatArray(str);
    }

    @Override // android.os.BaseBundle
    public CharSequence[] getCharSequenceArray(String str) {
        return super.getCharSequenceArray(str);
    }

    public IBinder getBinder(String str) {
        unparcel();
        Object obj = this.mMap.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (IBinder) obj;
        } catch (ClassCastException e) {
            typeWarning(str, obj, "IBinder", e);
            return null;
        }
    }

    @Deprecated
    public IBinder getIBinder(String str) {
        unparcel();
        Object obj = this.mMap.get(str);
        if (obj == null) {
            return null;
        }
        try {
            return (IBinder) obj;
        } catch (ClassCastException e) {
            typeWarning(str, obj, "IBinder", e);
            return null;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return hasFileDescriptors() ? 1 : 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        boolean zPushAllowFds = parcel.pushAllowFds((this.mFlags & 1024) != 0);
        try {
            super.writeToParcelInner(parcel, i);
        } finally {
            parcel.restoreAllowFds(zPushAllowFds);
        }
    }

    public void readFromParcel(Parcel parcel) {
        super.readFromParcelInner(parcel);
        this.mFlags = 1024;
        maybePrefillHasFds();
    }

    public synchronized String toString() {
        if (this.mParcelledData != null) {
            if (isEmptyParcel()) {
                return "Bundle[EMPTY_PARCEL]";
            }
            return "Bundle[mParcelledData.dataSize=" + this.mParcelledData.dataSize() + "]";
        }
        return "Bundle[" + this.mMap.toString() + "]";
    }

    public synchronized String toShortString() {
        if (this.mParcelledData != null) {
            if (isEmptyParcel()) {
                return "EMPTY_PARCEL";
            }
            return "mParcelledData.dataSize=" + this.mParcelledData.dataSize();
        }
        return this.mMap.toString();
    }

    public void dumpDebug(ProtoOutputStream protoOutputStream, long j) {
        long jStart = protoOutputStream.start(j);
        if (this.mParcelledData != null) {
            if (isEmptyParcel()) {
                protoOutputStream.write(1120986464257L, 0);
            } else {
                protoOutputStream.write(1120986464257L, this.mParcelledData.dataSize());
            }
        } else {
            protoOutputStream.write(1138166333442L, this.mMap.toString());
        }
        protoOutputStream.end(jStart);
    }
}
