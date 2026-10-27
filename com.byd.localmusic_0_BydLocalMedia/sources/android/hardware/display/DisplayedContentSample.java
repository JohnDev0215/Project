package android.hardware.display;

/* JADX INFO: loaded from: classes2.dex */
public final class DisplayedContentSample {
    private long mNumFrames;
    private long[] mSamplesComponent0;
    private long[] mSamplesComponent1;
    private long[] mSamplesComponent2;
    private long[] mSamplesComponent3;

    public enum ColorComponent {
        CHANNEL0,
        CHANNEL1,
        CHANNEL2,
        CHANNEL3
    }

    public DisplayedContentSample(long j, long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4) {
        this.mNumFrames = j;
        this.mSamplesComponent0 = jArr;
        this.mSamplesComponent1 = jArr2;
        this.mSamplesComponent2 = jArr3;
        this.mSamplesComponent3 = jArr4;
    }

    /* JADX INFO: renamed from: android.hardware.display.DisplayedContentSample$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$hardware$display$DisplayedContentSample$ColorComponent;

        static {
            int[] iArr = new int[ColorComponent.values().length];
            $SwitchMap$android$hardware$display$DisplayedContentSample$ColorComponent = iArr;
            try {
                iArr[ColorComponent.CHANNEL0.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$hardware$display$DisplayedContentSample$ColorComponent[ColorComponent.CHANNEL1.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$hardware$display$DisplayedContentSample$ColorComponent[ColorComponent.CHANNEL2.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$hardware$display$DisplayedContentSample$ColorComponent[ColorComponent.CHANNEL3.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public long[] getSampleComponent(ColorComponent colorComponent) {
        int i = AnonymousClass1.$SwitchMap$android$hardware$display$DisplayedContentSample$ColorComponent[colorComponent.ordinal()];
        if (i == 1) {
            return this.mSamplesComponent0;
        }
        if (i == 2) {
            return this.mSamplesComponent1;
        }
        if (i == 3) {
            return this.mSamplesComponent2;
        }
        if (i == 4) {
            return this.mSamplesComponent3;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public long getNumFrames() {
        return this.mNumFrames;
    }
}
