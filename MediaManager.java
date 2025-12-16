import java.io.*;
import java.util.*;

public class MediaManager {
    private ArrayList<Product> mediaList = new ArrayList<>();

    // Load data from the CSV file
    public void loadMediaData(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1); // include empty strings
                if (data.length < 2) continue;

                String type = data[1].trim().toLowerCase();

                switch (type) {
                    case "movie":
                        mediaList.add(new Movie(
                                data[0], data[2], data[3], data[4],
                                Integer.parseInt(data[5]), data[6],
                                Integer.parseInt(data[7]), data[8]
                        ));
                        break;

                    case "tv show":
                        mediaList.add(new TVShow(
                                data[0], data[2], data[3], data[4],
                                Integer.parseInt(data[5]), data[6],
                                Integer.parseInt(data[7]), data[8]
                        ));
                        break;

                    case "video game":
                        mediaList.add(new VideoGame(
                                data[0], data[2], data[3],
                                Integer.parseInt(data[4]), data[5],
                                data[6], Double.parseDouble(data[7])
                        ));
                        break;

                    case "music album":
                        mediaList.add(new MusicAlbum(
                                data[0], Integer.parseInt(data[2]), data[3],
                                data[4], Double.parseDouble(data[5]),
                                Integer.parseInt(data[6]),
                                Double.parseDouble(data[7]), data[8]
                        ));
                        break;

                    default:
                        // skip unknown type
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // --- ANALYSIS METHODS ---
    public int getTotalProducts() { return mediaList.size(); }
    public long countMovies() { return mediaList.stream().filter(m -> m instanceof Movie).count(); }
    public long countTVShows() { return mediaList.stream().filter(m -> m instanceof TVShow).count(); }
    public long countVideoGames() { return mediaList.stream().filter(m -> m instanceof VideoGame).count(); }
    public long countMusicAlbums() { return mediaList.stream().filter(m -> m instanceof MusicAlbum).count(); }

    public Product findOldestProduct() {
        return mediaList.stream().min(Comparator.comparing(Product::getReleaseYear)).orElse(null);
    }

    public MusicAlbum findMostPopularMusicAlbum() {
        return mediaList.stream()
                .filter(p -> p instanceof MusicAlbum)
                .map(p -> (MusicAlbum) p)
                .max(Comparator.comparing(MusicAlbum::getGlobalSales))
                .orElse(null);
    }

    public VideoGame findMostPopularVideoGame() {
        return mediaList.stream()
                .filter(p -> p instanceof VideoGame)
                .map(p -> (VideoGame) p)
                .max(Comparator.comparing(VideoGame::getCopiesSold))
                .orElse(null);
    }

    public String findMostCommonAgeRating() {
        Map<String, Integer> ratings = new HashMap<>();
        for (Product p : mediaList) {
            if (p instanceof Movie)
                ratings.merge(((Movie) p).getRating(), 1, Integer::sum);
            else if (p instanceof TVShow)
                ratings.merge(((TVShow) p).getRating(), 1, Integer::sum);
        }
        return ratings.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
    }

    public Movie findShortestMovie() {
        return mediaList.stream()
                .filter(p -> p instanceof Movie)
                .map(p -> (Movie) p)
                .min(Comparator.comparing(Movie::getDuration))
                .orElse(null);
    }

    public MusicAlbum findShortestMusicAlbum() {
        return mediaList.stream()
                .filter(p -> p instanceof MusicAlbum)
                .map(p -> (MusicAlbum) p)
                .min(Comparator.comparing(MusicAlbum::getDuration))
                .orElse(null);
    }

    // --- For quick internal check ---
    public static void main(String[] args) {
        MediaManager mm = new MediaManager();
        mm.loadMediaData("project1dataset.csv");
        System.out.println("Loaded products: " + mm.getTotalProducts()); // should print 41
    }
}
