package com.amazon.mp3.util;

import java.util.HashMap;
import java.util.Stack;

/* JADX INFO: loaded from: classes4.dex */
public class Profiler {
    private static boolean ENABLED = false;
    private static final String TAG = "Profiler";
    private static HashMap<Long, Stack<ProfilerNode>> sThreadStacks = new HashMap<>();
    private static HashMap<String, ProfilerLocation> sProfilerLocations = new HashMap<>();

    public static void enable() {
    }

    public static void disable() {
        ENABLED = false;
    }

    private static class ProfilerLocation {
        private String mFileLocation;
        private final String mTag;
        private long mCalls = 0;
        private long mTotalTime = 0;
        private boolean mMultiThread = false;
        private final long mStartThreadId = Thread.currentThread().getId();

        public ProfilerLocation(String tag) {
            this.mFileLocation = "<unknown>";
            this.mTag = tag;
            for (StackTraceElement stackTraceElement : new Throwable().getStackTrace()) {
                if (!stackTraceElement.getClassName().contains(Profiler.class.getName())) {
                    this.mFileLocation = stackTraceElement.toString();
                    return;
                }
            }
        }

        public void recordTime(long timeAtLocation) {
            if (this.mMultiThread) {
                synchronized (this) {
                    this.mCalls++;
                    this.mTotalTime += timeAtLocation;
                }
            } else {
                this.mCalls++;
                this.mTotalTime += timeAtLocation;
            }
        }

        public String getTag() {
            return this.mTag;
        }

        public long getCallCount() {
            return this.mCalls;
        }

        public long getTotalTime() {
            return this.mTotalTime;
        }

        public long getAverageTime() {
            long j = this.mCalls;
            if (j != 0) {
                return this.mTotalTime / j;
            }
            return 0L;
        }

        public String getFileLocation() {
            return this.mFileLocation;
        }
    }

    private static class ProfilerNode {
        ProfilerLocation mLocation;
        public long mStartTime = 0;

        public ProfilerNode(ProfilerLocation location) {
            this.mLocation = location;
            begin();
        }

        public void begin() {
            this.mStartTime = System.currentTimeMillis();
        }

        public void end() {
            if (this.mStartTime != 0) {
                this.mLocation.recordTime(System.currentTimeMillis() - this.mStartTime);
                this.mStartTime = 0L;
            }
        }
    }

    private static Stack<ProfilerNode> getStack() {
        return getStack(Thread.currentThread().getId());
    }

    private static Stack<ProfilerNode> getStack(long threadId) {
        Stack<ProfilerNode> stack = sThreadStacks.get(Long.valueOf(threadId));
        if (stack != null) {
            return stack;
        }
        Stack<ProfilerNode> stack2 = new Stack<>();
        sThreadStacks.put(Long.valueOf(threadId), stack2);
        return stack2;
    }

    private static synchronized ProfilerLocation getLocation(String tag) {
        ProfilerLocation profilerLocation;
        profilerLocation = sProfilerLocations.get(tag);
        if (profilerLocation == null) {
            profilerLocation = new ProfilerLocation(tag);
            sProfilerLocations.put(tag, profilerLocation);
        }
        return profilerLocation;
    }

    public static void begin(String tag) {
        if (ENABLED) {
            long id = Thread.currentThread().getId();
            ProfilerLocation location = getLocation(tag);
            if (location.mStartThreadId != id) {
                location.mMultiThread = true;
            }
            getStack(id).push(new ProfilerNode(location));
        }
    }

    public static void end() {
        if (ENABLED) {
            Stack<ProfilerNode> stack = getStack();
            if (stack.empty()) {
                Log.warning(TAG, "Unbalanced profiler end call - empty stack");
            } else {
                stack.pop().end();
            }
        }
    }

    public static final void logAll() {
        if (sProfilerLocations.size() > 0) {
            Log.info(TAG, "============= Begin Time report for %d time points ==============", Integer.valueOf(sProfilerLocations.size()));
            for (ProfilerLocation profilerLocation : sProfilerLocations.values()) {
                Log.info(TAG, "%s %s: total time over %d calls: %dms (%d average) - first thread %d", profilerLocation.getFileLocation(), profilerLocation.getTag(), Long.valueOf(profilerLocation.getCallCount()), Long.valueOf(profilerLocation.getTotalTime()), Long.valueOf(profilerLocation.getAverageTime()), Integer.valueOf((int) profilerLocation.mStartThreadId));
            }
            Log.info(TAG, "=============   End Time report for %d time points ==============", Integer.valueOf(sProfilerLocations.size()));
        }
    }
}
