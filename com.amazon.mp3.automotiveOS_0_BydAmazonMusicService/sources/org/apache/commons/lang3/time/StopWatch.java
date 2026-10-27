package org.apache.commons.lang3.time;

import java.util.Objects;

/* JADX INFO: loaded from: classes7.dex */
public class StopWatch {
    private final String message;
    private State runningState;
    private SplitState splitState;
    private long startTime;
    private long startTimeMillis;
    private long stopTime;

    private enum SplitState {
        SPLIT,
        UNSPLIT
    }

    private enum State {
        RUNNING { // from class: org.apache.commons.lang3.time.StopWatch.State.1
            @Override // org.apache.commons.lang3.time.StopWatch.State
            boolean isStarted() {
                return true;
            }

            @Override // org.apache.commons.lang3.time.StopWatch.State
            boolean isStopped() {
                return false;
            }

            @Override // org.apache.commons.lang3.time.StopWatch.State
            boolean isSuspended() {
                return false;
            }
        },
        STOPPED { // from class: org.apache.commons.lang3.time.StopWatch.State.2
            @Override // org.apache.commons.lang3.time.StopWatch.State
            boolean isStarted() {
                return false;
            }

            @Override // org.apache.commons.lang3.time.StopWatch.State
            boolean isStopped() {
                return true;
            }

            @Override // org.apache.commons.lang3.time.StopWatch.State
            boolean isSuspended() {
                return false;
            }
        },
        SUSPENDED { // from class: org.apache.commons.lang3.time.StopWatch.State.3
            @Override // org.apache.commons.lang3.time.StopWatch.State
            boolean isStarted() {
                return true;
            }

            @Override // org.apache.commons.lang3.time.StopWatch.State
            boolean isStopped() {
                return false;
            }

            @Override // org.apache.commons.lang3.time.StopWatch.State
            boolean isSuspended() {
                return true;
            }
        },
        UNSTARTED { // from class: org.apache.commons.lang3.time.StopWatch.State.4
            @Override // org.apache.commons.lang3.time.StopWatch.State
            boolean isStarted() {
                return false;
            }

            @Override // org.apache.commons.lang3.time.StopWatch.State
            boolean isStopped() {
                return true;
            }

            @Override // org.apache.commons.lang3.time.StopWatch.State
            boolean isSuspended() {
                return false;
            }
        };

        abstract boolean isStarted();

        abstract boolean isStopped();

        abstract boolean isSuspended();
    }

    public StopWatch() {
        this(null);
    }

    public StopWatch(String str) {
        this.runningState = State.UNSTARTED;
        this.splitState = SplitState.UNSPLIT;
        this.message = str;
    }

    public String formatTime() {
        return DurationFormatUtils.formatDurationHMS(getTime());
    }

    public long getNanoTime() {
        long jNanoTime;
        long j;
        State state = this.runningState;
        if (state == State.STOPPED || state == State.SUSPENDED) {
            jNanoTime = this.stopTime;
            j = this.startTime;
        } else {
            if (state == State.UNSTARTED) {
                return 0L;
            }
            if (state == State.RUNNING) {
                jNanoTime = System.nanoTime();
                j = this.startTime;
            } else {
                throw new RuntimeException("Illegal running state has occurred.");
            }
        }
        return jNanoTime - j;
    }

    public long getTime() {
        return getNanoTime() / 1000000;
    }

    public boolean isStarted() {
        return this.runningState.isStarted();
    }

    public void start() {
        State state = this.runningState;
        if (state == State.STOPPED) {
            throw new IllegalStateException("Stopwatch must be reset before being restarted. ");
        }
        if (state != State.UNSTARTED) {
            throw new IllegalStateException("Stopwatch already started. ");
        }
        this.startTime = System.nanoTime();
        this.startTimeMillis = System.currentTimeMillis();
        this.runningState = State.RUNNING;
    }

    public void stop() {
        State state = this.runningState;
        State state2 = State.RUNNING;
        if (state != state2 && state != State.SUSPENDED) {
            throw new IllegalStateException("Stopwatch is not running. ");
        }
        if (state == state2) {
            this.stopTime = System.nanoTime();
        }
        this.runningState = State.STOPPED;
    }

    public String toString() {
        String string = Objects.toString(this.message, "");
        String time = formatTime();
        if (string.isEmpty()) {
            return time;
        }
        return string + " " + time;
    }
}
