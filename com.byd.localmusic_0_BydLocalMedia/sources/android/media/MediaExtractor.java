package android.media;

import android.content.res.AssetFileDescriptor;
import android.media.DrmInitData;
import android.media.MediaCas;
import android.media.MediaCodec;
import android.os.IBinder;
import android.os.IHwBinder;
import android.os.PersistableBundle;
import com.android.internal.util.Preconditions;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaExtractor {
    public static final int SAMPLE_FLAG_ENCRYPTED = 2;
    public static final int SAMPLE_FLAG_PARTIAL_FRAME = 4;
    public static final int SAMPLE_FLAG_SYNC = 1;
    public static final int SEEK_TO_CLOSEST_SYNC = 2;
    public static final int SEEK_TO_NEXT_SYNC = 1;
    public static final int SEEK_TO_PREVIOUS_SYNC = 0;
    private MediaCas mMediaCas;
    private long mNativeContext;

    @Retention(RetentionPolicy.SOURCE)
    public @interface SampleFlag {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SeekMode {
    }

    private native Map<String, Object> getFileFormatNative();

    private native Map<String, Object> getTrackFormatNative(int i);

    static /* synthetic */ DrmInitData.SchemeInitData lambda$getDrmInitData$3(DrmInitData.SchemeInitData schemeInitData) {
        return schemeInitData;
    }

    private final native void nativeSetDataSource(IBinder iBinder, String str, String[] strArr, String[] strArr2) throws IOException;

    private final native void nativeSetMediaCas(IHwBinder iHwBinder);

    private final native void native_finalize();

    private native List<AudioPresentation> native_getAudioPresentations(int i);

    private native PersistableBundle native_getMetrics();

    private static final native void native_init();

    private final native void native_setup();

    public native boolean advance();

    public native long getCachedDuration();

    public native boolean getSampleCryptoInfo(MediaCodec.CryptoInfo cryptoInfo);

    public native int getSampleFlags();

    public native long getSampleSize();

    public native long getSampleTime();

    public native int getSampleTrackIndex();

    public final native int getTrackCount();

    public native boolean hasCacheReachedEndOfStream();

    public native int readSampleData(ByteBuffer byteBuffer, int i);

    public final native void release();

    public native void seekTo(long j, int i);

    public native void selectTrack(int i);

    public final native void setDataSource(MediaDataSource mediaDataSource) throws IOException;

    public final native void setDataSource(FileDescriptor fileDescriptor, long j, long j2) throws IOException;

    public native void unselectTrack(int i);

    public MediaExtractor() {
        native_setup();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setDataSource(android.content.Context r8, android.net.Uri r9, java.util.Map<java.lang.String, java.lang.String> r10) throws java.io.IOException {
        /*
            r7 = this;
            java.lang.String r0 = r9.getScheme()
            if (r0 == 0) goto L62
            java.lang.String r1 = "file"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto Lf
            goto L62
        Lf:
            r0 = 0
            android.content.ContentResolver r8 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L52 java.lang.SecurityException -> L55
            java.lang.String r1 = "r"
            android.content.res.AssetFileDescriptor r0 = r8.openAssetFileDescriptor(r9, r1)     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L52 java.lang.SecurityException -> L55
            if (r0 != 0) goto L23
            if (r0 == 0) goto L22
            r0.close()
        L22:
            return
        L23:
            long r1 = r0.getDeclaredLength()     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L52 java.lang.SecurityException -> L55
            r3 = 0
            int r8 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r8 >= 0) goto L35
            java.io.FileDescriptor r8 = r0.getFileDescriptor()     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L52 java.lang.SecurityException -> L55
            r7.setDataSource(r8)     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L52 java.lang.SecurityException -> L55
            goto L45
        L35:
            java.io.FileDescriptor r2 = r0.getFileDescriptor()     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L52 java.lang.SecurityException -> L55
            long r3 = r0.getStartOffset()     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L52 java.lang.SecurityException -> L55
            long r5 = r0.getDeclaredLength()     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L52 java.lang.SecurityException -> L55
            r1 = r7
            r1.setDataSource(r2, r3, r5)     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L52 java.lang.SecurityException -> L55
        L45:
            if (r0 == 0) goto L4a
            r0.close()
        L4a:
            return
        L4b:
            r8 = move-exception
            if (r0 == 0) goto L51
            r0.close()
        L51:
            throw r8
        L52:
            if (r0 == 0) goto L5a
            goto L57
        L55:
            if (r0 == 0) goto L5a
        L57:
            r0.close()
        L5a:
            java.lang.String r8 = r9.toString()
            r7.setDataSource(r8, r10)
            return
        L62:
            java.lang.String r8 = r9.getPath()
            r7.setDataSource(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.media.MediaExtractor.setDataSource(android.content.Context, android.net.Uri, java.util.Map):void");
    }

    public final void setDataSource(String str, Map<String, String> map) throws IOException {
        String[] strArr;
        String[] strArr2 = null;
        if (map != null) {
            strArr2 = new String[map.size()];
            strArr = new String[map.size()];
            int i = 0;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                strArr2[i] = entry.getKey();
                strArr[i] = entry.getValue();
                i++;
            }
        } else {
            strArr = null;
        }
        nativeSetDataSource(MediaHTTPService.createHttpServiceBinderIfNecessary(str), str, strArr2, strArr);
    }

    public final void setDataSource(String str) throws IOException {
        nativeSetDataSource(MediaHTTPService.createHttpServiceBinderIfNecessary(str), str, null, null);
    }

    public final void setDataSource(AssetFileDescriptor assetFileDescriptor) throws IllegalStateException, IOException, IllegalArgumentException {
        Preconditions.checkNotNull(assetFileDescriptor);
        if (assetFileDescriptor.getDeclaredLength() < 0) {
            setDataSource(assetFileDescriptor.getFileDescriptor());
        } else {
            setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getDeclaredLength());
        }
    }

    public final void setDataSource(FileDescriptor fileDescriptor) throws IOException {
        setDataSource(fileDescriptor, 0L, 576460752303423487L);
    }

    public final void setMediaCas(MediaCas mediaCas) {
        this.mMediaCas = mediaCas;
        nativeSetMediaCas(mediaCas.getBinder());
    }

    public static final class CasInfo {
        private final byte[] mPrivateData;
        private final MediaCas.Session mSession;
        private final int mSystemId;

        CasInfo(int i, MediaCas.Session session, byte[] bArr) {
            this.mSystemId = i;
            this.mSession = session;
            this.mPrivateData = bArr;
        }

        public int getSystemId() {
            return this.mSystemId;
        }

        public byte[] getPrivateData() {
            return this.mPrivateData;
        }

        public MediaCas.Session getSession() {
            return this.mSession;
        }
    }

    private ArrayList<Byte> toByteArray(byte[] bArr) {
        ArrayList<Byte> arrayList = new ArrayList<>(bArr.length);
        for (int i = 0; i < bArr.length; i++) {
            arrayList.add(i, Byte.valueOf(bArr[i]));
        }
        return arrayList;
    }

    public CasInfo getCasInfo(int i) {
        byte[] bArr;
        Map<String, Object> trackFormatNative = getTrackFormatNative(i);
        MediaCas.Session sessionCreateFromSessionId = null;
        if (!trackFormatNative.containsKey(MediaFormat.KEY_CA_SYSTEM_ID)) {
            return null;
        }
        int iIntValue = ((Integer) trackFormatNative.get(MediaFormat.KEY_CA_SYSTEM_ID)).intValue();
        if (trackFormatNative.containsKey(MediaFormat.KEY_CA_PRIVATE_DATA)) {
            ByteBuffer byteBuffer = (ByteBuffer) trackFormatNative.get(MediaFormat.KEY_CA_PRIVATE_DATA);
            byteBuffer.rewind();
            bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
        } else {
            bArr = null;
        }
        if (this.mMediaCas != null && trackFormatNative.containsKey(MediaFormat.KEY_CA_SESSION_ID)) {
            ByteBuffer byteBuffer2 = (ByteBuffer) trackFormatNative.get(MediaFormat.KEY_CA_SESSION_ID);
            byteBuffer2.rewind();
            byte[] bArr2 = new byte[byteBuffer2.remaining()];
            byteBuffer2.get(bArr2);
            sessionCreateFromSessionId = this.mMediaCas.createFromSessionId(toByteArray(bArr2));
        }
        return new CasInfo(iIntValue, sessionCreateFromSessionId, bArr);
    }

    protected void finalize() {
        native_finalize();
    }

    public DrmInitData getDrmInitData() {
        Map<String, Object> fileFormatNative = getFileFormatNative();
        if (fileFormatNative == null) {
            return null;
        }
        if (fileFormatNative.containsKey("pssh")) {
            final DrmInitData.SchemeInitData[] schemeInitDataArr = (DrmInitData.SchemeInitData[]) getPsshInfo().entrySet().stream().map(new Function() { // from class: android.media.-$$Lambda$MediaExtractor$AQWHHMUeHgJTkS0k0b0xoZxPezM
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return MediaExtractor.lambda$getDrmInitData$0((Map.Entry) obj);
                }
            }).toArray(new IntFunction() { // from class: android.media.-$$Lambda$MediaExtractor$XTtCu2qZqPfrj1LW7KOxdVVSgoY
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return MediaExtractor.lambda$getDrmInitData$1(i);
                }
            });
            final Map map = (Map) Arrays.stream(schemeInitDataArr).collect(Collectors.toMap(new Function() { // from class: android.media.-$$Lambda$MediaExtractor$R3OLaOzRieGuaOtoydwuYEiQATg
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((DrmInitData.SchemeInitData) obj).uuid;
                }
            }, new Function() { // from class: android.media.-$$Lambda$MediaExtractor$j4boqJIEvcYyVawgtNNWskNKVz8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return MediaExtractor.lambda$getDrmInitData$3((DrmInitData.SchemeInitData) obj);
                }
            }));
            return new DrmInitData() { // from class: android.media.MediaExtractor.1
                @Override // android.media.DrmInitData
                public DrmInitData.SchemeInitData get(UUID uuid) {
                    return (DrmInitData.SchemeInitData) map.get(uuid);
                }

                @Override // android.media.DrmInitData
                public int getSchemeInitDataCount() {
                    return schemeInitDataArr.length;
                }

                @Override // android.media.DrmInitData
                public DrmInitData.SchemeInitData getSchemeInitDataAt(int i) {
                    return schemeInitDataArr[i];
                }
            };
        }
        int trackCount = getTrackCount();
        for (int i = 0; i < trackCount; i++) {
            Map<String, Object> trackFormatNative = getTrackFormatNative(i);
            if (trackFormatNative.containsKey("crypto-key")) {
                ByteBuffer byteBuffer = (ByteBuffer) trackFormatNative.get("crypto-key");
                byteBuffer.rewind();
                byte[] bArr = new byte[byteBuffer.remaining()];
                byteBuffer.get(bArr);
                final DrmInitData.SchemeInitData schemeInitData = new DrmInitData.SchemeInitData(DrmInitData.SchemeInitData.UUID_NIL, "webm", bArr);
                return new DrmInitData() { // from class: android.media.MediaExtractor.2
                    @Override // android.media.DrmInitData
                    public int getSchemeInitDataCount() {
                        return 1;
                    }

                    @Override // android.media.DrmInitData
                    public DrmInitData.SchemeInitData get(UUID uuid) {
                        return schemeInitData;
                    }

                    @Override // android.media.DrmInitData
                    public DrmInitData.SchemeInitData getSchemeInitDataAt(int i2) {
                        return schemeInitData;
                    }
                };
            }
        }
        return null;
    }

    static /* synthetic */ DrmInitData.SchemeInitData lambda$getDrmInitData$0(Map.Entry entry) {
        return new DrmInitData.SchemeInitData((UUID) entry.getKey(), "cenc", (byte[]) entry.getValue());
    }

    static /* synthetic */ DrmInitData.SchemeInitData[] lambda$getDrmInitData$1(int i) {
        return new DrmInitData.SchemeInitData[i];
    }

    public List<AudioPresentation> getAudioPresentations(int i) {
        return native_getAudioPresentations(i);
    }

    public Map<UUID, byte[]> getPsshInfo() {
        Map<String, Object> fileFormatNative = getFileFormatNative();
        if (fileFormatNative == null || !fileFormatNative.containsKey("pssh")) {
            return null;
        }
        ByteBuffer byteBuffer = (ByteBuffer) fileFormatNative.get("pssh");
        byteBuffer.order(ByteOrder.nativeOrder());
        byteBuffer.rewind();
        fileFormatNative.remove("pssh");
        HashMap map = new HashMap();
        while (byteBuffer.remaining() > 0) {
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
            UUID uuid = new UUID(byteBuffer.getLong(), byteBuffer.getLong());
            byteBuffer.order(ByteOrder.nativeOrder());
            byte[] bArr = new byte[byteBuffer.getInt()];
            byteBuffer.get(bArr);
            map.put(uuid, bArr);
        }
        return map;
    }

    public MediaFormat getTrackFormat(int i) {
        return new MediaFormat(getTrackFormatNative(i));
    }

    public PersistableBundle getMetrics() {
        return native_getMetrics();
    }

    static {
        System.loadLibrary("media_jni");
        native_init();
    }

    public static final class MetricsConstants {
        public static final String FORMAT = "android.media.mediaextractor.fmt";
        public static final String MIME_TYPE = "android.media.mediaextractor.mime";
        public static final String TRACKS = "android.media.mediaextractor.ntrk";

        private MetricsConstants() {
        }
    }
}
