package Podcast.Touch.PodcastDetailTemplateInterface.v1_0;

/* JADX INFO: loaded from: classes.dex */
public class FollowState {
    public static final String FOLLOW = "FOLLOW";
    public static final String UNFOLLOW = "UNFOLLOW";
    private static final String[] values = {"FOLLOW", "UNFOLLOW"};

    private FollowState() {
        throw new UnsupportedOperationException();
    }

    public static String[] values() {
        return values;
    }
}
