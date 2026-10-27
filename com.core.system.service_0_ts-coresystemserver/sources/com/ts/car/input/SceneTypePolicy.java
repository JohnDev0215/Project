package com.ts.car.input;

/* JADX INFO: loaded from: classes.dex */
public class SceneTypePolicy {
    private static final int KEYDODE_UNDEFINE = -1;
    private static final int PID_UNKNOWN = -1;
    int callingPid;
    int keycode;
    int policy;
    int priority;
    int sceneType;

    public SceneTypePolicy() {
        this(-1);
    }

    public SceneTypePolicy(int keycode) {
        this.sceneType = -1;
        this.priority = 0;
        this.policy = 1;
        this.callingPid = -1;
        this.keycode = keycode;
    }

    public SceneTypePolicy(int sceneType, int priority, int policy, int keycode) {
        this.sceneType = -1;
        this.priority = 0;
        this.policy = 1;
        this.callingPid = -1;
        this.sceneType = sceneType;
        this.priority = priority;
        this.policy = policy;
        this.keycode = keycode;
    }

    public int getSceneType() {
        return this.sceneType;
    }

    public void setSceneType(int sceneType) {
        this.sceneType = sceneType;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPolicy() {
        return this.policy;
    }

    public void setPolicy(int policy) {
        this.policy = policy;
    }

    public int getKeycode() {
        return this.keycode;
    }

    public void setCallingPid(int callingPid) {
        this.callingPid = callingPid;
    }

    public int getCallingPid() {
        return this.callingPid;
    }
}
