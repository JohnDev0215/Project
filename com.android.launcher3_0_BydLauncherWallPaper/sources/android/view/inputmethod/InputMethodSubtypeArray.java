package android.view.inputmethod;

import android.os.Parcel;
import android.util.Slog;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: classes3.dex */
public class InputMethodSubtypeArray {
    private static final String TAG = "InputMethodSubtypeArray";
    private volatile byte[] mCompressedData;
    private final int mCount;
    private volatile int mDecompressedSize;
    private volatile InputMethodSubtype[] mInstance;
    private final Object mLockObject = new Object();

    public InputMethodSubtypeArray(List<InputMethodSubtype> list) {
        if (list == null) {
            this.mCount = 0;
            return;
        }
        int size = list.size();
        this.mCount = size;
        this.mInstance = (InputMethodSubtype[]) list.toArray(new InputMethodSubtype[size]);
    }

    public InputMethodSubtypeArray(Parcel parcel) {
        int i = parcel.readInt();
        this.mCount = i;
        if (i > 0) {
            this.mDecompressedSize = parcel.readInt();
            this.mCompressedData = parcel.createByteArray();
        }
    }

    public void writeToParcel(Parcel parcel) {
        int length;
        int i = this.mCount;
        if (i == 0) {
            parcel.writeInt(i);
            return;
        }
        byte[] bArr = this.mCompressedData;
        int i2 = this.mDecompressedSize;
        if (bArr == null && i2 == 0) {
            synchronized (this.mLockObject) {
                bArr = this.mCompressedData;
                i2 = this.mDecompressedSize;
                if (bArr == null && i2 == 0) {
                    byte[] bArrMarshall = marshall(this.mInstance);
                    byte[] bArrCompress = compress(bArrMarshall);
                    if (bArrCompress == null) {
                        length = -1;
                        Slog.i(TAG, "Failed to compress data.");
                    } else {
                        length = bArrMarshall.length;
                    }
                    this.mDecompressedSize = length;
                    this.mCompressedData = bArrCompress;
                    i2 = length;
                    bArr = bArrCompress;
                }
            }
        }
        if (bArr != null && i2 > 0) {
            parcel.writeInt(this.mCount);
            parcel.writeInt(i2);
            parcel.writeByteArray(bArr);
        } else {
            Slog.i(TAG, "Unexpected state. Behaving as an empty array.");
            parcel.writeInt(0);
        }
    }

    public InputMethodSubtype get(int i) {
        if (i < 0 || this.mCount <= i) {
            throw new ArrayIndexOutOfBoundsException();
        }
        InputMethodSubtype[] inputMethodSubtypeArrUnmarshall = this.mInstance;
        if (inputMethodSubtypeArrUnmarshall == null) {
            synchronized (this.mLockObject) {
                inputMethodSubtypeArrUnmarshall = this.mInstance;
                if (inputMethodSubtypeArrUnmarshall == null) {
                    byte[] bArrDecompress = decompress(this.mCompressedData, this.mDecompressedSize);
                    this.mCompressedData = null;
                    this.mDecompressedSize = 0;
                    if (bArrDecompress != null) {
                        inputMethodSubtypeArrUnmarshall = unmarshall(bArrDecompress);
                    } else {
                        Slog.e(TAG, "Failed to decompress data. Returns null as fallback.");
                        inputMethodSubtypeArrUnmarshall = new InputMethodSubtype[this.mCount];
                    }
                    this.mInstance = inputMethodSubtypeArrUnmarshall;
                }
            }
        }
        return inputMethodSubtypeArrUnmarshall[i];
    }

    public int getCount() {
        return this.mCount;
    }

    private static byte[] marshall(InputMethodSubtype[] inputMethodSubtypeArr) throws Throwable {
        Parcel parcelObtain;
        try {
            parcelObtain = Parcel.obtain();
        } catch (Throwable th) {
            th = th;
            parcelObtain = null;
        }
        try {
            parcelObtain.writeTypedArray(inputMethodSubtypeArr, 0);
            byte[] bArrMarshall = parcelObtain.marshall();
            if (parcelObtain != null) {
                parcelObtain.recycle();
            }
            return bArrMarshall;
        } catch (Throwable th2) {
            th = th2;
            if (parcelObtain != null) {
                parcelObtain.recycle();
            }
            throw th;
        }
    }

    private static InputMethodSubtype[] unmarshall(byte[] bArr) throws Throwable {
        Parcel parcelObtain;
        try {
            parcelObtain = Parcel.obtain();
        } catch (Throwable th) {
            th = th;
            parcelObtain = null;
        }
        try {
            parcelObtain.unmarshall(bArr, 0, bArr.length);
            parcelObtain.setDataPosition(0);
            InputMethodSubtype[] inputMethodSubtypeArr = (InputMethodSubtype[]) parcelObtain.createTypedArray(InputMethodSubtype.CREATOR);
            if (parcelObtain != null) {
                parcelObtain.recycle();
            }
            return inputMethodSubtypeArr;
        } catch (Throwable th2) {
            th = th2;
            if (parcelObtain != null) {
                parcelObtain.recycle();
            }
            throw th;
        }
    }

    private static byte[] compress(byte[] bArr) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream.write(bArr);
                    gZIPOutputStream.finish();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    gZIPOutputStream.close();
                    byteArrayOutputStream.close();
                    return byteArray;
                } finally {
                }
            } finally {
            }
        } catch (Exception e) {
            Slog.e(TAG, "Failed to compress the data.", e);
            return null;
        }
    }

    private static byte[] decompress(byte[] bArr, int i) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                try {
                    byte[] bArr2 = new byte[i];
                    int i2 = 0;
                    while (i2 < i) {
                        int i3 = gZIPInputStream.read(bArr2, i2, i - i2);
                        if (i3 < 0) {
                            break;
                        }
                        i2 += i3;
                    }
                    if (i != i2) {
                        gZIPInputStream.close();
                        byteArrayInputStream.close();
                        return null;
                    }
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return bArr2;
                } finally {
                }
            } finally {
            }
        } catch (Exception e) {
            Slog.e(TAG, "Failed to decompress the data.", e);
            return null;
        }
    }
}
