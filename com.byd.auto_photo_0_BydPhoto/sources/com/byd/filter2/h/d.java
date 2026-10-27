package com.byd.filter2.h;

import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.byd.util.w;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f2834a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2835b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f2836c;
    private long d;
    private long e;
    private long f;
    private b g = new b(this);
    private int h;
    private boolean i;
    private InterfaceC0078d j;

    private static class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<d> f2837a;

        b(d dVar) {
            super(Looper.getMainLooper());
            this.f2837a = new WeakReference<>(dVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                this.f2837a.get().r(((Integer) message.obj).intValue());
            } else if (i == 2) {
                this.f2837a.get().q();
            } else {
                if (i != 3) {
                    return;
                }
                this.f2837a.get().p();
            }
        }
    }

    private class c implements Runnable {
        private c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MediaMuxer mediaMuxer;
            long j;
            int iAddTrack;
            boolean z;
            long j2;
            boolean z2;
            MediaMuxer mediaMuxer2;
            long sampleTime;
            MediaExtractor mediaExtractor;
            long j3;
            int i;
            int i2;
            long j4;
            String str;
            MediaMuxer mediaMuxer3;
            long j5;
            int i3;
            try {
                mediaMuxer = new MediaMuxer(d.this.f2834a, 0);
            } catch (Exception e) {
                w.c("error path" + e.getMessage());
                mediaMuxer = null;
            }
            MediaMuxer mediaMuxer4 = mediaMuxer;
            if (mediaMuxer4 == null) {
                w.c("MediaMuxer init fail");
                d.this.g.sendEmptyMessage(3);
                return;
            }
            MediaExtractor mediaExtractor2 = new MediaExtractor();
            try {
                mediaExtractor2.setDataSource(d.this.f2836c);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            int iO = d.this.o(mediaExtractor2, true);
            int iAddTrack2 = -1;
            if (iO < 0) {
                z = false;
                iAddTrack = -1;
                j = 0;
            } else {
                MediaFormat trackFormat = mediaExtractor2.getTrackFormat(iO);
                j = trackFormat.getLong("durationUs");
                iAddTrack = mediaMuxer4.addTrack(trackFormat);
                mediaExtractor2.selectTrack(iO);
                z = true;
            }
            if (d.this.f > 0) {
                j = d.this.f;
            }
            MediaExtractor mediaExtractor3 = new MediaExtractor();
            try {
                mediaExtractor3.setDataSource(d.this.f2835b);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            int iO2 = d.this.o(mediaExtractor3, false);
            if (iO2 < 0) {
                z2 = false;
                j2 = 0;
            } else {
                MediaFormat trackFormat2 = mediaExtractor3.getTrackFormat(iO2);
                j2 = trackFormat2.getLong("durationUs");
                iAddTrack2 = mediaMuxer4.addTrack(trackFormat2);
                mediaExtractor3.selectTrack(iO2);
                z2 = true;
            }
            if (!z && !z2) {
                mediaExtractor2.release();
                mediaExtractor3.release();
                mediaMuxer4.release();
                w.c("no video and no audio");
                d.this.g.sendEmptyMessage(3);
                return;
            }
            mediaMuxer4.start();
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(1048576);
            String str2 = "m_tag";
            if (d.this.e <= 0 || !z2) {
                mediaMuxer2 = mediaMuxer4;
                sampleTime = 0;
            } else {
                mediaMuxer2 = mediaMuxer4;
                mediaExtractor3.seekTo(d.this.e, 2);
                sampleTime = mediaExtractor3.getSampleTime();
                Log.e("m_tag", "audio start : " + sampleTime);
            }
            int i4 = d.this.h;
            if (z && z2) {
                i4 /= 2;
            }
            long j6 = sampleTime;
            int i5 = iAddTrack2;
            int i6 = 0;
            int i7 = 0;
            long j7 = 0;
            while (true) {
                if (!d.this.i) {
                    break;
                }
                if (!z && !z2) {
                    w.c("mix finish !!!");
                    break;
                }
                boolean z3 = z2;
                if (z) {
                    mediaExtractor = mediaExtractor2;
                    j3 = j7;
                    i = iAddTrack;
                } else {
                    mediaExtractor = mediaExtractor3;
                    j3 = j6;
                    i = i5;
                }
                byteBufferAllocate.rewind();
                MediaExtractor mediaExtractor4 = mediaExtractor2;
                MediaExtractor mediaExtractor5 = mediaExtractor3;
                int sampleData = mediaExtractor.readSampleData(byteBufferAllocate, 0);
                if (sampleData < 0) {
                    i2 = iAddTrack;
                    j4 = j;
                    if (i == iAddTrack) {
                        z = false;
                        mediaMuxer3 = mediaMuxer2;
                        z2 = z3;
                    } else {
                        z2 = false;
                        mediaMuxer3 = mediaMuxer2;
                    }
                    str = str2;
                } else {
                    MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                    i2 = iAddTrack;
                    j4 = j;
                    long sampleTime2 = mediaExtractor.getSampleTime();
                    bufferInfo.offset = 0;
                    bufferInfo.size = sampleData;
                    if (z) {
                        bufferInfo.presentationTimeUs = j7;
                        j7 += d.this.d;
                        i6++;
                        str = str2;
                    } else {
                        Log.e(str2, "audio time : " + sampleTime2);
                        str = str2;
                        bufferInfo.presentationTimeUs = sampleTime2 - j3;
                        if (d.this.f > 0 && sampleTime2 >= j3 + d.this.f) {
                            z3 = false;
                        }
                    }
                    bufferInfo.flags = mediaExtractor.getSampleFlags();
                    byteBufferAllocate.rewind();
                    mediaMuxer3 = mediaMuxer2;
                    mediaMuxer3.writeSampleData(i, byteBufferAllocate, bufferInfo);
                    if (z) {
                        j5 = j7;
                        i3 = (int) ((bufferInfo.presentationTimeUs * ((long) i4)) / j4);
                    } else {
                        j5 = j7;
                        i3 = (int) ((bufferInfo.presentationTimeUs * ((long) i4)) / j2);
                    }
                    int i8 = i3 + i7;
                    d.this.g.obtainMessage(1, Integer.valueOf(i8)).sendToTarget();
                    if (i8 >= i4) {
                        i7 += i4;
                    }
                    mediaExtractor.advance();
                    z2 = z3;
                    j7 = j5;
                }
                mediaMuxer2 = mediaMuxer3;
                str2 = str;
                mediaExtractor2 = mediaExtractor4;
                mediaExtractor3 = mediaExtractor5;
                iAddTrack = i2;
                j = j4;
            }
            MediaExtractor mediaExtractor6 = mediaExtractor2;
            MediaMuxer mediaMuxer5 = mediaMuxer2;
            mediaMuxer5.stop();
            mediaMuxer5.release();
            d.this.g.obtainMessage(1, Integer.valueOf(d.this.h)).sendToTarget();
            mediaExtractor6.release();
            mediaExtractor3.release();
            w.c("mxi run end : " + i6);
            d.this.i = false;
            d.this.g.sendEmptyMessage(2);
        }
    }

    /* JADX INFO: renamed from: com.byd.filter2.h.d$d, reason: collision with other inner class name */
    public interface InterfaceC0078d {
        void a(String str);

        void b();

        void c(int i);
    }

    public d(String str, String str2, long j, String str3, long j2, long j3) {
        this.f2834a = str;
        this.f2835b = str3;
        this.e = j2;
        this.f = j3;
        this.f2836c = str2;
        this.d = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int o(MediaExtractor mediaExtractor, boolean z) {
        if (mediaExtractor == null) {
            w.c("mediaExtractor mediaExtractor is null");
            return -1;
        }
        String str = z ? "video/" : "audio/";
        int trackCount = mediaExtractor.getTrackCount();
        for (int i = 0; i < trackCount; i++) {
            String string = mediaExtractor.getTrackFormat(i).getString("mime");
            if (string != null && string.startsWith(str)) {
                return i;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        InterfaceC0078d interfaceC0078d = this.j;
        if (interfaceC0078d != null) {
            interfaceC0078d.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        InterfaceC0078d interfaceC0078d = this.j;
        if (interfaceC0078d != null) {
            interfaceC0078d.a(this.f2834a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(int i) {
        InterfaceC0078d interfaceC0078d = this.j;
        if (interfaceC0078d == null) {
            return;
        }
        interfaceC0078d.c(i);
    }

    public void s(InterfaceC0078d interfaceC0078d) {
        this.j = interfaceC0078d;
    }

    public void t(int i) {
        this.h = i;
    }

    public void u() {
        this.i = true;
        new Thread(new c()).start();
    }

    public void v() {
        if (this.i) {
            this.i = false;
        }
    }
}
