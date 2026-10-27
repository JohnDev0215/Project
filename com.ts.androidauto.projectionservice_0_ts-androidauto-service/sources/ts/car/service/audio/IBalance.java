package ts.car.service.audio;

/* JADX INFO: loaded from: classes2.dex */
public interface IBalance {
    int getBalanceTowardRightLevel();

    int[] getBalanceTowardRightLevelRange();

    int getFadeTowardFrontLevel();

    int[] getFadeTowardFrontLevelRange();

    boolean isBalanceSupported();

    boolean isFadeSupported();

    void setBalanceTowardRightLevel(int i);

    void setFadeTowardFrontLevel(int i);
}
