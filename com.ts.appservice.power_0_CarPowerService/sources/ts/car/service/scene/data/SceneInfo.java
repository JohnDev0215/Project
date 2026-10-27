package ts.car.service.scene.data;

import android.graphics.Rect;

/* JADX INFO: loaded from: classes.dex */
public final class SceneInfo {
    private Rect mBounds;
    private boolean mIsLastMode;
    private int mSceneState;
    private int mSceneType;
    private String mPackageName = "";
    private String mClassName = "";
    private String mIgnoreClass = "";
    private String mWindowTitle = "";
    private boolean mIsHaveBoundFlag = false;
    private boolean mIsHaveClassName = false;
    private boolean mIsHaveTitle = false;
    private boolean mIsHaveIgnoreClass = false;

    public int getSceneType() {
        return this.mSceneType;
    }

    public void setSceneType(int sceneType) {
        this.mSceneType = sceneType;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public void setPackageName(String packageName) {
        this.mPackageName = packageName;
    }

    public String getClassName() {
        return this.mClassName;
    }

    public void setClassName(String className) {
        this.mClassName = className;
    }

    public String getIgnoreClass() {
        return this.mIgnoreClass;
    }

    public void setIgnoreClass(String ignoreClass) {
        this.mIgnoreClass = ignoreClass;
    }

    public boolean isLastMode() {
        return this.mIsLastMode;
    }

    public void setLastMode(boolean isLastMode) {
        this.mIsLastMode = isLastMode;
    }

    public String getWindowTitle() {
        return this.mWindowTitle;
    }

    public void setWindowTitle(String windowTitle) {
        this.mWindowTitle = windowTitle;
    }

    public int getSceneState() {
        return this.mSceneState;
    }

    public void setSceneState(int sceneState) {
        this.mSceneState = sceneState;
    }

    public boolean isHaveBoundFlag() {
        return this.mIsHaveBoundFlag;
    }

    public void setHaveBoundFlag(boolean isHaveBoundFlag) {
        this.mIsHaveBoundFlag = isHaveBoundFlag;
    }

    public boolean isHaveClassName() {
        return this.mIsHaveClassName;
    }

    public void setHaveClassName(boolean isHaveClassName) {
        this.mIsHaveClassName = isHaveClassName;
    }

    public boolean isHaveTitle() {
        return this.mIsHaveTitle;
    }

    public void setHaveTitle(boolean isHaveTitle) {
        this.mIsHaveTitle = isHaveTitle;
    }

    public boolean isHaveIgnoreClass() {
        return this.mIsHaveIgnoreClass;
    }

    public void setHaveIgnoreClass(boolean isHaveIgnoreClass) {
        this.mIsHaveIgnoreClass = isHaveIgnoreClass;
    }

    public Rect getBounds() {
        return this.mBounds;
    }

    public void setBounds(Rect bounds) {
        this.mBounds = bounds;
    }
}
