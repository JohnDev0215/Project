package PlaybackMetricsInterface.v1_0;

/* JADX INFO: loaded from: classes.dex */
public class PlaybackSignalType {
    public static final String PLAYBACK_STARTED = "PLAYBACK_STARTED";
    public static final String PLAYBACK_PAUSED = "PLAYBACK_PAUSED";
    public static final String PLAYBACK_RESUMED = "PLAYBACK_RESUMED";
    public static final String PLAYBACK_STOPPED = "PLAYBACK_STOPPED";
    public static final String PLAYBACK_FINISHED = "PLAYBACK_FINISHED";
    public static final String PLAYBACK_ERROR = "PLAYBACK_ERROR";
    public static final String PLAYBACK_SCRUBBED = "PLAYBACK_SCRUBBED";
    public static final String PLAYBACK_REBUFFERED = "PLAYBACK_REBUFFERED";
    public static final String PLAYBACK_PRE_SCRUBBED = "PLAYBACK_PRE_SCRUBBED";
    public static final String PLAYBACK_POST_SCRUBBED = "PLAYBACK_POST_SCRUBBED";
    public static final String BITE_PLAYBACK_STARTED = "BITE_PLAYBACK_STARTED";
    public static final String BITE_PLAYBACK_PAUSED = "BITE_PLAYBACK_PAUSED";
    public static final String BITE_PLAYBACK_RESUMED = "BITE_PLAYBACK_RESUMED";
    public static final String BITE_PLAYBACK_STOPPED = "BITE_PLAYBACK_STOPPED";
    public static final String BITE_PLAYBACK_FINISHED = "BITE_PLAYBACK_FINISHED";
    public static final String BITE_PLAYBACK_ERROR = "BITE_PLAYBACK_ERROR";
    public static final String BITE_PLAYBACK_REBUFFERED = "BITE_PLAYBACK_REBUFFERED";
    private static final String[] values = {PLAYBACK_STARTED, PLAYBACK_PAUSED, PLAYBACK_RESUMED, PLAYBACK_STOPPED, PLAYBACK_FINISHED, PLAYBACK_ERROR, PLAYBACK_SCRUBBED, PLAYBACK_REBUFFERED, PLAYBACK_PRE_SCRUBBED, PLAYBACK_POST_SCRUBBED, BITE_PLAYBACK_STARTED, BITE_PLAYBACK_PAUSED, BITE_PLAYBACK_RESUMED, BITE_PLAYBACK_STOPPED, BITE_PLAYBACK_FINISHED, BITE_PLAYBACK_ERROR, BITE_PLAYBACK_REBUFFERED};

    private PlaybackSignalType() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
