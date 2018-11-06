import java.util.Comparator;

class ComposedComparator implements Comparator<Song>{

    private Comparator<Song> song1, song2;

    public ComposedComparator(Comparator<Song> song1, Comparator<Song> song2) {
        this.song1 = song1;
        this.song2 = song2;
    }

    @Override
    public int compare(Song s1, Song s2) {
        if(song1.compare(s1, s2) == 0) {
            return song2.compare(s1, s2);
        }
        else {
            return song1.compare(s1, s2);
        }
    }
}