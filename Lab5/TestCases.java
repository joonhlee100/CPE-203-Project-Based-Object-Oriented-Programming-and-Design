import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.*;
import java.util.Arrays;

import org.junit.Test;
import org.junit.Before;

public class TestCases
{
   private static final Song[] songs = new Song[] {
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Gerry Rafferty", "Baker Street", 1978)
      };

   @Test
   public void testArtistComparator()
   {
       Song.ArtistComparator sortedSongs = new Song.ArtistComparator();
       Song[] s = new Song[]{songs[1], songs[2]};
       Arrays.sort(s, sortedSongs);
       assertEquals("Avett Brothers", s[0].getArtist());
       assertEquals("Rogue Wave", s[1].getArtist());

   }

   @Test
   public void testLambdaTitleComparator()
   {
       Comparator<Song> sortByTitle = (Song song1, Song song2) -> song1.getTitle().compareTo(song2.getTitle());
       Song[] s = new Song[]{songs[5], songs[6]};
       Arrays.sort(s, sortByTitle);
       assertEquals("Baker Street", s[0].getTitle());
       assertEquals("Bohemian Rhapsody", s[1].getTitle());
   }

   @Test
   public void testYearExtractorComparator()
   {
       Comparator<Song> sortByYear = Comparator.comparing(Song::getYear, (s1,s2) -> s2.compareTo(s1));
       Song[] s = new Song[]{songs[3], songs[5]};
       Arrays.sort(s, sortByYear);
       assertEquals(1998, s[0].getYear());
       assertEquals(1997, s[1].getYear());
   }

   @Test
   public void testComposedComparator()
   {
       Song.ArtistComparator sortedSongs = new Song.ArtistComparator();
       Comparator<Song> sortByYear = Comparator.comparing(Song::getYear, (s1,s2) -> s1.compareTo(s2));
       Comparator<Song> sortByTitle = (Song s1, Song s2) -> s1.getTitle().compareTo(s2.getTitle());

       Comparator<Song> artistAndYear = new ComposedComparator(sortedSongs, sortByYear);
       Song[] s1 = new Song[] {songs[3], songs[7]};
       Arrays.sort(s1, artistAndYear);
       assertEquals("Gerry Rafferty", s1[0].getArtist());
       assertEquals(1998, s1[1].getYear());

       Comparator<Song> titleAndArtist = new ComposedComparator(sortByTitle, sortedSongs);
       Song[] s2 = new Song[] {songs[3], songs[5]};
       Arrays.sort(s2, titleAndArtist);
       assertEquals("Foo Fighters", s2[0].getArtist());
       assertEquals("Baker Street", s2[1].getTitle());

       Comparator<Song> yearAndTitle = new ComposedComparator(sortByYear, sortByTitle);
       Song[] s3 = new Song[] {songs[1], songs[0]};
       Arrays.sort(s3, yearAndTitle);
       assertEquals(2005, s3[0].getYear());
       assertEquals("The Mariner's Revenge Song", s3[1].getTitle());
   }

   @Test
   public void testThenComparing()
   {
       Song.ArtistComparator sortedSongs = new Song.ArtistComparator();
       Comparator<Song> thenCompare = Comparator.comparing(Song::getTitle, (s1,s2) -> s1.compareTo(s2))
               .thenComparing(sortedSongs);

       Song[] s1 = new Song[] {songs[0], songs[1]};
       Arrays.sort(s1, thenCompare);
       assertEquals("Rogue Wave", s1[0].getArtist());
       assertEquals("Decemberists", s1[1].getArtist());
   }

   @Test
   public void runSort()
   {
       Comparator<Song> testCompare = Comparator.comparing(Song::getArtist, (s1,s2) -> s1.compareTo(s2))
               .thenComparing(Song::getTitle, (s1,s2) -> s1.compareTo(s2))
               .thenComparing(Song::getYear, (s1,s2) -> s1.compareTo(s2));

      List<Song> songList = new ArrayList<>(Arrays.asList(songs));
      List<Song> expectedList = Arrays.asList(
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Gerry Rafferty", "Baker Street", 1978),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
         );

      songList.sort(testCompare);

      assertEquals(songList, expectedList);
   }
}
