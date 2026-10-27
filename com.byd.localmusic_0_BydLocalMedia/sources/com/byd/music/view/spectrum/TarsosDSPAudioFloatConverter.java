package com.byd.music.view.spectrum;

import android.hardware.biometrics.BiometricManager;
import com.android.internal.midi.MidiConstants;
import com.byd.music.view.spectrum.TarsosDSPAudioFormat;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/* JADX INFO: loaded from: classes5.dex */
public abstract class TarsosDSPAudioFloatConverter {
    public static final TarsosDSPAudioFormat.Encoding PCM_FLOAT = new TarsosDSPAudioFormat.Encoding("PCM_FLOAT");
    private TarsosDSPAudioFormat format;

    public abstract byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3);

    public abstract float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3);

    private static class AudioFloatLSBFilter extends TarsosDSPAudioFloatConverter {
        private TarsosDSPAudioFloatConverter converter;
        private final byte mask;
        private byte[] mask_buffer;
        private final int offset;
        private final int stepsize;

        public AudioFloatLSBFilter(TarsosDSPAudioFloatConverter tarsosDSPAudioFloatConverter, TarsosDSPAudioFormat tarsosDSPAudioFormat) {
            int sampleSizeInBits = tarsosDSPAudioFormat.getSampleSizeInBits();
            boolean zIsBigEndian = tarsosDSPAudioFormat.isBigEndian();
            this.converter = tarsosDSPAudioFloatConverter;
            int i = (sampleSizeInBits + 7) / 8;
            this.stepsize = i;
            this.offset = zIsBigEndian ? i - 1 : 0;
            int i2 = sampleSizeInBits % 8;
            if (i2 == 0) {
                this.mask = (byte) 0;
                return;
            }
            if (i2 == 1) {
                this.mask = (byte) -128;
                return;
            }
            if (i2 == 2) {
                this.mask = (byte) -64;
                return;
            }
            if (i2 == 3) {
                this.mask = MidiConstants.STATUS_PITCH_BEND;
                return;
            }
            if (i2 == 4) {
                this.mask = (byte) -16;
                return;
            }
            if (i2 == 5) {
                this.mask = (byte) -8;
                return;
            }
            if (i2 == 6) {
                this.mask = (byte) -4;
            } else if (i2 == 7) {
                this.mask = (byte) -2;
            } else {
                this.mask = (byte) -1;
            }
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            byte[] byteArray = this.converter.toByteArray(fArr, i, i2, bArr, i3);
            int i4 = i2 * this.stepsize;
            int i5 = this.offset;
            while (true) {
                i3 += i5;
                if (i3 >= i4) {
                    return byteArray;
                }
                bArr[i3] = (byte) (bArr[i3] & this.mask);
                i5 = this.stepsize;
            }
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            byte[] bArr2 = this.mask_buffer;
            if (bArr2 == null || bArr2.length < bArr.length) {
                this.mask_buffer = new byte[bArr.length];
            }
            System.arraycopy(bArr, 0, this.mask_buffer, 0, bArr.length);
            int i4 = this.stepsize * i3;
            int i5 = this.offset + i;
            while (i5 < i4) {
                byte[] bArr3 = this.mask_buffer;
                bArr3[i5] = (byte) (bArr3[i5] & this.mask);
                i5 += this.stepsize;
            }
            return this.converter.toFloatArray(this.mask_buffer, i, fArr, i2, i3);
        }
    }

    private static class AudioFloatConversion64L extends TarsosDSPAudioFloatConverter {
        ByteBuffer bytebuffer;
        double[] double_buff;
        DoubleBuffer floatbuffer;

        private AudioFloatConversion64L() {
            this.bytebuffer = null;
            this.floatbuffer = null;
            this.double_buff = null;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = i3 * 8;
            ByteBuffer byteBuffer = this.bytebuffer;
            if (byteBuffer == null || byteBuffer.capacity() < i4) {
                ByteBuffer byteBufferOrder = ByteBuffer.allocate(i4).order(ByteOrder.LITTLE_ENDIAN);
                this.bytebuffer = byteBufferOrder;
                this.floatbuffer = byteBufferOrder.asDoubleBuffer();
            }
            this.bytebuffer.position(0);
            this.floatbuffer.position(0);
            this.bytebuffer.put(bArr, i, i4);
            double[] dArr = this.double_buff;
            if (dArr == null || dArr.length < i3 + i2) {
                this.double_buff = new double[i3 + i2];
            }
            this.floatbuffer.get(this.double_buff, i2, i3);
            int i5 = i3 + i2;
            while (i2 < i5) {
                fArr[i2] = (float) this.double_buff[i2];
                i2++;
            }
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = i2 * 8;
            ByteBuffer byteBuffer = this.bytebuffer;
            if (byteBuffer == null || byteBuffer.capacity() < i4) {
                ByteBuffer byteBufferOrder = ByteBuffer.allocate(i4).order(ByteOrder.LITTLE_ENDIAN);
                this.bytebuffer = byteBufferOrder;
                this.floatbuffer = byteBufferOrder.asDoubleBuffer();
            }
            this.floatbuffer.position(0);
            this.bytebuffer.position(0);
            double[] dArr = this.double_buff;
            if (dArr == null || dArr.length < i + i2) {
                this.double_buff = new double[i + i2];
            }
            int i5 = i + i2;
            for (int i6 = i; i6 < i5; i6++) {
                this.double_buff[i6] = fArr[i6];
            }
            this.floatbuffer.put(this.double_buff, i, i2);
            this.bytebuffer.get(bArr, i3, i4);
            return bArr;
        }
    }

    private static class AudioFloatConversion64B extends TarsosDSPAudioFloatConverter {
        ByteBuffer bytebuffer;
        double[] double_buff;
        DoubleBuffer floatbuffer;

        private AudioFloatConversion64B() {
            this.bytebuffer = null;
            this.floatbuffer = null;
            this.double_buff = null;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = i3 * 8;
            ByteBuffer byteBuffer = this.bytebuffer;
            if (byteBuffer == null || byteBuffer.capacity() < i4) {
                ByteBuffer byteBufferOrder = ByteBuffer.allocate(i4).order(ByteOrder.BIG_ENDIAN);
                this.bytebuffer = byteBufferOrder;
                this.floatbuffer = byteBufferOrder.asDoubleBuffer();
            }
            this.bytebuffer.position(0);
            this.floatbuffer.position(0);
            this.bytebuffer.put(bArr, i, i4);
            double[] dArr = this.double_buff;
            if (dArr == null || dArr.length < i3 + i2) {
                this.double_buff = new double[i3 + i2];
            }
            this.floatbuffer.get(this.double_buff, i2, i3);
            int i5 = i3 + i2;
            while (i2 < i5) {
                fArr[i2] = (float) this.double_buff[i2];
                i2++;
            }
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = i2 * 8;
            ByteBuffer byteBuffer = this.bytebuffer;
            if (byteBuffer == null || byteBuffer.capacity() < i4) {
                ByteBuffer byteBufferOrder = ByteBuffer.allocate(i4).order(ByteOrder.BIG_ENDIAN);
                this.bytebuffer = byteBufferOrder;
                this.floatbuffer = byteBufferOrder.asDoubleBuffer();
            }
            this.floatbuffer.position(0);
            this.bytebuffer.position(0);
            double[] dArr = this.double_buff;
            if (dArr == null || dArr.length < i + i2) {
                this.double_buff = new double[i + i2];
            }
            int i5 = i + i2;
            for (int i6 = i; i6 < i5; i6++) {
                this.double_buff[i6] = fArr[i6];
            }
            this.floatbuffer.put(this.double_buff, i, i2);
            this.bytebuffer.get(bArr, i3, i4);
            return bArr;
        }
    }

    private static class AudioFloatConversion32L extends TarsosDSPAudioFloatConverter {
        ByteBuffer bytebuffer;
        FloatBuffer floatbuffer;

        private AudioFloatConversion32L() {
            this.bytebuffer = null;
            this.floatbuffer = null;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = i3 * 4;
            ByteBuffer byteBuffer = this.bytebuffer;
            if (byteBuffer == null || byteBuffer.capacity() < i4) {
                ByteBuffer byteBufferOrder = ByteBuffer.allocate(i4).order(ByteOrder.LITTLE_ENDIAN);
                this.bytebuffer = byteBufferOrder;
                this.floatbuffer = byteBufferOrder.asFloatBuffer();
            }
            this.bytebuffer.position(0);
            this.floatbuffer.position(0);
            this.bytebuffer.put(bArr, i, i4);
            this.floatbuffer.get(fArr, i2, i3);
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = i2 * 4;
            ByteBuffer byteBuffer = this.bytebuffer;
            if (byteBuffer == null || byteBuffer.capacity() < i4) {
                ByteBuffer byteBufferOrder = ByteBuffer.allocate(i4).order(ByteOrder.LITTLE_ENDIAN);
                this.bytebuffer = byteBufferOrder;
                this.floatbuffer = byteBufferOrder.asFloatBuffer();
            }
            this.floatbuffer.position(0);
            this.bytebuffer.position(0);
            this.floatbuffer.put(fArr, i, i2);
            this.bytebuffer.get(bArr, i3, i4);
            return bArr;
        }
    }

    private static class AudioFloatConversion32B extends TarsosDSPAudioFloatConverter {
        ByteBuffer bytebuffer;
        FloatBuffer floatbuffer;

        private AudioFloatConversion32B() {
            this.bytebuffer = null;
            this.floatbuffer = null;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = i3 * 4;
            ByteBuffer byteBuffer = this.bytebuffer;
            if (byteBuffer == null || byteBuffer.capacity() < i4) {
                ByteBuffer byteBufferOrder = ByteBuffer.allocate(i4).order(ByteOrder.BIG_ENDIAN);
                this.bytebuffer = byteBufferOrder;
                this.floatbuffer = byteBufferOrder.asFloatBuffer();
            }
            this.bytebuffer.position(0);
            this.floatbuffer.position(0);
            this.bytebuffer.put(bArr, i, i4);
            this.floatbuffer.get(fArr, i2, i3);
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = i2 * 4;
            ByteBuffer byteBuffer = this.bytebuffer;
            if (byteBuffer == null || byteBuffer.capacity() < i4) {
                ByteBuffer byteBufferOrder = ByteBuffer.allocate(i4).order(ByteOrder.BIG_ENDIAN);
                this.bytebuffer = byteBufferOrder;
                this.floatbuffer = byteBufferOrder.asFloatBuffer();
            }
            this.floatbuffer.position(0);
            this.bytebuffer.position(0);
            this.floatbuffer.put(fArr, i, i2);
            this.bytebuffer.get(bArr, i3, i4);
            return bArr;
        }
    }

    private static class AudioFloatConversion8S extends TarsosDSPAudioFloatConverter {
        private AudioFloatConversion8S() {
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                fArr[i2] = bArr[i] * 0.007874016f;
                i4++;
                i2++;
                i++;
            }
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                bArr[i3] = (byte) (fArr[i] * 127.0f);
                i4++;
                i3++;
                i++;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion8U extends TarsosDSPAudioFloatConverter {
        private AudioFloatConversion8U() {
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                fArr[i2] = ((bArr[i] & 255) - 127) * 0.007874016f;
                i4++;
                i2++;
                i++;
            }
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                bArr[i3] = (byte) ((fArr[i] * 127.0f) + 127.0f);
                i4++;
                i3++;
                i++;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion16SL extends TarsosDSPAudioFloatConverter {
        private AudioFloatConversion16SL() {
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = i3 + i2;
            while (i2 < i4) {
                fArr[i2] = ((short) ((bArr[i] & 255) | (bArr[r0] << 8))) * 3.051851E-5f;
                i2++;
                i = i + 1 + 1;
            }
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = i2 + i;
            while (i < i4) {
                int i5 = (int) (((double) fArr[i]) * 32767.0d);
                int i6 = i3 + 1;
                bArr[i3] = (byte) i5;
                i3 = i6 + 1;
                bArr[i6] = (byte) (i5 >>> 8);
                i++;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion16SB extends TarsosDSPAudioFloatConverter {
        private AudioFloatConversion16SB() {
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                fArr[i2] = ((short) ((bArr[i] << 8) | (bArr[r2] & 255))) * 3.051851E-5f;
                i4++;
                i2++;
                i = i + 1 + 1;
            }
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = (int) (((double) fArr[i]) * 32767.0d);
                int i7 = i3 + 1;
                bArr[i3] = (byte) (i6 >>> 8);
                i3 = i7 + 1;
                bArr[i7] = (byte) i6;
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion16UL extends TarsosDSPAudioFloatConverter {
        private AudioFloatConversion16UL() {
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                fArr[i2] = (((bArr[i] & 255) | ((bArr[r1] & 255) << 8)) - 32767) * 3.051851E-5f;
                i4++;
                i2++;
                i = i + 1 + 1;
            }
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = ((int) (((double) fArr[i]) * 32767.0d)) + BiometricManager.Authenticators.BIOMETRIC_MIN_STRENGTH;
                int i7 = i3 + 1;
                bArr[i3] = (byte) i6;
                i3 = i7 + 1;
                bArr[i7] = (byte) (i6 >>> 8);
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion16UB extends TarsosDSPAudioFloatConverter {
        private AudioFloatConversion16UB() {
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                fArr[i2] = ((((bArr[i] & 255) << 8) | (bArr[r1] & 255)) - 32767) * 3.051851E-5f;
                i4++;
                i2++;
                i = i + 1 + 1;
            }
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = ((int) (((double) fArr[i]) * 32767.0d)) + BiometricManager.Authenticators.BIOMETRIC_MIN_STRENGTH;
                int i7 = i3 + 1;
                bArr[i3] = (byte) (i6 >>> 8);
                i3 = i7 + 1;
                bArr[i7] = (byte) i6;
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion24SL extends TarsosDSPAudioFloatConverter {
        private AudioFloatConversion24SL() {
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                int i5 = i + 1;
                int i6 = i5 + 1;
                int i7 = (bArr[i] & 255) | ((bArr[i5] & 255) << 8);
                int i8 = i6 + 1;
                int i9 = i7 | ((bArr[i6] & 255) << 16);
                if (i9 > 8388607) {
                    i9 -= 16777216;
                }
                fArr[i2] = i9 * 1.192093E-7f;
                i4++;
                i = i8;
                i2++;
            }
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = (int) (fArr[i] * 8388607.0f);
                if (i6 < 0) {
                    i6 += 16777216;
                }
                int i7 = i3 + 1;
                bArr[i3] = (byte) i6;
                int i8 = i7 + 1;
                bArr[i7] = (byte) (i6 >>> 8);
                bArr[i8] = (byte) (i6 >>> 16);
                i4++;
                i = i5;
                i3 = i8 + 1;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion24SB extends TarsosDSPAudioFloatConverter {
        private AudioFloatConversion24SB() {
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                int i5 = i + 1;
                int i6 = i5 + 1;
                int i7 = ((bArr[i] & 255) << 16) | ((bArr[i5] & 255) << 8);
                int i8 = i6 + 1;
                int i9 = i7 | (bArr[i6] & 255);
                if (i9 > 8388607) {
                    i9 -= 16777216;
                }
                fArr[i2] = i9 * 1.192093E-7f;
                i4++;
                i = i8;
                i2++;
            }
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = (int) (fArr[i] * 8388607.0f);
                if (i6 < 0) {
                    i6 += 16777216;
                }
                int i7 = i3 + 1;
                bArr[i3] = (byte) (i6 >>> 16);
                int i8 = i7 + 1;
                bArr[i7] = (byte) (i6 >>> 8);
                bArr[i8] = (byte) i6;
                i4++;
                i = i5;
                i3 = i8 + 1;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion24UL extends TarsosDSPAudioFloatConverter {
        private AudioFloatConversion24UL() {
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                int i5 = i + 1;
                int i6 = (bArr[i] & 255) | ((bArr[i5] & 255) << 8);
                fArr[i2] = ((i6 | ((bArr[r2] & 255) << 16)) - 8388607) * 1.192093E-7f;
                i4++;
                i = i5 + 1 + 1;
                i2++;
            }
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = ((int) (fArr[i] * 8388607.0f)) + 8388607;
                int i7 = i3 + 1;
                bArr[i3] = (byte) i6;
                int i8 = i7 + 1;
                bArr[i7] = (byte) (i6 >>> 8);
                bArr[i8] = (byte) (i6 >>> 16);
                i4++;
                i = i5;
                i3 = i8 + 1;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion24UB extends TarsosDSPAudioFloatConverter {
        private AudioFloatConversion24UB() {
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                int i5 = i + 1;
                int i6 = ((bArr[i] & 255) << 16) | ((bArr[i5] & 255) << 8);
                fArr[i2] = ((i6 | (bArr[r2] & 255)) - 8388607) * 1.192093E-7f;
                i4++;
                i = i5 + 1 + 1;
                i2++;
            }
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = ((int) (fArr[i] * 8388607.0f)) + 8388607;
                int i7 = i3 + 1;
                bArr[i3] = (byte) (i6 >>> 16);
                int i8 = i7 + 1;
                bArr[i7] = (byte) (i6 >>> 8);
                bArr[i8] = (byte) i6;
                i4++;
                i = i5;
                i3 = i8 + 1;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion32SL extends TarsosDSPAudioFloatConverter {
        private AudioFloatConversion32SL() {
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                int i5 = i + 1;
                int i6 = i5 + 1;
                int i7 = (bArr[i] & 255) | ((bArr[i5] & 255) << 8);
                int i8 = i7 | ((bArr[i6] & 255) << 16);
                fArr[i2] = (i8 | ((bArr[r1] & 255) << 24)) * 4.656613E-10f;
                i4++;
                i2++;
                i = i6 + 1 + 1;
            }
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = (int) (fArr[i] * 2.1474836E9f);
                int i7 = i3 + 1;
                bArr[i3] = (byte) i6;
                int i8 = i7 + 1;
                bArr[i7] = (byte) (i6 >>> 8);
                int i9 = i8 + 1;
                bArr[i8] = (byte) (i6 >>> 16);
                i3 = i9 + 1;
                bArr[i9] = (byte) (i6 >>> 24);
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion32SB extends TarsosDSPAudioFloatConverter {
        private AudioFloatConversion32SB() {
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                int i5 = i + 1;
                int i6 = i5 + 1;
                int i7 = ((bArr[i] & 255) << 24) | ((bArr[i5] & 255) << 16);
                int i8 = i7 | ((bArr[i6] & 255) << 8);
                fArr[i2] = (i8 | (bArr[r1] & 255)) * 4.656613E-10f;
                i4++;
                i2++;
                i = i6 + 1 + 1;
            }
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = (int) (fArr[i] * 2.1474836E9f);
                int i7 = i3 + 1;
                bArr[i3] = (byte) (i6 >>> 24);
                int i8 = i7 + 1;
                bArr[i7] = (byte) (i6 >>> 16);
                int i9 = i8 + 1;
                bArr[i8] = (byte) (i6 >>> 8);
                i3 = i9 + 1;
                bArr[i9] = (byte) i6;
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion32UL extends TarsosDSPAudioFloatConverter {
        private AudioFloatConversion32UL() {
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                int i5 = i + 1;
                int i6 = i5 + 1;
                int i7 = (bArr[i] & 255) | ((bArr[i5] & 255) << 8);
                int i8 = i7 | ((bArr[i6] & 255) << 16);
                fArr[i2] = ((i8 | ((bArr[r1] & 255) << 24)) - Integer.MAX_VALUE) * 4.656613E-10f;
                i4++;
                i2++;
                i = i6 + 1 + 1;
            }
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = ((int) (fArr[i] * 2.1474836E9f)) + Integer.MAX_VALUE;
                int i7 = i3 + 1;
                bArr[i3] = (byte) i6;
                int i8 = i7 + 1;
                bArr[i7] = (byte) (i6 >>> 8);
                int i9 = i8 + 1;
                bArr[i8] = (byte) (i6 >>> 16);
                i3 = i9 + 1;
                bArr[i9] = (byte) (i6 >>> 24);
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion32UB extends TarsosDSPAudioFloatConverter {
        private AudioFloatConversion32UB() {
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                int i5 = i + 1;
                int i6 = i5 + 1;
                int i7 = ((bArr[i] & 255) << 24) | ((bArr[i5] & 255) << 16);
                int i8 = i7 | ((bArr[i6] & 255) << 8);
                fArr[i2] = ((i8 | (bArr[r1] & 255)) - Integer.MAX_VALUE) * 4.656613E-10f;
                i4++;
                i2++;
                i = i6 + 1 + 1;
            }
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = ((int) (fArr[i] * 2.1474836E9f)) + Integer.MAX_VALUE;
                int i7 = i3 + 1;
                bArr[i3] = (byte) (i6 >>> 24);
                int i8 = i7 + 1;
                bArr[i7] = (byte) (i6 >>> 16);
                int i9 = i8 + 1;
                bArr[i8] = (byte) (i6 >>> 8);
                i3 = i9 + 1;
                bArr[i9] = (byte) i6;
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion32xSL extends TarsosDSPAudioFloatConverter {
        final int xbytes;

        public AudioFloatConversion32xSL(int i) {
            this.xbytes = i;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                int i5 = i + this.xbytes;
                int i6 = i5 + 1;
                int i7 = i6 + 1;
                int i8 = (bArr[i5] & 255) | ((bArr[i6] & 255) << 8);
                int i9 = i8 | ((bArr[i7] & 255) << 16);
                fArr[i2] = (i9 | ((bArr[r1] & 255) << 24)) * 4.656613E-10f;
                i4++;
                i2++;
                i = i7 + 1 + 1;
            }
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = (int) (fArr[i] * 2.1474836E9f);
                int i7 = 0;
                while (i7 < this.xbytes) {
                    bArr[i3] = 0;
                    i7++;
                    i3++;
                }
                int i8 = i3 + 1;
                bArr[i3] = (byte) i6;
                int i9 = i8 + 1;
                bArr[i8] = (byte) (i6 >>> 8);
                int i10 = i9 + 1;
                bArr[i9] = (byte) (i6 >>> 16);
                i3 = i10 + 1;
                bArr[i10] = (byte) (i6 >>> 24);
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion32xSB extends TarsosDSPAudioFloatConverter {
        final int xbytes;

        public AudioFloatConversion32xSB(int i) {
            this.xbytes = i;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                int i5 = i + 1;
                int i6 = i5 + 1;
                int i7 = ((bArr[i] & 255) << 24) | ((bArr[i5] & 255) << 16);
                int i8 = i6 + 1;
                int i9 = i7 | ((bArr[i6] & 255) << 8) | (bArr[i8] & 255);
                int i10 = this.xbytes + i8 + 1;
                fArr[i2] = i9 * 4.656613E-10f;
                i4++;
                i = i10;
                i2++;
            }
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = (int) (fArr[i] * 2.1474836E9f);
                int i7 = i3 + 1;
                bArr[i3] = (byte) (i6 >>> 24);
                int i8 = i7 + 1;
                bArr[i7] = (byte) (i6 >>> 16);
                int i9 = i8 + 1;
                bArr[i8] = (byte) (i6 >>> 8);
                i3 = i9 + 1;
                bArr[i9] = (byte) i6;
                int i10 = 0;
                while (i10 < this.xbytes) {
                    bArr[i3] = 0;
                    i10++;
                    i3++;
                }
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion32xUL extends TarsosDSPAudioFloatConverter {
        final int xbytes;

        public AudioFloatConversion32xUL(int i) {
            this.xbytes = i;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                int i5 = i + this.xbytes;
                int i6 = i5 + 1;
                int i7 = i6 + 1;
                int i8 = (bArr[i5] & 255) | ((bArr[i6] & 255) << 8);
                int i9 = i8 | ((bArr[i7] & 255) << 16);
                fArr[i2] = ((i9 | ((bArr[r1] & 255) << 24)) - Integer.MAX_VALUE) * 4.656613E-10f;
                i4++;
                i2++;
                i = i7 + 1 + 1;
            }
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = ((int) (fArr[i] * 2.1474836E9f)) + Integer.MAX_VALUE;
                int i7 = 0;
                while (i7 < this.xbytes) {
                    bArr[i3] = 0;
                    i7++;
                    i3++;
                }
                int i8 = i3 + 1;
                bArr[i3] = (byte) i6;
                int i9 = i8 + 1;
                bArr[i8] = (byte) (i6 >>> 8);
                int i10 = i9 + 1;
                bArr[i9] = (byte) (i6 >>> 16);
                i3 = i10 + 1;
                bArr[i10] = (byte) (i6 >>> 24);
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion32xUB extends TarsosDSPAudioFloatConverter {
        final int xbytes;

        public AudioFloatConversion32xUB(int i) {
            this.xbytes = i;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                int i5 = i + 1;
                int i6 = i5 + 1;
                int i7 = ((bArr[i] & 255) << 24) | ((bArr[i5] & 255) << 16);
                int i8 = i6 + 1;
                int i9 = i7 | ((bArr[i6] & 255) << 8) | (bArr[i8] & 255);
                int i10 = this.xbytes + i8 + 1;
                fArr[i2] = (i9 - Integer.MAX_VALUE) * 4.656613E-10f;
                i4++;
                i = i10;
                i2++;
            }
            return fArr;
        }

        @Override // com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter
        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = ((int) (((double) fArr[i]) * 2.147483647E9d)) + Integer.MAX_VALUE;
                int i7 = i3 + 1;
                bArr[i3] = (byte) (i6 >>> 24);
                int i8 = i7 + 1;
                bArr[i7] = (byte) (i6 >>> 16);
                int i9 = i8 + 1;
                bArr[i8] = (byte) (i6 >>> 8);
                i3 = i9 + 1;
                bArr[i9] = (byte) i6;
                int i10 = 0;
                while (i10 < this.xbytes) {
                    bArr[i3] = 0;
                    i10++;
                    i3++;
                }
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0208  */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter$1] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter getConverter(com.byd.music.view.spectrum.TarsosDSPAudioFormat r7) {
        /*
            Method dump skipped, instruction units count: 538
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter.getConverter(com.byd.music.view.spectrum.TarsosDSPAudioFormat):com.byd.music.view.spectrum.TarsosDSPAudioFloatConverter");
    }

    public TarsosDSPAudioFormat getFormat() {
        return this.format;
    }

    public float[] toFloatArray(byte[] bArr, float[] fArr, int i, int i2) {
        return toFloatArray(bArr, 0, fArr, i, i2);
    }

    public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2) {
        return toFloatArray(bArr, i, fArr, 0, i2);
    }

    public float[] toFloatArray(byte[] bArr, float[] fArr, int i) {
        return toFloatArray(bArr, 0, fArr, 0, i);
    }

    public float[] toFloatArray(byte[] bArr, float[] fArr) {
        return toFloatArray(bArr, 0, fArr, 0, fArr.length);
    }

    public byte[] toByteArray(float[] fArr, int i, byte[] bArr, int i2) {
        return toByteArray(fArr, 0, i, bArr, i2);
    }

    public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr) {
        return toByteArray(fArr, i, i2, bArr, 0);
    }

    public byte[] toByteArray(float[] fArr, int i, byte[] bArr) {
        return toByteArray(fArr, 0, i, bArr, 0);
    }

    public byte[] toByteArray(float[] fArr, byte[] bArr) {
        return toByteArray(fArr, 0, fArr.length, bArr, 0);
    }
}
