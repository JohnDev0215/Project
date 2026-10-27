package com.amazon.musicensembleservice.brush;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class LiveStream extends MusicEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.LiveStream");
    private List<Artist> artists;
    private String id;
    private List<Image> images;
    private List<String> languagesOfPerformance;
    private String name;
    private StreamProvider streamProvider;
    private String title;
    private String viewers;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Entity entity) {
        if (entity == null) {
            return -1;
        }
        if (entity == this) {
            return 0;
        }
        if (!(entity instanceof LiveStream)) {
            return 1;
        }
        LiveStream liveStream = (LiveStream) entity;
        StreamProvider streamProvider = getStreamProvider();
        StreamProvider streamProvider2 = liveStream.getStreamProvider();
        if (streamProvider != streamProvider2) {
            if (streamProvider == null) {
                return -1;
            }
            if (streamProvider2 == null) {
                return 1;
            }
            int iCompareTo = streamProvider.compareTo(streamProvider2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String viewers = getViewers();
        String viewers2 = liveStream.getViewers();
        if (viewers != viewers2) {
            if (viewers == null) {
                return -1;
            }
            if (viewers2 == null) {
                return 1;
            }
            int iCompareTo2 = viewers.compareTo(viewers2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<Artist> artists = getArtists();
        List<Artist> artists2 = liveStream.getArtists();
        if (artists != artists2) {
            if (artists == null) {
                return -1;
            }
            if (artists2 == null) {
                return 1;
            }
            if (artists instanceof Comparable) {
                int iCompareTo3 = ((Comparable) artists).compareTo(artists2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!artists.equals(artists2)) {
                int iHashCode = artists.hashCode();
                int iHashCode2 = artists2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<String> languagesOfPerformance = getLanguagesOfPerformance();
        List<String> languagesOfPerformance2 = liveStream.getLanguagesOfPerformance();
        if (languagesOfPerformance != languagesOfPerformance2) {
            if (languagesOfPerformance == null) {
                return -1;
            }
            if (languagesOfPerformance2 == null) {
                return 1;
            }
            if (languagesOfPerformance instanceof Comparable) {
                int iCompareTo4 = ((Comparable) languagesOfPerformance).compareTo(languagesOfPerformance2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!languagesOfPerformance.equals(languagesOfPerformance2)) {
                int iHashCode3 = languagesOfPerformance.hashCode();
                int iHashCode4 = languagesOfPerformance2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String name = getName();
        String name2 = liveStream.getName();
        if (name != name2) {
            if (name == null) {
                return -1;
            }
            if (name2 == null) {
                return 1;
            }
            int iCompareTo5 = name.compareTo(name2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String id = getId();
        String id2 = liveStream.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo6 = id.compareTo(id2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        List<Image> images = getImages();
        List<Image> images2 = liveStream.getImages();
        if (images != images2) {
            if (images == null) {
                return -1;
            }
            if (images2 == null) {
                return 1;
            }
            if (images instanceof Comparable) {
                int iCompareTo7 = ((Comparable) images).compareTo(images2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!images.equals(images2)) {
                int iHashCode5 = images.hashCode();
                int iHashCode6 = images2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        String title = getTitle();
        String title2 = liveStream.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo8 = title.compareTo(title2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof LiveStream)) {
            return false;
        }
        LiveStream liveStream = (LiveStream) obj;
        return super.equals(obj) && internalEqualityCheck(getStreamProvider(), liveStream.getStreamProvider()) && internalEqualityCheck(getViewers(), liveStream.getViewers()) && internalEqualityCheck(getArtists(), liveStream.getArtists()) && internalEqualityCheck(getLanguagesOfPerformance(), liveStream.getLanguagesOfPerformance()) && internalEqualityCheck(getName(), liveStream.getName()) && internalEqualityCheck(getId(), liveStream.getId()) && internalEqualityCheck(getImages(), liveStream.getImages()) && internalEqualityCheck(getTitle(), liveStream.getTitle());
    }

    public List<Artist> getArtists() {
        return this.artists;
    }

    public String getId() {
        return this.id;
    }

    public List<Image> getImages() {
        return this.images;
    }

    public List<String> getLanguagesOfPerformance() {
        return this.languagesOfPerformance;
    }

    public String getName() {
        return this.name;
    }

    public StreamProvider getStreamProvider() {
        return this.streamProvider;
    }

    public String getTitle() {
        return this.title;
    }

    public String getViewers() {
        return this.viewers;
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getStreamProvider(), getViewers(), getArtists(), getLanguagesOfPerformance(), getName(), getId(), getImages(), getTitle());
    }

    public void setArtists(List<Artist> list) {
        this.artists = list;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setImages(List<Image> list) {
        this.images = list;
    }

    public void setLanguagesOfPerformance(List<String> list) {
        this.languagesOfPerformance = list;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setStreamProvider(StreamProvider streamProvider) {
        this.streamProvider = streamProvider;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setViewers(String str) {
        this.viewers = str;
    }
}
